package com.chartify.chartify.service.databaseService;

import com.chartify.chartify.entity.sqlEntity.DatabaseCredentials;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface DatabaseService {
    Connection connect(DatabaseCredentials databaseCredentials) throws SQLException;
    List<Map<String, Object>> executeQuery(Connection conn, String query) throws SQLException;
}
