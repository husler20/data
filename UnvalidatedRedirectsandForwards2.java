import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/redirect")
public class SafeRedirectServlet extends HttpServlet {
    private static final List<String> ALLOWED_URLS = Arrays.asList(
            "https://www.example.com/home",
            "https://www.example.com/profile",
            "https://www.example.com/settings"
    );

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String targetUrl = request.getParameter("url");
        if (targetUrl != null && isAllowedUrl(targetUrl)) {
            response.sendRedirect(targetUrl);
        } else {
            response.getWriter().println("Invalid or no URL provided.");
        }
    }

    private boolean isAllowedUrl(String url) {
        return ALLOWED_URLS.contains(url);
    }
}
