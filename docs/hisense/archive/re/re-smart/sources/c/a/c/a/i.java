package c.a.c.a;

import java.lang.reflect.Method;
import java.security.PrivilegedExceptionAction;
import javax.net.ssl.SSLSocket;

/* JADX INFO: loaded from: classes2.dex */
public class i implements PrivilegedExceptionAction<Method> {
    @Override // java.security.PrivilegedExceptionAction
    public Method run() {
        return SSLSocket.class.getMethod("getApplicationProtocol", new Class[0]);
    }
}
