package com.google.longrunning;

import com.google.protobuf.DescriptorProtos;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.WireFormat;

/* JADX INFO: loaded from: classes.dex */
public final class OperationsProto {
    public static final int OPERATION_INFO_FIELD_NUMBER = 1049;
    public static final GeneratedMessageLite.GeneratedExtension<DescriptorProtos.MethodOptions, OperationInfo> operationInfo;

    static {
        DescriptorProtos.MethodOptions methodOptions = DescriptorProtos.MethodOptions.DEFAULT_INSTANCE;
        OperationInfo operationInfo2 = OperationInfo.DEFAULT_INSTANCE;
        operationInfo = GeneratedMessageLite.newSingularGeneratedExtension(methodOptions, operationInfo2, operationInfo2, null, 1049, WireFormat.FieldType.MESSAGE, OperationInfo.class);
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
        extensionRegistryLite.add((GeneratedMessageLite.GeneratedExtension<?, ?>) operationInfo);
    }
}
