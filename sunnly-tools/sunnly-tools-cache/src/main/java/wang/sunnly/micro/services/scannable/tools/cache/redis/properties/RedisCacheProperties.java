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
 * @since 2019/7/8 15:35
 */
@EnableConfigurationProperties(RedisCacheProperties.class)
@ConfigurationProperties("sunnly.cache.redis")
@Data
public class RedisCacheProperties {

    private int database = 2;
    private String host = "localhost";
    private int port = 6379;
    private String password;
    private int expire = 3600;

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
            private int maxTotal = 100;
            private int minIdle = 8;
            private int maxIdle = 8;
            private Long maxWaitMillis = 10000L;

            private boolean lifo = true;
            private boolean fairness = false;
            private long minEvictableIdleTimeMillis = 1800000L;
            private long evictorShutdownTimeoutMillis = 10000L;
            private long softMinEvictableIdleTimeMillis = -1L;
            private int numTestsPerEvictionRun = 3;
        }

    }
}
