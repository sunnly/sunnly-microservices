package wang.sunnly.micro.services.scannable.security.auth.response.client.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import wang.sunnly.micro.services.scannable.security.auth.core.api.RefreshPubKey;

/**
 * RefreshClientPubKeySchedule
 *
 * @author Sunnly
 * @create 2019/6/22 0022 2:33
 */
@Configuration
@Slf4j
@EnableScheduling
public class RefreshClientPubKeySchedule {

    @Autowired
    private RefreshPubKey refreshPubKey;

//    @Scheduled(cron = "0 0/1 * * * ?")
    public void refreshClientPubKey(){
        refreshPubKey.refreshClientPubKey();
    }
}
