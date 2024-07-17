import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/secure")
public class SecureServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 假设我们改用 JSON 作为数据传输格式，避免使用反序列化
        String jsonData = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
        
        // 使用安全的 JSON 库来解析数据
        Map<String, String> data = parseJsonToMap(jsonData);
        
        response.getWriter().println("Received data: " + data);
    }
    
    private Map<String, String> parseJsonToMap(String jsonData) {
        // 使用安全的 JSON 解析库，例如 Jackson 或 Gson
        // 这里假设使用 Jackson 库进行解析
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(jsonData, new TypeReference<Map<String, String>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }
}
