package xml;

import java.io.File;
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class LdapSearch {
  public static void main(String[] args) throws Exception {
    String sid = "magat";
    Hashtable env = new Hashtable();

    env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");

    env.put(Context.PROVIDER_URL, "ldap://bluepages.ibm.com");
    
    DirContext dctx = new InitialDirContext(env);

    String base = "";

    SearchControls sc = new SearchControls();
    String[] attributeFilter = { "uid", "cn" };
    sc.setReturningAttributes(attributeFilter);
    sc.setSearchScope(SearchControls.SUBTREE_SCOPE);

    String filter = "(&(sn="+ sid +"))";
    //System.out.println(filter);
    NamingEnumeration results = dctx.search(base, filter, sc);
    int c=0;
   
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
    
                Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("bpperson");
		doc.appendChild(rootElement);
  
    while (results.hasMore()) {
      SearchResult sr = (SearchResult) results.next();
      Attributes ldapattrs = sr.getAttributes();
                
                Element obj = doc.createElement(sid);
		rootElement.appendChild(obj);

		Attr attr = doc.createAttribute("id");
		obj.setAttribute("id",""+ c +"");
                
                String uid = (String) ldapattrs.get("uid").get();
                System.out.print(uid);

		Element xuid = doc.createElement("uid");
		xuid.appendChild(doc.createTextNode(uid));
		obj.appendChild(xuid);

                String cn = (String) ldapattrs.get("cn").get();
                System.out.println(cn);

		Element xcn = doc.createElement("cn");
		xcn.appendChild(doc.createTextNode(cn));
		obj.appendChild(xcn);
                c++;
    }
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("/home/gabor/NetBeansProjects/XML/src/xml/"+sid+".xml"));
                transformer.transform(source, result);
    dctx.close();
  }
}
