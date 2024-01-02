package com.chartify.chartify.service.databaseService.impl;

import com.chartify.chartify.entity.sqlEntity.DatabaseCredentials;
import com.chartify.chartify.service.databaseService.DatabaseService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class PgsqlServiceImpl implements DatabaseService {
    @Override
    public Connection connect(DatabaseCredentials databaseCredentials) throws SQLException {
        return null;
    }

    @Override
    public List<Map<String, Object>> executeQuery(Connection conn, String query) throws SQLException {
        return null;
    }
}
