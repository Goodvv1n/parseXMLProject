package com.pleshkov.util;

import com.pleshkov.sheduled.SheduledService;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author pleshkov on 04.10.2018.
 */
public class ConfigService {
    private static final String APP_CFG_FILE = "application.properties";
    private static final Logger LOG = Logger.getLogger(SheduledService.class);
    private static ConfigService instance;
    private String folderPath;

    private ConfigService() {
        Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            inputStream = getClass().getClassLoader().getResourceAsStream(APP_CFG_FILE);
            if (inputStream != null) {
                properties.load(inputStream);
                folderPath = properties.getProperty("xmlFolder");
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
     * Получить путь к каталогу, где хранятся XML файлы
     * @return рещультат
     */
    public String getFolderPath() {
        return folderPath;
    }
}
