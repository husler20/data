import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username != null && password != null && isPasswordStrong(password)) {
            String hashedPassword = hashPassword(password);
            saveUser(username, hashedPassword);
            response.sendRedirect("login.html");
        } else {
            response.sendRedirect("register.html");
        }
    }

    private boolean isPasswordStrong(String password) {
        return PASSWORD_PATTERN.matcher(password).matches();
    }

    private String hashPassword(String password) {
        // 使用安全的哈希算法对密码进行哈希处理
        // 例如，使用 BCrypt, SCrypt 或 Argon2
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    private void saveUser(String username, String hashedPassword) {
        // 将用户保存到数据库的逻辑
        // 这里保存的是哈希后的密码
    }
}
