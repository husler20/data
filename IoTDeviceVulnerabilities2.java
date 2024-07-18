import java.util.HashMap;
import java.util.Map;

public class SecureIoTDevice {
    private static Map<String, String> deviceSettings = new HashMap<>();
    private static String adminPassword = "securePassword"; // 简单示例，实际应用中应使用更安全的方式保存密码

    public static void main(String[] args) {
        // 初始化设备设置
        deviceSettings.put("setting1", "value1");
        deviceSettings.put("setting2", "value2");

        // 演示安全访问
        String setting = getDeviceSetting("setting1", "securePassword");
        if (setting != null) {
            System.out.println("Device setting: " + setting);
        } else {
            System.out.println("Unauthorized access attempt detected.");
        }
    }

    // 添加认证机制的方法
    public static String getDeviceSetting(String key, String password) {
        if (authenticate(password)) {
            return deviceSettings.get(key);
        } else {
            return null;
        }
    }

    // 简单的认证方法
    private static boolean authenticate(String password) {
        return adminPassword.equals(password);
    }
}
