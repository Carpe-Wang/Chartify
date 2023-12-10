package com.chartify.chartify.controller;


import com.chartify.chartify.entity.UserData;
import com.chartify.chartify.model.Result;
import com.chartify.chartify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/createUser")
    public Result createUser(@RequestBody UserData user) {
        return userService.createUser(user);
    }

    @GetMapping("/getUserById/{username}")
    public Result getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }
//
//    @PostMapping("/updateUser")
//    public Result updateUser(@RequestBody UserData) {
//
//    }
//
//    @DeleteMapping("/deleteUser")
//    public Result deleteUser(@RequestBody UserData) {
//
//    }
}