package wang.sunnly.micro.services.scannable.security.auth.client.res.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import wang.sunnly.micro.services.scannable.security.auth.client.res.api.RefreshClientPubKey;

/**
 * RefreshClientPubKeySchedule
 *
 * @author Sunnly
 * @since 2019/6/22 0022 2:33
 */
@Configurable
@EnableScheduling
public class RefreshClientPubKeySchedule {

    @Autowired
    private RefreshClientPubKey refreshClientPubKey;

    //【定时刷ClientPubKey】
    @Scheduled(cron = "${sunnly.schedule.refresh-client-pubkey:'*/50 * * * * ?'}")
    public void refreshClientPubKey(){
        refreshClientPubKey.refreshClientPubKey();
    }
}
