package qr;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
 
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

public class QR {
    public static void main(String[] args) {
        ByteArrayOutputStream out = QRCode.from("Pfening Gabor").withSize(250, 250).to(ImageType.PNG).stream();
 
        try {
            FileOutputStream fout = new FileOutputStream(new File("/home/gabor/Pictures/QR_Code"));
 
            fout.write(out.toByteArray());
 
            fout.flush();
            fout.close();
 
        } catch (FileNotFoundException e) {
            // Do Logging
        	e.printStackTrace();
        } catch (IOException e) {
            // Do Logging
        	e.printStackTrace();
        }
    }
}
