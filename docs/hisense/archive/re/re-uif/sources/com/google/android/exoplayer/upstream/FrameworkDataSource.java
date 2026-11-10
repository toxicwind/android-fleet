package com.google.android.exoplayer.upstream;

import android.annotation.TargetApi;
import android.media.MediaDataSource;
import com.google.android.exoplayer.FrameworkSampleSource;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.Util;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* JADX INFO: loaded from: classes.dex */
public abstract class FrameworkDataSource {
    private Object nativeExtractorDataSource;

    public interface StreamDataSource {
        long getSize();

        InputStream newInputStream();
    }

    protected abstract void close();

    protected abstract long getSize();

    @TargetApi(22)
    protected abstract int readAtV22(long j, byte[] bArr, int i);

    @TargetApi(23)
    protected abstract int readAtV23(long j, byte[] bArr, int i, int i2);

    public void seekTo(long j) {
    }

    public void tryDisconnect() {
    }

    public void tryResume(long j) {
    }

    public FrameworkDataSource() {
        initInstance();
    }

    public FrameworkDataSource(StreamDataSource streamDataSource) {
        Assertions.checkNotNull(streamDataSource);
        initInstance();
    }

    private void initInstance() {
        if (Util.SDK_INT == 22) {
            this.nativeExtractorDataSource = Proxy.newProxyInstance(FrameworkDataSource.class.getClassLoader(), new Class[]{FrameworkSampleSource.DATA_SOURCE_INTERFACE}, new InvocationHandler() { // from class: com.google.android.exoplayer.upstream.FrameworkDataSource.1
                /* JADX WARN: Code duplicated, block: B:18:0x0039  */
                @Override // java.lang.reflect.InvocationHandler
                public Object invoke(Object obj, Method method, Object[] objArr) {
                    byte b;
                    String name = method.getName();
                    int iHashCode = name.hashCode();
                    if (iHashCode != -934980503) {
                        if (iHashCode != -75151241) {
                            if (iHashCode == 94756344 && name.equals("close")) {
                                b = 1;
                            } else {
                                b = -1;
                            }
                        } else if (name.equals("getSize")) {
                            b = 2;
                        } else {
                            b = -1;
                        }
                    } else if (name.equals("readAt")) {
                        b = 0;
                    } else {
                        b = -1;
                    }
                    if (b == 0) {
                        return Integer.valueOf(FrameworkDataSource.this.readAtV22(((Long) objArr[0]).longValue(), (byte[]) objArr[1], ((Integer) objArr[2]).intValue()));
                    }
                    if (b == 1) {
                        FrameworkDataSource.this.close();
                        return null;
                    }
                    if (b != 2) {
                        return null;
                    }
                    return Long.valueOf(FrameworkDataSource.this.getSize());
                }
            });
        } else {
            this.nativeExtractorDataSource = new FrameworkMediaDataSource();
        }
    }

    public final Object getDataSourceForNativeExtractor() {
        return this.nativeExtractorDataSource;
    }

    @TargetApi(23)
    class FrameworkMediaDataSource extends MediaDataSource {
        FrameworkMediaDataSource() {
        }

        @Override // android.media.MediaDataSource
        public int readAt(long j, byte[] bArr, int i, int i2) {
            return FrameworkDataSource.this.readAtV23(j, bArr, i, i2);
        }

        @Override // android.media.MediaDataSource
        public long getSize() {
            return FrameworkDataSource.this.getSize();
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            FrameworkDataSource.this.close();
        }
    }
}
