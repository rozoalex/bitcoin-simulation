package networks;

import datastructures.Block;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static utils.Helper.writeToFile;

/**
 * Simulate the whole network of nodes
 * it serves as a communication broker for all nodes
 *
 */
public class Network {

    // All nodes in this network
    List<Node> nodes = new ArrayList<Node>();

    // Last time broadcast new block
    long lastTimeBroadcast = System.currentTimeMillis();

    public void addNodes(Collection<Node> nodes) {
        nodes.addAll(nodes);
    }

    public void addNode(Node node) {
        nodes.add(node);
    }


    /**
     * Assign each node to a thread to mimic real-life scenario
     * where each node runs on different people's machine
     *
     * @param forSeconds run network for this many seconds
     */
    public void runNetwork(int forSeconds) throws InterruptedException {
        System.out.println("Run " + this.nodes.size() + " nodes for " + forSeconds + "s." );
        ExecutorService threadPool = Executors.newFixedThreadPool(this.nodes.size());
        for (Node node : this.nodes)
            threadPool.execute(node);

        TimeUnit.SECONDS.sleep(forSeconds);

        // write the blockchain in each nodes to files in output folder
        for (Node node : this.nodes) {
            writeToFile(
                    node.getPubKeyHashAddress() + ".json",
                    node.getBlockChain().toString()
            );
        }
    }

    public Node addNewNode(String pubkeyHash) throws NoSuchAlgorithmException {
        Node node = new Node(pubkeyHash, this);
        nodes.add(node);
        return node;
    }

    /**
     * Broad cast a new transaction to all nodes
     *
     * @param transaction
     */
    public void broadcastTransaction(Transaction transaction) {
        // TODO
    }

    public void broadcastNextBlock(Block nextBlock) {
        long currentTime = System.currentTimeMillis();
        System.out.println("Next block found " + nextBlock
                + ", took " + ((currentTime - this.lastTimeBroadcast)/1000.0) + "s\n");
        this.lastTimeBroadcast = currentTime;

        for (Node node : this.nodes) {
            node.receive(nextBlock);
        }
    }
}
