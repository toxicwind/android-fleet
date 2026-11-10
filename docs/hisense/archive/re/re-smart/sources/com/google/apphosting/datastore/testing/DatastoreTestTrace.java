package com.google.apphosting.datastore.testing;

import com.google.firestore.v1.BatchGetDocumentsRequest;
import com.google.firestore.v1.BatchGetDocumentsResponse;
import com.google.firestore.v1.BatchGetDocumentsResponseOrBuilder;
import com.google.firestore.v1.BeginTransactionRequest;
import com.google.firestore.v1.BeginTransactionResponse;
import com.google.firestore.v1.CommitRequest;
import com.google.firestore.v1.CommitResponse;
import com.google.firestore.v1.CreateDocumentRequest;
import com.google.firestore.v1.DeleteDocumentRequest;
import com.google.firestore.v1.Document;
import com.google.firestore.v1.GetDocumentRequest;
import com.google.firestore.v1.ListCollectionIdsRequest;
import com.google.firestore.v1.ListCollectionIdsResponse;
import com.google.firestore.v1.ListDocumentsRequest;
import com.google.firestore.v1.ListDocumentsResponse;
import com.google.firestore.v1.ListenRequest;
import com.google.firestore.v1.ListenResponse;
import com.google.firestore.v1.RollbackRequest;
import com.google.firestore.v1.RunQueryRequest;
import com.google.firestore.v1.RunQueryResponse;
import com.google.firestore.v1.RunQueryResponseOrBuilder;
import com.google.firestore.v1.UpdateDocumentRequest;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.Empty;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class DatastoreTestTrace {

    /* JADX INFO: renamed from: com.google.apphosting.datastore.testing.DatastoreTestTrace$1, reason: invalid class name */
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

    public static final class DatastoreAction extends GeneratedMessageLite<DatastoreAction, Builder> implements DatastoreActionOrBuilder {
        public static final int ACTION_ID_FIELD_NUMBER = 200;
        public static final DatastoreAction DEFAULT_INSTANCE;
        public static final int FIRESTORE_V1_ACTION_FIELD_NUMBER = 3;
        public static volatile Parser<DatastoreAction> PARSER = null;
        public static final int VALIDATION_RULE_FIELD_NUMBER = 201;
        public int actionCase_ = 0;
        public int actionId_;
        public Object action_;
        public ValidationRule validationRule_;

        public enum ActionCase {
            FIRESTORE_V1_ACTION(3),
            ACTION_NOT_SET(0);

            public final int value;

            ActionCase(int i) {
                this.value = i;
            }

            public static ActionCase forNumber(int i) {
                if (i == 0) {
                    return ACTION_NOT_SET;
                }
                if (i != 3) {
                    return null;
                }
                return FIRESTORE_V1_ACTION;
            }

            public int getNumber() {
                return this.value;
            }

            @Deprecated
            public static ActionCase valueOf(int i) {
                return forNumber(i);
            }
        }

        static {
            DatastoreAction datastoreAction = new DatastoreAction();
            DEFAULT_INSTANCE = datastoreAction;
            GeneratedMessageLite.defaultInstanceMap.put(DatastoreAction.class, datastoreAction);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAction() {
            this.actionCase_ = 0;
            this.action_ = null;
        }

        private void clearActionId() {
            this.actionId_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearFirestoreV1Action() {
            if (this.actionCase_ == 3) {
                this.actionCase_ = 0;
                this.action_ = null;
            }
        }

        private void clearValidationRule() {
            this.validationRule_ = null;
        }

        public static DatastoreAction getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeFirestoreV1Action(FirestoreV1Action firestoreV1Action) {
            Object obj;
            firestoreV1Action.getClass();
            if (this.actionCase_ != 3 || (obj = this.action_) == FirestoreV1Action.DEFAULT_INSTANCE) {
                this.action_ = firestoreV1Action;
            } else {
                this.action_ = FirestoreV1Action.newBuilder((FirestoreV1Action) obj).mergeFrom(firestoreV1Action).buildPartial();
            }
            this.actionCase_ = 3;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeValidationRule(ValidationRule validationRule) {
            validationRule.getClass();
            ValidationRule validationRule2 = this.validationRule_;
            if (validationRule2 == null || validationRule2 == ValidationRule.DEFAULT_INSTANCE) {
                this.validationRule_ = validationRule;
            } else {
                this.validationRule_ = ValidationRule.newBuilder(validationRule2).mergeFrom(validationRule).buildPartial();
            }
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static DatastoreAction parseDelimitedFrom(InputStream inputStream) {
            return (DatastoreAction) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static DatastoreAction parseFrom(ByteBuffer byteBuffer) {
            return (DatastoreAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<DatastoreAction> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        private void setActionId(int i) {
            this.actionId_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFirestoreV1Action(FirestoreV1Action firestoreV1Action) {
            firestoreV1Action.getClass();
            this.action_ = firestoreV1Action;
            this.actionCase_ = 3;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setValidationRule(ValidationRule validationRule) {
            validationRule.getClass();
            this.validationRule_ = validationRule;
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
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0001\u0000\u0003É\u0003\u0000\u0000\u0000\u0003<\u0000È\u0004É\t", new Object[]{"action_", "actionCase_", FirestoreV1Action.class, "actionId_", "validationRule_"});
                case NEW_MUTABLE_INSTANCE:
                    return new DatastoreAction();
                case NEW_BUILDER:
                    return new Builder(anonymousClass1);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser<DatastoreAction> defaultInstanceBasedParser = PARSER;
                    if (defaultInstanceBasedParser == null) {
                        synchronized (DatastoreAction.class) {
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

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.DatastoreActionOrBuilder
        public ActionCase getActionCase() {
            return ActionCase.forNumber(this.actionCase_);
        }

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.DatastoreActionOrBuilder
        public int getActionId() {
            return this.actionId_;
        }

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.DatastoreActionOrBuilder
        public FirestoreV1Action getFirestoreV1Action() {
            return this.actionCase_ == 3 ? (FirestoreV1Action) this.action_ : FirestoreV1Action.DEFAULT_INSTANCE;
        }

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.DatastoreActionOrBuilder
        public ValidationRule getValidationRule() {
            ValidationRule validationRule = this.validationRule_;
            return validationRule == null ? ValidationRule.DEFAULT_INSTANCE : validationRule;
        }

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.DatastoreActionOrBuilder
        public boolean hasFirestoreV1Action() {
            return this.actionCase_ == 3;
        }

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.DatastoreActionOrBuilder
        public boolean hasValidationRule() {
            return this.validationRule_ != null;
        }

        public static final class Builder extends GeneratedMessageLite.Builder<DatastoreAction, Builder> implements DatastoreActionOrBuilder {
            public Builder() {
                super(DatastoreAction.DEFAULT_INSTANCE);
            }

            public Builder clearAction() {
                copyOnWrite();
                ((DatastoreAction) this.instance).clearAction();
                return this;
            }

            public Builder clearActionId() {
                copyOnWrite();
                ((DatastoreAction) this.instance).actionId_ = 0;
                return this;
            }

            public Builder clearFirestoreV1Action() {
                copyOnWrite();
                ((DatastoreAction) this.instance).clearFirestoreV1Action();
                return this;
            }

            public Builder clearValidationRule() {
                copyOnWrite();
                ((DatastoreAction) this.instance).validationRule_ = null;
                return this;
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.DatastoreActionOrBuilder
            public ActionCase getActionCase() {
                return ((DatastoreAction) this.instance).getActionCase();
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.DatastoreActionOrBuilder
            public int getActionId() {
                return ((DatastoreAction) this.instance).getActionId();
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.DatastoreActionOrBuilder
            public FirestoreV1Action getFirestoreV1Action() {
                return ((DatastoreAction) this.instance).getFirestoreV1Action();
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.DatastoreActionOrBuilder
            public ValidationRule getValidationRule() {
                return ((DatastoreAction) this.instance).getValidationRule();
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.DatastoreActionOrBuilder
            public boolean hasFirestoreV1Action() {
                return ((DatastoreAction) this.instance).hasFirestoreV1Action();
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.DatastoreActionOrBuilder
            public boolean hasValidationRule() {
                return ((DatastoreAction) this.instance).hasValidationRule();
            }

            public Builder mergeFirestoreV1Action(FirestoreV1Action firestoreV1Action) {
                copyOnWrite();
                ((DatastoreAction) this.instance).mergeFirestoreV1Action(firestoreV1Action);
                return this;
            }

            public Builder mergeValidationRule(ValidationRule validationRule) {
                copyOnWrite();
                ((DatastoreAction) this.instance).mergeValidationRule(validationRule);
                return this;
            }

            public Builder setActionId(int i) {
                copyOnWrite();
                ((DatastoreAction) this.instance).actionId_ = i;
                return this;
            }

            public Builder setFirestoreV1Action(FirestoreV1Action firestoreV1Action) {
                copyOnWrite();
                ((DatastoreAction) this.instance).setFirestoreV1Action(firestoreV1Action);
                return this;
            }

            public Builder setValidationRule(ValidationRule validationRule) {
                copyOnWrite();
                ((DatastoreAction) this.instance).setValidationRule(validationRule);
                return this;
            }

            public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                super(DatastoreAction.DEFAULT_INSTANCE);
            }

            public Builder setFirestoreV1Action(FirestoreV1Action.Builder builder) {
                copyOnWrite();
                ((DatastoreAction) this.instance).setFirestoreV1Action(builder.build());
                return this;
            }

            public Builder setValidationRule(ValidationRule.Builder builder) {
                copyOnWrite();
                ((DatastoreAction) this.instance).setValidationRule(builder.build());
                return this;
            }
        }

        public static Builder newBuilder(DatastoreAction datastoreAction) {
            return DEFAULT_INSTANCE.createBuilder(datastoreAction);
        }

        public static DatastoreAction parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (DatastoreAction) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static DatastoreAction parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
            return (DatastoreAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static DatastoreAction parseFrom(ByteString byteString) {
            return (DatastoreAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static DatastoreAction parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (DatastoreAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static DatastoreAction parseFrom(byte[] bArr) {
            return (DatastoreAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static DatastoreAction parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (DatastoreAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static DatastoreAction parseFrom(InputStream inputStream) {
            return (DatastoreAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static DatastoreAction parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (DatastoreAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static DatastoreAction parseFrom(CodedInputStream codedInputStream) {
            return (DatastoreAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static DatastoreAction parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (DatastoreAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    public interface DatastoreActionOrBuilder extends MessageLiteOrBuilder {
        DatastoreAction.ActionCase getActionCase();

        int getActionId();

        FirestoreV1Action getFirestoreV1Action();

        ValidationRule getValidationRule();

        boolean hasFirestoreV1Action();

        boolean hasValidationRule();
    }

    public static final class FirestoreV1Action extends GeneratedMessageLite<FirestoreV1Action, Builder> implements FirestoreV1ActionOrBuilder {
        public static final int BATCH_GET_DOCUMENTS_FIELD_NUMBER = 10;
        public static final int BEGIN_TRANSACTION_FIELD_NUMBER = 6;
        public static final int COMMIT_FIELD_NUMBER = 7;
        public static final int CREATE_DOCUMENT_FIELD_NUMBER = 3;
        public static final int DATABASE_CONTENTS_BEFORE_ACTION_FIELD_NUMBER = 202;
        public static final FirestoreV1Action DEFAULT_INSTANCE;
        public static final int DELETE_DOCUMENT_FIELD_NUMBER = 5;
        public static final int GET_DOCUMENT_FIELD_NUMBER = 1;
        public static final int LISTEN_FIELD_NUMBER = 12;
        public static final int LIST_COLLECTION_IDS_FIELD_NUMBER = 9;
        public static final int LIST_DOCUMENTS_FIELD_NUMBER = 2;
        public static final int MATCHING_DOCUMENTS_FIELD_NUMBER = 203;
        public static volatile Parser<FirestoreV1Action> PARSER = null;
        public static final int REMOVE_LISTEN_FIELD_NUMBER = 13;
        public static final int ROLLBACK_FIELD_NUMBER = 8;
        public static final int RUN_QUERY_FIELD_NUMBER = 11;
        public static final int STATUS_FIELD_NUMBER = 201;
        public static final int UPDATE_DOCUMENT_FIELD_NUMBER = 4;
        public Object action_;
        public RunQuery databaseContentsBeforeAction_;
        public StatusProto status_;
        public int actionCase_ = 0;
        public Internal.ProtobufList<MatchingDocuments> matchingDocuments_ = GeneratedMessageLite.emptyProtobufList();

        public enum ActionCase {
            GET_DOCUMENT(1),
            LIST_DOCUMENTS(2),
            CREATE_DOCUMENT(3),
            UPDATE_DOCUMENT(4),
            DELETE_DOCUMENT(5),
            BEGIN_TRANSACTION(6),
            COMMIT(7),
            ROLLBACK(8),
            LIST_COLLECTION_IDS(9),
            BATCH_GET_DOCUMENTS(10),
            RUN_QUERY(11),
            LISTEN(12),
            REMOVE_LISTEN(13),
            ACTION_NOT_SET(0);

            public final int value;

            ActionCase(int i) {
                this.value = i;
            }

            public static ActionCase forNumber(int i) {
                switch (i) {
                    case 0:
                        return ACTION_NOT_SET;
                    case 1:
                        return GET_DOCUMENT;
                    case 2:
                        return LIST_DOCUMENTS;
                    case 3:
                        return CREATE_DOCUMENT;
                    case 4:
                        return UPDATE_DOCUMENT;
                    case 5:
                        return DELETE_DOCUMENT;
                    case 6:
                        return BEGIN_TRANSACTION;
                    case 7:
                        return COMMIT;
                    case 8:
                        return ROLLBACK;
                    case 9:
                        return LIST_COLLECTION_IDS;
                    case 10:
                        return BATCH_GET_DOCUMENTS;
                    case 11:
                        return RUN_QUERY;
                    case 12:
                        return LISTEN;
                    case 13:
                        return REMOVE_LISTEN;
                    default:
                        return null;
                }
            }

            public int getNumber() {
                return this.value;
            }

            @Deprecated
            public static ActionCase valueOf(int i) {
                return forNumber(i);
            }
        }

        public static final class BatchGetDocuments extends GeneratedMessageLite<BatchGetDocuments, Builder> implements BatchGetDocumentsOrBuilder {
            public static final BatchGetDocuments DEFAULT_INSTANCE;
            public static volatile Parser<BatchGetDocuments> PARSER = null;
            public static final int REQUEST_FIELD_NUMBER = 1;
            public static final int RESPONSE_FIELD_NUMBER = 2;
            public BatchGetDocumentsRequest request_;
            public Internal.ProtobufList<BatchGetDocumentsResponse> response_ = GeneratedMessageLite.emptyProtobufList();

            static {
                BatchGetDocuments batchGetDocuments = new BatchGetDocuments();
                DEFAULT_INSTANCE = batchGetDocuments;
                GeneratedMessageLite.defaultInstanceMap.put(BatchGetDocuments.class, batchGetDocuments);
            }

            public static /* synthetic */ void access$9700(BatchGetDocuments batchGetDocuments, Iterable iterable) {
                batchGetDocuments.ensureResponseIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, (List) batchGetDocuments.response_);
            }

            public static /* synthetic */ void access$9900(BatchGetDocuments batchGetDocuments, int i) {
                batchGetDocuments.ensureResponseIsMutable();
                batchGetDocuments.response_.remove(i);
            }

            private void addAllResponse(Iterable<? extends BatchGetDocumentsResponse> iterable) {
                ensureResponseIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.response_);
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void addResponse(BatchGetDocumentsResponse batchGetDocumentsResponse) {
                batchGetDocumentsResponse.getClass();
                ensureResponseIsMutable();
                this.response_.add(batchGetDocumentsResponse);
            }

            private void clearRequest() {
                this.request_ = null;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void clearResponse() {
                this.response_ = GeneratedMessageLite.emptyProtobufList();
            }

            private void ensureResponseIsMutable() {
                Internal.ProtobufList<BatchGetDocumentsResponse> protobufList = this.response_;
                if (protobufList.isModifiable()) {
                    return;
                }
                this.response_ = GeneratedMessageLite.mutableCopy(protobufList);
            }

            public static BatchGetDocuments getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void mergeRequest(BatchGetDocumentsRequest batchGetDocumentsRequest) {
                batchGetDocumentsRequest.getClass();
                BatchGetDocumentsRequest batchGetDocumentsRequest2 = this.request_;
                if (batchGetDocumentsRequest2 == null || batchGetDocumentsRequest2 == BatchGetDocumentsRequest.DEFAULT_INSTANCE) {
                    this.request_ = batchGetDocumentsRequest;
                } else {
                    this.request_ = BatchGetDocumentsRequest.newBuilder(batchGetDocumentsRequest2).mergeFrom(batchGetDocumentsRequest).buildPartial();
                }
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.createBuilder();
            }

            public static BatchGetDocuments parseDelimitedFrom(InputStream inputStream) {
                return (BatchGetDocuments) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static BatchGetDocuments parseFrom(ByteBuffer byteBuffer) {
                return (BatchGetDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
            }

            public static Parser<BatchGetDocuments> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }

            private void removeResponse(int i) {
                ensureResponseIsMutable();
                this.response_.remove(i);
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setRequest(BatchGetDocumentsRequest batchGetDocumentsRequest) {
                batchGetDocumentsRequest.getClass();
                this.request_ = batchGetDocumentsRequest;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setResponse(int i, BatchGetDocumentsResponse batchGetDocumentsResponse) {
                batchGetDocumentsResponse.getClass();
                ensureResponseIsMutable();
                this.response_.set(i, batchGetDocumentsResponse);
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
                        return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\t\u0002\u001b", new Object[]{"request_", "response_", BatchGetDocumentsResponse.class});
                    case NEW_MUTABLE_INSTANCE:
                        return new BatchGetDocuments();
                    case NEW_BUILDER:
                        return new Builder(anonymousClass1);
                    case GET_DEFAULT_INSTANCE:
                        return DEFAULT_INSTANCE;
                    case GET_PARSER:
                        Parser<BatchGetDocuments> defaultInstanceBasedParser = PARSER;
                        if (defaultInstanceBasedParser == null) {
                            synchronized (BatchGetDocuments.class) {
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

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.BatchGetDocumentsOrBuilder
            public BatchGetDocumentsRequest getRequest() {
                BatchGetDocumentsRequest batchGetDocumentsRequest = this.request_;
                return batchGetDocumentsRequest == null ? BatchGetDocumentsRequest.DEFAULT_INSTANCE : batchGetDocumentsRequest;
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.BatchGetDocumentsOrBuilder
            public BatchGetDocumentsResponse getResponse(int i) {
                return this.response_.get(i);
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.BatchGetDocumentsOrBuilder
            public int getResponseCount() {
                return this.response_.size();
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.BatchGetDocumentsOrBuilder
            public List<BatchGetDocumentsResponse> getResponseList() {
                return this.response_;
            }

            public BatchGetDocumentsResponseOrBuilder getResponseOrBuilder(int i) {
                return this.response_.get(i);
            }

            public List<? extends BatchGetDocumentsResponseOrBuilder> getResponseOrBuilderList() {
                return this.response_;
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.BatchGetDocumentsOrBuilder
            public boolean hasRequest() {
                return this.request_ != null;
            }

            public static final class Builder extends GeneratedMessageLite.Builder<BatchGetDocuments, Builder> implements BatchGetDocumentsOrBuilder {
                public Builder() {
                    super(BatchGetDocuments.DEFAULT_INSTANCE);
                }

                public Builder addAllResponse(Iterable<? extends BatchGetDocumentsResponse> iterable) {
                    copyOnWrite();
                    BatchGetDocuments.access$9700((BatchGetDocuments) this.instance, iterable);
                    return this;
                }

                public Builder addResponse(BatchGetDocumentsResponse batchGetDocumentsResponse) {
                    copyOnWrite();
                    ((BatchGetDocuments) this.instance).addResponse(batchGetDocumentsResponse);
                    return this;
                }

                public Builder clearRequest() {
                    copyOnWrite();
                    ((BatchGetDocuments) this.instance).request_ = null;
                    return this;
                }

                public Builder clearResponse() {
                    copyOnWrite();
                    ((BatchGetDocuments) this.instance).clearResponse();
                    return this;
                }

                @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.BatchGetDocumentsOrBuilder
                public BatchGetDocumentsRequest getRequest() {
                    return ((BatchGetDocuments) this.instance).getRequest();
                }

                @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.BatchGetDocumentsOrBuilder
                public BatchGetDocumentsResponse getResponse(int i) {
                    return ((BatchGetDocuments) this.instance).getResponse(i);
                }

                @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.BatchGetDocumentsOrBuilder
                public int getResponseCount() {
                    return ((BatchGetDocuments) this.instance).getResponseCount();
                }

                @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.BatchGetDocumentsOrBuilder
                public List<BatchGetDocumentsResponse> getResponseList() {
                    return Collections.unmodifiableList(((BatchGetDocuments) this.instance).getResponseList());
                }

                @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.BatchGetDocumentsOrBuilder
                public boolean hasRequest() {
                    return ((BatchGetDocuments) this.instance).hasRequest();
                }

                public Builder mergeRequest(BatchGetDocumentsRequest batchGetDocumentsRequest) {
                    copyOnWrite();
                    ((BatchGetDocuments) this.instance).mergeRequest(batchGetDocumentsRequest);
                    return this;
                }

                public Builder removeResponse(int i) {
                    copyOnWrite();
                    BatchGetDocuments.access$9900((BatchGetDocuments) this.instance, i);
                    return this;
                }

                public Builder setRequest(BatchGetDocumentsRequest batchGetDocumentsRequest) {
                    copyOnWrite();
                    ((BatchGetDocuments) this.instance).setRequest(batchGetDocumentsRequest);
                    return this;
                }

                public Builder setResponse(int i, BatchGetDocumentsResponse batchGetDocumentsResponse) {
                    copyOnWrite();
                    ((BatchGetDocuments) this.instance).setResponse(i, batchGetDocumentsResponse);
                    return this;
                }

                public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                    super(BatchGetDocuments.DEFAULT_INSTANCE);
                }

                public Builder addResponse(int i, BatchGetDocumentsResponse batchGetDocumentsResponse) {
                    copyOnWrite();
                    ((BatchGetDocuments) this.instance).addResponse(i, batchGetDocumentsResponse);
                    return this;
                }

                public Builder setRequest(BatchGetDocumentsRequest.Builder builder) {
                    copyOnWrite();
                    ((BatchGetDocuments) this.instance).setRequest(builder.build());
                    return this;
                }

                public Builder setResponse(int i, BatchGetDocumentsResponse.Builder builder) {
                    copyOnWrite();
                    ((BatchGetDocuments) this.instance).setResponse(i, builder.build());
                    return this;
                }

                public Builder addResponse(BatchGetDocumentsResponse.Builder builder) {
                    copyOnWrite();
                    ((BatchGetDocuments) this.instance).addResponse(builder.build());
                    return this;
                }

                public Builder addResponse(int i, BatchGetDocumentsResponse.Builder builder) {
                    copyOnWrite();
                    ((BatchGetDocuments) this.instance).addResponse(i, builder.build());
                    return this;
                }
            }

            public static Builder newBuilder(BatchGetDocuments batchGetDocuments) {
                return DEFAULT_INSTANCE.createBuilder(batchGetDocuments);
            }

            public static BatchGetDocuments parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (BatchGetDocuments) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static BatchGetDocuments parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
                return (BatchGetDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
            }

            public static BatchGetDocuments parseFrom(ByteString byteString) {
                return (BatchGetDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void addResponse(int i, BatchGetDocumentsResponse batchGetDocumentsResponse) {
                batchGetDocumentsResponse.getClass();
                ensureResponseIsMutable();
                this.response_.add(i, batchGetDocumentsResponse);
            }

            public static BatchGetDocuments parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
                return (BatchGetDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
            }

            public static BatchGetDocuments parseFrom(byte[] bArr) {
                return (BatchGetDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
            }

            public static BatchGetDocuments parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
                return (BatchGetDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
            }

            public static BatchGetDocuments parseFrom(InputStream inputStream) {
                return (BatchGetDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static BatchGetDocuments parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (BatchGetDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static BatchGetDocuments parseFrom(CodedInputStream codedInputStream) {
                return (BatchGetDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
            }

            public static BatchGetDocuments parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (BatchGetDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
            }
        }

        public interface BatchGetDocumentsOrBuilder extends MessageLiteOrBuilder {
            BatchGetDocumentsRequest getRequest();

            BatchGetDocumentsResponse getResponse(int i);

            int getResponseCount();

            List<BatchGetDocumentsResponse> getResponseList();

            boolean hasRequest();
        }

        public static final class BeginTransaction extends GeneratedMessageLite<BeginTransaction, Builder> implements BeginTransactionOrBuilder {
            public static final BeginTransaction DEFAULT_INSTANCE;
            public static volatile Parser<BeginTransaction> PARSER = null;
            public static final int REQUEST_FIELD_NUMBER = 1;
            public static final int RESPONSE_FIELD_NUMBER = 2;
            public BeginTransactionRequest request_;
            public BeginTransactionResponse response_;

            static {
                BeginTransaction beginTransaction = new BeginTransaction();
                DEFAULT_INSTANCE = beginTransaction;
                GeneratedMessageLite.defaultInstanceMap.put(BeginTransaction.class, beginTransaction);
            }

            private void clearRequest() {
                this.request_ = null;
            }

            private void clearResponse() {
                this.response_ = null;
            }

            public static BeginTransaction getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void mergeRequest(BeginTransactionRequest beginTransactionRequest) {
                beginTransactionRequest.getClass();
                BeginTransactionRequest beginTransactionRequest2 = this.request_;
                if (beginTransactionRequest2 == null || beginTransactionRequest2 == BeginTransactionRequest.DEFAULT_INSTANCE) {
                    this.request_ = beginTransactionRequest;
                } else {
                    this.request_ = BeginTransactionRequest.newBuilder(beginTransactionRequest2).mergeFrom(beginTransactionRequest).buildPartial();
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void mergeResponse(BeginTransactionResponse beginTransactionResponse) {
                beginTransactionResponse.getClass();
                BeginTransactionResponse beginTransactionResponse2 = this.response_;
                if (beginTransactionResponse2 == null || beginTransactionResponse2 == BeginTransactionResponse.DEFAULT_INSTANCE) {
                    this.response_ = beginTransactionResponse;
                } else {
                    this.response_ = BeginTransactionResponse.newBuilder(beginTransactionResponse2).mergeFrom(beginTransactionResponse).buildPartial();
                }
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.createBuilder();
            }

            public static BeginTransaction parseDelimitedFrom(InputStream inputStream) {
                return (BeginTransaction) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static BeginTransaction parseFrom(ByteBuffer byteBuffer) {
                return (BeginTransaction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
            }

            public static Parser<BeginTransaction> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setRequest(BeginTransactionRequest beginTransactionRequest) {
                beginTransactionRequest.getClass();
                this.request_ = beginTransactionRequest;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setResponse(BeginTransactionResponse beginTransactionResponse) {
                beginTransactionResponse.getClass();
                this.response_ = beginTransactionResponse;
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
                        return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\t", new Object[]{"request_", "response_"});
                    case NEW_MUTABLE_INSTANCE:
                        return new BeginTransaction();
                    case NEW_BUILDER:
                        return new Builder(anonymousClass1);
                    case GET_DEFAULT_INSTANCE:
                        return DEFAULT_INSTANCE;
                    case GET_PARSER:
                        Parser<BeginTransaction> defaultInstanceBasedParser = PARSER;
                        if (defaultInstanceBasedParser == null) {
                            synchronized (BeginTransaction.class) {
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

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.BeginTransactionOrBuilder
            public BeginTransactionRequest getRequest() {
                BeginTransactionRequest beginTransactionRequest = this.request_;
                return beginTransactionRequest == null ? BeginTransactionRequest.DEFAULT_INSTANCE : beginTransactionRequest;
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.BeginTransactionOrBuilder
            public BeginTransactionResponse getResponse() {
                BeginTransactionResponse beginTransactionResponse = this.response_;
                return beginTransactionResponse == null ? BeginTransactionResponse.DEFAULT_INSTANCE : beginTransactionResponse;
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.BeginTransactionOrBuilder
            public boolean hasRequest() {
                return this.request_ != null;
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.BeginTransactionOrBuilder
            public boolean hasResponse() {
                return this.response_ != null;
            }

            public static final class Builder extends GeneratedMessageLite.Builder<BeginTransaction, Builder> implements BeginTransactionOrBuilder {
                public Builder() {
                    super(BeginTransaction.DEFAULT_INSTANCE);
                }

                public Builder clearRequest() {
                    copyOnWrite();
                    ((BeginTransaction) this.instance).request_ = null;
                    return this;
                }

                public Builder clearResponse() {
                    copyOnWrite();
                    ((BeginTransaction) this.instance).response_ = null;
                    return this;
                }

                @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.BeginTransactionOrBuilder
                public BeginTransactionRequest getRequest() {
                    return ((BeginTransaction) this.instance).getRequest();
                }

                @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.BeginTransactionOrBuilder
                public BeginTransactionResponse getResponse() {
                    return ((BeginTransaction) this.instance).getResponse();
                }

                @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.BeginTransactionOrBuilder
                public boolean hasRequest() {
                    return ((BeginTransaction) this.instance).hasRequest();
                }

                @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.BeginTransactionOrBuilder
                public boolean hasResponse() {
                    return ((BeginTransaction) this.instance).hasResponse();
                }

                public Builder mergeRequest(BeginTransactionRequest beginTransactionRequest) {
                    copyOnWrite();
                    ((BeginTransaction) this.instance).mergeRequest(beginTransactionRequest);
                    return this;
                }

                public Builder mergeResponse(BeginTransactionResponse beginTransactionResponse) {
                    copyOnWrite();
                    ((BeginTransaction) this.instance).mergeResponse(beginTransactionResponse);
                    return this;
                }

                public Builder setRequest(BeginTransactionRequest beginTransactionRequest) {
                    copyOnWrite();
                    ((BeginTransaction) this.instance).setRequest(beginTransactionRequest);
                    return this;
                }

                public Builder setResponse(BeginTransactionResponse beginTransactionResponse) {
                    copyOnWrite();
                    ((BeginTransaction) this.instance).setResponse(beginTransactionResponse);
                    return this;
                }

                public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                    super(BeginTransaction.DEFAULT_INSTANCE);
                }

                public Builder setRequest(BeginTransactionRequest.Builder builder) {
                    copyOnWrite();
                    ((BeginTransaction) this.instance).setRequest(builder.build());
                    return this;
                }

                public Builder setResponse(BeginTransactionResponse.Builder builder) {
                    copyOnWrite();
                    ((BeginTransaction) this.instance).setResponse(builder.build());
                    return this;
                }
            }

            public static Builder newBuilder(BeginTransaction beginTransaction) {
                return DEFAULT_INSTANCE.createBuilder(beginTransaction);
            }

            public static BeginTransaction parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (BeginTransaction) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static BeginTransaction parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
                return (BeginTransaction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
            }

            public static BeginTransaction parseFrom(ByteString byteString) {
                return (BeginTransaction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
            }

            public static BeginTransaction parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
                return (BeginTransaction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
            }

            public static BeginTransaction parseFrom(byte[] bArr) {
                return (BeginTransaction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
            }

            public static BeginTransaction parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
                return (BeginTransaction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
            }

            public static BeginTransaction parseFrom(InputStream inputStream) {
                return (BeginTransaction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static BeginTransaction parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (BeginTransaction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static BeginTransaction parseFrom(CodedInputStream codedInputStream) {
                return (BeginTransaction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
            }

            public static BeginTransaction parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (BeginTransaction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
            }
        }

        public interface BeginTransactionOrBuilder extends MessageLiteOrBuilder {
            BeginTransactionRequest getRequest();

            BeginTransactionResponse getResponse();

            boolean hasRequest();

            boolean hasResponse();
        }

        public static final class Commit extends GeneratedMessageLite<Commit, Builder> implements CommitOrBuilder {
            public static final Commit DEFAULT_INSTANCE;
            public static volatile Parser<Commit> PARSER = null;
            public static final int REQUEST_FIELD_NUMBER = 1;
            public static final int RESPONSE_FIELD_NUMBER = 2;
            public CommitRequest request_;
            public CommitResponse response_;

            static {
                Commit commit = new Commit();
                DEFAULT_INSTANCE = commit;
                GeneratedMessageLite.defaultInstanceMap.put(Commit.class, commit);
            }

            private void clearRequest() {
                this.request_ = null;
            }

            private void clearResponse() {
                this.response_ = null;
            }

            public static Commit getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void mergeRequest(CommitRequest commitRequest) {
                commitRequest.getClass();
                CommitRequest commitRequest2 = this.request_;
                if (commitRequest2 == null || commitRequest2 == CommitRequest.DEFAULT_INSTANCE) {
                    this.request_ = commitRequest;
                } else {
                    this.request_ = CommitRequest.newBuilder(commitRequest2).mergeFrom(commitRequest).buildPartial();
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void mergeResponse(CommitResponse commitResponse) {
                commitResponse.getClass();
                CommitResponse commitResponse2 = this.response_;
                if (commitResponse2 == null || commitResponse2 == CommitResponse.DEFAULT_INSTANCE) {
                    this.response_ = commitResponse;
                } else {
                    this.response_ = CommitResponse.newBuilder(commitResponse2).mergeFrom(commitResponse).buildPartial();
                }
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.createBuilder();
            }

            public static Commit parseDelimitedFrom(InputStream inputStream) {
                return (Commit) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static Commit parseFrom(ByteBuffer byteBuffer) {
                return (Commit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
            }

            public static Parser<Commit> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setRequest(CommitRequest commitRequest) {
                commitRequest.getClass();
                this.request_ = commitRequest;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setResponse(CommitResponse commitResponse) {
                commitResponse.getClass();
                this.response_ = commitResponse;
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
                        return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\t", new Object[]{"request_", "response_"});
                    case NEW_MUTABLE_INSTANCE:
                        return new Commit();
                    case NEW_BUILDER:
                        return new Builder(anonymousClass1);
                    case GET_DEFAULT_INSTANCE:
                        return DEFAULT_INSTANCE;
                    case GET_PARSER:
                        Parser<Commit> defaultInstanceBasedParser = PARSER;
                        if (defaultInstanceBasedParser == null) {
                            synchronized (Commit.class) {
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

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.CommitOrBuilder
            public CommitRequest getRequest() {
                CommitRequest commitRequest = this.request_;
                return commitRequest == null ? CommitRequest.DEFAULT_INSTANCE : commitRequest;
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.CommitOrBuilder
            public CommitResponse getResponse() {
                CommitResponse commitResponse = this.response_;
                return commitResponse == null ? CommitResponse.DEFAULT_INSTANCE : commitResponse;
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.CommitOrBuilder
            public boolean hasRequest() {
                return this.request_ != null;
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.CommitOrBuilder
            public boolean hasResponse() {
                return this.response_ != null;
            }

            public static final class Builder extends GeneratedMessageLite.Builder<Commit, Builder> implements CommitOrBuilder {
                public Builder() {
                    super(Commit.DEFAULT_INSTANCE);
                }

                public Builder clearRequest() {
                    copyOnWrite();
                    ((Commit) this.instance).request_ = null;
                    return this;
                }

                public Builder clearResponse() {
                    copyOnWrite();
                    ((Commit) this.instance).response_ = null;
                    return this;
                }

                @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.CommitOrBuilder
                public CommitRequest getRequest() {
                    return ((Commit) this.instance).getRequest();
                }

                @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.CommitOrBuilder
                public CommitResponse getResponse() {
                    return ((Commit) this.instance).getResponse();
                }

                @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.CommitOrBuilder
                public boolean hasRequest() {
                    return ((Commit) this.instance).hasRequest();
                }

                @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.CommitOrBuilder
                public boolean hasResponse() {
                    return ((Commit) this.instance).hasResponse();
                }

                public Builder mergeRequest(CommitRequest commitRequest) {
                    copyOnWrite();
                    ((Commit) this.instance).mergeRequest(commitRequest);
                    return this;
                }

                public Builder mergeResponse(CommitResponse commitResponse) {
                    copyOnWrite();
                    ((Commit) this.instance).mergeResponse(commitResponse);
                    return this;
                }

                public Builder setRequest(CommitRequest commitRequest) {
                    copyOnWrite();
                    ((Commit) this.instance).setRequest(commitRequest);
                    return this;
                }

                public Builder setResponse(CommitResponse commitResponse) {
                    copyOnWrite();
                    ((Commit) this.instance).setResponse(commitResponse);
                    return this;
                }

                public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                    super(Commit.DEFAULT_INSTANCE);
                }

                public Builder setRequest(CommitRequest.Builder builder) {
                    copyOnWrite();
                    ((Commit) this.instance).setRequest(builder.build());
                    return this;
                }

                public Builder setResponse(CommitResponse.Builder builder) {
                    copyOnWrite();
                    ((Commit) this.instance).setResponse(builder.build());
                    return this;
                }
            }

            public static Builder newBuilder(Commit commit) {
                return DEFAULT_INSTANCE.createBuilder(commit);
            }

            public static Commit parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (Commit) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static Commit parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
                return (Commit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
            }

            public static Commit parseFrom(ByteString byteString) {
                return (Commit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
            }

            public static Commit parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
                return (Commit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
            }

            public static Commit parseFrom(byte[] bArr) {
                return (Commit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
            }

            public static Commit parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
                return (Commit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
            }

            public static Commit parseFrom(InputStream inputStream) {
                return (Commit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static Commit parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (Commit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static Commit parseFrom(CodedInputStream codedInputStream) {
                return (Commit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
            }

            public static Commit parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (Commit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
            }
        }

        public interface CommitOrBuilder extends MessageLiteOrBuilder {
            CommitRequest getRequest();

            CommitResponse getResponse();

            boolean hasRequest();

            boolean hasResponse();
        }

        public static final class CreateDocument extends GeneratedMessageLite<CreateDocument, Builder> implements CreateDocumentOrBuilder {
            public static final CreateDocument DEFAULT_INSTANCE;
            public static volatile Parser<CreateDocument> PARSER = null;
            public static final int REQUEST_FIELD_NUMBER = 1;
            public static final int RESPONSE_FIELD_NUMBER = 2;
            public CreateDocumentRequest request_;
            public Document response_;

            static {
                CreateDocument createDocument = new CreateDocument();
                DEFAULT_INSTANCE = createDocument;
                GeneratedMessageLite.defaultInstanceMap.put(CreateDocument.class, createDocument);
            }

            private void clearRequest() {
                this.request_ = null;
            }

            private void clearResponse() {
                this.response_ = null;
            }

            public static CreateDocument getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void mergeRequest(CreateDocumentRequest createDocumentRequest) {
                createDocumentRequest.getClass();
                CreateDocumentRequest createDocumentRequest2 = this.request_;
                if (createDocumentRequest2 == null || createDocumentRequest2 == CreateDocumentRequest.DEFAULT_INSTANCE) {
                    this.request_ = createDocumentRequest;
                } else {
                    this.request_ = CreateDocumentRequest.newBuilder(createDocumentRequest2).mergeFrom(createDocumentRequest).buildPartial();
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void mergeResponse(Document document) {
                document.getClass();
                Document document2 = this.response_;
                if (document2 == null || document2 == Document.DEFAULT_INSTANCE) {
                    this.response_ = document;
                } else {
                    this.response_ = Document.newBuilder(document2).mergeFrom(document).buildPartial();
                }
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.createBuilder();
            }

            public static CreateDocument parseDelimitedFrom(InputStream inputStream) {
                return (CreateDocument) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static CreateDocument parseFrom(ByteBuffer byteBuffer) {
                return (CreateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
            }

            public static Parser<CreateDocument> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setRequest(CreateDocumentRequest createDocumentRequest) {
                createDocumentRequest.getClass();
                this.request_ = createDocumentRequest;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setResponse(Document document) {
                document.getClass();
                this.response_ = document;
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
                        return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\t", new Object[]{"request_", "response_"});
                    case NEW_MUTABLE_INSTANCE:
                        return new CreateDocument();
                    case NEW_BUILDER:
                        return new Builder(anonymousClass1);
                    case GET_DEFAULT_INSTANCE:
                        return DEFAULT_INSTANCE;
                    case GET_PARSER:
                        Parser<CreateDocument> defaultInstanceBasedParser = PARSER;
                        if (defaultInstanceBasedParser == null) {
                            synchronized (CreateDocument.class) {
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

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.CreateDocumentOrBuilder
            public CreateDocumentRequest getRequest() {
                CreateDocumentRequest createDocumentRequest = this.request_;
                return createDocumentRequest == null ? CreateDocumentRequest.DEFAULT_INSTANCE : createDocumentRequest;
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.CreateDocumentOrBuilder
            public Document getResponse() {
                Document document = this.response_;
                return document == null ? Document.DEFAULT_INSTANCE : document;
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.CreateDocumentOrBuilder
            public boolean hasRequest() {
                return this.request_ != null;
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.CreateDocumentOrBuilder
            public boolean hasResponse() {
                return this.response_ != null;
            }

            public static final class Builder extends GeneratedMessageLite.Builder<CreateDocument, Builder> implements CreateDocumentOrBuilder {
                public Builder() {
                    super(CreateDocument.DEFAULT_INSTANCE);
                }

                public Builder clearRequest() {
                    copyOnWrite();
                    ((CreateDocument) this.instance).request_ = null;
                    return this;
                }

                public Builder clearResponse() {
                    copyOnWrite();
                    ((CreateDocument) this.instance).response_ = null;
                    return this;
                }

                @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.CreateDocumentOrBuilder
                public CreateDocumentRequest getRequest() {
                    return ((CreateDocument) this.instance).getRequest();
                }

                @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.CreateDocumentOrBuilder
                public Document getResponse() {
                    return ((CreateDocument) this.instance).getResponse();
                }

                @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.CreateDocumentOrBuilder
                public boolean hasRequest() {
                    return ((CreateDocument) this.instance).hasRequest();
                }

                @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.CreateDocumentOrBuilder
                public boolean hasResponse() {
                    return ((CreateDocument) this.instance).hasResponse();
                }

                public Builder mergeRequest(CreateDocumentRequest createDocumentRequest) {
                    copyOnWrite();
                    ((CreateDocument) this.instance).mergeRequest(createDocumentRequest);
                    return this;
                }

                public Builder mergeResponse(Document document) {
                    copyOnWrite();
                    ((CreateDocument) this.instance).mergeResponse(document);
                    return this;
                }

                public Builder setRequest(CreateDocumentRequest createDocumentRequest) {
                    copyOnWrite();
                    ((CreateDocument) this.instance).setRequest(createDocumentRequest);
                    return this;
                }

                public Builder setResponse(Document document) {
                    copyOnWrite();
                    ((CreateDocument) this.instance).setResponse(document);
                    return this;
                }

                public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                    super(CreateDocument.DEFAULT_INSTANCE);
                }

                public Builder setRequest(CreateDocumentRequest.Builder builder) {
                    copyOnWrite();
                    ((CreateDocument) this.instance).setRequest(builder.build());
                    return this;
                }

                public Builder setResponse(Document.Builder builder) {
                    copyOnWrite();
                    ((CreateDocument) this.instance).setResponse(builder.build());
                    return this;
                }
            }

            public static Builder newBuilder(CreateDocument createDocument) {
                return DEFAULT_INSTANCE.createBuilder(createDocument);
            }

            public static CreateDocument parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (CreateDocument) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static CreateDocument parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
                return (CreateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
            }

            public static CreateDocument parseFrom(ByteString byteString) {
                return (CreateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
            }

            public static CreateDocument parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
                return (CreateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
            }

            public static CreateDocument parseFrom(byte[] bArr) {
                return (CreateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
            }

            public static CreateDocument parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
                return (CreateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
            }

            public static CreateDocument parseFrom(InputStream inputStream) {
                return (CreateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static CreateDocument parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (CreateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static CreateDocument parseFrom(CodedInputStream codedInputStream) {
                return (CreateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
            }

            public static CreateDocument parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (CreateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
            }
        }

        public interface CreateDocumentOrBuilder extends MessageLiteOrBuilder {
            CreateDocumentRequest getRequest();

            Document getResponse();

            boolean hasRequest();

            boolean hasResponse();
        }

        public static final class DeleteDocument extends GeneratedMessageLite<DeleteDocument, Builder> implements DeleteDocumentOrBuilder {
            public static final DeleteDocument DEFAULT_INSTANCE;
            public static volatile Parser<DeleteDocument> PARSER = null;
            public static final int REQUEST_FIELD_NUMBER = 1;
            public static final int RESPONSE_FIELD_NUMBER = 2;
            public DeleteDocumentRequest request_;
            public Empty response_;

            static {
                DeleteDocument deleteDocument = new DeleteDocument();
                DEFAULT_INSTANCE = deleteDocument;
                GeneratedMessageLite.defaultInstanceMap.put(DeleteDocument.class, deleteDocument);
            }

            private void clearRequest() {
                this.request_ = null;
            }

            private void clearResponse() {
                this.response_ = null;
            }

            public static DeleteDocument getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void mergeRequest(DeleteDocumentRequest deleteDocumentRequest) {
                deleteDocumentRequest.getClass();
                DeleteDocumentRequest deleteDocumentRequest2 = this.request_;
                if (deleteDocumentRequest2 == null || deleteDocumentRequest2 == DeleteDocumentRequest.DEFAULT_INSTANCE) {
                    this.request_ = deleteDocumentRequest;
                } else {
                    this.request_ = DeleteDocumentRequest.newBuilder(deleteDocumentRequest2).mergeFrom(deleteDocumentRequest).buildPartial();
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void mergeResponse(Empty empty) {
                empty.getClass();
                Empty empty2 = this.response_;
                if (empty2 == null || empty2 == Empty.DEFAULT_INSTANCE) {
                    this.response_ = empty;
                } else {
                    this.response_ = Empty.newBuilder(empty2).mergeFrom(empty).buildPartial();
                }
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.createBuilder();
            }

            public static DeleteDocument parseDelimitedFrom(InputStream inputStream) {
                return (DeleteDocument) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static DeleteDocument parseFrom(ByteBuffer byteBuffer) {
                return (DeleteDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
            }

            public static Parser<DeleteDocument> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setRequest(DeleteDocumentRequest deleteDocumentRequest) {
                deleteDocumentRequest.getClass();
                this.request_ = deleteDocumentRequest;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setResponse(Empty empty) {
                empty.getClass();
                this.response_ = empty;
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
                        return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\t", new Object[]{"request_", "response_"});
                    case NEW_MUTABLE_INSTANCE:
                        return new DeleteDocument();
                    case NEW_BUILDER:
                        return new Builder(anonymousClass1);
                    case GET_DEFAULT_INSTANCE:
                        return DEFAULT_INSTANCE;
                    case GET_PARSER:
                        Parser<DeleteDocument> defaultInstanceBasedParser = PARSER;
                        if (defaultInstanceBasedParser == null) {
                            synchronized (DeleteDocument.class) {
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

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.DeleteDocumentOrBuilder
            public DeleteDocumentRequest getRequest() {
                DeleteDocumentRequest deleteDocumentRequest = this.request_;
                return deleteDocumentRequest == null ? DeleteDocumentRequest.DEFAULT_INSTANCE : deleteDocumentRequest;
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.DeleteDocumentOrBuilder
            public Empty getResponse() {
                Empty empty = this.response_;
                return empty == null ? Empty.DEFAULT_INSTANCE : empty;
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.DeleteDocumentOrBuilder
            public boolean hasRequest() {
                return this.request_ != null;
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.DeleteDocumentOrBuilder
            public boolean hasResponse() {
                return this.response_ != null;
            }

            public static final class Builder extends GeneratedMessageLite.Builder<DeleteDocument, Builder> implements DeleteDocumentOrBuilder {
                public Builder() {
                    super(DeleteDocument.DEFAULT_INSTANCE);
                }

                public Builder clearRequest() {
                    copyOnWrite();
                    ((DeleteDocument) this.instance).request_ = null;
                    return this;
                }

                public Builder clearResponse() {
                    copyOnWrite();
                    ((DeleteDocument) this.instance).response_ = null;
                    return this;
                }

                @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.DeleteDocumentOrBuilder
                public DeleteDocumentRequest getRequest() {
                    return ((DeleteDocument) this.instance).getRequest();
                }

                @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.DeleteDocumentOrBuilder
                public Empty getResponse() {
                    return ((DeleteDocument) this.instance).getResponse();
                }

                @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.DeleteDocumentOrBuilder
                public boolean hasRequest() {
                    return ((DeleteDocument) this.instance).hasRequest();
                }

                @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.DeleteDocumentOrBuilder
                public boolean hasResponse() {
                    return ((DeleteDocument) this.instance).hasResponse();
                }

                public Builder mergeRequest(DeleteDocumentRequest deleteDocumentRequest) {
                    copyOnWrite();
                    ((DeleteDocument) this.instance).mergeRequest(deleteDocumentRequest);
                    return this;
                }

                public Builder mergeResponse(Empty empty) {
                    copyOnWrite();
                    ((DeleteDocument) this.instance).mergeResponse(empty);
                    return this;
                }

                public Builder setRequest(DeleteDocumentRequest deleteDocumentRequest) {
                    copyOnWrite();
                    ((DeleteDocument) this.instance).setRequest(deleteDocumentRequest);
                    return this;
                }

                public Builder setResponse(Empty empty) {
                    copyOnWrite();
                    ((DeleteDocument) this.instance).setResponse(empty);
                    return this;
                }

                public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                    super(DeleteDocument.DEFAULT_INSTANCE);
                }

                public Builder setRequest(DeleteDocumentRequest.Builder builder) {
                    copyOnWrite();
                    ((DeleteDocument) this.instance).setRequest(builder.build());
                    return this;
                }

                public Builder setResponse(Empty.Builder builder) {
                    copyOnWrite();
                    ((DeleteDocument) this.instance).setResponse(builder.build());
                    return this;
                }
            }

            public static Builder newBuilder(DeleteDocument deleteDocument) {
                return DEFAULT_INSTANCE.createBuilder(deleteDocument);
            }

            public static DeleteDocument parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (DeleteDocument) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static DeleteDocument parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
                return (DeleteDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
            }

            public static DeleteDocument parseFrom(ByteString byteString) {
                return (DeleteDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
            }

            public static DeleteDocument parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
                return (DeleteDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
            }

            public static DeleteDocument parseFrom(byte[] bArr) {
                return (DeleteDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
            }

            public static DeleteDocument parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
                return (DeleteDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
            }

            public static DeleteDocument parseFrom(InputStream inputStream) {
                return (DeleteDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static DeleteDocument parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (DeleteDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static DeleteDocument parseFrom(CodedInputStream codedInputStream) {
                return (DeleteDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
            }

            public static DeleteDocument parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (DeleteDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
            }
        }

        public interface DeleteDocumentOrBuilder extends MessageLiteOrBuilder {
            DeleteDocumentRequest getRequest();

            Empty getResponse();

            boolean hasRequest();

            boolean hasResponse();
        }

        public static final class GetDocument extends GeneratedMessageLite<GetDocument, Builder> implements GetDocumentOrBuilder {
            public static final GetDocument DEFAULT_INSTANCE;
            public static volatile Parser<GetDocument> PARSER = null;
            public static final int REQUEST_FIELD_NUMBER = 1;
            public static final int RESPONSE_FIELD_NUMBER = 2;
            public GetDocumentRequest request_;
            public Document response_;

            static {
                GetDocument getDocument = new GetDocument();
                DEFAULT_INSTANCE = getDocument;
                GeneratedMessageLite.defaultInstanceMap.put(GetDocument.class, getDocument);
            }

            private void clearRequest() {
                this.request_ = null;
            }

            private void clearResponse() {
                this.response_ = null;
            }

            public static GetDocument getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void mergeRequest(GetDocumentRequest getDocumentRequest) {
                getDocumentRequest.getClass();
                GetDocumentRequest getDocumentRequest2 = this.request_;
                if (getDocumentRequest2 == null || getDocumentRequest2 == GetDocumentRequest.DEFAULT_INSTANCE) {
                    this.request_ = getDocumentRequest;
                } else {
                    this.request_ = GetDocumentRequest.newBuilder(getDocumentRequest2).mergeFrom(getDocumentRequest).buildPartial();
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void mergeResponse(Document document) {
                document.getClass();
                Document document2 = this.response_;
                if (document2 == null || document2 == Document.DEFAULT_INSTANCE) {
                    this.response_ = document;
                } else {
                    this.response_ = Document.newBuilder(document2).mergeFrom(document).buildPartial();
                }
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.createBuilder();
            }

            public static GetDocument parseDelimitedFrom(InputStream inputStream) {
                return (GetDocument) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static GetDocument parseFrom(ByteBuffer byteBuffer) {
                return (GetDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
            }

            public static Parser<GetDocument> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setRequest(GetDocumentRequest getDocumentRequest) {
                getDocumentRequest.getClass();
                this.request_ = getDocumentRequest;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setResponse(Document document) {
                document.getClass();
                this.response_ = document;
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
                        return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\t", new Object[]{"request_", "response_"});
                    case NEW_MUTABLE_INSTANCE:
                        return new GetDocument();
                    case NEW_BUILDER:
                        return new Builder(anonymousClass1);
                    case GET_DEFAULT_INSTANCE:
                        return DEFAULT_INSTANCE;
                    case GET_PARSER:
                        Parser<GetDocument> defaultInstanceBasedParser = PARSER;
                        if (defaultInstanceBasedParser == null) {
                            synchronized (GetDocument.class) {
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

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.GetDocumentOrBuilder
            public GetDocumentRequest getRequest() {
                GetDocumentRequest getDocumentRequest = this.request_;
                return getDocumentRequest == null ? GetDocumentRequest.DEFAULT_INSTANCE : getDocumentRequest;
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.GetDocumentOrBuilder
            public Document getResponse() {
                Document document = this.response_;
                return document == null ? Document.DEFAULT_INSTANCE : document;
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.GetDocumentOrBuilder
            public boolean hasRequest() {
                return this.request_ != null;
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.GetDocumentOrBuilder
            public boolean hasResponse() {
                return this.response_ != null;
            }

            public static final class Builder extends GeneratedMessageLite.Builder<GetDocument, Builder> implements GetDocumentOrBuilder {
                public Builder() {
                    super(GetDocument.DEFAULT_INSTANCE);
                }

                public Builder clearRequest() {
                    copyOnWrite();
                    ((GetDocument) this.instance).request_ = null;
                    return this;
                }

                public Builder clearResponse() {
                    copyOnWrite();
                    ((GetDocument) this.instance).response_ = null;
                    return this;
                }

                @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.GetDocumentOrBuilder
                public GetDocumentRequest getRequest() {
                    return ((GetDocument) this.instance).getRequest();
                }

                @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.GetDocumentOrBuilder
                public Document getResponse() {
                    return ((GetDocument) this.instance).getResponse();
                }

                @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.GetDocumentOrBuilder
                public boolean hasRequest() {
                    return ((GetDocument) this.instance).hasRequest();
                }

                @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.GetDocumentOrBuilder
                public boolean hasResponse() {
                    return ((GetDocument) this.instance).hasResponse();
                }

                public Builder mergeRequest(GetDocumentRequest getDocumentRequest) {
                    copyOnWrite();
                    ((GetDocument) this.instance).mergeRequest(getDocumentRequest);
                    return this;
                }

                public Builder mergeResponse(Document document) {
                    copyOnWrite();
                    ((GetDocument) this.instance).mergeResponse(document);
                    return this;
                }

                public Builder setRequest(GetDocumentRequest getDocumentRequest) {
                    copyOnWrite();
                    ((GetDocument) this.instance).setRequest(getDocumentRequest);
                    return this;
                }

                public Builder setResponse(Document document) {
                    copyOnWrite();
                    ((GetDocument) this.instance).setResponse(document);
                    return this;
                }

                public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                    super(GetDocument.DEFAULT_INSTANCE);
                }

                public Builder setRequest(GetDocumentRequest.Builder builder) {
                    copyOnWrite();
                    ((GetDocument) this.instance).setRequest(builder.build());
                    return this;
                }

                public Builder setResponse(Document.Builder builder) {
                    copyOnWrite();
                    ((GetDocument) this.instance).setResponse(builder.build());
                    return this;
                }
            }

            public static Builder newBuilder(GetDocument getDocument) {
                return DEFAULT_INSTANCE.createBuilder(getDocument);
            }

            public static GetDocument parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (GetDocument) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static GetDocument parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
                return (GetDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
            }

            public static GetDocument parseFrom(ByteString byteString) {
                return (GetDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
            }

            public static GetDocument parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
                return (GetDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
            }

            public static GetDocument parseFrom(byte[] bArr) {
                return (GetDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
            }

            public static GetDocument parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
                return (GetDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
            }

            public static GetDocument parseFrom(InputStream inputStream) {
                return (GetDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static GetDocument parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (GetDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static GetDocument parseFrom(CodedInputStream codedInputStream) {
                return (GetDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
            }

            public static GetDocument parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (GetDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
            }
        }

        public interface GetDocumentOrBuilder extends MessageLiteOrBuilder {
            GetDocumentRequest getRequest();

            Document getResponse();

            boolean hasRequest();

            boolean hasResponse();
        }

        public static final class ListCollectionIds extends GeneratedMessageLite<ListCollectionIds, Builder> implements ListCollectionIdsOrBuilder {
            public static final ListCollectionIds DEFAULT_INSTANCE;
            public static volatile Parser<ListCollectionIds> PARSER = null;
            public static final int REQUEST_FIELD_NUMBER = 1;
            public static final int RESPONSE_FIELD_NUMBER = 2;
            public ListCollectionIdsRequest request_;
            public ListCollectionIdsResponse response_;

            static {
                ListCollectionIds listCollectionIds = new ListCollectionIds();
                DEFAULT_INSTANCE = listCollectionIds;
                GeneratedMessageLite.defaultInstanceMap.put(ListCollectionIds.class, listCollectionIds);
            }

            private void clearRequest() {
                this.request_ = null;
            }

            private void clearResponse() {
                this.response_ = null;
            }

            public static ListCollectionIds getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void mergeRequest(ListCollectionIdsRequest listCollectionIdsRequest) {
                listCollectionIdsRequest.getClass();
                ListCollectionIdsRequest listCollectionIdsRequest2 = this.request_;
                if (listCollectionIdsRequest2 == null || listCollectionIdsRequest2 == ListCollectionIdsRequest.DEFAULT_INSTANCE) {
                    this.request_ = listCollectionIdsRequest;
                } else {
                    this.request_ = ListCollectionIdsRequest.newBuilder(listCollectionIdsRequest2).mergeFrom(listCollectionIdsRequest).buildPartial();
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void mergeResponse(ListCollectionIdsResponse listCollectionIdsResponse) {
                listCollectionIdsResponse.getClass();
                ListCollectionIdsResponse listCollectionIdsResponse2 = this.response_;
                if (listCollectionIdsResponse2 == null || listCollectionIdsResponse2 == ListCollectionIdsResponse.DEFAULT_INSTANCE) {
                    this.response_ = listCollectionIdsResponse;
                } else {
                    this.response_ = ListCollectionIdsResponse.newBuilder(listCollectionIdsResponse2).mergeFrom(listCollectionIdsResponse).buildPartial();
                }
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.createBuilder();
            }

            public static ListCollectionIds parseDelimitedFrom(InputStream inputStream) {
                return (ListCollectionIds) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static ListCollectionIds parseFrom(ByteBuffer byteBuffer) {
                return (ListCollectionIds) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
            }

            public static Parser<ListCollectionIds> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setRequest(ListCollectionIdsRequest listCollectionIdsRequest) {
                listCollectionIdsRequest.getClass();
                this.request_ = listCollectionIdsRequest;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setResponse(ListCollectionIdsResponse listCollectionIdsResponse) {
                listCollectionIdsResponse.getClass();
                this.response_ = listCollectionIdsResponse;
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
                        return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\t", new Object[]{"request_", "response_"});
                    case NEW_MUTABLE_INSTANCE:
                        return new ListCollectionIds();
                    case NEW_BUILDER:
                        return new Builder(anonymousClass1);
                    case GET_DEFAULT_INSTANCE:
                        return DEFAULT_INSTANCE;
                    case GET_PARSER:
                        Parser<ListCollectionIds> defaultInstanceBasedParser = PARSER;
                        if (defaultInstanceBasedParser == null) {
                            synchronized (ListCollectionIds.class) {
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

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.ListCollectionIdsOrBuilder
            public ListCollectionIdsRequest getRequest() {
                ListCollectionIdsRequest listCollectionIdsRequest = this.request_;
                return listCollectionIdsRequest == null ? ListCollectionIdsRequest.DEFAULT_INSTANCE : listCollectionIdsRequest;
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.ListCollectionIdsOrBuilder
            public ListCollectionIdsResponse getResponse() {
                ListCollectionIdsResponse listCollectionIdsResponse = this.response_;
                return listCollectionIdsResponse == null ? ListCollectionIdsResponse.DEFAULT_INSTANCE : listCollectionIdsResponse;
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.ListCollectionIdsOrBuilder
            public boolean hasRequest() {
                return this.request_ != null;
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.ListCollectionIdsOrBuilder
            public boolean hasResponse() {
                return this.response_ != null;
            }

            public static final class Builder extends GeneratedMessageLite.Builder<ListCollectionIds, Builder> implements ListCollectionIdsOrBuilder {
                public Builder() {
                    super(ListCollectionIds.DEFAULT_INSTANCE);
                }

                public Builder clearRequest() {
                    copyOnWrite();
                    ((ListCollectionIds) this.instance).request_ = null;
                    return this;
                }

                public Builder clearResponse() {
                    copyOnWrite();
                    ((ListCollectionIds) this.instance).response_ = null;
                    return this;
                }

                @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.ListCollectionIdsOrBuilder
                public ListCollectionIdsRequest getRequest() {
                    return ((ListCollectionIds) this.instance).getRequest();
                }

                @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.ListCollectionIdsOrBuilder
                public ListCollectionIdsResponse getResponse() {
                    return ((ListCollectionIds) this.instance).getResponse();
                }

                @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.ListCollectionIdsOrBuilder
                public boolean hasRequest() {
                    return ((ListCollectionIds) this.instance).hasRequest();
                }

                @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.ListCollectionIdsOrBuilder
                public boolean hasResponse() {
                    return ((ListCollectionIds) this.instance).hasResponse();
                }

                public Builder mergeRequest(ListCollectionIdsRequest listCollectionIdsRequest) {
                    copyOnWrite();
                    ((ListCollectionIds) this.instance).mergeRequest(listCollectionIdsRequest);
                    return this;
                }

                public Builder mergeResponse(ListCollectionIdsResponse listCollectionIdsResponse) {
                    copyOnWrite();
                    ((ListCollectionIds) this.instance).mergeResponse(listCollectionIdsResponse);
                    return this;
                }

                public Builder setRequest(ListCollectionIdsRequest listCollectionIdsRequest) {
                    copyOnWrite();
                    ((ListCollectionIds) this.instance).setRequest(listCollectionIdsRequest);
                    return this;
                }

                public Builder setResponse(ListCollectionIdsResponse listCollectionIdsResponse) {
                    copyOnWrite();
                    ((ListCollectionIds) this.instance).setResponse(listCollectionIdsResponse);
                    return this;
                }

                public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                    super(ListCollectionIds.DEFAULT_INSTANCE);
                }

                public Builder setRequest(ListCollectionIdsRequest.Builder builder) {
                    copyOnWrite();
                    ((ListCollectionIds) this.instance).setRequest(builder.build());
                    return this;
                }

                public Builder setResponse(ListCollectionIdsResponse.Builder builder) {
                    copyOnWrite();
                    ((ListCollectionIds) this.instance).setResponse(builder.build());
                    return this;
                }
            }

            public static Builder newBuilder(ListCollectionIds listCollectionIds) {
                return DEFAULT_INSTANCE.createBuilder(listCollectionIds);
            }

            public static ListCollectionIds parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (ListCollectionIds) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static ListCollectionIds parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
                return (ListCollectionIds) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
            }

            public static ListCollectionIds parseFrom(ByteString byteString) {
                return (ListCollectionIds) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
            }

            public static ListCollectionIds parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
                return (ListCollectionIds) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
            }

            public static ListCollectionIds parseFrom(byte[] bArr) {
                return (ListCollectionIds) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
            }

            public static ListCollectionIds parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
                return (ListCollectionIds) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
            }

            public static ListCollectionIds parseFrom(InputStream inputStream) {
                return (ListCollectionIds) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static ListCollectionIds parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (ListCollectionIds) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static ListCollectionIds parseFrom(CodedInputStream codedInputStream) {
                return (ListCollectionIds) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
            }

            public static ListCollectionIds parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (ListCollectionIds) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
            }
        }

        public interface ListCollectionIdsOrBuilder extends MessageLiteOrBuilder {
            ListCollectionIdsRequest getRequest();

            ListCollectionIdsResponse getResponse();

            boolean hasRequest();

            boolean hasResponse();
        }

        public static final class ListDocuments extends GeneratedMessageLite<ListDocuments, Builder> implements ListDocumentsOrBuilder {
            public static final ListDocuments DEFAULT_INSTANCE;
            public static volatile Parser<ListDocuments> PARSER = null;
            public static final int REQUEST_FIELD_NUMBER = 1;
            public static final int RESPONSE_FIELD_NUMBER = 2;
            public ListDocumentsRequest request_;
            public ListDocumentsResponse response_;

            static {
                ListDocuments listDocuments = new ListDocuments();
                DEFAULT_INSTANCE = listDocuments;
                GeneratedMessageLite.defaultInstanceMap.put(ListDocuments.class, listDocuments);
            }

            private void clearRequest() {
                this.request_ = null;
            }

            private void clearResponse() {
                this.response_ = null;
            }

            public static ListDocuments getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void mergeRequest(ListDocumentsRequest listDocumentsRequest) {
                listDocumentsRequest.getClass();
                ListDocumentsRequest listDocumentsRequest2 = this.request_;
                if (listDocumentsRequest2 == null || listDocumentsRequest2 == ListDocumentsRequest.DEFAULT_INSTANCE) {
                    this.request_ = listDocumentsRequest;
                } else {
                    this.request_ = ListDocumentsRequest.newBuilder(listDocumentsRequest2).mergeFrom(listDocumentsRequest).buildPartial();
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void mergeResponse(ListDocumentsResponse listDocumentsResponse) {
                listDocumentsResponse.getClass();
                ListDocumentsResponse listDocumentsResponse2 = this.response_;
                if (listDocumentsResponse2 == null || listDocumentsResponse2 == ListDocumentsResponse.DEFAULT_INSTANCE) {
                    this.response_ = listDocumentsResponse;
                } else {
                    this.response_ = ListDocumentsResponse.newBuilder(listDocumentsResponse2).mergeFrom(listDocumentsResponse).buildPartial();
                }
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.createBuilder();
            }

            public static ListDocuments parseDelimitedFrom(InputStream inputStream) {
                return (ListDocuments) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static ListDocuments parseFrom(ByteBuffer byteBuffer) {
                return (ListDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
            }

            public static Parser<ListDocuments> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setRequest(ListDocumentsRequest listDocumentsRequest) {
                listDocumentsRequest.getClass();
                this.request_ = listDocumentsRequest;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setResponse(ListDocumentsResponse listDocumentsResponse) {
                listDocumentsResponse.getClass();
                this.response_ = listDocumentsResponse;
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
                        return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\t", new Object[]{"request_", "response_"});
                    case NEW_MUTABLE_INSTANCE:
                        return new ListDocuments();
                    case NEW_BUILDER:
                        return new Builder(anonymousClass1);
                    case GET_DEFAULT_INSTANCE:
                        return DEFAULT_INSTANCE;
                    case GET_PARSER:
                        Parser<ListDocuments> defaultInstanceBasedParser = PARSER;
                        if (defaultInstanceBasedParser == null) {
                            synchronized (ListDocuments.class) {
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

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.ListDocumentsOrBuilder
            public ListDocumentsRequest getRequest() {
                ListDocumentsRequest listDocumentsRequest = this.request_;
                return listDocumentsRequest == null ? ListDocumentsRequest.DEFAULT_INSTANCE : listDocumentsRequest;
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.ListDocumentsOrBuilder
            public ListDocumentsResponse getResponse() {
                ListDocumentsResponse listDocumentsResponse = this.response_;
                return listDocumentsResponse == null ? ListDocumentsResponse.DEFAULT_INSTANCE : listDocumentsResponse;
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.ListDocumentsOrBuilder
            public boolean hasRequest() {
                return this.request_ != null;
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.ListDocumentsOrBuilder
            public boolean hasResponse() {
                return this.response_ != null;
            }

            public static final class Builder extends GeneratedMessageLite.Builder<ListDocuments, Builder> implements ListDocumentsOrBuilder {
                public Builder() {
                    super(ListDocuments.DEFAULT_INSTANCE);
                }

                public Builder clearRequest() {
                    copyOnWrite();
                    ((ListDocuments) this.instance).request_ = null;
                    return this;
                }

                public Builder clearResponse() {
                    copyOnWrite();
                    ((ListDocuments) this.instance).response_ = null;
                    return this;
                }

                @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.ListDocumentsOrBuilder
                public ListDocumentsRequest getRequest() {
                    return ((ListDocuments) this.instance).getRequest();
                }

                @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.ListDocumentsOrBuilder
                public ListDocumentsResponse getResponse() {
                    return ((ListDocuments) this.instance).getResponse();
                }

                @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.ListDocumentsOrBuilder
                public boolean hasRequest() {
                    return ((ListDocuments) this.instance).hasRequest();
                }

                @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.ListDocumentsOrBuilder
                public boolean hasResponse() {
                    return ((ListDocuments) this.instance).hasResponse();
                }

                public Builder mergeRequest(ListDocumentsRequest listDocumentsRequest) {
                    copyOnWrite();
                    ((ListDocuments) this.instance).mergeRequest(listDocumentsRequest);
                    return this;
                }

                public Builder mergeResponse(ListDocumentsResponse listDocumentsResponse) {
                    copyOnWrite();
                    ((ListDocuments) this.instance).mergeResponse(listDocumentsResponse);
                    return this;
                }

                public Builder setRequest(ListDocumentsRequest listDocumentsRequest) {
                    copyOnWrite();
                    ((ListDocuments) this.instance).setRequest(listDocumentsRequest);
                    return this;
                }

                public Builder setResponse(ListDocumentsResponse listDocumentsResponse) {
                    copyOnWrite();
                    ((ListDocuments) this.instance).setResponse(listDocumentsResponse);
                    return this;
                }

                public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                    super(ListDocuments.DEFAULT_INSTANCE);
                }

                public Builder setRequest(ListDocumentsRequest.Builder builder) {
                    copyOnWrite();
                    ((ListDocuments) this.instance).setRequest(builder.build());
                    return this;
                }

                public Builder setResponse(ListDocumentsResponse.Builder builder) {
                    copyOnWrite();
                    ((ListDocuments) this.instance).setResponse(builder.build());
                    return this;
                }
            }

            public static Builder newBuilder(ListDocuments listDocuments) {
                return DEFAULT_INSTANCE.createBuilder(listDocuments);
            }

            public static ListDocuments parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (ListDocuments) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static ListDocuments parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
                return (ListDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
            }

            public static ListDocuments parseFrom(ByteString byteString) {
                return (ListDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
            }

            public static ListDocuments parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
                return (ListDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
            }

            public static ListDocuments parseFrom(byte[] bArr) {
                return (ListDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
            }

            public static ListDocuments parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
                return (ListDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
            }

            public static ListDocuments parseFrom(InputStream inputStream) {
                return (ListDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static ListDocuments parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (ListDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static ListDocuments parseFrom(CodedInputStream codedInputStream) {
                return (ListDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
            }

            public static ListDocuments parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (ListDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
            }
        }

        public interface ListDocumentsOrBuilder extends MessageLiteOrBuilder {
            ListDocumentsRequest getRequest();

            ListDocumentsResponse getResponse();

            boolean hasRequest();

            boolean hasResponse();
        }

        public static final class Listen extends GeneratedMessageLite<Listen, Builder> implements ListenOrBuilder {
            public static final Listen DEFAULT_INSTANCE;
            public static volatile Parser<Listen> PARSER = null;
            public static final int REQUEST_FIELD_NUMBER = 1;
            public static final int RESPONSE_FIELD_NUMBER = 2;
            public ListenRequest request_;
            public ListenResponse response_;

            static {
                Listen listen = new Listen();
                DEFAULT_INSTANCE = listen;
                GeneratedMessageLite.defaultInstanceMap.put(Listen.class, listen);
            }

            private void clearRequest() {
                this.request_ = null;
            }

            private void clearResponse() {
                this.response_ = null;
            }

            public static Listen getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void mergeRequest(ListenRequest listenRequest) {
                listenRequest.getClass();
                ListenRequest listenRequest2 = this.request_;
                if (listenRequest2 == null || listenRequest2 == ListenRequest.DEFAULT_INSTANCE) {
                    this.request_ = listenRequest;
                } else {
                    this.request_ = ListenRequest.newBuilder(listenRequest2).mergeFrom(listenRequest).buildPartial();
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void mergeResponse(ListenResponse listenResponse) {
                listenResponse.getClass();
                ListenResponse listenResponse2 = this.response_;
                if (listenResponse2 == null || listenResponse2 == ListenResponse.DEFAULT_INSTANCE) {
                    this.response_ = listenResponse;
                } else {
                    this.response_ = ListenResponse.newBuilder(listenResponse2).mergeFrom(listenResponse).buildPartial();
                }
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.createBuilder();
            }

            public static Listen parseDelimitedFrom(InputStream inputStream) {
                return (Listen) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static Listen parseFrom(ByteBuffer byteBuffer) {
                return (Listen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
            }

            public static Parser<Listen> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setRequest(ListenRequest listenRequest) {
                listenRequest.getClass();
                this.request_ = listenRequest;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setResponse(ListenResponse listenResponse) {
                listenResponse.getClass();
                this.response_ = listenResponse;
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
                        return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\t", new Object[]{"request_", "response_"});
                    case NEW_MUTABLE_INSTANCE:
                        return new Listen();
                    case NEW_BUILDER:
                        return new Builder(anonymousClass1);
                    case GET_DEFAULT_INSTANCE:
                        return DEFAULT_INSTANCE;
                    case GET_PARSER:
                        Parser<Listen> defaultInstanceBasedParser = PARSER;
                        if (defaultInstanceBasedParser == null) {
                            synchronized (Listen.class) {
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

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.ListenOrBuilder
            public ListenRequest getRequest() {
                ListenRequest listenRequest = this.request_;
                return listenRequest == null ? ListenRequest.DEFAULT_INSTANCE : listenRequest;
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.ListenOrBuilder
            public ListenResponse getResponse() {
                ListenResponse listenResponse = this.response_;
                return listenResponse == null ? ListenResponse.DEFAULT_INSTANCE : listenResponse;
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.ListenOrBuilder
            public boolean hasRequest() {
                return this.request_ != null;
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.ListenOrBuilder
            public boolean hasResponse() {
                return this.response_ != null;
            }

            public static final class Builder extends GeneratedMessageLite.Builder<Listen, Builder> implements ListenOrBuilder {
                public Builder() {
                    super(Listen.DEFAULT_INSTANCE);
                }

                public Builder clearRequest() {
                    copyOnWrite();
                    ((Listen) this.instance).request_ = null;
                    return this;
                }

                public Builder clearResponse() {
                    copyOnWrite();
                    ((Listen) this.instance).response_ = null;
                    return this;
                }

                @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.ListenOrBuilder
                public ListenRequest getRequest() {
                    return ((Listen) this.instance).getRequest();
                }

                @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.ListenOrBuilder
                public ListenResponse getResponse() {
                    return ((Listen) this.instance).getResponse();
                }

                @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.ListenOrBuilder
                public boolean hasRequest() {
                    return ((Listen) this.instance).hasRequest();
                }

                @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.ListenOrBuilder
                public boolean hasResponse() {
                    return ((Listen) this.instance).hasResponse();
                }

                public Builder mergeRequest(ListenRequest listenRequest) {
                    copyOnWrite();
                    ((Listen) this.instance).mergeRequest(listenRequest);
                    return this;
                }

                public Builder mergeResponse(ListenResponse listenResponse) {
                    copyOnWrite();
                    ((Listen) this.instance).mergeResponse(listenResponse);
                    return this;
                }

                public Builder setRequest(ListenRequest listenRequest) {
                    copyOnWrite();
                    ((Listen) this.instance).setRequest(listenRequest);
                    return this;
                }

                public Builder setResponse(ListenResponse listenResponse) {
                    copyOnWrite();
                    ((Listen) this.instance).setResponse(listenResponse);
                    return this;
                }

                public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                    super(Listen.DEFAULT_INSTANCE);
                }

                public Builder setRequest(ListenRequest.Builder builder) {
                    copyOnWrite();
                    ((Listen) this.instance).setRequest(builder.build());
                    return this;
                }

                public Builder setResponse(ListenResponse.Builder builder) {
                    copyOnWrite();
                    ((Listen) this.instance).setResponse(builder.build());
                    return this;
                }
            }

            public static Builder newBuilder(Listen listen) {
                return DEFAULT_INSTANCE.createBuilder(listen);
            }

            public static Listen parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (Listen) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static Listen parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
                return (Listen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
            }

            public static Listen parseFrom(ByteString byteString) {
                return (Listen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
            }

            public static Listen parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
                return (Listen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
            }

            public static Listen parseFrom(byte[] bArr) {
                return (Listen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
            }

            public static Listen parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
                return (Listen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
            }

            public static Listen parseFrom(InputStream inputStream) {
                return (Listen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static Listen parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (Listen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static Listen parseFrom(CodedInputStream codedInputStream) {
                return (Listen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
            }

            public static Listen parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (Listen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
            }
        }

        public interface ListenOrBuilder extends MessageLiteOrBuilder {
            ListenRequest getRequest();

            ListenResponse getResponse();

            boolean hasRequest();

            boolean hasResponse();
        }

        public static final class MatchingDocuments extends GeneratedMessageLite<MatchingDocuments, Builder> implements MatchingDocumentsOrBuilder {
            public static final MatchingDocuments DEFAULT_INSTANCE;
            public static final int LISTEN_RESPONSE_FIELD_NUMBER = 1;
            public static final int MATCHING_DOCUMENTS_FIELD_NUMBER = 2;
            public static volatile Parser<MatchingDocuments> PARSER;
            public ListenResponse listenResponse_;
            public RunQueryResponse matchingDocuments_;

            static {
                MatchingDocuments matchingDocuments = new MatchingDocuments();
                DEFAULT_INSTANCE = matchingDocuments;
                GeneratedMessageLite.defaultInstanceMap.put(MatchingDocuments.class, matchingDocuments);
            }

            private void clearListenResponse() {
                this.listenResponse_ = null;
            }

            private void clearMatchingDocuments() {
                this.matchingDocuments_ = null;
            }

            public static MatchingDocuments getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void mergeListenResponse(ListenResponse listenResponse) {
                listenResponse.getClass();
                ListenResponse listenResponse2 = this.listenResponse_;
                if (listenResponse2 == null || listenResponse2 == ListenResponse.DEFAULT_INSTANCE) {
                    this.listenResponse_ = listenResponse;
                } else {
                    this.listenResponse_ = ListenResponse.newBuilder(listenResponse2).mergeFrom(listenResponse).buildPartial();
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void mergeMatchingDocuments(RunQueryResponse runQueryResponse) {
                runQueryResponse.getClass();
                RunQueryResponse runQueryResponse2 = this.matchingDocuments_;
                if (runQueryResponse2 == null || runQueryResponse2 == RunQueryResponse.DEFAULT_INSTANCE) {
                    this.matchingDocuments_ = runQueryResponse;
                } else {
                    this.matchingDocuments_ = RunQueryResponse.newBuilder(runQueryResponse2).mergeFrom(runQueryResponse).buildPartial();
                }
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.createBuilder();
            }

            public static MatchingDocuments parseDelimitedFrom(InputStream inputStream) {
                return (MatchingDocuments) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static MatchingDocuments parseFrom(ByteBuffer byteBuffer) {
                return (MatchingDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
            }

            public static Parser<MatchingDocuments> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setListenResponse(ListenResponse listenResponse) {
                listenResponse.getClass();
                this.listenResponse_ = listenResponse;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setMatchingDocuments(RunQueryResponse runQueryResponse) {
                runQueryResponse.getClass();
                this.matchingDocuments_ = runQueryResponse;
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
                        return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\t", new Object[]{"listenResponse_", "matchingDocuments_"});
                    case NEW_MUTABLE_INSTANCE:
                        return new MatchingDocuments();
                    case NEW_BUILDER:
                        return new Builder(anonymousClass1);
                    case GET_DEFAULT_INSTANCE:
                        return DEFAULT_INSTANCE;
                    case GET_PARSER:
                        Parser<MatchingDocuments> defaultInstanceBasedParser = PARSER;
                        if (defaultInstanceBasedParser == null) {
                            synchronized (MatchingDocuments.class) {
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

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.MatchingDocumentsOrBuilder
            public ListenResponse getListenResponse() {
                ListenResponse listenResponse = this.listenResponse_;
                return listenResponse == null ? ListenResponse.DEFAULT_INSTANCE : listenResponse;
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.MatchingDocumentsOrBuilder
            public RunQueryResponse getMatchingDocuments() {
                RunQueryResponse runQueryResponse = this.matchingDocuments_;
                return runQueryResponse == null ? RunQueryResponse.DEFAULT_INSTANCE : runQueryResponse;
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.MatchingDocumentsOrBuilder
            public boolean hasListenResponse() {
                return this.listenResponse_ != null;
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.MatchingDocumentsOrBuilder
            public boolean hasMatchingDocuments() {
                return this.matchingDocuments_ != null;
            }

            public static final class Builder extends GeneratedMessageLite.Builder<MatchingDocuments, Builder> implements MatchingDocumentsOrBuilder {
                public Builder() {
                    super(MatchingDocuments.DEFAULT_INSTANCE);
                }

                public Builder clearListenResponse() {
                    copyOnWrite();
                    ((MatchingDocuments) this.instance).listenResponse_ = null;
                    return this;
                }

                public Builder clearMatchingDocuments() {
                    copyOnWrite();
                    ((MatchingDocuments) this.instance).matchingDocuments_ = null;
                    return this;
                }

                @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.MatchingDocumentsOrBuilder
                public ListenResponse getListenResponse() {
                    return ((MatchingDocuments) this.instance).getListenResponse();
                }

                @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.MatchingDocumentsOrBuilder
                public RunQueryResponse getMatchingDocuments() {
                    return ((MatchingDocuments) this.instance).getMatchingDocuments();
                }

                @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.MatchingDocumentsOrBuilder
                public boolean hasListenResponse() {
                    return ((MatchingDocuments) this.instance).hasListenResponse();
                }

                @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.MatchingDocumentsOrBuilder
                public boolean hasMatchingDocuments() {
                    return ((MatchingDocuments) this.instance).hasMatchingDocuments();
                }

                public Builder mergeListenResponse(ListenResponse listenResponse) {
                    copyOnWrite();
                    ((MatchingDocuments) this.instance).mergeListenResponse(listenResponse);
                    return this;
                }

                public Builder mergeMatchingDocuments(RunQueryResponse runQueryResponse) {
                    copyOnWrite();
                    ((MatchingDocuments) this.instance).mergeMatchingDocuments(runQueryResponse);
                    return this;
                }

                public Builder setListenResponse(ListenResponse listenResponse) {
                    copyOnWrite();
                    ((MatchingDocuments) this.instance).setListenResponse(listenResponse);
                    return this;
                }

                public Builder setMatchingDocuments(RunQueryResponse runQueryResponse) {
                    copyOnWrite();
                    ((MatchingDocuments) this.instance).setMatchingDocuments(runQueryResponse);
                    return this;
                }

                public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                    super(MatchingDocuments.DEFAULT_INSTANCE);
                }

                public Builder setListenResponse(ListenResponse.Builder builder) {
                    copyOnWrite();
                    ((MatchingDocuments) this.instance).setListenResponse(builder.build());
                    return this;
                }

                public Builder setMatchingDocuments(RunQueryResponse.Builder builder) {
                    copyOnWrite();
                    ((MatchingDocuments) this.instance).setMatchingDocuments(builder.build());
                    return this;
                }
            }

            public static Builder newBuilder(MatchingDocuments matchingDocuments) {
                return DEFAULT_INSTANCE.createBuilder(matchingDocuments);
            }

            public static MatchingDocuments parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (MatchingDocuments) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static MatchingDocuments parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
                return (MatchingDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
            }

            public static MatchingDocuments parseFrom(ByteString byteString) {
                return (MatchingDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
            }

            public static MatchingDocuments parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
                return (MatchingDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
            }

            public static MatchingDocuments parseFrom(byte[] bArr) {
                return (MatchingDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
            }

            public static MatchingDocuments parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
                return (MatchingDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
            }

            public static MatchingDocuments parseFrom(InputStream inputStream) {
                return (MatchingDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static MatchingDocuments parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (MatchingDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static MatchingDocuments parseFrom(CodedInputStream codedInputStream) {
                return (MatchingDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
            }

            public static MatchingDocuments parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (MatchingDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
            }
        }

        public interface MatchingDocumentsOrBuilder extends MessageLiteOrBuilder {
            ListenResponse getListenResponse();

            RunQueryResponse getMatchingDocuments();

            boolean hasListenResponse();

            boolean hasMatchingDocuments();
        }

        public static final class RemoveListen extends GeneratedMessageLite<RemoveListen, Builder> implements RemoveListenOrBuilder {
            public static final RemoveListen DEFAULT_INSTANCE;
            public static volatile Parser<RemoveListen> PARSER = null;
            public static final int TARGET_ID_FIELD_NUMBER = 1;
            public int targetId_;

            static {
                RemoveListen removeListen = new RemoveListen();
                DEFAULT_INSTANCE = removeListen;
                GeneratedMessageLite.defaultInstanceMap.put(RemoveListen.class, removeListen);
            }

            private void clearTargetId() {
                this.targetId_ = 0;
            }

            public static RemoveListen getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.createBuilder();
            }

            public static RemoveListen parseDelimitedFrom(InputStream inputStream) {
                return (RemoveListen) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static RemoveListen parseFrom(ByteBuffer byteBuffer) {
                return (RemoveListen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
            }

            public static Parser<RemoveListen> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }

            private void setTargetId(int i) {
                this.targetId_ = i;
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
                        return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u0004", new Object[]{"targetId_"});
                    case NEW_MUTABLE_INSTANCE:
                        return new RemoveListen();
                    case NEW_BUILDER:
                        return new Builder(anonymousClass1);
                    case GET_DEFAULT_INSTANCE:
                        return DEFAULT_INSTANCE;
                    case GET_PARSER:
                        Parser<RemoveListen> defaultInstanceBasedParser = PARSER;
                        if (defaultInstanceBasedParser == null) {
                            synchronized (RemoveListen.class) {
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

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.RemoveListenOrBuilder
            public int getTargetId() {
                return this.targetId_;
            }

            public static final class Builder extends GeneratedMessageLite.Builder<RemoveListen, Builder> implements RemoveListenOrBuilder {
                public Builder() {
                    super(RemoveListen.DEFAULT_INSTANCE);
                }

                public Builder clearTargetId() {
                    copyOnWrite();
                    ((RemoveListen) this.instance).targetId_ = 0;
                    return this;
                }

                @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.RemoveListenOrBuilder
                public int getTargetId() {
                    return ((RemoveListen) this.instance).getTargetId();
                }

                public Builder setTargetId(int i) {
                    copyOnWrite();
                    ((RemoveListen) this.instance).targetId_ = i;
                    return this;
                }

                public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                    super(RemoveListen.DEFAULT_INSTANCE);
                }
            }

            public static Builder newBuilder(RemoveListen removeListen) {
                return DEFAULT_INSTANCE.createBuilder(removeListen);
            }

            public static RemoveListen parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (RemoveListen) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static RemoveListen parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
                return (RemoveListen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
            }

            public static RemoveListen parseFrom(ByteString byteString) {
                return (RemoveListen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
            }

            public static RemoveListen parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
                return (RemoveListen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
            }

            public static RemoveListen parseFrom(byte[] bArr) {
                return (RemoveListen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
            }

            public static RemoveListen parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
                return (RemoveListen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
            }

            public static RemoveListen parseFrom(InputStream inputStream) {
                return (RemoveListen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static RemoveListen parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (RemoveListen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static RemoveListen parseFrom(CodedInputStream codedInputStream) {
                return (RemoveListen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
            }

            public static RemoveListen parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (RemoveListen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
            }
        }

        public interface RemoveListenOrBuilder extends MessageLiteOrBuilder {
            int getTargetId();
        }

        public static final class Rollback extends GeneratedMessageLite<Rollback, Builder> implements RollbackOrBuilder {
            public static final Rollback DEFAULT_INSTANCE;
            public static volatile Parser<Rollback> PARSER = null;
            public static final int REQUEST_FIELD_NUMBER = 1;
            public static final int RESPONSE_FIELD_NUMBER = 2;
            public RollbackRequest request_;
            public Empty response_;

            static {
                Rollback rollback = new Rollback();
                DEFAULT_INSTANCE = rollback;
                GeneratedMessageLite.defaultInstanceMap.put(Rollback.class, rollback);
            }

            private void clearRequest() {
                this.request_ = null;
            }

            private void clearResponse() {
                this.response_ = null;
            }

            public static Rollback getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void mergeRequest(RollbackRequest rollbackRequest) {
                rollbackRequest.getClass();
                RollbackRequest rollbackRequest2 = this.request_;
                if (rollbackRequest2 == null || rollbackRequest2 == RollbackRequest.DEFAULT_INSTANCE) {
                    this.request_ = rollbackRequest;
                } else {
                    this.request_ = RollbackRequest.newBuilder(rollbackRequest2).mergeFrom(rollbackRequest).buildPartial();
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void mergeResponse(Empty empty) {
                empty.getClass();
                Empty empty2 = this.response_;
                if (empty2 == null || empty2 == Empty.DEFAULT_INSTANCE) {
                    this.response_ = empty;
                } else {
                    this.response_ = Empty.newBuilder(empty2).mergeFrom(empty).buildPartial();
                }
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.createBuilder();
            }

            public static Rollback parseDelimitedFrom(InputStream inputStream) {
                return (Rollback) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static Rollback parseFrom(ByteBuffer byteBuffer) {
                return (Rollback) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
            }

            public static Parser<Rollback> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setRequest(RollbackRequest rollbackRequest) {
                rollbackRequest.getClass();
                this.request_ = rollbackRequest;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setResponse(Empty empty) {
                empty.getClass();
                this.response_ = empty;
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
                        return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\t", new Object[]{"request_", "response_"});
                    case NEW_MUTABLE_INSTANCE:
                        return new Rollback();
                    case NEW_BUILDER:
                        return new Builder(anonymousClass1);
                    case GET_DEFAULT_INSTANCE:
                        return DEFAULT_INSTANCE;
                    case GET_PARSER:
                        Parser<Rollback> defaultInstanceBasedParser = PARSER;
                        if (defaultInstanceBasedParser == null) {
                            synchronized (Rollback.class) {
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

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.RollbackOrBuilder
            public RollbackRequest getRequest() {
                RollbackRequest rollbackRequest = this.request_;
                return rollbackRequest == null ? RollbackRequest.DEFAULT_INSTANCE : rollbackRequest;
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.RollbackOrBuilder
            public Empty getResponse() {
                Empty empty = this.response_;
                return empty == null ? Empty.DEFAULT_INSTANCE : empty;
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.RollbackOrBuilder
            public boolean hasRequest() {
                return this.request_ != null;
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.RollbackOrBuilder
            public boolean hasResponse() {
                return this.response_ != null;
            }

            public static final class Builder extends GeneratedMessageLite.Builder<Rollback, Builder> implements RollbackOrBuilder {
                public Builder() {
                    super(Rollback.DEFAULT_INSTANCE);
                }

                public Builder clearRequest() {
                    copyOnWrite();
                    ((Rollback) this.instance).request_ = null;
                    return this;
                }

                public Builder clearResponse() {
                    copyOnWrite();
                    ((Rollback) this.instance).response_ = null;
                    return this;
                }

                @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.RollbackOrBuilder
                public RollbackRequest getRequest() {
                    return ((Rollback) this.instance).getRequest();
                }

                @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.RollbackOrBuilder
                public Empty getResponse() {
                    return ((Rollback) this.instance).getResponse();
                }

                @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.RollbackOrBuilder
                public boolean hasRequest() {
                    return ((Rollback) this.instance).hasRequest();
                }

                @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.RollbackOrBuilder
                public boolean hasResponse() {
                    return ((Rollback) this.instance).hasResponse();
                }

                public Builder mergeRequest(RollbackRequest rollbackRequest) {
                    copyOnWrite();
                    ((Rollback) this.instance).mergeRequest(rollbackRequest);
                    return this;
                }

                public Builder mergeResponse(Empty empty) {
                    copyOnWrite();
                    ((Rollback) this.instance).mergeResponse(empty);
                    return this;
                }

                public Builder setRequest(RollbackRequest rollbackRequest) {
                    copyOnWrite();
                    ((Rollback) this.instance).setRequest(rollbackRequest);
                    return this;
                }

                public Builder setResponse(Empty empty) {
                    copyOnWrite();
                    ((Rollback) this.instance).setResponse(empty);
                    return this;
                }

                public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                    super(Rollback.DEFAULT_INSTANCE);
                }

                public Builder setRequest(RollbackRequest.Builder builder) {
                    copyOnWrite();
                    ((Rollback) this.instance).setRequest(builder.build());
                    return this;
                }

                public Builder setResponse(Empty.Builder builder) {
                    copyOnWrite();
                    ((Rollback) this.instance).setResponse(builder.build());
                    return this;
                }
            }

            public static Builder newBuilder(Rollback rollback) {
                return DEFAULT_INSTANCE.createBuilder(rollback);
            }

            public static Rollback parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (Rollback) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static Rollback parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
                return (Rollback) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
            }

            public static Rollback parseFrom(ByteString byteString) {
                return (Rollback) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
            }

            public static Rollback parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
                return (Rollback) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
            }

            public static Rollback parseFrom(byte[] bArr) {
                return (Rollback) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
            }

            public static Rollback parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
                return (Rollback) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
            }

            public static Rollback parseFrom(InputStream inputStream) {
                return (Rollback) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static Rollback parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (Rollback) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static Rollback parseFrom(CodedInputStream codedInputStream) {
                return (Rollback) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
            }

            public static Rollback parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (Rollback) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
            }
        }

        public interface RollbackOrBuilder extends MessageLiteOrBuilder {
            RollbackRequest getRequest();

            Empty getResponse();

            boolean hasRequest();

            boolean hasResponse();
        }

        public static final class RunQuery extends GeneratedMessageLite<RunQuery, Builder> implements RunQueryOrBuilder {
            public static final RunQuery DEFAULT_INSTANCE;
            public static volatile Parser<RunQuery> PARSER = null;
            public static final int REQUEST_FIELD_NUMBER = 1;
            public static final int RESPONSE_FIELD_NUMBER = 2;
            public RunQueryRequest request_;
            public Internal.ProtobufList<RunQueryResponse> response_ = GeneratedMessageLite.emptyProtobufList();

            static {
                RunQuery runQuery = new RunQuery();
                DEFAULT_INSTANCE = runQuery;
                GeneratedMessageLite.defaultInstanceMap.put(RunQuery.class, runQuery);
            }

            public static /* synthetic */ void access$10800(RunQuery runQuery, Iterable iterable) {
                runQuery.ensureResponseIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, (List) runQuery.response_);
            }

            public static /* synthetic */ void access$11000(RunQuery runQuery, int i) {
                runQuery.ensureResponseIsMutable();
                runQuery.response_.remove(i);
            }

            private void addAllResponse(Iterable<? extends RunQueryResponse> iterable) {
                ensureResponseIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.response_);
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void addResponse(RunQueryResponse runQueryResponse) {
                runQueryResponse.getClass();
                ensureResponseIsMutable();
                this.response_.add(runQueryResponse);
            }

            private void clearRequest() {
                this.request_ = null;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void clearResponse() {
                this.response_ = GeneratedMessageLite.emptyProtobufList();
            }

            private void ensureResponseIsMutable() {
                Internal.ProtobufList<RunQueryResponse> protobufList = this.response_;
                if (protobufList.isModifiable()) {
                    return;
                }
                this.response_ = GeneratedMessageLite.mutableCopy(protobufList);
            }

            public static RunQuery getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void mergeRequest(RunQueryRequest runQueryRequest) {
                runQueryRequest.getClass();
                RunQueryRequest runQueryRequest2 = this.request_;
                if (runQueryRequest2 == null || runQueryRequest2 == RunQueryRequest.DEFAULT_INSTANCE) {
                    this.request_ = runQueryRequest;
                } else {
                    this.request_ = RunQueryRequest.newBuilder(runQueryRequest2).mergeFrom(runQueryRequest).buildPartial();
                }
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.createBuilder();
            }

            public static RunQuery parseDelimitedFrom(InputStream inputStream) {
                return (RunQuery) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static RunQuery parseFrom(ByteBuffer byteBuffer) {
                return (RunQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
            }

            public static Parser<RunQuery> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }

            private void removeResponse(int i) {
                ensureResponseIsMutable();
                this.response_.remove(i);
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setRequest(RunQueryRequest runQueryRequest) {
                runQueryRequest.getClass();
                this.request_ = runQueryRequest;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setResponse(int i, RunQueryResponse runQueryResponse) {
                runQueryResponse.getClass();
                ensureResponseIsMutable();
                this.response_.set(i, runQueryResponse);
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
                        return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\t\u0002\u001b", new Object[]{"request_", "response_", RunQueryResponse.class});
                    case NEW_MUTABLE_INSTANCE:
                        return new RunQuery();
                    case NEW_BUILDER:
                        return new Builder(anonymousClass1);
                    case GET_DEFAULT_INSTANCE:
                        return DEFAULT_INSTANCE;
                    case GET_PARSER:
                        Parser<RunQuery> defaultInstanceBasedParser = PARSER;
                        if (defaultInstanceBasedParser == null) {
                            synchronized (RunQuery.class) {
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

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.RunQueryOrBuilder
            public RunQueryRequest getRequest() {
                RunQueryRequest runQueryRequest = this.request_;
                return runQueryRequest == null ? RunQueryRequest.DEFAULT_INSTANCE : runQueryRequest;
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.RunQueryOrBuilder
            public RunQueryResponse getResponse(int i) {
                return this.response_.get(i);
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.RunQueryOrBuilder
            public int getResponseCount() {
                return this.response_.size();
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.RunQueryOrBuilder
            public List<RunQueryResponse> getResponseList() {
                return this.response_;
            }

            public RunQueryResponseOrBuilder getResponseOrBuilder(int i) {
                return this.response_.get(i);
            }

            public List<? extends RunQueryResponseOrBuilder> getResponseOrBuilderList() {
                return this.response_;
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.RunQueryOrBuilder
            public boolean hasRequest() {
                return this.request_ != null;
            }

            public static final class Builder extends GeneratedMessageLite.Builder<RunQuery, Builder> implements RunQueryOrBuilder {
                public Builder() {
                    super(RunQuery.DEFAULT_INSTANCE);
                }

                public Builder addAllResponse(Iterable<? extends RunQueryResponse> iterable) {
                    copyOnWrite();
                    RunQuery.access$10800((RunQuery) this.instance, iterable);
                    return this;
                }

                public Builder addResponse(RunQueryResponse runQueryResponse) {
                    copyOnWrite();
                    ((RunQuery) this.instance).addResponse(runQueryResponse);
                    return this;
                }

                public Builder clearRequest() {
                    copyOnWrite();
                    ((RunQuery) this.instance).request_ = null;
                    return this;
                }

                public Builder clearResponse() {
                    copyOnWrite();
                    ((RunQuery) this.instance).clearResponse();
                    return this;
                }

                @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.RunQueryOrBuilder
                public RunQueryRequest getRequest() {
                    return ((RunQuery) this.instance).getRequest();
                }

                @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.RunQueryOrBuilder
                public RunQueryResponse getResponse(int i) {
                    return ((RunQuery) this.instance).getResponse(i);
                }

                @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.RunQueryOrBuilder
                public int getResponseCount() {
                    return ((RunQuery) this.instance).getResponseCount();
                }

                @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.RunQueryOrBuilder
                public List<RunQueryResponse> getResponseList() {
                    return Collections.unmodifiableList(((RunQuery) this.instance).getResponseList());
                }

                @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.RunQueryOrBuilder
                public boolean hasRequest() {
                    return ((RunQuery) this.instance).hasRequest();
                }

                public Builder mergeRequest(RunQueryRequest runQueryRequest) {
                    copyOnWrite();
                    ((RunQuery) this.instance).mergeRequest(runQueryRequest);
                    return this;
                }

                public Builder removeResponse(int i) {
                    copyOnWrite();
                    RunQuery.access$11000((RunQuery) this.instance, i);
                    return this;
                }

                public Builder setRequest(RunQueryRequest runQueryRequest) {
                    copyOnWrite();
                    ((RunQuery) this.instance).setRequest(runQueryRequest);
                    return this;
                }

                public Builder setResponse(int i, RunQueryResponse runQueryResponse) {
                    copyOnWrite();
                    ((RunQuery) this.instance).setResponse(i, runQueryResponse);
                    return this;
                }

                public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                    super(RunQuery.DEFAULT_INSTANCE);
                }

                public Builder addResponse(int i, RunQueryResponse runQueryResponse) {
                    copyOnWrite();
                    ((RunQuery) this.instance).addResponse(i, runQueryResponse);
                    return this;
                }

                public Builder setRequest(RunQueryRequest.Builder builder) {
                    copyOnWrite();
                    ((RunQuery) this.instance).setRequest(builder.build());
                    return this;
                }

                public Builder setResponse(int i, RunQueryResponse.Builder builder) {
                    copyOnWrite();
                    ((RunQuery) this.instance).setResponse(i, builder.build());
                    return this;
                }

                public Builder addResponse(RunQueryResponse.Builder builder) {
                    copyOnWrite();
                    ((RunQuery) this.instance).addResponse(builder.build());
                    return this;
                }

                public Builder addResponse(int i, RunQueryResponse.Builder builder) {
                    copyOnWrite();
                    ((RunQuery) this.instance).addResponse(i, builder.build());
                    return this;
                }
            }

            public static Builder newBuilder(RunQuery runQuery) {
                return DEFAULT_INSTANCE.createBuilder(runQuery);
            }

            public static RunQuery parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (RunQuery) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static RunQuery parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
                return (RunQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
            }

            public static RunQuery parseFrom(ByteString byteString) {
                return (RunQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void addResponse(int i, RunQueryResponse runQueryResponse) {
                runQueryResponse.getClass();
                ensureResponseIsMutable();
                this.response_.add(i, runQueryResponse);
            }

            public static RunQuery parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
                return (RunQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
            }

            public static RunQuery parseFrom(byte[] bArr) {
                return (RunQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
            }

            public static RunQuery parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
                return (RunQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
            }

            public static RunQuery parseFrom(InputStream inputStream) {
                return (RunQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static RunQuery parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (RunQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static RunQuery parseFrom(CodedInputStream codedInputStream) {
                return (RunQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
            }

            public static RunQuery parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (RunQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
            }
        }

        public interface RunQueryOrBuilder extends MessageLiteOrBuilder {
            RunQueryRequest getRequest();

            RunQueryResponse getResponse(int i);

            int getResponseCount();

            List<RunQueryResponse> getResponseList();

            boolean hasRequest();
        }

        public static final class UpdateDocument extends GeneratedMessageLite<UpdateDocument, Builder> implements UpdateDocumentOrBuilder {
            public static final UpdateDocument DEFAULT_INSTANCE;
            public static volatile Parser<UpdateDocument> PARSER = null;
            public static final int REQUEST_FIELD_NUMBER = 1;
            public static final int RESPONSE_FIELD_NUMBER = 2;
            public UpdateDocumentRequest request_;
            public Document response_;

            static {
                UpdateDocument updateDocument = new UpdateDocument();
                DEFAULT_INSTANCE = updateDocument;
                GeneratedMessageLite.defaultInstanceMap.put(UpdateDocument.class, updateDocument);
            }

            private void clearRequest() {
                this.request_ = null;
            }

            private void clearResponse() {
                this.response_ = null;
            }

            public static UpdateDocument getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void mergeRequest(UpdateDocumentRequest updateDocumentRequest) {
                updateDocumentRequest.getClass();
                UpdateDocumentRequest updateDocumentRequest2 = this.request_;
                if (updateDocumentRequest2 == null || updateDocumentRequest2 == UpdateDocumentRequest.DEFAULT_INSTANCE) {
                    this.request_ = updateDocumentRequest;
                } else {
                    this.request_ = UpdateDocumentRequest.newBuilder(updateDocumentRequest2).mergeFrom(updateDocumentRequest).buildPartial();
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void mergeResponse(Document document) {
                document.getClass();
                Document document2 = this.response_;
                if (document2 == null || document2 == Document.DEFAULT_INSTANCE) {
                    this.response_ = document;
                } else {
                    this.response_ = Document.newBuilder(document2).mergeFrom(document).buildPartial();
                }
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.createBuilder();
            }

            public static UpdateDocument parseDelimitedFrom(InputStream inputStream) {
                return (UpdateDocument) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static UpdateDocument parseFrom(ByteBuffer byteBuffer) {
                return (UpdateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
            }

            public static Parser<UpdateDocument> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setRequest(UpdateDocumentRequest updateDocumentRequest) {
                updateDocumentRequest.getClass();
                this.request_ = updateDocumentRequest;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setResponse(Document document) {
                document.getClass();
                this.response_ = document;
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
                        return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\t", new Object[]{"request_", "response_"});
                    case NEW_MUTABLE_INSTANCE:
                        return new UpdateDocument();
                    case NEW_BUILDER:
                        return new Builder(anonymousClass1);
                    case GET_DEFAULT_INSTANCE:
                        return DEFAULT_INSTANCE;
                    case GET_PARSER:
                        Parser<UpdateDocument> defaultInstanceBasedParser = PARSER;
                        if (defaultInstanceBasedParser == null) {
                            synchronized (UpdateDocument.class) {
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

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.UpdateDocumentOrBuilder
            public UpdateDocumentRequest getRequest() {
                UpdateDocumentRequest updateDocumentRequest = this.request_;
                return updateDocumentRequest == null ? UpdateDocumentRequest.DEFAULT_INSTANCE : updateDocumentRequest;
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.UpdateDocumentOrBuilder
            public Document getResponse() {
                Document document = this.response_;
                return document == null ? Document.DEFAULT_INSTANCE : document;
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.UpdateDocumentOrBuilder
            public boolean hasRequest() {
                return this.request_ != null;
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.UpdateDocumentOrBuilder
            public boolean hasResponse() {
                return this.response_ != null;
            }

            public static final class Builder extends GeneratedMessageLite.Builder<UpdateDocument, Builder> implements UpdateDocumentOrBuilder {
                public Builder() {
                    super(UpdateDocument.DEFAULT_INSTANCE);
                }

                public Builder clearRequest() {
                    copyOnWrite();
                    ((UpdateDocument) this.instance).request_ = null;
                    return this;
                }

                public Builder clearResponse() {
                    copyOnWrite();
                    ((UpdateDocument) this.instance).response_ = null;
                    return this;
                }

                @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.UpdateDocumentOrBuilder
                public UpdateDocumentRequest getRequest() {
                    return ((UpdateDocument) this.instance).getRequest();
                }

                @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.UpdateDocumentOrBuilder
                public Document getResponse() {
                    return ((UpdateDocument) this.instance).getResponse();
                }

                @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.UpdateDocumentOrBuilder
                public boolean hasRequest() {
                    return ((UpdateDocument) this.instance).hasRequest();
                }

                @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.UpdateDocumentOrBuilder
                public boolean hasResponse() {
                    return ((UpdateDocument) this.instance).hasResponse();
                }

                public Builder mergeRequest(UpdateDocumentRequest updateDocumentRequest) {
                    copyOnWrite();
                    ((UpdateDocument) this.instance).mergeRequest(updateDocumentRequest);
                    return this;
                }

                public Builder mergeResponse(Document document) {
                    copyOnWrite();
                    ((UpdateDocument) this.instance).mergeResponse(document);
                    return this;
                }

                public Builder setRequest(UpdateDocumentRequest updateDocumentRequest) {
                    copyOnWrite();
                    ((UpdateDocument) this.instance).setRequest(updateDocumentRequest);
                    return this;
                }

                public Builder setResponse(Document document) {
                    copyOnWrite();
                    ((UpdateDocument) this.instance).setResponse(document);
                    return this;
                }

                public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                    super(UpdateDocument.DEFAULT_INSTANCE);
                }

                public Builder setRequest(UpdateDocumentRequest.Builder builder) {
                    copyOnWrite();
                    ((UpdateDocument) this.instance).setRequest(builder.build());
                    return this;
                }

                public Builder setResponse(Document.Builder builder) {
                    copyOnWrite();
                    ((UpdateDocument) this.instance).setResponse(builder.build());
                    return this;
                }
            }

            public static Builder newBuilder(UpdateDocument updateDocument) {
                return DEFAULT_INSTANCE.createBuilder(updateDocument);
            }

            public static UpdateDocument parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (UpdateDocument) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static UpdateDocument parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
                return (UpdateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
            }

            public static UpdateDocument parseFrom(ByteString byteString) {
                return (UpdateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
            }

            public static UpdateDocument parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
                return (UpdateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
            }

            public static UpdateDocument parseFrom(byte[] bArr) {
                return (UpdateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
            }

            public static UpdateDocument parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
                return (UpdateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
            }

            public static UpdateDocument parseFrom(InputStream inputStream) {
                return (UpdateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static UpdateDocument parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (UpdateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static UpdateDocument parseFrom(CodedInputStream codedInputStream) {
                return (UpdateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
            }

            public static UpdateDocument parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (UpdateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
            }
        }

        public interface UpdateDocumentOrBuilder extends MessageLiteOrBuilder {
            UpdateDocumentRequest getRequest();

            Document getResponse();

            boolean hasRequest();

            boolean hasResponse();
        }

        static {
            FirestoreV1Action firestoreV1Action = new FirestoreV1Action();
            DEFAULT_INSTANCE = firestoreV1Action;
            GeneratedMessageLite.defaultInstanceMap.put(FirestoreV1Action.class, firestoreV1Action);
        }

        public static /* synthetic */ void access$18200(FirestoreV1Action firestoreV1Action, Iterable iterable) {
            firestoreV1Action.ensureMatchingDocumentsIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, (List) firestoreV1Action.matchingDocuments_);
        }

        public static /* synthetic */ void access$18400(FirestoreV1Action firestoreV1Action, int i) {
            firestoreV1Action.ensureMatchingDocumentsIsMutable();
            firestoreV1Action.matchingDocuments_.remove(i);
        }

        private void addAllMatchingDocuments(Iterable<? extends MatchingDocuments> iterable) {
            ensureMatchingDocumentsIsMutable();
            AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.matchingDocuments_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addMatchingDocuments(MatchingDocuments matchingDocuments) {
            matchingDocuments.getClass();
            ensureMatchingDocumentsIsMutable();
            this.matchingDocuments_.add(matchingDocuments);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAction() {
            this.actionCase_ = 0;
            this.action_ = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearBatchGetDocuments() {
            if (this.actionCase_ == 10) {
                this.actionCase_ = 0;
                this.action_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearBeginTransaction() {
            if (this.actionCase_ == 6) {
                this.actionCase_ = 0;
                this.action_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCommit() {
            if (this.actionCase_ == 7) {
                this.actionCase_ = 0;
                this.action_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCreateDocument() {
            if (this.actionCase_ == 3) {
                this.actionCase_ = 0;
                this.action_ = null;
            }
        }

        private void clearDatabaseContentsBeforeAction() {
            this.databaseContentsBeforeAction_ = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDeleteDocument() {
            if (this.actionCase_ == 5) {
                this.actionCase_ = 0;
                this.action_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearGetDocument() {
            if (this.actionCase_ == 1) {
                this.actionCase_ = 0;
                this.action_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearListCollectionIds() {
            if (this.actionCase_ == 9) {
                this.actionCase_ = 0;
                this.action_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearListDocuments() {
            if (this.actionCase_ == 2) {
                this.actionCase_ = 0;
                this.action_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearListen() {
            if (this.actionCase_ == 12) {
                this.actionCase_ = 0;
                this.action_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearMatchingDocuments() {
            this.matchingDocuments_ = GeneratedMessageLite.emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearRemoveListen() {
            if (this.actionCase_ == 13) {
                this.actionCase_ = 0;
                this.action_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearRollback() {
            if (this.actionCase_ == 8) {
                this.actionCase_ = 0;
                this.action_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearRunQuery() {
            if (this.actionCase_ == 11) {
                this.actionCase_ = 0;
                this.action_ = null;
            }
        }

        private void clearStatus() {
            this.status_ = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearUpdateDocument() {
            if (this.actionCase_ == 4) {
                this.actionCase_ = 0;
                this.action_ = null;
            }
        }

        private void ensureMatchingDocumentsIsMutable() {
            Internal.ProtobufList<MatchingDocuments> protobufList = this.matchingDocuments_;
            if (protobufList.isModifiable()) {
                return;
            }
            this.matchingDocuments_ = GeneratedMessageLite.mutableCopy(protobufList);
        }

        public static FirestoreV1Action getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeBatchGetDocuments(BatchGetDocuments batchGetDocuments) {
            Object obj;
            batchGetDocuments.getClass();
            if (this.actionCase_ != 10 || (obj = this.action_) == BatchGetDocuments.DEFAULT_INSTANCE) {
                this.action_ = batchGetDocuments;
            } else {
                this.action_ = BatchGetDocuments.newBuilder((BatchGetDocuments) obj).mergeFrom(batchGetDocuments).buildPartial();
            }
            this.actionCase_ = 10;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeBeginTransaction(BeginTransaction beginTransaction) {
            Object obj;
            beginTransaction.getClass();
            if (this.actionCase_ != 6 || (obj = this.action_) == BeginTransaction.DEFAULT_INSTANCE) {
                this.action_ = beginTransaction;
            } else {
                this.action_ = BeginTransaction.newBuilder((BeginTransaction) obj).mergeFrom(beginTransaction).buildPartial();
            }
            this.actionCase_ = 6;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeCommit(Commit commit) {
            Object obj;
            commit.getClass();
            if (this.actionCase_ != 7 || (obj = this.action_) == Commit.DEFAULT_INSTANCE) {
                this.action_ = commit;
            } else {
                this.action_ = Commit.newBuilder((Commit) obj).mergeFrom(commit).buildPartial();
            }
            this.actionCase_ = 7;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeCreateDocument(CreateDocument createDocument) {
            Object obj;
            createDocument.getClass();
            if (this.actionCase_ != 3 || (obj = this.action_) == CreateDocument.DEFAULT_INSTANCE) {
                this.action_ = createDocument;
            } else {
                this.action_ = CreateDocument.newBuilder((CreateDocument) obj).mergeFrom(createDocument).buildPartial();
            }
            this.actionCase_ = 3;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeDatabaseContentsBeforeAction(RunQuery runQuery) {
            runQuery.getClass();
            RunQuery runQuery2 = this.databaseContentsBeforeAction_;
            if (runQuery2 == null || runQuery2 == RunQuery.DEFAULT_INSTANCE) {
                this.databaseContentsBeforeAction_ = runQuery;
            } else {
                this.databaseContentsBeforeAction_ = RunQuery.newBuilder(runQuery2).mergeFrom(runQuery).buildPartial();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeDeleteDocument(DeleteDocument deleteDocument) {
            Object obj;
            deleteDocument.getClass();
            if (this.actionCase_ != 5 || (obj = this.action_) == DeleteDocument.DEFAULT_INSTANCE) {
                this.action_ = deleteDocument;
            } else {
                this.action_ = DeleteDocument.newBuilder((DeleteDocument) obj).mergeFrom(deleteDocument).buildPartial();
            }
            this.actionCase_ = 5;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeGetDocument(GetDocument getDocument) {
            Object obj;
            getDocument.getClass();
            if (this.actionCase_ != 1 || (obj = this.action_) == GetDocument.DEFAULT_INSTANCE) {
                this.action_ = getDocument;
            } else {
                this.action_ = GetDocument.newBuilder((GetDocument) obj).mergeFrom(getDocument).buildPartial();
            }
            this.actionCase_ = 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeListCollectionIds(ListCollectionIds listCollectionIds) {
            Object obj;
            listCollectionIds.getClass();
            if (this.actionCase_ != 9 || (obj = this.action_) == ListCollectionIds.DEFAULT_INSTANCE) {
                this.action_ = listCollectionIds;
            } else {
                this.action_ = ListCollectionIds.newBuilder((ListCollectionIds) obj).mergeFrom(listCollectionIds).buildPartial();
            }
            this.actionCase_ = 9;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeListDocuments(ListDocuments listDocuments) {
            Object obj;
            listDocuments.getClass();
            if (this.actionCase_ != 2 || (obj = this.action_) == ListDocuments.DEFAULT_INSTANCE) {
                this.action_ = listDocuments;
            } else {
                this.action_ = ListDocuments.newBuilder((ListDocuments) obj).mergeFrom(listDocuments).buildPartial();
            }
            this.actionCase_ = 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeListen(Listen listen) {
            Object obj;
            listen.getClass();
            if (this.actionCase_ != 12 || (obj = this.action_) == Listen.DEFAULT_INSTANCE) {
                this.action_ = listen;
            } else {
                this.action_ = Listen.newBuilder((Listen) obj).mergeFrom(listen).buildPartial();
            }
            this.actionCase_ = 12;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeRemoveListen(RemoveListen removeListen) {
            Object obj;
            removeListen.getClass();
            if (this.actionCase_ != 13 || (obj = this.action_) == RemoveListen.DEFAULT_INSTANCE) {
                this.action_ = removeListen;
            } else {
                this.action_ = RemoveListen.newBuilder((RemoveListen) obj).mergeFrom(removeListen).buildPartial();
            }
            this.actionCase_ = 13;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeRollback(Rollback rollback) {
            Object obj;
            rollback.getClass();
            if (this.actionCase_ != 8 || (obj = this.action_) == Rollback.DEFAULT_INSTANCE) {
                this.action_ = rollback;
            } else {
                this.action_ = Rollback.newBuilder((Rollback) obj).mergeFrom(rollback).buildPartial();
            }
            this.actionCase_ = 8;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeRunQuery(RunQuery runQuery) {
            Object obj;
            runQuery.getClass();
            if (this.actionCase_ != 11 || (obj = this.action_) == RunQuery.DEFAULT_INSTANCE) {
                this.action_ = runQuery;
            } else {
                this.action_ = RunQuery.newBuilder((RunQuery) obj).mergeFrom(runQuery).buildPartial();
            }
            this.actionCase_ = 11;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeStatus(StatusProto statusProto) {
            statusProto.getClass();
            StatusProto statusProto2 = this.status_;
            if (statusProto2 == null || statusProto2 == StatusProto.DEFAULT_INSTANCE) {
                this.status_ = statusProto;
            } else {
                this.status_ = StatusProto.newBuilder(statusProto2).mergeFrom(statusProto).buildPartial();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeUpdateDocument(UpdateDocument updateDocument) {
            Object obj;
            updateDocument.getClass();
            if (this.actionCase_ != 4 || (obj = this.action_) == UpdateDocument.DEFAULT_INSTANCE) {
                this.action_ = updateDocument;
            } else {
                this.action_ = UpdateDocument.newBuilder((UpdateDocument) obj).mergeFrom(updateDocument).buildPartial();
            }
            this.actionCase_ = 4;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static FirestoreV1Action parseDelimitedFrom(InputStream inputStream) {
            return (FirestoreV1Action) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static FirestoreV1Action parseFrom(ByteBuffer byteBuffer) {
            return (FirestoreV1Action) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<FirestoreV1Action> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        private void removeMatchingDocuments(int i) {
            ensureMatchingDocumentsIsMutable();
            this.matchingDocuments_.remove(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBatchGetDocuments(BatchGetDocuments batchGetDocuments) {
            batchGetDocuments.getClass();
            this.action_ = batchGetDocuments;
            this.actionCase_ = 10;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBeginTransaction(BeginTransaction beginTransaction) {
            beginTransaction.getClass();
            this.action_ = beginTransaction;
            this.actionCase_ = 6;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCommit(Commit commit) {
            commit.getClass();
            this.action_ = commit;
            this.actionCase_ = 7;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCreateDocument(CreateDocument createDocument) {
            createDocument.getClass();
            this.action_ = createDocument;
            this.actionCase_ = 3;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDatabaseContentsBeforeAction(RunQuery runQuery) {
            runQuery.getClass();
            this.databaseContentsBeforeAction_ = runQuery;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeleteDocument(DeleteDocument deleteDocument) {
            deleteDocument.getClass();
            this.action_ = deleteDocument;
            this.actionCase_ = 5;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetDocument(GetDocument getDocument) {
            getDocument.getClass();
            this.action_ = getDocument;
            this.actionCase_ = 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setListCollectionIds(ListCollectionIds listCollectionIds) {
            listCollectionIds.getClass();
            this.action_ = listCollectionIds;
            this.actionCase_ = 9;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setListDocuments(ListDocuments listDocuments) {
            listDocuments.getClass();
            this.action_ = listDocuments;
            this.actionCase_ = 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setListen(Listen listen) {
            listen.getClass();
            this.action_ = listen;
            this.actionCase_ = 12;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMatchingDocuments(int i, MatchingDocuments matchingDocuments) {
            matchingDocuments.getClass();
            ensureMatchingDocumentsIsMutable();
            this.matchingDocuments_.set(i, matchingDocuments);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRemoveListen(RemoveListen removeListen) {
            removeListen.getClass();
            this.action_ = removeListen;
            this.actionCase_ = 13;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRollback(Rollback rollback) {
            rollback.getClass();
            this.action_ = rollback;
            this.actionCase_ = 8;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRunQuery(RunQuery runQuery) {
            runQuery.getClass();
            this.action_ = runQuery;
            this.actionCase_ = 11;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setStatus(StatusProto statusProto) {
            statusProto.getClass();
            this.status_ = statusProto;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUpdateDocument(UpdateDocument updateDocument) {
            updateDocument.getClass();
            this.action_ = updateDocument;
            this.actionCase_ = 4;
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
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0010\u0001\u0000\u0001Ë\u0010\u0000\u0001\u0000\u0001<\u0000\u0002<\u0000\u0003<\u0000\u0004<\u0000\u0005<\u0000\u0006<\u0000\u0007<\u0000\b<\u0000\t<\u0000\n<\u0000\u000b<\u0000\f<\u0000\r<\u0000É\tÊ\tË\u001b", new Object[]{"action_", "actionCase_", GetDocument.class, ListDocuments.class, CreateDocument.class, UpdateDocument.class, DeleteDocument.class, BeginTransaction.class, Commit.class, Rollback.class, ListCollectionIds.class, BatchGetDocuments.class, RunQuery.class, Listen.class, RemoveListen.class, "status_", "databaseContentsBeforeAction_", "matchingDocuments_", MatchingDocuments.class});
                case NEW_MUTABLE_INSTANCE:
                    return new FirestoreV1Action();
                case NEW_BUILDER:
                    return new Builder(anonymousClass1);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser<FirestoreV1Action> defaultInstanceBasedParser = PARSER;
                    if (defaultInstanceBasedParser == null) {
                        synchronized (FirestoreV1Action.class) {
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

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
        public ActionCase getActionCase() {
            return ActionCase.forNumber(this.actionCase_);
        }

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
        public BatchGetDocuments getBatchGetDocuments() {
            return this.actionCase_ == 10 ? (BatchGetDocuments) this.action_ : BatchGetDocuments.DEFAULT_INSTANCE;
        }

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
        public BeginTransaction getBeginTransaction() {
            return this.actionCase_ == 6 ? (BeginTransaction) this.action_ : BeginTransaction.DEFAULT_INSTANCE;
        }

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
        public Commit getCommit() {
            return this.actionCase_ == 7 ? (Commit) this.action_ : Commit.DEFAULT_INSTANCE;
        }

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
        public CreateDocument getCreateDocument() {
            return this.actionCase_ == 3 ? (CreateDocument) this.action_ : CreateDocument.DEFAULT_INSTANCE;
        }

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
        public RunQuery getDatabaseContentsBeforeAction() {
            RunQuery runQuery = this.databaseContentsBeforeAction_;
            return runQuery == null ? RunQuery.DEFAULT_INSTANCE : runQuery;
        }

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
        public DeleteDocument getDeleteDocument() {
            return this.actionCase_ == 5 ? (DeleteDocument) this.action_ : DeleteDocument.DEFAULT_INSTANCE;
        }

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
        public GetDocument getGetDocument() {
            return this.actionCase_ == 1 ? (GetDocument) this.action_ : GetDocument.DEFAULT_INSTANCE;
        }

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
        public ListCollectionIds getListCollectionIds() {
            return this.actionCase_ == 9 ? (ListCollectionIds) this.action_ : ListCollectionIds.DEFAULT_INSTANCE;
        }

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
        public ListDocuments getListDocuments() {
            return this.actionCase_ == 2 ? (ListDocuments) this.action_ : ListDocuments.DEFAULT_INSTANCE;
        }

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
        public Listen getListen() {
            return this.actionCase_ == 12 ? (Listen) this.action_ : Listen.DEFAULT_INSTANCE;
        }

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
        public MatchingDocuments getMatchingDocuments(int i) {
            return this.matchingDocuments_.get(i);
        }

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
        public int getMatchingDocumentsCount() {
            return this.matchingDocuments_.size();
        }

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
        public List<MatchingDocuments> getMatchingDocumentsList() {
            return this.matchingDocuments_;
        }

        public MatchingDocumentsOrBuilder getMatchingDocumentsOrBuilder(int i) {
            return this.matchingDocuments_.get(i);
        }

        public List<? extends MatchingDocumentsOrBuilder> getMatchingDocumentsOrBuilderList() {
            return this.matchingDocuments_;
        }

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
        public RemoveListen getRemoveListen() {
            return this.actionCase_ == 13 ? (RemoveListen) this.action_ : RemoveListen.DEFAULT_INSTANCE;
        }

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
        public Rollback getRollback() {
            return this.actionCase_ == 8 ? (Rollback) this.action_ : Rollback.DEFAULT_INSTANCE;
        }

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
        public RunQuery getRunQuery() {
            return this.actionCase_ == 11 ? (RunQuery) this.action_ : RunQuery.DEFAULT_INSTANCE;
        }

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
        public StatusProto getStatus() {
            StatusProto statusProto = this.status_;
            return statusProto == null ? StatusProto.DEFAULT_INSTANCE : statusProto;
        }

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
        public UpdateDocument getUpdateDocument() {
            return this.actionCase_ == 4 ? (UpdateDocument) this.action_ : UpdateDocument.DEFAULT_INSTANCE;
        }

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
        public boolean hasBatchGetDocuments() {
            return this.actionCase_ == 10;
        }

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
        public boolean hasBeginTransaction() {
            return this.actionCase_ == 6;
        }

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
        public boolean hasCommit() {
            return this.actionCase_ == 7;
        }

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
        public boolean hasCreateDocument() {
            return this.actionCase_ == 3;
        }

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
        public boolean hasDatabaseContentsBeforeAction() {
            return this.databaseContentsBeforeAction_ != null;
        }

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
        public boolean hasDeleteDocument() {
            return this.actionCase_ == 5;
        }

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
        public boolean hasGetDocument() {
            return this.actionCase_ == 1;
        }

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
        public boolean hasListCollectionIds() {
            return this.actionCase_ == 9;
        }

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
        public boolean hasListDocuments() {
            return this.actionCase_ == 2;
        }

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
        public boolean hasListen() {
            return this.actionCase_ == 12;
        }

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
        public boolean hasRemoveListen() {
            return this.actionCase_ == 13;
        }

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
        public boolean hasRollback() {
            return this.actionCase_ == 8;
        }

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
        public boolean hasRunQuery() {
            return this.actionCase_ == 11;
        }

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
        public boolean hasStatus() {
            return this.status_ != null;
        }

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
        public boolean hasUpdateDocument() {
            return this.actionCase_ == 4;
        }

        public static final class Builder extends GeneratedMessageLite.Builder<FirestoreV1Action, Builder> implements FirestoreV1ActionOrBuilder {
            public Builder() {
                super(FirestoreV1Action.DEFAULT_INSTANCE);
            }

            public Builder addAllMatchingDocuments(Iterable<? extends MatchingDocuments> iterable) {
                copyOnWrite();
                FirestoreV1Action.access$18200((FirestoreV1Action) this.instance, iterable);
                return this;
            }

            public Builder addMatchingDocuments(MatchingDocuments matchingDocuments) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).addMatchingDocuments(matchingDocuments);
                return this;
            }

            public Builder clearAction() {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).clearAction();
                return this;
            }

            public Builder clearBatchGetDocuments() {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).clearBatchGetDocuments();
                return this;
            }

            public Builder clearBeginTransaction() {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).clearBeginTransaction();
                return this;
            }

            public Builder clearCommit() {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).clearCommit();
                return this;
            }

            public Builder clearCreateDocument() {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).clearCreateDocument();
                return this;
            }

            public Builder clearDatabaseContentsBeforeAction() {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).databaseContentsBeforeAction_ = null;
                return this;
            }

            public Builder clearDeleteDocument() {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).clearDeleteDocument();
                return this;
            }

            public Builder clearGetDocument() {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).clearGetDocument();
                return this;
            }

            public Builder clearListCollectionIds() {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).clearListCollectionIds();
                return this;
            }

            public Builder clearListDocuments() {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).clearListDocuments();
                return this;
            }

            public Builder clearListen() {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).clearListen();
                return this;
            }

            public Builder clearMatchingDocuments() {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).clearMatchingDocuments();
                return this;
            }

            public Builder clearRemoveListen() {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).clearRemoveListen();
                return this;
            }

            public Builder clearRollback() {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).clearRollback();
                return this;
            }

            public Builder clearRunQuery() {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).clearRunQuery();
                return this;
            }

            public Builder clearStatus() {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).status_ = null;
                return this;
            }

            public Builder clearUpdateDocument() {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).clearUpdateDocument();
                return this;
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
            public ActionCase getActionCase() {
                return ((FirestoreV1Action) this.instance).getActionCase();
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
            public BatchGetDocuments getBatchGetDocuments() {
                return ((FirestoreV1Action) this.instance).getBatchGetDocuments();
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
            public BeginTransaction getBeginTransaction() {
                return ((FirestoreV1Action) this.instance).getBeginTransaction();
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
            public Commit getCommit() {
                return ((FirestoreV1Action) this.instance).getCommit();
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
            public CreateDocument getCreateDocument() {
                return ((FirestoreV1Action) this.instance).getCreateDocument();
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
            public RunQuery getDatabaseContentsBeforeAction() {
                return ((FirestoreV1Action) this.instance).getDatabaseContentsBeforeAction();
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
            public DeleteDocument getDeleteDocument() {
                return ((FirestoreV1Action) this.instance).getDeleteDocument();
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
            public GetDocument getGetDocument() {
                return ((FirestoreV1Action) this.instance).getGetDocument();
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
            public ListCollectionIds getListCollectionIds() {
                return ((FirestoreV1Action) this.instance).getListCollectionIds();
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
            public ListDocuments getListDocuments() {
                return ((FirestoreV1Action) this.instance).getListDocuments();
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
            public Listen getListen() {
                return ((FirestoreV1Action) this.instance).getListen();
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
            public MatchingDocuments getMatchingDocuments(int i) {
                return ((FirestoreV1Action) this.instance).getMatchingDocuments(i);
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
            public int getMatchingDocumentsCount() {
                return ((FirestoreV1Action) this.instance).getMatchingDocumentsCount();
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
            public List<MatchingDocuments> getMatchingDocumentsList() {
                return Collections.unmodifiableList(((FirestoreV1Action) this.instance).getMatchingDocumentsList());
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
            public RemoveListen getRemoveListen() {
                return ((FirestoreV1Action) this.instance).getRemoveListen();
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
            public Rollback getRollback() {
                return ((FirestoreV1Action) this.instance).getRollback();
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
            public RunQuery getRunQuery() {
                return ((FirestoreV1Action) this.instance).getRunQuery();
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
            public StatusProto getStatus() {
                return ((FirestoreV1Action) this.instance).getStatus();
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
            public UpdateDocument getUpdateDocument() {
                return ((FirestoreV1Action) this.instance).getUpdateDocument();
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
            public boolean hasBatchGetDocuments() {
                return ((FirestoreV1Action) this.instance).hasBatchGetDocuments();
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
            public boolean hasBeginTransaction() {
                return ((FirestoreV1Action) this.instance).hasBeginTransaction();
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
            public boolean hasCommit() {
                return ((FirestoreV1Action) this.instance).hasCommit();
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
            public boolean hasCreateDocument() {
                return ((FirestoreV1Action) this.instance).hasCreateDocument();
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
            public boolean hasDatabaseContentsBeforeAction() {
                return ((FirestoreV1Action) this.instance).hasDatabaseContentsBeforeAction();
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
            public boolean hasDeleteDocument() {
                return ((FirestoreV1Action) this.instance).hasDeleteDocument();
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
            public boolean hasGetDocument() {
                return ((FirestoreV1Action) this.instance).hasGetDocument();
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
            public boolean hasListCollectionIds() {
                return ((FirestoreV1Action) this.instance).hasListCollectionIds();
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
            public boolean hasListDocuments() {
                return ((FirestoreV1Action) this.instance).hasListDocuments();
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
            public boolean hasListen() {
                return ((FirestoreV1Action) this.instance).hasListen();
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
            public boolean hasRemoveListen() {
                return ((FirestoreV1Action) this.instance).hasRemoveListen();
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
            public boolean hasRollback() {
                return ((FirestoreV1Action) this.instance).hasRollback();
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
            public boolean hasRunQuery() {
                return ((FirestoreV1Action) this.instance).hasRunQuery();
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
            public boolean hasStatus() {
                return ((FirestoreV1Action) this.instance).hasStatus();
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1ActionOrBuilder
            public boolean hasUpdateDocument() {
                return ((FirestoreV1Action) this.instance).hasUpdateDocument();
            }

            public Builder mergeBatchGetDocuments(BatchGetDocuments batchGetDocuments) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).mergeBatchGetDocuments(batchGetDocuments);
                return this;
            }

            public Builder mergeBeginTransaction(BeginTransaction beginTransaction) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).mergeBeginTransaction(beginTransaction);
                return this;
            }

            public Builder mergeCommit(Commit commit) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).mergeCommit(commit);
                return this;
            }

            public Builder mergeCreateDocument(CreateDocument createDocument) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).mergeCreateDocument(createDocument);
                return this;
            }

            public Builder mergeDatabaseContentsBeforeAction(RunQuery runQuery) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).mergeDatabaseContentsBeforeAction(runQuery);
                return this;
            }

            public Builder mergeDeleteDocument(DeleteDocument deleteDocument) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).mergeDeleteDocument(deleteDocument);
                return this;
            }

            public Builder mergeGetDocument(GetDocument getDocument) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).mergeGetDocument(getDocument);
                return this;
            }

            public Builder mergeListCollectionIds(ListCollectionIds listCollectionIds) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).mergeListCollectionIds(listCollectionIds);
                return this;
            }

            public Builder mergeListDocuments(ListDocuments listDocuments) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).mergeListDocuments(listDocuments);
                return this;
            }

            public Builder mergeListen(Listen listen) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).mergeListen(listen);
                return this;
            }

            public Builder mergeRemoveListen(RemoveListen removeListen) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).mergeRemoveListen(removeListen);
                return this;
            }

            public Builder mergeRollback(Rollback rollback) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).mergeRollback(rollback);
                return this;
            }

            public Builder mergeRunQuery(RunQuery runQuery) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).mergeRunQuery(runQuery);
                return this;
            }

            public Builder mergeStatus(StatusProto statusProto) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).mergeStatus(statusProto);
                return this;
            }

            public Builder mergeUpdateDocument(UpdateDocument updateDocument) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).mergeUpdateDocument(updateDocument);
                return this;
            }

            public Builder removeMatchingDocuments(int i) {
                copyOnWrite();
                FirestoreV1Action.access$18400((FirestoreV1Action) this.instance, i);
                return this;
            }

            public Builder setBatchGetDocuments(BatchGetDocuments batchGetDocuments) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setBatchGetDocuments(batchGetDocuments);
                return this;
            }

            public Builder setBeginTransaction(BeginTransaction beginTransaction) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setBeginTransaction(beginTransaction);
                return this;
            }

            public Builder setCommit(Commit commit) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setCommit(commit);
                return this;
            }

            public Builder setCreateDocument(CreateDocument createDocument) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setCreateDocument(createDocument);
                return this;
            }

            public Builder setDatabaseContentsBeforeAction(RunQuery runQuery) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setDatabaseContentsBeforeAction(runQuery);
                return this;
            }

            public Builder setDeleteDocument(DeleteDocument deleteDocument) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setDeleteDocument(deleteDocument);
                return this;
            }

            public Builder setGetDocument(GetDocument getDocument) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setGetDocument(getDocument);
                return this;
            }

            public Builder setListCollectionIds(ListCollectionIds listCollectionIds) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setListCollectionIds(listCollectionIds);
                return this;
            }

            public Builder setListDocuments(ListDocuments listDocuments) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setListDocuments(listDocuments);
                return this;
            }

            public Builder setListen(Listen listen) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setListen(listen);
                return this;
            }

            public Builder setMatchingDocuments(int i, MatchingDocuments matchingDocuments) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setMatchingDocuments(i, matchingDocuments);
                return this;
            }

            public Builder setRemoveListen(RemoveListen removeListen) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setRemoveListen(removeListen);
                return this;
            }

            public Builder setRollback(Rollback rollback) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setRollback(rollback);
                return this;
            }

            public Builder setRunQuery(RunQuery runQuery) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setRunQuery(runQuery);
                return this;
            }

            public Builder setStatus(StatusProto statusProto) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setStatus(statusProto);
                return this;
            }

            public Builder setUpdateDocument(UpdateDocument updateDocument) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setUpdateDocument(updateDocument);
                return this;
            }

            public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                super(FirestoreV1Action.DEFAULT_INSTANCE);
            }

            public Builder addMatchingDocuments(int i, MatchingDocuments matchingDocuments) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).addMatchingDocuments(i, matchingDocuments);
                return this;
            }

            public Builder setBatchGetDocuments(BatchGetDocuments.Builder builder) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setBatchGetDocuments(builder.build());
                return this;
            }

            public Builder setBeginTransaction(BeginTransaction.Builder builder) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setBeginTransaction(builder.build());
                return this;
            }

            public Builder setCommit(Commit.Builder builder) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setCommit(builder.build());
                return this;
            }

            public Builder setCreateDocument(CreateDocument.Builder builder) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setCreateDocument(builder.build());
                return this;
            }

            public Builder setDatabaseContentsBeforeAction(RunQuery.Builder builder) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setDatabaseContentsBeforeAction(builder.build());
                return this;
            }

            public Builder setDeleteDocument(DeleteDocument.Builder builder) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setDeleteDocument(builder.build());
                return this;
            }

            public Builder setGetDocument(GetDocument.Builder builder) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setGetDocument(builder.build());
                return this;
            }

            public Builder setListCollectionIds(ListCollectionIds.Builder builder) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setListCollectionIds(builder.build());
                return this;
            }

            public Builder setListDocuments(ListDocuments.Builder builder) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setListDocuments(builder.build());
                return this;
            }

            public Builder setListen(Listen.Builder builder) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setListen(builder.build());
                return this;
            }

            public Builder setMatchingDocuments(int i, MatchingDocuments.Builder builder) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setMatchingDocuments(i, builder.build());
                return this;
            }

            public Builder setRemoveListen(RemoveListen.Builder builder) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setRemoveListen(builder.build());
                return this;
            }

            public Builder setRollback(Rollback.Builder builder) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setRollback(builder.build());
                return this;
            }

            public Builder setRunQuery(RunQuery.Builder builder) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setRunQuery(builder.build());
                return this;
            }

            public Builder setStatus(StatusProto.Builder builder) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setStatus(builder.build());
                return this;
            }

            public Builder setUpdateDocument(UpdateDocument.Builder builder) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setUpdateDocument(builder.build());
                return this;
            }

            public Builder addMatchingDocuments(MatchingDocuments.Builder builder) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).addMatchingDocuments(builder.build());
                return this;
            }

            public Builder addMatchingDocuments(int i, MatchingDocuments.Builder builder) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).addMatchingDocuments(i, builder.build());
                return this;
            }
        }

        public static Builder newBuilder(FirestoreV1Action firestoreV1Action) {
            return DEFAULT_INSTANCE.createBuilder(firestoreV1Action);
        }

        public static FirestoreV1Action parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (FirestoreV1Action) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static FirestoreV1Action parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
            return (FirestoreV1Action) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static FirestoreV1Action parseFrom(ByteString byteString) {
            return (FirestoreV1Action) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addMatchingDocuments(int i, MatchingDocuments matchingDocuments) {
            matchingDocuments.getClass();
            ensureMatchingDocumentsIsMutable();
            this.matchingDocuments_.add(i, matchingDocuments);
        }

        public static FirestoreV1Action parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (FirestoreV1Action) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static FirestoreV1Action parseFrom(byte[] bArr) {
            return (FirestoreV1Action) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static FirestoreV1Action parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (FirestoreV1Action) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static FirestoreV1Action parseFrom(InputStream inputStream) {
            return (FirestoreV1Action) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static FirestoreV1Action parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (FirestoreV1Action) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static FirestoreV1Action parseFrom(CodedInputStream codedInputStream) {
            return (FirestoreV1Action) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static FirestoreV1Action parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (FirestoreV1Action) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    public interface FirestoreV1ActionOrBuilder extends MessageLiteOrBuilder {
        FirestoreV1Action.ActionCase getActionCase();

        FirestoreV1Action.BatchGetDocuments getBatchGetDocuments();

        FirestoreV1Action.BeginTransaction getBeginTransaction();

        FirestoreV1Action.Commit getCommit();

        FirestoreV1Action.CreateDocument getCreateDocument();

        FirestoreV1Action.RunQuery getDatabaseContentsBeforeAction();

        FirestoreV1Action.DeleteDocument getDeleteDocument();

        FirestoreV1Action.GetDocument getGetDocument();

        FirestoreV1Action.ListCollectionIds getListCollectionIds();

        FirestoreV1Action.ListDocuments getListDocuments();

        FirestoreV1Action.Listen getListen();

        FirestoreV1Action.MatchingDocuments getMatchingDocuments(int i);

        int getMatchingDocumentsCount();

        List<FirestoreV1Action.MatchingDocuments> getMatchingDocumentsList();

        FirestoreV1Action.RemoveListen getRemoveListen();

        FirestoreV1Action.Rollback getRollback();

        FirestoreV1Action.RunQuery getRunQuery();

        StatusProto getStatus();

        FirestoreV1Action.UpdateDocument getUpdateDocument();

        boolean hasBatchGetDocuments();

        boolean hasBeginTransaction();

        boolean hasCommit();

        boolean hasCreateDocument();

        boolean hasDatabaseContentsBeforeAction();

        boolean hasDeleteDocument();

        boolean hasGetDocument();

        boolean hasListCollectionIds();

        boolean hasListDocuments();

        boolean hasListen();

        boolean hasRemoveListen();

        boolean hasRollback();

        boolean hasRunQuery();

        boolean hasStatus();

        boolean hasUpdateDocument();
    }

    public static final class ParallelTestTrace extends GeneratedMessageLite<ParallelTestTrace, Builder> implements ParallelTestTraceOrBuilder {
        public static final ParallelTestTrace DEFAULT_INSTANCE;
        public static volatile Parser<ParallelTestTrace> PARSER = null;
        public static final int TEST_TRACE_FIELD_NUMBER = 1;
        public TestTrace testTrace_;

        static {
            ParallelTestTrace parallelTestTrace = new ParallelTestTrace();
            DEFAULT_INSTANCE = parallelTestTrace;
            GeneratedMessageLite.defaultInstanceMap.put(ParallelTestTrace.class, parallelTestTrace);
        }

        private void clearTestTrace() {
            this.testTrace_ = null;
        }

        public static ParallelTestTrace getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeTestTrace(TestTrace testTrace) {
            testTrace.getClass();
            TestTrace testTrace2 = this.testTrace_;
            if (testTrace2 == null || testTrace2 == TestTrace.DEFAULT_INSTANCE) {
                this.testTrace_ = testTrace;
            } else {
                this.testTrace_ = TestTrace.newBuilder(testTrace2).mergeFrom(testTrace).buildPartial();
            }
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static ParallelTestTrace parseDelimitedFrom(InputStream inputStream) {
            return (ParallelTestTrace) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ParallelTestTrace parseFrom(ByteBuffer byteBuffer) {
            return (ParallelTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<ParallelTestTrace> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTestTrace(TestTrace testTrace) {
            testTrace.getClass();
            this.testTrace_ = testTrace;
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
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\t", new Object[]{"testTrace_"});
                case NEW_MUTABLE_INSTANCE:
                    return new ParallelTestTrace();
                case NEW_BUILDER:
                    return new Builder(anonymousClass1);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser<ParallelTestTrace> defaultInstanceBasedParser = PARSER;
                    if (defaultInstanceBasedParser == null) {
                        synchronized (ParallelTestTrace.class) {
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

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.ParallelTestTraceOrBuilder
        public TestTrace getTestTrace() {
            TestTrace testTrace = this.testTrace_;
            return testTrace == null ? TestTrace.DEFAULT_INSTANCE : testTrace;
        }

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.ParallelTestTraceOrBuilder
        public boolean hasTestTrace() {
            return this.testTrace_ != null;
        }

        public static final class Builder extends GeneratedMessageLite.Builder<ParallelTestTrace, Builder> implements ParallelTestTraceOrBuilder {
            public Builder() {
                super(ParallelTestTrace.DEFAULT_INSTANCE);
            }

            public Builder clearTestTrace() {
                copyOnWrite();
                ((ParallelTestTrace) this.instance).testTrace_ = null;
                return this;
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.ParallelTestTraceOrBuilder
            public TestTrace getTestTrace() {
                return ((ParallelTestTrace) this.instance).getTestTrace();
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.ParallelTestTraceOrBuilder
            public boolean hasTestTrace() {
                return ((ParallelTestTrace) this.instance).hasTestTrace();
            }

            public Builder mergeTestTrace(TestTrace testTrace) {
                copyOnWrite();
                ((ParallelTestTrace) this.instance).mergeTestTrace(testTrace);
                return this;
            }

            public Builder setTestTrace(TestTrace testTrace) {
                copyOnWrite();
                ((ParallelTestTrace) this.instance).setTestTrace(testTrace);
                return this;
            }

            public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                super(ParallelTestTrace.DEFAULT_INSTANCE);
            }

            public Builder setTestTrace(TestTrace.Builder builder) {
                copyOnWrite();
                ((ParallelTestTrace) this.instance).setTestTrace(builder.build());
                return this;
            }
        }

        public static Builder newBuilder(ParallelTestTrace parallelTestTrace) {
            return DEFAULT_INSTANCE.createBuilder(parallelTestTrace);
        }

        public static ParallelTestTrace parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (ParallelTestTrace) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ParallelTestTrace parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
            return (ParallelTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static ParallelTestTrace parseFrom(ByteString byteString) {
            return (ParallelTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static ParallelTestTrace parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (ParallelTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static ParallelTestTrace parseFrom(byte[] bArr) {
            return (ParallelTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static ParallelTestTrace parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (ParallelTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static ParallelTestTrace parseFrom(InputStream inputStream) {
            return (ParallelTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ParallelTestTrace parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (ParallelTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ParallelTestTrace parseFrom(CodedInputStream codedInputStream) {
            return (ParallelTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static ParallelTestTrace parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (ParallelTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    public interface ParallelTestTraceOrBuilder extends MessageLiteOrBuilder {
        TestTrace getTestTrace();

        boolean hasTestTrace();
    }

    public static final class StatusProto extends GeneratedMessageLite<StatusProto, Builder> implements StatusProtoOrBuilder {
        public static final int CANONICAL_CODE_FIELD_NUMBER = 6;
        public static final int CODE_FIELD_NUMBER = 1;
        public static final StatusProto DEFAULT_INSTANCE;
        public static final int MESSAGE_FIELD_NUMBER = 3;
        public static volatile Parser<StatusProto> PARSER = null;
        public static final int SPACE_FIELD_NUMBER = 2;
        public int canonicalCode_;
        public int code_;
        public String space_ = "";
        public String message_ = "";

        static {
            StatusProto statusProto = new StatusProto();
            DEFAULT_INSTANCE = statusProto;
            GeneratedMessageLite.defaultInstanceMap.put(StatusProto.class, statusProto);
        }

        private void clearCanonicalCode() {
            this.canonicalCode_ = 0;
        }

        private void clearCode() {
            this.code_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearMessage() {
            this.message_ = DEFAULT_INSTANCE.getMessage();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSpace() {
            this.space_ = DEFAULT_INSTANCE.getSpace();
        }

        public static StatusProto getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static StatusProto parseDelimitedFrom(InputStream inputStream) {
            return (StatusProto) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static StatusProto parseFrom(ByteBuffer byteBuffer) {
            return (StatusProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<StatusProto> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        private void setCanonicalCode(int i) {
            this.canonicalCode_ = i;
        }

        private void setCode(int i) {
            this.code_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMessage(String str) {
            str.getClass();
            this.message_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMessageBytes(ByteString byteString) {
            AbstractMessageLite.checkByteStringIsUtf8(byteString);
            this.message_ = byteString.toStringUtf8();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSpace(String str) {
            str.getClass();
            this.space_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSpaceBytes(ByteString byteString) {
            AbstractMessageLite.checkByteStringIsUtf8(byteString);
            this.space_ = byteString.toStringUtf8();
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
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0006\u0004\u0000\u0000\u0000\u0001\u0004\u0002Ȉ\u0003Ȉ\u0006\u0004", new Object[]{"code_", "space_", "message_", "canonicalCode_"});
                case NEW_MUTABLE_INSTANCE:
                    return new StatusProto();
                case NEW_BUILDER:
                    return new Builder(anonymousClass1);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser<StatusProto> defaultInstanceBasedParser = PARSER;
                    if (defaultInstanceBasedParser == null) {
                        synchronized (StatusProto.class) {
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

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.StatusProtoOrBuilder
        public int getCanonicalCode() {
            return this.canonicalCode_;
        }

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.StatusProtoOrBuilder
        public int getCode() {
            return this.code_;
        }

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.StatusProtoOrBuilder
        public String getMessage() {
            return this.message_;
        }

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.StatusProtoOrBuilder
        public ByteString getMessageBytes() {
            return ByteString.copyFromUtf8(this.message_);
        }

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.StatusProtoOrBuilder
        public String getSpace() {
            return this.space_;
        }

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.StatusProtoOrBuilder
        public ByteString getSpaceBytes() {
            return ByteString.copyFromUtf8(this.space_);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<StatusProto, Builder> implements StatusProtoOrBuilder {
            public Builder() {
                super(StatusProto.DEFAULT_INSTANCE);
            }

            public Builder clearCanonicalCode() {
                copyOnWrite();
                ((StatusProto) this.instance).canonicalCode_ = 0;
                return this;
            }

            public Builder clearCode() {
                copyOnWrite();
                ((StatusProto) this.instance).code_ = 0;
                return this;
            }

            public Builder clearMessage() {
                copyOnWrite();
                ((StatusProto) this.instance).clearMessage();
                return this;
            }

            public Builder clearSpace() {
                copyOnWrite();
                ((StatusProto) this.instance).clearSpace();
                return this;
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.StatusProtoOrBuilder
            public int getCanonicalCode() {
                return ((StatusProto) this.instance).getCanonicalCode();
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.StatusProtoOrBuilder
            public int getCode() {
                return ((StatusProto) this.instance).getCode();
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.StatusProtoOrBuilder
            public String getMessage() {
                return ((StatusProto) this.instance).getMessage();
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.StatusProtoOrBuilder
            public ByteString getMessageBytes() {
                return ((StatusProto) this.instance).getMessageBytes();
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.StatusProtoOrBuilder
            public String getSpace() {
                return ((StatusProto) this.instance).getSpace();
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.StatusProtoOrBuilder
            public ByteString getSpaceBytes() {
                return ((StatusProto) this.instance).getSpaceBytes();
            }

            public Builder setCanonicalCode(int i) {
                copyOnWrite();
                ((StatusProto) this.instance).canonicalCode_ = i;
                return this;
            }

            public Builder setCode(int i) {
                copyOnWrite();
                ((StatusProto) this.instance).code_ = i;
                return this;
            }

            public Builder setMessage(String str) {
                copyOnWrite();
                ((StatusProto) this.instance).setMessage(str);
                return this;
            }

            public Builder setMessageBytes(ByteString byteString) {
                copyOnWrite();
                ((StatusProto) this.instance).setMessageBytes(byteString);
                return this;
            }

            public Builder setSpace(String str) {
                copyOnWrite();
                ((StatusProto) this.instance).setSpace(str);
                return this;
            }

            public Builder setSpaceBytes(ByteString byteString) {
                copyOnWrite();
                ((StatusProto) this.instance).setSpaceBytes(byteString);
                return this;
            }

            public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                super(StatusProto.DEFAULT_INSTANCE);
            }
        }

        public static Builder newBuilder(StatusProto statusProto) {
            return DEFAULT_INSTANCE.createBuilder(statusProto);
        }

        public static StatusProto parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (StatusProto) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static StatusProto parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
            return (StatusProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static StatusProto parseFrom(ByteString byteString) {
            return (StatusProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static StatusProto parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (StatusProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static StatusProto parseFrom(byte[] bArr) {
            return (StatusProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static StatusProto parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (StatusProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static StatusProto parseFrom(InputStream inputStream) {
            return (StatusProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static StatusProto parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (StatusProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static StatusProto parseFrom(CodedInputStream codedInputStream) {
            return (StatusProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static StatusProto parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (StatusProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    public interface StatusProtoOrBuilder extends MessageLiteOrBuilder {
        int getCanonicalCode();

        int getCode();

        String getMessage();

        ByteString getMessageBytes();

        String getSpace();

        ByteString getSpaceBytes();
    }

    public static final class TestTrace extends GeneratedMessageLite<TestTrace, Builder> implements TestTraceOrBuilder {
        public static final int ACTION_FIELD_NUMBER = 2;
        public static final TestTrace DEFAULT_INSTANCE;
        public static volatile Parser<TestTrace> PARSER = null;
        public static final int TRACE_DESCRIPTION_FIELD_NUMBER = 3;
        public static final int TRACE_ID_FIELD_NUMBER = 1;
        public String traceId_ = "";
        public Internal.ProtobufList<DatastoreAction> action_ = GeneratedMessageLite.emptyProtobufList();
        public String traceDescription_ = "";

        static {
            TestTrace testTrace = new TestTrace();
            DEFAULT_INSTANCE = testTrace;
            GeneratedMessageLite.defaultInstanceMap.put(TestTrace.class, testTrace);
        }

        public static /* synthetic */ void access$20400(TestTrace testTrace, Iterable iterable) {
            testTrace.ensureActionIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, (List) testTrace.action_);
        }

        public static /* synthetic */ void access$20600(TestTrace testTrace, int i) {
            testTrace.ensureActionIsMutable();
            testTrace.action_.remove(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAction(DatastoreAction datastoreAction) {
            datastoreAction.getClass();
            ensureActionIsMutable();
            this.action_.add(datastoreAction);
        }

        private void addAllAction(Iterable<? extends DatastoreAction> iterable) {
            ensureActionIsMutable();
            AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.action_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAction() {
            this.action_ = GeneratedMessageLite.emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearTraceDescription() {
            this.traceDescription_ = DEFAULT_INSTANCE.getTraceDescription();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearTraceId() {
            this.traceId_ = DEFAULT_INSTANCE.getTraceId();
        }

        private void ensureActionIsMutable() {
            Internal.ProtobufList<DatastoreAction> protobufList = this.action_;
            if (protobufList.isModifiable()) {
                return;
            }
            this.action_ = GeneratedMessageLite.mutableCopy(protobufList);
        }

        public static TestTrace getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static TestTrace parseDelimitedFrom(InputStream inputStream) {
            return (TestTrace) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static TestTrace parseFrom(ByteBuffer byteBuffer) {
            return (TestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<TestTrace> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        private void removeAction(int i) {
            ensureActionIsMutable();
            this.action_.remove(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAction(int i, DatastoreAction datastoreAction) {
            datastoreAction.getClass();
            ensureActionIsMutable();
            this.action_.set(i, datastoreAction);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTraceDescription(String str) {
            str.getClass();
            this.traceDescription_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTraceDescriptionBytes(ByteString byteString) {
            AbstractMessageLite.checkByteStringIsUtf8(byteString);
            this.traceDescription_ = byteString.toStringUtf8();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTraceId(String str) {
            str.getClass();
            this.traceId_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTraceIdBytes(ByteString byteString) {
            AbstractMessageLite.checkByteStringIsUtf8(byteString);
            this.traceId_ = byteString.toStringUtf8();
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
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0001\u0000\u0001Ȉ\u0002\u001b\u0003Ȉ", new Object[]{"traceId_", "action_", DatastoreAction.class, "traceDescription_"});
                case NEW_MUTABLE_INSTANCE:
                    return new TestTrace();
                case NEW_BUILDER:
                    return new Builder(anonymousClass1);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser<TestTrace> defaultInstanceBasedParser = PARSER;
                    if (defaultInstanceBasedParser == null) {
                        synchronized (TestTrace.class) {
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

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.TestTraceOrBuilder
        public DatastoreAction getAction(int i) {
            return this.action_.get(i);
        }

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.TestTraceOrBuilder
        public int getActionCount() {
            return this.action_.size();
        }

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.TestTraceOrBuilder
        public List<DatastoreAction> getActionList() {
            return this.action_;
        }

        public DatastoreActionOrBuilder getActionOrBuilder(int i) {
            return this.action_.get(i);
        }

        public List<? extends DatastoreActionOrBuilder> getActionOrBuilderList() {
            return this.action_;
        }

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.TestTraceOrBuilder
        public String getTraceDescription() {
            return this.traceDescription_;
        }

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.TestTraceOrBuilder
        public ByteString getTraceDescriptionBytes() {
            return ByteString.copyFromUtf8(this.traceDescription_);
        }

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.TestTraceOrBuilder
        public String getTraceId() {
            return this.traceId_;
        }

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.TestTraceOrBuilder
        public ByteString getTraceIdBytes() {
            return ByteString.copyFromUtf8(this.traceId_);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<TestTrace, Builder> implements TestTraceOrBuilder {
            public Builder() {
                super(TestTrace.DEFAULT_INSTANCE);
            }

            public Builder addAction(DatastoreAction datastoreAction) {
                copyOnWrite();
                ((TestTrace) this.instance).addAction(datastoreAction);
                return this;
            }

            public Builder addAllAction(Iterable<? extends DatastoreAction> iterable) {
                copyOnWrite();
                TestTrace.access$20400((TestTrace) this.instance, iterable);
                return this;
            }

            public Builder clearAction() {
                copyOnWrite();
                ((TestTrace) this.instance).clearAction();
                return this;
            }

            public Builder clearTraceDescription() {
                copyOnWrite();
                ((TestTrace) this.instance).clearTraceDescription();
                return this;
            }

            public Builder clearTraceId() {
                copyOnWrite();
                ((TestTrace) this.instance).clearTraceId();
                return this;
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.TestTraceOrBuilder
            public DatastoreAction getAction(int i) {
                return ((TestTrace) this.instance).getAction(i);
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.TestTraceOrBuilder
            public int getActionCount() {
                return ((TestTrace) this.instance).getActionCount();
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.TestTraceOrBuilder
            public List<DatastoreAction> getActionList() {
                return Collections.unmodifiableList(((TestTrace) this.instance).getActionList());
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.TestTraceOrBuilder
            public String getTraceDescription() {
                return ((TestTrace) this.instance).getTraceDescription();
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.TestTraceOrBuilder
            public ByteString getTraceDescriptionBytes() {
                return ((TestTrace) this.instance).getTraceDescriptionBytes();
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.TestTraceOrBuilder
            public String getTraceId() {
                return ((TestTrace) this.instance).getTraceId();
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.TestTraceOrBuilder
            public ByteString getTraceIdBytes() {
                return ((TestTrace) this.instance).getTraceIdBytes();
            }

            public Builder removeAction(int i) {
                copyOnWrite();
                TestTrace.access$20600((TestTrace) this.instance, i);
                return this;
            }

            public Builder setAction(int i, DatastoreAction datastoreAction) {
                copyOnWrite();
                ((TestTrace) this.instance).setAction(i, datastoreAction);
                return this;
            }

            public Builder setTraceDescription(String str) {
                copyOnWrite();
                ((TestTrace) this.instance).setTraceDescription(str);
                return this;
            }

            public Builder setTraceDescriptionBytes(ByteString byteString) {
                copyOnWrite();
                ((TestTrace) this.instance).setTraceDescriptionBytes(byteString);
                return this;
            }

            public Builder setTraceId(String str) {
                copyOnWrite();
                ((TestTrace) this.instance).setTraceId(str);
                return this;
            }

            public Builder setTraceIdBytes(ByteString byteString) {
                copyOnWrite();
                ((TestTrace) this.instance).setTraceIdBytes(byteString);
                return this;
            }

            public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                super(TestTrace.DEFAULT_INSTANCE);
            }

            public Builder addAction(int i, DatastoreAction datastoreAction) {
                copyOnWrite();
                ((TestTrace) this.instance).addAction(i, datastoreAction);
                return this;
            }

            public Builder setAction(int i, DatastoreAction.Builder builder) {
                copyOnWrite();
                ((TestTrace) this.instance).setAction(i, builder.build());
                return this;
            }

            public Builder addAction(DatastoreAction.Builder builder) {
                copyOnWrite();
                ((TestTrace) this.instance).addAction(builder.build());
                return this;
            }

            public Builder addAction(int i, DatastoreAction.Builder builder) {
                copyOnWrite();
                ((TestTrace) this.instance).addAction(i, builder.build());
                return this;
            }
        }

        public static Builder newBuilder(TestTrace testTrace) {
            return DEFAULT_INSTANCE.createBuilder(testTrace);
        }

        public static TestTrace parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (TestTrace) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static TestTrace parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
            return (TestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static TestTrace parseFrom(ByteString byteString) {
            return (TestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAction(int i, DatastoreAction datastoreAction) {
            datastoreAction.getClass();
            ensureActionIsMutable();
            this.action_.add(i, datastoreAction);
        }

        public static TestTrace parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (TestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static TestTrace parseFrom(byte[] bArr) {
            return (TestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static TestTrace parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (TestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static TestTrace parseFrom(InputStream inputStream) {
            return (TestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static TestTrace parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (TestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static TestTrace parseFrom(CodedInputStream codedInputStream) {
            return (TestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static TestTrace parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (TestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    public interface TestTraceOrBuilder extends MessageLiteOrBuilder {
        DatastoreAction getAction(int i);

        int getActionCount();

        List<DatastoreAction> getActionList();

        String getTraceDescription();

        ByteString getTraceDescriptionBytes();

        String getTraceId();

        ByteString getTraceIdBytes();
    }

    public static final class TimelineTestTrace extends GeneratedMessageLite<TimelineTestTrace, Builder> implements TimelineTestTraceOrBuilder {
        public static final TimelineTestTrace DEFAULT_INSTANCE;
        public static volatile Parser<TimelineTestTrace> PARSER = null;
        public static final int TEST_TRACE_FIELD_NUMBER = 1;
        public TestTrace testTrace_;

        static {
            TimelineTestTrace timelineTestTrace = new TimelineTestTrace();
            DEFAULT_INSTANCE = timelineTestTrace;
            GeneratedMessageLite.defaultInstanceMap.put(TimelineTestTrace.class, timelineTestTrace);
        }

        private void clearTestTrace() {
            this.testTrace_ = null;
        }

        public static TimelineTestTrace getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeTestTrace(TestTrace testTrace) {
            testTrace.getClass();
            TestTrace testTrace2 = this.testTrace_;
            if (testTrace2 == null || testTrace2 == TestTrace.DEFAULT_INSTANCE) {
                this.testTrace_ = testTrace;
            } else {
                this.testTrace_ = TestTrace.newBuilder(testTrace2).mergeFrom(testTrace).buildPartial();
            }
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static TimelineTestTrace parseDelimitedFrom(InputStream inputStream) {
            return (TimelineTestTrace) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static TimelineTestTrace parseFrom(ByteBuffer byteBuffer) {
            return (TimelineTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<TimelineTestTrace> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTestTrace(TestTrace testTrace) {
            testTrace.getClass();
            this.testTrace_ = testTrace;
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
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\t", new Object[]{"testTrace_"});
                case NEW_MUTABLE_INSTANCE:
                    return new TimelineTestTrace();
                case NEW_BUILDER:
                    return new Builder(anonymousClass1);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser<TimelineTestTrace> defaultInstanceBasedParser = PARSER;
                    if (defaultInstanceBasedParser == null) {
                        synchronized (TimelineTestTrace.class) {
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

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.TimelineTestTraceOrBuilder
        public TestTrace getTestTrace() {
            TestTrace testTrace = this.testTrace_;
            return testTrace == null ? TestTrace.DEFAULT_INSTANCE : testTrace;
        }

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.TimelineTestTraceOrBuilder
        public boolean hasTestTrace() {
            return this.testTrace_ != null;
        }

        public static final class Builder extends GeneratedMessageLite.Builder<TimelineTestTrace, Builder> implements TimelineTestTraceOrBuilder {
            public Builder() {
                super(TimelineTestTrace.DEFAULT_INSTANCE);
            }

            public Builder clearTestTrace() {
                copyOnWrite();
                ((TimelineTestTrace) this.instance).testTrace_ = null;
                return this;
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.TimelineTestTraceOrBuilder
            public TestTrace getTestTrace() {
                return ((TimelineTestTrace) this.instance).getTestTrace();
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.TimelineTestTraceOrBuilder
            public boolean hasTestTrace() {
                return ((TimelineTestTrace) this.instance).hasTestTrace();
            }

            public Builder mergeTestTrace(TestTrace testTrace) {
                copyOnWrite();
                ((TimelineTestTrace) this.instance).mergeTestTrace(testTrace);
                return this;
            }

            public Builder setTestTrace(TestTrace testTrace) {
                copyOnWrite();
                ((TimelineTestTrace) this.instance).setTestTrace(testTrace);
                return this;
            }

            public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                super(TimelineTestTrace.DEFAULT_INSTANCE);
            }

            public Builder setTestTrace(TestTrace.Builder builder) {
                copyOnWrite();
                ((TimelineTestTrace) this.instance).setTestTrace(builder.build());
                return this;
            }
        }

        public static Builder newBuilder(TimelineTestTrace timelineTestTrace) {
            return DEFAULT_INSTANCE.createBuilder(timelineTestTrace);
        }

        public static TimelineTestTrace parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (TimelineTestTrace) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static TimelineTestTrace parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
            return (TimelineTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static TimelineTestTrace parseFrom(ByteString byteString) {
            return (TimelineTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static TimelineTestTrace parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (TimelineTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static TimelineTestTrace parseFrom(byte[] bArr) {
            return (TimelineTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static TimelineTestTrace parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (TimelineTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static TimelineTestTrace parseFrom(InputStream inputStream) {
            return (TimelineTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static TimelineTestTrace parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (TimelineTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static TimelineTestTrace parseFrom(CodedInputStream codedInputStream) {
            return (TimelineTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static TimelineTestTrace parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (TimelineTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    public interface TimelineTestTraceOrBuilder extends MessageLiteOrBuilder {
        TestTrace getTestTrace();

        boolean hasTestTrace();
    }

    public static final class ValidationRule extends GeneratedMessageLite<ValidationRule, Builder> implements ValidationRuleOrBuilder {
        public static final ValidationRule DEFAULT_INSTANCE;
        public static volatile Parser<ValidationRule> PARSER = null;
        public static final int VALIDATE_QUERY_INDEXES_FIELD_NUMBER = 2;
        public static final int VALIDATE_QUERY_RESULT_ORDER_FIELD_NUMBER = 1;
        public boolean validateQueryIndexes_;
        public boolean validateQueryResultOrder_;

        static {
            ValidationRule validationRule = new ValidationRule();
            DEFAULT_INSTANCE = validationRule;
            GeneratedMessageLite.defaultInstanceMap.put(ValidationRule.class, validationRule);
        }

        private void clearValidateQueryIndexes() {
            this.validateQueryIndexes_ = false;
        }

        private void clearValidateQueryResultOrder() {
            this.validateQueryResultOrder_ = false;
        }

        public static ValidationRule getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static ValidationRule parseDelimitedFrom(InputStream inputStream) {
            return (ValidationRule) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ValidationRule parseFrom(ByteBuffer byteBuffer) {
            return (ValidationRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<ValidationRule> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        private void setValidateQueryIndexes(boolean z) {
            this.validateQueryIndexes_ = z;
        }

        private void setValidateQueryResultOrder(boolean z) {
            this.validateQueryResultOrder_ = z;
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
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0007\u0002\u0007", new Object[]{"validateQueryResultOrder_", "validateQueryIndexes_"});
                case NEW_MUTABLE_INSTANCE:
                    return new ValidationRule();
                case NEW_BUILDER:
                    return new Builder(anonymousClass1);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser<ValidationRule> defaultInstanceBasedParser = PARSER;
                    if (defaultInstanceBasedParser == null) {
                        synchronized (ValidationRule.class) {
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

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.ValidationRuleOrBuilder
        public boolean getValidateQueryIndexes() {
            return this.validateQueryIndexes_;
        }

        @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.ValidationRuleOrBuilder
        public boolean getValidateQueryResultOrder() {
            return this.validateQueryResultOrder_;
        }

        public static final class Builder extends GeneratedMessageLite.Builder<ValidationRule, Builder> implements ValidationRuleOrBuilder {
            public Builder() {
                super(ValidationRule.DEFAULT_INSTANCE);
            }

            public Builder clearValidateQueryIndexes() {
                copyOnWrite();
                ((ValidationRule) this.instance).validateQueryIndexes_ = false;
                return this;
            }

            public Builder clearValidateQueryResultOrder() {
                copyOnWrite();
                ((ValidationRule) this.instance).validateQueryResultOrder_ = false;
                return this;
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.ValidationRuleOrBuilder
            public boolean getValidateQueryIndexes() {
                return ((ValidationRule) this.instance).getValidateQueryIndexes();
            }

            @Override // com.google.apphosting.datastore.testing.DatastoreTestTrace.ValidationRuleOrBuilder
            public boolean getValidateQueryResultOrder() {
                return ((ValidationRule) this.instance).getValidateQueryResultOrder();
            }

            public Builder setValidateQueryIndexes(boolean z) {
                copyOnWrite();
                ((ValidationRule) this.instance).validateQueryIndexes_ = z;
                return this;
            }

            public Builder setValidateQueryResultOrder(boolean z) {
                copyOnWrite();
                ((ValidationRule) this.instance).validateQueryResultOrder_ = z;
                return this;
            }

            public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                super(ValidationRule.DEFAULT_INSTANCE);
            }
        }

        public static Builder newBuilder(ValidationRule validationRule) {
            return DEFAULT_INSTANCE.createBuilder(validationRule);
        }

        public static ValidationRule parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (ValidationRule) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ValidationRule parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
            return (ValidationRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static ValidationRule parseFrom(ByteString byteString) {
            return (ValidationRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static ValidationRule parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (ValidationRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static ValidationRule parseFrom(byte[] bArr) {
            return (ValidationRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static ValidationRule parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (ValidationRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static ValidationRule parseFrom(InputStream inputStream) {
            return (ValidationRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ValidationRule parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (ValidationRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ValidationRule parseFrom(CodedInputStream codedInputStream) {
            return (ValidationRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static ValidationRule parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (ValidationRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    public interface ValidationRuleOrBuilder extends MessageLiteOrBuilder {
        boolean getValidateQueryIndexes();

        boolean getValidateQueryResultOrder();
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }
}
