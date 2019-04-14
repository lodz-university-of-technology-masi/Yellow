package edu.pl.masi.yellow.controller.api.v1;

import edu.pl.masi.yellow.manager.UserManager;
import edu.pl.masi.yellow.model.LoginToken;
import edu.pl.masi.yellow.model.request.LoginRequest;
import edu.pl.masi.yellow.model.response.RegisterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Auth {
    UserManager userManager;

    /*
     *  Performs login and returns token
     *  Input JSON:
     *  {
     *    "username": "Jan Kowalski",
     *    "password": "Secret password"
     *  }
     *
     *  Output:
     *  {
     *    "token": "login token for authorization",
     *    "valid": true/false
     *  }
     *
     *  valid field informs if logged successfully
     */
    @RequestMapping("/api/v1/login")
    public LoginToken loginController(@RequestBody LoginRequest loginRequest) {
        return userManager.loginUser(loginRequest);
    }

    /*
     *  Performs user registration
     *  Input JSON:
     *  {
     *    "username": "Jan Kowalski",
     *    "password": "Secret password"
     *  }
     *
     *  Output:
     *  {
     *    "status": "Textual status of registration process"
     *  }
     */
    @RequestMapping("/api/v1/register")
    public RegisterResponse registerController(@RequestBody LoginRequest request) {
        return userManager.registerUser(request);
    }

    @Autowired
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }
}
