package wang.sunnly.micro.services.scannable.security.auth.user.core.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import wang.sunnly.micro.services.scannable.security.auth.user.core.api.RefreshUserPubKey;

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
    private RefreshUserPubKey refreshUserPubKey;

    //【定时刷UserPubKey】
    @Scheduled(cron = "${sunnly.schedule.refresh-user-pubkey:'* */40 * * * ?'}")
    public void refreshUserPubKey(){
        refreshUserPubKey.refreshUserPubKey();
    }

}
