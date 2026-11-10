package com.google.android.gms.internal.measurement;

/* JADX INFO: loaded from: classes.dex */
public final class zzkp extends zzkq {
    public zzkp() {
        super(null);
    }

    public /* synthetic */ zzkp(zzkn zzknVar) {
        super(null);
    }

    @Override // com.google.android.gms.internal.measurement.zzkq
    public final void zza(Object obj, long j) {
        ((zzke) zzml.zzf(obj, j)).zzb();
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // com.google.android.gms.internal.measurement.zzkq
    public final <E> void zzb(Object obj, Object obj2, long j) {
        zzke zzkeVarZzd = (zzke) zzml.zzf(obj, j);
        zzke zzkeVar = (zzke) zzml.zzf(obj2, j);
        int size = zzkeVarZzd.size();
        int size2 = zzkeVar.size();
        if (size > 0 && size2 > 0) {
            if (!zzkeVarZzd.zzc()) {
                zzkeVarZzd = zzkeVarZzd.zzd(size2 + size);
            }
            zzkeVarZzd.addAll(zzkeVar);
        }
        if (size > 0) {
            zzkeVar = zzkeVarZzd;
        }
        zzml.zzg.zzp(obj, j, zzkeVar);
    }
}
