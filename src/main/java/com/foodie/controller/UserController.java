package com.foodie.controller;

import com.foodie.io.UserRequest;
import com.foodie.io.UserResponse;
import com.foodie.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse register(@RequestBody UserRequest request){
        return  userService.registerUser(request);

    }
}
