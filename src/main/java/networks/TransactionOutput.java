package networks;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import lombok.Getter;

public class TransactionOutput {
    @Getter private String pubKeyHashAddress;
    @Getter private String pkScript;
    @Getter private String sigScript;
    @Getter private Double value;
    @Getter private Boolean isSpent;

    private Gson jsonPrettyBuilder = new GsonBuilder().setPrettyPrinting().create();

    // Helper functions
    public JsonObject toJsonObject() {
        JsonObject obj = new JsonObject();
        obj.addProperty("address", this.pubKeyHashAddress);
        obj.addProperty("PkScript", this.pkScript);
        if (this.sigScript != null) obj.addProperty("SigScript", this.sigScript);
        obj.addProperty("value", this.value);
        obj.addProperty("status", this.isSpent ? "spent" : "unspent");
        return obj;
    }

    @Override
    public String toString() {
        return jsonPrettyBuilder.toJson(this.toJsonObject());
    }
}
