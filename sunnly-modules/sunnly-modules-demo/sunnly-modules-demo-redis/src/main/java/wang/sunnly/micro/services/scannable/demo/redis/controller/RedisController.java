package wang.sunnly.micro.services.scannable.demo.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * RedisController
 *
 * @author Sunnly
 * @create 2019/7/9 13:45
 */
@RestController
@RequestMapping("redis")
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Resource(name = "testService")
    @Lazy
    private RedisTemplate testService;

    @GetMapping("/{key}/{value}")
    public String save(@PathVariable("key") String key,
                       @PathVariable("value") String value){
        redisTemplate.opsForValue().set(key, value);
        testService.opsForValue().get(key);
        return "success";
    }
}
