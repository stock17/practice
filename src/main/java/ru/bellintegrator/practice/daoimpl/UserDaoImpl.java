package ru.bellintegrator.practice.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.exception.NoSuchIdException;
import ru.bellintegrator.practice.dao.UserDao;
import ru.bellintegrator.practice.filter.UserRequestFilter;
import ru.bellintegrator.practice.model.Document;
import ru.bellintegrator.practice.model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация интерфейса {@link ru.bellintegrator.practice.dao.UserDao}
 * для работы с базой данных по спецификации JPA
 */
@Repository
public class UserDaoImpl implements UserDao {

    private final EntityManager em;

    @Autowired
    public UserDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public User findById(long id) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.where(builder.equal(root.get("id"), id));
        try {
            return em.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException e) {
            throw new NoSuchIdException("Нет работника с Id = " + id, e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public void update(User user) {
        em.merge(user);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public void save(User user) {
        em.persist(user);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public List<User> findByFilter(UserRequestFilter filter) {

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Document> query = builder.createQuery(Document.class);
        Root<Document> document = query.from(Document.class);
        Fetch<Document, User> documentUserFetch = document.fetch("user");

        Predicate predicate = builder.equal(document.get("user").get("office").get("id"), filter.getOfficeId());
        if (filter.getFirstName() != null) {
            predicate = builder.and(predicate, builder.equal(document.get("user").get("firstName"), filter.getFirstName()));
        }
        if (filter.getMiddleName() != null) {
            predicate = builder.and(predicate, builder.equal(document.get("user").get("middleName"), filter.getMiddleName()));
        }
        if (filter.getSecondName() != null) {
            predicate = builder.and(predicate, builder.equal(document.get("user").get("secondName"), filter.getSecondName()));
        }
        if (filter.getPosition() != null) {
            predicate = builder.and(predicate, builder.equal(document.get("user").get("position"), filter.getPosition()));
        }
        if (filter.getCitizenshipCode() != null) {
            predicate = builder.and(predicate, builder.equal(document.get("user").get("citizenship").get("code"), filter.getCitizenshipCode()));
        }
        if (filter.getDocCode() != null) {
            predicate = builder.and(predicate, builder.equal(document.get("documentType").get("code"), filter.getDocCode()));
        }

        query.select(document).where(predicate);
        List<Document> documents = em.createQuery(query).getResultList();
        return documents.stream().map(Document::getUser).collect(Collectors.toList());
    }
}
