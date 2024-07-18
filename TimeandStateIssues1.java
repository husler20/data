public class InsecureCounter {
    private int counter = 0;

    public void increment() {
        counter++;
    }

    public int getCounter() {
        return counter;
    }

    public static void main(String[] args) {
        InsecureCounter counter = new InsecureCounter();
        
        // 创建多个线程同时访问和修改counter
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter.increment();
                }
            }).start();
        }

        // 等待所有线程完成
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 可能得到不一致的结果
        System.out.println("Final counter value: " + counter.getCounter());
    }
}
