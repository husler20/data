import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 假设我们有一个简单的用户认证机制
        if ("admin".equals(username) && "password123".equals(password)) {
            response.getWriter().println("Login successful");
        } else {
            // 简化错误信息，避免泄露敏感信息
            response.getWriter().println("Login failed");
        }
    }
}
