package wang.sunnly.micro.services.scannable.tools.verification.core.storage.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.request.ServletWebRequest;
import wang.sunnly.micro.services.scannable.tools.verification.core.storage.AbstractValidateCodeStorage;

import java.util.concurrent.TimeUnit;

/**
 * RedisStorage
 *
 * @author Sunnly
 * @create 2019/9/1 0001 18:22
 */
public class RedisStorageImpl extends AbstractValidateCodeStorage {

    private static final String VALIDATE_KEY = "validate_%s_key";

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Override
    public void save(ServletWebRequest request, String key, String id, String code, int expire) {
        redisTemplate.opsForValue().set(String.format(VALIDATE_KEY, id), code, expire, TimeUnit.SECONDS);
    }

    @Override
    public boolean validate(ServletWebRequest request, String key, String id, String code, boolean clear) {
        boolean flag = StringUtils.equals(
                redisTemplate.opsForValue().get(String.format(VALIDATE_KEY, id)),
                code);
        if (clear) {
            redisTemplate.opsForValue().set(String.format(VALIDATE_KEY, id), "", 0, TimeUnit.SECONDS);
        }
        return flag;
    }
}
