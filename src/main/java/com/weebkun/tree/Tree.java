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

import java.util.List;

/**
 * ADT provided for a tree.
 * This interface describes a general structure of how a tree should be.
 * note: this is in no way related to {@link java.util.TreeMap} or {@link java.util.TreeSet} that already exists in java.
 * @param <T> the type of values that will be stored in this tree
 */
public interface Tree<T> extends Iterable<T> {

    /**
     * returns the number of nodes in this tree.
     * @return the number of nodes
     */
    int size();

    /**
     * checks if this tree is empty.
     * @return true if this tree is empty
     */
    boolean isEmpty();

    /**
     * returns the root node of this tree.
     * @return the root
     */
    Node<T> getRoot();

    /**
     * traverses the tree and returns a list containing the values.
     * the order in which to traverse is left up to the implementor.
     * @return the list
     */
    List<T> traverse();

    /**
     * returns the height of this tree.
     * height is calculated by the number of edges from the root to the furthest leaf node in this tree.
     * @return the height of this tree
     */
    int getHeight();
}
