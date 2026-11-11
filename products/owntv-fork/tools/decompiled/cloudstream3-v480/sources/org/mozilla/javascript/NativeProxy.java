package org.mozilla.javascript;

import androidx.exifinterface.media.ExifInterface;
import j$.util.Objects;
import j$.util.function.Predicate$CC;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes8.dex */
final class NativeProxy extends ScriptableObject implements Callable, Constructable {
    private static final String PROXY_TAG = "Proxy";
    private static final String TRAP_APPLY = "apply";
    private static final String TRAP_CONSTRUCT = "construct";
    private static final String TRAP_DEFINE_PROPERTY = "defineProperty";
    private static final String TRAP_DELETE_PROPERTY = "deleteProperty";
    private static final String TRAP_GET = "get";
    private static final String TRAP_GET_OWN_PROPERTY_DESCRIPTOR = "getOwnPropertyDescriptor";
    private static final String TRAP_GET_PROTOTYPE_OF = "getPrototypeOf";
    private static final String TRAP_HAS = "has";
    private static final String TRAP_IS_EXTENSIBLE = "isExtensible";
    private static final String TRAP_OWN_KEYS = "ownKeys";
    private static final String TRAP_PREVENT_EXTENSIONS = "preventExtensions";
    private static final String TRAP_SET = "set";
    private static final String TRAP_SET_PROTOTYPE_OF = "setPrototypeOf";
    private static final long serialVersionUID = 6676871870513494844L;
    private Scriptable handlerObj;
    private ScriptableObject targetObj;
    private final String typeOf;

    private static final class Revoker implements Callable {
        private NativeProxy revocableProxy;

        public Revoker(NativeProxy nativeProxy) {
            this.revocableProxy = nativeProxy;
        }

        @Override // org.mozilla.javascript.Callable
        public Object call(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
            NativeProxy nativeProxy = this.revocableProxy;
            if (nativeProxy != null) {
                nativeProxy.handlerObj = null;
                this.revocableProxy.targetObj = null;
                this.revocableProxy = null;
            }
            return Undefined.instance;
        }
    }

    public static void init(Context context, Scriptable scriptable, boolean z) {
        LambdaConstructor lambdaConstructor = new LambdaConstructor(scriptable, PROXY_TAG, 2, 2, new Constructable() { // from class: org.mozilla.javascript.NativeProxy$$ExternalSyntheticLambda0
            @Override // org.mozilla.javascript.Constructable
            public final Scriptable construct(Context context2, Scriptable scriptable2, Object[] objArr) {
                return NativeProxy.constructor(context2, scriptable2, objArr);
            }
        }) { // from class: org.mozilla.javascript.NativeProxy.1
            @Override // org.mozilla.javascript.LambdaConstructor, org.mozilla.javascript.LambdaFunction, org.mozilla.javascript.BaseFunction, org.mozilla.javascript.Function, org.mozilla.javascript.Constructable
            public Scriptable construct(Context context2, Scriptable scriptable2, Object[] objArr) {
                NativeProxy nativeProxy = (NativeProxy) getTargetConstructor().construct(context2, scriptable2, objArr);
                nativeProxy.setPrototypeDirect(getClassPrototype());
                nativeProxy.setParentScope(scriptable2);
                return nativeProxy;
            }
        };
        lambdaConstructor.setPrototypeProperty(null);
        lambdaConstructor.defineConstructorMethod(scriptable, "revocable", 2, new Callable() { // from class: org.mozilla.javascript.NativeProxy$$ExternalSyntheticLambda1
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeProxy.revocable(context2, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        ScriptableObject.defineProperty(scriptable, PROXY_TAG, lambdaConstructor, 2);
        if (z) {
            lambdaConstructor.sealObject();
        }
    }

    private NativeProxy(ScriptableObject scriptableObject, Scriptable scriptable) {
        this.targetObj = scriptableObject;
        this.handlerObj = scriptable;
        if (scriptableObject == null || !(scriptableObject instanceof Callable)) {
            this.typeOf = super.getTypeOf();
        } else {
            this.typeOf = scriptableObject.getTypeOf();
        }
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public String getClassName() {
        return getTargetThrowIfRevoked().getClassName();
    }

    @Override // org.mozilla.javascript.Constructable
    public Scriptable construct(Context context, Scriptable scriptable, Object[] objArr) {
        Object targetThrowIfRevoked = getTargetThrowIfRevoked();
        Callable trap = getTrap(TRAP_CONSTRUCT);
        if (trap != null) {
            Object objCallTrap = callTrap(trap, new Object[]{targetThrowIfRevoked, objArr, this});
            if (!(objCallTrap instanceof Scriptable) || ScriptRuntime.isSymbol(objCallTrap)) {
                throw ScriptRuntime.typeError("Constructor trap has to return a scriptable.");
            }
            return (ScriptableObject) objCallTrap;
        }
        return ((Constructable) targetThrowIfRevoked).construct(context, scriptable, objArr);
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public boolean has(String str, Scriptable scriptable) {
        ScriptableObject ownPropertyDescriptor;
        ScriptableObject targetThrowIfRevoked = getTargetThrowIfRevoked();
        Callable trap = getTrap(TRAP_HAS);
        Scriptable scriptable2 = scriptable;
        if (trap != null) {
            boolean z = ScriptRuntime.toBoolean(callTrap(trap, new Object[]{targetThrowIfRevoked, str}));
            if (z || (ownPropertyDescriptor = targetThrowIfRevoked.getOwnPropertyDescriptor(Context.getContext(), str)) == null || (!Boolean.FALSE.equals(ownPropertyDescriptor.get("configurable")) && targetThrowIfRevoked.isExtensible())) {
                return z;
            }
            throw ScriptRuntime.typeError("proxy can't report an existing own property '" + str + "' as non-existent on a non-extensible object");
        }
        if (scriptable == this) {
            scriptable2 = targetThrowIfRevoked;
        }
        return targetThrowIfRevoked.has(str, scriptable2);
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public boolean has(int i, Scriptable scriptable) {
        ScriptableObject ownPropertyDescriptor;
        ScriptableObject targetThrowIfRevoked = getTargetThrowIfRevoked();
        Callable trap = getTrap(TRAP_HAS);
        Scriptable scriptable2 = scriptable;
        if (trap != null) {
            boolean z = ScriptRuntime.toBoolean(callTrap(trap, new Object[]{targetThrowIfRevoked, ScriptRuntime.toString(i)}));
            if (z || (ownPropertyDescriptor = targetThrowIfRevoked.getOwnPropertyDescriptor(Context.getContext(), Integer.valueOf(i))) == null || (!Boolean.FALSE.equals(ownPropertyDescriptor.get("configurable")) && targetThrowIfRevoked.isExtensible())) {
                return z;
            }
            throw ScriptRuntime.typeError("proxy can't check an existing property ' + name + ' existance on an not configurable or not extensible object");
        }
        if (scriptable == this) {
            scriptable2 = targetThrowIfRevoked;
        }
        return targetThrowIfRevoked.has(i, scriptable2);
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.SymbolScriptable
    public boolean has(Symbol symbol, Scriptable scriptable) {
        ScriptableObject ownPropertyDescriptor;
        ScriptableObject targetThrowIfRevoked = getTargetThrowIfRevoked();
        Callable trap = getTrap(TRAP_HAS);
        if (trap != null) {
            boolean z = ScriptRuntime.toBoolean(callTrap(trap, new Object[]{targetThrowIfRevoked, symbol}));
            if (z || (ownPropertyDescriptor = targetThrowIfRevoked.getOwnPropertyDescriptor(Context.getContext(), symbol)) == null || (!Boolean.FALSE.equals(ownPropertyDescriptor.get("configurable")) && targetThrowIfRevoked.isExtensible())) {
                return z;
            }
            throw ScriptRuntime.typeError("proxy can't check an existing property ' + name + ' existance on an not configurable or not extensible object");
        }
        if (scriptable == this) {
            scriptable = targetThrowIfRevoked;
        }
        return ensureSymbolScriptable(targetThrowIfRevoked).has(symbol, scriptable);
    }

    @Override // org.mozilla.javascript.ScriptableObject
    Object[] getIds(boolean z, boolean z2) {
        ScriptableObject targetThrowIfRevoked = getTargetThrowIfRevoked();
        Callable trap = getTrap(TRAP_OWN_KEYS);
        if (trap != null) {
            Object objCallTrap = callTrap(trap, new Object[]{targetThrowIfRevoked});
            if (!(objCallTrap instanceof Scriptable)) {
                throw ScriptRuntime.typeError("ownKeys trap must be an object");
            }
            Scriptable scriptable = (Scriptable) objCallTrap;
            if (!ScriptRuntime.isArrayLike(scriptable)) {
                throw ScriptRuntime.typeError("ownKeys trap must be an array like object");
            }
            Context context = Context.getContext();
            List<Object> listCreateListFromArrayLike = AbstractEcmaObjectOperations.createListFromArrayLike(context, scriptable, new Predicate() { // from class: org.mozilla.javascript.NativeProxy$$ExternalSyntheticLambda2
                public /* synthetic */ Predicate and(Predicate predicate) {
                    return Predicate$CC.$default$and(this, predicate);
                }

                public /* synthetic */ Predicate negate() {
                    return Predicate$CC.$default$negate(this);
                }

                public /* synthetic */ Predicate or(Predicate predicate) {
                    return Predicate$CC.$default$or(this, predicate);
                }

                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return NativeProxy.lambda$getIds$0(obj);
                }
            }, "proxy [[OwnPropertyKeys]] must return an array with only string and symbol elements");
            boolean zIsExtensible = targetThrowIfRevoked.isExtensible();
            Object[] ids = targetThrowIfRevoked.getIds(true, true);
            HashSet hashSet = new HashSet(listCreateListFromArrayLike);
            if (hashSet.size() != listCreateListFromArrayLike.size()) {
                throw ScriptRuntime.typeError("ownKeys trap result must not contain duplicates");
            }
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : ids) {
                ScriptableObject ownPropertyDescriptor = targetThrowIfRevoked.getOwnPropertyDescriptor(context, obj);
                if (ownPropertyDescriptor != null && Boolean.FALSE.equals(ownPropertyDescriptor.get("configurable"))) {
                    arrayList2.add(obj);
                } else {
                    arrayList.add(obj);
                }
            }
            if (zIsExtensible && arrayList2.size() == 0) {
                return listCreateListFromArrayLike.toArray();
            }
            for (Object obj2 : arrayList2) {
                if (!hashSet.contains(obj2)) {
                    throw ScriptRuntime.typeError("proxy can't skip a non-configurable property '" + String.valueOf(obj2) + "'");
                }
                hashSet.remove(obj2);
            }
            if (zIsExtensible) {
                return listCreateListFromArrayLike.toArray();
            }
            for (Object obj3 : arrayList) {
                if (!hashSet.contains(obj3)) {
                    throw ScriptRuntime.typeError("proxy can't skip a configurable property " + String.valueOf(obj3));
                }
                hashSet.remove(obj3);
            }
            if (hashSet.size() > 0) {
                throw ScriptRuntime.typeError("proxy can't skip properties");
            }
        }
        return targetThrowIfRevoked.getIds(z, z2);
    }

    static /* synthetic */ boolean lambda$getIds$0(Object obj) {
        return (obj instanceof CharSequence) || (obj instanceof NativeString) || ScriptRuntime.isSymbol(obj);
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public Object get(String str, Scriptable scriptable) {
        ScriptableObject targetThrowIfRevoked = getTargetThrowIfRevoked();
        Callable trap = getTrap(TRAP_GET);
        Scriptable scriptable2 = scriptable;
        if (trap != null) {
            Object objCallTrap = callTrap(trap, new Object[]{targetThrowIfRevoked, str, this});
            ScriptableObject ownPropertyDescriptor = targetThrowIfRevoked.getOwnPropertyDescriptor(Context.getContext(), str);
            if (ownPropertyDescriptor != null && !Undefined.isUndefined(ownPropertyDescriptor) && Boolean.FALSE.equals(ownPropertyDescriptor.get("configurable"))) {
                if (ScriptableObject.isDataDescriptor(ownPropertyDescriptor) && Boolean.FALSE.equals(ownPropertyDescriptor.get("writable")) && !Objects.equals(objCallTrap, ownPropertyDescriptor.get(ES6Iterator.VALUE_PROPERTY))) {
                    throw ScriptRuntime.typeError("proxy get has to return the same value as the plain call");
                }
                if (ScriptableObject.isAccessorDescriptor(ownPropertyDescriptor) && Undefined.isUndefined(ownPropertyDescriptor.get(TRAP_GET)) && !Undefined.isUndefined(objCallTrap)) {
                    throw ScriptRuntime.typeError("proxy get has to return the same value as the plain call");
                }
            }
            return objCallTrap;
        }
        if (scriptable == this) {
            scriptable2 = targetThrowIfRevoked;
        }
        return targetThrowIfRevoked.get(str, scriptable2);
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public Object get(int i, Scriptable scriptable) {
        ScriptableObject targetThrowIfRevoked = getTargetThrowIfRevoked();
        Callable trap = getTrap(TRAP_GET);
        Scriptable scriptable2 = scriptable;
        if (trap != null) {
            Object objCallTrap = callTrap(trap, new Object[]{targetThrowIfRevoked, ScriptRuntime.toString(i), this});
            ScriptableObject ownPropertyDescriptor = targetThrowIfRevoked.getOwnPropertyDescriptor(Context.getContext(), Integer.valueOf(i));
            if (ownPropertyDescriptor != null && !Undefined.isUndefined(ownPropertyDescriptor) && Boolean.FALSE.equals(ownPropertyDescriptor.get("configurable"))) {
                if (ScriptableObject.isDataDescriptor(ownPropertyDescriptor) && Boolean.FALSE.equals(ownPropertyDescriptor.get("writable")) && !Objects.equals(objCallTrap, ownPropertyDescriptor.get(ES6Iterator.VALUE_PROPERTY))) {
                    throw ScriptRuntime.typeError("proxy get has to return the same value as the plain call");
                }
                if (ScriptableObject.isAccessorDescriptor(ownPropertyDescriptor) && Undefined.isUndefined(ownPropertyDescriptor.get(TRAP_GET)) && !Undefined.isUndefined(objCallTrap)) {
                    throw ScriptRuntime.typeError("proxy get has to return the same value as the plain call");
                }
            }
            return objCallTrap;
        }
        if (scriptable == this) {
            scriptable2 = targetThrowIfRevoked;
        }
        return targetThrowIfRevoked.get(i, scriptable2);
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.SymbolScriptable
    public Object get(Symbol symbol, Scriptable scriptable) {
        ScriptableObject targetThrowIfRevoked = getTargetThrowIfRevoked();
        Callable trap = getTrap(TRAP_GET);
        if (trap != null) {
            Object objCallTrap = callTrap(trap, new Object[]{targetThrowIfRevoked, symbol, this});
            ScriptableObject ownPropertyDescriptor = targetThrowIfRevoked.getOwnPropertyDescriptor(Context.getContext(), symbol);
            if (ownPropertyDescriptor != null && !Undefined.isUndefined(ownPropertyDescriptor) && Boolean.FALSE.equals(ownPropertyDescriptor.get("configurable"))) {
                if (ScriptableObject.isDataDescriptor(ownPropertyDescriptor) && Boolean.FALSE.equals(ownPropertyDescriptor.get("writable")) && !Objects.equals(objCallTrap, ownPropertyDescriptor.get(ES6Iterator.VALUE_PROPERTY))) {
                    throw ScriptRuntime.typeError("proxy get has to return the same value as the plain call");
                }
                if (ScriptableObject.isAccessorDescriptor(ownPropertyDescriptor) && Undefined.isUndefined(ownPropertyDescriptor.get(TRAP_GET)) && !Undefined.isUndefined(objCallTrap)) {
                    throw ScriptRuntime.typeError("proxy get has to return the same value as the plain call");
                }
            }
            return objCallTrap;
        }
        if (scriptable == this) {
            scriptable = targetThrowIfRevoked;
        }
        return ensureSymbolScriptable(targetThrowIfRevoked).get(symbol, scriptable);
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public void put(String str, Scriptable scriptable, Object obj) {
        ScriptableObject ownPropertyDescriptor;
        ScriptableObject targetThrowIfRevoked = getTargetThrowIfRevoked();
        Callable trap = getTrap(TRAP_SET);
        Scriptable scriptable2 = scriptable;
        if (trap != null) {
            if (ScriptRuntime.toBoolean(callTrap(trap, new Object[]{targetThrowIfRevoked, str, obj})) && (ownPropertyDescriptor = targetThrowIfRevoked.getOwnPropertyDescriptor(Context.getContext(), str)) != null && !Undefined.isUndefined(ownPropertyDescriptor) && Boolean.FALSE.equals(ownPropertyDescriptor.get("configurable"))) {
                if (ScriptableObject.isDataDescriptor(ownPropertyDescriptor) && Boolean.FALSE.equals(ownPropertyDescriptor.get("writable")) && !Objects.equals(obj, ownPropertyDescriptor.get(ES6Iterator.VALUE_PROPERTY))) {
                    throw ScriptRuntime.typeError("proxy set has to use the same value as the plain call");
                }
                if (ScriptableObject.isAccessorDescriptor(ownPropertyDescriptor) && Undefined.isUndefined(ownPropertyDescriptor.get(TRAP_SET))) {
                    throw ScriptRuntime.typeError("proxy set has to be available");
                }
                return;
            }
            return;
        }
        if (scriptable == this) {
            scriptable2 = targetThrowIfRevoked;
        }
        targetThrowIfRevoked.put(str, scriptable2, obj);
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public void put(int i, Scriptable scriptable, Object obj) {
        ScriptableObject ownPropertyDescriptor;
        ScriptableObject targetThrowIfRevoked = getTargetThrowIfRevoked();
        Callable trap = getTrap(TRAP_SET);
        Scriptable scriptable2 = scriptable;
        if (trap != null) {
            if (ScriptRuntime.toBoolean(callTrap(trap, new Object[]{targetThrowIfRevoked, ScriptRuntime.toString(i), obj})) && (ownPropertyDescriptor = targetThrowIfRevoked.getOwnPropertyDescriptor(Context.getContext(), Integer.valueOf(i))) != null && !Undefined.isUndefined(ownPropertyDescriptor) && Boolean.FALSE.equals(ownPropertyDescriptor.get("configurable"))) {
                if (ScriptableObject.isDataDescriptor(ownPropertyDescriptor) && Boolean.FALSE.equals(ownPropertyDescriptor.get("writable")) && !Objects.equals(obj, ownPropertyDescriptor.get(ES6Iterator.VALUE_PROPERTY))) {
                    throw ScriptRuntime.typeError("proxy set has to use the same value as the plain call");
                }
                if (ScriptableObject.isAccessorDescriptor(ownPropertyDescriptor) && Undefined.isUndefined(ownPropertyDescriptor.get(TRAP_SET))) {
                    throw ScriptRuntime.typeError("proxy set has to be available");
                }
                return;
            }
            return;
        }
        if (scriptable == this) {
            scriptable2 = targetThrowIfRevoked;
        }
        targetThrowIfRevoked.put(i, scriptable2, obj);
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.SymbolScriptable
    public void put(Symbol symbol, Scriptable scriptable, Object obj) {
        ScriptableObject ownPropertyDescriptor;
        ScriptableObject targetThrowIfRevoked = getTargetThrowIfRevoked();
        Callable trap = getTrap(TRAP_SET);
        if (trap != null) {
            if (ScriptRuntime.toBoolean(callTrap(trap, new Object[]{targetThrowIfRevoked, symbol, obj})) && (ownPropertyDescriptor = targetThrowIfRevoked.getOwnPropertyDescriptor(Context.getContext(), symbol)) != null && !Undefined.isUndefined(ownPropertyDescriptor) && Boolean.FALSE.equals(ownPropertyDescriptor.get("configurable"))) {
                if (ScriptableObject.isDataDescriptor(ownPropertyDescriptor) && Boolean.FALSE.equals(ownPropertyDescriptor.get("writable")) && !Objects.equals(obj, ownPropertyDescriptor.get(ES6Iterator.VALUE_PROPERTY))) {
                    throw ScriptRuntime.typeError("proxy set has to use the same value as the plain call");
                }
                if (ScriptableObject.isAccessorDescriptor(ownPropertyDescriptor) && Undefined.isUndefined(ownPropertyDescriptor.get(TRAP_SET))) {
                    throw ScriptRuntime.typeError("proxy set has to be available");
                }
                return;
            }
            return;
        }
        if (scriptable == this) {
            scriptable = targetThrowIfRevoked;
        }
        ensureSymbolScriptable(targetThrowIfRevoked).put(symbol, scriptable, obj);
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public void delete(String str) {
        ScriptableObject ownPropertyDescriptor;
        ScriptableObject targetThrowIfRevoked = getTargetThrowIfRevoked();
        Callable trap = getTrap(TRAP_DELETE_PROPERTY);
        if (trap != null) {
            if (ScriptRuntime.toBoolean(callTrap(trap, new Object[]{targetThrowIfRevoked, str})) && (ownPropertyDescriptor = targetThrowIfRevoked.getOwnPropertyDescriptor(Context.getContext(), str)) != null) {
                if (Boolean.FALSE.equals(ownPropertyDescriptor.get("configurable")) || !targetThrowIfRevoked.isExtensible()) {
                    throw ScriptRuntime.typeError("proxy can't delete an existing own property ' + name + ' on an not configurable or not extensible object");
                }
                return;
            }
            return;
        }
        targetThrowIfRevoked.delete(str);
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public void delete(int i) {
        ScriptableObject ownPropertyDescriptor;
        ScriptableObject targetThrowIfRevoked = getTargetThrowIfRevoked();
        Callable trap = getTrap(TRAP_DELETE_PROPERTY);
        if (trap != null) {
            if (ScriptRuntime.toBoolean(callTrap(trap, new Object[]{targetThrowIfRevoked, ScriptRuntime.toString(i)})) && (ownPropertyDescriptor = targetThrowIfRevoked.getOwnPropertyDescriptor(Context.getContext(), Integer.valueOf(i))) != null) {
                if (Boolean.FALSE.equals(ownPropertyDescriptor.get("configurable")) || !targetThrowIfRevoked.isExtensible()) {
                    throw ScriptRuntime.typeError("proxy can't delete an existing own property ' + name + ' on an not configurable or not extensible object");
                }
                return;
            }
            return;
        }
        targetThrowIfRevoked.delete(i);
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.SymbolScriptable
    public void delete(Symbol symbol) {
        ScriptableObject ownPropertyDescriptor;
        ScriptableObject targetThrowIfRevoked = getTargetThrowIfRevoked();
        Callable trap = getTrap(TRAP_DELETE_PROPERTY);
        if (trap != null) {
            if (ScriptRuntime.toBoolean(callTrap(trap, new Object[]{targetThrowIfRevoked, symbol})) && (ownPropertyDescriptor = targetThrowIfRevoked.getOwnPropertyDescriptor(Context.getContext(), symbol)) != null) {
                if (Boolean.FALSE.equals(ownPropertyDescriptor.get("configurable")) || !targetThrowIfRevoked.isExtensible()) {
                    throw ScriptRuntime.typeError("proxy can't delete an existing own property ' + name + ' on an not configurable or not extensible object");
                }
                return;
            }
            return;
        }
        ensureSymbolScriptable(targetThrowIfRevoked).delete(symbol);
    }

    @Override // org.mozilla.javascript.ScriptableObject
    protected ScriptableObject getOwnPropertyDescriptor(Context context, Object obj) {
        ScriptableObject ownPropertyDescriptor;
        ScriptableObject targetThrowIfRevoked = getTargetThrowIfRevoked();
        Callable trap = getTrap(TRAP_GET_OWN_PROPERTY_DESCRIPTOR);
        if (trap != null) {
            Object objCallTrap = callTrap(trap, new Object[]{targetThrowIfRevoked, obj});
            if (!Undefined.isUndefined(objCallTrap) && (!(objCallTrap instanceof Scriptable) || ScriptRuntime.isSymbol(objCallTrap))) {
                throw ScriptRuntime.typeError("getOwnPropertyDescriptor trap has to return undefined or an object");
            }
            if (ScriptRuntime.isSymbol(obj)) {
                ownPropertyDescriptor = targetThrowIfRevoked.getOwnPropertyDescriptor(context, obj);
            } else {
                ownPropertyDescriptor = targetThrowIfRevoked.getOwnPropertyDescriptor(context, ScriptRuntime.toString(obj));
            }
            if (Undefined.isUndefined(objCallTrap)) {
                if (Undefined.isUndefined(ownPropertyDescriptor)) {
                    return null;
                }
                if (!Boolean.FALSE.equals(ownPropertyDescriptor.get("configurable")) && targetThrowIfRevoked.isExtensible()) {
                    return null;
                }
                throw ScriptRuntime.typeError("proxy can't report an existing own property '" + String.valueOf(obj) + "' as non-existent on a non-extensible object");
            }
            Scriptable scriptable = (Scriptable) objCallTrap;
            if (objCallTrap != null) {
                return ScriptableObject.buildDataDescriptor(targetThrowIfRevoked, ScriptableObject.getProperty(scriptable, ES6Iterator.VALUE_PROPERTY), applyDescriptorToAttributeBitset(7, getProperty(scriptable, "enumerable"), getProperty(scriptable, "writable"), getProperty(scriptable, "configurable")));
            }
            return null;
        }
        if (ScriptRuntime.isSymbol(obj)) {
            return targetThrowIfRevoked.getOwnPropertyDescriptor(context, obj);
        }
        return targetThrowIfRevoked.getOwnPropertyDescriptor(context, ScriptRuntime.toString(obj));
    }

    @Override // org.mozilla.javascript.ScriptableObject
    public boolean defineOwnProperty(Context context, Object obj, ScriptableObject scriptableObject) {
        ScriptableObject targetThrowIfRevoked = getTargetThrowIfRevoked();
        Callable trap = getTrap(TRAP_DEFINE_PROPERTY);
        if (trap != null) {
            boolean z = false;
            if (!ScriptRuntime.toBoolean(callTrap(trap, new Object[]{targetThrowIfRevoked, obj, scriptableObject}))) {
                return false;
            }
            ScriptableObject ownPropertyDescriptor = targetThrowIfRevoked.getOwnPropertyDescriptor(Context.getContext(), obj);
            boolean zIsExtensible = targetThrowIfRevoked.isExtensible();
            if (Boolean.TRUE.equals(Boolean.valueOf(ScriptableObject.hasProperty(scriptableObject, "configurable"))) && Boolean.FALSE.equals(scriptableObject.get("configurable"))) {
                z = true;
            }
            if (ownPropertyDescriptor == null) {
                if (!zIsExtensible || z) {
                    throw ScriptRuntime.typeError("proxy can't define an incompatible property descriptor");
                }
            } else if (AbstractEcmaObjectOperations.isCompatiblePropertyDescriptor(context, zIsExtensible, scriptableObject, ownPropertyDescriptor)) {
                if (z && Boolean.TRUE.equals(ownPropertyDescriptor.get("configurable"))) {
                    throw ScriptRuntime.typeError("proxy can't define an incompatible property descriptor");
                }
                if (ScriptableObject.isDataDescriptor(ownPropertyDescriptor) && Boolean.FALSE.equals(ownPropertyDescriptor.get("configurable")) && Boolean.TRUE.equals(ownPropertyDescriptor.get("writable")) && Boolean.TRUE.equals(Boolean.valueOf(ScriptableObject.hasProperty(scriptableObject, "writable"))) && Boolean.FALSE.equals(scriptableObject.get("writable"))) {
                    throw ScriptRuntime.typeError("proxy can't define an incompatible property descriptor");
                }
            } else {
                throw ScriptRuntime.typeError("proxy can't define an incompatible property descriptor");
            }
            return true;
        }
        return targetThrowIfRevoked.defineOwnProperty(context, obj, scriptableObject);
    }

    @Override // org.mozilla.javascript.ScriptableObject
    public boolean isExtensible() {
        ScriptableObject targetThrowIfRevoked = getTargetThrowIfRevoked();
        Callable trap = getTrap(TRAP_IS_EXTENSIBLE);
        if (trap == null) {
            return targetThrowIfRevoked.isExtensible();
        }
        boolean z = ScriptRuntime.toBoolean(callTrap(trap, new Object[]{targetThrowIfRevoked}));
        if (z == targetThrowIfRevoked.isExtensible()) {
            return z;
        }
        throw ScriptRuntime.typeError("IsExtensible trap has to return the same value as the target");
    }

    @Override // org.mozilla.javascript.ScriptableObject
    public boolean preventExtensions() {
        ScriptableObject targetThrowIfRevoked = getTargetThrowIfRevoked();
        Callable trap = getTrap(TRAP_PREVENT_EXTENSIONS);
        if (trap == null) {
            return targetThrowIfRevoked.preventExtensions();
        }
        boolean z = ScriptRuntime.toBoolean(callTrap(trap, new Object[]{targetThrowIfRevoked}));
        if (z && targetThrowIfRevoked.isExtensible()) {
            throw ScriptRuntime.typeError("target is extensible but trap returned true");
        }
        return z;
    }

    @Override // org.mozilla.javascript.ScriptableObject
    public String getTypeOf() {
        return this.typeOf;
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public Scriptable getPrototype() {
        ScriptableObject targetThrowIfRevoked = getTargetThrowIfRevoked();
        Callable trap = getTrap(TRAP_GET_PROTOTYPE_OF);
        if (trap != null) {
            Object objCallTrap = callTrap(trap, new Object[]{targetThrowIfRevoked});
            if (Undefined.SCRIPTABLE_UNDEFINED == null || Undefined.isUndefined(objCallTrap) || ScriptRuntime.isSymbol(objCallTrap)) {
                throw ScriptRuntime.typeErrorById("msg.arg.not.object", ScriptRuntime.typeof(objCallTrap));
            }
            Scriptable scriptableEnsureScriptable = ensureScriptable(objCallTrap);
            if (targetThrowIfRevoked.isExtensible() || objCallTrap == targetThrowIfRevoked.getPrototype()) {
                return scriptableEnsureScriptable;
            }
            throw ScriptRuntime.typeError("getPrototypeOf trap has to return the original prototype");
        }
        return targetThrowIfRevoked.getPrototype();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPrototypeDirect(Scriptable scriptable) {
        super.setPrototype(scriptable);
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public void setPrototype(Scriptable scriptable) {
        ScriptableObject targetThrowIfRevoked = getTargetThrowIfRevoked();
        Callable trap = getTrap(TRAP_SET_PROTOTYPE_OF);
        if (trap != null) {
            if (ScriptRuntime.toBoolean(callTrap(trap, new Object[]{targetThrowIfRevoked, scriptable}))) {
                targetThrowIfRevoked.isExtensible();
                return;
            }
            return;
        }
        targetThrowIfRevoked.setPrototype(scriptable);
    }

    @Override // org.mozilla.javascript.Callable
    public Object call(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        ScriptableObject targetThrowIfRevoked = getTargetThrowIfRevoked();
        Scriptable scriptableNewArray = context.newArray(scriptable, objArr);
        Callable trap = getTrap(TRAP_APPLY);
        return trap != null ? callTrap(trap, new Object[]{targetThrowIfRevoked, scriptable2, scriptableNewArray}) : ScriptRuntime.applyOrCall(true, context, scriptable, targetThrowIfRevoked, new Object[]{scriptable2, scriptableNewArray});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static NativeProxy constructor(Context context, Scriptable scriptable, Object[] objArr) {
        if (objArr.length < 2) {
            throw ScriptRuntime.typeErrorById("msg.method.missing.parameter", "Proxy.ctor", ExifInterface.GPS_MEASUREMENT_2D, Integer.toString(objArr.length));
        }
        NativeProxy nativeProxy = new NativeProxy(ensureScriptableObjectButNotSymbol(objArr[0]), ensureScriptableObjectButNotSymbol(objArr[1]));
        nativeProxy.setPrototypeDirect(ScriptableObject.getClassPrototype(scriptable, PROXY_TAG));
        nativeProxy.setParentScope(scriptable);
        return nativeProxy;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object revocable(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (!ScriptRuntime.isObject(scriptable2)) {
            throw ScriptRuntime.typeErrorById("msg.arg.not.object", ScriptRuntime.typeof(scriptable2));
        }
        NativeProxy nativeProxyConstructor = constructor(context, scriptable, objArr);
        Scriptable scriptable3 = (NativeObject) context.newObject(scriptable);
        scriptable3.put("proxy", scriptable3, nativeProxyConstructor);
        scriptable3.put("revoke", scriptable3, new LambdaFunction(scriptable, "", 0, new Revoker(nativeProxyConstructor)));
        return scriptable3;
    }

    private Callable getTrap(String str) {
        Object property = ScriptableObject.getProperty(this.handlerObj, str);
        if (Scriptable.NOT_FOUND == property || property == null || Undefined.isUndefined(property)) {
            return null;
        }
        if (!(property instanceof Callable)) {
            throw ScriptRuntime.notFunctionError(property, str);
        }
        return (Callable) property;
    }

    private Object callTrap(Callable callable, Object[] objArr) {
        Context context = Context.getContext();
        Scriptable scriptable = this.handlerObj;
        return callable.call(context, scriptable, scriptable, objArr);
    }

    ScriptableObject getTargetThrowIfRevoked() {
        ScriptableObject scriptableObject = this.targetObj;
        if (scriptableObject != null) {
            return scriptableObject;
        }
        throw ScriptRuntime.typeError("Illegal operation attempted on a revoked proxy");
    }
}
