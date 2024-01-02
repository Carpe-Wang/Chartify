package com.chartify.chartify.service.databaseService.impl;

import com.alibaba.fastjson.JSONObject;
import com.chartify.chartify.entity.sqlEntity.DatabaseCredentials;
import com.chartify.chartify.service.databaseService.DatabaseService;
import com.chartify.chartify.service.impl.UserServiceImpl;
import com.chartify.chartify.utils.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class MySQLServiceImpl implements DatabaseService {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseService.class);

    @Override
    public Connection connect(DatabaseCredentials databaseCredentials) throws SQLException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(databaseCredentials.getAddress(),
                    databaseCredentials.getUsername(),
                    databaseCredentials.getPassword());
            // 这里可以加入其他逻辑，比如检查连接是否为 null
            if (connection != null) {
                LogUtil.info(logger,"连接成功，具体参数为:{0}", JSONObject.toJSONString(databaseCredentials));
            } else {
                LogUtil.info(logger,"连接失败,具体参数为:{0}。请重新检查" , JSONObject.toJSONString(databaseCredentials));
            }
        } catch (SQLException e) {
            LogUtil.info(logger,"连接失败,具体参数为:{0}。请重新检查" , JSONObject.toJSONString(databaseCredentials));
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public List<Map<String, Object>> executeQuery(Connection conn, String query) throws SQLException {
        return null;
    }
}
