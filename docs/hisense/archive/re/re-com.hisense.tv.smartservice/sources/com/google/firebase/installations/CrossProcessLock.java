package com.google.firebase.installations;

import android.content.Context;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;

/* JADX INFO: loaded from: classes.dex */
public class CrossProcessLock {
    public static final String TAG = "CrossProcessLock";
    public final FileChannel channel;
    public final FileLock lock;

    public CrossProcessLock(FileChannel fileChannel, FileLock fileLock) {
        this.channel = fileChannel;
        this.lock = fileLock;
    }

    /* JADX WARN: Code duplicated, block: B:33:0x0041 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:35:0x003c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public static CrossProcessLock acquire(Context context, String str) {
        FileChannel channel;
        FileLock fileLockLock;
        try {
            channel = new RandomAccessFile(new File(context.getFilesDir(), str), "rw").getChannel();
            try {
                fileLockLock = channel.lock();
                try {
                    return new CrossProcessLock(channel, fileLockLock);
                } catch (IOException e2) {
                    e = e2;
                    Log.e(TAG, "encountered error while creating and acquiring the lock, ignoring", e);
                    if (fileLockLock != null) {
                        try {
                            fileLockLock.release();
                        } catch (IOException unused) {
                        }
                    }
                    if (channel != null) {
                        try {
                            channel.close();
                        } catch (IOException unused2) {
                        }
                    }
                    return null;
                } catch (Error e3) {
                    e = e3;
                    Log.e(TAG, "encountered error while creating and acquiring the lock, ignoring", e);
                    if (fileLockLock != null) {
                        fileLockLock.release();
                    }
                    if (channel != null) {
                        channel.close();
                    }
                    return null;
                } catch (OverlappingFileLockException e4) {
                    e = e4;
                    Log.e(TAG, "encountered error while creating and acquiring the lock, ignoring", e);
                    if (fileLockLock != null) {
                        fileLockLock.release();
                    }
                    if (channel != null) {
                        channel.close();
                    }
                    return null;
                }
            } catch (IOException | Error | OverlappingFileLockException e5) {
                e = e5;
                fileLockLock = null;
            }
        } catch (IOException | Error | OverlappingFileLockException e6) {
            e = e6;
            channel = null;
            fileLockLock = null;
        }
    }

    public void releaseAndClose() {
        try {
            this.lock.release();
            this.channel.close();
        } catch (IOException e2) {
            Log.e(TAG, "encountered error while releasing, ignoring", e2);
        }
    }
}
