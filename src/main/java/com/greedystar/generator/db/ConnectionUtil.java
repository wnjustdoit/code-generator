package com.greedystar.generator.db;


import com.greedystar.generator.entity.ColumnInfo;
import com.greedystar.generator.utils.ConfigUtil;
import com.greedystar.generator.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

/**
 * @author GreedyStar
 * @since 1.0.0, 2018/4/19
 */
public class ConnectionUtil {

    private static final Logger logger = LoggerFactory.getLogger(ConnectionUtil.class);

    private Connection connection;

    public ConnectionUtil() {
    }

    /**
     * 初始化数据库连接
     *
     * @return
     */
    public boolean initConnection() {
        try {
            Class.forName(DriverFactory.getDriver(ConfigUtil.getConfiguration().getDb().getUrl()));
            String url = ConfigUtil.getConfiguration().getDb().getUrl();
            String username = ConfigUtil.getConfiguration().getDb().getUsername();
            String password = ConfigUtil.getConfiguration().getDb().getPassword();
            Properties properties = new Properties();
            properties.put("user", username);
            properties.put("password", password == null ? "" : password);
            properties.setProperty("remarks", "true");
            properties.setProperty("useInformationSchema", "true");
            connection = DriverManager.getConnection(url, properties);
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return false;
    }

    /**
     * 获取表结构数据
     *
     * @param tableName 表名
     * @return 包含表结构数据的列表
     */
    public List<ColumnInfo> getMetaData(String tableName) throws Exception {
        // 获取主键
        ResultSet keyResultSet = connection.getMetaData().getPrimaryKeys(null, getSchema(connection), tableName.toUpperCase());
        String primaryKey = null;
        if (keyResultSet.next()) {
            primaryKey = keyResultSet.getObject(4).toString();
        }
        keyResultSet.close();
        // 获取表注释
        String tableRemarks = null;
        if (connection.getMetaData().getURL().contains("sqlserver")) { // SQLServer
            tableRemarks = parseSqlServerTableRemarks(tableName);
        } else { // Oracle & MySQL
            ResultSet tableResultSet = connection.getMetaData().getTables(null, getSchema(connection), tableName.toUpperCase(), new String[]{"TABLE"});
            if (tableResultSet.next()) {
                tableRemarks = StringUtil.isBlank(tableResultSet.getString("REMARKS")) ? tableName : tableResultSet.getString("REMARKS");
            }
            tableResultSet.close();
        }
        // 获取列信息
        List<ColumnInfo> columnInfos = new ArrayList<>();
        ResultSet columnResultSet = connection.getMetaData().getColumns(null, getSchema(connection), tableName.toUpperCase(), "%");
        while (columnResultSet.next()) {
            boolean isPrimaryKey;
            if (columnResultSet.getString("COLUMN_NAME").equals(primaryKey)) {
                isPrimaryKey = true;
            } else {
                isPrimaryKey = false;
            }
            ColumnInfo info = new ColumnInfo(columnResultSet.getString("COLUMN_NAME"), columnResultSet.getInt("DATA_TYPE"),
                    StringUtil.isBlank(columnResultSet.getString("REMARKS")) ? columnResultSet.getString("COLUMN_NAME") : columnResultSet.getString("REMARKS"), tableRemarks, isPrimaryKey);
            columnInfos.add(info);
        }
        columnResultSet.close();
        if (columnInfos.isEmpty()) {
            throw new Exception("Can not find column information from table:" + tableName);
        }
        if (connection.getMetaData().getURL().contains("sqlserver")) { // SQLServer需要单独处理列REMARKS
            parseSqlServerColumnRemarks(tableName, columnInfos);
        }
        return columnInfos;
    }

    /**
     * 主动查询SqlServer指定表的注释
     *
     * @param tableName tableName
     */
    private String parseSqlServerTableRemarks(String tableName) throws SQLException {
        String tableRemarks = null;
        String sql = "SELECT CAST(ISNULL(p.value, '') AS nvarchar(25)) AS REMARKS FROM sys.tables t LEFT JOIN sys.extended_properties p ON p.major_id=t.object_id AND p.minor_id=0 AND p.class=1 WHERE t.name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, tableName);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            tableRemarks = StringUtil.isBlank(resultSet.getString("REMARKS")) ? "Unknown Table" : resultSet.getString("REMARKS");
        }
        resultSet.close();
        preparedStatement.close();
        return tableRemarks;
    }

    /**
     * 主动查询SqlServer指定表的数据列的注释
     *
     * @param tableName tableName
     */
    private void parseSqlServerColumnRemarks(String tableName, List<ColumnInfo> columnInfos) throws SQLException {
        HashMap<String, String> map = new HashMap<>();
        String sql = "SELECT c.name AS COLUMN_NAME, CAST(ISNULL(p.value, '') AS nvarchar(25)) AS REMARKS FROM sys.tables t INNER JOIN sys.columns c ON c.object_id = t.object_id LEFT JOIN sys.extended_properties p ON p.major_id = c.object_id AND p.minor_id = c.column_id WHERE t.name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, tableName);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            map.put(resultSet.getString("COLUMN_NAME"), StringUtil.isBlank(resultSet.getString("REMARKS")) ? "Unknown" : resultSet.getString("REMARKS"));
        }
        for (ColumnInfo columnInfo : columnInfos) {
            columnInfo.setRemarks(map.get(columnInfo.getColumnName()));
        }
        resultSet.close();
        preparedStatement.close();
    }

    public String getSchema(Connection connection) throws SQLException {
        String schema;
        if (connection.getMetaData().getURL().contains("sqlserver")) {
            schema = connection.getSchema();
        } else {
            schema = connection.getMetaData().getUserName();
        }
        return schema;
    }

    public void closeConnection() {
        try {
            if (!connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

}
