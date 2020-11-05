package ru.bellintegrator.practice.view;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class OrganizationView {
    /**
     * Id
     */
    private long id;

    /**
     * Краткое наименование организации
     */
    @NotEmpty(message = "Наименование не может быть пустым")
    @Size(max = 50)
    private String name;

    /**
     * Полное наименование организации
     */
    @NotEmpty(message = "Полное наименование не может быть пустым")
    @Size(max = 255)
    private String fullName;

    /**
     * Идентификационный налоговый номер
     */
    @NotEmpty(message = "ИНН не может быть пустым")
    @Size(min = 10, max = 10)
    private String inn;

    /**
     * Код причины постановки
     */
    @NotEmpty(message = "КПП не может быть пустым")
    @Size(min = 9, max = 9)
    private String kpp;

    /**
     * Адрес
     */
    @NotEmpty(message = "Адрес не может быть пустым")
    @Size(max = 255)
    private String address;

    /**
     * Телефон
     */
    @NotEmpty(message = "Наименование не может быть пустым")
    @Size(max = 20)
    private String phone;

    /**
     * Действующий статус
     */
    @NotNull(message = "Статус не может быть не установленным")
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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
