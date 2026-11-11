package kotlinx.atomicfu.locks;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SynchronousMutex.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a6\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0004H\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0002\u0010\u0005\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0006"}, d2 = {"withLock", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/atomicfu/locks/SynchronousMutex;", "block", "Lkotlin/Function0;", "(Lkotlinx/atomicfu/locks/SynchronousMutex;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "atomicfu"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class SynchronousMutexKt {
    public static final <T> T withLock(SynchronousMutex synchronousMutex, Function0<? extends T> block) {
        Intrinsics.checkNotNullParameter(synchronousMutex, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        synchronousMutex.lock();
        try {
            return block.invoke();
        } finally {
            synchronousMutex.unlock();
        }
    }
}
