package com.google.android.exoplayer.upstream;

import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import com.google.android.exoplayer.util.MtkDebugLogUtil;
import com.google.android.exoplayer.util.PriorityHandlerThread;
import com.google.android.exoplayer.util.Util;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class ReadbackableDataCache implements Handler.Callback {
    private long contentLength;
    private RollingDataBuffer dataBuffer;
    private DataSource dataSource;
    private final Handler handler;
    private final HandlerThread thread;
    private Uri uri;

    public ReadbackableDataCache(Uri uri, DataSource dataSource, int i) throws IOException {
        Log.d("ReadbackableDataCache", "ReadbackableDataCache~");
        this.uri = uri;
        this.dataSource = dataSource;
        this.dataBuffer = new RollingDataBuffer(i);
        initDatasource();
        PriorityHandlerThread priorityHandlerThread = new PriorityHandlerThread("ReadbackableDataCache:Handler", -16);
        this.thread = priorityHandlerThread;
        priorityHandlerThread.start();
        Handler handler = new Handler(this.thread.getLooper(), this);
        this.handler = handler;
        handler.sendEmptyMessage(1);
    }

    private void initDatasource() throws IOException {
        Log.d("ReadbackableDataCache", "initDatasource()");
        seekDatasource(0L);
    }

    private void seekDatasource(long j) throws IOException {
        Log.d("ReadbackableDataCache", "seekDatasource() position=" + j);
        Util.closeQuietly(this.dataSource);
        long jOpen = this.dataSource.open(new DataSpec(this.uri, j, -1L, null));
        if (j == 0) {
            this.contentLength = jOpen;
            Log.d("ReadbackableDataCache", "contentLength=" + this.contentLength);
        }
    }

    private void scheduleNextFetch(long j) {
        if (j > 0) {
            this.handler.sendEmptyMessageDelayed(1, j);
        } else {
            this.handler.sendEmptyMessage(1);
        }
    }

    private synchronized int fetchInternal(long j, int i) throws IOException {
        MtkDebugLogUtil.log("ReadbackableDataCache", "+fetchInternal position=" + j + " size=" + i, 64);
        long j2 = 0;
        if (j >= 0 && this.dataBuffer.tryReset(j, i)) {
            seekDatasource(j);
        }
        if (this.dataBuffer.isFull()) {
            scheduleNextFetch(50L);
            return -1;
        }
        byte[] bArr = new byte[32768];
        int i2 = 0;
        do {
            int i3 = this.dataSource.read(bArr, 0, 32768);
            if (i3 <= 0) {
                j2 = 100;
                break;
            }
            this.dataBuffer.write(bArr, i3);
            i2 += i3;
        } while (i2 < 32768);
        MtkDebugLogUtil.log("ReadbackableDataCache", "-fetchInternal fetchlength=" + i2, 64);
        scheduleNextFetch(j2);
        return i2 > 0 ? i2 : -1;
    }

    public int readAt(long j, byte[] bArr, int i, int i2) throws IOException {
        int iFetchInternal;
        MtkDebugLogUtil.log("ReadbackableDataCache", "+readAt position=" + j + " size=" + i2, 64);
        int i3 = 0;
        do {
            i3 += this.dataBuffer.read(((long) i3) + j, bArr, i + i3, i2 - i3);
            if (i3 >= i2) {
                break;
            }
            this.handler.removeMessages(1);
            try {
                iFetchInternal = fetchInternal(((long) i3) + j, i2 - i3);
            } catch (Exception e) {
                Log.e("ReadbackableDataCache", "Fetch error: ", e);
                this.handler.removeMessages(1);
                iFetchInternal = -1;
            }
        } while (iFetchInternal > 0);
        MtkDebugLogUtil.log("ReadbackableDataCache", "-readAt totalSize=" + i3, 64);
        if (i3 > 0) {
            return i3;
        }
        return -1;
    }

    public long getSize() {
        return this.contentLength;
    }

    public void release() {
        HandlerThread handlerThread = this.thread;
        if (handlerThread != null) {
            handlerThread.quit();
        }
        Util.closeQuietly(this.dataSource);
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        try {
            if (message.what != 1) {
                return false;
            }
            fetchInternal(-1L, -1);
            return true;
        } catch (IOException e) {
            Log.e("ReadbackableDataCache", "Fetch internal error: ", e);
            return true;
        } catch (RuntimeException e2) {
            Log.e("ReadbackableDataCache", "Fetch runtime error: ", e2);
            return true;
        }
    }
}
