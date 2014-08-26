/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xml;

import java.io.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import javax.xml.parsers.*;
import javax.xml.transform.*; 
import javax.xml.transform.dom.DOMSource; 
import javax.xml.transform.stream.StreamResult;

public class GetData{
  static public void main(String[] arg) {
  try{
  BufferedReader bf = new BufferedReader(
    new InputStreamReader(System.in));
  System.out.print("Enter XML file name: ");
  String xmlFile = bf.readLine();
  File file = new File(xmlFile);
  if (file.exists()){
  DocumentBuilderFactory factory = 
    DocumentBuilderFactory.newInstance();
  DocumentBuilder builder = factory.newDocumentBuilder();
  Document doc = builder.parse(xmlFile);
//  Create transformer
  Transformer tFormer = 
    TransformerFactory.newInstance().newTransformer();
//  Output text type
  tFormer.setOutputProperty(OutputKeys.METHOD, "text");
//  Write the document to a file
  Source source = new DOMSource(doc);
  Result result = new StreamResult(System.out);
  tFormer.transform(source, result);
  }
  else{
  System.out.println("File not found!");
  }
  }
  catch (IOException | ParserConfigurationException | SAXException | TransformerFactoryConfigurationError | IllegalArgumentException | TransformerException e){
  System.err.println(e);
  System.exit(0);
  }  
  }
}

