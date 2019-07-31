package wang.sunnly.micro.services.scannable.demo.logger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.sunnly.micro.services.scannable.common.web.msg.ObjectRestResponse;
import wang.sunnly.micro.services.scannable.demo.logger.service.LoggerService;
import wang.sunnly.micro.services.scannable.tools.logger.annotation.SunnlyLogger;

/**
 * LoggerController
 *
 * @author Sunnly
 * @since 2019/7/24 14:07
 */
@RestController
@RequestMapping("logger")
public class LoggerController {

    @Autowired
    private LoggerService loggerService;

    @GetMapping("lg")
    @SunnlyLogger(value = "用户日志表",cata = "mystream")
    public ObjectRestResponse logger(){

        return new ObjectRestResponse().data(null);
    }
}
