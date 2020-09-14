package main.java.com.weebkun.utils;

import main.java.com.weebkun.tuples.Tuple;

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
