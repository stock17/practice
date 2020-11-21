package ru.bellintegrator.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.serviceinterface.OfficeService;
import ru.bellintegrator.practice.view.office.OfficeListRequestView;
import ru.bellintegrator.practice.view.office.OfficeListResponseView;
import ru.bellintegrator.practice.view.office.OfficeSaveView;
import ru.bellintegrator.practice.view.office.OfficeUpdateView;
import ru.bellintegrator.practice.view.office.OfficeView;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


/**
 * Класс контроллер для обработки запросов по адресу: "/api/office/
 * Проводит операции с офисами {@link ru.bellintegrator.practice.model.Office}
 */
@RestController
@RequestMapping(value = "/api/office/", produces = APPLICATION_JSON_VALUE)
public class OfficeController {

    private final OfficeService service;

    @Autowired
    public OfficeController(OfficeService service) {
        this.service = service;
    }

    /**
     * Метод возвращает офис с указанным Id
     *
     * @param id идентификатор офиса
     * @return офис
     */
    @GetMapping("/{id}")
    public OfficeView getById(@NotNull @Min(1) @PathVariable long id) {
        return service.findById(id);
    }

    /**
     * Метод обновляет запись об офисе согласно переданным параметрам
     *
     * @param officeUpdateView офис
     * @return результат обновления
     */
    @PostMapping("/update")
    public String update(@Valid @RequestBody OfficeUpdateView officeUpdateView) {
        service.update(officeUpdateView);

        //TODO refactor return success
        return "{\"result\":\"success\"}";
    }

    /**
     * Метод сохраняет организацию с переданными параметрами
     *
     * @param officeSaveView офис
     * @return результат сохранения
     */
    @PostMapping("/save")
    public String save(@Valid @RequestBody OfficeSaveView officeSaveView) {
        service.save(officeSaveView);

        //TODO refactor return success
        return "{\"result\":\"success\"}";
    }

    /**
     * Метод возвращает список офисов в кратком виде, у которых совпадают переданные параметры
     *
     * @param requestView DTO офиса, содержащий переданные параметры orgId, name, phone, isActive
     * @return список DTO офиса в кратком виде
     */
    @PostMapping("/list")
    public List<OfficeListResponseView> getListByOrgId(@Valid @RequestBody OfficeListRequestView requestView) {
        return service.findByOrgId(requestView);
    }
}
