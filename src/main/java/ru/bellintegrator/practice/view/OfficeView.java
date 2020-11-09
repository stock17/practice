package ru.bellintegrator.practice.view;

import javax.validation.constraints.Size;

public class OfficeView {
    /**
     * Id
     */
    private long id;

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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
