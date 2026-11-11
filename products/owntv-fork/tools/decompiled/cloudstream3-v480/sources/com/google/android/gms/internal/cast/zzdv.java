package com.google.android.gms.internal.cast;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkRequest;
import androidx.core.content.ContextCompat;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.internal.Preconditions;
import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzdv implements zzds {
    private static final Logger zzb = new Logger("ConnectivityMonitor");
    private final zzvp zzc;
    private final ConnectivityManager zze;
    private boolean zzh;
    private final Context zzi;
    private final Object zzj = new Object();
    public final Set zza = DesugarCollections.synchronizedSet(new HashSet());
    private final Map zzf = DesugarCollections.synchronizedMap(new HashMap());
    private final List zzg = DesugarCollections.synchronizedList(new ArrayList());
    private final ConnectivityManager.NetworkCallback zzd = new zzdu(this);

    public zzdv(Context context, zzvp zzvpVar) {
        this.zzc = zzvpVar;
        this.zzi = context;
        this.zze = (ConnectivityManager) context.getSystemService("connectivity");
    }

    public static /* synthetic */ void zzb(zzdv zzdvVar, zzdr zzdrVar) {
        List list = zzdvVar.zzg;
        if (list != null) {
            list.isEmpty();
        }
        zzdrVar.zza();
    }

    static /* bridge */ /* synthetic */ void zzd(zzdv zzdvVar) {
        List list;
        synchronized (Preconditions.checkNotNull(zzdvVar.zzj)) {
            Map map = zzdvVar.zzf;
            if (map != null && (list = zzdvVar.zzg) != null) {
                zzb.d("all networks are unavailable.", new Object[0]);
                map.clear();
                list.clear();
                zzdvVar.zzg();
            }
        }
    }

    static /* bridge */ /* synthetic */ void zze(zzdv zzdvVar, Network network) {
        List list;
        synchronized (Preconditions.checkNotNull(zzdvVar.zzj)) {
            Map map = zzdvVar.zzf;
            if (map != null && (list = zzdvVar.zzg) != null) {
                zzb.d("the network is lost", new Object[0]);
                if (list.remove(network)) {
                    map.remove(network);
                }
                zzdvVar.zzg();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzf(Network network, LinkProperties linkProperties) {
        List list;
        synchronized (Preconditions.checkNotNull(this.zzj)) {
            Map map = this.zzf;
            if (map != null && (list = this.zzg) != null) {
                zzb.d("a new network is available", new Object[0]);
                if (map.containsKey(network)) {
                    list.remove(network);
                }
                map.put(network, linkProperties);
                list.add(network);
                zzg();
            }
        }
    }

    private final void zzg() {
        zzvp zzvpVar = this.zzc;
        if (zzvpVar == null) {
            return;
        }
        Set<zzdr> set = this.zza;
        synchronized (set) {
            for (final zzdr zzdrVar : set) {
                if (!zzvpVar.isShutdown()) {
                    zzvpVar.execute(new Runnable() { // from class: com.google.android.gms.internal.cast.zzdt
                        @Override // java.lang.Runnable
                        public final void run() {
                            zzdv.zzb(this.zza, zzdrVar);
                        }
                    });
                }
            }
        }
    }

    @Override // com.google.android.gms.internal.cast.zzds
    public final void zza() {
        ConnectivityManager connectivityManager;
        LinkProperties linkProperties;
        zzb.d("Start monitoring connectivity changes", new Object[0]);
        if (this.zzh || (connectivityManager = this.zze) == null || ContextCompat.checkSelfPermission(this.zzi, "android.permission.ACCESS_NETWORK_STATE") != 0) {
            return;
        }
        Network activeNetwork = connectivityManager.getActiveNetwork();
        if (activeNetwork != null && (linkProperties = connectivityManager.getLinkProperties(activeNetwork)) != null) {
            zzf(activeNetwork, linkProperties);
        }
        connectivityManager.registerNetworkCallback(new NetworkRequest.Builder().addTransportType(1).build(), this.zzd);
        this.zzh = true;
    }
}
