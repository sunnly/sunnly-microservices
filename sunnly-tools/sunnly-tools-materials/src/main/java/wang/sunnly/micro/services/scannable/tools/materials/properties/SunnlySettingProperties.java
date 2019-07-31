package wang.sunnly.micro.services.scannable.tools.materials.properties;

import lombok.Data;

/**
 * SunnlyMultipartProperties
 *
 * @author Sunnly
 * @since 2019/7/23 9:43
 */
@Data
public class SunnlySettingProperties {

    private String name;
    private String baseUrl;
    private boolean security;
    private String secretKey;

}
