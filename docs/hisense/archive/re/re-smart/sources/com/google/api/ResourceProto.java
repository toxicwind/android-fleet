package com.google.api;

import com.google.protobuf.DescriptorProtos;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.WireFormat;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class ResourceProto {
    public static final int RESOURCE_DEFINITION_FIELD_NUMBER = 1053;
    public static final int RESOURCE_FIELD_NUMBER = 1053;
    public static final int RESOURCE_REFERENCE_FIELD_NUMBER = 1055;
    public static final GeneratedMessageLite.GeneratedExtension<DescriptorProtos.MessageOptions, ResourceDescriptor> resource;
    public static final GeneratedMessageLite.GeneratedExtension<DescriptorProtos.FileOptions, List<ResourceDescriptor>> resourceDefinition;
    public static final GeneratedMessageLite.GeneratedExtension<DescriptorProtos.FieldOptions, ResourceReference> resourceReference;

    static {
        DescriptorProtos.FieldOptions fieldOptions = DescriptorProtos.FieldOptions.DEFAULT_INSTANCE;
        ResourceReference resourceReference2 = ResourceReference.DEFAULT_INSTANCE;
        resourceReference = GeneratedMessageLite.newSingularGeneratedExtension(fieldOptions, resourceReference2, resourceReference2, null, RESOURCE_REFERENCE_FIELD_NUMBER, WireFormat.FieldType.MESSAGE, ResourceReference.class);
        resourceDefinition = GeneratedMessageLite.newRepeatedGeneratedExtension(DescriptorProtos.FileOptions.DEFAULT_INSTANCE, ResourceDescriptor.DEFAULT_INSTANCE, null, 1053, WireFormat.FieldType.MESSAGE, false, ResourceDescriptor.class);
        DescriptorProtos.MessageOptions messageOptions = DescriptorProtos.MessageOptions.DEFAULT_INSTANCE;
        ResourceDescriptor resourceDescriptor = ResourceDescriptor.DEFAULT_INSTANCE;
        resource = GeneratedMessageLite.newSingularGeneratedExtension(messageOptions, resourceDescriptor, resourceDescriptor, null, 1053, WireFormat.FieldType.MESSAGE, ResourceDescriptor.class);
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
        extensionRegistryLite.add((GeneratedMessageLite.GeneratedExtension<?, ?>) resourceReference);
        extensionRegistryLite.add((GeneratedMessageLite.GeneratedExtension<?, ?>) resourceDefinition);
        extensionRegistryLite.add((GeneratedMessageLite.GeneratedExtension<?, ?>) resource);
    }
}
