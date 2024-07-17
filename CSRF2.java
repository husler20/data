import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

public class CSRFSecureServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String csrfToken = (String) session.getAttribute("csrfToken");
        if (csrfToken == null) {
            csrfToken = UUID.randomUUID().toString();
            session.setAttribute("csrfToken", csrfToken);
        }

        response.setContentType("text/html");
        response.getWriter().println("<html><body>");
        response.getWriter().println("<form method='POST' action=''>");
        response.getWriter().println("Amount: <input type='text' name='amount' />");
        response.getWriter().println("<input type='hidden' name='csrfToken' value='" + csrfToken + "' />");
        response.getWriter().println("<input type='submit' value='Transfer' />");
        response.getWriter().println("</form>");
        response.getWriter().println("</body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String csrfToken = (String) session.getAttribute("csrfToken");
        String requestCsrfToken = request.getParameter("csrfToken");

        if (csrfToken != null && csrfToken.equals(requestCsrfToken)) {
            String action = request.getParameter("action");
            if ("transfer".equals(action)) {
                String amount = request.getParameter("amount");
                // 处理转账逻辑
                response.getWriter().println("Transferred " + amount + " dollars.");
            }
        } else {
            response.getWriter().println("Invalid CSRF token.");
        }
    }
}
