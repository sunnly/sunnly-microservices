package wang.sunnly.micro.services.scannable.security.auth.response.client.schedule;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import wang.sunnly.micro.services.scannable.common.web.msg.ObjectRestResponse;
import wang.sunnly.micro.services.scannable.security.auth.core.properties.SecurityAuthClientProperties;
import wang.sunnly.micro.services.scannable.security.auth.response.client.feign.SecurityAuthClientFeign;

import java.util.List;

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

    //保存允许访问的服务
    private List<String> allowedClient;

    @Value("${sunnly.security.oauth.client.signle-client:}")
    private String signleClient;

    //保存微服务token
    private String clientToken;

    @Autowired
    private SecurityAuthClientProperties securityOAuthClientProperties;

    @Autowired
    private SecurityAuthClientFeign securityAuthClientFeign;


    /**
     * 每30s刷新一次允许访问的微服务列表
     */
    @Scheduled(cron = "${sunnly.schedule.allowed-client:0/30 * * * * ?}")
    public void refreshAllowedClient(){
        if (StringUtils.isNotEmpty(signleClient)){
            if (allowedClient!=null && allowedClient.size()>0){
                return;
            }
            this.allowedClient = Lists.newArrayList(signleClient);
            return;
        }
        //从鉴权服务器获取允许访问的微服务
        ObjectRestResponse<List<String>> allowClient = securityAuthClientFeign.getAllowClient(securityOAuthClientProperties.getId(),
                securityOAuthClientProperties.getSecret());
        if (allowClient.getStatus() == HttpStatus.OK.value()){
            this.allowedClient = allowClient.getData();
        }else{
            this.allowedClient = null;
        }

    }

    public List<String> getAllowedClient(){
        if (this.allowedClient == null)
            refreshAllowedClient();
        return this.allowedClient;
    }

    //定时刷新微服务token
   @Scheduled(cron = "${sunnly.schedule.client-token:0 0/10 * * * ?}")
    public void refreshClientToken(){
        ObjectRestResponse<String> accessToken = securityAuthClientFeign.getAccessToken(securityOAuthClientProperties.getId(),
                securityOAuthClientProperties.getSecret());
        if (accessToken.getStatus() == HttpStatus.OK.value()){
            this.clientToken = accessToken.getData();
        }
    }

    public String getClientToken(){
        if (this.clientToken == null){
            //获取token
            refreshClientToken();
        }
        return this.clientToken;

    }
}
