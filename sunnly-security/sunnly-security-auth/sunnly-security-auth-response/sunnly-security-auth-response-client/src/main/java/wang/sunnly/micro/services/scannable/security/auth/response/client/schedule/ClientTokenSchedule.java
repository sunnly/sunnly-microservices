package wang.sunnly.micro.services.scannable.security.auth.response.client.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import wang.sunnly.micro.services.scannable.security.auth.core.store.ClientTokenStore;

/**
 *
 *
 * @Author Sunnly
 * @create 2019年6月20日 14:47:23
 **/
@Configuration
@Slf4j
@EnableScheduling
public class ClientTokenSchedule {

    @Autowired
    private ClientTokenStore clientTokenStore;

    /**
     * 每30s刷新一次允许访问的微服务列表
     */
//TODO 暂时关闭，调试完成打开
//    @Scheduled(cron = "${sunnly.schedule.allowed-client:0/30 * * * * ?}")
    public void refreshAllowedClient(){
        clientTokenStore.refreshAllowedClient();
    }

    //定时刷新微服务token
//TODO 暂时关闭，调试完成打开
//   @Scheduled(cron = "${sunnly.schedule.client-token:0 0/10 * * * ?}")
    public void refreshClientToken(){
       if (clientTokenStore.isAccess()){
           clientTokenStore.setAccess(false);
       }
       clientTokenStore.refreshClientToken();
    }

}
