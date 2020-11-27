package ru.bellintegrator.practice.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.bellintegrator.practice.daointerface.OfficeDao;
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

    @Test
    void givenSaved_whenFindByOrgId_thenCorrect() {
        em.persist(office);
        long orgId = office.getOrganization().getId();

        assertTrue(dao.findByOrgId(orgId, office.getName(), office.getPhone(), office.getActive()).contains(office));
        assertTrue(dao.findByOrgId(orgId, null, null, null).contains(office));
    }

    @Test
    void givenNotSaved_whenFindByOrgId_thenThrowException() {
        long anotherId = 999L;
        assertTrue(dao.findByOrgId(anotherId, office.getName(), office.getPhone(), office.getActive()).isEmpty());
        assertTrue(dao.findByOrgId(anotherId, null, null, null).isEmpty());
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