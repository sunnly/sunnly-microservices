package wang.sunnly.micro.services.scannable.tools.cache.selector;

import wang.sunnly.micro.services.scannable.tools.cache.annotation.EnableSunnlyCaching;
import wang.sunnly.micro.services.scannable.tools.cache.enums.SunnlyAdviceMode;

/**
 * SunnlyCachingConfigurationSelector
 *
 * @author Sunnly
 * @create 2019/7/7 0007 20:19
 */
public class SunnlyCachingConfigurationSelector extends SunnlyAdviceModeImportSelector<EnableSunnlyCaching> {

    private static SunnlyAdviceMode sunnlyAdviceMode = SunnlyAdviceMode.REDIS;

    @Override
    protected String[] selectImports(SunnlyAdviceMode adviceMode) {
        this.sunnlyAdviceMode = adviceMode;
        return new String[]{adviceMode.name()};
    }
}
