package io.ktor.utils.io.pool;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;

/* JADX INFO: compiled from: Pool.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\bf\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00060\u0003j\u0002`\u0004J\u000f\u0010\u0005\u001a\u00028\u0000H&¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00028\u0000H&¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u00020\bH&¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\r\u001a\u00020\bH\u0016¢\u0006\u0004\b\r\u0010\fR\u0014\u0010\u0011\u001a\u00020\u000e8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0012À\u0006\u0003"}, d2 = {"Lio/ktor/utils/io/pool/ObjectPool;", "", ExifInterface.GPS_DIRECTION_TRUE, "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "borrow", "()Ljava/lang/Object;", "instance", "", "recycle", "(Ljava/lang/Object;)V", "dispose", "()V", "close", "", "getCapacity", "()I", "capacity", "ktor-io"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface ObjectPool<T> extends AutoCloseable {
    T borrow();

    @Override // java.lang.AutoCloseable
    void close();

    void dispose();

    int getCapacity();

    void recycle(T instance);

    /* JADX INFO: renamed from: io.ktor.utils.io.pool.ObjectPool$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: Pool.kt */
    public final /* synthetic */ class CC {
    }

    /* JADX INFO: compiled from: Pool.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static <T> void close(ObjectPool<T> objectPool) {
            objectPool.dispose();
        }
    }
}
