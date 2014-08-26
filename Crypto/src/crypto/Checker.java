/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crypto;

public class Checker {

    public static void main(String[] args) throws Exception {

        String password = "770922";
        String passwordEnc = AESencrp.encrypt(password);
        //String passwordEnc = "22fK1Ai6Q7yRZkv4SW2cJA==";
        String passwordDec = AESencrp.decrypt(passwordEnc);

        System.out.println("Plain Text : " + password);
        System.out.println("Encrypted Text : " + passwordEnc);
        System.out.println("Decrypted Text : " + passwordDec);
    }
}
