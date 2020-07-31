package com.greedystar.generator.utils;

import freemarker.template.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Objects;

public final class FreemarkerConfigUtil {

    private static final Logger logger = LoggerFactory.getLogger(FreemarkerConfigUtil.class);

    private static final String TEMPLATE_FOLDER = "templates";
    private static final String PATH = new File(Objects.requireNonNull(FreemarkerConfigUtil.class.getClassLoader().getResource(TEMPLATE_FOLDER)).getFile()).getPath();

    public static final int TYPE_ENTITY = 0;
    public static final int TYPE_ENTITY1 = 10;
    public static final int TYPE_DAO = 1;
    public static final int TYPE_SERVICE = 2;
    public static final int TYPE_CONTROLLER = 3;
    public static final int TYPE_CONTROLLER1 = 30;
    public static final int TYPE_MAPPER = 4;
    public static final int TYPE_INTERFACE = 5;
    private static Configuration configuration;

    public static Configuration getInstance() {
        if (null == configuration) {
            synchronized (FreemarkerConfigUtil.class) {
                if (null == configuration) {
                    configuration = new Configuration(Configuration.VERSION_2_3_23);
                    try {
                        if (PATH.contains("jar")) {
                            configuration.setClassForTemplateLoading(FreemarkerConfigUtil.class, "/" + TEMPLATE_FOLDER);
                        } else {
                            configuration.setDirectoryForTemplateLoading(new File(PATH));
                        }
                    } catch (IOException e) {
                        logger.error(e.getMessage(), e);
                    }
                    configuration.setEncoding(Locale.CHINA, "utf-8");
                }
            }
        }
        return configuration;
    }

    private FreemarkerConfigUtil() {
    }

}
