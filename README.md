# README

- 在 https://gitee.com/y_project/RuoYi-Vue 基础上修改
- 注意mybatis-plus的configuration配置

# 修改

- 去掉ruoyi相关标志, 方便直接套用
- 按照个人命名习惯, 调整了模块名称
- service如果没有必要写接口, 就不要写, 会把Java的臃肿暴露无疑
- 模块下的domain包改名为entity包, 更加贴合语义
- 添加lombok及其全局链式支持的配置

# TODO

- mybatis-plus --> 貌似水土不服??
- easyexcel

# 常见问题 @faq

- 出现 Invalid bound statement (not found) 异常
    - https://mp.baomidou.com/guide/faq.html
    - https://blog.csdn.net/qq_25940921/article/details/88527773

```
SqlSessionFactory不要使用原生的，请使用MybatisSqlSessionFactory
```

- 解决mybatis plus 3.x 和pagehelper无法共用、包冲突问题
    - 先不要用pagehelper-sprng-boot-starter
    - https://blog.csdn.net/u012280292/article/details/99678037
    - https://blog.csdn.net/xieec/article/details/106124325

```xml
<dependency>
    <groupId>com.github.pagehelper</groupId>
    <artifactId>pagehelper</artifactId>
    <version>5.1.10</version>
</dependency>

<dependency>
    <groupId>com.github.jsqlparser</groupId>
    <artifactId>jsqlparser</artifactId>
    <version>2.1</version>
</dependency>
```
