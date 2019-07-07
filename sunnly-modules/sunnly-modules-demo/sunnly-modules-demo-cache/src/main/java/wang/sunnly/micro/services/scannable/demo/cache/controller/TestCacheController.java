package wang.sunnly.micro.services.scannable.demo.cache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.sunnly.micro.services.scannable.demo.cache.service.TestCacheService;

/**
 * TestCacheController
 *
 * @author Sunnly
 * @create 2019/7/7 0007 19:47
 */
@RestController
@RequestMapping("cache")
public class TestCacheController {

    @Autowired
    private TestCacheService testCacheService;

    @GetMapping("/{username}/{password}")
    public String get(@PathVariable("username") String username,
                      @PathVariable("password") String password){
        return testCacheService.get(username, password);
    }
}
