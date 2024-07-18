import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class InsecureDatabaseConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USER = "root"; // 默认用户名
    private static final String PASS = "password"; // 默认密码

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            System.out.println("Connection established.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
