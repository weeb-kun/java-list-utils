package main.java.com.weebkun.enumerate;

public class Item<T> {
    private T item;
    private int index;

    public Item(T item, int idx){
        this.item = item;
        this.index = idx;
    }

    public T getItem(){
        return this.item;
    }

    public int getIndex(){
        return this.index;
    }
}
