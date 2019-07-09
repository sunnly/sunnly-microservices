package wang.sunnly.micro.services.scannable.tools.cache.collection.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.Set;

/**
 * SimpleCacheProperties
 *
 * @author Sunnly
 * @create 2019/7/8 16:52
 */
@EnableConfigurationProperties(CollectionCacheProperties.class)
@ConfigurationProperties("sunnly.cache.simple")
@Data
public class CollectionCacheProperties {

    private Set<String> names;
}
