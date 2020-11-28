package ru.bellintegrator.practice.service;

import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.daointerface.OrganizationDao;
import ru.bellintegrator.practice.filter.OrganizationRequestFilter;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.serviceinterface.OrganizationService;
import ru.bellintegrator.practice.view.OrganizationShortView;
import ru.bellintegrator.practice.view.OrganizationView;

import javax.transaction.Transactional;
import java.util.List;
/**
 * Реализация интерфейса {@link ru.bellintegrator.practice.serviceinterface.OrganizationService}
 * с использованием библиотеки {@link ma.glasnost.orika}
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationDao dao;
    private final MapperFactory mapperFactory;

    @Autowired
    public OrganizationServiceImpl(OrganizationDao dao, MapperFactory mapperFactory) {
        this.dao = dao;
        this.mapperFactory = mapperFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OrganizationView findById(long id) {
        Organization organization = dao.findById(id);
        return mapperFactory.getMapperFacade().map(organization, OrganizationView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void save(OrganizationView organizationView) {
        Organization organization = mapperFactory.getMapperFacade().map(organizationView, Organization.class);
        dao.save(organization);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<OrganizationView> findAll() {
        List<Organization> organizations = dao.findAll();
        return mapperFactory.getMapperFacade().mapAsList(organizations, OrganizationView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<OrganizationShortView> findByFilter(OrganizationRequestFilter filter) {
        List<Organization> organizations = dao.findByFilter(filter);
        return mapperFactory.getMapperFacade().mapAsList(organizations, OrganizationShortView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(OrganizationView organizationView) {
        Organization organization = dao.findById(organizationView.getId());
        mapperFactory.getMapperFacade().map(organizationView, organization);
        dao.update(organization);
    }
}
