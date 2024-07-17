import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

@WebServlet("/secureUpload")
@MultipartConfig
public class SecureFileUploadServlet extends HttpServlet {
    private static final String UPLOAD_DIR = "/var/www/uploads"; // 安全的上传目录
    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList("jpg", "jpeg", "png", "gif", "pdf");

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 创建上传目录
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // 保存上传的文件
        for (Part part : request.getParts()) {
            String fileName = getFileName(part);
            if (fileName != null && isAllowedFileType(fileName)) {
                File file = new File(uploadDir, fileName);
                part.write(file.getAbsolutePath());

                // 验证文件内容（例如，检查 MIME 类型）
                if (isValidFileContent(file)) {
                    response.getWriter().println("File uploaded successfully!");
                } else {
                    file.delete();
                    response.getWriter().println("Invalid file content.");
                }
            } else {
                response.getWriter().println("Invalid file type.");
            }
        }
    }

    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        for (String cd : contentDisposition.split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    private boolean isAllowedFileType(String fileName) {
        String fileExtension = getFileExtension(fileName);
        return ALLOWED_EXTENSIONS.contains(fileExtension.toLowerCase());
    }

    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
    }

    private boolean isValidFileContent(File file) throws IOException {
        // 检查文件 MIME 类型
        String mimeType = Files.probeContentType(file.toPath());
        return mimeType != null && mimeType.startsWith("image/"); // 示例：只允许图片文件
    }
}
