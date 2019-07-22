package wang.sunnly.micro.services.scannable.tools.cache.collection.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.util.Assert;
import wang.sunnly.micro.services.scannable.tools.cache.collection.properties.CollectionCacheProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * SunnlyCacheForSimpleConfiguration
 *
 * @author Sunnly
 * @since 2019/7/8 14:13
 */
@Configurable
public class SunnlyCacheForCollectionConfiguration {

    @Bean
    public CollectionCacheProperties collectionCacheProperties(){
        return new CollectionCacheProperties();
    }

    @Bean
    public CacheManager simpleCacheManager(CollectionCacheProperties collectionCacheProperties) {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        List<Cache> caches = new ArrayList<Cache>();

        Assert.notNull(collectionCacheProperties.getNames(), "Collection缓存需要在配置文件中配置\"sunnly.cache.redis.collection.names\"的列表");
        for (String name : collectionCacheProperties.getNames()){
            ConcurrentMapCache cache = new ConcurrentMapCache(name);
            caches.add(cache);
        }
        cacheManager.setCaches(caches);
        return cacheManager;
    }

}
