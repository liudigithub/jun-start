/*
 * Copyright © 2013-2016 BLT, Co., Ltd. All Rights Reserved.
 */

package com.liu.component;

import java.io.IOException;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.CompactHtmlSerializer;
import org.htmlcleaner.DoctypeToken;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.HtmlSerializer;
import org.htmlcleaner.TagNode;

/**
 * 说明书HTML格式化
 * 
 * @author liudi
 */
public class ManualHtmlCleanerUtils {

    /**
     * 格式化HTML
     * @param pruneHtml 格式化前HTML
     * @return 格式化后HTML
     * @throws IOException IO异常
     * @since  3.0
     */
    public static String clean(String pruneHtml) throws IOException {
        final  CleanerProperties propertyies = new CleanerProperties();
        propertyies.setUseEmptyElementTags(true);
        propertyies.setAdvancedXmlEscape(true);
        propertyies.setHtmlVersion(5);
        propertyies.setOmitDoctypeDeclaration(false);
        //2016-11-09 不生成<?xml .../>文件头
        propertyies.setOmitXmlDeclaration(true);
        propertyies.setNamespacesAware(true);
        propertyies.setCharset("utf-8");
        HtmlCleaner cleaner = new  HtmlCleaner(propertyies);
        
        TagNode tagNode = cleaner.clean(pruneHtml);
        TagNode[] tags = tagNode.getElementsByName("head", true);
        
        //HTML5文件头
        DoctypeToken docType = new DoctypeToken("html", null, null, null);
        tagNode.setDocType(docType);
        
        if (tags.length > 0) {

            //编码
            TagNode charsetTag = new TagNode("meta");
            charsetTag.addAttribute("charset", "UTF-8");
            
            TagNode metaTag = new TagNode("meta");
            metaTag.addAttribute("name", "viewport");
            metaTag.addAttribute("content", "width=device-width,user-scalable=no,initial-scale=1");
            TagNode metaCharsetTag = new TagNode("meta");
            metaCharsetTag.addAttribute("equiv", "Content-Type");
            metaCharsetTag.addAttribute("content", "text/html; charset=utf-8");
            TagNode cssTag = new TagNode("link");
            cssTag.addAttribute("href", "/static/common.css");
            cssTag.addAttribute("rel", "stylesheet");
            cssTag.addAttribute("type", "text/css");

            TagNode headTag = tags[0];
            headTag.addChild(charsetTag);
            headTag.addChild(metaTag);
            headTag.addChild(metaCharsetTag);
            headTag.addChild(cssTag);
        }
        
        HtmlSerializer serializer = new CompactHtmlSerializer(propertyies);
        return serializer.getAsString(tagNode);
    }
    
    /**
     * 获取HTML中的BODY内容
     * @param fullHtml HTML内容
     * @return 清除后HTML
     * @since  3.0
     */
    public static String prune(String fullHtml) {
        HtmlCleaner cleaner = new  HtmlCleaner();
        CleanerProperties propertyies = cleaner.getProperties();
        propertyies.setUseEmptyElementTags(true);
        propertyies.setAdvancedXmlEscape(true);
        propertyies.setHtmlVersion(5);
        propertyies.setCharset("utf-8");
        TagNode html = cleaner.clean(fullHtml);
        TagNode[] body = html.getElementsByName("body", false);
        if (body.length > 0) {
            return cleaner.getInnerHtml(body[0]);
        } else {
            return fullHtml;
        }
    }

}
