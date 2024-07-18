import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class WeakEncryption {
    public static void main(String[] args) throws Exception {
        String password = "mysecretpassword";

        // 使用 DES 加密（不安全）
        KeyGenerator keyGen = KeyGenerator.getInstance("DES");
        SecretKey secretKey = keyGen.generateKey();
        Cipher cipher = Cipher.getInstance("DES");

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
