package edu.pl.masi.yellow.controller;

import edu.pl.masi.yellow.controller.api.v1.manage.Redactors;
import edu.pl.masi.yellow.entity.UserEntity;
import edu.pl.masi.yellow.manager.UserManager;
import edu.pl.masi.yellow.model.LoginToken;
import edu.pl.masi.yellow.utils.exceptions.ForbiddenException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class ManagerRedactorsTest {
    @Mock
    UserManager mockedManager;
    Redactors managerRedactors;

    @Before
    public void initTests() {
        MockitoAnnotations.initMocks(this);
        this.managerRedactors = new Redactors();
        this.managerRedactors.setUserManager(mockedManager);
    }

    @Test(expected = ForbiddenException.class)
    public void RequestIsForbiddenIfNoHeader() {
        managerRedactors.getAllUserList(null);
    }

    @Test
    public void CheckIfModeratorWhenHeaderExists() {
        LoginToken authToken = new LoginToken();

        when(mockedManager.userCanAccess(authToken, UserEntity.UserRole.MODERATOR))
                .thenReturn(true);

        managerRedactors.getAllUserList(authToken);
        managerRedactors.changeUserRoleToRedactor(authToken, 0);
        managerRedactors.removeRedactorStatus(authToken, 0);

        verify(mockedManager, times(3)).userCanAccess(authToken,
                UserEntity.UserRole.MODERATOR);
    }
}
