package ru.bellintegrator.practice.daointerface;

import ru.bellintegrator.practice.model.Organization;

import javax.validation.constraints.NotEmpty;
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
     * соотвествующий запросу по наименованию, инн и статусу. Наименование - обязательный параметр.
     *
     * @param name наименование (обязательный параметр)
     * @param inn  идентификационный налоговый номер
     * @param isActive действующий статус
     * @return список организаций {@link ru.bellintegrator.practice.model.Organization}
     */
    List<Organization> findByName(@NotEmpty String name, String inn, Boolean isActive);
}
