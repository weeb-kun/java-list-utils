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

import java.util.Iterator;

/**
 * Enumerator class that enumerates an iterable.
 * enumeration in this context means listing the element with its index in a pair.
 * this is an attempt at implementing the enumerate() function from python.
 * @param <T> the type of elements in the iterable.
 */
public class Enumerator<T> implements Iterable<Item<T>> {

    private Iterable<T> target;
    private int start;
    private T[] array;

    /**
     * constructor for this enumerator.
     *
     * @param target the target iterable
     * @param start the start index to start enumerating from
     */
    public Enumerator(Iterable<T> target, int start) {
        this.target = target;
        this.start = start;
    }

    /**
     * overloaded constructor that expects an array instead of an interable.
     * @param array the array
     * @param start the start index
     */
    public Enumerator(T[] array, int start) {
        this.array = array;
        this.target = null;
        this.start = start;
    }

    /**
     * returns the iterator for this Enumerator. returns an anonymous implementation of {@link Iterator}.
     * depending on what was passed during construction, this method will return a different {@code Iterator}.
     * @return the iterator
     */
    public Iterator<Item<T>> iterator() {
        if(this.target != null) {
            // check if target is not null. if target is null, that means an array was passed during construction.

            return new Iterator<>() {
                Iterator<T> iter = target.iterator();
                int idx = start;

                @Override
                public boolean hasNext() {
                    return iter.hasNext();
                }

                @Override
                public Item<T> next() {
                    return new Item<>(iter.next(), idx++);
                }
            };

        } else {
            // an array was passed when this object was constructed
            return new Iterator<>() {
                T[] target = array;
                int index;

                @Override
                public boolean hasNext() {
                    return this.target.length > this.index;
                }

                @Override
                public Item<T> next() {
                    return new Item<>(this.target[this.index], this.index++);
                }
            };
        }
    }
}
