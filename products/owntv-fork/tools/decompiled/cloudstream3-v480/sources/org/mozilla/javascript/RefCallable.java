package org.mozilla.javascript;

/* JADX INFO: loaded from: classes8.dex */
public interface RefCallable extends Callable {
    Ref refCall(Context context, Scriptable scriptable, Object[] objArr);
}
