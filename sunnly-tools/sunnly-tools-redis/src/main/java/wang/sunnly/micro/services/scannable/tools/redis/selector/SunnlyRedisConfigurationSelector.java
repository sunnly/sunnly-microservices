package wang.sunnly.micro.services.scannable.tools.redis.selector;

import wang.sunnly.micro.services.scannable.tools.redis.annotation.EnableSunnlyRedis;

/**
 * SunnlyCachingConfigurationSelector
 *
 * @author Sunnly
 * @create 2019/7/7 0007 20:19
 */
public class SunnlyRedisConfigurationSelector extends SunnlyRedisModeImportSelector<EnableSunnlyRedis> {

    private final String DEFAULTREDISTEMPLATE = "wang.sunnly.micro.services.scannable.tools.redis.config.RedisTemplateConfig";
    private final String REDISTEMPLEGENERATOR = "wang.sunnly.micro.services.scannable.tools.redis.config.SunnlyRedisTempleGenerator";

    @Override
    protected String[] selectImports(int size) {
        if(size == 0){
            //默认
            return new String[]{DEFAULTREDISTEMPLATE};
        }else{
            return new String[]{REDISTEMPLEGENERATOR};
        }
    }


}
