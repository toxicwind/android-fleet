package org.mozilla.javascript;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/* JADX INFO: loaded from: classes8.dex */
final class MemberBox implements Serializable {
    private static final Class<?>[] primitives = {Boolean.TYPE, Byte.TYPE, Character.TYPE, Double.TYPE, Float.TYPE, Integer.TYPE, Long.TYPE, Short.TYPE, Void.TYPE};
    private static final long serialVersionUID = 6358550398665688245L;
    transient Class<?>[] argTypes;
    transient Function asGetterFunction;
    transient Function asSetterFunction;
    transient Object delegateTo;
    private transient Member memberObject;
    transient boolean vararg;

    MemberBox(Method method) {
        init(method);
    }

    MemberBox(Constructor<?> constructor) {
        init(constructor);
    }

    private void init(Method method) {
        this.memberObject = method;
        this.argTypes = method.getParameterTypes();
        this.vararg = method.isVarArgs();
    }

    private void init(Constructor<?> constructor) {
        this.memberObject = constructor;
        this.argTypes = constructor.getParameterTypes();
        this.vararg = constructor.isVarArgs();
    }

    Method method() {
        return (Method) this.memberObject;
    }

    Constructor<?> ctor() {
        return (Constructor) this.memberObject;
    }

    Member member() {
        return this.memberObject;
    }

    boolean isMethod() {
        return this.memberObject instanceof Method;
    }

    boolean isCtor() {
        return this.memberObject instanceof Constructor;
    }

    boolean isStatic() {
        return Modifier.isStatic(this.memberObject.getModifiers());
    }

    boolean isPublic() {
        return Modifier.isPublic(this.memberObject.getModifiers());
    }

    String getName() {
        return this.memberObject.getName();
    }

    Class<?> getDeclaringClass() {
        return this.memberObject.getDeclaringClass();
    }

    String toJavaDeclaration() {
        StringBuilder sb = new StringBuilder();
        if (isMethod()) {
            Method method = method();
            sb.append(method.getReturnType());
            sb.append(' ');
            sb.append(method.getName());
        } else {
            String name = ctor().getDeclaringClass().getName();
            int iLastIndexOf = name.lastIndexOf(46);
            if (iLastIndexOf >= 0) {
                name = name.substring(iLastIndexOf + 1);
            }
            sb.append(name);
        }
        sb.append(JavaMembers.liveConnectSignature(this.argTypes));
        return sb.toString();
    }

    public String toString() {
        return this.memberObject.toString();
    }

    boolean isSameGetterFunction(Object obj) {
        Object obj2 = this.asGetterFunction;
        if (obj2 == null) {
            obj2 = Undefined.instance;
        }
        return ScriptRuntime.shallowEq(obj, obj2);
    }

    boolean isSameSetterFunction(Object obj) {
        Object obj2 = this.asSetterFunction;
        if (obj2 == null) {
            obj2 = Undefined.instance;
        }
        return ScriptRuntime.shallowEq(obj, obj2);
    }

    Function asGetterFunction(final String str, Scriptable scriptable) {
        if (this.asGetterFunction == null) {
            this.asGetterFunction = new BaseFunction(scriptable, ScriptableObject.getFunctionPrototype(scriptable)) { // from class: org.mozilla.javascript.MemberBox.1
                /* JADX WARN: Type inference fix 'apply assigned field type' failed
                java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
                	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
                	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
                	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
                 */
                @Override // org.mozilla.javascript.BaseFunction, org.mozilla.javascript.Function, org.mozilla.javascript.Callable
                public Object call(Context context, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                    Object obj;
                    Object[] objArr2;
                    MemberBox memberBox = MemberBox.this;
                    if (memberBox.delegateTo == null) {
                        objArr2 = ScriptRuntime.emptyArgs;
                        obj = scriptable3;
                    } else {
                        Object[] objArr3 = {scriptable3};
                        obj = memberBox.delegateTo;
                        objArr2 = objArr3;
                    }
                    return memberBox.invoke(obj, objArr2);
                }

                @Override // org.mozilla.javascript.BaseFunction
                public String getFunctionName() {
                    return str;
                }
            };
        }
        return this.asGetterFunction;
    }

    Function asSetterFunction(final String str, Scriptable scriptable) {
        if (this.asSetterFunction == null) {
            this.asSetterFunction = new BaseFunction(scriptable, ScriptableObject.getFunctionPrototype(scriptable)) { // from class: org.mozilla.javascript.MemberBox.2
                /* JADX WARN: Type inference fix 'apply assigned field type' failed
                java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
                	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
                	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
                	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
                 */
                @Override // org.mozilla.javascript.BaseFunction, org.mozilla.javascript.Function, org.mozilla.javascript.Callable
                public Object call(Context context, Scriptable scriptable2, Scriptable scriptable3, Object[] objArr) {
                    Object objConvertArg;
                    Object obj;
                    Object[] objArr2;
                    MemberBox memberBox = MemberBox.this;
                    if (objArr.length > 0) {
                        objConvertArg = FunctionObject.convertArg(context, scriptable3, objArr[0], FunctionObject.getTypeTag(memberBox.argTypes[0]));
                    } else {
                        objConvertArg = Undefined.instance;
                    }
                    if (memberBox.delegateTo == null) {
                        objArr2 = new Object[]{objConvertArg};
                        obj = scriptable3;
                    } else {
                        Object[] objArr3 = {scriptable3, objConvertArg};
                        obj = memberBox.delegateTo;
                        objArr2 = objArr3;
                    }
                    return memberBox.invoke(obj, objArr2);
                }

                @Override // org.mozilla.javascript.BaseFunction
                public String getFunctionName() {
                    return str;
                }
            };
        }
        return this.asSetterFunction;
    }

    Object invoke(Object obj, Object[] objArr) {
        Method method = method();
        if (obj instanceof Delegator) {
            obj = ((Delegator) obj).getDelegee();
        }
        if (objArr != null) {
            for (int i = 0; i < objArr.length; i++) {
                Object obj2 = objArr[i];
                if (obj2 instanceof Delegator) {
                    objArr[i] = ((Delegator) obj2).getDelegee();
                }
            }
        }
        try {
            try {
                return method.invoke(obj, objArr);
            } catch (IllegalAccessException e) {
                Method methodSearchAccessibleMethod = searchAccessibleMethod(method, this.argTypes);
                if (methodSearchAccessibleMethod != null) {
                    this.memberObject = methodSearchAccessibleMethod;
                    method = methodSearchAccessibleMethod;
                } else if (!VMBridge.instance.tryToMakeAccessible(method)) {
                    throw Context.throwAsScriptRuntimeEx(e);
                }
                return method.invoke(obj, objArr);
            }
        } catch (InvocationTargetException e2) {
            e = e2;
            do {
                e = ((InvocationTargetException) e).getTargetException();
            } while (e instanceof InvocationTargetException);
            if (e instanceof ContinuationPending) {
                throw ((ContinuationPending) e);
            }
            throw Context.throwAsScriptRuntimeEx(e);
        } catch (Exception e3) {
            throw Context.throwAsScriptRuntimeEx(e3);
        }
    }

    Object newInstance(Object[] objArr) {
        Constructor<?> constructorCtor = ctor();
        try {
            try {
                return constructorCtor.newInstance(objArr);
            } catch (IllegalAccessException e) {
                if (!VMBridge.instance.tryToMakeAccessible(constructorCtor)) {
                    throw Context.throwAsScriptRuntimeEx(e);
                }
                return constructorCtor.newInstance(objArr);
            }
        } catch (Exception e2) {
            throw Context.throwAsScriptRuntimeEx(e2);
        }
    }

    private static Method searchAccessibleMethod(Method method, Class<?>[] clsArr) {
        int modifiers = method.getModifiers();
        if (!Modifier.isPublic(modifiers) || Modifier.isStatic(modifiers)) {
            return null;
        }
        Class<?> declaringClass = method.getDeclaringClass();
        if (Modifier.isPublic(declaringClass.getModifiers())) {
            return null;
        }
        String name = method.getName();
        Class<?>[] interfaces = declaringClass.getInterfaces();
        int length = interfaces.length;
        for (int i = 0; i != length; i++) {
            Class<?> cls = interfaces[i];
            if (Modifier.isPublic(cls.getModifiers())) {
                try {
                    return cls.getMethod(name, clsArr);
                } catch (NoSuchMethodException | SecurityException unused) {
                    continue;
                }
            }
        }
        while (true) {
            declaringClass = declaringClass.getSuperclass();
            if (declaringClass == null) {
                return null;
            }
            if (Modifier.isPublic(declaringClass.getModifiers())) {
                try {
                    Method method2 = declaringClass.getMethod(name, clsArr);
                    int modifiers2 = method2.getModifiers();
                    if (Modifier.isPublic(modifiers2) && !Modifier.isStatic(modifiers2)) {
                        return method2;
                    }
                } catch (NoSuchMethodException | SecurityException unused2) {
                    continue;
                }
            }
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        Member member = readMember(objectInputStream);
        if (member instanceof Method) {
            init((Method) member);
        } else {
            init((Constructor<?>) member);
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        writeMember(objectOutputStream, this.memberObject);
    }

    private static void writeMember(ObjectOutputStream objectOutputStream, Member member) throws IOException {
        if (member == null) {
            objectOutputStream.writeBoolean(false);
            return;
        }
        objectOutputStream.writeBoolean(true);
        boolean z = member instanceof Method;
        if (!z && !(member instanceof Constructor)) {
            throw new IllegalArgumentException("not Method or Constructor");
        }
        objectOutputStream.writeBoolean(z);
        objectOutputStream.writeObject(member.getName());
        objectOutputStream.writeObject(member.getDeclaringClass());
        if (z) {
            writeParameters(objectOutputStream, ((Method) member).getParameterTypes());
        } else {
            writeParameters(objectOutputStream, ((Constructor) member).getParameterTypes());
        }
    }

    private static Member readMember(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        if (!objectInputStream.readBoolean()) {
            return null;
        }
        boolean z = objectInputStream.readBoolean();
        String str = (String) objectInputStream.readObject();
        Class cls = (Class) objectInputStream.readObject();
        Class<?>[] parameters = readParameters(objectInputStream);
        try {
            if (z) {
                return cls.getMethod(str, parameters);
            }
            return cls.getConstructor(parameters);
        } catch (NoSuchMethodException e) {
            throw new IOException("Cannot find member: " + String.valueOf(e));
        }
    }

    private static void writeParameters(ObjectOutputStream objectOutputStream, Class<?>[] clsArr) throws IOException {
        objectOutputStream.writeShort(clsArr.length);
        for (Class<?> cls : clsArr) {
            boolean zIsPrimitive = cls.isPrimitive();
            objectOutputStream.writeBoolean(zIsPrimitive);
            if (zIsPrimitive) {
                int i = 0;
                while (true) {
                    Class<?>[] clsArr2 = primitives;
                    if (i < clsArr2.length) {
                        if (cls.equals(clsArr2[i])) {
                            objectOutputStream.writeByte(i);
                            break;
                        }
                        i++;
                    } else {
                        throw new IllegalArgumentException("Primitive " + String.valueOf(cls) + " not found");
                    }
                }
            } else {
                objectOutputStream.writeObject(cls);
            }
        }
    }

    private static Class<?>[] readParameters(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        int i = objectInputStream.readShort();
        Class<?>[] clsArr = new Class[i];
        for (int i2 = 0; i2 < i; i2++) {
            if (!objectInputStream.readBoolean()) {
                clsArr[i2] = (Class) objectInputStream.readObject();
            } else {
                clsArr[i2] = primitives[objectInputStream.readByte()];
            }
        }
        return clsArr;
    }
}
