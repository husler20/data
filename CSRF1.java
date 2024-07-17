import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CSRFVulnerableServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("transfer".equals(action)) {
            String amount = request.getParameter("amount");
            // 处理转账逻辑
            response.getWriter().println("Transferred " + amount + " dollars.");
        }
    }
}
