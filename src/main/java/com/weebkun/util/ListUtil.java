package main.java.com.weebkun.util;

import main.java.com.weebkun.enumerate.Enumerator;
import main.java.com.weebkun.enumerate.Item;

public class ListUtil {

    public static <T> Iterable<Item<T>> enumerate(Iterable<T> iter, int start){
        return new Enumerator<T>(iter, start);
    }

    public static <T> Iterable<Item<T>> enumerate(Iterable<T> iter){
        return new Enumerator<>(iter, 0);
    }
}
