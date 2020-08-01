## code-generator
#### 来源
来自：https://github.com/GreedyStar/generator/   
查看原 [REAME.md](README_ORIGIN.md)   
**改进**：
* 修复部分bug；
* 增强稳定性；
* 拓展更多的模板

#### 使用说明
* 更改配置文件的配置：src/main/resources/generator.yaml
    * 指定数据源
    * 指定要生成的信息和模块（模板位置：src/main/resources/templates/*）
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
生成依赖于 [common-db](https://github.com/wnjustdoit/common/tree/master/common-db) 的快速开发的ORM框架

* 组合：controller + service(interface & implementation) + dao + mybatis_mapper + entity
传统MVC框架的代码组织结构
