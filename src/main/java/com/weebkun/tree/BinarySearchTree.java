package com.weebkun.tree;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * tree data structure for binary searching.
 * implements {@link Tree<Integer>} for easier implementation.
 *
 * <p>
 * this implementation allows for duplicate values, the duplicate value will be inserted as the left child if the existing node is a leaf,
 * otherwise the new value will be inserted as normal.
 * the equivalent comparison made will be 'is {new value} less than or equal to {existing leaf}?'.
 * </p>
 * @see Tree
 */
public class BinarySearchTree extends BinaryTree<Integer> implements Tree<Integer> {

    private int size;
    private BinaryNode<Integer> root;

    /**
     * constructs a BST from a given list of values.
     * @param values the list of values
     */
    public BinarySearchTree(List<Integer> values){
        // do binary search tree algo
        // set root as first elem in values
        this.root = new BinaryNode<>(values.get(0));
        // iterate through rest of values and insert appropriately
        for(int i = 1; i < values.size(); i++){
            // recursively insert values into this tree, starting from root.
            insert(this.root, values.get(i));
        }
    }

    /**
     * constructs a BST from a given array.
     * @param array the array
     */
    public BinarySearchTree(int[] array){
        this.root = new BinaryNode<>(array[0]);
        for(int i = 1; i < array.length; i++){
            insert(this.root, array[i]);
        }
    }

    private void insert(BinaryNode<Integer> node, int value) {
        if(value <= node.getValue()){
            // check if subtree has left child
            if(node.hasLeft()) {
                // insert into left subtree
                insert(node.visitLeft(), value);
            } else {
                // left subtree does not exist. set the left node to be current value
                node.setLeft(value);
            }
        } else {
            // value is more than this node
            if(node.hasRight()){
                // insert into right subtree
                insert(node.visitRight(), value);
            } else {
                // right subtree does not exist. set right node to be current value
                node.setRight(value);
            }
        }
    }
}
