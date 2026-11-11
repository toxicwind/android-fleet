package org.mozilla.javascript;

import io.ktor.http.ContentType;
import j$.util.function.Consumer$CC;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes8.dex */
final class NativeError extends IdScriptableObject {
    private static final int ConstructorId_captureStackTrace = -1;
    public static final int DEFAULT_STACK_LIMIT = -1;
    private static final Object ERROR_TAG = "Error";
    private static final int Id_constructor = 1;
    private static final int Id_toSource = 3;
    private static final int Id_toString = 2;
    private static final int MAX_PROTOTYPE_ID = 3;
    private static final String STACK_HIDE_KEY = "_stackHide";
    private static final String STACK_TAG = "stack";
    private static final long serialVersionUID = -5338413581437645187L;
    private Object stack;
    private RhinoException stackProvider;

    NativeError() {
    }

    static void init(Scriptable scriptable, boolean z) {
        NativeError nativeError = new NativeError();
        ScriptableObject.putProperty(nativeError, "name", "Error");
        ScriptableObject.putProperty(nativeError, ContentType.Message.TYPE, "");
        ScriptableObject.putProperty(nativeError, "fileName", "");
        ScriptableObject.putProperty((Scriptable) nativeError, "lineNumber", (Object) 0);
        nativeError.setAttributes("name", 2);
        nativeError.setAttributes(ContentType.Message.TYPE, 2);
        nativeError.exportAsJSClass(3, scriptable, z);
        NativeCallSite.init(nativeError, z);
    }

    static NativeError makeProto(Scriptable scriptable, IdFunctionObject idFunctionObject) {
        Scriptable scriptable2 = (Scriptable) idFunctionObject.get("prototype", idFunctionObject);
        NativeError nativeError = new NativeError();
        nativeError.setPrototype(scriptable2);
        nativeError.setParentScope(scriptable);
        return nativeError;
    }

    static NativeError make(Context context, Scriptable scriptable, IdFunctionObject idFunctionObject, Object[] objArr) {
        NativeError nativeErrorMakeProto = makeProto(scriptable, idFunctionObject);
        int length = objArr.length;
        if (length >= 1) {
            if (!Undefined.isUndefined(objArr[0])) {
                ScriptableObject.putProperty(nativeErrorMakeProto, ContentType.Message.TYPE, ScriptRuntime.toString(objArr[0]));
                nativeErrorMakeProto.setAttributes(ContentType.Message.TYPE, 2);
            }
            if (length >= 2) {
                Object obj = objArr[1];
                if (obj instanceof NativeObject) {
                    installCause((NativeObject) obj, nativeErrorMakeProto);
                } else {
                    ScriptableObject.putProperty(nativeErrorMakeProto, "fileName", ScriptRuntime.toString(obj));
                    if (length >= 3) {
                        ScriptableObject.putProperty(nativeErrorMakeProto, "lineNumber", Integer.valueOf(ScriptRuntime.toInt32(objArr[2])));
                    }
                }
            }
        }
        nativeErrorMakeProto.setStackProvider(new EvaluatorException(""));
        return nativeErrorMakeProto;
    }

    static NativeError makeAggregate(Context context, Scriptable scriptable, IdFunctionObject idFunctionObject, Object[] objArr) {
        NativeError nativeErrorMakeProto = makeProto(scriptable, idFunctionObject);
        int length = objArr.length;
        if (length >= 1) {
            if (length >= 2) {
                if (!Undefined.isUndefined(objArr[1])) {
                    ScriptableObject.putProperty(nativeErrorMakeProto, ContentType.Message.TYPE, ScriptRuntime.toString(objArr[1]));
                    nativeErrorMakeProto.setAttributes(ContentType.Message.TYPE, 2);
                }
                if (length >= 3) {
                    Object obj = objArr[2];
                    if (obj instanceof NativeObject) {
                        installCause((NativeObject) obj, nativeErrorMakeProto);
                    } else {
                        ScriptableObject.putProperty(nativeErrorMakeProto, "fileName", ScriptRuntime.toString(obj));
                        if (length >= 4) {
                            ScriptableObject.putProperty(nativeErrorMakeProto, "lineNumber", Integer.valueOf(ScriptRuntime.toInt32(objArr[3])));
                        }
                    }
                }
            }
            IteratorLikeIterable iteratorLikeIterable = new IteratorLikeIterable(context, scriptable, ScriptRuntime.callIterator(objArr[0], context, scriptable));
            try {
                ArrayList arrayList = new ArrayList();
                Iterator<Object> it = iteratorLikeIterable.iterator();
                while (it.hasNext()) {
                    arrayList.add(it.next());
                }
                nativeErrorMakeProto.defineProperty("errors", context.newArray(scriptable, arrayList.toArray()), 2);
                iteratorLikeIterable.close();
                nativeErrorMakeProto.setStackProvider(new EvaluatorException(""));
                return nativeErrorMakeProto;
            } catch (Throwable th) {
                try {
                    iteratorLikeIterable.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        throw ScriptRuntime.typeErrorById("msg.iterable.expected", new Object[0]);
    }

    static void installCause(NativeObject nativeObject, NativeError nativeError) {
        Object property = ScriptableObject.getProperty(nativeObject, "cause");
        if (property != NOT_FOUND) {
            ScriptableObject.putProperty(nativeError, "cause", property);
            nativeError.setAttributes("cause", 2);
        }
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected void fillConstructorProperties(IdFunctionObject idFunctionObject) {
        addIdFunctionProperty(idFunctionObject, ERROR_TAG, -1, "captureStackTrace", 2);
        final ProtoProps protoProps = new ProtoProps();
        associateValue("_ErrorPrototypeProps", protoProps);
        idFunctionObject.defineProperty("stackTraceLimit", new Supplier() { // from class: org.mozilla.javascript.NativeError$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return protoProps.getStackTraceLimit();
            }
        }, new Consumer() { // from class: org.mozilla.javascript.NativeError$$ExternalSyntheticLambda3
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                protoProps.setStackTraceLimit(obj);
            }

            public /* synthetic */ Consumer andThen(Consumer consumer) {
                return Consumer$CC.$default$andThen(this, consumer);
            }
        }, 0);
        idFunctionObject.defineProperty("prepareStackTrace", new Supplier() { // from class: org.mozilla.javascript.NativeError$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return protoProps.getPrepareStackTrace();
            }
        }, new Consumer() { // from class: org.mozilla.javascript.NativeError$$ExternalSyntheticLambda5
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                protoProps.setPrepareStackTrace(obj);
            }

            public /* synthetic */ Consumer andThen(Consumer consumer) {
                return Consumer$CC.$default$andThen(this, consumer);
            }
        }, 0);
        super.fillConstructorProperties(idFunctionObject);
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public String getClassName() {
        return "Error";
    }

    public String toString() {
        Object objJs_toString = js_toString(this);
        return objJs_toString instanceof String ? (String) objJs_toString : super.toString();
    }

    private static NativeError realThis(Scriptable scriptable, IdFunctionObject idFunctionObject) {
        return (NativeError) ensureType(scriptable, NativeError.class, idFunctionObject);
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
            } else {
                throw new IllegalArgumentException(String.valueOf(i));
            }
        } else {
            i2 = 1;
            str = "constructor";
        }
        initPrototypeMethod(ERROR_TAG, i, str, i2);
    }

    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.IdFunctionCall
    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (!idFunctionObject.hasTag(ERROR_TAG)) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int iMethodId = idFunctionObject.methodId();
        if (iMethodId == -1) {
            js_captureStackTrace(context, scriptable, scriptable2, objArr);
            return Undefined.instance;
        }
        if (iMethodId == 1) {
            return make(context, scriptable, idFunctionObject, objArr);
        }
        if (iMethodId != 2) {
            if (iMethodId == 3) {
                return js_toSource(context, scriptable, scriptable2);
            }
            throw new IllegalArgumentException(String.valueOf(iMethodId));
        }
        if (scriptable2 != scriptable && (scriptable2 instanceof NativeObject)) {
            return js_toString(scriptable2);
        }
        return js_toString(realThis(scriptable2, idFunctionObject));
    }

    public void setStackProvider(RhinoException rhinoException) {
        if (this.stackProvider == null) {
            defineProperty(STACK_TAG, new Supplier() { // from class: org.mozilla.javascript.NativeError$$ExternalSyntheticLambda0
                @Override // java.util.function.Supplier
                public final Object get() {
                    return this.f$0.getStackDelegated();
                }
            }, new Consumer() { // from class: org.mozilla.javascript.NativeError$$ExternalSyntheticLambda1
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.f$0.setStackDelegated(obj);
                }

                public /* synthetic */ Consumer andThen(Consumer consumer) {
                    return Consumer$CC.$default$andThen(this, consumer);
                }
            }, 2);
        }
        this.stackProvider = rhinoException;
    }

    public Object getStackDelegated() {
        int i;
        Function function;
        Object objCallPrepareStack;
        Object obj = this.stack;
        if (obj != null) {
            return obj;
        }
        if (this.stackProvider == null) {
            return NOT_FOUND;
        }
        ProtoProps protoProps = (ProtoProps) ((NativeError) getPrototype()).getAssociatedValue("_ErrorPrototypeProps");
        if (protoProps != null) {
            i = protoProps.stackTraceLimit;
            function = protoProps.prepareStackTrace;
        } else {
            i = -1;
            function = null;
        }
        ScriptStackElement[] scriptStack = this.stackProvider.getScriptStack(i, (String) getAssociatedValue(STACK_HIDE_KEY));
        if (function == null) {
            objCallPrepareStack = RhinoException.formatStackTrace(scriptStack, this.stackProvider.details());
        } else {
            objCallPrepareStack = callPrepareStack(function, scriptStack);
        }
        this.stack = objCallPrepareStack;
        return objCallPrepareStack;
    }

    public void setStackDelegated(Object obj) {
        this.stackProvider = null;
        this.stack = obj;
    }

    private Object callPrepareStack(Function function, ScriptStackElement[] scriptStackElementArr) {
        Context currentContext = Context.getCurrentContext();
        Object[] objArr = new Object[scriptStackElementArr.length];
        for (int i = 0; i < scriptStackElementArr.length; i++) {
            NativeCallSite nativeCallSite = (NativeCallSite) currentContext.newObject(this, "CallSite");
            nativeCallSite.setElement(scriptStackElementArr[i]);
            objArr[i] = nativeCallSite;
        }
        return function.call(currentContext, function, this, new Object[]{this, currentContext.newArray(this, objArr)});
    }

    private static Object js_toString(Scriptable scriptable) {
        String string;
        String string2;
        Object property = ScriptableObject.getProperty(scriptable, "name");
        if (property == NOT_FOUND || Undefined.isUndefined(property)) {
            string = "Error";
        } else {
            string = ScriptRuntime.toString(property);
        }
        Object property2 = ScriptableObject.getProperty(scriptable, ContentType.Message.TYPE);
        if (property2 == NOT_FOUND || Undefined.isUndefined(property2)) {
            string2 = "";
        } else {
            string2 = ScriptRuntime.toString(property2);
        }
        if (string.isEmpty()) {
            return string2;
        }
        if (string2.isEmpty()) {
            return string;
        }
        return string + ": " + string2;
    }

    private static String js_toSource(Context context, Scriptable scriptable, Scriptable scriptable2) {
        int int32;
        Object property = ScriptableObject.getProperty(scriptable2, "name");
        Object property2 = ScriptableObject.getProperty(scriptable2, ContentType.Message.TYPE);
        Object property3 = ScriptableObject.getProperty(scriptable2, "fileName");
        Object property4 = ScriptableObject.getProperty(scriptable2, "lineNumber");
        StringBuilder sb = new StringBuilder("(new ");
        if (property == NOT_FOUND) {
            property = Undefined.instance;
        }
        sb.append(ScriptRuntime.toString(property));
        sb.append("(");
        if (property2 != NOT_FOUND || property3 != NOT_FOUND || property4 != NOT_FOUND) {
            if (property2 == NOT_FOUND) {
                property2 = "";
            }
            sb.append(ScriptRuntime.uneval(context, scriptable, property2));
            if (property3 != NOT_FOUND || property4 != NOT_FOUND) {
                sb.append(", ");
                if (property3 == NOT_FOUND) {
                    property3 = "";
                }
                sb.append(ScriptRuntime.uneval(context, scriptable, property3));
                if (property4 != NOT_FOUND && (int32 = ScriptRuntime.toInt32(property4)) != 0) {
                    sb.append(", ");
                    sb.append(ScriptRuntime.toString(int32));
                }
            }
        }
        sb.append("))");
        return sb.toString();
    }

    private static void js_captureStackTrace(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        Object obj;
        ScriptableObject scriptableObject = (ScriptableObject) ScriptRuntime.toObject(context, scriptable, objArr[0]);
        Scriptable scriptable3 = objArr.length > 1 ? (Function) ScriptRuntime.toObjectOrNull(context, objArr[1], scriptable) : null;
        NativeError nativeError = (NativeError) context.newObject(scriptable2, "Error");
        nativeError.setStackProvider(new EvaluatorException("[object Object]"));
        if (scriptable3 != null && (obj = scriptable3.get("name", scriptable3)) != null && !Undefined.isUndefined(obj)) {
            nativeError.associateValue(STACK_HIDE_KEY, Context.toString(obj));
        }
        scriptableObject.defineProperty(STACK_TAG, nativeError.get(STACK_TAG), 2);
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
            default:
                return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static final class ProtoProps implements Serializable {
        static final String KEY = "_ErrorPrototypeProps";
        private static final long serialVersionUID = 1907180507775337939L;
        Function prepareStackTrace;
        int stackTraceLimit;

        private ProtoProps() {
            this.stackTraceLimit = -1;
        }

        public Object getStackTraceLimit() {
            int i = this.stackTraceLimit;
            if (i >= 0) {
                return Integer.valueOf(i);
            }
            return Double.valueOf(Double.POSITIVE_INFINITY);
        }

        public void setStackTraceLimit(Object obj) {
            double number = Context.toNumber(obj);
            if (Double.isNaN(number) || Double.isInfinite(number)) {
                this.stackTraceLimit = -1;
            } else {
                this.stackTraceLimit = (int) number;
            }
        }

        public Object getPrepareStackTrace() {
            Function function = this.prepareStackTrace;
            return function == null ? Undefined.instance : function;
        }

        public void setPrepareStackTrace(Object obj) {
            if (obj == null || Undefined.isUndefined(obj)) {
                this.prepareStackTrace = null;
            } else if (obj instanceof Function) {
                this.prepareStackTrace = (Function) obj;
            }
        }
    }
}
