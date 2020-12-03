package ru.bellintegrator.practice.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.exception.NoSuchIdException;
import ru.bellintegrator.practice.dao.OrganizationDao;
import ru.bellintegrator.practice.filter.OrganizationRequestFilter;
import ru.bellintegrator.practice.model.Organization;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

/**
 * Реализация интерфейса {@link ru.bellintegrator.practice.dao.OrganizationDao}
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
    @Transactional
    @Override
    public Organization findById(long id) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Organization> criteria = builder.createQuery(Organization.class);
        Root<Organization> root = criteria.from(Organization.class);
        criteria.where(builder.equal(root.get("id"), id));
        try {
            return em.createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            throw new NoSuchIdException("Нет организации с Id = " + id, e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public List<Organization> findByFilter(OrganizationRequestFilter filter) {
        Objects.requireNonNull(filter);
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Organization> criteria = builder.createQuery(Organization.class);
        Root<Organization> root = criteria.from(Organization.class);
        Predicate predicate = builder.like(root.get("name"), filter.getName());
        if (filter.getInn() != null) {
            predicate = builder.and(predicate, builder.equal(root.get("inn"), filter.getInn()));
        }
        if (filter.getActive() != null) {
            predicate = builder.and(predicate, builder.equal(root.get("isActive"), filter.getActive()));
        }
        criteria.select(root).where(predicate);
        return em.createQuery(criteria).getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public void save(Organization organization) {
        em.persist(organization);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public List<Organization> findAll() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Organization> criteria = builder.createQuery(Organization.class);
        criteria.from(Organization.class);
        return em.createQuery(criteria).getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public void update(Organization organization) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaUpdate<Organization> update = builder.createCriteriaUpdate(Organization.class);
        Root<Organization> root = update.from(Organization.class);
        update.where(builder.equal(root.get("id"), organization.getId()))
                .set(root.get("name"), organization.getName())
                .set(root.get("fullName"), organization.getFullName())
                .set(root.get("inn"), organization.getInn())
                .set(root.get("kpp"), organization.getKpp())
                .set(root.get("address"), organization.getAddress())
                .set(root.get("phone"), organization.getPhone())
                .set(root.get("isActive"), organization.getActive());
        em.createQuery(update).executeUpdate();
    }
}
