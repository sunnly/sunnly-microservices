package wang.sunnly.micro.services.scannable.tools.cache.redis.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.redis.cache.CacheKeyPrefix;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import wang.sunnly.micro.services.scannable.tools.cache.redis.properties.RedisCacheProperties;

import java.lang.reflect.Method;
import java.time.Duration;

/**
 * SunnlyCacheForRedisConfiguration
 *
 * @author Sunnly
 * @create 2019/7/8 13:49
 */
@Configurable
public class SunnlyCacheForRedisConfiguration {

    //配置Key的生成策略
    @Bean
    public KeyGenerator KeyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
//                List<Map<String, Object>> caches = SunnlyCachingConfigurationSelector.caches;
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }

    @Bean
    public RedisCacheProperties redisCacheProperties(){
        return new RedisCacheProperties();
    }
    @Bean("simpleCacheManager")
    public CacheManager cacheManager(RedisCacheProperties redisCacheProperties,
                                     RedisConnectionFactory connectionFactory) {
        //初始化一个RedisCacheWriter
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory);
        RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        cacheConfiguration = cacheConfiguration.entryTtl(Duration.ofSeconds(redisCacheProperties.getExpire()));
        RedisCacheManager cacheManager = new RedisCacheManager(redisCacheWriter, cacheConfiguration);

        return cacheManager;
    }

    public JedisConnectionFactory createJedisConnectionFactory(int dbIndex, String host, int port, String password, int timeout) {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
//        RedisSentinelConfiguration redisSentinelConfiguration = new RedisSentinelConfiguration();
//        JedisConnectionFactory redisStandaloneConfiguration = new JedisConnectionFactory();
        redisStandaloneConfiguration.setDatabase(dbIndex);
        redisStandaloneConfiguration.setHostName(host);
        redisStandaloneConfiguration.setPort(port);
//        redisStandaloneConfiguration.setPassword();
//        redisStandaloneConfiguration.setTimeout(timeout);
//        redisStandaloneConfiguration.setsetPoolConfig(setPoolConfig(redisPoolMaxIdle, redisPoolMinIdle, redisPoolMaxActive, redisPoolMaxWait, true));
        return new JedisConnectionFactory(redisStandaloneConfiguration);

    }

//    @Bean
//    public JedisPoolConfig jedisPoolConfig() {
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        //最大连接数
//        jedisPoolConfig.setMaxTotal(100);
//        //最小空闲连接数
//        jedisPoolConfig.setMinIdle(20);
//        //当池内没有可用的连接时，最大等待时间
//        jedisPoolConfig.setMaxWaitMillis(10000);
//        //------其他属性根据需要自行添加-------------
//        return jedisPoolConfig;
//    }
//    @Bean
//    public RedisConnectionFactory redisConnectionFactory(JedisPoolConfig jedisPoolConfig) {
//        //单机版jedis
//        RedisStandaloneConfiguration redisStandaloneConfiguration =
//                new RedisStandaloneConfiguration();
//        //设置redis服务器的host或者ip地址
//        redisStandaloneConfiguration.setHostName("localhost");
//        //设置默认使用的数据库
//        redisStandaloneConfiguration.setDatabase(0);
//        //设置密码
//        redisStandaloneConfiguration.setPassword(RedisPassword.of("123456"));
//        //设置redis的服务的端口号
//        redisStandaloneConfiguration.setPort(6380);
//        //获得默认的连接池构造器(怎么设计的，为什么不抽象出单独类，供用户使用呢)
//        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jpcb =
//                (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder)JedisClientConfiguration.builder();
//        //指定jedisPoolConifig来修改默认的连接池构造器（真麻烦，滥用设计模式！）
//        jpcb.poolConfig(jedisPoolConfig);
//        //通过构造器来构造jedis客户端配置
//        JedisClientConfiguration jedisClientConfiguration = jpcb.build();
//        //单机配置 + 客户端配置 = jedis连接工厂
//        return new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration);
//    }
}
