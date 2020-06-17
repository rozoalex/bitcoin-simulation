package demos;

import datastructures.MerkleTree;

public class MerkleTreeDemo {
    public static void main(String[] args) {
        MerkleTree<String> merkleTree = new MerkleTree<>();
        merkleTree.add("aaa");
        merkleTree.add("bbb");
        merkleTree.add("ccc");
        merkleTree.add("ddd");
        merkleTree.add("eee");
        System.out.println(merkleTree.toString());
    }
}
