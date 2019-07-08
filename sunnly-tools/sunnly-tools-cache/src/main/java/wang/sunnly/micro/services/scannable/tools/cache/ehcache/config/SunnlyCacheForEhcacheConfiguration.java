package wang.sunnly.micro.services.scannable.tools.cache.ehcache.config;

import ch.qos.logback.core.util.FileUtil;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cache.CacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileSystemUtils;
import wang.sunnly.micro.services.scannable.tools.cache.ehcache.properties.EhcacheProperites;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * SunnlyCacheForEhcacheConfiguration
 *
 * @author Sunnly
 * @create 2019/7/8 14:13
 */
@Configurable
public class SunnlyCacheForEhcacheConfiguration {

    @Bean
    public EhcacheProperites ehcacheProperites(){
        return new EhcacheProperites();
    }

    @Bean
    public CacheManager cacheManager(EhcacheProperites ehcacheProperites){
        try {
            net.sf.ehcache.CacheManager ehcacheCacheManager
                    = new net.sf.ehcache.CacheManager(new ClassPathResource(ehcacheProperites.getPath()).getInputStream());

            EhCacheCacheManager cacheCacheManager = new EhCacheCacheManager(ehcacheCacheManager);
            return cacheCacheManager;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
