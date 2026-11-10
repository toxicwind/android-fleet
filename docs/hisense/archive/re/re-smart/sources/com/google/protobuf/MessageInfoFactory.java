package com.google.protobuf;

/* JADX INFO: loaded from: classes2.dex */
public interface MessageInfoFactory {
    boolean isSupported(Class<?> cls);

    MessageInfo messageInfoFor(Class<?> cls);
}
