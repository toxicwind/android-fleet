package com.google.firebase.installations.remote;

import androidx.annotation.GuardedBy;
import com.google.firebase.installations.Utils;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class RequestLimiter {
    public static final long MAXIMUM_BACKOFF_DURATION_FOR_CONFIGURATION_ERRORS = TimeUnit.HOURS.toMillis(24);
    public static final long MAXIMUM_BACKOFF_DURATION_FOR_SERVER_ERRORS = TimeUnit.MINUTES.toMillis(30);

    @GuardedBy("this")
    public int attemptCount;

    @GuardedBy("this")
    public long nextRequestTime;
    public final Utils utils;

    public RequestLimiter(Utils utils) {
        this.utils = utils;
    }

    private synchronized long getBackoffDuration(int i) {
        if (isRetryableError(i)) {
            return (long) Math.min(Math.pow(2.0d, this.attemptCount) + this.utils.getRandomDelayForSyncPrevention(), MAXIMUM_BACKOFF_DURATION_FOR_SERVER_ERRORS);
        }
        return MAXIMUM_BACKOFF_DURATION_FOR_CONFIGURATION_ERRORS;
    }

    public static boolean isRetryableError(int i) {
        return i == 429 || (i >= 500 && i < 600);
    }

    public static boolean isSuccessfulOrRequiresNewFidCreation(int i) {
        return (i >= 200 && i < 300) || i == 401 || i == 404;
    }

    private synchronized void resetBackoffStrategy() {
        this.attemptCount = 0;
    }

    public synchronized boolean isRequestAllowed() {
        return this.attemptCount == 0 || this.utils.currentTimeInMillis() > this.nextRequestTime;
    }

    public synchronized void setNextRequestTime(int i) {
        if (isSuccessfulOrRequiresNewFidCreation(i)) {
            resetBackoffStrategy();
            return;
        }
        this.attemptCount++;
        this.nextRequestTime = this.utils.currentTimeInMillis() + getBackoffDuration(i);
    }

    public RequestLimiter() {
        this.utils = Utils.getInstance();
    }
}
