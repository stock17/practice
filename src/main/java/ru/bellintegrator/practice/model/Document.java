package ru.bellintegrator.practice.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import java.sql.Date;

@Entity
public class Document {

    @Id
    private long id;

    @Version
    private Integer version;

    @Column(name="doc_number", nullable = false)
    private Integer docNumber;

    @Column(name="doc_date", nullable = false)
    private Date docDate;

    @Column(name="doc_code", nullable = false)
    private Integer docCode;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    @MapsId("id")
    private User user;
}
