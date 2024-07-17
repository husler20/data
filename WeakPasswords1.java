import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 简单地将用户名和密码存储在数据库中
        if (username != null && password != null) {
            saveUser(username, password);
            response.sendRedirect("login.html");
        } else {
            response.sendRedirect("register.html");
        }
    }

    private void saveUser(String username, String password) {
        // 将用户保存到数据库的逻辑
        // 注意：这里省略了密码哈希处理，仅作为示例
    }
}
