package ru.bellintegrator.practice.serviceinterface;

import ru.bellintegrator.practice.filter.OfficeRequestFilter;
import ru.bellintegrator.practice.view.office.OfficeListView;
import ru.bellintegrator.practice.view.office.OfficeSaveView;
import ru.bellintegrator.practice.view.office.OfficeUpdateView;
import ru.bellintegrator.practice.view.office.OfficeView;

import java.util.List;

/**
 * Интерфейс сервиса для работы с классом {@link ru.bellintegrator.practice.model.Office}
 */
public interface OfficeService {

    /**
     * Метод возвращает объект {@link OfficeView}
     * соответствующий переданному Id
     *
     * @param id идентификатор офиса
     * @return DTO для класса {@link ru.bellintegrator.practice.model.Office}
     */
    OfficeView findById(long id);

    /**
     * Метод возращает все найденые офисы для указанной организации в виде списка DTO-объектов
     *
     * @param filter DTO запроса
     * @return список {@link OfficeListView}
     */
    List<OfficeListView> findByFilter(OfficeRequestFilter filter);

    /**
     * Метод обновляет данные об уже существующем офисе согласно переданным параметрам
     *
     * @param officeUpdateView DTO-объект офиса
     */
    void update(OfficeUpdateView officeUpdateView);

    /**
     * Метод сохраняет переданный объект
     * @param officeSaveView DTO-объект офиса
     */
    void save(OfficeSaveView officeSaveView);
}
