import datastructures.MerkleTree;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Test basic functionality of the Merkle Tree implementation
 */
public class MerkleTreeTest {

    @Test
    public void testSize() {
        MerkleTree<String> merkleTree = new MerkleTree<>();
        // construct tree with randomSize number of nodes
        for (int i = 0; i < 100; i++) {
            merkleTree.add("test");
            // size should increase 1 at a time
            assertEquals(merkleTree.size(), i+1);
        }
    }

    @Test
    public void testDepth() {
        // test 10 times
        for (int time = 0; time < 10; time++) {
            MerkleTree<String> merkleTree = new MerkleTree<>();
            // rand number between 10 to 100
            int randomSize = (int)(Math.random() * 90) + 10;
            // construct tree with randomSize number of nodes
            for (int i = 0; i < randomSize; i++) merkleTree.add("test");
            // depth should be log2(size - 1) + 1
            assertEquals(merkleTree.depth(), (int)(Math.log(randomSize - 1) / Math.log(2)) + 1);
        }
    }

    @Test
    public void testHashChanging() {
        MerkleTree<String> merkleTree = new MerkleTree<>();
        Set<String> merkleRootSet = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            merkleTree.add("test");
            // The merkle root should be a hash that never appear before as new elements are added
            assertFalse(merkleRootSet.contains(merkleTree.getMerkleRoot()));
            merkleRootSet.add(merkleTree.getMerkleRoot());
        }
    }

    @Test
    public void testElementsOrder() {
        MerkleTree<String> merkleTree = new MerkleTree<>();
        List<String> strings = new LinkedList<>();
        // construct tree with randomSize number of nodes
        for (int i = 0; i < 100; i++) {
            int randomNumber = (int)(Math.random() * 100);
            merkleTree.add(String.valueOf(randomNumber));
            strings.add(String.valueOf(randomNumber));
            // verify the order of the elements
            assertEquals(merkleTree.getElementList().toString(), strings.toString());
        }
    }
}