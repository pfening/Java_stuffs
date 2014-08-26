package tox;

import java.io.*;

public class ReadWrite{

	public static void Write(File file, String input){

        try {
        FileWriter outFile = new FileWriter(file);
        try (PrintWriter out = new PrintWriter(outFile)) {
        out.println(input);
        }
        } catch (IOException e){
        }
	}
        
        public static String Read(File file) throws IOException{
            
        BufferedReader in = new BufferedReader(new FileReader(file));
        
        String line = in.readLine();
        return line;
}
}