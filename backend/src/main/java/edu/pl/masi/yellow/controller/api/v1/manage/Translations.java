package edu.pl.masi.yellow.controller.api.v1.manage;

import edu.pl.masi.yellow.entity.UserEntity;
import edu.pl.masi.yellow.manager.TranslationsManager;
import edu.pl.masi.yellow.manager.UserManager;
import edu.pl.masi.yellow.model.LoginToken;
import edu.pl.masi.yellow.model.request.TranslationRequest;
import edu.pl.masi.yellow.model.response.GenericResponse;
import edu.pl.masi.yellow.repository.UserRepository;
import edu.pl.masi.yellow.utils.exceptions.ForbiddenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Translations {
    private TranslationsManager translationsManager;
    private UserManager userManager;


    @RequestMapping(path = "/api/v1/translate/test", method = RequestMethod.POST)
    public GenericResponse translateTest(@RequestHeader(name = "Auth-Token", required = false) LoginToken authToken,
                                         @RequestBody TranslationRequest translationRequest) {
        if (authToken != null && userManager.userCanAccess(authToken, UserEntity.UserRole.REDACTOR)) {
            return this.translationsManager.translateTest(authToken.getUserName(), translationRequest);
        } else {
            throw new ForbiddenException();
        }
    }

    @Autowired
    public void setTranslationsManager(TranslationsManager translationsManager) {
        this.translationsManager = translationsManager;
    }

    @Autowired
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }
}
