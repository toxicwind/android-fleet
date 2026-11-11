package j$.nio.file;

import j$.lang.Iterable;
import j$.util.Spliterator;
import j$.util.function.Consumer$CC;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.util.Iterator;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes3.dex */
public abstract class PathApiFlips {
    public static Iterator flipIteratorPath(Iterator it) {
        return new IteratorPathWrapper(it);
    }

    public static Iterable flipIterablePath(Iterable iterable) {
        return new IterablePathWrapper(iterable);
    }

    public static DirectoryStream flipDirectoryStreamPath(DirectoryStream directoryStream) {
        return new DirectoryStreamPathWrapper(directoryStream);
    }

    public static DirectoryStream.Filter flipDirectoryStreamFilterPath(DirectoryStream.Filter filter) {
        return new DirectoryStreamFilterWrapper(filter);
    }

    public static Object convertPath(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Path) {
            return Path.Wrapper.convert((Path) obj);
        }
        return obj instanceof java.nio.file.Path ? Path.VivifiedWrapper.convert((java.nio.file.Path) obj) : obj;
    }

    public static class DirectoryStreamFilterWrapper implements DirectoryStream.Filter {
        private final DirectoryStream.Filter filter;

        public DirectoryStreamFilterWrapper(DirectoryStream.Filter filter) {
            this.filter = filter;
        }

        @Override // java.nio.file.DirectoryStream.Filter
        public boolean accept(Object obj) {
            return this.filter.accept(PathApiFlips.convertPath(obj));
        }
    }

    public static class IterablePathWrapper implements Iterable, Iterable {
        private final Iterable iterable;

        @Override // java.lang.Iterable, j$.lang.Iterable, j$.util.Collection
        public /* synthetic */ Spliterator spliterator() {
            return Iterable.CC.$default$spliterator(this);
        }

        @Override // java.lang.Iterable
        public /* synthetic */ java.util.Spliterator spliterator() {
            return Spliterator.Wrapper.convert(spliterator());
        }

        public IterablePathWrapper(Iterable iterable) {
            this.iterable = iterable;
        }

        @Override // java.lang.Iterable
        public Iterator iterator() {
            return new IteratorPathWrapper(this.iterable.iterator());
        }

        static /* synthetic */ void lambda$forEach$0(Consumer consumer, Object obj) {
            consumer.accept(PathApiFlips.convertPath(obj));
        }

        @Override // java.lang.Iterable, j$.lang.Iterable
        public void forEach(final Consumer consumer) {
            Iterable.EL.forEach(this.iterable, new Consumer() { // from class: j$.nio.file.PathApiFlips.IterablePathWrapper.0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    IterablePathWrapper.lambda$forEach$0(consumer, obj);
                }

                public /* synthetic */ Consumer andThen(Consumer consumer2) {
                    return Consumer$CC.$default$andThen(this, consumer2);
                }
            });
        }
    }

    public static class IteratorPathWrapper implements Iterator {
        private final Iterator iterator;

        public IteratorPathWrapper(Iterator it) {
            this.iterator = it;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        @Override // java.util.Iterator
        public Object next() {
            return PathApiFlips.convertPath(this.iterator.next());
        }

        @Override // java.util.Iterator
        public void remove() {
            this.iterator.remove();
        }
    }

    public static class DirectoryStreamPathWrapper implements DirectoryStream, Iterable {
        private final DirectoryStream directoryStream;

        @Override // java.lang.Iterable, j$.lang.Iterable, j$.util.Collection
        public /* synthetic */ Spliterator spliterator() {
            return Iterable.CC.$default$spliterator(this);
        }

        @Override // java.lang.Iterable
        public /* synthetic */ java.util.Spliterator spliterator() {
            return Spliterator.Wrapper.convert(spliterator());
        }

        public DirectoryStreamPathWrapper(DirectoryStream directoryStream) {
            this.directoryStream = directoryStream;
        }

        @Override // java.nio.file.DirectoryStream, java.lang.Iterable
        public Iterator iterator() {
            return new IteratorPathWrapper(this.directoryStream.iterator());
        }

        static /* synthetic */ void lambda$forEach$0(Consumer consumer, Object obj) {
            consumer.accept(PathApiFlips.convertPath(obj));
        }

        @Override // java.lang.Iterable, j$.lang.Iterable
        public void forEach(final Consumer consumer) {
            Iterable.EL.forEach(this.directoryStream, new Consumer() { // from class: j$.nio.file.PathApiFlips.DirectoryStreamPathWrapper.0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    DirectoryStreamPathWrapper.lambda$forEach$0(consumer, obj);
                }

                public /* synthetic */ Consumer andThen(Consumer consumer2) {
                    return Consumer$CC.$default$andThen(this, consumer2);
                }
            });
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.directoryStream.close();
        }
    }
}
