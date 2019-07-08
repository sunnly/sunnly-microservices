package wang.sunnly.micro.services.scannable.demo.cache.service.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import wang.sunnly.micro.services.scannable.demo.cache.service.CacheService;

/**
 * CacheServiceImpl
 *
 * @author Sunnly
 * @create 2019/7/8 9:57
 */
@Service
public class CacheServiceImpl implements CacheService {

//    @Cacheable(key = "'mycache'", value = "mycache")

    @Override
    @Cacheable(key = "#username", value = "aaa")
    public String def(String username, String password) {
        System.out.println("===================================");
        System.out.println("===================================");
        System.out.println("===================================");
        System.out.println("===================================");
        System.out.println("===================================");
        return String.format("Hello, %s. Your password is %s.", username, password);
    }

    @Override
    @Cacheable(key = "#username", value = "mycache3")
    public String mer(String username, String password) {
        System.out.println("===================================");
        System.out.println("===================================");
        System.out.println("===================================");
        System.out.println("===================================");
        System.out.println("===================================");
        return String.format("Hello, %s. Your password is %s.", username, password);
    }

    @Override
    @Cacheable(key = "#username", value = "mycache5")
    public String ehc(String username, String password) {
        System.out.println("===================================");
        System.out.println("===================================");
        System.out.println("===================================");
        System.out.println("===================================");
        System.out.println("===================================");
        return String.format("Hello, %s. Your password is %s.", username, password);
    }

    @Override
    @Cacheable(value = "sss",key = "#username.concat(#password)")
    public String red(String username, String password) {
        System.out.println("===================================");
        System.out.println("===================================");
        System.out.println("===================================");
        System.out.println("===================================");
        System.out.println("===================================");
        return String.format("Hello, %s. Your password is %s.", username, password);
    }
}
