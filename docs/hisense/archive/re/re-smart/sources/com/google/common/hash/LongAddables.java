package com.google.common.hash;

import com.google.common.base.Supplier;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes.dex */
public final class LongAddables {
    public static final Supplier<LongAddable> SUPPLIER;

    private static final class PureJavaLongAddable extends AtomicLong implements LongAddable {
        public PureJavaLongAddable() {
        }

        @Override // com.google.common.hash.LongAddable
        public void add(long j) {
            getAndAdd(j);
        }

        @Override // com.google.common.hash.LongAddable
        public void increment() {
            getAndIncrement();
        }

        @Override // com.google.common.hash.LongAddable
        public long sum() {
            return get();
        }

        public /* synthetic */ PureJavaLongAddable(AnonymousClass1 anonymousClass1) {
        }
    }

    static {
        Supplier<LongAddable> supplier;
        try {
            new LongAdder();
            supplier = new Supplier<LongAddable>() { // from class: com.google.common.hash.LongAddables.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.google.common.base.Supplier
                public LongAddable get() {
                    return new LongAdder();
                }
            };
        } catch (Throwable unused) {
            supplier = new Supplier<LongAddable>() { // from class: com.google.common.hash.LongAddables.2
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.google.common.base.Supplier
                public LongAddable get() {
                    return new PureJavaLongAddable(null);
                }
            };
        }
        SUPPLIER = supplier;
    }

    public static LongAddable create() {
        return SUPPLIER.get();
    }
}
