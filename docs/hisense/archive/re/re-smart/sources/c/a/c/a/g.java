package c.a.c.a;

import java.lang.reflect.Method;
import java.security.PrivilegedExceptionAction;
import javax.net.ssl.SSLEngine;

/* JADX INFO: loaded from: classes2.dex */
public class g implements PrivilegedExceptionAction<Method> {
    @Override // java.security.PrivilegedExceptionAction
    public Method run() {
        return SSLEngine.class.getMethod("getApplicationProtocol", new Class[0]);
    }
}
