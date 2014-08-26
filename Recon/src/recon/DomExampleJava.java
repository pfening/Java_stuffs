
package recon;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomExampleJava {

public static void main(String args[]) {
try {

File stocks = new File("/home/gabor/NetBeansProjects/Recon/src/recon/output.xml");
DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
Document doc = dBuilder.parse(stocks);
doc.getDocumentElement().normalize();

NodeList nodes = doc.getElementsByTagName("service");

for (int i = 0; i < nodes.getLength(); i++) {
Node node = nodes.item(i);

if (node.getNodeType() == Node.ELEMENT_NODE) {
Element element = (Element) node;
System.out.println("Service name: " + element.getAttribute("name"));
System.out.println("url: " + getValue("erurl", element));
System.out.println("mail: " + getValue("mail", element));
System.out.println("erposixpkfile: " + getValue("erposixpkfile", element));
System.out.println("objectclass: " + getValue("objectclass", element));
System.out.println("users: " + getValue("users", element));
System.out.println("orphans: " + getValue("orphans", element));
System.out.println("cbn: " + getValue("cbn", element));
System.out.println("privcbn: " + getValue("privcbn", element));
System.out.println("errormsg: " + getValue("errormsg", element));
System.out.println("lastrecon: " + getValue("lastrecon", element));
System.out.println("");
}
}
} catch (ParserConfigurationException | SAXException | IOException ex) {
}
}

private static String getValue(String tag, Element element) {
NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
Node node = (Node) nodes.item(0);
return node.getNodeValue();
}
}
