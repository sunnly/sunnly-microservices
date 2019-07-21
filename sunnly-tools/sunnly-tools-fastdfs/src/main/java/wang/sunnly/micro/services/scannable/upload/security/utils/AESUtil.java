package wang.sunnly.micro.services.scannable.upload.security.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * AESUtil
 *
 * @author Sunnly
 * @create 2019/7/20 0020 16:53
 */
public class AESUtil {

    private static Cipher initAESCipher(String sKey, int cipherMode){
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
            cipher.init(cipherMode, keySpec);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
        return cipher;
    }

    private static OutputStream securityFile(MultipartFile sourceFile,OutputStream outputStream, String skey, int type){
        InputStream inputStream = null;
        try {
            inputStream = sourceFile.getInputStream();
            Cipher cipher = initAESCipher(skey, type);
            CipherInputStream cipherInputStream = new CipherInputStream(inputStream, cipher);
            IOUtils.copy(cipherInputStream, outputStream);
            IOUtils.readFully(cipherInputStream, new byte[1024]);
            return outputStream;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static OutputStream encodeFile(MultipartFile sourceFile,OutputStream outputStream, String skey){
        return securityFile(sourceFile, outputStream, skey, Cipher.ENCRYPT_MODE);
    }

    public static OutputStream decodeFile(MultipartFile sourceFile,OutputStream outputStream, String skey){
        return securityFile(sourceFile, outputStream, skey, Cipher.DECRYPT_MODE);
    }

    public static File encodeFile(MultipartFile sourceFile, File oFile, String skey){
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(oFile);
            outputStream = securityFile(sourceFile, outputStream, skey, Cipher.ENCRYPT_MODE);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

//    /**
//     * 文件格式化
//     * @param sourceFile1 上传文件
//     * @param sourceFile2 本地文件
//     * @param encrypFile  加密后的文件
//     * @param sKey        密钥
//     * @param type        加密类型
//     * @return
//     */
//    private static File enOrDeFile(MultipartFile sourceFile1, File sourceFile2, File encrypFile, String sKey, int type) {
//        InputStream inputStream = null;
//        OutputStream outputStream = null;
//
//        try {
//            if (sourceFile1 != null) inputStream = sourceFile1.getInputStream();
//            if (sourceFile2 != null) inputStream = new FileInputStream(sourceFile2);
//            outputStream = new FileOutputStream(encrypFile);
//            Cipher cipher = initAESCipher(sKey, type);
//            CipherInputStream cipherInputStream = null;
//            CipherOutputStream cipherOutputStream = null;
//            if (Cipher.ENCRYPT_MODE == type) {
//                // 创建加密流
//                cipherInputStream = new CipherInputStream(inputStream, cipher);
//            }else if (Cipher.DECRYPT_MODE == type) {
//                // 创建解密流
//                cipherOutputStream = new CipherOutputStream(outputStream, cipher);
//            }
//
//            byte [] cache = new byte[1024];
//            int isread = 0;
//            if (Cipher.ENCRYPT_MODE == type) {
//                // 加密流写入文件
//                while ((isread = cipherInputStream.read(cache, 0, cache.length)) != -1) {
//                    outputStream.write(cache, 0, isread);
//                }
//
//            }else if (Cipher.DECRYPT_MODE == type) {
//                // 解密流开始写入文件
//                while ((isread = inputStream.read(cache, 0, cache.length)) != -1) {
//                    cipherOutputStream.write(cache, 0, isread);
//                }
//            }
//
//            if (cipherInputStream != null) cipherInputStream.close();
//            if (cipherOutputStream != null) cipherOutputStream.close();
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (inputStream != null) inputStream.close();
//                if (outputStream != null) outputStream.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return encrypFile;
//    }

//
//    /**
//     * 文件解密
//     * @param sourceFile http通讯文件
//     * @param encrypFile 目标文件
//     * @param sKey  密钥
//     * @return
//     */
//    public static File decryptFile(MultipartFile sourceFile, File encrypFile, String sKey) {
//        File file = enOrDeFile(sourceFile, null, encrypFile,sKey, Cipher.DECRYPT_MODE);
//        return file;
//    }
//
//    /**
//     * 加密文件
//     * @param sourceFile 源文件
//     * @param encrypFile 目标文件
//     * @param sKey  密钥
//     * @return
//     */
//    public static File encryptFile(File sourceFile, File encrypFile, String sKey) {
//        File file = enOrDeFile(null, sourceFile, encrypFile, sKey, Cipher.ENCRYPT_MODE);
//        return file;
//    }
//    // 文件加密
//    public static File encryptFile(MultipartFile sourceFile, File encrypFile, String sKey) {
//        File file = enOrDeFile(sourceFile, null, encrypFile,sKey, Cipher.ENCRYPT_MODE);
//        return file;
//    }

}
