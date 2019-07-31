package wang.sunnly.micro.services.scannable.tools.materials.configuration;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.web.servlet.MultipartProperties;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;

/**
 * SunnlyUploadConfig
 *
 * @author Sunnly
 * @since 2019/7/23 10:07
 */
@Configurable
public class SunnlyUploadConfig {

    @Bean
    public MultipartConfigElement multipartConfigElement(MultipartProperties multipartProperties) {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(multipartProperties.getMaxFileSize());
        factory.setMaxRequestSize(multipartProperties.getMaxRequestSize());
        return factory.createMultipartConfig();
    }
}
