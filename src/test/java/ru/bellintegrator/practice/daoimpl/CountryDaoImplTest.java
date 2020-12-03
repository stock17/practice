package ru.bellintegrator.practice.daoimpl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.bellintegrator.practice.aspect.NoSuchIdException;
import ru.bellintegrator.practice.dao.CountryDao;
import ru.bellintegrator.practice.model.Country;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(properties = {"spring.jpa.hibernate.ddl-auto=create-drop"})
@Import(CountryDaoImpl.class)
class CountryDaoImplTest {

    private Country country;

    @Autowired
    EntityManager em;

    @Autowired
    CountryDao dao;

    @BeforeEach
    void setUp() {
        country = DaoUtils.createCitizenship();
    }

    @AfterEach
    void tearDown() {
        em.clear();
    }

    @Test
    void whenSave_thenSizeIncreased() {
        int originalSize = dao.findAll().size();
        dao.save(country);
        assertEquals(++originalSize, dao.findAll().size());
    }

    @Test
    void whenSave_thenContains() {
        dao.save(country);
        assertTrue(dao.findAll().contains(country));
    }

    @Test
    void whenSave_thenFoundByCode() {
        int code = country.getCode();
        dao.save(country);
        assertNotNull(dao.findByCode(code));
    }

    @Test void givenNotSaved_whenFindByCode_thenThrowException() {
        int code = country.getCode();
        assertThrows(NoSuchIdException.class, () -> dao.findByCode(code));
    }

    @Test
    void givenCodeIsNull_whenSave_thenThrowException() {
        country.setCode(null);
        assertThrows(PersistenceException.class, () -> dao.save(country));
    }

    @Test
    void givenNameIsNull_whenSave_thenThrowException() {
        assertThrows(PersistenceException.class, () -> {
            country.setName(null);
            dao.save(country);
            em.flush();
        });
    }
}