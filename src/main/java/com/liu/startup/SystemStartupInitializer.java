package com.liu.startup;

import java.io.File;

import javax.servlet.ServletContext;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.FileConfiguration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.XmlWebApplicationContext;

/**
 * 初始化
 * 
 * @author liudi
 * @date 2017年12月26日
 */
@Component
public class SystemStartupInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private final Logger logger = LoggerFactory.getLogger(SystemStartupInitializer.class);

    private ApplicationContext applicationContext = null;

    private ServletContext servletContext;

    private boolean inited = false;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (!inited) {
            try {
                applicationContext = event.getApplicationContext();

                servletContext =
                        ((XmlWebApplicationContext) applicationContext).getServletContext();

                // 加载服务器相关配置
                String ConfBase = System.getProperty("liu.conf.base");
                
                File file = new File(FilenameUtils.concat(ConfBase, "base-server.conf"));
                FileConfiguration serverConfig = new PropertiesConfiguration();
                serverConfig.setEncoding("UTF-8");
                serverConfig.setFile(file);
                try {
                    serverConfig.load();
                } catch (ConfigurationException e) {
                    e.printStackTrace();
                }

                servletContext.setAttribute("imageRoot", serverConfig.getString("image.server.root", "http://118.184.32.71/"));
                servletContext.setAttribute("serverRoot", serverConfig.getString("server.root", "http://118.184.32.71:8080/test-project/"));
                
            } finally {
                logger.debug("System startup initialize finished");
                inited = true;
            }
        }
    }

}
