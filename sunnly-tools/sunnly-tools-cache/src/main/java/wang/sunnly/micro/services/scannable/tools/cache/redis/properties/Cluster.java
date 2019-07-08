package wang.sunnly.micro.services.scannable.tools.cache.redis.properties;

import lombok.Data;

/**
 * Cluster
 *
 * @author Sunnly
 * @create 2019/7/9 0009 1:36
 */
@Data
public class Cluster {
    private String host;
    private int port;
}
