package wang.sunnly.modules.test.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import wang.sunnly.modules.test.spring.service.CityService;

/**
 * TestSpringApplication
 *
 * @author Sunnly
 * @create 2019/7/23 0023 21:43
 */
public class TestSpringApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        CityService cityService = ctx.getBean(CityService.class);
//        CityService cityService = (CityService) ctx.getBean("cityService");
        System.out.println(cityService);
        ctx.close();
    }
}
