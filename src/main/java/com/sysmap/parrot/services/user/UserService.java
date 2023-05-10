package com.sysmap.parrot.services.user;

import com.sysmap.parrot.entities.User;
import com.sysmap.parrot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService implements IUserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private User getAuthenticatedUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public String createUser(CreateUserRequest request) {
        var user = new User(
                request.name,
                request.email);
        if (!userRepository.findUserByEmail(request.email).isEmpty()) {
            return "The email is already in use";
        }

        var hash = passwordEncoder.encode(request.password);
        user.setPassword(hash);
        userRepository.save(user);
        return user.getId().toString();
    }
    public FindUserResponse findUserByEmail(String email) throws Exception {
        var user = userRepository.findUserByEmail(email).get();
        if (user == null) {
            throw new Exception("User not found");
        }
        var response = new FindUserResponse(
                user.getId(),
                user.getName(),
                user.getEmail());
        return response;
    }

    public FindUserResponse findUserByName(String name) throws Exception {
        var user = userRepository.findUserByName(name).get();
        if (user == null) {
            throw new Exception("User not found");
        }
         var response = new FindUserResponse(
                 user.getId(),
                 user.getName(),
                 user.getEmail());
        return response;
    }

    public User getUser(String email) {
        return userRepository.findUserByEmail(email).get();
    }
    public User getUserById(UUID id) {
        return userRepository.findUserById(id).get();

    }

}
