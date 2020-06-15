package demos;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

import static utils.Helper.bytesToHex;

public class PublicKeyCryptographyDemo {

    public static void main(String[] args) {
        try {
            // The most popular Public Key Algorithms are RSA, Diffie-Hellman, ElGamal, DSS. We use RSA here as example
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            KeyPair pair = keyPairGenerator.generateKeyPair();

            System.out.println("Public Key: " + bytesToHex(pair.getPublic().getEncoded()));
            System.out.println("Private Key: " + bytesToHex(pair.getPrivate().getEncoded()));
        } catch (NoSuchAlgorithmException e) {
            System.err.println(e.getMessage());
        }
    }

}