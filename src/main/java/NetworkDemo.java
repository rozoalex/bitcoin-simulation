import utils.Network;
import utils.Node;

import java.security.NoSuchAlgorithmException;

public class NetworkDemo {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Network network = new Network();

        Node node1 = network.addNewNode("person a");
        Node node2 = network.addNewNode("person b");
        Node node3 = network.addNewNode("person c");

        Thread t1 = new Thread(node1);
        t1.start();
        Thread t2 = new Thread(node2);
        t2.start();
        Thread t3 = new Thread(node3);
        t3.start();


    }
}
