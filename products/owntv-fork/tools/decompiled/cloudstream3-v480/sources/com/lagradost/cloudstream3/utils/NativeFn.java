package com.lagradost.cloudstream3.utils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JsInterpreter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010%\n\u0002\b\n\b\u0002\u0018\u00002\u00020\u0001BE\u0012\u001c\u0010\u0002\u001a\u0018\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0016\b\u0002\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\b¢\u0006\u0004\b\t\u0010\nJ\n\u0010\u0011\u001a\u00020\u0006H\u0096\u0080\u0004R'\u0010\u0002\u001a\u0018\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001f\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0012"}, d2 = {"Lcom/lagradost/cloudstream3/utils/NativeFn;", "", "fn", "Lkotlin/Function1;", "", "name", "", "props", "", "<init>", "(Lkotlin/jvm/functions/Function1;Ljava/lang/String;Ljava/util/Map;)V", "getFn", "()Lkotlin/jvm/functions/Function1;", "getName", "()Ljava/lang/String;", "getProps", "()Ljava/util/Map;", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class NativeFn {
    private final Function1<List<? extends Object>, Object> fn;
    private final String name;
    private final Map<String, Object> props;

    /* JADX WARN: Multi-variable type inference failed */
    public NativeFn(Function1<? super List<? extends Object>, ? extends Object> fn, String name, Map<String, Object> props) {
        Intrinsics.checkNotNullParameter(fn, "fn");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(props, "props");
        this.fn = fn;
        this.name = name;
        this.props = props;
    }

    public /* synthetic */ NativeFn(Function1 function1, String str, LinkedHashMap linkedHashMap, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(function1, str, (i & 4) != 0 ? new LinkedHashMap() : linkedHashMap);
    }

    public final Function1<List<? extends Object>, Object> getFn() {
        return this.fn;
    }

    public final String getName() {
        return this.name;
    }

    public final Map<String, Object> getProps() {
        return this.props;
    }

    public String toString() {
        return "function " + this.name + "() { [native code] }";
    }
}
