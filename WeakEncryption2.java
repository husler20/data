import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class StrongEncryption {
    public static void main(String[] args) throws Exception {
        String password = "mysecretpassword";

        // 使用 AES 加密
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256, new SecureRandom()); // 使用安全的随机数生成器
        SecretKey secretKey = keyGen.generateKey();
        Cipher cipher = Cipher.getInstance("AES");

        // 加密
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(password.getBytes());
        String encryptedPassword = Base64.getEncoder().encodeToString(encryptedBytes);
        System.out.println("Encrypted Password: " + encryptedPassword);

        // 解密
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedPassword));
        String decryptedPassword = new String(decryptedBytes);
        System.out.println("Decrypted Password: " + decryptedPassword);
    }
}
