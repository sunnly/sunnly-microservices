package wang.sunnly.micro.services.scannable.tools.cache.collection.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import wang.sunnly.micro.services.scannable.tools.cache.collection.properties.CollectionCacheProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * SunnlyCacheForSimpleConfiguration
 *
 * @author Sunnly
 * @create 2019/7/8 14:13
 */
@Configurable
public class SunnlyCacheForCollectionConfiguration {

    @Bean
    public CollectionCacheProperties simpleCacheProperties(){
        return new CollectionCacheProperties();
    }

    @Bean
    public CacheManager simpleCacheManager(CollectionCacheProperties simpleCacheProperties) {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        List<Cache> caches = new ArrayList<Cache>();

        for (String name : simpleCacheProperties.getNames()){
            ConcurrentMapCache cache = new ConcurrentMapCache(name);
            caches.add(cache);
        }
        cacheManager.setCaches(caches);
        return cacheManager;
    }

}
