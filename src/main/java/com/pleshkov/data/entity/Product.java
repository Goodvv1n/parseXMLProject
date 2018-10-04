package com.pleshkov.data.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Сущность "Продукт"
 * @author pleshkov on 04.10.2018.
 */
@Entity
public class Product {

    /**
     * Ид продукта
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * Код продукта
     */
    private Long code;

    /**
     * Наименование продукта
     */
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
