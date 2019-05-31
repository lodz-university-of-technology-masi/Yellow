package edu.pl.masi.yellow.controller.api.v1.manage;

import edu.pl.masi.yellow.entity.UserEntity;
import edu.pl.masi.yellow.manager.TestManager;
import edu.pl.masi.yellow.manager.UserManager;
import edu.pl.masi.yellow.model.LoginToken;
import edu.pl.masi.yellow.model.request.QuestionAddRequest;
import edu.pl.masi.yellow.model.response.GenericResponse;
import edu.pl.masi.yellow.model.response.TestDefResponse;
import edu.pl.masi.yellow.utils.exceptions.ForbiddenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Tests {
    private TestManager testManager;
    private UserManager userManager;


    @RequestMapping(value = "api/v1/manage/tests/", method = RequestMethod.GET)
    public List<TestDefResponse> getAllTests(@RequestHeader(name = "Auth-Token",
            required = false) LoginToken authToken) {
        if (authToken != null && userManager.userCanAccess(authToken, UserEntity.UserRole.MODERATOR)) {
            return testManager.getAllTests();
        } else {
            throw new ForbiddenException();
        }
    }

    @RequestMapping(value = "/api/v1/manage/tests/redactor/{id}",
            method = RequestMethod.GET)
    public List<TestDefResponse> getAllTestsByRedactor(
            @PathVariable("id") int redactorId) {
        return testManager.getAllTestsByRedactor(redactorId);
    }

    @RequestMapping(value = "/api/v1/manage/tests/id/{id}", method = RequestMethod.GET)
    public TestDefResponse getTestById(@PathVariable("id") int testId) {
        return testManager.getTestById(testId);
    }

    @RequestMapping(value = "/api/v1/manage/tests/me",
            method = RequestMethod.GET)
    public List<TestDefResponse> getAllMyTests(@RequestHeader(name = "Auth-Token",
            required = false) LoginToken authToken) {
        if (authToken != null && userManager.userCanAccess(authToken, UserEntity.UserRole.REDACTOR)) {
            return testManager.getAllTestsByRedactor(userManager.getAllUsers().stream()
                    .filter(u -> u.getUsername().equals(authToken.getUserName()))
                    .map(UserEntity::getId).findAny().orElse(0));
        } else {
            throw new ForbiddenException();
        }
    }

    @RequestMapping(value = "/api/v1/manage/tests/id/{id}", method = RequestMethod.DELETE)
    public GenericResponse removeTestWithAllQuestions(@RequestHeader(name = "Auth-Token", required = false) LoginToken authToken,
                                                      @PathVariable("id") int id) {
        if (authToken != null && (userManager.userCanAccess(authToken, UserEntity.UserRole.REDACTOR)
                || userManager.userCanAccess(authToken, UserEntity.UserRole.MODERATOR))) {
            return this.testManager.removeTest(authToken.getUserName(), id);
        } else {
            throw new ForbiddenException();
        }
    }

    @RequestMapping(value = "/api/v1/manage/tests/add", method = RequestMethod.GET)
    public TestDefResponse addTest(@RequestHeader(name = "Auth-Token", required = false) LoginToken authToken,
                                   @RequestParam("name") String testName) {
        if (authToken != null && (userManager.userCanAccess(authToken, UserEntity.UserRole.REDACTOR))) {
            return this.testManager.addTest(authToken.getUserName(), testName);
        } else {
            throw new ForbiddenException();
        }
    }

    @RequestMapping(value = "/api/v1/manage/tests/modify/{testId}/{questionId}",
            method = RequestMethod.DELETE)
    public GenericResponse removeQuestionFromTest(@RequestHeader(name = "Auth-Token", required = false) LoginToken authToken,
                                                  @PathVariable("testId") int testId, @PathVariable("questionId") int questionId) {
        if (authToken != null && (userManager.userCanAccess(authToken, UserEntity.UserRole.REDACTOR)
                || userManager.userCanAccess(authToken, UserEntity.UserRole.MODERATOR))) {
            return this.testManager.removeQuestionFromTest(authToken.getUserName(), testId, questionId);
        } else {
            throw new ForbiddenException();
        }
    }

    @RequestMapping(value = "/api/v1/manage/tests/modify/{testId}",
            method = RequestMethod.POST)
    public GenericResponse addQuestionToTest(@RequestHeader(name = "Auth-Token", required = false) LoginToken authToken,
                                             @PathVariable("testId") int testId,
                                             @RequestBody QuestionAddRequest request) {
        if (authToken != null && (userManager.userCanAccess(authToken, UserEntity.UserRole.REDACTOR)
                || userManager.userCanAccess(authToken, UserEntity.UserRole.MODERATOR))) {
            return this.testManager.addQuestionToTest(authToken.getUserName(), testId, request);
        } else {
            throw new ForbiddenException();
        }
    }

    @Autowired
    public void setTestManager(TestManager testManager) {
        this.testManager = testManager;
    }

    @Autowired
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }
}
