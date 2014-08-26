
package itim;

import java.util.*;
import javax.naming.*;
import javax.naming.directory.*;

public class AddUser {

 public static void main(String argv[]) throws NamingException {

 Hashtable env=new Hashtable();
 env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
 env.put(Context.PROVIDER_URL, "ldap://192.168.122.92:389");
 env.put(Context.SECURITY_AUTHENTICATION, "simple");
 env.put(Context.SECURITY_PRINCIPAL, "cn=root");
 env.put(Context.SECURITY_CREDENTIALS, "password");
 
 DirContext ctx=new InitialDirContext(env);
 
 String base = "eruid=newuser,ou=systemUser,ou=itim,ou=org,dc=com";
 
 
 Attributes attributes=new BasicAttributes(true);
 //attributes.put("title","software engg");

 //ModificationItem[] mods = new ModificationItem[8];
 attributes.put(new BasicAttribute("eruid","newuser"));
 attributes.put(new BasicAttribute("owner","erglobalid=8978657814386635376,ou=0,ou=people,erglobalid=00000000000000000000,ou=org,dc=com"));
 attributes.put(new BasicAttribute("objectclass","top"));
 attributes.put(new BasicAttribute("objectclass","erAccountItem"));
 attributes.put(new BasicAttribute("objectclass","erManagedItem"));
 attributes.put(new BasicAttribute("objectclass","erSystemUser"));
 attributes.put(new BasicAttribute("erservice","erglobalid=00000000000000000002,ou=services,erglobalid=00000000000000000000,ou=org,dc=com"));
 attributes.put(new BasicAttribute("eraccountstatus","0"));
 attributes.put(new BasicAttribute("erchangepswdrequired","false"));
 
 //ctx.createSubcontext("eruid=newuser,ou=systemUser,ou=itim,ou=org,dc=com", mods);
 ctx.createSubcontext(base, null);
 ctx.close();


 }
}