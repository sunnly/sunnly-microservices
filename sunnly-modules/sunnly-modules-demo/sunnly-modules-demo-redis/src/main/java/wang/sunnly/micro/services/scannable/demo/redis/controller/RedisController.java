package wang.sunnly.micro.services.scannable.demo.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.sunnly.micro.services.scannable.tools.redis.annotation.Reference;

import java.util.concurrent.TimeUnit;

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

    @Reference
    private RedisTemplate oneTemplate;
    @Reference
    private RedisTemplate twoTemplate;

    @GetMapping("/{key}/{value}")
    public String save(@PathVariable("key") String key,
                       @PathVariable("value") String value){
        oneTemplate.opsForValue().set(key+"_one", value,50, TimeUnit.SECONDS);
        twoTemplate.opsForValue().set(key+"_two", value);
        return "success";
    }
}
