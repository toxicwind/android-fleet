package org.mozilla.javascript;

/* JADX INFO: loaded from: classes8.dex */
public interface SymbolScriptable {
    void delete(Symbol symbol);

    Object get(Symbol symbol, Scriptable scriptable);

    boolean has(Symbol symbol, Scriptable scriptable);

    void put(Symbol symbol, Scriptable scriptable, Object obj);
}
