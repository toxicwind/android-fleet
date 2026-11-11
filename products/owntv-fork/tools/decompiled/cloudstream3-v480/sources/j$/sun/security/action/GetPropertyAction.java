package j$.sun.security.action;

import java.security.AccessController;
import java.security.PrivilegedAction;

/* JADX INFO: loaded from: classes3.dex */
public class GetPropertyAction implements PrivilegedAction {
    private String defaultVal;
    private String theProp;

    public GetPropertyAction(String str) {
        this.theProp = str;
    }

    @Override // java.security.PrivilegedAction
    public String run() {
        String property = System.getProperty(this.theProp);
        return property == null ? this.defaultVal : property;
    }

    public static String privilegedGetProperty(String str) {
        if (System.getSecurityManager() == null) {
            return System.getProperty(str);
        }
        return (String) AccessController.doPrivileged(new GetPropertyAction(str));
    }
}
