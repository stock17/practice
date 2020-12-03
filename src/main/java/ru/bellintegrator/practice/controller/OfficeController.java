package ru.bellintegrator.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.aspect.RequestDataValidationException;
import ru.bellintegrator.practice.service.OfficeService;
import ru.bellintegrator.practice.view.StatusView;
import ru.bellintegrator.practice.filter.OfficeRequestFilter;
import ru.bellintegrator.practice.view.office.OfficeListView;
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
@RequestMapping(value = "/api/office/", produces = APPLICATION_JSON_VALUE + "; charset=utf-8")
public class OfficeController {

    private final OfficeService service;

    @Autowired
    public OfficeController(OfficeService service) {
        this.service = service;
    }

    /**
     * Метод сохраняет организацию с переданными параметрами
     *
     * @param officeSaveView офис
     */
    @PostMapping("/save")
    public void save(@Valid @RequestBody OfficeSaveView officeSaveView, Errors errors) {
        if (errors.hasErrors()) {
            throw new RequestDataValidationException(errors);
        }
        service.save(officeSaveView);
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
     * Метод возвращает список офисов в кратком виде, у которых совпадают переданные параметры
     *
     * @param filter DTO офиса, содержащий переданные параметры orgId, name, phone, isActive
     * @return список DTO офиса в кратком виде
     */
    @PostMapping("/list")
    public List<OfficeListView> getListByFilter(@Valid @RequestBody OfficeRequestFilter filter) {
        return service.findByFilter(filter);
    }

    /**
     * Метод обновляет запись об офисе согласно переданным параметрам
     *
     * @param officeUpdateView офис
     */
    @PostMapping("/update")
    public void update(@Valid @RequestBody OfficeUpdateView officeUpdateView, Errors errors) {
        if (errors.hasErrors()) {
            throw new RequestDataValidationException(errors);
        }
        service.update(officeUpdateView);
    }
}
