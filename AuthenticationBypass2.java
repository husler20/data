import java.util.HashMap;
import java.util.Map;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

class Authenticator {
    private Map<String, String> users = new HashMap<>();
    private Map<String, String> salts = new HashMap<>();

    public Authenticator() {
        // 初始化一些用户
        addUser("admin", "admin123");
        addUser("user", "user123");
    }

    private void addUser(String username, String password) {
        String salt = generateSalt();
        salts.put(username, salt);
        users.put(username, hashPassword(password, salt));
    }

    public boolean authenticate(String username, String password) {
        if (!users.containsKey(username)) {
            return false;
        }
        String salt = salts.get(username);
        String hashedPassword = hashPassword(password, salt);
        return users.get(username).equals(hashedPassword);
    }

    private String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    private String hashPassword(String password, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes());
            byte[] hashedPassword = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}

public class SecureAuthenticationExample {
    public static void main(String[] args) {
        Authenticator authenticator = new Authenticator();
        
        if (args.length < 2) {
            System.out.println("Usage: java SecureAuthenticationExample <username> <password>");
            return;
        }

        String username = args[0];
        String password = args[1];

        if (authenticator.authenticate(username, password)) {
            System.out.println("Authentication successful. Access granted.");
        } else {
            System.out.println("Authentication failed. Access denied.");
        }
    }
}
