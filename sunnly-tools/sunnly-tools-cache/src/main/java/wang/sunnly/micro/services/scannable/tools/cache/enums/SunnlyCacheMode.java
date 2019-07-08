package wang.sunnly.micro.services.scannable.tools.cache.enums;

import lombok.Delegate;
import org.springframework.context.annotation.AdviceMode;

/**
 * SunnlyCacheMode
 *
 * @author Sunnly
 * @create 2019/7/7 0007 20:02
 */
public enum SunnlyCacheMode {
    @Deprecated
    SIMPLE,
    @Deprecated
    EHCACHE,
    REDIS
}
