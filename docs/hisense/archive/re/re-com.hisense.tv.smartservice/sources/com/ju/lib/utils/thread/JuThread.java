package com.ju.lib.utils.thread;

import b.a.a.a.a;

/* JADX INFO: loaded from: classes2.dex */
public class JuThread extends Thread {
    public JuThread(String str) {
        super(a.a(Constants.THREAD_NAME_PREFIX, str));
    }

    public JuThread(Runnable runnable, String str) {
        super(runnable, a.a(Constants.THREAD_NAME_PREFIX, str));
    }

    public JuThread(ThreadGroup threadGroup, Runnable runnable, String str) {
        super(threadGroup, runnable, a.a(Constants.THREAD_NAME_PREFIX, str));
    }

    public JuThread(ThreadGroup threadGroup, String str) {
        super(threadGroup, a.a(Constants.THREAD_NAME_PREFIX, str));
    }

    public JuThread(ThreadGroup threadGroup, Runnable runnable, String str, long j) {
        super(threadGroup, runnable, a.a(Constants.THREAD_NAME_PREFIX, str), j);
    }
}
