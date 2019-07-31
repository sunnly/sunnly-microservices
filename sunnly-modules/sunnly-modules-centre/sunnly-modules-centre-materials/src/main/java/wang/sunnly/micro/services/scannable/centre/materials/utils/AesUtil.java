package wang.sunnly.micro.services.scannable.centre.materials.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * AESUtil
 *
 * @author Sunnly
 * @create 2019/7/20 0020 16:53
 */
public class AesUtil {

    protected static Cipher initAesCipher(String sKey, AesEnum cipherMode) {
        // 创建Key gen
        KeyGenerator generator = null;
        Cipher cipher = null;
        try {
            generator = KeyGenerator.getInstance("AES");
            generator.init(128, new SecureRandom(sKey.getBytes()));
            SecretKey secretKey = generator.generateKey();
            byte[] codeFormat = secretKey.getEncoded();
            SecretKeySpec keySpec = new SecretKeySpec(codeFormat, "AES");
            cipher = Cipher.getInstance("AES");
            // 初始化
            cipher.init(cipherMode.value(), keySpec);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
        return cipher;
    }
}
