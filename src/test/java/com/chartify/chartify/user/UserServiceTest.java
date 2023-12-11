package com.chartify.chartify.user;

import com.chartify.chartify.entity.UserData;
import com.chartify.chartify.mapper.UserMapper;
import com.chartify.chartify.model.Result;
import com.chartify.chartify.service.UserService;
import com.chartify.chartify.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    private UserData userData;
    private String rawPassword = "password";
    private String encodedPassword = "caTest";


    @BeforeEach
    public void setUp() {
        userData = new UserData();
        userData.setUsername("caTestUser");
        userData.setEmail("wangcarpe@gmail.com");
        userData.setPassword(rawPassword);
    }

    @Test
    public void testCreateUser() {
        Result result = userService.createUser(userData);

        // 验证返回结果
        assertEquals("插入成功", result.getMessage());

        // 由于是集成测试，数据库应该会有新记录
        // ... 这里可以添加检查数据库中是否真的有新记录的代码·
    }

    @Test
    public void testGetUserByUsername(){
        Result userByUsername = userService.getUserByUsername(userData.getUsername());
        System.out.println(userByUsername);
        assertEquals(true , userByUsername.getIsSuccess());
    }

    @Test
    public void testUpdateUser(){
        Result result = userService.updateUser(userData);
        assertEquals(true , result.getIsSuccess());
    }
}
