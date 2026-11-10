package com.google.firebase.storage.internal;

import android.util.Log;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class AdaptiveStreamBuffer {
    public static final String TAG = "AdaptiveStreamBuffer";
    public static final Runtime runtime = Runtime.getRuntime();
    public byte[] buffer;
    public final InputStream source;
    public int availableBytes = 0;
    public boolean adaptiveMode = true;
    public boolean reachedEnd = false;

    public AdaptiveStreamBuffer(InputStream inputStream, int i) {
        this.source = inputStream;
        this.buffer = new byte[i];
    }

    private int resize(int i) {
        int iMax = Math.max(this.buffer.length * 2, i);
        long jMaxMemory = runtime.maxMemory() - (runtime.totalMemory() - runtime.freeMemory());
        if (!this.adaptiveMode || iMax >= jMaxMemory) {
            Log.w(TAG, "Turning off adaptive buffer resizing to conserve memory.");
        } else {
            try {
                byte[] bArr = new byte[iMax];
                System.arraycopy(this.buffer, 0, bArr, 0, this.availableBytes);
                this.buffer = bArr;
            } catch (OutOfMemoryError unused) {
                Log.w(TAG, "Turning off adaptive buffer resizing due to low memory.");
                this.adaptiveMode = false;
            }
        }
        return this.buffer.length;
    }

    public int advance(int i) {
        int i2 = this.availableBytes;
        if (i <= i2) {
            this.availableBytes = i2 - i;
            byte[] bArr = this.buffer;
            System.arraycopy(bArr, i, bArr, 0, this.availableBytes);
            return i;
        }
        this.availableBytes = 0;
        int i3 = this.availableBytes;
        while (i3 < i) {
            int iSkip = (int) this.source.skip(i - i3);
            if (iSkip > 0) {
                i3 += iSkip;
            } else if (iSkip != 0) {
                continue;
            } else {
                if (this.source.read() == -1) {
                    break;
                }
                i3++;
            }
        }
        return i3;
    }

    public int available() {
        return this.availableBytes;
    }

    public void close() throws IOException {
        this.source.close();
    }

    public int fill(int i) throws IOException {
        if (i > this.buffer.length) {
            i = Math.min(i, resize(i));
        }
        while (true) {
            int i2 = this.availableBytes;
            if (i2 >= i) {
                break;
            }
            int i3 = this.source.read(this.buffer, i2, i - i2);
            if (i3 == -1) {
                this.reachedEnd = true;
                break;
            }
            this.availableBytes += i3;
        }
        return this.availableBytes;
    }

    public byte[] get() {
        return this.buffer;
    }

    public boolean isFinished() {
        return this.reachedEnd;
    }
}
