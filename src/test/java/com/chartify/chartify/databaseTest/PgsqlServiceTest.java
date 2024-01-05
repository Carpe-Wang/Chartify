package com.chartify.chartify.databaseTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.alibaba.fastjson.JSONObject;
import com.chartify.chartify.entity.sqlEntity.DatabaseCredentials;
import com.chartify.chartify.service.databaseService.DatabaseService;
import com.chartify.chartify.service.databaseService.impl.PgsqlServiceImpl;
import com.chartify.chartify.utils.LogUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ExtendWith(MockitoExtension.class)
public class PgsqlServiceTest {
    private static final Logger logger = LoggerFactory.getLogger(PgsqlServiceTest.class);
    @Mock
    private Connection mockConnection;

    @BeforeEach
    public void setUp() {
        mockStatic(DriverManager.class);
    }

    @Test
    public void testConnectSuccess() throws SQLException {
        // 准备输入参数
        DatabaseCredentials credentials = new DatabaseCredentials("jdbc:postgresql://localhost:5432/mydb", "username", "password");

        // 设置预期行为
        when(DriverManager.getConnection(anyString(), anyString(), anyString())).thenReturn(mockConnection);

        // 创建服务对象
        PgsqlServiceImpl pgsqlService = new PgsqlServiceImpl();
        Connection result = pgsqlService.connect(credentials);

        // 验证结果
        assertNotNull(result, "连接应该成功并返回非空的Connection对象");
        DriverManager.getConnection(credentials.getAddress(), credentials.getUsername(), credentials.getPassword());
    }

    @Test
    public void testConnectFailure() throws SQLException {
        // 准备输入参数
        DatabaseCredentials credentials = new DatabaseCredentials("jdbc:postgresql://wrongaddress:5432/mydb", "username", "password");
        LogUtil.info(logger,"具体信息为：{0}", JSONObject.toJSONString(credentials));
        // 设置预期行为
        when(DriverManager.getConnection(anyString(), anyString(), anyString())).thenThrow(new SQLException());
        // 创建服务对象
        PgsqlServiceImpl pgsqlService = new PgsqlServiceImpl();

        // 验证异常
        assertThrows(SQLException.class, () -> {
            pgsqlService.connect(credentials);
        });
    }
}