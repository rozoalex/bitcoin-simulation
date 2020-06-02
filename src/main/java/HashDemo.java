import utils.SHA256;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HashDemo {
    public static void main(String[] args) {
        System.out.println("DEMO: SHA256 Hashing");
        try {
            SHA256 hashAlgorithm = new SHA256();
            //Enter data using BufferReader
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while(true) {
                System.out.println(
                        hashAlgorithm.hash(reader.readLine())
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
