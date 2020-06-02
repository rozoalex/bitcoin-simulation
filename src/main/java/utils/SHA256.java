package utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static utils.Helper.bytesToHex;

public class SHA256 {
    private MessageDigest digest;

    public SHA256() throws NoSuchAlgorithmException {
        this.digest = MessageDigest.getInstance("SHA-256");
    }

    public String hash(String originalString) {
        byte[] encodedHash = digest.digest(originalString.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(encodedHash);
    }
}
