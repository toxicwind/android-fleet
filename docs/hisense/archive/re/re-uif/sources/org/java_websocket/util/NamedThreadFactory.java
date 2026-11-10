package org.java_websocket.util;

import com.mediatek.twoworlds.tv.MtkTvScanDvbsBase;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public class NamedThreadFactory implements ThreadFactory {
    private final ThreadFactory defaultThreadFactory = Executors.defaultThreadFactory();
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String threadPrefix;

    public NamedThreadFactory(String str) {
        this.threadPrefix = str;
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        Thread threadNewThread = this.defaultThreadFactory.newThread(runnable);
        threadNewThread.setName(this.threadPrefix + MtkTvScanDvbsBase.TVAPI_DVBS_LINK_STRING + this.threadNumber);
        return threadNewThread;
    }
}
