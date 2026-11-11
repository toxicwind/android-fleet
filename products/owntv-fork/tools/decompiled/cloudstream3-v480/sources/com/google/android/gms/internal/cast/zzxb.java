package com.google.android.gms.internal.cast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzxb {
    private static final zzxb zzb = new zzxb(true);
    final zzzd zza = new zzyy();
    private boolean zzc;
    private boolean zzd;

    private zzxb() {
    }

    static int zza(zzzr zzzrVar, int i, Object obj) {
        zzwu.zzz(i << 3);
        if (zzzr.GROUP == null) {
            zzyl zzylVar = (zzyl) obj;
            byte[] bArr = zzxo.zzb;
            if (zzylVar instanceof zzwb) {
                throw null;
            }
        }
        zzzs zzzsVar = zzzs.INT;
        throw null;
    }

    public static int zzb(zzxa zzxaVar, Object obj) {
        zzzr zzzrVarZzb = zzxaVar.zzb();
        int iZza = zzxaVar.zza();
        if (!zzxaVar.zze()) {
            return zza(zzzrVarZzb, iZza, obj);
        }
        List list = (List) obj;
        int size = list.size();
        if (!zzxaVar.zzd()) {
            int iZza2 = 0;
            for (int i = 0; i < size; i++) {
                iZza2 += zza(zzzrVarZzb, iZza, list.get(i));
            }
            return iZza2;
        }
        if (list.isEmpty()) {
            return 0;
        }
        if (size <= 0) {
            return zzwu.zzz(iZza << 3) + zzwu.zzz(0);
        }
        list.get(0);
        zzzr zzzrVar = zzzr.DOUBLE;
        zzzs zzzsVar = zzzs.INT;
        throw null;
    }

    public static zzxb zzd() {
        return zzb;
    }

    private static boolean zzi(Map.Entry entry) {
        zzxa zzxaVar = (zzxa) entry.getKey();
        if (zzxaVar.zzc() != zzzs.MESSAGE) {
            return true;
        }
        if (!zzxaVar.zze()) {
            return zzj(entry.getValue());
        }
        List list = (List) entry.getValue();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (!zzj(list.get(i))) {
                return false;
            }
        }
        return true;
    }

    private static boolean zzj(Object obj) {
        if (obj instanceof zzym) {
            return ((zzym) obj).zzx();
        }
        if (obj instanceof zzxv) {
            return true;
        }
        throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
    }

    private static final int zzk(Map.Entry entry) {
        int i;
        int iZzz;
        int iZzz2;
        int iZzw;
        int iZzz3;
        zzxa zzxaVar = (zzxa) entry.getKey();
        Object value = entry.getValue();
        if (zzxaVar.zzc() != zzzs.MESSAGE || zzxaVar.zze() || zzxaVar.zzd()) {
            return zzb(zzxaVar, value);
        }
        if (value instanceof zzxv) {
            int iZza = ((zzxa) entry.getKey()).zza();
            int iZzz4 = zzwu.zzz(8);
            i = iZzz4 + iZzz4;
            iZzz = zzwu.zzz(16) + zzwu.zzz(iZza);
            iZzz2 = zzwu.zzz(24);
            iZzw = ((zzxv) value).zza();
            iZzz3 = zzwu.zzz(iZzw);
        } else {
            int iZza2 = ((zzxa) entry.getKey()).zza();
            int iZzz5 = zzwu.zzz(8);
            i = iZzz5 + iZzz5;
            iZzz = zzwu.zzz(16) + zzwu.zzz(iZza2);
            iZzz2 = zzwu.zzz(24);
            iZzw = ((zzyl) value).zzw();
            iZzz3 = zzwu.zzz(iZzw);
        }
        return i + iZzz + iZzz2 + iZzz3 + iZzw;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:32:? A[RETURN, SYNTHETIC] */
    private static final void zzl(zzxa zzxaVar, Object obj) {
        boolean z;
        zzxaVar.zzb();
        byte[] bArr = zzxo.zzb;
        obj.getClass();
        zzzr zzzrVar = zzzr.DOUBLE;
        zzzs zzzsVar = zzzs.INT;
        switch (r0.zza()) {
            case INT:
                z = obj instanceof Integer;
                if (z) {
                    return;
                }
                throw new IllegalArgumentException(String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", Integer.valueOf(zzxaVar.zza()), zzxaVar.zzb().zza(), obj.getClass().getName()));
            case LONG:
                z = obj instanceof Long;
                if (z) {
                    return;
                }
                throw new IllegalArgumentException(String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", Integer.valueOf(zzxaVar.zza()), zzxaVar.zzb().zza(), obj.getClass().getName()));
            case FLOAT:
                z = obj instanceof Float;
                if (z) {
                    return;
                }
                throw new IllegalArgumentException(String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", Integer.valueOf(zzxaVar.zza()), zzxaVar.zzb().zza(), obj.getClass().getName()));
            case DOUBLE:
                z = obj instanceof Double;
                if (z) {
                    return;
                }
                throw new IllegalArgumentException(String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", Integer.valueOf(zzxaVar.zza()), zzxaVar.zzb().zza(), obj.getClass().getName()));
            case BOOLEAN:
                z = obj instanceof Boolean;
                if (z) {
                    return;
                }
                throw new IllegalArgumentException(String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", Integer.valueOf(zzxaVar.zza()), zzxaVar.zzb().zza(), obj.getClass().getName()));
            case STRING:
                z = obj instanceof String;
                if (z) {
                    return;
                }
                throw new IllegalArgumentException(String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", Integer.valueOf(zzxaVar.zza()), zzxaVar.zzb().zza(), obj.getClass().getName()));
            case BYTE_STRING:
                if ((obj instanceof zzwn) || (obj instanceof byte[])) {
                    return;
                }
                throw new IllegalArgumentException(String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", Integer.valueOf(zzxaVar.zza()), zzxaVar.zzb().zza(), obj.getClass().getName()));
            case ENUM:
                if ((obj instanceof Integer) || (obj instanceof zzpb)) {
                    return;
                }
                throw new IllegalArgumentException(String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", Integer.valueOf(zzxaVar.zza()), zzxaVar.zzb().zza(), obj.getClass().getName()));
            case MESSAGE:
                if ((obj instanceof zzyl) || (obj instanceof zzxv)) {
                    return;
                }
                throw new IllegalArgumentException(String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", Integer.valueOf(zzxaVar.zza()), zzxaVar.zzb().zza(), obj.getClass().getName()));
            default:
                throw new IllegalArgumentException(String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", Integer.valueOf(zzxaVar.zza()), zzxaVar.zzb().zza(), obj.getClass().getName()));
        }
    }

    public final /* bridge */ /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzxb zzxbVar = new zzxb();
        zzzd zzzdVar = this.zza;
        int iZzc = zzzdVar.zzc();
        for (int i = 0; i < iZzc; i++) {
            Map.Entry entryZzg = zzzdVar.zzg(i);
            zzxbVar.zzg((zzxa) ((zzyz) entryZzg).zza(), entryZzg.getValue());
        }
        for (Map.Entry entry : zzzdVar.zzd()) {
            zzxbVar.zzg((zzxa) entry.getKey(), entry.getValue());
        }
        zzxbVar.zzd = this.zzd;
        return zzxbVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzxb) {
            return this.zza.equals(((zzxb) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final int zzc() {
        zzzd zzzdVar = this.zza;
        int iZzc = zzzdVar.zzc();
        int iZzk = 0;
        for (int i = 0; i < iZzc; i++) {
            iZzk += zzk(zzzdVar.zzg(i));
        }
        Iterator it = zzzdVar.zzd().iterator();
        while (it.hasNext()) {
            iZzk += zzk((Map.Entry) it.next());
        }
        return iZzk;
    }

    public final Iterator zze() {
        zzzd zzzdVar = this.zza;
        if (zzzdVar.isEmpty()) {
            return Collections.emptyIterator();
        }
        return this.zzd ? new zzxt(zzzdVar.entrySet().iterator()) : zzzdVar.entrySet().iterator();
    }

    public final void zzf() {
        if (this.zzc) {
            return;
        }
        zzzd zzzdVar = this.zza;
        int iZzc = zzzdVar.zzc();
        for (int i = 0; i < iZzc; i++) {
            Object value = zzzdVar.zzg(i).getValue();
            if (value instanceof zzxi) {
                ((zzxi) value).zzH();
            }
        }
        Iterator it = zzzdVar.zzd().iterator();
        while (it.hasNext()) {
            Object value2 = ((Map.Entry) it.next()).getValue();
            if (value2 instanceof zzxi) {
                ((zzxi) value2).zzH();
            }
        }
        zzzdVar.zza();
        this.zzc = true;
    }

    public final void zzg(zzxa zzxaVar, Object obj) {
        if (!zzxaVar.zze()) {
            zzl(zzxaVar, obj);
        } else {
            if (!(obj instanceof List)) {
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }
            List list = (List) obj;
            int size = list.size();
            ArrayList arrayList = new ArrayList(size);
            for (int i = 0; i < size; i++) {
                Object obj2 = list.get(i);
                zzl(zzxaVar, obj2);
                arrayList.add(obj2);
            }
            obj = arrayList;
        }
        if (obj instanceof zzxv) {
            this.zzd = true;
        }
        this.zza.put(zzxaVar, obj);
    }

    public final boolean zzh() {
        zzzd zzzdVar = this.zza;
        int iZzc = zzzdVar.zzc();
        for (int i = 0; i < iZzc; i++) {
            if (!zzi(zzzdVar.zzg(i))) {
                return false;
            }
        }
        Iterator it = zzzdVar.zzd().iterator();
        while (it.hasNext()) {
            if (!zzi((Map.Entry) it.next())) {
                return false;
            }
        }
        return true;
    }

    private zzxb(boolean z) {
        zzf();
        zzf();
    }
}
