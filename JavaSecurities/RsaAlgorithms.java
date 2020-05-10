import java.util.Base64;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RsaAlgorithms {
    public static void main(final String[] args) {
        try {
            final KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            final KeyPair kp = kpg.genKeyPair();
            final PublicKey publicKey = kp.getPublic();
            final PrivateKey privateKey = kp.getPrivate();

            String originalMessage = "Cộng hòa xã hội chủ nghĩa Việt Nam.";
            String encryptedMessage = RsaAlgorithms.encrypt(originalMessage, publicKey);
            String decryptedMessage = RsaAlgorithms.decrypt(encryptedMessage, privateKey);

            System.out.println("Original Message: " + originalMessage);
            System.out.println("Encrypted Message: " + encryptedMessage);
            System.out.println("Decrypted Message: " + decryptedMessage);
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
    }

    public static String encrypt(String strToDecrypt, PublicKey publicKey) {
        try {
            final Cipher ci = Cipher.getInstance("RSA");
            ci.init(Cipher.ENCRYPT_MODE, publicKey);
            return Base64.getEncoder().encodeToString(ci.doFinal(strToDecrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            return null;
        }
    }

    public static String decrypt(String strToDecrypt, PrivateKey privateKey) {
        try {
            final Cipher ci = Cipher.getInstance("RSA");
            ci.init(Cipher.DECRYPT_MODE, privateKey);
            return new String(ci.doFinal(Base64.getDecoder().decode(strToDecrypt)), "UTF-8");
        } catch (Exception e) {
            return null;
        }
    }
}