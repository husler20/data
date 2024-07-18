import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class InsecureCORSExample extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置不安全的CORS策略
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        response.setContentType("text/plain");
        response.getWriter().write("Hello, world!");
    }
}
