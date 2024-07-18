import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/secure")
public class SecureServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userInput = request.getParameter("input");
        if (userInput != null) {
            userInput = sanitizeInput(userInput);
        }
        response.setHeader("Custom-Header", userInput);
        response.getWriter().println("Response with custom header.");
    }

    private String sanitizeInput(String input) {
        // 移除 CRLF 字符
        return input.replaceAll("[\\r\\n]", "");
    }
}
