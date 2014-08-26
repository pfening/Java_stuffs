
package itim;

import java.util.Hashtable;
import java.util.Random;
import javax.naming.Context;
import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.ldap.LdapName;

public class CreatePeople {

public static void main(String argv[]) throws NamingException {

 String globid=gid();
    
 Hashtable env=new Hashtable();
 env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
 env.put(Context.PROVIDER_URL, "ldap://192.168.122.92:389");
 env.put(Context.SECURITY_AUTHENTICATION, "simple");
 env.put(Context.SECURITY_PRINCIPAL, "cn=root");
 env.put(Context.SECURITY_CREDENTIALS, "password");
 
 DirContext ctx=new InitialDirContext(env);
 
 String base = "ou=0,ou=people,erglobalid=00000000000000000000,ou=org,dc=com";

    LdapName dn = new LdapName(base);

    LdapName uid = new LdapName("erglobalid=" + globid +"" );
    Name tocreate=dn.addAll(uid); 
    
    Attributes attributes=new BasicAttributes();
    
    BasicAttribute oc = new BasicAttribute("objectClass", "top");
    oc.add("inetOrgPerson");
    oc.add("erManagedItem");
    oc.add("organizationalPerson");
    oc.add("person");    
    oc.add("erPersonItem");
        
    attributes.put(oc);
 
    attributes.put(new BasicAttribute("uid", "newuser"));  
    attributes.put(new BasicAttribute("cn", "newuser"));             
    attributes.put(new BasicAttribute("givenname", "newuser"));             
    attributes.put(new BasicAttribute("sn", "newuser"));  
    
    attributes.put(new BasicAttribute("mail","pfening@gmail.com"));
    attributes.put(new BasicAttribute("erparent","erglobalid=00000000000000000000,ou=org,dc=com"));
    attributes.put(new BasicAttribute("erpersonstatus","0"));
     
    ctx.createSubcontext(tocreate, attributes);
    
 ctx.close();

 }

public static String gid() {
    String erGlobalIDPrefix="999999999910";    
    Random randomGenerator = new Random();
    int randomInt = randomGenerator.nextInt(2000000);    
    String erGlobalID=erGlobalIDPrefix + String.format("%07d", randomInt);
    return erGlobalID;
    }

}