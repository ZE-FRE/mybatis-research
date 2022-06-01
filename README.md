# 简介

mybatis系列源码，导入了mybatis-parent、mybatis、mybatis-spring以及mybatis-spring-boot

导入的项目都是release版本，具体如下：

| 项目                | 版本  | github地址                                                   | 文档链接                                                     | 描述                       |
| ------------------- | ----- | ------------------------------------------------------------ | ------------------------------------------------------------ | -------------------------- |
| mybatis-parent      | 33    | [mybatis-parent-33](https://github.com/mybatis/parent/releases/tag/mybatis-parent-33) | [官方文档](http://mybatis.org/spring/zh/index.html)          | mybatis系列依赖管理pom项目 |
| mybatis             | 3.5.9 | [mybatis-3.5.9](https://github.com/mybatis/mybatis-3/releases/tag/mybatis-3.5.9) | [官方文档](https://mybatis.org/mybatis-3/zh/)                | mybatis项目                |
| mybatis-spring      | 2.0.7 | [mybatis-spring-2.0.7](https://github.com/mybatis/spring/releases/tag/mybatis-spring-2.0.7) | [官方文档](http://mybatis.org/spring/zh/index.html)          | mybatis与spring整合        |
| mybatis-spring-boot | 2.2.2 | [mybatis-spring-boot-2.2.2](https://github.com/mybatis/spring-boot-starter/releases/tag/mybatis-spring-boot-2.2.2) | [官方文档](http://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/) | mybatis与spring boot整合   |

### 项目构建

首先导入项目到idea，然后在右上角找到Project Structure按钮并打开，打开后界面如下

![project structure](/asset/project structure.png)



点击+号，选择Import Module导入mybatis-parent-33模块

![](/asset/parent33.png)

然后在命令行执行mvn clean install![install](/asset/install.png)

重复上述步骤导入mybatis-3.5.9，然后在命令行执行mvn clean install -Dmaven.test.skip=true![](/asset/install test skip.png)



重复上述步骤导入mybatis-spring-2.0.7，然后在命令行执行mvn clean install -Dmaven.test.skip=true

重复上述步骤导入mybatis-spring-boot-2.2.2