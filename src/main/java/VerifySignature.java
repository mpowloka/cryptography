import java.security.*;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

public class VerifySignature {

    public static void main(String[] args) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {

        long startTime = System.currentTimeMillis();

        KeyPair keyPair = Commons.generateKeyPair();

        String signature = sign("fooBar", keyPair.getPrivate());

        boolean isCorrect = verify("fooBar", signature, keyPair.getPublic());

        System.out.println("Signature correct: " + isCorrect);
        System.out.println(System.currentTimeMillis() - startTime);
    }

    private static String sign(String plainText, PrivateKey privateKey)
            throws SignatureException, NoSuchAlgorithmException, InvalidKeyException {

        Signature privateSignature = Signature.getInstance(Commons.SIGNATURE_ALGORITHM);
        privateSignature.initSign(privateKey);
        privateSignature.update(plainText.getBytes(UTF_8));

        byte[] signature = privateSignature.sign();

        return Base64.getEncoder().encodeToString(signature);
    }

    private static boolean verify(String plainText, String signature, PublicKey publicKey)
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {

        Signature publicSignature = Signature.getInstance(Commons.SIGNATURE_ALGORITHM);
        publicSignature.initVerify(publicKey);
        publicSignature.update(plainText.getBytes(UTF_8));

        byte[] signatureBytes = Base64.getDecoder().decode(signature);

        return publicSignature.verify(signatureBytes);
    }

}
