package org.slf4j.helpers;

/* JADX INFO: loaded from: classes8.dex */
public class Slf4jEnvUtil {
    public static String slf4jVersion() {
        Package r0 = Slf4jEnvUtil.class.getPackage();
        if (r0 == null) {
            return null;
        }
        return r0.getImplementationVersion();
    }
}
