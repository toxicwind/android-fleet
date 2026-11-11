package com.google.android.gms.internal.cast;

import com.google.android.gms.internal.cast.zzxf;
import com.google.android.gms.internal.cast.zzxi;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public abstract class zzxi<MessageType extends zzxi<MessageType, BuilderType>, BuilderType extends zzxf<MessageType, BuilderType>> extends zzwa<MessageType, BuilderType> {
    private static final Map zzb = new ConcurrentHashMap();
    private int zzd = -1;
    protected zzzi zzc = zzzi.zzc();

    protected static zzxl zzB() {
        return zzxj.zzf();
    }

    protected static zzxm zzC() {
        return zzya.zzf();
    }

    protected static zzxn zzD() {
        return zzyt.zze();
    }

    protected static zzxn zzE(zzxn zzxnVar) {
        int size = zzxnVar.size();
        return zzxnVar.zzd(size + size);
    }

    static Object zzF(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            if (cause instanceof Error) {
                throw ((Error) cause);
            }
            throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
        }
    }

    protected static Object zzG(zzyl zzylVar, String str, Object[] objArr) {
        return new zzyu(zzylVar, str, objArr);
    }

    protected static void zzJ(Class cls, zzxi zzxiVar) {
        zzxiVar.zzI();
        zzb.put(cls, zzxiVar);
    }

    private final int zzc(zzyv zzyvVar) {
        return zzys.zza().zzb(getClass()).zza(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean zzd(zzxi zzxiVar, boolean z) {
        byte bByteValue = ((Byte) zzxiVar.zzb(1, null, null)).byteValue();
        if (bByteValue == 1) {
            return true;
        }
        if (bByteValue == 0) {
            return false;
        }
        boolean zZzh = zzys.zza().zzb(zzxiVar.getClass()).zzh(zzxiVar);
        if (z) {
            zzxiVar.zzb(2, true != zZzh ? null : zzxiVar, null);
        }
        return zZzh;
    }

    static zzxi zzz(Class cls) {
        Map map = zzb;
        zzxi zzxiVar = (zzxi) map.get(cls);
        if (zzxiVar == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                zzxiVar = (zzxi) map.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (zzxiVar != null) {
            return zzxiVar;
        }
        zzxi zzxiVar2 = (zzxi) ((zzxi) zzzo.zze(cls)).zzb(6, null, null);
        if (zzxiVar2 == null) {
            throw new IllegalStateException();
        }
        map.put(cls, zzxiVar2);
        return zzxiVar2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return zzys.zza().zzb(getClass()).zzg(this, (zzxi) obj);
    }

    public final int hashCode() {
        if (zzM()) {
            return zzv();
        }
        int i = this.zza;
        if (i != 0) {
            return i;
        }
        int iZzv = zzv();
        this.zza = iZzv;
        return iZzv;
    }

    public final String toString() {
        return zzyn.zza(this, super.toString());
    }

    final zzxi zzA() {
        return (zzxi) zzb(4, null, null);
    }

    protected final void zzH() {
        zzys.zza().zzb(getClass()).zzd(this);
        zzI();
    }

    final void zzI() {
        this.zzd &= Integer.MAX_VALUE;
    }

    final void zzK(int i) {
        this.zzd = (this.zzd & Integer.MIN_VALUE) | Integer.MAX_VALUE;
    }

    final boolean zzM() {
        return (this.zzd & Integer.MIN_VALUE) != 0;
    }

    @Override // com.google.android.gms.internal.cast.zzyl
    public final /* synthetic */ zzyk zzN() {
        return (zzxf) zzb(5, null, null);
    }

    @Override // com.google.android.gms.internal.cast.zzyl
    public final void zzO(zzwu zzwuVar) throws IOException {
        zzys.zza().zzb(getClass()).zzf(this, zzwv.zza(zzwuVar));
    }

    protected abstract Object zzb(int i, Object obj, Object obj2);

    @Override // com.google.android.gms.internal.cast.zzwa
    final int zzr(zzyv zzyvVar) {
        if (zzM()) {
            int iZza = zzyvVar.zza(this);
            if (iZza >= 0) {
                return iZza;
            }
            throw new IllegalStateException("serialized size must be non-negative, was " + iZza);
        }
        int i = this.zzd & Integer.MAX_VALUE;
        if (i != Integer.MAX_VALUE) {
            return i;
        }
        int iZza2 = zzyvVar.zza(this);
        if (iZza2 >= 0) {
            this.zzd = (this.zzd & Integer.MIN_VALUE) | iZza2;
            return iZza2;
        }
        throw new IllegalStateException("serialized size must be non-negative, was " + iZza2);
    }

    @Override // com.google.android.gms.internal.cast.zzym
    public final /* synthetic */ zzyl zzu() {
        return (zzxi) zzb(6, null, null);
    }

    final int zzv() {
        return zzys.zza().zzb(getClass()).zzb(this);
    }

    @Override // com.google.android.gms.internal.cast.zzym
    public final boolean zzx() {
        return zzd(this, true);
    }

    protected final zzxf zzy() {
        return (zzxf) zzb(5, null, null);
    }

    @Override // com.google.android.gms.internal.cast.zzyl
    public final int zzw() {
        if (zzM()) {
            int iZzc = zzc(null);
            if (iZzc >= 0) {
                return iZzc;
            }
            throw new IllegalStateException("serialized size must be non-negative, was " + iZzc);
        }
        int i = this.zzd & Integer.MAX_VALUE;
        if (i != Integer.MAX_VALUE) {
            return i;
        }
        int iZzc2 = zzc(null);
        if (iZzc2 >= 0) {
            this.zzd = (this.zzd & Integer.MIN_VALUE) | iZzc2;
            return iZzc2;
        }
        throw new IllegalStateException("serialized size must be non-negative, was " + iZzc2);
    }
}
