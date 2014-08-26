/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ods;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MakeCSVFile {

    public static void main(String[] args) throws IOException {

        FileWriter fw = new FileWriter("/home/gabor/WriteTest.csv");
        PrintWriter pw = new PrintWriter(fw);

        
        pw.println("meg,egy,szo");
        
        pw.println("meg,ket,szo");

        pw.flush();

        pw.close();

        fw.close();        
    }
}
