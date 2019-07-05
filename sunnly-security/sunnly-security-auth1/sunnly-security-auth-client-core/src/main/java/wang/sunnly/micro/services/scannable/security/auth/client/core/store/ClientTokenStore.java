package wang.sunnly.micro.services.scannable.security.auth.client.core.store;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import wang.sunnly.micro.services.scannable.common.web.msg.ObjectRestResponse;
import wang.sunnly.micro.services.scannable.security.auth.core.feign.SecurityAuthClientFeign;
import wang.sunnly.micro.services.scannable.security.auth.core.properties.SecurityAuthClientProperties;

import java.util.List;

/**
 * ClientTokensStore
 *
 * @author Sunnly
 * @create 2019/6/22 0022 1:28
 */
@Component
@Slf4j
public class ClientTokenStore {

    @Autowired
    private SecurityAuthClientProperties securityOAuthClientProperties;

    @Autowired
    private SecurityAuthClientFeign securityAuthClientFeign;

    //保存允许访问的服务
    private List<String> allowedClient;

    //保存微服务token
    private String clientToken;

    //如果只允许指定微服务访问，则配置该属性
    @Value("${sunnly.security.auth.client.signle-client:}")
    private String signleClient;

    private boolean access = true;



    public void refreshClientToken(){
        log.info("【ClientTokenStore：refreshClientToken】开始刷新服务端token");
        ObjectRestResponse<String> accessToken = securityAuthClientFeign.getAccessToken(securityOAuthClientProperties.getId(),
                securityOAuthClientProperties.getSecret());
        if (accessToken.getStatus() == HttpStatus.OK.value()){
            this.clientToken = accessToken.getData();
        }
        log.info("【ClientTokenStore：refreshClientToken】刷新服务端token结束");

    }

    public void refreshAllowedClient(){
        //这里配置只能由指定的某一个微服务访问，
        // 一般可以只作为一个生产者，还需要些一个指定的消费者来消费
        if (StringUtils.isNotEmpty(signleClient)){
            if (allowedClient!=null && allowedClient.size()>0){
                return;
            }
            this.allowedClient = Lists.newArrayList(signleClient);
            return;
        }
        log.info("【ClientTokenStore：refreshAllowedClient】开始刷新允许访问的微服务");
        //从鉴权服务器获取允许访问的微服务
        ObjectRestResponse<List<String>> allowClient = securityAuthClientFeign.getAllowClient(securityOAuthClientProperties.getId(),
                securityOAuthClientProperties.getSecret());
        if (allowClient.getStatus() == HttpStatus.OK.value()){
            this.allowedClient = allowClient.getData();
        }else{
            this.allowedClient = null;
        }
        log.info("【ClientTokenStore：refreshAllowedClient】刷新允许访问的微服务完成");

    }

    public List<String> getAllowedClient(){
        if (this.allowedClient == null)
            refreshAllowedClient();
        return this.allowedClient;
    }

    public void setAllowedClient(List<String> allowedClient) {
        this.allowedClient = allowedClient;
    }

    public String getClientToken(){
        if (this.clientToken == null){
            refreshClientToken();
        }
        return this.clientToken;
    }

    public void setClientToken(String clientToken) {
        this.clientToken = clientToken;
    }

    public String getSignleClient() {
        return signleClient;
    }

    public void setSignleClient(String signleClient) {
        this.signleClient = signleClient;
    }

    public boolean isAccess() {
        return access;
    }

    public void setAccess(boolean access) {
        this.access = access;
    }
}
