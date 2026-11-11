package org.mozilla.javascript;

import java.lang.reflect.AccessibleObject;

/* JADX INFO: loaded from: classes8.dex */
public abstract class VMBridge {
    static final VMBridge instance = makeInstance();

    protected abstract Context getContext(Object obj);

    protected abstract Object getInterfaceProxyHelper(ContextFactory contextFactory, Class<?>[] clsArr);

    protected abstract Object getThreadContextHelper();

    protected abstract Object newInterfaceProxy(Object obj, ContextFactory contextFactory, InterfaceAdapter interfaceAdapter, Object obj2, Scriptable scriptable);

    protected abstract void setContext(Object obj, Context context);

    protected abstract boolean tryToMakeAccessible(AccessibleObject accessibleObject);

    private static VMBridge makeInstance() {
        VMBridge vMBridge;
        String[] strArr = {"org.mozilla.javascript.VMBridge_custom", "org.mozilla.javascript.jdk18.VMBridge_jdk18"};
        for (int i = 0; i != 2; i++) {
            Class<?> clsClassOrNull = Kit.classOrNull(strArr[i]);
            if (clsClassOrNull != null && (vMBridge = (VMBridge) Kit.newInstanceOrNull(clsClassOrNull)) != null) {
                return vMBridge;
            }
        }
        throw new IllegalStateException("Failed to create VMBridge instance");
    }
}
