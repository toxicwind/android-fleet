package org.mozilla.javascript;

import java.util.EnumSet;

/* JADX INFO: loaded from: classes8.dex */
public class ArrowFunction extends BaseFunction {
    private static final long serialVersionUID = -7377989503697220633L;
    private final Scriptable boundHomeObject;
    private final Scriptable boundThis;
    private final Callable targetFunction;

    public ArrowFunction(Context context, Scriptable scriptable, Callable callable, Scriptable scriptable2, Scriptable scriptable3) {
        this.targetFunction = callable;
        this.boundThis = scriptable2;
        this.boundHomeObject = scriptable3;
        ScriptRuntime.setFunctionProtoAndParent(this, context, scriptable, false);
        Object objTypeErrorThrower = ScriptRuntime.typeErrorThrower(context);
        NativeObject nativeObject = new NativeObject();
        ScriptRuntime.setBuiltinProtoAndParent(nativeObject, scriptable, TopLevel.Builtins.Object);
        nativeObject.put("get", nativeObject, objTypeErrorThrower);
        nativeObject.put("set", nativeObject, objTypeErrorThrower);
        nativeObject.put("enumerable", nativeObject, Boolean.FALSE);
        nativeObject.put("configurable", nativeObject, Boolean.FALSE);
        nativeObject.preventExtensions();
        defineOwnProperty(context, "caller", nativeObject, false);
        defineOwnProperty(context, "arguments", nativeObject, false);
    }

    @Override // org.mozilla.javascript.BaseFunction, org.mozilla.javascript.Function, org.mozilla.javascript.Callable
    public Object call(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        return this.targetFunction.call(context, scriptable, getCallThis(context), objArr);
    }

    @Override // org.mozilla.javascript.BaseFunction, org.mozilla.javascript.Function, org.mozilla.javascript.Constructable
    public Scriptable construct(Context context, Scriptable scriptable, Object[] objArr) {
        throw ScriptRuntime.typeErrorById("msg.not.ctor", decompile(0, EnumSet.noneOf(DecompilerFlag.class)));
    }

    @Override // org.mozilla.javascript.BaseFunction, org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public boolean hasInstance(Scriptable scriptable) {
        Callable callable = this.targetFunction;
        if (callable instanceof Function) {
            return ((Function) callable).hasInstance(scriptable);
        }
        throw ScriptRuntime.typeErrorById("msg.not.ctor", new Object[0]);
    }

    @Override // org.mozilla.javascript.BaseFunction
    public int getLength() {
        Callable callable = this.targetFunction;
        if (callable instanceof BaseFunction) {
            return ((BaseFunction) callable).getLength();
        }
        return 0;
    }

    @Override // org.mozilla.javascript.BaseFunction
    public int getArity() {
        return getLength();
    }

    @Override // org.mozilla.javascript.BaseFunction
    String decompile(int i, EnumSet<DecompilerFlag> enumSet) {
        Callable callable = this.targetFunction;
        if (callable instanceof BaseFunction) {
            return ((BaseFunction) callable).decompile(i, enumSet);
        }
        return super.decompile(i, enumSet);
    }

    Scriptable getCallThis(Context context) {
        Scriptable scriptable = this.boundThis;
        return scriptable != null ? scriptable : ScriptRuntime.getTopCallScope(context);
    }

    Scriptable getBoundHomeObject() {
        return this.boundHomeObject;
    }

    Callable getTargetFunction() {
        return this.targetFunction;
    }

    static boolean equalObjectGraphs(ArrowFunction arrowFunction, ArrowFunction arrowFunction2, EqualObjectGraphs equalObjectGraphs) {
        return equalObjectGraphs.equalGraphs(arrowFunction.boundThis, arrowFunction2.boundThis) && equalObjectGraphs.equalGraphs(arrowFunction.targetFunction, arrowFunction2.targetFunction);
    }
}
