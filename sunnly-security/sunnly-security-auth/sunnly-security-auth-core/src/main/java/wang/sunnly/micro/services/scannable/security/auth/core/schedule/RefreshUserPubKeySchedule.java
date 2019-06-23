package wang.sunnly.micro.services.scannable.security.auth.core.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import wang.sunnly.micro.services.scannable.security.auth.core.api.RefreshPubKey;

/**
 * RefreshUserPubKeySchedule
 *
 * @author Sunnly
 * @create 2019/6/22 0022 2:33
 */
@Configuration
@Slf4j
@EnableScheduling
public class RefreshUserPubKeySchedule {

    @Autowired
    private RefreshPubKey refreshPubKey;

//    @Scheduled(cron = "0 0/1 * * * ?")
    public void refreshUserPubKey(){
        refreshPubKey.refreshUserPubKey();
    }

}
