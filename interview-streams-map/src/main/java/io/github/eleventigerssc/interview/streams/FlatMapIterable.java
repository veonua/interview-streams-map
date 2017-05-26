package io.github.eleventigerssc.interview.streams;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by veon on 5/18/17.
 */

class FlatMapIterable<T, R> implements Iterable<R> {
    private final Iterable<T> iterable;
    private final Function<? super T, ? extends Stream<? extends R>> mapper;

    public FlatMapIterable(Iterable<T> iterable, Function<? super T, ? extends Stream<? extends R>> mapper) {
        this.iterable = iterable;
        this.mapper = mapper;
    }

    @Override
    public Iterator<R> iterator() {
        return new Iterator<R>() {
            Iterator<T> iterator = iterable.iterator();
            Iterator<? extends R> iin;
            @Override
            public boolean hasNext() {
                if (iterator==null) return false;

                if (iin == null || !iin.hasNext()) {
                    if (!iterator.hasNext()) return false;

                    Stream<? extends R> kk = mapper.call(iterator.next());
                    iin = kk.iterator();
                }

                return iterator!=null && iin!=null && iin.hasNext();
            }

            @Override
            public R next() {
                if (iin == null || !iin.hasNext()) {
                    Stream<? extends R> kk = mapper.call(iterator.next());
                    iin = kk.iterator();
                }

                return iin.next();
            }
        };
    }
}
