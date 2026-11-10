package com.google.android.exoplayer.upstream;

import java.util.PriorityQueue;
import kotlin.jvm.internal.IntCompanionObject;

/* JADX INFO: loaded from: classes.dex */
public final class NetworkLock {
    public static final NetworkLock instance = new NetworkLock();
    private final Object lock = new Object();
    private final PriorityQueue<Integer> queue = new PriorityQueue<>();
    private int highestPriority = IntCompanionObject.MAX_VALUE;

    private NetworkLock() {
    }

    public void add(int i) {
        synchronized (this.lock) {
            this.queue.add(Integer.valueOf(i));
            this.highestPriority = Math.min(this.highestPriority, i);
        }
    }

    public void remove(int i) {
        synchronized (this.lock) {
            this.queue.remove(Integer.valueOf(i));
            this.highestPriority = this.queue.isEmpty() ? IntCompanionObject.MAX_VALUE : this.queue.peek().intValue();
            this.lock.notifyAll();
        }
    }
}
