package crypto;

import java.io.IOException;
import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Cripto {
    
    private static final byte[] SALT = new byte[] { 'I', 'B', 'M', '4', 'e', 'v', 'e', 'r' };

    public static String encrypt(String property, String pass) throws GeneralSecurityException {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
        SecretKey key = keyFactory.generateSecret(new PBEKeySpec(pass.toCharArray()));        
        Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");        
        pbeCipher.init(Cipher.ENCRYPT_MODE, key, new PBEParameterSpec(SALT, 20));        
        return base64Encode(pbeCipher.doFinal(property.getBytes()));
    }

    public static String base64Encode(byte[] bytes) {
    return new BASE64Encoder().encode(bytes);
    }

    public static String decrypt(String property, String pass) throws GeneralSecurityException, IOException {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
        SecretKey key = keyFactory.generateSecret(new PBEKeySpec(pass.toCharArray()));
        Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
        pbeCipher.init(Cipher.DECRYPT_MODE, key, new PBEParameterSpec(SALT, 20));
        return new String(pbeCipher.doFinal(base64Decode(property)));
    }

    public static byte[] base64Decode(String property) throws IOException {
    return new BASE64Decoder().decodeBuffer(property);
    }
}