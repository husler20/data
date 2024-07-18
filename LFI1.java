import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LFIExample {
    public static void main(String[] args) {
        String filename = args[0]; // 模拟用户输入
        try {
            readFile(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
    }
}
