package ru.bellintegrator.practice.dao;

import ru.bellintegrator.practice.filter.OfficeRequestFilter;
import ru.bellintegrator.practice.model.Office;

import java.util.List;

/**
 * DAO интерфейс для класса {@link ru.bellintegrator.practice.model.Office}
 */
public interface OfficeDao {

    /**
     * Метод добавляет новый офис {@link ru.bellintegrator.practice.model.Office}
     * @param office офис
     */
    void save(Office office);

    /**
     * Метод возвращает {@link ru.bellintegrator.practice.model.Office} по Id
     *
     * @param id идентификатор
     * @return объект {@link ru.bellintegrator.practice.model.Office}
     */
    Office findById(long id);

    /**
     * Метод возвращает список офисов  {@link ru.bellintegrator.practice.model.Office},
     * которые соостоят в организации с указанным Id и соответствуют дополнительным параметрам
     *
     * @param filter dto, содержащий параметры запроса
     * @return список офисов {@link ru.bellintegrator.practice.model.Office}
     */
    List<Office> findByFilter(OfficeRequestFilter filter);

    /**
     * Метод обноваляет данные уже существующего офиса {@link ru.bellintegrator.practice.model.Office}
     * @param office офис
     */
    void update(Office office);


}
