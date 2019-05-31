package edu.pl.masi.yellow.controller.api.v1.manage;

import edu.pl.masi.yellow.entity.UserEntity;
import edu.pl.masi.yellow.manager.UserManager;
import edu.pl.masi.yellow.model.LoginToken;
import edu.pl.masi.yellow.model.response.GenericResponse;
import edu.pl.masi.yellow.utils.exceptions.ForbiddenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Redactors {
    UserManager userManager;

    @RequestMapping(value = "/api/v1/manage/redactors", method = RequestMethod.GET)
    public List<UserEntity> getAllUserList(@RequestHeader(name = "Auth-Token", required = false)
                                                       LoginToken authToken) {
        if (authToken != null && userManager.userCanAccess(authToken,
                UserEntity.UserRole.MODERATOR))
            return userManager.getAllUsers();
        else
            throw new ForbiddenException();
    }

    @RequestMapping(value = "/api/v1/manage/redactors/{id}", method = RequestMethod.PUT)
    public GenericResponse changeUserRoleToRedactor(@RequestHeader(name = "Auth-Token",
            required = false) LoginToken authToken, @PathVariable("id") int id) {
        if (authToken != null && userManager.userCanAccess(authToken,
                UserEntity.UserRole.MODERATOR))
            return userManager.setUserType(id, UserEntity.UserRole.REDACTOR);
        else
            throw new ForbiddenException();
    }

    @RequestMapping(value = "/api/v1/manage/redactors/{id}", method = RequestMethod.DELETE)
    public GenericResponse removeRedactorStatus(@RequestHeader(name = "Auth-Token",
            required = false) LoginToken authToken, @PathVariable("id") int id) {
        if (authToken != null && userManager.userCanAccess(authToken,
                UserEntity.UserRole.MODERATOR))
            return userManager.setUserType(id, UserEntity.UserRole.USER);
        else
            throw new ForbiddenException();
    }

    @RequestMapping(value = "/api/v1/manage/users", method = RequestMethod.POST)
    public UserEntity updateUser(@RequestHeader(name = "Auth-Token",
            required = false) LoginToken authToken, UserEntity userEntity) {
        if (authToken != null && userManager.userCanAccess(authToken,
                UserEntity.UserRole.MODERATOR)) {
            return userManager.updateUser(userEntity);
        } else {
            throw new ForbiddenException();
        }
    }

    @RequestMapping(value = "/api/v1/manage/users/{id}", method = RequestMethod.DELETE)
    public GenericResponse deleteUser(@RequestHeader(name = "Auth-Token",
            required = false) LoginToken authToken, @PathVariable("id") int id) {
        if (authToken != null && userManager.userCanAccess(authToken,
                UserEntity.UserRole.MODERATOR)) {
            return userManager.deleteUser(id);
        } else {
            throw new ForbiddenException();
        }
    }

    @Autowired
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }
}
