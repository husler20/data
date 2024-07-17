import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirectoryTraversalExample {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java DirectoryTraversalExample <filename>");
            return;
        }

        String filename = args[0];
        String basePath = "/safe/directory/";

        try {
            // 规范化路径并确保其在basePath目录下
            Path base = Paths.get(basePath).toRealPath();
            Path filePath = base.resolve(filename).normalize().toRealPath();

            if (!filePath.startsWith(base)) {
                throw new SecurityException("Attempt to access unauthorized file: " + filename);
            }

            File file = filePath.toFile();
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
        } catch (IOException | SecurityException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
