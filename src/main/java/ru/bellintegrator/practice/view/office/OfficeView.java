package ru.bellintegrator.practice.view.office;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Size;

/**
 * DTO-класс для отображения полной информации об офисе {@link ru.bellintegrator.practice.model.Office}
 */
public class OfficeView {
    /**
     * Id
     */
    private long id;

    /**
     * Наименование
     */
    @Size(max = 50,  message = "Наименование не более 50 символов")
    private String name;

    /**
     * Почтовый адрес
     */
    @Size(max = 255,  message = "Адрес не более 255 символов")
    private String address;

    /**
     * Телефон
     */
    @Size(max = 20,  message = "Телефон не более 20 символов")
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
