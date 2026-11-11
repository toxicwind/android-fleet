package org.mozilla.javascript;

import j$.util.Map;
import j$.util.function.Function$CC;
import java.util.HashMap;

/* JADX INFO: loaded from: classes8.dex */
public class NativeSymbol extends ScriptableObject implements Symbol {
    public static final String CLASS_NAME = "Symbol";
    private static final Object GLOBAL_TABLE_KEY = new Object();
    public static final String TYPE_NAME = "symbol";
    private static final long serialVersionUID = -589539749749830003L;
    private final SymbolKey key;
    private final NativeSymbol symbolData;

    public static void init(Context context, Scriptable scriptable, boolean z) {
        final LambdaConstructor lambdaConstructor = new LambdaConstructor(scriptable, CLASS_NAME, 0, 1, new Constructable() { // from class: org.mozilla.javascript.NativeSymbol$$ExternalSyntheticLambda0
            @Override // org.mozilla.javascript.Constructable
            public final Scriptable construct(Context context2, Scriptable scriptable2, Object[] objArr) {
                return NativeSymbol.js_constructor(context2, scriptable2, objArr);
            }
        });
        lambdaConstructor.setPrototypePropertyAttributes(7);
        lambdaConstructor.defineConstructorMethod(scriptable, "for", 1, new Callable() { // from class: org.mozilla.javascript.NativeSymbol$$ExternalSyntheticLambda1
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeSymbol.js_for(context2, scriptable2, objArr, lambdaConstructor);
            }
        }, 2, 3);
        lambdaConstructor.defineConstructorMethod(scriptable, "keyFor", 1, new Callable() { // from class: org.mozilla.javascript.NativeSymbol$$ExternalSyntheticLambda2
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeSymbol.js_keyFor(context2, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        lambdaConstructor.definePrototypeMethod(scriptable, "toString", 0, new Callable() { // from class: org.mozilla.javascript.NativeSymbol$$ExternalSyntheticLambda3
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeSymbol.js_toString(context2, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        lambdaConstructor.definePrototypeMethod(scriptable, "valueOf", 0, new Callable() { // from class: org.mozilla.javascript.NativeSymbol$$ExternalSyntheticLambda4
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeSymbol.js_valueOf(context2, scriptable2, scriptable3, objArr);
            }
        }, 2, 3);
        lambdaConstructor.definePrototypeMethod(scriptable, SymbolKey.TO_PRIMITIVE, 1, new Callable() { // from class: org.mozilla.javascript.NativeSymbol$$ExternalSyntheticLambda4
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return NativeSymbol.js_valueOf(context2, scriptable2, scriptable3, objArr);
            }
        }, 3, 3);
        lambdaConstructor.definePrototypeProperty(SymbolKey.TO_STRING_TAG, CLASS_NAME, 3);
        lambdaConstructor.definePrototypeProperty(context, "description", new java.util.function.Function() { // from class: org.mozilla.javascript.NativeSymbol$$ExternalSyntheticLambda5
            @Override // java.util.function.Function
            public /* synthetic */ java.util.function.Function andThen(java.util.function.Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return NativeSymbol.js_description((Scriptable) obj);
            }

            public /* synthetic */ java.util.function.Function compose(java.util.function.Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }, 3);
        ScriptableObject.defineProperty(scriptable, CLASS_NAME, lambdaConstructor, 2);
        createStandardSymbol(context, scriptable, lambdaConstructor, "iterator", SymbolKey.ITERATOR);
        createStandardSymbol(context, scriptable, lambdaConstructor, "species", SymbolKey.SPECIES);
        createStandardSymbol(context, scriptable, lambdaConstructor, "toStringTag", SymbolKey.TO_STRING_TAG);
        createStandardSymbol(context, scriptable, lambdaConstructor, "hasInstance", SymbolKey.HAS_INSTANCE);
        createStandardSymbol(context, scriptable, lambdaConstructor, "isConcatSpreadable", SymbolKey.IS_CONCAT_SPREADABLE);
        createStandardSymbol(context, scriptable, lambdaConstructor, "isRegExp", SymbolKey.IS_REGEXP);
        createStandardSymbol(context, scriptable, lambdaConstructor, "toPrimitive", SymbolKey.TO_PRIMITIVE);
        createStandardSymbol(context, scriptable, lambdaConstructor, "match", SymbolKey.MATCH);
        createStandardSymbol(context, scriptable, lambdaConstructor, "matchAll", SymbolKey.MATCH_ALL);
        createStandardSymbol(context, scriptable, lambdaConstructor, "replace", SymbolKey.REPLACE);
        createStandardSymbol(context, scriptable, lambdaConstructor, "search", SymbolKey.SEARCH);
        createStandardSymbol(context, scriptable, lambdaConstructor, "split", SymbolKey.SPLIT);
        createStandardSymbol(context, scriptable, lambdaConstructor, "unscopables", SymbolKey.UNSCOPABLES);
        if (z) {
            lambdaConstructor.sealObject();
        }
    }

    NativeSymbol(SymbolKey symbolKey) {
        this.key = symbolKey;
        this.symbolData = this;
    }

    public NativeSymbol(NativeSymbol nativeSymbol) {
        this.key = nativeSymbol.key;
        this.symbolData = nativeSymbol.symbolData;
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public String getClassName() {
        return CLASS_NAME;
    }

    private static NativeSymbol constructSymbol(Context context, Scriptable scriptable, LambdaConstructor lambdaConstructor, SymbolKey symbolKey) {
        return (NativeSymbol) lambdaConstructor.call(context, scriptable, null, new Object[]{Undefined.instance, symbolKey});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static NativeSymbol constructSymbol(Context context, Scriptable scriptable, LambdaConstructor lambdaConstructor, String str) {
        return (NativeSymbol) lambdaConstructor.call(context, scriptable, null, new Object[]{str});
    }

    private static void createStandardSymbol(Context context, Scriptable scriptable, LambdaConstructor lambdaConstructor, String str, SymbolKey symbolKey) {
        lambdaConstructor.defineProperty(str, constructSymbol(context, scriptable, lambdaConstructor, symbolKey), 7);
    }

    private static NativeSymbol getSelf(Scriptable scriptable) {
        return (NativeSymbol) LambdaConstructor.convertThisObject(scriptable, NativeSymbol.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static NativeSymbol js_constructor(Context context, Scriptable scriptable, Object[] objArr) {
        String string = (objArr.length <= 0 || Undefined.isUndefined(objArr[0])) ? null : ScriptRuntime.toString(objArr[0]);
        if (objArr.length > 1) {
            return new NativeSymbol((SymbolKey) objArr[1]);
        }
        return new NativeSymbol(new SymbolKey(string));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String js_toString(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        return getSelf(scriptable2).toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object js_valueOf(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        return getSelf(scriptable2).symbolData;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object js_description(Scriptable scriptable) {
        return getSelf(scriptable).getKey().getDescription();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object js_for(final Context context, final Scriptable scriptable, Object[] objArr, final LambdaConstructor lambdaConstructor) {
        final String string;
        if (objArr.length > 0) {
            string = ScriptRuntime.toString(objArr[0]);
        } else {
            string = ScriptRuntime.toString(Undefined.instance);
        }
        return Map.EL.computeIfAbsent(getGlobalMap(scriptable), string, new java.util.function.Function() { // from class: org.mozilla.javascript.NativeSymbol$$ExternalSyntheticLambda6
            @Override // java.util.function.Function
            public /* synthetic */ java.util.function.Function andThen(java.util.function.Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return NativeSymbol.constructSymbol(context, scriptable, lambdaConstructor, string);
            }

            public /* synthetic */ java.util.function.Function compose(java.util.function.Function function) {
                return Function$CC.$default$compose(this, function);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object js_keyFor(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        Object obj = objArr.length > 0 ? objArr[0] : Undefined.instance;
        if (!(obj instanceof NativeSymbol)) {
            throw ScriptRuntime.throwCustomError(context, scriptable, "TypeError", "Not a Symbol");
        }
        NativeSymbol nativeSymbol = (NativeSymbol) obj;
        for (java.util.Map.Entry<String, NativeSymbol> entry : getGlobalMap(scriptable).entrySet()) {
            if (entry.getValue().key == nativeSymbol.key) {
                return entry.getKey();
            }
        }
        return Undefined.instance;
    }

    public String toString() {
        return this.key.toString();
    }

    private static boolean isStrictMode() {
        Context currentContext = Context.getCurrentContext();
        return currentContext != null && currentContext.isStrictMode();
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public void put(String str, Scriptable scriptable, Object obj) {
        if (!isSymbol()) {
            super.put(str, scriptable, obj);
        } else if (isStrictMode()) {
            throw ScriptRuntime.typeErrorById("msg.no.assign.symbol.strict", new Object[0]);
        }
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public void put(int i, Scriptable scriptable, Object obj) {
        if (!isSymbol()) {
            super.put(i, scriptable, obj);
        } else if (isStrictMode()) {
            throw ScriptRuntime.typeErrorById("msg.no.assign.symbol.strict", new Object[0]);
        }
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.SymbolScriptable
    public void put(Symbol symbol, Scriptable scriptable, Object obj) {
        if (!isSymbol()) {
            super.put(symbol, scriptable, obj);
        } else if (isStrictMode()) {
            throw ScriptRuntime.typeErrorById("msg.no.assign.symbol.strict", new Object[0]);
        }
    }

    public boolean isSymbol() {
        return this.symbolData == this;
    }

    @Override // org.mozilla.javascript.ScriptableObject
    public String getTypeOf() {
        return isSymbol() ? TYPE_NAME : super.getTypeOf();
    }

    public int hashCode() {
        return this.key.hashCode();
    }

    public boolean equals(Object obj) {
        return this.key.equals(obj);
    }

    SymbolKey getKey() {
        return this.key;
    }

    private static java.util.Map<String, NativeSymbol> getGlobalMap(Scriptable scriptable) {
        ScriptableObject scriptableObject = (ScriptableObject) getTopLevelScope(scriptable);
        Object obj = GLOBAL_TABLE_KEY;
        java.util.Map<String, NativeSymbol> map = (java.util.Map) scriptableObject.getAssociatedValue(obj);
        if (map != null) {
            return map;
        }
        HashMap map2 = new HashMap();
        scriptableObject.associateValue(obj, map2);
        return map2;
    }
}
