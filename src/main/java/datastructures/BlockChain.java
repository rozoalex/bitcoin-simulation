package datastructures;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.LinkedBlockingDeque;

import static utils.Helper.GENESIS_BLOCK_HASH;

/**
 * Data Structure of blockchain
 * a list of blocks
 */
public class BlockChain {
    private LinkedBlockingDeque<Block> blocksList = new LinkedBlockingDeque<Block>();

    /**
     * Init a block chain, hard coded the first block
     */
    public BlockChain() {
        blocksList.add(new Block(0l, 0l, "demo", GENESIS_BLOCK_HASH));
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
