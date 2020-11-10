package ru.bellintegrator.practice.view;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO-класс для внесения новой записи об офисе {@link ru.bellintegrator.practice.model.Office}
 */
public class OfficeSaveView {

    /**
     * Id организации, к которой относится офис
     */
    @NotNull(message = "Id организации не должно быть пустым")
    private Integer orgId;

    /**
     * Наименование
     */
    @Size(max = 50)
    private String name;

    /**
     * Почтовый адрес
     */
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

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
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
    public void setActive(Boolean active) {
        isActive = active;
    }
}