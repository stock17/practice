package ru.bellintegrator.practice.model;

import javax.persistence.*;

/**
 * Справочный класс, содержащий в себе числовой код страны и соответствующее наименование
 */
@Entity
@Table(name = "Country")
public class Country {

    /**
     * Числовой код страны
     */
    @Column(name="id")
    private Integer code;

    /**
     * служебное поле Hibernate
     */
    @Version
    private Integer version;

    /**
     * Наименование страны, соответствующее коду
     */
    @Column(name = "name")
    private String name;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
