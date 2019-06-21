package wang.sunnly.micro.services.scannable.security.auth.request.client.schedule;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableScheduling;
import wang.sunnly.micro.services.scannable.common.web.msg.ObjectRestResponse;
import wang.sunnly.micro.services.scannable.security.auth.core.properties.SecurityAuthClientProperties;
import wang.sunnly.micro.services.scannable.security.auth.request.client.feign.SecurityAuthClientFeign;

import java.util.List;

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

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SecurityAuthClientProperties securityAuthClientProperties;

    //保存允许访问的服务
    private List<String> allowedClient;

    //保存微服务token
    private String clientToken;

    @Autowired
    private SecurityAuthClientFeign securityAuthClientFeign;

    /**
     * 每30s刷新一次允许访问的微服务列表
     */
// TODO   @Scheduled(cron = "0/30 * * * * ?")
    public void refreshAllowedClient(){
        logger.info("【SecurityAuthClientSchedule：refreshAllowedClient】：开始获取允许访问的微服务");
        //从鉴权服务器获取允许访问的微服务
        ObjectRestResponse<List<String>> allowClient = securityAuthClientFeign.getAllowClient(securityAuthClientProperties.getId(),
                securityAuthClientProperties.getSecret());
        if (allowClient.getStatus() == HttpStatus.OK.value()){
            this.allowedClient = allowClient.getData();
        }else{
            this.allowedClient = null;
        }
        logger.info("【SecurityAuthClientSchedule：refreshAllowedClient】：获取允许访问的微服务结束");
    }

    public List<String> getAllowedClient(){
        if (this.allowedClient == null)
            refreshAllowedClient();
        return this.allowedClient;
    }

    //定时刷新微服务token
//  TODO   @Scheduled(cron = "0 0/10 * * * ?")
    public void refreshClientToken(){
        logger.info("【SecurityAuthClientSchedule：refreshClientToken】：开始刷新微服务Token");
        ObjectRestResponse<String> accessToken = securityAuthClientFeign.getAccessToken(securityAuthClientProperties.getId(),
                securityAuthClientProperties.getSecret());
        if (accessToken.getStatus() == HttpStatus.OK.value()){
            this.clientToken = accessToken.getData();
        }
        logger.info("【SecurityAuthClientSchedule：refreshClientToken】：刷新微服务Token结束");
    }

    public String getClientToken(){
        if (this.clientToken == null){
            refreshClientToken();
        }
        return this.clientToken;

    }

}
