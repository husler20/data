import java.util.Scanner;

public class SecureLogin {
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
        // 移除后门，确保只有合法的用户名和密码可以通过认证
        return "user".equals(username) && "password".equals(password);
    }
}
