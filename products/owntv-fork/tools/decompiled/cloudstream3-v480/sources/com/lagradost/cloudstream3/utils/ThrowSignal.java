package com.lagradost.cloudstream3.utils;

import kotlin.Metadata;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: JsInterpreter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/lagradost/cloudstream3/utils/ThrowSignal;", "", ES6Iterator.VALUE_PROPERTY, "", "<init>", "(Ljava/lang/Object;)V", "getValue", "()Ljava/lang/Object;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class ThrowSignal extends Throwable {
    private final Object value;

    public ThrowSignal(Object obj) {
        this.value = obj;
    }

    public final Object getValue() {
        return this.value;
    }
}
