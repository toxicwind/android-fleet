package coil3.request;

import coil3.Image;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ImageResult.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B!\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ&\u0010\u0010\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0096\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0016R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0019"}, d2 = {"Lcoil3/request/ErrorResult;", "Lcoil3/request/ImageResult;", "image", "Lcoil3/Image;", "request", "Lcoil3/request/ImageRequest;", "throwable", "", "<init>", "(Lcoil3/Image;Lcoil3/request/ImageRequest;Ljava/lang/Throwable;)V", "getImage", "()Lcoil3/Image;", "getRequest", "()Lcoil3/request/ImageRequest;", "getThrowable", "()Ljava/lang/Throwable;", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "coil-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ErrorResult implements ImageResult {
    private final Image image;
    private final ImageRequest request;
    private final Throwable throwable;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ErrorResult)) {
            return false;
        }
        ErrorResult errorResult = (ErrorResult) other;
        return Intrinsics.areEqual(this.image, errorResult.image) && Intrinsics.areEqual(this.request, errorResult.request) && Intrinsics.areEqual(this.throwable, errorResult.throwable);
    }

    public int hashCode() {
        Image image = this.image;
        return ((((image == null ? 0 : image.hashCode()) * 31) + this.request.hashCode()) * 31) + this.throwable.hashCode();
    }

    public String toString() {
        return "ErrorResult(image=" + this.image + ", request=" + this.request + ", throwable=" + this.throwable + ')';
    }

    public ErrorResult(Image image, ImageRequest imageRequest, Throwable th) {
        this.image = image;
        this.request = imageRequest;
        this.throwable = th;
    }

    @Override // coil3.request.ImageResult
    public Image getImage() {
        return this.image;
    }

    @Override // coil3.request.ImageResult
    public ImageRequest getRequest() {
        return this.request;
    }

    public final Throwable getThrowable() {
        return this.throwable;
    }

    public static /* synthetic */ ErrorResult copy$default(ErrorResult errorResult, Image image, ImageRequest imageRequest, Throwable th, int i, Object obj) {
        if ((i & 1) != 0) {
            image = errorResult.getImage();
        }
        if ((i & 2) != 0) {
            imageRequest = errorResult.getRequest();
        }
        if ((i & 4) != 0) {
            th = errorResult.throwable;
        }
        return errorResult.copy(image, imageRequest, th);
    }

    public final ErrorResult copy(Image image, ImageRequest request, Throwable throwable) {
        return new ErrorResult(image, request, throwable);
    }
}
