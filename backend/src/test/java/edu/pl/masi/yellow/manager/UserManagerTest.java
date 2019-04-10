package edu.pl.masi.yellow.manager;

import edu.pl.masi.yellow.entity.UserEntity;
import edu.pl.masi.yellow.model.LoginToken;
import edu.pl.masi.yellow.model.request.LoginRequest;
import edu.pl.masi.yellow.model.response.RegisterResponse;
import edu.pl.masi.yellow.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static junit.framework.TestCase.*;

public class UserManagerTest {
    @Mock
    private UserRepository mockedRepository;

    private UserManager userManager;

    @Before
    public void initTests()  {
        MockitoAnnotations.initMocks(this);
        this.userManager = new UserManager();
        this.userManager.setUserRepository(mockedRepository);
    }

    @Test
    public void ReturnsValidUserResponseWhenUserCorrect() {
        when(mockedRepository.findByUsername("Kowalski")).thenReturn(
                new UserEntity("Kowalski", "SecretPassowrd"));
        LoginToken token = userManager.loginUser(new LoginRequest("Kowalski",
                "SecretPassowrd"));
        assertTrue(token.isValid());
        assertEquals("Kowalski", token.getUserName());
        assertEquals("SecretPassowrd", token.getUserPassword());
    }

    @Test
    public void ReturnsInvalidUserWhenPasswordIncorrect() {
        when(mockedRepository.findByUsername("Kowalski")).thenReturn(
                new UserEntity("Kowalski", "SecretPassowrd"));
        LoginToken token = userManager.loginUser(new LoginRequest("Kowalski",
                "wrongPassword"));
        assertFalse(token.isValid());
    }

    @Test
    public void ReturnsInvalidUserWhenNoUserFound() {
        when(mockedRepository.findByUsername("Kowalski")).thenReturn(null);
        LoginToken token = userManager.loginUser(
                new LoginRequest("Kowalski", "anyPassword"));
        assertFalse(token.isValid());
    }

    @Test
    public void CanCreateNewUserFromRequest() {
        when(mockedRepository.findByUsername("Kowalski")).thenReturn(null);
        RegisterResponse response = userManager.registerUser(
                new LoginRequest("Kowalski", "password"));
        verify(mockedRepository).save(ArgumentMatchers.any());
        assertEquals("User with name Kowalski created", response.getStatus());
    }

    @Test
    public void CannotCreateNewUserIfUserExists() {
        when(mockedRepository.findByUsername("Kowalski")).thenReturn(
                new UserEntity("Kowalski", "password"));

        RegisterResponse response = userManager.registerUser(
                new LoginRequest("Kowalski", "password"));
        verify(mockedRepository, never()).save(ArgumentMatchers.any());
        assertEquals("User with name Kowalski already exists", response.getStatus());
    }

    @Test
    public void UserCanAccessSiteWhenRoleOK() {
        UserEntity user = new UserEntity("Kowalski", "password");
        user.setRole("administrator");

        when(mockedRepository.findByUsername("Kowalski")).thenReturn(
                user);
        LoginToken token = new LoginToken("Kowalski:password");
        assertTrue(userManager.userCanAccess(token, "administrator"));
        assertFalse(userManager.userCanAccess(token, "client"));
    }
}
