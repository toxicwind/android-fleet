package org.mozilla.javascript;

/* JADX INFO: loaded from: classes8.dex */
public class ScriptRuntimeES6 {
    static /* synthetic */ Object lambda$addSymbolSpecies$0(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        return scriptable2;
    }

    public static Object requireObjectCoercible(Context context, Object obj, IdFunctionObject idFunctionObject) {
        if (obj == null || Undefined.isUndefined(obj)) {
            throw ScriptRuntime.typeErrorById("msg.called.null.or.undefined", idFunctionObject.getTag(), idFunctionObject.getFunctionName());
        }
        return obj;
    }

    public static void addSymbolSpecies(Context context, Scriptable scriptable, IdScriptableObject idScriptableObject) {
        ScriptableObject scriptableObject = (ScriptableObject) context.newObject(scriptable);
        ScriptableObject.putProperty((Scriptable) scriptableObject, "enumerable", (Object) false);
        ScriptableObject.putProperty((Scriptable) scriptableObject, "configurable", (Object) true);
        ScriptableObject.putProperty(scriptableObject, "get", new LambdaFunction(scriptable, "get [Symbol.species]", 0, new Callable() { // from class: org.mozilla.javascript.ScriptRuntimeES6$$ExternalSyntheticLambda0
            @Override // org.mozilla.javascript.Callable
            public final Object call(Context context2, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                return ScriptRuntimeES6.lambda$addSymbolSpecies$0(context2, scriptable2, scriptable3, objArr);
            }
        }));
        idScriptableObject.defineOwnProperty(context, SymbolKey.SPECIES, scriptableObject, false);
    }

    public static void addSymbolUnscopables(Context context, Scriptable scriptable, IdScriptableObject idScriptableObject) {
        ScriptableObject scriptableObject = (ScriptableObject) context.newObject(scriptable);
        ScriptableObject.putProperty((Scriptable) scriptableObject, "enumerable", (Object) false);
        ScriptableObject.putProperty((Scriptable) scriptableObject, "configurable", (Object) false);
        ScriptableObject.putProperty((Scriptable) scriptableObject, "writable", (Object) false);
        idScriptableObject.defineOwnProperty(context, SymbolKey.UNSCOPABLES, scriptableObject, false);
    }
}
