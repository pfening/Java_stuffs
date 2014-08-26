package qr;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Hashtable;

import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.*;

public class BarCode {

public static void main(String[] args)
{
    Charset charset = Charset.forName("UTF-8");
    CharsetEncoder encoder = charset.newEncoder();
    byte[] b = null;
    try {
        // Convert a string to UTF-8 bytes in a ByteBuffer
        ByteBuffer bbuf = encoder.encode(CharBuffer.wrap("utf 8 characters - i used hebrew, but you should write some of your own language characters"));
        b = bbuf.array();
    } catch (CharacterCodingException e) {
        System.out.println(e.getMessage());
    }

    String data;
    try {
        data = new String(b, "UTF-8");
        // get a byte matrix for the data
        BitMatrix matrix = null;
        int h = 100;
        int w = 100;
        com.google.zxing.Writer writer = new MultiFormatWriter();
        try {
            Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>(2);
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            matrix = writer.encode(data,
            com.google.zxing.BarcodeFormat.QR_CODE, w, h, hints);
        } catch (com.google.zxing.WriterException e) {
            System.out.println(e.getMessage());
        }

        // change this path to match yours (this is my mac home folder, you can use: c:\\qr_png.png if you are on windows)
                String filePath = "/home/gabor/Pictures/qr_png.png";
        File file = new File(filePath);
        try {
            MatrixToImageWriter.writeToFile(matrix, "PNG", file);
            System.out.println("printing to " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    } catch (UnsupportedEncodingException e) {
        System.out.println(e.getMessage());
    }
}

}