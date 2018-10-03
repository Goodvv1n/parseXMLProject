package com.pleshkov.sheduled;

import com.pleshkov.data.entity.LoadInfo;
import com.pleshkov.exceptions.SAPIException;
import com.pleshkov.services.LoadInfoService;
import com.pleshkov.xmlBean.Sales;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Сервис для управлением фоновой задачей
 * @author pleshkov on 03.10.2018.
 */
@Service
public class SheduledService {

    @Autowired
    LoadInfoService service;

    private static final Logger LOG = Logger.getLogger(SheduledService.class);
    private static final String APP_CFG_FILE = "application.properties";
    private static final String CRON = "*/10 * * * * *";

    @Scheduled(cron = CRON)
    public void scanXMLFiles() throws SAPIException {
        String path = getFolderPath();
        File dir = new File(path); //path указывает на директорию
        File[] arrFiles = dir.listFiles();
        if (arrFiles == null){
            throw new SAPIException("Path " + path + " not found");
        }
        List<File> lst = Arrays.asList(arrFiles);
        for (File file : lst){
            loadSalefromFile(file, Sales.class);
        }
    }

    /**
     * Получить путь к каталогу, где хранятся XML файлы
     * @return рещультат
     */
    private String getFolderPath()  {
        Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            inputStream = getClass().getClassLoader().getResourceAsStream(APP_CFG_FILE);
            if (inputStream != null) {
                properties.load(inputStream);
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
        return properties.getProperty("xmlFolder");
    }

    /**
     * Парсинг и загрузка данных из файла
     * @param file файл
     * @param clazz Класс загружаемых объектов
     */
    private void loadSalefromFile(File file, Class clazz) {
        if (!isNewFile(file)){
            return;
        }
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(clazz);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Sales sales = (Sales) jaxbUnmarshaller.unmarshal(file);
            service.saveInfo(createLoadInfo(file.getName(), sales));
        } catch (JAXBException e) {
            LOG.info("File " + file.getPath() + " has an incorrect structure. Data not loaded.");
            service.saveInfo(createLoadInfo(file.getName(), e));
        }
    }

    /**
     * Проверка, новый ли это файл.
     * @param file файл
     * @return результат
     */
    private boolean isNewFile(File file){
        return !service.isLoadInfoExist(file.getName());
    }

    /**
     * Создать сущность Лога загрузки для записи в БД
     * @param fileName имя файла
     * @param e ошибка
     * @return результат
     */
    private LoadInfo createLoadInfo(String fileName, Exception e){
        LoadInfo info = new LoadInfo();
        info.setFileName(fileName);
        info.setLog(e.getCause().getMessage());
        return info;
    }

    /**
     * Создать сущность Лога загрузки для записи в БД
     * @param fileName Имя файла
     * @param sales загруженные чеки
     * @return результат
     */
    private LoadInfo createLoadInfo(String fileName, Sales sales){
        LoadInfo info = new LoadInfo();
        info.setFileName(fileName);
        info.setLoadedSaleCount(sales.getSales().size());
        info.setLog("Успешно");
        return info;
    }
}
