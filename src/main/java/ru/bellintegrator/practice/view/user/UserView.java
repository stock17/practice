package ru.bellintegrator.practice.view.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

/**
 * DTO-класс для отображения полной информации о работнике {@link ru.bellintegrator.practice.model.User}
 */
public class UserView {

    /**
     * Id (обязательный параметр)
     */
    @NotNull(message = "Id не может быть пустым")
    private long id;

    /**
     * Имя
     */
    @NotEmpty(message = "Имя не может быть пустым")
    @Size(max = 50)
    private String firstName;

    /**
     * Отчество
     */
    @Size(max = 50)
    private String middleName;

    /**
     * Фамилия
     */
    @Size(max = 50)
    private String secondName;

    /**
     * Должность
     */
    @NotEmpty(message = "Должность не может быть пустой")
    @Size(max = 50)
    private String position;

    /**
     * Телефон
     */
    @Size(max = 20)
    private String phone;

    /**
     * Адрес
     */
    @Size(max = 255)
    private String address;

    /**
     * Код документа, удостоверяющего личность
     */
    private Integer docCode;

    /**
     * Наименование документа, удостоверяющего личность
     */
    @Size(max = 255)
    private String docName;

    /**
     * Номер документа, удостоверяющего личность
     */
    @Size(max = 10)
    private String docNumber;

    /**
     * Дата выдачи документа, удостоверяющего личность
     */
    private Date docDate;

    /**
     * Наименование гражданства
     */
    private String citizenshipName;

    /**
     * Код гражданства
     */
    private Integer citizenshipCode;

    /**
     * Действующий статус
     */
    private Boolean isIdentified;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getDocCode() {
        return docCode;
    }

    public void setDocCode(Integer docCode) {
        this.docCode = docCode;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public Integer getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(Integer citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }

    @JsonProperty("isIdentified")
    public Boolean getIdentified() {
        return isIdentified;
    }

    public void setIdentified(Boolean identified) {
        isIdentified = identified;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCitizenshipName() {
        return citizenshipName;
    }

    public void setCitizenshipName(String citizenshipName) {
        this.citizenshipName = citizenshipName;
    }
}
