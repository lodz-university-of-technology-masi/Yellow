package edu.pl.masi.yellow.manager;

import edu.pl.masi.yellow.entity.UserEntity;
import edu.pl.masi.yellow.model.LoginToken;
import edu.pl.masi.yellow.model.request.LoginRequest;
import edu.pl.masi.yellow.model.response.GenericResponse;
import edu.pl.masi.yellow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManager {
    private UserRepository userRepository;

    public LoginToken loginUser(LoginRequest request) {
        UserEntity selectedUser = userRepository.findByUsername(
                request.getUsername());
        LoginToken response = new LoginToken();
        if (selectedUser != null &&
            selectedUser.getPassword().equals(request.getPassword())) {
            response = new LoginToken(request.getUsername(),
                    request.getPassword(),
                    selectedUser.getRole());
        }

        return response;
    }

    public GenericResponse registerUser(LoginRequest request) {
        UserEntity selectedUser = userRepository.findByUsername(
                request.getUsername());
        GenericResponse response;
        if (selectedUser != null) {
            response = new GenericResponse("User with name "
                    + request.getUsername() + " already exists");
        } else {
            selectedUser = new UserEntity(request.getUsername(),
                    request.getPassword());
            userRepository.save(selectedUser);
            response = new GenericResponse("User with name "
                    + request.getUsername() + " created");
        }
        return response;
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public GenericResponse setUserType(int userId, UserEntity.UserRole userRole) {
        UserEntity user = userRepository.findById(userId);
        GenericResponse response;

        if (user == null) {
            response = new GenericResponse("Cannot find user with id " + userId);
        } else if (user.getRole().equals(UserEntity.UserRole.MODERATOR)) {
            response = new GenericResponse("Cannot change moderator role");
        } else if (user.getRole().equals(userRole)) {
            response = new GenericResponse("User already a " + userRole);
        } else {
            user.setRole(userRole);
            userRepository.save(user);
            response = new GenericResponse("Changed user role to " + userRole);
        }

        return response;
    }

    private UserEntity getUserEntity(LoginToken token) {
        return userRepository.findByUsername(token.getUserName());
    }

    private UserEntity.UserRole getUserRole(LoginToken token) {
        return this.getUserEntity(token).getRole();
    }

    private boolean isValidUser(LoginToken token) {
        UserEntity user = getUserEntity(token);
        return user.getPassword().equals(
                token.getUserPassword());
    }

    public boolean userCanAccess(LoginToken token, UserEntity.UserRole expectedRole) {
        return this.isValidUser(token) &&
            this.getUserRole(token).equals(expectedRole);
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
