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

public class Xml2DB {

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
    String servername = element.getAttribute("name");
    String url = getValue("erurl", element);
    String mail = getValue("mail", element);
    String key = getValue("erposixpkfile", element);
    String obj = getValue("objectclass", element);
    String usr = getValue("users", element);
    String urf = getValue("orphans", element);
    String cbn = getValue("cbn", element);
    String pcbn = getValue("privcbn", element);
    String err = getValue("errormsg", element);
    String lst = getValue("lastrecon", element);
    
    String sql = "insert into serverdetails values('"+servername+"','"+url+"','"+mail+"','"+key+"','"+obj+"','"+usr+"','"+urf+"','"+cbn+"','"+pcbn+"','"+err+"','"+lst+"')";
    selectdb.write(sql);
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
