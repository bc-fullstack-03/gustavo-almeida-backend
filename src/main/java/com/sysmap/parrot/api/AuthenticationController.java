package com.sysmap.parrot.api;

import com.sysmap.parrot.services.authentication.AuthenticateRequest;
import com.sysmap.parrot.services.authentication.AuthenticateResponse;
import com.sysmap.parrot.services.authentication.IAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/parrot/authentication")
public class AuthenticationController {

    @Autowired
    private IAuthenticationService authenticationService;

    @PostMapping("authenticate")
    public ResponseEntity<AuthenticateResponse> authenticate(@RequestBody AuthenticateRequest request){
        try {
            return ResponseEntity.ok().body(authenticationService.authenticate(request));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage() , HttpStatus.UNAUTHORIZED);
        }
    }
}
