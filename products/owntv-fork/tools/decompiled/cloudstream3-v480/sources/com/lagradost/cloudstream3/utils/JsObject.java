package com.lagradost.cloudstream3.utils;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JsInterpreter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0002\u0018\u00002\u00020\u0001B+\u0012\u0016\b\u0002\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bR\u001f\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/lagradost/cloudstream3/utils/JsObject;", "", "props", "", "", "constructor", "Lcom/lagradost/cloudstream3/utils/JsFunction;", "<init>", "(Ljava/util/Map;Lcom/lagradost/cloudstream3/utils/JsFunction;)V", "getProps", "()Ljava/util/Map;", "getConstructor", "()Lcom/lagradost/cloudstream3/utils/JsFunction;", "setConstructor", "(Lcom/lagradost/cloudstream3/utils/JsFunction;)V", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class JsObject {
    private JsFunction constructor;
    private final Map<String, Object> props;

    /* JADX WARN: Multi-variable type inference failed */
    public JsObject() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public JsObject(Map<String, Object> props, JsFunction jsFunction) {
        Intrinsics.checkNotNullParameter(props, "props");
        this.props = props;
        this.constructor = jsFunction;
    }

    public /* synthetic */ JsObject(LinkedHashMap linkedHashMap, JsFunction jsFunction, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new LinkedHashMap() : linkedHashMap, (i & 2) != 0 ? null : jsFunction);
    }

    public final JsFunction getConstructor() {
        return this.constructor;
    }

    public final Map<String, Object> getProps() {
        return this.props;
    }

    public final void setConstructor(JsFunction jsFunction) {
        this.constructor = jsFunction;
    }
}
