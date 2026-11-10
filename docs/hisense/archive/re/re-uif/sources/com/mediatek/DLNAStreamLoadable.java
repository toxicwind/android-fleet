package com.mediatek;

import android.util.Log;
import com.google.android.exoplayer.upstream.FrameworkDataSource;
import com.google.android.exoplayer.upstream.Loader;
import com.google.android.exoplayer.util.MtkDebugLogUtil;
import com.mediatek.dlna.object.ContentInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes.dex */
public class DLNAStreamLoadable implements Loader.Loadable {
    private static int mLoadable_FLG;
    private CachedSource cachedSource;
    private volatile boolean isCanceled;
    private final Condition loaderCondition;
    private final Lock loaderLock;
    private volatile int loaderResult;
    private InputStream mInputStream;
    private int mSeekType;

    public DLNAStreamLoadable(InputStream inputStream, FrameworkDataSource.StreamDataSource streamDataSource, long j, long j2) {
        this.mSeekType = 3;
        ReentrantLock reentrantLock = new ReentrantLock();
        this.loaderLock = reentrantLock;
        this.loaderCondition = reentrantLock.newCondition();
        this.mInputStream = inputStream;
        if (inputStream instanceof ContentInputStream) {
            try {
                int i = ((ContentInputStream) inputStream).getseektype();
                if (i == 0) {
                    this.mSeekType = 0;
                    Log.d("DLNAStreamLoadable", "mSeekType is DLNA_SEEK_TYPE_NONSUPPORTED");
                } else if (i == 1) {
                    this.mSeekType = 1;
                    Log.d("DLNAStreamLoadable", "mSeekType is DLNA_SEEK_TYPE_BYTE_ONLY");
                } else if (i == 2) {
                    this.mSeekType = 2;
                    Log.d("DLNAStreamLoadable", "mSeekType is DLNA_SEEK_TYPE_TIME_ONLY");
                } else if (i == 3) {
                    this.mSeekType = 3;
                    Log.d("DLNAStreamLoadable", "mSeekType is DLNA_SEEK_TYPE_BYTE_AND_TIME");
                } else if (i != 4 && i != 8 && i != 12 && i == 13) {
                    this.mSeekType = 13;
                    Log.d("DLNAStreamLoadable", "mSeekType is DLNA_SEEK_TYPE_UNKNOWN");
                } else {
                    this.mSeekType = 0;
                    Log.d("DLNAStreamLoadable", "mSeekType is DLNA_SEEK_TYPE_NONSUPPORTED");
                }
                Log.d("DLNAStreamLoadable", "mSeekType = " + this.mSeekType + ", lastBytePosition = " + j2);
                CachedSource cachedSource = new CachedSource(inputStream, streamDataSource, j, this.mSeekType);
                this.cachedSource = cachedSource;
                if (j2 != 0) {
                    if (this.mSeekType == 2) {
                        long j3 = j2 / 1000000;
                        if (mLoadable_FLG == 1) {
                            cachedSource.timeSeek(1L);
                            Log.d("DLNAStreamLoadable", "timeSeek");
                            return;
                        }
                        return;
                    }
                    if ((this.mSeekType == 1 || this.mSeekType == 3) && mLoadable_FLG == 1) {
                        this.cachedSource.byteSeek(1L);
                        return;
                    }
                    return;
                }
                return;
            } catch (Exception e) {
                Log.e("DLNAStreamLoadable", "getmSeekType(): call inputStream.getmSeekType(), exception happened!" + e.toString());
                return;
            }
        }
        Log.e("DLNAStreamLoadable", "Not expected network type");
        this.cachedSource = new CachedSource(inputStream, streamDataSource, j, 1);
    }

    @Override // com.google.android.exoplayer.upstream.Loader.Loadable
    public final void cancelLoad() {
        Log.d("DLNAStreamLoadable", "cancelLoad");
        this.isCanceled = true;
        this.loaderLock.lock();
        this.cachedSource.quit();
        this.loaderCondition.signal();
        this.loaderLock.unlock();
    }

    @Override // com.google.android.exoplayer.upstream.Loader.Loadable
    public final boolean isLoadCanceled() {
        return this.isCanceled;
    }

    @Override // com.google.android.exoplayer.upstream.Loader.Loadable
    public final void load() throws InterruptedException, IOException {
        Log.d("DLNAStreamLoadable", "load");
        this.cachedSource.startLoading();
        try {
            try {
                this.loaderLock.lock();
                this.loaderCondition.await();
                this.loaderLock.unlock();
                if (this.loaderResult != -2) {
                    return;
                }
                Log.e("DLNAStreamLoadable", "loaderResult == STATUS_DMS_BUSY.");
                throw new IOException("dms busy");
            } catch (Exception e) {
                Log.e("DLNAStreamLoadable", "Exception: load(), ex: " + e.getMessage());
                this.loaderLock.unlock();
                if (this.loaderResult != -2) {
                    return;
                }
                Log.e("DLNAStreamLoadable", "loaderResult == STATUS_DMS_BUSY.");
                throw new IOException("dms busy");
            }
        } catch (Throwable th) {
            this.loaderLock.unlock();
            if (this.loaderResult == -2) {
                Log.e("DLNAStreamLoadable", "loaderResult == STATUS_DMS_BUSY.");
                throw new IOException("dms busy");
            }
            throw th;
        }
    }

    public int readAt(long j, byte[] bArr, int i) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        synchronized (this) {
            int at = this.cachedSource.readAt(j, bArr, i);
            MtkDebugLogUtil.log("DLNAStreamLoadable", "readAt-, return " + at + ", spendTime(ms) = " + (System.currentTimeMillis() - jCurrentTimeMillis), 64);
            this.loaderResult = at;
            if (at >= 0) {
                return at;
            }
            this.loaderLock.lock();
            this.loaderCondition.signal();
            this.loaderLock.unlock();
            return this.loaderResult;
        }
    }

    public void seekTo(long j) {
        Log.d("DLNAStreamLoadable", "seekTo(Us): " + j);
        if (mLoadable_FLG == 1) {
            Log.d("DLNAStreamLoadable", "Loadable_FLG_DMR");
            int i = this.mSeekType;
            if (i == 2) {
                long j2 = j / 1000000;
                boolean z = this.mInputStream instanceof ContentInputStream;
                return;
            } else if (i != 1) {
                return;
            } else {
                return;
            }
        }
        Log.d("DLNAStreamLoadable", "skip seekTo");
    }
}
