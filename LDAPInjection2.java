import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import java.util.Hashtable;

public class SecureLDAPInjectionExample {
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
        Hashtable<String, String> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://localhost:389");

        DirContext ctx = new InitialDirContext(env);

        // 使用参数化查询来防止 LDAP 注入
        String searchFilter = "(&(uid={0})(userPassword={1}))";
        String[] filterArgs = {escapeLDAPSearchFilter(username), escapeLDAPSearchFilter(password)};
        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);

        NamingEnumeration<SearchResult> results = ctx.search("dc=example,dc=com", searchFilter, filterArgs, searchControls);
        if (results.hasMore()) {
            System.out.println("Authentication successful");
        } else {
            System.out.println("Authentication failed");
        }
        ctx.close();
    }

    private static String escapeLDAPSearchFilter(String filter) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < filter.length(); i++) {
            char curChar = filter.charAt(i);
            switch (curChar) {
                case '\\':
                    sb.append("\\5c");
                    break;
                case '*':
                    sb.append("\\2a");
                    break;
                case '(':
                    sb.append("\\28");
                    break;
                case ')':
                    sb.append("\\29");
                    break;
                case '\u0000':
                    sb.append("\\00");
                    break;
                default:
                    sb.append(curChar);
            }
        }
        return sb.toString();
    }
}
