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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Immutable list implementation. similar to {@link com.weebkun.tuples.Tuple} but a more direct implementation of {@link List}.
 * uses an internal array to store the elements, similar to {@link java.util.ArrayList}.
 * all mutating operations throw {@link UnsupportedOperationException}.
 * @param <T> the type of element to store in this list. elements must implement {@code Cloneable}
 * @see com.weebkun.tuples.Tuple
 * @see ArrayList
 * @see UnsupportedOperationException
 */
@SuppressWarnings("unchecked")
public class ImmutableList<T extends Cloneable> extends ArrayList<T> implements List<T> {

    private final int size;
    private final T[] array;

    /**
     * creates an ImmutableList from an existing collection.
     * @param collection the collection
     */
    public ImmutableList(Collection<T> collection){
        this.array = (T[]) collection.toArray();
        this.size = collection.size();
    }

    /**
     * creates an ImmutableList from an array.
     * calls Arrays.copyOf() method to create a copy of the passed in array.
     * @param array the array
     */
    public ImmutableList(T[] array){
        this.array = Arrays.copyOf(array, array.length);
        this.size = array.length;
    }

    /**
     * gets the size of this {@code ImmutableList}.
     * @return the size of this list
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * returns true if this list is empty.
     * @return true if empty
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * returns true if the object is in this list.
     * @param o the object to check
     * @return true if object is in the list
     */
    @Override
    public boolean contains(Object o) {
        return Arrays.asList(this.array).contains(o);
    }

    /**
     * converts this list to an array.
     * @return the array representation of this list
     */
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(this.array, this.size);
    }

    /**
     * returns the string representation of this list.
     * @return the string representation
     */
    public String toString(){
        return Arrays.toString(this.array);
    }

    @Override
    public boolean add(T t) throws UnsupportedOperationException{
        throw new UnsupportedOperationException("this list is immutable.");
    }

    @Override
    public boolean remove(Object o) throws UnsupportedOperationException{
        throw new UnsupportedOperationException("this list is immutable.");
    }

    @Override
    public boolean addAll(Collection<? extends T> c) throws UnsupportedOperationException{
        throw new UnsupportedOperationException("this list is immutable.");
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) throws UnsupportedOperationException{
        throw new UnsupportedOperationException("this list is immutable.");
    }

    @Override
    public boolean removeAll(Collection<?> c) throws UnsupportedOperationException{
        throw new UnsupportedOperationException("this list is immutable.");
    }

    @Override
    public boolean retainAll(Collection<?> c) throws UnsupportedOperationException{
        throw new UnsupportedOperationException("this list is immutable.");
    }

    @Override
    public void clear() throws UnsupportedOperationException{
        throw new UnsupportedOperationException("this list is immutable.");
    }

    /**
     * gets the element in this list at the specified index.
     * @param index the index
     * @return the element
     */
    @Override
    public T get(int index) {
        return this.array[index];
    }

    @Override
    public T set(int index, T element) throws UnsupportedOperationException{
        throw new UnsupportedOperationException("this list is immutable.");
    }

    @Override
    public void add(int index, T element) throws UnsupportedOperationException{
        throw new UnsupportedOperationException("this list is immutable.");
    }

    @Override
    public T remove(int index) throws UnsupportedOperationException{
        throw new UnsupportedOperationException("this list is immutable.");
    }

    /**
     * does a shallow copy of this {@code ImmutableList}.
     * @return the cloned list
     */
    @Override
    public ImmutableList<T> clone() {
        return new ImmutableList<>(this);
    }

    /**
     * does a deep copy of this list. returns a new list with each element having a new reference.
     * @return the cloned list
     */
    public ImmutableList<T> deepClone(){
        // intermediate list to store the new references
        List<T> inter = new ArrayList<>();
        for(T elem : this) {
            try {
                // clone each element using reflection
                Method clone = elem.getClass().getMethod("clone");
                inter.add((T) clone.invoke(elem));
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        return new ImmutableList<>(inter);
    }
}
