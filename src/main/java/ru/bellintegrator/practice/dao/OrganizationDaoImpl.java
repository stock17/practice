package ru.bellintegrator.practice.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.daointerface.OrganizationDao;
import ru.bellintegrator.practice.model.Organization;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * Реализация интерфейса {@link ru.bellintegrator.practice.daointerface.OrganizationDao}
 * для работы с базой данных по спецификации JPA
 */
@Repository
public class OrganizationDaoImpl implements OrganizationDao {

    private final EntityManager em;

    @Autowired
    public OrganizationDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Organization findById(long id) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Organization> criteria = builder.createQuery(Organization.class);
        Root<Organization> root = criteria.from(Organization.class);
        criteria.where(builder.equal(root.get("id"), id));
        return em.createQuery(criteria).getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Organization> findByName(@NotEmpty String name, String inn, Boolean isActive) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Organization> criteria = builder.createQuery(Organization.class);
        Root<Organization> root = criteria.from(Organization.class);
        Predicate predicate = builder.like(root.get("name"), name);
        if (inn != null) {
            predicate = builder.and(predicate, builder.equal(root.get("inn"), inn));
        }
        if (isActive != null) {
            predicate = builder.and(predicate, builder.equal(root.get("isActive"), isActive));
        }
        criteria.select(root).where(predicate);
        return em.createQuery(criteria).getResultList();
    }
}
