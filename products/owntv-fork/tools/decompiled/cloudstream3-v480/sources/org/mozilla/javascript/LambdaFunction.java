package org.mozilla.javascript;

/* JADX INFO: loaded from: classes8.dex */
public class LambdaFunction extends BaseFunction {
    private static final long serialVersionUID = -8388132362854748293L;
    private final int length;
    private final String name;
    protected final transient Callable target;

    public LambdaFunction(Scriptable scriptable, String str, int i, Callable callable) {
        this.target = callable;
        this.name = str;
        this.length = i;
        ScriptRuntime.setFunctionProtoAndParent(this, Context.getCurrentContext(), scriptable);
        setupDefaultPrototype();
    }

    public LambdaFunction(Scriptable scriptable, int i, Callable callable) {
        this.target = callable;
        this.length = i;
        this.name = "";
        ScriptRuntime.setFunctionProtoAndParent(this, Context.getCurrentContext(), scriptable);
    }

    @Override // org.mozilla.javascript.BaseFunction, org.mozilla.javascript.Function, org.mozilla.javascript.Callable
    public Object call(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        return this.target.call(context, scriptable, scriptable2, objArr);
    }

    @Override // org.mozilla.javascript.BaseFunction, org.mozilla.javascript.Function, org.mozilla.javascript.Constructable
    public Scriptable construct(Context context, Scriptable scriptable, Object[] objArr) {
        throw ScriptRuntime.typeErrorById("msg.no.new", getFunctionName());
    }

    @Override // org.mozilla.javascript.BaseFunction
    public int getLength() {
        return this.length;
    }

    @Override // org.mozilla.javascript.BaseFunction
    public int getArity() {
        return this.length;
    }

    @Override // org.mozilla.javascript.BaseFunction
    public String getFunctionName() {
        return this.name;
    }

    Callable getTarget() {
        return this.target;
    }
}
