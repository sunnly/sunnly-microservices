package wang.sunnly.micro.services.scannable.tools.logger.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * LoggerProperties
 *
 * @author Sunnly
 * @since 2019/7/24 17:43
 */
@ConfigurationProperties("sunnly.logger")
@Configuration
@Data
public class LoggerProperties {
    Map<String ,Map<String ,String>> catalog = new HashMap<>();
}
