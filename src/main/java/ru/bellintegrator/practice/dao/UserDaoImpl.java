package ru.bellintegrator.practice.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.daointerface.UserDao;
import ru.bellintegrator.practice.model.Document;
import ru.bellintegrator.practice.model.User;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация интерфейса {@link ru.bellintegrator.practice.daointerface.UserDao}
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
        return em.createQuery(criteriaQuery).getSingleResult();
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
    public List<User> findByOfficeId(Integer officeId, String firstName, String middleName, String secondName,
                                     String position, Integer docCode, Integer citizenshipCode) {

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Document> query = builder.createQuery(Document.class);
        Root<Document> document = query.from(Document.class);
        Fetch<Document, User> documentUserFetch = document.fetch("user");

        Predicate predicate = builder.equal(document.get("user").get("office").get("id"), officeId);
        if (firstName != null) {
            predicate = builder.and(predicate, builder.equal(document.get("user").get("firstName"), firstName));
        }
        if (middleName != null) {
            predicate = builder.and(predicate, builder.equal(document.get("user").get("middleName"), middleName));
        }
        if (secondName != null) {
            predicate = builder.and(predicate, builder.equal(document.get("user").get("secondName"), secondName));
        }
        if (position != null) {
            predicate = builder.and(predicate, builder.equal(document.get("user").get("position"), position));
        }
        if (citizenshipCode != null) {
            predicate = builder.and(predicate, builder.equal(document.get("user").get("citizenship").get("code"), citizenshipCode));
        }
        if (docCode != null) {
            predicate = builder.and(predicate, builder.equal(document.get("code"), docCode));
        }

        query.select(document).where(predicate);
        List<Document> documents = em.createQuery(query).getResultList();
        List<User> users = documents.stream().map(Document::getUser).collect(Collectors.toList());
        return users;
    }
}
