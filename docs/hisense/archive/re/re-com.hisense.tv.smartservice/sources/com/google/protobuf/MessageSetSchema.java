package com.google.protobuf;

/* JADX INFO: loaded from: classes2.dex */
public final class MessageSetSchema<T> implements Schema<T> {
    public final MessageLite defaultInstance;
    public final ExtensionSchema<?> extensionSchema;
    public final boolean hasExtensions;
    public final UnknownFieldSchema<?, ?> unknownFieldSchema;

    public MessageSetSchema(UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MessageLite messageLite) {
        this.unknownFieldSchema = unknownFieldSchema;
        this.hasExtensions = extensionSchema.hasExtensions(messageLite);
        this.extensionSchema = extensionSchema;
        this.defaultInstance = messageLite;
    }

    private <UT, UB> int getUnknownFieldsSerializedSize(UnknownFieldSchema<UT, UB> unknownFieldSchema, T t) {
        return unknownFieldSchema.getSerializedSizeAsMessageSet(unknownFieldSchema.getFromMessage(t));
    }

    private <UT, UB, ET extends FieldSet.FieldDescriptorLite<ET>> void mergeFromHelper(UnknownFieldSchema<UT, UB> unknownFieldSchema, ExtensionSchema<ET> extensionSchema, T t, Reader reader, ExtensionRegistryLite extensionRegistryLite) {
        UB builderFromMessage = unknownFieldSchema.getBuilderFromMessage(t);
        FieldSet<ET> mutableExtensions = extensionSchema.getMutableExtensions(t);
        while (reader.getFieldNumber() != Integer.MAX_VALUE) {
            try {
                if (!parseMessageSetItemOrUnknownField(reader, extensionRegistryLite, extensionSchema, mutableExtensions, unknownFieldSchema, builderFromMessage)) {
                    return;
                }
            } finally {
                unknownFieldSchema.setBuilderToMessage(t, builderFromMessage);
            }
        }
    }

    public static <T> MessageSetSchema<T> newSchema(UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MessageLite messageLite) {
        return new MessageSetSchema<>(unknownFieldSchema, extensionSchema, messageLite);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <UT, UB, ET extends FieldSet.FieldDescriptorLite<ET>> boolean parseMessageSetItemOrUnknownField(Reader reader, ExtensionRegistryLite extensionRegistryLite, ExtensionSchema<ET> extensionSchema, FieldSet<ET> fieldSet, UnknownFieldSchema<UT, UB> unknownFieldSchema, UB ub) throws InvalidProtocolBufferException {
        int tag = reader.getTag();
        if (tag != WireFormat.MESSAGE_SET_ITEM_TAG) {
            if ((tag & 7) != 2) {
                return reader.skipField();
            }
            Object objFindExtensionByNumber = extensionSchema.findExtensionByNumber(extensionRegistryLite, this.defaultInstance, tag >>> 3);
            if (objFindExtensionByNumber == null) {
                return unknownFieldSchema.mergeOneFieldFrom(ub, reader);
            }
            extensionSchema.parseLengthPrefixedMessageSetItem(reader, objFindExtensionByNumber, extensionRegistryLite, fieldSet);
            return true;
        }
        Object objFindExtensionByNumber2 = null;
        int uInt32 = 0;
        ByteString bytes = null;
        while (reader.getFieldNumber() != Integer.MAX_VALUE) {
            int tag2 = reader.getTag();
            if (tag2 == WireFormat.MESSAGE_SET_TYPE_ID_TAG) {
                uInt32 = reader.readUInt32();
                objFindExtensionByNumber2 = extensionSchema.findExtensionByNumber(extensionRegistryLite, this.defaultInstance, uInt32);
            } else if (tag2 == WireFormat.MESSAGE_SET_MESSAGE_TAG) {
                if (objFindExtensionByNumber2 != null) {
                    extensionSchema.parseLengthPrefixedMessageSetItem(reader, objFindExtensionByNumber2, extensionRegistryLite, fieldSet);
                } else {
                    bytes = reader.readBytes();
                }
            } else if (!reader.skipField()) {
                break;
            }
        }
        if (reader.getTag() != WireFormat.MESSAGE_SET_ITEM_END_TAG) {
            throw InvalidProtocolBufferException.invalidEndTag();
        }
        if (bytes != null) {
            if (objFindExtensionByNumber2 != null) {
                extensionSchema.parseMessageSetItem(bytes, objFindExtensionByNumber2, extensionRegistryLite, fieldSet);
            } else {
                unknownFieldSchema.addLengthDelimited(ub, uInt32, bytes);
            }
        }
        return true;
    }

    private <UT, UB> void writeUnknownFieldsHelper(UnknownFieldSchema<UT, UB> unknownFieldSchema, T t, Writer writer) {
        unknownFieldSchema.writeAsMessageSetTo(unknownFieldSchema.getFromMessage(t), writer);
    }

    @Override // com.google.protobuf.Schema
    public boolean equals(T t, T t2) {
        if (!this.unknownFieldSchema.getFromMessage(t).equals(this.unknownFieldSchema.getFromMessage(t2))) {
            return false;
        }
        if (this.hasExtensions) {
            return this.extensionSchema.getExtensions(t).equals(this.extensionSchema.getExtensions(t2));
        }
        return true;
    }

    @Override // com.google.protobuf.Schema
    public int getSerializedSize(T t) {
        UnknownFieldSchema<?, ?> unknownFieldSchema = this.unknownFieldSchema;
        int serializedSizeAsMessageSet = unknownFieldSchema.getSerializedSizeAsMessageSet(unknownFieldSchema.getFromMessage(t)) + 0;
        return this.hasExtensions ? serializedSizeAsMessageSet + this.extensionSchema.getExtensions(t).getMessageSetSerializedSize() : serializedSizeAsMessageSet;
    }

    @Override // com.google.protobuf.Schema
    public int hashCode(T t) {
        int iHashCode = this.unknownFieldSchema.getFromMessage(t).hashCode();
        return this.hasExtensions ? (iHashCode * 53) + this.extensionSchema.getExtensions(t).hashCode() : iHashCode;
    }

    @Override // com.google.protobuf.Schema
    public final boolean isInitialized(T t) {
        return this.extensionSchema.getExtensions(t).isInitialized();
    }

    @Override // com.google.protobuf.Schema
    public void makeImmutable(T t) {
        this.unknownFieldSchema.makeImmutable(t);
        this.extensionSchema.makeImmutable(t);
    }

    @Override // com.google.protobuf.Schema
    public void mergeFrom(T t, T t2) {
        SchemaUtil.mergeUnknownFields(this.unknownFieldSchema, t, t2);
        if (this.hasExtensions) {
            SchemaUtil.mergeExtensions(this.extensionSchema, t, t2);
        }
    }

    @Override // com.google.protobuf.Schema
    public T newInstance() {
        return (T) this.defaultInstance.newBuilderForType().buildPartial();
    }

    @Override // com.google.protobuf.Schema
    public void writeTo(T t, Writer writer) {
        for (T t2 : this.extensionSchema.getExtensions(t)) {
            FieldSet.FieldDescriptorLite fieldDescriptorLite = (FieldSet.FieldDescriptorLite) t2.getKey();
            if (fieldDescriptorLite.getLiteJavaType() != WireFormat.JavaType.MESSAGE || fieldDescriptorLite.isRepeated() || fieldDescriptorLite.isPacked()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
            if (t2 instanceof LazyField.LazyEntry) {
                writer.writeMessageSetItem(fieldDescriptorLite.getNumber(), ((LazyField.LazyEntry) t2).getField().toByteString());
            } else {
                writer.writeMessageSetItem(fieldDescriptorLite.getNumber(), t2.getValue());
            }
        }
        UnknownFieldSchema<?, ?> unknownFieldSchema = this.unknownFieldSchema;
        unknownFieldSchema.writeAsMessageSetTo(unknownFieldSchema.getFromMessage(t), writer);
    }

    /* JADX WARN: Code duplicated, block: B:33:0x00b9  */
    /* JADX WARN: Code duplicated, block: B:58:0x00be A[EDGE_INSN: B:58:0x00be->B:34:0x00be BREAK  A[LOOP:1: B:18:0x0066->B:61:0x0066], SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.protobuf.Schema
    public void mergeFrom(T t, byte[] bArr, int i, int i2, ArrayDecoders.Registers registers) throws InvalidProtocolBufferException {
        GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite) t;
        UnknownFieldSetLite unknownFieldSetLite = generatedMessageLite.unknownFields;
        if (unknownFieldSetLite == UnknownFieldSetLite.DEFAULT_INSTANCE) {
            unknownFieldSetLite = new UnknownFieldSetLite();
            generatedMessageLite.unknownFields = unknownFieldSetLite;
        }
        FieldSet<GeneratedMessageLite.ExtensionDescriptor> fieldSetEnsureExtensionsAreMutable = ((GeneratedMessageLite.ExtendableMessage) t).ensureExtensionsAreMutable();
        GeneratedMessageLite.GeneratedExtension generatedExtension = null;
        while (i < i2) {
            int iDecodeVarint32 = ArrayDecoders.decodeVarint32(bArr, i, registers);
            int i3 = registers.int1;
            if (i3 == WireFormat.MESSAGE_SET_ITEM_TAG) {
                int i4 = 0;
                ByteString byteString = null;
                while (iDecodeVarint32 < i2) {
                    iDecodeVarint32 = ArrayDecoders.decodeVarint32(bArr, iDecodeVarint32, registers);
                    int i5 = registers.int1;
                    int i6 = i5 >>> 3;
                    int i7 = i5 & 7;
                    if (i6 != 2) {
                        if (i6 == 3) {
                            if (generatedExtension != null) {
                                iDecodeVarint32 = ArrayDecoders.decodeMessageField(Protobuf.INSTANCE.schemaFor((Class) generatedExtension.getMessageDefaultInstance().getClass()), bArr, iDecodeVarint32, i2, registers);
                                fieldSetEnsureExtensionsAreMutable.setField(generatedExtension.descriptor, registers.object1);
                            } else if (i7 == 2) {
                                iDecodeVarint32 = ArrayDecoders.decodeBytes(bArr, iDecodeVarint32, registers);
                                byteString = (ByteString) registers.object1;
                            }
                        }
                        if (i5 == WireFormat.MESSAGE_SET_ITEM_END_TAG) {
                            break;
                        } else {
                            iDecodeVarint32 = ArrayDecoders.skipField(i5, bArr, iDecodeVarint32, i2, registers);
                        }
                    } else if (i7 == 0) {
                        iDecodeVarint32 = ArrayDecoders.decodeVarint32(bArr, iDecodeVarint32, registers);
                        i4 = registers.int1;
                        generatedExtension = (GeneratedMessageLite.GeneratedExtension) this.extensionSchema.findExtensionByNumber(registers.extensionRegistry, this.defaultInstance, i4);
                    } else {
                        if (i5 == WireFormat.MESSAGE_SET_ITEM_END_TAG) {
                            break;
                            break;
                        }
                        iDecodeVarint32 = ArrayDecoders.skipField(i5, bArr, iDecodeVarint32, i2, registers);
                    }
                }
                if (byteString != null) {
                    unknownFieldSetLite.storeField((i4 << 3) | 2, byteString);
                }
                i = iDecodeVarint32;
            } else if ((i3 & 7) == 2) {
                GeneratedMessageLite.GeneratedExtension generatedExtension2 = (GeneratedMessageLite.GeneratedExtension) this.extensionSchema.findExtensionByNumber(registers.extensionRegistry, this.defaultInstance, i3 >>> 3);
                if (generatedExtension2 != null) {
                    i = ArrayDecoders.decodeMessageField(Protobuf.INSTANCE.schemaFor((Class) generatedExtension2.getMessageDefaultInstance().getClass()), bArr, iDecodeVarint32, i2, registers);
                    fieldSetEnsureExtensionsAreMutable.setField(generatedExtension2.descriptor, registers.object1);
                } else {
                    i = ArrayDecoders.decodeUnknownField(i3, bArr, iDecodeVarint32, i2, unknownFieldSetLite, registers);
                }
                generatedExtension = generatedExtension2;
            } else {
                i = ArrayDecoders.skipField(i3, bArr, iDecodeVarint32, i2, registers);
            }
        }
        if (i != i2) {
            throw InvalidProtocolBufferException.parseFailure();
        }
    }

    @Override // com.google.protobuf.Schema
    public void mergeFrom(T t, Reader reader, ExtensionRegistryLite extensionRegistryLite) {
        mergeFromHelper(this.unknownFieldSchema, this.extensionSchema, t, reader, extensionRegistryLite);
    }
}
