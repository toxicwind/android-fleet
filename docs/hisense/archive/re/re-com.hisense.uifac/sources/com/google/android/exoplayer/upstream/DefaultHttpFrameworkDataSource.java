package com.google.android.exoplayer.upstream;

import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import com.google.android.exoplayer.util.PriorityHandlerThread;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class DefaultHttpFrameworkDataSource extends FrameworkDataSource implements Handler.Callback {
    private ReadbackableDataCache cache;
    private DataSource dataSource;
    private final Handler handler;
    private boolean initSuccess = true;
    private boolean prepared;
    private final HandlerThread thread;
    private Uri uri;

    public DefaultHttpFrameworkDataSource(String str, Uri uri) throws IOException {
        this.uri = uri;
        this.dataSource = new DefaultHttpDataSource(str, null, null, 8000, 8000, false);
        PriorityHandlerThread priorityHandlerThread = new PriorityHandlerThread("DefaultHttpFrameworkDataSource:Handler", -16);
        this.thread = priorityHandlerThread;
        priorityHandlerThread.start();
        Handler handler = new Handler(this.thread.getLooper(), this);
        this.handler = handler;
        handler.sendEmptyMessage(1);
        await();
        if (!this.initSuccess) {
            throw new IOException("DefaultHttpFrameworkDataSource prepare fail");
        }
    }

    private void prepareInternal() throws IOException {
        try {
            this.cache = new ReadbackableDataCache(this.uri, this.dataSource, 31457280);
        } catch (IOException e) {
            this.initSuccess = false;
            Log.e("DefaultHttpFDS", "new ReadbackableDataCache Fail. " + e.toString());
        }
        synchronized (this) {
            this.prepared = true;
            notifyAll();
        }
    }

    private synchronized void await() {
        while (!this.prepared) {
            try {
                wait();
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override // com.google.android.exoplayer.upstream.FrameworkDataSource
    public int readAtV22(long j, byte[] bArr, int i) {
        try {
            return this.cache.readAt(j, bArr, 0, i);
        } catch (IOException e) {
            Log.e("DefaultHttpFDS", "readv22 error. " + e.toString());
            return -1;
        }
    }

    @Override // com.google.android.exoplayer.upstream.FrameworkDataSource
    public int readAtV23(long j, byte[] bArr, int i, int i2) {
        try {
            return this.cache.readAt(j, bArr, i, i2);
        } catch (IOException e) {
            Log.e("DefaultHttpFDS", "readv23 error. " + e.toString());
            return -1;
        }
    }

    @Override // com.google.android.exoplayer.upstream.FrameworkDataSource
    public long getSize() {
        return this.cache.getSize();
    }

    private void releaseInternal() {
        HandlerThread handlerThread = this.thread;
        if (handlerThread != null) {
            handlerThread.quit();
        }
        ReadbackableDataCache readbackableDataCache = this.cache;
        if (readbackableDataCache != null) {
            readbackableDataCache.release();
        }
    }

    @Override // com.google.android.exoplayer.upstream.FrameworkDataSource
    public void close() {
        this.handler.sendEmptyMessage(2);
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        try {
            int i = message.what;
            if (i == 1) {
                prepareInternal();
                return true;
            }
            if (i != 2) {
                return false;
            }
            releaseInternal();
            return true;
        } catch (IOException e) {
            Log.e("DefaultHttpFDS", "Prepare internal error: ", e);
            return true;
        } catch (RuntimeException e2) {
            Log.e("DefaultHttpFDS", "Internal runtime error: ", e2);
            return true;
        }
    }
}
