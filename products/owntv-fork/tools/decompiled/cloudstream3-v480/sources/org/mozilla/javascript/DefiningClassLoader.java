package org.mozilla.javascript;

/* JADX INFO: loaded from: classes8.dex */
public class DefiningClassLoader extends ClassLoader implements GeneratedClassLoader {
    private final ClassLoader parentLoader;

    public DefiningClassLoader() {
        this.parentLoader = getClass().getClassLoader();
    }

    public DefiningClassLoader(ClassLoader classLoader) {
        this.parentLoader = classLoader;
    }

    @Override // org.mozilla.javascript.GeneratedClassLoader
    public Class<?> defineClass(String str, byte[] bArr) {
        return super.defineClass(str, bArr, 0, bArr.length, SecurityUtilities.getProtectionDomain(getClass()));
    }

    @Override // org.mozilla.javascript.GeneratedClassLoader
    public void linkClass(Class<?> cls) {
        resolveClass(cls);
    }

    @Override // java.lang.ClassLoader
    public Class<?> loadClass(String str, boolean z) throws ClassNotFoundException {
        Class<?> clsFindLoadedClass = findLoadedClass(str);
        if (clsFindLoadedClass == null) {
            ClassLoader classLoader = this.parentLoader;
            if (classLoader != null) {
                clsFindLoadedClass = classLoader.loadClass(str);
            } else {
                clsFindLoadedClass = findSystemClass(str);
            }
        }
        if (z) {
            resolveClass(clsFindLoadedClass);
        }
        return clsFindLoadedClass;
    }
}
