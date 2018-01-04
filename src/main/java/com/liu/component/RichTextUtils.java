/*
 * Copyright © 2013-2016 BLT, Co., Ltd. All Rights Reserved.
 */

package com.liu.component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.liu.codemap.GlobalErrorCode;
import com.liu.exception.PlatformException;

/**
 * 富文本保存为静态资源
 * 
 * @author liudi
 */
@Component
public class RichTextUtils {

    @Autowired
    private FileUploadUtils fileStorageComponent;
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    public String saveHtml(String oldUrl, String content) throws PlatformException {
        if (StringUtils.isEmpty(content)) {
            return oldUrl;
        }
        try {
            String cleanedContent = ManualHtmlCleanerUtils.clean(content);
            //如果OldUrl不为空，则取旧的HTML内容
            if (StringUtils.isNotEmpty(oldUrl)) {
                InputStream stream = fileStorageComponent.download(oldUrl);
                if (stream != null) {
                    try {
                        String oldContent = IOUtils.toString(stream, StandardCharsets.UTF_8);
                        if (oldContent.equals(cleanedContent)) {
                            return oldUrl;
                        }
                    } catch (IOException e) {
                        logger.warn("内容读取失败：{}", oldUrl);
                    }
                }
            }
            
            //保存内容
            InputStream stream =  IOUtils.toInputStream(cleanedContent, StandardCharsets.UTF_8);
            String newUrl =
                    fileStorageComponent.save(System.currentTimeMillis() + ".html", stream , false);
            
            //删除旧文件
            fileStorageComponent.deleteFile(oldUrl);

            return newUrl;
        } catch (IOException ioe) {
            throw new PlatformException(GlobalErrorCode.QJZ0002.getCode(),
                    GlobalErrorCode.QJZ0002.getName(), ioe.getMessage());
        }
    }

    public String readHtml(String url) throws PlatformException {
        InputStream stream = fileStorageComponent.download(url);
        if (stream != null) {
            try {
                String oldContent = IOUtils.toString(stream, StandardCharsets.UTF_8);
                return ManualHtmlCleanerUtils.prune(oldContent);
            } catch (IOException e) {
                logger.warn("内容读取失败：{}", url);
            }
        }
        return null;
    }

}
