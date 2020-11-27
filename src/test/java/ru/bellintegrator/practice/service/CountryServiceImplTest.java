package ru.bellintegrator.practice.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.bellintegrator.practice.daointerface.CountryDao;
import ru.bellintegrator.practice.model.Country;
import ru.bellintegrator.practice.serviceinterface.CountryService;
import ru.bellintegrator.practice.view.CountryView;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureTestDatabase
class CountryServiceImplTest {

    @Autowired
    CountryService service;

    @MockBean
    CountryDao dao;

    private static Country country;

    @BeforeAll
    static void beforeAll() {
        country = new Country();
        country.setName("Hawaii");
        country.setCode(999);
    }

    @Test
    void findByCode() {
        when(dao.findByCode(country.getCode())).thenReturn(country);
        CountryView countryView = service.findByCode(999);
        assertEquals(999, countryView.getCode());
        assertEquals("Hawaii", countryView.getName());
    }

    @Test
    void addCountry() {
        CountryView countryView = new CountryView();
        service.addCountry(countryView);
        verify(dao, atLeastOnce()).save(any(Country.class));
    }

    @Test
    void findAll() {
        when(dao.findAll()).thenReturn(List.of(country));
        List<CountryView> countries = service.findAll();
        assertTrue(countries.size() > 0);
        assertEquals(999, countries.get(0).getCode());
        assertEquals("Hawaii", countries.get(0).getName());
    }
}