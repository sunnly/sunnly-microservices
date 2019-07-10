package wang.sunnly.micro.services.scannable.tools.redis.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import wang.sunnly.micro.services.scannable.tools.redis.properties.SunnlyRedisProperties;
import wang.sunnly.micro.services.scannable.tools.redis.properties.SunnlyRedisPropertiesList;

/**
 * @author Sunnly
 * @ClassName SunnlyRedisTempleGenerator
 * @Date 2019/6/13 14:23
 * @Version 1.0
 */
@Configurable
public class SunnlyRedisTempleGenerator{

    static String name;
    @Bean
    public SunnlyRedisPropertiesList sunnlyRedisPropertiesList(){
        return new SunnlyRedisPropertiesList();
    }

    @Bean
    public String redisTempleGenerator(ApplicationContext ctx,SunnlyRedisPropertiesList sunnlyRedisPropertiesList){
        for (int i=0;i<sunnlyRedisPropertiesList.getList().length;i++){
            SunnlyRedisProperties srp = sunnlyRedisPropertiesList.getList()[i];
            DefaultListableBeanFactory defaultListableBeanFactory =
                    (DefaultListableBeanFactory)ctx.getAutowireCapableBeanFactory();
            BeanDefinitionBuilder beanDefinitionBuilder =
                    BeanDefinitionBuilder.genericBeanDefinition(StringRedisTemplate.class);

            beanDefinitionBuilder.addConstructorArgValue(jedisConnectionFactoryConfig(srp));
            defaultListableBeanFactory.registerBeanDefinition(genKey(srp.getName(),i),beanDefinitionBuilder.getBeanDefinition());
        }
        return null;
    }

    private String genKey(String name,int i) {
        if(StringUtils.isEmpty(name)){
            return "sunnlyTemplate" + i;
        }
        return name ;
    }

    public JedisConnectionFactory jedisConnectionFactoryConfig(
            SunnlyRedisProperties redisProperties) {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setDatabase(redisProperties.getDatabase());
        redisStandaloneConfiguration.setHostName(redisProperties.getHost());
        redisStandaloneConfiguration.setPort(redisProperties.getPort());
       if (StringUtils.isNotEmpty(redisProperties.getPassword())) {
            redisStandaloneConfiguration.setPassword(RedisPassword.of(redisProperties.getPassword()));
        }
        JedisClientConfiguration clientConfiguration = JedisClientConfiguration.defaultConfiguration();
        return new JedisConnectionFactory(redisStandaloneConfiguration, clientConfiguration);
    }

}
