import java.util.HashMap;
import java.util.Map;

public class SecureCloudStorage {
    private static Map<String, String> cloudData = new HashMap<>();
    private static Map<String, String> userTokens = new HashMap<>();

    public static void main(String[] args) {
        // 初始化云存储数据
        cloudData.put("file1", "This is file 1 content");
        cloudData.put("file2", "This is file 2 content");

        // 模拟用户登录并获取访问令牌
        String userToken = login("user1", "password1");

        // 演示安全访问
        String fileContent = getFileContent("file1", userToken);
        if (fileContent != null) {
            System.out.println("File content: " + fileContent);
        } else {
            System.out.println("Unauthorized access attempt detected.");
        }
    }

    // 添加认证和授权机制的方法
    public static String getFileContent(String fileName, String token) {
        if (authenticate(token)) {
            return cloudData.get(fileName);
        } else {
            return null;
        }
    }

    // 简单的认证方法
    private static boolean authenticate(String token) {
        return userTokens.containsKey(token);
    }

    // 模拟用户登录方法
    private static String login(String username, String password) {
        // 简单示例，实际应用中应使用更安全的方式验证用户身份
        if ("user1".equals(username) && "password1".equals(password)) {
            String token = generateToken();
            userTokens.put(token, username);
            return token;
        } else {
            return null;
        }
    }

    // 生成访问令牌的方法
    private static String generateToken() {
        // 简单示例，实际应用中应使用更安全的方式生成令牌
        return "secureToken";
    }
}
