package org.mozilla.javascript;

import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes8.dex */
final class NativeReflect extends ScriptableObject {
    private static final String REFLECT_TAG = "Reflect";
    private static final long serialVersionUID = 2920773905356325445L;

    public static void init(Context context, Scriptable scriptable, boolean z) {
        NativeReflect nativeReflect = new NativeReflect();
        nativeReflect.setPrototype(getObjectPrototype(scriptable));
        nativeReflect.setParentScope(scriptable);
        nativeReflect.defineProperty(scriptable, "apply", 3, new Callable() { // from class: org.mozilla.javascript.NativeReflect$$ExternalSyntheticLambda0
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeReflect.apply(context2, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        nativeReflect.defineProperty(scriptable, "construct", 2, new Callable() { // from class: org.mozilla.javascript.NativeReflect$$ExternalSyntheticLambda7
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeReflect.construct(context2, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        nativeReflect.defineProperty(scriptable, "defineProperty", 3, new Callable() { // from class: org.mozilla.javascript.NativeReflect$$ExternalSyntheticLambda8
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeReflect.defineProperty(context2, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        nativeReflect.defineProperty(scriptable, "deleteProperty", 2, new Callable() { // from class: org.mozilla.javascript.NativeReflect$$ExternalSyntheticLambda9
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeReflect.deleteProperty(context2, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        nativeReflect.defineProperty(scriptable, "get", 2, new Callable() { // from class: org.mozilla.javascript.NativeReflect$$ExternalSyntheticLambda10
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeReflect.get(context2, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        nativeReflect.defineProperty(scriptable, "getOwnPropertyDescriptor", 2, new Callable() { // from class: org.mozilla.javascript.NativeReflect$$ExternalSyntheticLambda11
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeReflect.getOwnPropertyDescriptor(context2, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        nativeReflect.defineProperty(scriptable, "getPrototypeOf", 1, new Callable() { // from class: org.mozilla.javascript.NativeReflect$$ExternalSyntheticLambda12
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeReflect.getPrototypeOf(context2, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        nativeReflect.defineProperty(scriptable, "has", 2, new Callable() { // from class: org.mozilla.javascript.NativeReflect$$ExternalSyntheticLambda1
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeReflect.has(context2, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        nativeReflect.defineProperty(scriptable, "isExtensible", 1, new Callable() { // from class: org.mozilla.javascript.NativeReflect$$ExternalSyntheticLambda2
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeReflect.isExtensible(context2, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        nativeReflect.defineProperty(scriptable, "ownKeys", 1, new Callable() { // from class: org.mozilla.javascript.NativeReflect$$ExternalSyntheticLambda3
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeReflect.ownKeys(context2, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        nativeReflect.defineProperty(scriptable, "preventExtensions", 1, new Callable() { // from class: org.mozilla.javascript.NativeReflect$$ExternalSyntheticLambda4
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeReflect.preventExtensions(context2, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        nativeReflect.defineProperty(scriptable, "set", 3, new Callable() { // from class: org.mozilla.javascript.NativeReflect$$ExternalSyntheticLambda5
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeReflect.set(context2, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        nativeReflect.defineProperty(scriptable, "setPrototypeOf", 2, new Callable() { // from class: org.mozilla.javascript.NativeReflect$$ExternalSyntheticLambda6
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeReflect.setPrototypeOf(context2, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        nativeReflect.defineProperty(SymbolKey.TO_STRING_TAG, REFLECT_TAG, 3);
        ScriptableObject.defineProperty(scriptable, REFLECT_TAG, nativeReflect, 2);
        if (z) {
            nativeReflect.sealObject();
        }
    }

    private NativeReflect() {
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public String getClassName() {
        return REFLECT_TAG;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object apply(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (objArr.length < 3) {
            throw ScriptRuntime.typeErrorById("msg.method.missing.parameter", "Reflect.apply", "3", Integer.toString(objArr.length));
        }
        Scriptable scriptableEnsureScriptable = ScriptableObject.ensureScriptable(objArr[0]);
        Object obj = objArr[1];
        if (obj instanceof Scriptable) {
            scriptable2 = (Scriptable) obj;
        } else if (ScriptRuntime.isPrimitive(obj)) {
            scriptable2 = context.newObject(scriptable, "Object", new Object[]{objArr[1]});
        }
        if (ScriptRuntime.isSymbol(objArr[2])) {
            throw ScriptRuntime.typeErrorById("msg.arg.not.object", ScriptRuntime.typeof(objArr[2]));
        }
        return ScriptRuntime.applyOrCall(true, context, scriptable, scriptableEnsureScriptable, new Object[]{scriptable2, ScriptableObject.ensureScriptableObject(objArr[2])});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Scriptable construct(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        BaseFunction baseFunction;
        Scriptable scriptableCreateObject;
        Object prototypeProperty;
        if (objArr.length < 1) {
            throw ScriptRuntime.typeErrorById("msg.method.missing.parameter", "Reflect.construct", "3", Integer.toString(objArr.length));
        }
        if (!AbstractEcmaObjectOperations.isConstructor(context, objArr[0])) {
            throw ScriptRuntime.typeErrorById("msg.not.ctor", ScriptRuntime.typeof(objArr[0]));
        }
        Constructable constructable = (Constructable) objArr[0];
        if (objArr.length < 2) {
            return constructable.construct(context, scriptable, ScriptRuntime.emptyArgs);
        }
        if (objArr.length > 2 && !AbstractEcmaObjectOperations.isConstructor(context, objArr[2])) {
            throw ScriptRuntime.typeErrorById("msg.not.ctor", ScriptRuntime.typeof(objArr[2]));
        }
        Object[] applyArguments = ScriptRuntime.getApplyArguments(context, objArr[1]);
        Object obj = null;
        if (objArr.length > 2) {
            Scriptable scriptableEnsureScriptable = ScriptableObject.ensureScriptable(objArr[2]);
            if (scriptableEnsureScriptable instanceof BaseFunction) {
                prototypeProperty = ((BaseFunction) scriptableEnsureScriptable).getPrototypeProperty();
            } else {
                prototypeProperty = scriptableEnsureScriptable.get("prototype", scriptableEnsureScriptable);
            }
            if ((prototypeProperty instanceof Scriptable) && !ScriptRuntime.isSymbol(prototypeProperty) && !Undefined.isUndefined(prototypeProperty)) {
                obj = prototypeProperty;
            }
        }
        if ((constructable instanceof BaseFunction) && obj != null && (scriptableCreateObject = (baseFunction = (BaseFunction) constructable).createObject(context, scriptable)) != null) {
            scriptableCreateObject.setPrototype((Scriptable) obj);
            Object objCall = baseFunction.call(context, scriptable, scriptableCreateObject, applyArguments);
            return objCall instanceof Scriptable ? (Scriptable) objCall : scriptableCreateObject;
        }
        Scriptable scriptableConstruct = constructable.construct(context, scriptable, applyArguments);
        if (obj != null) {
            scriptableConstruct.setPrototype((Scriptable) obj);
        }
        return scriptableConstruct;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object defineProperty(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (objArr.length < 3) {
            throw ScriptRuntime.typeErrorById("msg.method.missing.parameter", "Reflect.defineProperty", "3", Integer.toString(objArr.length));
        }
        try {
            return Boolean.valueOf(checkTarget(objArr).defineOwnProperty(context, objArr[1], ScriptableObject.ensureScriptableObject(objArr[2])));
        } catch (EcmaError unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object deleteProperty(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        ScriptableObject scriptableObjectCheckTarget = checkTarget(objArr);
        if (objArr.length > 1) {
            if (ScriptRuntime.isSymbol(objArr[1])) {
                return Boolean.valueOf(ScriptableObject.deleteProperty(scriptableObjectCheckTarget, (Symbol) objArr[1]));
            }
            return Boolean.valueOf(ScriptableObject.deleteProperty(scriptableObjectCheckTarget, ScriptRuntime.toString(objArr[1])));
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object get(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        ScriptableObject scriptableObjectCheckTarget = checkTarget(objArr);
        if (objArr.length > 1) {
            if (ScriptRuntime.isSymbol(objArr[1])) {
                Object property = ScriptableObject.getProperty(scriptableObjectCheckTarget, (Symbol) objArr[1]);
                return property == Scriptable.NOT_FOUND ? Undefined.SCRIPTABLE_UNDEFINED : property;
            }
            Object obj = objArr[1];
            if (obj instanceof Number) {
                Object property2 = ScriptableObject.getProperty(scriptableObjectCheckTarget, ScriptRuntime.toIndex(obj));
                return property2 == Scriptable.NOT_FOUND ? Undefined.SCRIPTABLE_UNDEFINED : property2;
            }
            Object property3 = ScriptableObject.getProperty(scriptableObjectCheckTarget, ScriptRuntime.toString(obj));
            return property3 == Scriptable.NOT_FOUND ? Undefined.SCRIPTABLE_UNDEFINED : property3;
        }
        return Undefined.SCRIPTABLE_UNDEFINED;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Scriptable getOwnPropertyDescriptor(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        ScriptableObject scriptableObjectCheckTarget = checkTarget(objArr);
        if (objArr.length > 1) {
            if (ScriptRuntime.isSymbol(objArr[1])) {
                ScriptableObject ownPropertyDescriptor = scriptableObjectCheckTarget.getOwnPropertyDescriptor(context, objArr[1]);
                return ownPropertyDescriptor == null ? Undefined.SCRIPTABLE_UNDEFINED : ownPropertyDescriptor;
            }
            ScriptableObject ownPropertyDescriptor2 = scriptableObjectCheckTarget.getOwnPropertyDescriptor(context, ScriptRuntime.toString(objArr[1]));
            return ownPropertyDescriptor2 == null ? Undefined.SCRIPTABLE_UNDEFINED : ownPropertyDescriptor2;
        }
        return Undefined.SCRIPTABLE_UNDEFINED;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Scriptable getPrototypeOf(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        return checkTarget(objArr).getPrototype();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object has(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        ScriptableObject scriptableObjectCheckTarget = checkTarget(objArr);
        if (objArr.length > 1) {
            if (ScriptRuntime.isSymbol(objArr[1])) {
                return Boolean.valueOf(ScriptableObject.hasProperty(scriptableObjectCheckTarget, (Symbol) objArr[1]));
            }
            return Boolean.valueOf(ScriptableObject.hasProperty(scriptableObjectCheckTarget, ScriptRuntime.toString(objArr[1])));
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object isExtensible(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        return Boolean.valueOf(checkTarget(objArr).isExtensible());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Scriptable ownKeys(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        ScriptableObject scriptableObjectCheckTarget = checkTarget(objArr);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : scriptableObjectCheckTarget.getIds(true, true)) {
            if (obj instanceof Symbol) {
                arrayList2.add(obj);
            } else {
                arrayList.add(ScriptRuntime.toString(obj));
            }
        }
        Object[] objArr2 = new Object[arrayList.size() + arrayList2.size()];
        System.arraycopy(arrayList.toArray(), 0, objArr2, 0, arrayList.size());
        System.arraycopy(arrayList2.toArray(), 0, objArr2, arrayList.size(), arrayList2.size());
        return context.newArray(scriptable, objArr2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object preventExtensions(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        return Boolean.valueOf(checkTarget(objArr).preventExtensions());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object set(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        ScriptableObject scriptableObject;
        ScriptableObject ownPropertyDescriptor;
        ScriptableObject scriptableObjectEnsureScriptableObject;
        ScriptableObject scriptableObjectCheckTarget = checkTarget(objArr);
        if (objArr.length < 2) {
            return true;
        }
        if (objArr.length > 3) {
            scriptableObjectEnsureScriptableObject = ScriptableObject.ensureScriptableObject(objArr[3]);
        } else {
            scriptableObject = scriptableObjectCheckTarget;
        }
        if (scriptableObject != scriptableObjectCheckTarget && (ownPropertyDescriptor = scriptableObjectCheckTarget.getOwnPropertyDescriptor(context, objArr[1])) != null) {
            Object obj = ownPropertyDescriptor.get("set");
            if (obj != null && obj != NOT_FOUND) {
                scriptableObject = scriptableObjectEnsureScriptableObject;
                ((Function) obj).call(context, scriptable, scriptableObject, new Object[]{objArr[2]});
                return true;
            }
            scriptableObject = scriptableObjectEnsureScriptableObject;
            scriptableObject = scriptableObjectEnsureScriptableObject;
            if (Boolean.FALSE.equals(ownPropertyDescriptor.get("configurable"))) {
                return false;
            }
        }
        scriptableObject = scriptableObjectEnsureScriptableObject;
        scriptableObject = scriptableObjectEnsureScriptableObject;
        if (ScriptRuntime.isSymbol(objArr[1])) {
            scriptableObject.put((Symbol) objArr[1], scriptableObject, objArr[2]);
        } else {
            Object obj2 = objArr[1];
            if (obj2 instanceof Double) {
                scriptableObject.put(ScriptRuntime.toIndex(obj2), scriptableObject, objArr[2]);
            } else {
                scriptableObject.put(ScriptRuntime.toString(obj2), scriptableObject, objArr[2]);
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object setPrototypeOf(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (objArr.length < 2) {
            throw ScriptRuntime.typeErrorById("msg.method.missing.parameter", "Reflect.js_setPrototypeOf", ExifInterface.GPS_MEASUREMENT_2D, Integer.toString(objArr.length));
        }
        ScriptableObject scriptableObjectCheckTarget = checkTarget(objArr);
        if (scriptableObjectCheckTarget.getPrototype() == objArr[1]) {
            return true;
        }
        if (!scriptableObjectCheckTarget.isExtensible()) {
            return false;
        }
        Object obj = objArr[1];
        if (obj == null) {
            scriptableObjectCheckTarget.setPrototype(null);
            return true;
        }
        if (ScriptRuntime.isSymbol(obj)) {
            throw ScriptRuntime.typeErrorById("msg.arg.not.object", ScriptRuntime.typeof(objArr[0]));
        }
        ScriptableObject scriptableObjectEnsureScriptableObject = ScriptableObject.ensureScriptableObject(objArr[1]);
        if (scriptableObjectCheckTarget.getPrototype() == scriptableObjectEnsureScriptableObject) {
            return true;
        }
        for (Scriptable prototype = scriptableObjectEnsureScriptableObject; prototype != null; prototype = prototype.getPrototype()) {
            if (scriptableObjectCheckTarget == prototype) {
                return false;
            }
        }
        scriptableObjectCheckTarget.setPrototype(scriptableObjectEnsureScriptableObject);
        return true;
    }

    private static ScriptableObject checkTarget(Object[] objArr) {
        Object obj;
        if (objArr.length == 0 || (obj = objArr[0]) == null || obj == Undefined.instance) {
            throw ScriptRuntime.typeErrorById("msg.no.properties", ScriptRuntime.toString(objArr.length == 0 ? Undefined.instance : objArr[0]));
        }
        if (ScriptRuntime.isSymbol(objArr[0])) {
            throw ScriptRuntime.typeErrorById("msg.arg.not.object", ScriptRuntime.typeof(objArr[0]));
        }
        return ScriptableObject.ensureScriptableObject(objArr[0]);
    }
}
