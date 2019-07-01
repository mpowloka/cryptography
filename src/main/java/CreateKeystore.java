import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class CreateKeystore {


    public static void main(String[] args) throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException {

        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        char[] password = Commons.KEYSTORE_PASSWORD.toCharArray();
        keyStore.load(null, password);

        try (FileOutputStream fos = new FileOutputStream(Commons.KEYSTORE_PATH)) {
            keyStore.store(fos, password);
        }

    }

}
