package com.chartify.chartify.service.impl;

import com.chartify.chartify.entity.UserData;
import com.chartify.chartify.mapper.UserMapper;
import com.chartify.chartify.model.Result;
import com.chartify.chartify.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;//后续做登陆用来加密，现在先不用动

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public Result createUser(UserData userData) {
        // TODO: 2023/12/10 通过select 查询username是否已经存在
        UserData userByUsername = userMapper.getUserByUsername(userData.getUsername());
        if (userByUsername != null ){
            return new Result<>(false, "插入失败，用户已经存在");
        }
        logger.info("createUser data is :{}",userData);
        String encodedPassword = passwordEncoder.encode(userData.getPassword());
        userData.setPassword(encodedPassword);
        try {
            int result = userMapper.createUser(userData);
            if (result == 1) {
                return new Result<>(true, "插入成功",userData.getUsername());
            } else {
                return new Result<>(false, "插入失败，未能创建用户");
            }
        } catch (Exception e) {
            logger.error("Error creating user: ", e);
            return new Result<>(false, "插入失败，发生异常");
        }
    }

    @Override
    public Result getUserByUsername(String username) {
        if (username.isBlank()){
            return new Result<>(false,"username为空");
        }
        UserData userByUsername = userMapper.getUserByUsername(username);
        if (userByUsername == null || username.isEmpty()){
            return new Result<>(false,"查询失败，未查到相关信息");
        }
        return new Result<>(true,"查询成功",userByUsername);
    }

    @Override
    public Result updateUser(UserData userData) {
        if (userData == null) {
            return new Result<>(false, "userData 为空");
        }
        String username = userData.getUsername();
        if (username == null || username.isBlank()) {
            return new Result<>(false, "username 为空");
        }
        // 检查用户是否存在
        UserData existingUser = userMapper.getUserByUsername(username);
        if (existingUser == null) {
            return new Result<>(false, "没有相关用户");
        }
        // 加密密码
        String encodedPassword = passwordEncoder.encode(userData.getPassword());
        userData.setPassword(encodedPassword);
        try {
            int result = userMapper.updateUser(userData);
            if (result == 1) {
                return new Result<>(true, "更新成功");
            } else {
                return new Result<>(false, "更新失败");
            }
        } catch (Exception e) {
            logger.error("Error updating user: ", e);
            return new Result<>(false, "更新失败，发生异常");
        }
    }

}
