import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class SecureCodeInjectionExample {
    public static void main(String[] args) {
        String userInput = "2 + 2"; // 这里模拟用户输入
        try {
            evaluateExpression(userInput);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    public static void evaluateExpression(String expression) throws ScriptException {
        if (isValidExpression(expression)) {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("JavaScript");
            Object result = engine.eval(expression);
            System.out.println("Result: " + result);
        } else {
            System.out.println("Invalid expression");
        }
    }

    private static boolean isValidExpression(String expression) {
        // 只允许数字、运算符和空格
        Pattern pattern = Pattern.compile("^[0-9+\\-*/()\\s]+$");
        Matcher matcher = pattern.matcher(expression);
        return matcher.matches();
    }
}
