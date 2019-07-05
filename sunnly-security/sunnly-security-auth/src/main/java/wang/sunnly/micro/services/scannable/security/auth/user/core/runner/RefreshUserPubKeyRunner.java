package wang.sunnly.micro.services.scannable.security.auth.user.core.runner;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import wang.sunnly.micro.services.scannable.common.core.exception.BaseRuntimeException;
import wang.sunnly.micro.services.scannable.security.auth.core.properties.SecurityAuthClientProperties;
import wang.sunnly.micro.services.scannable.security.auth.user.core.schedule.RefreshUserPubKeySchedule;

/**
 * @author Sunnly
 * RefreshUserPubKeyRunner
 * @created 2019/6/16 0016 23:27
 **/
@Configurable
//@Slf4j
@EnableScheduling
public class RefreshUserPubKeyRunner implements CommandLineRunner {
    Logger log = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private RefreshUserPubKeySchedule refreshUserPubKeySchedule;

    @Autowired
    private SecurityAuthClientProperties securityAuthClientProperties;

    @Override
    public void run(String... args) throws Exception {
        try {
            log.info("初始化用户pubKey");
            //检查配置文件是否齐全
            if (StringUtils.isEmpty(securityAuthClientProperties.getId())
                    || StringUtils.isEmpty(securityAuthClientProperties.getSecret())){
                log.error("请配置client id 和 secret");
                throw new BaseRuntimeException("client id 或 secret未配置");
            }
            refreshUserPubKeySchedule.refreshUserPubKey();
        }catch (Exception e){
            log.info("用户PubKey初始化失败");
            e.printStackTrace();
        }


    }

}
