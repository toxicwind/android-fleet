package dev.whyoleg.cryptography.providers.jdk;

import android.R;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: pooling.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0003\u0018\u0019\u001aB\u0017\bD\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\t\u001a\u00028\u0000H¤\u0080\u0004¢\u0006\u0002\u0010\nJ\u0017\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00028\u0000H¤\u0080\u0004¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010H\u0086\u0080\u0004J,\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00102\u0017\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\f0\u0012¢\u0006\u0002\b\u0013H\u0086\u0088\u0004ø\u0001\u0000J,\u0010\u0014\u001a\u0002H\u0015\"\u0004\b\u0001\u0010\u00152\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u0002H\u00150\u0012H\u0086\u0088\u0004ø\u0001\u0000¢\u0006\u0002\u0010\u0017R\u001b\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0084\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u0082\u0001\u0002\u001b\u001c\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u001d"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/Pooled;", ExifInterface.GPS_DIRECTION_TRUE, "", "instantiate", "Lkotlin/Function0;", "<init>", "(Lkotlin/jvm/functions/Function0;)V", "getInstantiate", "()Lkotlin/jvm/functions/Function0;", "borrow", "()Ljava/lang/Object;", "recycle", "", ES6Iterator.VALUE_PROPERTY, "(Ljava/lang/Object;)V", "borrowResource", "Ldev/whyoleg/cryptography/providers/jdk/Pooled$Resource;", "initialize", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "use", "R", "block", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "Empty", "Cached", "Resource", "Ldev/whyoleg/cryptography/providers/jdk/Pooled$Cached;", "Ldev/whyoleg/cryptography/providers/jdk/Pooled$Empty;", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class Pooled<T> {
    private final Function0<T> instantiate;

    public /* synthetic */ Pooled(Function0 function0, DefaultConstructorMarker defaultConstructorMarker) {
        this(function0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract T borrow();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void recycle(T value);

    /* JADX INFO: compiled from: pooling.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0017\bF\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\u0007\u001a\u00028\u0001H\u0094\u0080\u0004¢\u0006\u0002\u0010\bJ\u0017\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00028\u0001H\u0094\u0080\u0004¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/Pooled$Empty;", ExifInterface.GPS_DIRECTION_TRUE, "Ldev/whyoleg/cryptography/providers/jdk/Pooled;", "instantiate", "Lkotlin/Function0;", "<init>", "(Lkotlin/jvm/functions/Function0;)V", "borrow", "()Ljava/lang/Object;", "recycle", "", ES6Iterator.VALUE_PROPERTY, "(Ljava/lang/Object;)V", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Empty<T> extends Pooled<T> {
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // dev.whyoleg.cryptography.providers.jdk.Pooled
        public void recycle(T value) {
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Empty(Function0<? extends T> instantiate) {
            super(instantiate, null);
            Intrinsics.checkNotNullParameter(instantiate, "instantiate");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // dev.whyoleg.cryptography.providers.jdk.Pooled
        public T borrow() {
            return getInstantiate().invoke();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Pooled(Function0<? extends T> function0) {
        this.instantiate = function0;
    }

    protected final Function0<T> getInstantiate() {
        return this.instantiate;
    }

    /* JADX INFO: compiled from: pooling.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0017\bF\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\t\u001a\u00028\u0001H\u0094\u0080\u0004¢\u0006\u0002\u0010\nJ\u0017\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00028\u0001H\u0094\u0080\u0004¢\u0006\u0002\u0010\u000eR\u0015\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00010\bX\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/Pooled$Cached;", ExifInterface.GPS_DIRECTION_TRUE, "Ldev/whyoleg/cryptography/providers/jdk/Pooled;", "instantiate", "Lkotlin/Function0;", "<init>", "(Lkotlin/jvm/functions/Function0;)V", "pooled", "Lkotlin/collections/ArrayDeque;", "borrow", "()Ljava/lang/Object;", "recycle", "", ES6Iterator.VALUE_PROPERTY, "(Ljava/lang/Object;)V", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Cached<T> extends Pooled<T> {
        private final ArrayDeque<T> pooled;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Cached(Function0<? extends T> instantiate) {
            super(instantiate, null);
            Intrinsics.checkNotNullParameter(instantiate, "instantiate");
            this.pooled = new ArrayDeque<>();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // dev.whyoleg.cryptography.providers.jdk.Pooled
        public T borrow() {
            T tRemoveLastOrNull;
            synchronized (this) {
                tRemoveLastOrNull = this.pooled.removeLastOrNull();
            }
            return tRemoveLastOrNull == null ? getInstantiate().invoke() : tRemoveLastOrNull;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // dev.whyoleg.cryptography.providers.jdk.Pooled
        public void recycle(T value) {
            synchronized (this) {
                if (this.pooled.size() < PoolingKt.maxPooled) {
                    this.pooled.addLast(value);
                }
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    public final Resource<T> borrowResource() {
        return new Resource<>(this);
    }

    public final Resource<T> borrowResource(Function1<? super T, Unit> initialize) {
        Intrinsics.checkNotNullParameter(initialize, "initialize");
        Resource<T> resourceBorrowResource = borrowResource();
        initialize.invoke(resourceBorrowResource.access());
        return resourceBorrowResource;
    }

    public final <R> R use(Function1<? super T, ? extends R> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        R.anim animVar = (Object) borrow();
        try {
            return block.invoke(animVar);
        } finally {
            recycle(animVar);
        }
    }

    /* JADX INFO: compiled from: pooling.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\u0018\u0000*\u0004\b\u0001\u0010\u00012\u00060\u0002j\u0002`\u0003B\u0017\bF\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\n\u001a\u00028\u0001H\u0086\u0080\u0004¢\u0006\u0002\u0010\u000bJ\b\u0010\f\u001a\u00020\rH\u0016R\u0015\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0013\u0010\b\u001a\u0004\u0018\u00018\u0001X\u0082\u008e\b¢\u0006\u0004\n\u0002\u0010\t¨\u0006\u000e"}, d2 = {"Ldev/whyoleg/cryptography/providers/jdk/Pooled$Resource;", ExifInterface.GPS_DIRECTION_TRUE, "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "pooled", "Ldev/whyoleg/cryptography/providers/jdk/Pooled;", "<init>", "(Ldev/whyoleg/cryptography/providers/jdk/Pooled;)V", "_value", "Ljava/lang/Object;", "access", "()Ljava/lang/Object;", "close", "", "cryptography-provider-jdk"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Resource<T> implements AutoCloseable {
        private T _value;
        private final Pooled<T> pooled;

        public Resource(Pooled<T> pooled) {
            Intrinsics.checkNotNullParameter(pooled, "pooled");
            this.pooled = pooled;
            this._value = pooled.borrow();
        }

        public final T access() {
            T t = this._value;
            if (t != null) {
                return t;
            }
            throw new IllegalStateException("Already closed".toString());
        }

        @Override // java.lang.AutoCloseable
        public void close() {
            Pooled<T> pooled = this.pooled;
            T t = this._value;
            if (t == null) {
                return;
            }
            pooled.recycle(t);
            this._value = null;
        }
    }
}
