package edu.pl.masi.yellow.controller.api.v1.manage;

import edu.pl.masi.yellow.entity.PositionEntity;
import edu.pl.masi.yellow.entity.UserEntity;
import edu.pl.masi.yellow.manager.PositionManager;
import edu.pl.masi.yellow.manager.QuestionManager;
import edu.pl.masi.yellow.manager.UserManager;
import edu.pl.masi.yellow.model.LoginToken;

import edu.pl.masi.yellow.model.response.GenericResponse;
import edu.pl.masi.yellow.utils.exceptions.ForbiddenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class Positions {
    private PositionManager positionManager;
    private UserManager userManager;
    private QuestionManager questionManager;

    @RequestMapping(value = "/api/v1/manage/positions", method = RequestMethod.GET)
    public List<PositionEntity> getAllPositions() {
        return this.positionManager.getAllPositions();
    }

    @RequestMapping(value = "/api/v1/manage/positions/{id}", method = RequestMethod.DELETE)
    public GenericResponse deletePosition(@RequestHeader(name = "Auth-Token", required = false) LoginToken authToken,
                                          @PathVariable("id") int positionId) {
        if (authToken != null && userManager.userCanAccess(authToken, UserEntity.UserRole.MODERATOR)) {
            return this.positionManager.deletePositionById(positionId);
        } else {
            throw new ForbiddenException();
        }
    }

    @RequestMapping(value = "/api/v1/manage/positions/{lang}", method = RequestMethod.GET)
    public List<PositionEntity> getAllTestsByLang(@PathVariable("lang") String language) {
        return this.positionManager.getAllPositions().stream()
                .filter(p -> p.getTestEntities().stream()
                        .anyMatch(t -> questionManager.getAllQuestionsByTest(t)
                                .stream().anyMatch(q -> q.getLanguage().equals(language))))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/api/v1/manage/positions/{id}/activate", method = RequestMethod.PUT)
    public GenericResponse activatePosition(@RequestHeader(name = "Auth-Token", required = false) LoginToken authToken,
                                            @PathVariable("id") int positionId) {
        if (authToken != null && userManager.userCanAccess(authToken, UserEntity.UserRole.MODERATOR)) {
            return this.positionManager.activatePositionById(positionId);
        } else {
            throw new ForbiddenException();
        }
    }

    @RequestMapping(value = "/api/v1/manage/positions/{id}/deactivate", method = RequestMethod.PUT)
    public GenericResponse deactivatePosition(@RequestHeader(name = "Auth-Token", required = false) LoginToken authToken,
                                              @PathVariable("id") int positionId) {
        if (authToken != null && userManager.userCanAccess(authToken, UserEntity.UserRole.MODERATOR)) {
            return this.positionManager.deactivatePositionById(positionId);
        } else {
            throw new ForbiddenException();
        }
    }

    @RequestMapping(value = "/api/v1/manage/positions/create", method = RequestMethod.GET)
    public PositionEntity createPosition(@RequestHeader(name = "Auth-Token", required = false) LoginToken authToken,
                                         @RequestParam("name") String name) {
        if (authToken != null && userManager.userCanAccess(authToken, UserEntity.UserRole.MODERATOR)) {
            return this.positionManager.createNewPosition(name);
        } else {
            throw new ForbiddenException();
        }
    }

    @RequestMapping(value = "/api/v1/manage/positions/{positionId}/{testId}", method = RequestMethod.PUT)
    public GenericResponse addTestToPosition(@RequestHeader(name = "Auth-Token", required = false) LoginToken authToken,
                                             @PathVariable("positionId") int positionId,
                                             @PathVariable("testId") int testId) {
        if (authToken != null && userManager.userCanAccess(authToken, UserEntity.UserRole.MODERATOR)) {
            return this.positionManager.addTestToPosition(positionId, testId);
        } else {
            throw new ForbiddenException();
        }
    }

    @RequestMapping(value = "/api/v1/manage/positions/{positionId}/{testId}", method = RequestMethod.DELETE)
    public GenericResponse removeTestFromPosition(@RequestHeader(name = "Auth-Token", required = false) LoginToken authToken,
                                                  @PathVariable("positionId") int positionId,
                                                  @PathVariable("testId") int testId) {
        if (authToken != null && userManager.userCanAccess(authToken, UserEntity.UserRole.MODERATOR)) {
            return this.positionManager.removeTestFromPosition(positionId, testId);
        } else {
            throw new ForbiddenException();
        }
    }

    @Autowired
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    @Autowired
    public void setPositionManager(PositionManager positionManager) {
        this.positionManager = positionManager;
    }

    @Autowired
    public void setQuestionManager(QuestionManager questionManager) {
        this.questionManager = questionManager;
    }
}
