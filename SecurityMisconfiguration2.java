import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/config")
public class ConfigServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(ConfigServlet.class.getName());

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 安全配置
        response.setHeader("X-Content-Type-Options", "nosniff");
        response.setHeader("X-XSS-Protection", "1; mode=block");
        response.setHeader("X-Frame-Options", "DENY");
        response.setHeader("Content-Security-Policy", "default-src 'self';");

        // 不暴露详细的错误信息和堆栈跟踪
        try {
            // 代码逻辑
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An error occurred", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An internal error occurred. Please try again later.");
        }

        response.getWriter().println("This is a protected configuration page.");
    }
}
