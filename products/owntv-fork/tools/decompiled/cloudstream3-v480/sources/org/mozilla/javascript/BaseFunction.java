package org.mozilla.javascript;

import androidx.core.app.NotificationCompat;
import java.util.EnumSet;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: classes8.dex */
public class BaseFunction extends IdScriptableObject implements Function {
    private static final String FUNCTION_CLASS = "Function";
    private static final Object FUNCTION_TAG = FUNCTION_CLASS;
    static final String GENERATOR_FUNCTION_CLASS = "__GeneratorFunction";
    private static final int Id_apply = 4;
    protected static final int Id_arguments = 5;
    protected static final int Id_arity = 2;
    private static final int Id_bind = 6;
    private static final int Id_call = 5;
    private static final int Id_constructor = 1;
    protected static final int Id_length = 1;
    protected static final int Id_name = 3;
    protected static final int Id_prototype = 4;
    private static final int Id_toSource = 3;
    private static final int Id_toString = 2;
    protected static final int MAX_INSTANCE_ID = 5;
    private static final int MAX_PROTOTYPE_ID = 6;
    private static final long serialVersionUID = 5311394446546053859L;
    private int argumentsAttributes;
    private Object argumentsObj;
    private int arityPropertyAttributes;
    private Scriptable homeObject;
    private boolean isGeneratorFunction;
    private int lengthPropertyAttributes;
    private int namePropertyAttributes;
    private String nameValue;
    private Object prototypeProperty;
    private int prototypePropertyAttributes;

    public int getArity() {
        return 0;
    }

    public int getLength() {
        return 0;
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected int getMaxInstanceId() {
        return 5;
    }

    protected boolean hasDefaultParameters() {
        return false;
    }

    static void init(Context context, Scriptable scriptable, boolean z) {
        BaseFunction baseFunction = new BaseFunction();
        baseFunction.prototypePropertyAttributes = 7;
        if (context.getLanguageVersion() >= 200) {
            baseFunction.setStandardPropertyAttributes(3);
        }
        baseFunction.exportAsJSClass(6, scriptable, z);
    }

    @Deprecated
    static void init(Scriptable scriptable, boolean z) {
        init(Context.getContext(), scriptable, z);
    }

    static Object initAsGeneratorFunction(Scriptable scriptable, boolean z) {
        BaseFunction baseFunction = new BaseFunction(true);
        baseFunction.prototypePropertyAttributes = 5;
        baseFunction.exportAsJSClass(6, scriptable, z);
        return ScriptableObject.getProperty(scriptable, GENERATOR_FUNCTION_CLASS);
    }

    public BaseFunction() {
        this.argumentsObj = NOT_FOUND;
        this.nameValue = null;
        this.isGeneratorFunction = false;
        this.homeObject = null;
        this.prototypePropertyAttributes = 6;
        this.argumentsAttributes = 6;
        this.arityPropertyAttributes = 7;
        this.namePropertyAttributes = 3;
        this.lengthPropertyAttributes = 7;
    }

    public BaseFunction(boolean z) {
        this.argumentsObj = NOT_FOUND;
        this.nameValue = null;
        this.homeObject = null;
        this.prototypePropertyAttributes = 6;
        this.argumentsAttributes = 6;
        this.arityPropertyAttributes = 7;
        this.namePropertyAttributes = 3;
        this.lengthPropertyAttributes = 7;
        this.isGeneratorFunction = z;
    }

    public BaseFunction(Scriptable scriptable, Scriptable scriptable2) {
        super(scriptable, scriptable2);
        this.argumentsObj = NOT_FOUND;
        this.nameValue = null;
        this.isGeneratorFunction = false;
        this.homeObject = null;
        this.prototypePropertyAttributes = 6;
        this.argumentsAttributes = 6;
        this.arityPropertyAttributes = 7;
        this.namePropertyAttributes = 3;
        this.lengthPropertyAttributes = 7;
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public String getClassName() {
        return isGeneratorFunction() ? GENERATOR_FUNCTION_CLASS : FUNCTION_CLASS;
    }

    protected boolean isGeneratorFunction() {
        return this.isGeneratorFunction;
    }

    @Override // org.mozilla.javascript.ScriptableObject
    public String getTypeOf() {
        return avoidObjectDetection() ? "undefined" : "function";
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public boolean hasInstance(Scriptable scriptable) {
        Object property = ScriptableObject.getProperty(this, "prototype");
        if (property instanceof Scriptable) {
            return ScriptRuntime.jsDelegatesTo(scriptable, (Scriptable) property);
        }
        throw ScriptRuntime.typeErrorById("msg.instanceof.bad.prototype", getFunctionName());
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected int findInstanceIdInfo(String str) {
        str.hashCode();
        switch (str) {
            case "arguments":
                return instanceIdInfo(this.argumentsAttributes, 5);
            case "length":
                int i = this.lengthPropertyAttributes;
                if (i >= 0) {
                    return instanceIdInfo(i, 1);
                }
                break;
            case "prototype":
                if (hasPrototypeProperty()) {
                    return instanceIdInfo(this.prototypePropertyAttributes, 4);
                }
                break;
            case "name":
                int i2 = this.namePropertyAttributes;
                if (i2 >= 0) {
                    return instanceIdInfo(i2, 3);
                }
                break;
            case "arity":
                int i3 = this.arityPropertyAttributes;
                if (i3 >= 0) {
                    return instanceIdInfo(i3, 2);
                }
                break;
        }
        return super.findInstanceIdInfo(str);
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected String getInstanceIdName(int i) {
        if (i == 1) {
            return "length";
        }
        if (i == 2) {
            return "arity";
        }
        if (i == 3) {
            return "name";
        }
        if (i == 4) {
            return "prototype";
        }
        if (i == 5) {
            return "arguments";
        }
        return super.getInstanceIdName(i);
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected Object getInstanceIdValue(int i) {
        if (i == 1) {
            return this.lengthPropertyAttributes >= 0 ? Integer.valueOf(getLength()) : NOT_FOUND;
        }
        if (i == 2) {
            return this.arityPropertyAttributes >= 0 ? Integer.valueOf(getArity()) : NOT_FOUND;
        }
        if (i == 3) {
            if (this.namePropertyAttributes >= 0) {
                String str = this.nameValue;
                return str != null ? str : getFunctionName();
            }
            return NOT_FOUND;
        }
        if (i == 4) {
            return getPrototypeProperty();
        }
        if (i == 5) {
            return getArguments();
        }
        return super.getInstanceIdValue(i);
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected void setInstanceIdValue(int i, Object obj) {
        if (i == 1) {
            if (obj == NOT_FOUND) {
                this.lengthPropertyAttributes = -1;
                return;
            }
            return;
        }
        if (i == 2) {
            if (obj == NOT_FOUND) {
                this.arityPropertyAttributes = -1;
                return;
            }
            return;
        }
        if (i == 3) {
            if (obj == NOT_FOUND) {
                this.namePropertyAttributes = -1;
                this.nameValue = null;
                return;
            } else if (obj instanceof CharSequence) {
                this.nameValue = ScriptRuntime.toString(obj);
                return;
            } else {
                this.nameValue = "";
                return;
            }
        }
        if (i == 4) {
            if ((this.prototypePropertyAttributes & 1) == 0) {
                if (obj == null) {
                    obj = UniqueTag.NULL_VALUE;
                }
                this.prototypeProperty = obj;
                return;
            }
            return;
        }
        if (i == 5) {
            if (obj == NOT_FOUND) {
                Kit.codeBug();
            }
            if (defaultHas("arguments")) {
                defaultPut("arguments", obj);
                return;
            } else {
                if ((this.argumentsAttributes & 1) == 0) {
                    this.argumentsObj = obj;
                    return;
                }
                return;
            }
        }
        super.setInstanceIdValue(i, obj);
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected void setInstanceIdAttributes(int i, int i2) {
        if (i == 1) {
            this.lengthPropertyAttributes = i2;
            return;
        }
        if (i == 2) {
            this.arityPropertyAttributes = i2;
            return;
        }
        if (i == 3) {
            this.namePropertyAttributes = i2;
            return;
        }
        if (i == 4) {
            this.prototypePropertyAttributes = i2;
        } else if (i == 5) {
            this.argumentsAttributes = i2;
        } else {
            super.setInstanceIdAttributes(i, i2);
        }
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected void fillConstructorProperties(IdFunctionObject idFunctionObject) {
        idFunctionObject.setPrototype(this);
        super.fillConstructorProperties(idFunctionObject);
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected void initPrototypeId(int i) {
        String str;
        int i2 = 1;
        switch (i) {
            case 1:
                str = "constructor";
                break;
            case 2:
                i2 = 0;
                str = "toString";
                break;
            case 3:
                str = "toSource";
                break;
            case 4:
                i2 = 2;
                str = "apply";
                break;
            case 5:
                str = NotificationCompat.CATEGORY_CALL;
                break;
            case 6:
                str = "bind";
                break;
            default:
                throw new IllegalArgumentException(String.valueOf(i));
        }
        initPrototypeMethod(FUNCTION_TAG, i, str, i2);
    }

    static boolean isApply(IdFunctionObject idFunctionObject) {
        return idFunctionObject.hasTag(FUNCTION_TAG) && idFunctionObject.methodId() == 4;
    }

    static boolean isApplyOrCall(IdFunctionObject idFunctionObject) {
        if (!idFunctionObject.hasTag(FUNCTION_TAG)) {
            return false;
        }
        int iMethodId = idFunctionObject.methodId();
        return iMethodId == 4 || iMethodId == 5;
    }

    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.IdFunctionCall
    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        int int32;
        Object[] objArr2;
        Scriptable objectOrNull;
        if (!idFunctionObject.hasTag(FUNCTION_TAG)) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int iMethodId = idFunctionObject.methodId();
        int i = 0;
        switch (iMethodId) {
            case 1:
                return jsConstructor(context, scriptable, objArr);
            case 2:
                return realFunction(scriptable2, idFunctionObject).decompile(ScriptRuntime.toInt32(objArr, 0), EnumSet.noneOf(DecompilerFlag.class));
            case 3:
                BaseFunction baseFunctionRealFunction = realFunction(scriptable2, idFunctionObject);
                EnumSet<DecompilerFlag> enumSetOf = EnumSet.of(DecompilerFlag.TO_SOURCE);
                if (objArr.length != 0 && (int32 = ScriptRuntime.toInt32(objArr[0])) >= 0) {
                    enumSetOf = EnumSet.noneOf(DecompilerFlag.class);
                    i = int32;
                }
                return baseFunctionRealFunction.decompile(i, enumSetOf);
            case 4:
            case 5:
                return ScriptRuntime.applyOrCall(iMethodId == 4, context, scriptable, scriptable2, objArr);
            case 6:
                if (!(scriptable2 instanceof Callable)) {
                    throw ScriptRuntime.notFunctionError(scriptable2);
                }
                Callable callable = (Callable) scriptable2;
                int length = objArr.length;
                if (length > 0) {
                    objectOrNull = ScriptRuntime.toObjectOrNull(context, objArr[0], scriptable);
                    int i2 = length - 1;
                    objArr2 = new Object[i2];
                    System.arraycopy(objArr, 1, objArr2, 0, i2);
                } else {
                    objArr2 = ScriptRuntime.emptyArgs;
                    objectOrNull = null;
                }
                return new BoundFunction(context, scriptable, callable, objectOrNull, objArr2);
            default:
                throw new IllegalArgumentException(String.valueOf(iMethodId));
        }
    }

    private static BaseFunction realFunction(Scriptable scriptable, IdFunctionObject idFunctionObject) {
        if (scriptable == null) {
            throw ScriptRuntime.notFunctionError(null);
        }
        Object defaultValue = scriptable.getDefaultValue(ScriptRuntime.FunctionClass);
        if (defaultValue instanceof Delegator) {
            defaultValue = ((Delegator) defaultValue).getDelegee();
        }
        return (BaseFunction) ensureType(defaultValue, BaseFunction.class, idFunctionObject);
    }

    public void setImmunePrototypeProperty(Object obj) {
        if ((this.prototypePropertyAttributes & 1) != 0) {
            throw new IllegalStateException();
        }
        if (obj == null) {
            obj = UniqueTag.NULL_VALUE;
        }
        this.prototypeProperty = obj;
        this.prototypePropertyAttributes = 7;
    }

    protected Scriptable getClassPrototype() {
        Object prototypeProperty = getPrototypeProperty();
        if (prototypeProperty instanceof Scriptable) {
            return (Scriptable) prototypeProperty;
        }
        return ScriptableObject.getObjectPrototype(this);
    }

    public Object call(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        return Undefined.instance;
    }

    public Scriptable construct(Context context, Scriptable scriptable, Object[] objArr) {
        Scriptable parentScope;
        Scriptable classPrototype;
        Scriptable scriptableCreateObject = createObject(context, scriptable);
        if (scriptableCreateObject != null) {
            Object objCall = call(context, scriptable, scriptableCreateObject, objArr);
            return objCall instanceof Scriptable ? (Scriptable) objCall : scriptableCreateObject;
        }
        Object objCall2 = call(context, scriptable, null, objArr);
        if (!(objCall2 instanceof Scriptable)) {
            throw new IllegalStateException("Bad implementation of call as constructor, name=" + getFunctionName() + " in " + getClass().getName());
        }
        Scriptable scriptable2 = (Scriptable) objCall2;
        if (scriptable2.getPrototype() == null && scriptable2 != (classPrototype = getClassPrototype())) {
            scriptable2.setPrototype(classPrototype);
        }
        if (scriptable2.getParentScope() == null && scriptable2 != (parentScope = getParentScope())) {
            scriptable2.setParentScope(parentScope);
        }
        return scriptable2;
    }

    public Scriptable createObject(Context context, Scriptable scriptable) {
        NativeObject nativeObject = new NativeObject();
        nativeObject.setPrototype(getClassPrototype());
        nativeObject.setParentScope(getParentScope());
        return nativeObject;
    }

    String decompile(int i, EnumSet<DecompilerFlag> enumSet) {
        StringBuilder sb = new StringBuilder();
        boolean zContains = enumSet.contains(DecompilerFlag.ONLY_BODY);
        if (!zContains) {
            sb.append("function ");
            sb.append(getFunctionName());
            sb.append("() {\n\t");
        }
        sb.append("[native code, arity=");
        sb.append(getArity());
        sb.append("]\n");
        if (!zContains) {
            sb.append("}\n");
        }
        return sb.toString();
    }

    public String getFunctionName() {
        return "";
    }

    public void setStandardPropertyAttributes(int i) {
        this.namePropertyAttributes = i;
        this.lengthPropertyAttributes = i;
        this.arityPropertyAttributes = i;
    }

    public void setPrototypePropertyAttributes(int i) {
        this.prototypePropertyAttributes = i;
    }

    protected boolean hasPrototypeProperty() {
        return this.prototypeProperty != null || (this instanceof NativeFunction);
    }

    public Object getPrototypeProperty() {
        Object obj = this.prototypeProperty;
        if (obj == null) {
            if (this instanceof NativeFunction) {
                return setupDefaultPrototype();
            }
            return Undefined.instance;
        }
        if (obj == UniqueTag.NULL_VALUE) {
            return null;
        }
        return obj;
    }

    protected void setPrototypeProperty(Object obj) {
        this.prototypeProperty = obj;
    }

    protected synchronized Object setupDefaultPrototype() {
        Object obj = this.prototypeProperty;
        if (obj != null) {
            return obj;
        }
        NativeObject nativeObject = new NativeObject();
        nativeObject.setParentScope(getParentScope());
        this.prototypeProperty = nativeObject;
        Scriptable objectPrototype = getObjectPrototype(this);
        if (objectPrototype != nativeObject) {
            nativeObject.setPrototype(objectPrototype);
        }
        nativeObject.defineProperty("constructor", this, 2);
        return nativeObject;
    }

    private Object getArguments() {
        Object objDefaultGet = defaultHas("arguments") ? defaultGet("arguments") : this.argumentsObj;
        if (objDefaultGet != NOT_FOUND) {
            return objDefaultGet;
        }
        Scriptable scriptableFindFunctionActivation = ScriptRuntime.findFunctionActivation(Context.getContext(), this);
        if (scriptableFindFunctionActivation == null) {
            return null;
        }
        return scriptableFindFunctionActivation.get("arguments", scriptableFindFunctionActivation);
    }

    private Object jsConstructor(Context context, Scriptable scriptable, Object[] objArr) {
        int i;
        int length = objArr.length;
        StringBuilder sb = new StringBuilder("function ");
        if (isGeneratorFunction()) {
            sb.append("* ");
        }
        if (context.getLanguageVersion() != 120) {
            sb.append("anonymous");
        }
        sb.append('(');
        int i2 = 0;
        while (true) {
            i = length - 1;
            if (i2 >= i) {
                break;
            }
            if (i2 > 0) {
                sb.append(AbstractJsonLexerKt.COMMA);
            }
            sb.append(ScriptRuntime.toString(objArr[i2]));
            i2++;
        }
        sb.append(") {");
        if (length != 0) {
            sb.append(ScriptRuntime.toString(objArr[i]));
        }
        sb.append("\n}");
        String string = sb.toString();
        int[] iArr = new int[1];
        String sourcePositionFromStack = Context.getSourcePositionFromStack(iArr);
        if (sourcePositionFromStack == null) {
            iArr[0] = 1;
            sourcePositionFromStack = "<eval'ed string>";
        }
        String strMakeUrlForGeneratedScript = ScriptRuntime.makeUrlForGeneratedScript(false, sourcePositionFromStack, iArr[0]);
        Scriptable topLevelScope = ScriptableObject.getTopLevelScope(scriptable);
        ErrorReporter errorReporterForEval = DefaultErrorReporter.forEval(context.getErrorReporter());
        Evaluator evaluatorCreateInterpreter = Context.createInterpreter();
        if (evaluatorCreateInterpreter == null) {
            throw new JavaScriptException("Interpreter not present", sourcePositionFromStack, iArr[0]);
        }
        return context.compileFunction(topLevelScope, string, evaluatorCreateInterpreter, errorReporterForEval, strMakeUrlForGeneratedScript, 1, null);
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
            case "bind":
                return 6;
            case "call":
                return 5;
            case "apply":
                return 4;
            default:
                return 0;
        }
    }

    public void setHomeObject(Scriptable scriptable) {
        this.homeObject = scriptable;
    }

    public Scriptable getHomeObject() {
        return this.homeObject;
    }
}
