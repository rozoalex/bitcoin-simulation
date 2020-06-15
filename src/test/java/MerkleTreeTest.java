import org.junit.jupiter.api.Test;
import utils.MerkleTree;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Test basic functionality of the Merkle Tree implementation
 */
public class MerkleTreeTest {

    @Test
    public void testMerkleTreeSize() {
        MerkleTree merkleTree = new MerkleTree();
        // construct tree with randomSize number of nodes
        for (int i = 0; i < 100; i++) {
            merkleTree.add("test");
            // size should increase 1 at a time
            assertEquals(merkleTree.size(), i+1);
        }
    }

    @Test
    public void testMerkleTreeDepth() {
        // test 10 times
        for (int time = 0; time < 10; time++) {
            MerkleTree merkleTree = new MerkleTree();
            // rand number between 10 to 100
            int randomSize = (int)(Math.random() * 90) + 10;
            // construct tree with randomSize number of nodes
            for (int i = 0; i < randomSize; i++) merkleTree.add("test");
            // depth should be log2(size - 1) + 1
            assertEquals(merkleTree.depth(), (int)(Math.log(randomSize - 1) / Math.log(2)) + 1);
        }
    }

    @Test
    public void testMerkleTreeHash() {
        MerkleTree merkleTree = new MerkleTree();
        Set<String> merkleRootSet = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            merkleTree.add("test");
            // The merkle root should be a hash that never appear before as new elements are added
            assertFalse(merkleRootSet.contains(merkleTree.getMerkleRoot()));
            merkleRootSet.add(merkleTree.getMerkleRoot());
        }
    }
}