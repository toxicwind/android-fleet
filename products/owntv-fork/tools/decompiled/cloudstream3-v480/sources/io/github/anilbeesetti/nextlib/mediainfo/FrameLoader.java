package io.github.anilbeesetti.nextlib.mediainfo;

import android.graphics.Bitmap;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FrameLoader.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0011\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0003J\u0010\u0010\u000b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u0003J\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lio/github/anilbeesetti/nextlib/mediainfo/FrameLoader;", "", "frameLoaderContextHandle", "", "<init>", "(J)V", "loadFrameInto", "", "bitmap", "Landroid/graphics/Bitmap;", "durationMillis", "getFrame", "release", "", "Companion", "mediainfo"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class FrameLoader {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private long frameLoaderContextHandle;

    /* JADX INFO: Access modifiers changed from: private */
    @JvmStatic
    public static final native Bitmap nativeGetFrame(long j, long j2);

    /* JADX INFO: Access modifiers changed from: private */
    @JvmStatic
    public static final native boolean nativeLoadFrame(long j, long j2, Bitmap bitmap);

    /* JADX INFO: Access modifiers changed from: private */
    @JvmStatic
    public static final native void nativeRelease(long j);

    public FrameLoader(long j) {
        this.frameLoaderContextHandle = j;
    }

    public final boolean loadFrameInto(Bitmap bitmap, long durationMillis) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        long j = this.frameLoaderContextHandle;
        if (j == -1) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        return INSTANCE.nativeLoadFrame(j, durationMillis, bitmap);
    }

    public final Bitmap getFrame(long durationMillis) {
        long j = this.frameLoaderContextHandle;
        if (j == -1) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        return INSTANCE.nativeGetFrame(j, durationMillis);
    }

    public final void release() {
        INSTANCE.nativeRelease(this.frameLoaderContextHandle);
        this.frameLoaderContextHandle = -1L;
    }

    /* JADX INFO: compiled from: FrameLoader.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0011\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0083 J!\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0083 J\u001b\u0010\r\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0007H\u0083 ¨\u0006\u000e"}, d2 = {"Lio/github/anilbeesetti/nextlib/mediainfo/FrameLoader$Companion;", "", "<init>", "()V", "nativeRelease", "", "handle", "", "nativeLoadFrame", "", "durationMillis", "bitmap", "Landroid/graphics/Bitmap;", "nativeGetFrame", "mediainfo"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        @JvmStatic
        public final Bitmap nativeGetFrame(long handle, long durationMillis) {
            return FrameLoader.nativeGetFrame(handle, durationMillis);
        }

        /* JADX INFO: Access modifiers changed from: private */
        @JvmStatic
        public final boolean nativeLoadFrame(long handle, long durationMillis, Bitmap bitmap) {
            return FrameLoader.nativeLoadFrame(handle, durationMillis, bitmap);
        }

        /* JADX INFO: Access modifiers changed from: private */
        @JvmStatic
        public final void nativeRelease(long handle) {
            FrameLoader.nativeRelease(handle);
        }

        private Companion() {
        }
    }
}
