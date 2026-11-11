package com.google.android.gms.internal.cast;

import java.io.IOException;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzyx {
    public static final /* synthetic */ int zza = 0;
    private static final zzzh zzb;

    static {
        int i = zzys.zza;
        zzb = new zzzj();
    }

    public static void zzA(int i, List list, zzzt zzztVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzztVar.zzD(i, list, z);
    }

    public static void zzB(int i, List list, zzzt zzztVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzztVar.zzH(i, list, z);
    }

    public static void zzC(int i, List list, zzzt zzztVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzztVar.zzJ(i, list, z);
    }

    static boolean zzD(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    static int zza(List list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (!(list instanceof zzxj)) {
            int iZzA = 0;
            while (i < size) {
                iZzA += zzwu.zzA(((Integer) list.get(i)).intValue());
                i++;
            }
            return iZzA;
        }
        zzxj zzxjVar = (zzxj) list;
        int iZzA2 = 0;
        while (i < size) {
            iZzA2 += zzwu.zzA(zzxjVar.zze(i));
            i++;
        }
        return iZzA2;
    }

    static int zzb(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzwu.zzz(i << 3) + 4);
    }

    static int zzc(List list) {
        return list.size() * 4;
    }

    static int zzd(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzwu.zzz(i << 3) + 8);
    }

    static int zze(List list) {
        return list.size() * 8;
    }

    static int zzf(List list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (!(list instanceof zzxj)) {
            int iZzA = 0;
            while (i < size) {
                iZzA += zzwu.zzA(((Integer) list.get(i)).intValue());
                i++;
            }
            return iZzA;
        }
        zzxj zzxjVar = (zzxj) list;
        int iZzA2 = 0;
        while (i < size) {
            iZzA2 += zzwu.zzA(zzxjVar.zze(i));
            i++;
        }
        return iZzA2;
    }

    static int zzg(List list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (!(list instanceof zzya)) {
            int iZzA = 0;
            while (i < size) {
                iZzA += zzwu.zzA(((Long) list.get(i)).longValue());
                i++;
            }
            return iZzA;
        }
        zzya zzyaVar = (zzya) list;
        int iZzA2 = 0;
        while (i < size) {
            iZzA2 += zzwu.zzA(zzyaVar.zze(i));
            i++;
        }
        return iZzA2;
    }

    static int zzh(int i, Object obj, zzyv zzyvVar) {
        int i2 = i << 3;
        if (!(obj instanceof zzxw)) {
            return zzwu.zzz(i2) + zzwu.zzx((zzyl) obj, zzyvVar);
        }
        int iZzz = zzwu.zzz(i2);
        int iZza = ((zzxw) obj).zza();
        return iZzz + zzwu.zzz(iZza) + iZza;
    }

    static int zzi(List list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (!(list instanceof zzxj)) {
            int iZzz = 0;
            while (i < size) {
                int iIntValue = ((Integer) list.get(i)).intValue();
                iZzz += zzwu.zzz((iIntValue >> 31) ^ (iIntValue + iIntValue));
                i++;
            }
            return iZzz;
        }
        zzxj zzxjVar = (zzxj) list;
        int iZzz2 = 0;
        while (i < size) {
            int iZze = zzxjVar.zze(i);
            iZzz2 += zzwu.zzz((iZze >> 31) ^ (iZze + iZze));
            i++;
        }
        return iZzz2;
    }

    static int zzj(List list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (!(list instanceof zzya)) {
            int iZzA = 0;
            while (i < size) {
                long jLongValue = ((Long) list.get(i)).longValue();
                iZzA += zzwu.zzA((jLongValue >> 63) ^ (jLongValue + jLongValue));
                i++;
            }
            return iZzA;
        }
        zzya zzyaVar = (zzya) list;
        int iZzA2 = 0;
        while (i < size) {
            long jZze = zzyaVar.zze(i);
            iZzA2 += zzwu.zzA((jZze >> 63) ^ (jZze + jZze));
            i++;
        }
        return iZzA2;
    }

    static int zzk(List list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (!(list instanceof zzxj)) {
            int iZzz = 0;
            while (i < size) {
                iZzz += zzwu.zzz(((Integer) list.get(i)).intValue());
                i++;
            }
            return iZzz;
        }
        zzxj zzxjVar = (zzxj) list;
        int iZzz2 = 0;
        while (i < size) {
            iZzz2 += zzwu.zzz(zzxjVar.zze(i));
            i++;
        }
        return iZzz2;
    }

    static int zzl(List list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (!(list instanceof zzya)) {
            int iZzA = 0;
            while (i < size) {
                iZzA += zzwu.zzA(((Long) list.get(i)).longValue());
                i++;
            }
            return iZzA;
        }
        zzya zzyaVar = (zzya) list;
        int iZzA2 = 0;
        while (i < size) {
            iZzA2 += zzwu.zzA(zzyaVar.zze(i));
            i++;
        }
        return iZzA2;
    }

    public static zzzh zzm() {
        return zzb;
    }

    static void zzn(zzwx zzwxVar, Object obj, Object obj2) {
        if (((zzxg) obj2).zzb.zza.isEmpty()) {
            return;
        }
        throw null;
    }

    static void zzo(zzzh zzzhVar, Object obj, Object obj2) {
        zzxi zzxiVar = (zzxi) obj;
        zzzi zzziVarZze = zzxiVar.zzc;
        zzzi zzziVar = ((zzxi) obj2).zzc;
        if (!zzzi.zzc().equals(zzziVar)) {
            if (zzzi.zzc().equals(zzziVarZze)) {
                zzziVarZze = zzzi.zze(zzziVarZze, zzziVar);
            } else {
                zzziVarZze.zzd(zzziVar);
            }
        }
        zzxiVar.zzc = zzziVarZze;
    }

    public static void zzp(int i, List list, zzzt zzztVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzztVar.zzc(i, list, z);
    }

    public static void zzq(int i, List list, zzzt zzztVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzztVar.zzg(i, list, z);
    }

    public static void zzr(int i, List list, zzzt zzztVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzztVar.zzi(i, list, z);
    }

    public static void zzs(int i, List list, zzzt zzztVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzztVar.zzk(i, list, z);
    }

    public static void zzt(int i, List list, zzzt zzztVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzztVar.zzm(i, list, z);
    }

    public static void zzu(int i, List list, zzzt zzztVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzztVar.zzo(i, list, z);
    }

    public static void zzv(int i, List list, zzzt zzztVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzztVar.zzr(i, list, z);
    }

    public static void zzw(int i, List list, zzzt zzztVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzztVar.zzt(i, list, z);
    }

    public static void zzx(int i, List list, zzzt zzztVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzztVar.zzx(i, list, z);
    }

    public static void zzy(int i, List list, zzzt zzztVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzztVar.zzz(i, list, z);
    }

    public static void zzz(int i, List list, zzzt zzztVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzztVar.zzB(i, list, z);
    }
}
