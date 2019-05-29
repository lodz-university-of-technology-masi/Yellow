package edu.pl.masi.yellow.controller;

import edu.pl.masi.yellow.controller.api.v1.Auth;
import edu.pl.masi.yellow.controller.api.v1.manage.Positions;
import edu.pl.masi.yellow.entity.UserEntity;
import edu.pl.masi.yellow.manager.PositionManager;
import edu.pl.masi.yellow.manager.UserManager;
import edu.pl.masi.yellow.model.LoginToken;
import edu.pl.masi.yellow.model.response.GenericResponse;
import edu.pl.masi.yellow.utils.exceptions.ForbiddenException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class ManagerPositionsTest {
    @Mock
    UserManager userManager;

    @Mock
    PositionManager positionManager;

    Positions positionsController;

    @Before
    public void initTests() {
        MockitoAnnotations.initMocks(this);

        this.positionsController = new Positions();
        this.positionsController.setPositionManager(positionManager);
        this.positionsController.setUserManager(userManager);
    }

    @Test
    public void CanGetListOfAllPositions() {
        when(this.positionManager.getAllPositions()).thenReturn(new ArrayList<>());

        assertNotNull(positionsController.getAllPositions());
        verify(this.positionManager, times(1)).getAllPositions();
    }

    @Test
    public void CanDeletePositions() {
        LoginToken login = new LoginToken("");

        when(this.positionManager.deletePositionById(anyInt())).thenReturn(new GenericResponse(""));
        when(this.userManager.userCanAccess(login, UserEntity.UserRole.MODERATOR)).thenReturn(true);

        assertNotNull(positionsController.deletePosition(login, 1));
        assertNotNull(positionsController.deletePosition(login, 3));
        assertNotNull(positionsController.deletePosition(login, 2));

        verify(this.positionManager, times(1)).deletePositionById(1);
        verify(this.positionManager, times(1)).deletePositionById(2);
        verify(this.positionManager, times(1)).deletePositionById(3);
    }

    @Test(expected = ForbiddenException.class)
    public void CantDeletePositionsException() {
        LoginToken login = new LoginToken("");

        when(this.positionManager.deletePositionById(anyInt())).thenReturn(new GenericResponse(""));
        when(this.userManager.userCanAccess(login, UserEntity.UserRole.MODERATOR)).thenReturn(false);

        assertNotNull(positionsController.deletePosition(login, 1));
        assertNotNull(positionsController.deletePosition(login, 3));
        assertNotNull(positionsController.deletePosition(login, 2));

        verify(this.positionManager, times(1)).deletePositionById(1);
        verify(this.positionManager, times(1)).deletePositionById(2);
        verify(this.positionManager, times(1)).deletePositionById(3);
    }

    @Test
    public void CanActivatePosition() {
        LoginToken login = new LoginToken("");

        when(this.positionManager.activatePositionById(anyInt())).thenReturn(new GenericResponse(""));
        when(this.userManager.userCanAccess(login, UserEntity.UserRole.MODERATOR)).thenReturn(true);

        assertNotNull(positionsController.activatePosition(login, 1));
        assertNotNull(positionsController.activatePosition(login, 3));
        assertNotNull(positionsController.activatePosition(login, 2));

        verify(this.positionManager, times(1)).activatePositionById(1);
        verify(this.positionManager, times(1)).activatePositionById(2);
        verify(this.positionManager, times(1)).activatePositionById(3);
    }

    @Test(expected = ForbiddenException.class)
    public void CantActivatePositionException() {
        LoginToken login = new LoginToken("");

        when(this.positionManager.activatePositionById(anyInt())).thenReturn(new GenericResponse(""));
        when(this.userManager.userCanAccess(login, UserEntity.UserRole.MODERATOR)).thenReturn(false);

        assertNotNull(positionsController.activatePosition(login, 1));
        assertNotNull(positionsController.activatePosition(login, 3));
        assertNotNull(positionsController.activatePosition(login, 2));

        verify(this.positionManager, times(1)).activatePositionById(1);
        verify(this.positionManager, times(1)).activatePositionById(2);
        verify(this.positionManager, times(1)).activatePositionById(3);
    }

    @Test
    public void CanDeactivatePosition() {
        LoginToken login = new LoginToken("");

        when(this.positionManager.deactivatePositionById(anyInt())).thenReturn(new GenericResponse(""));
        when(this.userManager.userCanAccess(login, UserEntity.UserRole.MODERATOR)).thenReturn(true);

        assertNotNull(positionsController.deactivatePosition(login, 1));
        assertNotNull(positionsController.deactivatePosition(login, 3));
        assertNotNull(positionsController.deactivatePosition(login, 2));

        verify(this.positionManager, times(1)).deactivatePositionById(1);
        verify(this.positionManager, times(1)).deactivatePositionById(2);
        verify(this.positionManager, times(1)).deactivatePositionById(3);
    }

    @Test(expected = ForbiddenException.class)
    public void CantDeactivatePosition() {
        LoginToken login = new LoginToken("");

        when(this.positionManager.deactivatePositionById(anyInt())).thenReturn(new GenericResponse(""));
        when(this.userManager.userCanAccess(login, UserEntity.UserRole.MODERATOR)).thenReturn(false);

        assertNotNull(positionsController.deactivatePosition(login, 1));
        assertNotNull(positionsController.deactivatePosition(login, 3));
        assertNotNull(positionsController.deactivatePosition(login, 2));

        verify(this.positionManager, times(1)).deactivatePositionById(1);
        verify(this.positionManager, times(1)).deactivatePositionById(2);
        verify(this.positionManager, times(1)).deactivatePositionById(3);
    }

    @Test
    public void CanCreatePosition() {
        LoginToken login = new LoginToken("");

        when(this.positionManager.createNewPosition(anyString())).thenReturn(new GenericResponse(""));
        when(this.userManager.userCanAccess(login, UserEntity.UserRole.MODERATOR)).thenReturn(true);

        assertNotNull(positionsController.createPosition(login, "a"));
        assertNotNull(positionsController.createPosition(login, "b"));
        assertNotNull(positionsController.createPosition(login, "c"));

        verify(this.positionManager, times(1)).createNewPosition("a");
        verify(this.positionManager, times(1)).createNewPosition("b");
        verify(this.positionManager, times(1)).createNewPosition("c");
    }
}
