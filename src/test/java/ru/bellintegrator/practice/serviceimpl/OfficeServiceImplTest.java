package ru.bellintegrator.practice.serviceimpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.bellintegrator.practice.daoimpl.DaoUtils;
import ru.bellintegrator.practice.daoimpl.OrganizationDaoImpl;
import ru.bellintegrator.practice.dao.OfficeDao;
import ru.bellintegrator.practice.filter.OfficeRequestFilter;
import ru.bellintegrator.practice.model.Office;
import ru.bellintegrator.practice.view.office.OfficeListView;
import ru.bellintegrator.practice.view.office.OfficeSaveView;
import ru.bellintegrator.practice.view.office.OfficeUpdateView;
import ru.bellintegrator.practice.view.office.OfficeView;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class OfficeServiceImplTest {

    @Autowired
    OfficeServiceImpl service;

    @MockBean
    OfficeDao dao;

    @MockBean
    OrganizationDaoImpl organizationDao;

    private Office office;

    @BeforeEach
    void setUp() {
        office = DaoUtils.createOffice();
    }

    @Test
    void whenSave_thenDaoSaveCalled() {
        long orgId = 1;
        when(organizationDao.findById(orgId)).thenReturn(DaoUtils.createOrganization());

        OfficeSaveView view = new OfficeSaveView();
        view.setOrgId(orgId);
        service.save(view);

        verify(dao, atLeastOnce()).save(any(Office.class));
    }

    @Test
    void givenViewIsNull_whenSave_thenThrowException() {
        assertThrows(NullPointerException.class, () -> service.save(null));
    }

    @Test
    void givenSaved_whenFindById_thenReturnCorrectView(){
        long id = office.getId();
        when(dao.findById(id)).thenReturn(office);
        OfficeView view = service.findById(id);
        verify(dao, atLeastOnce()).findById(id);
        assertEquals(office.getId(), view.getId());
        assertEquals(office.getName(), view.getName());
    }

    @Test
    void givenSaved_whenFindByFilter_thenReturnViewList() {
        OfficeRequestFilter filter = new OfficeRequestFilter();
        when(dao.findByFilter(filter)).thenReturn(List.of(office));
        List<OfficeListView> organizations = service.findByFilter(filter);
        verify(dao, atLeastOnce()).findByFilter(filter);
        assertTrue(organizations.size() > 0);
        assertEquals(office.getId(), organizations.get(0).getId());
        assertEquals(office.getName(), organizations.get(0).getName());
    }

    @Test
    void whenUpdate_thenDaoUpdateCall() {
        long id = office.getId();
        when(dao.findById(id)).thenReturn(office);
        OfficeUpdateView view = new OfficeUpdateView();
        view.setId(id);

        service.update(view);
        verify(dao, atLeastOnce()).update(any(Office.class));
    }
}