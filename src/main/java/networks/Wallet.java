package networks;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Simulate the wallet program
 * which finds all Unspent Transaction Output (UTXO) of the owner in the network and calculates the balance
 * it also can construct new transactions when the owner wants to give someone bitcoins
 */
public class Wallet {
    Queue<TransactionOutput> UTXOs = new LinkedBlockingDeque<TransactionOutput>();

    Network network;

    public Transaction constructNewTransaction(String receiverPubKeyHash) {
        // TODO
        return null;
    }

    // get all unspent output from the networks
    public void refreshUTXOs () {

    }
}
