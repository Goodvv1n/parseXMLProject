package com.pleshkov.data.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Описание объекта лога загружаемых файлов
 * @author pleshkov on 03.10.2018.
 */
@Entity
public class LoadInfo {

    @Id
    @GeneratedValue
    private Long id;

    private String fileName;

    private Integer loadedSaleCount;

    private String Log;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getLoadedSaleCount() {
        return loadedSaleCount;
    }

    public void setLoadedSaleCount(Integer loadedSaleCount) {
        this.loadedSaleCount = loadedSaleCount;
    }

    public String getLog() {
        return Log;
    }

    public void setLog(String log) {
        Log = log;
    }
}
