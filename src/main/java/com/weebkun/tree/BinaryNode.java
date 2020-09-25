/*
   Copyright 2020 weebkun

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

package com.weebkun.tree;

import com.weebkun.utils.NodeOccupiedException;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a node in a binary tree.
 * @param <T> the type of values that this node will have
 */
public class BinaryNode<T> implements Node<T>{

    private T element;
    private BinaryNode<T> left;
    private BinaryNode<T> right;
    private BinaryNode<T> parent;

    /**
     * constructs a node with a given value.
     * @param value the value
     */
    public BinaryNode(T value){
        this.element = value;
    }

    /**
     * constructs a {@code BinaryNode} with a given value and a parent reference.
     * @param value the value
     * @param parent the parent node
     */
    public BinaryNode(T value, BinaryNode<T> parent) {
        this.element = value;
        this.parent = parent;
    }

    /**
     * adds a child node from left to right.
     * if no nodes are available, throws {@link NodeOccupiedException}.
     * @param value the value
     * @throws IllegalArgumentException if the value is null
     * @throws NodeOccupiedException when both childs are not available
     */
    @Override
    public void add(T value) throws IllegalArgumentException, NodeOccupiedException {
        if(value == null) throw new IllegalArgumentException("value cannot be null.");
        if(this.left == null) {
            // assign to left node if null
            this.left = new BinaryNode<>(value, this);
        } else if(this.right == null) {
            // assign to right node if left has a value already
            this.right = new BinaryNode<>(value, this);
        } else {
            throw new NodeOccupiedException("nodes have been occupied.", this.right);
        }
    }

    /**
     * calculates the height of this node from the furthest leaf.
     * @return the height of this node
     * @see Node#calculateHeight() for further explanation.
     */
    public int calculateHeight(){
        // calculate the heights of left and right subtree
        int leftHeight = this.left != null ? this.left.calculateHeight() : -1;
        int rightHeight = this.right != null ? this.right.calculateHeight() : -1;

        // return the larger of the 2
        return Math.max(leftHeight + 1, rightHeight + 1);
    }

    /**
     * returns the size of the subtree of this node.
     * @return the size of this subtree
     */
    @Override
    public int size() {
        return subtree().size();
    }

    /**
     * returns a subtree with this node as root.
     * @return the subtree
     */
    public Tree<T> subtree(){
        return new BinaryTree<>(this);
    }

    /**
     * sets the left node.
     * @param value the value to add
     * @throws IllegalArgumentException if value is null.
     * @throws NodeOccupiedException if the left node is occupied
     */
    public void setLeft(T value) throws IllegalArgumentException, NodeOccupiedException {
        if(value == null) throw new IllegalArgumentException("value cannot be null.");
        if(this.left != null) throw new NodeOccupiedException("left node already occupied", this.left);
        this.left = new BinaryNode<>(value, this);
    }

    /**
     * sets the right node.
     * @param value the value to add
     * @throws IllegalArgumentException if value is null
     * @throws NodeOccupiedException if right node is occupied
     */
    public void setRight(T value) throws IllegalArgumentException, NodeOccupiedException {
        if(value == null) throw new IllegalArgumentException("value cannot be null.");
        if(this.right != null) throw new NodeOccupiedException("right node already occupied", this.right);
        this.right = new BinaryNode<>(value, this);
    }

    public T getValue(){
        return this.element;
    }

    /**
     * returns the parent of this node.
     * may return a null reference.
     * @return the parent
     */
    @Nullable
    @Override
    public Node<T> getParent() {
        return this.parent;
    }

    /**
     * returns a list of both children.
     * may return null references
     * @return the list of children
     */
    @Override
    public List<Node<T>> getChildren() {
        return Arrays.asList(this.left, this.right);
    }

    /**
     * returns a list of this node's children as their values.
     * @return a list containing the values of the children
     */
    public List<T> getChildrenAsValues(){
        return Arrays.asList(this.left.getValue(), this.right.getValue());
    }

    /**
     * checks if this node has any children. returns true if so.
     * @return true if this node has children
     */
    public boolean hasChildren(){
        return getChildren() != null;
    }

    /**
     * checks if the left child is not null.
     * @return true if not null
     */
    public boolean hasLeft(){
        return this.left != null;
    }

    /**
     * checks if the right child is not null.
     * @return true if not null
     */
    public boolean hasRight(){
        return this.right != null;
    }

    /**
     * updates the value of this node's element.
     * @param elem the new value
     * @throws IllegalArgumentException if the value is null
     */
    public void update(T elem) throws IllegalArgumentException{
        if(elem == null) throw new IllegalArgumentException("value cannot be null");
        this.element = elem;
    }

    /**
     * returns the node at {@code index}.
     * But in the case for a binary tree, visits either the left or right node depending on index.
     * throws {@link IndexOutOfBoundsException} if {@code index} is not 0 or 1.
     * @param index 0 for the left or 1 for the right node
     * @return the left(0) or right(1) node
     */
    @Override
    public Node<T> visit(int index) throws IndexOutOfBoundsException{
        if(index < 0 || index > 1) throw new IndexOutOfBoundsException("index can only be 0(left) or 1(right).");
        return index == 0 ? visitLeft() : visitRight();
    }

    /**
     * visits the left subtree. returns the left child node.
     * @return the left node
     */
    BinaryNode<T> visitLeft(){
        return this.left;
    }

    /**
     * visits the right subtree. returns the right child node.
     * @return the right node
     */
    BinaryNode<T> visitRight(){
        return this.right;
    }
}
