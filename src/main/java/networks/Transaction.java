package networks;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.Getter;

/**
 * Simulate a transaction
 * A transaction takes bitcoins from one or more Unspent Transaction Outputs (UTXO)
 * and outputs to one or more new UTXOs
 * with specific conditions defining who can use them
 */
public class Transaction {
    @Getter private TransactionOutput[] inputs;
    private double inputSum;
    @Getter private TransactionOutput[] outputs;
    private double outputSum;
    @Getter private Double fees;

    private Gson jsonPrettyBuilder = new GsonBuilder().setPrettyPrinting().create();


    public Transaction(TransactionOutput[] inputs, TransactionOutput[] outputs) {
        this.inputs = inputs;
        this.outputs = outputs;
        this.inputSum = 0;
        for (TransactionOutput input : inputs) {
            inputSum += input.getValue();
        }
        this.outputSum = 0;
        for (TransactionOutput output : outputs) {
            outputSum += output.getValue();
        }
        this.fees = inputSum - outputSum;
    }

    /*
        Helper functions and classes
        Not related to algorithm
     */

    @Override
    public String toString() {
        JsonObject obj = new JsonObject();
        JsonArray inputJsonArray = new JsonArray();
        for (TransactionOutput input : inputs)
            inputJsonArray.add(input.toJsonObject());
        JsonArray outputJsonArray = new JsonArray();
        for (TransactionOutput output : outputs)
            outputJsonArray.add(output.toJsonObject());
        obj.addProperty("input sum", this.inputSum);
        obj.add("input", inputJsonArray);
        obj.addProperty("output sum", this.outputSum);
        obj.add("output", outputJsonArray);
        obj.addProperty("fees", this.fees);
        return jsonPrettyBuilder.toJson(obj);
    }
}
