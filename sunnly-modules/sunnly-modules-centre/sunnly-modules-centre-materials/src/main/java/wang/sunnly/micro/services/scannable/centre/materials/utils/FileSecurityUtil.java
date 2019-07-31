package wang.sunnly.micro.services.scannable.centre.materials.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * FileSecurityUtil
 *
 * @author Sunnly
 * @since 2019/7/23 13:02
 */
public class FileSecurityUtil {

    public static InputStream encryptFile(MultipartFile sourceFile, String secretKey) throws IOException {
        return securityFile(sourceFile, secretKey, AesEnum.ENCRYPT_MODE);
    }

    public static InputStream encryptFile(InputStream sourceFile, String secretKey) {
        return securityFile(sourceFile, secretKey, AesEnum.ENCRYPT_MODE);
    }

    public static InputStream decryptFile(MultipartFile sourceFile, String secretKey) throws IOException {
        return securityFile(sourceFile, secretKey, AesEnum.DECRYPT_MODE);
    }

    public static InputStream decryptFile(InputStream sourceFile, String secretKey) {
        return securityFile(sourceFile, secretKey, AesEnum.DECRYPT_MODE);
    }

    public static InputStream securityFile(MultipartFile sourceFile, String secretKey, AesEnum cipherMode) throws IOException {
        InputStream inputStream = sourceFile.getInputStream();
        return securityFile(inputStream, secretKey, cipherMode);
    }

    public static InputStream securityFile(InputStream inputStream, String secretKey, AesEnum cipherMode) {
        Cipher cipher = AesUtil.initAesCipher(secretKey, cipherMode);
        CipherInputStream cipherInputStream = new CipherInputStream(inputStream, cipher);
        return cipherInputStream;
    }
}
