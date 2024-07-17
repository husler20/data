import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String username = (String) session.getAttribute("user");
        String fileId = request.getParameter("fileId");

        if (username != null && fileId != null) {
            // 检查用户是否有权限访问该文件
            if (hasPermissionToAccessFile(username, fileId)) {
                File file = new File("/files/" + fileId);
                if (file.exists()) {
                    response.setContentType("application/octet-stream");
                    response.setContentLength((int) file.length());
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");

                    try (FileInputStream in = new FileInputStream(file);
                         OutputStream out = response.getOutputStream()) {
                        byte[] buffer = new byte[4096];
                        int bytesRead;
                        while ((bytesRead = in.read(buffer)) != -1) {
                            out.write(buffer, 0, bytesRead);
                        }
                    }
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
            } else {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to access this file.");
            }
        } else {
            response.sendRedirect("login.html");
        }
    }

    private boolean hasPermissionToAccessFile(String username, String fileId) {
        // 检查用户是否有权限访问该文件的逻辑
        // 比如通过查询数据库来验证
        // return true if the user has permission, otherwise false
        return true; // 这里仅作为示例，实际应有权限检查逻辑
    }
}
