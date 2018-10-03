package com.pleshkov.services;

import com.pleshkov.data.entity.LoadInfo;
import com.pleshkov.data.repozitory.LoadInfoRepository;
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

    public void saveInfo(LoadInfo loadInfo){
        repository.save(loadInfo);
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

}
