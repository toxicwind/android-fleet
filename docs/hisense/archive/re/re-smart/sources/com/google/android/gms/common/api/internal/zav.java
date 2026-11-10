package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.util.concurrent.HandlerExecutor;
import com.google.android.gms.signin.SignInOptions;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/* JADX INFO: loaded from: classes.dex */
public final class zav implements zabr {
    public final Looper zabl;
    public final GoogleApiManager zabo;
    public final Lock zaer;
    public final Map<Api<?>, Boolean> zaew;
    public final zaaw zaex;
    public final GoogleApiAvailabilityLight zaey;
    public final Condition zaez;
    public final ClientSettings zafa;
    public final boolean zafb;
    public final boolean zafc;
    public boolean zafe;
    public Map<ApiKey<?>, ConnectionResult> zaff;
    public Map<ApiKey<?>, ConnectionResult> zafg;
    public zaaa zafh;
    public ConnectionResult zafi;
    public final Map<Api.AnyClientKey<?>, zaw<?>> zaeu = new HashMap();
    public final Map<Api.AnyClientKey<?>, zaw<?>> zaev = new HashMap();
    public final Queue<BaseImplementation.ApiMethodImpl<?, ?>> zafd = new LinkedList();

    public zav(Context context, Lock lock, Looper looper, GoogleApiAvailabilityLight googleApiAvailabilityLight, Map<Api.AnyClientKey<?>, Api.Client> map, ClientSettings clientSettings, Map<Api<?>, Boolean> map2, Api.AbstractClientBuilder<? extends com.google.android.gms.signin.zac, SignInOptions> abstractClientBuilder, ArrayList<zap> arrayList, zaaw zaawVar, boolean z) {
        boolean z2;
        boolean z3;
        boolean z4;
        this.zaer = lock;
        this.zabl = looper;
        this.zaez = lock.newCondition();
        this.zaey = googleApiAvailabilityLight;
        this.zaex = zaawVar;
        this.zaew = map2;
        this.zafa = clientSettings;
        this.zafb = z;
        HashMap map3 = new HashMap();
        for (Api<?> api : map2.keySet()) {
            map3.put(api.getClientKey(), api);
        }
        HashMap map4 = new HashMap();
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            zap zapVar = arrayList.get(i);
            i++;
            zap zapVar2 = zapVar;
            map4.put(zapVar2.mApi, zapVar2);
        }
        boolean z5 = true;
        boolean z6 = false;
        boolean z7 = false;
        for (Map.Entry<Api.AnyClientKey<?>, Api.Client> entry : map.entrySet()) {
            Api api2 = (Api) map3.get(entry.getKey());
            Api.Client value = entry.getValue();
            if (!value.requiresGooglePlayServices()) {
                z2 = z6;
                z3 = z7;
                z4 = false;
            } else if (this.zaew.get(api2).booleanValue()) {
                z4 = z5;
                z3 = z7;
                z2 = true;
            } else {
                z4 = z5;
                z3 = true;
                z2 = true;
            }
            zaw<?> zawVar = new zaw<>(context, api2, looper, value, (zap) map4.get(api2), clientSettings, abstractClientBuilder);
            this.zaeu.put(entry.getKey(), zawVar);
            if (value.requiresSignIn()) {
                this.zaev.put(entry.getKey(), zawVar);
            }
            z7 = z3;
            z5 = z4;
            z6 = z2;
        }
        this.zafc = (!z6 || z5 || z7) ? false : true;
        this.zabo = GoogleApiManager.zaba();
    }

    @Nullable
    private final ConnectionResult zaa(@NonNull Api.AnyClientKey<?> anyClientKey) {
        this.zaer.lock();
        try {
            zaw<?> zawVar = this.zaeu.get(anyClientKey);
            if (this.zaff == null || zawVar == null) {
                return null;
            }
            return this.zaff.get(zawVar.getApiKey());
        } finally {
            this.zaer.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zaaa() {
        ClientSettings clientSettings = this.zafa;
        if (clientSettings == null) {
            this.zaex.zahe = Collections.emptySet();
            return;
        }
        HashSet hashSet = new HashSet(clientSettings.getRequiredScopes());
        Map<Api<?>, ClientSettings.OptionalApiSettings> optionalApiSettings = this.zafa.getOptionalApiSettings();
        for (Api<?> api : optionalApiSettings.keySet()) {
            ConnectionResult connectionResult = getConnectionResult(api);
            if (connectionResult != null && connectionResult.isSuccess()) {
                hashSet.addAll(optionalApiSettings.get(api).mScopes);
            }
        }
        this.zaex.zahe = hashSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zaab() {
        while (!this.zafd.isEmpty()) {
            execute(this.zafd.remove());
        }
        this.zaex.zab((Bundle) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Nullable
    public final ConnectionResult zaac() {
        ConnectionResult connectionResult = null;
        int i = 0;
        int i2 = 0;
        ConnectionResult connectionResult2 = null;
        for (zaw<?> zawVar : this.zaeu.values()) {
            Api<?> api = zawVar.getApi();
            ConnectionResult connectionResult3 = this.zaff.get(zawVar.getApiKey());
            if (!connectionResult3.isSuccess() && (!this.zaew.get(api).booleanValue() || connectionResult3.hasResolution() || this.zaey.isUserResolvableError(connectionResult3.getErrorCode()))) {
                if (connectionResult3.getErrorCode() == 4 && this.zafb) {
                    int priority = api.zah().getPriority();
                    if (connectionResult2 == null || i2 > priority) {
                        connectionResult2 = connectionResult3;
                        i2 = priority;
                    }
                } else {
                    int priority2 = api.zah().getPriority();
                    if (connectionResult == null || i > priority2) {
                        connectionResult = connectionResult3;
                        i = priority2;
                    }
                }
            }
        }
        return (connectionResult == null || connectionResult2 == null || i <= i2) ? connectionResult : connectionResult2;
    }

    private final <T extends BaseImplementation.ApiMethodImpl<? extends Result, ? extends Api.AnyClient>> boolean zab(@NonNull T t) {
        Api.AnyClientKey<?> clientKey = t.getClientKey();
        ConnectionResult connectionResultZaa = zaa(clientKey);
        if (connectionResultZaa == null || connectionResultZaa.getErrorCode() != 4) {
            return false;
        }
        t.setFailedResult(new Status(1, 4, null, this.zabo.zaa(this.zaeu.get(clientKey).getApiKey(), System.identityHashCode(this.zaex))));
        return true;
    }

    private final boolean zaz() {
        this.zaer.lock();
        try {
            if (this.zafe && this.zafb) {
                Iterator<Api.AnyClientKey<?>> it = this.zaev.keySet().iterator();
                while (it.hasNext()) {
                    ConnectionResult connectionResultZaa = zaa(it.next());
                    if (connectionResultZaa == null || !connectionResultZaa.isSuccess()) {
                        this.zaer.unlock();
                        return false;
                    }
                }
                this.zaer.unlock();
                return true;
            }
            this.zaer.unlock();
            return false;
        } catch (Throwable th) {
            this.zaer.unlock();
            throw th;
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabr
    public final ConnectionResult blockingConnect() {
        connect();
        while (isConnecting()) {
            try {
                this.zaez.await();
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
                return new ConnectionResult(15, null);
            }
        }
        if (isConnected()) {
            return ConnectionResult.RESULT_SUCCESS;
        }
        ConnectionResult connectionResult = this.zafi;
        return connectionResult != null ? connectionResult : new ConnectionResult(13, null);
    }

    @Override // com.google.android.gms.common.api.internal.zabr
    public final void connect() {
        this.zaer.lock();
        try {
            if (this.zafe) {
                return;
            }
            this.zafe = true;
            this.zaff = null;
            this.zafg = null;
            this.zafh = null;
            this.zafi = null;
            this.zabo.zam();
            this.zabo.zaa(this.zaeu.values()).addOnCompleteListener(new HandlerExecutor(this.zabl), new zax(this, null));
        } finally {
            this.zaer.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabr
    public final void disconnect() {
        this.zaer.lock();
        try {
            this.zafe = false;
            this.zaff = null;
            this.zafg = null;
            if (this.zafh != null) {
                this.zafh.cancel();
                this.zafh = null;
            }
            this.zafi = null;
            while (!this.zafd.isEmpty()) {
                BaseImplementation.ApiMethodImpl<?, ?> apiMethodImplRemove = this.zafd.remove();
                apiMethodImplRemove.zaa((zacq) null);
                apiMethodImplRemove.cancel();
            }
            this.zaez.signalAll();
        } finally {
            this.zaer.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabr
    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    @Override // com.google.android.gms.common.api.internal.zabr
    public final <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(@NonNull T t) {
        if (this.zafb && zab(t)) {
            return t;
        }
        if (isConnected()) {
            this.zaex.zahj.zac(t);
            return (T) this.zaeu.get(t.getClientKey()).doRead(t);
        }
        this.zafd.add(t);
        return t;
    }

    @Override // com.google.android.gms.common.api.internal.zabr
    public final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(@NonNull T t) {
        Api.AnyClientKey<A> clientKey = t.getClientKey();
        if (this.zafb && zab(t)) {
            return t;
        }
        this.zaex.zahj.zac(t);
        return (T) this.zaeu.get(clientKey).doWrite(t);
    }

    @Override // com.google.android.gms.common.api.internal.zabr
    @Nullable
    public final ConnectionResult getConnectionResult(@NonNull Api<?> api) {
        return zaa(api.getClientKey());
    }

    @Override // com.google.android.gms.common.api.internal.zabr
    public final boolean isConnected() {
        this.zaer.lock();
        try {
            return this.zaff != null && this.zafi == null;
        } finally {
            this.zaer.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabr
    public final boolean isConnecting() {
        this.zaer.lock();
        try {
            return this.zaff == null && this.zafe;
        } finally {
            this.zaer.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabr
    public final boolean maybeSignIn(SignInConnectionListener signInConnectionListener) {
        Lock lock;
        this.zaer.lock();
        try {
            if (!this.zafe || zaz()) {
                return false;
            }
            this.zabo.zam();
            this.zafh = new zaaa(this, signInConnectionListener);
            this.zabo.zaa(this.zaev.values()).addOnCompleteListener(new HandlerExecutor(this.zabl), this.zafh);
            return true;
        } finally {
            this.zaer.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabr
    public final void maybeSignOut() {
        this.zaer.lock();
        try {
            this.zabo.maybeSignOut();
            if (this.zafh != null) {
                this.zafh.cancel();
                this.zafh = null;
            }
            if (this.zafg == null) {
                this.zafg = new ArrayMap(this.zaev.size());
            }
            ConnectionResult connectionResult = new ConnectionResult(4);
            Iterator<zaw<?>> it = this.zaev.values().iterator();
            while (it.hasNext()) {
                this.zafg.put(it.next().getApiKey(), connectionResult);
            }
            if (this.zaff != null) {
                this.zaff.putAll(this.zafg);
            }
        } finally {
            this.zaer.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabr
    public final void zau() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean zaa(zaw<?> zawVar, ConnectionResult connectionResult) {
        return !connectionResult.isSuccess() && !connectionResult.hasResolution() && this.zaew.get(zawVar.getApi()).booleanValue() && zawVar.zaad().requiresGooglePlayServices() && this.zaey.isUserResolvableError(connectionResult.getErrorCode());
    }

    @Override // com.google.android.gms.common.api.internal.zabr
    public final ConnectionResult blockingConnect(long j, TimeUnit timeUnit) {
        connect();
        long nanos = timeUnit.toNanos(j);
        while (isConnecting()) {
            if (nanos <= 0) {
                disconnect();
                return new ConnectionResult(14, null);
            }
            try {
                nanos = this.zaez.awaitNanos(nanos);
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
                return new ConnectionResult(15, null);
            }
            Thread.currentThread().interrupt();
            return new ConnectionResult(15, null);
        }
        if (isConnected()) {
            return ConnectionResult.RESULT_SUCCESS;
        }
        ConnectionResult connectionResult = this.zafi;
        return connectionResult != null ? connectionResult : new ConnectionResult(13, null);
    }

    public static /* synthetic */ boolean zaa(zav zavVar, boolean z) {
        zavVar.zafe = false;
        return false;
    }
}
