package com.pleshkov.services;

import com.pleshkov.data.entity.LoadInfo;
import com.pleshkov.data.repozitory.LoadInfoRepository;
import com.pleshkov.beans.xml.XMLSales;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author pleshkov on 03.10.2018.
 */
@Service
public class LoadInfoService {

    @Autowired
    private LoadInfoRepository repository;

    /**
     * Сохранить информацию об успешной загрузке
     * @param fileName имя файла
     * @param sales Чеки
     */
    public void saveInfo(String fileName, XMLSales sales){
        saveInfo(createLoadInfo(fileName, sales));
    }

    /**
     * Сохранить информацию о неудачной загрузке
     * @param fileName имя файла
     * @param exception ошибка
     */
    public void saveInfo(String fileName, Exception exception){
        saveInfo(createLoadInfo(fileName, exception));
    }

    /**
     * Получить все записи о загруженном файле
     * @return результат
     */
    public List<LoadInfo> getAll(){
        return (List<LoadInfo>) repository.findAll();
    }

    /**
     * Проверка, загружался ли уже данный файл
     * @param fileName имя файла
     * @return результат
     */
    public boolean isLoadInfoExist(String fileName){
        List<LoadInfo> list = repository.findByFileName(fileName);
        return list != null && list.size() > 0;
    }

    /**
     * Удалить нформацию о загруженном файле
     * @param info нфо
     */
    public void deleteLoadInfo(LoadInfo info){
        repository.delete(info);
    }

    /**
     * сохранить информацию о загруженном файле
     * @param loadInfo инфо
     */
    private void saveInfo(LoadInfo loadInfo){
        repository.save(loadInfo);
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
        if (e.getCause() != null) {
            info.setLog(e.getCause().getMessage());
        } else {
            info.setLog(e.getMessage());
        }
        return info;
    }

    /**
     * Создать сущность Лога загрузки для записи в БД
     * @param fileName Имя файла
     * @param sales загруженные чеки
     * @return результат
     */
    private LoadInfo createLoadInfo(String fileName, XMLSales sales){
        LoadInfo info = new LoadInfo();
        info.setFileName(fileName);
        info.setLoadedSaleCount(sales.getSales().size());
        info.setLog("Успешно");
        return info;
    }

}
