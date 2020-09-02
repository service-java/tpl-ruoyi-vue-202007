# README

- https://www.baeldung.com/spring-rest-api-with-binary-data-formats
- https://blog.csdn.net/boling_cavalry/article/details/80710774
- https://blog.csdn.net/wanbf123/article/details/77984832
- https://stackoverflow.com/questions/33009918/spring-boot-controller-content-negotiation
- 测试地址 
    - http://localhost:8080/kryo/set
    - http://localhost:8080/kryo/remote
    - http://localhost:8080/kryo/remote2?name=hello&code=123
    - http://localhost:8080/kryo/remote3

```java
private static final ThreadLocal<Kryo> kryoThreadLocal = ThreadLocal.withInitial(() -> {
    Kryo kryo = new Kryo();

    // @fix 使用kryo做序列化会遇到的几个坑 --> 暂时不需要
    // https://my.oschina.net/yangming0322/blog/740949/print
    // https://blog.csdn.net/paoma_1008/article/details/79827803
    SynchronizedCollectionsSerializer.registerSerializers(kryo);

    // 如果是远程项目交接, 这里需要注册
    kryo.register(KryoParams.class, 1);
    kryo.register(KryoResult.class, 2);

    kryo.setReferences(true);

    return kryo;
});
```
