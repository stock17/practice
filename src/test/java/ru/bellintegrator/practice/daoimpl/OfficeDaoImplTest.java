package ru.bellintegrator.practice.daoimpl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.bellintegrator.practice.aspect.NoSuchIdException;
import ru.bellintegrator.practice.dao.OfficeDao;
import ru.bellintegrator.practice.filter.OfficeRequestFilter;
import ru.bellintegrator.practice.model.Office;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(properties = {"spring.jpa.hibernate.ddl-auto=create-drop"})
@Import(OfficeDaoImpl.class)
class OfficeDaoImplTest {

    private Office office;

    @Autowired
    EntityManager em;

    @Autowired
    OfficeDao dao;

    @BeforeEach
    void setUp() {
        office = DaoUtils.createOffice();
    }

    @AfterEach
    void tearDown() {
        em.clear();
    }

    @Test
    void whenSave_thenPersist() {
        dao.save(office);
        assertNotNull(em.find(Office.class, office.getId()));
    }

    @Test
    void givenNameNotValid_whenSave_thenThrowException() {
        office.setName(new String(new byte[51]));
        assertThrows(PersistenceException.class, () -> dao.save(office));
    }

    @Test
    void givenAddressNotValid_whenSave_thenThrowException() {
        office.setAddress(new String(new byte[256]));
        assertThrows(PersistenceException.class, () -> dao.save(office));
    }

    @Test
    void givenPhoneNotValid_whenSave_thenThrowException() {
        office.setPhone(new String(new byte[21]));
        assertThrows(PersistenceException.class, () -> dao.save(office));
    }

    @Test
    void givenSaved_whenFindById_thenCorrect() {
        em.persist(office);
        long id = office.getId();
        assertSame(office, dao.findById(id));
    }

    @Test void givenNotSaved_whenFindById_thenThrowException() {
        long badId = 999;
        assertThrows(NoSuchIdException.class, () -> dao.findById(badId));
    }

    @Test
    void givenSaved_whenFindByFilter_thenFound() {
        em.persist(office);
        OfficeRequestFilter filter = new OfficeRequestFilter();
        filter.setOrgId(office.getOrganization().getId());

        assertTrue(dao.findByFilter(filter).contains(office));
    }

    @Test
    void givenNotSaved_whenFindByFilter_thenNotFound() {
        OfficeRequestFilter filter = new OfficeRequestFilter();
        filter.setOrgId(999L);

        assertFalse(dao.findByFilter(filter).contains(office));
    }

    @Test
    void whenUpdateFields_thenCorrect() {
        dao.save(office);
        String newName = "new name";
        String newAddress = "new address";
        String newPhone = "new phone";
        Boolean newActive = false;

        office.setName(newName);
        office.setAddress(newAddress);
        office.setPhone(newPhone);
        office.setActive(newActive);
        dao.update(office);
        Office updated = dao.findById(office.getId());

        assertEquals(newName, updated.getName());
        assertEquals(newAddress, updated.getAddress());
        assertEquals(newPhone, updated.getPhone());
        assertEquals(newActive, updated.getActive());
    }
}