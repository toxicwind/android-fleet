package org.mozilla.javascript;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import org.mozilla.javascript.commonjs.module.ModuleScope;

/* JADX INFO: loaded from: classes8.dex */
public class FunctionObject extends BaseFunction {
    public static final int JAVA_BOOLEAN_TYPE = 3;
    public static final int JAVA_DOUBLE_TYPE = 4;
    public static final int JAVA_INT_TYPE = 2;
    public static final int JAVA_OBJECT_TYPE = 6;
    public static final int JAVA_SCRIPTABLE_TYPE = 5;
    public static final int JAVA_STRING_TYPE = 1;
    public static final int JAVA_UNSUPPORTED_TYPE = 0;
    private static final short VARARGS_CTOR = -2;
    private static final short VARARGS_METHOD = -1;
    private static boolean sawSecurityException = false;
    private static final long serialVersionUID = -5332312783643935019L;
    private String functionName;
    private transient boolean hasVoidReturn;
    private boolean isStatic;
    MemberBox member;
    private int parmsLength;
    private transient int returnTypeTag;
    private transient byte[] typeTags;

    public FunctionObject(String str, Member member, Scriptable scriptable) {
        if (member instanceof Constructor) {
            this.member = new MemberBox((Constructor<?>) member);
            this.isStatic = true;
        } else {
            MemberBox memberBox = new MemberBox((Method) member);
            this.member = memberBox;
            this.isStatic = memberBox.isStatic();
        }
        String name = this.member.getName();
        this.functionName = str;
        Class<?>[] clsArr = this.member.argTypes;
        int length = clsArr.length;
        if (length == 4 && (clsArr[1].isArray() || clsArr[2].isArray())) {
            boolean zIsArray = clsArr[1].isArray();
            boolean z = this.isStatic;
            if (zIsArray) {
                if (!z || clsArr[0] != ScriptRuntime.ContextClass || clsArr[1].getComponentType() != ScriptRuntime.ObjectClass || clsArr[2] != ScriptRuntime.FunctionClass || clsArr[3] != Boolean.TYPE) {
                    throw Context.reportRuntimeErrorById("msg.varargs.ctor", name);
                }
                this.parmsLength = -2;
            } else {
                if (!z || clsArr[0] != ScriptRuntime.ContextClass || clsArr[1] != ScriptRuntime.ScriptableClass || clsArr[2].getComponentType() != ScriptRuntime.ObjectClass || clsArr[3] != ScriptRuntime.FunctionClass) {
                    throw Context.reportRuntimeErrorById("msg.varargs.fun", name);
                }
                this.parmsLength = -1;
            }
        } else {
            this.parmsLength = length;
            if (length > 0) {
                this.typeTags = new byte[length];
                for (int i = 0; i != length; i++) {
                    int typeTag = getTypeTag(clsArr[i]);
                    if (typeTag == 0) {
                        throw Context.reportRuntimeErrorById("msg.bad.parms", clsArr[i].getName(), name);
                    }
                    this.typeTags[i] = (byte) typeTag;
                }
            }
        }
        boolean zIsMethod = this.member.isMethod();
        MemberBox memberBox2 = this.member;
        if (zIsMethod) {
            Class<?> returnType = memberBox2.method().getReturnType();
            if (returnType == Void.TYPE) {
                this.hasVoidReturn = true;
            } else {
                this.returnTypeTag = getTypeTag(returnType);
            }
        } else {
            Class<?> declaringClass = memberBox2.getDeclaringClass();
            if (!ScriptRuntime.ScriptableClass.isAssignableFrom(declaringClass)) {
                throw Context.reportRuntimeErrorById("msg.bad.ctor.return", declaringClass.getName());
            }
        }
        ScriptRuntime.setFunctionProtoAndParent(this, Context.getCurrentContext(), scriptable, false);
    }

    public static int getTypeTag(Class<?> cls) {
        if (cls == ScriptRuntime.StringClass) {
            return 1;
        }
        if (cls == ScriptRuntime.IntegerClass || cls == Integer.TYPE) {
            return 2;
        }
        if (cls == ScriptRuntime.BooleanClass || cls == Boolean.TYPE) {
            return 3;
        }
        if (cls == ScriptRuntime.DoubleClass || cls == Double.TYPE) {
            return 4;
        }
        if (ScriptRuntime.ScriptableClass.isAssignableFrom(cls)) {
            return 5;
        }
        return cls == ScriptRuntime.ObjectClass ? 6 : 0;
    }

    public static Object convertArg(Context context, Scriptable scriptable, Object obj, int i) {
        switch (i) {
            case 1:
                if (!(obj instanceof String)) {
                    return ScriptRuntime.toString(obj);
                }
                return obj;
            case 2:
                if (!(obj instanceof Integer)) {
                    return Integer.valueOf(ScriptRuntime.toInt32(obj));
                }
                return obj;
            case 3:
                if (!(obj instanceof Boolean)) {
                    return ScriptRuntime.toBoolean(obj) ? Boolean.TRUE : Boolean.FALSE;
                }
                return obj;
            case 4:
                if (!(obj instanceof Double)) {
                    return Double.valueOf(ScriptRuntime.toNumber(obj));
                }
                return obj;
            case 5:
                return ScriptRuntime.toObjectOrNull(context, obj, scriptable);
            case 6:
                if (obj instanceof ConsString) {
                    return obj.toString();
                }
                return obj;
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override // org.mozilla.javascript.BaseFunction
    public int getArity() {
        int i = this.parmsLength;
        if (i < 0) {
            return 1;
        }
        return i;
    }

    @Override // org.mozilla.javascript.BaseFunction
    public int getLength() {
        return getArity();
    }

    @Override // org.mozilla.javascript.BaseFunction
    public String getFunctionName() {
        String str = this.functionName;
        return str == null ? "" : str;
    }

    public Member getMethodOrConstructor() {
        boolean zIsMethod = this.member.isMethod();
        MemberBox memberBox = this.member;
        if (zIsMethod) {
            return memberBox.method();
        }
        return memberBox.ctor();
    }

    static Method findSingleMethod(Method[] methodArr, String str) {
        int length = methodArr.length;
        Method method = null;
        for (int i = 0; i != length; i++) {
            Method method2 = methodArr[i];
            if (method2 != null && str.equals(method2.getName())) {
                if (method != null) {
                    throw Context.reportRuntimeErrorById("msg.no.overload", str, method2.getDeclaringClass().getName());
                }
                method = method2;
            }
        }
        return method;
    }

    static Method[] getMethodList(Class<?> cls) {
        Method[] declaredMethods;
        try {
            declaredMethods = !sawSecurityException ? cls.getDeclaredMethods() : null;
        } catch (SecurityException unused) {
            sawSecurityException = true;
        }
        if (declaredMethods == null) {
            declaredMethods = cls.getMethods();
        }
        int i = 0;
        for (int i2 = 0; i2 < declaredMethods.length; i2++) {
            if (!sawSecurityException ? Modifier.isPublic(declaredMethods[i2].getModifiers()) : declaredMethods[i2].getDeclaringClass() == cls) {
                i++;
            } else {
                declaredMethods[i2] = null;
            }
        }
        Method[] methodArr = new Method[i];
        int i3 = 0;
        for (Method method : declaredMethods) {
            if (method != null) {
                methodArr[i3] = method;
                i3++;
            }
        }
        return methodArr;
    }

    public void addAsConstructor(Scriptable scriptable, Scriptable scriptable2) {
        initAsConstructor(scriptable, scriptable2, 7);
        defineProperty(scriptable, scriptable2.getClassName(), this, 2);
    }

    public void addAsConstructor(Scriptable scriptable, Scriptable scriptable2, int i) {
        initAsConstructor(scriptable, scriptable2, i);
        defineProperty(scriptable, scriptable2.getClassName(), this, 2);
    }

    void initAsConstructor(Scriptable scriptable, Scriptable scriptable2, int i) {
        ScriptRuntime.setFunctionProtoAndParent(this, Context.getCurrentContext(), scriptable);
        setImmunePrototypeProperty(scriptable2);
        scriptable2.setParentScope(this);
        defineProperty(scriptable2, "constructor", this, i);
        setParentScope(scriptable);
    }

    @Deprecated
    public static Object convertArg(Context context, Scriptable scriptable, Object obj, Class<?> cls) {
        int typeTag = getTypeTag(cls);
        if (typeTag != 0) {
            return convertArg(context, scriptable, obj, typeTag);
        }
        throw Context.reportRuntimeErrorById("msg.cant.convert", cls.getName());
    }

    @Override // org.mozilla.javascript.BaseFunction, org.mozilla.javascript.Function, org.mozilla.javascript.Callable
    public Object call(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        Object[] objArr2;
        Object objNewInstance;
        Scriptable parentScope;
        boolean zIsInstance;
        int length = objArr.length;
        boolean z = true;
        if (this.parmsLength < 0) {
            for (int i = 0; i < length; i++) {
                Object obj = objArr[i];
                if (obj instanceof ConsString) {
                    objArr[i] = obj.toString();
                }
            }
            if (this.parmsLength == -1) {
                objNewInstance = this.member.invoke(null, new Object[]{context, scriptable2, objArr, this});
            } else {
                Object[] objArr3 = {context, objArr, this, scriptable2 == null ? Boolean.TRUE : Boolean.FALSE};
                boolean zIsCtor = this.member.isCtor();
                MemberBox memberBox = this.member;
                if (zIsCtor) {
                    objNewInstance = memberBox.newInstance(objArr3);
                } else {
                    objNewInstance = memberBox.invoke(null, objArr3);
                }
                z = false;
            }
        } else {
            if (!this.isStatic) {
                Class<?> declaringClass = this.member.getDeclaringClass();
                if (scriptable2 instanceof Delegator) {
                    scriptable2 = ((Delegator) scriptable2).getDelegee();
                }
                if (!declaringClass.isInstance(scriptable2)) {
                    if ((scriptable2 == scriptable || (scriptable2 instanceof ModuleScope)) && scriptable != (parentScope = getParentScope())) {
                        zIsInstance = declaringClass.isInstance(parentScope);
                        if (zIsInstance) {
                            scriptable2 = parentScope;
                        }
                    } else {
                        zIsInstance = false;
                    }
                    if (!zIsInstance) {
                        throw ScriptRuntime.typeErrorById("msg.incompat.call", this.functionName);
                    }
                }
            }
            int i2 = this.parmsLength;
            if (i2 == length) {
                objArr2 = objArr;
                for (int i3 = 0; i3 != this.parmsLength; i3++) {
                    Object obj2 = objArr[i3];
                    Object objConvertArg = convertArg(context, scriptable, obj2, this.typeTags[i3]);
                    if (obj2 != objConvertArg) {
                        if (objArr2 == objArr) {
                            objArr2 = (Object[]) objArr.clone();
                        }
                        objArr2[i3] = objConvertArg;
                    }
                }
            } else if (i2 == 0) {
                objArr2 = ScriptRuntime.emptyArgs;
            } else {
                objArr2 = new Object[i2];
                int i4 = 0;
                while (i4 != this.parmsLength) {
                    objArr2[i4] = convertArg(context, scriptable, i4 < length ? objArr[i4] : Undefined.instance, this.typeTags[i4]);
                    i4++;
                }
            }
            boolean zIsMethod = this.member.isMethod();
            MemberBox memberBox2 = this.member;
            if (zIsMethod) {
                objNewInstance = memberBox2.invoke(scriptable2, objArr2);
            } else {
                objNewInstance = memberBox2.newInstance(objArr2);
                z = false;
            }
        }
        if (z) {
            if (this.hasVoidReturn) {
                return Undefined.instance;
            }
            if (this.returnTypeTag == 0) {
                return context.getWrapFactory().wrap(context, scriptable, objNewInstance, null);
            }
        }
        return objNewInstance;
    }

    @Override // org.mozilla.javascript.BaseFunction
    public Scriptable createObject(Context context, Scriptable scriptable) {
        if (this.member.isCtor() || this.parmsLength == -2) {
            return null;
        }
        try {
            Scriptable scriptable2 = (Scriptable) this.member.getDeclaringClass().getDeclaredConstructor(null).newInstance(null);
            scriptable2.setPrototype(getClassPrototype());
            scriptable2.setParentScope(getParentScope());
            return scriptable2;
        } catch (Exception e) {
            throw Context.throwAsScriptRuntimeEx(e);
        }
    }

    boolean isVarArgsMethod() {
        return this.parmsLength == -1;
    }

    boolean isVarArgsConstructor() {
        return this.parmsLength == -2;
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        if (this.parmsLength > 0) {
            Class<?>[] clsArr = this.member.argTypes;
            this.typeTags = new byte[this.parmsLength];
            for (int i = 0; i != this.parmsLength; i++) {
                this.typeTags[i] = (byte) getTypeTag(clsArr[i]);
            }
        }
        if (this.member.isMethod()) {
            Class<?> returnType = this.member.method().getReturnType();
            if (returnType == Void.TYPE) {
                this.hasVoidReturn = true;
            } else {
                this.returnTypeTag = getTypeTag(returnType);
            }
        }
    }
}
