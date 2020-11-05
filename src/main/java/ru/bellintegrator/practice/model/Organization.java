package ru.bellintegrator.practice.model;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GenerationType;

/**
 * Entity-класс для объектов организаций
 */
@Entity
@Table(name = "Organization")
public class Organization {

    /**
     * Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    /**
     * Краткое наименование организации
     */
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    /**
     * Полное наименование организации
     */
    @Column(name = "full_name", nullable = false, length = 255)
    private String fullName;

    /**
     * Идентификационный налоговый номер
     */
    @Column(name = "inn", nullable = false)
    private Long inn;

    /**
     * Код причины постановки
     */
    @Column(name = "kpp", nullable = false)
    private Long kpp;

    /**
     * Адрес
     */
    @Column(name = "address", nullable = false, length = 255)
    private String address;

    /**
     * Телефон
     */
    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    /**
     * Действующий статус
     */
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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

    public Long getInn() {
        return inn;
    }

    public void setInn(Long inn) {
        this.inn = inn;
    }

    public Long getKpp() {
        return kpp;
    }

    public void setKpp(Long kpp) {
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
