import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/deletePost")
public class DeletePostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String username = (String) session.getAttribute("user");
        String postId = request.getParameter("postId");

        if (username != null && postId != null) {
            // 检查用户是否有权限删除这篇文章
            if (hasPermissionToDelete(username, postId)) {
                deletePost(postId);
                response.sendRedirect("posts");
            } else {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to delete this post.");
            }
        } else {
            response.sendRedirect("login.html");
        }
    }

    private boolean hasPermissionToDelete(String username, String postId) {
        // 检查用户是否有权限删除这篇文章的逻辑
        // 比如通过查询数据库来验证
        // return true if the user has permission, otherwise false
        return true; // 这里仅作为示例，实际应有权限检查逻辑
    }

    private void deletePost(String postId) {
        // 删除文章的逻辑
    }
}
