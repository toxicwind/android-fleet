package com.google.cloud.audit;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.Any;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.Parser;
import com.google.protobuf.Struct;
import com.google.rpc.Status;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class AuditLog extends GeneratedMessageLite<AuditLog, Builder> implements AuditLogOrBuilder {
    public static final int AUTHENTICATION_INFO_FIELD_NUMBER = 3;
    public static final int AUTHORIZATION_INFO_FIELD_NUMBER = 9;
    public static final AuditLog DEFAULT_INSTANCE;
    public static final int METHOD_NAME_FIELD_NUMBER = 8;
    public static final int NUM_RESPONSE_ITEMS_FIELD_NUMBER = 12;
    public static volatile Parser<AuditLog> PARSER = null;
    public static final int REQUEST_FIELD_NUMBER = 16;
    public static final int REQUEST_METADATA_FIELD_NUMBER = 4;
    public static final int RESOURCE_NAME_FIELD_NUMBER = 11;
    public static final int RESPONSE_FIELD_NUMBER = 17;
    public static final int SERVICE_DATA_FIELD_NUMBER = 15;
    public static final int SERVICE_NAME_FIELD_NUMBER = 7;
    public static final int STATUS_FIELD_NUMBER = 2;
    public AuthenticationInfo authenticationInfo_;
    public long numResponseItems_;
    public RequestMetadata requestMetadata_;
    public Struct request_;
    public Struct response_;
    public Any serviceData_;
    public Status status_;
    public String serviceName_ = "";
    public String methodName_ = "";
    public String resourceName_ = "";
    public Internal.ProtobufList<AuthorizationInfo> authorizationInfo_ = GeneratedMessageLite.emptyProtobufList();

    /* JADX INFO: renamed from: com.google.cloud.audit.AuditLog$1, reason: invalid class name */
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

    static {
        AuditLog auditLog = new AuditLog();
        DEFAULT_INSTANCE = auditLog;
        GeneratedMessageLite.defaultInstanceMap.put(AuditLog.class, auditLog);
    }

    public static /* synthetic */ void access$2100(AuditLog auditLog, Iterable iterable) {
        auditLog.ensureAuthorizationInfoIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) auditLog.authorizationInfo_);
    }

    public static /* synthetic */ void access$2300(AuditLog auditLog, int i) {
        auditLog.ensureAuthorizationInfoIsMutable();
        auditLog.authorizationInfo_.remove(i);
    }

    private void addAllAuthorizationInfo(Iterable<? extends AuthorizationInfo> iterable) {
        ensureAuthorizationInfoIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.authorizationInfo_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAuthorizationInfo(AuthorizationInfo authorizationInfo) {
        authorizationInfo.getClass();
        ensureAuthorizationInfoIsMutable();
        this.authorizationInfo_.add(authorizationInfo);
    }

    private void clearAuthenticationInfo() {
        this.authenticationInfo_ = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearAuthorizationInfo() {
        this.authorizationInfo_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearMethodName() {
        this.methodName_ = DEFAULT_INSTANCE.getMethodName();
    }

    private void clearNumResponseItems() {
        this.numResponseItems_ = 0L;
    }

    private void clearRequest() {
        this.request_ = null;
    }

    private void clearRequestMetadata() {
        this.requestMetadata_ = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearResourceName() {
        this.resourceName_ = DEFAULT_INSTANCE.getResourceName();
    }

    private void clearResponse() {
        this.response_ = null;
    }

    private void clearServiceData() {
        this.serviceData_ = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearServiceName() {
        this.serviceName_ = DEFAULT_INSTANCE.getServiceName();
    }

    private void clearStatus() {
        this.status_ = null;
    }

    private void ensureAuthorizationInfoIsMutable() {
        Internal.ProtobufList<AuthorizationInfo> protobufList = this.authorizationInfo_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.authorizationInfo_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    public static AuditLog getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeAuthenticationInfo(AuthenticationInfo authenticationInfo) {
        authenticationInfo.getClass();
        AuthenticationInfo authenticationInfo2 = this.authenticationInfo_;
        if (authenticationInfo2 == null || authenticationInfo2 == AuthenticationInfo.DEFAULT_INSTANCE) {
            this.authenticationInfo_ = authenticationInfo;
        } else {
            this.authenticationInfo_ = AuthenticationInfo.newBuilder(authenticationInfo2).mergeFrom(authenticationInfo).buildPartial();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeRequest(Struct struct) {
        struct.getClass();
        Struct struct2 = this.request_;
        if (struct2 == null || struct2 == Struct.DEFAULT_INSTANCE) {
            this.request_ = struct;
        } else {
            this.request_ = Struct.newBuilder(struct2).mergeFrom(struct).buildPartial();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeRequestMetadata(RequestMetadata requestMetadata) {
        requestMetadata.getClass();
        RequestMetadata requestMetadata2 = this.requestMetadata_;
        if (requestMetadata2 == null || requestMetadata2 == RequestMetadata.DEFAULT_INSTANCE) {
            this.requestMetadata_ = requestMetadata;
        } else {
            this.requestMetadata_ = RequestMetadata.newBuilder(requestMetadata2).mergeFrom(requestMetadata).buildPartial();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeResponse(Struct struct) {
        struct.getClass();
        Struct struct2 = this.response_;
        if (struct2 == null || struct2 == Struct.DEFAULT_INSTANCE) {
            this.response_ = struct;
        } else {
            this.response_ = Struct.newBuilder(struct2).mergeFrom(struct).buildPartial();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeServiceData(Any any) {
        any.getClass();
        Any any2 = this.serviceData_;
        if (any2 == null || any2 == Any.DEFAULT_INSTANCE) {
            this.serviceData_ = any;
        } else {
            this.serviceData_ = Any.newBuilder(any2).mergeFrom(any).buildPartial();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeStatus(Status status) {
        status.getClass();
        Status status2 = this.status_;
        if (status2 == null || status2 == Status.DEFAULT_INSTANCE) {
            this.status_ = status;
        } else {
            this.status_ = Status.newBuilder(status2).mergeFrom(status).buildPartial();
        }
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static AuditLog parseDelimitedFrom(InputStream inputStream) {
        return (AuditLog) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static AuditLog parseFrom(ByteBuffer byteBuffer) {
        return (AuditLog) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<AuditLog> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    private void removeAuthorizationInfo(int i) {
        ensureAuthorizationInfoIsMutable();
        this.authorizationInfo_.remove(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAuthenticationInfo(AuthenticationInfo authenticationInfo) {
        authenticationInfo.getClass();
        this.authenticationInfo_ = authenticationInfo;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAuthorizationInfo(int i, AuthorizationInfo authorizationInfo) {
        authorizationInfo.getClass();
        ensureAuthorizationInfoIsMutable();
        this.authorizationInfo_.set(i, authorizationInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMethodName(String str) {
        str.getClass();
        this.methodName_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMethodNameBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.methodName_ = byteString.toStringUtf8();
    }

    private void setNumResponseItems(long j) {
        this.numResponseItems_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRequest(Struct struct) {
        struct.getClass();
        this.request_ = struct;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRequestMetadata(RequestMetadata requestMetadata) {
        requestMetadata.getClass();
        this.requestMetadata_ = requestMetadata;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setResourceName(String str) {
        str.getClass();
        this.resourceName_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setResourceNameBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.resourceName_ = byteString.toStringUtf8();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setResponse(Struct struct) {
        struct.getClass();
        this.response_ = struct;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setServiceData(Any any) {
        any.getClass();
        this.serviceData_ = any;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setServiceName(String str) {
        str.getClass();
        this.serviceName_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setServiceNameBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.serviceName_ = byteString.toStringUtf8();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStatus(Status status) {
        status.getClass();
        this.status_ = status;
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u000b\u0000\u0000\u0002\u0011\u000b\u0000\u0001\u0000\u0002\t\u0003\t\u0004\t\u0007Ȉ\bȈ\t\u001b\u000bȈ\f\u0002\u000f\t\u0010\t\u0011\t", new Object[]{"status_", "authenticationInfo_", "requestMetadata_", "serviceName_", "methodName_", "authorizationInfo_", AuthorizationInfo.class, "resourceName_", "numResponseItems_", "serviceData_", "request_", "response_"});
            case NEW_MUTABLE_INSTANCE:
                return new AuditLog();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<AuditLog> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (AuditLog.class) {
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

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public AuthenticationInfo getAuthenticationInfo() {
        AuthenticationInfo authenticationInfo = this.authenticationInfo_;
        return authenticationInfo == null ? AuthenticationInfo.DEFAULT_INSTANCE : authenticationInfo;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public AuthorizationInfo getAuthorizationInfo(int i) {
        return this.authorizationInfo_.get(i);
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public int getAuthorizationInfoCount() {
        return this.authorizationInfo_.size();
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public List<AuthorizationInfo> getAuthorizationInfoList() {
        return this.authorizationInfo_;
    }

    public AuthorizationInfoOrBuilder getAuthorizationInfoOrBuilder(int i) {
        return this.authorizationInfo_.get(i);
    }

    public List<? extends AuthorizationInfoOrBuilder> getAuthorizationInfoOrBuilderList() {
        return this.authorizationInfo_;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public String getMethodName() {
        return this.methodName_;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public ByteString getMethodNameBytes() {
        return ByteString.copyFromUtf8(this.methodName_);
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public long getNumResponseItems() {
        return this.numResponseItems_;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public Struct getRequest() {
        Struct struct = this.request_;
        return struct == null ? Struct.DEFAULT_INSTANCE : struct;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public RequestMetadata getRequestMetadata() {
        RequestMetadata requestMetadata = this.requestMetadata_;
        return requestMetadata == null ? RequestMetadata.DEFAULT_INSTANCE : requestMetadata;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public String getResourceName() {
        return this.resourceName_;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public ByteString getResourceNameBytes() {
        return ByteString.copyFromUtf8(this.resourceName_);
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public Struct getResponse() {
        Struct struct = this.response_;
        return struct == null ? Struct.DEFAULT_INSTANCE : struct;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public Any getServiceData() {
        Any any = this.serviceData_;
        return any == null ? Any.DEFAULT_INSTANCE : any;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public String getServiceName() {
        return this.serviceName_;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public ByteString getServiceNameBytes() {
        return ByteString.copyFromUtf8(this.serviceName_);
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public Status getStatus() {
        Status status = this.status_;
        return status == null ? Status.DEFAULT_INSTANCE : status;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public boolean hasAuthenticationInfo() {
        return this.authenticationInfo_ != null;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public boolean hasRequest() {
        return this.request_ != null;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public boolean hasRequestMetadata() {
        return this.requestMetadata_ != null;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public boolean hasResponse() {
        return this.response_ != null;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public boolean hasServiceData() {
        return this.serviceData_ != null;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public boolean hasStatus() {
        return this.status_ != null;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<AuditLog, Builder> implements AuditLogOrBuilder {
        public Builder() {
            super(AuditLog.DEFAULT_INSTANCE);
        }

        public Builder addAllAuthorizationInfo(Iterable<? extends AuthorizationInfo> iterable) {
            copyOnWrite();
            AuditLog.access$2100((AuditLog) this.instance, iterable);
            return this;
        }

        public Builder addAuthorizationInfo(AuthorizationInfo authorizationInfo) {
            copyOnWrite();
            ((AuditLog) this.instance).addAuthorizationInfo(authorizationInfo);
            return this;
        }

        public Builder clearAuthenticationInfo() {
            copyOnWrite();
            ((AuditLog) this.instance).authenticationInfo_ = null;
            return this;
        }

        public Builder clearAuthorizationInfo() {
            copyOnWrite();
            ((AuditLog) this.instance).clearAuthorizationInfo();
            return this;
        }

        public Builder clearMethodName() {
            copyOnWrite();
            ((AuditLog) this.instance).clearMethodName();
            return this;
        }

        public Builder clearNumResponseItems() {
            copyOnWrite();
            ((AuditLog) this.instance).numResponseItems_ = 0L;
            return this;
        }

        public Builder clearRequest() {
            copyOnWrite();
            ((AuditLog) this.instance).request_ = null;
            return this;
        }

        public Builder clearRequestMetadata() {
            copyOnWrite();
            ((AuditLog) this.instance).requestMetadata_ = null;
            return this;
        }

        public Builder clearResourceName() {
            copyOnWrite();
            ((AuditLog) this.instance).clearResourceName();
            return this;
        }

        public Builder clearResponse() {
            copyOnWrite();
            ((AuditLog) this.instance).response_ = null;
            return this;
        }

        public Builder clearServiceData() {
            copyOnWrite();
            ((AuditLog) this.instance).serviceData_ = null;
            return this;
        }

        public Builder clearServiceName() {
            copyOnWrite();
            ((AuditLog) this.instance).clearServiceName();
            return this;
        }

        public Builder clearStatus() {
            copyOnWrite();
            ((AuditLog) this.instance).status_ = null;
            return this;
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public AuthenticationInfo getAuthenticationInfo() {
            return ((AuditLog) this.instance).getAuthenticationInfo();
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public AuthorizationInfo getAuthorizationInfo(int i) {
            return ((AuditLog) this.instance).getAuthorizationInfo(i);
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public int getAuthorizationInfoCount() {
            return ((AuditLog) this.instance).getAuthorizationInfoCount();
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public List<AuthorizationInfo> getAuthorizationInfoList() {
            return Collections.unmodifiableList(((AuditLog) this.instance).getAuthorizationInfoList());
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public String getMethodName() {
            return ((AuditLog) this.instance).getMethodName();
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public ByteString getMethodNameBytes() {
            return ((AuditLog) this.instance).getMethodNameBytes();
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public long getNumResponseItems() {
            return ((AuditLog) this.instance).getNumResponseItems();
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public Struct getRequest() {
            return ((AuditLog) this.instance).getRequest();
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public RequestMetadata getRequestMetadata() {
            return ((AuditLog) this.instance).getRequestMetadata();
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public String getResourceName() {
            return ((AuditLog) this.instance).getResourceName();
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public ByteString getResourceNameBytes() {
            return ((AuditLog) this.instance).getResourceNameBytes();
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public Struct getResponse() {
            return ((AuditLog) this.instance).getResponse();
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public Any getServiceData() {
            return ((AuditLog) this.instance).getServiceData();
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public String getServiceName() {
            return ((AuditLog) this.instance).getServiceName();
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public ByteString getServiceNameBytes() {
            return ((AuditLog) this.instance).getServiceNameBytes();
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public Status getStatus() {
            return ((AuditLog) this.instance).getStatus();
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public boolean hasAuthenticationInfo() {
            return ((AuditLog) this.instance).hasAuthenticationInfo();
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public boolean hasRequest() {
            return ((AuditLog) this.instance).hasRequest();
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public boolean hasRequestMetadata() {
            return ((AuditLog) this.instance).hasRequestMetadata();
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public boolean hasResponse() {
            return ((AuditLog) this.instance).hasResponse();
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public boolean hasServiceData() {
            return ((AuditLog) this.instance).hasServiceData();
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public boolean hasStatus() {
            return ((AuditLog) this.instance).hasStatus();
        }

        public Builder mergeAuthenticationInfo(AuthenticationInfo authenticationInfo) {
            copyOnWrite();
            ((AuditLog) this.instance).mergeAuthenticationInfo(authenticationInfo);
            return this;
        }

        public Builder mergeRequest(Struct struct) {
            copyOnWrite();
            ((AuditLog) this.instance).mergeRequest(struct);
            return this;
        }

        public Builder mergeRequestMetadata(RequestMetadata requestMetadata) {
            copyOnWrite();
            ((AuditLog) this.instance).mergeRequestMetadata(requestMetadata);
            return this;
        }

        public Builder mergeResponse(Struct struct) {
            copyOnWrite();
            ((AuditLog) this.instance).mergeResponse(struct);
            return this;
        }

        public Builder mergeServiceData(Any any) {
            copyOnWrite();
            ((AuditLog) this.instance).mergeServiceData(any);
            return this;
        }

        public Builder mergeStatus(Status status) {
            copyOnWrite();
            ((AuditLog) this.instance).mergeStatus(status);
            return this;
        }

        public Builder removeAuthorizationInfo(int i) {
            copyOnWrite();
            AuditLog.access$2300((AuditLog) this.instance, i);
            return this;
        }

        public Builder setAuthenticationInfo(AuthenticationInfo authenticationInfo) {
            copyOnWrite();
            ((AuditLog) this.instance).setAuthenticationInfo(authenticationInfo);
            return this;
        }

        public Builder setAuthorizationInfo(int i, AuthorizationInfo authorizationInfo) {
            copyOnWrite();
            ((AuditLog) this.instance).setAuthorizationInfo(i, authorizationInfo);
            return this;
        }

        public Builder setMethodName(String str) {
            copyOnWrite();
            ((AuditLog) this.instance).setMethodName(str);
            return this;
        }

        public Builder setMethodNameBytes(ByteString byteString) {
            copyOnWrite();
            ((AuditLog) this.instance).setMethodNameBytes(byteString);
            return this;
        }

        public Builder setNumResponseItems(long j) {
            copyOnWrite();
            ((AuditLog) this.instance).numResponseItems_ = j;
            return this;
        }

        public Builder setRequest(Struct struct) {
            copyOnWrite();
            ((AuditLog) this.instance).setRequest(struct);
            return this;
        }

        public Builder setRequestMetadata(RequestMetadata requestMetadata) {
            copyOnWrite();
            ((AuditLog) this.instance).setRequestMetadata(requestMetadata);
            return this;
        }

        public Builder setResourceName(String str) {
            copyOnWrite();
            ((AuditLog) this.instance).setResourceName(str);
            return this;
        }

        public Builder setResourceNameBytes(ByteString byteString) {
            copyOnWrite();
            ((AuditLog) this.instance).setResourceNameBytes(byteString);
            return this;
        }

        public Builder setResponse(Struct struct) {
            copyOnWrite();
            ((AuditLog) this.instance).setResponse(struct);
            return this;
        }

        public Builder setServiceData(Any any) {
            copyOnWrite();
            ((AuditLog) this.instance).setServiceData(any);
            return this;
        }

        public Builder setServiceName(String str) {
            copyOnWrite();
            ((AuditLog) this.instance).setServiceName(str);
            return this;
        }

        public Builder setServiceNameBytes(ByteString byteString) {
            copyOnWrite();
            ((AuditLog) this.instance).setServiceNameBytes(byteString);
            return this;
        }

        public Builder setStatus(Status status) {
            copyOnWrite();
            ((AuditLog) this.instance).setStatus(status);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(AuditLog.DEFAULT_INSTANCE);
        }

        public Builder addAuthorizationInfo(int i, AuthorizationInfo authorizationInfo) {
            copyOnWrite();
            ((AuditLog) this.instance).addAuthorizationInfo(i, authorizationInfo);
            return this;
        }

        public Builder setAuthenticationInfo(AuthenticationInfo.Builder builder) {
            copyOnWrite();
            ((AuditLog) this.instance).setAuthenticationInfo(builder.build());
            return this;
        }

        public Builder setAuthorizationInfo(int i, AuthorizationInfo.Builder builder) {
            copyOnWrite();
            ((AuditLog) this.instance).setAuthorizationInfo(i, builder.build());
            return this;
        }

        public Builder setRequest(Struct.Builder builder) {
            copyOnWrite();
            ((AuditLog) this.instance).setRequest(builder.build());
            return this;
        }

        public Builder setRequestMetadata(RequestMetadata.Builder builder) {
            copyOnWrite();
            ((AuditLog) this.instance).setRequestMetadata(builder.build());
            return this;
        }

        public Builder setResponse(Struct.Builder builder) {
            copyOnWrite();
            ((AuditLog) this.instance).setResponse(builder.build());
            return this;
        }

        public Builder setServiceData(Any.Builder builder) {
            copyOnWrite();
            ((AuditLog) this.instance).setServiceData(builder.build());
            return this;
        }

        public Builder setStatus(Status.Builder builder) {
            copyOnWrite();
            ((AuditLog) this.instance).setStatus(builder.build());
            return this;
        }

        public Builder addAuthorizationInfo(AuthorizationInfo.Builder builder) {
            copyOnWrite();
            ((AuditLog) this.instance).addAuthorizationInfo(builder.build());
            return this;
        }

        public Builder addAuthorizationInfo(int i, AuthorizationInfo.Builder builder) {
            copyOnWrite();
            ((AuditLog) this.instance).addAuthorizationInfo(i, builder.build());
            return this;
        }
    }

    public static Builder newBuilder(AuditLog auditLog) {
        return DEFAULT_INSTANCE.createBuilder(auditLog);
    }

    public static AuditLog parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (AuditLog) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static AuditLog parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (AuditLog) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static AuditLog parseFrom(ByteString byteString) {
        return (AuditLog) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAuthorizationInfo(int i, AuthorizationInfo authorizationInfo) {
        authorizationInfo.getClass();
        ensureAuthorizationInfoIsMutable();
        this.authorizationInfo_.add(i, authorizationInfo);
    }

    public static AuditLog parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (AuditLog) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static AuditLog parseFrom(byte[] bArr) {
        return (AuditLog) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static AuditLog parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (AuditLog) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static AuditLog parseFrom(InputStream inputStream) {
        return (AuditLog) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static AuditLog parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (AuditLog) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static AuditLog parseFrom(CodedInputStream codedInputStream) {
        return (AuditLog) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static AuditLog parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (AuditLog) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
