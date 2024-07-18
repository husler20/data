import java.util.List;
import java.util.ArrayList;

// 使用经过验证的安全依赖库
import com.example.safe.Dependency;

public class SecureApplication {
    public static void main(String[] args) {
        List<String> data = new ArrayList<>();
        data.add("Sample data");

        // 使用安全的依赖库
        Dependency.processData(data);
    }
}
