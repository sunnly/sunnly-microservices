package wang.sunnly.micro.services.scannable.security.auth.core.properties;

import com.google.common.collect.Lists;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.util.List;

/**
 * AuthCheckPathFilterProperties
 * 配置拦截和放行的路径
 * @author Sunnly
 * @create 2019/6/21 11:21
 */
public class AuthCheckPathFilterProperties {

    @NestedConfigurationProperty
    private List<String> intercept = Lists.newArrayList();

    @NestedConfigurationProperty
    private List<String> exclude = Lists.newArrayList();

    public List<String> getIntercept() {
        return intercept;
    }

    public void setIntercept(List<String> intercept) {
        this.intercept = intercept;
    }

    public List<String> getExclude() {
        return exclude;
    }

    public void setExclude(List<String> exclude) {
        this.exclude = exclude;
    }
}
