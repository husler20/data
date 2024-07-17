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

public class InsecureDeserializationExample {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java InsecureDeserializationExample <serialized object file>");
            return;
        }

        String filename = args[0];

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            Object obj = ois.readObject();
            System.out.println("Deserialized object: " + obj);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
