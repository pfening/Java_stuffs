
package itim;

import java.util.*;
import javax.naming.*;
import javax.naming.directory.*;

public class chLdapDetails {

 public static void main(String argv[]) throws NamingException {

 Hashtable env=new Hashtable();
 env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
 env.put(Context.PROVIDER_URL, "ldap://192.168.122.92:389");
 env.put(Context.SECURITY_AUTHENTICATION, "simple");
 env.put(Context.SECURITY_PRINCIPAL, "cn=root");
 env.put(Context.SECURITY_CREDENTIALS, "password");
 
 DirContext ctx=new InitialDirContext(env);
 
 String base = "ou=systemUser,ou=itim,ou=org,dc=com";
 
 ModificationItem[] mods = new ModificationItem[1];
 mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("erpassword","SHA-256:aGs2cnhkOXVuZ2k4:Fz3CIed5FlpBg4Gzv2PKZ/JCJaoFJl1EVLGkLjV6PJk="));
 ctx.modifyAttributes("eruid=pfg," + base + "", mods);
 ctx.close();


 }
}