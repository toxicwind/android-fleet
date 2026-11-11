package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzbz {
    private final String zza;
    private final long zzb;
    private final int zzc;
    private final long zzd;
    private final long zze;
    private long zzf;

    public zzbz(zzby zzbyVar) {
        this.zza = zzbyVar.zza;
        this.zzb = zzbyVar.zzb;
        this.zzc = zzbyVar.zzc;
        this.zzd = zzbyVar.zzd;
        this.zze = zzbyVar.zze;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:71:0x0108  */
    public final zzqg zza() {
        int i;
        String str = this.zza;
        zzqf zzqfVarZza = zzqg.zza();
        switch (str) {
            case "queueInsert":
                i = 13;
                break;
            case "launch":
                i = 22;
                break;
            case "queueRemove":
                i = 15;
                break;
            case "queueFetchItems":
                i = 19;
                break;
            case "setPlaybackDevices":
                i = 23;
                break;
            case "volume-mute":
                i = 9;
                break;
            case "skipAd":
                i = 21;
                break;
            case "status":
                i = 10;
                break;
            case "queueUpdate":
                i = 14;
                break;
            case "volume":
                i = 7;
                break;
            case "setPlaybackRate":
                i = 20;
                break;
            case "load":
                i = 2;
                break;
            case "mute":
                i = 8;
                break;
            case "play":
                i = 3;
                break;
            case "seek":
                i = 6;
                break;
            case "stop":
                i = 5;
                break;
            case "pause":
                i = 4;
                break;
            case "queueFetchItemRange":
                i = 18;
                break;
            case "queueReorder":
                i = 16;
                break;
            case "trackStyle":
                i = 12;
                break;
            case "activeTracks":
                i = 11;
                break;
            case "queueFetchItemIds":
                i = 17;
                break;
            default:
                i = 1;
                break;
        }
        zzqfVarZza.zze(i);
        zzqfVarZza.zzb((int) this.zzb);
        zzqfVarZza.zzd(this.zzc);
        zzqfVarZza.zzc((int) (this.zzd - this.zzf));
        zzqfVarZza.zza((int) (this.zze - this.zzf));
        return (zzqg) zzqfVarZza.zzr();
    }

    public final void zzb(long j) {
        this.zzf = j;
    }
}
