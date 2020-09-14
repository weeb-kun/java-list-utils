package main.java.com.weebkun.tuples;

import main.java.com.weebkun.utils.TupleIterator;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * Tuple class representing an immutable collection. uses underlying array for implementation.
 * null values are not allowed.
 * List-like implementation but does not actually implement List.
 * cloning and copying operations cannot guarantee deep copies especially with non-primitive elements.
 */
public class Tuple implements Iterable<Object>, Cloneable {

    private Object[] array;
    /**
     * the length of the tuple
     */
    public int length;

    /**
     * instantiates a Tuple with given Object array.
     * @param array the array to use
     * @throws NullPointerException if the given {@code array} contains {@code null} elements
     */
    public Tuple(Object[] array) throws NullPointerException{
        for (Object elem : array){
            if(elem == null) throw new NullPointerException("elements cannot be null.");
        }
        // shallow copy the array so this doesnt share the same reference as to what is passed in
        this.array = Arrays.copyOf(array, array.length);
        this.length = array.length;
    }

    /**
     * overloaded constructor which takes in a collection instead.
     * @param collection the collection
     * @throws NullPointerException if the given {@code collection} contains {@code null} elements
     */
    public Tuple(Collection<Object> collection) throws NullPointerException {
        for (Object elem : collection) if (elem == null) throw new NullPointerException("elements cannot be null.");
        this.array = collection.toArray();
        this.length = collection.size();
    }

    /**
     * copy constructor for Tuple class. may be used to clone a new {@code Tuple}.
     * @param tuple the source tuple to copy from
     */
    public Tuple(Tuple tuple){
        this.array = tuple.array;
        this.length = tuple.length;
    }

    /**
     * static factory method for creating tuples with varargs, since its confusing when using the normal constructor with arrays.
     * @param elements the elements to instantiate the tuple with
     * @return tuple object containing the elements provided
     */
    public static Tuple create(Object... elements){
        for (Object elem : elements) if (elem == null) throw new NullPointerException("elements cannot be null.");
        return new Tuple(elements);
    }

    /**
     * returns the element at the specified index.
     * @param index the index of the element to retrieve
     * @return the element at
     */
    public Object get(int index) throws IndexOutOfBoundsException{
        if(index < 0 || index >= this.length) throw new IndexOutOfBoundsException("index out of range of tuple");
        return this.array[index];
    }

    /**
     * checks if a given element exists in this tuple.
     * @param e the element to search
     * @return true if {@code e} is found
     */
    public boolean contains(Object e){
        for(Object elem : this.array){
            if(elem == e){
                return true;
            }
        }
        return false;
    }

    /**
     * converts this tuple to an array of the same length.
     * @return the array representation of this tuple
     */
    public Object[] toArray(){
        return Arrays.copyOf(this.array, this.array.length);
    }

    /**
     * returns the string representation of this tuple, which is the elements listed in the same form as an array.
     * @return the string representing this tuple
     */
    @Override
    public String toString() {
        return String.format("Tuple: %s", Arrays.toString(this.array));
    }

    /**
     * clones this tuple and returns a copy. calls {@code super.clone()} from {@link Object}.
     * @return a copy of this tuple
     * @throws CloneNotSupportedException if cloning fails somehow
     */
    public Tuple clone() throws CloneNotSupportedException{
        Tuple tuple = (Tuple) super.clone();
        tuple.array = Arrays.copyOf(this.array, this.array.length);
        return tuple;
    }

    /**
     * wrapper method for {@code new Tuple(this)}
     * @return the deep copy of this tuple
     */
    public Tuple copy(){
        return new Tuple(this);
    }

    /**
     * returns an iterator for this tuple.
     * @return the iterator
     */
    public Iterator<Object> iterator(){
        return new TupleIterator(this.array);
    }
}