package com.google.common.util.concurrent;

import java.util.concurrent.locks.LockSupport;

/* JADX INFO: loaded from: classes.dex */
public final class OverflowAvoidingLockSupport {
    public static final long MAX_NANOSECONDS_THRESHOLD = 2147483647999999999L;

    public static void parkNanos(Object obj, long j) {
        LockSupport.parkNanos(obj, Math.min(j, MAX_NANOSECONDS_THRESHOLD));
    }
}
