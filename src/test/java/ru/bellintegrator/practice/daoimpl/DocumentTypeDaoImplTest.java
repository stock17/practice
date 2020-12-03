package ru.bellintegrator.practice.daoimpl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.bellintegrator.practice.dao.DocumentTypeDao;
import ru.bellintegrator.practice.model.DocumentType;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(properties = {"spring.jpa.hibernate.ddl-auto=create-drop"})
@Import(DocumentTypeDaoImpl.class)
class DocumentTypeDaoImplTest {

    private final Integer code = 123456789;
    private DocumentType documentType;

    @Autowired
    EntityManager em;

    @Autowired
    DocumentTypeDao dao;

    @BeforeEach
    void setUp() {
        documentType = new DocumentType();
        documentType.setCode(code);
        String name = "Межгалактический паспорт";
        documentType.setName(name);
    }

    @AfterEach
    void tearDown() {
        em.clear();
    }

    @Test
    void whenSave_thenSizeIncreased() {
        int originalSize = dao.findAll().size();
        dao.save(documentType);
        assertEquals(++originalSize, dao.findAll().size());
    }

    @Test
    void whenSave_thenContains() {
        dao.save(documentType);
        assertTrue(dao.findAll().contains(documentType));
    }

    @Test
    void whenSave_thenFoundByCode() {
        dao.save(documentType);
        assertNotNull(dao.findByCode(code));
    }

    @Test void givenNotSaved_whenFindByCode_thenThrowException() {
        assertThrows(NoResultException.class, () -> dao.findByCode(code));
    }

    @Test
    void givenCodeIsNull_whenSave_thenThrowException() {
        documentType.setCode(null);
        assertThrows(PersistenceException.class, () -> dao.save(documentType));
    }

    @Test
    void givenNameIsNull_whenSave_thenThrowException() {
        assertThrows(PersistenceException.class, () -> {
            documentType.setName(null);
            dao.save(documentType);
            em.flush();
        });
    }
}