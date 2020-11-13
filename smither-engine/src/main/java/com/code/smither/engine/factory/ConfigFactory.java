package com.code.smither.engine.factory;

import com.code.smither.engine.EngineConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigFactory {


    public static Properties loadProperties(String path) throws IOException {
        InputStream stream = ClassLoader.getSystemResourceAsStream(path);
        if (stream == null) {
            throw new RuntimeException("找不到配置文件：" + path);
        }
        Properties property = new Properties();
        property.load(stream);
        stream.close();
        return property;
    }

    public static <T extends EngineConfig> T loadConfig(Properties properties, T config) {

        config.setNow(properties.getProperty("code.smither.now",config.getNow()));

        config.setTemplateFtlOnly("true".equalsIgnoreCase(properties.getProperty("code.smither.template.ftl-only")));
        config.setTemplatePath(properties.getProperty("code.smither.template.path",config.getTemplatePath()));
        config.setTemplateCharset(properties.getProperty("code.smither.template.charset",config.getTemplateCharset()));

        config.setTargetPath(properties.getProperty("code.smither.target.path",config.getTargetPath()));
        config.setTargetCharset(properties.getProperty("code.smither.target.charset",config.getTargetCharset()));

        config.setIncludeFile(properties.getProperty("code.smither.template.include.file",config.getIncludeFile()));
        config.setIncludePath(properties.getProperty("code.smither.template.include.path",config.getIncludePath()));
        config.setFilterFile(properties.getProperty("code.smither.template.filter.file",config.getFilterFile()));
        config.setFilterPath(properties.getProperty("code.smither.template.filter.path",config.getFilterPath()));
        return config;
    }

}