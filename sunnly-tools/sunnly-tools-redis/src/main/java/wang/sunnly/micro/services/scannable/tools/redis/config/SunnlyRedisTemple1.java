package wang.sunnly.micro.services.scannable.tools.redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPoolConfig;
import wang.sunnly.micro.services.scannable.tools.redis.properties.SunnlyRedisProperties;
import wang.sunnly.micro.services.scannable.tools.redis.properties.SunnlyRedisPropertiesList;

import javax.annotation.Resource;

/**
 * @author Sunnly
 * @ClassName RedisTemplateConfig
 * @Date 2019/6/13 14:23
 * @Version 1.0
 */
@Configurable
public class SunnlyRedisTemple1 extends SunnlyRedisConfig{

    static String name;
    @Bean
    public SunnlyRedisPropertiesList sunnlyRedisPropertiesList(){
        return new SunnlyRedisPropertiesList();
    }
//    @ConditionalOnProperty("sunnly.redis[0].host")
//    @Bean("${sunnly.redis.list[0].name}")
//    @Bean("redisTemplate1")
    @Bean("sunnlyRedisTemplate")
    public RedisTemplate redisTemplate(SunnlyRedisPropertiesList sunnlyRedisPropertiesList){
        return new StringRedisTemplate(jedisConnectionFactoryConfig(sunnlyRedisPropertiesList.getList()[0]));
    }

    @Resource(name = "sunnlyRedisTemplate")
    private RedisTemplate sunnlyRedisTemplate;
    @Bean
    public String test(ApplicationContext ctx){
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory)ctx.getAutowireCapableBeanFactory();
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(sunnlyRedisTemplate.getClass());
        defaultListableBeanFactory.registerBeanDefinition("testService",beanDefinitionBuilder.getBeanDefinition());
        beanDefinitionBuilder.setFactoryMethod("sunnlyRedisPropertiesList");
        ctx.getBean("testService");
        return null;
    }
}
