package edu.pl.masi.yellow.controller.api.v1.manage;

import edu.pl.masi.yellow.entity.UserEntity;
import edu.pl.masi.yellow.manager.AnswerManager;
import edu.pl.masi.yellow.manager.UserManager;
import edu.pl.masi.yellow.model.LoginToken;
import edu.pl.masi.yellow.model.request.TestSolutionRequest;
import edu.pl.masi.yellow.model.response.GenericResponse;
import edu.pl.masi.yellow.model.response.TestSolutionResponse;
import edu.pl.masi.yellow.utils.exceptions.ForbiddenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Answer {
    private AnswerManager answerManager;
    private UserManager userManager;

    @RequestMapping(value = "/api/v1/manage/answer", method = RequestMethod.POST)
    public GenericResponse answerTest(@RequestHeader(name = "Auth-Token", required = false) LoginToken authToken,
                                      @RequestBody TestSolutionRequest answerRequest) {
        if (authToken != null && userManager.userCanAccess(authToken, UserEntity.UserRole.USER)) {
            return this.answerManager.answerTest(authToken.getUserName(), answerRequest);
        } else {
            throw new ForbiddenException();
        }
    }

    @RequestMapping(value = "/api/v1/manage/meanswer", method = RequestMethod.GET)
    public List<TestSolutionResponse> getMyAnswers(@RequestHeader(name = "Auth-Token",
            required = false) LoginToken authToken) {
        if (authToken != null && userManager.userCanAccess(authToken, UserEntity.UserRole.REDACTOR)) {
            return this.answerManager.getAnswerTest(authToken.getUserName());
        } else {
            throw new ForbiddenException();
        }
    }

    @RequestMapping(value = "/api/v1/manage/meanswer/{id}", method = RequestMethod.PUT)
    public GenericResponse acceptAnswer(@RequestHeader(name = "Auth-Token",
            required = false) LoginToken authToken, @PathVariable("id") int answerId) {
        if (authToken != null && userManager.userCanAccess(authToken, UserEntity.UserRole.REDACTOR)) {
            return this.answerManager.acceptAnswer(answerId);
        } else {
            throw new ForbiddenException();
        }
    }

    @RequestMapping(value = "/api/v1/manage/meanswer/{id}", method = RequestMethod.DELETE)
    public GenericResponse deleteAnswer(@RequestHeader(name = "Auth-Token",
            required = false) LoginToken authToken, @PathVariable("id") int answerId) {
        if (authToken != null && userManager.userCanAccess(authToken, UserEntity.UserRole.REDACTOR)) {
            return this.answerManager.refuseAnswer(answerId);
        } else {
            throw new ForbiddenException();
        }
    }

    @Autowired
    public void setAnswerManager(AnswerManager answerManager) {
        this.answerManager = answerManager;
    }

    @Autowired
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }
}
