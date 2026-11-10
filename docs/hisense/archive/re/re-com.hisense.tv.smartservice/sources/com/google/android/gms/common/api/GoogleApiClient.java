package com.google.android.gms.common.api;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import androidx.fragment.app.FragmentActivity;
import b.a.a.a.a;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.api.internal.LifecycleActivity;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.SignInConnectionListener;
import com.google.android.gms.common.api.internal.zaaw;
import com.google.android.gms.common.api.internal.zack;
import com.google.android.gms.common.api.internal.zai;
import com.google.android.gms.common.api.internal.zap;
import com.google.android.gms.common.internal.AccountType;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.signin.SignInOptions;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes.dex */
@KeepForSdk
@Deprecated
public abstract class GoogleApiClient {

    @KeepForSdk
    public static final String DEFAULT_ACCOUNT = "<<default account>>";
    public static final int SIGN_IN_MODE_OPTIONAL = 2;
    public static final int SIGN_IN_MODE_REQUIRED = 1;
    public static final Set<GoogleApiClient> zacj = Collections.newSetFromMap(new WeakHashMap());

    @Deprecated
    public interface ConnectionCallbacks extends com.google.android.gms.common.api.internal.ConnectionCallbacks {
        public static final int CAUSE_NETWORK_LOST = 2;
        public static final int CAUSE_SERVICE_DISCONNECTED = 1;
    }

    @Deprecated
    public interface OnConnectionFailedListener extends com.google.android.gms.common.api.internal.OnConnectionFailedListener {
    }

    public static void dumpAll(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        synchronized (zacj) {
            int i = 0;
            String strConcat = String.valueOf(str).concat("  ");
            for (GoogleApiClient googleApiClient : zacj) {
                printWriter.append((CharSequence) str).append("GoogleApiClient#").println(i);
                googleApiClient.dump(strConcat, fileDescriptor, printWriter, strArr);
                i++;
            }
        }
    }

    @KeepForSdk
    public static Set<GoogleApiClient> getAllClients() {
        Set<GoogleApiClient> set;
        synchronized (zacj) {
            set = zacj;
        }
        return set;
    }

    public abstract ConnectionResult blockingConnect();

    public abstract ConnectionResult blockingConnect(long j, @NonNull TimeUnit timeUnit);

    public abstract PendingResult<Status> clearDefaultAccountAndReconnect();

    public abstract void connect();

    public void connect(int i) {
        throw new UnsupportedOperationException();
    }

    public abstract void disconnect();

    public abstract void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    @KeepForSdk
    public <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(@NonNull T t) {
        throw new UnsupportedOperationException();
    }

    @KeepForSdk
    public <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(@NonNull T t) {
        throw new UnsupportedOperationException();
    }

    @NonNull
    @KeepForSdk
    public <C extends Api.Client> C getClient(@NonNull Api.AnyClientKey<C> anyClientKey) {
        throw new UnsupportedOperationException();
    }

    @NonNull
    public abstract ConnectionResult getConnectionResult(@NonNull Api<?> api);

    @KeepForSdk
    public Context getContext() {
        throw new UnsupportedOperationException();
    }

    @KeepForSdk
    public Looper getLooper() {
        throw new UnsupportedOperationException();
    }

    @KeepForSdk
    public boolean hasApi(@NonNull Api<?> api) {
        throw new UnsupportedOperationException();
    }

    public abstract boolean hasConnectedApi(@NonNull Api<?> api);

    public abstract boolean isConnected();

    public abstract boolean isConnecting();

    public abstract boolean isConnectionCallbacksRegistered(@NonNull ConnectionCallbacks connectionCallbacks);

    public abstract boolean isConnectionFailedListenerRegistered(@NonNull OnConnectionFailedListener onConnectionFailedListener);

    @KeepForSdk
    public boolean maybeSignIn(SignInConnectionListener signInConnectionListener) {
        throw new UnsupportedOperationException();
    }

    @KeepForSdk
    public void maybeSignOut() {
        throw new UnsupportedOperationException();
    }

    public abstract void reconnect();

    public abstract void registerConnectionCallbacks(@NonNull ConnectionCallbacks connectionCallbacks);

    public abstract void registerConnectionFailedListener(@NonNull OnConnectionFailedListener onConnectionFailedListener);

    @KeepForSdk
    public <L> ListenerHolder<L> registerListener(@NonNull L l) {
        throw new UnsupportedOperationException();
    }

    public abstract void stopAutoManage(@NonNull FragmentActivity fragmentActivity);

    public abstract void unregisterConnectionCallbacks(@NonNull ConnectionCallbacks connectionCallbacks);

    public abstract void unregisterConnectionFailedListener(@NonNull OnConnectionFailedListener onConnectionFailedListener);

    public void zaa(zack zackVar) {
        throw new UnsupportedOperationException();
    }

    public void zab(zack zackVar) {
        throw new UnsupportedOperationException();
    }

    @KeepForSdk
    @Deprecated
    public static final class Builder {
        public final Context mContext;
        public Looper zabl;
        public final Set<Scope> zabs;
        public final Set<Scope> zabt;
        public int zabu;
        public View zabv;
        public String zabw;
        public String zabx;
        public final Map<Api<?>, ClientSettings.OptionalApiSettings> zaby;
        public boolean zabz;
        public final Map<Api<?>, Api.ApiOptions> zaca;
        public LifecycleActivity zacb;
        public int zacc;
        public OnConnectionFailedListener zacd;
        public GoogleApiAvailability zace;
        public Api.AbstractClientBuilder<? extends com.google.android.gms.signin.zac, SignInOptions> zacf;
        public final ArrayList<ConnectionCallbacks> zacg;
        public final ArrayList<OnConnectionFailedListener> zach;
        public boolean zaci;
        public Account zax;

        @KeepForSdk
        public Builder(@NonNull Context context) {
            this.zabs = new HashSet();
            this.zabt = new HashSet();
            this.zaby = new ArrayMap();
            this.zabz = false;
            this.zaca = new ArrayMap();
            this.zacc = -1;
            this.zace = GoogleApiAvailability.zaao;
            this.zacf = com.google.android.gms.signin.zab.zapv;
            this.zacg = new ArrayList<>();
            this.zach = new ArrayList<>();
            this.zaci = false;
            this.mContext = context;
            this.zabl = context.getMainLooper();
            this.zabw = context.getPackageName();
            this.zabx = context.getClass().getName();
        }

        private final <O extends Api.ApiOptions> void zaa(Api<O> api, O o, Scope... scopeArr) {
            HashSet hashSet = new HashSet(api.zah().getImpliedScopes(o));
            for (Scope scope : scopeArr) {
                hashSet.add(scope);
            }
            this.zaby.put(api, new ClientSettings.OptionalApiSettings(hashSet));
        }

        public final Builder addApi(@NonNull Api<? extends Api.ApiOptions.NotRequiredOptions> api) {
            Preconditions.checkNotNull(api, "Api must not be null");
            this.zaca.put(api, null);
            List<Scope> impliedScopes = api.zah().getImpliedScopes(null);
            this.zabt.addAll(impliedScopes);
            this.zabs.addAll(impliedScopes);
            return this;
        }

        public final Builder addApiIfAvailable(@NonNull Api<? extends Api.ApiOptions.NotRequiredOptions> api, Scope... scopeArr) {
            Preconditions.checkNotNull(api, "Api must not be null");
            this.zaca.put(api, null);
            zaa(api, null, scopeArr);
            return this;
        }

        public final Builder addConnectionCallbacks(@NonNull ConnectionCallbacks connectionCallbacks) {
            Preconditions.checkNotNull(connectionCallbacks, "Listener must not be null");
            this.zacg.add(connectionCallbacks);
            return this;
        }

        public final Builder addOnConnectionFailedListener(@NonNull OnConnectionFailedListener onConnectionFailedListener) {
            Preconditions.checkNotNull(onConnectionFailedListener, "Listener must not be null");
            this.zach.add(onConnectionFailedListener);
            return this;
        }

        public final Builder addScope(@NonNull Scope scope) {
            Preconditions.checkNotNull(scope, "Scope must not be null");
            this.zabs.add(scope);
            return this;
        }

        @KeepForSdk
        public final Builder addScopeNames(String[] strArr) {
            for (String str : strArr) {
                this.zabs.add(new Scope(1, str));
            }
            return this;
        }

        public final GoogleApiClient build() {
            Preconditions.checkArgument(!this.zaca.isEmpty(), "must call addApi() to add at least one API");
            ClientSettings clientSettingsBuildClientSettings = buildClientSettings();
            Api<?> api = null;
            Map<Api<?>, ClientSettings.OptionalApiSettings> optionalApiSettings = clientSettingsBuildClientSettings.getOptionalApiSettings();
            ArrayMap arrayMap = new ArrayMap();
            ArrayMap arrayMap2 = new ArrayMap();
            ArrayList arrayList = new ArrayList();
            boolean z = false;
            for (Api<?> api2 : this.zaca.keySet()) {
                Api.ApiOptions apiOptions = this.zaca.get(api2);
                boolean z2 = optionalApiSettings.get(api2) != null;
                arrayMap.put(api2, Boolean.valueOf(z2));
                zap zapVar = new zap(api2, z2);
                arrayList.add(zapVar);
                Api.AbstractClientBuilder<?, O> abstractClientBuilderZai = api2.zai();
                Api.Client clientBuildClient = abstractClientBuilderZai.buildClient(this.mContext, this.zabl, clientSettingsBuildClientSettings, apiOptions, (ConnectionCallbacks) zapVar, (OnConnectionFailedListener) zapVar);
                arrayMap2.put(api2.getClientKey(), clientBuildClient);
                if (abstractClientBuilderZai.getPriority() == 1) {
                    z = apiOptions != null;
                }
                if (clientBuildClient.providesSignIn()) {
                    if (api != null) {
                        String name = api2.getName();
                        String name2 = api.getName();
                        throw new IllegalStateException(a.a(a.a((Object) name2, a.a((Object) name, 21)), name, " cannot be used with ", name2));
                    }
                    api = api2;
                }
            }
            if (api != null) {
                if (z) {
                    String name3 = api.getName();
                    throw new IllegalStateException(a.a(a.a((Object) name3, 82), "With using ", name3, ", GamesOptions can only be specified within GoogleSignInOptions.Builder"));
                }
                Preconditions.checkState(this.zax == null, "Must not set an account in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead", api.getName());
                Preconditions.checkState(this.zabs.equals(this.zabt), "Must not set scopes in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead.", api.getName());
            }
            zaaw zaawVar = new zaaw(this.mContext, new ReentrantLock(), this.zabl, clientSettingsBuildClientSettings, this.zace, this.zacf, arrayMap, this.zacg, this.zach, arrayMap2, this.zacc, zaaw.zaa(arrayMap2.values(), true), arrayList, false);
            synchronized (GoogleApiClient.zacj) {
                GoogleApiClient.zacj.add(zaawVar);
            }
            if (this.zacc >= 0) {
                zai.zaa(this.zacb).zaa(this.zacc, zaawVar, this.zacd);
            }
            return zaawVar;
        }

        @VisibleForTesting
        @KeepForSdk
        public final ClientSettings buildClientSettings() {
            SignInOptions signInOptions = SignInOptions.DEFAULT;
            if (this.zaca.containsKey(com.google.android.gms.signin.zab.API)) {
                signInOptions = (SignInOptions) this.zaca.get(com.google.android.gms.signin.zab.API);
            }
            return new ClientSettings(this.zax, this.zabs, this.zaby, this.zabu, this.zabv, this.zabw, this.zabx, signInOptions, false);
        }

        public final Builder enableAutoManage(@NonNull FragmentActivity fragmentActivity, int i, @Nullable OnConnectionFailedListener onConnectionFailedListener) {
            LifecycleActivity lifecycleActivity = new LifecycleActivity((Activity) fragmentActivity);
            Preconditions.checkArgument(i >= 0, "clientId must be non-negative");
            this.zacc = i;
            this.zacd = onConnectionFailedListener;
            this.zacb = lifecycleActivity;
            return this;
        }

        public final Builder setAccountName(String str) {
            this.zax = str == null ? null : new Account(str, AccountType.GOOGLE);
            return this;
        }

        public final Builder setGravityForPopups(int i) {
            this.zabu = i;
            return this;
        }

        public final Builder setHandler(@NonNull Handler handler) {
            Preconditions.checkNotNull(handler, "Handler must not be null");
            this.zabl = handler.getLooper();
            return this;
        }

        public final Builder setViewForPopups(@NonNull View view) {
            Preconditions.checkNotNull(view, "View must not be null");
            this.zabv = view;
            return this;
        }

        public final Builder useDefaultAccount() {
            return setAccountName("<<default account>>");
        }

        public final <O extends Api.ApiOptions.HasOptions> Builder addApiIfAvailable(@NonNull Api<O> api, @NonNull O o, Scope... scopeArr) {
            Preconditions.checkNotNull(api, "Api must not be null");
            Preconditions.checkNotNull(o, "Null options are not permitted for this Api");
            this.zaca.put(api, o);
            zaa(api, o, scopeArr);
            return this;
        }

        public final <O extends Api.ApiOptions.HasOptions> Builder addApi(@NonNull Api<O> api, @NonNull O o) {
            Preconditions.checkNotNull(api, "Api must not be null");
            Preconditions.checkNotNull(o, "Null options are not permitted for this Api");
            this.zaca.put(api, o);
            List<Scope> impliedScopes = api.zah().getImpliedScopes(o);
            this.zabt.addAll(impliedScopes);
            this.zabs.addAll(impliedScopes);
            return this;
        }

        public final Builder enableAutoManage(@NonNull FragmentActivity fragmentActivity, @Nullable OnConnectionFailedListener onConnectionFailedListener) {
            return enableAutoManage(fragmentActivity, 0, onConnectionFailedListener);
        }

        @KeepForSdk
        public Builder(@NonNull Context context, @NonNull ConnectionCallbacks connectionCallbacks, @NonNull OnConnectionFailedListener onConnectionFailedListener) {
            this(context);
            Preconditions.checkNotNull(connectionCallbacks, "Must provide a connected listener");
            this.zacg.add(connectionCallbacks);
            Preconditions.checkNotNull(onConnectionFailedListener, "Must provide a connection failed listener");
            this.zach.add(onConnectionFailedListener);
        }
    }
}
