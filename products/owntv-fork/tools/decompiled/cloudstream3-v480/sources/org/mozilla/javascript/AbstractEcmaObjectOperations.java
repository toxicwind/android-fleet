package org.mozilla.javascript;

import j$.util.Objects;
import j$.util.function.Function$CC;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes8.dex */
public class AbstractEcmaObjectOperations {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    enum INTEGRITY_LEVEL {
        FROZEN,
        SEALED
    }

    enum KEY_COERCION {
        PROPERTY,
        COLLECTION
    }

    static boolean hasOwnProperty(Context context, Object obj, Object obj2) {
        Scriptable scriptableEnsureScriptable = ScriptableObject.ensureScriptable(obj);
        if (obj2 instanceof Symbol) {
            return ScriptableObject.ensureSymbolScriptable(obj).has((Symbol) obj2, scriptableEnsureScriptable);
        }
        ScriptRuntime.StringIdOrIndex stringIdOrIndex = ScriptRuntime.toStringIdOrIndex(obj2);
        if (stringIdOrIndex.stringId == null) {
            return scriptableEnsureScriptable.has(stringIdOrIndex.index, scriptableEnsureScriptable);
        }
        return scriptableEnsureScriptable.has(stringIdOrIndex.stringId, scriptableEnsureScriptable);
    }

    static boolean testIntegrityLevel(Context context, Object obj, INTEGRITY_LEVEL integrity_level) {
        ScriptableObject scriptableObjectEnsureScriptableObject = ScriptableObject.ensureScriptableObject(obj);
        if (scriptableObjectEnsureScriptableObject.isExtensible()) {
            return false;
        }
        for (Object obj2 : scriptableObjectEnsureScriptableObject.getIds(true, true)) {
            ScriptableObject ownPropertyDescriptor = scriptableObjectEnsureScriptableObject.getOwnPropertyDescriptor(context, obj2);
            if (Boolean.TRUE.equals(ownPropertyDescriptor.get("configurable"))) {
                return false;
            }
            if (integrity_level == INTEGRITY_LEVEL.FROZEN && ScriptableObject.isDataDescriptor(ownPropertyDescriptor) && Boolean.TRUE.equals(ownPropertyDescriptor.get("writable"))) {
                return false;
            }
        }
        return true;
    }

    static boolean setIntegrityLevel(Context context, Object obj, INTEGRITY_LEVEL integrity_level) {
        ScriptableObject scriptableObjectEnsureScriptableObject = ScriptableObject.ensureScriptableObject(obj);
        if (!scriptableObjectEnsureScriptableObject.preventExtensions()) {
            return false;
        }
        for (Object obj2 : scriptableObjectEnsureScriptableObject.getIds(true, true)) {
            ScriptableObject ownPropertyDescriptor = scriptableObjectEnsureScriptableObject.getOwnPropertyDescriptor(context, obj2);
            if (integrity_level == INTEGRITY_LEVEL.SEALED) {
                if (Boolean.TRUE.equals(ownPropertyDescriptor.get("configurable"))) {
                    ownPropertyDescriptor.put("configurable", ownPropertyDescriptor, Boolean.FALSE);
                    scriptableObjectEnsureScriptableObject.defineOwnProperty(context, obj2, ownPropertyDescriptor, false);
                }
            } else {
                if (ScriptableObject.isDataDescriptor(ownPropertyDescriptor) && Boolean.TRUE.equals(ownPropertyDescriptor.get("writable"))) {
                    ownPropertyDescriptor.put("writable", ownPropertyDescriptor, Boolean.FALSE);
                }
                if (Boolean.TRUE.equals(ownPropertyDescriptor.get("configurable"))) {
                    ownPropertyDescriptor.put("configurable", ownPropertyDescriptor, Boolean.FALSE);
                }
                scriptableObjectEnsureScriptableObject.defineOwnProperty(context, obj2, ownPropertyDescriptor, false);
            }
        }
        return true;
    }

    public static Constructable speciesConstructor(Context context, Scriptable scriptable, Constructable constructable) {
        Object property = ScriptableObject.getProperty(scriptable, "constructor");
        if (property != Scriptable.NOT_FOUND && !Undefined.isUndefined(property)) {
            if (!ScriptRuntime.isObject(property)) {
                throw ScriptRuntime.typeErrorById("msg.arg.not.object", ScriptRuntime.typeof(property));
            }
            Object property2 = ScriptableObject.getProperty((Scriptable) property, SymbolKey.SPECIES);
            if (property2 != Scriptable.NOT_FOUND && property2 != null && !Undefined.isUndefined(property2)) {
                if (property2 instanceof Constructable) {
                    return (Constructable) property2;
                }
                throw ScriptRuntime.typeErrorById("msg.not.ctor", ScriptRuntime.typeof(property2));
            }
        }
        return constructable;
    }

    static void put(Context context, Scriptable scriptable, String str, Object obj, boolean z) {
        Scriptable base = ScriptableObject.getBase(scriptable, str);
        if (base == null) {
            base = scriptable;
        }
        if (base instanceof ScriptableObject) {
            if (((ScriptableObject) base).putOwnProperty(str, scriptable, obj, z)) {
                return;
            }
            scriptable.put(str, scriptable, obj);
            return;
        }
        base.put(str, scriptable, obj);
    }

    static void put(Context context, Scriptable scriptable, int i, Object obj, boolean z) {
        Scriptable base = ScriptableObject.getBase(scriptable, i);
        if (base == null) {
            base = scriptable;
        }
        if (base instanceof ScriptableObject) {
            if (((ScriptableObject) base).putOwnProperty(i, scriptable, obj, z)) {
                return;
            }
            scriptable.put(i, scriptable, obj);
            return;
        }
        base.put(i, scriptable, obj);
    }

    static Map<Object, List<Object>> groupBy(Context context, Scriptable scriptable, IdFunctionObject idFunctionObject, Object obj, Object obj2, KEY_COERCION key_coercion) {
        if (context.getLanguageVersion() >= 200) {
            ScriptRuntimeES6.requireObjectCoercible(context, obj, idFunctionObject);
        }
        if (!(obj2 instanceof Callable)) {
            throw ScriptRuntime.typeErrorById("msg.isnt.function", obj2, ScriptRuntime.typeof(obj2));
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        IteratorLikeIterable iteratorLikeIterable = new IteratorLikeIterable(context, scriptable, ScriptRuntime.callIterator(obj, context, scriptable));
        try {
            Iterator<Object> itIterator2 = iteratorLikeIterable.iterator();
            double d = 0.0d;
            while (itIterator2.hasNext()) {
                Object next = itIterator2.next();
                if (d > 9.007199254740991E15d) {
                    iteratorLikeIterable.close();
                    throw ScriptRuntime.typeError("Too many values to iterate");
                }
                Object objCall = ((Callable) obj2).call(context, scriptable, Undefined.SCRIPTABLE_UNDEFINED, new Object[]{next, Double.valueOf(d)});
                if (key_coercion == KEY_COERCION.PROPERTY) {
                    if (!ScriptRuntime.isSymbol(objCall)) {
                        objCall = ScriptRuntime.toString(objCall);
                    }
                } else if ((objCall instanceof Number) && ((Number) objCall).doubleValue() == ScriptRuntime.negativeZero) {
                    objCall = ScriptRuntime.zeroObj;
                }
                ((List) j$.util.Map.EL.computeIfAbsent(linkedHashMap, objCall, new java.util.function.Function() { // from class: org.mozilla.javascript.AbstractEcmaObjectOperations$$ExternalSyntheticLambda0
                    @Override // java.util.function.Function
                    public /* synthetic */ java.util.function.Function andThen(java.util.function.Function function) {
                        return Function$CC.$default$andThen(this, function);
                    }

                    @Override // java.util.function.Function
                    public final Object apply(Object obj3) {
                        return AbstractEcmaObjectOperations.lambda$groupBy$0(obj3);
                    }

                    public /* synthetic */ java.util.function.Function compose(java.util.function.Function function) {
                        return Function$CC.$default$compose(this, function);
                    }
                })).add(next);
                d += 1.0d;
            }
            iteratorLikeIterable.close();
            return linkedHashMap;
        } catch (Throwable th) {
            try {
                iteratorLikeIterable.close();
                throw th;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
                throw th;
            }
        }
    }

    static /* synthetic */ List lambda$groupBy$0(Object obj) {
        return new ArrayList();
    }

    static List<Object> createListFromArrayLike(Context context, Scriptable scriptable, Predicate<Object> predicate, String str) {
        ScriptableObject scriptableObjectEnsureScriptableObject = ScriptableObject.ensureScriptableObject(scriptable);
        if (scriptableObjectEnsureScriptableObject instanceof NativeArray) {
            Object[] array = ((NativeArray) scriptableObjectEnsureScriptableObject).toArray();
            for (Object obj : array) {
                if (!predicate.test(obj)) {
                    throw ScriptRuntime.typeError(str);
                }
            }
            return Arrays.asList(array);
        }
        long jLengthOfArrayLike = lengthOfArrayLike(context, scriptableObjectEnsureScriptableObject);
        ArrayList arrayList = new ArrayList();
        for (long j = 0; j < jLengthOfArrayLike; j++) {
            Object property = ScriptableObject.getProperty(scriptableObjectEnsureScriptableObject, (int) j);
            if (!predicate.test(property)) {
                throw ScriptRuntime.typeError(str);
            }
            arrayList.add(property);
        }
        return arrayList;
    }

    static long lengthOfArrayLike(Context context, Scriptable scriptable) {
        return ScriptRuntime.toLength(new Object[]{ScriptableObject.getProperty(scriptable, "length")}, 0);
    }

    static boolean isCompatiblePropertyDescriptor(Context context, boolean z, ScriptableObject scriptableObject, ScriptableObject scriptableObject2) {
        return validateAndApplyPropertyDescriptor(context, Undefined.SCRIPTABLE_UNDEFINED, Undefined.SCRIPTABLE_UNDEFINED, z, scriptableObject, scriptableObject2);
    }

    static boolean validateAndApplyPropertyDescriptor(Context context, Scriptable scriptable, Scriptable scriptable2, boolean z, ScriptableObject scriptableObject, ScriptableObject scriptableObject2) {
        if (Undefined.isUndefined(scriptableObject2)) {
            if (!z) {
                return false;
            }
            if (!ScriptableObject.isGenericDescriptor(scriptableObject)) {
                ScriptableObject.isDataDescriptor(scriptableObject);
            }
            return true;
        }
        if (scriptableObject.getIds().length == 0) {
            return true;
        }
        if (Boolean.FALSE.equals(scriptableObject2.get("configurable"))) {
            if (Boolean.TRUE.equals(Boolean.valueOf(ScriptableObject.hasProperty(scriptableObject, "configurable"))) && Boolean.TRUE.equals(scriptableObject.get("configurable"))) {
                return false;
            }
            if (Boolean.TRUE.equals(Boolean.valueOf(ScriptableObject.hasProperty(scriptableObject, "enumerable"))) && !Objects.equals(scriptableObject.get("enumerable"), scriptableObject2.get("enumerable"))) {
                return false;
            }
        }
        if (ScriptableObject.isGenericDescriptor(scriptableObject)) {
            return true;
        }
        if (ScriptableObject.isDataDescriptor(scriptableObject2) != ScriptableObject.isDataDescriptor(scriptableObject)) {
            if (Boolean.FALSE.equals(scriptableObject2.get("configurable"))) {
                return false;
            }
            if (ScriptableObject.isDataDescriptor(scriptableObject2)) {
                Boolean.FALSE.equals(scriptableObject2.get("configurable"));
            }
        } else if (ScriptableObject.isDataDescriptor(scriptableObject2) && ScriptableObject.isDataDescriptor(scriptableObject)) {
            if (Boolean.FALSE.equals(scriptableObject2.get("configurable")) && Boolean.FALSE.equals(scriptableObject2.get("writable"))) {
                if (Boolean.TRUE.equals(Boolean.valueOf(ScriptableObject.hasProperty(scriptableObject, "writable"))) && Boolean.TRUE.equals(scriptableObject.get("writable"))) {
                    return false;
                }
                return !Boolean.TRUE.equals(Boolean.valueOf(ScriptableObject.hasProperty(scriptableObject, ES6Iterator.VALUE_PROPERTY))) || Objects.equals(scriptableObject.get(ES6Iterator.VALUE_PROPERTY), scriptableObject2.get(ES6Iterator.VALUE_PROPERTY));
            }
        } else if (Boolean.FALSE.equals(scriptableObject2.get("configurable"))) {
            if (Boolean.TRUE.equals(Boolean.valueOf(ScriptableObject.hasProperty(scriptableObject, "set"))) && !Objects.equals(scriptableObject.get("set"), scriptableObject2.get("set"))) {
                return false;
            }
            if (Boolean.TRUE.equals(Boolean.valueOf(ScriptableObject.hasProperty(scriptableObject, "get"))) && !Objects.equals(scriptableObject.get("get"), scriptableObject2.get("get"))) {
                return false;
            }
        }
        return true;
    }

    static boolean isConstructor(Context context, Object obj) {
        if (obj instanceof LambdaConstructor) {
            return true;
        }
        if (obj instanceof LambdaFunction) {
            return false;
        }
        return obj instanceof Constructable;
    }
}
