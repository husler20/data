import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class CodeInjectionExample {
    public static void main(String[] args) {
        String userInput = "2 + 2"; // 这里模拟用户输入
        try {
            evaluateExpression(userInput);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    public static void evaluateExpression(String expression) throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        Object result = engine.eval(expression);
        System.out.println("Result: " + result);
    }
}
