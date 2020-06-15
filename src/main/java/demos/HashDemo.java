package demos;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;

public class HashDemo {
    public static void main(String[] args) {
        System.out.println("DEMO: SHA256 Hashing");
        try {
            //Enter data using BufferReader
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while(true) {
                System.out.println(sha256Hex(reader.readLine()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
