package com.mediatek;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import com.google.android.exoplayer.upstream.FrameworkDataSource;
import com.google.android.exoplayer.util.MtkDebugLogUtil;
import com.google.android.exoplayer.util.PriorityHandlerThread;
import com.mediatek.dlna.object.ContentInputStream;
import com.mediatek.dlna.object.ContentInputStream.MediaInfo;
import com.mediatek.twoworlds.tv.MtkTvScanDvbsBase;
import com.mediatek.twoworlds.tv.MtkTvTimeFormatBase;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* JADX INFO: loaded from: classes.dex */
public class CachedSource implements Handler.Callback {
    private volatile PageCache globalPageCache;
    private volatile PageCache globalPageCache2;
    private long inputStreamLastReadAtByteOffset;
    private final HandlerThread mCacheThread;
    private final Condition mCondition;
    private final FrameworkDataSource.StreamDataSource mDataSource;
    private final long mFileSize;
    private final Handler mHandler;
    private InputStream mInputStream;
    private long mInputStreamNeedSeekByteOffset;
    private long mInputStreamNeedSeekTimeS;
    private volatile boolean mIsQuiting;
    private boolean mIsStallingSupport;
    private volatile boolean mIsStarted;
    private volatile boolean mLoading;
    private final Lock mLock;
    private final long mMaxBufferSizeForPart2;
    private final ReadWriteLock mPageCacheLock;
    private final int mSeekType;
    private boolean mfetchLooping = false;
    private volatile long readAtLastAccessPosition;

    static class Page {
        public byte[] data;
        public int size;

        Page() {
        }
    }

    static class PageCache {
        private int pageSize;
        private int totalSize = 0;
        private volatile List<Page> activePages = new ArrayList();
        private volatile List<Page> freePages = new ArrayList();
        public volatile long cachedOffset = 0;
        public volatile long lastAccessPosition = 0;
        public volatile boolean fetching = true;
        public volatile boolean isCacheFull = false;
        public volatile int finalStatus = 1;
        public volatile int asyncReadResult = -1000;

        public PageCache(int i) {
            this.pageSize = i;
        }

        public Page acquirePage() {
            if (!this.freePages.isEmpty()) {
                return this.freePages.remove(0);
            }
            Page page = new Page();
            page.data = new byte[this.pageSize];
            page.size = 0;
            return page;
        }

        public void appendPage(Page page) {
            this.totalSize += page.size;
            this.activePages.add(page);
        }

        public void releasePage(Page page) {
            page.size = 0;
            this.freePages.add(page);
        }

        public void deletePage() {
            for (int i = 0; i < this.activePages.size(); i++) {
                this.activePages.get(i).data = null;
            }
            for (int i2 = 0; i2 < this.freePages.size(); i2++) {
                this.freePages.get(i2).data = null;
            }
        }

        public int copy(int i, byte[] bArr, int i2) {
            if (i2 == 0 || this.activePages.isEmpty()) {
                return 0;
            }
            long j = 0;
            Page page = this.activePages.get(0);
            int i3 = 0;
            int i4 = 0;
            while (true) {
                if (i3 >= this.activePages.size()) {
                    i3 = i4;
                    break;
                }
                page = this.activePages.get(i3);
                long j2 = i;
                int i5 = page.size;
                if (j2 <= ((long) i5) + j) {
                    break;
                }
                j += (long) i5;
                i4 = i3;
                i3++;
            }
            int i6 = (int) (((long) i) - j);
            int i7 = page.size;
            if (i7 - i6 >= i2) {
                System.arraycopy(page.data, i6, bArr, 0, i2);
                return i2;
            }
            int i8 = i7 - i6;
            System.arraycopy(page.data, i6, bArr, 0, i8);
            int i9 = i2 - i8;
            while (true) {
                i3++;
                if (i3 >= this.activePages.size()) {
                    return i8;
                }
                Page page2 = this.activePages.get(i3);
                int i10 = page2.size;
                if (i10 < i9) {
                    System.arraycopy(page2.data, 0, bArr, i8, i10);
                    int i11 = page2.size;
                    i8 += i11;
                    i9 -= i11;
                } else {
                    System.arraycopy(page2.data, 0, bArr, i8, i9);
                    return i8 + i9;
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int releaseFromStart(int i) {
            int i2 = 0;
            while (i > 0 && !this.activePages.isEmpty()) {
                Page page = this.activePages.get(0);
                if (i < page.size) {
                    break;
                }
                this.activePages.remove(0);
                int i3 = page.size;
                i -= i3;
                i2 += i3;
                releasePage(page);
            }
            this.totalSize -= i2;
            return i2;
        }
    }

    private class ReadRequest {
        public byte[] data;
        public boolean loading;
        public Object lock;
        public long offset;
        public PageCache pageCache;
        public int size;

        private ReadRequest(CachedSource cachedSource) {
        }
    }

    public CachedSource(InputStream inputStream, FrameworkDataSource.StreamDataSource streamDataSource, long j, int i) {
        this.mInputStream = inputStream;
        this.mDataSource = streamDataSource;
        this.mFileSize = j;
        this.mSeekType = i;
        ReentrantLock reentrantLock = new ReentrantLock();
        this.mLock = reentrantLock;
        this.mCondition = reentrantLock.newCondition();
        this.mIsStarted = false;
        this.mIsQuiting = false;
        this.globalPageCache = new PageCache(MtkTvScanDvbsBase.SB_DVBS_CONFIG_BGM_SCAN);
        this.globalPageCache2 = new PageCache(MtkTvScanDvbsBase.SB_DVBS_CONFIG_BGM_SCAN);
        this.readAtLastAccessPosition = 0L;
        this.inputStreamLastReadAtByteOffset = 0L;
        this.mInputStreamNeedSeekByteOffset = 0L;
        this.mInputStreamNeedSeekTimeS = 0L;
        long j2 = j / 100;
        this.mMaxBufferSizeForPart2 = j2 <= 5242880 ? 5242880L : j2;
        this.mCacheThread = new PriorityHandlerThread(CachedSource.class.getSimpleName() + ":Handler", -16);
        this.mPageCacheLock = new ReentrantReadWriteLock();
        Log.d("CachedSource", "create CachedSource thread");
        this.mCacheThread.start();
        this.mHandler = new Handler(this.mCacheThread.getLooper(), this);
        InputStream inputStream2 = this.mInputStream;
        if (inputStream2 instanceof ContentInputStream) {
            try {
                this.mIsStallingSupport = ((ContentInputStream) inputStream2).getstallingsupport();
                Log.d("CachedSource", "mIsStallingSupport = " + this.mIsStallingSupport);
            } catch (Exception e) {
                Log.e("CachedSource", "getmSeekType(): call inputStream.getstallingsupport(), exception happened!" + e.toString());
            }
        }
    }

    public void startLoading() {
        if (this.mIsStarted) {
            return;
        }
        this.mIsStarted = true;
        Log.d("CachedSource", "startLoading");
        Log.d("CachedSource", "sendEmptyMessage(MSG_FETCH_MORE) 001");
        this.mHandler.sendEmptyMessage(1);
        this.mfetchLooping = true;
    }

    public void quit() {
        if (this.mCacheThread != null) {
            Log.d("CachedSource", "quit");
            this.mIsQuiting = true;
            this.mLock.lock();
            if (this.mInputStream != null) {
                try {
                    synchronized (this) {
                        Log.d("CachedSource", "removeMessages(MSG_FETCH_MORE) 004");
                        this.mHandler.removeMessages(1);
                        this.mfetchLooping = false;
                    }
                    if (this.mInputStream instanceof ContentInputStream) {
                        ((ContentInputStream) this.mInputStream).abort();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            this.mLoading = false;
            this.mCondition.signal();
            this.mLock.unlock();
            this.globalPageCache.deletePage();
            this.globalPageCache2.deletePage();
            this.mCacheThread.quit();
        }
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        int i = message.what;
        if (i == 1) {
            MtkDebugLogUtil.log("CachedSource", "+MSG_FETCH_MORE", 64);
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (this.mIsQuiting) {
                Log.d("CachedSource", "issue handle");
            } else {
                onFetch();
            }
            MtkDebugLogUtil.log("CachedSource", "-MSG_FETCH_MORE spendTime(ms)=" + (System.currentTimeMillis() - jCurrentTimeMillis), 64);
            return true;
        }
        if (i != 2) {
            if (i == 3) {
                timeSeekInternal(this.mInputStreamNeedSeekTimeS);
                return true;
            }
            if (i == 4) {
                byteSeekInternal(this.mInputStreamNeedSeekByteOffset);
                return true;
            }
            Log.e("CachedSource", "unknown message type (" + message.what + ")");
            return false;
        }
        MtkDebugLogUtil.log("CachedSource", "+MSG_READ_CACHE", 64);
        long jCurrentTimeMillis2 = System.currentTimeMillis();
        ReadRequest readRequest = (ReadRequest) message.obj;
        try {
            this.readAtLastAccessPosition = readRequest.offset;
            onReadCache(readRequest);
        } catch (IOException e) {
            Log.e("CachedSource", "onReadCache, catch IOException, e: " + e.getMessage());
        }
        MtkDebugLogUtil.log("CachedSource", "-MSG_READ_CACHE spendTime(ms)=" + (System.currentTimeMillis() - jCurrentTimeMillis2), 64);
        return true;
    }

    public int readAt(long j, byte[] bArr, int i) {
        synchronized (this) {
            boolean zIsFirstPartOfStream = isFirstPartOfStream(j);
            PageCache pageCache = zIsFirstPartOfStream ? this.globalPageCache : this.globalPageCache2;
            this.mPageCacheLock.readLock().lock();
            MtkDebugLogUtil.log("CachedSource", "VIP readAt, isFirstPart(" + zIsFirstPartOfStream + ") offset " + j + ", size " + i + ", fileSize = " + this.mFileSize + ", (offset1 " + this.globalPageCache.cachedOffset + ", size1 = " + this.globalPageCache.totalSize + ", offset2 = " + this.globalPageCache2.cachedOffset + ", size2 = " + this.globalPageCache2.totalSize + ")", 64);
            if (j >= this.mFileSize) {
                this.mPageCacheLock.readLock().unlock();
                Log.d("CachedSource", "offset >= fileSize return -1");
                return -1;
            }
            if (j >= pageCache.cachedOffset) {
                long j2 = ((long) i) + j;
                if (j2 <= pageCache.cachedOffset + ((long) pageCache.totalSize)) {
                    pageCache.copy((int) (j - pageCache.cachedOffset), bArr, i);
                    pageCache.lastAccessPosition = j2;
                    this.readAtLastAccessPosition = pageCache.lastAccessPosition;
                    this.mPageCacheLock.readLock().unlock();
                    return i;
                }
            }
            if (j >= pageCache.cachedOffset && pageCache.finalStatus == -1) {
                int i2 = (int) (j - pageCache.cachedOffset);
                int i3 = pageCache.totalSize - i2;
                Log.d("CachedSource", "readAt, EOS, remainSize = " + i3);
                if (i3 > 0) {
                    pageCache.copy(i2, bArr, i3);
                    pageCache.lastAccessPosition += (long) i;
                    this.readAtLastAccessPosition = pageCache.lastAccessPosition;
                }
                this.mPageCacheLock.readLock().unlock();
                return i3;
            }
            this.mPageCacheLock.readLock().unlock();
            if (pageCache.finalStatus == -2) {
                Log.d("CachedSource", "finalStatus == STATUS_DMS_BUSY return -1");
                return -1;
            }
            ReadRequest readRequest = new ReadRequest();
            readRequest.offset = j;
            readRequest.data = bArr;
            readRequest.size = i;
            readRequest.pageCache = pageCache;
            readRequest.loading = true;
            readRequest.lock = new Object();
            MtkDebugLogUtil.log("CachedSource", "cache is not enough, send read msg", 64);
            this.mHandler.obtainMessage(2, readRequest).sendToTarget();
            this.mLoading = true;
            while (this.mLoading) {
                try {
                    try {
                        this.mLock.lock();
                        this.mCondition.await();
                        synchronized (readRequest.lock) {
                            if (this.mLoading) {
                                this.mLoading = readRequest.loading;
                            }
                        }
                    } catch (InterruptedException e) {
                        Log.e("CachedSource", "readAt, catch InterruptedException, e:" + e.getMessage());
                        synchronized (readRequest.lock) {
                            if (this.mLoading) {
                                this.mLoading = readRequest.loading;
                            }
                        }
                    }
                    this.mLock.unlock();
                } catch (Throwable th) {
                    synchronized (readRequest.lock) {
                        if (this.mLoading) {
                            this.mLoading = readRequest.loading;
                        }
                        this.mLock.unlock();
                        throw th;
                    }
                }
            }
            int i4 = pageCache.asyncReadResult;
            MtkDebugLogUtil.log("CachedSource", "read success, size = " + i4, 64);
            pageCache.asyncReadResult = -1000;
            if (i4 > 0) {
                pageCache.lastAccessPosition = j + ((long) i4);
                this.readAtLastAccessPosition = pageCache.lastAccessPosition;
            }
            return i4;
        }
    }

    public void byteSeek(long j) {
        int i = this.mSeekType;
        if (13 == i || i == 0) {
            Log.e("CachedSource", "mSeekType not support skip");
            return;
        }
        synchronized (this) {
            Log.d("CachedSource", "removeMessages(MSG_FETCH_MORE) 002");
            this.mHandler.removeMessages(1);
            this.mfetchLooping = false;
            this.mInputStreamNeedSeekByteOffset = j;
            Log.d("CachedSource", "Send message MSG_CACHE_BYTE_SEEK");
            this.mHandler.obtainMessage(4, 0, 0).sendToTarget();
        }
        try {
            try {
                this.mLock.lock();
                this.mCondition.await();
            } catch (InterruptedException e) {
                Log.e("CachedSource", "timeSeek, catch InterruptedException, e:" + e.getMessage());
            }
        } finally {
            this.mLock.unlock();
        }
    }

    private void byteSeekInternal(long j) {
        skipInputStream(this.mInputStreamNeedSeekByteOffset);
        this.mLock.lock();
        this.mCondition.signal();
        this.mLock.unlock();
        Log.d("CachedSource", "sendEmptyMessageDelayed(MSG_FETCH_MORE) 009");
        this.mHandler.sendEmptyMessageDelayed(1, 0L);
        this.mfetchLooping = true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v4, types: [java.util.concurrent.locks.Lock] */
    public void timeSeek(long j) {
        int i = this.mSeekType;
        if (13 == i || i == 0 || 1 == i) {
            Log.e("CachedSource", "mSeekType not support timeSeek");
            return;
        }
        synchronized (this) {
            Log.d("CachedSource", "removeMessages(MSG_FETCH_MORE) 003");
            this.mHandler.removeMessages(1);
            this.mfetchLooping = false;
            this.mInputStreamNeedSeekTimeS = j;
            Log.d("CachedSource", "Send message MSG_CACHE_TIME_SEEK");
            this.mHandler.obtainMessage(3, 0, 0).sendToTarget();
        }
        try {
            try {
                this.mLock.lock();
                this.mCondition.await();
            } catch (InterruptedException e) {
                Log.e("CachedSource", "timeSeek, catch InterruptedException, e:" + e.getMessage());
            }
        } finally {
            this.mLock.unlock();
        }
    }

    private void timeSeekInternal(long j) {
        try {
            ((ContentInputStream) this.mInputStream).timeskip(j, 0L);
            Log.d("CachedSource", "timeSeekInternal timeskip() to:  " + j);
        } catch (Exception e) {
            Log.e("CachedSource", "timeskip(): exception happened! ex:" + e.toString());
        }
        this.mLock.lock();
        this.mCondition.signal();
        this.mLock.unlock();
        Log.d("CachedSource", " sendEmptyMessageDelayed(MSG_FETCH_MORE) 008");
        this.mfetchLooping = true;
        this.mHandler.sendEmptyMessageDelayed(1, 0L);
    }

    private void onFetch() {
        boolean zIsFirstPartOfStream = isFirstPartOfStream(this.readAtLastAccessPosition);
        PageCache pageCache = zIsFirstPartOfStream ? this.globalPageCache : this.globalPageCache2;
        MtkDebugLogUtil.log("CachedSource", "onFetch(), status(" + zIsFirstPartOfStream + ") = " + pageCache.finalStatus + ", fetching = " + pageCache.fetching + ", totalSize = " + pageCache.totalSize + ", isCacheFull = " + pageCache.isCacheFull, 64);
        boolean z = true;
        if (pageCache.finalStatus != 1) {
            Log.d("CachedSource", "isFirstPart:" + zIsFirstPartOfStream);
            Log.d("CachedSource", "onFetch finalStatus:" + pageCache.finalStatus);
            pageCache.fetching = false;
        }
        if (pageCache.fetching && getCacheSize(pageCache) <= 20971520) {
            fetchInternal(pageCache, this.readAtLastAccessPosition);
        }
        if (pageCache.totalSize >= 10485760) {
            boolean z2 = !this.mIsStallingSupport && this.mSeekType == 0;
            MtkDebugLogUtil.log("CachedSource", "isFirstPart(" + zIsFirstPartOfStream + ") cache full, done prefetching for now, isForceRelease=" + z2, 64);
            restartPrefetcherIfNecessary(pageCache, zIsFirstPartOfStream, z2);
        }
        int i = pageCache.fetching ? 0 : 100;
        if (pageCache.finalStatus == -2 || pageCache.finalStatus == -3) {
            Log.d("CachedSource", "break pageCache.finalStatus" + pageCache.finalStatus);
        } else if (this.globalPageCache.finalStatus == -1) {
            Log.d("CachedSource", "globalPageCache.finalStatus" + this.globalPageCache.finalStatus);
        } else {
            try {
                MtkDebugLogUtil.log("CachedSource", "sendEmptyMessageDelayed(MSG_FETCH_MORE, " + i + ") 006", 64);
                this.mHandler.sendEmptyMessageDelayed(1, (long) i);
                this.mfetchLooping = true;
            } catch (IllegalStateException unused) {
            }
            z = false;
        }
        if (z) {
            this.mfetchLooping = false;
            Log.d("CachedSource", "isFirstPart:" + zIsFirstPartOfStream + "finalStatus: " + pageCache.finalStatus + " cancel fetch loop!");
        }
    }

    private void fetchInternal(PageCache pageCache, long j) {
        isFirstPartOfStream(this.readAtLastAccessPosition);
        try {
            Page pageAcquirePage = pageCache.acquirePage();
            if (pageCache.totalSize == 0) {
                if (pageCache.cachedOffset != this.inputStreamLastReadAtByteOffset) {
                    Log.d("CachedSource", "skipInputStream seek stream: new cache");
                    skipInputStream(pageCache.cachedOffset);
                    this.inputStreamLastReadAtByteOffset = pageCache.cachedOffset;
                    Log.d("CachedSource", "inputStreamLastReadAtByteOffset = " + this.inputStreamLastReadAtByteOffset);
                }
            } else if (pageCache.cachedOffset + ((long) pageCache.totalSize) != this.inputStreamLastReadAtByteOffset) {
                Log.d("CachedSource", "test skipInputStream seek stream: default");
                skipInputStream(pageCache.cachedOffset + ((long) pageCache.totalSize));
                this.inputStreamLastReadAtByteOffset = pageCache.cachedOffset + ((long) pageCache.totalSize);
            }
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                MtkDebugLogUtil.log("CachedSource", "+mInputStream.read()", 64);
                int i = this.mInputStream.read(pageAcquirePage.data, 0, MtkTvScanDvbsBase.SB_DVBS_CONFIG_BGM_SCAN);
                MtkDebugLogUtil.log("CachedSource", "-mInputStream.read()", 64);
                MtkDebugLogUtil.log("CachedSource", "-inputStream.read, ret = " + i + ", spendTime(ms) = " + (System.currentTimeMillis() - jCurrentTimeMillis), 64);
                if (i == -1) {
                    pageCache.finalStatus = -1;
                    Log.d("CachedSource", "pageCache.finalStatus = STATUS_END_OF_STREAM");
                    pageCache.releasePage(pageAcquirePage);
                } else {
                    if (i == -2) {
                        pageCache.finalStatus = -2;
                        pageCache.releasePage(pageAcquirePage);
                        return;
                    }
                    pageCache.finalStatus = 1;
                    pageAcquirePage.size = i;
                    pageCache.appendPage(pageAcquirePage);
                    this.inputStreamLastReadAtByteOffset = pageCache.cachedOffset + ((long) pageCache.totalSize);
                    MtkDebugLogUtil.log("CachedSource", "005 inputStreamLastReadAtByteOffset = " + this.inputStreamLastReadAtByteOffset, 64);
                }
            } catch (IOException e) {
                Log.e("CachedSource", "inputStream.read, catch IOException: " + e.getMessage());
                pageCache.releasePage(pageAcquirePage);
                pageCache.finalStatus = -3;
                Log.e("CachedSource", "pageCache.finalStatus = STATUS_READ_EXCEPTION");
            }
        } catch (Exception e2) {
            Log.e("CachedSource", "acquirePage(): exception:" + e2.toString());
        }
    }

    private void onReadCache(ReadRequest readRequest) throws IOException {
        int cacheInternal = readCacheInternal(readRequest);
        if (cacheInternal == 2 || cacheInternal == 3) {
            Handler handler = this.mHandler;
            handler.sendMessageDelayed(handler.obtainMessage(2, readRequest), 10L);
            return;
        }
        readRequest.pageCache.asyncReadResult = cacheInternal;
        this.mLock.lock();
        synchronized (readRequest.lock) {
            readRequest.loading = false;
        }
        this.mCondition.signal();
        this.mLock.unlock();
    }

    private int readCacheInternal(ReadRequest readRequest) throws IOException {
        long j = readRequest.offset;
        byte[] bArr = readRequest.data;
        int i = readRequest.size;
        PageCache pageCache = readRequest.pageCache;
        MtkDebugLogUtil.log("CachedSource", "readCacheInternal offset: " + readRequest.offset + " size:" + readRequest.size + " cachedOffset:" + pageCache.cachedOffset + " totalSize:" + pageCache.totalSize + " finalStatus = " + pageCache.finalStatus + " lastAccessPosition:" + pageCache.lastAccessPosition, 64);
        if (pageCache.finalStatus == -2 || pageCache.finalStatus == -3) {
            Log.d("CachedSource", "001 server retuen error pageCache.finalStatus" + pageCache.finalStatus);
            return pageCache.finalStatus;
        }
        if (j < pageCache.cachedOffset || ((long) i) + j >= (pageCache.cachedOffset + ((long) pageCache.totalSize)) + 983040) {
            seekCacheInternal(j, pageCache);
            Log.d("CachedSource", "return STATUS_TRY_AGAIN_AFTER_SEEK_INTERNAL");
            return 3;
        }
        int i2 = (int) (j - pageCache.cachedOffset);
        if (pageCache.finalStatus == 1) {
            if (j + ((long) i) <= pageCache.cachedOffset + ((long) pageCache.totalSize)) {
                MtkDebugLogUtil.log("CachedSource", "cache enough pageCache.copy(delta, data, size)", 64);
                pageCache.copy(i2, bArr, i);
                return i;
            }
            MtkDebugLogUtil.log("CachedSource", "deferring readCache, data is not enough", 64);
            if (this.mfetchLooping) {
                return 2;
            }
            Log.d("CachedSource", "sendEmptyMessage(MSG_FETCH_MORE) 010");
            this.mHandler.sendEmptyMessage(1);
            this.mfetchLooping = true;
            return 2;
        }
        Log.d("CachedSource", "pageCache.finalStatus != STATUS_OK 001" + pageCache.finalStatus);
        if (i2 <= pageCache.totalSize) {
            int i3 = pageCache.totalSize - i2;
            if (i3 <= i) {
                i = i3;
            }
            pageCache.copy(i2, bArr, i);
            return i;
        }
        Log.d("CachedSource", "pageCache.finalStatus:" + pageCache.finalStatus);
        return pageCache.finalStatus;
    }

    private void seekCacheInternal(long j, PageCache pageCache) throws IOException {
        pageCache.lastAccessPosition = j;
        if (j >= pageCache.cachedOffset && j <= pageCache.cachedOffset + ((long) pageCache.totalSize)) {
            Log.d("CachedSource", "cache matched, needn't seek ");
            return;
        }
        Log.d("CachedSource", "seekCacheInternal seek cache. offset:" + j);
        this.readAtLastAccessPosition = j;
        pageCache.cachedOffset = j;
        int i = pageCache.totalSize;
        this.mPageCacheLock.writeLock().lock();
        Log.d("CachedSource", "seekCacheInternal, release: " + pageCache.releaseFromStart(i));
        if (pageCache.finalStatus == -1) {
            Log.d("CachedSource", "finalStatus is STATUS_END_OF_STREAM, refetch after seek!");
            Log.d("CachedSource", "sendEmptyMessageDelayed(MSG_FETCH_MORE) 007");
            this.mHandler.sendEmptyMessageDelayed(1, 0L);
            this.mfetchLooping = true;
        }
        pageCache.finalStatus = 1;
        pageCache.fetching = true;
        pageCache.isCacheFull = false;
        this.mPageCacheLock.writeLock().unlock();
    }

    private void restartPrefetcherIfNecessary(PageCache pageCache, boolean z, boolean z2) {
        boolean z3;
        if (z2) {
            z3 = true;
        } else {
            if (pageCache.finalStatus != 1) {
                Log.d("CachedSource", "restart return. finalStatus" + pageCache.finalStatus);
                z3 = false;
            } else {
                z3 = true;
            }
            if (getCacheSize(pageCache) >= 2097152 && pageCache.totalSize < 20971520) {
                MtkDebugLogUtil.log("CachedSource", "return, Still have more than 2 M data, cache is enough", 64);
                z3 = false;
            }
        }
        this.mPageCacheLock.writeLock().lock();
        if (z3) {
            pageCache.cachedOffset += (long) pageCache.releaseFromStart((int) (pageCache.lastAccessPosition - pageCache.cachedOffset));
            Log.d("CachedSource", "isFirstPart(" + z + "), restarting prefetcher, totalSize = " + pageCache.totalSize);
        }
        if (getCacheSize(pageCache) < 10485760) {
            pageCache.fetching = true;
            pageCache.isCacheFull = false;
        } else {
            pageCache.fetching = false;
            pageCache.isCacheFull = true;
        }
        this.mPageCacheLock.writeLock().unlock();
    }

    private void skipInputStream(long j) {
        int i = this.mSeekType;
        if (13 == i || i == 0) {
            Log.e("CachedSource", "mSeekType not support skip");
            return;
        }
        if (2 == i) {
            Log.e("CachedSource", "TIME_ONLY timeskip");
            long jConvertOffsetToTimeUs = convertOffsetToTimeUs(j);
            if (jConvertOffsetToTimeUs >= 0) {
                try {
                    ((ContentInputStream) this.mInputStream).timeskip(jConvertOffsetToTimeUs, 0L);
                    Log.d("CachedSource", "skipInputStream timeskip to: " + jConvertOffsetToTimeUs);
                    return;
                } catch (Exception e) {
                    Log.e("CachedSource", "timeskip(): exception happened! ex:" + e.toString());
                    return;
                }
            }
            return;
        }
        boolean zIsFirstPartOfStream = isFirstPartOfStream(this.readAtLastAccessPosition);
        long j2 = this.inputStreamLastReadAtByteOffset;
        if (j2 == j) {
            Log.d("CachedSource", "001 inputStreamLastReadAtByteOffset == offset " + this.inputStreamLastReadAtByteOffset);
            return;
        }
        if (j2 < j) {
            keepStreamAndSkipStream(j - j2);
            return;
        }
        Log.d("CachedSource", "seek backward ");
        if (this.mInputStream instanceof ContentInputStream) {
            if (!zIsFirstPartOfStream) {
                Log.d("CachedSource", "seek backward reopenStream");
            } else if (this.globalPageCache2.cachedOffset != 0) {
                int unused = this.globalPageCache2.totalSize;
            }
            keepStreamAndSkipStream(j - this.inputStreamLastReadAtByteOffset);
        }
    }

    private void keepStreamAndSkipStream(long j) {
        int i = this.mSeekType;
        if (13 == i || i == 0) {
            Log.e("CachedSource", "keepStreamAndSkipStream mSeekType not support skip");
            return;
        }
        try {
            this.mInputStream.skip(j);
        } catch (IOException unused) {
            Log.d("CachedSource", "IOException e 001");
        }
    }

    private long convertOffsetToTimeUs(long j) {
        long size;
        InputStream inputStream = this.mInputStream;
        int i = 0;
        if (inputStream instanceof ContentInputStream) {
            try {
                ContentInputStream contentInputStream = (ContentInputStream) inputStream;
                Objects.requireNonNull(contentInputStream);
                ContentInputStream.MediaInfo mediaInfo = contentInputStream.new MediaInfo();
                if (mediaInfo.getDuration() != null) {
                    String[] strArrSplit = mediaInfo.getDuration().split(":");
                    i = (Integer.parseInt(strArrSplit[0]) * MtkTvTimeFormatBase.SECONDS_PER_HOUR) + (Integer.parseInt(strArrSplit[1]) * 60) + Integer.parseInt(strArrSplit[2]);
                } else {
                    Log.w("CachedSource", "getDuration(string mode) return null, duration = 0");
                }
                size = this.mDataSource.getSize();
            } catch (Exception e) {
                Log.e("CachedSource", "getmSeekType(): call inputStream.getstallingsupport(), exception happened!" + e.toString());
                size = 0;
            }
        } else {
            size = 0;
        }
        if (size != 0) {
            return (j * ((long) i)) / size;
        }
        return 0L;
    }

    private boolean isFirstPartOfStream(long j) {
        int i = this.mSeekType;
        if (i == 0 || i == 13) {
            this.globalPageCache2.finalStatus = -1;
            return true;
        }
        if (this.globalPageCache2.cachedOffset > 0 && j >= this.globalPageCache2.cachedOffset) {
            return false;
        }
        if (this.globalPageCache.cachedOffset + ((long) this.globalPageCache.totalSize) != this.mFileSize && this.globalPageCache2.cachedOffset == 0) {
            long j2 = this.mFileSize;
            long j3 = this.mMaxBufferSizeForPart2;
            if (j2 > j3 && j > j2 - j3) {
                return false;
            }
        }
        return true;
    }

    public long getCacheSize(PageCache pageCache) {
        return (pageCache.cachedOffset + ((long) pageCache.totalSize)) - pageCache.lastAccessPosition;
    }
}
