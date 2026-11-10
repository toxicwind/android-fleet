package androidx.room.util;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import b.a.a.a.a;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public class CopyLock {
    public static final Map<String, Lock> sThreadLocks = new HashMap();
    public final File mCopyLockFile;
    public final boolean mFileLevelLock;
    public FileChannel mLockChannel;
    public final Lock mThreadLock;

    public CopyLock(@NonNull String str, @NonNull File file, boolean z) {
        this.mCopyLockFile = new File(file, a.a(str, ".lck"));
        this.mThreadLock = getThreadLock(this.mCopyLockFile.getAbsolutePath());
        this.mFileLevelLock = z;
    }

    public static Lock getThreadLock(String str) {
        Lock reentrantLock;
        synchronized (sThreadLocks) {
            reentrantLock = sThreadLocks.get(str);
            if (reentrantLock == null) {
                reentrantLock = new ReentrantLock();
                sThreadLocks.put(str, reentrantLock);
            }
        }
        return reentrantLock;
    }

    public void lock() {
        this.mThreadLock.lock();
        if (this.mFileLevelLock) {
            try {
                this.mLockChannel = new FileOutputStream(this.mCopyLockFile).getChannel();
                this.mLockChannel.lock();
            } catch (IOException e2) {
                throw new IllegalStateException("Unable to grab copy lock.", e2);
            }
        }
    }

    public void unlock() {
        FileChannel fileChannel = this.mLockChannel;
        if (fileChannel != null) {
            try {
                fileChannel.close();
            } catch (IOException unused) {
            }
        }
        this.mThreadLock.unlock();
    }
}
