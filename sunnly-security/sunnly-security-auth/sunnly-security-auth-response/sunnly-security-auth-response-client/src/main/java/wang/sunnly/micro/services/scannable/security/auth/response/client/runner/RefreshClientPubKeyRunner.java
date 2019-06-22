package wang.sunnly.micro.services.scannable.security.auth.response.client.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import wang.sunnly.micro.services.scannable.security.auth.response.client.schedule.RefreshClientPubKeySchedule;

/**
 * @author Sunnly
 * @ClassName RefreshPubKeyRunner
 * @Date 2019/6/16 0016 23:27
 **/
@Configuration
//@Slf4j
@EnableScheduling
public class RefreshClientPubKeyRunner implements CommandLineRunner {
    Logger log = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private RefreshClientPubKeySchedule refreshClientPubKeySchedule;
    @Override
    public void run(String... args) throws Exception {

        try {
            log.info("初始化服务pubKey");
            refreshClientPubKeySchedule.refreshClientPubKey();
        }catch (Exception e){
            log.info("服务PubKey初始化失败");
            e.printStackTrace();
        }


    }


}
