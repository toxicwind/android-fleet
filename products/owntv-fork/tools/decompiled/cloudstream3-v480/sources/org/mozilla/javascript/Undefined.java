package org.mozilla.javascript;

import java.io.Serializable;

/* JADX INFO: loaded from: classes8.dex */
public class Undefined implements Serializable {
    public static final Scriptable SCRIPTABLE_UNDEFINED;
    public static final Object instance;
    private static final int instanceHash;
    private static final long serialVersionUID = 9195680630202616767L;

    static {
        Undefined undefined = new Undefined();
        instance = undefined;
        instanceHash = System.identityHashCode(undefined);
        SCRIPTABLE_UNDEFINED = new Scriptable() { // from class: org.mozilla.javascript.Undefined.1
            @Override // org.mozilla.javascript.Scriptable
            public void delete(int i) {
            }

            @Override // org.mozilla.javascript.Scriptable
            public void delete(String str) {
            }

            @Override // org.mozilla.javascript.Scriptable
            public Scriptable getParentScope() {
                return null;
            }

            @Override // org.mozilla.javascript.Scriptable
            public Scriptable getPrototype() {
                return null;
            }

            @Override // org.mozilla.javascript.Scriptable
            public boolean has(int i, Scriptable scriptable) {
                return false;
            }

            @Override // org.mozilla.javascript.Scriptable
            public boolean has(String str, Scriptable scriptable) {
                return false;
            }

            @Override // org.mozilla.javascript.Scriptable
            public boolean hasInstance(Scriptable scriptable) {
                return false;
            }

            @Override // org.mozilla.javascript.Scriptable
            public void put(int i, Scriptable scriptable, Object obj) {
            }

            @Override // org.mozilla.javascript.Scriptable
            public void put(String str, Scriptable scriptable, Object obj) {
            }

            @Override // org.mozilla.javascript.Scriptable
            public void setParentScope(Scriptable scriptable) {
            }

            @Override // org.mozilla.javascript.Scriptable
            public void setPrototype(Scriptable scriptable) {
            }

            @Override // org.mozilla.javascript.Scriptable
            public String getClassName() {
                return "undefined";
            }

            @Override // org.mozilla.javascript.Scriptable
            public Object get(String str, Scriptable scriptable) {
                return NOT_FOUND;
            }

            @Override // org.mozilla.javascript.Scriptable
            public Object get(int i, Scriptable scriptable) {
                return NOT_FOUND;
            }

            @Override // org.mozilla.javascript.Scriptable
            public Object[] getIds() {
                return ScriptRuntime.emptyArgs;
            }

            @Override // org.mozilla.javascript.Scriptable
            public Object getDefaultValue(Class<?> cls) {
                if (cls == null || cls == ScriptRuntime.StringClass) {
                    return toString();
                }
                return null;
            }

            public String toString() {
                return "undefined";
            }

            public boolean equals(Object obj) {
                return Undefined.isUndefined(obj) || obj == this;
            }

            public int hashCode() {
                return Undefined.instanceHash;
            }
        };
    }

    private Undefined() {
    }

    public Object readResolve() {
        return instance;
    }

    public boolean equals(Object obj) {
        return isUndefined(obj) || obj == this;
    }

    public int hashCode() {
        return instanceHash;
    }

    public static boolean isUndefined(Object obj) {
        return instance == obj || SCRIPTABLE_UNDEFINED == obj;
    }
}
