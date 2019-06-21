package wang.sunnly.micro.services.scannable.security.auth.core.utils.jwt;

import wang.sunnly.micro.services.scannable.security.auth.core.utils.IJWTInfo;

import java.io.Serializable;
import java.util.Objects;

/**
 * 
 * @Author Sunnly
 * @create 2019年6月20日 14:09:20 
 **/
public class JWTInfo implements IJWTInfo, Serializable {

    private String username;
    private String userId;
    private String name;

    public JWTInfo(String username, String userId, String name) {
        this.username = username;
        this.userId = userId;
        this.name = name;
    }

    @Override
    public String getUniqueName() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JWTInfo jwtInfo = (JWTInfo) o;

        return (username != null ? Objects.equals(username,jwtInfo.username) : jwtInfo.username == null)
                && (userId != null ? Objects.equals(userId,jwtInfo.userId) : jwtInfo.userId == null);
    }

    @Override
    public int hashCode() {
        return 31*(username != null ? username.hashCode() : 0)
                + (userId != null ? userId.hashCode() : 0);
    }
}
