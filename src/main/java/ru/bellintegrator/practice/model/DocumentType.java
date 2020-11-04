package ru.bellintegrator.practice.model;

import javax.persistence.*;

/**
 * Справочный класс, содержащий в себе числовой код документа, удостоверяющего личность,
 * и соответствующее ему наименование
 */
@Entity
@Table(name = "document_type")
public class DocumentType {

    /**
     * Числовой код документа
     */
    @Id
    @Column(name = "code")
    private Integer code;

    /**
     * служебное поле Hibernate
     */
    @Version
    private Integer version;

    /**
     * Наименование документа, соответствующее коду
     */
    @Column(name = "name")
    private String name;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
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
}
