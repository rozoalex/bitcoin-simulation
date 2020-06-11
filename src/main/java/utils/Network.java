package utils;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This simulate the whole network of nodes
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

    public Node addNewNode(String pubkeyHash) throws NoSuchAlgorithmException {
        Node node = new Node(pubkeyHash, this);
        nodes.add(node);
        return node;
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
