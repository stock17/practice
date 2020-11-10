package ru.bellintegrator.practice.service;

import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.daointerface.OfficeDao;
import ru.bellintegrator.practice.daointerface.OrganizationDao;
import ru.bellintegrator.practice.model.Office;
import ru.bellintegrator.practice.serviceinterface.OfficeService;
import ru.bellintegrator.practice.view.OfficeListRequestView;
import ru.bellintegrator.practice.view.OfficeListResponseView;
import ru.bellintegrator.practice.view.OfficeSaveView;
import ru.bellintegrator.practice.view.OfficeUpdateView;
import ru.bellintegrator.practice.view.OfficeView;

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
    public OfficeView findById(long id) {
        Office office = officeDao.findById(id);
        return mapperFactory.getMapperFacade().map(office, OfficeView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<OfficeListResponseView> findByOrgId(OfficeListRequestView requestView) {
        Objects.requireNonNull(requestView, "DTO офиса не должен быть NULL");
        List<Office> offices = officeDao.findByOrgId(requestView.getOrgId(),
                                                     requestView.getName(),
                                                     requestView.getPhone(),
                                                     requestView.getActive());
        return mapperFactory.getMapperFacade().mapAsList(offices, OfficeListResponseView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(OfficeUpdateView officeUpdateView) {
        Objects.requireNonNull(officeUpdateView, "DTO офиса не должен быть NULL");
        Office office = mapperFactory.getMapperFacade().map(officeUpdateView, Office.class);
        officeDao.update(office);
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
}
