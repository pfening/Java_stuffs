package ods;

import java.io.File;
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.jopendocument.dom.OOUtils;
import org.jopendocument.dom.spreadsheet.SpreadSheet;


public class LdapSearch {
  public static void main(String[] args) throws Exception {
    String sid = "Mohamad Izatul Reduan Hanapiah";
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
    final Object[][] data = new Object[1000][1000];
    String[] columns = new String[] { "uid", "cn" };
    
    
    while (results.hasMore()) {
      SearchResult sr = (SearchResult) results.next();
      Attributes attrs = sr.getAttributes();
     
      //System.out.println(attrs);

      Object uid = attrs.get("uid").get();
      System.out.print(uid);
      Object cn = attrs.get("cn").get();
      System.out.println(cn);
      data[c] = new Object[] { uid, cn };

      c++;
    }
     TableModel model = new DefaultTableModel(data, columns);  
 
  final File file = new File(sid +".ods");
  SpreadSheet.createEmpty(model).saveAs(file);
    
  OOUtils.open(file);
    
    
    dctx.close();
  }
}
