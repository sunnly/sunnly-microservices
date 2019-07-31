package wang.sunnly.micro.services.scannable.tools.cache.enums;

import java.util.Date;

/**
 * SunnlyCacheMode
 *
 * @author Sunnly
 * @since 2019/7/7 0007 20:02
 */
public enum SunnlyCacheMode {
    /**
     *  此模式只适用于单体，请谨慎选择
     *
     * @see SunnlyCacheMode#REDIS
     */
    @Deprecated
    COLLECTION,
    /**
     *  此模式只适用于单体，请谨慎选择
     *
     * @see SunnlyCacheMode#REDIS
     */
    @Deprecated
    EHCACHE,
    REDIS
}
