package com.google.android.gms.common.api.internal;

import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.AvailabilityException;
import com.google.android.gms.common.api.HasApiKey;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class zaj {
    public int zadb;
    public final ArrayMap<ApiKey<?>, String> zacz = new ArrayMap<>();
    public final TaskCompletionSource<Map<ApiKey<?>, String>> zada = new TaskCompletionSource<>();
    public boolean zadc = false;
    public final ArrayMap<ApiKey<?>, ConnectionResult> zaba = new ArrayMap<>();

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public zaj(Iterable<? extends HasApiKey<?>> iterable) {
        Iterator<? extends HasApiKey<?>> it = iterable.iterator();
        while (it.hasNext()) {
            this.zaba.put(it.next().getApiKey(), null);
        }
        this.zadb = this.zaba.keySet().size();
    }

    public final Task<Map<ApiKey<?>, String>> getTask() {
        return this.zada.getTask();
    }

    public final void zaa(ApiKey<?> apiKey, ConnectionResult connectionResult, @Nullable String str) {
        this.zaba.put(apiKey, connectionResult);
        this.zacz.put(apiKey, str);
        this.zadb--;
        if (!connectionResult.isSuccess()) {
            this.zadc = true;
        }
        if (this.zadb == 0) {
            if (!this.zadc) {
                this.zada.setResult(this.zacz);
            } else {
                this.zada.setException(new AvailabilityException(this.zaba));
            }
        }
    }

    public final Set<ApiKey<?>> zan() {
        return this.zaba.keySet();
    }
}
