package com.sysmap.parrot.services.user;

import com.sysmap.parrot.entities.User;

import java.util.UUID;

public interface IUserService {

    String createUser(CreateUserRequest request);
    FindUserResponse findUserByEmail(String email) throws Exception ;
    FindUserResponse findUserByName(String name) throws Exception ;
    User getUserById(UUID id);
    User getUser(String email);
}
