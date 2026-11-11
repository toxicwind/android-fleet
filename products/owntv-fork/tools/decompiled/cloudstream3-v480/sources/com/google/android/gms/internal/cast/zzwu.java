package com.google.android.gms.internal.cast;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public abstract class zzwu extends zzwf {
    public static final /* synthetic */ int zzb = 0;
    private static final Logger zzc = Logger.getLogger(zzwu.class.getName());
    private static final boolean zzd = zzzo.zzx();
    zzwv zza;

    private zzwu() {
        throw null;
    }

    /* synthetic */ zzwu(zzwt zzwtVar) {
    }

    public static int zzA(long j) {
        return (640 - (Long.numberOfLeadingZeros(j) * 9)) >>> 6;
    }

    @Deprecated
    static int zzw(int i, zzyl zzylVar, zzyv zzyvVar) {
        int iZzz = zzz(i << 3);
        return iZzz + iZzz + ((zzwa) zzylVar).zzr(zzyvVar);
    }

    static int zzx(zzyl zzylVar, zzyv zzyvVar) {
        int iZzr = ((zzwa) zzylVar).zzr(zzyvVar);
        return zzz(iZzr) + iZzr;
    }

    public static int zzy(String str) {
        int length;
        try {
            length = zzzq.zzb(str);
        } catch (zzzp unused) {
            length = str.getBytes(zzxo.zza).length;
        }
        return zzz(length) + length;
    }

    public static int zzz(int i) {
        return (352 - (Integer.numberOfLeadingZeros(i) * 9)) >>> 6;
    }

    public final void zzB() {
        if (zza() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    final void zzC(String str, zzzp zzzpVar) throws IOException {
        zzc.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", (Throwable) zzzpVar);
        byte[] bytes = str.getBytes(zzxo.zza);
        try {
            int length = bytes.length;
            zzt(length);
            zzl(bytes, 0, length);
        } catch (IndexOutOfBoundsException e) {
            throw new zzws(e);
        }
    }

    public abstract int zza();

    public abstract void zzb(byte b) throws IOException;

    public abstract void zzd(int i, boolean z) throws IOException;

    public abstract void zze(int i, zzwn zzwnVar) throws IOException;

    public abstract void zzf(int i, int i2) throws IOException;

    public abstract void zzg(int i) throws IOException;

    public abstract void zzh(int i, long j) throws IOException;

    public abstract void zzi(long j) throws IOException;

    public abstract void zzj(int i, int i2) throws IOException;

    public abstract void zzk(int i) throws IOException;

    public abstract void zzl(byte[] bArr, int i, int i2) throws IOException;

    abstract void zzm(int i, zzyl zzylVar, zzyv zzyvVar) throws IOException;

    public abstract void zzn(int i, zzyl zzylVar) throws IOException;

    public abstract void zzo(int i, zzwn zzwnVar) throws IOException;

    public abstract void zzp(int i, String str) throws IOException;

    public abstract void zzr(int i, int i2) throws IOException;

    public abstract void zzs(int i, int i2) throws IOException;

    public abstract void zzt(int i) throws IOException;

    public abstract void zzu(int i, long j) throws IOException;

    public abstract void zzv(long j) throws IOException;
}
