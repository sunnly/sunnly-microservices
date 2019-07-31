package wang.sunnly.micro.services.scannable.centre.materials.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ViewController
 *
 * @author Sunnly
 * @since 2019/7/22 15:34
 */
@Controller
public class ViewController {

    @GetMapping("upload")
    public String upload() {
        return "upload/upload";
    }

    @GetMapping("big/upload")
    public String bigUpload() {
        return "upload/bigfile";
    }
}
