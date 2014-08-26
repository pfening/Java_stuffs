package tox;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class pre {
    public static void main(String[] args) throws GeneralSecurityException {

    String fs=System.getProperty("file.separator");
    String hd=System.getProperty("user.home");
    String fname=".jtox.conf";
    try{
    File file = new File(hd + fs + fname); 
    if (file.createNewFile()){
    String text = ";;";
    ReadWrite.Write(file, Cripto.encrypt(text,"ibm"));
    }else{
    }
    }catch (IOException e) {
    }
}
}