import java.util.Scanner;
import java.util.regex.Pattern;

public class SecureLogin {
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z0-9]{4,12}$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^[a-zA-Z0-9@#\\$%]{8,20}$");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String username = "";
        String password = "";

        while (true) {
            System.out.print("Enter your username: ");
            username = scanner.nextLine();
            if (validateUsername(username)) {
                break;
            } else {
                System.out.println("Invalid username. Please try again.");
            }
        }

        while (true) {
            System.out.print("Enter your password: ");
            password = scanner.nextLine();
            if (validatePassword(password)) {
                break;
            } else {
                System.out.println("Invalid password. Please try again.");
            }
        }

        if (authenticate(username, password)) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Login failed!");
        }

        scanner.close();
    }

    private static boolean validateUsername(String username) {
        return USERNAME_PATTERN.matcher(username).matches();
    }

    private static boolean validatePassword(String password) {
        return PASSWORD_PATTERN.matcher(password).matches();
    }

    private static boolean authenticate(String username, String password) {
        // 模拟的认证逻辑
        return "admin".equals(username) && "password123".equals(password);
    }
}
