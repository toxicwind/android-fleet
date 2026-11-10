package com.google.protobuf;

import b.a.a.a.a;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class ExtensionSchemaLite extends ExtensionSchema<GeneratedMessageLite.ExtensionDescriptor> {

    /* JADX INFO: renamed from: com.google.protobuf.ExtensionSchemaLite$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$WireFormat$FieldType = new int[WireFormat.FieldType.values().length];

        static {
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.DOUBLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FLOAT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT64.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT64.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT32.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED64.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED32.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.BOOL.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT32.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED32.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED64.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT32.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT64.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.ENUM.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.BYTES.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.STRING.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.GROUP.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.MESSAGE.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
        }
    }

    @Override // com.google.protobuf.ExtensionSchema
    public int extensionNumber(Map.Entry<?, ?> entry) {
        return ((GeneratedMessageLite.ExtensionDescriptor) entry.getKey()).getNumber();
    }

    @Override // com.google.protobuf.ExtensionSchema
    public Object findExtensionByNumber(ExtensionRegistryLite extensionRegistryLite, MessageLite messageLite, int i) {
        return extensionRegistryLite.findLiteExtensionByNumber(messageLite, i);
    }

    @Override // com.google.protobuf.ExtensionSchema
    public FieldSet<GeneratedMessageLite.ExtensionDescriptor> getExtensions(Object obj) {
        return ((GeneratedMessageLite.ExtendableMessage) obj).extensions;
    }

    @Override // com.google.protobuf.ExtensionSchema
    public FieldSet<GeneratedMessageLite.ExtensionDescriptor> getMutableExtensions(Object obj) {
        return ((GeneratedMessageLite.ExtendableMessage) obj).ensureExtensionsAreMutable();
    }

    @Override // com.google.protobuf.ExtensionSchema
    public boolean hasExtensions(MessageLite messageLite) {
        return messageLite instanceof GeneratedMessageLite.ExtendableMessage;
    }

    @Override // com.google.protobuf.ExtensionSchema
    public void makeImmutable(Object obj) {
        getExtensions(obj).makeImmutable();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.google.protobuf.ExtensionSchema
    public <UT, UB> UB parseExtension(Reader reader, Object obj, ExtensionRegistryLite extensionRegistryLite, FieldSet<GeneratedMessageLite.ExtensionDescriptor> fieldSet, UB ub, UnknownFieldSchema<UT, UB> unknownFieldSchema) {
        Object field;
        ArrayList arrayList;
        GeneratedMessageLite.GeneratedExtension generatedExtension = (GeneratedMessageLite.GeneratedExtension) obj;
        int number = generatedExtension.getNumber();
        if (generatedExtension.descriptor.isRepeated() && generatedExtension.descriptor.isPacked()) {
            switch (generatedExtension.getLiteType().ordinal()) {
                case 0:
                    arrayList = new ArrayList();
                    reader.readDoubleList(arrayList);
                    break;
                case 1:
                    arrayList = new ArrayList();
                    reader.readFloatList(arrayList);
                    break;
                case 2:
                    arrayList = new ArrayList();
                    reader.readInt64List(arrayList);
                    break;
                case 3:
                    arrayList = new ArrayList();
                    reader.readUInt64List(arrayList);
                    break;
                case 4:
                    arrayList = new ArrayList();
                    reader.readInt32List(arrayList);
                    break;
                case 5:
                    arrayList = new ArrayList();
                    reader.readFixed64List(arrayList);
                    break;
                case 6:
                    arrayList = new ArrayList();
                    reader.readFixed32List(arrayList);
                    break;
                case 7:
                    arrayList = new ArrayList();
                    reader.readBoolList(arrayList);
                    break;
                case 8:
                case 9:
                case 10:
                case 11:
                default:
                    StringBuilder sbA = a.a("Type cannot be packed: ");
                    sbA.append(generatedExtension.descriptor.getLiteType());
                    throw new IllegalStateException(sbA.toString());
                case 12:
                    arrayList = new ArrayList();
                    reader.readUInt32List(arrayList);
                    break;
                case 13:
                    arrayList = new ArrayList();
                    reader.readEnumList(arrayList);
                    ub = (UB) SchemaUtil.filterUnknownEnumList(number, arrayList, generatedExtension.descriptor.getEnumType(), ub, unknownFieldSchema);
                    break;
                case 14:
                    arrayList = new ArrayList();
                    reader.readSFixed32List(arrayList);
                    break;
                case 15:
                    arrayList = new ArrayList();
                    reader.readSFixed64List(arrayList);
                    break;
                case 16:
                    arrayList = new ArrayList();
                    reader.readSInt32List(arrayList);
                    break;
                case 17:
                    arrayList = new ArrayList();
                    reader.readSInt64List(arrayList);
                    break;
            }
            fieldSet.setField(generatedExtension.descriptor, arrayList);
        } else {
            Object objValueOf = null;
            if (generatedExtension.getLiteType() != WireFormat.FieldType.ENUM) {
                switch (generatedExtension.getLiteType().ordinal()) {
                    case 0:
                        objValueOf = Double.valueOf(reader.readDouble());
                        break;
                    case 1:
                        objValueOf = Float.valueOf(reader.readFloat());
                        break;
                    case 2:
                        objValueOf = Long.valueOf(reader.readInt64());
                        break;
                    case 3:
                        objValueOf = Long.valueOf(reader.readUInt64());
                        break;
                    case 4:
                        objValueOf = Integer.valueOf(reader.readInt32());
                        break;
                    case 5:
                        objValueOf = Long.valueOf(reader.readFixed64());
                        break;
                    case 6:
                        objValueOf = Integer.valueOf(reader.readFixed32());
                        break;
                    case 7:
                        objValueOf = Boolean.valueOf(reader.readBool());
                        break;
                    case 8:
                        objValueOf = reader.readString();
                        break;
                    case 9:
                        objValueOf = reader.readGroup(generatedExtension.getMessageDefaultInstance().getClass(), extensionRegistryLite);
                        break;
                    case 10:
                        objValueOf = reader.readMessage(generatedExtension.getMessageDefaultInstance().getClass(), extensionRegistryLite);
                        break;
                    case 11:
                        objValueOf = reader.readBytes();
                        break;
                    case 12:
                        objValueOf = Integer.valueOf(reader.readUInt32());
                        break;
                    case 13:
                        throw new IllegalStateException("Shouldn't reach here.");
                    case 14:
                        objValueOf = Integer.valueOf(reader.readSFixed32());
                        break;
                    case 15:
                        objValueOf = Long.valueOf(reader.readSFixed64());
                        break;
                    case 16:
                        objValueOf = Integer.valueOf(reader.readSInt32());
                        break;
                    case 17:
                        objValueOf = Long.valueOf(reader.readSInt64());
                        break;
                }
            } else {
                int int32 = reader.readInt32();
                if (generatedExtension.descriptor.getEnumType().findValueByNumber(int32) == null) {
                    return (UB) SchemaUtil.storeUnknownEnum(number, int32, ub, unknownFieldSchema);
                }
                objValueOf = Integer.valueOf(int32);
            }
            if (generatedExtension.isRepeated()) {
                fieldSet.addRepeatedField(generatedExtension.descriptor, objValueOf);
            } else {
                int iOrdinal = generatedExtension.getLiteType().ordinal();
                if ((iOrdinal == 9 || iOrdinal == 10) && (field = fieldSet.getField(generatedExtension.descriptor)) != null) {
                    objValueOf = Internal.mergeMessage(field, objValueOf);
                }
                fieldSet.setField(generatedExtension.descriptor, objValueOf);
            }
        }
        return ub;
    }

    @Override // com.google.protobuf.ExtensionSchema
    public void parseLengthPrefixedMessageSetItem(Reader reader, Object obj, ExtensionRegistryLite extensionRegistryLite, FieldSet<GeneratedMessageLite.ExtensionDescriptor> fieldSet) {
        GeneratedMessageLite.GeneratedExtension generatedExtension = (GeneratedMessageLite.GeneratedExtension) obj;
        fieldSet.setField(generatedExtension.descriptor, reader.readMessage(generatedExtension.getMessageDefaultInstance().getClass(), extensionRegistryLite));
    }

    @Override // com.google.protobuf.ExtensionSchema
    public void parseMessageSetItem(ByteString byteString, Object obj, ExtensionRegistryLite extensionRegistryLite, FieldSet<GeneratedMessageLite.ExtensionDescriptor> fieldSet) throws InvalidProtocolBufferException {
        GeneratedMessageLite.GeneratedExtension generatedExtension = (GeneratedMessageLite.GeneratedExtension) obj;
        MessageLite messageLiteBuildPartial = generatedExtension.getMessageDefaultInstance().newBuilderForType().buildPartial();
        BinaryReader binaryReaderNewInstance = BinaryReader.newInstance(ByteBuffer.wrap(byteString.toByteArray()), true);
        Protobuf.INSTANCE.mergeFrom(messageLiteBuildPartial, binaryReaderNewInstance, extensionRegistryLite);
        fieldSet.setField(generatedExtension.descriptor, messageLiteBuildPartial);
        if (binaryReaderNewInstance.getFieldNumber() != Integer.MAX_VALUE) {
            throw InvalidProtocolBufferException.invalidEndTag();
        }
    }

    @Override // com.google.protobuf.ExtensionSchema
    public void serializeExtension(Writer writer, Map.Entry<?, ?> entry) {
        GeneratedMessageLite.ExtensionDescriptor extensionDescriptor = (GeneratedMessageLite.ExtensionDescriptor) entry.getKey();
        if (!extensionDescriptor.isRepeated()) {
            switch (extensionDescriptor.getLiteType().ordinal()) {
                case 0:
                    writer.writeDouble(extensionDescriptor.getNumber(), ((Double) entry.getValue()).doubleValue());
                    break;
                case 1:
                    writer.writeFloat(extensionDescriptor.getNumber(), ((Float) entry.getValue()).floatValue());
                    break;
                case 2:
                    writer.writeInt64(extensionDescriptor.getNumber(), ((Long) entry.getValue()).longValue());
                    break;
                case 3:
                    writer.writeUInt64(extensionDescriptor.getNumber(), ((Long) entry.getValue()).longValue());
                    break;
                case 4:
                    writer.writeInt32(extensionDescriptor.getNumber(), ((Integer) entry.getValue()).intValue());
                    break;
                case 5:
                    writer.writeFixed64(extensionDescriptor.getNumber(), ((Long) entry.getValue()).longValue());
                    break;
                case 6:
                    writer.writeFixed32(extensionDescriptor.getNumber(), ((Integer) entry.getValue()).intValue());
                    break;
                case 7:
                    writer.writeBool(extensionDescriptor.getNumber(), ((Boolean) entry.getValue()).booleanValue());
                    break;
                case 8:
                    writer.writeString(extensionDescriptor.getNumber(), (String) entry.getValue());
                    break;
                case 9:
                    writer.writeGroup(extensionDescriptor.getNumber(), entry.getValue(), Protobuf.INSTANCE.schemaFor((Class) entry.getValue().getClass()));
                    break;
                case 10:
                    writer.writeMessage(extensionDescriptor.getNumber(), entry.getValue(), Protobuf.INSTANCE.schemaFor((Class) entry.getValue().getClass()));
                    break;
                case 11:
                    writer.writeBytes(extensionDescriptor.getNumber(), (ByteString) entry.getValue());
                    break;
                case 12:
                    writer.writeUInt32(extensionDescriptor.getNumber(), ((Integer) entry.getValue()).intValue());
                    break;
                case 13:
                    writer.writeInt32(extensionDescriptor.getNumber(), ((Integer) entry.getValue()).intValue());
                    break;
                case 14:
                    writer.writeSFixed32(extensionDescriptor.getNumber(), ((Integer) entry.getValue()).intValue());
                    break;
                case 15:
                    writer.writeSFixed64(extensionDescriptor.getNumber(), ((Long) entry.getValue()).longValue());
                    break;
                case 16:
                    writer.writeSInt32(extensionDescriptor.getNumber(), ((Integer) entry.getValue()).intValue());
                    break;
                case 17:
                    writer.writeSInt64(extensionDescriptor.getNumber(), ((Long) entry.getValue()).longValue());
                    break;
            }
        }
        switch (extensionDescriptor.getLiteType().ordinal()) {
            case 0:
                SchemaUtil.writeDoubleList(extensionDescriptor.getNumber(), (List) entry.getValue(), writer, extensionDescriptor.isPacked());
                break;
            case 1:
                SchemaUtil.writeFloatList(extensionDescriptor.getNumber(), (List) entry.getValue(), writer, extensionDescriptor.isPacked());
                break;
            case 2:
                SchemaUtil.writeInt64List(extensionDescriptor.getNumber(), (List) entry.getValue(), writer, extensionDescriptor.isPacked());
                break;
            case 3:
                SchemaUtil.writeUInt64List(extensionDescriptor.getNumber(), (List) entry.getValue(), writer, extensionDescriptor.isPacked());
                break;
            case 4:
                SchemaUtil.writeInt32List(extensionDescriptor.getNumber(), (List) entry.getValue(), writer, extensionDescriptor.isPacked());
                break;
            case 5:
                SchemaUtil.writeFixed64List(extensionDescriptor.getNumber(), (List) entry.getValue(), writer, extensionDescriptor.isPacked());
                break;
            case 6:
                SchemaUtil.writeFixed32List(extensionDescriptor.getNumber(), (List) entry.getValue(), writer, extensionDescriptor.isPacked());
                break;
            case 7:
                SchemaUtil.writeBoolList(extensionDescriptor.getNumber(), (List) entry.getValue(), writer, extensionDescriptor.isPacked());
                break;
            case 8:
                SchemaUtil.writeStringList(extensionDescriptor.getNumber(), (List) entry.getValue(), writer);
                break;
            case 9:
                List list = (List) entry.getValue();
                if (list != null && !list.isEmpty()) {
                    SchemaUtil.writeGroupList(extensionDescriptor.getNumber(), (List) entry.getValue(), writer, Protobuf.INSTANCE.schemaFor((Class) list.get(0).getClass()));
                    break;
                }
                break;
            case 10:
                List list2 = (List) entry.getValue();
                if (list2 != null && !list2.isEmpty()) {
                    SchemaUtil.writeMessageList(extensionDescriptor.getNumber(), (List) entry.getValue(), writer, Protobuf.INSTANCE.schemaFor((Class) list2.get(0).getClass()));
                    break;
                }
                break;
            case 11:
                SchemaUtil.writeBytesList(extensionDescriptor.getNumber(), (List) entry.getValue(), writer);
                break;
            case 12:
                SchemaUtil.writeUInt32List(extensionDescriptor.getNumber(), (List) entry.getValue(), writer, extensionDescriptor.isPacked());
                break;
            case 13:
                SchemaUtil.writeInt32List(extensionDescriptor.getNumber(), (List) entry.getValue(), writer, extensionDescriptor.isPacked());
                break;
            case 14:
                SchemaUtil.writeSFixed32List(extensionDescriptor.getNumber(), (List) entry.getValue(), writer, extensionDescriptor.isPacked());
                break;
            case 15:
                SchemaUtil.writeSFixed64List(extensionDescriptor.getNumber(), (List) entry.getValue(), writer, extensionDescriptor.isPacked());
                break;
            case 16:
                SchemaUtil.writeSInt32List(extensionDescriptor.getNumber(), (List) entry.getValue(), writer, extensionDescriptor.isPacked());
                break;
            case 17:
                SchemaUtil.writeSInt64List(extensionDescriptor.getNumber(), (List) entry.getValue(), writer, extensionDescriptor.isPacked());
                break;
        }
    }

    @Override // com.google.protobuf.ExtensionSchema
    public void setExtensions(Object obj, FieldSet<GeneratedMessageLite.ExtensionDescriptor> fieldSet) {
        ((GeneratedMessageLite.ExtendableMessage) obj).extensions = fieldSet;
    }
}
