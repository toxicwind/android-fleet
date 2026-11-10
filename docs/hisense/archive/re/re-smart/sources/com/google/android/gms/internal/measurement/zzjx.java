package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzjt;
import com.google.android.gms.internal.measurement.zzjx;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class zzjx<MessageType extends zzjx<MessageType, BuilderType>, BuilderType extends zzjt<MessageType, BuilderType>> extends zzih<MessageType, BuilderType> {
    public static final Map<Object, zzjx<?, ?>> zza = new ConcurrentHashMap();
    public zzmc zzc = zzmc.zza;
    public int zzd = -1;

    public static <E> zzke<E> zzbA() {
        return zzll.zza;
    }

    public static <E> zzke<E> zzbB(zzke<E> zzkeVar) {
        int size = zzkeVar.size();
        return zzkeVar.zzd(size == 0 ? 10 : size + size);
    }

    public static Object zzbE(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e2);
        } catch (InvocationTargetException e3) {
            Throwable cause = e3.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            if (cause instanceof Error) {
                throw ((Error) cause);
            }
            throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
        }
    }

    public static Object zzbF(zzlc zzlcVar, String str, Object[] objArr) {
        return new zzlm(zzlcVar, str, objArr);
    }

    public static <T extends zzjx> void zzbG(Class<T> cls, T t) {
        zza.put(cls, t);
    }

    public static <T extends zzjx> T zzbw(Class<T> cls) {
        zzjx<?, ?> zzjxVar = zza.get(cls);
        if (zzjxVar == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                zzjxVar = zza.get(cls);
            } catch (ClassNotFoundException e2) {
                throw new IllegalStateException("Class initialization cannot fail.", e2);
            }
        }
        if (zzjxVar == null) {
            zzjxVar = (zzjx) ((zzjx) zzml.zze(cls)).zzl(6, null, null);
            if (zzjxVar == null) {
                throw new IllegalStateException();
            }
            zza.put(cls, zzjxVar);
        }
        return zzjxVar;
    }

    public static zzkc zzbx() {
        return zzjy.zza;
    }

    public static zzkd zzby() {
        return zzkr.zza;
    }

    public static zzkd zzbz(zzkd zzkdVar) {
        int size = zzkdVar.size();
        return zzkdVar.zzd(size == 0 ? 10 : size + size);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return zzlk.zza.zzb(getClass()).zzi(this, (zzjx) obj);
        }
        return false;
    }

    public final int hashCode() {
        int i = this.zzb;
        if (i != 0) {
            return i;
        }
        int iZzb = zzlk.zza.zzb(getClass()).zzb(this);
        this.zzb = iZzb;
        return iZzb;
    }

    public final String toString() {
        return zzle.zza(this, super.toString());
    }

    @Override // com.google.android.gms.internal.measurement.zzlc
    public final /* synthetic */ zzlb zzbC() {
        return (zzjt) zzl(5, null, null);
    }

    @Override // com.google.android.gms.internal.measurement.zzlc
    public final /* synthetic */ zzlb zzbD() {
        zzjt zzjtVar = (zzjt) zzl(5, null, null);
        zzjtVar.zzay(this);
        return zzjtVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzlc
    public final void zzbH(zzje zzjeVar) {
        zzln zzlnVarZzb = zzlk.zza.zzb(getClass());
        zzjf zzjfVar = zzjeVar.zza;
        if (zzjfVar == null) {
            zzjfVar = new zzjf(zzjeVar);
        }
        zzlnVarZzb.zzm(this, zzjfVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzld
    public final /* synthetic */ zzlc zzbL() {
        return (zzjx) zzl(6, null, null);
    }

    @Override // com.google.android.gms.internal.measurement.zzih
    public final int zzbo() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.measurement.zzih
    public final void zzbr(int i) {
        this.zzd = i;
    }

    @Override // com.google.android.gms.internal.measurement.zzlc
    public final int zzbt() {
        int i = this.zzd;
        if (i != -1) {
            return i;
        }
        int iZza = zzlk.zza.zzb(getClass()).zza(this);
        this.zzd = iZza;
        return iZza;
    }

    public final <MessageType extends zzjx<MessageType, BuilderType>, BuilderType extends zzjt<MessageType, BuilderType>> BuilderType zzbu() {
        return (BuilderType) zzl(5, null, null);
    }

    public final BuilderType zzbv() {
        BuilderType buildertype = (BuilderType) zzl(5, null, null);
        buildertype.zzay(this);
        return buildertype;
    }

    public abstract Object zzl(int i, Object obj, Object obj2);
}
