package ru.bellintegrator.practice.view;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * DTO-класс для обновления записи об офисе {@link ru.bellintegrator.practice.model.Office}
 */
public class OfficeUpdateView {
    /**
     * Id
     */
    @NotNull(message = "Id не должно быть пустым")
    private Long id;

    /**
     * Наименование
     */
    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(max = 50)
    private String name;

    /**
     * Почтовый адрес
     */
    @NotEmpty(message = "Адрес не должен быть пустым")
    @Size(max = 255)
    private String address;

    /**
     * Телефон
     */
    @Size(max = 20)
    private String phone;

    /**
     * Действующий статус
     */
    private Boolean isActive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Boolean getActive() {
        return isActive;
    }
    @JsonProperty("isActive")
    public void setActive(Boolean isActive) {
        this.isActive = isActive;
    }
}
