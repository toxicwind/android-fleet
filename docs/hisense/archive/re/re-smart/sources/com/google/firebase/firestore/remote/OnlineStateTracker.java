package com.google.firebase.firestore.remote;

import c.a.za;
import com.google.firebase.firestore.core.OnlineState;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.Logger;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class OnlineStateTracker {
    public static final String LOG_TAG = "OnlineStateTracker";
    public static final int MAX_WATCH_STREAM_FAILURES = 1;
    public static final int ONLINE_STATE_TIMEOUT_MS = 10000;
    public final OnlineStateCallback onlineStateCallback;
    public AsyncQueue.DelayedTask onlineStateTimer;
    public int watchStreamFailures;
    public final AsyncQueue workerQueue;
    public OnlineState state = OnlineState.UNKNOWN;
    public boolean shouldWarnClientIsOffline = true;

    /* JADX INFO: Access modifiers changed from: package-private */
    public interface OnlineStateCallback {
        void handleOnlineStateChange(OnlineState onlineState);
    }

    public OnlineStateTracker(AsyncQueue asyncQueue, OnlineStateCallback onlineStateCallback) {
        this.workerQueue = asyncQueue;
        this.onlineStateCallback = onlineStateCallback;
    }

    private void clearOnlineStateTimer() {
        AsyncQueue.DelayedTask delayedTask = this.onlineStateTimer;
        if (delayedTask != null) {
            delayedTask.cancel();
            this.onlineStateTimer = null;
        }
    }

    private void logClientOfflineWarningIfNecessary(String str) {
        String str2 = String.format("Could not reach Cloud Firestore backend. %s\nThis typically indicates that your device does not have a healthy Internet connection at the moment. The client will operate in offline mode until it is able to successfully connect to the backend.", str);
        if (!this.shouldWarnClientIsOffline) {
            Logger.debug(LOG_TAG, "%s", str2);
        } else {
            Logger.warn(LOG_TAG, "%s", str2);
            this.shouldWarnClientIsOffline = false;
        }
    }

    private void setAndBroadcastState(OnlineState onlineState) {
        if (onlineState != this.state) {
            this.state = onlineState;
            this.onlineStateCallback.handleOnlineStateChange(onlineState);
        }
    }

    public /* synthetic */ void a() {
        this.onlineStateTimer = null;
        Assert.hardAssert(this.state == OnlineState.UNKNOWN, "Timer should be canceled if we transitioned to a different state.", new Object[0]);
        logClientOfflineWarningIfNecessary(String.format(Locale.ENGLISH, "Backend didn't respond within %d seconds\n", 10));
        setAndBroadcastState(OnlineState.OFFLINE);
    }

    public OnlineState getState() {
        return this.state;
    }

    public void handleWatchStreamFailure(za zaVar) {
        if (this.state == OnlineState.ONLINE) {
            setAndBroadcastState(OnlineState.UNKNOWN);
            Assert.hardAssert(this.watchStreamFailures == 0, "watchStreamFailures must be 0", new Object[0]);
            Assert.hardAssert(this.onlineStateTimer == null, "onlineStateTimer must be null", new Object[0]);
        } else {
            this.watchStreamFailures++;
            if (this.watchStreamFailures >= 1) {
                clearOnlineStateTimer();
                logClientOfflineWarningIfNecessary(String.format(Locale.ENGLISH, "Connection failed %d times. Most recent error: %s", 1, zaVar));
                setAndBroadcastState(OnlineState.OFFLINE);
            }
        }
    }

    public void handleWatchStreamStart() {
        if (this.watchStreamFailures == 0) {
            setAndBroadcastState(OnlineState.UNKNOWN);
            Assert.hardAssert(this.onlineStateTimer == null, "onlineStateTimer shouldn't be started yet", new Object[0]);
            this.onlineStateTimer = this.workerQueue.enqueueAfterDelay(AsyncQueue.TimerId.ONLINE_STATE_TIMEOUT, 10000L, new Runnable() { // from class: b.e.b.d.f.w
                @Override // java.lang.Runnable
                public final void run() {
                    this.f528a.a();
                }
            });
        }
    }

    public void updateState(OnlineState onlineState) {
        clearOnlineStateTimer();
        this.watchStreamFailures = 0;
        if (onlineState == OnlineState.ONLINE) {
            this.shouldWarnClientIsOffline = false;
        }
        if (onlineState != this.state) {
            this.state = onlineState;
            this.onlineStateCallback.handleOnlineStateChange(onlineState);
        }
    }
}
