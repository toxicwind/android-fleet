package coil3.size;

import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RealSizeResolver.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0002\u001a\u00020\u0003H\u0096@¢\u0006\u0002\u0010\u0006J\u0013\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0096\u0002J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcoil3/size/RealSizeResolver;", "Lcoil3/size/SizeResolver;", ContentDisposition.Parameters.Size, "Lcoil3/size/Size;", "<init>", "(Lcoil3/size/Size;)V", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "equals", "", "other", "", "hashCode", "", "toString", "", "coil-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class RealSizeResolver implements SizeResolver {
    private final Size size;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof RealSizeResolver) && Intrinsics.areEqual(this.size, ((RealSizeResolver) other).size);
    }

    public int hashCode() {
        return this.size.hashCode();
    }

    public String toString() {
        return "RealSizeResolver(size=" + this.size + ')';
    }

    public RealSizeResolver(Size size) {
        this.size = size;
    }

    @Override // coil3.size.SizeResolver
    public Object size(Continuation<? super Size> continuation) {
        return this.size;
    }
}
