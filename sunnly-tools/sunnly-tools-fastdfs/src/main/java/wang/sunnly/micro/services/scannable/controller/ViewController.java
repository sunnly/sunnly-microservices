package wang.sunnly.micro.services.scannable.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ViewController
 *
 * @author Sunnly
 * @create 2019/6/28 0028 20:38
 */

@Controller
public class ViewController {

    @GetMapping("form")
    public String form(){
        return "upload/form";
    }
}
