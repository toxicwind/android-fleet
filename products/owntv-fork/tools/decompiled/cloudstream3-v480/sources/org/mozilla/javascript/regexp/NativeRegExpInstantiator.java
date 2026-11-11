package org.mozilla.javascript.regexp;

import org.mozilla.javascript.Scriptable;

/* JADX INFO: loaded from: classes8.dex */
public class NativeRegExpInstantiator {
    private NativeRegExpInstantiator() {
    }

    static NativeRegExp withLanguageVersion(int i) {
        if (i < 200) {
            return new NativeRegExpCallable();
        }
        return new NativeRegExp();
    }

    static NativeRegExp withLanguageVersionScopeCompiled(int i, Scriptable scriptable, RECompiled rECompiled) {
        if (i < 200) {
            return new NativeRegExpCallable(scriptable, rECompiled);
        }
        return new NativeRegExp(scriptable, rECompiled);
    }
}
