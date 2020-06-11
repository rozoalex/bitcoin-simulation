package utils;

import java.util.List;

/**
 * This simulate the whole network of nodes
 * it serves as a communication broker for all nodes
 *
 */
public class Network {

    List<Node> nodes;

    public void broadcastNextBlock(Block nextBlock) {
        for (Node node : this.nodes) {
            node.receive(nextBlock);
        }
    }
}
