import org.junit.jupiter.api.Test;
import utils.MerkleTree;

import static org.junit.Assert.assertEquals;

public class MerkleTreeTest {

    @Test
    public void testMerkleTreeDepth() {
        MerkleTree merkleTree = new MerkleTree();

        merkleTree.add("a");
        merkleTree.add("b");
        merkleTree.add("c");
        merkleTree.add("d");
        merkleTree.add("e");

        assertEquals(merkleTree.depth(), 2);
    }
}