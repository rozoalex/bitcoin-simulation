package datastructures;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;

/**
 * Data structure Merkle Tree
 */
public class MerkleTree<T> {
    private int size = 0;

    private MerkleTreeNode root = new MerkleTreeNode(1);

    /**
     * distance from the root to the leaves
     *
     * @return
     */
    public int depth() {
        return this.root.depth;
    }

    /**
     * Number of elements in the tree
     *
     * @return
     */
    public int size() {
        return this.size;
    }

    /**
     * Merkle Root is the root hash of all the values in the tree
     *
     * @return
     */
    public String getMerkleRoot() {
        return root.value;
    }

    /**
     * Generate a list view of this tree
     * This method is meant to traverse the whole tree
     *
     * @return
     */
    public List<T> getElementList() {
        List<MerkleTreeNode> explore = new LinkedList<>();
        List<T> result = new LinkedList<>();
        explore.add(this.root);
        while (!explore.isEmpty()) {
            List<MerkleTreeNode> next = new LinkedList<>();
            for (MerkleTreeNode node : explore) {
                if (node == null) continue;
                if (node.depth == 0) {
                    // add to result if depth is zero (leaf)
                    result.add(((MerkleTreeLeaf)node).element);
                } else {
                    next.add(node.left);
                    next.add(node.right);
                }
            }
            explore = next;
        }
        return result;
    }

    @Override
    public String toString() {
        return this.root.toString();
    }

    /**
     * Add a new element to the tree
     *
     * @param element new element
     */
    public void add(T element) {
        this.size++;
        if (this.size > Math.pow(2, this.depth())) {
            MerkleTreeNode newRoot = new MerkleTreeNode(this.root.depth + 1);
            newRoot.left = this.root;
            this.root = newRoot;
        }
        this.root.add(element);
    }

    /**
     * Structure of Merkle Tree
     *          node
     *       /       \
     *    node       node
     *   /   \      /   \
     * leaf leaf  leaf leaf
     *
     * Each node contains the hash of the two children
     *
     */
    private class MerkleTreeNode {
        // stores the hash value
        String value;
        // distance to bottom
        public final int depth;
        // child nodes
        MerkleTreeNode left;
        MerkleTreeNode right;

        MerkleTreeNode(int depth) {
            this.depth = depth;
        }

        boolean add(T element) {
            if (this.depth == 0) {
                // found the leaf
                MerkleTreeLeaf leaf = (MerkleTreeLeaf)this;
                // if the leaf is not new abort, else set the new element
                if (leaf.element != null) return false;
                leaf.element = element;
            } else {
                // this is not a leaf, continue the recursion
                boolean result = false;
                // try add to the left first
                if (this.left == null) this.left = createChildNode(this.depth - 1);
                if (this.right == null) result = this.left.add(element);
                // then try add to right
                if (!result) {
                    if (this.right == null) this.right = createChildNode(this.depth - 1);
                    result = this.right.add(element);
                    if (!result) return false;
                }
            }
            computeHash();
            return true;
        }

        MerkleTreeNode createChildNode(int depth) {
            return depth == 0 ?
                    new MerkleTreeLeaf() :
                    new MerkleTreeNode(depth);
        }

        /**
         * Calculate hash. If one child node is empty use a copy of the other one
         */
        void computeHash() {
            this.value = sha256Hex(
                    left.value + " " + (right == null ? left.value : right.value)
            );
        }

        @Override
        public String toString() {
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(MerkleTreeNode.class, new MerkleTreeJsonAdapter());
            Gson gson = builder.setPrettyPrinting().create();
            return gson.toJson(this);
        }
    }

    /**
     * A Merkle Tree Leaf is a special kind of Merkle Tree Node
     * It stores an element of type T
     * The hash is the string conversion of the element
     */
    private class MerkleTreeLeaf extends MerkleTreeNode {
        T element;

        MerkleTreeLeaf() {
            super(0);
        }

        /**
         * The hash value of the leaf is the string conversion of the element
         */
        @Override
        void computeHash() {
            this.value = element.toString();
        }
    }

    /**
     * Helper class
     * Customized adapter for serializing the tree to json object
     */
    private class MerkleTreeJsonAdapter extends TypeAdapter<MerkleTreeNode> {
        @Override
        public void write(JsonWriter jsonWriter, MerkleTreeNode node) throws IOException {
            if (node == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("val").value(node.value);
            jsonWriter.name("depth").value(node.depth);
            if (node.depth > 0) {
                // add left & right node, only if the node is a leaf
                jsonWriter.name("left");
                write(jsonWriter, node.left);
                jsonWriter.name("right");
                write(jsonWriter, node.right);
            }
            jsonWriter.endObject();
        }

        @Override
        public MerkleTreeNode read(JsonReader jsonReader) throws IOException {
            // TODO: implement this to convert a json to a tree, IF NEEDED
            return null;
        }
    }
}
