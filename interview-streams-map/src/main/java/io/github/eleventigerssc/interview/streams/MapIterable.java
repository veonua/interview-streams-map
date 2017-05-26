package io.github.eleventigerssc.interview.streams;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by veon on 5/17/17.
 */

class MapIterable<T, R> implements Iterable<R> {
    private final Iterable<T> iterable;
    private final Function<? super T, ? extends R> mapper;

    public MapIterable(Iterable<T> iterable, Function<? super T, ? extends R> mapper) {
        this.iterable = iterable;
        this.mapper = mapper;
    }

    @Override
    public Iterator<R> iterator() {
        return new Iterator<R>() {
            Iterator<T> iterator = iterable.iterator();
            @Override
            public boolean hasNext() {
                return iterator!=null && iterator.hasNext();
            }

            @Override
            public R next() {
                return mapper.call(iterator.next());
            }
        };
    }
}
