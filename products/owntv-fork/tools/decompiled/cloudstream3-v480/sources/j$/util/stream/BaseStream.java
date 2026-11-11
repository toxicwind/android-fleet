package j$.util.stream;

import j$.util.Spliterator;
import j$.util.stream.BaseStream;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
public interface BaseStream<T, S extends BaseStream<T, S>> extends AutoCloseable {

    public final /* synthetic */ class VivifiedWrapper implements BaseStream {
        public final /* synthetic */ java.util.stream.BaseStream wrappedValue;

        private /* synthetic */ VivifiedWrapper(java.util.stream.BaseStream baseStream) {
            this.wrappedValue = baseStream;
        }

        public static /* synthetic */ BaseStream convert(java.util.stream.BaseStream baseStream) {
            if (baseStream == null) {
                return null;
            }
            if (baseStream instanceof Wrapper) {
                return BaseStream.this;
            }
            if (baseStream instanceof java.util.stream.DoubleStream) {
                return DoubleStream.VivifiedWrapper.convert((java.util.stream.DoubleStream) baseStream);
            }
            if (baseStream instanceof java.util.stream.IntStream) {
                return IntStream.VivifiedWrapper.convert((java.util.stream.IntStream) baseStream);
            }
            if (baseStream instanceof java.util.stream.LongStream) {
                return LongStream.VivifiedWrapper.convert((java.util.stream.LongStream) baseStream);
            }
            return baseStream instanceof java.util.stream.Stream ? Stream.VivifiedWrapper.convert((java.util.stream.Stream) baseStream) : new VivifiedWrapper(baseStream);
        }

        @Override // j$.util.stream.BaseStream, java.lang.AutoCloseable
        public /* synthetic */ void close() {
            this.wrappedValue.close();
        }

        public /* synthetic */ boolean equals(Object obj) {
            java.util.stream.BaseStream baseStream = this.wrappedValue;
            if (obj instanceof VivifiedWrapper) {
                obj = ((VivifiedWrapper) obj).wrappedValue;
            }
            return baseStream.equals(obj);
        }

        public /* synthetic */ int hashCode() {
            return this.wrappedValue.hashCode();
        }

        @Override // j$.util.stream.BaseStream
        public /* synthetic */ boolean isParallel() {
            return this.wrappedValue.isParallel();
        }

        @Override // j$.util.stream.BaseStream
        public /* synthetic */ Iterator iterator() {
            return this.wrappedValue.iterator();
        }

        @Override // j$.util.stream.BaseStream
        public /* synthetic */ BaseStream onClose(Runnable runnable) {
            return convert(this.wrappedValue.onClose(runnable));
        }

        @Override // j$.util.stream.BaseStream
        public /* synthetic */ BaseStream parallel() {
            return convert(this.wrappedValue.parallel());
        }

        @Override // j$.util.stream.BaseStream
        public /* synthetic */ BaseStream sequential() {
            return convert(this.wrappedValue.sequential());
        }

        @Override // j$.util.stream.BaseStream
        public /* synthetic */ Spliterator spliterator() {
            return Spliterator.VivifiedWrapper.convert(this.wrappedValue.spliterator());
        }

        @Override // j$.util.stream.BaseStream
        public /* synthetic */ BaseStream unordered() {
            return convert(this.wrappedValue.unordered());
        }
    }

    public final /* synthetic */ class Wrapper implements java.util.stream.BaseStream {
        private /* synthetic */ Wrapper() {
        }

        public static /* synthetic */ java.util.stream.BaseStream convert(BaseStream baseStream) {
            if (baseStream == null) {
                return null;
            }
            if (baseStream instanceof VivifiedWrapper) {
                return ((VivifiedWrapper) baseStream).wrappedValue;
            }
            if (baseStream instanceof DoubleStream) {
                return DoubleStream.Wrapper.convert((DoubleStream) baseStream);
            }
            if (baseStream instanceof IntStream) {
                return IntStream.Wrapper.convert((IntStream) baseStream);
            }
            if (baseStream instanceof LongStream) {
                return LongStream.Wrapper.convert((LongStream) baseStream);
            }
            return baseStream instanceof Stream ? Stream.Wrapper.convert((Stream) baseStream) : new Wrapper();
        }

        @Override // java.util.stream.BaseStream, java.lang.AutoCloseable
        public /* synthetic */ void close() {
            BaseStream.this.close();
        }

        public /* synthetic */ boolean equals(Object obj) {
            BaseStream baseStream = BaseStream.this;
            if (obj instanceof Wrapper) {
                obj = BaseStream.this;
            }
            return baseStream.equals(obj);
        }

        public /* synthetic */ int hashCode() {
            return BaseStream.this.hashCode();
        }

        @Override // java.util.stream.BaseStream
        public /* synthetic */ boolean isParallel() {
            return BaseStream.this.isParallel();
        }

        @Override // java.util.stream.BaseStream
        public /* synthetic */ Iterator iterator() {
            return BaseStream.this.iterator();
        }

        @Override // java.util.stream.BaseStream
        public /* synthetic */ java.util.stream.BaseStream onClose(Runnable runnable) {
            return convert(BaseStream.this.onClose(runnable));
        }

        @Override // java.util.stream.BaseStream
        public /* synthetic */ java.util.stream.BaseStream parallel() {
            return convert(BaseStream.this.parallel());
        }

        @Override // java.util.stream.BaseStream
        public /* synthetic */ java.util.stream.BaseStream sequential() {
            return convert(BaseStream.this.sequential());
        }

        @Override // java.util.stream.BaseStream
        public /* synthetic */ java.util.Spliterator spliterator() {
            return Spliterator.Wrapper.convert(BaseStream.this.spliterator());
        }

        @Override // java.util.stream.BaseStream
        public /* synthetic */ java.util.stream.BaseStream unordered() {
            return convert(BaseStream.this.unordered());
        }
    }

    @Override // java.lang.AutoCloseable
    void close();

    boolean isParallel();

    Iterator<T> iterator();

    BaseStream onClose(Runnable runnable);

    BaseStream parallel();

    BaseStream sequential();

    Spliterator spliterator();

    BaseStream unordered();
}
