import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class DirectoryTraversalExample {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java DirectoryTraversalExample <filename>");
            return;
        }

        String filename = args[0];
        String basePath = "/safe/directory/";

        File file = new File(basePath + filename);
        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file)) {
                byte[] data = new byte[(int) file.length()];
                fis.read(data);
                System.out.println("File content: " + new String(data));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("File not found: " + file.getPath());
        }
    }
}
