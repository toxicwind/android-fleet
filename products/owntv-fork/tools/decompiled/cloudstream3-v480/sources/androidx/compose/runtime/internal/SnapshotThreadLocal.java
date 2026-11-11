package androidx.compose.runtime.internal;

import androidx.exifinterface.media.ExifInterface;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.Unit;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: SnapshotThreadLocal.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0001\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\r\u0010\u000e\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010\u000fJ\u0015\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010\u0013R \u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\tR\u0014\u0010\n\u001a\u00060\u0002j\u0002`\u000bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\fR\u0012\u0010\r\u001a\u0004\u0018\u00018\u0000X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\f¨\u0006\u0014"}, d2 = {"Landroidx/compose/runtime/internal/SnapshotThreadLocal;", ExifInterface.GPS_DIRECTION_TRUE, "", "<init>", "()V", "map", "Ljava/util/concurrent/atomic/AtomicReference;", "Landroidx/compose/runtime/internal/ThreadMap;", "Landroidx/compose/runtime/internal/AtomicReference;", "Ljava/util/concurrent/atomic/AtomicReference;", "writeMutex", "Landroidx/compose/runtime/platform/SynchronizedObject;", "Ljava/lang/Object;", "mainThreadValue", "get", "()Ljava/lang/Object;", "set", "", ES6Iterator.VALUE_PROPERTY, "(Ljava/lang/Object;)V", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SnapshotThreadLocal<T> {
    public static final int $stable = 8;
    private T mainThreadValue;
    private final AtomicReference<ThreadMap> map = new AtomicReference<>(SnapshotThreadLocalKt.emptyThreadMap);
    private final Object writeMutex = new Object();

    public final T get() {
        long jCurrentThreadId = Thread_jvmKt.currentThreadId();
        if (jCurrentThreadId == Thread_androidKt.getMainThreadId()) {
            return this.mainThreadValue;
        }
        return (T) this.map.get().get(jCurrentThreadId);
    }

    public final void set(T value) {
        long jCurrentThreadId = Thread_jvmKt.currentThreadId();
        if (jCurrentThreadId == Thread_androidKt.getMainThreadId()) {
            this.mainThreadValue = value;
            return;
        }
        synchronized (this.writeMutex) {
            ThreadMap threadMap = this.map.get();
            if (threadMap.trySet(jCurrentThreadId, value)) {
                return;
            }
            this.map.set(threadMap.newWith(jCurrentThreadId, value));
            Unit unit = Unit.INSTANCE;
        }
    }
}
