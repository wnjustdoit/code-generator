package com.greedystar.generator.task;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.greedystar.generator.utils.ConfigUtil;
import com.greedystar.generator.utils.FileUtil;
import com.greedystar.generator.utils.FreemarkerConfigUtil;
import com.greedystar.generator.utils.StringUtil;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author GreedyStar
 * @since 1.0.0, 2018/4/20
 */
public class Controller1Task extends ControllerTask {

    public Controller1Task(String className) {
        super(className);
    }

    @Override
    public void run() throws IOException, TemplateException {
        // 生成Controller填充数据
        Map<String, String> controllerData = new HashMap<>();
        controllerData.put("BasePackageName", ConfigUtil.getConfiguration().getPackageName());
        controllerData.put("ControllerPackageName", ConfigUtil.getConfiguration().getPath().getController1());
        if (StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getInterf())) {
            controllerData.put("ServicePackageName", ConfigUtil.getConfiguration().getPath().getService());
        } else {
            controllerData.put("ServicePackageName", ConfigUtil.getConfiguration().getPath().getInterf());
        }
        controllerData.put("EntityPackageName", ConfigUtil.getConfiguration().getPath().getEntity1());
        controllerData.put("Author", ConfigUtil.getConfiguration().getAuthor());
        controllerData.put("Date", new SimpleDateFormat("yyyy/M/dd").format(new Date()));
        controllerData.put("ClassName", className);
        controllerData.put("EntityName", StringUtil.firstToLowerCase(className));
        controllerData.put("ClassURI", PropertyNamingStrategy.KEBAB_CASE.nameForField(null, null, className).concat("s"));
        String filePath = FileUtil.getSourcePath() + StringUtil.package2Path(ConfigUtil.getConfiguration().getPackageName()) + StringUtil.package2Path(ConfigUtil.getConfiguration().getPath().getController1());
        String fileName = className + "Controller.java";
        // 生成Controller文件
        FileUtil.generateToJava(FreemarkerConfigUtil.TYPE_CONTROLLER1, controllerData, filePath, fileName);
    }
}
