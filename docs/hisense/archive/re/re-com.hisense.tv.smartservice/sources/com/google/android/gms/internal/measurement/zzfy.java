package com.google.android.gms.internal.measurement;

import com.google.firebase.storage.UploadTask;
import com.google.protobuf.MessageSchema;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class zzfy extends zzjx<zzfy, zzfx> implements zzld {
    public static final /* synthetic */ int zza = 0;
    public static final zzfy zze;
    public boolean zzA;
    public long zzC;
    public int zzD;
    public boolean zzG;
    public int zzJ;
    public int zzK;
    public int zzL;
    public long zzN;
    public long zzO;
    public int zzR;
    public zzgb zzT;
    public long zzV;
    public long zzW;
    public int zzZ;
    public boolean zzaa;
    public boolean zzac;
    public zzfu zzad;
    public int zzf;
    public int zzg;
    public int zzh;
    public long zzk;
    public long zzl;
    public long zzm;
    public long zzn;
    public long zzo;
    public int zzt;
    public long zzx;
    public long zzy;
    public zzke<zzfo> zzi = zzjx.zzbA();
    public zzke<zzgh> zzj = zzjx.zzbA();
    public String zzp = "";
    public String zzq = "";
    public String zzr = "";
    public String zzs = "";
    public String zzu = "";
    public String zzv = "";
    public String zzw = "";
    public String zzz = "";
    public String zzB = "";
    public String zzE = "";
    public String zzF = "";
    public zzke<zzfk> zzH = zzjx.zzbA();
    public String zzI = "";
    public String zzM = "";
    public String zzP = "";
    public String zzQ = "";
    public String zzS = "";
    public zzkc zzU = zzjx.zzbx();
    public String zzX = "";
    public String zzY = "";
    public String zzab = "";

    static {
        zzfy zzfyVar = new zzfy();
        zze = zzfyVar;
        zzjx.zza.put(zzfy.class, zzfyVar);
    }

    public static /* synthetic */ void zzQ(zzfy zzfyVar, long j) {
        zzfyVar.zzf |= 1073741824;
        zzfyVar.zzO = j;
    }

    public static /* synthetic */ void zzR(zzfy zzfyVar) {
        zzfyVar.zzf &= Integer.MAX_VALUE;
        zzfyVar.zzP = zze.zzP;
    }

    public static /* synthetic */ void zzS(zzfy zzfyVar, int i) {
        zzfyVar.zzg |= 2;
        zzfyVar.zzR = i;
    }

    public static /* synthetic */ void zzT(zzfy zzfyVar, int i, zzfo zzfoVar) {
        zzfoVar.getClass();
        zzfyVar.zzbI();
        zzfyVar.zzi.set(i, zzfoVar);
    }

    public static /* synthetic */ void zzU(zzfy zzfyVar, String str) {
        str.getClass();
        zzfyVar.zzg |= 4;
        zzfyVar.zzS = str;
    }

    public static /* synthetic */ void zzV(zzfy zzfyVar, zzgb zzgbVar) {
        zzgbVar.getClass();
        zzfyVar.zzT = zzgbVar;
        zzfyVar.zzg |= 8;
    }

    public static /* synthetic */ void zzW(zzfy zzfyVar, Iterable iterable) {
        zzkc zzkcVar = zzfyVar.zzU;
        if (!zzkcVar.zzc()) {
            int size = zzkcVar.size();
            zzfyVar.zzU = zzkcVar.zzd(size == 0 ? 10 : size + size);
        }
        zzih.zzbq(iterable, zzfyVar.zzU);
    }

    public static /* synthetic */ void zzX(zzfy zzfyVar, zzfo zzfoVar) {
        zzfoVar.getClass();
        zzfyVar.zzbI();
        zzfyVar.zzi.add(zzfoVar);
    }

    public static /* synthetic */ void zzY(zzfy zzfyVar, long j) {
        zzfyVar.zzg |= 16;
        zzfyVar.zzV = j;
    }

    public static /* synthetic */ void zzZ(zzfy zzfyVar, long j) {
        zzfyVar.zzg |= 32;
        zzfyVar.zzW = j;
    }

    public static /* synthetic */ void zzaA(zzfy zzfyVar, String str) {
        str.getClass();
        zzfyVar.zzf |= 65536;
        zzfyVar.zzz = str;
    }

    public static /* synthetic */ void zzaB(zzfy zzfyVar) {
        zzfyVar.zzf &= -65537;
        zzfyVar.zzz = zze.zzz;
    }

    public static /* synthetic */ void zzaC(zzfy zzfyVar, boolean z) {
        zzfyVar.zzf |= 131072;
        zzfyVar.zzA = z;
    }

    public static /* synthetic */ void zzaD(zzfy zzfyVar) {
        zzfyVar.zzf &= -131073;
        zzfyVar.zzA = false;
    }

    public static /* synthetic */ void zzaE(zzfy zzfyVar, String str) {
        str.getClass();
        zzfyVar.zzf |= 262144;
        zzfyVar.zzB = str;
    }

    public static /* synthetic */ void zzaF(zzfy zzfyVar) {
        zzfyVar.zzf &= -262145;
        zzfyVar.zzB = zze.zzB;
    }

    public static /* synthetic */ void zzaG(zzfy zzfyVar, long j) {
        zzfyVar.zzf |= 524288;
        zzfyVar.zzC = j;
    }

    public static /* synthetic */ void zzaH(zzfy zzfyVar, int i) {
        zzfyVar.zzf |= 1048576;
        zzfyVar.zzD = i;
    }

    public static /* synthetic */ void zzaI(zzfy zzfyVar, String str) {
        zzfyVar.zzf |= 2097152;
        zzfyVar.zzE = str;
    }

    public static /* synthetic */ void zzaJ(zzfy zzfyVar) {
        zzfyVar.zzf &= -2097153;
        zzfyVar.zzE = zze.zzE;
    }

    public static /* synthetic */ void zzaK(zzfy zzfyVar, String str) {
        str.getClass();
        zzfyVar.zzf |= 4194304;
        zzfyVar.zzF = str;
    }

    public static /* synthetic */ void zzaL(zzfy zzfyVar, boolean z) {
        zzfyVar.zzf |= 8388608;
        zzfyVar.zzG = z;
    }

    public static /* synthetic */ void zzaM(zzfy zzfyVar, Iterable iterable) {
        zzke<zzfk> zzkeVar = zzfyVar.zzH;
        if (!zzkeVar.zzc()) {
            zzfyVar.zzH = zzjx.zzbB(zzkeVar);
        }
        zzih.zzbq(iterable, zzfyVar.zzH);
    }

    public static /* synthetic */ void zzaO(zzfy zzfyVar, String str) {
        str.getClass();
        zzfyVar.zzf |= 16777216;
        zzfyVar.zzI = str;
    }

    public static /* synthetic */ void zzaP(zzfy zzfyVar, int i) {
        zzfyVar.zzf |= UploadTask.MAXIMUM_CHUNK_SIZE;
        zzfyVar.zzJ = i;
    }

    public static /* synthetic */ void zzaQ(zzfy zzfyVar, int i) {
        zzfyVar.zzf |= 1;
        zzfyVar.zzh = 1;
    }

    public static /* synthetic */ void zzaR(zzfy zzfyVar) {
        zzfyVar.zzf &= -268435457;
        zzfyVar.zzM = zze.zzM;
    }

    public static /* synthetic */ void zzaS(zzfy zzfyVar, long j) {
        zzfyVar.zzf |= MessageSchema.ENFORCE_UTF8_MASK;
        zzfyVar.zzN = j;
    }

    public static /* synthetic */ void zzaa(zzfy zzfyVar, String str) {
        str.getClass();
        zzfyVar.zzg |= 64;
        zzfyVar.zzX = str;
    }

    public static /* synthetic */ void zzab(zzfy zzfyVar, String str) {
        zzfyVar.zzg |= 128;
        zzfyVar.zzY = str;
    }

    public static /* synthetic */ void zzac(zzfy zzfyVar, Iterable iterable) {
        zzfyVar.zzbI();
        zzih.zzbq(iterable, zzfyVar.zzi);
    }

    public static /* synthetic */ void zzae(zzfy zzfyVar, int i) {
        zzfyVar.zzbI();
        zzfyVar.zzi.remove(i);
    }

    public static /* synthetic */ void zzaf(zzfy zzfyVar, int i, zzgh zzghVar) {
        zzghVar.getClass();
        zzfyVar.zzbJ();
        zzfyVar.zzj.set(i, zzghVar);
    }

    public static /* synthetic */ void zzag(zzfy zzfyVar, zzgh zzghVar) {
        zzghVar.getClass();
        zzfyVar.zzbJ();
        zzfyVar.zzj.add(zzghVar);
    }

    public static /* synthetic */ void zzah(zzfy zzfyVar, Iterable iterable) {
        zzfyVar.zzbJ();
        zzih.zzbq(iterable, zzfyVar.zzj);
    }

    public static /* synthetic */ void zzai(zzfy zzfyVar, int i) {
        zzfyVar.zzbJ();
        zzfyVar.zzj.remove(i);
    }

    public static /* synthetic */ void zzaj(zzfy zzfyVar, long j) {
        zzfyVar.zzf |= 2;
        zzfyVar.zzk = j;
    }

    public static /* synthetic */ void zzak(zzfy zzfyVar, long j) {
        zzfyVar.zzf |= 4;
        zzfyVar.zzl = j;
    }

    public static /* synthetic */ void zzal(zzfy zzfyVar, long j) {
        zzfyVar.zzf |= 8;
        zzfyVar.zzm = j;
    }

    public static /* synthetic */ void zzam(zzfy zzfyVar, long j) {
        zzfyVar.zzf |= 16;
        zzfyVar.zzn = j;
    }

    public static /* synthetic */ void zzan(zzfy zzfyVar) {
        zzfyVar.zzf &= -17;
        zzfyVar.zzn = 0L;
    }

    public static /* synthetic */ void zzao(zzfy zzfyVar, long j) {
        zzfyVar.zzf |= 32;
        zzfyVar.zzo = j;
    }

    public static /* synthetic */ void zzap(zzfy zzfyVar) {
        zzfyVar.zzf &= -33;
        zzfyVar.zzo = 0L;
    }

    public static /* synthetic */ void zzaq(zzfy zzfyVar, String str) {
        zzfyVar.zzf |= 64;
        zzfyVar.zzp = "android";
    }

    public static /* synthetic */ void zzar(zzfy zzfyVar, String str) {
        str.getClass();
        zzfyVar.zzf |= 128;
        zzfyVar.zzq = str;
    }

    public static /* synthetic */ void zzas(zzfy zzfyVar, String str) {
        str.getClass();
        zzfyVar.zzf |= 256;
        zzfyVar.zzr = str;
    }

    public static /* synthetic */ void zzat(zzfy zzfyVar, String str) {
        str.getClass();
        zzfyVar.zzf |= 512;
        zzfyVar.zzs = str;
    }

    public static /* synthetic */ void zzau(zzfy zzfyVar, int i) {
        zzfyVar.zzf |= 1024;
        zzfyVar.zzt = i;
    }

    public static /* synthetic */ void zzav(zzfy zzfyVar, String str) {
        str.getClass();
        zzfyVar.zzf |= 2048;
        zzfyVar.zzu = str;
    }

    public static /* synthetic */ void zzaw(zzfy zzfyVar, String str) {
        str.getClass();
        zzfyVar.zzf |= 4096;
        zzfyVar.zzv = str;
    }

    public static /* synthetic */ void zzax(zzfy zzfyVar, String str) {
        str.getClass();
        zzfyVar.zzf |= 8192;
        zzfyVar.zzw = str;
    }

    public static /* synthetic */ void zzay(zzfy zzfyVar, long j) {
        zzfyVar.zzf |= 16384;
        zzfyVar.zzx = j;
    }

    public static /* synthetic */ void zzaz(zzfy zzfyVar, long j) {
        zzfyVar.zzf |= 32768;
        zzfyVar.zzy = 46000L;
    }

    private final void zzbI() {
        zzke<zzfo> zzkeVar = this.zzi;
        if (zzkeVar.zzc()) {
            return;
        }
        this.zzi = zzjx.zzbB(zzkeVar);
    }

    private final void zzbJ() {
        zzke<zzgh> zzkeVar = this.zzj;
        if (zzkeVar.zzc()) {
            return;
        }
        this.zzj = zzjx.zzbB(zzkeVar);
    }

    public static zzfx zzu() {
        return zze.zzbu();
    }

    public final String zzA() {
        return this.zzu;
    }

    public final String zzB() {
        return this.zzw;
    }

    public final String zzC() {
        return this.zzY;
    }

    public final String zzD() {
        return this.zzr;
    }

    public final String zzE() {
        return this.zzP;
    }

    public final String zzF() {
        return this.zzI;
    }

    public final String zzG() {
        return this.zzX;
    }

    public final String zzH() {
        return this.zzF;
    }

    public final String zzI() {
        return this.zzE;
    }

    public final String zzJ() {
        return this.zzq;
    }

    public final String zzK() {
        return this.zzp;
    }

    public final String zzL() {
        return this.zzz;
    }

    public final String zzM() {
        return this.zzs;
    }

    public final List<zzfk> zzN() {
        return this.zzH;
    }

    public final List<zzfo> zzO() {
        return this.zzi;
    }

    public final List<zzgh> zzP() {
        return this.zzj;
    }

    public final int zza() {
        return this.zzJ;
    }

    public final boolean zzaT() {
        return this.zzA;
    }

    public final boolean zzaU() {
        return this.zzG;
    }

    public final boolean zzaV() {
        return (this.zzf & 1073741824) != 0;
    }

    public final boolean zzaW() {
        return (this.zzf & UploadTask.MAXIMUM_CHUNK_SIZE) != 0;
    }

    public final boolean zzaX() {
        return (this.zzf & 1048576) != 0;
    }

    public final boolean zzaY() {
        return (this.zzf & MessageSchema.ENFORCE_UTF8_MASK) != 0;
    }

    public final boolean zzaZ() {
        return (this.zzg & 128) != 0;
    }

    public final int zzb() {
        return this.zzD;
    }

    public final boolean zzba() {
        return (this.zzf & 524288) != 0;
    }

    public final boolean zzbb() {
        return (this.zzg & 16) != 0;
    }

    public final boolean zzbc() {
        return (this.zzf & 8) != 0;
    }

    public final boolean zzbd() {
        return (this.zzf & 16384) != 0;
    }

    public final boolean zzbe() {
        return (this.zzf & 131072) != 0;
    }

    public final boolean zzbf() {
        return (this.zzf & 32) != 0;
    }

    public final boolean zzbg() {
        return (this.zzf & 16) != 0;
    }

    public final boolean zzbh() {
        return (this.zzf & 1) != 0;
    }

    public final boolean zzbi() {
        return (this.zzg & 2) != 0;
    }

    public final boolean zzbj() {
        return (this.zzf & 8388608) != 0;
    }

    public final boolean zzbk() {
        return (this.zzf & 4) != 0;
    }

    public final boolean zzbl() {
        return (this.zzf & 1024) != 0;
    }

    public final boolean zzbm() {
        return (this.zzf & 2) != 0;
    }

    public final boolean zzbn() {
        return (this.zzf & 32768) != 0;
    }

    public final int zzc() {
        return this.zzi.size();
    }

    public final int zzd() {
        return this.zzh;
    }

    public final int zze() {
        return this.zzR;
    }

    public final int zzf() {
        return this.zzt;
    }

    public final int zzg() {
        return this.zzj.size();
    }

    public final long zzh() {
        return this.zzO;
    }

    public final long zzi() {
        return this.zzN;
    }

    public final long zzj() {
        return this.zzC;
    }

    public final long zzk() {
        return this.zzV;
    }

    @Override // com.google.android.gms.internal.measurement.zzjx
    public final Object zzl(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzjx.zzbF(zze, "\u00011\u0000\u0002\u0001;1\u0000\u0004\u0000\u0001င\u0000\u0002\u001b\u0003\u001b\u0004ဂ\u0001\u0005ဂ\u0002\u0006ဂ\u0003\u0007ဂ\u0005\bဈ\u0006\tဈ\u0007\nဈ\b\u000bဈ\t\fင\n\rဈ\u000b\u000eဈ\f\u0010ဈ\r\u0011ဂ\u000e\u0012ဂ\u000f\u0013ဈ\u0010\u0014ဇ\u0011\u0015ဈ\u0012\u0016ဂ\u0013\u0017င\u0014\u0018ဈ\u0015\u0019ဈ\u0016\u001aဂ\u0004\u001cဇ\u0017\u001d\u001b\u001eဈ\u0018\u001fင\u0019 င\u001a!င\u001b\"ဈ\u001c#ဂ\u001d$ဂ\u001e%ဈ\u001f&ဈ 'င!)ဈ\",ဉ#-\u001d.ဂ$/ဂ%2ဈ&4ဈ'5ဌ(7ဇ)9ဈ*:ဇ+;ဉ,", new Object[]{"zzf", "zzg", "zzh", "zzi", zzfo.class, "zzj", zzgh.class, "zzk", "zzl", "zzm", "zzo", "zzp", "zzq", "zzr", "zzs", "zzt", "zzu", "zzv", "zzw", "zzx", "zzy", "zzz", "zzA", "zzB", "zzC", "zzD", "zzE", "zzF", "zzn", "zzG", "zzH", zzfk.class, "zzI", "zzJ", "zzK", "zzL", "zzM", "zzN", "zzO", "zzP", "zzQ", "zzR", "zzS", "zzT", "zzU", "zzV", "zzW", "zzX", "zzY", "zzZ", zzfg.zza, "zzaa", "zzab", "zzac", "zzad"});
        }
        if (i2 == 3) {
            return new zzfy();
        }
        zzff zzffVar = null;
        if (i2 == 4) {
            return new zzfx(zzffVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zze;
    }

    public final long zzm() {
        return this.zzm;
    }

    public final long zzn() {
        return this.zzx;
    }

    public final long zzo() {
        return this.zzo;
    }

    public final long zzp() {
        return this.zzn;
    }

    public final long zzq() {
        return this.zzl;
    }

    public final long zzr() {
        return this.zzk;
    }

    public final long zzs() {
        return this.zzy;
    }

    public final zzfo zzt(int i) {
        return this.zzi.get(i);
    }

    public final zzgh zzw(int i) {
        return this.zzj.get(i);
    }

    public final String zzx() {
        return this.zzS;
    }

    public final String zzy() {
        return this.zzv;
    }

    public final String zzz() {
        return this.zzB;
    }
}
