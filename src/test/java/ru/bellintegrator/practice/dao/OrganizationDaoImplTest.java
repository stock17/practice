package ru.bellintegrator.practice.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.bellintegrator.practice.daointerface.OrganizationDao;
import ru.bellintegrator.practice.model.Organization;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(properties = {"spring.jpa.hibernate.ddl-auto=create-drop"})
@Import(OrganizationDaoImpl.class)
class OrganizationDaoImplTest {

    private Organization organization;

    @Autowired
    EntityManager em;

    @Autowired
    OrganizationDao dao;

    @BeforeEach
    void setUp() {
        organization = DaoUtils.createOrganization();
    }

    @AfterEach
    void tearDown() {
        em.clear();
    }

    @Test
    void whenSave_thenSizeIncreased() {
        int originalSize = dao.findAll().size();
        dao.save(organization);
        assertEquals(++originalSize, dao.findAll().size());
    }

    @Test
    void whenSave_thenContains() {
        dao.save(organization);
        assertTrue(dao.findAll().contains(organization));
    }

    @Test
    void givenNameIsNull_whenSave_thenThrowException() {
        organization.setName(null);
        assertThrows(PersistenceException.class, () -> dao.save(organization));
    }

    @Test
    void givenNameNotValid_whenSave_thenThrowException() {
        organization.setName("111111111122222222223333333333444444444455555555556");
        assertThrows(PersistenceException.class, () -> dao.save(organization));
    }

    @Test
    void givenFullNameIsNull_whenSave_thenThrowException() {
        organization.setFullName(null);
        assertThrows(PersistenceException.class, () -> dao.save(organization));
    }

    @Test
    void givenFullNameNotValid_whenSave_thenThrowException() {
        organization.setName(new String(new byte[256]));
        assertThrows(PersistenceException.class, () -> dao.save(organization));
    }

    @Test
    void givenInnIsNull_whenSave_thenThrowException() {
        organization.setInn(null);
        assertThrows(PersistenceException.class, () -> dao.save(organization));
    }

    @Test
    void givenInnNotValid_whenSave_thenThrowException() {
        organization.setInn("12345678901234567890");
        assertThrows(PersistenceException.class, () -> dao.save(organization));
    }

    @Test
    void givenKppIsNull_whenSave_thenThrowException() {
        assertThrows(PersistenceException.class, () -> {
            organization.setKpp(null);
            dao.save(organization);
        });
    }

    @Test
    void givenKppNotValid_whenSave_thenThrowException() {
        organization.setKpp("12345678901");
        assertThrows(PersistenceException.class, () -> dao.save(organization));
    }

    @Test
    void givenAddressIsNull_whenSave_thenThrowException() {
        organization.setAddress(null);
        assertThrows(PersistenceException.class, () -> dao.save(organization));
    }

    @Test
    void givenAddressNotValid_whenSave_thenThrowException() {
        organization.setAddress(new String(new byte[256]));
        assertThrows(PersistenceException.class, () -> dao.save(organization));
    }

    @Test
    void givenPhoneNotValid_whenSave_thenThrowException() {
        organization.setPhone(new String(new byte[21]));
        assertThrows(PersistenceException.class, () -> dao.save(organization));
    }

    @Test
    void givenSaved_whenFindById_thenCorrect() {
        dao.save(organization);
        long id = organization.getId();
        assertSame(organization, dao.findById(id));
    }

    @Test
    void givenSaved_whenFindByName_thenCorrect() {
        dao.save(organization);
        String name = organization.getName();
        String inn = organization.getInn();
        Boolean isActive = organization.getActive();

        assertTrue(dao.findByName(name, inn, isActive).contains(organization));
        assertTrue(dao.findByName(name, null, null).contains(organization));
    }

    @Test
    void givenNotSaved_whenFindById_thenThrowException() {
        assertThrows(NoResultException.class, () -> dao.findById(organization.getId()));
    }

    @Test
    void givenNotSaved_whenFindByName_thenThrowException() {
        assertTrue(dao.findByName("another_name", null, null).isEmpty());
    }

    @Test
    void whenUpdateFields_thenCorrect() {
        dao.save(organization);
        String newName = "new name";
        String newFullName = "new full name";
        String newInn = "9998887776";
        String newKpp = "555444333";
        String newAddress = "new address";
        String newPhone = "new phone";
        Boolean newActive = false;

        organization.setName(newName);
        organization.setFullName(newFullName);
        organization.setInn(newInn);
        organization.setKpp(newKpp);
        organization.setAddress(newAddress);
        organization.setPhone(newPhone);
        organization.setActive(newActive);

        dao.update(organization);

        Organization updated = dao.findById(organization.getId());

        assertEquals(newName, updated.getName());
        assertEquals(newFullName, updated.getFullName());
        assertEquals(newInn, updated.getInn());
        assertEquals(newKpp, updated.getKpp());
        assertEquals(newAddress, updated.getAddress());
        assertEquals(newPhone, updated.getPhone());
        assertEquals(newActive, updated.getActive());
    }
}