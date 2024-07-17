import java.util.HashMap;
import java.util.Map;

class Authenticator {
    private Map<String, String> users = new HashMap<>();

    public Authenticator() {
        // 初始化一些用户
        users.put("admin", "admin123");
        users.put("user", "user123");
    }

    public boolean authenticate(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }
}

public class AuthenticationBypassExample {
    public static void main(String[] args) {
        Authenticator authenticator = new Authenticator();
        
        if (args.length < 2) {
            System.out.println("Usage: java AuthenticationBypassExample <username> <password>");
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
