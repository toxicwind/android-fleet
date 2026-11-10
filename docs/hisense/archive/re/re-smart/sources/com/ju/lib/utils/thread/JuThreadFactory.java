package com.ju.lib.utils.thread;

import b.a.a.a.a;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes2.dex */
public class JuThreadFactory implements ThreadFactory {
    public final ThreadGroup group;
    public final String namePrefix;
    public final AtomicInteger threadNumber = new AtomicInteger(1);

    public JuThreadFactory(String str) {
        SecurityManager securityManager = System.getSecurityManager();
        this.group = securityManager != null ? securityManager.getThreadGroup() : Thread.currentThread().getThreadGroup();
        this.namePrefix = a.a(str, "-thread-");
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        JuThread juThread = new JuThread(this.group, runnable, this.namePrefix + this.threadNumber.getAndIncrement(), 0L);
        if (juThread.isDaemon()) {
            juThread.setDaemon(false);
        }
        if (juThread.getPriority() != 5) {
            juThread.setPriority(5);
        }
        return juThread;
    }
}
