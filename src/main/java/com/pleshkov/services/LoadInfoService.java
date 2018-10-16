package com.pleshkov.services;

import com.pleshkov.data.entity.LoadInfo;
import com.pleshkov.data.repozitory.LoadInfoRepository;
import com.pleshkov.xmlBean.XMLSales;
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

    public void saveInfo(String fileName, XMLSales sales){
        saveInfo(createLoadInfo(fileName, sales));
    }

    public void saveInfo(String fileName, Exception exception){
        saveInfo(createLoadInfo(fileName, exception));
    }

    public List<LoadInfo> getAll(){
        return (List<LoadInfo>) repository.findAll();
    }

    public boolean isLoadInfoExist(String fileName){
        List<LoadInfo> list = repository.findByFileName(fileName);
        return list != null && list.size() > 0;
    }

    public void deleteLoadIfo(LoadInfo info){
        repository.delete(info);
    }

    public void saveInfo(LoadInfo loadInfo){
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
        info.setLog(e.getCause().getMessage());
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
