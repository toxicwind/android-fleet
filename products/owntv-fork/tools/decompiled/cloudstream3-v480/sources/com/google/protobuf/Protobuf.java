package com.google.protobuf;

import j$.util.concurrent.ConcurrentHashMap;
import java.io.IOException;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: loaded from: classes6.dex */
@CheckReturnValue
final class Protobuf {
    private static final Protobuf INSTANCE = new Protobuf();
    private final ConcurrentMap<Class<?>, Schema<?>> schemaCache = new ConcurrentHashMap();
    private final ManifestSchemaFactory schemaFactory = new ManifestSchemaFactory();

    static Protobuf getInstance() {
        return INSTANCE;
    }

    <T> void writeTo(T message, Writer writer) throws IOException {
        schemaFor(message).writeTo(message, writer);
    }

    <T> void mergeFrom(T message, Reader reader, ExtensionRegistryLite extensionRegistry) throws IOException {
        schemaFor(message).mergeFrom(message, reader, extensionRegistry);
    }

    <T> boolean isInitialized(T message) {
        return schemaFor(message).isInitialized(message);
    }

    <T> Schema<T> schemaFor(Class<T> messageType) {
        Object obj = this.schemaCache.get(messageType);
        if (obj == null) {
            return registerSchema(messageType);
        }
        return (Schema) obj;
    }

    @DoNotInline
    private <T> Schema<T> registerSchema(Class<T> messageType) {
        Schema<T> schemaCreateSchema = this.schemaFactory.createSchema(messageType);
        Internal.checkNotNull(schemaCreateSchema, "schema");
        Schema<T> schema = (Schema) this.schemaCache.putIfAbsent(messageType, schemaCreateSchema);
        return schema != null ? schema : schemaCreateSchema;
    }

    <T> Schema<T> schemaFor(T message) {
        return schemaFor((Class) message.getClass());
    }

    private Protobuf() {
    }
}
