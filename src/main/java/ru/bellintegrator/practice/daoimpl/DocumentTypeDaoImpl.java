package ru.bellintegrator.practice.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.aspect.NoSuchIdException;
import ru.bellintegrator.practice.dao.DocumentTypeDao;
import ru.bellintegrator.practice.model.DocumentType;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;

/**
 * Реализация интерфейса {@link ru.bellintegrator.practice.dao.DocumentTypeDao}
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
        Objects.requireNonNull(code, "Code не может быть NULL");
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<DocumentType> criteria = builder.createQuery(DocumentType.class);
        Root<DocumentType> root = criteria.from(DocumentType.class);
        criteria.where(builder.equal(root.get("code"), code));
        try {
            return em.createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            throw new NoSuchIdException("Нет типа документа с кодом = " + code, e);
        }

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
    public void save(DocumentType type) {
        Objects.requireNonNull(type, "Type не может быть NULL");
        em.persist(type);
    }
}
