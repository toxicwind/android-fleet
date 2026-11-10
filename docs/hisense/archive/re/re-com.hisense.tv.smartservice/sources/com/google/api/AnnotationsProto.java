package com.google.api;

import com.google.protobuf.DescriptorProtos;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.WireFormat;

/* JADX INFO: loaded from: classes.dex */
public final class AnnotationsProto {
    public static final int HTTP_FIELD_NUMBER = 72295728;
    public static final GeneratedMessageLite.GeneratedExtension<DescriptorProtos.MethodOptions, HttpRule> http;

    static {
        DescriptorProtos.MethodOptions methodOptions = DescriptorProtos.MethodOptions.DEFAULT_INSTANCE;
        HttpRule httpRule = HttpRule.DEFAULT_INSTANCE;
        http = GeneratedMessageLite.newSingularGeneratedExtension(methodOptions, httpRule, httpRule, null, HTTP_FIELD_NUMBER, WireFormat.FieldType.MESSAGE, HttpRule.class);
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
        extensionRegistryLite.add((GeneratedMessageLite.GeneratedExtension<?, ?>) http);
    }
}
