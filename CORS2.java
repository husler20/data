import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class SecureCORSExample extends HttpServlet {
    private static final String ALLOWED_ORIGIN = "https://trusteddomain.com"; // 修改为你信任的域名

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String origin = request.getHeader("Origin");
        
        // 仅允许特定的来源
        if (ALLOWED_ORIGIN.equals(origin)) {
            response.setHeader("Access-Control-Allow-Origin", ALLOWED_ORIGIN);
            response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
            response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        }

        response.setContentType("text/plain");
        response.getWriter().write("Hello, world!");
    }
}
