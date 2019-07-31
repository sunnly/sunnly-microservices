package wang.sunnly.micro.services.scannable.tools.cache.selector;

import wang.sunnly.micro.services.scannable.tools.cache.annotation.EnableSunnlyCaching;
import wang.sunnly.micro.services.scannable.tools.cache.enums.SunnlyCacheMode;

/**
 * SunnlyCachingConfigurationSelector
 *
 * @author Sunnly
 * @since 2019/7/7 0007 20:19
 */
public class SunnlyCachingConfigurationSelector extends AbstractCacheModeImportSelector<EnableSunnlyCaching> {

    private final String REDISCONFIG = "wang.sunnly.micro.services.scannable.tools.cache.redis.config.SunnlyCacheForRedisConfiguration";
    private final String EHCACHECONFIG = "wang.sunnly.micro.services.scannable.tools.cache.ehcache.config.SunnlyCacheForEhcacheConfiguration";
    private final String COLLECTIONCONFIG = "wang.sunnly.micro.services.scannable.tools.cache.collection.config.SunnlyCacheForCollectionConfiguration";

    @Override
    protected String[] selectImports(SunnlyCacheMode cacheMode) {
        switch (cacheMode){
            case REDIS:
                return new String[]{REDISCONFIG};
            case EHCACHE:
                return new String[]{EHCACHECONFIG};
            case COLLECTION:
                return new String[]{COLLECTIONCONFIG};
            default: return new String[0];
        }
    }

}
