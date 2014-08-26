/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package recon;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author gabor
 */
public class csvread {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        String strFile = "/home/gabor/ITIM4U/test.csv";

BufferedReader br = new BufferedReader( new FileReader(strFile));
String strLine = "";
while( (strLine = br.readLine()) != null)			
{
String[] st = strLine.split(";");
System.out.println("Servicename: " + st[0] );
String service = st[0];
}
    }
}
