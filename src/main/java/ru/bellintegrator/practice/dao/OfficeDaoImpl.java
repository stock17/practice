package ru.bellintegrator.practice.dao;

import org.springframework.beans.factory.annotation.Autowired;
import ru.bellintegrator.practice.daointerface.OfficeDao;
import ru.bellintegrator.practice.model.Office;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Реализация интерфейса {@link ru.bellintegrator.practice.daointerface.OfficeDao}
 * для работы с базой данных по спецификации JPA
 */
public class OfficeDaoImpl implements OfficeDao {

    private final EntityManager em;

    @Autowired
    public OfficeDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Office> findByOrgId(Integer orgId) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Office> criteriaQuery = builder.createQuery(Office.class);
        Root<Office> root = criteriaQuery.from(Office.class);
        criteriaQuery.where(builder.equal(root.get("organizationId"), orgId));
        return em.createQuery(criteriaQuery).getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Office findById(long id) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Office> criteriaQuery = builder.createQuery(Office.class);
        Root<Office> root = criteriaQuery.from(Office.class);
        criteriaQuery.where(builder.equal(root.get("id"), id));
        return em.createQuery(criteriaQuery).getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Office office) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaUpdate<Office> criteriaUpdate = builder.createCriteriaUpdate(Office.class);
        Root<Office> root = criteriaUpdate.from(Office.class);
        criteriaUpdate
                .where(builder.equal(root.get("id"), office.getId()))
                .set(root.get("name"), office.getName())
                .set(root.get("address"), office.getAddress());

        if (office.getPhone() != null) {
            criteriaUpdate.set(root.get("phone"), office.getPhone());
        }
        if (office.getActive() != null) {
            criteriaUpdate.set(root.get("isActive"), office.getActive());
        }
        em.createQuery(criteriaUpdate);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Office office) {
        em.persist(office);
    }
}
