package wang.sunnly.micro.services.scannable.tools.cache.redis.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisPoolConfig;
import wang.sunnly.micro.services.scannable.tools.cache.redis.properties.Cluster;
import wang.sunnly.micro.services.scannable.tools.cache.redis.properties.RedisCacheProperties;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

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
    public CacheManager cacheManager(JedisConnectionFactory jedisConnectionFactory,
                                     RedisCacheProperties redisCacheProperties) {

        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(jedisConnectionFactory);
        RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        cacheConfiguration = cacheConfiguration.entryTtl(Duration.ofSeconds(redisCacheProperties.getExpire()));
        RedisCacheManager cacheManager = new RedisCacheManager(redisCacheWriter, cacheConfiguration);

        return cacheManager;
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory(
            RedisCacheProperties redisCacheProperties) {
        if (redisCacheProperties.getClusters().isClusterEnabled()) {
            RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
            redisClusterConfiguration.setMaxRedirects(redisCacheProperties.getClusters().getMaxRedirects());
            Set<RedisNode> nodes = new HashSet<>();
            for (Cluster cluster : redisCacheProperties.getClusters().getCluster()){
                RedisNode node = new RedisNode(
                        cluster.getHost(),
                        cluster.getPort());
                nodes.add(node);
            }
            redisClusterConfiguration.setClusterNodes(nodes);
            return new JedisConnectionFactory(redisClusterConfiguration,jedisPoolConfig());
        }else {
            RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
            redisStandaloneConfiguration.setDatabase(redisCacheProperties.getDbIndex());
            redisStandaloneConfiguration.setHostName(redisCacheProperties.getHost());
            redisStandaloneConfiguration.setPort(redisCacheProperties.getPort());
            if (StringUtils.isNotEmpty(redisCacheProperties.getPassword())) {
                redisStandaloneConfiguration.setPassword(RedisPassword.of(redisCacheProperties.getPassword()));
            }
            JedisClientConfiguration clientConfiguration = JedisClientConfiguration.defaultConfiguration();
            JedisPoolConfig jedisPoolConfig = null;
            return new JedisConnectionFactory(redisStandaloneConfiguration, clientConfiguration);
        }
    }


    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //最大连接数
        jedisPoolConfig.setMaxTotal(100);
        //最小空闲连接数
        jedisPoolConfig.setMinIdle(20);
        //当池内没有可用的连接时，最大等待时间
        jedisPoolConfig.setMaxWaitMillis(10000);
        //------其他属性根据需要自行添加-------------
        return jedisPoolConfig;
    }
}
