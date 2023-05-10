package com.sysmap.parrot.api;

import com.sysmap.parrot.services.user.CreateUserRequest;
import com.sysmap.parrot.services.user.FindUserResponse;
import com.sysmap.parrot.services.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/parrot/user")
public class UserController {
    @Autowired
    private IUserService userService;
    @PostMapping("createUser")
    public ResponseEntity<String> createUser(@RequestBody CreateUserRequest request) {
        var response = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }
    @GetMapping("getUserByEmail")
    public ResponseEntity<FindUserResponse> getUserByEmail(String email) {
        try {
            return ResponseEntity.ok().body(userService.findUserByEmail(email));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("getUserByName")
    public ResponseEntity<FindUserResponse> getUserByName(String name) {
        try {
            return ResponseEntity.ok().body(userService.findUserByName(name));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
