package wang.sunnly.micro.services.scannable.tools.redis.selector;

import wang.sunnly.micro.services.scannable.tools.redis.annotation.EnableSunnlyRedis;

/**
 * SunnlyCachingConfigurationSelector
 *
 * @author Sunnly
 * @since 2019/7/7 0007 20:19
 */
public class SunnlyRedisConfigurationSelector extends AbstractRedisModeImportSelector<EnableSunnlyRedis> {

    private static final String DEFAULT_REDIS_TEMPLATE = "wang.sunnly.micro.services.scannable.tools.redis.config.SunnlyRedisTemplateConfig";
    private static final String REDIS_TEMPLE_GENERATOR = "wang.sunnly.micro.services.scannable.tools.redis.config.SunnlyRedisTempleGenerator";

    @Override
    protected String[] selectImports(int size) {
        if(size == 0){
            //默认
            return new String[]{DEFAULT_REDIS_TEMPLATE};
        }else{
            return new String[]{REDIS_TEMPLE_GENERATOR};
        }
    }


}
