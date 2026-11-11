package org.mozilla.javascript;

import java.util.function.BiConsumer;

/* JADX INFO: loaded from: classes8.dex */
public class LambdaConstructor extends LambdaFunction {
    public static final int CONSTRUCTOR_DEFAULT = 3;
    public static final int CONSTRUCTOR_FUNCTION = 1;
    public static final int CONSTRUCTOR_NEW = 2;
    private static final long serialVersionUID = 2691205302914111400L;
    private final int flags;
    protected final transient Constructable targetConstructor;

    public LambdaConstructor(Scriptable scriptable, String str, int i, Constructable constructable) {
        super(scriptable, str, i, null);
        this.targetConstructor = constructable;
        this.flags = 3;
    }

    public LambdaConstructor(Scriptable scriptable, String str, int i, int i2, Constructable constructable) {
        super(scriptable, str, i, null);
        this.targetConstructor = constructable;
        this.flags = i2;
    }

    public LambdaConstructor(Scriptable scriptable, String str, int i, Callable callable, Constructable constructable) {
        super(scriptable, str, i, callable);
        this.targetConstructor = constructable;
        this.flags = 3;
    }

    protected Constructable getTargetConstructor() {
        return this.targetConstructor;
    }

    @Override // org.mozilla.javascript.LambdaFunction, org.mozilla.javascript.BaseFunction, org.mozilla.javascript.Function, org.mozilla.javascript.Callable
    public Object call(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if ((this.flags & 1) == 0) {
            throw ScriptRuntime.typeErrorById("msg.constructor.no.function", getFunctionName());
        }
        if (this.target == null) {
            return fireConstructor(context, scriptable, objArr);
        }
        return this.target.call(context, scriptable, scriptable2, objArr);
    }

    @Override // org.mozilla.javascript.LambdaFunction, org.mozilla.javascript.BaseFunction, org.mozilla.javascript.Function, org.mozilla.javascript.Constructable
    public Scriptable construct(Context context, Scriptable scriptable, Object[] objArr) {
        if ((this.flags & 2) != 0) {
            return fireConstructor(context, scriptable, objArr);
        }
        throw ScriptRuntime.typeErrorById("msg.no.new", getFunctionName());
    }

    private Scriptable fireConstructor(Context context, Scriptable scriptable, Object[] objArr) {
        Scriptable scriptableConstruct = this.targetConstructor.construct(context, scriptable, objArr);
        scriptableConstruct.setPrototype(getClassPrototype());
        scriptableConstruct.setParentScope(scriptable);
        return scriptableConstruct;
    }

    public void definePrototypeMethod(Scriptable scriptable, String str, int i, Callable callable) {
        getPrototypeScriptable().defineProperty(str, new LambdaFunction(scriptable, str, i, callable), 0);
    }

    public void definePrototypeMethod(Scriptable scriptable, String str, int i, Callable callable, int i2, int i3) {
        LambdaFunction lambdaFunction = new LambdaFunction(scriptable, str, i, callable);
        lambdaFunction.setStandardPropertyAttributes(i3);
        getPrototypeScriptable().defineProperty(str, lambdaFunction, i2);
    }

    public void definePrototypeMethod(Scriptable scriptable, SymbolKey symbolKey, int i, Callable callable, int i2, int i3) {
        LambdaFunction lambdaFunction = new LambdaFunction(scriptable, "[" + symbolKey.getName() + "]", i, callable);
        lambdaFunction.setStandardPropertyAttributes(i3);
        getPrototypeScriptable().defineProperty(symbolKey, lambdaFunction, i2);
    }

    public void definePrototypeProperty(String str, Object obj, int i) {
        getPrototypeScriptable().defineProperty(str, obj, i);
    }

    public void definePrototypeProperty(Symbol symbol, Object obj, int i) {
        getPrototypeScriptable().defineProperty(symbol, obj, i);
    }

    public void definePrototypeProperty(Context context, String str, java.util.function.Function<Scriptable, Object> function, int i) {
        getPrototypeScriptable().defineProperty(context, str, function, (BiConsumer<Scriptable, Object>) null, i);
    }

    public void definePrototypeProperty(Context context, String str, java.util.function.Function<Scriptable, Object> function, BiConsumer<Scriptable, Object> biConsumer, int i) {
        getPrototypeScriptable().defineProperty(context, str, function, biConsumer, i);
    }

    public void defineConstructorMethod(Scriptable scriptable, String str, int i, Callable callable, int i2) {
        defineProperty(str, new LambdaFunction(scriptable, str, i, callable), i2);
    }

    public void defineConstructorMethod(Scriptable scriptable, Symbol symbol, String str, int i, Callable callable, int i2) {
        defineProperty(symbol, new LambdaFunction(scriptable, str, i, callable), i2);
    }

    public void defineConstructorMethod(Scriptable scriptable, String str, int i, Callable callable, int i2, int i3) {
        LambdaFunction lambdaFunction = new LambdaFunction(scriptable, str, i, callable);
        lambdaFunction.setStandardPropertyAttributes(i3);
        defineProperty(str, lambdaFunction, i2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T convertThisObject(Scriptable scriptable, Class<T> cls) {
        if (cls.isInstance(scriptable)) {
            return scriptable;
        }
        throw ScriptRuntime.typeErrorById("msg.this.not.instance", new Object[0]);
    }

    private ScriptableObject getPrototypeScriptable() {
        Object prototypeProperty = getPrototypeProperty();
        if (!(prototypeProperty instanceof ScriptableObject)) {
            throw ScriptRuntime.typeError("Not properly a lambda constructor");
        }
        return (ScriptableObject) prototypeProperty;
    }
}
