public class ArrayOverflowExample {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java ArrayOverflowExample <input>");
            return;
        }

        String input = args[0];
        char[] buffer = new char[10];

        for (int i = 0; i < input.length() && i < buffer.length; i++) {
            buffer[i] = input.charAt(i); // 进行边界检查，防止数组越界
        }

        System.out.println("Buffer content: " + new String(buffer));
    }
}
