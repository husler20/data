import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/execute")
public class CommandInjectionServlet extends HttpServlet {
    private static final Map<String, String> ALLOWED_COMMANDS = new HashMap<>();

    static {
        ALLOWED_COMMANDS.put("date", "date");
        ALLOWED_COMMANDS.put("uptime", "uptime");
        ALLOWED_COMMANDS.put("whoami", "whoami");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandKey = request.getParameter("command");

        if (ALLOWED_COMMANDS.containsKey(commandKey)) {
            String command = ALLOWED_COMMANDS.get(commandKey);

            try {
                Process process = Runtime.getRuntime().exec(command);
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                StringBuilder output = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }
                response.getWriter().println(output.toString());
            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error executing command");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid command");
        }
    }
}
