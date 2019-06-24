package wang.sunnly.micro.services.scannable.security.auth.client.req.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import wang.sunnly.micro.services.scannable.security.auth.clietn.core.store.ClientTokenStore;

/**
 *
 * 由于服务鉴权管理员可能会修改，故需要定时去获取允许访问的服务
 * SecurityAuthClientSchedule
 * @author Sunnly
 * @create 2019/6/21 16:20
 */

@Configuration
@Slf4j
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
