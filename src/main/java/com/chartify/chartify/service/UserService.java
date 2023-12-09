package com.chartify.chartify.service;

import com.chartify.chartify.entity.UserData;
import com.chartify.chartify.model.Result;

public interface UserService {
    Result createUser(UserData userData);
}
