package ru.bellintegrator.practice.view;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * DTO-класс для {@link ru.bellintegrator.practice.model.Organization}
 */
public class OrganizationView {
    /**
     * Id
     */
    private long id;

    /**
     * Краткое наименование организации
     */
    @NotEmpty(message = "Наименование не может быть пустым")
    @Size(max = 50, message = "Наименование не более 50 символов")
    private String name;

    /**
     * Полное наименование организации
     */
    @NotEmpty(message = "Полное наименование не может быть пустым")
    @Size(max = 255, message = "Полное наименование не более 255 символов")
    private String fullName;

    /**
     * Идентификационный налоговый номер
     */
    @NotEmpty(message = "ИНН не может быть пустым")
    @Size(min = 10, max = 10, message = "ИНН 10 символов")
    private String inn;

    /**
     * Код причины постановки
     */
    @NotEmpty(message = "КПП не может быть пустым")
    @Size(min = 9, max = 9, message = "КПП 9 символов")
    private String kpp;

    /**
     * Адрес
     */
    @NotEmpty(message = "Адрес не может быть пустым")
    @Size(max = 255, message = "Адрес не более 255 символов")
    private String address;

    /**
     * Телефон
     */
    @Size(max = 20, message = "Телефон не более 20 символов")
    private String phone;

    /**
     * Действующий статус
     */
    private Boolean isActive;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @JsonProperty("isActive")
    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
