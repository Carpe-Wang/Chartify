package com.chartify.chartify.mapper;

import com.chartify.chartify.entity.UserData;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.jdbc.SQL;

@Mapper
public interface UserMapper {
    @InsertProvider(type = UserSqlProvider.class, method = "createUser")
    int createUser(UserData user);

    class UserSqlProvider {

        public String createUser(final UserData user) {
            return new SQL() {{
                INSERT_INTO("chartify_user");
                VALUES("id", "#{id}");
                VALUES("username", "#{username}");
                VALUES("email", "#{email}");
                VALUES("password", "#{password}");
                VALUES("created_time", "CURRENT_TIMESTAMP");
                VALUES("updated_time", "CURRENT_TIMESTAMP");
                VALUES("user_id","userId");
            }}.toString();
        }
    }
}
