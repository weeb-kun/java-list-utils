# list-utils library
simple library for working with lists and list-like sequences.

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
A zipped List is a list that contains many tuples that consist of elements from the source iterables at some index `i`.