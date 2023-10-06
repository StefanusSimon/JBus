package StefanusSimonJBusRS;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Arrays;
import java.util.List;

public class Algorithm {

    private Algorithm() {

    }

    public static <T> boolean exists(Iterator<T> iterator, Predicate<T> pred) {
        while (iterator.hasNext()){
            T current = iterator.next();
            if(pred.predicate(current))
                return true;
        }
        return false;
    }

    public static <T> boolean exists(Iterator<T> iterator, T value) {
        final Predicate<T> pred = value::equals;
        return exists(iterator, pred);
    }

    public static <T> boolean exists(Iterable<T> iterable, Predicate<T> pred) {
        final Iterator<T> it = iterable.iterator();
        return exists(it, pred);
    }

    public static <T> boolean exists(Iterable<T> iterable, T value) {
        final Iterator<T> it = iterable.iterator();
        return exists(it, value);
    }

    public static <T> boolean exists(T[] tArr, T value) {
        final Iterator<T> it = Arrays.stream(tArr).iterator();
        return exists(it, value);
    }

    public static <T> boolean exists(T[] tArr, Predicate<T> pred) {
        final Iterator<T> it = Arrays.stream(tArr).iterator();
        return exists(it, pred);
    }

    public static <T> List<T> collect(Iterable<T> iterable, Predicate<T> pred){
        final Iterator<T> it = iterable.iterator();
        return collect(it, pred);
    }
    public static <T> List<T> collect(Iterable<T> iterable, T value){
        final Iterator<T> it = iterable.iterator();
        return collect(it, value);
    }
    public static <T> List<T> collect(T[] tArr, T value){
        final Iterator<T>  it = Arrays.stream(tArr).iterator();
        return collect(it, value);
    }
    public static <T> List<T> collect(T[] tArr, Predicate<T> pred){
        final Iterator<T> it = Arrays.stream(tArr).iterator();
        return collect(it, pred);
    }
    public static <T> List<T> collect(Iterator<T> iterator, T value){
        final Predicate<T> pred = value::equals;
        return collect(iterator, pred);
    }
    public static <T> List<T> collect(Iterator<T> iterator, Predicate<T> pred){
        List<T> list = new ArrayList<>();
        while (iterator.hasNext()){
            T current = iterator.next();
            if(pred.predicate(current))
                list.add(current);
        }
        return list;
    }
    public static <T> int count(Iterator<T> iterator, T value){
        final Predicate<T> pred = value::equals;
        return count(iterator, pred);
    }
    public static <T> int count(T[] tArr, T value){
        final Iterator<T>  it = Arrays.stream(tArr).iterator();
        return count(it, value);
    }
    public static <T> int count(Iterator<T> iterator, Predicate<T> pred){
        int x = 0;
        while (iterator.hasNext()){
            T current = iterator.next();
            if(pred.predicate(current))
             x++;
        }
        return x;
    }
    public static <T> int count(T[] tArr, Predicate<T> pred){
        final Iterator<T> it = Arrays.stream(tArr).iterator();
        return count(it, pred);
    }
    public static <T> int count(Iterable<T> iterable, T value){
        final Iterator<T> it = iterable.iterator();
        return count(it, value);
    }
    public static <T> int count(Iterable<T> iterable, Predicate<T> pred){
        final Iterator<T> it = iterable.iterator();
        return count(it, pred);
    }


    public static <T> T find(Iterator<T> iterator, T value){
        final Predicate<T> pred = value::equals;
        return find(iterator, pred);
    }
    public static <T> T find(T[] tArr, T value){
        final Iterator<T>  it = Arrays.stream(tArr).iterator();
        return find(it, value);
    }
    public static <T> T find(Iterator<T> iterator, Predicate<T> pred){
        while (iterator.hasNext()){
            T current = iterator.next();
            if(pred.predicate(current))
                return current;
        }
        return null;
    }
    public static <T> T find(T[] tArr, Predicate<T> pred){
        final Iterator<T> it = Arrays.stream(tArr).iterator();
        return find(it, pred);
    }
    public static <T> T find(Iterable<T> iterable, T value){
        final Iterator<T> it = iterable.iterator();
        return find(it, value);
    }
    public static <T> T find(Iterable<T> iterable, Predicate<T> pred){
        final Iterator<T> it = iterable.iterator();
        return find(it, pred);
    }
}
