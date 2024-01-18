依赖 

```maven
<dependency>
    <groupId>com.g7.framework</groupId>
    <artifactId>idgenerator-spring-boot-autoconfigure</artifactId>
    <version>1.0.1-SNAPSHOT</version>
</dependency>
```

使用方式 

```java
@Autowired
private SnowflakeIdGenerator snowflakeIdGenerator;

public void test() {
    snowflakeIdGenerator.next();
}
```