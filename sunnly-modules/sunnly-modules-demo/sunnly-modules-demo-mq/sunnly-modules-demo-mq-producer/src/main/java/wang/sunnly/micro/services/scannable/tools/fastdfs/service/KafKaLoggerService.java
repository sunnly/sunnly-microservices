package wang.sunnly.micro.services.scannable.tools.fastdfs.service;

/**
 * RabbitLoggerService
 *
 * @author Sunnly
 * @since 2019/7/17 12:04
 */
public interface KafKaLoggerService {

    boolean debug(String message);

    boolean error(String message);

    boolean test(String message);
}
