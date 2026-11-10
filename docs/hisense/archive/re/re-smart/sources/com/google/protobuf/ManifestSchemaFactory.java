package com.google.protobuf;

import b.a.a.a.a;

/* JADX INFO: loaded from: classes2.dex */
public final class ManifestSchemaFactory implements SchemaFactory {
    public static final MessageInfoFactory EMPTY_FACTORY = new MessageInfoFactory() { // from class: com.google.protobuf.ManifestSchemaFactory.1
        @Override // com.google.protobuf.MessageInfoFactory
        public boolean isSupported(Class<?> cls) {
            return false;
        }

        @Override // com.google.protobuf.MessageInfoFactory
        public MessageInfo messageInfoFor(Class<?> cls) {
            throw new IllegalStateException("This should never be called.");
        }
    };
    public final MessageInfoFactory messageInfoFactory;

    private static class CompositeMessageInfoFactory implements MessageInfoFactory {
        public MessageInfoFactory[] factories;

        public CompositeMessageInfoFactory(MessageInfoFactory... messageInfoFactoryArr) {
            this.factories = messageInfoFactoryArr;
        }

        @Override // com.google.protobuf.MessageInfoFactory
        public boolean isSupported(Class<?> cls) {
            for (MessageInfoFactory messageInfoFactory : this.factories) {
                if (messageInfoFactory.isSupported(cls)) {
                    return true;
                }
            }
            return false;
        }

        @Override // com.google.protobuf.MessageInfoFactory
        public MessageInfo messageInfoFor(Class<?> cls) {
            for (MessageInfoFactory messageInfoFactory : this.factories) {
                if (messageInfoFactory.isSupported(cls)) {
                    return messageInfoFactory.messageInfoFor(cls);
                }
            }
            StringBuilder sbA = a.a("No factory is available for message type: ");
            sbA.append(cls.getName());
            throw new UnsupportedOperationException(sbA.toString());
        }
    }

    public ManifestSchemaFactory() {
        MessageInfoFactory defaultMessageInfoFactory = getDefaultMessageInfoFactory();
        Internal.checkNotNull(defaultMessageInfoFactory, "messageInfoFactory");
        this.messageInfoFactory = defaultMessageInfoFactory;
    }

    public static MessageInfoFactory getDefaultMessageInfoFactory() {
        return new CompositeMessageInfoFactory(GeneratedMessageInfoFactory.instance, getDescriptorMessageInfoFactory());
    }

    public static MessageInfoFactory getDescriptorMessageInfoFactory() {
        try {
            return (MessageInfoFactory) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            return EMPTY_FACTORY;
        }
    }

    public static boolean isProto2(MessageInfo messageInfo) {
        return messageInfo.getSyntax() == ProtoSyntax.PROTO2;
    }

    public static <T> Schema<T> newSchema(Class<T> cls, MessageInfo messageInfo) {
        if (GeneratedMessageLite.class.isAssignableFrom(cls)) {
            return isProto2(messageInfo) ? MessageSchema.newSchema(cls, messageInfo, NewInstanceSchemas.LITE_SCHEMA, ListFieldSchema.LITE_INSTANCE, SchemaUtil.UNKNOWN_FIELD_SET_LITE_SCHEMA, ExtensionSchemas.LITE_SCHEMA, MapFieldSchemas.LITE_SCHEMA) : MessageSchema.newSchema(cls, messageInfo, NewInstanceSchemas.LITE_SCHEMA, ListFieldSchema.LITE_INSTANCE, SchemaUtil.UNKNOWN_FIELD_SET_LITE_SCHEMA, null, MapFieldSchemas.LITE_SCHEMA);
        }
        return isProto2(messageInfo) ? MessageSchema.newSchema(cls, messageInfo, NewInstanceSchemas.FULL_SCHEMA, ListFieldSchema.FULL_INSTANCE, SchemaUtil.PROTO2_UNKNOWN_FIELD_SET_SCHEMA, ExtensionSchemas.full(), MapFieldSchemas.FULL_SCHEMA) : MessageSchema.newSchema(cls, messageInfo, NewInstanceSchemas.FULL_SCHEMA, ListFieldSchema.FULL_INSTANCE, SchemaUtil.PROTO3_UNKNOWN_FIELD_SET_SCHEMA, null, MapFieldSchemas.FULL_SCHEMA);
    }

    @Override // com.google.protobuf.SchemaFactory
    public <T> Schema<T> createSchema(Class<T> cls) {
        SchemaUtil.requireGeneratedMessage(cls);
        MessageInfo messageInfoMessageInfoFor = this.messageInfoFactory.messageInfoFor(cls);
        if (messageInfoMessageInfoFor.isMessageSetWireFormat()) {
            return GeneratedMessageLite.class.isAssignableFrom(cls) ? new MessageSetSchema(SchemaUtil.UNKNOWN_FIELD_SET_LITE_SCHEMA, ExtensionSchemas.LITE_SCHEMA, messageInfoMessageInfoFor.getDefaultInstance()) : new MessageSetSchema(SchemaUtil.PROTO2_UNKNOWN_FIELD_SET_SCHEMA, ExtensionSchemas.full(), messageInfoMessageInfoFor.getDefaultInstance());
        }
        return newSchema(cls, messageInfoMessageInfoFor);
    }

    public ManifestSchemaFactory(MessageInfoFactory messageInfoFactory) {
        Internal.checkNotNull(messageInfoFactory, "messageInfoFactory");
        this.messageInfoFactory = messageInfoFactory;
    }
}
