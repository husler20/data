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

        // 假设我们有一个方法可以根据postId删除文章
        if (username != null && postId != null) {
            // 没有检查用户是否有权限删除这篇文章
            deletePost(postId);
            response.sendRedirect("posts");
        } else {
            response.sendRedirect("login.html");
        }
    }

    private void deletePost(String postId) {
        // 删除文章的逻辑
    }
}
