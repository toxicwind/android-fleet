package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzqe extends zzxi implements zzym {
    private static final zzqe zzb;
    private zztp zzD;
    private int zzE;
    private int zzF;
    private zzrc zzG;
    private int zzH;
    private zzqc zzI;
    private zzrc zzK;
    private int zzL;
    private int zzM;
    private int zzN;
    private int zzO;
    private int zzP;
    private int zzQ;
    private zzun zzR;
    private zzpt zzS;
    private zzqm zzT;
    private zzph zzU;
    private zzry zzV;
    private zztn zzW;
    private zzse zzX;
    private zzsc zzZ;
    private int zzaa;
    private zzsm zzab;
    private boolean zzad;
    private boolean zzae;
    private int zzaf;
    private zzpj zzag;
    private zzsu zzah;
    private zzrs zzai;
    private zzqu zzaj;
    private zzsk zzak;
    private zztx zzal;
    private zzqw zzam;
    private int zzan;
    private int zzao;
    private int zzap;
    private zzut zzar;
    private zztz zzas;
    private zztv zzat;
    private zzpx zzau;
    private zzuf zzav;
    private zztj zzaw;
    private zzsa zzax;
    private int zzd;
    private int zze;
    private long zzf;
    private long zzg;
    private int zzh;
    private zzro zzi;
    private zzss zzj;
    private zzrm zzk;
    private zzri zzl;
    private zzqa zzm;
    private zzsq zzn;
    private zzpn zzo;
    private zzul zzp;
    private zzrg zzr;
    private zzkq zzs;
    private int zzv;
    private zzsi zzw;
    private byte zzay = 2;
    private String zzq = "";
    private String zzt = "";
    private String zzu = "";
    private String zzx = "";
    private zzxl zzy = zzB();
    private zzxn zzz = zzD();
    private zzxn zzA = zzD();
    private zzxn zzB = zzD();
    private zzxn zzC = zzD();
    private zzxn zzJ = zzD();
    private zzxn zzY = zzD();
    private zzxn zzac = zzD();
    private zzxn zzaq = zzD();

    static {
        zzqe zzqeVar = new zzqe();
        zzb = zzqeVar;
        zzxi.zzJ(zzqe.class, zzqeVar);
    }

    private zzqe() {
    }

    public static zzqd zzc() {
        return (zzqd) zzb.zzy();
    }

    public static zzqd zzd(zzqe zzqeVar) {
        zzxf zzxfVarZzy = zzb.zzy();
        zzxfVarZzy.zzq(zzqeVar);
        return (zzqd) zzxfVarZzy;
    }

    static /* synthetic */ void zzf(zzqe zzqeVar, zzpp zzppVar) {
        zzppVar.getClass();
        zzxn zzxnVar = zzqeVar.zzY;
        if (!zzxnVar.zzc()) {
            zzqeVar.zzY = zzxi.zzE(zzxnVar);
        }
        zzqeVar.zzY.add(zzppVar);
    }

    static /* synthetic */ void zzg(zzqe zzqeVar, zzpj zzpjVar) {
        zzpjVar.getClass();
        zzqeVar.zzag = zzpjVar;
        zzqeVar.zze |= 8192;
    }

    static /* synthetic */ void zzh(zzqe zzqeVar, zzpt zzptVar) {
        zzptVar.getClass();
        zzqeVar.zzS = zzptVar;
        zzqeVar.zze |= 2;
    }

    static /* synthetic */ void zzi(zzqe zzqeVar, String str) {
        str.getClass();
        zzqeVar.zzd |= 32768;
        zzqeVar.zzu = str;
    }

    static /* synthetic */ void zzj(zzqe zzqeVar, String str) {
        str.getClass();
        zzqeVar.zzd |= 2048;
        zzqeVar.zzq = str;
    }

    static /* synthetic */ void zzk(zzqe zzqeVar, int i) {
        zzqeVar.zzd |= Integer.MIN_VALUE;
        zzqeVar.zzQ = i;
    }

    static /* synthetic */ void zzl(zzqe zzqeVar, int i) {
        zzqeVar.zzd |= 65536;
        zzqeVar.zzv = i;
    }

    static /* synthetic */ void zzm(zzqe zzqeVar, zztv zztvVar) {
        zztvVar.getClass();
        zzqeVar.zzat = zztvVar;
        zzqeVar.zze |= 33554432;
    }

    static /* synthetic */ void zzn(zzqe zzqeVar, String str) {
        zzqeVar.zzd |= 262144;
        zzqeVar.zzx = str;
    }

    static /* synthetic */ void zzo(zzqe zzqeVar, String str) {
        str.getClass();
        zzqeVar.zzd |= 16384;
        zzqeVar.zzt = str;
    }

    static /* synthetic */ void zzp(zzqe zzqeVar, zzqm zzqmVar) {
        zzqmVar.getClass();
        zzqeVar.zzT = zzqmVar;
        zzqeVar.zze |= 4;
    }

    static /* synthetic */ void zzq(zzqe zzqeVar, long j) {
        zzqeVar.zzd |= 2;
        zzqeVar.zzg = j;
    }

    public final zzpt zza() {
        zzpt zzptVar = this.zzS;
        return zzptVar == null ? zzpt.zze() : zzptVar;
    }

    @Override // com.google.android.gms.internal.cast.zzxi
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.zzay);
        }
        if (i2 == 2) {
            return zzG(zzb, "\u0001G\u0000\u0002\u0001GG\u0000\t\u0001\u0001ဂ\u0000\u0002ဂ\u0001\u0003᠌\u0002\u0004ဉ\u0003\u0005ဉ\u0004\u0006ဉ\u0005\u0007ဉ\u0006\bဉ\u0007\tဈ\u000e\nဉ\b\u000bဉ\t\fဉ\n\rဈ\u000b\u000eဉ\f\u000fဉ\r\u0010ဉ\u0011\u0011ဈ\u0012\u0012\u0016\u0013\u001b\u0014\u001b\u0015\u001b\u0016\u001b\u0017᠌\u0014\u0018ဉ\u0018\u0019\u001b\u001aဉ\u0019\u001b᠌\u001b\u001cင\u001c\u001dင\u001d\u001eင\u001e\u001fဆ\u001f ဉ !ဉ!\"ဉ##᠌\u0015$ဉ\u0016%ᐉ$&ဉ%'ဉ&(\u001b)᠌(*ဉ)+\u001b,᠌\u001a-ဇ*.ဇ+/᠌,0ဉ-1င\u00172ဉ.3ဉ/4ဉ15ဉ26ဉ37᠌48᠌59᠌6:\u001b;ဈ\u000f<ဉ7=ဉ0>ဉ\u0013?ဉ\"@င\u0010Aဉ8Bဉ'Cဉ9Dဉ:Eဉ;Fဉ<Gဉ=", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", zzol.zza(), "zzi", "zzj", "zzk", "zzl", "zzm", "zzt", "zzn", "zzo", "zzp", "zzq", "zzr", "zzs", "zzw", "zzx", "zzy", "zzz", zzrq.class, "zzA", zzru.class, "zzB", zzqy.class, "zzC", zztr.class, "zzE", zznv.zza(), "zzI", "zzJ", zzrc.class, "zzK", "zzM", zzmj.zza(), "zzN", "zzO", "zzP", "zzQ", "zzR", "zzS", "zzU", "zzF", zznr.zza(), "zzG", "zzV", "zzW", "zzX", "zzY", zzpp.class, "zzaa", zznt.zza(), "zzab", "zzac", zzpv.class, "zzL", zzmh.zza(), "zzad", "zzae", "zzaf", zzmd.zza(), "zzag", "zzH", "zzah", "zzai", "zzak", "zzal", "zzam", "zzan", zzlj.zza(), "zzao", zznd.zza(), "zzap", zzlh.zza(), "zzaq", zzuh.class, "zzu", "zzar", "zzaj", "zzD", "zzT", "zzv", "zzas", "zzZ", "zzat", "zzau", "zzav", "zzaw", "zzax"});
        }
        if (i2 == 3) {
            return new zzqe();
        }
        zzuw zzuwVar = null;
        if (i2 == 4) {
            return new zzqd(zzuwVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        this.zzay = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
