package wang.sunnly.micro.services.scannable.security.auth.client.req.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import wang.sunnly.micro.services.scannable.security.auth.client.core.store.ClientTokenStore;

/**
 * SecurityAuthClientSchedule
 *
 * 由于服务鉴权管理员可能会修改，故需要定时去获取允许访问的服务
 * @author Sunnly
 * @since 2019/6/21 16:20
 */

@Configurable
@EnableScheduling
public class SecurityAuthClientSchedule {

    @Autowired
    private ClientTokenStore clientTokenStore;

    //【定时刷新服务端token】
    @Scheduled(cron = "${sunnly.schedule.refresh-client-token:'*/20 * * * * ?'}")
    public void refreshClientToken(){
        if (clientTokenStore.isAccess()){
            clientTokenStore.refreshClientToken();
        }
    }


}
