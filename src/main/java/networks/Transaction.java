package networks;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Simulate a transaction
 * A transaction takes bitcoins from one or more Unspent Transaction Outputs (UTXO)
 * and outputs to one or more new UTXOs
 * with specific conditions defining who can use them
 */
public class Transaction {
    private TransactionOutput[] inputs;
    private TransactionOutput[] outputs;
    private Double fees;
    


    /*
        Helper functions and classes
        Not related to algorithm
     */
    private TransactionJsonAdapter jsonAdapter = new TransactionJsonAdapter();

    @Override
    public String toString() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Transaction.class, this.jsonAdapter);
        Gson gson = builder.setPrettyPrinting().create();
        return gson.toJson(this);
    }

    private class TransactionJsonAdapter extends TypeAdapter<Transaction> {

        @Override
        public void write(JsonWriter jsonWriter, Transaction transaction) throws IOException {
            // TODO
        }

        @Override
        public Transaction read(JsonReader jsonReader) throws IOException {
            return null;
        }
    }
}
