package com.google.android.gms.internal.measurement;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes.dex */
public final class zzkf {
    public static final Charset zza = Charset.forName("US-ASCII");
    public static final Charset zzb = Charset.forName("UTF-8");
    public static final Charset zzc = Charset.forName("ISO-8859-1");
    public static final byte[] zzd = new byte[0];
    public static final ByteBuffer zze = ByteBuffer.wrap(zzd);
    public static final zzja zzf;

    static {
        byte[] bArr = zzd;
        int i = zzja.zza;
        int length = bArr.length;
        zziz zzizVar = new zziz(bArr, 0, 0, false, null);
        try {
            zzizVar.zza(0);
            zzf = zzizVar;
        } catch (zzkh e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    public static int zza(boolean z) {
        return z ? 1231 : 1237;
    }

    public static int zzb(byte[] bArr) {
        int length = bArr.length;
        int iZzd = zzd(length, bArr, 0, length);
        if (iZzd == 0) {
            return 1;
        }
        return iZzd;
    }

    public static int zzc(long j) {
        return (int) (j ^ (j >>> 32));
    }

    public static int zzd(int i, byte[] bArr, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            i = (i * 31) + bArr[i4];
        }
        return i;
    }

    public static <T> T zze(T t) {
        if (t != null) {
            return t;
        }
        throw null;
    }

    public static <T> T zzf(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    public static Object zzg(Object obj, Object obj2) {
        return ((zzlc) obj).zzbD().zzau((zzlc) obj2).zzaC();
    }

    public static String zzh(byte[] bArr) {
        return new String(bArr, zzb);
    }

    public static boolean zzi(byte[] bArr) {
        return zzmq.zze(bArr);
    }
}
