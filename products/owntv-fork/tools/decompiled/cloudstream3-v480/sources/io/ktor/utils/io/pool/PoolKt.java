package io.ktor.utils.io.pool;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Pool.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aA\u0010\u0006\u001a\u00028\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\u0004\b\u0001\u0010\u0002*\b\u0012\u0004\u0012\u00028\u00000\u00032\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001aA\u0010\b\u001a\u00028\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\u0004\b\u0001\u0010\u0002*\b\u0012\u0004\u0012\u00028\u00000\u00032\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004H\u0086\bø\u0001\u0000¢\u0006\u0004\b\b\u0010\u0007\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\t"}, d2 = {"", ExifInterface.GPS_DIRECTION_TRUE, "R", "Lio/ktor/utils/io/pool/ObjectPool;", "Lkotlin/Function1;", "block", "useBorrowed", "(Lio/ktor/utils/io/pool/ObjectPool;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "useInstance", "ktor-io"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class PoolKt {
    public static final <T, R> R useInstance(ObjectPool<T> objectPool, Function1<? super T, ? extends R> block) {
        Intrinsics.checkNotNullParameter(objectPool, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        T tBorrow = objectPool.borrow();
        try {
            return block.invoke(tBorrow);
        } finally {
            objectPool.recycle(tBorrow);
        }
    }

    @Deprecated(message = "Use useInstance instead", replaceWith = @ReplaceWith(expression = "useInstance(block)", imports = {}))
    public static final <T, R> R useBorrowed(ObjectPool<T> objectPool, Function1<? super T, ? extends R> block) {
        Intrinsics.checkNotNullParameter(objectPool, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        T tBorrow = objectPool.borrow();
        try {
            return block.invoke(tBorrow);
        } finally {
            objectPool.recycle(tBorrow);
        }
    }
}
