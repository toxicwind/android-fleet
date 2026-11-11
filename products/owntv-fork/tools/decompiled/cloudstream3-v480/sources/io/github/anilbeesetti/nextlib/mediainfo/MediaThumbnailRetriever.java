package io.github.anilbeesetti.nextlib.mediainfo;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: MediaThumbnailRetriever.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011J\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u0005J\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0016\u001a\u00020\u0017J\b\u0010\u0018\u001a\u00020\u0007H\u0016J\u0006\u0010\u0019\u001a\u00020\u0007J\b\u0010\u001a\u001a\u00020\u0005H\u0002J\b\u0010\u001b\u001a\u00020\u0007H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lio/github/anilbeesetti/nextlib/mediainfo/MediaThumbnailRetriever;", "Ljava/io/Closeable;", "<init>", "()V", "nativeHandle", "", "setDataSource", "", "filePath", "", "descriptor", "Landroid/os/ParcelFileDescriptor;", "context", "Landroid/content/Context;", "uri", "Landroid/net/Uri;", "getEmbeddedPicture", "", "getFrameAtTime", "Landroid/graphics/Bitmap;", "timeUs", "getFrameAtIndex", "frameIndex", "", "close", "release", "requireHandle", "reset", "Companion", "mediainfo"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MediaThumbnailRetriever implements Closeable {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private long nativeHandle;

    /* JADX INFO: Access modifiers changed from: private */
    @JvmStatic
    public static final native long nativeCreateFromFD(int i);

    /* JADX INFO: Access modifiers changed from: private */
    @JvmStatic
    public static final native long nativeCreateFromPath(String str);

    /* JADX INFO: Access modifiers changed from: private */
    @JvmStatic
    public static final native byte[] nativeGetEmbeddedPicture(long j);

    /* JADX INFO: Access modifiers changed from: private */
    @JvmStatic
    public static final native Bitmap nativeGetFrameAtIndex(long j, int i);

    /* JADX INFO: Access modifiers changed from: private */
    @JvmStatic
    public static final native Bitmap nativeGetFrameAtTime(long j, long j2);

    /* JADX INFO: Access modifiers changed from: private */
    @JvmStatic
    public static final native int nativeGetRotationDegrees(long j);

    /* JADX INFO: Access modifiers changed from: private */
    @JvmStatic
    public static final native void nativeRelease(long j);

    public final void setDataSource(String filePath) {
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        reset();
        long jNativeCreateFromPath = INSTANCE.nativeCreateFromPath(filePath);
        this.nativeHandle = jNativeCreateFromPath;
        if (jNativeCreateFromPath == 0) {
            throw new IllegalArgumentException("Unable to open media source from path.".toString());
        }
    }

    public final void setDataSource(ParcelFileDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        reset();
        long jNativeCreateFromFD = INSTANCE.nativeCreateFromFD(descriptor.getFd());
        this.nativeHandle = jNativeCreateFromFD;
        if (jNativeCreateFromFD == 0) {
            throw new IllegalArgumentException("Unable to open media source from file descriptor.".toString());
        }
    }

    public final void setDataSource(Context context, Uri uri) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        String scheme = uri.getScheme();
        if (scheme != null) {
            String lowerCase = scheme.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            if (lowerCase != null && StringsKt.startsWith$default(lowerCase, "http", false, 2, (Object) null)) {
                String string = uri.toString();
                Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
                setDataSource(string);
                return;
            }
        }
        String path = PathUtil.INSTANCE.getPath(context, uri);
        if (path != null) {
            setDataSource(path);
            return;
        }
        try {
            ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = context.getContentResolver().openFileDescriptor(uri, "r");
            if (parcelFileDescriptorOpenFileDescriptor != null) {
                ParcelFileDescriptor parcelFileDescriptor = parcelFileDescriptorOpenFileDescriptor;
                try {
                    setDataSource(parcelFileDescriptor);
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(parcelFileDescriptor, null);
                    return;
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(parcelFileDescriptor, th);
                        throw th2;
                    }
                }
            }
            throw new IllegalStateException("Unable to open media source from uri.".toString());
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Unable to open media source from uri.", e);
        }
    }

    public final byte[] getEmbeddedPicture() {
        return INSTANCE.nativeGetEmbeddedPicture(requireHandle());
    }

    public final Bitmap getFrameAtTime(long timeUs) {
        if (timeUs < 0) {
            throw new IllegalArgumentException("timeUs must be >= 0".toString());
        }
        long jRequireHandle = requireHandle();
        Companion companion = INSTANCE;
        Bitmap bitmapNativeGetFrameAtTime = companion.nativeGetFrameAtTime(jRequireHandle, timeUs);
        if (bitmapNativeGetFrameAtTime == null) {
            return null;
        }
        return MediaThumbnailRetrieverKt.rotate(bitmapNativeGetFrameAtTime, companion.nativeGetRotationDegrees(jRequireHandle));
    }

    public final Bitmap getFrameAtIndex(int frameIndex) {
        if (frameIndex < 0) {
            throw new IllegalArgumentException("frameIndex must be >= 0".toString());
        }
        long jRequireHandle = requireHandle();
        Companion companion = INSTANCE;
        Bitmap bitmapNativeGetFrameAtIndex = companion.nativeGetFrameAtIndex(jRequireHandle, frameIndex);
        if (bitmapNativeGetFrameAtIndex == null) {
            return null;
        }
        return MediaThumbnailRetrieverKt.rotate(bitmapNativeGetFrameAtIndex, companion.nativeGetRotationDegrees(jRequireHandle));
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        reset();
    }

    public final void release() {
        reset();
    }

    private final long requireHandle() {
        long j = this.nativeHandle;
        if (j != 0) {
            return j;
        }
        throw new IllegalStateException("Data source is not set. Call setDataSource(...) first.".toString());
    }

    private final void reset() {
        long j = this.nativeHandle;
        if (j != 0) {
            INSTANCE.nativeRelease(j);
            this.nativeHandle = 0L;
        }
    }

    /* JADX INFO: compiled from: MediaThumbnailRetriever.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0011\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0083 J\u0011\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0083 J\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u0005H\u0083 J\u001b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0005H\u0083 J\u001b\u0010\u0011\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\nH\u0083 J\u0011\u0010\u0013\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u0005H\u0083 J\u0011\u0010\u0014\u001a\u00020\u00152\u0006\u0010\r\u001a\u00020\u0005H\u0083 ¨\u0006\u0016"}, d2 = {"Lio/github/anilbeesetti/nextlib/mediainfo/MediaThumbnailRetriever$Companion;", "", "<init>", "()V", "nativeCreateFromPath", "", "filePath", "", "nativeCreateFromFD", "fileDescriptor", "", "nativeGetEmbeddedPicture", "", "handle", "nativeGetFrameAtTime", "Landroid/graphics/Bitmap;", "timeUs", "nativeGetFrameAtIndex", "frameIndex", "nativeGetRotationDegrees", "nativeRelease", "", "mediainfo"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        @JvmStatic
        public final long nativeCreateFromFD(int fileDescriptor) {
            return MediaThumbnailRetriever.nativeCreateFromFD(fileDescriptor);
        }

        /* JADX INFO: Access modifiers changed from: private */
        @JvmStatic
        public final long nativeCreateFromPath(String filePath) {
            return MediaThumbnailRetriever.nativeCreateFromPath(filePath);
        }

        /* JADX INFO: Access modifiers changed from: private */
        @JvmStatic
        public final byte[] nativeGetEmbeddedPicture(long handle) {
            return MediaThumbnailRetriever.nativeGetEmbeddedPicture(handle);
        }

        /* JADX INFO: Access modifiers changed from: private */
        @JvmStatic
        public final Bitmap nativeGetFrameAtIndex(long handle, int frameIndex) {
            return MediaThumbnailRetriever.nativeGetFrameAtIndex(handle, frameIndex);
        }

        /* JADX INFO: Access modifiers changed from: private */
        @JvmStatic
        public final Bitmap nativeGetFrameAtTime(long handle, long timeUs) {
            return MediaThumbnailRetriever.nativeGetFrameAtTime(handle, timeUs);
        }

        /* JADX INFO: Access modifiers changed from: private */
        @JvmStatic
        public final int nativeGetRotationDegrees(long handle) {
            return MediaThumbnailRetriever.nativeGetRotationDegrees(handle);
        }

        /* JADX INFO: Access modifiers changed from: private */
        @JvmStatic
        public final void nativeRelease(long handle) {
            MediaThumbnailRetriever.nativeRelease(handle);
        }

        private Companion() {
        }
    }

    static {
        System.loadLibrary("mediainfo");
    }
}
