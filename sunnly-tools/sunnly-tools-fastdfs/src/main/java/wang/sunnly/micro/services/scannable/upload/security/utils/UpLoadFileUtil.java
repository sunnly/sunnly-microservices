//package wang.sunnly.micro.services.scannable.upload.security.utils;
//
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.IOException;
//
///**
// * UpLoadFileUtil
// *
// * @author Sunnly
// * @create 2019/7/21 0021 20:57
// */
//public class UpLoadFileUtil {
//
//    /**
//     *
//     * @param file      上传源文件
//     * @param filePath  保存路径
//     * @param fileName  保存文件名
//     * @param type      密文类型
//     */
//    public static void saveAndEditFile(MultipartFile file, String filePath, String fileName, Integer type) {
//
//        // 公共密钥
//        String cKey = "123";
//        // 开始加密文件
//
//        // 临时解密文件
//        File tempFile = new File(filePath + "_" + fileName);
//        // 再加密的目标文件
//        File goalFile = new File(filePath + fileName);
//
//        if (!tempFile.getParentFile().exists()) {
//            tempFile.getParentFile().mkdirs();
//        }
//        if (tempFile.isFile() && tempFile.exists()) {
//            tempFile.delete();
//        }
//        if (type == AESTypeEnum.ENCRYPT.getCode()) {
//            // 文件加密
//            tempFile = AESUtil.encryptFile(file, tempFile, cKey);
//        }else if (type == AESTypeEnum.DECRYPT.getCode()) {
//            // 文件解密
//            tempFile = AESUtil.decryptFile(file, tempFile, cKey);
//
//            // 使用密钥2加密
//            String sKey2 = "456";
//            goalFile = AESUtil.encryptFile(tempFile, goalFile, sKey2);
//            if (tempFile.exists()) tempFile.delete();
//
//        }else if (type == AESTypeEnum.NONE.getCode()) {
//            try {
//                file.transferTo(tempFile);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//    }
//
//
//}
