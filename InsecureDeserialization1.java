import java.io.*;

class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{username='" + username + "', password='" + password + "'}";
    }
}

public class SecureDeserializationExample {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java SecureDeserializationExample <serialized object file>");
            return;
        }

        String filename = args[0];

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            Object obj = readObjectSafely(ois);
            System.out.println("Deserialized object: " + obj);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Object readObjectSafely(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        Object obj = ois.readObject();

        // 检查反序列化的对象是否是允许的类型
        if (obj instanceof User) {
            return obj;
        } else {
            throw new InvalidClassException("Unauthorized deserialization attempt", obj.getClass().getName());
        }
    }
}
