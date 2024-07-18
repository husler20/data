public class SecureComparison {
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
        // 使用恒定时间比较方法防止时间侧信道攻击
        return constantTimeEquals(userInput, correctPassword);
    }

    private static boolean constantTimeEquals(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }

        int result = 0;
        for (int i = 0; i < a.length(); i++) {
            result |= a.charAt(i) ^ b.charAt(i);
        }

        return result == 0;
    }
}
