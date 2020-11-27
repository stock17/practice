package ru.bellintegrator.practice.daointerface;

import ru.bellintegrator.practice.model.Country;
import ru.bellintegrator.practice.model.DocumentType;

import java.util.List;

/**
 * DAO интерфейс для класса {@link ru.bellintegrator.practice.model.DocumentType}
 */
public interface DocumentTypeDao {
    /**
     * Метод возвращает {@link ru.bellintegrator.practice.model.DocumentType},
     * который соответствует переданному коду
     *
     * @param code код документа
     * @return     объект {@link ru.bellintegrator.practice.model.DocumentType}
     */
    DocumentType findByCode(Integer code);

    /**
     * Метод возвращает все {@link ru.bellintegrator.practice.model.DocumentType},
     * содержащиеся в базе в виде списка
     *
     * @return список {@link ru.bellintegrator.practice.model.DocumentType}
     */
    List<DocumentType> findAll();

    /**
     * Метод сохраняет в справочник новую запись {@link ru.bellintegrator.practice.model.DocumentType}
     * @param type  новая запись о типе документа
     */
    void save(DocumentType type);
}
