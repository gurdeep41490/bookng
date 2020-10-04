package com.org.jwt.controller;


import com.org.jwt.model.User;
import com.org.jwt.service.UserService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(path = "/register")
    public User register(@NotNull @RequestBody User user){
       return userService.registerUser(user);
    }
}
