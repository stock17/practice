package ru.bellintegrator.practice.service;

import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.daointerface.OfficeDao;
import ru.bellintegrator.practice.daointerface.OrganizationDao;
import ru.bellintegrator.practice.model.Office;
import ru.bellintegrator.practice.serviceinterface.OfficeService;
import ru.bellintegrator.practice.filter.OfficeRequestFilter;
import ru.bellintegrator.practice.view.office.OfficeListView;
import ru.bellintegrator.practice.view.office.OfficeSaveView;
import ru.bellintegrator.practice.view.office.OfficeUpdateView;
import ru.bellintegrator.practice.view.office.OfficeView;

import java.util.List;
import java.util.Objects;

/**
 * Реализация интерфейса {@link ru.bellintegrator.practice.serviceinterface.OfficeService}
 * с использованием библиотеки {@link ma.glasnost.orika}
 */
@Service
public class OfficeServiceImpl implements OfficeService {

    private final OfficeDao officeDao;
    private final OrganizationDao organizationDao;
    private final MapperFactory mapperFactory;

    @Autowired
    public OfficeServiceImpl(OfficeDao officeDao, OrganizationDao organizationDao, MapperFactory mapperFactory) {
        this.officeDao = officeDao;
        this.organizationDao = organizationDao;
        this.mapperFactory = mapperFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(OfficeSaveView officeSaveView) {
        Objects.requireNonNull(officeSaveView, "DTO офиса не должен быть NULL");
        Office office = mapperFactory.getMapperFacade().map(officeSaveView, Office.class);
        office.setOrganization(organizationDao.findById(officeSaveView.getOrgId()));
        officeDao.save(office);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OfficeView findById(long id) {
        Office office = officeDao.findById(id);
        return mapperFactory.getMapperFacade().map(office, OfficeView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<OfficeListView> findByFilter(OfficeRequestFilter filter) {
        Objects.requireNonNull(filter, "DTO офиса не должен быть NULL");
        List<Office> offices = officeDao.findByFilter(filter);
        return mapperFactory.getMapperFacade().mapAsList(offices, OfficeListView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(OfficeUpdateView officeUpdateView) {
        Objects.requireNonNull(officeUpdateView, "DTO офиса не должен быть NULL");
        Office office = officeDao.findById(officeUpdateView.getId());
        mapperFactory.getMapperFacade().map(officeUpdateView, office);
        officeDao.update(office);
    }
}
