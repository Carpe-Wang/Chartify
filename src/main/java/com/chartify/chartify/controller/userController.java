package com.chartify.chartify.controller;


import com.chartify.chartify.entity.UserData;
import com.chartify.chartify.model.Result;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Users")
public class UserController {


    @PostMapping("/createUser")
    public Result createUser(@RequestBody UserData user) {

    }

    @GetMapping("/getUserById}")
    public Result getUserById(@PathVariable Long id) {

    }

    @PostMapping("/updateUser")
    public Result updateUser(@RequestBody UserData) {

    }

    @DeleteMapping("/deleteUser")
    public Result deleteUser(@RequestBody UserData) {

    }
}