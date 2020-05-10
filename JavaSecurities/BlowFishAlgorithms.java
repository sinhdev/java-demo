import java.util.Base64;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class BlowFishAlgorithms {
    public static void main(String[] args) {
        final String keyFilePath = "sinhnx.dev";
        String originalMessage = "Cộng hòa xã hội chủ nghĩa Việt Nam.";
        String encryptedMessage = BlowFishAlgorithms.encrypt(originalMessage, keyFilePath);
        String decryptedMessage = BlowFishAlgorithms.decrypt(encryptedMessage, keyFilePath);

        System.out.println("Original Message: " + originalMessage);
        System.out.println("Encrypted Message: " + encryptedMessage);
        System.out.println("Decrypted Message: " + decryptedMessage);
    }

    public static String encrypt(String msg, String keyFilePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(keyFilePath))) {
            SecretKey sKey = KeyGenerator.getInstance("BlowFish").generateKey();
            oos.writeObject(sKey);
            Cipher ci = Cipher.getInstance("BlowFish");
            ci.init(Cipher.ENCRYPT_MODE, sKey);
            return Base64.getEncoder().encodeToString(ci.doFinal(msg.getBytes("UTF-8")));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String strToDecrypt, String keyFilePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(keyFilePath))) {
            Object obj = ois.readObject();
            if (obj instanceof SecretKey) {
                SecretKey sKey = (SecretKey) obj;
                Cipher ci = Cipher.getInstance("BlowFish");
                ci.init(Cipher.DECRYPT_MODE, sKey);
                return new String(ci.doFinal(Base64.getDecoder().decode(strToDecrypt)), "UTF-8");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}