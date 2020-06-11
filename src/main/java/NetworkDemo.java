import utils.Network;
import utils.Node;

import java.security.NoSuchAlgorithmException;

public class NetworkDemo {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Network network = new Network();

        Node node = network.addNewNode("alex");

        Thread t1 = new Thread(node);
        t1.start();

    }
}
