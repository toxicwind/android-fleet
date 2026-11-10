package e.a.a.a;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes2.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final boolean f2156a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Method f2157b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Method f2158c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static Object f2159d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static Object f2160e;

    static {
        boolean z = false;
        try {
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            Class<?> clsLoadClass = contextClassLoader.loadClass("java.nio.file.Files");
            Class<?> clsLoadClass2 = contextClassLoader.loadClass("java.nio.file.Path");
            Class<?> clsLoadClass3 = contextClassLoader.loadClass("java.nio.file.attribute.FileAttribute");
            Class<?> clsLoadClass4 = contextClassLoader.loadClass("java.nio.file.LinkOption");
            f2157b = clsLoadClass.getMethod("isSymbolicLink", clsLoadClass2);
            clsLoadClass.getMethod("delete", clsLoadClass2);
            clsLoadClass.getMethod("readSymbolicLink", clsLoadClass2);
            f2160e = Array.newInstance(clsLoadClass3, 0);
            clsLoadClass.getMethod("createSymbolicLink", clsLoadClass2, clsLoadClass2, f2160e.getClass());
            f2159d = Array.newInstance(clsLoadClass4, 0);
            clsLoadClass.getMethod("exists", clsLoadClass2, f2159d.getClass());
            f2158c = File.class.getMethod("toPath", new Class[0]);
            clsLoadClass2.getMethod("toFile", new Class[0]);
            z = true;
        } catch (ClassNotFoundException | NoSuchMethodException unused) {
        }
        f2156a = z;
    }

    public static boolean a(File file) {
        try {
            return ((Boolean) f2157b.invoke(null, f2158c.invoke(file, new Object[0]))).booleanValue();
        } catch (IllegalAccessException e2) {
            throw new RuntimeException(e2);
        } catch (InvocationTargetException e3) {
            throw new RuntimeException(e3);
        }
    }
}
