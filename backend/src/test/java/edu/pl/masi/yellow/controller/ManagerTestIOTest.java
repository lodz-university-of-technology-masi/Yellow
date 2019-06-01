package edu.pl.masi.yellow.controller;

import edu.pl.masi.yellow.controller.api.v1.manage.TestIO;
import edu.pl.masi.yellow.entity.UserEntity;
import edu.pl.masi.yellow.manager.TestFormatterManager;
import edu.pl.masi.yellow.manager.UserManager;
import edu.pl.masi.yellow.model.LoginToken;
import edu.pl.masi.yellow.utils.exceptions.ForbiddenException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

public class ManagerTestIOTest {
    @Mock
    private UserManager userManager;

    @Mock
    private TestFormatterManager testFormatterManager;

    @Mock
    private MultipartFile multipartFile;

    private TestIO testIO;

    @Before
    public void initTests() {
        MockitoAnnotations.initMocks(this);

        this.testIO = new TestIO();

        this.testIO.setUserManager(userManager);
        this.testIO.setTestIOFormatter(testFormatterManager);
    }

    @Test
    public void CanGenerateCSV() {
        LoginToken login = new LoginToken("user:password");
        when(userManager.userCanAccess(any(), any())).thenReturn(true);

        this.testIO.generateTestCSV(login, 0, null);

        verify(testFormatterManager).getCSVResponse("user", 0, null);
    }

    @Test(expected = ForbiddenException.class)
    public void ForbiddenGenerateCSV() {
        LoginToken login = new LoginToken("user:password");
        when(userManager.userCanAccess(any(), any())).thenReturn(false);

        this.testIO.generateTestCSV(login, 0, null);

        verify(testFormatterManager).getCSVResponse("user", 0, null);
    }

    @Test
    public void CanGeneratePDF() {
        LoginToken login = new LoginToken("user:password");
        when(userManager.userCanAccess(any(), any())).thenReturn(true);

        this.testIO.generateTestPDF(login, 0);

        verify(testFormatterManager).getPDFResponse("user", 0);
    }

    @Test(expected = ForbiddenException.class)
    public void ForbiddenGeneratePDF() {
        LoginToken login = new LoginToken("user:password");
        when(userManager.userCanAccess(any(), any())).thenReturn(false);

        this.testIO.generateTestPDF(login, 0);

        verify(testFormatterManager).getPDFResponse("user", 0);
    }

    @Test
    public void CanUploadPDF() throws IOException {
        LoginToken login = new LoginToken("user:password");
        when(userManager.userCanAccess(any(), any())).thenReturn(true);
        when(multipartFile.getBytes()).thenReturn("fileContent".getBytes());

        this.testIO.importFromCSV(login, multipartFile);

        verify(testFormatterManager).uploadCSV("user", "fileContent", null);
    }

    @Test(expected = ForbiddenException.class)
    public void ForbiddenUploadPDF() throws IOException {
        LoginToken login = new LoginToken("user:password");
        when(userManager.userCanAccess(any(), any())).thenReturn(false);
        when(multipartFile.getBytes()).thenReturn("fileContent".getBytes());

        this.testIO.importFromCSV(login, multipartFile);

        verify(testFormatterManager).uploadCSV("user", "fileContent", "name");
    }
}
