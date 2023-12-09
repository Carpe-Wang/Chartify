package com.chartify.chartify.user;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.chartify.chartify.entity.UserData;
import com.chartify.chartify.mapper.UserMapper;
import com.chartify.chartify.model.Result;
import com.chartify.chartify.service.impl.UserServiceImpl;
import com.chartify.chartify.utils.SnowflakeIdGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserMapper userMapper;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private SnowflakeIdGenerator snowflakeIdGenerator;//这个不要删除，InjectMocks userService，如果没有这个会导致空指针

    private UserData userData;
    private String rawPassword = "password";
    private String encodedPassword = "encodedPassword";

    @Before
    public void setUp() {
        userData = new UserData();
        userData.setUserName("testUser");
        userData.setEmail("testUser@example.com");
        userData.setPassword(rawPassword);
        when(passwordEncoder.encode(rawPassword)).thenReturn(encodedPassword);
        when(userMapper.createUser(any(UserData.class))).thenReturn(1);
    }

    @Test
    public void testCreateUser() {
        Result result = userService.createUser(userData);
        Assert.assertEquals("插入成功", result.getMessage());
        Assert.assertEquals(encodedPassword, userData.getPassword());
    }
}
