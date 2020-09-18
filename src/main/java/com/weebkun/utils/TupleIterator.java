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

package com.weebkun.utils;

import com.weebkun.tuples.Tuple;

import java.util.Iterator;

/**
 * iterator class for tuples.
 * @see Tuple
 */
public class TupleIterator implements Iterator<Object> {

    private final Object[] array;
    private int index;

    /**
     * constructor for this iterator.
     * @param array the internal array used for the tuple implementation
     */
    public TupleIterator(Object[] array){
        this.array = array;
    }

    /**
     * returns true if there is another element.
     * @return boolean indication whether there is a next element
     */
    @Override
    public boolean hasNext() {
        return this.array.length > this.index;
    }

    /**
     * returns the next element in the tuple.
     * @return the next element
     */
    @Override
    public Object next() {
        return this.array[index++];
    }
}
