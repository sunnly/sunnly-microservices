package wang.sunnly.micro.services.scannable.tools.cache.redis.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * RedisCacheProperties
 *
 * @author Sunnly
 * @create 2019/7/8 15:35
 */
@EnableConfigurationProperties(RedisCacheProperties.class)
@ConfigurationProperties("sunnly.cache.redis")
@Data
public class RedisCacheProperties {

    private int expire;
}
