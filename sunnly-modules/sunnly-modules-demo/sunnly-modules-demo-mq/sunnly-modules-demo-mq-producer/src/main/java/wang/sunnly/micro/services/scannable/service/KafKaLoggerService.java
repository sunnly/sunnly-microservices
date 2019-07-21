package wang.sunnly.micro.services.scannable.service;

/**
 * RabbitLoggerService
 *
 * @author Sunnly
 * @create 2019/7/17 12:04
 */
public interface KafKaLoggerService {

    boolean debug(String message);

    boolean error(String message);

    boolean test(String message);
}
