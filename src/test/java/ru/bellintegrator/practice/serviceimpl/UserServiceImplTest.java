package ru.bellintegrator.practice.serviceimpl;

import ma.glasnost.orika.MapperFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.bellintegrator.practice.daoimpl.DaoUtils;
import ru.bellintegrator.practice.dao.DocumentDao;
import ru.bellintegrator.practice.dao.UserDao;
import ru.bellintegrator.practice.filter.UserRequestFilter;
import ru.bellintegrator.practice.model.User;
import ru.bellintegrator.practice.view.user.UserListView;
import ru.bellintegrator.practice.view.user.UserSaveView;
import ru.bellintegrator.practice.view.user.UserUpdateView;
import ru.bellintegrator.practice.view.user.UserView;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    MapperFactory mapperFactory;

    @Autowired
    UserServiceImpl service;

    @MockBean
    UserDao userDao;

    @MockBean
    DocumentDao documentDao;

    private User user;

    @BeforeEach
    void setUp() {
        user = DaoUtils.createUser();
    }

    @Test
    void whenSave_thenDaoSaveCalled() {
        when(userDao.findById(user.getId())).thenReturn(DaoUtils.createUser());
        UserSaveView view = mapperFactory.getMapperFacade().map(user, UserSaveView.class);
        service.save(view);
        verify(userDao, atLeastOnce()).save(any(User.class));
    }

    @Test
    void givenViewIsNull_whenSave_thenThrowException() {
        assertThrows(NullPointerException.class, () -> service.save(null));
    }

    @Test
    void givenSaved_whenFindById_thenReturnCorrectView(){
        long id = user.getId();
        when(userDao.findById(id)).thenReturn(user);
        when(documentDao.findById(id)).thenReturn(DaoUtils.createDocument(user));
        UserView view = service.findById(id);
        verify(userDao, atLeastOnce()).findById(id);
        assertEquals(user.getId(), view.getId());
        assertEquals(user.getFirstName(), view.getFirstName());
    }

    @Test
    void givenSaved_whenFindByFilter_thenReturnViewList() {
        UserRequestFilter filter = new UserRequestFilter();
        when(userDao.findByFilter(filter)).thenReturn(List.of(user));
        List<UserListView> users = service.findByFilter(filter);
        verify(userDao, atLeastOnce()).findByFilter(filter);
        assertTrue(users.size() > 0);
        assertEquals(user.getId(), users.get(0).getId());
        assertEquals(user.getFirstName(), users.get(0).getFirstName());
    }

    @Test
    void whenUpdate_thenDaoUpdateCall() {
        long id = user.getId();
        when(userDao.findById(id)).thenReturn(user);
        when(documentDao.findById(id)).thenReturn(DaoUtils.createDocument(user));
        UserUpdateView view = new UserUpdateView();
        view.setId(id);

        service.update(view);
        verify(userDao, atLeastOnce()).update(any(User.class));
    }
}