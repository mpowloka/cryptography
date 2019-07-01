import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Commons {

    public static final String ALGORITHM = "RSA";

    public static final String SIGNATURE_ALGORITHM = "SHA256withRSA";

    public static final String KEYSTORE_PATH = "createdKeystore.jks";

    public static final String KEYSTORE_PASSWORD = "password";

    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance(ALGORITHM);
        generator.initialize(2048, new SecureRandom());

        return generator.generateKeyPair();
    }

}
