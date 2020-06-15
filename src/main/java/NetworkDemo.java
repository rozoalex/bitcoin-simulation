import networks.Network;
import networks.Node;

import java.security.NoSuchAlgorithmException;

public class NetworkDemo {
    public static void main(String[] args) throws NoSuchAlgorithmException, InterruptedException {
        Network network = new Network();

        Node node1 = network.addNewNode("person a");
        Node node2 = network.addNewNode("person b");
        Node node3 = network.addNewNode("person c");

        // keep the network run for 60 seconds
        network.runNetwork(10);
    }
}
