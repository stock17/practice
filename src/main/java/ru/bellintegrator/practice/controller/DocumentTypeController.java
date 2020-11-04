package ru.bellintegrator.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.model.DocumentType;
import ru.bellintegrator.practice.serviceinterface.DocumentTypeService;
import ru.bellintegrator.practice.view.DocumentTypeView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Класс контроллер для операций с типами документов {@link ru.bellintegrator.practice.model.DocumentType}
 */
@RestController
@RequestMapping(value = "/api/docs/", produces = APPLICATION_JSON_VALUE)
public class DocumentTypeController {

    private final DocumentTypeService service;

    @Autowired
    public DocumentTypeController(DocumentTypeService service) {
        this.service = service;
    }

    /**
     * Возвращает список типов документов, содержащихся в справочнике, в виде списка DTO-объектов
     *
     * @return список {@link ru.bellintegrator.practice.view.DocumentTypeView}
     */
    @GetMapping(value = "/")
    public List<DocumentTypeView> getDocuments() {
        return service.findAll();
    }
}
