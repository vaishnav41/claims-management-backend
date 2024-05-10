package com.cognizant.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authenticate")
@CrossOrigin(origins = "http://localhost:4200/")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @PostMapping("users")
    public ResponseEntity<?> authenticate(@RequestBody UserRequest userRequest){
        UserDTO userDTO = userService.authenticateUser(userRequest.getUserName(), userRequest.getPassword());
        return new ResponseEntity<UserDTO>(userDTO, HttpStatus.ACCEPTED);
    }
}
