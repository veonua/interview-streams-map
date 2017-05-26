package io.github.eleventigerssc.interview.streams;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by veon on 5/17/17.
 */

class FilterIterable<T> implements Iterable<T> {
    private final Iterable<T> iterable;
    private final Predicate<? super T> predicate;

    public FilterIterable(Iterable<T> iterable, Predicate<? super T> predicate) {
        this.iterable = iterable;
        this.predicate = predicate;
    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Iterator<T> iterator = iterable.iterator();
            private T nextObject;
            private boolean nextObjectSet = false;

            @Override
            public boolean hasNext() {
                if ( nextObjectSet ) {
                    return true;
                } else {
                    return setNextObject();
                }
            }

            private boolean setNextObject() {
                while ( iterator.hasNext() ) {
                    T object = iterator.next();
                    if ( predicate.test( object ) ) {
                        nextObject = object;
                        nextObjectSet = true;
                        return true;
                    }
                }
                return false;
            }

            public T next() {
                if ( !nextObjectSet ) {
                    if (!setNextObject()) {
                        throw new NoSuchElementException();
                    }
                }
                nextObjectSet = false;
                return nextObject;
            }
        };
    }
}
