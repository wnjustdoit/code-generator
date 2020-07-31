package com.greedystar.generator.task;

import com.greedystar.generator.entity.ColumnInfo;
import com.greedystar.generator.utils.*;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangnan
 * @since 1.0.0, 2020/7/31
 **/
public class Entity1Task extends EntityTask {

    /**
     * 1.单表生成  2.多表时生成子表实体
     */
    public Entity1Task(String className, String tableName, List<ColumnInfo> infos) {
        super(className, null, null, infos);
        this.tableName = tableName;
    }

    @Override
    public void run() throws IOException, TemplateException {
        // 生成Entity填充数据
        Map<String, String> entityData = new HashMap<>();
        entityData.put("BasePackageName", ConfigUtil.getConfiguration().getPackageName());
        entityData.put("EntityPackageName", ConfigUtil.getConfiguration().getPath().getEntity1());
        entityData.put("Author", ConfigUtil.getConfiguration().getAuthor());
        entityData.put("Date", new SimpleDateFormat("yyyy/M/dd").format(new Date()));
        entityData.put("ClassName", className);
        entityData.put("Remarks", tableInfos.get(0).getTableRemarks());
        if (!StringUtil.isBlank(parentForeignKey)) { // 多对多：主表实体
            entityData.put("Properties", GeneratorUtil.generateEntityProperties(parentClassName, tableInfos));
        } else if (!StringUtil.isBlank(foreignKey)) { // 多对一：主表实体
            entityData.put("Properties", GeneratorUtil.generateEntityProperties(parentClassName, tableInfos, foreignKey));
        } else { // 单表关系
            entityData.put("Properties", GeneratorUtil.generateEntityProperties(tableInfos, true));
        }
        entityData.put("TableName", tableName);
        String filePath = FileUtil.getSourcePath() + StringUtil.package2Path(ConfigUtil.getConfiguration().getPackageName()) + StringUtil.package2Path(ConfigUtil.getConfiguration().getPath().getEntity1());
        String fileName = className + ".java";
        // 生成Entity文件
        FileUtil.generateToJava(FreemarkerConfigUtil.TYPE_ENTITY1, entityData, filePath, fileName);
    }
}
