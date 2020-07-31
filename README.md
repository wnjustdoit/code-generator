#### 使用说明
* 更改配置文件的配置：src/main/resources/generator.yaml
    * 指定数据源
    * 指定要生成的信息和模块
* 更改 java 运行类：
    * 指定表名和实体名
```java
/**
 * {@link com.greedystar.generator.Main#main(java.lang.String[])} }
 **/
```

* 执行并生成结果（注意不要让生成的内容提交到本git仓库^_^）

#### 模板说明
* 组合：controller1 + entity1
生成依赖于common-db的快速开发的ORM框架

* 组合：controller + service(interface & implementation) + dao + mybatis_mapper + entity
传统MVC框架的代码组织结构
