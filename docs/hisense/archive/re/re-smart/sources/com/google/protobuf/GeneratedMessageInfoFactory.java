package com.google.protobuf;

import b.a.a.a.a;

/* JADX INFO: loaded from: classes2.dex */
public class GeneratedMessageInfoFactory implements MessageInfoFactory {
    public static final GeneratedMessageInfoFactory instance = new GeneratedMessageInfoFactory();

    public static GeneratedMessageInfoFactory getInstance() {
        return instance;
    }

    @Override // com.google.protobuf.MessageInfoFactory
    public boolean isSupported(Class<?> cls) {
        return GeneratedMessageLite.class.isAssignableFrom(cls);
    }

    @Override // com.google.protobuf.MessageInfoFactory
    public MessageInfo messageInfoFor(Class<?> cls) {
        if (!GeneratedMessageLite.class.isAssignableFrom(cls)) {
            StringBuilder sbA = a.a("Unsupported message type: ");
            sbA.append(cls.getName());
            throw new IllegalArgumentException(sbA.toString());
        }
        try {
            return (MessageInfo) GeneratedMessageLite.getDefaultInstance(cls.asSubclass(GeneratedMessageLite.class)).buildMessageInfo();
        } catch (Exception e2) {
            StringBuilder sbA2 = a.a("Unable to get message info for ");
            sbA2.append(cls.getName());
            throw new RuntimeException(sbA2.toString(), e2);
        }
    }
}
