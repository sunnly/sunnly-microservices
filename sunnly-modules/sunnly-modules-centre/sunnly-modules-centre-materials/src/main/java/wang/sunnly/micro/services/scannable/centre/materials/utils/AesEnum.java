package wang.sunnly.micro.services.scannable.centre.materials.utils;

/**
 * AESEnum
 *
 * @author Sunnly
 * @since 2019/7/23 13:21
 */
public enum AesEnum {
//    加密
    ENCRYPT_MODE(1),
//    解密
    DECRYPT_MODE(2),
    WRAP_MODE(3),
    UNWRAP_MODE(4);

    private int value;

    private AesEnum(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
