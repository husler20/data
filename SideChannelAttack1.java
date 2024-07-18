public class InsecureComparison {
    public static void main(String[] args) {
        String userInput = "userInput"; // 模拟用户输入
        String correctPassword = "correctPassword";

        if (isPasswordCorrect(userInput, correctPassword)) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Login failed!");
        }
    }

    private static boolean isPasswordCorrect(String userInput, String correctPassword) {
        // 存在时间侧信道攻击漏洞的字符串比较
        return userInput.equals(correctPassword);
    }
}
