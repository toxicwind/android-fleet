package org.mozilla.javascript;

/* JADX INFO: loaded from: classes8.dex */
final class NativeBoolean extends IdScriptableObject {
    private static final Object BOOLEAN_TAG = "Boolean";
    private static final int Id_constructor = 1;
    private static final int Id_toSource = 3;
    private static final int Id_toString = 2;
    private static final int Id_valueOf = 4;
    private static final int MAX_PROTOTYPE_ID = 4;
    private static final long serialVersionUID = -3716996899943880933L;
    private boolean booleanValue;

    static void init(Scriptable scriptable, boolean z) {
        new NativeBoolean(false).exportAsJSClass(4, scriptable, z);
    }

    NativeBoolean(boolean z) {
        this.booleanValue = z;
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public String getClassName() {
        return "Boolean";
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public Object getDefaultValue(Class<?> cls) {
        return cls == ScriptRuntime.BooleanClass ? ScriptRuntime.wrapBoolean(this.booleanValue) : super.getDefaultValue(cls);
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected void initPrototypeId(int i) {
        int i2;
        String str;
        if (i != 1) {
            i2 = 0;
            if (i == 2) {
                str = "toString";
            } else if (i == 3) {
                str = "toSource";
            } else if (i == 4) {
                str = "valueOf";
            } else {
                throw new IllegalArgumentException(String.valueOf(i));
            }
        } else {
            i2 = 1;
            str = "constructor";
        }
        initPrototypeMethod(BOOLEAN_TAG, i, str, i2);
    }

    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.IdFunctionCall
    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (!idFunctionObject.hasTag(BOOLEAN_TAG)) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int iMethodId = idFunctionObject.methodId();
        if (iMethodId == 1) {
            boolean z = false;
            if (objArr.length != 0) {
                Object obj = objArr[0];
                if (!(obj instanceof ScriptableObject) || !((ScriptableObject) obj).avoidObjectDetection()) {
                    z = ScriptRuntime.toBoolean(objArr[0]);
                }
            }
            if (scriptable2 == null) {
                return new NativeBoolean(z);
            }
            return ScriptRuntime.wrapBoolean(z);
        }
        boolean z2 = ((NativeBoolean) ensureType(scriptable2, NativeBoolean.class, idFunctionObject)).booleanValue;
        if (iMethodId == 2) {
            return z2 ? "true" : "false";
        }
        if (iMethodId == 3) {
            return z2 ? "(new Boolean(true))" : "(new Boolean(false))";
        }
        if (iMethodId == 4) {
            return ScriptRuntime.wrapBoolean(z2);
        }
        throw new IllegalArgumentException(String.valueOf(iMethodId));
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected int findPrototypeId(String str) {
        str.hashCode();
        switch (str) {
            case "toSource":
                return 3;
            case "toString":
                return 2;
            case "constructor":
                return 1;
            case "valueOf":
                return 4;
            default:
                return 0;
        }
    }
}
