import java.util.HashMap;
import java.util.Map;

public class InsecureIoTDevice {
    private static Map<String, String> deviceSettings = new HashMap<>();

    public static void main(String[] args) {
        // 初始化设备设置
        deviceSettings.put("setting1", "value1");
        deviceSettings.put("setting2", "value2");

        // 演示未授权访问漏洞
        String setting = getDeviceSetting("setting1");
        System.out.println("Device setting: " + setting);
    }

    // 存在未授权访问漏洞的方法
    public static String getDeviceSetting(String key) {
        return deviceSettings.get(key);
    }
}
