package ru.bellintegrator.practice.daointerface;

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
     * @param orgId идентификатор организации (обязательный параметр)
     * @param name  наименование офиса
     * @param phone телефон офиса
     * @param isActive действующий статус
     * @return список офисов {@link ru.bellintegrator.practice.model.Office}
     */
    List<Office> findByOrgId(long orgId, String name, String phone, Boolean isActive);

    /**
     * Метод обноваляет данные уже существующего офиса {@link ru.bellintegrator.practice.model.Office}
     * @param office офис
     */
    void update(Office office);


}
