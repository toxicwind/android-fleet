package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import b.a.a.a.a;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClientEventManager;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.service.Common;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.signin.SignInOptions;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;

/* JADX INFO: loaded from: classes.dex */
public final class zaaw extends GoogleApiClient implements zabs {
    public final Context mContext;
    public final Looper zabl;
    public final int zacc;
    public final GoogleApiAvailability zace;
    public final Api.AbstractClientBuilder<? extends com.google.android.gms.signin.zac, SignInOptions> zacf;
    public final Lock zaer;
    public final Map<Api<?>, Boolean> zaew;
    public final ClientSettings zafa;
    public final GmsClientEventManager zagw;
    public volatile boolean zagy;
    public final zaaz zahb;

    @VisibleForTesting
    public zabq zahc;
    public final Map<Api.AnyClientKey<?>, Api.Client> zahd;
    public final ArrayList<zap> zahg;
    public Integer zahh;
    public final zacp zahj;
    public zabr zagx = null;

    @VisibleForTesting
    public final Queue<BaseImplementation.ApiMethodImpl<?, ?>> zafd = new LinkedList();
    public long zagz = 120000;
    public long zaha = 5000;
    public Set<Scope> zahe = new HashSet();
    public final ListenerHolders zahf = new ListenerHolders();
    public Set<zack> zahi = null;
    public final GmsClientEventManager.GmsClientEventState zahk = new zaav(this);
    public boolean zaci = false;

    public zaaw(Context context, Lock lock, Looper looper, ClientSettings clientSettings, GoogleApiAvailability googleApiAvailability, Api.AbstractClientBuilder<? extends com.google.android.gms.signin.zac, SignInOptions> abstractClientBuilder, Map<Api<?>, Boolean> map, List<GoogleApiClient.ConnectionCallbacks> list, List<GoogleApiClient.OnConnectionFailedListener> list2, Map<Api.AnyClientKey<?>, Api.Client> map2, int i, int i2, ArrayList<zap> arrayList, boolean z) {
        this.zahh = null;
        this.mContext = context;
        this.zaer = lock;
        this.zagw = new GmsClientEventManager(looper, this.zahk);
        this.zabl = looper;
        this.zahb = new zaaz(this, looper);
        this.zace = googleApiAvailability;
        this.zacc = i;
        if (this.zacc >= 0) {
            this.zahh = Integer.valueOf(i2);
        }
        this.zaew = map;
        this.zahd = map2;
        this.zahg = arrayList;
        this.zahj = new zacp(this.zahd);
        Iterator<GoogleApiClient.ConnectionCallbacks> it = list.iterator();
        while (it.hasNext()) {
            this.zagw.registerConnectionCallbacks(it.next());
        }
        Iterator<GoogleApiClient.OnConnectionFailedListener> it2 = list2.iterator();
        while (it2.hasNext()) {
            this.zagw.registerConnectionFailedListener(it2.next());
        }
        this.zafa = clientSettings;
        this.zacf = abstractClientBuilder;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resume() {
        this.zaer.lock();
        try {
            if (this.zagy) {
                zaas();
            }
        } finally {
            this.zaer.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zaa(GoogleApiClient googleApiClient, StatusPendingResult statusPendingResult, boolean z) {
        Common.zapw.zaa(googleApiClient).setResultCallback(new zaba(this, statusPendingResult, z, googleApiClient));
    }

    private final void zaas() {
        this.zagw.enableCallbacks();
        this.zagx.connect();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zaat() {
        this.zaer.lock();
        try {
            if (zaau()) {
                zaas();
            }
        } finally {
            this.zaer.unlock();
        }
    }

    private final void zae(int i) {
        Integer num = this.zahh;
        if (num == null) {
            this.zahh = Integer.valueOf(i);
        } else if (num.intValue() != i) {
            String strZaf = zaf(i);
            String strZaf2 = zaf(this.zahh.intValue());
            throw new IllegalStateException(a.b(strZaf2.length() + strZaf.length() + 51, "Cannot use sign-in mode: ", strZaf, ". Mode was already set to ", strZaf2));
        }
        if (this.zagx != null) {
            return;
        }
        boolean z = false;
        boolean z2 = false;
        for (Api.Client client : this.zahd.values()) {
            if (client.requiresSignIn()) {
                z = true;
            }
            if (client.providesSignIn()) {
                z2 = true;
            }
        }
        int iIntValue = this.zahh.intValue();
        if (iIntValue == 1) {
            if (!z) {
                throw new IllegalStateException("SIGN_IN_MODE_REQUIRED cannot be used on a GoogleApiClient that does not contain any authenticated APIs. Use connect() instead.");
            }
            if (z2) {
                throw new IllegalStateException("Cannot use SIGN_IN_MODE_REQUIRED with GOOGLE_SIGN_IN_API. Use connect(SIGN_IN_MODE_OPTIONAL) instead.");
            }
        } else if (iIntValue == 2 && z) {
            if (this.zaci) {
                this.zagx = new zav(this.mContext, this.zaer, this.zabl, this.zace, this.zahd, this.zafa, this.zaew, this.zacf, this.zahg, this, true);
                return;
            } else {
                this.zagx = zaq.zaa(this.mContext, this, this.zaer, this.zabl, this.zace, this.zahd, this.zafa, this.zaew, this.zacf, this.zahg);
                return;
            }
        }
        if (!this.zaci || z2) {
            this.zagx = new zabe(this.mContext, this, this.zaer, this.zabl, this.zace, this.zahd, this.zafa, this.zaew, this.zacf, this.zahg, this);
        } else {
            this.zagx = new zav(this.mContext, this.zaer, this.zabl, this.zace, this.zahd, this.zafa, this.zaew, this.zacf, this.zahg, this, false);
        }
    }

    public static String zaf(int i) {
        if (i == 1) {
            return "SIGN_IN_MODE_REQUIRED";
        }
        if (i != 2) {
            return i != 3 ? "UNKNOWN" : "SIGN_IN_MODE_NONE";
        }
        return "SIGN_IN_MODE_OPTIONAL";
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final ConnectionResult blockingConnect() {
        boolean z = true;
        Preconditions.checkState(Looper.myLooper() != Looper.getMainLooper(), "blockingConnect must not be called on the UI thread");
        this.zaer.lock();
        try {
            if (this.zacc >= 0) {
                if (this.zahh == null) {
                    z = false;
                }
                Preconditions.checkState(z, "Sign-in mode should have been set explicitly by auto-manage.");
            } else if (this.zahh == null) {
                this.zahh = Integer.valueOf(zaa(this.zahd.values(), false));
            } else if (this.zahh.intValue() == 2) {
                throw new IllegalStateException("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
            }
            zae(this.zahh.intValue());
            this.zagw.enableCallbacks();
            ConnectionResult connectionResultBlockingConnect = this.zagx.blockingConnect();
            this.zaer.unlock();
            return connectionResultBlockingConnect;
        } catch (Throwable th) {
            this.zaer.unlock();
            throw th;
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final PendingResult<Status> clearDefaultAccountAndReconnect() {
        Preconditions.checkState(isConnected(), "GoogleApiClient is not connected yet.");
        Preconditions.checkState(this.zahh.intValue() != 2, "Cannot use clearDefaultAccountAndReconnect with GOOGLE_SIGN_IN_API");
        StatusPendingResult statusPendingResult = new StatusPendingResult(this);
        if (this.zahd.containsKey(Common.CLIENT_KEY)) {
            zaa(this, statusPendingResult, false);
        } else {
            AtomicReference atomicReference = new AtomicReference();
            GoogleApiClient googleApiClientBuild = new GoogleApiClient.Builder(this.mContext).addApi(Common.API).addConnectionCallbacks(new zaay(this, atomicReference, statusPendingResult)).addOnConnectionFailedListener(new zaax(this, statusPendingResult)).setHandler(this.zahb).build();
            atomicReference.set(googleApiClientBuild);
            googleApiClientBuild.connect();
        }
        return statusPendingResult;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void connect() {
        this.zaer.lock();
        try {
            if (this.zacc >= 0) {
                Preconditions.checkState(this.zahh != null, "Sign-in mode should have been set explicitly by auto-manage.");
            } else if (this.zahh == null) {
                this.zahh = Integer.valueOf(zaa(this.zahd.values(), false));
            } else if (this.zahh.intValue() == 2) {
                throw new IllegalStateException("Cannot call connect() when SignInMode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
            }
            connect(this.zahh.intValue());
            this.zaer.unlock();
        } catch (Throwable th) {
            this.zaer.unlock();
            throw th;
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void disconnect() {
        this.zaer.lock();
        try {
            this.zahj.release();
            if (this.zagx != null) {
                this.zagx.disconnect();
            }
            this.zahf.release();
            for (BaseImplementation.ApiMethodImpl<?, ?> apiMethodImpl : this.zafd) {
                apiMethodImpl.zaa((zacq) null);
                apiMethodImpl.cancel();
            }
            this.zafd.clear();
            if (this.zagx == null) {
                return;
            }
            zaau();
            this.zagw.disableCallbacks();
        } finally {
            this.zaer.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.append((CharSequence) str).append("mContext=").println(this.mContext);
        printWriter.append((CharSequence) str).append("mResuming=").print(this.zagy);
        printWriter.append(" mWorkQueue.size()=").print(this.zafd.size());
        printWriter.append(" mUnconsumedApiCalls.size()=").println(this.zahj.zald.size());
        zabr zabrVar = this.zagx;
        if (zabrVar != null) {
            zabrVar.dump(str, fileDescriptor, printWriter, strArr);
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(@NonNull T t) {
        Preconditions.checkArgument(t.getClientKey() != null, "This task can not be enqueued (it's probably a Batch or malformed)");
        boolean zContainsKey = this.zahd.containsKey(t.getClientKey());
        String name = t.getApi() != null ? t.getApi().getName() : "the API";
        StringBuilder sb = new StringBuilder(a.a((Object) name, 65));
        sb.append("GoogleApiClient is not configured to use ");
        sb.append(name);
        sb.append(" required for this call.");
        Preconditions.checkArgument(zContainsKey, sb.toString());
        this.zaer.lock();
        try {
            if (this.zagx != null) {
                return (T) this.zagx.enqueue(t);
            }
            this.zafd.add(t);
            return t;
        } finally {
            this.zaer.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(@NonNull T t) {
        Preconditions.checkArgument(t.getClientKey() != null, "This task can not be executed (it's probably a Batch or malformed)");
        boolean zContainsKey = this.zahd.containsKey(t.getClientKey());
        String name = t.getApi() != null ? t.getApi().getName() : "the API";
        StringBuilder sb = new StringBuilder(a.a((Object) name, 65));
        sb.append("GoogleApiClient is not configured to use ");
        sb.append(name);
        sb.append(" required for this call.");
        Preconditions.checkArgument(zContainsKey, sb.toString());
        this.zaer.lock();
        try {
            if (this.zagx == null) {
                throw new IllegalStateException("GoogleApiClient is not connected yet.");
            }
            if (!this.zagy) {
                T t2 = (T) this.zagx.execute(t);
                this.zaer.unlock();
                return t2;
            }
            this.zafd.add(t);
            while (!this.zafd.isEmpty()) {
                BaseImplementation.ApiMethodImpl<?, ?> apiMethodImplRemove = this.zafd.remove();
                this.zahj.zac(apiMethodImplRemove);
                apiMethodImplRemove.setFailedResult(Status.RESULT_INTERNAL_ERROR);
            }
            this.zaer.unlock();
            return t;
        } catch (Throwable th) {
            this.zaer.unlock();
            throw th;
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    @NonNull
    public final <C extends Api.Client> C getClient(@NonNull Api.AnyClientKey<C> anyClientKey) {
        C c2 = (C) this.zahd.get(anyClientKey);
        Preconditions.checkNotNull(c2, "Appropriate Api was not requested.");
        return c2;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    @NonNull
    public final ConnectionResult getConnectionResult(@NonNull Api<?> api) {
        this.zaer.lock();
        try {
            if (!isConnected() && !this.zagy) {
                throw new IllegalStateException("Cannot invoke getConnectionResult unless GoogleApiClient is connected");
            }
            if (!this.zahd.containsKey(api.getClientKey())) {
                throw new IllegalArgumentException(String.valueOf(api.getName()).concat(" was never registered with GoogleApiClient"));
            }
            ConnectionResult connectionResult = this.zagx.getConnectionResult(api);
            if (connectionResult != null) {
                this.zaer.unlock();
                return connectionResult;
            }
            if (this.zagy) {
                ConnectionResult connectionResult2 = ConnectionResult.RESULT_SUCCESS;
                this.zaer.unlock();
                return connectionResult2;
            }
            Log.w("GoogleApiClientImpl", zaaw());
            Log.wtf("GoogleApiClientImpl", String.valueOf(api.getName()).concat(" requested in getConnectionResult is not connected but is not present in the failed  connections map"), new Exception());
            ConnectionResult connectionResult3 = new ConnectionResult(8, null);
            this.zaer.unlock();
            return connectionResult3;
        } catch (Throwable th) {
            this.zaer.unlock();
            throw th;
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final Context getContext() {
        return this.mContext;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final Looper getLooper() {
        return this.zabl;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final boolean hasApi(@NonNull Api<?> api) {
        return this.zahd.containsKey(api.getClientKey());
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final boolean hasConnectedApi(@NonNull Api<?> api) {
        Api.Client client;
        return isConnected() && (client = this.zahd.get(api.getClientKey())) != null && client.isConnected();
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final boolean isConnected() {
        zabr zabrVar = this.zagx;
        return zabrVar != null && zabrVar.isConnected();
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final boolean isConnecting() {
        zabr zabrVar = this.zagx;
        return zabrVar != null && zabrVar.isConnecting();
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final boolean isConnectionCallbacksRegistered(@NonNull GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        return this.zagw.isConnectionCallbacksRegistered(connectionCallbacks);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final boolean isConnectionFailedListenerRegistered(@NonNull GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return this.zagw.isConnectionFailedListenerRegistered(onConnectionFailedListener);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final boolean maybeSignIn(SignInConnectionListener signInConnectionListener) {
        zabr zabrVar = this.zagx;
        return zabrVar != null && zabrVar.maybeSignIn(signInConnectionListener);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void maybeSignOut() {
        zabr zabrVar = this.zagx;
        if (zabrVar != null) {
            zabrVar.maybeSignOut();
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void reconnect() {
        disconnect();
        connect();
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void registerConnectionCallbacks(@NonNull GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        this.zagw.registerConnectionCallbacks(connectionCallbacks);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void registerConnectionFailedListener(@NonNull GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this.zagw.registerConnectionFailedListener(onConnectionFailedListener);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final <L> ListenerHolder<L> registerListener(@NonNull L l) {
        this.zaer.lock();
        try {
            return this.zahf.zaa(l, this.zabl, "NO_TYPE");
        } finally {
            this.zaer.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void stopAutoManage(@NonNull FragmentActivity fragmentActivity) {
        LifecycleActivity lifecycleActivity = new LifecycleActivity((Activity) fragmentActivity);
        if (this.zacc < 0) {
            throw new IllegalStateException("Called stopAutoManage but automatic lifecycle management is not enabled.");
        }
        zai.zaa(lifecycleActivity).zaa(this.zacc);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void unregisterConnectionCallbacks(@NonNull GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        this.zagw.unregisterConnectionCallbacks(connectionCallbacks);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void unregisterConnectionFailedListener(@NonNull GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this.zagw.unregisterConnectionFailedListener(onConnectionFailedListener);
    }

    public final boolean zaau() {
        if (!this.zagy) {
            return false;
        }
        this.zagy = false;
        this.zahb.removeMessages(2);
        this.zahb.removeMessages(1);
        zabq zabqVar = this.zahc;
        if (zabqVar != null) {
            zabqVar.unregister();
            this.zahc = null;
        }
        return true;
    }

    public final boolean zaav() {
        Lock lock;
        this.zaer.lock();
        try {
            if (this.zahi == null) {
                return false;
            }
            return !this.zahi.isEmpty();
        } finally {
            this.zaer.unlock();
        }
    }

    public final String zaaw() {
        StringWriter stringWriter = new StringWriter();
        dump("", null, new PrintWriter(stringWriter), null);
        return stringWriter.toString();
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    public final void zab(Bundle bundle) {
        while (!this.zafd.isEmpty()) {
            execute(this.zafd.remove());
        }
        this.zagw.onConnectionSuccess(bundle);
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    public final void zac(ConnectionResult connectionResult) {
        if (!this.zace.isPlayServicesPossiblyUpdating(this.mContext, connectionResult.getErrorCode())) {
            zaau();
        }
        if (this.zagy) {
            return;
        }
        this.zagw.onConnectionFailure(connectionResult);
        this.zagw.disableCallbacks();
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void zaa(zack zackVar) {
        this.zaer.lock();
        try {
            if (this.zahi == null) {
                this.zahi = new HashSet();
            }
            this.zahi.add(zackVar);
        } finally {
            this.zaer.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    public final void zab(int i, boolean z) {
        if (i == 1 && !z && !this.zagy) {
            this.zagy = true;
            if (this.zahc == null) {
                try {
                    this.zahc = this.zace.zaa(this.mContext.getApplicationContext(), new zabc(this));
                } catch (SecurityException unused) {
                }
            }
            zaaz zaazVar = this.zahb;
            zaazVar.sendMessageDelayed(zaazVar.obtainMessage(1), this.zagz);
            zaaz zaazVar2 = this.zahb;
            zaazVar2.sendMessageDelayed(zaazVar2.obtainMessage(2), this.zaha);
        }
        this.zahj.zabv();
        this.zagw.onUnintentionalDisconnection(i);
        this.zagw.disableCallbacks();
        if (i == 2) {
            zaas();
        }
    }

    public static int zaa(Iterable<Api.Client> iterable, boolean z) {
        boolean z2 = false;
        boolean z3 = false;
        for (Api.Client client : iterable) {
            if (client.requiresSignIn()) {
                z2 = true;
            }
            if (client.providesSignIn()) {
                z3 = true;
            }
        }
        if (z2) {
            return (z3 && z) ? 2 : 1;
        }
        return 3;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void connect(int i) {
        this.zaer.lock();
        boolean z = true;
        if (i != 3 && i != 1 && i != 2) {
            z = false;
        }
        try {
            StringBuilder sb = new StringBuilder(33);
            sb.append("Illegal sign-in mode: ");
            sb.append(i);
            Preconditions.checkArgument(z, sb.toString());
            zae(i);
            zaas();
        } finally {
            this.zaer.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final ConnectionResult blockingConnect(long j, @NonNull TimeUnit timeUnit) {
        Preconditions.checkState(Looper.myLooper() != Looper.getMainLooper(), "blockingConnect must not be called on the UI thread");
        Preconditions.checkNotNull(timeUnit, "TimeUnit must not be null");
        this.zaer.lock();
        try {
            if (this.zahh == null) {
                this.zahh = Integer.valueOf(zaa(this.zahd.values(), false));
            } else if (this.zahh.intValue() == 2) {
                throw new IllegalStateException("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
            }
            zae(this.zahh.intValue());
            this.zagw.enableCallbacks();
            ConnectionResult connectionResultBlockingConnect = this.zagx.blockingConnect(j, timeUnit);
            this.zaer.unlock();
            return connectionResultBlockingConnect;
        } catch (Throwable th) {
            this.zaer.unlock();
            throw th;
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void zab(zack zackVar) {
        this.zaer.lock();
        try {
            if (this.zahi == null) {
                Log.wtf("GoogleApiClientImpl", "Attempted to remove pending transform when no transforms are registered.", new Exception());
            } else if (!this.zahi.remove(zackVar)) {
                Log.wtf("GoogleApiClientImpl", "Failed to remove pending transform - this may lead to memory leaks!", new Exception());
            } else if (!zaav()) {
                this.zagx.zau();
            }
        } finally {
            this.zaer.unlock();
        }
    }
}
