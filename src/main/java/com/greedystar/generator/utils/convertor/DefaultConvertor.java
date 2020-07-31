package com.greedystar.generator.utils.convertor;

import java.sql.JDBCType;

/**
 * @author GreedyStar
 * @since 1.0.0, 2019/6/2
 */
public class DefaultConvertor implements TypeConvertor {

    /**
     * 将JDBC类型转换为Java类型
     *
     * @param type JDBC类型名
     */
    @Override
    public String convertType(JDBCType type) {
        StringBuilder sb = new StringBuilder();
        switch (type) {
            case BIT:
            case BOOLEAN:
                sb.append("Boolean");
                break;
            case TINYINT:
            case SMALLINT:
            case INTEGER:
                sb.append("Integer");
                break;
            case BIGINT:
                sb.append("Long");
                break;
            case REAL:
                sb.append("Float");
                break;
            case FLOAT:
            case DOUBLE:
                sb.append("Double");
                break;
            case DECIMAL:
            case NUMERIC:
                sb.append("BigDecimal");
                break;
            case VARCHAR:
            case CHAR:
            case NCHAR:
            case NVARCHAR:
            case LONGVARCHAR:
            case LONGNVARCHAR:
                sb.append("String");
                break;
            case TIME:
            case TIMESTAMP:
            case DATE:
                sb.append("Date");
                break;
            case CLOB:
            case NCLOB:
            case BLOB:
            case BINARY:
            case VARBINARY:
            case LONGVARBINARY:
                sb.append("Byte[]");
                break;
            case NULL:
            case OTHER:
            case JAVA_OBJECT:
                sb.append("Object");
                break;
            default:
                sb.append("Object");

        }
        return sb.toString();
    }

    @Override
    public String convertSimpleType(JDBCType type) {
        StringBuilder sb = new StringBuilder();
        switch (type) {
            case BIT:
            case BOOLEAN:
                sb.append("boolean");
                break;
            case TINYINT:
            case SMALLINT:
            case INTEGER:
                sb.append("int");
                break;
            case BIGINT:
                sb.append("long");
                break;
            case REAL:
                sb.append("float");
                break;
            case FLOAT:
            case DOUBLE:
                sb.append("double");
                break;
            case DECIMAL:
            case NUMERIC:
                sb.append("BigDecimal");
                break;
            case VARCHAR:
            case CHAR:
            case NCHAR:
            case NVARCHAR:
            case LONGVARCHAR:
            case LONGNVARCHAR:
                sb.append("String");
                break;
            case TIME:
            case TIMESTAMP:
            case DATE:
                sb.append("Date");
                break;
            case CLOB:
            case NCLOB:
            case BLOB:
            case BINARY:
            case VARBINARY:
            case LONGVARBINARY:
                sb.append("byte[]");
                break;
            case NULL:
            case OTHER:
            case JAVA_OBJECT:
                sb.append("Object");
                break;
            default:
                sb.append("Object");

        }
        return sb.toString();
    }

}
