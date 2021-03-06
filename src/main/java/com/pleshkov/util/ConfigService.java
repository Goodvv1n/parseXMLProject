package com.pleshkov.util;

import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Класс для получения настроек из application.properties
 * @author pleshkov on 04.10.2018.
 */
public class ConfigService {
    private static final String APP_CFG_FILE = "application.properties";
    private static final Logger LOG = Logger.getLogger(ConfigService.class);
    private static ConfigService instance;
    private String folderPath;
    private boolean enableLoading;

    private ConfigService() {
        Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            inputStream = getClass().getClassLoader().getResourceAsStream(APP_CFG_FILE);
            if (inputStream != null) {
                properties.load(inputStream);
                folderPath = properties.getProperty("xmlFolder");
                enableLoading = Boolean.parseBoolean(properties.getProperty("enableLoading"));
            } else {
                throw new FileNotFoundException("Property file " + APP_CFG_FILE + " not found in classpath root.");
            }
        } catch (IOException e) {
            LOG.error(e.getCause().getMessage());
        }finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    LOG.error("Все очень плохо. Не смогли закрыть inputStream");
                }
            }
        }
    }

    public static ConfigService getInstance(){
        if (instance == null){
            instance = new ConfigService();
        }
        return instance;
    }

    /**
     * включен ли режим загрузки файлов
     * @return результат
     */
    public boolean isEnableLoading() {
        return enableLoading;
    }

    /**
     * Получить путь к каталогу, где хранятся XML файлы
     * @return рещультат
     */
    public String getFolderPath() {
        return folderPath;
    }
}
