package ru.bellintegrator.practice.serviceinterface;

import ru.bellintegrator.practice.view.OfficeListRequestView;
import ru.bellintegrator.practice.view.OfficeListResponseView;
import ru.bellintegrator.practice.view.OfficeSaveView;
import ru.bellintegrator.practice.view.OfficeUpdateView;
import ru.bellintegrator.practice.view.OfficeView;

import java.util.List;

/**
 * Интерфейс сервиса для работы с классом {@link ru.bellintegrator.practice.model.Office}
 */
public interface OfficeService {

    /**
     * Метод возвращает объект {@link ru.bellintegrator.practice.view.OfficeView}
     * соответствующий переданному Id
     *
     * @param id идентификатор офиса
     * @return DTO для класса {@link ru.bellintegrator.practice.model.Office}
     */
    OfficeView findById(long id);

    /**
     * Метод возращает все найденые офисы для указанной организации в виде списка DTO-объектов
     *
     * @param requestView DTO запроса {@link ru.bellintegrator.practice.view.OfficeListRequestView} (обязательный параметр)
     * @return список {@link ru.bellintegrator.practice.view.OfficeListResponseView}
     */
    List<OfficeListResponseView> findByOrgId(OfficeListRequestView requestView);

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
