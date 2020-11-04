package ru.bellintegrator.practice.dao;

import org.springframework.beans.factory.annotation.Autowired;
import ru.bellintegrator.practice.daointerface.CountryDao;
import ru.bellintegrator.practice.model.Country;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Реализация интерфейса {@link ru.bellintegrator.practice.daointerface.CountryDao}
 * для работы с базой данных по спецификации JPA
 */

public class CountryDaoImpl implements CountryDao {

    private final EntityManager em;

    @Autowired
    public CountryDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Country findByCode(Integer code) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Country> criteria = builder.createQuery(Country.class);
        Root<Country> root = criteria.from(Country.class);
        criteria.where(builder.equal(root.get("code"), code));
        return em.createQuery(criteria).getSingleResult();
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public List<Country> findAll() {
        CriteriaQuery<Country> criteria = em.getCriteriaBuilder().createQuery(Country.class);
        return em.createQuery(criteria).getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(Country country) {
        em.persist(country);
    }
}
