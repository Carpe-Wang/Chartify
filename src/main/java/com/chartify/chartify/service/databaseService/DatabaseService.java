package com.chartify.chartify.service.databaseService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface DatabaseService {
    Connection connect(String address, String username, String password) throws SQLException;
    List<Map<String, Object>> executeQuery(Connection conn, String query) throws SQLException;
}
