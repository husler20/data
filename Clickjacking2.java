import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/example")
public class ExampleServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置 X-Frame-Options 响应头来防止点击劫持
        response.setHeader("X-Frame-Options", "DENY");
        
        // 或者使用 Content-Security-Policy 响应头
        // response.setHeader("Content-Security-Policy", "frame-ancestors 'none';");

        response.getWriter().println("This is a protected page.");
    }
}
