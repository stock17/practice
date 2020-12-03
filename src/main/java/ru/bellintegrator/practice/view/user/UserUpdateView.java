package ru.bellintegrator.practice.view.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

/**
 * DTO-класс для обновления информации о работнике {@link ru.bellintegrator.practice.model.User}
 */
public class UserUpdateView {

    /**
     * Id (обязательный параметр)
     */
    @NotNull(message = "Id не может быть пустым")
    private long id;

    /**
     * Id офиса
     */
    private Integer officeId;

    /**
     * Имя
     */
    @NotEmpty(message = "Имя не может быть пустым")
    @Size(max = 50,  message = "Имя не более 50 символов")
    private String firstName;

    /**
     * Отчество
     */
    @Size(max = 50,  message = "Отчество не более 50 символов")
    private String middleName;

    /**
     * Фамилия
     */
    @Size(max = 50,  message = "Фамилия не более 50 символов")
    private String secondName;

    /**
     * Должность
     */
    @NotEmpty(message = "Должность не может быть пустой")
    @Size(max = 50,  message = "Должность не более 50 символов")
    private String position;

    /**
     * Телефон
     */
    @Size(max = 20,  message = "Телефон не более 20 символов")
    private String phone;

    /**
     * Адрес
     */
    @Size(max = 255,  message = "Адрес не более 255 символов")
    private String address;

    /**
     * код документа, удостоверяющего личность
     */
    private Integer docCode;

    /**
     * Номер документа, удостоверяющего личность
     */
    @Size(max = 10, message = "Номер документа не более 10 символов")
    private String docNumber;

    /**
     * Дата выдачи документа, удостоверяющего личность
     */
    private Date docDate;

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

    public Integer getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Integer officeId) {
        this.officeId = officeId;
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

    public Integer getDocCode() {
        return docCode;
    }

    public void setDocCode(Integer docCode) {
        this.docCode = docCode;
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

    public Boolean getIdentified() {
        return isIdentified;
    }

    @JsonProperty("isIdentified")
    public void setIdentified(Boolean identified) {
        isIdentified = identified;
    }
}
