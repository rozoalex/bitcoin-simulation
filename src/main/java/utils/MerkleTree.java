package utils;

import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;

/**
 * Data structure Merkle Tree
 */
public class MerkleTree {
    private MerkleTreeNode root;

    private MerkleTreeNode last;

    public int depth() {
        return root.depth;
    }

    public String getMerkleRoot() {
        return root.value;
    }

    public void add(String newValue) {
        // TODO
    }



    private class MerkleTreeNode {
        String value;

        int depth = 0;

        MerkleTreeNode left;
        MerkleTreeNode right;

        boolean add(String value, int targetDepth) {
            if (isLeaf() && targetDepth == 0) {
                this.value = value;
                return true;
            } else {
                if (right != null && left != null) {
                    boolean result = right.add(value, targetDepth - 1);
                    if (result) computeHash();
                    return result;
                } else if (left == null) {
                    left = new MerkleTreeNode();
                    left.depth = targetDepth - 1;
                    left.add(value, targetDepth - 1);
                    computeHash();
                    return true;
                } else {
                    if (left.add(value, targetDepth - 1)) {
                        computeHash();
                        return true;
                    } else {
                        right = new MerkleTreeNode();
                        right.depth = targetDepth - 1;
                        right.add(value, targetDepth - 1);
                        computeHash();
                        return true;
                    }
                }
            }
        }

        /**
         * Calculate hash. If one child node is empty use a copy of the other one
         */
        void computeHash() {
            this.value = sha256Hex(
                    left.value + " " + (right == null ? left.value : right.value)
            );
        }

        boolean isLeaf() {
            return this.left == null && this.right == null;
        }
    }



}
