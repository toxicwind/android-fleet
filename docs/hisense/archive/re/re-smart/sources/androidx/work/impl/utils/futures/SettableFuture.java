package androidx.work.impl.utils.futures;

import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import com.google.common.util.concurrent.ListenableFuture;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class SettableFuture<V> extends AbstractFuture<V> {
    public static <V> SettableFuture<V> create() {
        return new SettableFuture<>();
    }

    @Override // androidx.work.impl.utils.futures.AbstractFuture
    public boolean set(@Nullable V value) {
        return super.set(value);
    }

    @Override // androidx.work.impl.utils.futures.AbstractFuture
    public boolean setException(Throwable throwable) {
        return super.setException(throwable);
    }

    @Override // androidx.work.impl.utils.futures.AbstractFuture
    public boolean setFuture(ListenableFuture<? extends V> future) {
        return super.setFuture(future);
    }
}
