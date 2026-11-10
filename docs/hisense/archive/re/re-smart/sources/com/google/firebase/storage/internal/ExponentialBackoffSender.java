package com.google.firebase.storage.internal;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.firebase.appcheck.interop.InternalAppCheckTokenProvider;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.storage.network.NetworkRequest;
import java.util.Random;

/* JADX INFO: loaded from: classes.dex */
public class ExponentialBackoffSender {
    public static final int MAXIMUM_WAIT_TIME_MILLI = 30000;
    public static final int NETWORK_STATUS_POLL_INTERVAL = 1000;
    public static final int RND_MAX = 250;
    public static final String TAG = "ExponenentialBackoff";

    @Nullable
    public final InternalAppCheckTokenProvider appCheckProvider;

    @Nullable
    public final InternalAuthProvider authProvider;
    public volatile boolean canceled;
    public final Context context;
    public long retryTime;
    public static final Random random = new Random();
    public static Sleeper sleeper = new SleeperImpl();
    public static Clock clock = DefaultClock.zza;

    public ExponentialBackoffSender(Context context, @Nullable InternalAuthProvider internalAuthProvider, @Nullable InternalAppCheckTokenProvider internalAppCheckTokenProvider, long j) {
        this.context = context;
        this.authProvider = internalAuthProvider;
        this.appCheckProvider = internalAppCheckTokenProvider;
        this.retryTime = j;
    }

    public void cancel() {
        this.canceled = true;
    }

    public boolean isRetryableError(int i) {
        return (i >= 500 && i < 600) || i == -2 || i == 429 || i == 408;
    }

    public void reset() {
        this.canceled = false;
    }

    public void sendWithExponentialBackoff(@NonNull NetworkRequest networkRequest) {
        sendWithExponentialBackoff(networkRequest, true);
    }

    public void sendWithExponentialBackoff(@NonNull NetworkRequest networkRequest, boolean z) {
        Preconditions.checkNotNull(networkRequest);
        long jElapsedRealtime = clock.elapsedRealtime() + this.retryTime;
        if (z) {
            networkRequest.performRequest(Util.getCurrentAuthToken(this.authProvider), Util.getCurrentAppCheckToken(this.appCheckProvider), this.context);
        } else {
            networkRequest.performRequestStart(Util.getCurrentAuthToken(this.authProvider), Util.getCurrentAppCheckToken(this.appCheckProvider));
        }
        int i = 1000;
        while (clock.elapsedRealtime() + ((long) i) <= jElapsedRealtime && !networkRequest.isResultSuccess() && isRetryableError(networkRequest.getResultCode())) {
            try {
                sleeper.sleep(random.nextInt(250) + i);
                if (i < 30000) {
                    if (networkRequest.getResultCode() != -2) {
                        i *= 2;
                        Log.w(TAG, "network error occurred, backing off/sleeping.");
                    } else {
                        Log.w(TAG, "network unavailable, sleeping.");
                        i = 1000;
                    }
                }
                if (this.canceled) {
                    return;
                }
                networkRequest.reset();
                if (z) {
                    networkRequest.performRequest(Util.getCurrentAuthToken(this.authProvider), Util.getCurrentAppCheckToken(this.appCheckProvider), this.context);
                } else {
                    networkRequest.performRequestStart(Util.getCurrentAuthToken(this.authProvider), Util.getCurrentAppCheckToken(this.appCheckProvider));
                }
            } catch (InterruptedException unused) {
                Log.w(TAG, "thread interrupted during exponential backoff.");
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
