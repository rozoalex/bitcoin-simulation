package utils;

public class Helper {
    public static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static void mine(Block block, String pattern) {
        while (!block.getHash().startsWith(pattern)) {
            block.increaseNonce();
            block.validateHash();
        }
    }
}
