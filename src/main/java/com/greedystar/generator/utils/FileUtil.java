package com.greedystar.generator.utils;

import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @author GreedyStar
 * @since 1.0.0, 2018/4/19
 */
public class FileUtil {

    /**
     * @param type     使用模板类型
     * @param data     填充数据
     * @param filePath 输出文件
     * @throws IOException
     * @throws TemplateException
     */
    public static void generateToJava(int type, Object data, String filePath, String fileName) throws IOException, TemplateException {
        String path = filePath + fileName; // 待生成的代码文件路径
        // 已存在的文件不予覆盖
        File file = new File(path);
        if (file.exists()) {
            path += ".generated";
            System.err.printf("%s already exit. Generating %s \n", fileName, path);
        } else {
            System.out.printf("Generating %s \n", path);
        }
        // 代码生成路径目录不存在则自动创建
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        Template tpl = getTemplate(type); // 获取模板文件
        // 填充数据
        StringWriter writer = new StringWriter();
        Objects.requireNonNull(tpl).process(data, writer);
        writer.flush();
        // 写入文件
        FileOutputStream fos = new FileOutputStream(path);
        OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
        BufferedWriter bw = new BufferedWriter(osw, 1024);
        tpl.process(data, bw);
        writer.close();
        bw.close();
    }

    /**
     * 获取模板
     *
     * @param type 模板类型
     */
    private static Template getTemplate(int type) throws IOException {
        switch (type) {
            case FreemarkerConfigUtil.TYPE_ENTITY:
                return FreemarkerConfigUtil.getInstance().getTemplate("entity.ftl");
            case FreemarkerConfigUtil.TYPE_ENTITY1:
                return FreemarkerConfigUtil.getInstance().getTemplate("entity1.ftl");
            case FreemarkerConfigUtil.TYPE_DAO:
                return FreemarkerConfigUtil.getInstance().getTemplate("dao.ftl");
            case FreemarkerConfigUtil.TYPE_SERVICE:
                return FreemarkerConfigUtil.getInstance().getTemplate("service_implementation.ftl");
            case FreemarkerConfigUtil.TYPE_CONTROLLER:
                return FreemarkerConfigUtil.getInstance().getTemplate("controller.ftl");
            case FreemarkerConfigUtil.TYPE_CONTROLLER1:
                return FreemarkerConfigUtil.getInstance().getTemplate("controller1.ftl");
            case FreemarkerConfigUtil.TYPE_MAPPER:
                return FreemarkerConfigUtil.getInstance().getTemplate("mybatis_mapper.ftl");
            case FreemarkerConfigUtil.TYPE_INTERFACE:
                return FreemarkerConfigUtil.getInstance().getTemplate("service_interface.ftl");
            default:
                return null;
        }
    }

    private static String getBasicProjectPath() {
        String path = new File(Objects.requireNonNull(FileUtil.class.getClassLoader().getResource("")).getFile()).getPath() + File.separator;
        return path.substring(0, path.indexOf("target")) + "src" + File.separator + "main" + File.separator;
    }

    /**
     * 获取源码路径
     */
    public static String getSourcePath() {
        return getBasicProjectPath() + "java" + File.separator;
    }

    /**
     * 获取资源文件路径
     */
    public static String getResourcePath() {
        return getBasicProjectPath() + "resources" + File.separator;
    }

}
