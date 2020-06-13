package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
        }
        return hexString.toString();
    }

    public static void mine(Block block, String pattern) {
        while (!block.getHash().startsWith(pattern)) {
            block.increaseNonce();
            block.validateHash();
        }
    }

    /**
     * helper for write some content to a file under output folder
     * file with the same name will be overwritten
     *
     * @param filename filename of the new file
     * @param content content
     */
    public static void writeToFile(String filename, String content) {
        try {
            String path = "output/" + filename;
            File file = new File(path);
            // always overwrite the old file
            if (file.exists() && file.isFile()) file.delete();
            // try create the file
            if (file.createNewFile())
                System.out.println("File created: " + file.getName());
            // write the content
            FileWriter writer = new FileWriter(path);
            writer.write(content);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
