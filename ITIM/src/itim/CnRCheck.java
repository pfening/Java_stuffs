package itim;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

public class CnRCheck {
    private static String acce;
    private static String icna;
    private static String icnb;
    private static String mal;

    public static void main(String[] args) throws NamingException, Exception {
 
        AdCheck();
    }
        
          public static void LdapCheck(String sid, String basea) throws NamingException  {

                    Hashtable env = new Hashtable();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.PROVIDER_URL, "ldap://10.70.7.69");
            DirContext dctx = new InitialDirContext(env);

            String base = "ou=systemUser,ou=itim,ou=Sapa,o=Sapa";

            SearchControls sc = new SearchControls();
            String[] attributeFilter = { "eruid", "erlostpasswordanswer","erlastaccessdate","owner" };
            sc.setReturningAttributes(attributeFilter);
            sc.setSearchScope(SearchControls.SUBTREE_SCOPE);

            String filter = "(&(eruid=lengyl))";

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
                  acce = acc[2];
                }

                if (ldapattrs.get("erlostpasswordanswer")!=null){

                    System.out.println(id[2] + " OK " + acce + " " + icna + icnb + " " + mal);
                }
                else {System.out.println(id[2] + " NOT OK " + acce + " " + icna + icnb + " " + mal);
                }

   dctx.close();
  }


          }
        
      public static void AdCheck() throws NamingException  {

            Hashtable env = new Hashtable();

            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");

            env.put(Context.PROVIDER_URL, "ldap://10.70.7.11:389");
            env.put(Context.SECURITY_AUTHENTICATION, "simple");
            env.put(Context.SECURITY_PRINCIPAL, "CN=service_tim,OU=ServiceAccounts,OU=IBM,DC=global,DC=to");
            env.put(Context.SECURITY_CREDENTIALS, "Sapa4ever");

            DirContext dctx = new InitialDirContext(env);

            String base = "OU=Sites,DC=global,DC=to";

            SearchControls sc = new SearchControls();
            String[] attributeFilter = { "distinguishedName" };
            sc.setReturningAttributes(attributeFilter);
            sc.setSearchScope(SearchControls.SUBTREE_SCOPE);

            String filter = "ou=Users";

            NamingEnumeration results = dctx.search(base, filter, sc);
            while (results.hasMore()) {
            SearchResult sr = (SearchResult) results.next();
            Attributes attrs = sr.getAttributes();

            String[] addn=attrs.get("distinguishedName").toString().split("[: ]");
            //System.out.println(addn[2]);

            String basea = addn[2];

            SearchControls sca = new SearchControls();
            String[] attributeFiltera = { "sAMAccountName" };
            sca.setReturningAttributes(attributeFiltera);
            sca.setSearchScope(SearchControls.SUBTREE_SCOPE);

            String filtera = "(&(sAMAccountName=*))";

            NamingEnumeration resultsa = dctx.search(basea, filtera, sca);

            while (resultsa.hasMore()) {
              SearchResult sra = (SearchResult) resultsa.next();
              Attributes adattrsa = sra.getAttributes();

                String[] id = adattrsa.get("sAMAccountName").toString().split("[: ]");
		LdapCheck(id[2],basea);
            }
            
    }
            dctx.close();
   }
}
