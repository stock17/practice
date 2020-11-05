package ru.bellintegrator.practice.serviceinterface;


import ru.bellintegrator.practice.view.DocumentTypeView;
import ru.bellintegrator.practice.view.OrganizationShortView;
import ru.bellintegrator.practice.view.OrganizationView;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * Интерфейс сервиса для работы с классом {@link ru.bellintegrator.practice.model.Organization}
 */
public interface OrganizationService {
    /**
     * Метод возвращает объект {@link ru.bellintegrator.practice.view.OrganizationView}
     * соответствующий переданному Id
     *
     * @param id идентификатор организации
     * @return DTO для класса {@link ru.bellintegrator.practice.model.Organization}
     */
    OrganizationView findById(long id);

    /**
     * Метод сохраняет переданный объект
     * @param organizationView DTO-объект
     */
    void save(OrganizationView organizationView);

    /**
     * Метод возращает все найденые организации в виде списка DTO-объектов
     * @return список {@link ru.bellintegrator.practice.view.OrganizationView}
     */
    List<OrganizationView> findAll();


    /**
     * Метод возращает все найденые организации в виде списка DTO-объектов
     *
     * @param name наименование организации (обязательный параметр)
     * @param inn идентификационный налоговый номер
     * @param isActive действующий статус
     * @return список {@link ru.bellintegrator.practice.view.OrganizationView}
     */
    List<OrganizationShortView> findByName(@NotEmpty String name, String inn, Boolean isActive);

    /**
     * Метод обновляет данные об уже существующей организации согласно переданным параметрам
     *
     * @param organizationView  организация
     */
    void update(OrganizationView organizationView);
}
