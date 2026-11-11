package org.mozilla.javascript.engine;

import javax.script.Bindings;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

/* JADX INFO: loaded from: classes8.dex */
public class BindingsObject extends ScriptableObject {
    private final Bindings bindings;

    BindingsObject(Bindings bindings) {
        if (bindings == null) {
            throw new IllegalArgumentException("Bindings must not be null");
        }
        this.bindings = bindings;
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public String getClassName() {
        return "BindingsObject";
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public Object get(String str, Scriptable scriptable) {
        if (!this.bindings.containsKey(str)) {
            return Scriptable.NOT_FOUND;
        }
        return Context.jsToJava(this.bindings.get(str), Object.class);
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public void put(String str, Scriptable scriptable, Object obj) {
        this.bindings.put(str, Context.javaToJS(obj, scriptable));
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public void delete(String str) {
        this.bindings.remove(str);
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public boolean has(String str, Scriptable scriptable) {
        return this.bindings.containsKey(str);
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public Object[] getIds() {
        return this.bindings.keySet().toArray();
    }
}
