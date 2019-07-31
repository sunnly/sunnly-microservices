package wang.sunnly.micro.services.scannable.tools.logger.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * LoggerEntity
 *
 * @author Sunnly
 * @since 2019/7/24 16:20
 */
@Data
public class LoggerEntity  implements Serializable {
    private String cata;

    private String userId;

    private String username; //用户名

    private String name; //姓名

    private String operation; //操作

    private String method; //方法名

    private String params; //参数

    private String ip; //ip地址

    private Date createDate; //操作时间
}
