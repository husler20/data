import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class SecureLFIExample {
    private static final List<String> WHITELIST = Arrays.asList("home.txt", "about.txt", "contact.txt");
    private static final String BASE_DIRECTORY = "/path/to/your/base/directory"; // 修改为你的实际目录

    public static void main(String[] args) {
        String filename = args[0]; // 模拟用户输入
        try {
            readFile(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readFile(String filename) throws IOException {
        if (WHITELIST.contains(filename)) {
            Path filePath = Paths.get(BASE_DIRECTORY, filename).normalize();
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }
        } else {
            System.out.println("Error: Unauthorized file access attempt!");
        }
    }
}
