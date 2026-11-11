package org.mozilla.javascript;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.EnumSet;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: classes8.dex */
public class NativeJavaMethod extends BaseFunction {
    private static final int PREFERENCE_AMBIGUOUS = 3;
    private static final int PREFERENCE_EQUAL = 0;
    private static final int PREFERENCE_FIRST_ARG = 1;
    private static final int PREFERENCE_SECOND_ARG = 2;
    private static final boolean debug = false;
    private static final long serialVersionUID = -3440381785576412928L;
    private String functionName;
    MemberBox[] methods;
    private final transient CopyOnWriteArrayList<ResolvedOverload> overloadCache;

    private static void printDebug(String str, MemberBox memberBox, Object[] objArr) {
    }

    NativeJavaMethod(MemberBox[] memberBoxArr) {
        this.overloadCache = new CopyOnWriteArrayList<>();
        this.functionName = memberBoxArr[0].getName();
        this.methods = memberBoxArr;
    }

    NativeJavaMethod(MemberBox[] memberBoxArr, String str) {
        this.overloadCache = new CopyOnWriteArrayList<>();
        this.functionName = str;
        this.methods = memberBoxArr;
    }

    NativeJavaMethod(MemberBox memberBox, String str) {
        this.overloadCache = new CopyOnWriteArrayList<>();
        this.functionName = str;
        this.methods = new MemberBox[]{memberBox};
    }

    public NativeJavaMethod(Method method, String str) {
        this(new MemberBox(method), str);
    }

    @Override // org.mozilla.javascript.BaseFunction
    public String getFunctionName() {
        return this.functionName;
    }

    static String scriptSignature(Object[] objArr) {
        String strJavaSignature;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i != objArr.length; i++) {
            Object obj = objArr[i];
            if (obj == null) {
                strJavaSignature = AbstractJsonLexerKt.NULL;
            } else if (obj instanceof Boolean) {
                strJavaSignature = TypedValues.Custom.S_BOOLEAN;
            } else if (obj instanceof String) {
                strJavaSignature = TypedValues.Custom.S_STRING;
            } else if (obj instanceof Number) {
                strJavaSignature = "number";
            } else if (obj instanceof Scriptable) {
                if (obj instanceof Undefined) {
                    strJavaSignature = "undefined";
                } else if (obj instanceof Wrapper) {
                    strJavaSignature = ((Wrapper) obj).unwrap().getClass().getName();
                } else if (obj instanceof Function) {
                    strJavaSignature = "function";
                } else {
                    strJavaSignature = "object";
                }
            } else {
                strJavaSignature = JavaMembers.javaSignature(obj.getClass());
            }
            if (i != 0) {
                sb.append(AbstractJsonLexerKt.COMMA);
            }
            sb.append(strJavaSignature);
        }
        return sb.toString();
    }

    @Override // org.mozilla.javascript.BaseFunction
    String decompile(int i, EnumSet<DecompilerFlag> enumSet) {
        StringBuilder sb = new StringBuilder();
        boolean zContains = enumSet.contains(DecompilerFlag.ONLY_BODY);
        if (!zContains) {
            sb.append("function ");
            sb.append(getFunctionName());
            sb.append("() {");
        }
        sb.append("/*\n");
        sb.append(toString());
        sb.append(zContains ? "*/\n" : "*/}\n");
        return sb.toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int length = this.methods.length;
        for (int i = 0; i != length; i++) {
            boolean zIsMethod = this.methods[i].isMethod();
            MemberBox[] memberBoxArr = this.methods;
            if (zIsMethod) {
                Method method = memberBoxArr[i].method();
                sb.append(JavaMembers.javaSignature(method.getReturnType()));
                sb.append(' ');
                sb.append(method.getName());
            } else {
                sb.append(memberBoxArr[i].getName());
            }
            sb.append(JavaMembers.liveConnectSignature(this.methods[i].argTypes));
            sb.append('\n');
        }
        return sb.toString();
    }

    @Override // org.mozilla.javascript.BaseFunction, org.mozilla.javascript.Function, org.mozilla.javascript.Callable
    public Object call(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        Object[] objArr2;
        Object obj;
        Object objJsToJava;
        if (this.methods.length == 0) {
            throw new RuntimeException("No methods defined for call");
        }
        int iFindCachedFunction = findCachedFunction(context, objArr);
        MemberBox[] memberBoxArr = this.methods;
        if (iFindCachedFunction < 0) {
            throw Context.reportRuntimeErrorById("msg.java.no_such_method", memberBoxArr[0].method().getDeclaringClass().getName() + "." + getFunctionName() + "(" + scriptSignature(objArr) + ")");
        }
        MemberBox memberBox = memberBoxArr[iFindCachedFunction];
        Class<?>[] clsArr = memberBox.argTypes;
        if (memberBox.vararg) {
            objArr2 = new Object[clsArr.length];
            for (int i = 0; i < clsArr.length - 1; i++) {
                objArr2[i] = Context.jsToJava(objArr[i], clsArr[i]);
            }
            if (objArr.length == clsArr.length && (objArr[objArr.length - 1] == null || (objArr[objArr.length - 1] instanceof NativeArray) || (objArr[objArr.length - 1] instanceof NativeJavaArray))) {
                objJsToJava = Context.jsToJava(objArr[objArr.length - 1], clsArr[clsArr.length - 1]);
            } else {
                Class<?> componentType = clsArr[clsArr.length - 1].getComponentType();
                Object objNewInstance = Array.newInstance(componentType, (objArr.length - clsArr.length) + 1);
                for (int i2 = 0; i2 < Array.getLength(objNewInstance); i2++) {
                    Array.set(objNewInstance, i2, Context.jsToJava(objArr[(clsArr.length - 1) + i2], componentType));
                }
                objJsToJava = objNewInstance;
            }
            objArr2[clsArr.length - 1] = objJsToJava;
        } else {
            objArr2 = objArr;
            for (int i3 = 0; i3 < objArr2.length; i3++) {
                Object obj2 = objArr2[i3];
                Object objJsToJava2 = Context.jsToJava(obj2, clsArr[i3]);
                if (objJsToJava2 != obj2) {
                    if (objArr == objArr2) {
                        objArr2 = (Object[]) objArr2.clone();
                    }
                    objArr2[i3] = objJsToJava2;
                }
            }
        }
        if (memberBox.isStatic()) {
            obj = null;
        } else {
            Class<?> declaringClass = memberBox.getDeclaringClass();
            Scriptable prototype = scriptable2;
            while (true) {
                if (prototype == null) {
                    throw Context.reportRuntimeErrorById("msg.nonjava.method", getFunctionName(), ScriptRuntime.toString(scriptable2), declaringClass.getName());
                }
                if (prototype instanceof Wrapper) {
                    Object objUnwrap = ((Wrapper) prototype).unwrap();
                    if (declaringClass.isInstance(objUnwrap)) {
                        obj = objUnwrap;
                        break;
                    }
                }
                prototype = prototype.getPrototype();
            }
        }
        Object objInvoke = memberBox.invoke(obj, objArr2);
        Class<?> returnType = memberBox.method().getReturnType();
        Object objWrap = context.getWrapFactory().wrap(context, scriptable, objInvoke, returnType);
        return (objWrap == null && returnType == Void.TYPE) ? Undefined.instance : objWrap;
    }

    int findCachedFunction(Context context, Object[] objArr) {
        MemberBox[] memberBoxArr = this.methods;
        if (memberBoxArr.length > 1) {
            for (ResolvedOverload resolvedOverload : this.overloadCache) {
                if (resolvedOverload.matches(objArr)) {
                    return resolvedOverload.index;
                }
            }
            int iFindFunction = findFunction(context, this.methods, objArr);
            if (this.overloadCache.size() < this.methods.length * 2) {
                this.overloadCache.addIfAbsent(new ResolvedOverload(objArr, iFindFunction));
            }
            return iFindFunction;
        }
        return findFunction(context, memberBoxArr, objArr);
    }

    static int findFunction(Context context, MemberBox[] memberBoxArr, Object[] objArr) {
        int[] iArr;
        int i;
        int i2 = -1;
        if (memberBoxArr.length == 0) {
            return -1;
        }
        int i3 = 0;
        if (memberBoxArr.length == 1) {
            MemberBox memberBox = memberBoxArr[0];
            Class<?>[] clsArr = memberBox.argTypes;
            int length = clsArr.length;
            if (memberBox.vararg) {
                length--;
                if (length > objArr.length) {
                    return -1;
                }
            } else if (length != objArr.length) {
                return -1;
            }
            for (int i4 = 0; i4 != length; i4++) {
                if (!NativeJavaObject.canConvert(objArr[i4], clsArr[i4])) {
                    return -1;
                }
            }
            return 0;
        }
        int[] iArr2 = null;
        int i5 = -1;
        int i6 = 0;
        int i7 = 0;
        while (i6 < memberBoxArr.length) {
            MemberBox memberBox2 = memberBoxArr[i6];
            Class<?>[] clsArr2 = memberBox2.argTypes;
            int length2 = clsArr2.length;
            if (!memberBox2.vararg ? length2 == objArr.length : (length2 = length2 - 1) <= objArr.length) {
                int i8 = i3;
                while (true) {
                    if (i8 < length2) {
                        i = i3;
                        if (NativeJavaObject.canConvert(objArr[i8], clsArr2[i8])) {
                            i8++;
                            i3 = i;
                        } else {
                            iArr = iArr2;
                        }
                    } else {
                        i = i3;
                        if (i5 >= 0) {
                            int i9 = i2;
                            int i10 = i;
                            int i11 = i10;
                            while (true) {
                                if (i9 != i7) {
                                    MemberBox memberBox3 = memberBoxArr[i9 == i2 ? i5 : iArr2[i9]];
                                    if (context.hasFeature(13) && memberBox3.isPublic() != memberBox2.isPublic()) {
                                        if (memberBox3.isPublic()) {
                                            i11++;
                                        } else {
                                            i10++;
                                        }
                                        iArr = iArr2;
                                    } else {
                                        iArr = iArr2;
                                        int iPreferSignature = preferSignature(objArr, clsArr2, memberBox2.vararg, memberBox3.argTypes, memberBox3.vararg);
                                        if (iPreferSignature != 3) {
                                            if (iPreferSignature == 1) {
                                                i10++;
                                            } else if (iPreferSignature == 2) {
                                                i11++;
                                            } else {
                                                if (iPreferSignature != 0) {
                                                    Kit.codeBug();
                                                }
                                                if (memberBox3.isStatic() && memberBox3.getDeclaringClass().isAssignableFrom(memberBox2.getDeclaringClass())) {
                                                    if (i9 != -1) {
                                                        iArr[i9] = i6;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    i9++;
                                    iArr2 = iArr;
                                    i2 = -1;
                                } else {
                                    iArr = iArr2;
                                }
                                int i12 = i7 + 1;
                                if (i10 != i12) {
                                    if (i11 != i12) {
                                        int[] iArr3 = iArr == null ? new int[memberBoxArr.length - 1] : iArr;
                                        iArr3[i7] = i6;
                                        i7 = i12;
                                        iArr2 = iArr3;
                                        break;
                                    }
                                } else {
                                    i5 = i6;
                                    i7 = i;
                                }
                            }
                        } else {
                            iArr = iArr2;
                        }
                        i5 = i6;
                    }
                }
                i6++;
                i3 = i;
                i2 = -1;
            } else {
                iArr = iArr2;
                i = i3;
            }
            iArr2 = iArr;
            i6++;
            i3 = i;
            i2 = -1;
        }
        int[] iArr4 = iArr2;
        int i13 = i3;
        if (i5 < 0) {
            return -1;
        }
        if (i7 == 0) {
            return i5;
        }
        StringBuilder sb = new StringBuilder();
        int i14 = -1;
        while (i14 != i7) {
            int i15 = i14 == -1 ? i5 : iArr4[i14];
            sb.append("\n    ");
            sb.append(memberBoxArr[i15].toJavaDeclaration());
            i14++;
        }
        MemberBox memberBox4 = memberBoxArr[i5];
        String name = memberBox4.getName();
        String name2 = memberBox4.getDeclaringClass().getName();
        if (memberBoxArr[i13].isCtor()) {
            String strScriptSignature = scriptSignature(objArr);
            String string = sb.toString();
            Object[] objArr2 = new Object[3];
            objArr2[i13] = name;
            objArr2[1] = strScriptSignature;
            objArr2[2] = string;
            throw Context.reportRuntimeErrorById("msg.constructor.ambiguous", objArr2);
        }
        String strScriptSignature2 = scriptSignature(objArr);
        String string2 = sb.toString();
        Object[] objArr3 = new Object[4];
        objArr3[i13] = name2;
        objArr3[1] = name;
        objArr3[2] = strScriptSignature2;
        objArr3[3] = string2;
        throw Context.reportRuntimeErrorById("msg.method.ambiguous", objArr3);
    }

    /* JADX WARN: Code duplicated, block: B:23:0x0032  */
    /* JADX WARN: Code duplicated, block: B:31:0x0044  */
    private static int preferSignature(Object[] objArr, Class<?>[] clsArr, boolean z, Class<?>[] clsArr2, boolean z2) {
        int i = 0;
        int i2 = 0;
        while (i < objArr.length) {
            int i3 = 1;
            Class<?> cls = (!z || i < clsArr.length) ? clsArr[i] : clsArr[clsArr.length - 1];
            Class<?> cls2 = (!z2 || i < clsArr2.length) ? clsArr2[i] : clsArr2[clsArr2.length - 1];
            if (cls != cls2) {
                Object obj = objArr[i];
                int conversionWeight = NativeJavaObject.getConversionWeight(obj, cls);
                int conversionWeight2 = NativeJavaObject.getConversionWeight(obj, cls2);
                if (conversionWeight >= conversionWeight2) {
                    if (conversionWeight > conversionWeight2) {
                        i3 = 2;
                    } else if (conversionWeight != 0) {
                        i3 = 3;
                    } else if (cls.isAssignableFrom(cls2)) {
                        i3 = 2;
                    } else if (!cls2.isAssignableFrom(cls)) {
                        i3 = 3;
                    }
                }
                i2 |= i3;
                if (i2 == 3) {
                    return i2;
                }
            }
            i++;
        }
        return i2;
    }
}
