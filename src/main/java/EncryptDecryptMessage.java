import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

public class EncryptDecryptMessage {

    public static final String ALGORITHM = "RSA";

    public static void main(String[] args)
            throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException {

        long startTime = System.currentTimeMillis();

        KeyPair keyPair = generateKeyPair();

        String plainMessage = "the answer to life the universe and everything";

        String encryptedMessage = encrypt(plainMessage, keyPair.getPublic());

        System.out.println(plainMessage);
        System.out.println(encryptedMessage);

        System.out.println(decrypt(encryptedMessage, keyPair.getPrivate()));

        System.out.println(System.currentTimeMillis() - startTime);
    }

    private static String encrypt(final String plainText, final PublicKey key)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        Cipher encryptCipher = Cipher.getInstance(ALGORITHM);
        encryptCipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] cipherText = encryptCipher.doFinal(plainText.getBytes(UTF_8));

        return Base64.getEncoder().encodeToString(cipherText);
    }

    private static String decrypt(String encryptedMessage, PrivateKey key) throws BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        byte[] bytes = Base64.getDecoder().decode(encryptedMessage);

        Cipher decryptCipher = Cipher.getInstance(ALGORITHM);
        decryptCipher.init(Cipher.DECRYPT_MODE, key);

        return new String(decryptCipher.doFinal(bytes), UTF_8);
    }

    private static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance(ALGORITHM);
        generator.initialize(2048, new SecureRandom());

        return generator.generateKeyPair();
    }

}
