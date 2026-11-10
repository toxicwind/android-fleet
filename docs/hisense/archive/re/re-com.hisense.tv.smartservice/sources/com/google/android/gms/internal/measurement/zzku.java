package com.google.android.gms.internal.measurement;

/* JADX INFO: loaded from: classes.dex */
public final class zzku implements zzlo {
    public static final zzla zza = new zzks();
    public final zzla zzb;

    public zzku() {
        zzla zzlaVar;
        zzla[] zzlaVarArr = new zzla[2];
        zzlaVarArr[0] = zzjs.zza;
        try {
            zzlaVar = (zzla) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            zzlaVar = zza;
        }
        zzlaVarArr[1] = zzlaVar;
        zzkt zzktVar = new zzkt(zzlaVarArr);
        zzkf.zzf(zzktVar, "messageInfoFactory");
        this.zzb = zzktVar;
    }

    public static boolean zzb(zzkz zzkzVar) {
        return zzkzVar.zzc() == 1;
    }

    @Override // com.google.android.gms.internal.measurement.zzlo
    public final <T> zzln<T> zza(Class<T> cls) {
        zzlp.zzG(cls);
        zzkz zzkzVarZzb = this.zzb.zzb(cls);
        if (zzkzVarZzb.zzb()) {
            return zzjx.class.isAssignableFrom(cls) ? new zzlg(zzlp.zzd, zzjm.zza, zzkzVarZzb.zza()) : new zzlg(zzlp.zzb, zzjm.zza(), zzkzVarZzb.zza());
        }
        if (zzjx.class.isAssignableFrom(cls)) {
            return zzkzVarZzb.zzc() == 1 ? zzlf.zzk(cls, zzkzVarZzb, zzli.zzb, zzkq.zzb, zzlp.zzd, zzjm.zza, zzky.zzb) : zzlf.zzk(cls, zzkzVarZzb, zzli.zzb, zzkq.zzb, zzlp.zzd, null, zzky.zzb);
        }
        return zzkzVarZzb.zzc() == 1 ? zzlf.zzk(cls, zzkzVarZzb, zzli.zza, zzkq.zza, zzlp.zzb, zzjm.zza(), zzky.zza) : zzlf.zzk(cls, zzkzVarZzb, zzli.zza, zzkq.zza, zzlp.zzc, null, zzky.zza);
    }
}
