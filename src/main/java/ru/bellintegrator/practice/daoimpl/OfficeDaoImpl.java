package ru.bellintegrator.practice.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.dao.OfficeDao;
import ru.bellintegrator.practice.filter.OfficeRequestFilter;
import ru.bellintegrator.practice.model.Office;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

/**
 * Реализация интерфейса {@link ru.bellintegrator.practice.dao.OfficeDao}
 * для работы с базой данных по спецификации JPA
 */
@Repository
public class OfficeDaoImpl implements OfficeDao {

    private final EntityManager em;

    @Autowired
    public OfficeDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     * @param filter
     */
    @Transactional
    @Override
    public List<Office> findByFilter(OfficeRequestFilter filter) {
        Objects.requireNonNull(filter, "Filter не должен быть NULL");

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Office> criteriaQuery = builder.createQuery(Office.class);
        Root<Office> root = criteriaQuery.from(Office.class);

        Predicate predicate = builder.equal(root.get("organization").get("id"), filter.getOrgId());
        if (filter.getName() != null) {
            predicate = builder.and(predicate, builder.equal(root.get("name"), filter.getName()));
        }
        if (filter.getPhone() != null) {
            predicate = builder.and(predicate, builder.equal(root.get("phone"), filter.getPhone()));
        }
        if (filter.getActive() != null) {
            predicate = builder.and(predicate, builder.equal(root.get("isActive"), filter.getActive()));
        }

        criteriaQuery.select(root).where(predicate);
        return em.createQuery(criteriaQuery).getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
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
    @Transactional
    @Override
    public void update(Office office) {
        Objects.requireNonNull(office, "Office не должен быть NULL");
        Objects.requireNonNull(office.getName(), "Имя не должно быть NULL");
        Objects.requireNonNull(office.getAddress(), "Адрес не должен быть NULL");

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaUpdate<Office> criteriaUpdate = builder.createCriteriaUpdate(Office.class);
        Root<Office> root = criteriaUpdate.from(Office.class);
        criteriaUpdate
                .where(builder.equal(root.get("id"), office.getId()))
                .set(root.get("name"), office.getName())
                .set(root.get("address"), office.getAddress())
                .set(root.get("phone"), office.getPhone())
                .set(root.get("isActive"), office.getActive());
        em.createQuery(criteriaUpdate).executeUpdate();
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public void save(Office office) {
        Objects.requireNonNull(office, "Office не должен быть NULL");
        em.persist(office);
    }
}
