package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;

import static utils.Helper.GENESIS_BLOCK_HASH;

/**
 * Data Structure of blockchain
 * a list of blocks
 */
public class BlockChain {
    private SHA256 hashAlgorithm;

    private LinkedList<Block> blocksList = new LinkedList<Block>();

    /**
     * Init a block chain, hard coded the first block
     */
    public BlockChain() throws NoSuchAlgorithmException {
        this.hashAlgorithm = new SHA256();
        blocksList.add(new Block(0l, 0l, "demo", GENESIS_BLOCK_HASH, this.hashAlgorithm));
    }

    /**
     * Add a new block to this block chain
     * @param block
     */
    public void add(Block block) {
        blocksList.add(block);
    }

    public int size() {
        return blocksList.size();
    }

    public Block getLastBlock(){
        return this.blocksList.getLast();
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
