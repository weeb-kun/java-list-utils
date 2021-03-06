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

package com.weebkun;

import com.weebkun.enumerate.Enumerator;
import com.weebkun.tuples.Tuple;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * util class for working with lists and collections.
 * @see com.weebkun.enumerate
 * @see com.weebkun.utils
 * @see com.weebkun.tuples
 */
public class ListUtil {

    /**
     * returns an {@link Enumerator} for a specified iterable starting at index {@code start}.
     * @param iter the iterable
     * @param start the start index. default is 0
     * @param <T> the type of the elements in {@code iter}
     * @return the enumerator object
     * @see Enumerator
     */
    public static <T> Enumerator<T> enumerate(Iterable<T> iter, int start){
        return new Enumerator<>(iter, start);
    }

    /**
     * returns an {@link Enumerator} for a specified iterable. equivalent to {@code enumerate(iter, 0)}.
     * @param iter the iterable
     * @param <T> the type of the elements in {@code iter}
     * @return the enumerator object
     * @see Enumerator
     */
    public static <T> Enumerator<T> enumerate(Iterable<T> iter){
        return new Enumerator<>(iter, 0);
    }

    /**
     * overload that is passed an array instead of a collection.
     * @param array the array
     * @param start the start index
     * @param <T> the type of elements in array
     * @return the Enumerator object
     * @see Enumerator
     */
    public static <T> Enumerator<T> enumerate(T[] array, int start) {
        return new Enumerator<>(array, start);
    }

    /**
     * overload for enumerate(T[] array, int start) that uses default value 0 for {@code start}.
     * @param array the array
     * @param <T> the type of element in array
     * @return the Enumerator object
     * @see Enumerator
     */
    public static <T> Enumerator<T> enumerate(T[] array) {
        return new Enumerator<T>(array, 0);
    }

    /**
     * returns a list containing elements generated from applying an expression to each element of the specified sequence,
     * and that meets the condition specified.
     * list comprehension implementation in java. similar to list comprehension in python.
     * e.g. [x for x in range(10)]
     * @param expression the expression to apply to each element. takes the form of a lambda expression. default is {@code x -> x}
     * @param list the sequence
     * @param condition the condition to filter the sequence with. default is {@code x -> true}
     * @param <T> the type of the elements in {@code list}
     * @return the new list
     * @see java.util.stream.Stream#map
     * @see java.util.stream.Stream#filter
     */
    public static <T> List<Object> comprehend(Function<T, Object> expression, Iterable<T> list, Predicate<T> condition){
        List<Object> rList = new ArrayList<>();
        for(T elem : list){
            //tests each element if it passes the predicate
            if(condition.test(elem)){
                // applies expression to elem and adds result to rList
                rList.add(expression.apply(elem));
            }
        }
        return rList;
    }

    /**
     * returns a list generated from a sequence, while filtering based on a predicate.
     * @param list the sequence
     * @param condition the condition to filter the sequence with
     * @param <T> the type of the elements in {@code list}
     * @return the new list
     * @see #comprehend(Function, Iterable, Predicate)
     */
    public static <T> List<Object> comprehend(Iterable<T> list, Predicate<T> condition){
        // no expression specified
        return ListUtil.comprehend(x -> x, list, condition);
    }

    /**
     * returns a list containing elements generated from applying an expression to each element of the specified sequence.
     * @param expression the expression to apply
     * @param list the sequence
     * @param <T> the type of the elements of {@code list}
     * @return the new list
     * @see #comprehend(Function, Iterable, Predicate)
     */
    public static <T> List<Object> comprehend(Function<T, Object> expression, Iterable<T> list){
        // no condition specified
        return ListUtil.comprehend(expression, list, x -> true);
    }

    /**
     * returns a list generated from a sequence.
     * uses defaults of {@code expression = x -> x} and {@code condition = x -> true}
     * @param list the sequence
     * @param <T> the type of the elements in {@code list}
     * @return the new list
     * @see #comprehend(Function, Iterable, Predicate)
     */
    public static <T> List<Object> comprehend(Iterable<T> list){
        // assuming expression is default and no condition specified.
        // python equivalent: [x for x in list]
        return ListUtil.comprehend(x -> x, list, x -> true);
    }

    /**
     * returns a zipped object containing tuples of elements constructed from the provided iterables.
     * attempted implementation of python zip() function.
     * @param iterables the iterables to zip
     * @return the zipped list
     */
    @SafeVarargs
    public static List<Tuple> zip(Collection<Object>... iterables){
        // loop through each collection and zip each element at the same index together into a Tuple
        // py: zip = zip([1, 2, 3, ...], ['a', 'b', 'c', ...], [...])
        // zip: [(1, 'a'), (2, 'b'), (3, 'c'), ...]

        // local variable to store iterators
        List<Iterator<Object>> iterators = new ArrayList<>();

        // zipped list to be returned at the end of this method.
        List<Tuple> zipped = new ArrayList<>();

        // step 1. get iterator for each iterable
        // step 2. calculate smallest size from iterables
        int smallest = Integer.MAX_VALUE;
        for (Collection<Object> iterable : iterables) {
            iterators.add(iterable.iterator());
            if(iterable.size() < smallest){
                smallest = iterable.size();
            }
        }

        //step 3. iterate and generate new list of tuples from i elements from provided iterables
        for(int i = 0; i < smallest; i++){
            // loop from index = 0 to smallest size iterable
            // in each iteration, add current element of each collection to new Tuple and then to zipped
            List<Object> elements = new ArrayList<>();
            for(Iterator<Object> iterator : iterators){
                elements.add(iterator.next());
            }
            //add elements at index i to zipped
            zipped.add(new Tuple(elements));
        }
        return zipped;
    }

    public static <T, R> List<R> map(List<T> list, Function<T, R> transform) {
        ArrayList<R> result = new ArrayList<>();
        for(T elem : list) {
            result.add(transform.apply(elem));
        }
        return result;
    }

    public static <T> List<T> filter(Collection<T> list, Predicate<T> predicate) {
        ArrayList<T> result = new ArrayList<>();
        for (T elem : list) {
            if(predicate.test(elem)) result.add(elem);
        }
        return result;
    }
}
