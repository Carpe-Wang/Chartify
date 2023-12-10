package com.chartify.chartify.mapper;

import com.chartify.chartify.entity.UserData;
import com.chartify.chartify.service.impl.UserServiceImpl;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mapper
public interface UserMapper {
    @InsertProvider(type = UserSqlProvider.class, method = "createUser")
    int createUser(UserData user);

    class UserSqlProvider {
        private static final Logger logger = LoggerFactory.getLogger(UserSqlProvider.class);

        public String createUser(final UserData user) {
            logger.info("insert userData is:{}" , user);
            return new SQL() {{
                INSERT_INTO("chartify_user");
                VALUES("username", "#{username}");
                VALUES("email", "#{email}");
                VALUES("password", "#{password}");
                VALUES("created_time", "CURRENT_TIMESTAMP");
                VALUES("updated_time", "CURRENT_TIMESTAMP");
            }}.toString();
        }
    }
}
