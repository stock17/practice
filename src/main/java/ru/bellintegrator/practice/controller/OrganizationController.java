package ru.bellintegrator.practice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.filter.OrganizationRequestFilter;
import ru.bellintegrator.practice.service.OrganizationService;
import ru.bellintegrator.practice.view.OrganizationShortView;
import ru.bellintegrator.practice.view.OrganizationView;
import ru.bellintegrator.practice.view.StatusView;

import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
* Класс контроллер для операций с организациями {@link ru.bellintegrator.practice.model.Organization}
*/
@RestController
@RequestMapping(value = "/api/organization/", produces = APPLICATION_JSON_VALUE + "; charset=utf-8")
public class OrganizationController {

    private final OrganizationService service;

    @Autowired
    public OrganizationController(OrganizationService service) {
        this.service = service;
    }

    /**
     * Метод сохраняет организацию с переданными параметрами
     *
     * @param organizationView организация
     */
    @PostMapping("/save")
    public StatusView save(@RequestBody @Valid OrganizationView organizationView) {
        service.save(organizationView);
        return StatusView.SUCCESS;
    }

    /**
     * Метод возвращает организацию с указанным Id
     *
     * @param id идентификатор организации
     * @return организация
     */
    @GetMapping("/{id}")
    public OrganizationView getOrganizationById(@NotNull @PathVariable(name = "id") long id) {
        return service.findById(id);
    }

    /**
     * Метод возвращает список организаций в кратком виде, у которых совпадают переданные параметры
     *
     * @param filter dto, содержащий параметры фильтрации
     * @return список организаций в кратком виде
     */
    @PostMapping("/list")
    public List<OrganizationShortView> getOrganizationsByName(@Valid @RequestBody OrganizationRequestFilter filter) {
        return service.findByFilter(filter);
    }

    /**
     * Метод обновляет запись об организации согласно переданным параметрам
     *
     * @param organizationView организация
     */
    @PostMapping("/update")
    public StatusView update(@RequestBody @Valid OrganizationView organizationView) {
        if (organizationView.getId() == 0) {
            throw new ValidationException("поле Id не может быть пустым");
        }
        service.update(organizationView);
        return StatusView.SUCCESS;
    }
}
