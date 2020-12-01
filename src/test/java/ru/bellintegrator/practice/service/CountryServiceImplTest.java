package ru.bellintegrator.practice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.bellintegrator.practice.dao.DaoUtils;
import ru.bellintegrator.practice.daointerface.CountryDao;
import ru.bellintegrator.practice.model.Country;
import ru.bellintegrator.practice.serviceinterface.CountryService;
import ru.bellintegrator.practice.view.CountryView;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class CountryServiceImplTest {

    @Autowired
    CountryService service;

    @MockBean
    CountryDao dao;

    private Country country;

    @BeforeEach
    void setUp() {
        country = DaoUtils.createCitizenship();
    }

    @Test
    void givenSaved_whenFindByCode_thenFound(){
        when(dao.findByCode(country.getCode())).thenReturn(country);
        CountryView countryView = service.findByCode(country.getCode());
        verify(dao, atLeastOnce()).findByCode(country.getCode());
        assertEquals(country.getName(), countryView.getName());
        assertEquals(country.getCode(), countryView.getCode());
    }

    @Test
    void whenSave_thenDaoSaveCalled() {
        CountryView countryView = new CountryView();
        service.save(countryView);
        verify(dao, atLeastOnce()).save(any(Country.class));
    }

    @Test
    void givenSaved_whenFindAll_thenFound() {
        when(dao.findAll()).thenReturn(List.of(country));
        List<CountryView> countries = service.findAll();
        verify(dao, atLeastOnce()).findAll();
        assertTrue(countries.size() > 0);
        assertEquals(country.getCode(), countries.get(0).getCode());
        assertEquals(country.getName(), countries.get(0).getName());
    }

    @Test
    void givenCodeIsNull_whenSave_thenThrowException() {
        assertThrows(NullPointerException.class, () -> service.save(null));
    }
}