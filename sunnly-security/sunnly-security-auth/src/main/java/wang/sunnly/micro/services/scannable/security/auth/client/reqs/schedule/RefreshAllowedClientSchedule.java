package wang.sunnly.micro.services.scannable.security.auth.client.reqs.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import wang.sunnly.micro.services.scannable.security.auth.client.core.store.ClientTokenStore;

/**
 * RefreshAllowedClientSchedule
 *
 * @author Sunnly
 * @create 2019/6/22 0022 2:33
 */
@Configurable
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
