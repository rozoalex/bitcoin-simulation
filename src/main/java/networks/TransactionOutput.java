package networks;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class TransactionOutput {
    private String pubKeyHashAddress;
    private String pkScript;
    private String sigScript;
    private Double value;
    private Boolean isSpent;



    // Helper functions
    public JsonObject toJsonObject() {
        JsonObject obj = new JsonObject();
        obj.addProperty("address", this.pubKeyHashAddress);
        obj.addProperty("PkScript", this.pkScript);
        if (this.sigScript != null) obj.addProperty("SigScript", this.sigScript);
        obj.addProperty("value", this.value + " BTC");
        obj.addProperty("status", this.isSpent ? "spent" : "unspent");
        return obj;
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this.toJsonObject());
    }
}
