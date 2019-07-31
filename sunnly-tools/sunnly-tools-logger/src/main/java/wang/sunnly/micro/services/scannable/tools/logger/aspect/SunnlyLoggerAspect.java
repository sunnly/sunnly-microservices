package wang.sunnly.micro.services.scannable.tools.logger.aspect;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import wang.sunnly.micro.services.scannable.common.core.handler.BaseThreadLocalHandler;
import wang.sunnly.micro.services.scannable.common.web.utils.IPUtils;
import wang.sunnly.micro.services.scannable.tools.logger.annotation.SunnlyLogger;
import wang.sunnly.micro.services.scannable.tools.logger.entity.LoggerEntity;
import wang.sunnly.micro.services.scannable.tools.logger.service.LoggerService;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * SunnlyLoggerAspect
 *
 * @author Sunnly
 * @since 2019/7/24 16:13
 */
@Aspect
public class SunnlyLoggerAspect {

    @Autowired
    HttpServletRequest request;

    @Autowired
    LoggerService loggerService;

    //定义切点 @Pointcut
    //在注解的位置切入代码
    @Pointcut("@annotation(wang.sunnly.micro.services.scannable.tools.logger.annotation.SunnlyLogger)")
    public void logPoinCut() {
    }

    //切面 配置通知
    @AfterReturning("logPoinCut()")
    public void saveSysLog(JoinPoint joinPoint) {
        //保存日志
        LoggerEntity loggerEntity = new LoggerEntity();

        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();

        //获取操作
        SunnlyLogger sunnlyLogger = method.getAnnotation(SunnlyLogger.class);
        if (sunnlyLogger != null) {
            String value = sunnlyLogger.value();
            loggerEntity.setOperation(value);//保存获取的操作

            String cata = sunnlyLogger.cata();
            loggerEntity.setCata(cata);
        }

        //获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取请求的方法名
        String methodName = method.getName();
        loggerEntity.setMethod(className + "." + methodName);

        //请求的参数
        Object[] args = joinPoint.getArgs();
        //将参数所在的数组转换成json
        String params = JSON.toJSONString(args);
        loggerEntity.setParams(params);

        loggerEntity.setCreateDate(new Date());
        //获取用户名 从本地线程池中获取
        loggerEntity.setUsername(BaseThreadLocalHandler.getUsername());
        loggerEntity.setName(BaseThreadLocalHandler.getName());
        loggerEntity.setUserId(BaseThreadLocalHandler.getUserId());
        //获取用户ip地址
        loggerEntity.setIp(IPUtils.getIpAddr(request));

        loggerService.loggerHandler(loggerEntity);
    }
}
