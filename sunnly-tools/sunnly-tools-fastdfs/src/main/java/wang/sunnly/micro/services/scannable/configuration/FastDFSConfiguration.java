package wang.sunnly.micro.services.scannable.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import wang.sunnly.micro.services.scannable.factory.StorageFactory;

/**
 * FastDFSConfiguration
 *
 * @author Sunnly
 * @create 2019/6/28 0028 22:42
 */
@Configuration
public class FastDFSConfiguration {
    @Bean
    public StorageFactory storageFactory() {
        return new StorageFactory();
    }
}
