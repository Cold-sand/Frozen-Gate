package top.coldsand.materiallogin.login;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.codec.digest.DigestUtils;
import java.util.ArrayList;
import java.util.List;

    public class SaltedHash {
    private static String getSalt() { //随机盐生成器

        return RandomStringUtils.randomAlphanumeric(5);
    }

    private static String getMD5(String str) { //MD5加密

        return DigestUtils.md5Hex(str);
    }

    public static List<String> createSaltedHash(String password) {
        List<String> return_list = new ArrayList<String>();

        String salt = getSalt();
        String hash_key = getMD5(password);
        String hash_salt_key = getMD5(hash_key +salt);

        return_list.add(hash_salt_key);
        return_list.add(salt);

        return return_list;
    }

    public static String getSaltedHash(String password, String salt) {
        String hash_key = getMD5(password);
        String hash_salt_key = getMD5(hash_key +salt);

        return hash_salt_key;
    }
}

