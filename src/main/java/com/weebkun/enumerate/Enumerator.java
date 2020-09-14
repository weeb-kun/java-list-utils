package main.java.com.weebkun.enumerate;

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

    /**
     * constructor for this enumerator.
     * @param target the target iterable
     * @param start the start index to start enumerating from
     */
    public Enumerator(Iterable<T> target, int start){
        this.target = target;
        this.start = start;
    }

    /**
     * returns the iterator for this Enumerator. returns an anonymous implementation of {@link Iterator}
     * @return the iterator
     */
    public Iterator<Item<T>> iterator(){
        Iterator<T> target = this.target.iterator();

        return new Iterator<>() {
            int idx = start;

            @Override
            public boolean hasNext() {
                return target.hasNext();
            }

            @Override
            public Item<T> next() {
                Item<T> item = new Item<>(target.next(), idx);
                idx++;
                return item;
            }
        };
    }
}
