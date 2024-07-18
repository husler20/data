import java.util.HashMap;
import java.util.Map;

import spark.Request;
import spark.Response;
import spark.Spark;

public class InsecureAPI {
    private static Map<String, String> userData = new HashMap<>();

    public static void main(String[] args) {
        // 初始化用户数据
        userData.put("user1", "This is user 1 data");
        userData.put("user2", "This is user 2 data");

        // 设置API端点
        Spark.get("/user/:username", InsecureAPI::getUserData);
    }

    // 存在未授权访问漏洞的方法
    public static String getUserData(Request req, Response res) {
        String username = req.params(":username");
        return userData.get(username);
    }
}
