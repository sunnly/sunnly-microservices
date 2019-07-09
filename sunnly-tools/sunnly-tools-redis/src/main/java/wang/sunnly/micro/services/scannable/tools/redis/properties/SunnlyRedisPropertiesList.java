package wang.sunnly.micro.services.scannable.tools.redis.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * RedisCacheProperties
 *
 * @author Sunnly
 * @create 2019/7/8 15:35
 */
@EnableConfigurationProperties(SunnlyRedisPropertiesList.class)
@ConfigurationProperties("sunnly.redis")
@Data
public class SunnlyRedisPropertiesList {

    @NestedConfigurationProperty
    private SunnlyRedisProperties[] list = {};

}
