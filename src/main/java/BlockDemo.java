import datastructures.Block;

import static utils.Helper.mine;

public class BlockDemo {
    public static void main(String[] args) {
        int numberOfBlocks = 3;
        String pattern = "0000";
        System.out.println("DEMO: Generate a chain of blocks");
        try {
            long blockNumber = 0l;
            String previousHash = "0000000000000000000000000000000000000000000000000000000000000000";
            while (blockNumber < numberOfBlocks) {
                // Initialize the block
                Block block = new Block(blockNumber, 0l, "demo", previousHash);
                long startTime = System.currentTimeMillis();
                // Do the mining
                mine(block, pattern);
                long timeElapsed = System.currentTimeMillis() - startTime;
                System.out.println("Took " + (timeElapsed / 1000.0) +"s, mined block: " + block);
                // set the hash for next block
                previousHash = block.getHash();
                blockNumber++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
