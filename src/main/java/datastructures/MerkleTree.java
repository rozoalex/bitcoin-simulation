package datastructures;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.util.LinkedList;
import java.util.List;

import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;

/**
 * Data structure Merkle Tree
 */
public class MerkleTree<T> {
    private int size = 0;

    private MerkleTreeNode root = new MerkleTreeNode();

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
                if (node.isLeaf) {
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
        JsonObject obj = new JsonObject();
        obj.addProperty("size", this.size);
        obj.addProperty("depth", this.depth());
        obj.addProperty("tree", this.root.toString());
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(obj);
    }

    /**
     * Add a new element to the tree
     *
     * @param element new element
     */
    public void add(T element) {
        this.size++;
        if (this.size > Math.pow(2, this.depth())) {
            MerkleTreeNode newRoot = new MerkleTreeNode();
            newRoot.left = this.root;
            newRoot.depth = this.root.depth + 1;
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
        protected final boolean isLeaf = false;
        // stores the hash value
        String value;
        // distance to bottom
        int depth = 1;

        MerkleTreeNode left;
        MerkleTreeNode right;

        boolean add(T element) {
            if (isLeaf) {
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
            MerkleTreeNode childNode = depth == 0 ? new MerkleTreeLeaf() : new MerkleTreeNode();
            childNode.depth = depth;
            return childNode;
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
            JsonObject obj = new JsonObject();
            obj.addProperty("val", this.value);
            obj.addProperty("left", this.left.toString());
            obj.addProperty("right", this.right.toString());
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            return gson.toJson(obj);
        }
    }

    /**
     * A Merkle Tree Leaf is a special kind of Merkle Tree Node
     * It stores an element of type T
     * The hash is the string conversion of the element
     */
    private class MerkleTreeLeaf extends MerkleTreeNode {
        T element;
        int depth = 0;
        protected final boolean isLeaf = true;

        /**
         * The hash value of the leaf is the string conversion of the element
         */
        @Override
        void computeHash() {
            this.value = element.toString();
        }

        @Override
        public String toString() {
            JsonObject obj = new JsonObject();
            obj.addProperty("val", this.value);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            return gson.toJson(obj);
        }
    }
}
