package com.chartify.chartify.databaseTest;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.chartify.chartify.entity.sqlEntity.DatabaseCredentials;
import com.chartify.chartify.service.databaseService.impl.MySQLServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.mockStatic;

public class MysqlServiceTest {
    @Autowired
    private MySQLServiceImpl mySQLService;

    @Mock
    private Connection mockConnection;

    @BeforeEach
    public void setUp() {
        mockStatic(DriverManager.class);
    }

    @Test
    public void testConnectSuccess() throws SQLException {
        // 准备输入参数
        DatabaseCredentials credentials = new DatabaseCredentials("jdbc:mysql://localhost:3306/mydb", "username", "password");

        // 设置预期行为
        when(DriverManager.getConnection(anyString(), anyString(), anyString())).thenReturn(mockConnection);

        // 创建服务对象
        Connection result = mySQLService.connect(credentials);

        // 验证结果
        assertNotNull(result, "连接应该成功并返回非空的Connection对象");
        DriverManager.getConnection(credentials.getAddress(), credentials.getUsername(), credentials.getPassword());
    }
}
