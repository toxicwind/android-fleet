package org.mozilla.javascript;

import io.ktor.http.ContentDisposition;
import java.util.Iterator;

/* JADX INFO: loaded from: classes8.dex */
public class NativeSet extends IdScriptableObject {
    static final String ITERATOR_TAG = "Set Iterator";
    private static final int Id_add = 2;
    private static final int Id_clear = 5;
    private static final int Id_constructor = 1;
    private static final int Id_delete = 3;
    private static final int Id_entries = 7;
    private static final int Id_forEach = 8;
    private static final int Id_has = 4;
    private static final int Id_keys = 6;
    private static final int Id_values = 6;
    private static final int MAX_PROTOTYPE_ID = 10;
    private static final int SymbolId_getSize = 9;
    private static final int SymbolId_toStringTag = 10;
    private static final long serialVersionUID = -8442212766987072986L;
    private final Hashtable entries = new Hashtable();
    private boolean instanceOfSet = false;
    private static final Object SET_TAG = "Set";
    static final SymbolKey GETSIZE = new SymbolKey("[Symbol.getSize]");

    static void init(Context context, Scriptable scriptable, boolean z) {
        NativeSet nativeSet = new NativeSet();
        IdFunctionObject idFunctionObjectExportAsJSClass = nativeSet.exportAsJSClass(10, scriptable, false);
        ScriptableObject scriptableObject = (ScriptableObject) context.newObject(scriptable);
        scriptableObject.put("enumerable", scriptableObject, Boolean.FALSE);
        scriptableObject.put("configurable", scriptableObject, Boolean.TRUE);
        scriptableObject.put("get", scriptableObject, nativeSet.get(GETSIZE, nativeSet));
        nativeSet.defineOwnProperty(context, ContentDisposition.Parameters.Size, scriptableObject);
        ScriptRuntimeES6.addSymbolSpecies(context, scriptable, idFunctionObjectExportAsJSClass);
        if (z) {
            nativeSet.sealObject();
        }
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public String getClassName() {
        return "Set";
    }

    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.IdFunctionCall
    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (!idFunctionObject.hasTag(SET_TAG)) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        switch (idFunctionObject.methodId()) {
            case 1:
                if (scriptable2 != null) {
                    throw ScriptRuntime.typeErrorById("msg.no.new", "Set");
                }
                NativeSet nativeSet = new NativeSet();
                nativeSet.instanceOfSet = true;
                if (objArr.length > 0) {
                    loadFromIterable(context, scriptable, nativeSet, NativeMap.key(objArr));
                }
                return nativeSet;
            case 2:
                return realThis(scriptable2, idFunctionObject).js_add(NativeMap.key(objArr));
            case 3:
                return realThis(scriptable2, idFunctionObject).js_delete(NativeMap.key(objArr));
            case 4:
                return realThis(scriptable2, idFunctionObject).js_has(NativeMap.key(objArr));
            case 5:
                return realThis(scriptable2, idFunctionObject).js_clear();
            case 6:
                return realThis(scriptable2, idFunctionObject).js_iterator(scriptable, NativeCollectionIterator.Type.VALUES);
            case 7:
                return realThis(scriptable2, idFunctionObject).js_iterator(scriptable, NativeCollectionIterator.Type.BOTH);
            case 8:
                return realThis(scriptable2, idFunctionObject).js_forEach(context, scriptable, NativeMap.key(objArr), objArr.length > 1 ? objArr[1] : Undefined.instance);
            case 9:
                return realThis(scriptable2, idFunctionObject).js_getSize();
            default:
                throw new IllegalArgumentException("Set.prototype has no method: " + idFunctionObject.getFunctionName());
        }
    }

    private Object js_add(Object obj) {
        if ((obj instanceof Number) && ((Number) obj).doubleValue() == ScriptRuntime.negativeZero) {
            obj = ScriptRuntime.zeroObj;
        }
        this.entries.put(obj, obj);
        return this;
    }

    private Object js_delete(Object obj) {
        return Boolean.valueOf(this.entries.deleteEntry(obj));
    }

    private Object js_has(Object obj) {
        return Boolean.valueOf(this.entries.has(obj));
    }

    private Object js_clear() {
        this.entries.clear();
        return Undefined.instance;
    }

    private Object js_getSize() {
        return Integer.valueOf(this.entries.size());
    }

    private Object js_iterator(Scriptable scriptable, NativeCollectionIterator.Type type) {
        return new NativeCollectionIterator(scriptable, ITERATOR_TAG, type, this.entries.iterator());
    }

    private Object js_forEach(Context context, Scriptable scriptable, Object obj, Object obj2) {
        if (!(obj instanceof Callable)) {
            throw ScriptRuntime.notFunctionError(obj);
        }
        Callable callable = (Callable) obj;
        boolean zIsStrictMode = context.isStrictMode();
        for (Hashtable.Entry entry : this.entries) {
            Scriptable objectOrNull = ScriptRuntime.toObjectOrNull(context, obj2, scriptable);
            if (objectOrNull == null && !zIsStrictMode) {
                objectOrNull = scriptable;
            }
            if (objectOrNull == null) {
                objectOrNull = Undefined.SCRIPTABLE_UNDEFINED;
            }
            callable.call(context, scriptable, objectOrNull, new Object[]{entry.value, entry.value, this});
        }
        return Undefined.instance;
    }

    static void loadFromIterable(Context context, Scriptable scriptable, ScriptableObject scriptableObject, Object obj) {
        if (obj == null || Undefined.instance.equals(obj)) {
            return;
        }
        Object objCallIterator = ScriptRuntime.callIterator(obj, context, scriptable);
        if (Undefined.instance.equals(objCallIterator)) {
            return;
        }
        Callable propFunctionAndThis = ScriptRuntime.getPropFunctionAndThis(ensureScriptableObject(context.newObject(scriptable, scriptableObject.getClassName())).getPrototype(), "add", context, scriptable);
        ScriptRuntime.lastStoredScriptable(context);
        IteratorLikeIterable iteratorLikeIterable = new IteratorLikeIterable(context, scriptable, objCallIterator);
        try {
            Iterator<Object> it = iteratorLikeIterable.iterator();
            while (it.hasNext()) {
                Object next = it.next();
                if (next == Scriptable.NOT_FOUND) {
                    next = Undefined.instance;
                }
                propFunctionAndThis.call(context, scriptable, scriptableObject, new Object[]{next});
            }
            iteratorLikeIterable.close();
        } catch (Throwable th) {
            try {
                iteratorLikeIterable.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    private static NativeSet realThis(Scriptable scriptable, IdFunctionObject idFunctionObject) {
        NativeSet nativeSet = (NativeSet) ensureType(scriptable, NativeSet.class, idFunctionObject);
        if (nativeSet.instanceOfSet) {
            return nativeSet;
        }
        throw ScriptRuntime.typeErrorById("msg.incompat.call", idFunctionObject.getFunctionName());
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected void initPrototypeId(int i) {
        String str;
        String str2;
        String str3;
        int i2;
        if (i == 9) {
            initPrototypeMethod(SET_TAG, i, GETSIZE, "get size", 0);
            return;
        }
        if (i == 10) {
            initPrototypeValue(10, SymbolKey.TO_STRING_TAG, getClassName(), 3);
            return;
        }
        switch (i) {
            case 1:
                str = "constructor";
                str3 = str;
                i2 = 0;
                initPrototypeMethod(SET_TAG, i, str3, (String) null, i2);
                return;
            case 2:
                str2 = "add";
                i2 = 1;
                str3 = str2;
                initPrototypeMethod(SET_TAG, i, str3, (String) null, i2);
                return;
            case 3:
                str2 = "delete";
                i2 = 1;
                str3 = str2;
                initPrototypeMethod(SET_TAG, i, str3, (String) null, i2);
                return;
            case 4:
                str2 = "has";
                i2 = 1;
                str3 = str2;
                initPrototypeMethod(SET_TAG, i, str3, (String) null, i2);
                return;
            case 5:
                str = "clear";
                str3 = str;
                i2 = 0;
                initPrototypeMethod(SET_TAG, i, str3, (String) null, i2);
                return;
            case 6:
                str = "values";
                str3 = str;
                i2 = 0;
                initPrototypeMethod(SET_TAG, i, str3, (String) null, i2);
                return;
            case 7:
                str = "entries";
                str3 = str;
                i2 = 0;
                initPrototypeMethod(SET_TAG, i, str3, (String) null, i2);
                return;
            case 8:
                str2 = "forEach";
                i2 = 1;
                str3 = str2;
                initPrototypeMethod(SET_TAG, i, str3, (String) null, i2);
                return;
            default:
                throw new IllegalArgumentException(String.valueOf(i));
        }
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected int findPrototypeId(Symbol symbol) {
        if (GETSIZE.equals(symbol)) {
            return 9;
        }
        if (SymbolKey.ITERATOR.equals(symbol)) {
            return 6;
        }
        return SymbolKey.TO_STRING_TAG.equals(symbol) ? 10 : 0;
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected int findPrototypeId(String str) {
        str.hashCode();
        switch (str) {
            case "entries":
                return 7;
            case "constructor":
                return 1;
            case "delete":
                return 3;
            case "values":
                return 6;
            case "forEach":
                return 8;
            case "add":
                return 2;
            case "has":
                return 4;
            case "keys":
                return 6;
            case "clear":
                return 5;
            default:
                return 0;
        }
    }
}
