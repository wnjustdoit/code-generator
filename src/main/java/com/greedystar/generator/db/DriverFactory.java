package com.greedystar.generator.db;

/**
 * @author GreedyStar
 * @since 1.0.0, 2018-10-24
 */
public class DriverFactory {
    private static final String DRIVER_MYSQL_5 = "com.mysql.jdbc.Driver";
    private static final String DRIVER_MYSQL_UPER = "com.mysql.cj.jdbc.Driver";
    private static final String DRIVER_ORACLE = "oracle.jdbc.driver.OracleDriver";
    private static final String DRIVER_SQLSERVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    public static String getDriver(String url) {
        if (url.contains("mysql")) {
            if (url.contains("serverTimezone")) {
                return DRIVER_MYSQL_UPER;
            } else {
                return DRIVER_MYSQL_5;
            }
        }
        if (url.contains("oracle")) {
            return DRIVER_ORACLE;
        }
        if (url.contains("sqlserver")) {
            return DRIVER_SQLSERVER;
        }
        return null;
    }

}
