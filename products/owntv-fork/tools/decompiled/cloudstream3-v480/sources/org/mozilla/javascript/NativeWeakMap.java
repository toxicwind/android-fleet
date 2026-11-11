package org.mozilla.javascript;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes8.dex */
public class NativeWeakMap extends IdScriptableObject {
    private static final int Id_constructor = 1;
    private static final int Id_delete = 2;
    private static final int Id_get = 3;
    private static final int Id_has = 4;
    private static final int Id_set = 5;
    private static final int MAX_PROTOTYPE_ID = 6;
    private static final int SymbolId_toStringTag = 6;
    private static final long serialVersionUID = 8670434366883930453L;
    private boolean instanceOfWeakMap = false;
    private transient WeakHashMap<Scriptable, Object> map = new WeakHashMap<>();
    private static final Object MAP_TAG = "WeakMap";
    private static final Object NULL_VALUE = new Object();

    static void init(Scriptable scriptable, boolean z) {
        new NativeWeakMap().exportAsJSClass(6, scriptable, z);
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public String getClassName() {
        return "WeakMap";
    }

    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.IdFunctionCall
    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (!idFunctionObject.hasTag(MAP_TAG)) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int iMethodId = idFunctionObject.methodId();
        if (iMethodId == 1) {
            if (scriptable2 != null) {
                throw ScriptRuntime.typeErrorById("msg.no.new", "WeakMap");
            }
            NativeWeakMap nativeWeakMap = new NativeWeakMap();
            nativeWeakMap.instanceOfWeakMap = true;
            if (objArr.length > 0) {
                NativeMap.loadFromIterable(context, scriptable, nativeWeakMap, NativeMap.key(objArr));
            }
            return nativeWeakMap;
        }
        if (iMethodId == 2) {
            return realThis(scriptable2, idFunctionObject).js_delete(NativeMap.key(objArr));
        }
        if (iMethodId == 3) {
            return realThis(scriptable2, idFunctionObject).js_get(NativeMap.key(objArr));
        }
        if (iMethodId == 4) {
            return realThis(scriptable2, idFunctionObject).js_has(NativeMap.key(objArr));
        }
        if (iMethodId == 5) {
            return realThis(scriptable2, idFunctionObject).js_set(NativeMap.key(objArr), objArr.length > 1 ? objArr[1] : Undefined.instance);
        }
        throw new IllegalArgumentException("WeakMap.prototype has no method: " + idFunctionObject.getFunctionName());
    }

    private Object js_delete(Object obj) {
        if (ScriptRuntime.isObject(obj)) {
            return Boolean.valueOf(this.map.remove(obj) != null);
        }
        return Boolean.FALSE;
    }

    private Object js_get(Object obj) {
        if (!ScriptRuntime.isObject(obj)) {
            return Undefined.instance;
        }
        Object obj2 = this.map.get(obj);
        if (obj2 == null) {
            return Undefined.instance;
        }
        if (obj2 == NULL_VALUE) {
            return null;
        }
        return obj2;
    }

    private Object js_has(Object obj) {
        if (!ScriptRuntime.isObject(obj)) {
            return Boolean.FALSE;
        }
        return Boolean.valueOf(this.map.containsKey(obj));
    }

    private Object js_set(Object obj, Object obj2) {
        if (!ScriptRuntime.isObject(obj)) {
            throw ScriptRuntime.typeErrorById("msg.arg.not.object", ScriptRuntime.typeof(obj));
        }
        if (obj2 == null) {
            obj2 = NULL_VALUE;
        }
        this.map.put((Scriptable) obj, obj2);
        return this;
    }

    private static NativeWeakMap realThis(Scriptable scriptable, IdFunctionObject idFunctionObject) {
        NativeWeakMap nativeWeakMap = (NativeWeakMap) ensureType(scriptable, NativeWeakMap.class, idFunctionObject);
        if (nativeWeakMap.instanceOfWeakMap) {
            return nativeWeakMap;
        }
        throw ScriptRuntime.typeErrorById("msg.incompat.call", idFunctionObject.getFunctionName());
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected void initPrototypeId(int i) {
        String str;
        String str2;
        int i2;
        if (i == 6) {
            initPrototypeValue(6, SymbolKey.TO_STRING_TAG, getClassName(), 3);
            return;
        }
        int i3 = 1;
        if (i == 1) {
            i3 = 0;
            str = "constructor";
        } else if (i == 2) {
            str = "delete";
        } else if (i == 3) {
            str = "get";
        } else {
            if (i == 4) {
                str = "has";
            } else if (i == 5) {
                str2 = "set";
                i2 = 2;
            } else {
                throw new IllegalArgumentException(String.valueOf(i));
            }
            initPrototypeMethod(MAP_TAG, i, str2, (String) null, i2);
        }
        str2 = str;
        i2 = i3;
        initPrototypeMethod(MAP_TAG, i, str2, (String) null, i2);
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected int findPrototypeId(Symbol symbol) {
        return SymbolKey.TO_STRING_TAG.equals(symbol) ? 6 : 0;
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected int findPrototypeId(String str) {
        str.hashCode();
        switch (str) {
            case "constructor":
                return 1;
            case "delete":
                return 2;
            case "get":
                return 3;
            case "has":
                return 4;
            case "set":
                return 5;
            default:
                return 0;
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        this.map = new WeakHashMap<>();
    }
}
