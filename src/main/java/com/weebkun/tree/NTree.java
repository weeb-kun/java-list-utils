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

import java.util.*;

/**
 * A data structure representing a generic tree where nodes can contain many children.
 * @param <T> the type of elements contained in this tree
 */
public class NTree<T> implements Tree<T>, Iterable<T> {

    private int size;
    private GenericNode<T> root;

    /**
     * default constructor for {@code NTree}.
     * sets size to 0.
     */
    public NTree(){
        this.size = 0;
    }

    /**
     * constructs an {@code NTree} with a given value.
     * @param value the value
     */
    public NTree(T value){
        this.root = new GenericNode<>(value);
        this.size = 1;
    }

    /**
     * constructs an {@code NTree} with a given root node.
     * @param root the root
     */
    public NTree(GenericNode<T> root) {
        this.root = root;
        this.size = root.size();
    }

    /**
     * returns the size of this tree.
     * @return the size
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * checks if this tree is empty. returns true if so.
     * @return true of empty
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * returns the root of this tree.
     * @return the root
     */
    @Override
    public Node<T> getRoot() {
        return this.root;
    }

    /**
     * returns the height of this tree.
     * @return the height
     * @see GenericNode#calculateHeight()
     * @see Tree#getHeight() for more details.
     */
    public int getHeight() {
        return this.root.calculateHeight();
    }

    /**
     * traverses this tree depth-first.
     * @return the list containing the elements in order.
     * @see BinaryTree#traverse()
     */
    @Override
    public List<T> traverse() {
        List<T> result = new ArrayList<>();
        // visit root
        result.add(this.root.getValue());
        // visit each child subtree
        result.addAll(traverse(this.root.getChildren()));
        return result;
    }

    /**
     * traverses the children of a node depth first.
     * @param children the list of children
     * @return list containing the elements in order
     */
    public List<T> traverse(List<Node<T>> children) {
        List<T> result = new ArrayList<>();
        for(Node<T> node : this.root.getChildren()) {
            // visit the child
            result.add(node.getValue());
            // visit the children of this node
            if(node.hasChildren()){
                result.addAll(traverse(node.getChildren()));
            }
        }
        return result;
    }

    /**
     * traverse this tree breadth first and returns a list of the elements in order.
     * @return list of the elements in order
     */
    public List<T> traverseBreadthFirst() {
        List<T> result = new ArrayList<>();
        // visit root
        result.add(this.root.getValue());
        // create queue
        Deque<Node<T>> queue = new ArrayDeque<>();
        // enqueue root
        queue.addFirst(this.root);
        while(queue.size() > 0){
            Node<T> node = queue.pop();
            result.add(node.getValue());
            // enqueue children starting from the left
            for(Node<T> child : this.root.getChildren()) {
                if(child != null) queue.addFirst(child);
            }
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
            Iterator<T> iterator = traverse().iterator();

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
     * returns the {@link Iterator} which yields the {@link Node}s themselves, not the values.
     * @return the node iterator
     */
    public Iterator<Node<T>> nodeIterator(){
        // convert elements from traversal to node objs
        List<Node<T>> nodeList = new ArrayList<>();
        for(T elem : traverse()){
            nodeList.add(new GenericNode<>(elem));
        }

        return new Iterator<>() {
            Iterator<Node<T>> iterator = nodeList.iterator();

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
     * returns the string representation of this tree in depth-first order.
     * @return the string representation of this tree
     */
    @Override
    public String toString() {
        return traverse().toString();
    }
}
