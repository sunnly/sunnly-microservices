package wang.sunnly.micro.services.scannable.tools.cache.enums;

/**
 * SunnlyCacheMode
 *
 * @author Sunnly
 * @create 2019/7/7 0007 20:02
 */
public enum SunnlyCacheMode {
    //不适合于分布式缓存
    @Deprecated
    COLLECTION,
    //不适合于分布式缓存
    @Deprecated
    EHCACHE,
    REDIS
}
