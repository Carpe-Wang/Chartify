package com.chartify.chartify.service.databaseService.impl;

import com.alibaba.fastjson.JSONObject;
import com.chartify.chartify.entity.sqlEntity.DatabaseCredentials;
import com.chartify.chartify.service.databaseService.DatabaseService;
import com.chartify.chartify.utils.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class PgsqlServiceImpl implements DatabaseService {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseService.class);
    @Override
    public Connection connect(DatabaseCredentials databaseCredentials) throws SQLException {
        Connection connection = null;
        try {
            // 尝试建立数据库连接
            connection = DriverManager.getConnection(
                    databaseCredentials.getAddress(),
                    databaseCredentials.getUsername(),
                    databaseCredentials.getPassword());

            // 检查连接是否成功建立
            if (connection != null) {
                LogUtil.info(logger,"连接成功，具体参数为:{0}", JSONObject.toJSONString(databaseCredentials));
            } else {
                LogUtil.info(logger,"连接失败,具体参数为:{0}。请重新检查" , JSONObject.toJSONString(databaseCredentials));
            }
        } catch (SQLException e) {
            // 在日志中记录异常信息
            LogUtil.info(logger,"连接失败,具体参数为:{0}。请重新检查" , JSONObject.toJSONString(databaseCredentials));
            // 可以选择在这里重新抛出异常，或者处理它
            e.printStackTrace();
            throw e;
        }
        return connection;
    }

    @Override
    public List<Map<String, Object>> executeQuery(Connection conn, String query) throws SQLException {
        return null;
    }
}
