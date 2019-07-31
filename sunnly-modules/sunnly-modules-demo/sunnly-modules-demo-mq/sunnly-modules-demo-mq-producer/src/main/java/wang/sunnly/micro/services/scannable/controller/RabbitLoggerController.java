package wang.sunnly.micro.services.scannable.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import wang.sunnly.micro.services.scannable.tools.fastdfs.service.RabbitLoggerService;

/**
 * LoggerController
 *
 * @author Sunnly
 * @since 2019/7/17 10:06
 */
@RestController
@RequestMapping("rabbit")
public class RabbitLoggerController {

    @Autowired
    RabbitLoggerService rabbitLoggerService;

    @GetMapping("/debug")
    public String debug(@RequestParam("msg") String message){

        return rabbitLoggerService.debug(message) ? "发送成功" : "发送失败";
    }
    @GetMapping("/error")
    public String error(@RequestParam("msg") String message){

        return rabbitLoggerService.error(message) ? "发送成功" : "发送失败";
    }
    @GetMapping("/test")
    public String test(@RequestParam("msg") String message){

        return rabbitLoggerService.test(message) ? "发送成功" : "发送失败";
    }
}
