package edu.pl.masi.yellow.controller;

import edu.pl.masi.yellow.controller.api.v1.manage.Tests;
import edu.pl.masi.yellow.entity.UserEntity;
import edu.pl.masi.yellow.manager.TestManager;
import edu.pl.masi.yellow.manager.UserManager;
import edu.pl.masi.yellow.model.LoginToken;
import edu.pl.masi.yellow.model.request.QuestionAddRequest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Any;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static junit.framework.TestCase.*;
import static org.mockito.Mockito.*;

public class ManagerTestsTest {
    @Mock
    TestManager testManager;

    @Mock
    UserManager userManager;

    Tests controller;

    @Before
    public void initTests() {
        MockitoAnnotations.initMocks(this);

        this.controller = new Tests();
        this.controller.setTestManager(testManager);
        this.controller.setUserManager(userManager);
    }

    @Test
    public void CanAcquireTestsByRedactorId() {
        when(testManager.getAllTestsByRedactor(anyInt())).thenReturn(new ArrayList<>());

        assertTrue(this.controller.getAllTestsByRedactor(4).isEmpty());
        assertTrue(this.controller.getAllTestsByRedactor(8).isEmpty());

        verify(testManager, times(1)).getAllTestsByRedactor(4);
        verify(testManager, times(1)).getAllTestsByRedactor(8);
        verify(testManager, never()).getAllTestsByRedactor(0);
    }

    @Test
    public void CanGetTestById() {
        when(testManager.getTestById(anyInt())).thenReturn(null);

        assertNull(this.controller.getTestById(3));
        assertNull(this.controller.getTestById(5));
        assertNull(this.controller.getTestById(7));
        assertNull(this.controller.getTestById(3));
        assertNull(this.controller.getTestById(3));

        verify(testManager, times(3)).getTestById(3);
        verify(testManager, times(1)).getTestById(5);
        verify(testManager, times(1)).getTestById(7);
    }

    @Test
    public void CanGetMyTests() {
        LoginToken loginToken = new LoginToken("testUser:testPassword");

        List<UserEntity> userList = new ArrayList<>();
        UserEntity currentRedactor = new UserEntity("testUser", "testPassword");
        currentRedactor.setRole(UserEntity.UserRole.REDACTOR);
        userList.add(currentRedactor);

        when(testManager.getAllTestsByRedactor(1)).thenReturn(new ArrayList<>());
        when(userManager.userCanAccess(loginToken, UserEntity.UserRole.REDACTOR)).thenReturn(true);
        when(userManager.getAllUsers()).thenReturn(userList);

        assertNotNull(this.controller.getAllMyTests(loginToken));
    }

    @Test
    public void CanRemoveQuestionAsModerator() {
        LoginToken loginToken = new LoginToken("testUser:testPassword");
        UserEntity currentRedactor = new UserEntity("testUser", "testPassword");
        currentRedactor.setRole(UserEntity.UserRole.MODERATOR);

        when(userManager.userCanAccess(loginToken, UserEntity.UserRole.MODERATOR)).thenReturn(true);

        assertNull(this.controller.removeQuestionFromTest(loginToken, 0, 0));
        verify(testManager).removeQuestionFromTest(loginToken.getUserName(), 0, 0);
    }

    @Test
    public void CanRemoveQuestionAsRedactor() {
        LoginToken loginToken = new LoginToken("testUser:testPassword");
        UserEntity currentRedactor = new UserEntity("testUser", "testPassword");
        currentRedactor.setRole(UserEntity.UserRole.REDACTOR);

        when(userManager.userCanAccess(loginToken, UserEntity.UserRole.REDACTOR)).thenReturn(true);

        assertNull(this.controller.removeQuestionFromTest(loginToken, 0, 0));
        verify(testManager).removeQuestionFromTest(loginToken.getUserName(), 0, 0);
    }

    @Test
    public void CanAddQuestionAsModerator() {

        LoginToken loginToken = new LoginToken("testUser:testPassword");
        UserEntity currentRedactor = new UserEntity("testUser", "testPassword");
        currentRedactor.setRole(UserEntity.UserRole.MODERATOR);
        QuestionAddRequest request = new QuestionAddRequest();

        when(userManager.userCanAccess(loginToken, UserEntity.UserRole.MODERATOR)).thenReturn(true);

        assertNull(this.controller.addQuestionToTest(loginToken, 0, request));
        verify(testManager).addQuestionToTest(loginToken.getUserName(), 0, request);
    }

    @Test
    public void CanAddQuestionAsRedator() {

        LoginToken loginToken = new LoginToken("testUser:testPassword");
        UserEntity currentRedactor = new UserEntity("testUser", "testPassword");
        currentRedactor.setRole(UserEntity.UserRole.REDACTOR);
        QuestionAddRequest request = new QuestionAddRequest();

        when(userManager.userCanAccess(loginToken, UserEntity.UserRole.REDACTOR)).thenReturn(true);

        assertNull(this.controller.addQuestionToTest(loginToken, 0, request));
        verify(testManager).addQuestionToTest(loginToken.getUserName(), 0, request);
    }
}