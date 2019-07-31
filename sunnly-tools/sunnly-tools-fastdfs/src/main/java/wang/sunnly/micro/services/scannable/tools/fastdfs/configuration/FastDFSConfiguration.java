package wang.sunnly.micro.services.scannable.tools.fastdfs.configuration;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import wang.sunnly.micro.services.scannable.tools.fastdfs.factory.StorageFactory;

/**
 * FastDFSConfiguration
 *
 * @author Sunnly
 * @since 2019/6/28 0028 22:42
 */
@Configurable
public class FastDFSConfiguration {
    @Bean
    public StorageFactory storageFactory() {
        return new StorageFactory();
    }

}
