package com.greedystar.generator.utils.convertor;

import java.sql.JDBCType;

/**
 * @author GreedyStar
 * @since 1.0.0, 2019/6/2
 */
public interface TypeConvertor {

    /**
     * 将JDBC类型转换为Java包装类型
     *
     * @param type JDBC类型名
     * @return Java类型名
     */
    String convertType(JDBCType type);

    /**
     * 将JDBC类型转换为Java基本类型
     *
     * @param type JDBC类型名
     * @return Java类型名
     */
    String convertSimpleType(JDBCType type);

}
