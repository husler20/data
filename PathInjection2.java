import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@WebServlet("/readFile")
public class PathInjectionServlet extends HttpServlet {
    private static final String BASE_DIRECTORY = "/safe/directory/";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filePath = request.getParameter("filePath");

        // 验证文件路径
        if (filePath == null || filePath.contains("..")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid file path");
            return;
        }

        File file = new File(BASE_DIRECTORY, filePath);

        // 确保文件在指定的安全目录下
        if (!file.getCanonicalPath().startsWith(BASE_DIRECTORY)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            StringBuilder output = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            response.getWriter().println(output.toString());
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error reading file");
        }
    }
}
