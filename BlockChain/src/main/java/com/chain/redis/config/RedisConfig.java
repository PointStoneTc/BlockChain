package com.chain.redis.config;

import java.lang.reflect.Method;
import java.time.Duration;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager.RedisCacheManagerBuilder;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.chain.redis.service.RedisService;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;

@Configuration
@EnableCaching // 开启缓存支持
public class RedisConfig extends CachingConfigurerSupport {
    private final static Logger logger = LoggerFactory.getLogger(RedisConfig.class);

    @Resource
    private LettuceConnectionFactory lettuceConnectionFactory;
    private Duration timeToLive = Duration.ofSeconds(600L);

    @Bean
    RedisMessageListenerContainer container(LettuceConnectionFactory lettuceConnectionFactory, MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(lettuceConnectionFactory);
        // 配置监听通道
        container.addMessageListener(listenerAdapter, new PatternTopic("mc"));// 通道的名字
        logger.info("初始化监听成功，监听通道：【mc】");
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(RedisService redisService) {
        return new MessageListenerAdapter(redisService, "receiveMessage");
    }

    /**
     * 显示声明缓存key生成器
     *
     * @return
     */
    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuffer sb = new StringBuffer();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }

    /**
     * 重写RedisCacheManager的getCache方法，实现设置key的有效时间 重写RedisCache的get方法，实现触发式自动刷新
     * <p>
     * 自动刷新方案： 1、获取缓存后再获取一次有效时间，拿这个时间和我们配置的自动刷新时间比较，如果小于这个时间就刷新。
     * 2、每次创建缓存的时候维护一个Map，存放key和方法信息（反射）。当要刷新缓存的时候，根据key获取方法信息。 通过获取其代理对象执行方法，刷新缓存。
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    public CacheManager cacheManager() {
        // 关键点，spring cache的注解使用的序列化都从这来，没有这个配置的话使用的jdk自己的序列化，实际上不影响使用，只是打印出来不适合人眼识别
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig().serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(keySerializer()))// key序列化方式
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer()))// value序列化方式
                .entryTtl(timeToLive).disableCachingNullValues();// 缓存过期时间
        RedisCacheManagerBuilder builder = RedisCacheManagerBuilder.fromConnectionFactory(lettuceConnectionFactory).cacheDefaults(config).transactionAware();
        return builder.build();
    }

    /**
     * 重写Redis序列化方式，使用Json方式:
     * 当我们的数据存储到Redis的时候，我们的键（key）和值（value）都是通过Spring提供的Serializer序列化到数据库的。RedisTemplate默认使用的是JdkSerializationRedisSerializer，StringRedisTemplate默认使用的是StringRedisSerializer。
     * Spring Data JPA为我们提供了下面的Serializer：
     * GenericToStringSerializer、Jackson2JsonRedisSerializer、JacksonJsonRedisSerializer、JdkSerializationRedisSerializer、OxmSerializer、StringRedisSerializer。
     * 在此我们将自己配置RedisTemplate并定义Serializer。
     *
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        // 设置序列化
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, Visibility.ANY);
        om.enableDefaultTyping(DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        // 配置redisTemplate
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        RedisSerializer<?> stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);// key序列化
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);// value序列化
        redisTemplate.setHashKeySerializer(stringSerializer);// Hash key序列化
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);// Hash value序列化
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    private RedisSerializer<String> keySerializer() {
        return new StringRedisSerializer();
    }

    /**
     * GenericJackson2JsonRedisSerializer和Jackson2JsonRedisSerializer大部分时候表现没有区别，实际上如果对象中有LinkedHashMap时候，后者会出错。</br>
     * GenericJackson2JsonRedisSerializer和Jackson2JsonRedisSerializer都有一个问题，无法反序列化接口的动态代理类，原因应该是动态代理类没有缺省构造函数。</br>
     * 实体中如果有java8time，诸如LocalDateTime，redis缓存反序列化的时候会失败，必须在实体中指定json序列化和反序列化的类@JsonDeserialize和@JsonSerialize。</br>
     * 
     * @JsonDeserialize(using = LocalDateTimeDeserializer.class) </br>
     * @JsonSerialize(using = LocalDateTimeSerializer.class)</br>
     * @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")</br>
     *                         格式化前台页面收到的json时间格式，不指定的话会变成缺省的"yyyy-MM-dd'T'HH:mm:ss"</br>
     * @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") </br>
     *                     private LocalDateTime createTime;//创建时间
     * 
     * @return
     */
    private RedisSerializer<Object> valueSerializer() {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        return jackson2JsonRedisSerializer;

        // 或者使用GenericJackson2JsonRedisSerializer
        // return new GenericJackson2JsonRedisSerializer();
    }
}
