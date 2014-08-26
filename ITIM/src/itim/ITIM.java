
package itim;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.ldap.LdapName;

public class ITIM {

public static void main(String argv[]) throws NamingException {

 Hashtable env=new Hashtable();
 env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
 env.put(Context.PROVIDER_URL, "ldap://192.168.122.93:389");
 env.put(Context.SECURITY_AUTHENTICATION, "simple");
 env.put(Context.SECURITY_PRINCIPAL, "cn=root");
 env.put(Context.SECURITY_CREDENTIALS, "password");
 
 DirContext ctx=new InitialDirContext(env);
 
 String base = "ou=systemUser,ou=itim,ou=org,dc=com";

    LdapName dn = new LdapName(base);

    LdapName uid = new LdapName("eruid=newuser");
    Name tocreate=dn.addAll(uid); 
    
    Attributes attributes=new BasicAttributes();
    
    BasicAttribute oc = new BasicAttribute("objectClass", "top");
    oc.add("erAccountItem");
    oc.add("erManagedItem");
    oc.add("erSystemUser");
    attributes.put(oc);
 
    attributes.put(new BasicAttribute("erchangepswdrequired","false"));
    attributes.put(new BasicAttribute("erservice","erglobalid=00000000000000000002,ou=services,erglobalid=00000000000000000000,ou=org,dc=com"));
    attributes.put(new BasicAttribute("eraccountstatus","0"));
    attributes.put(new BasicAttribute("owner","erglobalid=9999999999101287536,ou=0,ou=people,erglobalid=00000000000000000000,ou=org,dc=com"));
 ctx.createSubcontext(tocreate, attributes);
    
 ctx.close();

 }
}