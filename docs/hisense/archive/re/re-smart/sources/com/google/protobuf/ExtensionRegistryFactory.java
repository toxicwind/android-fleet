package com.google.protobuf;

/* JADX INFO: loaded from: classes2.dex */
public final class ExtensionRegistryFactory {
    public static final Class<?> EXTENSION_REGISTRY_CLASS = reflectExtensionRegistry();
    public static final String FULL_REGISTRY_CLASS_NAME = "com.google.protobuf.ExtensionRegistry";

    public static ExtensionRegistryLite create() {
        ExtensionRegistryLite extensionRegistryLiteInvokeSubclassFactory = invokeSubclassFactory("newInstance");
        return extensionRegistryLiteInvokeSubclassFactory != null ? extensionRegistryLiteInvokeSubclassFactory : new ExtensionRegistryLite();
    }

    public static ExtensionRegistryLite createEmpty() {
        ExtensionRegistryLite extensionRegistryLiteInvokeSubclassFactory = invokeSubclassFactory("getEmptyRegistry");
        return extensionRegistryLiteInvokeSubclassFactory != null ? extensionRegistryLiteInvokeSubclassFactory : ExtensionRegistryLite.EMPTY_REGISTRY_LITE;
    }

    public static final ExtensionRegistryLite invokeSubclassFactory(String str) {
        Class<?> cls = EXTENSION_REGISTRY_CLASS;
        if (cls == null) {
            return null;
        }
        try {
            return (ExtensionRegistryLite) cls.getDeclaredMethod(str, new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    public static boolean isFullRegistry(ExtensionRegistryLite extensionRegistryLite) {
        Class<?> cls = EXTENSION_REGISTRY_CLASS;
        return cls != null && cls.isAssignableFrom(extensionRegistryLite.getClass());
    }

    public static Class<?> reflectExtensionRegistry() {
        try {
            return Class.forName(FULL_REGISTRY_CLASS_NAME);
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }
}
