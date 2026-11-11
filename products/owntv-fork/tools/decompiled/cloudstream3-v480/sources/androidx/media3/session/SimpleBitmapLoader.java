package androidx.media3.session;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.util.Util;
import coil3.util.UtilsKt;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.io.ByteStreams;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes4.dex */
@Deprecated
public final class SimpleBitmapLoader implements androidx.media3.common.util.BitmapLoader {
    private static final Supplier<ListeningExecutorService> DEFAULT_EXECUTOR_SERVICE = Suppliers.memoize(new Supplier() { // from class: androidx.media3.session.SimpleBitmapLoader$$ExternalSyntheticLambda1
        @Override // com.google.common.base.Supplier
        public final Object get() {
            return MoreExecutors.listeningDecorator(Executors.newSingleThreadExecutor());
        }
    });
    private static final String FILE_URI_EXCEPTION_MESSAGE = "Could not read image from file";
    private final ListeningExecutorService executorService;

    @Override // androidx.media3.common.util.BitmapLoader
    public /* synthetic */ ListenableFuture loadBitmapFromMetadata(MediaMetadata mediaMetadata) {
        return androidx.media3.common.util.BitmapLoader.CC.$default$loadBitmapFromMetadata(this, mediaMetadata);
    }

    public SimpleBitmapLoader() {
        this((ExecutorService) Preconditions.checkNotNull(DEFAULT_EXECUTOR_SERVICE.get()));
    }

    public SimpleBitmapLoader(ExecutorService executorService) {
        this.executorService = MoreExecutors.listeningDecorator(executorService);
    }

    @Override // androidx.media3.common.util.BitmapLoader
    public boolean supportsMimeType(String str) {
        return Util.isBitmapFactorySupportedMimeType(str);
    }

    @Override // androidx.media3.common.util.BitmapLoader
    public ListenableFuture<Bitmap> decodeBitmap(final byte[] bArr) {
        return this.executorService.submit(new Callable() { // from class: androidx.media3.session.SimpleBitmapLoader$$ExternalSyntheticLambda2
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return SimpleBitmapLoader.decode(bArr);
            }
        });
    }

    @Override // androidx.media3.common.util.BitmapLoader
    public ListenableFuture<Bitmap> loadBitmap(final Uri uri) {
        return this.executorService.submit(new Callable() { // from class: androidx.media3.session.SimpleBitmapLoader$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return SimpleBitmapLoader.load(uri);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Bitmap decode(byte[] bArr) {
        Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        Preconditions.checkArgument(bitmapDecodeByteArray != null, "Could not decode image data");
        return bitmapDecodeByteArray;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Bitmap load(Uri uri) throws IOException {
        if (UtilsKt.SCHEME_FILE.equals(uri.getScheme())) {
            String path = uri.getPath();
            if (path == null) {
                throw new IllegalArgumentException(FILE_URI_EXCEPTION_MESSAGE);
            }
            Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(path);
            if (bitmapDecodeFile != null) {
                return bitmapDecodeFile;
            }
            throw new IllegalArgumentException(FILE_URI_EXCEPTION_MESSAGE);
        }
        URLConnection uRLConnectionOpenConnection = new URL(uri.toString()).openConnection();
        if (!(uRLConnectionOpenConnection instanceof HttpURLConnection)) {
            throw new UnsupportedOperationException("Unsupported scheme: " + uri.getScheme());
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) uRLConnectionOpenConnection;
        httpURLConnection.connect();
        int responseCode = httpURLConnection.getResponseCode();
        if (responseCode != 200) {
            throw new IOException("Invalid response status code: " + responseCode);
        }
        InputStream inputStream = httpURLConnection.getInputStream();
        try {
            Bitmap bitmapDecode = decode(ByteStreams.toByteArray(inputStream));
            if (inputStream != null) {
                inputStream.close();
            }
            return bitmapDecode;
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }
}
