package edu.pl.masi.yellow.controller;

import edu.pl.masi.yellow.controller.api.v1.Auth;
import edu.pl.masi.yellow.manager.UserManager;
import edu.pl.masi.yellow.model.request.LoginRequest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class AuthTest {
    @Mock
    UserManager mockedManager;
    Auth authController;

    @Before
    public void initTests() {
        MockitoAnnotations.initMocks(this);
        this.authController = new Auth();
        this.authController.setUserManager(mockedManager);
    }

    @Test
    public void WhenLoggingRequestForwardedToManager() {
        LoginRequest request = new LoginRequest();
        request.setPassword("asdfasdf");
        request.setUsername("asdfasdf");

        authController.loginController(request);
        verify(mockedManager).loginUser(request);
        verify(mockedManager, never()).registerUser(ArgumentMatchers.any());
    }

    @Test
    public void WhenRegisterRequestFrowardedToManager() {
        LoginRequest request = new LoginRequest();
        request.setPassword("asdfasdf");
        request.setUsername("asdfasdf");

        authController.registerController(request);
        verify(mockedManager, never()).loginUser(ArgumentMatchers.any());
        verify(mockedManager).registerUser(request);
    }
}
