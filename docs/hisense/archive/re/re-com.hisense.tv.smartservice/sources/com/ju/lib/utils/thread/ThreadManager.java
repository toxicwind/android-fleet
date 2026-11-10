package com.ju.lib.utils.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes2.dex */
public class ThreadManager {
    public static final int DEFAULT_POOL_SIZE = 10;
    public static final ExecutorService sThreadPool = Executors.newFixedThreadPool(10, new JuThreadFactory("baseLib-ThreadManager"));

    public static ExecutorService getPool() {
        return sThreadPool;
    }
}
