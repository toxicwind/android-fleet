package com.google.android.gms.internal.measurement;

import com.ju.lib.datacommunication.network.http.address.AddressResponse;
import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes.dex */
public final class zzml {
    public static final long zza;
    public static final boolean zzb;
    public static final Unsafe zzc = zzg();
    public static final Class<?> zzd = zzij.zza();
    public static final boolean zze = zzv(Long.TYPE);
    public static final boolean zzf = zzv(Integer.TYPE);
    public static final zzmk zzg;
    public static final boolean zzh;
    public static final boolean zzi;

    /* JADX WARN: Code duplicated, block: B:13:0x0042  */
    static {
        Unsafe unsafe;
        boolean z;
        Unsafe unsafe2;
        boolean z2;
        zzmk zzmkVar;
        Unsafe unsafe3 = zzc;
        zzmk zzmiVar = null;
        if (unsafe3 != null) {
            if (zze) {
                zzmiVar = new zzmj(unsafe3);
            } else if (zzf) {
                zzmiVar = new zzmi(unsafe3);
            }
        }
        zzg = zzmiVar;
        zzmk zzmkVar2 = zzg;
        if (zzmkVar2 == null || (unsafe = zzmkVar2.zza) == null) {
            z = false;
        } else {
            try {
                Class<?> cls = unsafe.getClass();
                cls.getMethod("objectFieldOffset", Field.class);
                cls.getMethod("getLong", Object.class, Long.TYPE);
                if (zzB() == null) {
                    z = false;
                } else {
                    z = true;
                }
            } catch (Throwable th) {
                zzh(th);
            }
        }
        zzh = z;
        zzmk zzmkVar3 = zzg;
        if (zzmkVar3 == null || (unsafe2 = zzmkVar3.zza) == null) {
            z2 = false;
        } else {
            try {
                Class<?> cls2 = unsafe2.getClass();
                cls2.getMethod("objectFieldOffset", Field.class);
                cls2.getMethod("arrayBaseOffset", Class.class);
                cls2.getMethod("arrayIndexScale", Class.class);
                cls2.getMethod("getInt", Object.class, Long.TYPE);
                cls2.getMethod("putInt", Object.class, Long.TYPE, Integer.TYPE);
                cls2.getMethod("getLong", Object.class, Long.TYPE);
                cls2.getMethod("putLong", Object.class, Long.TYPE, Long.TYPE);
                cls2.getMethod("getObject", Object.class, Long.TYPE);
                cls2.getMethod("putObject", Object.class, Long.TYPE, Object.class);
                z2 = true;
            } catch (Throwable th2) {
                zzh(th2);
                z2 = false;
            }
        }
        zzi = z2;
        zza = zzz(byte[].class);
        zzz(boolean[].class);
        zzA(boolean[].class);
        zzz(int[].class);
        zzA(int[].class);
        zzz(long[].class);
        zzA(long[].class);
        zzz(float[].class);
        zzA(float[].class);
        zzz(double[].class);
        zzA(double[].class);
        zzz(Object[].class);
        zzA(Object[].class);
        Field fieldZzB = zzB();
        if (fieldZzB != null && (zzmkVar = zzg) != null) {
            zzmkVar.zzl(fieldZzB);
        }
        zzb = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN;
    }

    public static int zzA(Class<?> cls) {
        if (zzi) {
            return zzg.zzi(cls);
        }
        return -1;
    }

    public static Field zzB() {
        int i = zzij.zza;
        Field fieldZzC = zzC(Buffer.class, "effectiveDirectAddress");
        if (fieldZzC != null) {
            return fieldZzC;
        }
        Field fieldZzC2 = zzC(Buffer.class, AddressResponse.sAddress);
        if (fieldZzC2 == null || fieldZzC2.getType() != Long.TYPE) {
            return null;
        }
        return fieldZzC2;
    }

    public static Field zzC(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void zzD(Object obj, long j, byte b2) {
        long j2 = (-4) & j;
        int iZzj = zzg.zzj(obj, j2);
        int i = ((~((int) j)) & 3) << 3;
        zzg.zzn(obj, j2, ((b2 & 255) << i) | (iZzj & (~(255 << i))));
    }

    public static void zzE(Object obj, long j, byte b2) {
        long j2 = (-4) & j;
        int iZzj = zzg.zzj(obj, j2);
        int i = (((int) j) & 3) << 3;
        zzg.zzn(obj, j2, ((b2 & 255) << i) | (iZzj & (~(255 << i))));
    }

    public static double zza(Object obj, long j) {
        return zzg.zza(obj, j);
    }

    public static float zzb(Object obj, long j) {
        return zzg.zzb(obj, j);
    }

    public static int zzc(Object obj, long j) {
        return zzg.zzj(obj, j);
    }

    public static long zzd(Object obj, long j) {
        return zzg.zzk(obj, j);
    }

    public static <T> T zze(Class<T> cls) {
        try {
            return (T) zzc.allocateInstance(cls);
        } catch (InstantiationException e2) {
            throw new IllegalStateException(e2);
        }
    }

    public static Object zzf(Object obj, long j) {
        return zzg.zzm(obj, j);
    }

    public static Unsafe zzg() {
        try {
            return (Unsafe) AccessController.doPrivileged(new zzmh());
        } catch (Throwable unused) {
            return null;
        }
    }

    public static /* synthetic */ void zzh(Throwable th) {
        Logger.getLogger(zzml.class.getName()).logp(Level.WARNING, "com.google.protobuf.UnsafeUtil", "logMissingMethod", "platform method missing - proto runtime falling back to safer methods: ".concat(th.toString()));
    }

    public static void zzm(Object obj, long j, boolean z) {
        zzg.zzc(obj, j, z);
    }

    public static void zzn(byte[] bArr, long j, byte b2) {
        zzg.zzd(bArr, zza + j, b2);
    }

    public static void zzo(Object obj, long j, double d2) {
        zzg.zze(obj, j, d2);
    }

    public static void zzp(Object obj, long j, float f2) {
        zzg.zzf(obj, j, f2);
    }

    public static void zzq(Object obj, long j, int i) {
        zzg.zzn(obj, j, i);
    }

    public static void zzr(Object obj, long j, long j2) {
        zzg.zzo(obj, j, j2);
    }

    public static void zzs(Object obj, long j, Object obj2) {
        zzg.zzp(obj, j, obj2);
    }

    public static /* synthetic */ boolean zzt(Object obj, long j) {
        return ((byte) ((zzg.zzj(obj, (-4) & j) >>> ((int) (((~j) & 3) << 3))) & 255)) != 0;
    }

    public static /* synthetic */ boolean zzu(Object obj, long j) {
        return ((byte) ((zzg.zzj(obj, (-4) & j) >>> ((int) ((j & 3) << 3))) & 255)) != 0;
    }

    public static boolean zzv(Class<?> cls) {
        int i = zzij.zza;
        try {
            Class<?> cls2 = zzd;
            cls2.getMethod("peekLong", cls, Boolean.TYPE);
            cls2.getMethod("pokeLong", cls, Long.TYPE, Boolean.TYPE);
            cls2.getMethod("pokeInt", cls, Integer.TYPE, Boolean.TYPE);
            cls2.getMethod("peekInt", cls, Boolean.TYPE);
            cls2.getMethod("pokeByte", cls, Byte.TYPE);
            cls2.getMethod("peekByte", cls);
            cls2.getMethod("pokeByteArray", cls, byte[].class, Integer.TYPE, Integer.TYPE);
            cls2.getMethod("peekByteArray", cls, byte[].class, Integer.TYPE, Integer.TYPE);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean zzw(Object obj, long j) {
        return zzg.zzg(obj, j);
    }

    public static boolean zzx() {
        return zzi;
    }

    public static boolean zzy() {
        return zzh;
    }

    public static int zzz(Class<?> cls) {
        if (zzi) {
            return zzg.zzh(cls);
        }
        return -1;
    }
}
