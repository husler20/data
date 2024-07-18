import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SecureLogger {
    public void log(String message) throws IOException {
        try (FileWriter fw = new FileWriter("application.log", true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(sanitizeInput(message));
        }
    }

    private String sanitizeInput(String input) {
        // 移除 CRLF 字符
        return input.replaceAll("[\\r\\n]", "");
    }

    public static void main(String[] args) throws IOException {
        SecureLogger logger = new SecureLogger();
        String userInput = "User input message"; // 这里模拟用户输入
        logger.log(userInput);
    }
}
