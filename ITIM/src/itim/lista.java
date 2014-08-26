package itim;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.*;

public class lista {
    private static String uid;
    private static String name;
    private static String gname;
    private static String mel;
    private static String acce;

  public static void main(String[] args) throws Exception {
    Hashtable env = new Hashtable();

    env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
    env.put(Context.PROVIDER_URL, "ldap://10.70.7.69");
    env.put(Context.SECURITY_AUTHENTICATION, "simple");
    env.put(Context.SECURITY_PRINCIPAL, "cn=root");
    env.put(Context.SECURITY_CREDENTIALS, "secret");

    DirContext dctx = new InitialDirContext(env);

    String base = "ou=0,ou=accounts,erglobalid=00000000000000000000,ou=Sapa,o=Sapa";

    SearchControls sc = new SearchControls();
    String[] attributeFilter = { "eruid", "mail","sn","givenname" };
    sc.setReturningAttributes(attributeFilter);
    sc.setSearchScope(SearchControls.SUBTREE_SCOPE);

    String filter = "(&(eruid=*)(mail=*@sapa*)(eradcontainer=ou=users*,*)(eraccountstatus=0))";

    NamingEnumeration results = dctx.search(base, filter, sc);
    while (results.hasMore()) {
      SearchResult sr = (SearchResult) results.next();
      Attributes attrs = sr.getAttributes();

	String[] id = attrs.get("eruid").toString().split("[: ]");
	uid = id[2];

            if (attrs.get("sn")==null){
                name="No name";
                }
                else {
                  String[] sna = attrs.get("sn").toString().split("[: ]");
                  name = sna[2];
                }

            if (attrs.get("givenname")==null){
                gname="No givenname";
                }
                else {
                  String[] gna = attrs.get("givenname").toString().split("[: ]");
                  gname = gna[2];
                }
                
            String basea = "ou=systemUser,ou=itim,ou=Sapa,o=Sapa";

            SearchControls sca = new SearchControls();
            String[] attributeFiltera = { "erlastaccessdate" };
            sca.setReturningAttributes(attributeFiltera);
            sca.setSearchScope(SearchControls.SUBTREE_SCOPE);
            
            String erid="eruid="+uid;
            System.out.println(erid);
            
            NamingEnumeration resultsa = dctx.search(basea, erid, sca);
            while (resultsa.hasMore()) {
              SearchResult sra = (SearchResult) results.next();
              Attributes ldapattrsa = sra.getAttributes();

            if (ldapattrsa.get("erlastaccessdate")==null){
                acce="Never accessed";
                }
                else {
                  String[] acc = ldapattrsa.get("erlastaccessdate").toString().split("[: ]");
                  acce = acc[2].substring(1, 4);
                }

	String[] mal = attrs.get("mail").toString().split("[: ]");
	mel = mal[2];

      System.out.println(uid + ";" + name + " " + gname + ";" + mel + ";" + acce );
    }
    dctx.close();
  }
}
}