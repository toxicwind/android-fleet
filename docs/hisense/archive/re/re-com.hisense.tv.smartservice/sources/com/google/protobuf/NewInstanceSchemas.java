package com.google.protobuf;

/* JADX INFO: loaded from: classes2.dex */
public final class NewInstanceSchemas {
    public static final NewInstanceSchema FULL_SCHEMA = loadSchemaForFullRuntime();
    public static final NewInstanceSchema LITE_SCHEMA = new NewInstanceSchemaLite();

    public static NewInstanceSchema full() {
        return FULL_SCHEMA;
    }

    public static NewInstanceSchema lite() {
        return LITE_SCHEMA;
    }

    public static NewInstanceSchema loadSchemaForFullRuntime() {
        try {
            return (NewInstanceSchema) Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}
