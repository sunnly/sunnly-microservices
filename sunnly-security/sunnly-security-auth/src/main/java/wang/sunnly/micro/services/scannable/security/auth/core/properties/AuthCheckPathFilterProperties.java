package wang.sunnly.micro.services.scannable.security.auth.core.properties;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * AuthReqPathFilterProperties
 *
 * @author Sunnly
 * @create 2019/6/21 11:21
 */
public class AuthCheckPathFilterProperties {
    private List<String> intercept = Lists.newArrayList();
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
