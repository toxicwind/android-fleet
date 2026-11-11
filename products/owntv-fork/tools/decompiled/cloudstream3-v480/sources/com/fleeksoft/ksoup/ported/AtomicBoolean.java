package com.fleeksoft.ksoup.ported;

import kotlin.Metadata;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: AtomicBoolean.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u0003J\u0006\u0010\b\u001a\u00020\u0003R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/fleeksoft/ksoup/ported/AtomicBoolean;", "", ES6Iterator.VALUE_PROPERTY, "", "<init>", "(Z)V", "set", "", "get", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class AtomicBoolean {
    private boolean value;

    public AtomicBoolean(boolean z) {
        this.value = z;
    }

    public final void set(boolean value) {
        this.value = value;
    }

    /* JADX INFO: renamed from: get, reason: from getter */
    public final boolean getValue() {
        return this.value;
    }
}
