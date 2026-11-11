package com.google.android.gms.internal.cast;

import com.google.android.gms.internal.cast.zzxf;
import com.google.android.gms.internal.cast.zzxi;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public class zzxf<MessageType extends zzxi<MessageType, BuilderType>, BuilderType extends zzxf<MessageType, BuilderType>> extends zzvz<MessageType, BuilderType> {
    protected zzxi zza;
    private final zzxi zzb;

    protected zzxf(MessageType messagetype) {
        this.zzb = messagetype;
        if (messagetype.zzM()) {
            throw new IllegalArgumentException("Default instance must be immutable.");
        }
        this.zza = messagetype.zzA();
    }

    private static void zza(Object obj, Object obj2) {
        zzys.zza().zzb(obj.getClass()).zze(obj, obj2);
    }

    @Override // com.google.android.gms.internal.cast.zzvz
    /* JADX INFO: renamed from: zzp, reason: merged with bridge method [inline-methods] */
    public final zzxf clone() {
        zzxf zzxfVar = (zzxf) this.zzb.zzb(5, null, null);
        zzxfVar.zza = zzt();
        return zzxfVar;
    }

    public final zzxf zzq(zzxi zzxiVar) {
        if (!this.zzb.equals(zzxiVar)) {
            if (!this.zza.zzM()) {
                zzw();
            }
            zza(this.zza, zzxiVar);
        }
        return this;
    }

    public final MessageType zzr() {
        MessageType messagetype = (MessageType) zzt();
        if (messagetype.zzx()) {
            return messagetype;
        }
        throw new zzzg(messagetype);
    }

    @Override // com.google.android.gms.internal.cast.zzyk
    /* JADX INFO: renamed from: zzs, reason: merged with bridge method [inline-methods] */
    public MessageType zzt() {
        boolean zZzM = this.zza.zzM();
        MessageType messagetype = (MessageType) this.zza;
        if (!zZzM) {
            return messagetype;
        }
        messagetype.zzH();
        return (MessageType) this.zza;
    }

    @Override // com.google.android.gms.internal.cast.zzym
    public final /* bridge */ /* synthetic */ zzyl zzu() {
        throw null;
    }

    protected final void zzv() {
        if (this.zza.zzM()) {
            return;
        }
        zzw();
    }

    protected void zzw() {
        zzxi zzxiVarZzA = this.zzb.zzA();
        zza(zzxiVarZzA, this.zza);
        this.zza = zzxiVarZzA;
    }

    @Override // com.google.android.gms.internal.cast.zzym
    public final boolean zzx() {
        return zzxi.zzd(this.zza, false);
    }
}
