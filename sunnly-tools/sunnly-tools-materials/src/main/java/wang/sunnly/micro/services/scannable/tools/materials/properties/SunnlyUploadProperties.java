package wang.sunnly.micro.services.scannable.tools.materials.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * SunnlyUploadProperties
 *
 * @author Sunnly
 * @since 2019/7/23 9:46
 */
@ConfigurationProperties("sunnly.servlet.upload")
@Data
public class SunnlyUploadProperties {

    //    MultipartProperties multiparts = new MultipartProperties();
    Map<String, SunnlySettingProperties> settings = new HashMap<>();

}
