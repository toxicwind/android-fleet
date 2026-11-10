package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Handler;
import android.os.IInterface;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
@KeepForSdk
public abstract class GmsClient<T extends IInterface> extends BaseGmsClient<T> implements Api.Client, GmsClientEventManager.GmsClientEventState {
    public final Set<Scope> mScopes;
    public final ClientSettings zafa;
    public final Account zax;

    @VisibleForTesting
    @KeepForSdk
    public GmsClient(Context context, Handler handler, int i, ClientSettings clientSettings) {
        this(context, handler, GmsClientSupervisor.getInstance(context), GoogleApiAvailability.zaao, i, clientSettings, (ConnectionCallbacks) null, (OnConnectionFailedListener) null);
    }

    private final Set<Scope> zaa(@NonNull Set<Scope> set) {
        Set<Scope> setValidateScopes = validateScopes(set);
        Iterator<Scope> it = setValidateScopes.iterator();
        while (it.hasNext()) {
            if (!set.contains(it.next())) {
                throw new IllegalStateException("Expanding scopes is not permitted, use implied scopes instead");
            }
        }
        return setValidateScopes;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final Account getAccount() {
        return this.zax;
    }

    @KeepForSdk
    public final ClientSettings getClientSettings() {
        return this.zafa;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public int getMinApkVersion() {
        return GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    }

    @Override // com.google.android.gms.common.api.Api.Client
    @KeepForSdk
    public Feature[] getRequiredFeatures() {
        return new Feature[0];
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    @KeepForSdk
    public final Set<Scope> getScopes() {
        return this.mScopes;
    }

    @Override // com.google.android.gms.common.api.Api.Client
    @NonNull
    @KeepForSdk
    public Set<Scope> getScopesForConnectionlessNonSignIn() {
        return requiresSignIn() ? this.mScopes : Collections.emptySet();
    }

    @NonNull
    @KeepForSdk
    public Set<Scope> validateScopes(@NonNull Set<Scope> set) {
        return set;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    @KeepForSdk
    public GmsClient(Context context, Looper looper, int i, ClientSettings clientSettings, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        GmsClientSupervisor gmsClientSupervisor = GmsClientSupervisor.getInstance(context);
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.zaao;
        Preconditions.checkNotNull(connectionCallbacks);
        Preconditions.checkNotNull(onConnectionFailedListener);
        this(context, looper, gmsClientSupervisor, googleApiAvailability, i, clientSettings, connectionCallbacks, onConnectionFailedListener);
    }

    @Nullable
    public static BaseGmsClient.BaseConnectionCallbacks zaa(ConnectionCallbacks connectionCallbacks) {
        if (connectionCallbacks == null) {
            return null;
        }
        return new zaf(connectionCallbacks);
    }

    @Nullable
    public static BaseGmsClient.BaseOnConnectionFailedListener zaa(OnConnectionFailedListener onConnectionFailedListener) {
        if (onConnectionFailedListener == null) {
            return null;
        }
        return new zag(onConnectionFailedListener);
    }

    @KeepForSdk
    @Deprecated
    public GmsClient(Context context, Looper looper, int i, ClientSettings clientSettings, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this(context, looper, i, clientSettings, (ConnectionCallbacks) connectionCallbacks, (OnConnectionFailedListener) onConnectionFailedListener);
    }

    @KeepForSdk
    public GmsClient(Context context, Looper looper, int i, ClientSettings clientSettings) {
        this(context, looper, GmsClientSupervisor.getInstance(context), GoogleApiAvailability.zaao, i, clientSettings, (ConnectionCallbacks) null, (OnConnectionFailedListener) null);
    }

    @VisibleForTesting
    public GmsClient(Context context, Looper looper, GmsClientSupervisor gmsClientSupervisor, GoogleApiAvailability googleApiAvailability, int i, ClientSettings clientSettings, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, gmsClientSupervisor, googleApiAvailability, i, zaa(connectionCallbacks), zaa(onConnectionFailedListener), clientSettings.getRealClientClassName());
        this.zafa = clientSettings;
        this.zax = clientSettings.getAccount();
        this.mScopes = zaa(clientSettings.getAllRequestedScopes());
    }

    @VisibleForTesting
    public GmsClient(Context context, Looper looper, GmsClientSupervisor gmsClientSupervisor, GoogleApiAvailability googleApiAvailability, int i, ClientSettings clientSettings, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this(context, looper, gmsClientSupervisor, googleApiAvailability, i, clientSettings, (ConnectionCallbacks) connectionCallbacks, (OnConnectionFailedListener) onConnectionFailedListener);
    }

    @VisibleForTesting
    public GmsClient(Context context, Handler handler, GmsClientSupervisor gmsClientSupervisor, GoogleApiAvailability googleApiAvailability, int i, ClientSettings clientSettings, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, handler, gmsClientSupervisor, googleApiAvailability, i, zaa(connectionCallbacks), zaa(onConnectionFailedListener));
        Preconditions.checkNotNull(clientSettings);
        this.zafa = clientSettings;
        this.zax = clientSettings.getAccount();
        this.mScopes = zaa(clientSettings.getAllRequestedScopes());
    }

    @VisibleForTesting
    @Deprecated
    public GmsClient(Context context, Handler handler, GmsClientSupervisor gmsClientSupervisor, GoogleApiAvailability googleApiAvailability, int i, ClientSettings clientSettings, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this(context, handler, gmsClientSupervisor, googleApiAvailability, i, clientSettings, (ConnectionCallbacks) connectionCallbacks, (OnConnectionFailedListener) onConnectionFailedListener);
    }
}
