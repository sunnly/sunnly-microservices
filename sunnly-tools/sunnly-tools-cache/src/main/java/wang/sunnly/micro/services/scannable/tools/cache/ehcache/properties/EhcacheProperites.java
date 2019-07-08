package wang.sunnly.micro.services.scannable.tools.cache.ehcache.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import wang.sunnly.micro.services.scannable.tools.cache.redis.properties.RedisCacheProperties;

/**
 * EhcacheProperites
 *
 * @author Sunnly
 * @create 2019/7/8 16:34
 */
@EnableConfigurationProperties(EhcacheProperites.class)
@ConfigurationProperties("sunnly.cache.ehcache")
@Data
public class EhcacheProperites {

    private String path;
}
