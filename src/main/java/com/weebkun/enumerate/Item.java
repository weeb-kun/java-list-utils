package main.java.com.weebkun.enumerate;

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
