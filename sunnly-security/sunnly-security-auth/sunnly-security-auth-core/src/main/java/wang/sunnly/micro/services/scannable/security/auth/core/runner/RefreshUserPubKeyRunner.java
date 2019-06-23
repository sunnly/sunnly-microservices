package wang.sunnly.micro.services.scannable.security.auth.core.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import wang.sunnly.micro.services.scannable.security.auth.core.schedule.RefreshUserPubKeySchedule;

/**
 * @author Sunnly
 * RefreshUserPubKeyRunner
 * @created 2019/6/16 0016 23:27
 **/
@Configuration
//@Slf4j
@EnableScheduling
public class RefreshUserPubKeyRunner implements CommandLineRunner {
    Logger log = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private RefreshUserPubKeySchedule refreshUserPubKeySchedule;
    @Override
    public void run(String... args) throws Exception {
        try {
            log.info("初始化用户pubKey");
            refreshUserPubKeySchedule.refreshUserPubKey();
        }catch (Exception e){
            log.info("用户PubKey初始化失败");
            e.printStackTrace();
        }


    }

    private void getClientPublicKey() {
    }

}
