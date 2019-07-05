package wang.sunnly.micro.services.scannable.security.auth.client.reqs.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import wang.sunnly.micro.services.scannable.security.auth.client.core.store.ClientTokenStore;

/**
 * RefreshClientPubKeySchedule
 *
 * @author Sunnly
 * @create 2019/6/22 0022 2:33
 */
@Configuration
@Slf4j
@EnableScheduling
public class RefreshAllowedClientSchedule {

    @Autowired
    private ClientTokenStore clientTokenStore;

    //【定时刷允许访问的微服务】
    @Scheduled(cron = "${sunnly.schedule.refresh-allowed-client:'*/10 * * * * ?'}")
    public void refreshAllowedClient(){
        clientTokenStore.refreshAllowedClient();
    }
}
