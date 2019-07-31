package wang.sunnly.micro.services.scannable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import wang.sunnly.micro.services.scannable.tools.materials.annotation.EnableSunnlyUpload;

/**
 * CentreMaterialsApplication
 *
 * @author Sunnly
 * @since 2019/7/22 12:17
 */
@SpringBootApplication
@EnableSunnlyUpload
public class CentreMaterialsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CentreMaterialsApplication.class, args);
    }
}
