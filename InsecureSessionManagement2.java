import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class SecureLoginServlet extends HttpServlet {
    private static final int SESSION_TIMEOUT = 15 * 60; // 15 分钟

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 简单的用户名和密码验证
        if ("admin".equals(username) && "password".equals(password)) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate(); // 使旧会话无效
            }
            session = request.getSession(true); // 创建新会话
            session.setAttribute("username", username);
            session.setMaxInactiveInterval(SESSION_TIMEOUT); // 设置会话超时

            response.getWriter().println("Login successful!");
        } else {
            response.getWriter().println("Invalid username or password.");
        }
    }
}
