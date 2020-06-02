package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.LinkedList;

public class BlockChain {

    private LinkedList<Block> blocksList;

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

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
