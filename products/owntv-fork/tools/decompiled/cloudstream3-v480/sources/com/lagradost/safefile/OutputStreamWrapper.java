package com.lagradost.safefile;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import java.io.IOException;
import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DebugKt;

/* JADX INFO: compiled from: OutputStreamWrapper.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0096\u0002J\b\u0010\u0010\u001a\u00020\u000bH\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\rH\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\u0012\u0010\u0017\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\"\u0010\u0017\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u0012H\u0016J\u0010\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u0012H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/lagradost/safefile/OutputStreamWrapper;", "Ljava/io/OutputStream;", "stream", "uri", "Landroid/net/Uri;", "contentResolver", "Landroid/content/ContentResolver;", "(Ljava/io/OutputStream;Landroid/net/Uri;Landroid/content/ContentResolver;)V", "getStream", "()Ljava/io/OutputStream;", "close", "", "equals", "", "other", "", "flush", "hashCode", "", "setPending", "pending", "toString", "", "write", "b", "", DebugKt.DEBUG_PROPERTY_VALUE_OFF, "len", "library_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class OutputStreamWrapper extends OutputStream {
    private final ContentResolver contentResolver;
    private final OutputStream stream;
    private final Uri uri;

    public final OutputStream getStream() {
        return this.stream;
    }

    public OutputStreamWrapper(OutputStream stream, Uri uri, ContentResolver contentResolver) {
        Intrinsics.checkNotNullParameter(stream, "stream");
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(contentResolver, "contentResolver");
        this.stream = stream;
        this.uri = uri;
        this.contentResolver = contentResolver;
    }

    private final void setPending(boolean pending) {
        try {
            ContentResolver contentResolver = this.contentResolver;
            Uri uri = this.uri;
            ContentValues contentValues = new ContentValues();
            contentValues.put("is_pending", Integer.valueOf(pending ? 1 : 0));
            Unit unit = Unit.INSTANCE;
            contentResolver.update(uri, contentValues, null, null);
        } catch (Throwable th) {
            SafeFileKt.logError(th);
        }
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.stream.close();
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        this.stream.flush();
    }

    @Override // java.io.OutputStream
    public void write(byte[] b, int off, int len) throws IOException {
        this.stream.write(b, off, len);
    }

    public boolean equals(Object other) {
        if (other instanceof OutputStreamWrapper) {
            return Intrinsics.areEqual(((OutputStreamWrapper) other).stream, this.stream);
        }
        return Intrinsics.areEqual(this.stream, other);
    }

    public int hashCode() {
        return this.stream.hashCode();
    }

    public String toString() {
        return this.stream.toString();
    }

    @Override // java.io.OutputStream
    public void write(byte[] b) throws IOException {
        this.stream.write(b);
    }

    @Override // java.io.OutputStream
    public void write(int b) throws IOException {
        this.stream.write(b);
    }
}
