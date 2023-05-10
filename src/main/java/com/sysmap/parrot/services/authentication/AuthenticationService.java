package com.sysmap.parrot.services.authentication;

import com.sysmap.parrot.services.security.IJwtService;
import com.sysmap.parrot.services.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements IAuthenticationService{

    @Autowired
    private IUserService userService;
    @Autowired
    private IJwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public AuthenticateResponse authenticate (AuthenticateRequest request) throws Exception {
        var user = userService.getUser(request.email);

        if(user == null) {
            throw new Exception("User does not exist");
        }

        if (!passwordEncoder.matches(request.password, user.getPassword())) {
            throw new Exception("Invalid Password");
        }

        var token = jwtService.generateToken(user.getId());
        var response = new AuthenticateResponse();
        response.setUserId(user.getId());
        response.setToken(token);

        return response;
    }
}
