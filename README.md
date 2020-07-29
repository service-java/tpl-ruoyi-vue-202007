# README

- 在 https://gitee.com/y_project/RuoYi-Vue 基础上修改
- 注意mybatis-plus的configuration配置

# 修改

- 去掉部分ruoyi的标志, 方便直接套用
- 按照个人命名习惯, 调整模块名称 @eg 业务模块下的domain改名为entity
- 将层级扁平化, 模块往下算起最多两层 @eg /common/config/properties/xxx.properties
- service如果没有必要写接口, 就不要写, 会把Java的臃肿暴露无疑的
- 添加lombok及其全局链式支持的配置(lombok.config)
- 部分支持mybatis-plus @eg 代码生成generator模块
- 添加hutool + apache-common依赖包

# TODO

- OSS模块
- 去掉RestFul风格命名的代码, 改为普通的语义命名
- BaseEntity调整参数
- easyexcel示例

# 同步

# 常见问题 @faq

- 出现 Invalid bound statement (not found) 异常
    - https://mp.baomidou.com/guide/faq.html
    - https://blog.csdn.net/qq_25940921/article/details/88527773

```
SqlSessionFactory不要使用原生的，请使用MybatisSqlSessionFactory

开发工具没有同步配置!! 
我重启貌似就不报错了, 灵异事件, 不知道是不是bug
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

- 热部署 LoginUser 类型转换失败
    - https://gitee.com/y_project/RuoYi-Vue/issues/I1H2JB
    
```
最后居然证明是
fastjson的@type是旧的地址 --> redis
所以文件位置以后还是不要随便调整了

===
Object obj = redisCache.getCacheObject(userKey);
String userValue = JSON.toJSONString(obj);
LoginUser user = JSON.parseObject(userValue, LoginUser.class);

关掉devtools
直接用jrebel
```        
