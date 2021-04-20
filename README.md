# 用于Spring学习之仓库

从零开始开发的课程管理系统

## References

* [Spring](https://spring.io/guides)
* [Spring: Guide - Serving Web Content](https://spring.io/guides/gs/serving-web-content/)
* [GitHub: Guide - Deploy Keys](https://developer.github.com/v3/guides/managing-deploy-keys/#deploy-keys)
* [Bootstrap: Guide - Getting Started](https://v3.bootcss.com/getting-started/)
* [GitHub: API](https://developer.github.com/apps/)
* [MyBatis: Guide - Work with Spring](http://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/)
* [Bootstrap: Components](https://v3.bootcss.com/components/)
* [Bootstrap: 全局 CSS 样式](https://v3.bootcss.com/css/)
* [Bootstrap: JavaScript 插件](https://v3.bootcss.com/javascript/)

## Tools

- IntelliJ IDEA
  - JetBrain Mono
  - Sarasa UI J
  - Monokai Pro 
- Git
- Notion
- Microsoft Edge

## Configuration

```properties
server.port=80
spring.datasource.url=jdbc:mysql://localhost:3306
spring.datasource.username=root
spring.datasource.password=123456
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

## 目标/要求

1. 实现针对一门的课程管理系统
2. （待实现）有班级管理、资料上传下载等功能
3. （待实现）分角色的权限系统，不同角色访问不同页面
    1. （待实现）通过区分登录角色Mapping到相应页面
    2. （待实现）查阅数据库来验证用户名与密码
        1. （未定）在数据库中使用暗文存储
    3. （待实现）在Springboot中采取较为科学的用户验证方式
4. （未定）美化UI
    

## 日々精進

1. model的使用和原理
2. jQuery浅尝
3. Java及Spring的规范

## 今日面倒

- 使用Bootstrap的Javascript插件时，必须先引入jQuery再引入Bootstrap Core，引入js时最好放在</body>前以获得最大效能。
- Bootstrap要求的jQuery不是最新版本。