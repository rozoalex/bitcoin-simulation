package utils;

import java.security.NoSuchAlgorithmException;

import static utils.Helper.mine;

public class Node implements Runnable{

    private String pubKeyHash;

    private BlockChain blockChain;

    public Node (String pubKeyHash) throws NoSuchAlgorithmException {
        this.pubKeyHash = pubKeyHash;
        this.blockChain = new BlockChain();
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
                lastBlock.getNumber(),
                0l,
                this.createCoinbaseTransaction(),
                lastBlock.getHash(),
                lastBlock.getHashAlgorithm()
        );
        mine(nextBlock, "0000");
        if (this.blockChain.getLastBlock() == lastBlock) {
            // This node won the race, tell all nodes
            broadcast(nextBlock);
            blockChain.add(nextBlock);
        }

    }

    /**
     * This node is the first to generate the next block
     * tell all nodes
     *
     * @param nextBlock
     */
    private void broadcast(Block nextBlock) {
        // TODO
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
