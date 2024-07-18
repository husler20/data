import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class SecureDNSExample {
    private static final Set<String> TRUSTED_IPS = new HashSet<>();

    static {
        // 添加可信的IP地址
        TRUSTED_IPS.add("93.184.216.34"); // 例如：api.trusteddomain.com的IP地址
        // 你可以添加更多的可信IP地址
    }

    public static void main(String[] args) {
        String apiUrl = "https://api.trusteddomain.com/data";
        try {
            URL url = new URL(apiUrl);
            InetAddress[] addresses = InetAddress.getAllByName(url.getHost());

            boolean isTrusted = false;
            for (InetAddress address : addresses) {
                if (TRUSTED_IPS.contains(address.getHostAddress())) {
                    isTrusted = true;
                    break;
                }
            }

            if (!isTrusted) {
                throw new SecurityException("DNS hijacking detected: Untrusted IP address");
            }

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            connection.disconnect();

            System.out.println(content.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
