package ru.bellintegrator.practice.serviceinterface;

import ru.bellintegrator.practice.model.DocumentType;
import ru.bellintegrator.practice.view.CountryView;
import ru.bellintegrator.practice.view.DocumentTypeView;

import java.util.List;

/**
 * Интерфейс сервиса для работы с классом {@link ru.bellintegrator.practice.model.DocumentType}
 */
public interface DocumentTypeService {
    /**
     * Метод возвращает объект {@link ru.bellintegrator.practice.view.DocumentTypeView}
     * соответствующий переданному коду
     *
     * @param code код документа
     * @return DTO для класса {@link ru.bellintegrator.practice.model.DocumentType}
     */
    DocumentTypeView findByCode(Integer code);

    /**
     * Метод сохраняет переданный объект
     * @param documentTypeView DTO-объект
     */
    void save(DocumentTypeView documentTypeView);

    /**
     * Метод возращает все найденые типы документов в виде списка DTO-объектов
     * @return список {@link ru.bellintegrator.practice.view.DocumentTypeView}
     */
    List<DocumentTypeView> findAll();
}
