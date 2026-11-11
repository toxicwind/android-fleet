package org.mozilla.javascript;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashSet;

/* JADX INFO: loaded from: classes8.dex */
public class InterfaceAdapter {
    private final Object proxyHelper;

    static Object create(Context context, Class<?> cls, ScriptableObject scriptableObject) {
        if (!cls.isInterface()) {
            throw new IllegalArgumentException();
        }
        Scriptable topCallScope = ScriptRuntime.getTopCallScope(context);
        ClassCache classCache = ClassCache.get(topCallScope);
        InterfaceAdapter interfaceAdapter = (InterfaceAdapter) classCache.getInterfaceAdapter(cls);
        ContextFactory factory = context.getFactory();
        if (interfaceAdapter == null) {
            if (scriptableObject instanceof Callable) {
                Method[] methods = cls.getMethods();
                HashSet hashSet = new HashSet();
                HashSet hashSet2 = new HashSet();
                for (Method method : methods) {
                    if (isFunctionalMethodCandidate(method)) {
                        hashSet.add(method.getName());
                        if (hashSet.size() > 1) {
                            break;
                        }
                    } else {
                        hashSet2.add(method.getName());
                    }
                }
                if (hashSet.size() != 1 && (!hashSet.isEmpty() || hashSet2.size() != 1)) {
                    if (hashSet.isEmpty() && hashSet2.isEmpty()) {
                        throw Context.reportRuntimeErrorById("msg.no.empty.interface.conversion", cls.getName());
                    }
                    throw Context.reportRuntimeErrorById("msg.no.function.interface.conversion", cls.getName());
                }
            }
            interfaceAdapter = new InterfaceAdapter(factory, cls);
            classCache.cacheInterfaceAdapter(cls, interfaceAdapter);
        }
        InterfaceAdapter interfaceAdapter2 = interfaceAdapter;
        return VMBridge.instance.newInterfaceProxy(interfaceAdapter2.proxyHelper, factory, interfaceAdapter2, scriptableObject, topCallScope);
    }

    private static boolean isFunctionalMethodCandidate(Method method) {
        if (method.getName().equals("equals") || method.getName().equals("hashCode") || method.getName().equals("toString")) {
            return false;
        }
        return Modifier.isAbstract(method.getModifiers());
    }

    private InterfaceAdapter(ContextFactory contextFactory, Class<?> cls) {
        this.proxyHelper = VMBridge.instance.getInterfaceProxyHelper(contextFactory, new Class[]{cls});
    }

    public Object invoke(ContextFactory contextFactory, final Object obj, final Scriptable scriptable, final Object obj2, final Method method, final Object[] objArr) {
        return contextFactory.call(new ContextAction() { // from class: org.mozilla.javascript.InterfaceAdapter$$ExternalSyntheticLambda0
            @Override // org.mozilla.javascript.ContextAction
            public final Object run(Context context) {
                return this.f$0.m10872lambda$invoke$0$orgmozillajavascriptInterfaceAdapter(obj, scriptable, obj2, method, objArr, context);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX INFO: renamed from: invokeImpl, reason: merged with bridge method [inline-methods] */
    public Object m10872lambda$invoke$0$orgmozillajavascriptInterfaceAdapter(Context context, Object obj, Scriptable scriptable, Object obj2, Method method, Object[] objArr) {
        Callable callable;
        if (obj instanceof Callable) {
            callable = (Callable) obj;
        } else {
            String name = method.getName();
            Object property = ScriptableObject.getProperty((Scriptable) obj, name);
            if (property == Scriptable.NOT_FOUND) {
                Context.reportWarning(ScriptRuntime.getMessageById("msg.undefined.function.interface", name));
                Class<?> returnType = method.getReturnType();
                if (returnType == Void.TYPE) {
                    return null;
                }
                return Context.jsToJava(null, returnType);
            }
            if (!(property instanceof Callable)) {
                throw Context.reportRuntimeErrorById("msg.not.function.interface", name);
            }
            callable = (Callable) property;
        }
        WrapFactory wrapFactory = context.getWrapFactory();
        if (objArr == null) {
            objArr = ScriptRuntime.emptyArgs;
        } else {
            int length = objArr.length;
            for (int i = 0; i != length; i++) {
                Object obj3 = objArr[i];
                if (!(obj3 instanceof String) && !(obj3 instanceof Number) && !(obj3 instanceof Boolean)) {
                    objArr[i] = wrapFactory.wrap(context, scriptable, obj3, null);
                }
            }
        }
        Object objCall = callable.call(context, scriptable, wrapFactory.wrapAsJavaObject(context, scriptable, obj2, null), objArr);
        Class<?> returnType2 = method.getReturnType();
        if (returnType2 == Void.TYPE) {
            return null;
        }
        return Context.jsToJava(objCall, returnType2);
    }
}
