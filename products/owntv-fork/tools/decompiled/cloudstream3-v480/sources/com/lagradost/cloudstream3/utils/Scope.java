package com.lagradost.cloudstream3.utils;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: JsInterpreter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\u0013\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0000¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\f\u001a\u0004\u0018\u00010\u00012\u0006\u0010\r\u001a\u00020\tJ\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\r\u001a\u00020\t2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001J\u0018\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\r\u001a\u00020\t2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u00002\u0006\u0010\r\u001a\u00020\tH\u0002R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0000¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001f\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0006\u0012\u0004\u0018\u00010\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0013"}, d2 = {"Lcom/lagradost/cloudstream3/utils/Scope;", "", "parent", "<init>", "(Lcom/lagradost/cloudstream3/utils/Scope;)V", "getParent", "()Lcom/lagradost/cloudstream3/utils/Scope;", "vars", "", "", "getVars", "()Ljava/util/Map;", "get", "name", "set", "", ES6Iterator.VALUE_PROPERTY, "define", "findOwner", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class Scope {
    private final Scope parent;
    private final Map<String, Object> vars;

    /* JADX WARN: Multi-variable type inference failed */
    public Scope() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public Scope(Scope scope) {
        this.parent = scope;
        this.vars = new LinkedHashMap();
    }

    public /* synthetic */ Scope(Scope scope, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : scope);
    }

    public final Scope getParent() {
        return this.parent;
    }

    public final Map<String, Object> getVars() {
        return this.vars;
    }

    public final Object get(String name) {
        Object obj;
        Intrinsics.checkNotNullParameter(name, "name");
        if (this.vars.containsKey(name)) {
            return this.vars.get(name);
        }
        Scope scope = this.parent;
        return (scope == null || (obj = scope.get(name)) == null) ? Unit.INSTANCE : obj;
    }

    public final void set(String name, Object value) {
        Intrinsics.checkNotNullParameter(name, "name");
        Scope scopeFindOwner = findOwner(name);
        (scopeFindOwner != null ? scopeFindOwner.vars : this.vars).put(name, value);
    }

    public final void define(String name, Object value) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.vars.put(name, value);
    }

    private final Scope findOwner(String name) {
        if (this.vars.containsKey(name)) {
            return this;
        }
        Scope scope = this.parent;
        if (scope != null) {
            return scope.findOwner(name);
        }
        return null;
    }
}
