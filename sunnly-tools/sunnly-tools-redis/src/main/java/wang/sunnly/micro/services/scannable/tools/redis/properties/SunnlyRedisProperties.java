package wang.sunnly.micro.services.scannable.tools.redis.properties;

import lombok.Data;

/**
 * SunnlyRedisProperties
 *
 * @author Sunnly
 * @since 2019/7/8 15:35
 */
@Data
public class SunnlyRedisProperties {

    private String name;

    private int database = 2;
    private String host = "localhost";
    private int port = 6379;
    private String password;

}
