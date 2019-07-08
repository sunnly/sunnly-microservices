package wang.sunnly.micro.services.scannable.tools.cache.redis.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.util.ArrayList;
import java.util.List;

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

    private int dbIndex;
    private String host;
    private int port;
    private String password;
    private int expire;

    @NestedConfigurationProperty
    private Clusters clusters = new Clusters();

    @Data
    public class Clusters{
        private boolean clusterEnabled;
        private int maxRedirects;
        @NestedConfigurationProperty
        private Pool pool = new Pool();
        @NestedConfigurationProperty
        private List<Cluster> cluster = new ArrayList<>();


        @Data
        public class Pool{
            private int maxTotal;
            private int minIdle;
            private int maxWaitMillis;
        }

    }
}
