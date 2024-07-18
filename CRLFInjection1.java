import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {
    public void log(String message) throws IOException {
        try (FileWriter fw = new FileWriter("application.log", true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(message);
        }
    }

    public static void main(String[] args) throws IOException {
        Logger logger = new Logger();
        String userInput = "User input message"; // 这里模拟用户输入
        logger.log(userInput);
    }
}
