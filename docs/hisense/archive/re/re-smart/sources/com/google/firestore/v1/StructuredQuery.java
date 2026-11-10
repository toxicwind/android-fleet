package com.google.firestore.v1;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Int32Value;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class StructuredQuery extends GeneratedMessageLite<StructuredQuery, Builder> implements StructuredQueryOrBuilder {
    public static final StructuredQuery DEFAULT_INSTANCE;
    public static final int END_AT_FIELD_NUMBER = 8;
    public static final int FROM_FIELD_NUMBER = 2;
    public static final int LIMIT_FIELD_NUMBER = 5;
    public static final int OFFSET_FIELD_NUMBER = 6;
    public static final int ORDER_BY_FIELD_NUMBER = 4;
    public static volatile Parser<StructuredQuery> PARSER = null;
    public static final int SELECT_FIELD_NUMBER = 1;
    public static final int START_AT_FIELD_NUMBER = 7;
    public static final int WHERE_FIELD_NUMBER = 3;
    public Cursor endAt_;
    public Int32Value limit_;
    public int offset_;
    public Projection select_;
    public Cursor startAt_;
    public Filter where_;
    public Internal.ProtobufList<CollectionSelector> from_ = GeneratedMessageLite.emptyProtobufList();
    public Internal.ProtobufList<Order> orderBy_ = GeneratedMessageLite.emptyProtobufList();

    /* JADX INFO: renamed from: com.google.firestore.v1.StructuredQuery$1, reason: invalid class name */
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

    public static final class CollectionSelector extends GeneratedMessageLite<CollectionSelector, Builder> implements CollectionSelectorOrBuilder {
        public static final int ALL_DESCENDANTS_FIELD_NUMBER = 3;
        public static final int COLLECTION_ID_FIELD_NUMBER = 2;
        public static final CollectionSelector DEFAULT_INSTANCE;
        public static volatile Parser<CollectionSelector> PARSER;
        public boolean allDescendants_;
        public String collectionId_ = "";

        static {
            CollectionSelector collectionSelector = new CollectionSelector();
            DEFAULT_INSTANCE = collectionSelector;
            GeneratedMessageLite.defaultInstanceMap.put(CollectionSelector.class, collectionSelector);
        }

        private void clearAllDescendants() {
            this.allDescendants_ = false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCollectionId() {
            this.collectionId_ = DEFAULT_INSTANCE.getCollectionId();
        }

        public static CollectionSelector getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static CollectionSelector parseDelimitedFrom(InputStream inputStream) {
            return (CollectionSelector) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CollectionSelector parseFrom(ByteBuffer byteBuffer) {
            return (CollectionSelector) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<CollectionSelector> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        private void setAllDescendants(boolean z) {
            this.allDescendants_ = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCollectionId(String str) {
            str.getClass();
            this.collectionId_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCollectionIdBytes(ByteString byteString) {
            AbstractMessageLite.checkByteStringIsUtf8(byteString);
            this.collectionId_ = byteString.toStringUtf8();
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
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0002\u0003\u0002\u0000\u0000\u0000\u0002Ȉ\u0003\u0007", new Object[]{"collectionId_", "allDescendants_"});
                case NEW_MUTABLE_INSTANCE:
                    return new CollectionSelector();
                case NEW_BUILDER:
                    return new Builder(anonymousClass1);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser<CollectionSelector> defaultInstanceBasedParser = PARSER;
                    if (defaultInstanceBasedParser == null) {
                        synchronized (CollectionSelector.class) {
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

        @Override // com.google.firestore.v1.StructuredQuery.CollectionSelectorOrBuilder
        public boolean getAllDescendants() {
            return this.allDescendants_;
        }

        @Override // com.google.firestore.v1.StructuredQuery.CollectionSelectorOrBuilder
        public String getCollectionId() {
            return this.collectionId_;
        }

        @Override // com.google.firestore.v1.StructuredQuery.CollectionSelectorOrBuilder
        public ByteString getCollectionIdBytes() {
            return ByteString.copyFromUtf8(this.collectionId_);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<CollectionSelector, Builder> implements CollectionSelectorOrBuilder {
            public Builder() {
                super(CollectionSelector.DEFAULT_INSTANCE);
            }

            public Builder clearAllDescendants() {
                copyOnWrite();
                ((CollectionSelector) this.instance).allDescendants_ = false;
                return this;
            }

            public Builder clearCollectionId() {
                copyOnWrite();
                ((CollectionSelector) this.instance).clearCollectionId();
                return this;
            }

            @Override // com.google.firestore.v1.StructuredQuery.CollectionSelectorOrBuilder
            public boolean getAllDescendants() {
                return ((CollectionSelector) this.instance).getAllDescendants();
            }

            @Override // com.google.firestore.v1.StructuredQuery.CollectionSelectorOrBuilder
            public String getCollectionId() {
                return ((CollectionSelector) this.instance).getCollectionId();
            }

            @Override // com.google.firestore.v1.StructuredQuery.CollectionSelectorOrBuilder
            public ByteString getCollectionIdBytes() {
                return ((CollectionSelector) this.instance).getCollectionIdBytes();
            }

            public Builder setAllDescendants(boolean z) {
                copyOnWrite();
                ((CollectionSelector) this.instance).allDescendants_ = z;
                return this;
            }

            public Builder setCollectionId(String str) {
                copyOnWrite();
                ((CollectionSelector) this.instance).setCollectionId(str);
                return this;
            }

            public Builder setCollectionIdBytes(ByteString byteString) {
                copyOnWrite();
                ((CollectionSelector) this.instance).setCollectionIdBytes(byteString);
                return this;
            }

            public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                super(CollectionSelector.DEFAULT_INSTANCE);
            }
        }

        public static Builder newBuilder(CollectionSelector collectionSelector) {
            return DEFAULT_INSTANCE.createBuilder(collectionSelector);
        }

        public static CollectionSelector parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (CollectionSelector) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static CollectionSelector parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
            return (CollectionSelector) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static CollectionSelector parseFrom(ByteString byteString) {
            return (CollectionSelector) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static CollectionSelector parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (CollectionSelector) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static CollectionSelector parseFrom(byte[] bArr) {
            return (CollectionSelector) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static CollectionSelector parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (CollectionSelector) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static CollectionSelector parseFrom(InputStream inputStream) {
            return (CollectionSelector) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CollectionSelector parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (CollectionSelector) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static CollectionSelector parseFrom(CodedInputStream codedInputStream) {
            return (CollectionSelector) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static CollectionSelector parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (CollectionSelector) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    public interface CollectionSelectorOrBuilder extends MessageLiteOrBuilder {
        boolean getAllDescendants();

        String getCollectionId();

        ByteString getCollectionIdBytes();
    }

    public static final class CompositeFilter extends GeneratedMessageLite<CompositeFilter, Builder> implements CompositeFilterOrBuilder {
        public static final CompositeFilter DEFAULT_INSTANCE;
        public static final int FILTERS_FIELD_NUMBER = 2;
        public static final int OP_FIELD_NUMBER = 1;
        public static volatile Parser<CompositeFilter> PARSER;
        public Internal.ProtobufList<Filter> filters_ = GeneratedMessageLite.emptyProtobufList();
        public int op_;

        public enum Operator implements Internal.EnumLite {
            OPERATOR_UNSPECIFIED(0),
            AND(1),
            UNRECOGNIZED(-1);

            public static final int AND_VALUE = 1;
            public static final int OPERATOR_UNSPECIFIED_VALUE = 0;
            public static final Internal.EnumLiteMap<Operator> internalValueMap = new Internal.EnumLiteMap<Operator>() { // from class: com.google.firestore.v1.StructuredQuery.CompositeFilter.Operator.1
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public Operator findValueByNumber(int i) {
                    return Operator.forNumber(i);
                }
            };
            public final int value;

            private static final class OperatorVerifier implements Internal.EnumVerifier {
                public static final Internal.EnumVerifier INSTANCE = new OperatorVerifier();

                @Override // com.google.protobuf.Internal.EnumVerifier
                public boolean isInRange(int i) {
                    return Operator.forNumber(i) != null;
                }
            }

            Operator(int i) {
                this.value = i;
            }

            public static Operator forNumber(int i) {
                if (i == 0) {
                    return OPERATOR_UNSPECIFIED;
                }
                if (i != 1) {
                    return null;
                }
                return AND;
            }

            public static Internal.EnumLiteMap<Operator> internalGetValueMap() {
                return internalValueMap;
            }

            public static Internal.EnumVerifier internalGetVerifier() {
                return OperatorVerifier.INSTANCE;
            }

            @Override // com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                if (this != UNRECOGNIZED) {
                    return this.value;
                }
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }

            @Deprecated
            public static Operator valueOf(int i) {
                return forNumber(i);
            }
        }

        static {
            CompositeFilter compositeFilter = new CompositeFilter();
            DEFAULT_INSTANCE = compositeFilter;
            GeneratedMessageLite.defaultInstanceMap.put(CompositeFilter.class, compositeFilter);
        }

        public static /* synthetic */ void access$2600(CompositeFilter compositeFilter, Iterable iterable) {
            compositeFilter.ensureFiltersIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, (List) compositeFilter.filters_);
        }

        public static /* synthetic */ void access$2800(CompositeFilter compositeFilter, int i) {
            compositeFilter.ensureFiltersIsMutable();
            compositeFilter.filters_.remove(i);
        }

        private void addAllFilters(Iterable<? extends Filter> iterable) {
            ensureFiltersIsMutable();
            AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.filters_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addFilters(Filter filter) {
            filter.getClass();
            ensureFiltersIsMutable();
            this.filters_.add(filter);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearFilters() {
            this.filters_ = GeneratedMessageLite.emptyProtobufList();
        }

        private void clearOp() {
            this.op_ = 0;
        }

        private void ensureFiltersIsMutable() {
            Internal.ProtobufList<Filter> protobufList = this.filters_;
            if (protobufList.isModifiable()) {
                return;
            }
            this.filters_ = GeneratedMessageLite.mutableCopy(protobufList);
        }

        public static CompositeFilter getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static CompositeFilter parseDelimitedFrom(InputStream inputStream) {
            return (CompositeFilter) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CompositeFilter parseFrom(ByteBuffer byteBuffer) {
            return (CompositeFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<CompositeFilter> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        private void removeFilters(int i) {
            ensureFiltersIsMutable();
            this.filters_.remove(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFilters(int i, Filter filter) {
            filter.getClass();
            ensureFiltersIsMutable();
            this.filters_.set(i, filter);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setOp(Operator operator) {
            this.op_ = operator.getNumber();
        }

        private void setOpValue(int i) {
            this.op_ = i;
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
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\f\u0002\u001b", new Object[]{"op_", "filters_", Filter.class});
                case NEW_MUTABLE_INSTANCE:
                    return new CompositeFilter();
                case NEW_BUILDER:
                    return new Builder(anonymousClass1);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser<CompositeFilter> defaultInstanceBasedParser = PARSER;
                    if (defaultInstanceBasedParser == null) {
                        synchronized (CompositeFilter.class) {
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

        @Override // com.google.firestore.v1.StructuredQuery.CompositeFilterOrBuilder
        public Filter getFilters(int i) {
            return this.filters_.get(i);
        }

        @Override // com.google.firestore.v1.StructuredQuery.CompositeFilterOrBuilder
        public int getFiltersCount() {
            return this.filters_.size();
        }

        @Override // com.google.firestore.v1.StructuredQuery.CompositeFilterOrBuilder
        public List<Filter> getFiltersList() {
            return this.filters_;
        }

        public FilterOrBuilder getFiltersOrBuilder(int i) {
            return this.filters_.get(i);
        }

        public List<? extends FilterOrBuilder> getFiltersOrBuilderList() {
            return this.filters_;
        }

        @Override // com.google.firestore.v1.StructuredQuery.CompositeFilterOrBuilder
        public Operator getOp() {
            Operator operatorForNumber = Operator.forNumber(this.op_);
            return operatorForNumber == null ? Operator.UNRECOGNIZED : operatorForNumber;
        }

        @Override // com.google.firestore.v1.StructuredQuery.CompositeFilterOrBuilder
        public int getOpValue() {
            return this.op_;
        }

        public static final class Builder extends GeneratedMessageLite.Builder<CompositeFilter, Builder> implements CompositeFilterOrBuilder {
            public Builder() {
                super(CompositeFilter.DEFAULT_INSTANCE);
            }

            public Builder addAllFilters(Iterable<? extends Filter> iterable) {
                copyOnWrite();
                CompositeFilter.access$2600((CompositeFilter) this.instance, iterable);
                return this;
            }

            public Builder addFilters(Filter filter) {
                copyOnWrite();
                ((CompositeFilter) this.instance).addFilters(filter);
                return this;
            }

            public Builder clearFilters() {
                copyOnWrite();
                ((CompositeFilter) this.instance).clearFilters();
                return this;
            }

            public Builder clearOp() {
                copyOnWrite();
                ((CompositeFilter) this.instance).op_ = 0;
                return this;
            }

            @Override // com.google.firestore.v1.StructuredQuery.CompositeFilterOrBuilder
            public Filter getFilters(int i) {
                return ((CompositeFilter) this.instance).getFilters(i);
            }

            @Override // com.google.firestore.v1.StructuredQuery.CompositeFilterOrBuilder
            public int getFiltersCount() {
                return ((CompositeFilter) this.instance).getFiltersCount();
            }

            @Override // com.google.firestore.v1.StructuredQuery.CompositeFilterOrBuilder
            public List<Filter> getFiltersList() {
                return Collections.unmodifiableList(((CompositeFilter) this.instance).getFiltersList());
            }

            @Override // com.google.firestore.v1.StructuredQuery.CompositeFilterOrBuilder
            public Operator getOp() {
                return ((CompositeFilter) this.instance).getOp();
            }

            @Override // com.google.firestore.v1.StructuredQuery.CompositeFilterOrBuilder
            public int getOpValue() {
                return ((CompositeFilter) this.instance).getOpValue();
            }

            public Builder removeFilters(int i) {
                copyOnWrite();
                CompositeFilter.access$2800((CompositeFilter) this.instance, i);
                return this;
            }

            public Builder setFilters(int i, Filter filter) {
                copyOnWrite();
                ((CompositeFilter) this.instance).setFilters(i, filter);
                return this;
            }

            public Builder setOp(Operator operator) {
                copyOnWrite();
                ((CompositeFilter) this.instance).setOp(operator);
                return this;
            }

            public Builder setOpValue(int i) {
                copyOnWrite();
                ((CompositeFilter) this.instance).op_ = i;
                return this;
            }

            public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                super(CompositeFilter.DEFAULT_INSTANCE);
            }

            public Builder addFilters(int i, Filter filter) {
                copyOnWrite();
                ((CompositeFilter) this.instance).addFilters(i, filter);
                return this;
            }

            public Builder setFilters(int i, Filter.Builder builder) {
                copyOnWrite();
                ((CompositeFilter) this.instance).setFilters(i, builder.build());
                return this;
            }

            public Builder addFilters(Filter.Builder builder) {
                copyOnWrite();
                ((CompositeFilter) this.instance).addFilters(builder.build());
                return this;
            }

            public Builder addFilters(int i, Filter.Builder builder) {
                copyOnWrite();
                ((CompositeFilter) this.instance).addFilters(i, builder.build());
                return this;
            }
        }

        public static Builder newBuilder(CompositeFilter compositeFilter) {
            return DEFAULT_INSTANCE.createBuilder(compositeFilter);
        }

        public static CompositeFilter parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (CompositeFilter) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static CompositeFilter parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
            return (CompositeFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static CompositeFilter parseFrom(ByteString byteString) {
            return (CompositeFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addFilters(int i, Filter filter) {
            filter.getClass();
            ensureFiltersIsMutable();
            this.filters_.add(i, filter);
        }

        public static CompositeFilter parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (CompositeFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static CompositeFilter parseFrom(byte[] bArr) {
            return (CompositeFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static CompositeFilter parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (CompositeFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static CompositeFilter parseFrom(InputStream inputStream) {
            return (CompositeFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CompositeFilter parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (CompositeFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static CompositeFilter parseFrom(CodedInputStream codedInputStream) {
            return (CompositeFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static CompositeFilter parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (CompositeFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    public interface CompositeFilterOrBuilder extends MessageLiteOrBuilder {
        Filter getFilters(int i);

        int getFiltersCount();

        List<Filter> getFiltersList();

        CompositeFilter.Operator getOp();

        int getOpValue();
    }

    public enum Direction implements Internal.EnumLite {
        DIRECTION_UNSPECIFIED(0),
        ASCENDING(1),
        DESCENDING(2),
        UNRECOGNIZED(-1);

        public static final int ASCENDING_VALUE = 1;
        public static final int DESCENDING_VALUE = 2;
        public static final int DIRECTION_UNSPECIFIED_VALUE = 0;
        public static final Internal.EnumLiteMap<Direction> internalValueMap = new Internal.EnumLiteMap<Direction>() { // from class: com.google.firestore.v1.StructuredQuery.Direction.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public Direction findValueByNumber(int i) {
                return Direction.forNumber(i);
            }
        };
        public final int value;

        private static final class DirectionVerifier implements Internal.EnumVerifier {
            public static final Internal.EnumVerifier INSTANCE = new DirectionVerifier();

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int i) {
                return Direction.forNumber(i) != null;
            }
        }

        Direction(int i) {
            this.value = i;
        }

        public static Direction forNumber(int i) {
            if (i == 0) {
                return DIRECTION_UNSPECIFIED;
            }
            if (i == 1) {
                return ASCENDING;
            }
            if (i != 2) {
                return null;
            }
            return DESCENDING;
        }

        public static Internal.EnumLiteMap<Direction> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return DirectionVerifier.INSTANCE;
        }

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }

        @Deprecated
        public static Direction valueOf(int i) {
            return forNumber(i);
        }
    }

    public static final class FieldFilter extends GeneratedMessageLite<FieldFilter, Builder> implements FieldFilterOrBuilder {
        public static final FieldFilter DEFAULT_INSTANCE;
        public static final int FIELD_FIELD_NUMBER = 1;
        public static final int OP_FIELD_NUMBER = 2;
        public static volatile Parser<FieldFilter> PARSER = null;
        public static final int VALUE_FIELD_NUMBER = 3;
        public FieldReference field_;
        public int op_;
        public Value value_;

        public enum Operator implements Internal.EnumLite {
            OPERATOR_UNSPECIFIED(0),
            LESS_THAN(1),
            LESS_THAN_OR_EQUAL(2),
            GREATER_THAN(3),
            GREATER_THAN_OR_EQUAL(4),
            EQUAL(5),
            NOT_EQUAL(6),
            ARRAY_CONTAINS(7),
            IN(8),
            ARRAY_CONTAINS_ANY(9),
            NOT_IN(10),
            UNRECOGNIZED(-1);

            public static final int ARRAY_CONTAINS_ANY_VALUE = 9;
            public static final int ARRAY_CONTAINS_VALUE = 7;
            public static final int EQUAL_VALUE = 5;
            public static final int GREATER_THAN_OR_EQUAL_VALUE = 4;
            public static final int GREATER_THAN_VALUE = 3;
            public static final int IN_VALUE = 8;
            public static final int LESS_THAN_OR_EQUAL_VALUE = 2;
            public static final int LESS_THAN_VALUE = 1;
            public static final int NOT_EQUAL_VALUE = 6;
            public static final int NOT_IN_VALUE = 10;
            public static final int OPERATOR_UNSPECIFIED_VALUE = 0;
            public static final Internal.EnumLiteMap<Operator> internalValueMap = new Internal.EnumLiteMap<Operator>() { // from class: com.google.firestore.v1.StructuredQuery.FieldFilter.Operator.1
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public Operator findValueByNumber(int i) {
                    return Operator.forNumber(i);
                }
            };
            public final int value;

            private static final class OperatorVerifier implements Internal.EnumVerifier {
                public static final Internal.EnumVerifier INSTANCE = new OperatorVerifier();

                @Override // com.google.protobuf.Internal.EnumVerifier
                public boolean isInRange(int i) {
                    return Operator.forNumber(i) != null;
                }
            }

            Operator(int i) {
                this.value = i;
            }

            public static Operator forNumber(int i) {
                switch (i) {
                    case 0:
                        return OPERATOR_UNSPECIFIED;
                    case 1:
                        return LESS_THAN;
                    case 2:
                        return LESS_THAN_OR_EQUAL;
                    case 3:
                        return GREATER_THAN;
                    case 4:
                        return GREATER_THAN_OR_EQUAL;
                    case 5:
                        return EQUAL;
                    case 6:
                        return NOT_EQUAL;
                    case 7:
                        return ARRAY_CONTAINS;
                    case 8:
                        return IN;
                    case 9:
                        return ARRAY_CONTAINS_ANY;
                    case 10:
                        return NOT_IN;
                    default:
                        return null;
                }
            }

            public static Internal.EnumLiteMap<Operator> internalGetValueMap() {
                return internalValueMap;
            }

            public static Internal.EnumVerifier internalGetVerifier() {
                return OperatorVerifier.INSTANCE;
            }

            @Override // com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                if (this != UNRECOGNIZED) {
                    return this.value;
                }
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }

            @Deprecated
            public static Operator valueOf(int i) {
                return forNumber(i);
            }
        }

        static {
            FieldFilter fieldFilter = new FieldFilter();
            DEFAULT_INSTANCE = fieldFilter;
            GeneratedMessageLite.defaultInstanceMap.put(FieldFilter.class, fieldFilter);
        }

        private void clearField() {
            this.field_ = null;
        }

        private void clearOp() {
            this.op_ = 0;
        }

        private void clearValue() {
            this.value_ = null;
        }

        public static FieldFilter getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeField(FieldReference fieldReference) {
            fieldReference.getClass();
            FieldReference fieldReference2 = this.field_;
            if (fieldReference2 == null || fieldReference2 == FieldReference.DEFAULT_INSTANCE) {
                this.field_ = fieldReference;
            } else {
                this.field_ = FieldReference.newBuilder(fieldReference2).mergeFrom(fieldReference).buildPartial();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeValue(Value value) {
            value.getClass();
            Value value2 = this.value_;
            if (value2 == null || value2 == Value.DEFAULT_INSTANCE) {
                this.value_ = value;
            } else {
                this.value_ = Value.newBuilder(value2).mergeFrom(value).buildPartial();
            }
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static FieldFilter parseDelimitedFrom(InputStream inputStream) {
            return (FieldFilter) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static FieldFilter parseFrom(ByteBuffer byteBuffer) {
            return (FieldFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<FieldFilter> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setField(FieldReference fieldReference) {
            fieldReference.getClass();
            this.field_ = fieldReference;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setOp(Operator operator) {
            this.op_ = operator.getNumber();
        }

        private void setOpValue(int i) {
            this.op_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setValue(Value value) {
            value.getClass();
            this.value_ = value;
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
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\t\u0002\f\u0003\t", new Object[]{"field_", "op_", "value_"});
                case NEW_MUTABLE_INSTANCE:
                    return new FieldFilter();
                case NEW_BUILDER:
                    return new Builder(anonymousClass1);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser<FieldFilter> defaultInstanceBasedParser = PARSER;
                    if (defaultInstanceBasedParser == null) {
                        synchronized (FieldFilter.class) {
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

        @Override // com.google.firestore.v1.StructuredQuery.FieldFilterOrBuilder
        public FieldReference getField() {
            FieldReference fieldReference = this.field_;
            return fieldReference == null ? FieldReference.DEFAULT_INSTANCE : fieldReference;
        }

        @Override // com.google.firestore.v1.StructuredQuery.FieldFilterOrBuilder
        public Operator getOp() {
            Operator operatorForNumber = Operator.forNumber(this.op_);
            return operatorForNumber == null ? Operator.UNRECOGNIZED : operatorForNumber;
        }

        @Override // com.google.firestore.v1.StructuredQuery.FieldFilterOrBuilder
        public int getOpValue() {
            return this.op_;
        }

        @Override // com.google.firestore.v1.StructuredQuery.FieldFilterOrBuilder
        public Value getValue() {
            Value value = this.value_;
            return value == null ? Value.DEFAULT_INSTANCE : value;
        }

        @Override // com.google.firestore.v1.StructuredQuery.FieldFilterOrBuilder
        public boolean hasField() {
            return this.field_ != null;
        }

        @Override // com.google.firestore.v1.StructuredQuery.FieldFilterOrBuilder
        public boolean hasValue() {
            return this.value_ != null;
        }

        public static final class Builder extends GeneratedMessageLite.Builder<FieldFilter, Builder> implements FieldFilterOrBuilder {
            public Builder() {
                super(FieldFilter.DEFAULT_INSTANCE);
            }

            public Builder clearField() {
                copyOnWrite();
                ((FieldFilter) this.instance).field_ = null;
                return this;
            }

            public Builder clearOp() {
                copyOnWrite();
                ((FieldFilter) this.instance).op_ = 0;
                return this;
            }

            public Builder clearValue() {
                copyOnWrite();
                ((FieldFilter) this.instance).value_ = null;
                return this;
            }

            @Override // com.google.firestore.v1.StructuredQuery.FieldFilterOrBuilder
            public FieldReference getField() {
                return ((FieldFilter) this.instance).getField();
            }

            @Override // com.google.firestore.v1.StructuredQuery.FieldFilterOrBuilder
            public Operator getOp() {
                return ((FieldFilter) this.instance).getOp();
            }

            @Override // com.google.firestore.v1.StructuredQuery.FieldFilterOrBuilder
            public int getOpValue() {
                return ((FieldFilter) this.instance).getOpValue();
            }

            @Override // com.google.firestore.v1.StructuredQuery.FieldFilterOrBuilder
            public Value getValue() {
                return ((FieldFilter) this.instance).getValue();
            }

            @Override // com.google.firestore.v1.StructuredQuery.FieldFilterOrBuilder
            public boolean hasField() {
                return ((FieldFilter) this.instance).hasField();
            }

            @Override // com.google.firestore.v1.StructuredQuery.FieldFilterOrBuilder
            public boolean hasValue() {
                return ((FieldFilter) this.instance).hasValue();
            }

            public Builder mergeField(FieldReference fieldReference) {
                copyOnWrite();
                ((FieldFilter) this.instance).mergeField(fieldReference);
                return this;
            }

            public Builder mergeValue(Value value) {
                copyOnWrite();
                ((FieldFilter) this.instance).mergeValue(value);
                return this;
            }

            public Builder setField(FieldReference fieldReference) {
                copyOnWrite();
                ((FieldFilter) this.instance).setField(fieldReference);
                return this;
            }

            public Builder setOp(Operator operator) {
                copyOnWrite();
                ((FieldFilter) this.instance).setOp(operator);
                return this;
            }

            public Builder setOpValue(int i) {
                copyOnWrite();
                ((FieldFilter) this.instance).op_ = i;
                return this;
            }

            public Builder setValue(Value value) {
                copyOnWrite();
                ((FieldFilter) this.instance).setValue(value);
                return this;
            }

            public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                super(FieldFilter.DEFAULT_INSTANCE);
            }

            public Builder setField(FieldReference.Builder builder) {
                copyOnWrite();
                ((FieldFilter) this.instance).setField(builder.build());
                return this;
            }

            public Builder setValue(Value.Builder builder) {
                copyOnWrite();
                ((FieldFilter) this.instance).setValue(builder.build());
                return this;
            }
        }

        public static Builder newBuilder(FieldFilter fieldFilter) {
            return DEFAULT_INSTANCE.createBuilder(fieldFilter);
        }

        public static FieldFilter parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (FieldFilter) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static FieldFilter parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
            return (FieldFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static FieldFilter parseFrom(ByteString byteString) {
            return (FieldFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static FieldFilter parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (FieldFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static FieldFilter parseFrom(byte[] bArr) {
            return (FieldFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static FieldFilter parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (FieldFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static FieldFilter parseFrom(InputStream inputStream) {
            return (FieldFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static FieldFilter parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (FieldFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static FieldFilter parseFrom(CodedInputStream codedInputStream) {
            return (FieldFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static FieldFilter parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (FieldFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    public interface FieldFilterOrBuilder extends MessageLiteOrBuilder {
        FieldReference getField();

        FieldFilter.Operator getOp();

        int getOpValue();

        Value getValue();

        boolean hasField();

        boolean hasValue();
    }

    public static final class FieldReference extends GeneratedMessageLite<FieldReference, Builder> implements FieldReferenceOrBuilder {
        public static final FieldReference DEFAULT_INSTANCE;
        public static final int FIELD_PATH_FIELD_NUMBER = 2;
        public static volatile Parser<FieldReference> PARSER;
        public String fieldPath_ = "";

        static {
            FieldReference fieldReference = new FieldReference();
            DEFAULT_INSTANCE = fieldReference;
            GeneratedMessageLite.defaultInstanceMap.put(FieldReference.class, fieldReference);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearFieldPath() {
            this.fieldPath_ = DEFAULT_INSTANCE.getFieldPath();
        }

        public static FieldReference getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static FieldReference parseDelimitedFrom(InputStream inputStream) {
            return (FieldReference) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static FieldReference parseFrom(ByteBuffer byteBuffer) {
            return (FieldReference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<FieldReference> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFieldPath(String str) {
            str.getClass();
            this.fieldPath_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFieldPathBytes(ByteString byteString) {
            AbstractMessageLite.checkByteStringIsUtf8(byteString);
            this.fieldPath_ = byteString.toStringUtf8();
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
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0002\u0002\u0001\u0000\u0000\u0000\u0002Ȉ", new Object[]{"fieldPath_"});
                case NEW_MUTABLE_INSTANCE:
                    return new FieldReference();
                case NEW_BUILDER:
                    return new Builder(anonymousClass1);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser<FieldReference> defaultInstanceBasedParser = PARSER;
                    if (defaultInstanceBasedParser == null) {
                        synchronized (FieldReference.class) {
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

        @Override // com.google.firestore.v1.StructuredQuery.FieldReferenceOrBuilder
        public String getFieldPath() {
            return this.fieldPath_;
        }

        @Override // com.google.firestore.v1.StructuredQuery.FieldReferenceOrBuilder
        public ByteString getFieldPathBytes() {
            return ByteString.copyFromUtf8(this.fieldPath_);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<FieldReference, Builder> implements FieldReferenceOrBuilder {
            public Builder() {
                super(FieldReference.DEFAULT_INSTANCE);
            }

            public Builder clearFieldPath() {
                copyOnWrite();
                ((FieldReference) this.instance).clearFieldPath();
                return this;
            }

            @Override // com.google.firestore.v1.StructuredQuery.FieldReferenceOrBuilder
            public String getFieldPath() {
                return ((FieldReference) this.instance).getFieldPath();
            }

            @Override // com.google.firestore.v1.StructuredQuery.FieldReferenceOrBuilder
            public ByteString getFieldPathBytes() {
                return ((FieldReference) this.instance).getFieldPathBytes();
            }

            public Builder setFieldPath(String str) {
                copyOnWrite();
                ((FieldReference) this.instance).setFieldPath(str);
                return this;
            }

            public Builder setFieldPathBytes(ByteString byteString) {
                copyOnWrite();
                ((FieldReference) this.instance).setFieldPathBytes(byteString);
                return this;
            }

            public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                super(FieldReference.DEFAULT_INSTANCE);
            }
        }

        public static Builder newBuilder(FieldReference fieldReference) {
            return DEFAULT_INSTANCE.createBuilder(fieldReference);
        }

        public static FieldReference parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (FieldReference) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static FieldReference parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
            return (FieldReference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static FieldReference parseFrom(ByteString byteString) {
            return (FieldReference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static FieldReference parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (FieldReference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static FieldReference parseFrom(byte[] bArr) {
            return (FieldReference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static FieldReference parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (FieldReference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static FieldReference parseFrom(InputStream inputStream) {
            return (FieldReference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static FieldReference parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (FieldReference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static FieldReference parseFrom(CodedInputStream codedInputStream) {
            return (FieldReference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static FieldReference parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (FieldReference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    public interface FieldReferenceOrBuilder extends MessageLiteOrBuilder {
        String getFieldPath();

        ByteString getFieldPathBytes();
    }

    public static final class Filter extends GeneratedMessageLite<Filter, Builder> implements FilterOrBuilder {
        public static final int COMPOSITE_FILTER_FIELD_NUMBER = 1;
        public static final Filter DEFAULT_INSTANCE;
        public static final int FIELD_FILTER_FIELD_NUMBER = 2;
        public static volatile Parser<Filter> PARSER = null;
        public static final int UNARY_FILTER_FIELD_NUMBER = 3;
        public int filterTypeCase_ = 0;
        public Object filterType_;

        public enum FilterTypeCase {
            COMPOSITE_FILTER(1),
            FIELD_FILTER(2),
            UNARY_FILTER(3),
            FILTERTYPE_NOT_SET(0);

            public final int value;

            FilterTypeCase(int i) {
                this.value = i;
            }

            public static FilterTypeCase forNumber(int i) {
                if (i == 0) {
                    return FILTERTYPE_NOT_SET;
                }
                if (i == 1) {
                    return COMPOSITE_FILTER;
                }
                if (i == 2) {
                    return FIELD_FILTER;
                }
                if (i != 3) {
                    return null;
                }
                return UNARY_FILTER;
            }

            public int getNumber() {
                return this.value;
            }

            @Deprecated
            public static FilterTypeCase valueOf(int i) {
                return forNumber(i);
            }
        }

        static {
            Filter filter = new Filter();
            DEFAULT_INSTANCE = filter;
            GeneratedMessageLite.defaultInstanceMap.put(Filter.class, filter);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCompositeFilter() {
            if (this.filterTypeCase_ == 1) {
                this.filterTypeCase_ = 0;
                this.filterType_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearFieldFilter() {
            if (this.filterTypeCase_ == 2) {
                this.filterTypeCase_ = 0;
                this.filterType_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearFilterType() {
            this.filterTypeCase_ = 0;
            this.filterType_ = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearUnaryFilter() {
            if (this.filterTypeCase_ == 3) {
                this.filterTypeCase_ = 0;
                this.filterType_ = null;
            }
        }

        public static Filter getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeCompositeFilter(CompositeFilter compositeFilter) {
            Object obj;
            compositeFilter.getClass();
            if (this.filterTypeCase_ != 1 || (obj = this.filterType_) == CompositeFilter.DEFAULT_INSTANCE) {
                this.filterType_ = compositeFilter;
            } else {
                this.filterType_ = CompositeFilter.newBuilder((CompositeFilter) obj).mergeFrom(compositeFilter).buildPartial();
            }
            this.filterTypeCase_ = 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeFieldFilter(FieldFilter fieldFilter) {
            Object obj;
            fieldFilter.getClass();
            if (this.filterTypeCase_ != 2 || (obj = this.filterType_) == FieldFilter.DEFAULT_INSTANCE) {
                this.filterType_ = fieldFilter;
            } else {
                this.filterType_ = FieldFilter.newBuilder((FieldFilter) obj).mergeFrom(fieldFilter).buildPartial();
            }
            this.filterTypeCase_ = 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeUnaryFilter(UnaryFilter unaryFilter) {
            Object obj;
            unaryFilter.getClass();
            if (this.filterTypeCase_ != 3 || (obj = this.filterType_) == UnaryFilter.DEFAULT_INSTANCE) {
                this.filterType_ = unaryFilter;
            } else {
                this.filterType_ = UnaryFilter.newBuilder((UnaryFilter) obj).mergeFrom(unaryFilter).buildPartial();
            }
            this.filterTypeCase_ = 3;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Filter parseDelimitedFrom(InputStream inputStream) {
            return (Filter) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Filter parseFrom(ByteBuffer byteBuffer) {
            return (Filter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<Filter> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCompositeFilter(CompositeFilter compositeFilter) {
            compositeFilter.getClass();
            this.filterType_ = compositeFilter;
            this.filterTypeCase_ = 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFieldFilter(FieldFilter fieldFilter) {
            fieldFilter.getClass();
            this.filterType_ = fieldFilter;
            this.filterTypeCase_ = 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUnaryFilter(UnaryFilter unaryFilter) {
            unaryFilter.getClass();
            this.filterType_ = unaryFilter;
            this.filterTypeCase_ = 3;
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
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0001\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001<\u0000\u0002<\u0000\u0003<\u0000", new Object[]{"filterType_", "filterTypeCase_", CompositeFilter.class, FieldFilter.class, UnaryFilter.class});
                case NEW_MUTABLE_INSTANCE:
                    return new Filter();
                case NEW_BUILDER:
                    return new Builder(anonymousClass1);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser<Filter> defaultInstanceBasedParser = PARSER;
                    if (defaultInstanceBasedParser == null) {
                        synchronized (Filter.class) {
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

        @Override // com.google.firestore.v1.StructuredQuery.FilterOrBuilder
        public CompositeFilter getCompositeFilter() {
            return this.filterTypeCase_ == 1 ? (CompositeFilter) this.filterType_ : CompositeFilter.DEFAULT_INSTANCE;
        }

        @Override // com.google.firestore.v1.StructuredQuery.FilterOrBuilder
        public FieldFilter getFieldFilter() {
            return this.filterTypeCase_ == 2 ? (FieldFilter) this.filterType_ : FieldFilter.DEFAULT_INSTANCE;
        }

        @Override // com.google.firestore.v1.StructuredQuery.FilterOrBuilder
        public FilterTypeCase getFilterTypeCase() {
            return FilterTypeCase.forNumber(this.filterTypeCase_);
        }

        @Override // com.google.firestore.v1.StructuredQuery.FilterOrBuilder
        public UnaryFilter getUnaryFilter() {
            return this.filterTypeCase_ == 3 ? (UnaryFilter) this.filterType_ : UnaryFilter.DEFAULT_INSTANCE;
        }

        @Override // com.google.firestore.v1.StructuredQuery.FilterOrBuilder
        public boolean hasCompositeFilter() {
            return this.filterTypeCase_ == 1;
        }

        @Override // com.google.firestore.v1.StructuredQuery.FilterOrBuilder
        public boolean hasFieldFilter() {
            return this.filterTypeCase_ == 2;
        }

        @Override // com.google.firestore.v1.StructuredQuery.FilterOrBuilder
        public boolean hasUnaryFilter() {
            return this.filterTypeCase_ == 3;
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Filter, Builder> implements FilterOrBuilder {
            public Builder() {
                super(Filter.DEFAULT_INSTANCE);
            }

            public Builder clearCompositeFilter() {
                copyOnWrite();
                ((Filter) this.instance).clearCompositeFilter();
                return this;
            }

            public Builder clearFieldFilter() {
                copyOnWrite();
                ((Filter) this.instance).clearFieldFilter();
                return this;
            }

            public Builder clearFilterType() {
                copyOnWrite();
                ((Filter) this.instance).clearFilterType();
                return this;
            }

            public Builder clearUnaryFilter() {
                copyOnWrite();
                ((Filter) this.instance).clearUnaryFilter();
                return this;
            }

            @Override // com.google.firestore.v1.StructuredQuery.FilterOrBuilder
            public CompositeFilter getCompositeFilter() {
                return ((Filter) this.instance).getCompositeFilter();
            }

            @Override // com.google.firestore.v1.StructuredQuery.FilterOrBuilder
            public FieldFilter getFieldFilter() {
                return ((Filter) this.instance).getFieldFilter();
            }

            @Override // com.google.firestore.v1.StructuredQuery.FilterOrBuilder
            public FilterTypeCase getFilterTypeCase() {
                return ((Filter) this.instance).getFilterTypeCase();
            }

            @Override // com.google.firestore.v1.StructuredQuery.FilterOrBuilder
            public UnaryFilter getUnaryFilter() {
                return ((Filter) this.instance).getUnaryFilter();
            }

            @Override // com.google.firestore.v1.StructuredQuery.FilterOrBuilder
            public boolean hasCompositeFilter() {
                return ((Filter) this.instance).hasCompositeFilter();
            }

            @Override // com.google.firestore.v1.StructuredQuery.FilterOrBuilder
            public boolean hasFieldFilter() {
                return ((Filter) this.instance).hasFieldFilter();
            }

            @Override // com.google.firestore.v1.StructuredQuery.FilterOrBuilder
            public boolean hasUnaryFilter() {
                return ((Filter) this.instance).hasUnaryFilter();
            }

            public Builder mergeCompositeFilter(CompositeFilter compositeFilter) {
                copyOnWrite();
                ((Filter) this.instance).mergeCompositeFilter(compositeFilter);
                return this;
            }

            public Builder mergeFieldFilter(FieldFilter fieldFilter) {
                copyOnWrite();
                ((Filter) this.instance).mergeFieldFilter(fieldFilter);
                return this;
            }

            public Builder mergeUnaryFilter(UnaryFilter unaryFilter) {
                copyOnWrite();
                ((Filter) this.instance).mergeUnaryFilter(unaryFilter);
                return this;
            }

            public Builder setCompositeFilter(CompositeFilter compositeFilter) {
                copyOnWrite();
                ((Filter) this.instance).setCompositeFilter(compositeFilter);
                return this;
            }

            public Builder setFieldFilter(FieldFilter fieldFilter) {
                copyOnWrite();
                ((Filter) this.instance).setFieldFilter(fieldFilter);
                return this;
            }

            public Builder setUnaryFilter(UnaryFilter unaryFilter) {
                copyOnWrite();
                ((Filter) this.instance).setUnaryFilter(unaryFilter);
                return this;
            }

            public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                super(Filter.DEFAULT_INSTANCE);
            }

            public Builder setCompositeFilter(CompositeFilter.Builder builder) {
                copyOnWrite();
                ((Filter) this.instance).setCompositeFilter(builder.build());
                return this;
            }

            public Builder setFieldFilter(FieldFilter.Builder builder) {
                copyOnWrite();
                ((Filter) this.instance).setFieldFilter(builder.build());
                return this;
            }

            public Builder setUnaryFilter(UnaryFilter.Builder builder) {
                copyOnWrite();
                ((Filter) this.instance).setUnaryFilter(builder.build());
                return this;
            }
        }

        public static Builder newBuilder(Filter filter) {
            return DEFAULT_INSTANCE.createBuilder(filter);
        }

        public static Filter parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (Filter) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Filter parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
            return (Filter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static Filter parseFrom(ByteString byteString) {
            return (Filter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static Filter parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (Filter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static Filter parseFrom(byte[] bArr) {
            return (Filter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Filter parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (Filter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static Filter parseFrom(InputStream inputStream) {
            return (Filter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Filter parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (Filter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Filter parseFrom(CodedInputStream codedInputStream) {
            return (Filter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static Filter parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (Filter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    public interface FilterOrBuilder extends MessageLiteOrBuilder {
        CompositeFilter getCompositeFilter();

        FieldFilter getFieldFilter();

        Filter.FilterTypeCase getFilterTypeCase();

        UnaryFilter getUnaryFilter();

        boolean hasCompositeFilter();

        boolean hasFieldFilter();

        boolean hasUnaryFilter();
    }

    public static final class Order extends GeneratedMessageLite<Order, Builder> implements OrderOrBuilder {
        public static final Order DEFAULT_INSTANCE;
        public static final int DIRECTION_FIELD_NUMBER = 2;
        public static final int FIELD_FIELD_NUMBER = 1;
        public static volatile Parser<Order> PARSER;
        public int direction_;
        public FieldReference field_;

        static {
            Order order = new Order();
            DEFAULT_INSTANCE = order;
            GeneratedMessageLite.defaultInstanceMap.put(Order.class, order);
        }

        private void clearDirection() {
            this.direction_ = 0;
        }

        private void clearField() {
            this.field_ = null;
        }

        public static Order getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeField(FieldReference fieldReference) {
            fieldReference.getClass();
            FieldReference fieldReference2 = this.field_;
            if (fieldReference2 == null || fieldReference2 == FieldReference.DEFAULT_INSTANCE) {
                this.field_ = fieldReference;
            } else {
                this.field_ = FieldReference.newBuilder(fieldReference2).mergeFrom(fieldReference).buildPartial();
            }
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Order parseDelimitedFrom(InputStream inputStream) {
            return (Order) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Order parseFrom(ByteBuffer byteBuffer) {
            return (Order) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<Order> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDirection(Direction direction) {
            this.direction_ = direction.getNumber();
        }

        private void setDirectionValue(int i) {
            this.direction_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setField(FieldReference fieldReference) {
            fieldReference.getClass();
            this.field_ = fieldReference;
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
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\f", new Object[]{"field_", "direction_"});
                case NEW_MUTABLE_INSTANCE:
                    return new Order();
                case NEW_BUILDER:
                    return new Builder(anonymousClass1);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser<Order> defaultInstanceBasedParser = PARSER;
                    if (defaultInstanceBasedParser == null) {
                        synchronized (Order.class) {
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

        @Override // com.google.firestore.v1.StructuredQuery.OrderOrBuilder
        public Direction getDirection() {
            Direction directionForNumber = Direction.forNumber(this.direction_);
            return directionForNumber == null ? Direction.UNRECOGNIZED : directionForNumber;
        }

        @Override // com.google.firestore.v1.StructuredQuery.OrderOrBuilder
        public int getDirectionValue() {
            return this.direction_;
        }

        @Override // com.google.firestore.v1.StructuredQuery.OrderOrBuilder
        public FieldReference getField() {
            FieldReference fieldReference = this.field_;
            return fieldReference == null ? FieldReference.DEFAULT_INSTANCE : fieldReference;
        }

        @Override // com.google.firestore.v1.StructuredQuery.OrderOrBuilder
        public boolean hasField() {
            return this.field_ != null;
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Order, Builder> implements OrderOrBuilder {
            public Builder() {
                super(Order.DEFAULT_INSTANCE);
            }

            public Builder clearDirection() {
                copyOnWrite();
                ((Order) this.instance).direction_ = 0;
                return this;
            }

            public Builder clearField() {
                copyOnWrite();
                ((Order) this.instance).field_ = null;
                return this;
            }

            @Override // com.google.firestore.v1.StructuredQuery.OrderOrBuilder
            public Direction getDirection() {
                return ((Order) this.instance).getDirection();
            }

            @Override // com.google.firestore.v1.StructuredQuery.OrderOrBuilder
            public int getDirectionValue() {
                return ((Order) this.instance).getDirectionValue();
            }

            @Override // com.google.firestore.v1.StructuredQuery.OrderOrBuilder
            public FieldReference getField() {
                return ((Order) this.instance).getField();
            }

            @Override // com.google.firestore.v1.StructuredQuery.OrderOrBuilder
            public boolean hasField() {
                return ((Order) this.instance).hasField();
            }

            public Builder mergeField(FieldReference fieldReference) {
                copyOnWrite();
                ((Order) this.instance).mergeField(fieldReference);
                return this;
            }

            public Builder setDirection(Direction direction) {
                copyOnWrite();
                ((Order) this.instance).setDirection(direction);
                return this;
            }

            public Builder setDirectionValue(int i) {
                copyOnWrite();
                ((Order) this.instance).direction_ = i;
                return this;
            }

            public Builder setField(FieldReference fieldReference) {
                copyOnWrite();
                ((Order) this.instance).setField(fieldReference);
                return this;
            }

            public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                super(Order.DEFAULT_INSTANCE);
            }

            public Builder setField(FieldReference.Builder builder) {
                copyOnWrite();
                ((Order) this.instance).setField(builder.build());
                return this;
            }
        }

        public static Builder newBuilder(Order order) {
            return DEFAULT_INSTANCE.createBuilder(order);
        }

        public static Order parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (Order) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Order parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
            return (Order) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static Order parseFrom(ByteString byteString) {
            return (Order) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static Order parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (Order) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static Order parseFrom(byte[] bArr) {
            return (Order) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Order parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (Order) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static Order parseFrom(InputStream inputStream) {
            return (Order) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Order parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (Order) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Order parseFrom(CodedInputStream codedInputStream) {
            return (Order) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static Order parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (Order) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    public interface OrderOrBuilder extends MessageLiteOrBuilder {
        Direction getDirection();

        int getDirectionValue();

        FieldReference getField();

        boolean hasField();
    }

    public static final class Projection extends GeneratedMessageLite<Projection, Builder> implements ProjectionOrBuilder {
        public static final Projection DEFAULT_INSTANCE;
        public static final int FIELDS_FIELD_NUMBER = 2;
        public static volatile Parser<Projection> PARSER;
        public Internal.ProtobufList<FieldReference> fields_ = GeneratedMessageLite.emptyProtobufList();

        static {
            Projection projection = new Projection();
            DEFAULT_INSTANCE = projection;
            GeneratedMessageLite.defaultInstanceMap.put(Projection.class, projection);
        }

        public static /* synthetic */ void access$6700(Projection projection, Iterable iterable) {
            projection.ensureFieldsIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, (List) projection.fields_);
        }

        public static /* synthetic */ void access$6900(Projection projection, int i) {
            projection.ensureFieldsIsMutable();
            projection.fields_.remove(i);
        }

        private void addAllFields(Iterable<? extends FieldReference> iterable) {
            ensureFieldsIsMutable();
            AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.fields_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addFields(FieldReference fieldReference) {
            fieldReference.getClass();
            ensureFieldsIsMutable();
            this.fields_.add(fieldReference);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearFields() {
            this.fields_ = GeneratedMessageLite.emptyProtobufList();
        }

        private void ensureFieldsIsMutable() {
            Internal.ProtobufList<FieldReference> protobufList = this.fields_;
            if (protobufList.isModifiable()) {
                return;
            }
            this.fields_ = GeneratedMessageLite.mutableCopy(protobufList);
        }

        public static Projection getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Projection parseDelimitedFrom(InputStream inputStream) {
            return (Projection) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Projection parseFrom(ByteBuffer byteBuffer) {
            return (Projection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<Projection> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        private void removeFields(int i) {
            ensureFieldsIsMutable();
            this.fields_.remove(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFields(int i, FieldReference fieldReference) {
            fieldReference.getClass();
            ensureFieldsIsMutable();
            this.fields_.set(i, fieldReference);
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
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0002\u0002\u0001\u0000\u0001\u0000\u0002\u001b", new Object[]{"fields_", FieldReference.class});
                case NEW_MUTABLE_INSTANCE:
                    return new Projection();
                case NEW_BUILDER:
                    return new Builder(anonymousClass1);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser<Projection> defaultInstanceBasedParser = PARSER;
                    if (defaultInstanceBasedParser == null) {
                        synchronized (Projection.class) {
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

        @Override // com.google.firestore.v1.StructuredQuery.ProjectionOrBuilder
        public FieldReference getFields(int i) {
            return this.fields_.get(i);
        }

        @Override // com.google.firestore.v1.StructuredQuery.ProjectionOrBuilder
        public int getFieldsCount() {
            return this.fields_.size();
        }

        @Override // com.google.firestore.v1.StructuredQuery.ProjectionOrBuilder
        public List<FieldReference> getFieldsList() {
            return this.fields_;
        }

        public FieldReferenceOrBuilder getFieldsOrBuilder(int i) {
            return this.fields_.get(i);
        }

        public List<? extends FieldReferenceOrBuilder> getFieldsOrBuilderList() {
            return this.fields_;
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Projection, Builder> implements ProjectionOrBuilder {
            public Builder() {
                super(Projection.DEFAULT_INSTANCE);
            }

            public Builder addAllFields(Iterable<? extends FieldReference> iterable) {
                copyOnWrite();
                Projection.access$6700((Projection) this.instance, iterable);
                return this;
            }

            public Builder addFields(FieldReference fieldReference) {
                copyOnWrite();
                ((Projection) this.instance).addFields(fieldReference);
                return this;
            }

            public Builder clearFields() {
                copyOnWrite();
                ((Projection) this.instance).clearFields();
                return this;
            }

            @Override // com.google.firestore.v1.StructuredQuery.ProjectionOrBuilder
            public FieldReference getFields(int i) {
                return ((Projection) this.instance).getFields(i);
            }

            @Override // com.google.firestore.v1.StructuredQuery.ProjectionOrBuilder
            public int getFieldsCount() {
                return ((Projection) this.instance).getFieldsCount();
            }

            @Override // com.google.firestore.v1.StructuredQuery.ProjectionOrBuilder
            public List<FieldReference> getFieldsList() {
                return Collections.unmodifiableList(((Projection) this.instance).getFieldsList());
            }

            public Builder removeFields(int i) {
                copyOnWrite();
                Projection.access$6900((Projection) this.instance, i);
                return this;
            }

            public Builder setFields(int i, FieldReference fieldReference) {
                copyOnWrite();
                ((Projection) this.instance).setFields(i, fieldReference);
                return this;
            }

            public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                super(Projection.DEFAULT_INSTANCE);
            }

            public Builder addFields(int i, FieldReference fieldReference) {
                copyOnWrite();
                ((Projection) this.instance).addFields(i, fieldReference);
                return this;
            }

            public Builder setFields(int i, FieldReference.Builder builder) {
                copyOnWrite();
                ((Projection) this.instance).setFields(i, builder.build());
                return this;
            }

            public Builder addFields(FieldReference.Builder builder) {
                copyOnWrite();
                ((Projection) this.instance).addFields(builder.build());
                return this;
            }

            public Builder addFields(int i, FieldReference.Builder builder) {
                copyOnWrite();
                ((Projection) this.instance).addFields(i, builder.build());
                return this;
            }
        }

        public static Builder newBuilder(Projection projection) {
            return DEFAULT_INSTANCE.createBuilder(projection);
        }

        public static Projection parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (Projection) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Projection parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
            return (Projection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static Projection parseFrom(ByteString byteString) {
            return (Projection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addFields(int i, FieldReference fieldReference) {
            fieldReference.getClass();
            ensureFieldsIsMutable();
            this.fields_.add(i, fieldReference);
        }

        public static Projection parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (Projection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static Projection parseFrom(byte[] bArr) {
            return (Projection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Projection parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (Projection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static Projection parseFrom(InputStream inputStream) {
            return (Projection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Projection parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (Projection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Projection parseFrom(CodedInputStream codedInputStream) {
            return (Projection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static Projection parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (Projection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    public interface ProjectionOrBuilder extends MessageLiteOrBuilder {
        FieldReference getFields(int i);

        int getFieldsCount();

        List<FieldReference> getFieldsList();
    }

    public static final class UnaryFilter extends GeneratedMessageLite<UnaryFilter, Builder> implements UnaryFilterOrBuilder {
        public static final UnaryFilter DEFAULT_INSTANCE;
        public static final int FIELD_FIELD_NUMBER = 2;
        public static final int OP_FIELD_NUMBER = 1;
        public static volatile Parser<UnaryFilter> PARSER;
        public int op_;
        public int operandTypeCase_ = 0;
        public Object operandType_;

        public enum OperandTypeCase {
            FIELD(2),
            OPERANDTYPE_NOT_SET(0);

            public final int value;

            OperandTypeCase(int i) {
                this.value = i;
            }

            public static OperandTypeCase forNumber(int i) {
                if (i == 0) {
                    return OPERANDTYPE_NOT_SET;
                }
                if (i != 2) {
                    return null;
                }
                return FIELD;
            }

            public int getNumber() {
                return this.value;
            }

            @Deprecated
            public static OperandTypeCase valueOf(int i) {
                return forNumber(i);
            }
        }

        public enum Operator implements Internal.EnumLite {
            OPERATOR_UNSPECIFIED(0),
            IS_NAN(2),
            IS_NULL(3),
            IS_NOT_NAN(4),
            IS_NOT_NULL(5),
            UNRECOGNIZED(-1);

            public static final int IS_NAN_VALUE = 2;
            public static final int IS_NOT_NAN_VALUE = 4;
            public static final int IS_NOT_NULL_VALUE = 5;
            public static final int IS_NULL_VALUE = 3;
            public static final int OPERATOR_UNSPECIFIED_VALUE = 0;
            public static final Internal.EnumLiteMap<Operator> internalValueMap = new Internal.EnumLiteMap<Operator>() { // from class: com.google.firestore.v1.StructuredQuery.UnaryFilter.Operator.1
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public Operator findValueByNumber(int i) {
                    return Operator.forNumber(i);
                }
            };
            public final int value;

            private static final class OperatorVerifier implements Internal.EnumVerifier {
                public static final Internal.EnumVerifier INSTANCE = new OperatorVerifier();

                @Override // com.google.protobuf.Internal.EnumVerifier
                public boolean isInRange(int i) {
                    return Operator.forNumber(i) != null;
                }
            }

            Operator(int i) {
                this.value = i;
            }

            public static Operator forNumber(int i) {
                if (i == 0) {
                    return OPERATOR_UNSPECIFIED;
                }
                if (i == 2) {
                    return IS_NAN;
                }
                if (i == 3) {
                    return IS_NULL;
                }
                if (i == 4) {
                    return IS_NOT_NAN;
                }
                if (i != 5) {
                    return null;
                }
                return IS_NOT_NULL;
            }

            public static Internal.EnumLiteMap<Operator> internalGetValueMap() {
                return internalValueMap;
            }

            public static Internal.EnumVerifier internalGetVerifier() {
                return OperatorVerifier.INSTANCE;
            }

            @Override // com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                if (this != UNRECOGNIZED) {
                    return this.value;
                }
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }

            @Deprecated
            public static Operator valueOf(int i) {
                return forNumber(i);
            }
        }

        static {
            UnaryFilter unaryFilter = new UnaryFilter();
            DEFAULT_INSTANCE = unaryFilter;
            GeneratedMessageLite.defaultInstanceMap.put(UnaryFilter.class, unaryFilter);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearField() {
            if (this.operandTypeCase_ == 2) {
                this.operandTypeCase_ = 0;
                this.operandType_ = null;
            }
        }

        private void clearOp() {
            this.op_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearOperandType() {
            this.operandTypeCase_ = 0;
            this.operandType_ = null;
        }

        public static UnaryFilter getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeField(FieldReference fieldReference) {
            Object obj;
            fieldReference.getClass();
            if (this.operandTypeCase_ != 2 || (obj = this.operandType_) == FieldReference.DEFAULT_INSTANCE) {
                this.operandType_ = fieldReference;
            } else {
                this.operandType_ = FieldReference.newBuilder((FieldReference) obj).mergeFrom(fieldReference).buildPartial();
            }
            this.operandTypeCase_ = 2;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static UnaryFilter parseDelimitedFrom(InputStream inputStream) {
            return (UnaryFilter) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static UnaryFilter parseFrom(ByteBuffer byteBuffer) {
            return (UnaryFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<UnaryFilter> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setField(FieldReference fieldReference) {
            fieldReference.getClass();
            this.operandType_ = fieldReference;
            this.operandTypeCase_ = 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setOp(Operator operator) {
            this.op_ = operator.getNumber();
        }

        private void setOpValue(int i) {
            this.op_ = i;
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
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0001\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\f\u0002<\u0000", new Object[]{"operandType_", "operandTypeCase_", "op_", FieldReference.class});
                case NEW_MUTABLE_INSTANCE:
                    return new UnaryFilter();
                case NEW_BUILDER:
                    return new Builder(anonymousClass1);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser<UnaryFilter> defaultInstanceBasedParser = PARSER;
                    if (defaultInstanceBasedParser == null) {
                        synchronized (UnaryFilter.class) {
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

        @Override // com.google.firestore.v1.StructuredQuery.UnaryFilterOrBuilder
        public FieldReference getField() {
            return this.operandTypeCase_ == 2 ? (FieldReference) this.operandType_ : FieldReference.DEFAULT_INSTANCE;
        }

        @Override // com.google.firestore.v1.StructuredQuery.UnaryFilterOrBuilder
        public Operator getOp() {
            Operator operatorForNumber = Operator.forNumber(this.op_);
            return operatorForNumber == null ? Operator.UNRECOGNIZED : operatorForNumber;
        }

        @Override // com.google.firestore.v1.StructuredQuery.UnaryFilterOrBuilder
        public int getOpValue() {
            return this.op_;
        }

        @Override // com.google.firestore.v1.StructuredQuery.UnaryFilterOrBuilder
        public OperandTypeCase getOperandTypeCase() {
            return OperandTypeCase.forNumber(this.operandTypeCase_);
        }

        @Override // com.google.firestore.v1.StructuredQuery.UnaryFilterOrBuilder
        public boolean hasField() {
            return this.operandTypeCase_ == 2;
        }

        public static final class Builder extends GeneratedMessageLite.Builder<UnaryFilter, Builder> implements UnaryFilterOrBuilder {
            public Builder() {
                super(UnaryFilter.DEFAULT_INSTANCE);
            }

            public Builder clearField() {
                copyOnWrite();
                ((UnaryFilter) this.instance).clearField();
                return this;
            }

            public Builder clearOp() {
                copyOnWrite();
                ((UnaryFilter) this.instance).op_ = 0;
                return this;
            }

            public Builder clearOperandType() {
                copyOnWrite();
                ((UnaryFilter) this.instance).clearOperandType();
                return this;
            }

            @Override // com.google.firestore.v1.StructuredQuery.UnaryFilterOrBuilder
            public FieldReference getField() {
                return ((UnaryFilter) this.instance).getField();
            }

            @Override // com.google.firestore.v1.StructuredQuery.UnaryFilterOrBuilder
            public Operator getOp() {
                return ((UnaryFilter) this.instance).getOp();
            }

            @Override // com.google.firestore.v1.StructuredQuery.UnaryFilterOrBuilder
            public int getOpValue() {
                return ((UnaryFilter) this.instance).getOpValue();
            }

            @Override // com.google.firestore.v1.StructuredQuery.UnaryFilterOrBuilder
            public OperandTypeCase getOperandTypeCase() {
                return ((UnaryFilter) this.instance).getOperandTypeCase();
            }

            @Override // com.google.firestore.v1.StructuredQuery.UnaryFilterOrBuilder
            public boolean hasField() {
                return ((UnaryFilter) this.instance).hasField();
            }

            public Builder mergeField(FieldReference fieldReference) {
                copyOnWrite();
                ((UnaryFilter) this.instance).mergeField(fieldReference);
                return this;
            }

            public Builder setField(FieldReference fieldReference) {
                copyOnWrite();
                ((UnaryFilter) this.instance).setField(fieldReference);
                return this;
            }

            public Builder setOp(Operator operator) {
                copyOnWrite();
                ((UnaryFilter) this.instance).setOp(operator);
                return this;
            }

            public Builder setOpValue(int i) {
                copyOnWrite();
                ((UnaryFilter) this.instance).op_ = i;
                return this;
            }

            public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                super(UnaryFilter.DEFAULT_INSTANCE);
            }

            public Builder setField(FieldReference.Builder builder) {
                copyOnWrite();
                ((UnaryFilter) this.instance).setField(builder.build());
                return this;
            }
        }

        public static Builder newBuilder(UnaryFilter unaryFilter) {
            return DEFAULT_INSTANCE.createBuilder(unaryFilter);
        }

        public static UnaryFilter parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (UnaryFilter) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static UnaryFilter parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
            return (UnaryFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static UnaryFilter parseFrom(ByteString byteString) {
            return (UnaryFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static UnaryFilter parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (UnaryFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static UnaryFilter parseFrom(byte[] bArr) {
            return (UnaryFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static UnaryFilter parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (UnaryFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static UnaryFilter parseFrom(InputStream inputStream) {
            return (UnaryFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static UnaryFilter parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (UnaryFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static UnaryFilter parseFrom(CodedInputStream codedInputStream) {
            return (UnaryFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static UnaryFilter parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (UnaryFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    public interface UnaryFilterOrBuilder extends MessageLiteOrBuilder {
        FieldReference getField();

        UnaryFilter.Operator getOp();

        int getOpValue();

        UnaryFilter.OperandTypeCase getOperandTypeCase();

        boolean hasField();
    }

    static {
        StructuredQuery structuredQuery = new StructuredQuery();
        DEFAULT_INSTANCE = structuredQuery;
        GeneratedMessageLite.defaultInstanceMap.put(StructuredQuery.class, structuredQuery);
    }

    public static /* synthetic */ void access$7800(StructuredQuery structuredQuery, Iterable iterable) {
        structuredQuery.ensureFromIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) structuredQuery.from_);
    }

    public static /* synthetic */ void access$8000(StructuredQuery structuredQuery, int i) {
        structuredQuery.ensureFromIsMutable();
        structuredQuery.from_.remove(i);
    }

    public static /* synthetic */ void access$8700(StructuredQuery structuredQuery, Iterable iterable) {
        structuredQuery.ensureOrderByIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) structuredQuery.orderBy_);
    }

    public static /* synthetic */ void access$8900(StructuredQuery structuredQuery, int i) {
        structuredQuery.ensureOrderByIsMutable();
        structuredQuery.orderBy_.remove(i);
    }

    private void addAllFrom(Iterable<? extends CollectionSelector> iterable) {
        ensureFromIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.from_);
    }

    private void addAllOrderBy(Iterable<? extends Order> iterable) {
        ensureOrderByIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.orderBy_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addFrom(CollectionSelector collectionSelector) {
        collectionSelector.getClass();
        ensureFromIsMutable();
        this.from_.add(collectionSelector);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addOrderBy(Order order) {
        order.getClass();
        ensureOrderByIsMutable();
        this.orderBy_.add(order);
    }

    private void clearEndAt() {
        this.endAt_ = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearFrom() {
        this.from_ = GeneratedMessageLite.emptyProtobufList();
    }

    private void clearLimit() {
        this.limit_ = null;
    }

    private void clearOffset() {
        this.offset_ = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearOrderBy() {
        this.orderBy_ = GeneratedMessageLite.emptyProtobufList();
    }

    private void clearSelect() {
        this.select_ = null;
    }

    private void clearStartAt() {
        this.startAt_ = null;
    }

    private void clearWhere() {
        this.where_ = null;
    }

    private void ensureFromIsMutable() {
        Internal.ProtobufList<CollectionSelector> protobufList = this.from_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.from_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    private void ensureOrderByIsMutable() {
        Internal.ProtobufList<Order> protobufList = this.orderBy_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.orderBy_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    public static StructuredQuery getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeEndAt(Cursor cursor) {
        cursor.getClass();
        Cursor cursor2 = this.endAt_;
        if (cursor2 == null || cursor2 == Cursor.DEFAULT_INSTANCE) {
            this.endAt_ = cursor;
        } else {
            this.endAt_ = Cursor.newBuilder(cursor2).mergeFrom(cursor).buildPartial();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeLimit(Int32Value int32Value) {
        int32Value.getClass();
        Int32Value int32Value2 = this.limit_;
        if (int32Value2 == null || int32Value2 == Int32Value.DEFAULT_INSTANCE) {
            this.limit_ = int32Value;
        } else {
            this.limit_ = Int32Value.newBuilder(int32Value2).mergeFrom(int32Value).buildPartial();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeSelect(Projection projection) {
        projection.getClass();
        Projection projection2 = this.select_;
        if (projection2 == null || projection2 == Projection.DEFAULT_INSTANCE) {
            this.select_ = projection;
        } else {
            this.select_ = Projection.newBuilder(projection2).mergeFrom(projection).buildPartial();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeStartAt(Cursor cursor) {
        cursor.getClass();
        Cursor cursor2 = this.startAt_;
        if (cursor2 == null || cursor2 == Cursor.DEFAULT_INSTANCE) {
            this.startAt_ = cursor;
        } else {
            this.startAt_ = Cursor.newBuilder(cursor2).mergeFrom(cursor).buildPartial();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeWhere(Filter filter) {
        filter.getClass();
        Filter filter2 = this.where_;
        if (filter2 == null || filter2 == Filter.DEFAULT_INSTANCE) {
            this.where_ = filter;
        } else {
            this.where_ = Filter.newBuilder(filter2).mergeFrom(filter).buildPartial();
        }
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static StructuredQuery parseDelimitedFrom(InputStream inputStream) {
        return (StructuredQuery) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static StructuredQuery parseFrom(ByteBuffer byteBuffer) {
        return (StructuredQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<StructuredQuery> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    private void removeFrom(int i) {
        ensureFromIsMutable();
        this.from_.remove(i);
    }

    private void removeOrderBy(int i) {
        ensureOrderByIsMutable();
        this.orderBy_.remove(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setEndAt(Cursor cursor) {
        cursor.getClass();
        this.endAt_ = cursor;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFrom(int i, CollectionSelector collectionSelector) {
        collectionSelector.getClass();
        ensureFromIsMutable();
        this.from_.set(i, collectionSelector);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLimit(Int32Value int32Value) {
        int32Value.getClass();
        this.limit_ = int32Value;
    }

    private void setOffset(int i) {
        this.offset_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOrderBy(int i, Order order) {
        order.getClass();
        ensureOrderByIsMutable();
        this.orderBy_.set(i, order);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSelect(Projection projection) {
        projection.getClass();
        this.select_ = projection;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStartAt(Cursor cursor) {
        cursor.getClass();
        this.startAt_ = cursor;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setWhere(Filter filter) {
        filter.getClass();
        this.where_ = filter;
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\b\u0000\u0000\u0001\b\b\u0000\u0002\u0000\u0001\t\u0002\u001b\u0003\t\u0004\u001b\u0005\t\u0006\u0004\u0007\t\b\t", new Object[]{"select_", "from_", CollectionSelector.class, "where_", "orderBy_", Order.class, "limit_", "offset_", "startAt_", "endAt_"});
            case NEW_MUTABLE_INSTANCE:
                return new StructuredQuery();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<StructuredQuery> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (StructuredQuery.class) {
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

    @Override // com.google.firestore.v1.StructuredQueryOrBuilder
    public Cursor getEndAt() {
        Cursor cursor = this.endAt_;
        return cursor == null ? Cursor.DEFAULT_INSTANCE : cursor;
    }

    @Override // com.google.firestore.v1.StructuredQueryOrBuilder
    public CollectionSelector getFrom(int i) {
        return this.from_.get(i);
    }

    @Override // com.google.firestore.v1.StructuredQueryOrBuilder
    public int getFromCount() {
        return this.from_.size();
    }

    @Override // com.google.firestore.v1.StructuredQueryOrBuilder
    public List<CollectionSelector> getFromList() {
        return this.from_;
    }

    public CollectionSelectorOrBuilder getFromOrBuilder(int i) {
        return this.from_.get(i);
    }

    public List<? extends CollectionSelectorOrBuilder> getFromOrBuilderList() {
        return this.from_;
    }

    @Override // com.google.firestore.v1.StructuredQueryOrBuilder
    public Int32Value getLimit() {
        Int32Value int32Value = this.limit_;
        return int32Value == null ? Int32Value.DEFAULT_INSTANCE : int32Value;
    }

    @Override // com.google.firestore.v1.StructuredQueryOrBuilder
    public int getOffset() {
        return this.offset_;
    }

    @Override // com.google.firestore.v1.StructuredQueryOrBuilder
    public Order getOrderBy(int i) {
        return this.orderBy_.get(i);
    }

    @Override // com.google.firestore.v1.StructuredQueryOrBuilder
    public int getOrderByCount() {
        return this.orderBy_.size();
    }

    @Override // com.google.firestore.v1.StructuredQueryOrBuilder
    public List<Order> getOrderByList() {
        return this.orderBy_;
    }

    public OrderOrBuilder getOrderByOrBuilder(int i) {
        return this.orderBy_.get(i);
    }

    public List<? extends OrderOrBuilder> getOrderByOrBuilderList() {
        return this.orderBy_;
    }

    @Override // com.google.firestore.v1.StructuredQueryOrBuilder
    public Projection getSelect() {
        Projection projection = this.select_;
        return projection == null ? Projection.DEFAULT_INSTANCE : projection;
    }

    @Override // com.google.firestore.v1.StructuredQueryOrBuilder
    public Cursor getStartAt() {
        Cursor cursor = this.startAt_;
        return cursor == null ? Cursor.DEFAULT_INSTANCE : cursor;
    }

    @Override // com.google.firestore.v1.StructuredQueryOrBuilder
    public Filter getWhere() {
        Filter filter = this.where_;
        return filter == null ? Filter.DEFAULT_INSTANCE : filter;
    }

    @Override // com.google.firestore.v1.StructuredQueryOrBuilder
    public boolean hasEndAt() {
        return this.endAt_ != null;
    }

    @Override // com.google.firestore.v1.StructuredQueryOrBuilder
    public boolean hasLimit() {
        return this.limit_ != null;
    }

    @Override // com.google.firestore.v1.StructuredQueryOrBuilder
    public boolean hasSelect() {
        return this.select_ != null;
    }

    @Override // com.google.firestore.v1.StructuredQueryOrBuilder
    public boolean hasStartAt() {
        return this.startAt_ != null;
    }

    @Override // com.google.firestore.v1.StructuredQueryOrBuilder
    public boolean hasWhere() {
        return this.where_ != null;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<StructuredQuery, Builder> implements StructuredQueryOrBuilder {
        public Builder() {
            super(StructuredQuery.DEFAULT_INSTANCE);
        }

        public Builder addAllFrom(Iterable<? extends CollectionSelector> iterable) {
            copyOnWrite();
            StructuredQuery.access$7800((StructuredQuery) this.instance, iterable);
            return this;
        }

        public Builder addAllOrderBy(Iterable<? extends Order> iterable) {
            copyOnWrite();
            StructuredQuery.access$8700((StructuredQuery) this.instance, iterable);
            return this;
        }

        public Builder addFrom(CollectionSelector collectionSelector) {
            copyOnWrite();
            ((StructuredQuery) this.instance).addFrom(collectionSelector);
            return this;
        }

        public Builder addOrderBy(Order order) {
            copyOnWrite();
            ((StructuredQuery) this.instance).addOrderBy(order);
            return this;
        }

        public Builder clearEndAt() {
            copyOnWrite();
            ((StructuredQuery) this.instance).endAt_ = null;
            return this;
        }

        public Builder clearFrom() {
            copyOnWrite();
            ((StructuredQuery) this.instance).clearFrom();
            return this;
        }

        public Builder clearLimit() {
            copyOnWrite();
            ((StructuredQuery) this.instance).limit_ = null;
            return this;
        }

        public Builder clearOffset() {
            copyOnWrite();
            ((StructuredQuery) this.instance).offset_ = 0;
            return this;
        }

        public Builder clearOrderBy() {
            copyOnWrite();
            ((StructuredQuery) this.instance).clearOrderBy();
            return this;
        }

        public Builder clearSelect() {
            copyOnWrite();
            ((StructuredQuery) this.instance).select_ = null;
            return this;
        }

        public Builder clearStartAt() {
            copyOnWrite();
            ((StructuredQuery) this.instance).startAt_ = null;
            return this;
        }

        public Builder clearWhere() {
            copyOnWrite();
            ((StructuredQuery) this.instance).where_ = null;
            return this;
        }

        @Override // com.google.firestore.v1.StructuredQueryOrBuilder
        public Cursor getEndAt() {
            return ((StructuredQuery) this.instance).getEndAt();
        }

        @Override // com.google.firestore.v1.StructuredQueryOrBuilder
        public CollectionSelector getFrom(int i) {
            return ((StructuredQuery) this.instance).getFrom(i);
        }

        @Override // com.google.firestore.v1.StructuredQueryOrBuilder
        public int getFromCount() {
            return ((StructuredQuery) this.instance).getFromCount();
        }

        @Override // com.google.firestore.v1.StructuredQueryOrBuilder
        public List<CollectionSelector> getFromList() {
            return Collections.unmodifiableList(((StructuredQuery) this.instance).getFromList());
        }

        @Override // com.google.firestore.v1.StructuredQueryOrBuilder
        public Int32Value getLimit() {
            return ((StructuredQuery) this.instance).getLimit();
        }

        @Override // com.google.firestore.v1.StructuredQueryOrBuilder
        public int getOffset() {
            return ((StructuredQuery) this.instance).getOffset();
        }

        @Override // com.google.firestore.v1.StructuredQueryOrBuilder
        public Order getOrderBy(int i) {
            return ((StructuredQuery) this.instance).getOrderBy(i);
        }

        @Override // com.google.firestore.v1.StructuredQueryOrBuilder
        public int getOrderByCount() {
            return ((StructuredQuery) this.instance).getOrderByCount();
        }

        @Override // com.google.firestore.v1.StructuredQueryOrBuilder
        public List<Order> getOrderByList() {
            return Collections.unmodifiableList(((StructuredQuery) this.instance).getOrderByList());
        }

        @Override // com.google.firestore.v1.StructuredQueryOrBuilder
        public Projection getSelect() {
            return ((StructuredQuery) this.instance).getSelect();
        }

        @Override // com.google.firestore.v1.StructuredQueryOrBuilder
        public Cursor getStartAt() {
            return ((StructuredQuery) this.instance).getStartAt();
        }

        @Override // com.google.firestore.v1.StructuredQueryOrBuilder
        public Filter getWhere() {
            return ((StructuredQuery) this.instance).getWhere();
        }

        @Override // com.google.firestore.v1.StructuredQueryOrBuilder
        public boolean hasEndAt() {
            return ((StructuredQuery) this.instance).hasEndAt();
        }

        @Override // com.google.firestore.v1.StructuredQueryOrBuilder
        public boolean hasLimit() {
            return ((StructuredQuery) this.instance).hasLimit();
        }

        @Override // com.google.firestore.v1.StructuredQueryOrBuilder
        public boolean hasSelect() {
            return ((StructuredQuery) this.instance).hasSelect();
        }

        @Override // com.google.firestore.v1.StructuredQueryOrBuilder
        public boolean hasStartAt() {
            return ((StructuredQuery) this.instance).hasStartAt();
        }

        @Override // com.google.firestore.v1.StructuredQueryOrBuilder
        public boolean hasWhere() {
            return ((StructuredQuery) this.instance).hasWhere();
        }

        public Builder mergeEndAt(Cursor cursor) {
            copyOnWrite();
            ((StructuredQuery) this.instance).mergeEndAt(cursor);
            return this;
        }

        public Builder mergeLimit(Int32Value int32Value) {
            copyOnWrite();
            ((StructuredQuery) this.instance).mergeLimit(int32Value);
            return this;
        }

        public Builder mergeSelect(Projection projection) {
            copyOnWrite();
            ((StructuredQuery) this.instance).mergeSelect(projection);
            return this;
        }

        public Builder mergeStartAt(Cursor cursor) {
            copyOnWrite();
            ((StructuredQuery) this.instance).mergeStartAt(cursor);
            return this;
        }

        public Builder mergeWhere(Filter filter) {
            copyOnWrite();
            ((StructuredQuery) this.instance).mergeWhere(filter);
            return this;
        }

        public Builder removeFrom(int i) {
            copyOnWrite();
            StructuredQuery.access$8000((StructuredQuery) this.instance, i);
            return this;
        }

        public Builder removeOrderBy(int i) {
            copyOnWrite();
            StructuredQuery.access$8900((StructuredQuery) this.instance, i);
            return this;
        }

        public Builder setEndAt(Cursor cursor) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setEndAt(cursor);
            return this;
        }

        public Builder setFrom(int i, CollectionSelector collectionSelector) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setFrom(i, collectionSelector);
            return this;
        }

        public Builder setLimit(Int32Value int32Value) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setLimit(int32Value);
            return this;
        }

        public Builder setOffset(int i) {
            copyOnWrite();
            ((StructuredQuery) this.instance).offset_ = i;
            return this;
        }

        public Builder setOrderBy(int i, Order order) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setOrderBy(i, order);
            return this;
        }

        public Builder setSelect(Projection projection) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setSelect(projection);
            return this;
        }

        public Builder setStartAt(Cursor cursor) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setStartAt(cursor);
            return this;
        }

        public Builder setWhere(Filter filter) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setWhere(filter);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(StructuredQuery.DEFAULT_INSTANCE);
        }

        public Builder addFrom(int i, CollectionSelector collectionSelector) {
            copyOnWrite();
            ((StructuredQuery) this.instance).addFrom(i, collectionSelector);
            return this;
        }

        public Builder addOrderBy(int i, Order order) {
            copyOnWrite();
            ((StructuredQuery) this.instance).addOrderBy(i, order);
            return this;
        }

        public Builder setEndAt(Cursor.Builder builder) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setEndAt(builder.build());
            return this;
        }

        public Builder setFrom(int i, CollectionSelector.Builder builder) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setFrom(i, builder.build());
            return this;
        }

        public Builder setLimit(Int32Value.Builder builder) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setLimit(builder.build());
            return this;
        }

        public Builder setOrderBy(int i, Order.Builder builder) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setOrderBy(i, builder.build());
            return this;
        }

        public Builder setSelect(Projection.Builder builder) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setSelect(builder.build());
            return this;
        }

        public Builder setStartAt(Cursor.Builder builder) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setStartAt(builder.build());
            return this;
        }

        public Builder setWhere(Filter.Builder builder) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setWhere(builder.build());
            return this;
        }

        public Builder addFrom(CollectionSelector.Builder builder) {
            copyOnWrite();
            ((StructuredQuery) this.instance).addFrom(builder.build());
            return this;
        }

        public Builder addOrderBy(Order.Builder builder) {
            copyOnWrite();
            ((StructuredQuery) this.instance).addOrderBy(builder.build());
            return this;
        }

        public Builder addFrom(int i, CollectionSelector.Builder builder) {
            copyOnWrite();
            ((StructuredQuery) this.instance).addFrom(i, builder.build());
            return this;
        }

        public Builder addOrderBy(int i, Order.Builder builder) {
            copyOnWrite();
            ((StructuredQuery) this.instance).addOrderBy(i, builder.build());
            return this;
        }
    }

    public static Builder newBuilder(StructuredQuery structuredQuery) {
        return DEFAULT_INSTANCE.createBuilder(structuredQuery);
    }

    public static StructuredQuery parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (StructuredQuery) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static StructuredQuery parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (StructuredQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static StructuredQuery parseFrom(ByteString byteString) {
        return (StructuredQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addFrom(int i, CollectionSelector collectionSelector) {
        collectionSelector.getClass();
        ensureFromIsMutable();
        this.from_.add(i, collectionSelector);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addOrderBy(int i, Order order) {
        order.getClass();
        ensureOrderByIsMutable();
        this.orderBy_.add(i, order);
    }

    public static StructuredQuery parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (StructuredQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static StructuredQuery parseFrom(byte[] bArr) {
        return (StructuredQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static StructuredQuery parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (StructuredQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static StructuredQuery parseFrom(InputStream inputStream) {
        return (StructuredQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static StructuredQuery parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (StructuredQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static StructuredQuery parseFrom(CodedInputStream codedInputStream) {
        return (StructuredQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static StructuredQuery parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (StructuredQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
