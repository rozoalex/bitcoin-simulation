package utils;

public class Helper {
    // hard coded previous hash for the first block
    public static final String GENESIS_BLOCK_HASH = "0000000000000000000000000000000000000000000000000000000000000000";

    public static final String MINING_TARGET_PATTERN = "000000";

    public static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
            hexString.append(" ");
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
