import org.apache.commons.collections4.map.HashedMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Map;

@WebServlet("/vulnerable")
public class VulnerableServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (ObjectInputStream ois = new ObjectInputStream(request.getInputStream())) {
            Map<String, String> data = (Map<String, String>) ois.readObject();
            response.getWriter().println("Received data: " + data);
        } catch (ClassNotFoundException e) {
            e.printStackTrace(response.getWriter());
        }
    }
}
