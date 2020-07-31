package com.greedystar.generator.utils;

import com.greedystar.generator.utils.convertor.DefaultConvertor;
import com.greedystar.generator.utils.convertor.TypeConvertor;

import java.sql.JDBCType;

/**
 * @author GreedyStar
 * @since 1.0.0, 2018/4/19
 */
public class ConvertorUtil {
    private volatile static TypeConvertor convertor;

    static {
        String convertorClass = ConfigUtil.getConfiguration().getConvertor();
        if (StringUtil.isBlank(convertorClass)) { // 用户未配置类型转换器，使用默认转换器
            convertor = new DefaultConvertor();
        } else { // 加载用户定义的类型转换器
            try {
                Class clazz = Class.forName(ConfigUtil.getConfiguration().getConvertor());
                convertor = (TypeConvertor) clazz.newInstance();
            } catch (Exception e) {
                System.err.println("未找到" + convertorClass + ", 使用默认类型转换器, 请检查配置文件");
            }
        }
    }

    /**
     * 将数据库数据类型转换为Java数据类型
     *
     * @param type
     * @return
     */
    protected static String parseTypeFormSqlType(JDBCType type) {
        /*
         * 用户配置了错误的TypeConvertor会导致convertor为null
         * 在生成多表关系代码时，会有两个EntityTask并发执行，防止创建多个实例，采用double-check的单例模式
         */
        if (convertor == null) {
            synchronized (ConvertorUtil.class) {
                if (convertor == null) {
                    convertor = new DefaultConvertor();
                }
            }
        }
        return convertor.convertType(type);
    }

}
