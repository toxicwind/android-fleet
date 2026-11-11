package org.mozilla.javascript;

/* JADX INFO: loaded from: classes8.dex */
final class Arguments extends IdScriptableObject {
    private static final String FTAG = "Arguments";
    private static final int Id_callee = 1;
    private static final int Id_caller = 3;
    private static final int Id_length = 2;
    private static final int MAX_INSTANCE_ID = 3;
    private static final long serialVersionUID = 4275508002492040609L;
    private NativeCall activation;
    private Object[] args;
    private Object calleeObj;
    private Object callerObj;
    private Object lengthObj;
    private int callerAttr = 2;
    private int calleeAttr = 2;
    private int lengthAttr = 2;

    @Override // org.mozilla.javascript.IdScriptableObject
    protected int getMaxInstanceId() {
        return 3;
    }

    public Arguments(NativeCall nativeCall) {
        this.activation = nativeCall;
        Scriptable parentScope = nativeCall.getParentScope();
        setParentScope(parentScope);
        setPrototype(ScriptableObject.getObjectPrototype(parentScope));
        Object[] objArr = nativeCall.originalArgs;
        this.args = objArr;
        this.lengthObj = Integer.valueOf(objArr.length);
        NativeFunction nativeFunction = nativeCall.function;
        this.calleeObj = nativeFunction;
        int languageVersion = nativeFunction.getLanguageVersion();
        if (languageVersion <= 130 && languageVersion != 0) {
            this.callerObj = null;
        } else {
            this.callerObj = NOT_FOUND;
        }
        defineProperty(SymbolKey.ITERATOR, TopLevel.getBuiltinPrototype(ScriptableObject.getTopLevelScope(parentScope), TopLevel.Builtins.Array).get("values", parentScope), 2);
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public String getClassName() {
        return FTAG;
    }

    private Object arg(int i) {
        if (i >= 0) {
            Object[] objArr = this.args;
            if (objArr.length > i) {
                return objArr[i];
            }
        }
        return NOT_FOUND;
    }

    private void putIntoActivation(int i, Object obj) {
        String paramOrVarName = this.activation.function.getParamOrVarName(i);
        Scriptable scriptable = this.activation;
        scriptable.put(paramOrVarName, scriptable, obj);
    }

    private Object getFromActivation(int i) {
        String paramOrVarName = this.activation.function.getParamOrVarName(i);
        Scriptable scriptable = this.activation;
        return scriptable.get(paramOrVarName, scriptable);
    }

    private void replaceArg(int i, Object obj) {
        if (sharedWithActivation(i)) {
            putIntoActivation(i, obj);
        }
        synchronized (this) {
            if (this.args == this.activation.originalArgs) {
                this.args = (Object[]) this.args.clone();
            }
            this.args[i] = obj;
        }
    }

    private void removeArg(int i) {
        synchronized (this) {
            if (this.args[i] != NOT_FOUND) {
                if (this.args == this.activation.originalArgs) {
                    this.args = (Object[]) this.args.clone();
                }
                this.args[i] = NOT_FOUND;
            }
        }
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public boolean has(int i, Scriptable scriptable) {
        if (arg(i) != NOT_FOUND) {
            return true;
        }
        return super.has(i, scriptable);
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public Object get(int i, Scriptable scriptable) {
        Object objArg = arg(i);
        if (objArg == NOT_FOUND) {
            return super.get(i, scriptable);
        }
        return sharedWithActivation(i) ? getFromActivation(i) : objArg;
    }

    private boolean sharedWithActivation(int i) {
        NativeFunction nativeFunction;
        int paramCount;
        if (Context.getContext().isStrictMode() || (nativeFunction = this.activation.function) == null || nativeFunction.hasDefaultParameters() || i >= (paramCount = nativeFunction.getParamCount())) {
            return false;
        }
        if (i < paramCount - 1) {
            String paramOrVarName = nativeFunction.getParamOrVarName(i);
            for (int i2 = i + 1; i2 < paramCount; i2++) {
                if (paramOrVarName.equals(nativeFunction.getParamOrVarName(i2))) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public void put(int i, Scriptable scriptable, Object obj) {
        if (arg(i) == NOT_FOUND) {
            super.put(i, scriptable, obj);
        } else {
            replaceArg(i, obj);
        }
    }

    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public void put(String str, Scriptable scriptable, Object obj) {
        super.put(str, scriptable, obj);
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public void delete(int i) {
        if (i >= 0 && i < this.args.length) {
            removeArg(i);
        }
        super.delete(i);
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected int findInstanceIdInfo(String str) {
        int i;
        str.hashCode();
        int i2 = 0;
        switch (str) {
            case "callee":
                i2 = 1;
                break;
            case "caller":
                i2 = 3;
                break;
            case "length":
                i2 = 2;
                break;
        }
        if (Context.getContext().isStrictMode() && (i2 == 1 || i2 == 3)) {
            return super.findInstanceIdInfo(str);
        }
        if (i2 == 0) {
            return super.findInstanceIdInfo(str);
        }
        if (i2 == 1) {
            i = this.calleeAttr;
        } else if (i2 == 2) {
            i = this.lengthAttr;
        } else if (i2 == 3) {
            i = this.callerAttr;
        } else {
            throw new IllegalStateException();
        }
        return instanceIdInfo(i, i2);
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected String getInstanceIdName(int i) {
        if (i == 1) {
            return "callee";
        }
        if (i == 2) {
            return "length";
        }
        if (i != 3) {
            return null;
        }
        return "caller";
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected Object getInstanceIdValue(int i) {
        Scriptable scriptable;
        if (i == 1) {
            return this.calleeObj;
        }
        if (i == 2) {
            return this.lengthObj;
        }
        if (i == 3) {
            Object obj = this.callerObj;
            if (obj == UniqueTag.NULL_VALUE) {
                return null;
            }
            return (obj != null || (scriptable = this.activation.parentActivationCall) == null) ? obj : scriptable.get("arguments", scriptable);
        }
        return super.getInstanceIdValue(i);
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected void setInstanceIdValue(int i, Object obj) {
        if (i == 1) {
            this.calleeObj = obj;
            return;
        }
        if (i == 2) {
            this.lengthObj = obj;
        } else {
            if (i != 3) {
                super.setInstanceIdValue(i, obj);
                return;
            }
            if (obj == null) {
                obj = UniqueTag.NULL_VALUE;
            }
            this.callerObj = obj;
        }
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected void setInstanceIdAttributes(int i, int i2) {
        if (i == 1) {
            this.calleeAttr = i2;
            return;
        }
        if (i == 2) {
            this.lengthAttr = i2;
        } else if (i == 3) {
            this.callerAttr = i2;
        } else {
            super.setInstanceIdAttributes(i, i2);
        }
    }

    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.ScriptableObject
    Object[] getIds(boolean z, boolean z2) {
        int iIntValue;
        Object[] ids = super.getIds(z, z2);
        Object[] objArr = this.args;
        if (objArr.length != 0) {
            int length = objArr.length;
            boolean[] zArr = new boolean[length];
            int length2 = objArr.length;
            for (int i = 0; i != ids.length; i++) {
                Object obj = ids[i];
                if ((obj instanceof Integer) && (iIntValue = ((Integer) obj).intValue()) >= 0 && iIntValue < this.args.length && !zArr[iIntValue]) {
                    zArr[iIntValue] = true;
                    length2--;
                }
            }
            if (!z) {
                for (int i2 = 0; i2 < length; i2++) {
                    if (!zArr[i2] && super.has(i2, this)) {
                        zArr[i2] = true;
                        length2--;
                    }
                }
            }
            if (length2 != 0) {
                Object[] objArr2 = new Object[ids.length + length2];
                System.arraycopy(ids, 0, objArr2, length2, ids.length);
                int i3 = 0;
                for (int i4 = 0; i4 != this.args.length; i4++) {
                    if (!zArr[i4]) {
                        objArr2[i3] = Integer.valueOf(i4);
                        i3++;
                    }
                }
                if (i3 != length2) {
                    Kit.codeBug();
                }
                return objArr2;
            }
        }
        return ids;
    }

    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.ScriptableObject
    protected ScriptableObject getOwnPropertyDescriptor(Context context, Object obj) {
        if (ScriptRuntime.isSymbol(obj) || (obj instanceof Scriptable)) {
            return super.getOwnPropertyDescriptor(context, obj);
        }
        double number = ScriptRuntime.toNumber(obj);
        int i = (int) number;
        if (number != i) {
            return super.getOwnPropertyDescriptor(context, obj);
        }
        Object objArg = arg(i);
        if (objArg == NOT_FOUND) {
            return super.getOwnPropertyDescriptor(context, obj);
        }
        if (sharedWithActivation(i)) {
            objArg = getFromActivation(i);
        }
        if (super.has(i, this)) {
            ScriptableObject ownPropertyDescriptor = super.getOwnPropertyDescriptor(context, obj);
            ownPropertyDescriptor.put(ES6Iterator.VALUE_PROPERTY, ownPropertyDescriptor, objArg);
            return ownPropertyDescriptor;
        }
        Scriptable parentScope = getParentScope();
        if (parentScope != null) {
            this = parentScope;
        }
        return buildDataDescriptor(this, objArg, 0);
    }

    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.ScriptableObject
    protected boolean defineOwnProperty(Context context, Object obj, ScriptableObject scriptableObject, boolean z) {
        super.defineOwnProperty(context, obj, scriptableObject, z);
        if (ScriptRuntime.isSymbol(obj)) {
            return true;
        }
        double number = ScriptRuntime.toNumber(obj);
        int i = (int) number;
        if (number != i || arg(i) == NOT_FOUND) {
            return true;
        }
        if (isAccessorDescriptor(scriptableObject)) {
            removeArg(i);
            return true;
        }
        Object property = getProperty(scriptableObject, ES6Iterator.VALUE_PROPERTY);
        if (property == NOT_FOUND) {
            return true;
        }
        replaceArg(i, property);
        if (isFalse(getProperty(scriptableObject, "writable"))) {
            removeArg(i);
        }
        return true;
    }

    void defineAttributesForStrictMode() {
        if (Context.getContext().isStrictMode()) {
            setGetterOrSetter("caller", 0, new ThrowTypeError("caller"), true);
            setGetterOrSetter("caller", 0, new ThrowTypeError("caller"), false);
            setGetterOrSetter("callee", 0, new ThrowTypeError("callee"), true);
            setGetterOrSetter("callee", 0, new ThrowTypeError("callee"), false);
            setAttributes("caller", 6);
            setAttributes("callee", 6);
            this.callerObj = null;
            this.calleeObj = null;
        }
    }

    private static class ThrowTypeError extends BaseFunction {
        private static final long serialVersionUID = -744615873947395749L;
        private String propertyName;

        ThrowTypeError(String str) {
            this.propertyName = str;
            super.setInstanceIdAttributes(3, 7);
        }

        @Override // org.mozilla.javascript.BaseFunction, org.mozilla.javascript.Function, org.mozilla.javascript.Callable
        public Object call(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
            throw ScriptRuntime.typeErrorById("msg.arguments.not.access.strict", this.propertyName);
        }
    }
}
