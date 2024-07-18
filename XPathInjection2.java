import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathExpressionException;

public class SecureXPathInjectionExample {
    public static void main(String[] args) {
        String username = "user"; // 这里模拟用户输入
        String password = "password"; // 这里模拟用户输入
        try {
            authenticate(username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void authenticate(String username, String password) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse("users.xml");

        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();

        // 使用参数化查询来防止 XPath 注入
        String expression = "/users/user[username=$username and password=$password]";
        XPathExpression xPathExpression = xPath.compile(expression);
        NodeList nodeList = (NodeList) xPathExpression.evaluate(doc, XPathConstants.NODESET, new Object[]{username, password});

        if (nodeList.getLength() > 0) {
            System.out.println("Authentication successful");
        } else {
            System.out.println("Authentication failed");
        }
    }
}
