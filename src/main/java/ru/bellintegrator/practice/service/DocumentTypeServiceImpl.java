package ru.bellintegrator.practice.service;

import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.daointerface.DocumentTypeDao;
import ru.bellintegrator.practice.model.DocumentType;
import ru.bellintegrator.practice.serviceinterface.DocumentTypeService;
import ru.bellintegrator.practice.view.DocumentTypeView;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация интерфейса {@link ru.bellintegrator.practice.serviceinterface.DocumentTypeService}
 * с использованием библиотеки {@link ma.glasnost.orika}
 */
@Service
public class DocumentTypeServiceImpl implements DocumentTypeService {

    private final DocumentTypeDao dao;
    private final MapperFactory mapperFactory;

    @Autowired
    public DocumentTypeServiceImpl(DocumentTypeDao dao, MapperFactory mapperFactory) {
        this.dao = dao;
        this.mapperFactory = mapperFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DocumentTypeView findByCode(Integer code) {
        DocumentType documentType = dao.findByCode(code);
        return mapperFactory.getMapperFacade().map(documentType, DocumentTypeView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addDocumentType(DocumentTypeView documentTypeView) {
        DocumentType documentType = mapperFactory.getMapperFacade().map(documentTypeView, DocumentType.class);
        dao.add(documentType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DocumentTypeView> findAll() {
        List<DocumentType> types = dao.findAll();
        return mapperFactory.getMapperFacade().mapAsList(types, DocumentTypeView.class);
    }
}
