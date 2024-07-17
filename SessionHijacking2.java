import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 简单的用户名和密码检查
        if ("admin".equals(username) && "admin123".equals(password)) {
            HttpSession oldSession = request.getSession(false);
            if (oldSession != null) {
                oldSession.invalidate(); // 使旧会话失效
            }
            HttpSession newSession = request.getSession(true); // 创建新会话
            newSession.setAttribute("user", username);
            newSession.setMaxInactiveInterval(15 * 60); // 设置会话超时时间为15分钟

            // 设置HttpOnly和Secure标志
            response.setHeader("Set-Cookie", "JSESSIONID=" + newSession.getId() + "; HttpOnly; Secure");

            response.sendRedirect("welcome");
        } else {
            response.sendRedirect("login.html");
        }
    }
}

@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (session != null && session.getAttribute("user") != null) {
            out.println("Welcome, " + session.getAttribute("user"));
        } else {
            response.sendRedirect("login.html");
        }
    }
}
