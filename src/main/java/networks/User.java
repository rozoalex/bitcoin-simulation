package networks;

/**
 * Simulate someone who owns and trades bitcoins
 */
public class User {
    // Wallet program
    Wallet wallet;

    // The network this user is in
    Network network;

    public void sendMoneyTo(String pubKeyHash) {
        Transaction newTX = this.wallet.constructNewTransaction(pubKeyHash);
        this.network.broadcastTransaction(newTX);
    }
}
