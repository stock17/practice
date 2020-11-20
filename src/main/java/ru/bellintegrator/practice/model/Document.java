package ru.bellintegrator.practice.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import java.sql.Date;


/**
 * Entity-класс для документа, удостоверяющего личность
 */
@Entity
public class Document {

    /**
     * Id документа.
     * Не генерируется, т.к. задействован MapsId в поле User
     */
    @Id
    private long id;

    /**
     * Служебное поле Hibernate
     */
    @Version
    private Integer version;

    /**
     * Номер
     */
    @Column(name="doc_number", length = 10)
    private String docNumber;

    /**
     * Дата выдачи
     */
    @Column(name="doc_date")
    private Date docDate;

    /**
     * Тип по справочнику
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="doc_code")
    private DocumentType documentType;

    /**
     * Владелец
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @MapsId
    private User user;

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

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
