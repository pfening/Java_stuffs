
package itim;

import java.io.IOException;
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.*;

public class weeklyReport {
    private static String uid;
    private static String erid; 
    private static String icn;
    private static String mali;
    private static String acce;

  public static void main(String[] args) throws Exception , IOException {
    Hashtable env = new Hashtable();

    env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
    env.put(Context.PROVIDER_URL, "ldap://10.70.7.69");
    env.put(Context.SECURITY_AUTHENTICATION, "simple");
    env.put(Context.SECURITY_PRINCIPAL, "cn=root");
    env.put(Context.SECURITY_CREDENTIALS, "secret");

    DirContext dctx = new InitialDirContext(env);

    String base = "ou=0,ou=accounts,erglobalid=00000000000000000000,ou=Sapa,o=Sapa";

    SearchControls sc = new SearchControls();
    String[] attributeFilter = { "owner","eraddisplayname","mail" };
    sc.setReturningAttributes(attributeFilter);
    sc.setSearchScope(SearchControls.SUBTREE_SCOPE);

    String filter = "(&(eruid=*)(mail=*@sapa*)(eradcontainer=ou=users*,*)(eraccountstatus=0))";

    NamingEnumeration results = dctx.search(base, filter, sc);
    while (results.hasMore()) {
      SearchResult sr = (SearchResult) results.next();
      Attributes attrs = sr.getAttributes();

	String[] id = attrs.get("owner").toString().split("[, ]");
	uid = id[1];
        
        String cn = attrs.get("eraddisplayname").toString().replaceAll("eraddisplayname: ", "");
        if (cn!=null){
        icn = cn;
        }  
        else{
        icn = "no name";
        }
    
	
	String[] mail = attrs.get("mail").toString().split("[: ]");
        mali = mail[2];

	System.out.println(uid + icn + mali);
        ///

	String basea = "ou=systemUser,ou=itim,ou=Sapa,o=Sapa";

            SearchControls sca = new SearchControls();
            String[] attributeFiltera = { "erlostpasswordanswer","erlastaccessdate" };
            sc.setReturningAttributes(attributeFiltera);
            sc.setSearchScope(SearchControls.SUBTREE_SCOPE);

            String filtera = "(&(owner=" + uid + "))";
            System.out.println(filtera);

            NamingEnumeration resultsa = dctx.search(basea, filtera, sca);
            while (resultsa.hasMore()) {
              SearchResult sra = (SearchResult) resultsa.next();
              Attributes ldapattrsa = sra.getAttributes();

            if (ldapattrsa.get("erlastaccessdate")==null){
                acce="Never accessed";
                }
                else {
                  String[] acc = ldapattrsa.get("erlastaccessdate").toString().split("[: ]");
                  acce = acc[2].substring(0, 4);
                }

                if (ldapattrsa.get("erlostpasswordanswer")!=null){

                    System.out.println(id[2] + "; OK ;" + acce + ";" + icn + ";" + mali);
                }
                else {System.out.println(id[2] + "; NOT OK ;" + acce + ";" + icn + ";" + mali);
                }
    }
  }
dctx.close();
}
}
