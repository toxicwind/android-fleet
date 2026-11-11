package com.google.android.gms.cast;

import com.google.android.gms.common.api.Status;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzbq extends com.google.android.gms.cast.internal.zzai {
    final /* synthetic */ zzbr zza;

    zzbq(zzbr zzbrVar) {
        this.zza = zzbrVar;
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzb(ApplicationMetadata applicationMetadata, String str, String str2, boolean z) {
        zzbr zzbrVar = this.zza;
        zzbrVar.zzp = applicationMetadata;
        zzbrVar.zzq = str;
        zzbr.zzL(zzbrVar, new com.google.android.gms.cast.internal.zzr(new Status(0), applicationMetadata, str, str2, z));
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzc(int i) {
        this.zza.zzU(i);
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzd(final int i) {
        zzbr zzbrVar = this.zza;
        zzbr.zzN(zzbrVar, i);
        if (zzbrVar.zzx != null) {
            zzbr.zzo(zzbrVar).post(new Runnable() { // from class: com.google.android.gms.cast.zzbo
                @Override // java.lang.Runnable
                public final void run() {
                    this.zza.zza.zzx.onApplicationDisconnected(i);
                }
            });
        }
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zze(int i) {
        zzbr.zzN(this.zza, i);
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzf(final com.google.android.gms.cast.internal.zza zzaVar) {
        zzbr.zzo(this.zza).post(new Runnable() { // from class: com.google.android.gms.cast.zzbk
            @Override // java.lang.Runnable
            public final void run() {
                zzbr.zzH(this.zza.zza, zzaVar);
            }
        });
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzg(int i) {
        zzbr.zzN(this.zza, i);
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzh(String str, byte[] bArr) {
        zzbr.zzg.d("IGNORING: Receive (type=binary, ns=%s) <%d bytes>", str, Integer.valueOf(bArr.length));
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzi(final int i) {
        zzbr.zzo(this.zza).post(new Runnable() { // from class: com.google.android.gms.cast.zzbm
            @Override // java.lang.Runnable
            public final void run() {
                zzbq zzbqVar = this.zza;
                int i2 = i;
                if (i2 != 0) {
                    zzbr zzbrVar = zzbqVar.zza;
                    zzbrVar.zzz = 1;
                    synchronized (zzbrVar.zzy) {
                        Iterator it = zzbrVar.zzy.iterator();
                        while (it.hasNext()) {
                            ((zzq) it.next()).zzb(i2);
                        }
                    }
                    zzbqVar.zza.zzS();
                    return;
                }
                zzbr zzbrVar2 = zzbqVar.zza;
                zzbrVar2.zzz = 3;
                zzbrVar2.zzk = true;
                zzbrVar2.zzl = true;
                synchronized (zzbrVar2.zzy) {
                    Iterator it2 = zzbrVar2.zzy.iterator();
                    while (it2.hasNext()) {
                        ((zzq) it2.next()).zza();
                    }
                }
            }
        });
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzj(final com.google.android.gms.cast.internal.zzac zzacVar) {
        zzbr.zzo(this.zza).post(new Runnable() { // from class: com.google.android.gms.cast.zzbj
            @Override // java.lang.Runnable
            public final void run() {
                zzbr.zzI(this.zza.zza, zzacVar);
            }
        });
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzk(final int i) {
        zzbr.zzo(this.zza).post(new Runnable() { // from class: com.google.android.gms.cast.zzbl
            @Override // java.lang.Runnable
            public final void run() {
                zzbq zzbqVar = this.zza;
                zzbr zzbrVar = zzbqVar.zza;
                zzbr.zzG(zzbrVar);
                zzbrVar.zzz = 1;
                int i2 = i;
                synchronized (zzbrVar.zzy) {
                    Iterator it = zzbrVar.zzy.iterator();
                    while (it.hasNext()) {
                        ((zzq) it.next()).zzd(i2);
                    }
                }
                zzbr zzbrVar2 = zzbqVar.zza;
                zzbrVar2.zzS();
                zzbrVar2.zzQ(zzbrVar2.zza);
            }
        });
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzl(String str, long j) {
        zzbr.zzM(this.zza, j, 0);
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzm(String str, long j, int i) {
        zzbr.zzM(this.zza, j, i);
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzn(String str, double d, boolean z) {
        zzbr.zzg.d("Deprecated callback: \"onStatusReceived\"", new Object[0]);
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzo(final int i) {
        zzbr.zzo(this.zza).post(new Runnable() { // from class: com.google.android.gms.cast.zzbp
            @Override // java.lang.Runnable
            public final void run() {
                zzbr zzbrVar = this.zza.zza;
                zzbrVar.zzz = 4;
                int i2 = i;
                synchronized (zzbrVar.zzy) {
                    Iterator it = zzbrVar.zzy.iterator();
                    while (it.hasNext()) {
                        ((zzq) it.next()).zzc(i2);
                    }
                }
            }
        });
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzp(final String str, final String str2) {
        zzbr.zzg.d("Receive (type=text, ns=%s) %s", str, str2);
        zzbr.zzo(this.zza).post(new Runnable() { // from class: com.google.android.gms.cast.zzbn
            @Override // java.lang.Runnable
            public final void run() {
                Cast.MessageReceivedCallback messageReceivedCallback;
                zzbq zzbqVar = this.zza;
                Map map = zzbqVar.zza.zze;
                String str3 = str;
                synchronized (map) {
                    messageReceivedCallback = (Cast.MessageReceivedCallback) map.get(str3);
                }
                if (messageReceivedCallback != null) {
                    messageReceivedCallback.onMessageReceived(zzbqVar.zza.zzw, str3, str2);
                } else {
                    zzbr.zzg.d("Discarded message for unknown namespace '%s'", str3);
                }
            }
        });
    }
}
