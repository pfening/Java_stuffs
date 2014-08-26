import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.*;

public class Search3 {
    private static String acce;
    private static String icna;
    private static String icnb;
    private static String mal;

  public static void main(String[] args) throws Exception {
      String sid = args[0];
      
        Hashtable env = new Hashtable();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.PROVIDER_URL, "ldap://10.70.7.69");
            DirContext dctx = new InitialDirContext(env);

            String base = "ou=systemUser,ou=itim,ou=Sapa,o=Sapa";

            SearchControls sc = new SearchControls();
            String[] attributeFilter = { "eruid", "erlastaccessdate","owner" };
            sc.setReturningAttributes(attributeFilter);
            sc.setSearchScope(SearchControls.SUBTREE_SCOPE);

            String filter = "(&(eruid="+sid+"))";

            NamingEnumeration results = dctx.search(base, filter, sc);
            while (results.hasMore()) {
              SearchResult sr = (SearchResult) results.next();
              Attributes ldapattrs = sr.getAttributes();

              String[] id = ldapattrs.get("eruid").toString().split("[: ]");
              String[] ow = ldapattrs.get("owner").toString().split("[: ]");
                String gid = ow[2];
                String[] ergid = gid.split("[, ]");

            String baseb = "ou=0,ou=people,erglobalid=00000000000000000000,ou=Sapa,o=Sapa";

            SearchControls scb = new SearchControls();
            String[] attributeFilterb = { "mail", "cn"};
            scb.setReturningAttributes(attributeFilterb);
            scb.setSearchScope(SearchControls.SUBTREE_SCOPE);

            String filterb = ergid[0];

            NamingEnumeration resultsb = dctx.search(baseb, filterb, scb);
            while (resultsb.hasMore()) {
              SearchResult srb = (SearchResult) resultsb.next();
              Attributes ldapattrsb = srb.getAttributes();

                String[] cn = ldapattrsb.get("cn").toString().split("[: ]");
                 icna = cn[2];
                 icnb = cn[3];
                String[] mail = ldapattrsb.get("mail").toString().split("[: ]");
                 mal = mail[2];
           }

            if (ldapattrs.get("erlastaccessdate")==null){
                acce="Never accessed";
                }
                else {
                  String[] acc = ldapattrs.get("erlastaccessdate").toString().split("[: ]");
                  acce = acc[2].substring(1, 4);
                }

                System.out.println(acce + " " + icna + icnb + " " + mal);


   dctx.close();
  }

}
}