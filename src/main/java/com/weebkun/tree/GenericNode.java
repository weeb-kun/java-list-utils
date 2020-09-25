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

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a node in an N-tree.
 * @param <T> the type of values this node will have
 */
public class GenericNode<T> implements Node<T>{

    private Node<T> parent;
    private T element;
    private List<Node<T>> children;

    /**
     * creates a root node.
     * @param value the value to store
     */
    public GenericNode(T value){
        this.element = value;
        this.children = new ArrayList<>();
    }

    /**
     * creates a child node in a tree. expects the value as well as the parent node.
     * @param value the value
     * @param parent the parent
     */
    public GenericNode(T value, Node<T> parent){
        this.element = value;
        this.children = new ArrayList<>();
        this.parent = parent;
    }

    /**
     * adds a new element as a child to this node.
     * @param value the value
     * @throws IllegalArgumentException if the value is null
     */
    @Override
    public void add(T value) throws IllegalArgumentException {
        if(value == null) throw new IllegalArgumentException("value cannot be null.");
        this.children.add(new GenericNode<>(value, this));
    }

    /**
     * calculates the height of this node to the furthest leaf.
     * @return the height of this node
     * @see Node#calculateHeight() for further explanation.
     */
    @Override
    public int calculateHeight() {
        // get heights of all children
        List<Integer> heightList = new ArrayList<>();
        for(Node<T> node : this.children) {
            heightList.add(node != null ? node.calculateHeight() : -1);
        }

        // return the largest height
        return Collections.max(heightList) + 1;
    }

    /**
     * returns the size of this node's subtree.
     * @return the size of this subtree
     */
    @Override
    public int size() {
        return subtree().size();
    }

    /**
     * returns a subtree of this node.
     * @return the subtree
     */
    @Override
    public Tree<T> subtree() {
        return new NTree<>(this);
    }

    /**
     * returns this node's value.
     * @return the value
     */
    @Override
    public T getValue(){
        return this.element;
    }

    /**
     * returns the parent of this node.
     * may return null.
     * @return the parent
     */
    @Nullable
    @Override
    public Node<T> getParent() {
        return null;
    }

    /**
     * returns a list of children of this node.
     * @return the list of children
     */
    @Override
    public List<Node<T>> getChildren() {
        return this.children;
    }

    /**
     * returns the list of children as their values.
     * @return list of children
     */
    public List<T> getChildrenAsValues() {
        List<T> result = new ArrayList<>();
        for(Node<T> node : this.children){
            result.add(node.getValue());
        }
        return result;
    }

    /**
     * returns true if this node has any children.
     * @return true if this node has children
     */
    @Override
    public boolean hasChildren() {
        return getChildren() != null;
    }

    @Override
    public Node<T> visit(int index) throws IndexOutOfBoundsException{
        if(index < 0 || index >= this.children.size()) throw new IndexOutOfBoundsException("index not found.");
        return this.children.get(index);
    }

    /**
     * updates this node's value.
     * @param value the new value
     * @throws IllegalArgumentException if the value is null
     */
    public void update(T value) throws IllegalArgumentException{
        if(value == null) throw new IllegalArgumentException("value cannot be null.");
        this.element = value;
    }
}
