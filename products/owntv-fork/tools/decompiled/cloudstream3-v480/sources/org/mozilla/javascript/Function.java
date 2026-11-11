package org.mozilla.javascript;

/* JADX INFO: loaded from: classes8.dex */
public interface Function extends Scriptable, Callable, Constructable {
    @Override // org.mozilla.javascript.Callable
    Object call(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr);

    Scriptable construct(Context context, Scriptable scriptable, Object[] objArr);
}
