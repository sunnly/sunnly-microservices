package wang.sunnly.micro.services.scannable.tools.logger.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import wang.sunnly.micro.services.scannable.tools.logger.entity.LoggerEntity;
import wang.sunnly.micro.services.scannable.tools.logger.handler.AbstractInterfaceLoggerHandler;
import wang.sunnly.micro.services.scannable.tools.logger.handler.AbstractStreamLoggerHandler;
import wang.sunnly.micro.services.scannable.tools.logger.properties.LoggerProperties;
import wang.sunnly.micro.services.scannable.tools.logger.service.LoggerService;

import java.util.Map;

/**
 * LoggerServiceImpl
 *
 * @author Sunnly
 * @since 2019/7/24 17:02
 */
public class LoggerServiceImpl implements LoggerService {

    Logger logger = LoggerFactory.getLogger(LoggerServiceImpl.class);

    @Autowired
    private LoggerProperties loggerProperties;

    @Autowired
    @Nullable
    private AbstractInterfaceLoggerHandler interfaceLoggerHandler;

    @Autowired
    @Nullable
    private AbstractStreamLoggerHandler streamLoggerHandler;

    @Override
    public void loggerHandler(LoggerEntity loggerEntity) {

        Map<String, String> pro = loggerProperties.getCatalog().get(loggerEntity.getCata());
        if (pro == null) {
            if (StringUtils.equals("logger", loggerEntity.getCata())) {
                logger.info("【logger.loggerEntity:】{}", loggerEntity);
                return;
            } else {
                //TODO
                throw new RuntimeException("暂时这么处理,后期自定义异常");
            }
        } else {
            switch (pro.get("type")) {
                case "stream":
                    Assert.notNull(streamLoggerHandler, "请配置StreamLoggerHandler的实现类");
                    streamLoggerHandler.handler(loggerEntity.getCata(),loggerEntity);
                    break;
                case "interface":
                    Assert.notNull(streamLoggerHandler, "请配置InterfaceLoggerHandler的实现类");
                    interfaceLoggerHandler.handler(loggerEntity.getCata(),loggerEntity);
                    break;
                default:
                    logger.info("【logger.loggerEntity:】{}", loggerEntity);
                    break;
            }

        }

        //获取日志存储方式sunnly.logger

    }


}
