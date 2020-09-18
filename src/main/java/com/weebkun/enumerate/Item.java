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

package com.weebkun.enumerate;

/**
 * represents a pair of (index, element) in enumerated lists.
 * @param <T> the type of the item
 * @see Enumerator
 */
public class Item<T> {
    private final T element;
    private final int index;

    /**
     * constructor for Item that takes in the element and index of the element in {@link Iterable} collection.
     * @param elem the element
     * @param idx the index of the element
     */
    public Item(T elem, int idx){
        this.element = elem;
        this.index = idx;
    }

    /**
     * returns this element.
     * @return the element of this Item
     */
    public T getElement(){
        return this.element;
    }

    /**
     * returns the index of this element.
     * @return the index
     */
    public int getIndex(){
        return this.index;
    }
}
