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
import java.util.List;

/**
 * Represents a node in a tree.
 * @param <T> the type of value this node stores
 */
public interface Node<T> {

    /**
     * adds a value as a child to this node.
     * @param value the value
     * @throws IllegalArgumentException if {@code value} is null
     */
    void add(T value) throws IllegalArgumentException;

    /**
     * returns the size of this node's subtree.
     * @return the size of this subtree
     */
    int size();

    /**
     * returns the tree with this node as root.
     * @return a tree with this node as root
     */
    Tree<T> subtree();

    /**
     * returns the parent node.
     * @return the parent node
     */
    @Nullable
    Node<T> getParent();

    /**
     * returns the value of this node.
     * @return this node's value.
     */
    T getValue();

    /**
     * returns a list of children nodes.
     * @return the children nodes
     */
    List<Node<T>> getChildren();

    /**
     * visits the nth child where index = n.
     * @param index the child at that index
     * @return the child node at {@code index}
     * @throws IndexOutOfBoundsException if index is out of range
     */
    Node<T> visit(int index) throws IndexOutOfBoundsException;

    /**
     * checks if this node has children.
     * @return true if this node has children
     */
    boolean hasChildren();

    /**
     * updates the value of this node.
     * @param value the new value
     * @throws IllegalArgumentException if {@code value} is null
     */
    void update(T value) throws IllegalArgumentException;

    /**
     * calculate the height of this node from the furthest leaf.
     * height is calculated as the number of edges to the the furthest leaf node.
     * if this is a leaf node, returns 0.
     * @return the height of this node
     */
    int calculateHeight();
}
