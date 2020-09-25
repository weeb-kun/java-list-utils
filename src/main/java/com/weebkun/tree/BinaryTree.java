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

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BinaryTree<T> implements Tree<T>, Iterable<T> {

    private int size;
    private BinaryNode<T> root;

    /**
     * default constructor for {@code BinaryTree}.
     * constructs an empty {@code BinaryTree} with size 0.
     * note that the root node will be a null reference.
     */
    public BinaryTree(){
        this.size = 0;
    }

    /**
     * constructs a {@code BinaryTree} from a given root node.
     * @param root the root node.
     */
    public BinaryTree(BinaryNode<T> root) {
        this.root = root;
        this.size = root.size();
    }

    /**
     * constructs a {@code BinaryTree} from a given root element.
     * @param root the root value
     * @throws IllegalArgumentException if {@code root} is null
     */
    public BinaryTree(T root) throws IllegalArgumentException {
        if(root == null) throw new IllegalArgumentException("root must not be null.");
        this.root = new BinaryNode<>(root);
    }

    /**
     * constructs a {@code BinaryTree} with the given root, left and right child.
     * @param root the root value
     * @param left the left child
     * @param right the right child
     * @throws IllegalArgumentException if {@code root} is null
     */
    public BinaryTree(T root, T left, T right) throws IllegalArgumentException{
        BinaryNode<T> node = new BinaryNode<>(root);
        if(left != null) node.setLeft(left);
        if(right != null) node.setRight(right);
        this.root = node;
    }

    /** returns the size of this tree.
     * @return the size of this tree
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * returns the height of this tree.
     * @return the height
     * @see BinaryNode#calculateHeight()
     * @see Tree#getHeight() for more details.
     */
    public int getHeight(){
        return this.root.calculateHeight();
    }

    /**
     * checks if this tree is empty. returns true if so.
     * @return true if this tree is empty
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * returns the root node of this tree.
     * @return
     */
    @Override
    public Node<T> getRoot() {
        return this.root;
    }

    /**
     * traverses this tree in pre-order and returns a list containing the elements.
     * utilises {@link #traverse(BinaryNode)} to recursively traverse this tree.
     * @return the list in pre-order
     */
    @Override
    public List<T> traverse() {
        // list to store the results of the traversal
        List<T> result = new ArrayList<>();
        // visit this node
        result.add(this.root.getValue());
        // visit left subtree
        result.addAll(traverse(this.root.visitLeft()));
        // visit right subtree
        result.addAll(traverse(this.root.visitLeft()));
        return result;
    }

    /**
     * traverses this tree recursively in pre-order.
     * if the node provided is a leaf node, return a list containing only that node's value.
     * @param subtree the root node of the subtree to traverse
     * @return the list containing the elements from the traversal
     */
    public List<T> traverse(BinaryNode<T> subtree) {
        List<T> result = new ArrayList<>();
        // visit this node
        result.add(subtree.getValue());
        if(subtree.hasLeft())
            // visit left subtree
            result.addAll(traverse(subtree.visitLeft()));
        if(subtree.hasRight())
            // visit right subtree
            result.addAll(traverse(subtree.visitRight()));
        // return the result
        return result;
    }

    /**
     * traverses this tree breadth first.
     * @return list containing the elements in order
     */
    public List<T> traverseBreadthFirst(){
        List<T> result = new ArrayList<>();
        //visit root
        result.add(this.root.getValue());
        // create queue
        ArrayDeque<BinaryNode<T>> queue = new ArrayDeque<>();
        // enqueue root
        queue.addFirst(this.root);
        while (queue.size() > 0){
            // add first node to result
            BinaryNode<T> node = queue.pop();
            result.add(node.getValue());
            // enqueue left child
            if(node.visitLeft() != null) result.add(node.visitLeft().getValue());
            //enqueue right child
            if(node.visitRight() != null) result.add(node.visitRight().getValue());
        }
        return result;
    }

    /**
     * returns the {@link Iterator} for this tree.
     * @return the iterator
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            final Iterator<T> iterator = traverse().iterator();

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public T next() {
                return iterator.next();
            }
        };
    }

    /**
     * returns the {@link Iterator} for this tree which yields nodes instead of the values.
     * @return the node iterator
     */
    public Iterator<Node<T>> nodeIterator(){
        // convert elements from traversal to node objs
        List<Node<T>> nodeList = new ArrayList<>();
        for(T elem : traverse()){
            nodeList.add(new BinaryNode<>(elem));
        }

        return new Iterator<>() {
            final Iterator<Node<T>> iterator = nodeList.iterator();

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Node<T> next() {
                return iterator.next();
            }
        };
    }

    /**
     * returns the string representation of this tree in pre-order.
     * @return the string representation of this tree
     */
    @Override
    public String toString() {
        return traverse().toString();
    }
}
