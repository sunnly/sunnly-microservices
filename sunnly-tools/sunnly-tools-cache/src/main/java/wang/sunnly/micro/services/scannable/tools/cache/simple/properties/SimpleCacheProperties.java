package wang.sunnly.micro.services.scannable.tools.cache.simple.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import wang.sunnly.micro.services.scannable.tools.cache.redis.properties.RedisCacheProperties;

import java.util.Set;

/**
 * SimpleCacheProperties
 *
 * @author Sunnly
 * @create 2019/7/8 16:52
 */
@EnableConfigurationProperties(SimpleCacheProperties.class)
@ConfigurationProperties("sunnly.cache.simple")
@Data
public class SimpleCacheProperties {

    private Set<String> names;
}
