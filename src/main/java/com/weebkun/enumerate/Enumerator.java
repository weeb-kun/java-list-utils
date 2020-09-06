package main.java.com.weebkun.enumerate;

import java.lang.module.FindException;
import java.util.Iterator;

public class Enumerator<T> implements Iterable<Item<T>> {

    private Iterable<T> target;
    private int start;

    public Enumerator(Iterable<T> target, int start){
        this.target = target;
        this.start = start;
    }

    public Iterator<Item<T>> iterator(){
        Iterator<T> target = this.target.iterator();

        return new Iterator<Item<T>>() {
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
