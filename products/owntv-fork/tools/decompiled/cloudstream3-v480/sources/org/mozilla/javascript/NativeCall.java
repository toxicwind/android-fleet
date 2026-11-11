package org.mozilla.javascript;

/* JADX INFO: loaded from: classes8.dex */
public final class NativeCall extends IdScriptableObject {
    private static final Object CALL_TAG = "Call";
    private static final int Id_constructor = 1;
    private static final int MAX_PROTOTYPE_ID = 1;
    private static final long serialVersionUID = -7471457301304454454L;
    private Arguments arguments;
    NativeFunction function;
    private Scriptable homeObject;
    boolean isArrow;
    boolean isStrict;
    Object[] originalArgs;
    transient NativeCall parentActivationCall;

    static void init(Scriptable scriptable, boolean z) {
        new NativeCall().exportAsJSClass(1, scriptable, z);
    }

    NativeCall() {
    }

    NativeCall(NativeFunction nativeFunction, Context context, Scriptable scriptable, Object[] objArr, boolean z, boolean z2, boolean z3, Scriptable scriptable2) {
        Object[] objArr2;
        this.function = nativeFunction;
        this.homeObject = scriptable2;
        this.isArrow = z;
        setParentScope(scriptable);
        this.originalArgs = objArr == null ? ScriptRuntime.emptyArgs : objArr;
        this.isStrict = z2;
        int paramAndVarCount = nativeFunction.getParamAndVarCount();
        int paramCount = nativeFunction.getParamCount();
        if (paramAndVarCount != 0) {
            int i = 0;
            if (z3) {
                if (objArr.length >= paramCount) {
                    objArr2 = new Object[objArr.length - paramCount];
                    System.arraycopy(objArr, paramCount, objArr2, 0, objArr.length - paramCount);
                } else {
                    objArr2 = ScriptRuntime.emptyArgs;
                }
                while (i < paramCount) {
                    defineProperty(nativeFunction.getParamOrVarName(i), i < objArr.length ? objArr[i] : Undefined.instance, 4);
                    i++;
                }
                defineProperty(nativeFunction.getParamOrVarName(paramCount), context.newArray(scriptable, objArr2), 4);
            } else {
                while (i < paramCount) {
                    defineProperty(nativeFunction.getParamOrVarName(i), i < objArr.length ? objArr[i] : Undefined.instance, 4);
                    i++;
                }
            }
        }
        if (!super.has("arguments", this) && !z) {
            Arguments arguments = new Arguments(this);
            this.arguments = arguments;
            defineProperty("arguments", arguments, 4);
        }
        if (paramAndVarCount != 0) {
            while (paramCount < paramAndVarCount) {
                String paramOrVarName = nativeFunction.getParamOrVarName(paramCount);
                if (!super.has(paramOrVarName, this)) {
                    if (nativeFunction.getParamOrVarConst(paramCount)) {
                        defineProperty(paramOrVarName, Undefined.instance, 13);
                    } else if (!(nativeFunction instanceof InterpretedFunction) || ((InterpretedFunction) nativeFunction).hasFunctionNamed(paramOrVarName)) {
                        defineProperty(paramOrVarName, Undefined.instance, 4);
                    }
                }
                paramCount++;
            }
        }
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public String getClassName() {
        return "Call";
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected int findPrototypeId(String str) {
        return str.equals("constructor") ? 1 : 0;
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected void initPrototypeId(int i) {
        if (i == 1) {
            initPrototypeMethod(CALL_TAG, i, "constructor", 1);
            return;
        }
        throw new IllegalArgumentException(String.valueOf(i));
    }

    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.IdFunctionCall
    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (!idFunctionObject.hasTag(CALL_TAG)) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int iMethodId = idFunctionObject.methodId();
        if (iMethodId != 1) {
            throw new IllegalArgumentException(String.valueOf(iMethodId));
        }
        if (scriptable2 != null) {
            throw Context.reportRuntimeErrorById("msg.only.from.new", "Call");
        }
        ScriptRuntime.checkDeprecated(context, "Call");
        NativeCall nativeCall = new NativeCall();
        nativeCall.setPrototype(getObjectPrototype(scriptable));
        return nativeCall;
    }

    public void defineAttributesForArguments() {
        Arguments arguments = this.arguments;
        if (arguments != null) {
            arguments.defineAttributesForStrictMode();
        }
    }

    public Scriptable getHomeObject() {
        return this.homeObject;
    }
}
