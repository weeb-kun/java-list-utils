# list-utils library
simple library for working with lists and list-like sequences.

## Installation
### gradle

```groovy
repositories {
    jcenter()
}

dependencies {
    implementation 'com.weebkun:list-utils:1.0.0'
}
```

### maven

```xml
<dependency>
  <groupId>com.weebkun</groupId>
  <artifactId>list-utils</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```

## ListUtil class
main class of this whole library.
this class provides the static methods necessary to use this library.

- enumerate
<br>
Enumerates through a collection and returns an iterable `Enumerator<T>`,
That when iterated through, yields `Item<T>`s, which contains an element and the index of that element in the original collection.
    - `static <T> Iterable<Item<T>> enumerate(Iterable<T>, int);`
    - `static <T> Iterable<Item<T>> enumerate(Iterable<T>);`

<br>

- comprehend
<br>
Java implementation of python's list comprehension.
4 overloads are provided, depending on usage requirements.
<br>
The `expression` argument is the expression applied to each element in the argument `list`.
The expression can be any lambda function that takes in the element and returns any object.
e.g. `x -> x * 2` or `x -> x % 2 == 0 ? "even" : "odd"`, etc
<br>
The `condition` argument is the condition to filter the elements in the `list` with.
It can be any `Predicate` that takes in the element and returns a boolean value.
    - `static <T> List<Object> comprehend(Function<T, Object> expression, Iterable<T> list, Predicate<T> condition);`
    - `static <T> List<Object> comprehend(Iterable<T> list, Predicate<T> condition);`
    - `static <T> List<Object> comprehend(Function<T, Object> expression, Iterable<T> list);`
    - `static <T> List<Object> comprehend(Iterable<T> list);`

- zip
<br>
Implementation of `zip()` function in python.
Takes in multiple iterable collections and returns a zipped list.
A zipped List is a list of tuples that consist of elements from the source iterables at some index `i`.
e.g. in python we have `zip([1, 2, 3], ["a", "b", "c"])`, the result is `[(1, "a"), (2, "b"), (3, "c")]`,
the same can be achieved in java by passing in iterables.
e.g.
```java
import com.weebkun.ListUtil;import main.java.com.weebkun.ListUtil;import java.util.ArrayList;import java.util.List;

class Test {

public static void main(String[] args){
    List<Integer> intList = new ArrayList<>();
    intList.add(1);
    intList.add(2);
    intList.add(3);
    
    List<String> strList = new ArrayList<>();
    strList.add("a");
    strList.add("b");
    strList.add("c");
    
    System.out.println(ListUtil.zip(intList, strList));
    }
}
```

## Documentation
Docs can be found at https://javadoc.io/com.weebkun/list-utils