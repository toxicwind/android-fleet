package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class zzko extends zzkq {
    public static final Class<?> zza = Collections.unmodifiableList(Collections.emptyList()).getClass();

    public zzko() {
        super(null);
    }

    public /* synthetic */ zzko(zzkn zzknVar) {
        super(null);
    }

    @Override // com.google.android.gms.internal.measurement.zzkq
    public final void zza(Object obj, long j) {
        Object objUnmodifiableList;
        List list = (List) zzml.zzf(obj, j);
        if (list instanceof zzkm) {
            objUnmodifiableList = ((zzkm) list).zze();
        } else {
            if (zza.isAssignableFrom(list.getClass())) {
                return;
            }
            if ((list instanceof zzlj) && (list instanceof zzke)) {
                zzke zzkeVar = (zzke) list;
                if (zzkeVar.zzc()) {
                    zzkeVar.zzb();
                    return;
                }
                return;
            }
            objUnmodifiableList = Collections.unmodifiableList(list);
        }
        zzml.zzg.zzp(obj, j, objUnmodifiableList);
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
        List list;
        List list2;
        List listZzd;
        List list3 = (List) zzml.zzf(obj2, j);
        int size = list3.size();
        List list4 = (List) zzml.zzf(obj, j);
        if (list4.isEmpty()) {
            if (list4 instanceof zzkm) {
                listZzd = new zzkl(size);
            } else {
                listZzd = ((list4 instanceof zzlj) && (list4 instanceof zzke)) ? ((zzke) list4).zzd(size) : new ArrayList(size);
            }
            zzml.zzg.zzp(obj, j, listZzd);
            list = listZzd;
        } else {
            if (zza.isAssignableFrom(list4.getClass())) {
                ArrayList arrayList = new ArrayList(list4.size() + size);
                arrayList.addAll(list4);
                zzml.zzg.zzp(obj, j, arrayList);
                list2 = arrayList;
            } else if (list4 instanceof zzmg) {
                zzkl zzklVar = new zzkl(list4.size() + size);
                zzklVar.addAll(zzklVar.size(), (zzmg) list4);
                zzml.zzg.zzp(obj, j, zzklVar);
                list2 = zzklVar;
            } else if ((list4 instanceof zzlj) && (list4 instanceof zzke)) {
                zzke zzkeVar = (zzke) list4;
                if (!zzkeVar.zzc()) {
                    list = list4;
                    list = list4;
                    list = list4;
                    zzke<E> zzkeVarZzd = zzkeVar.zzd(list4.size() + size);
                    zzml.zzg.zzp(obj, j, zzkeVarZzd);
                    list = zzkeVarZzd;
                }
            }
            list = list2;
        }
        list = list4;
        list = list4;
        list = list4;
        list = list4;
        list = list4;
        list = list4;
        int size2 = list.size();
        int size3 = list3.size();
        if (size2 > 0 && size3 > 0) {
            list.addAll(list3);
        }
        if (size2 > 0) {
            list3 = list;
        }
        zzml.zzg.zzp(obj, j, list3);
    }
}
