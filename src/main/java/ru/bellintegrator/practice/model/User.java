package ru.bellintegrator.practice.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

/**
 * Entity-класс для работников
 */
@Entity
public class User {

    /**
     * Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Служебное поле Hibernate
     */
    @Version
    private Integer version;

    /**
     * Имя
     */
    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    /**
     * Отчество
     */
    @Column(name = "middle_name", length = 50)
    private String middleName;

    /**
     * Фамилия
     */
    @Column(name = "second_name", length = 50)
    private String secondName;

    /**
     * Должность
     */
    @Column(name = "position", length = 50, nullable = false)
    private String position;

    /**
     * Телефон
     */
    @Column(name = "phone", length = 20)
    private String phone;

    /**
     * Адрес
     */
    @Column(name = "address", length = 255)
    private String address;

    /**
     * Офис (место работы)
     */
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "office_id", nullable = false)
    private Office office;

    /**
     * Гражданство
     */
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "citizenship")
    private Country citizenship;

    /**
     * Действующий статус
     */
    @Column(name = "is_identified")
    private Boolean isIdentified;

    public long getId() {
        return id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public Country getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(Country citizenship) {
        this.citizenship = citizenship;
    }

    public Boolean getIdentified() {
        return isIdentified;
    }

    public void setIdentified(Boolean identified) {
        isIdentified = identified;
    }
}