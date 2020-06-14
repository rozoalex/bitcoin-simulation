package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import lombok.Data;

import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;

/**
 * The general Data Structure of a Block
 */
public @Data class Block {
    // number of this block in the whole chain
    private long number;

    // Hash of the current block
    private String hash;

    // a number that used for validating the hash
    private long nonce;

    // information that this block stored
    private String data;

    // Hash of the previous block
    private String previousHash;

    public Block(long number, long nonce, String data, String previousHash) {
        this.nonce = nonce;
        this.number = number;
        this.data = data;
        this.previousHash = previousHash;
        this.validateHash();
    }

    /**
     * re-calculate the hash for this block from number, nonce, data, and hash of the previous block
     * with sha256 algorithm
     */
    public void validateHash(){
        this.hash = sha256Hex(String.format("%d\n%d\n%s\n%s", number, nonce, data, previousHash));
    }

    public void increaseNonce() {
        this.nonce += 1;
    }

    @Override
    public String toString() {
        JsonObject obj = new JsonObject();
        obj.addProperty("number", this.number);
        obj.addProperty("nonce", this.nonce);
        obj.addProperty("hash", this.hash);
        obj.addProperty("previousHash", this.previousHash);
        obj.addProperty("data", this.data);
        // Make this json output pretty
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(obj);
    }
}
