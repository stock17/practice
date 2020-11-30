package ru.bellintegrator.practice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.bellintegrator.practice.dao.DaoUtils;
import ru.bellintegrator.practice.daointerface.CountryDao;
import ru.bellintegrator.practice.daointerface.DocumentTypeDao;
import ru.bellintegrator.practice.model.Country;
import ru.bellintegrator.practice.model.DocumentType;
import ru.bellintegrator.practice.serviceinterface.CountryService;
import ru.bellintegrator.practice.view.CountryView;
import ru.bellintegrator.practice.view.DocumentTypeView;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class DocumentTypeServiceImplTest {

    @Autowired
    DocumentTypeServiceImpl service;

    @MockBean
    DocumentTypeDao dao;

    private DocumentType documentType;

    @BeforeEach
    void setUp() {
        documentType = DaoUtils.createDocumentType();
    }

    @Test
    void givenSaved_whenFindByCode_thenFound(){
        when(dao.findByCode(documentType.getCode())).thenReturn(documentType);
        DocumentTypeView view = service.findByCode(documentType.getCode());
        verify(dao, atLeastOnce()).findByCode(documentType.getCode());
        assertEquals(documentType.getName(), documentType.getName());
        assertEquals(documentType.getCode(), documentType.getCode());
    }

    @Test
    void whenSave_thenDaoSaveCalled() {
        DocumentTypeView view = new DocumentTypeView();
        service.save(view);
        verify(dao, atLeastOnce()).save(any(DocumentType.class));
    }

    @Test
    void givenCodeIsNull_whenFindByCode_thenThrowException() {
        assertThrows(NullPointerException.class, () -> service.findByCode(null));
    }

    @Test
    void givenSaved_whenFindAll_thenFound() {
        when(dao.findAll()).thenReturn(List.of(documentType));
        List<DocumentTypeView> types = service.findAll();
        verify(dao, atLeastOnce()).findAll();
        assertTrue(types.size() > 0);
        assertEquals(documentType.getCode(), types.get(0).getCode());
        assertEquals(documentType.getName(), types.get(0).getName());
    }

    @Test
    void givenViewIsNull_whenSave_thenThrowException() {
        assertThrows(NullPointerException.class, () -> service.save(null));
    }


}