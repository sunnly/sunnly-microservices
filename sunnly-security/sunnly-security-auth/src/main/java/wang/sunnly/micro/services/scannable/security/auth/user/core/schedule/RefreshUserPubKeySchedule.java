package wang.sunnly.micro.services.scannable.security.auth.user.core.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import wang.sunnly.micro.services.scannable.security.auth.user.core.api.RefreshUserPubKey;

/**
 * RefreshUserPubKeySchedule
 * @author Sun
 * @since 2019/6/22 0022 2:33
 */
@Configurable
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
