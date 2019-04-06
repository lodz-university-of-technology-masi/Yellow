package edu.pl.masi.yellow.manager;

import edu.pl.masi.yellow.entity.UserEntity;
import edu.pl.masi.yellow.model.LoginToken;
import edu.pl.masi.yellow.model.request.LoginRequest;
import edu.pl.masi.yellow.model.response.RegisterResponse;
import edu.pl.masi.yellow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManager {
    @Autowired
    UserRepository userRepository;

    public LoginToken loginUser(LoginRequest request) {
        UserEntity selectedUser = userRepository.findByUsername(
                request.getUsername());
        LoginToken response = new LoginToken();
        if (selectedUser != null &&
            selectedUser.getPassword().equals(request.getPassword())) {
            response = new LoginToken(request.getUsername(),
                    request.getPassword());
        }

        return response;
    }

    public RegisterResponse registerUser(LoginRequest request) {
        UserEntity selectedUser = userRepository.findByUsername(
                request.getUsername());
        RegisterResponse response;
        if (selectedUser != null) {
            response = new RegisterResponse("User with name "
                    + request.getUsername() + " already exists");
        } else {
            selectedUser = new UserEntity(request.getUsername(),
                    request.getPassword());
            userRepository.save(selectedUser);
            response = new RegisterResponse("User with name "
                    + request.getUsername() + " created");
        }
        return response;
    }

    private UserEntity getUserEntity(LoginToken token) {
        return userRepository.findByUsername(token.getUserName());
    }

    private String getUserRole(LoginToken token) {
        return this.getUserEntity(token).getRole();
    }

    private boolean isValidUser(LoginToken token) {
        UserEntity user = getUserEntity(token);
        return user.getPassword().equals(
                token.getUserPassword());
    }

    public boolean userCanAccess(LoginToken token, String expectedRole) {
        return this.isValidUser(token) &&
            this.getUserRole(token).equals(expectedRole);
    }
}
