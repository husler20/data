import java.util.Scanner;

public class InsecureLoginWithBackdoor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        if (authenticate(username, password)) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Login failed!");
        }

        scanner.close();
    }

    private static boolean authenticate(String username, String password) {
        // 后门：使用特定用户名和密码可以绕过认证
        if ("admin".equals(username) && "backdoor123".equals(password)) {
            return true;
        }

        // 正常的认证逻辑
        return "user".equals(username) && "password".equals(password);
    }
}
