package c.a.c.a;

import java.lang.reflect.Method;
import java.security.PrivilegedExceptionAction;
import javax.net.ssl.SSLParameters;

/* JADX INFO: loaded from: classes2.dex */
public class h implements PrivilegedExceptionAction<Method> {
    @Override // java.security.PrivilegedExceptionAction
    public Method run() {
        return SSLParameters.class.getMethod("setApplicationProtocols", String[].class);
    }
}
