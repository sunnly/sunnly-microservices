package wang.sunnly.micro.services.scannable.tools.cache.selector;

import wang.sunnly.micro.services.scannable.tools.cache.annotation.EnableSunnlyCaching;
import wang.sunnly.micro.services.scannable.tools.cache.enums.SunnlyCacheMode;

import java.util.List;
import java.util.Map;

/**
 * SunnlyCachingConfigurationSelector
 *
 * @author Sunnly
 * @create 2019/7/7 0007 20:19
 */
public class SunnlyCachingConfigurationSelector extends SunnlyCacheModeImportSelector<EnableSunnlyCaching> {

    private final String REDISCONFIG = "wang.sunnly.micro.services.scannable.tools.cache.redis.config.SunnlyCacheForRedisConfiguration";
    private final String EHCACHECONFIG = "wang.sunnly.micro.services.scannable.tools.cache.ehcache.config.SunnlyCacheForEhcacheConfiguration";
    private final String COLLECTIONCONFIG = "wang.sunnly.micro.services.scannable.tools.cache.collection.config.SunnlyCacheForwang.sunnly.micro.services.scannable.tools.cache.collection.config.SunnlyCacheForCollectionConfigurationConfiguration";

    @Override
    protected String[] selectImports(SunnlyCacheMode cacheMode) {
        switch (cacheMode){
            case REDIS:
                return new String[]{REDISCONFIG};
            case EHCACHE:
                return new String[]{EHCACHECONFIG};
            case COLLECTION:
                return new String[]{COLLECTIONCONFIG};
        }
        return new String[0];
    }

}
