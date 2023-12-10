package com.chartify.chartify.mapper;

import com.chartify.chartify.entity.UserData;
import com.chartify.chartify.service.impl.UserServiceImpl;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mapper
public interface UserMapper {
    @InsertProvider(type = UserSqlProvider.class, method = "createUser")
    int createUser(UserData user);

    @Select("SELECT username, email, password FROM chartify_user WHERE username = #{username}")
    @Results({
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password")
    })
    UserData getUserByUsername(String username);

    @UpdateProvider(type = UserSqlProvider.class, method = "updateUser")
    int updateUser(UserData userData);

    class UserSqlProvider {
        private static final Logger logger = LoggerFactory.getLogger(UserSqlProvider.class);

        public String createUser(final UserData user) {
            logger.info("insert userData is:{}", user);
            return new SQL() {{
                INSERT_INTO("chartify_user");
                VALUES("username", "#{username}");
                VALUES("email", "#{email}");
                VALUES("password", "#{password}");
                VALUES("created_time", "CURRENT_TIMESTAMP");
                VALUES("updated_time", "CURRENT_TIMESTAMP");
            }}.toString();
        }

        public String updateUser(final UserData userData) {
            return new SQL() {{
                UPDATE("chartify_user"); // 确保这是您的数据库表名

                // 对于需要更新的每个字段，检查是否为 null，如果不为 null 则更新
                // 以下是一些典型的字段更新
                if (userData.getUsername() != null) {
                    SET("username = #{username}");
                }
                if (userData.getEmail() != null) {
                    SET("email = #{email}");
                }
                if (userData.getPassword() != null) {
                    SET("password = #{password}");
                }
                SET("updated_time = now()");

                // 使用主键（通常是id）来定位更新的记录
                WHERE("username = #{username}");
            }}.toString();
        }
    }
}
