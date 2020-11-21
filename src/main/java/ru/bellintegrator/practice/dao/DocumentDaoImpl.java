package ru.bellintegrator.practice.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.daointerface.DocumentDao;
import ru.bellintegrator.practice.model.Document;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

/**
 * Реализация интерфейса {@link ru.bellintegrator.practice.daointerface.DocumentDao}
 * для работы с базой данных по спецификации JPA
 */
@Repository
public class DocumentDaoImpl implements DocumentDao {

    private final EntityManager em;

    @Autowired
    public DocumentDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public void save(Document document) {
        em.persist(document);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public Document findById(long id) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Document> query = builder.createQuery(Document.class);
        Root<Document> root = query.from(Document.class);
        query.select(root).where(builder.equal(root.get("id"), id));
        return em.createQuery(query).getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public void update(Document document) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaUpdate<Document> update = builder.createCriteriaUpdate(Document.class);
        Root<Document> root = update.from(Document.class);
        update.set(root.get("docNumber"), document.getDocNumber())
              .set(root.get("docDate"), document.getDocDate())
              .set(root.get("user"), document.getUser())
              .set(root.get("documentType"), document.getDocumentType())
              .where(builder.equal(root.get("id"), document.getId()));
        em.createQuery(update).executeUpdate();
    }
}