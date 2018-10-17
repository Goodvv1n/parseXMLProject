package com.pleshkov.sheduled;

import com.pleshkov.exceptions.SAPIException;
import com.pleshkov.services.LoadInfoService;
import com.pleshkov.services.SaleService;
import com.pleshkov.util.ConfigService;
import com.pleshkov.util.XMLParser;
import com.pleshkov.xmlBean.XMLSales;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * Сервис для управлением фоновой задачей
 * @author pleshkov on 03.10.2018.
 */
@Service
public class SheduledService {

    @Autowired
    LoadInfoService loadInfoService;

    @Autowired
    SaleService saleService;
    private static final Logger LOG = Logger.getLogger(SheduledService.class);
    private static final String CRON = "0 */10 * * * *";

    @Scheduled(cron = CRON)
    public void scanXMLFiles() throws SAPIException {
        LOG.info("FON");
        ConfigService configService = ConfigService.getInstance();
        if (!configService.isEnableLoading()){
            return;
        }
        String path = configService.getFolderPath();
        File dir = new File(path); //path указывает на директорию
        File[] arrFiles = dir.listFiles();
        if (arrFiles == null){
            throw new SAPIException("Path " + path + " not found");
        }
        List<File> lst = Arrays.asList(arrFiles);
        for (File file : lst){
            loadSalefromFile(file, XMLSales.class);
        }
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
        LOG.info("Loading file " + file.getPath());
        try {
            XMLSales sales = (XMLSales) XMLParser.parseFile(file, clazz);
            loadInfoService.saveInfo(file.getName(), sales);
            saleService.save(sales);
        } catch (JAXBException e) {
            LOG.info("File " + file.getPath() + " has an incorrect structure. Data not loaded.");
            loadInfoService.saveInfo(file.getName(), e);
        }

    }

    /**
     * Проверка, новый ли это файл.
     * @param file файл
     * @return результат
     */
    private boolean isNewFile(File file){
        return !loadInfoService.isLoadInfoExist(file.getName());
    }
}
