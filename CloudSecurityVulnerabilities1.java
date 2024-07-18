import java.util.HashMap;
import java.util.Map;

public class InsecureCloudStorage {
    private static Map<String, String> cloudData = new HashMap<>();

    public static void main(String[] args) {
        // 初始化云存储数据
        cloudData.put("file1", "This is file 1 content");
        cloudData.put("file2", "This is file 2 content");

        // 演示未授权访问漏洞
        String fileContent = getFileContent("file1");
        System.out.println("File content: " + fileContent);
    }

    // 存在未授权访问漏洞的方法
    public static String getFileContent(String fileName) {
        return cloudData.get(fileName);
    }
}
