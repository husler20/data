import java.util.ArrayList;
import java.util.List;

public class InsecureApplication {
    private static List<Object> dataList = new ArrayList<>();

    public static void main(String[] args) {
        for (int i = 0; i < 1000000; i++) {
            // 模拟对象的创建和添加
            dataList.add(new Object());
        }
        
        // 模拟程序的其他操作
        performOtherOperations();
    }

    private static void performOtherOperations() {
        // 其他操作代码
    }
}
