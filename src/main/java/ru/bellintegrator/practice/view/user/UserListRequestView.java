package ru.bellintegrator.practice.view.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO-класс для запроса списка работников {@link ru.bellintegrator.practice.model.User}
 */
public class UserListRequestView {

    /**
     * Id офиса
     */
    @NotNull(message = "Офис не может быть пустым")
    private Integer officeId;

    /**
     * Имя
     */
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
    @Size(max = 50)
    private String position;

    /**
     * Код документа, удостоверяющего личность
     */
    private Integer docCode;

    /**
     * Код гражданства
     */
    private Integer citizenshipCode;

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

    public Integer getDocCode() {
        return docCode;
    }

    public void setDocCode(Integer docCode) {
        this.docCode = docCode;
    }

    public Integer getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(Integer citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }
}
