package com.google.firestore.v1;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MapEntryLite;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.Parser;
import com.google.protobuf.WireFormat;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class ListenRequest extends GeneratedMessageLite<ListenRequest, Builder> implements ListenRequestOrBuilder {
    public static final int ADD_TARGET_FIELD_NUMBER = 2;
    public static final int DATABASE_FIELD_NUMBER = 1;
    public static final ListenRequest DEFAULT_INSTANCE;
    public static final int LABELS_FIELD_NUMBER = 4;
    public static volatile Parser<ListenRequest> PARSER = null;
    public static final int REMOVE_TARGET_FIELD_NUMBER = 3;
    public Object targetChange_;
    public int targetChangeCase_ = 0;
    public MapFieldLite<String, String> labels_ = MapFieldLite.EMPTY_MAP_FIELD;
    public String database_ = "";

    /* JADX INFO: renamed from: com.google.firestore.v1.ListenRequest$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = new int[GeneratedMessageLite.MethodToInvoke.values().length];

        static {
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    private static final class LabelsDefaultEntryHolder {
        public static final MapEntryLite<String, String> defaultEntry;

        static {
            WireFormat.FieldType fieldType = WireFormat.FieldType.STRING;
            defaultEntry = new MapEntryLite<>(fieldType, "", fieldType, "");
        }
    }

    public enum TargetChangeCase {
        ADD_TARGET(2),
        REMOVE_TARGET(3),
        TARGETCHANGE_NOT_SET(0);

        public final int value;

        TargetChangeCase(int i) {
            this.value = i;
        }

        public static TargetChangeCase forNumber(int i) {
            if (i == 0) {
                return TARGETCHANGE_NOT_SET;
            }
            if (i == 2) {
                return ADD_TARGET;
            }
            if (i != 3) {
                return null;
            }
            return REMOVE_TARGET;
        }

        public int getNumber() {
            return this.value;
        }

        @Deprecated
        public static TargetChangeCase valueOf(int i) {
            return forNumber(i);
        }
    }

    static {
        ListenRequest listenRequest = new ListenRequest();
        DEFAULT_INSTANCE = listenRequest;
        GeneratedMessageLite.defaultInstanceMap.put(ListenRequest.class, listenRequest);
    }

    public static /* synthetic */ void access$800(ListenRequest listenRequest, int i) {
        listenRequest.targetChangeCase_ = 3;
        listenRequest.targetChange_ = Integer.valueOf(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearAddTarget() {
        if (this.targetChangeCase_ == 2) {
            this.targetChangeCase_ = 0;
            this.targetChange_ = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDatabase() {
        this.database_ = DEFAULT_INSTANCE.getDatabase();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearRemoveTarget() {
        if (this.targetChangeCase_ == 3) {
            this.targetChangeCase_ = 0;
            this.targetChange_ = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTargetChange() {
        this.targetChangeCase_ = 0;
        this.targetChange_ = null;
    }

    public static ListenRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private Map<String, String> getMutableLabelsMap() {
        return internalGetMutableLabels();
    }

    private MapFieldLite<String, String> internalGetLabels() {
        return this.labels_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MapFieldLite<String, String> internalGetMutableLabels() {
        if (!this.labels_.isMutable()) {
            this.labels_ = this.labels_.mutableCopy();
        }
        return this.labels_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeAddTarget(Target target) {
        Object obj;
        target.getClass();
        if (this.targetChangeCase_ != 2 || (obj = this.targetChange_) == Target.DEFAULT_INSTANCE) {
            this.targetChange_ = target;
        } else {
            this.targetChange_ = Target.newBuilder((Target) obj).mergeFrom(target).buildPartial();
        }
        this.targetChangeCase_ = 2;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static ListenRequest parseDelimitedFrom(InputStream inputStream) {
        return (ListenRequest) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ListenRequest parseFrom(ByteBuffer byteBuffer) {
        return (ListenRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<ListenRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAddTarget(Target target) {
        target.getClass();
        this.targetChange_ = target;
        this.targetChangeCase_ = 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDatabase(String str) {
        str.getClass();
        this.database_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDatabaseBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.database_ = byteString.toStringUtf8();
    }

    private void setRemoveTarget(int i) {
        this.targetChangeCase_ = 3;
        this.targetChange_ = Integer.valueOf(i);
    }

    @Override // com.google.firestore.v1.ListenRequestOrBuilder
    public boolean containsLabels(String str) {
        str.getClass();
        return this.labels_.containsKey(str);
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        AnonymousClass1 anonymousClass1 = null;
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0001\u0000\u0001\u0004\u0004\u0001\u0000\u0000\u0001Ȉ\u0002<\u0000\u00037\u0000\u00042", new Object[]{"targetChange_", "targetChangeCase_", "database_", Target.class, "labels_", LabelsDefaultEntryHolder.defaultEntry});
            case NEW_MUTABLE_INSTANCE:
                return new ListenRequest();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<ListenRequest> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (ListenRequest.class) {
                        defaultInstanceBasedParser = PARSER;
                        if (defaultInstanceBasedParser == null) {
                            defaultInstanceBasedParser = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                            PARSER = defaultInstanceBasedParser;
                        }
                        break;
                    }
                }
                return defaultInstanceBasedParser;
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override // com.google.firestore.v1.ListenRequestOrBuilder
    public Target getAddTarget() {
        return this.targetChangeCase_ == 2 ? (Target) this.targetChange_ : Target.DEFAULT_INSTANCE;
    }

    @Override // com.google.firestore.v1.ListenRequestOrBuilder
    public String getDatabase() {
        return this.database_;
    }

    @Override // com.google.firestore.v1.ListenRequestOrBuilder
    public ByteString getDatabaseBytes() {
        return ByteString.copyFromUtf8(this.database_);
    }

    @Override // com.google.firestore.v1.ListenRequestOrBuilder
    @Deprecated
    public Map<String, String> getLabels() {
        return getLabelsMap();
    }

    @Override // com.google.firestore.v1.ListenRequestOrBuilder
    public int getLabelsCount() {
        return this.labels_.size();
    }

    @Override // com.google.firestore.v1.ListenRequestOrBuilder
    public Map<String, String> getLabelsMap() {
        return Collections.unmodifiableMap(this.labels_);
    }

    @Override // com.google.firestore.v1.ListenRequestOrBuilder
    public String getLabelsOrDefault(String str, String str2) {
        str.getClass();
        MapFieldLite<String, String> mapFieldLite = this.labels_;
        return mapFieldLite.containsKey(str) ? mapFieldLite.get(str) : str2;
    }

    @Override // com.google.firestore.v1.ListenRequestOrBuilder
    public String getLabelsOrThrow(String str) {
        str.getClass();
        MapFieldLite<String, String> mapFieldLite = this.labels_;
        if (mapFieldLite.containsKey(str)) {
            return mapFieldLite.get(str);
        }
        throw new IllegalArgumentException();
    }

    @Override // com.google.firestore.v1.ListenRequestOrBuilder
    public int getRemoveTarget() {
        if (this.targetChangeCase_ == 3) {
            return ((Integer) this.targetChange_).intValue();
        }
        return 0;
    }

    @Override // com.google.firestore.v1.ListenRequestOrBuilder
    public TargetChangeCase getTargetChangeCase() {
        return TargetChangeCase.forNumber(this.targetChangeCase_);
    }

    @Override // com.google.firestore.v1.ListenRequestOrBuilder
    public boolean hasAddTarget() {
        return this.targetChangeCase_ == 2;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ListenRequest, Builder> implements ListenRequestOrBuilder {
        public Builder() {
            super(ListenRequest.DEFAULT_INSTANCE);
        }

        public Builder clearAddTarget() {
            copyOnWrite();
            ((ListenRequest) this.instance).clearAddTarget();
            return this;
        }

        public Builder clearDatabase() {
            copyOnWrite();
            ((ListenRequest) this.instance).clearDatabase();
            return this;
        }

        public Builder clearLabels() {
            copyOnWrite();
            ((ListenRequest) this.instance).internalGetMutableLabels().clear();
            return this;
        }

        public Builder clearRemoveTarget() {
            copyOnWrite();
            ((ListenRequest) this.instance).clearRemoveTarget();
            return this;
        }

        public Builder clearTargetChange() {
            copyOnWrite();
            ((ListenRequest) this.instance).clearTargetChange();
            return this;
        }

        @Override // com.google.firestore.v1.ListenRequestOrBuilder
        public boolean containsLabels(String str) {
            str.getClass();
            return ((ListenRequest) this.instance).getLabelsMap().containsKey(str);
        }

        @Override // com.google.firestore.v1.ListenRequestOrBuilder
        public Target getAddTarget() {
            return ((ListenRequest) this.instance).getAddTarget();
        }

        @Override // com.google.firestore.v1.ListenRequestOrBuilder
        public String getDatabase() {
            return ((ListenRequest) this.instance).getDatabase();
        }

        @Override // com.google.firestore.v1.ListenRequestOrBuilder
        public ByteString getDatabaseBytes() {
            return ((ListenRequest) this.instance).getDatabaseBytes();
        }

        @Override // com.google.firestore.v1.ListenRequestOrBuilder
        @Deprecated
        public Map<String, String> getLabels() {
            return getLabelsMap();
        }

        @Override // com.google.firestore.v1.ListenRequestOrBuilder
        public int getLabelsCount() {
            return ((ListenRequest) this.instance).getLabelsMap().size();
        }

        @Override // com.google.firestore.v1.ListenRequestOrBuilder
        public Map<String, String> getLabelsMap() {
            return Collections.unmodifiableMap(((ListenRequest) this.instance).getLabelsMap());
        }

        @Override // com.google.firestore.v1.ListenRequestOrBuilder
        public String getLabelsOrDefault(String str, String str2) {
            str.getClass();
            Map<String, String> labelsMap = ((ListenRequest) this.instance).getLabelsMap();
            return labelsMap.containsKey(str) ? labelsMap.get(str) : str2;
        }

        @Override // com.google.firestore.v1.ListenRequestOrBuilder
        public String getLabelsOrThrow(String str) {
            str.getClass();
            Map<String, String> labelsMap = ((ListenRequest) this.instance).getLabelsMap();
            if (labelsMap.containsKey(str)) {
                return labelsMap.get(str);
            }
            throw new IllegalArgumentException();
        }

        @Override // com.google.firestore.v1.ListenRequestOrBuilder
        public int getRemoveTarget() {
            return ((ListenRequest) this.instance).getRemoveTarget();
        }

        @Override // com.google.firestore.v1.ListenRequestOrBuilder
        public TargetChangeCase getTargetChangeCase() {
            return ((ListenRequest) this.instance).getTargetChangeCase();
        }

        @Override // com.google.firestore.v1.ListenRequestOrBuilder
        public boolean hasAddTarget() {
            return ((ListenRequest) this.instance).hasAddTarget();
        }

        public Builder mergeAddTarget(Target target) {
            copyOnWrite();
            ((ListenRequest) this.instance).mergeAddTarget(target);
            return this;
        }

        public Builder putAllLabels(Map<String, String> map) {
            copyOnWrite();
            ((ListenRequest) this.instance).internalGetMutableLabels().putAll(map);
            return this;
        }

        public Builder putLabels(String str, String str2) {
            str.getClass();
            str2.getClass();
            copyOnWrite();
            ((ListenRequest) this.instance).internalGetMutableLabels().put(str, str2);
            return this;
        }

        public Builder removeLabels(String str) {
            str.getClass();
            copyOnWrite();
            ((ListenRequest) this.instance).internalGetMutableLabels().remove(str);
            return this;
        }

        public Builder setAddTarget(Target target) {
            copyOnWrite();
            ((ListenRequest) this.instance).setAddTarget(target);
            return this;
        }

        public Builder setDatabase(String str) {
            copyOnWrite();
            ((ListenRequest) this.instance).setDatabase(str);
            return this;
        }

        public Builder setDatabaseBytes(ByteString byteString) {
            copyOnWrite();
            ((ListenRequest) this.instance).setDatabaseBytes(byteString);
            return this;
        }

        public Builder setRemoveTarget(int i) {
            copyOnWrite();
            ListenRequest.access$800((ListenRequest) this.instance, i);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(ListenRequest.DEFAULT_INSTANCE);
        }

        public Builder setAddTarget(Target.Builder builder) {
            copyOnWrite();
            ((ListenRequest) this.instance).setAddTarget(builder.build());
            return this;
        }
    }

    public static Builder newBuilder(ListenRequest listenRequest) {
        return DEFAULT_INSTANCE.createBuilder(listenRequest);
    }

    public static ListenRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (ListenRequest) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ListenRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (ListenRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static ListenRequest parseFrom(ByteString byteString) {
        return (ListenRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static ListenRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (ListenRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static ListenRequest parseFrom(byte[] bArr) {
        return (ListenRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static ListenRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (ListenRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static ListenRequest parseFrom(InputStream inputStream) {
        return (ListenRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ListenRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (ListenRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ListenRequest parseFrom(CodedInputStream codedInputStream) {
        return (ListenRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static ListenRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (ListenRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
