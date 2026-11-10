package com.google.android.gms.common.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;

/* JADX INFO: loaded from: classes.dex */
public class SimpleClientAdapter<T extends IInterface> extends GmsClient<T> {
    public final Api.SimpleClient<T> zapu;

    public SimpleClientAdapter(Context context, Looper looper, int i, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, ClientSettings clientSettings, Api.SimpleClient<T> simpleClient) {
        super(context, looper, i, clientSettings, (ConnectionCallbacks) connectionCallbacks, (OnConnectionFailedListener) onConnectionFailedListener);
        this.zapu = simpleClient;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public T createServiceInterface(IBinder iBinder) {
        return (T) this.zapu.createServiceInterface(iBinder);
    }

    public Api.SimpleClient<T> getClient() {
        return this.zapu;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public String getServiceDescriptor() {
        return this.zapu.getServiceDescriptor();
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public String getStartServiceAction() {
        return this.zapu.getStartServiceAction();
    }

    public void onSetConnectState(int i, T t) {
        this.zapu.setState(i, t);
    }
}
