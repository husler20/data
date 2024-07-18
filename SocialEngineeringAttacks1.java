import java.util.Scanner;

public class InsecureLogin {
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
        // 模拟的认证逻辑
        return "admin".equals(username) && "password123".equals(password);
    }
}
