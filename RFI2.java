import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class SecureRFIExample {
    private static final List<String> WHITELIST = Arrays.asList("home.txt", "about.txt", "contact.txt");

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
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } else {
            System.out.println("Error: Unauthorized file access attempt!");
        }
    }
}
