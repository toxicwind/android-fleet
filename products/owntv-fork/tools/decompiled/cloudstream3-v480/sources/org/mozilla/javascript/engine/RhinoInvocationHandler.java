package org.mozilla.javascript.engine;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes8.dex */
public class RhinoInvocationHandler implements InvocationHandler {
    private final RhinoScriptEngine engine;
    private final Object thiz;

    RhinoInvocationHandler(RhinoScriptEngine rhinoScriptEngine, Object obj) {
        this.engine = rhinoScriptEngine;
        this.thiz = obj;
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
        return this.engine.invokeMethodRaw(this.thiz, method.getName(), method.getReturnType(), objArr);
    }
}
