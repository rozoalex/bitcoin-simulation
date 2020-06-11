package utils;

import java.security.NoSuchAlgorithmException;

public class Node implements Runnable{

    // The public key hash of the person who runs this node
    private String pubKeyHash;

    // each node stores a copy of the block chain
    private BlockChain blockChain;

    // The network this node is in
    private Network network;

    public Node (String pubKeyHash, Network network) throws NoSuchAlgorithmException {
        this.pubKeyHash = pubKeyHash;
        this.blockChain = new BlockChain();
        this.network = network;
    }

    public void run() {
        while (true) {
            createNewBlock();
        }
    }

    /**
     * Try to create a new block
     * @return
     */
    private void createNewBlock() {
        Block lastBlock = this.blockChain.getLastBlock();
        Block nextBlock = new Block(
                lastBlock.getNumber() + 1,
                0l,
                this.createCoinbaseTransaction(),
                lastBlock.getHash(),
                lastBlock.getHashAlgorithm()
        );

        // mine this block while
        // 1) hash not start with the pattern,
        // 2) no one else mined the next block yet
        while (!nextBlock.getHash().startsWith("00000")
                && this.blockChain.getLastBlock() == lastBlock) {
            nextBlock.increaseNonce();
            nextBlock.validateHash();
        }

        if (this.blockChain.getLastBlock() == lastBlock) {
            // This node won the race, tell all nodes this node found the next block
            this.network.broadcastNextBlock(nextBlock);
        }
    }

    /**
     * Receive the latest block over the network
     *
     * @param nextBlock
     */
    public void receive(Block nextBlock) {
        // add new block to the chain only if the next block is valid
        if (this.validateBlock(nextBlock)) {
            this.blockChain.add(nextBlock);
        }
    }

    /**
     * Check if the block received is legit
     *
     * @param block
     * @return
     */
    private boolean validateBlock(Block block) {
        return true;
    }

    /**
     *
     *
     * @return
     */
    private String createCoinbaseTransaction() {
        // TODO: implement transaction
        return this.pubKeyHash;
    }




}
