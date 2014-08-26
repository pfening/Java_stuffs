 

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Cripto {

    private static final byte[] SALT  = salt().getBytes();
    
    
        /**
     *
     * @return
     */
    public static String salt(){
        String salt=System.getProperty("user.name");

        int l = salt.length();
        if (l >= 8){
            salt = salt.substring(0, 8);
        }
        if (l < 8){
            int i = 8-l;
            String ad = salt.substring(0, i);
            salt = salt + ad;
        }
        return salt;
        }
    
    /**
     *
     * @param titok
     * @return
     * @throws GeneralSecurityException
     */
    public static String encrypt(String titok) throws GeneralSecurityException {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
        SecretKey key = keyFactory.generateSecret(new PBEKeySpec(pre.pass.toCharArray()));        
        Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");        
        pbeCipher.init(Cipher.ENCRYPT_MODE, key, new PBEParameterSpec(SALT, 20));        
        return base64Encode(pbeCipher.doFinal(titok.getBytes()));
    }

    /**
     *
     * @param bytes
     * @return
     */
    public static String base64Encode(byte[] bytes) {
    return new BASE64Encoder().encode(bytes);
    }

    /**
     *
     * @param titok
     * @return
     * @throws GeneralSecurityException
     * @throws IOException
     */
    public static String decrypt(String titok) throws GeneralSecurityException, IOException {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
        SecretKey key = keyFactory.generateSecret(new PBEKeySpec(pre.pass.toCharArray()));
        Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
        pbeCipher.init(Cipher.DECRYPT_MODE, key, new PBEParameterSpec(SALT, 20));
        return new String(pbeCipher.doFinal(base64Decode(titok)));
    }

    /**
     *
     * @param titok
     * @return
     * @throws IOException
     */
    public static byte[] base64Decode(String titok) throws IOException {
    return new BASE64Decoder().decodeBuffer(titok);
    }
    private static final Logger LOG = Logger.getLogger(Cripto.class.getName());
}