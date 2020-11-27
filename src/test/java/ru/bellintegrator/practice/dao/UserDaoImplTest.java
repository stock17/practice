package ru.bellintegrator.practice.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.bellintegrator.practice.daointerface.UserDao;
import ru.bellintegrator.practice.model.Country;
import ru.bellintegrator.practice.model.Document;
import ru.bellintegrator.practice.model.Office;
import ru.bellintegrator.practice.model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(properties = {"spring.jpa.hibernate.ddl-auto=create-drop"})
@Import(UserDaoImpl.class)
class UserDaoImplTest {

    private User user;
    private Document document;

    @Autowired
    EntityManager em;

    @Autowired
    UserDao userDao;

    @BeforeEach
    void setUp() {
        user = DaoUtils.createUser();
        document = DaoUtils.createDocument(user);
    }

    @AfterEach
    void tearDown() {
        em.clear();
    }

    @Test
    void whenSave_thenPersist() {
        userDao.save(user);
        assertNotNull(em.find(User.class, user.getId()));
    }

    @Test
    void givenFirstNameIsNull_whenSave_thenThrowException() {
        user.setFirstName(null);
        assertThrows(PersistenceException.class, () -> userDao.save(user));
    }

    @Test
    void givenFirstNameNotValid_whenSave_thenThrowException() {
        user.setFirstName(new String(new byte[51]));
        assertThrows(PersistenceException.class, () -> userDao.save(user));
    }

    @Test
    void givenMiddleNameNotValid_whenSave_thenThrowException() {
        user.setMiddleName(new String(new byte[51]));
        assertThrows(PersistenceException.class, () -> userDao.save(user));
    }

    @Test
    void givenSecondNameNotValid_whenSave_thenThrowException() {
        user.setSecondName(new String(new byte[51]));
        assertThrows(PersistenceException.class, () -> userDao.save(user));
    }

    @Test
    void givenAddressNotValid_whenSave_thenThrowException() {
        user.setAddress(new String(new byte[256]));
        assertThrows(PersistenceException.class, () -> userDao.save(user));
    }

    @Test
    void givenPhoneNotValid_whenSave_thenThrowException() {
        user.setPhone(new String(new byte[21]));
        assertThrows(PersistenceException.class, () -> userDao.save(user));
    }

    @Test
    void givenPositionIsNull_whenSave_thenThrowException() {
        user.setPosition(null);
        assertThrows(PersistenceException.class, () -> userDao.save(user));
    }

    @Test
    void givenPositionNotValid_whenSave_thenThrowException() {
        user.setPosition(new String(new byte[51]));
        assertThrows(PersistenceException.class, () -> userDao.save(user));
    }

    @Test
    void givenSaved_whenFindById_thenCorrect() {
        em.persist(user);
        long id = user.getId();
        assertSame(user, userDao.findById(id));
    }

    @Test
    void givenNotSaved_whenFindById_thenThrowException() {
        assertThrows(NoResultException.class, () -> userDao.findById(user.getId()));
    }

    @Test
    void givenSaved_whenFindByOfficeId_thenCorrect() {
        em.persist(user);
        em.persist(document.getDocumentType());
        em.persist(document);

        long officeId = user.getOffice().getId();
        Integer docCode = document.getDocumentType().getCode();
        assertTrue(userDao.findByOfficeId(officeId, user.getFirstName(), user.getMiddleName(),
                user.getSecondName(), user.getPosition(), docCode, user.getCitizenship().getCode()).contains(user));
        assertTrue(userDao.findByOfficeId(officeId, user.getFirstName(), null, null,
                user.getPosition(), null, null).contains(user));
    }

    @Test
    void givenNotSaved_whenFindByOrgId_thenThrowException() {
        long anotherId = 999L;
        Integer docCode = document.getDocumentType().getCode();
        assertTrue(userDao.findByOfficeId(anotherId, user.getFirstName(), user.getMiddleName(),
                user.getSecondName(), user.getPosition(), docCode, user.getCitizenship().getCode()).isEmpty());
        assertTrue(userDao.findByOfficeId(anotherId, user.getFirstName(), null, null,
                user.getPosition(), null, null).isEmpty());
    }

    @Test
    void whenUpdateFields_thenCorrect() {
        userDao.save(user);
        String newFirstName = "new first name";
        String newMiddleName = "new middle full name";
        String newSecondName = "new second name";
        String newAddress = "new address";
        String newPhone = "new phone";
        String newPosition = "new position";

        Office newOffice = DaoUtils.createOffice();
        em.persist(newOffice);
        Country newCitizenship = DaoUtils.createCitizenship(888);
        em.persist(newCitizenship);

        Boolean newIdentified = false;

        user.setFirstName(newFirstName);
        user.setMiddleName(newMiddleName);
        user.setSecondName(newSecondName);
        user.setAddress(newAddress);
        user.setPhone(newPhone);
        user.setPosition(newPosition);
        user.setOffice(newOffice);
        user.setCitizenship(newCitizenship);
        user.setIdentified(newIdentified);

        userDao.update(user);

        User updated = userDao.findById(user.getId());

        assertEquals(newFirstName, updated.getFirstName());
        assertEquals(newMiddleName, updated.getMiddleName());
        assertEquals(newSecondName, updated.getSecondName());
        assertEquals(newAddress, updated.getAddress());
        assertEquals(newPhone, updated.getPhone());
        assertEquals(newPhone, updated.getPhone());
        assertEquals(newAddress, updated.getAddress());
        assertEquals(newOffice, updated.getOffice());
        assertEquals(newCitizenship, updated.getCitizenship());
        assertEquals(newIdentified, updated.getIdentified());

    }
}