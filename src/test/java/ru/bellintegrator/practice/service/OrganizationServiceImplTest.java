package ru.bellintegrator.practice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.bellintegrator.practice.dao.DaoUtils;
import ru.bellintegrator.practice.daointerface.OrganizationDao;
import ru.bellintegrator.practice.filter.OrganizationRequestFilter;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.view.OrganizationShortView;
import ru.bellintegrator.practice.view.OrganizationView;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class OrganizationServiceImplTest {

    @Autowired
    OrganizationServiceImpl service;

    @MockBean
    OrganizationDao dao;

    private Organization organization;

    @BeforeEach
    void setUp() {
        organization = DaoUtils.createOrganization();
    }

    @Test
    void whenSave_thenDaoSaveCalled() {
        service.save(new OrganizationView());
        verify(dao, atLeastOnce()).save(any(Organization.class));
    }

    @Test
    void givenViewIsNull_whenSave_thenThrowException() {
        assertThrows(NullPointerException.class, () -> service.save(null));
    }

    @Test
    void givenSaved_whenFindById_thenReturnCorrectView(){
        long id = organization.getId();
        when(dao.findById(id)).thenReturn(organization);
        OrganizationView view = service.findById(id);
        verify(dao, atLeastOnce()).findById(id);
        assertEquals(organization.getId(), view.getId());
        assertEquals(organization.getName(), view.getName());
    }

    @Test
    void givenSaved_whenFindAll_thenReturnViewList() {
        when(dao.findAll()).thenReturn(List.of(organization));
        List<OrganizationView> organizations = service.findAll();
        verify(dao, atLeastOnce()).findAll();
        assertTrue(organizations.size() > 0);
    }

    @Test
    void givenSaved_whenFindByFilter_thenReturnShortViewList() {
        OrganizationRequestFilter filter = new OrganizationRequestFilter();
        when(dao.findByFilter(filter)).thenReturn(List.of(organization));
        List<OrganizationShortView> organizations = service.findByFilter(filter);
        verify(dao, atLeastOnce()).findByFilter(filter);
        assertTrue(organizations.size() > 0);
        assertEquals(organization.getId(), organizations.get(0).getId());
        assertEquals(organization.getName(), organizations.get(0).getName());
    }

    @Test
    void whenUpdate_thenDaoUpdateCall() {
        long id = organization.getId();
        when(dao.findById(id)).thenReturn(organization);
        service.update(new OrganizationView());
        verify(dao, atLeastOnce()).update(any(Organization.class));
    }
}