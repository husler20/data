import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/config")
public class ConfigServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 默认配置，未作任何安全设置
        response.getWriter().println("This is a vulnerable configuration page.");
        
        // 打印详细的错误信息和堆栈跟踪
        try {
            // 代码逻辑
        } catch (Exception e) {
            e.printStackTrace(response.getWriter());
        }
    }
}
