package ru.bellintegrator.practice.dao;

import ru.bellintegrator.practice.filter.OrganizationRequestFilter;
import ru.bellintegrator.practice.model.Organization;

import java.util.List;

/**
 * DAO интерфейс для класса {@link ru.bellintegrator.practice.model.Organization}
 */
public interface OrganizationDao {

    /**
     * Метод возвращает {@link ru.bellintegrator.practice.model.Organization} по Id
     *
     * @param id идентификатор
     * @return объект {@link ru.bellintegrator.practice.model.Organization}
     */
    Organization findById(long id);

    /**
     * Метод возвращает список организаций {@link ru.bellintegrator.practice.model.Organization},
     * соотвествующий фильтру по наименованию, инн и статусу.
     *
     * @param filter dto, содержащий параметры фильтрации
     * @return список организаций {@link ru.bellintegrator.practice.model.Organization}
     */
    List<Organization> findByFilter(OrganizationRequestFilter filter);

    /**
     * Метод добавляет новую организацию {@link ru.bellintegrator.practice.model.Country}
     * @param organization организация
     */
    void save(Organization organization);

    /**
     * Метод возвращает все {@link ru.bellintegrator.practice.model.Organization},
     * содержащиеся в базе в виде списка
     *
     * @return список {@link ru.bellintegrator.practice.model.Organization}
     */
    List<Organization> findAll();

    /**
     * Метод обноваляет данные уже существующей организации {@link ru.bellintegrator.practice.model.Country}
     * @param organization организация
     */
    void update(Organization organization);
}
