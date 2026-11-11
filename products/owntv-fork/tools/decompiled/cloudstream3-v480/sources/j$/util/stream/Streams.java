package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import j$.util.function.Consumer$CC;
import java.util.Comparator;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes3.dex */
abstract class Streams {

    private static abstract class AbstractStreamBuilderImpl implements Spliterator {
        int count;

        @Override // j$.util.Spliterator
        public int characteristics() {
            return 17488;
        }

        @Override // j$.util.Spliterator
        public /* synthetic */ Comparator getComparator() {
            return Spliterator.CC.$default$getComparator(this);
        }

        @Override // j$.util.Spliterator
        public /* synthetic */ long getExactSizeIfKnown() {
            return Spliterator.CC.$default$getExactSizeIfKnown(this);
        }

        @Override // j$.util.Spliterator
        public /* synthetic */ boolean hasCharacteristics(int i) {
            return Spliterator.CC.$default$hasCharacteristics(this, i);
        }

        @Override // j$.util.Spliterator
        public Spliterator trySplit() {
            return null;
        }

        private AbstractStreamBuilderImpl() {
        }

        @Override // j$.util.Spliterator
        public long estimateSize() {
            return (-this.count) - 1;
        }
    }

    static final class StreamBuilderImpl extends AbstractStreamBuilderImpl implements Consumer {
        SpinedBuffer buffer;
        Object first;

        public /* synthetic */ Consumer andThen(Consumer consumer) {
            return Consumer$CC.$default$andThen(this, consumer);
        }

        StreamBuilderImpl(Object obj) {
            super();
            this.first = obj;
            this.count = -2;
        }

        @Override // java.util.function.Consumer
        public void accept(Object obj) {
            int i = this.count;
            if (i == 0) {
                this.first = obj;
                this.count = i + 1;
            } else {
                if (i > 0) {
                    if (this.buffer == null) {
                        SpinedBuffer spinedBuffer = new SpinedBuffer();
                        this.buffer = spinedBuffer;
                        spinedBuffer.accept(this.first);
                        this.count++;
                    }
                    this.buffer.accept(obj);
                    return;
                }
                throw new IllegalStateException();
            }
        }

        @Override // j$.util.Spliterator
        public boolean tryAdvance(Consumer consumer) {
            Objects.requireNonNull(consumer);
            if (this.count != -2) {
                return false;
            }
            consumer.accept(this.first);
            this.count = -1;
            return true;
        }

        @Override // j$.util.Spliterator
        public void forEachRemaining(Consumer consumer) {
            Objects.requireNonNull(consumer);
            if (this.count == -2) {
                consumer.accept(this.first);
                this.count = -1;
            }
        }
    }

    static Runnable composeWithExceptions(final Runnable runnable, final Runnable runnable2) {
        return new Runnable() { // from class: j$.util.stream.Streams.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    runnable.run();
                    runnable2.run();
                } catch (Throwable th) {
                    try {
                        runnable2.run();
                    } catch (Throwable th2) {
                        try {
                            th.addSuppressed(th2);
                        } catch (Throwable unused) {
                        }
                    }
                    throw th;
                }
            }
        };
    }
}
