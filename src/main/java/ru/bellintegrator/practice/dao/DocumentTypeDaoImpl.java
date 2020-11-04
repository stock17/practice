package ru.bellintegrator.practice.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.daointerface.DocumentTypeDao;
import ru.bellintegrator.practice.model.DocumentType;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Реализация интерфейса {@link ru.bellintegrator.practice.daointerface.DocumentTypeDao}
 * для работы с базой данных по спецификации JPA
 */
@Repository
public class DocumentTypeDaoImpl implements DocumentTypeDao {

    private final EntityManager em;

    @Autowired
    public DocumentTypeDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DocumentType findByCode(Integer code) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<DocumentType> criteria = builder.createQuery(DocumentType.class);
        Root<DocumentType> root = criteria.from(DocumentType.class);
        criteria.where(builder.equal(root.get("code"), code));
        return em.createQuery(criteria).getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DocumentType> findAll() {
        CriteriaQuery<DocumentType> criteria = em.getCriteriaBuilder().createQuery(DocumentType.class);
        criteria.from(DocumentType.class);
        return em.createQuery(criteria).getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(DocumentType type) {
        em.persist(type);
    }
}
