package ru.bellintegrator.practice.dao;

import ru.bellintegrator.practice.model.Document;

/**
 * DAO интерфейс для класса {@link ru.bellintegrator.practice.model.Document}
 */
public interface DocumentDao {

    /**
     * Метод добавляет новый документ {@link ru.bellintegrator.practice.model.Document}
     * @param document документ
     */
    void save(Document document);

    /**
     * Метод возвращает {@link ru.bellintegrator.practice.model.Document} по Id
     *
     * @param id идентификатор
     * @return объект {@link ru.bellintegrator.practice.model.Document}
     */
    Document findById(long id);

    /**
     * Метод обноваляет данные уже существующего документа {@link ru.bellintegrator.practice.model.Document}
     * @param document документа
     */
    void update(Document document);
}