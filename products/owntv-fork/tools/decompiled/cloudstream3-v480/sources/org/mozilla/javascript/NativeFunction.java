package org.mozilla.javascript;

import java.util.EnumSet;
import org.mozilla.javascript.debug.DebuggableScript;

/* JADX INFO: loaded from: classes8.dex */
public abstract class NativeFunction extends BaseFunction {
    private static final long serialVersionUID = 8713897114082216401L;

    public DebuggableScript getDebuggableView() {
        return null;
    }

    protected abstract int getLanguageVersion();

    protected abstract int getParamAndVarCount();

    protected abstract int getParamCount();

    protected boolean getParamOrVarConst(int i) {
        return false;
    }

    protected abstract String getParamOrVarName(int i);

    public String getRawSource() {
        return null;
    }

    public final void initScriptFunction(Context context, Scriptable scriptable) {
        initScriptFunction(context, scriptable, isGeneratorFunction());
    }

    public final void initScriptFunction(Context context, Scriptable scriptable, boolean z) {
        ScriptRuntime.setFunctionProtoAndParent(this, context, scriptable, z);
    }

    @Override // org.mozilla.javascript.BaseFunction
    final String decompile(int i, EnumSet<DecompilerFlag> enumSet) {
        String rawSource = getRawSource();
        return rawSource != null ? rawSource : super.decompile(i, enumSet);
    }

    @Override // org.mozilla.javascript.BaseFunction
    public int getLength() {
        NativeCall nativeCallFindFunctionActivation;
        return (getLanguageVersion() == 120 && (nativeCallFindFunctionActivation = ScriptRuntime.findFunctionActivation(Context.getContext(), this)) != null) ? nativeCallFindFunctionActivation.originalArgs.length : getParamCount();
    }

    @Override // org.mozilla.javascript.BaseFunction
    public int getArity() {
        return getParamCount();
    }

    @Deprecated
    public String jsGet_name() {
        return getFunctionName();
    }

    public Object resumeGenerator(Context context, Scriptable scriptable, int i, Object obj, Object obj2) {
        throw new EvaluatorException("resumeGenerator() not implemented");
    }
}
