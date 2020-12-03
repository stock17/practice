package ru.bellintegrator.practice.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.dao.CountryDao;
import ru.bellintegrator.practice.model.Country;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.List;
import java.util.Objects;

/**
 * Реализация интерфейса {@link ru.bellintegrator.practice.dao.CountryDao}
 * для работы с базой данных по спецификации JPA
 */
@Repository
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
        Objects.requireNonNull(code, "Code не может быть NULL");
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
        criteria.from(Country.class);
        return em.createQuery(criteria).getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Country country) {
        Objects.requireNonNull(country, "Country не может быть NULL");
        em.persist(country);
    }
}
