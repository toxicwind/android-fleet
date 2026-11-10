package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.signin.SignInOptions;
import com.google.firebase.installations.Utils;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/* JADX INFO: loaded from: classes.dex */
public final class zaq implements zabr {
    public final Context mContext;
    public final Looper zabl;
    public final zaaw zaeh;
    public final zabe zaei;
    public final zabe zaej;
    public final Map<Api.AnyClientKey<?>, zabe> zaek;
    public final Api.Client zaem;
    public Bundle zaen;
    public final Lock zaer;
    public final Set<SignInConnectionListener> zael = Collections.newSetFromMap(new WeakHashMap());
    public ConnectionResult zaeo = null;
    public ConnectionResult zaep = null;
    public boolean zaeq = false;
    public int zaes = 0;

    public zaq(Context context, zaaw zaawVar, Lock lock, Looper looper, GoogleApiAvailabilityLight googleApiAvailabilityLight, Map<Api.AnyClientKey<?>, Api.Client> map, Map<Api.AnyClientKey<?>, Api.Client> map2, ClientSettings clientSettings, Api.AbstractClientBuilder<? extends com.google.android.gms.signin.zac, SignInOptions> abstractClientBuilder, Api.Client client, ArrayList<zap> arrayList, ArrayList<zap> arrayList2, Map<Api<?>, Boolean> map3, Map<Api<?>, Boolean> map4) {
        this.mContext = context;
        this.zaeh = zaawVar;
        this.zaer = lock;
        this.zabl = looper;
        this.zaem = client;
        this.zaei = new zabe(context, this.zaeh, lock, looper, googleApiAvailabilityLight, map2, null, map4, null, arrayList2, new zas(this, null));
        this.zaej = new zabe(context, this.zaeh, lock, looper, googleApiAvailabilityLight, map, clientSettings, map3, abstractClientBuilder, arrayList, new zau(this, null));
        ArrayMap arrayMap = new ArrayMap();
        Iterator<Api.AnyClientKey<?>> it = map2.keySet().iterator();
        while (it.hasNext()) {
            arrayMap.put(it.next(), this.zaei);
        }
        Iterator<Api.AnyClientKey<?>> it2 = map.keySet().iterator();
        while (it2.hasNext()) {
            arrayMap.put(it2.next(), this.zaej);
        }
        this.zaek = Collections.unmodifiableMap(arrayMap);
    }

    public static zaq zaa(Context context, zaaw zaawVar, Lock lock, Looper looper, GoogleApiAvailabilityLight googleApiAvailabilityLight, Map<Api.AnyClientKey<?>, Api.Client> map, ClientSettings clientSettings, Map<Api<?>, Boolean> map2, Api.AbstractClientBuilder<? extends com.google.android.gms.signin.zac, SignInOptions> abstractClientBuilder, ArrayList<zap> arrayList) {
        ArrayMap arrayMap = new ArrayMap();
        ArrayMap arrayMap2 = new ArrayMap();
        Api.Client client = null;
        for (Map.Entry<Api.AnyClientKey<?>, Api.Client> entry : map.entrySet()) {
            Api.Client value = entry.getValue();
            if (value.providesSignIn()) {
                client = value;
            }
            if (value.requiresSignIn()) {
                arrayMap.put(entry.getKey(), value);
            } else {
                arrayMap2.put(entry.getKey(), value);
            }
        }
        Preconditions.checkState(!arrayMap.isEmpty(), "CompositeGoogleApiClient should not be used without any APIs that require sign-in.");
        ArrayMap arrayMap3 = new ArrayMap();
        ArrayMap arrayMap4 = new ArrayMap();
        for (Api<?> api : map2.keySet()) {
            Api.AnyClientKey<?> clientKey = api.getClientKey();
            if (arrayMap.containsKey(clientKey)) {
                arrayMap3.put(api, map2.get(api));
            } else {
                if (!arrayMap2.containsKey(clientKey)) {
                    throw new IllegalStateException("Each API in the isOptionalMap must have a corresponding client in the clients map.");
                }
                arrayMap4.put(api, map2.get(api));
            }
        }
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            zap zapVar = arrayList.get(i);
            i++;
            zap zapVar2 = zapVar;
            if (arrayMap3.containsKey(zapVar2.mApi)) {
                arrayList2.add(zapVar2);
            } else {
                if (!arrayMap4.containsKey(zapVar2.mApi)) {
                    throw new IllegalStateException("Each ClientCallbacks must have a corresponding API in the isOptionalMap");
                }
                arrayList3.add(zapVar2);
            }
        }
        return new zaq(context, zaawVar, lock, looper, googleApiAvailabilityLight, arrayMap, arrayMap2, clientSettings, abstractClientBuilder, client, arrayList2, arrayList3, arrayMap3, arrayMap4);
    }

    public static boolean zab(ConnectionResult connectionResult) {
        return connectionResult != null && connectionResult.isSuccess();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zav() {
        ConnectionResult connectionResult;
        if (!zab(this.zaeo)) {
            if (this.zaeo != null && zab(this.zaep)) {
                this.zaej.disconnect();
                zaa(this.zaeo);
                return;
            }
            ConnectionResult connectionResult2 = this.zaeo;
            if (connectionResult2 == null || (connectionResult = this.zaep) == null) {
                return;
            }
            if (this.zaej.zahw < this.zaei.zahw) {
                connectionResult2 = connectionResult;
            }
            zaa(connectionResult2);
            return;
        }
        if (zab(this.zaep) || zax()) {
            int i = this.zaes;
            if (i == 1) {
                zaw();
            } else if (i != 2) {
                Log.wtf("CompositeGAC", "Attempted to call success callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new AssertionError());
            } else {
                this.zaeh.zab(this.zaen);
                zaw();
            }
            this.zaes = 0;
            return;
        }
        ConnectionResult connectionResult3 = this.zaep;
        if (connectionResult3 != null) {
            if (this.zaes == 1) {
                zaw();
            } else {
                zaa(connectionResult3);
                this.zaei.disconnect();
            }
        }
    }

    private final void zaw() {
        Iterator<SignInConnectionListener> it = this.zael.iterator();
        while (it.hasNext()) {
            it.next().onComplete();
        }
        this.zael.clear();
    }

    private final boolean zax() {
        ConnectionResult connectionResult = this.zaep;
        return connectionResult != null && connectionResult.getErrorCode() == 4;
    }

    @Nullable
    private final PendingIntent zay() {
        if (this.zaem == null) {
            return null;
        }
        return PendingIntent.getActivity(this.mContext, System.identityHashCode(this.zaeh), this.zaem.getSignInIntent(), 134217728);
    }

    @Override // com.google.android.gms.common.api.internal.zabr
    public final ConnectionResult blockingConnect() {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.common.api.internal.zabr
    public final void connect() {
        this.zaes = 2;
        this.zaeq = false;
        this.zaep = null;
        this.zaeo = null;
        this.zaei.connect();
        this.zaej.connect();
    }

    @Override // com.google.android.gms.common.api.internal.zabr
    public final void disconnect() {
        this.zaep = null;
        this.zaeo = null;
        this.zaes = 0;
        this.zaei.disconnect();
        this.zaej.disconnect();
        zaw();
    }

    @Override // com.google.android.gms.common.api.internal.zabr
    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.append((CharSequence) str).append("authClient").println(Utils.APP_ID_IDENTIFICATION_SUBSTRING);
        this.zaej.dump(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
        printWriter.append((CharSequence) str).append("anonClient").println(Utils.APP_ID_IDENTIFICATION_SUBSTRING);
        this.zaei.dump(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
    }

    @Override // com.google.android.gms.common.api.internal.zabr
    public final <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(@NonNull T t) {
        if (!zaa((BaseImplementation.ApiMethodImpl<? extends Result, ? extends Api.AnyClient>) t)) {
            return (T) this.zaei.enqueue(t);
        }
        if (!zax()) {
            return (T) this.zaej.enqueue(t);
        }
        t.setFailedResult(new Status(1, 4, null, zay()));
        return t;
    }

    @Override // com.google.android.gms.common.api.internal.zabr
    public final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(@NonNull T t) {
        if (!zaa((BaseImplementation.ApiMethodImpl<? extends Result, ? extends Api.AnyClient>) t)) {
            return (T) this.zaei.execute(t);
        }
        if (!zax()) {
            return (T) this.zaej.execute(t);
        }
        t.setFailedResult(new Status(1, 4, null, zay()));
        return t;
    }

    @Override // com.google.android.gms.common.api.internal.zabr
    @Nullable
    public final ConnectionResult getConnectionResult(@NonNull Api<?> api) {
        if (this.zaek.get(api.getClientKey()).equals(this.zaej)) {
            return zax() ? new ConnectionResult(4, zay()) : this.zaej.getConnectionResult(api);
        }
        return this.zaei.getConnectionResult(api);
    }

    @Override // com.google.android.gms.common.api.internal.zabr
    public final boolean isConnected() {
        this.zaer.lock();
        try {
            boolean z = true;
            if (!this.zaei.isConnected() || (!this.zaej.isConnected() && !zax() && this.zaes != 1)) {
                z = false;
            }
            return z;
        } finally {
            this.zaer.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabr
    public final boolean isConnecting() {
        this.zaer.lock();
        try {
            return this.zaes == 2;
        } finally {
            this.zaer.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabr
    public final boolean maybeSignIn(SignInConnectionListener signInConnectionListener) {
        this.zaer.lock();
        try {
            if ((!isConnecting() && !isConnected()) || this.zaej.isConnected()) {
                return false;
            }
            this.zael.add(signInConnectionListener);
            if (this.zaes == 0) {
                this.zaes = 1;
            }
            this.zaep = null;
            this.zaej.connect();
            return true;
        } finally {
            this.zaer.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabr
    public final void maybeSignOut() {
        this.zaer.lock();
        try {
            boolean zIsConnecting = isConnecting();
            this.zaej.disconnect();
            this.zaep = new ConnectionResult(4);
            if (zIsConnecting) {
                new com.google.android.gms.internal.base.zar(this.zabl).post(new zat(this));
            } else {
                zaw();
            }
        } finally {
            this.zaer.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabr
    public final void zau() {
        this.zaei.zau();
        this.zaej.zau();
    }

    @Override // com.google.android.gms.common.api.internal.zabr
    public final ConnectionResult blockingConnect(long j, @NonNull TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    private final void zaa(ConnectionResult connectionResult) {
        int i = this.zaes;
        if (i == 1) {
            zaw();
        } else if (i != 2) {
            Log.wtf("CompositeGAC", "Attempted to call failure callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new Exception());
        } else {
            this.zaeh.zac(connectionResult);
            zaw();
        }
        this.zaes = 0;
    }

    private final void zaa(int i, boolean z) {
        this.zaeh.zab(i, z);
        this.zaep = null;
        this.zaeo = null;
    }

    public static /* synthetic */ void zaa(zaq zaqVar, int i, boolean z) {
        zaqVar.zaeh.zab(i, z);
        zaqVar.zaep = null;
        zaqVar.zaeo = null;
    }

    private final boolean zaa(BaseImplementation.ApiMethodImpl<? extends Result, ? extends Api.AnyClient> apiMethodImpl) {
        Object clientKey = apiMethodImpl.getClientKey();
        Preconditions.checkArgument(this.zaek.containsKey(clientKey), "GoogleApiClient is not configured to use the API required for this call.");
        return this.zaek.get(clientKey).equals(this.zaej);
    }

    private final void zaa(Bundle bundle) {
        Bundle bundle2 = this.zaen;
        if (bundle2 == null) {
            this.zaen = bundle;
        } else if (bundle != null) {
            bundle2.putAll(bundle);
        }
    }

    public static /* synthetic */ void zaa(zaq zaqVar, Bundle bundle) {
        Bundle bundle2 = zaqVar.zaen;
        if (bundle2 == null) {
            zaqVar.zaen = bundle;
        } else if (bundle != null) {
            bundle2.putAll(bundle);
        }
    }
}
