package edu.pl.masi.yellow.controller.api.v1.manage;

import edu.pl.masi.yellow.entity.UserEntity;
import edu.pl.masi.yellow.manager.TestFormatterManager;
import edu.pl.masi.yellow.manager.UserManager;
import edu.pl.masi.yellow.model.LoginToken;
import edu.pl.masi.yellow.utils.exceptions.ForbiddenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class TestIO {
    private UserManager userManager;
    private TestFormatterManager testIOFormatter;

    @RequestMapping(path = "/api/v1/tests/io/csv/{testId}", method = RequestMethod.GET)
    public void generateTestCSV(@RequestHeader(name = "Auth-Token", required = false) LoginToken authToken,
                                @PathVariable("testId") int testId,
                                HttpServletResponse servletResponse) {
        if (authToken != null && (userManager.userCanAccess(authToken, UserEntity.UserRole.REDACTOR)
                || userManager.userCanAccess(authToken, UserEntity.UserRole.MODERATOR))) {
            this.testIOFormatter.getCSVResponse(authToken.getUserName(), testId, servletResponse);
        } else {
            throw new ForbiddenException();
        }
    }

    @RequestMapping(path = "/api/v1/tests/io/pdf/{testId}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> generateTestPDF(@RequestHeader(name = "Auth-Token", required = false) LoginToken authToken,
                                          @PathVariable("testId") int testId) {
        if (authToken != null && (userManager.userCanAccess(authToken, UserEntity.UserRole.REDACTOR)
                || userManager.userCanAccess(authToken, UserEntity.UserRole.MODERATOR))) {
            return this.testIOFormatter.getPDFResponse(authToken.getUserName(), testId);
        } else {
            throw new ForbiddenException();
        }
    }

    @Autowired
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    @Autowired
    public void setTestIOFormatter(TestFormatterManager ioFormatter) {
        this.testIOFormatter = ioFormatter;
    }
}
