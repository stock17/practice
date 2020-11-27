package ru.bellintegrator.practice.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.bellintegrator.practice.daointerface.DocumentDao;
import ru.bellintegrator.practice.model.Document;
import ru.bellintegrator.practice.model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest(properties = {"spring.jpa.hibernate.ddl-auto=create-drop"})
@Import(DocumentDaoImpl.class)
class DocumentDaoImplTest {

    private Document document;

    @Autowired
    EntityManager em;

    @Autowired
    DocumentDao dao;


    @BeforeEach
    void setUp() {
        User user = DaoUtils.createUser();
        em.persist(user.getOffice());
        em.persist(user);
        document = DaoUtils.createDocument(user);
    }

    @Test
    void whenSave_thenPersist() {
        dao.save(document);
        assertSame(document, em.find(Document.class, document.getId()));
    }

    @Test
    void givenSaved_whenFindByCode_thenCorrect() {
        em.persist(document.getDocumentType());
        em.persist(document);
        assertNotNull(dao.findById(document.getId()));
    }

    @Test void givenNotSaved_whenFindByCode_thenThrowException() {
        assertThrows(NoResultException.class, () -> dao.findById(document.getId()));
    }
}
