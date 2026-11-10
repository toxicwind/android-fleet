package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import b.a.a.a.a;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.base.zar;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public final class GmsClientEventManager implements Handler.Callback {
    public final Handler mHandler;
    public final GmsClientEventState zaov;
    public final ArrayList<GoogleApiClient.ConnectionCallbacks> zaow = new ArrayList<>();

    @VisibleForTesting
    public final ArrayList<GoogleApiClient.ConnectionCallbacks> zaox = new ArrayList<>();
    public final ArrayList<GoogleApiClient.OnConnectionFailedListener> zaoy = new ArrayList<>();
    public volatile boolean zaoz = false;
    public final AtomicInteger zapa = new AtomicInteger(0);
    public boolean zapb = false;
    public final Object mLock = new Object();

    @VisibleForTesting
    public interface GmsClientEventState {
        Bundle getConnectionHint();

        boolean isConnected();
    }

    public GmsClientEventManager(Looper looper, GmsClientEventState gmsClientEventState) {
        this.zaov = gmsClientEventState;
        this.mHandler = new zar(looper, this);
    }

    public final boolean areCallbacksEnabled() {
        return this.zaoz;
    }

    public final void disableCallbacks() {
        this.zaoz = false;
        this.zapa.incrementAndGet();
    }

    public final void enableCallbacks() {
        this.zaoz = true;
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        int i = message.what;
        if (i != 1) {
            Log.wtf("GmsClientEvents", a.a(45, "Don't know how to handle message: ", i), new Exception());
            return false;
        }
        GoogleApiClient.ConnectionCallbacks connectionCallbacks = (GoogleApiClient.ConnectionCallbacks) message.obj;
        synchronized (this.mLock) {
            if (this.zaoz && this.zaov.isConnected() && this.zaow.contains(connectionCallbacks)) {
                connectionCallbacks.onConnected(this.zaov.getConnectionHint());
            }
        }
        return true;
    }

    public final boolean isConnectionCallbacksRegistered(GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        boolean zContains;
        Preconditions.checkNotNull(connectionCallbacks);
        synchronized (this.mLock) {
            zContains = this.zaow.contains(connectionCallbacks);
        }
        return zContains;
    }

    public final boolean isConnectionFailedListenerRegistered(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        boolean zContains;
        Preconditions.checkNotNull(onConnectionFailedListener);
        synchronized (this.mLock) {
            zContains = this.zaoy.contains(onConnectionFailedListener);
        }
        return zContains;
    }

    @VisibleForTesting
    public final void onConnectionFailure(ConnectionResult connectionResult) {
        Preconditions.checkHandlerThread(this.mHandler, "onConnectionFailure must only be called on the Handler thread");
        this.mHandler.removeMessages(1);
        synchronized (this.mLock) {
            ArrayList arrayList = new ArrayList(this.zaoy);
            int i = this.zapa.get();
            int size = arrayList.size();
            int i2 = 0;
            while (i2 < size) {
                Object obj = arrayList.get(i2);
                i2++;
                GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener = (GoogleApiClient.OnConnectionFailedListener) obj;
                if (this.zaoz && this.zapa.get() == i) {
                    if (this.zaoy.contains(onConnectionFailedListener)) {
                        onConnectionFailedListener.onConnectionFailed(connectionResult);
                    }
                }
                return;
            }
        }
    }

    @VisibleForTesting
    public final void onConnectionSuccess() {
        synchronized (this.mLock) {
            onConnectionSuccess(this.zaov.getConnectionHint());
        }
    }

    @VisibleForTesting
    public final void onUnintentionalDisconnection(int i) {
        Preconditions.checkHandlerThread(this.mHandler, "onUnintentionalDisconnection must only be called on the Handler thread");
        this.mHandler.removeMessages(1);
        synchronized (this.mLock) {
            this.zapb = true;
            ArrayList arrayList = new ArrayList(this.zaow);
            int i2 = this.zapa.get();
            int size = arrayList.size();
            int i3 = 0;
            while (i3 < size) {
                Object obj = arrayList.get(i3);
                i3++;
                GoogleApiClient.ConnectionCallbacks connectionCallbacks = (GoogleApiClient.ConnectionCallbacks) obj;
                if (!this.zaoz || this.zapa.get() != i2) {
                    break;
                } else if (this.zaow.contains(connectionCallbacks)) {
                    connectionCallbacks.onConnectionSuspended(i);
                }
            }
            this.zaox.clear();
            this.zapb = false;
        }
    }

    public final void registerConnectionCallbacks(GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        Preconditions.checkNotNull(connectionCallbacks);
        synchronized (this.mLock) {
            if (this.zaow.contains(connectionCallbacks)) {
                String strValueOf = String.valueOf(connectionCallbacks);
                StringBuilder sb = new StringBuilder(strValueOf.length() + 62);
                sb.append("registerConnectionCallbacks(): listener ");
                sb.append(strValueOf);
                sb.append(" is already registered");
                Log.w("GmsClientEvents", sb.toString());
            } else {
                this.zaow.add(connectionCallbacks);
            }
        }
        if (this.zaov.isConnected()) {
            Handler handler = this.mHandler;
            handler.sendMessage(handler.obtainMessage(1, connectionCallbacks));
        }
    }

    public final void registerConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        Preconditions.checkNotNull(onConnectionFailedListener);
        synchronized (this.mLock) {
            if (this.zaoy.contains(onConnectionFailedListener)) {
                String strValueOf = String.valueOf(onConnectionFailedListener);
                StringBuilder sb = new StringBuilder(strValueOf.length() + 67);
                sb.append("registerConnectionFailedListener(): listener ");
                sb.append(strValueOf);
                sb.append(" is already registered");
                Log.w("GmsClientEvents", sb.toString());
            } else {
                this.zaoy.add(onConnectionFailedListener);
            }
        }
    }

    public final void unregisterConnectionCallbacks(GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        Preconditions.checkNotNull(connectionCallbacks);
        synchronized (this.mLock) {
            if (!this.zaow.remove(connectionCallbacks)) {
                String strValueOf = String.valueOf(connectionCallbacks);
                StringBuilder sb = new StringBuilder(strValueOf.length() + 52);
                sb.append("unregisterConnectionCallbacks(): listener ");
                sb.append(strValueOf);
                sb.append(" not found");
                Log.w("GmsClientEvents", sb.toString());
            } else if (this.zapb) {
                this.zaox.add(connectionCallbacks);
            }
        }
    }

    public final void unregisterConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        Preconditions.checkNotNull(onConnectionFailedListener);
        synchronized (this.mLock) {
            if (!this.zaoy.remove(onConnectionFailedListener)) {
                String strValueOf = String.valueOf(onConnectionFailedListener);
                StringBuilder sb = new StringBuilder(strValueOf.length() + 57);
                sb.append("unregisterConnectionFailedListener(): listener ");
                sb.append(strValueOf);
                sb.append(" not found");
                Log.w("GmsClientEvents", sb.toString());
            }
        }
    }

    @VisibleForTesting
    public final void onConnectionSuccess(Bundle bundle) {
        Preconditions.checkHandlerThread(this.mHandler, "onConnectionSuccess must only be called on the Handler thread");
        synchronized (this.mLock) {
            boolean z = true;
            Preconditions.checkState(!this.zapb);
            this.mHandler.removeMessages(1);
            this.zapb = true;
            if (this.zaox.size() != 0) {
                z = false;
            }
            Preconditions.checkState(z);
            ArrayList arrayList = new ArrayList(this.zaow);
            int i = this.zapa.get();
            int size = arrayList.size();
            int i2 = 0;
            while (i2 < size) {
                Object obj = arrayList.get(i2);
                i2++;
                GoogleApiClient.ConnectionCallbacks connectionCallbacks = (GoogleApiClient.ConnectionCallbacks) obj;
                if (!this.zaoz || !this.zaov.isConnected() || this.zapa.get() != i) {
                    break;
                } else if (!this.zaox.contains(connectionCallbacks)) {
                    connectionCallbacks.onConnected(bundle);
                }
            }
            this.zaox.clear();
            this.zapb = false;
        }
    }
}
