package ru.bellintegrator.practice.service;

import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.daointerface.CountryDao;
import ru.bellintegrator.practice.model.Country;
import ru.bellintegrator.practice.serviceinterface.CountryService;
import ru.bellintegrator.practice.view.CountryView;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация интерфейса {@link ru.bellintegrator.practice.serviceinterface.CountryService}
 * с использованием библиотеки {@link ma.glasnost.orika}
 */
@Service
public class CountryServiceImpl implements CountryService {

    private final CountryDao countryDao;
    private final MapperFactory mapperFactory;

    @Autowired
    public CountryServiceImpl(CountryDao countryDao, MapperFactory mapperFactory) {
        this.countryDao = countryDao;
        this.mapperFactory = mapperFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CountryView findByCode(Integer code) {
        Country country = countryDao.findByCode(code);
        return mapperFactory.getMapperFacade().map(country, CountryView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addCountry(CountryView countryView) {
        Country country = mapperFactory.getMapperFacade().map(countryView, Country.class);
        countryDao.save(country);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CountryView> findAll() {
        List<Country> countries = countryDao.findAll();
        return mapperFactory.getMapperFacade().mapAsList(countries, CountryView.class);
    }
}
