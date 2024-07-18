import java.util.List;
import java.util.ArrayList;

import com.example.unsafe.Dependency; // 假设这个依赖库存在供应链攻击风险

public class InsecureApplication {
    public static void main(String[] args) {
        List<String> data = new ArrayList<>();
        data.add("Sample data");

        // 使用存在供应链攻击风险的依赖库
        Dependency.processData(data);
    }
}
