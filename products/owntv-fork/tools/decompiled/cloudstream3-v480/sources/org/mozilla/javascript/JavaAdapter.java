package org.mozilla.javascript;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.mozilla.classfile.ClassFileWriter;
import org.mozilla.javascript.optimizer.Signatures;

/* JADX INFO: loaded from: classes8.dex */
public final class JavaAdapter implements IdFunctionCall {
    private static final Object FTAG = "JavaAdapter";
    private static final int Id_JavaAdapter = 1;

    static class JavaAdapterSignature {
        Class<?>[] interfaces;
        Map<String, Integer> names;
        Class<?> superClass;

        JavaAdapterSignature(Class<?> cls, Class<?>[] clsArr, Map<String, Integer> map) {
            this.superClass = cls;
            this.interfaces = clsArr;
            this.names = map;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof JavaAdapterSignature)) {
                return false;
            }
            JavaAdapterSignature javaAdapterSignature = (JavaAdapterSignature) obj;
            if (this.superClass != javaAdapterSignature.superClass) {
                return false;
            }
            Class<?>[] clsArr = this.interfaces;
            Class<?>[] clsArr2 = javaAdapterSignature.interfaces;
            if (clsArr != clsArr2) {
                if (clsArr.length != clsArr2.length) {
                    return false;
                }
                int i = 0;
                while (true) {
                    Class<?>[] clsArr3 = this.interfaces;
                    if (i >= clsArr3.length) {
                        break;
                    }
                    if (clsArr3[i] != javaAdapterSignature.interfaces[i]) {
                        return false;
                    }
                    i++;
                }
            }
            if (this.names.size() != javaAdapterSignature.names.size()) {
                return false;
            }
            for (Map.Entry<String, Integer> entry : this.names.entrySet()) {
                String key = entry.getKey();
                int iIntValue = entry.getValue().intValue();
                if (iIntValue != ((Integer) j$.util.Map.EL.getOrDefault(javaAdapterSignature.names, key, Integer.valueOf(iIntValue + 1))).intValue()) {
                    return false;
                }
            }
            return true;
        }

        public int hashCode() {
            return this.names.size() ^ (this.superClass.hashCode() + Arrays.hashCode(this.interfaces));
        }
    }

    public static void init(Context context, Scriptable scriptable, boolean z) {
        IdFunctionObject idFunctionObject = new IdFunctionObject(new JavaAdapter(), FTAG, 1, "JavaAdapter", 1, scriptable);
        idFunctionObject.markAsConstructor(null);
        if (z) {
            idFunctionObject.sealObject();
        }
        idFunctionObject.exportAsScopeProperty();
    }

    @Override // org.mozilla.javascript.IdFunctionCall
    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (idFunctionObject.hasTag(FTAG) && idFunctionObject.methodId() == 1) {
            return js_createAdapter(context, scriptable, objArr);
        }
        throw idFunctionObject.unknown();
    }

    public static Object convertResult(Object obj, Class<?> cls) {
        if (obj != Undefined.instance || cls == ScriptRuntime.ObjectClass || cls == ScriptRuntime.StringClass) {
            return Context.jsToJava(obj, cls);
        }
        return null;
    }

    public static Scriptable createAdapterWrapper(Scriptable scriptable, Object obj) {
        NativeJavaObject nativeJavaObject = new NativeJavaObject(ScriptableObject.getTopLevelScope(scriptable), obj, null, true);
        nativeJavaObject.setPrototype(scriptable);
        return nativeJavaObject;
    }

    public static Object getAdapterSelf(Class<?> cls, Object obj) throws IllegalAccessException, NoSuchFieldException {
        return cls.getDeclaredField("self").get(obj);
    }

    static Object js_createAdapter(Context context, Scriptable scriptable, Object[] objArr) {
        Object objNewInstance;
        int length = objArr.length;
        if (length == 0) {
            throw ScriptRuntime.typeErrorById("msg.adapter.zero.args", new Object[0]);
        }
        int i = 0;
        while (i < length - 1) {
            Object obj = objArr[i];
            if (obj instanceof NativeObject) {
                break;
            }
            if (!(obj instanceof NativeJavaClass)) {
                throw ScriptRuntime.typeErrorById("msg.not.java.class.arg", String.valueOf(i), ScriptRuntime.toString(obj));
            }
            i++;
        }
        Class[] clsArr = new Class[i];
        Class<?> cls = null;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Class<?> classObject = ((NativeJavaClass) objArr[i3]).getClassObject();
            if (classObject.isInterface()) {
                clsArr[i2] = classObject;
                i2++;
            } else {
                if (cls != null) {
                    throw ScriptRuntime.typeErrorById("msg.only.one.super", cls.getName(), classObject.getName());
                }
                cls = classObject;
            }
        }
        if (cls == null) {
            cls = ScriptRuntime.ObjectClass;
        }
        Class[] clsArr2 = new Class[i2];
        System.arraycopy(clsArr, 0, clsArr2, 0, i2);
        Scriptable scriptableEnsureScriptable = ScriptableObject.ensureScriptable(objArr[i]);
        Class<?> adapterClass = getAdapterClass(scriptable, cls, clsArr2, scriptableEnsureScriptable);
        int i4 = length - i;
        int i5 = i4 - 1;
        try {
            if (i5 > 0) {
                Object[] objArr2 = new Object[i4 + 1];
                objArr2[0] = scriptableEnsureScriptable;
                objArr2[1] = context.getFactory();
                System.arraycopy(objArr, i + 1, objArr2, 2, i5);
                NativeJavaMethod nativeJavaMethod = new NativeJavaClass(scriptable, adapterClass, true).members.ctors;
                int iFindCachedFunction = nativeJavaMethod.findCachedFunction(context, objArr2);
                if (iFindCachedFunction < 0) {
                    throw Context.reportRuntimeErrorById("msg.no.java.ctor", adapterClass.getName(), NativeJavaMethod.scriptSignature(objArr));
                }
                objNewInstance = NativeJavaClass.constructInternal(objArr2, nativeJavaMethod.methods[iFindCachedFunction]);
            } else {
                objNewInstance = adapterClass.getConstructor(ScriptRuntime.ScriptableClass, ScriptRuntime.ContextFactoryClass).newInstance(scriptableEnsureScriptable, context.getFactory());
            }
            Object adapterSelf = getAdapterSelf(adapterClass, objNewInstance);
            if (adapterSelf instanceof Wrapper) {
                Object objUnwrap = ((Wrapper) adapterSelf).unwrap();
                if (objUnwrap instanceof Scriptable) {
                    if (objUnwrap instanceof ScriptableObject) {
                        ScriptRuntime.setObjectProtoAndParent((ScriptableObject) objUnwrap, scriptable);
                    }
                    return objUnwrap;
                }
            }
            return adapterSelf;
        } catch (Exception e) {
            throw Context.throwAsScriptRuntimeEx(e);
        }
    }

    public static void writeAdapterObject(Object obj, ObjectOutputStream objectOutputStream) throws IOException {
        Class<?> cls = obj.getClass();
        objectOutputStream.writeObject(cls.getSuperclass().getName());
        Class<?>[] interfaces = cls.getInterfaces();
        String[] strArr = new String[interfaces.length];
        for (int i = 0; i < interfaces.length; i++) {
            strArr[i] = interfaces[i].getName();
        }
        objectOutputStream.writeObject(strArr);
        try {
            objectOutputStream.writeObject(cls.getField("delegee").get(obj));
        } catch (IllegalAccessException | NoSuchFieldException unused) {
            throw new IOException();
        }
    }

    public static Object readAdapterObject(Scriptable scriptable, ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        Context currentContext = Context.getCurrentContext();
        ContextFactory factory = currentContext != null ? currentContext.getFactory() : null;
        Class<?> cls = Class.forName((String) objectInputStream.readObject());
        String[] strArr = (String[]) objectInputStream.readObject();
        Class[] clsArr = new Class[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            clsArr[i] = Class.forName(strArr[i]);
        }
        Scriptable scriptable2 = (Scriptable) objectInputStream.readObject();
        Class<?> adapterClass = getAdapterClass(scriptable, cls, clsArr, scriptable2);
        Class<Scriptable> cls2 = ScriptRuntime.ScriptableClass;
        try {
            return adapterClass.getConstructor(ScriptRuntime.ContextFactoryClass, cls2, cls2).newInstance(factory, scriptable2, scriptable);
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException unused) {
            throw new ClassNotFoundException("adapter");
        }
    }

    private static Map<String, Integer> getObjectFunctionNames(Scriptable scriptable) {
        Object[] propertyIds = ScriptableObject.getPropertyIds(scriptable);
        HashMap map = new HashMap();
        for (int i = 0; i != propertyIds.length; i++) {
            Object obj = propertyIds[i];
            if (obj instanceof String) {
                String str = (String) obj;
                Object property = ScriptableObject.getProperty(scriptable, str);
                if (property instanceof Function) {
                    int int32 = ScriptRuntime.toInt32(ScriptableObject.getProperty((Function) property, "length"));
                    if (int32 < 0) {
                        int32 = 0;
                    }
                    map.put(str, Integer.valueOf(int32));
                }
            }
        }
        return map;
    }

    private static Class<?> getAdapterClass(Scriptable scriptable, Class<?> cls, Class<?>[] clsArr, Scriptable scriptable2) {
        ClassCache classCache = ClassCache.get(scriptable);
        Map<JavaAdapterSignature, Class<?>> interfaceAdapterCacheMap = classCache.getInterfaceAdapterCacheMap();
        Map<String, Integer> objectFunctionNames = getObjectFunctionNames(scriptable2);
        JavaAdapterSignature javaAdapterSignature = new JavaAdapterSignature(cls, clsArr, objectFunctionNames);
        Class<?> cls2 = interfaceAdapterCacheMap.get(javaAdapterSignature);
        if (cls2 != null) {
            return cls2;
        }
        String str = "adapter" + classCache.newClassSerialNumber();
        Class<?> clsLoadAdapterClass = loadAdapterClass(str, createAdapterCode(objectFunctionNames, str, cls, clsArr, null));
        if (classCache.isCachingEnabled()) {
            interfaceAdapterCacheMap.put(javaAdapterSignature, clsLoadAdapterClass);
        }
        return clsLoadAdapterClass;
    }

    /* JADX WARN: Code duplicated, block: B:42:0x00dd  */
    /* JADX WARN: Code duplicated, block: B:43:0x00f6  */
    /* JADX WARN: Code duplicated, block: B:59:0x0182  */
    public static byte[] createAdapterCode(Map<String, Integer> map, String str, Class<?> cls, Class<?>[] clsArr, String str2) {
        String str3;
        int i;
        ClassFileWriter classFileWriter;
        int i2;
        String str4;
        Map<String, Integer> map2 = map;
        Class<?>[] clsArr2 = clsArr;
        ClassFileWriter classFileWriter2 = new ClassFileWriter(str, cls.getName(), "<adapter>");
        classFileWriter2.addField("factory", "Lorg/mozilla/javascript/ContextFactory;", (short) 17);
        classFileWriter2.addField("delegee", "Lorg/mozilla/javascript/Scriptable;", (short) 17);
        classFileWriter2.addField("self", "Lorg/mozilla/javascript/Scriptable;", (short) 17);
        int i3 = 0;
        int length = clsArr2 == null ? 0 : clsArr2.length;
        for (int i4 = 0; i4 < length; i4++) {
            Class<?> cls2 = clsArr2[i4];
            if (cls2 != null) {
                classFileWriter2.addInterface(cls2.getName());
            }
        }
        String strReplace = cls.getName().replace('.', '/');
        for (Constructor<?> constructor : cls.getDeclaredConstructors()) {
            int modifiers = constructor.getModifiers();
            if (Modifier.isPublic(modifiers) || Modifier.isProtected(modifiers)) {
                generateCtor(classFileWriter2, str, strReplace, constructor);
            }
        }
        generateSerialCtor(classFileWriter2, str, strReplace);
        if (str2 != null) {
            generateEmptyCtor(classFileWriter2, str, strReplace, str2);
        }
        HashMap map3 = new HashMap();
        HashMap map4 = new HashMap();
        int i5 = 0;
        while (i5 < length) {
            Method[] methods = clsArr2[i5].getMethods();
            int length2 = methods.length;
            int i6 = i3;
            while (i6 < length2) {
                Method method = methods[i6];
                int modifiers2 = method.getModifiers();
                if (Modifier.isStatic(modifiers2) || Modifier.isFinal(modifiers2) || method.isDefault()) {
                    i = length2;
                    classFileWriter = classFileWriter2;
                    i2 = i6;
                } else {
                    int i7 = length2;
                    classFileWriter = classFileWriter2;
                    String name = method.getName();
                    int i8 = i6;
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    if (!map2.containsKey(name)) {
                        try {
                            cls.getMethod(name, parameterTypes);
                        } catch (NoSuchMethodException unused) {
                            str4 = name + getMethodSignature(method, parameterTypes);
                            if (map3.containsKey(str4)) {
                                classFileWriter = classFileWriter;
                            } else {
                                i2 = i8;
                                classFileWriter = classFileWriter;
                                i = i7;
                                generateMethod(classFileWriter, str, name, parameterTypes, method.getReturnType(), true);
                                map3.put(str4, 0);
                                map4.put(name, 0);
                            }
                            i6 = i2 + 1;
                            classFileWriter2 = classFileWriter;
                            length2 = i;
                        }
                        i2 = i8;
                        i = i7;
                    }
                    str4 = name + getMethodSignature(method, parameterTypes);
                    if (map3.containsKey(str4)) {
                        i2 = i8;
                        classFileWriter = classFileWriter;
                        i = i7;
                        generateMethod(classFileWriter, str, name, parameterTypes, method.getReturnType(), true);
                        map3.put(str4, 0);
                        map4.put(name, 0);
                    } else {
                        classFileWriter = classFileWriter;
                        i2 = i8;
                        i = i7;
                    }
                }
                i6 = i2 + 1;
                classFileWriter2 = classFileWriter;
                length2 = i;
            }
            i5++;
            clsArr2 = clsArr;
            i3 = 0;
        }
        ClassFileWriter classFileWriter3 = classFileWriter2;
        Method[] overridableMethods = getOverridableMethods(cls);
        int length3 = overridableMethods.length;
        int i9 = 0;
        while (i9 < length3) {
            Method method2 = overridableMethods[i9];
            boolean zIsAbstract = Modifier.isAbstract(method2.getModifiers());
            String name2 = method2.getName();
            if (zIsAbstract || map2.containsKey(name2)) {
                Class<?>[] parameterTypes2 = method2.getParameterTypes();
                String methodSignature = getMethodSignature(method2, parameterTypes2);
                String str5 = name2 + methodSignature;
                if (map3.containsKey(str5)) {
                    str3 = strReplace;
                } else {
                    generateMethod(classFileWriter3, str, name2, parameterTypes2, method2.getReturnType(), true);
                    map3.put(str5, 0);
                    map4.put(name2, 0);
                    if (zIsAbstract) {
                        str3 = strReplace;
                    } else {
                        str3 = strReplace;
                        generateSuper(classFileWriter3, str, str3, name2, methodSignature, parameterTypes2, method2.getReturnType());
                    }
                }
            } else {
                str3 = strReplace;
            }
            i9++;
            map2 = map;
            strReplace = str3;
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            if (!map4.containsKey(key)) {
                int iIntValue = entry.getValue().intValue();
                Class[] clsArr3 = new Class[iIntValue];
                for (int i10 = 0; i10 < iIntValue; i10++) {
                    clsArr3[i10] = ScriptRuntime.ObjectClass;
                }
                ClassFileWriter classFileWriter4 = classFileWriter3;
                generateMethod(classFileWriter4, str, key, clsArr3, ScriptRuntime.ObjectClass, false);
                classFileWriter3 = classFileWriter4;
            }
        }
        return classFileWriter3.toByteArray();
    }

    static Method[] getOverridableMethods(Class<?> cls) {
        ArrayList arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        for (Class<?> superclass = cls; superclass != null; superclass = superclass.getSuperclass()) {
            appendOverridableMethods(superclass, arrayList, hashSet);
        }
        while (true) {
            if (cls != null) {
                for (Class<?> cls2 : cls.getInterfaces()) {
                    appendOverridableMethods(cls2, arrayList, hashSet);
                }
                cls = cls.getSuperclass();
            } else {
                return (Method[]) arrayList.toArray(new Method[0]);
            }
        }
    }

    private static void appendOverridableMethods(Class<?> cls, List<Method> list, Set<String> set) {
        for (Method method : cls.isInterface() ? cls.getMethods() : cls.getDeclaredMethods()) {
            String str = method.getName() + getMethodSignature(method, method.getParameterTypes());
            if (!set.contains(str)) {
                int modifiers = method.getModifiers();
                if (!Modifier.isStatic(modifiers)) {
                    if (Modifier.isFinal(modifiers)) {
                        set.add(str);
                    } else if (Modifier.isPublic(modifiers) || Modifier.isProtected(modifiers)) {
                        list.add(method);
                        set.add(str);
                    }
                }
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x000e  */
    static Class<?> loadAdapterClass(String str, byte[] bArr) {
        ProtectionDomain codeSource;
        Class<?> staticSecurityDomainClass = SecurityController.getStaticSecurityDomainClass();
        if (staticSecurityDomainClass == CodeSource.class || staticSecurityDomainClass == ProtectionDomain.class) {
            ProtectionDomain scriptProtectionDomain = SecurityUtilities.getScriptProtectionDomain();
            ProtectionDomain protectionDomain = scriptProtectionDomain;
            if (scriptProtectionDomain == null) {
                protectionDomain = JavaAdapter.class.getProtectionDomain();
            }
            codeSource = protectionDomain;
            if (staticSecurityDomainClass == CodeSource.class) {
                if (protectionDomain == null) {
                    codeSource = null;
                } else {
                    codeSource = protectionDomain.getCodeSource();
                }
            }
        } else {
            codeSource = null;
        }
        GeneratedClassLoader generatedClassLoaderCreateLoader = SecurityController.createLoader(null, codeSource);
        Class<?> clsDefineClass = generatedClassLoaderCreateLoader.defineClass(str, bArr);
        generatedClassLoaderCreateLoader.linkClass(clsDefineClass);
        return clsDefineClass;
    }

    public static Function getFunction(Scriptable scriptable, String str) {
        Object property = ScriptableObject.getProperty(scriptable, str);
        if (property == Scriptable.NOT_FOUND) {
            return null;
        }
        if (!(property instanceof Function)) {
            throw ScriptRuntime.notFunctionError(property, str);
        }
        return (Function) property;
    }

    public static Object callMethod(ContextFactory contextFactory, final Scriptable scriptable, final Function function, final Object[] objArr, final long j) {
        if (function == null) {
            return null;
        }
        if (contextFactory == null) {
            contextFactory = ContextFactory.getGlobal();
        }
        final Scriptable parentScope = function.getParentScope();
        if (j == 0) {
            return Context.call(contextFactory, function, parentScope, scriptable, objArr);
        }
        Context currentContext = Context.getCurrentContext();
        if (currentContext != null) {
            return doCall(currentContext, parentScope, scriptable, function, objArr, j);
        }
        return contextFactory.call(new ContextAction() { // from class: org.mozilla.javascript.JavaAdapter$$ExternalSyntheticLambda1
            @Override // org.mozilla.javascript.ContextAction
            public final Object run(Context context) {
                return JavaAdapter.doCall(context, parentScope, scriptable, function, objArr, j);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object doCall(Context context, Scriptable scriptable, Scriptable scriptable2, Function function, Object[] objArr, long j) {
        for (int i = 0; i != objArr.length; i++) {
            if (0 != (((long) (1 << i)) & j)) {
                Object obj = objArr[i];
                if (!(obj instanceof Scriptable)) {
                    objArr[i] = context.getWrapFactory().wrap(context, scriptable, obj, null);
                }
            }
        }
        return function.call(context, scriptable, scriptable2, objArr);
    }

    public static Scriptable runScript(final Script script) {
        return (Scriptable) ContextFactory.getGlobal().call(new ContextAction() { // from class: org.mozilla.javascript.JavaAdapter$$ExternalSyntheticLambda2
            @Override // org.mozilla.javascript.ContextAction
            public final Object run(Context context) {
                return JavaAdapter.lambda$runScript$1(script, context);
            }
        });
    }

    static /* synthetic */ ScriptableObject lambda$runScript$1(Script script, Context context) {
        ScriptableObject global = ScriptRuntime.getGlobal(context);
        script.exec(context, global);
        return global;
    }

    private static void generateCtor(ClassFileWriter classFileWriter, String str, String str2, Constructor<?> constructor) {
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        int iGeneratePushParam = 3;
        if (parameterTypes.length == 0) {
            classFileWriter.startMethod("<init>", "(Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/ContextFactory;)V", (short) 1);
            classFileWriter.add(42);
            classFileWriter.addInvoke(183, str2, "<init>", "()V");
        } else {
            StringBuilder sb = new StringBuilder("(Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/ContextFactory;");
            int length = sb.length();
            for (Class<?> cls : parameterTypes) {
                appendTypeString(sb, cls);
            }
            sb.append(")V");
            classFileWriter.startMethod("<init>", sb.toString(), (short) 1);
            classFileWriter.add(42);
            for (Class<?> cls2 : parameterTypes) {
                iGeneratePushParam += generatePushParam(classFileWriter, iGeneratePushParam, cls2);
            }
            sb.delete(1, length);
            classFileWriter.addInvoke(183, str2, "<init>", sb.toString());
        }
        classFileWriter.add(42);
        classFileWriter.add(43);
        classFileWriter.add(181, str, "delegee", "Lorg/mozilla/javascript/Scriptable;");
        classFileWriter.add(42);
        classFileWriter.add(44);
        classFileWriter.add(181, str, "factory", "Lorg/mozilla/javascript/ContextFactory;");
        classFileWriter.add(42);
        classFileWriter.add(43);
        classFileWriter.add(42);
        classFileWriter.addInvoke(184, "org/mozilla/javascript/JavaAdapter", "createAdapterWrapper", "(Lorg/mozilla/javascript/Scriptable;Ljava/lang/Object;)Lorg/mozilla/javascript/Scriptable;");
        classFileWriter.add(181, str, "self", "Lorg/mozilla/javascript/Scriptable;");
        classFileWriter.add(177);
        classFileWriter.stopMethod(iGeneratePushParam);
    }

    private static void generateSerialCtor(ClassFileWriter classFileWriter, String str, String str2) {
        classFileWriter.startMethod("<init>", "(Lorg/mozilla/javascript/ContextFactory;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;)V", (short) 1);
        classFileWriter.add(42);
        classFileWriter.addInvoke(183, str2, "<init>", "()V");
        classFileWriter.add(42);
        classFileWriter.add(43);
        classFileWriter.add(181, str, "factory", "Lorg/mozilla/javascript/ContextFactory;");
        classFileWriter.add(42);
        classFileWriter.add(44);
        classFileWriter.add(181, str, "delegee", "Lorg/mozilla/javascript/Scriptable;");
        classFileWriter.add(42);
        classFileWriter.add(45);
        classFileWriter.add(181, str, "self", "Lorg/mozilla/javascript/Scriptable;");
        classFileWriter.add(177);
        classFileWriter.stopMethod(4);
    }

    private static void generateEmptyCtor(ClassFileWriter classFileWriter, String str, String str2, String str3) {
        classFileWriter.startMethod("<init>", "()V", (short) 1);
        classFileWriter.add(42);
        classFileWriter.addInvoke(183, str2, "<init>", "()V");
        classFileWriter.add(42);
        classFileWriter.add(1);
        classFileWriter.add(181, str, "factory", "Lorg/mozilla/javascript/ContextFactory;");
        classFileWriter.add(187, str3);
        classFileWriter.add(89);
        classFileWriter.addInvoke(183, str3, "<init>", "()V");
        classFileWriter.addInvoke(184, "org/mozilla/javascript/JavaAdapter", "runScript", "(Lorg/mozilla/javascript/Script;)Lorg/mozilla/javascript/Scriptable;");
        classFileWriter.add(76);
        classFileWriter.add(42);
        classFileWriter.add(43);
        classFileWriter.add(181, str, "delegee", "Lorg/mozilla/javascript/Scriptable;");
        classFileWriter.add(42);
        classFileWriter.add(43);
        classFileWriter.add(42);
        classFileWriter.addInvoke(184, "org/mozilla/javascript/JavaAdapter", "createAdapterWrapper", "(Lorg/mozilla/javascript/Scriptable;Ljava/lang/Object;)Lorg/mozilla/javascript/Scriptable;");
        classFileWriter.add(181, str, "self", "Lorg/mozilla/javascript/Scriptable;");
        classFileWriter.add(177);
        classFileWriter.stopMethod(2);
    }

    static void generatePushWrappedArgs(ClassFileWriter classFileWriter, Class<?>[] clsArr, int i) {
        classFileWriter.addPush(i);
        classFileWriter.add(189, "java/lang/Object");
        int iGenerateWrapArg = 1;
        for (int i2 = 0; i2 != clsArr.length; i2++) {
            classFileWriter.add(89);
            classFileWriter.addPush(i2);
            iGenerateWrapArg += generateWrapArg(classFileWriter, iGenerateWrapArg, clsArr[i2]);
            classFileWriter.add(83);
        }
    }

    /* JADX WARN: Code duplicated, block: B:31:0x0087  */
    private static int generateWrapArg(ClassFileWriter classFileWriter, int i, Class<?> cls) {
        int i2 = 1;
        if (!cls.isPrimitive()) {
            classFileWriter.add(25, i);
            return 1;
        }
        if (cls == Boolean.TYPE) {
            classFileWriter.add(187, "java/lang/Boolean");
            classFileWriter.add(89);
            classFileWriter.add(21, i);
            classFileWriter.addInvoke(183, "java/lang/Boolean", "<init>", "(Z)V");
            return 1;
        }
        if (cls == Character.TYPE) {
            classFileWriter.add(21, i);
            classFileWriter.addInvoke(184, "java/lang/String", "valueOf", "(C)Ljava/lang/String;");
            return 1;
        }
        classFileWriter.add(187, "java/lang/Double");
        classFileWriter.add(89);
        char cCharAt = cls.getName().charAt(0);
        if (cCharAt == 'b') {
            classFileWriter.add(21, i);
            classFileWriter.add(135);
        } else {
            if (cCharAt == 'd') {
                classFileWriter.add(24, i);
            } else if (cCharAt == 'f') {
                classFileWriter.add(23, i);
                classFileWriter.add(141);
            } else if (cCharAt == 'i') {
                classFileWriter.add(21, i);
                classFileWriter.add(135);
            } else if (cCharAt == 'l') {
                classFileWriter.add(22, i);
                classFileWriter.add(138);
            } else if (cCharAt == 's') {
                classFileWriter.add(21, i);
                classFileWriter.add(135);
            }
            i2 = 2;
        }
        classFileWriter.addInvoke(183, "java/lang/Double", "<init>", "(D)V");
        return i2;
    }

    static void generateReturnResult(ClassFileWriter classFileWriter, Class<?> cls, boolean z) {
        if (cls == Void.TYPE) {
            classFileWriter.add(87);
            classFileWriter.add(177);
            return;
        }
        if (cls == Boolean.TYPE) {
            classFileWriter.addInvoke(184, "org/mozilla/javascript/Context", "toBoolean", Signatures.MATH_TO_BOOLEAN);
            classFileWriter.add(172);
            return;
        }
        if (cls == Character.TYPE) {
            classFileWriter.addInvoke(184, "org/mozilla/javascript/Context", "toString", "(Ljava/lang/Object;)Ljava/lang/String;");
            classFileWriter.add(3);
            classFileWriter.addInvoke(182, "java/lang/String", "charAt", "(I)C");
            classFileWriter.add(172);
            return;
        }
        if (cls.isPrimitive()) {
            classFileWriter.addInvoke(184, "org/mozilla/javascript/Context", "toNumber", Signatures.MATH_TO_NUMBER);
            char cCharAt = cls.getName().charAt(0);
            if (cCharAt != 'b') {
                if (cCharAt == 'd') {
                    classFileWriter.add(175);
                    return;
                }
                if (cCharAt == 'f') {
                    classFileWriter.add(144);
                    classFileWriter.add(174);
                    return;
                } else if (cCharAt != 'i') {
                    if (cCharAt == 'l') {
                        classFileWriter.add(143);
                        classFileWriter.add(173);
                        return;
                    } else if (cCharAt != 's') {
                        throw new RuntimeException("Unexpected return type " + String.valueOf(cls));
                    }
                }
            }
            classFileWriter.add(142);
            classFileWriter.add(172);
            return;
        }
        String name = cls.getName();
        if (z) {
            classFileWriter.addLoadConstant(name);
            classFileWriter.addInvoke(184, "java/lang/Class", "forName", "(Ljava/lang/String;)Ljava/lang/Class;");
            classFileWriter.addInvoke(184, "org/mozilla/javascript/JavaAdapter", "convertResult", "(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;");
        }
        classFileWriter.add(192, name);
        classFileWriter.add(176);
    }

    private static void generateMethod(ClassFileWriter classFileWriter, String str, String str2, Class<?>[] clsArr, Class<?> cls, boolean z) {
        StringBuilder sb = new StringBuilder();
        int iAppendMethodSignature = appendMethodSignature(clsArr, cls, sb);
        classFileWriter.startMethod(str2, sb.toString(), (short) 1);
        classFileWriter.add(42);
        classFileWriter.add(180, str, "factory", "Lorg/mozilla/javascript/ContextFactory;");
        classFileWriter.add(42);
        classFileWriter.add(180, str, "self", "Lorg/mozilla/javascript/Scriptable;");
        classFileWriter.add(42);
        classFileWriter.add(180, str, "delegee", "Lorg/mozilla/javascript/Scriptable;");
        classFileWriter.addPush(str2);
        classFileWriter.addInvoke(184, "org/mozilla/javascript/JavaAdapter", "getFunction", "(Lorg/mozilla/javascript/Scriptable;Ljava/lang/String;)Lorg/mozilla/javascript/Function;");
        generatePushWrappedArgs(classFileWriter, clsArr, clsArr.length);
        if (clsArr.length > 64) {
            throw Context.reportRuntimeErrorById("JavaAdapter can not subclass methods with more then 64 arguments.", new Object[0]);
        }
        long j = 0;
        for (int i = 0; i != clsArr.length; i++) {
            if (!clsArr[i].isPrimitive()) {
                j |= (long) (1 << i);
            }
        }
        classFileWriter.addPush(j);
        classFileWriter.addInvoke(184, "org/mozilla/javascript/JavaAdapter", "callMethod", "(Lorg/mozilla/javascript/ContextFactory;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Function;[Ljava/lang/Object;J)Ljava/lang/Object;");
        generateReturnResult(classFileWriter, cls, z);
        classFileWriter.stopMethod((short) iAppendMethodSignature);
    }

    private static int generatePushParam(ClassFileWriter classFileWriter, int i, Class<?> cls) {
        if (!cls.isPrimitive()) {
            classFileWriter.addALoad(i);
            return 1;
        }
        char cCharAt = cls.getName().charAt(0);
        if (cCharAt != 'f') {
            if (cCharAt != 'i') {
                if (cCharAt == 'l') {
                    classFileWriter.addLLoad(i);
                    return 2;
                }
                if (cCharAt != 's' && cCharAt != 'z') {
                    switch (cCharAt) {
                        case 'b':
                        case 'c':
                            break;
                        case 'd':
                            classFileWriter.addDLoad(i);
                            return 2;
                        default:
                            throw Kit.codeBug();
                    }
                }
            }
            classFileWriter.addILoad(i);
            return 1;
        }
        classFileWriter.addFLoad(i);
        return 1;
    }

    private static void generatePopResult(ClassFileWriter classFileWriter, Class<?> cls) {
        if (cls.isPrimitive()) {
            char cCharAt = cls.getName().charAt(0);
            if (cCharAt != 'f') {
                if (cCharAt != 'i') {
                    if (cCharAt == 'l') {
                        classFileWriter.add(173);
                        return;
                    } else if (cCharAt != 's' && cCharAt != 'z') {
                        switch (cCharAt) {
                            case 'd':
                                classFileWriter.add(175);
                                break;
                        }
                        return;
                    }
                }
                classFileWriter.add(172);
                return;
            }
            classFileWriter.add(174);
            return;
        }
        classFileWriter.add(176);
    }

    private static void generateSuper(ClassFileWriter classFileWriter, String str, String str2, String str3, String str4, Class<?>[] clsArr, Class<?> cls) {
        classFileWriter.startMethod("super$" + str3, str4, (short) 1);
        classFileWriter.add(25, 0);
        int iGeneratePushParam = 1;
        for (Class<?> cls2 : clsArr) {
            iGeneratePushParam += generatePushParam(classFileWriter, iGeneratePushParam, cls2);
        }
        classFileWriter.addInvoke(183, str2, str3, str4);
        if (!cls.equals(Void.TYPE)) {
            generatePopResult(classFileWriter, cls);
        } else {
            classFileWriter.add(177);
        }
        classFileWriter.stopMethod((short) (iGeneratePushParam + 1));
    }

    private static String getMethodSignature(Method method, Class<?>[] clsArr) {
        StringBuilder sb = new StringBuilder();
        appendMethodSignature(clsArr, method.getReturnType(), sb);
        return sb.toString();
    }

    static int appendMethodSignature(Class<?>[] clsArr, Class<?> cls, StringBuilder sb) {
        sb.append('(');
        int length = clsArr.length + 1;
        for (Class<?> cls2 : clsArr) {
            appendTypeString(sb, cls2);
            if (cls2 == Long.TYPE || cls2 == Double.TYPE) {
                length++;
            }
        }
        sb.append(')');
        appendTypeString(sb, cls);
        return length;
    }

    private static StringBuilder appendTypeString(StringBuilder sb, Class<?> cls) {
        char upperCase;
        while (cls.isArray()) {
            sb.append(AbstractJsonLexerKt.BEGIN_LIST);
            cls = cls.getComponentType();
        }
        if (cls.isPrimitive()) {
            if (cls == Boolean.TYPE) {
                upperCase = 'Z';
            } else {
                upperCase = cls == Long.TYPE ? 'J' : Character.toUpperCase(cls.getName().charAt(0));
            }
            sb.append(upperCase);
            return sb;
        }
        sb.append('L');
        sb.append(cls.getName().replace('.', '/'));
        sb.append(';');
        return sb;
    }

    static int[] getArgsToConvert(Class<?>[] clsArr) {
        int i = 0;
        for (int i2 = 0; i2 != clsArr.length; i2++) {
            if (!clsArr[i2].isPrimitive()) {
                i++;
            }
        }
        if (i == 0) {
            return null;
        }
        int[] iArr = new int[i];
        int i3 = 0;
        for (int i4 = 0; i4 != clsArr.length; i4++) {
            if (!clsArr[i4].isPrimitive()) {
                iArr[i3] = i4;
                i3++;
            }
        }
        return iArr;
    }
}
