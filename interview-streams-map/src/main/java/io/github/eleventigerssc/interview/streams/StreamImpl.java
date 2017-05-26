package io.github.eleventigerssc.interview.streams;

import java.util.Iterator;

/**
 * Created by veon on 5/17/17.
 */

class StreamImpl<T> implements Stream<T> {

    private final Iterable<T> iterable;

    public StreamImpl(Iterable<T> iterable) {
        this.iterable = iterable;
    }

    @Override
    public Iterator<T> iterator() {
        return iterable.iterator();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        iterable.forEach(action::accept);
    }

    @Override
    public <R> Stream<R> map(Function<? super T, ? extends R> mapper) {
        return new StreamImpl<>(new MapIterable<T,R>(iterable, mapper));
    }

    @Override
    public <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper) {
       return new StreamImpl<>(new FlatMapIterable<T,R>(iterable, mapper));
    }

    @Override
    public Stream<T> filter(Predicate<? super T> predicate) {
        return new StreamImpl<>(new FilterIterable<>(iterable, predicate));
    }

}
