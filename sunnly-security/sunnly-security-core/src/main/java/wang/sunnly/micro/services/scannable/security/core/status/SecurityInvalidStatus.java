package wang.sunnly.micro.services.scannable.security.core.status;

public enum SecurityInvalidStatus {

    //服务相关---------------5XXXX-------------------
    //配置类5030X
    CLIENT_TOKEN_HEADER_NOT_CONFIG(50001,"Please configure: sunnly.security.auth.client.token-header"),
    //Token验证失败 5031X
    CLIENT_TOKEN_EMPTY(50310,"Client token is null or empty!"),
    CLIENT_TOKEN_EXPIRED(50311,"Client token expired!"),
    CLIENT_TOKEN_SIGNATURE_ERROR(50312,"Client token signature error!"),

    CLIENT_OR_SECRENT_ERROR(50320,"Client not found or Client secret is error!"),
    CLIENT_FORBIDDEN(50321, "Client is Forbidden!"),

    NETWORK_CONNECTION_ERR(50330,"网络连接错误"),
    //用户鉴权异常
    //配置类5035X
    USER_TOKEN_HEADER_NOT_CONFIG(50350,"Please configure: sunnly.security.auth.user.token-header"),




    //Token不合法 5036X
    TOHER_ERROR(50002,"其他异常"),

    //用户相关-----------------4XXXX--------------------------
    USER_PASS_INVALID_CODE(40001,"用户名或密码错误"),
    USER_VALIDATE_ERR(40002,"验证失败"),
    USER_TOKEN_ERR(40003,"用户名或密码错误"),
    USER_TOKEN_INVALID_ERR(40004,"用户名或密码错误"),

    USER_TOKEN_EMPTY(40310,"jwt token is null or empty!"),
    USER_TOKEN_EXPIRED(40311,"jwt token expired!"),
    USER_TOKEN_SIGNATURE_ERROR(40312,"jwt token signature error!"),
    USER_FORBIDDEN(50321, "jwt is Forbidden!"),




    NEWWORK_ERROR(41001,"网络异常")
            ;

    private int value;
    private String reasonPhrase;

    private SecurityInvalidStatus(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    public int value(){
        return this.value;
    }

    public String reasonPhrase(){
        return this.reasonPhrase;
    }

    public static String reasonPhrase(int code){
        for (SecurityInvalidStatus s : SecurityInvalidStatus.values()){
            if (code == s.value)
                return s.reasonPhrase();
        }
        return null;
    }
}
