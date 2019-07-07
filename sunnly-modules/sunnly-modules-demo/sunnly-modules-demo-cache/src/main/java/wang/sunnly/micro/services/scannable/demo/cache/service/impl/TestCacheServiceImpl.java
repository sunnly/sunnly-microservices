package wang.sunnly.micro.services.scannable.demo.cache.service.impl;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import wang.sunnly.micro.services.scannable.demo.cache.service.TestCacheService;

/**
 * TestCacheServiceImpl
 *
 * @author Sunnly
 * @create 2019/7/7 0007 19:50
 */
@Service
public class TestCacheServiceImpl implements TestCacheService {
    @Override
    @Cacheable(value = "mycache", key = "aaa")
    public String get(String username, String password) {

        System.out.println("===================================");
        System.out.println("===================================");
        System.out.println("===================================");
        System.out.println("===================================");
        System.out.println("===================================");
        return String.format("Hello, %s. Your password is %s.", username, password);
    }
}
