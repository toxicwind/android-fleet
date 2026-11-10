package com.google.rpc.context;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MapEntryLite;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.Struct;
import com.google.protobuf.Timestamp;
import com.google.protobuf.WireFormat;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class AttributeContext extends GeneratedMessageLite<AttributeContext, Builder> implements AttributeContextOrBuilder {
    public static final int API_FIELD_NUMBER = 6;
    public static final AttributeContext DEFAULT_INSTANCE;
    public static final int DESTINATION_FIELD_NUMBER = 2;
    public static final int ORIGIN_FIELD_NUMBER = 7;
    public static volatile Parser<AttributeContext> PARSER = null;
    public static final int REQUEST_FIELD_NUMBER = 3;
    public static final int RESOURCE_FIELD_NUMBER = 5;
    public static final int RESPONSE_FIELD_NUMBER = 4;
    public static final int SOURCE_FIELD_NUMBER = 1;
    public Api api_;
    public Peer destination_;
    public Peer origin_;
    public Request request_;
    public Resource resource_;
    public Response response_;
    public Peer source_;

    /* JADX INFO: renamed from: com.google.rpc.context.AttributeContext$1, reason: invalid class name */
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

    public static final class Api extends GeneratedMessageLite<Api, Builder> implements ApiOrBuilder {
        public static final Api DEFAULT_INSTANCE;
        public static final int OPERATION_FIELD_NUMBER = 2;
        public static volatile Parser<Api> PARSER = null;
        public static final int PROTOCOL_FIELD_NUMBER = 3;
        public static final int SERVICE_FIELD_NUMBER = 1;
        public static final int VERSION_FIELD_NUMBER = 4;
        public String service_ = "";
        public String operation_ = "";
        public String protocol_ = "";
        public String version_ = "";

        static {
            Api api = new Api();
            DEFAULT_INSTANCE = api;
            GeneratedMessageLite.defaultInstanceMap.put(Api.class, api);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearOperation() {
            this.operation_ = DEFAULT_INSTANCE.getOperation();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearProtocol() {
            this.protocol_ = DEFAULT_INSTANCE.getProtocol();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearService() {
            this.service_ = DEFAULT_INSTANCE.getService();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearVersion() {
            this.version_ = DEFAULT_INSTANCE.getVersion();
        }

        public static Api getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Api parseDelimitedFrom(InputStream inputStream) {
            return (Api) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Api parseFrom(ByteBuffer byteBuffer) {
            return (Api) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<Api> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setOperation(String str) {
            str.getClass();
            this.operation_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setOperationBytes(ByteString byteString) {
            AbstractMessageLite.checkByteStringIsUtf8(byteString);
            this.operation_ = byteString.toStringUtf8();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setProtocol(String str) {
            str.getClass();
            this.protocol_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setProtocolBytes(ByteString byteString) {
            AbstractMessageLite.checkByteStringIsUtf8(byteString);
            this.protocol_ = byteString.toStringUtf8();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setService(String str) {
            str.getClass();
            this.service_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setServiceBytes(ByteString byteString) {
            AbstractMessageLite.checkByteStringIsUtf8(byteString);
            this.service_ = byteString.toStringUtf8();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setVersion(String str) {
            str.getClass();
            this.version_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setVersionBytes(ByteString byteString) {
            AbstractMessageLite.checkByteStringIsUtf8(byteString);
            this.version_ = byteString.toStringUtf8();
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
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003Ȉ\u0004Ȉ", new Object[]{"service_", "operation_", "protocol_", "version_"});
                case NEW_MUTABLE_INSTANCE:
                    return new Api();
                case NEW_BUILDER:
                    return new Builder(anonymousClass1);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser<Api> defaultInstanceBasedParser = PARSER;
                    if (defaultInstanceBasedParser == null) {
                        synchronized (Api.class) {
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

        @Override // com.google.rpc.context.AttributeContext.ApiOrBuilder
        public String getOperation() {
            return this.operation_;
        }

        @Override // com.google.rpc.context.AttributeContext.ApiOrBuilder
        public ByteString getOperationBytes() {
            return ByteString.copyFromUtf8(this.operation_);
        }

        @Override // com.google.rpc.context.AttributeContext.ApiOrBuilder
        public String getProtocol() {
            return this.protocol_;
        }

        @Override // com.google.rpc.context.AttributeContext.ApiOrBuilder
        public ByteString getProtocolBytes() {
            return ByteString.copyFromUtf8(this.protocol_);
        }

        @Override // com.google.rpc.context.AttributeContext.ApiOrBuilder
        public String getService() {
            return this.service_;
        }

        @Override // com.google.rpc.context.AttributeContext.ApiOrBuilder
        public ByteString getServiceBytes() {
            return ByteString.copyFromUtf8(this.service_);
        }

        @Override // com.google.rpc.context.AttributeContext.ApiOrBuilder
        public String getVersion() {
            return this.version_;
        }

        @Override // com.google.rpc.context.AttributeContext.ApiOrBuilder
        public ByteString getVersionBytes() {
            return ByteString.copyFromUtf8(this.version_);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Api, Builder> implements ApiOrBuilder {
            public Builder() {
                super(Api.DEFAULT_INSTANCE);
            }

            public Builder clearOperation() {
                copyOnWrite();
                ((Api) this.instance).clearOperation();
                return this;
            }

            public Builder clearProtocol() {
                copyOnWrite();
                ((Api) this.instance).clearProtocol();
                return this;
            }

            public Builder clearService() {
                copyOnWrite();
                ((Api) this.instance).clearService();
                return this;
            }

            public Builder clearVersion() {
                copyOnWrite();
                ((Api) this.instance).clearVersion();
                return this;
            }

            @Override // com.google.rpc.context.AttributeContext.ApiOrBuilder
            public String getOperation() {
                return ((Api) this.instance).getOperation();
            }

            @Override // com.google.rpc.context.AttributeContext.ApiOrBuilder
            public ByteString getOperationBytes() {
                return ((Api) this.instance).getOperationBytes();
            }

            @Override // com.google.rpc.context.AttributeContext.ApiOrBuilder
            public String getProtocol() {
                return ((Api) this.instance).getProtocol();
            }

            @Override // com.google.rpc.context.AttributeContext.ApiOrBuilder
            public ByteString getProtocolBytes() {
                return ((Api) this.instance).getProtocolBytes();
            }

            @Override // com.google.rpc.context.AttributeContext.ApiOrBuilder
            public String getService() {
                return ((Api) this.instance).getService();
            }

            @Override // com.google.rpc.context.AttributeContext.ApiOrBuilder
            public ByteString getServiceBytes() {
                return ((Api) this.instance).getServiceBytes();
            }

            @Override // com.google.rpc.context.AttributeContext.ApiOrBuilder
            public String getVersion() {
                return ((Api) this.instance).getVersion();
            }

            @Override // com.google.rpc.context.AttributeContext.ApiOrBuilder
            public ByteString getVersionBytes() {
                return ((Api) this.instance).getVersionBytes();
            }

            public Builder setOperation(String str) {
                copyOnWrite();
                ((Api) this.instance).setOperation(str);
                return this;
            }

            public Builder setOperationBytes(ByteString byteString) {
                copyOnWrite();
                ((Api) this.instance).setOperationBytes(byteString);
                return this;
            }

            public Builder setProtocol(String str) {
                copyOnWrite();
                ((Api) this.instance).setProtocol(str);
                return this;
            }

            public Builder setProtocolBytes(ByteString byteString) {
                copyOnWrite();
                ((Api) this.instance).setProtocolBytes(byteString);
                return this;
            }

            public Builder setService(String str) {
                copyOnWrite();
                ((Api) this.instance).setService(str);
                return this;
            }

            public Builder setServiceBytes(ByteString byteString) {
                copyOnWrite();
                ((Api) this.instance).setServiceBytes(byteString);
                return this;
            }

            public Builder setVersion(String str) {
                copyOnWrite();
                ((Api) this.instance).setVersion(str);
                return this;
            }

            public Builder setVersionBytes(ByteString byteString) {
                copyOnWrite();
                ((Api) this.instance).setVersionBytes(byteString);
                return this;
            }

            public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                super(Api.DEFAULT_INSTANCE);
            }
        }

        public static Builder newBuilder(Api api) {
            return DEFAULT_INSTANCE.createBuilder(api);
        }

        public static Api parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (Api) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Api parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
            return (Api) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static Api parseFrom(ByteString byteString) {
            return (Api) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static Api parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (Api) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static Api parseFrom(byte[] bArr) {
            return (Api) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Api parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (Api) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static Api parseFrom(InputStream inputStream) {
            return (Api) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Api parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (Api) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Api parseFrom(CodedInputStream codedInputStream) {
            return (Api) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static Api parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (Api) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    public interface ApiOrBuilder extends MessageLiteOrBuilder {
        String getOperation();

        ByteString getOperationBytes();

        String getProtocol();

        ByteString getProtocolBytes();

        String getService();

        ByteString getServiceBytes();

        String getVersion();

        ByteString getVersionBytes();
    }

    public static final class Auth extends GeneratedMessageLite<Auth, Builder> implements AuthOrBuilder {
        public static final int ACCESS_LEVELS_FIELD_NUMBER = 5;
        public static final int AUDIENCES_FIELD_NUMBER = 2;
        public static final int CLAIMS_FIELD_NUMBER = 4;
        public static final Auth DEFAULT_INSTANCE;
        public static volatile Parser<Auth> PARSER = null;
        public static final int PRESENTER_FIELD_NUMBER = 3;
        public static final int PRINCIPAL_FIELD_NUMBER = 1;
        public Struct claims_;
        public String principal_ = "";
        public Internal.ProtobufList<String> audiences_ = GeneratedMessageLite.emptyProtobufList();
        public String presenter_ = "";
        public Internal.ProtobufList<String> accessLevels_ = GeneratedMessageLite.emptyProtobufList();

        static {
            Auth auth = new Auth();
            DEFAULT_INSTANCE = auth;
            GeneratedMessageLite.defaultInstanceMap.put(Auth.class, auth);
        }

        public static /* synthetic */ void access$3400(Auth auth, Iterable iterable) {
            auth.ensureAudiencesIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, (List) auth.audiences_);
        }

        public static /* synthetic */ void access$4500(Auth auth, Iterable iterable) {
            auth.ensureAccessLevelsIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, (List) auth.accessLevels_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAccessLevels(String str) {
            str.getClass();
            ensureAccessLevelsIsMutable();
            this.accessLevels_.add(str);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAccessLevelsBytes(ByteString byteString) {
            AbstractMessageLite.checkByteStringIsUtf8(byteString);
            ensureAccessLevelsIsMutable();
            this.accessLevels_.add(byteString.toStringUtf8());
        }

        private void addAllAccessLevels(Iterable<String> iterable) {
            ensureAccessLevelsIsMutable();
            AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.accessLevels_);
        }

        private void addAllAudiences(Iterable<String> iterable) {
            ensureAudiencesIsMutable();
            AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.audiences_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAudiences(String str) {
            str.getClass();
            ensureAudiencesIsMutable();
            this.audiences_.add(str);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAudiencesBytes(ByteString byteString) {
            AbstractMessageLite.checkByteStringIsUtf8(byteString);
            ensureAudiencesIsMutable();
            this.audiences_.add(byteString.toStringUtf8());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAccessLevels() {
            this.accessLevels_ = GeneratedMessageLite.emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAudiences() {
            this.audiences_ = GeneratedMessageLite.emptyProtobufList();
        }

        private void clearClaims() {
            this.claims_ = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPresenter() {
            this.presenter_ = DEFAULT_INSTANCE.getPresenter();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPrincipal() {
            this.principal_ = DEFAULT_INSTANCE.getPrincipal();
        }

        private void ensureAccessLevelsIsMutable() {
            Internal.ProtobufList<String> protobufList = this.accessLevels_;
            if (protobufList.isModifiable()) {
                return;
            }
            this.accessLevels_ = GeneratedMessageLite.mutableCopy(protobufList);
        }

        private void ensureAudiencesIsMutable() {
            Internal.ProtobufList<String> protobufList = this.audiences_;
            if (protobufList.isModifiable()) {
                return;
            }
            this.audiences_ = GeneratedMessageLite.mutableCopy(protobufList);
        }

        public static Auth getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeClaims(Struct struct) {
            struct.getClass();
            Struct struct2 = this.claims_;
            if (struct2 == null || struct2 == Struct.DEFAULT_INSTANCE) {
                this.claims_ = struct;
            } else {
                this.claims_ = Struct.newBuilder(struct2).mergeFrom(struct).buildPartial();
            }
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Auth parseDelimitedFrom(InputStream inputStream) {
            return (Auth) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Auth parseFrom(ByteBuffer byteBuffer) {
            return (Auth) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<Auth> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAccessLevels(int i, String str) {
            str.getClass();
            ensureAccessLevelsIsMutable();
            this.accessLevels_.set(i, str);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAudiences(int i, String str) {
            str.getClass();
            ensureAudiencesIsMutable();
            this.audiences_.set(i, str);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setClaims(Struct struct) {
            struct.getClass();
            this.claims_ = struct;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPresenter(String str) {
            str.getClass();
            this.presenter_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPresenterBytes(ByteString byteString) {
            AbstractMessageLite.checkByteStringIsUtf8(byteString);
            this.presenter_ = byteString.toStringUtf8();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPrincipal(String str) {
            str.getClass();
            this.principal_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPrincipalBytes(ByteString byteString) {
            AbstractMessageLite.checkByteStringIsUtf8(byteString);
            this.principal_ = byteString.toStringUtf8();
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
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0005\u0000\u0000\u0001\u0005\u0005\u0000\u0002\u0000\u0001Ȉ\u0002Ț\u0003Ȉ\u0004\t\u0005Ț", new Object[]{"principal_", "audiences_", "presenter_", "claims_", "accessLevels_"});
                case NEW_MUTABLE_INSTANCE:
                    return new Auth();
                case NEW_BUILDER:
                    return new Builder(anonymousClass1);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser<Auth> defaultInstanceBasedParser = PARSER;
                    if (defaultInstanceBasedParser == null) {
                        synchronized (Auth.class) {
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

        @Override // com.google.rpc.context.AttributeContext.AuthOrBuilder
        public String getAccessLevels(int i) {
            return this.accessLevels_.get(i);
        }

        @Override // com.google.rpc.context.AttributeContext.AuthOrBuilder
        public ByteString getAccessLevelsBytes(int i) {
            return ByteString.copyFromUtf8(this.accessLevels_.get(i));
        }

        @Override // com.google.rpc.context.AttributeContext.AuthOrBuilder
        public int getAccessLevelsCount() {
            return this.accessLevels_.size();
        }

        @Override // com.google.rpc.context.AttributeContext.AuthOrBuilder
        public List<String> getAccessLevelsList() {
            return this.accessLevels_;
        }

        @Override // com.google.rpc.context.AttributeContext.AuthOrBuilder
        public String getAudiences(int i) {
            return this.audiences_.get(i);
        }

        @Override // com.google.rpc.context.AttributeContext.AuthOrBuilder
        public ByteString getAudiencesBytes(int i) {
            return ByteString.copyFromUtf8(this.audiences_.get(i));
        }

        @Override // com.google.rpc.context.AttributeContext.AuthOrBuilder
        public int getAudiencesCount() {
            return this.audiences_.size();
        }

        @Override // com.google.rpc.context.AttributeContext.AuthOrBuilder
        public List<String> getAudiencesList() {
            return this.audiences_;
        }

        @Override // com.google.rpc.context.AttributeContext.AuthOrBuilder
        public Struct getClaims() {
            Struct struct = this.claims_;
            return struct == null ? Struct.DEFAULT_INSTANCE : struct;
        }

        @Override // com.google.rpc.context.AttributeContext.AuthOrBuilder
        public String getPresenter() {
            return this.presenter_;
        }

        @Override // com.google.rpc.context.AttributeContext.AuthOrBuilder
        public ByteString getPresenterBytes() {
            return ByteString.copyFromUtf8(this.presenter_);
        }

        @Override // com.google.rpc.context.AttributeContext.AuthOrBuilder
        public String getPrincipal() {
            return this.principal_;
        }

        @Override // com.google.rpc.context.AttributeContext.AuthOrBuilder
        public ByteString getPrincipalBytes() {
            return ByteString.copyFromUtf8(this.principal_);
        }

        @Override // com.google.rpc.context.AttributeContext.AuthOrBuilder
        public boolean hasClaims() {
            return this.claims_ != null;
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Auth, Builder> implements AuthOrBuilder {
            public Builder() {
                super(Auth.DEFAULT_INSTANCE);
            }

            public Builder addAccessLevels(String str) {
                copyOnWrite();
                ((Auth) this.instance).addAccessLevels(str);
                return this;
            }

            public Builder addAccessLevelsBytes(ByteString byteString) {
                copyOnWrite();
                ((Auth) this.instance).addAccessLevelsBytes(byteString);
                return this;
            }

            public Builder addAllAccessLevels(Iterable<String> iterable) {
                copyOnWrite();
                Auth.access$4500((Auth) this.instance, iterable);
                return this;
            }

            public Builder addAllAudiences(Iterable<String> iterable) {
                copyOnWrite();
                Auth.access$3400((Auth) this.instance, iterable);
                return this;
            }

            public Builder addAudiences(String str) {
                copyOnWrite();
                ((Auth) this.instance).addAudiences(str);
                return this;
            }

            public Builder addAudiencesBytes(ByteString byteString) {
                copyOnWrite();
                ((Auth) this.instance).addAudiencesBytes(byteString);
                return this;
            }

            public Builder clearAccessLevels() {
                copyOnWrite();
                ((Auth) this.instance).clearAccessLevels();
                return this;
            }

            public Builder clearAudiences() {
                copyOnWrite();
                ((Auth) this.instance).clearAudiences();
                return this;
            }

            public Builder clearClaims() {
                copyOnWrite();
                ((Auth) this.instance).claims_ = null;
                return this;
            }

            public Builder clearPresenter() {
                copyOnWrite();
                ((Auth) this.instance).clearPresenter();
                return this;
            }

            public Builder clearPrincipal() {
                copyOnWrite();
                ((Auth) this.instance).clearPrincipal();
                return this;
            }

            @Override // com.google.rpc.context.AttributeContext.AuthOrBuilder
            public String getAccessLevels(int i) {
                return ((Auth) this.instance).getAccessLevels(i);
            }

            @Override // com.google.rpc.context.AttributeContext.AuthOrBuilder
            public ByteString getAccessLevelsBytes(int i) {
                return ((Auth) this.instance).getAccessLevelsBytes(i);
            }

            @Override // com.google.rpc.context.AttributeContext.AuthOrBuilder
            public int getAccessLevelsCount() {
                return ((Auth) this.instance).getAccessLevelsCount();
            }

            @Override // com.google.rpc.context.AttributeContext.AuthOrBuilder
            public List<String> getAccessLevelsList() {
                return Collections.unmodifiableList(((Auth) this.instance).getAccessLevelsList());
            }

            @Override // com.google.rpc.context.AttributeContext.AuthOrBuilder
            public String getAudiences(int i) {
                return ((Auth) this.instance).getAudiences(i);
            }

            @Override // com.google.rpc.context.AttributeContext.AuthOrBuilder
            public ByteString getAudiencesBytes(int i) {
                return ((Auth) this.instance).getAudiencesBytes(i);
            }

            @Override // com.google.rpc.context.AttributeContext.AuthOrBuilder
            public int getAudiencesCount() {
                return ((Auth) this.instance).getAudiencesCount();
            }

            @Override // com.google.rpc.context.AttributeContext.AuthOrBuilder
            public List<String> getAudiencesList() {
                return Collections.unmodifiableList(((Auth) this.instance).getAudiencesList());
            }

            @Override // com.google.rpc.context.AttributeContext.AuthOrBuilder
            public Struct getClaims() {
                return ((Auth) this.instance).getClaims();
            }

            @Override // com.google.rpc.context.AttributeContext.AuthOrBuilder
            public String getPresenter() {
                return ((Auth) this.instance).getPresenter();
            }

            @Override // com.google.rpc.context.AttributeContext.AuthOrBuilder
            public ByteString getPresenterBytes() {
                return ((Auth) this.instance).getPresenterBytes();
            }

            @Override // com.google.rpc.context.AttributeContext.AuthOrBuilder
            public String getPrincipal() {
                return ((Auth) this.instance).getPrincipal();
            }

            @Override // com.google.rpc.context.AttributeContext.AuthOrBuilder
            public ByteString getPrincipalBytes() {
                return ((Auth) this.instance).getPrincipalBytes();
            }

            @Override // com.google.rpc.context.AttributeContext.AuthOrBuilder
            public boolean hasClaims() {
                return ((Auth) this.instance).hasClaims();
            }

            public Builder mergeClaims(Struct struct) {
                copyOnWrite();
                ((Auth) this.instance).mergeClaims(struct);
                return this;
            }

            public Builder setAccessLevels(int i, String str) {
                copyOnWrite();
                ((Auth) this.instance).setAccessLevels(i, str);
                return this;
            }

            public Builder setAudiences(int i, String str) {
                copyOnWrite();
                ((Auth) this.instance).setAudiences(i, str);
                return this;
            }

            public Builder setClaims(Struct struct) {
                copyOnWrite();
                ((Auth) this.instance).setClaims(struct);
                return this;
            }

            public Builder setPresenter(String str) {
                copyOnWrite();
                ((Auth) this.instance).setPresenter(str);
                return this;
            }

            public Builder setPresenterBytes(ByteString byteString) {
                copyOnWrite();
                ((Auth) this.instance).setPresenterBytes(byteString);
                return this;
            }

            public Builder setPrincipal(String str) {
                copyOnWrite();
                ((Auth) this.instance).setPrincipal(str);
                return this;
            }

            public Builder setPrincipalBytes(ByteString byteString) {
                copyOnWrite();
                ((Auth) this.instance).setPrincipalBytes(byteString);
                return this;
            }

            public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                super(Auth.DEFAULT_INSTANCE);
            }

            public Builder setClaims(Struct.Builder builder) {
                copyOnWrite();
                ((Auth) this.instance).setClaims(builder.build());
                return this;
            }
        }

        public static Builder newBuilder(Auth auth) {
            return DEFAULT_INSTANCE.createBuilder(auth);
        }

        public static Auth parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (Auth) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Auth parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
            return (Auth) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static Auth parseFrom(ByteString byteString) {
            return (Auth) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static Auth parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (Auth) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static Auth parseFrom(byte[] bArr) {
            return (Auth) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Auth parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (Auth) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static Auth parseFrom(InputStream inputStream) {
            return (Auth) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Auth parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (Auth) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Auth parseFrom(CodedInputStream codedInputStream) {
            return (Auth) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static Auth parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (Auth) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    public interface AuthOrBuilder extends MessageLiteOrBuilder {
        String getAccessLevels(int i);

        ByteString getAccessLevelsBytes(int i);

        int getAccessLevelsCount();

        List<String> getAccessLevelsList();

        String getAudiences(int i);

        ByteString getAudiencesBytes(int i);

        int getAudiencesCount();

        List<String> getAudiencesList();

        Struct getClaims();

        String getPresenter();

        ByteString getPresenterBytes();

        String getPrincipal();

        ByteString getPrincipalBytes();

        boolean hasClaims();
    }

    public static final class Peer extends GeneratedMessageLite<Peer, Builder> implements PeerOrBuilder {
        public static final Peer DEFAULT_INSTANCE;
        public static final int IP_FIELD_NUMBER = 1;
        public static final int LABELS_FIELD_NUMBER = 6;
        public static volatile Parser<Peer> PARSER = null;
        public static final int PORT_FIELD_NUMBER = 2;
        public static final int PRINCIPAL_FIELD_NUMBER = 7;
        public static final int REGION_CODE_FIELD_NUMBER = 8;
        public long port_;
        public MapFieldLite<String, String> labels_ = MapFieldLite.EMPTY_MAP_FIELD;
        public String ip_ = "";
        public String principal_ = "";
        public String regionCode_ = "";

        private static final class LabelsDefaultEntryHolder {
            public static final MapEntryLite<String, String> defaultEntry;

            static {
                WireFormat.FieldType fieldType = WireFormat.FieldType.STRING;
                defaultEntry = new MapEntryLite<>(fieldType, "", fieldType, "");
            }
        }

        static {
            Peer peer = new Peer();
            DEFAULT_INSTANCE = peer;
            GeneratedMessageLite.defaultInstanceMap.put(Peer.class, peer);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearIp() {
            this.ip_ = DEFAULT_INSTANCE.getIp();
        }

        private void clearPort() {
            this.port_ = 0L;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPrincipal() {
            this.principal_ = DEFAULT_INSTANCE.getPrincipal();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearRegionCode() {
            this.regionCode_ = DEFAULT_INSTANCE.getRegionCode();
        }

        public static Peer getDefaultInstance() {
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

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Peer parseDelimitedFrom(InputStream inputStream) {
            return (Peer) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Peer parseFrom(ByteBuffer byteBuffer) {
            return (Peer) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<Peer> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIp(String str) {
            str.getClass();
            this.ip_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIpBytes(ByteString byteString) {
            AbstractMessageLite.checkByteStringIsUtf8(byteString);
            this.ip_ = byteString.toStringUtf8();
        }

        private void setPort(long j) {
            this.port_ = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPrincipal(String str) {
            str.getClass();
            this.principal_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPrincipalBytes(ByteString byteString) {
            AbstractMessageLite.checkByteStringIsUtf8(byteString);
            this.principal_ = byteString.toStringUtf8();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRegionCode(String str) {
            str.getClass();
            this.regionCode_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRegionCodeBytes(ByteString byteString) {
            AbstractMessageLite.checkByteStringIsUtf8(byteString);
            this.regionCode_ = byteString.toStringUtf8();
        }

        @Override // com.google.rpc.context.AttributeContext.PeerOrBuilder
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
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0005\u0000\u0000\u0001\b\u0005\u0001\u0000\u0000\u0001Ȉ\u0002\u0002\u00062\u0007Ȉ\bȈ", new Object[]{"ip_", "port_", "labels_", LabelsDefaultEntryHolder.defaultEntry, "principal_", "regionCode_"});
                case NEW_MUTABLE_INSTANCE:
                    return new Peer();
                case NEW_BUILDER:
                    return new Builder(anonymousClass1);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser<Peer> defaultInstanceBasedParser = PARSER;
                    if (defaultInstanceBasedParser == null) {
                        synchronized (Peer.class) {
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

        @Override // com.google.rpc.context.AttributeContext.PeerOrBuilder
        public String getIp() {
            return this.ip_;
        }

        @Override // com.google.rpc.context.AttributeContext.PeerOrBuilder
        public ByteString getIpBytes() {
            return ByteString.copyFromUtf8(this.ip_);
        }

        @Override // com.google.rpc.context.AttributeContext.PeerOrBuilder
        @Deprecated
        public Map<String, String> getLabels() {
            return getLabelsMap();
        }

        @Override // com.google.rpc.context.AttributeContext.PeerOrBuilder
        public int getLabelsCount() {
            return this.labels_.size();
        }

        @Override // com.google.rpc.context.AttributeContext.PeerOrBuilder
        public Map<String, String> getLabelsMap() {
            return Collections.unmodifiableMap(this.labels_);
        }

        @Override // com.google.rpc.context.AttributeContext.PeerOrBuilder
        public String getLabelsOrDefault(String str, String str2) {
            str.getClass();
            MapFieldLite<String, String> mapFieldLite = this.labels_;
            return mapFieldLite.containsKey(str) ? mapFieldLite.get(str) : str2;
        }

        @Override // com.google.rpc.context.AttributeContext.PeerOrBuilder
        public String getLabelsOrThrow(String str) {
            str.getClass();
            MapFieldLite<String, String> mapFieldLite = this.labels_;
            if (mapFieldLite.containsKey(str)) {
                return mapFieldLite.get(str);
            }
            throw new IllegalArgumentException();
        }

        @Override // com.google.rpc.context.AttributeContext.PeerOrBuilder
        public long getPort() {
            return this.port_;
        }

        @Override // com.google.rpc.context.AttributeContext.PeerOrBuilder
        public String getPrincipal() {
            return this.principal_;
        }

        @Override // com.google.rpc.context.AttributeContext.PeerOrBuilder
        public ByteString getPrincipalBytes() {
            return ByteString.copyFromUtf8(this.principal_);
        }

        @Override // com.google.rpc.context.AttributeContext.PeerOrBuilder
        public String getRegionCode() {
            return this.regionCode_;
        }

        @Override // com.google.rpc.context.AttributeContext.PeerOrBuilder
        public ByteString getRegionCodeBytes() {
            return ByteString.copyFromUtf8(this.regionCode_);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Peer, Builder> implements PeerOrBuilder {
            public Builder() {
                super(Peer.DEFAULT_INSTANCE);
            }

            public Builder clearIp() {
                copyOnWrite();
                ((Peer) this.instance).clearIp();
                return this;
            }

            public Builder clearLabels() {
                copyOnWrite();
                ((Peer) this.instance).internalGetMutableLabels().clear();
                return this;
            }

            public Builder clearPort() {
                copyOnWrite();
                ((Peer) this.instance).port_ = 0L;
                return this;
            }

            public Builder clearPrincipal() {
                copyOnWrite();
                ((Peer) this.instance).clearPrincipal();
                return this;
            }

            public Builder clearRegionCode() {
                copyOnWrite();
                ((Peer) this.instance).clearRegionCode();
                return this;
            }

            @Override // com.google.rpc.context.AttributeContext.PeerOrBuilder
            public boolean containsLabels(String str) {
                str.getClass();
                return ((Peer) this.instance).getLabelsMap().containsKey(str);
            }

            @Override // com.google.rpc.context.AttributeContext.PeerOrBuilder
            public String getIp() {
                return ((Peer) this.instance).getIp();
            }

            @Override // com.google.rpc.context.AttributeContext.PeerOrBuilder
            public ByteString getIpBytes() {
                return ((Peer) this.instance).getIpBytes();
            }

            @Override // com.google.rpc.context.AttributeContext.PeerOrBuilder
            @Deprecated
            public Map<String, String> getLabels() {
                return getLabelsMap();
            }

            @Override // com.google.rpc.context.AttributeContext.PeerOrBuilder
            public int getLabelsCount() {
                return ((Peer) this.instance).getLabelsMap().size();
            }

            @Override // com.google.rpc.context.AttributeContext.PeerOrBuilder
            public Map<String, String> getLabelsMap() {
                return Collections.unmodifiableMap(((Peer) this.instance).getLabelsMap());
            }

            @Override // com.google.rpc.context.AttributeContext.PeerOrBuilder
            public String getLabelsOrDefault(String str, String str2) {
                str.getClass();
                Map<String, String> labelsMap = ((Peer) this.instance).getLabelsMap();
                return labelsMap.containsKey(str) ? labelsMap.get(str) : str2;
            }

            @Override // com.google.rpc.context.AttributeContext.PeerOrBuilder
            public String getLabelsOrThrow(String str) {
                str.getClass();
                Map<String, String> labelsMap = ((Peer) this.instance).getLabelsMap();
                if (labelsMap.containsKey(str)) {
                    return labelsMap.get(str);
                }
                throw new IllegalArgumentException();
            }

            @Override // com.google.rpc.context.AttributeContext.PeerOrBuilder
            public long getPort() {
                return ((Peer) this.instance).getPort();
            }

            @Override // com.google.rpc.context.AttributeContext.PeerOrBuilder
            public String getPrincipal() {
                return ((Peer) this.instance).getPrincipal();
            }

            @Override // com.google.rpc.context.AttributeContext.PeerOrBuilder
            public ByteString getPrincipalBytes() {
                return ((Peer) this.instance).getPrincipalBytes();
            }

            @Override // com.google.rpc.context.AttributeContext.PeerOrBuilder
            public String getRegionCode() {
                return ((Peer) this.instance).getRegionCode();
            }

            @Override // com.google.rpc.context.AttributeContext.PeerOrBuilder
            public ByteString getRegionCodeBytes() {
                return ((Peer) this.instance).getRegionCodeBytes();
            }

            public Builder putAllLabels(Map<String, String> map) {
                copyOnWrite();
                ((Peer) this.instance).internalGetMutableLabels().putAll(map);
                return this;
            }

            public Builder putLabels(String str, String str2) {
                str.getClass();
                str2.getClass();
                copyOnWrite();
                ((Peer) this.instance).internalGetMutableLabels().put(str, str2);
                return this;
            }

            public Builder removeLabels(String str) {
                str.getClass();
                copyOnWrite();
                ((Peer) this.instance).internalGetMutableLabels().remove(str);
                return this;
            }

            public Builder setIp(String str) {
                copyOnWrite();
                ((Peer) this.instance).setIp(str);
                return this;
            }

            public Builder setIpBytes(ByteString byteString) {
                copyOnWrite();
                ((Peer) this.instance).setIpBytes(byteString);
                return this;
            }

            public Builder setPort(long j) {
                copyOnWrite();
                ((Peer) this.instance).port_ = j;
                return this;
            }

            public Builder setPrincipal(String str) {
                copyOnWrite();
                ((Peer) this.instance).setPrincipal(str);
                return this;
            }

            public Builder setPrincipalBytes(ByteString byteString) {
                copyOnWrite();
                ((Peer) this.instance).setPrincipalBytes(byteString);
                return this;
            }

            public Builder setRegionCode(String str) {
                copyOnWrite();
                ((Peer) this.instance).setRegionCode(str);
                return this;
            }

            public Builder setRegionCodeBytes(ByteString byteString) {
                copyOnWrite();
                ((Peer) this.instance).setRegionCodeBytes(byteString);
                return this;
            }

            public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                super(Peer.DEFAULT_INSTANCE);
            }
        }

        public static Builder newBuilder(Peer peer) {
            return DEFAULT_INSTANCE.createBuilder(peer);
        }

        public static Peer parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (Peer) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Peer parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
            return (Peer) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static Peer parseFrom(ByteString byteString) {
            return (Peer) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static Peer parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (Peer) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static Peer parseFrom(byte[] bArr) {
            return (Peer) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Peer parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (Peer) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static Peer parseFrom(InputStream inputStream) {
            return (Peer) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Peer parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (Peer) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Peer parseFrom(CodedInputStream codedInputStream) {
            return (Peer) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static Peer parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (Peer) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    public interface PeerOrBuilder extends MessageLiteOrBuilder {
        boolean containsLabels(String str);

        String getIp();

        ByteString getIpBytes();

        @Deprecated
        Map<String, String> getLabels();

        int getLabelsCount();

        Map<String, String> getLabelsMap();

        String getLabelsOrDefault(String str, String str2);

        String getLabelsOrThrow(String str);

        long getPort();

        String getPrincipal();

        ByteString getPrincipalBytes();

        String getRegionCode();

        ByteString getRegionCodeBytes();
    }

    public static final class Request extends GeneratedMessageLite<Request, Builder> implements RequestOrBuilder {
        public static final int AUTH_FIELD_NUMBER = 13;
        public static final Request DEFAULT_INSTANCE;
        public static final int HEADERS_FIELD_NUMBER = 3;
        public static final int HOST_FIELD_NUMBER = 5;
        public static final int ID_FIELD_NUMBER = 1;
        public static final int METHOD_FIELD_NUMBER = 2;
        public static volatile Parser<Request> PARSER = null;
        public static final int PATH_FIELD_NUMBER = 4;
        public static final int PROTOCOL_FIELD_NUMBER = 11;
        public static final int QUERY_FIELD_NUMBER = 7;
        public static final int REASON_FIELD_NUMBER = 12;
        public static final int SCHEME_FIELD_NUMBER = 6;
        public static final int SIZE_FIELD_NUMBER = 10;
        public static final int TIME_FIELD_NUMBER = 9;
        public Auth auth_;
        public long size_;
        public Timestamp time_;
        public MapFieldLite<String, String> headers_ = MapFieldLite.EMPTY_MAP_FIELD;
        public String id_ = "";
        public String method_ = "";
        public String path_ = "";
        public String host_ = "";
        public String scheme_ = "";
        public String query_ = "";
        public String protocol_ = "";
        public String reason_ = "";

        private static final class HeadersDefaultEntryHolder {
            public static final MapEntryLite<String, String> defaultEntry;

            static {
                WireFormat.FieldType fieldType = WireFormat.FieldType.STRING;
                defaultEntry = new MapEntryLite<>(fieldType, "", fieldType, "");
            }
        }

        static {
            Request request = new Request();
            DEFAULT_INSTANCE = request;
            GeneratedMessageLite.defaultInstanceMap.put(Request.class, request);
        }

        private void clearAuth() {
            this.auth_ = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearHost() {
            this.host_ = DEFAULT_INSTANCE.getHost();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearId() {
            this.id_ = DEFAULT_INSTANCE.getId();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearMethod() {
            this.method_ = DEFAULT_INSTANCE.getMethod();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPath() {
            this.path_ = DEFAULT_INSTANCE.getPath();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearProtocol() {
            this.protocol_ = DEFAULT_INSTANCE.getProtocol();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearQuery() {
            this.query_ = DEFAULT_INSTANCE.getQuery();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearReason() {
            this.reason_ = DEFAULT_INSTANCE.getReason();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearScheme() {
            this.scheme_ = DEFAULT_INSTANCE.getScheme();
        }

        private void clearSize() {
            this.size_ = 0L;
        }

        private void clearTime() {
            this.time_ = null;
        }

        public static Request getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        private Map<String, String> getMutableHeadersMap() {
            return internalGetMutableHeaders();
        }

        private MapFieldLite<String, String> internalGetHeaders() {
            return this.headers_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public MapFieldLite<String, String> internalGetMutableHeaders() {
            if (!this.headers_.isMutable()) {
                this.headers_ = this.headers_.mutableCopy();
            }
            return this.headers_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeAuth(Auth auth) {
            auth.getClass();
            Auth auth2 = this.auth_;
            if (auth2 == null || auth2 == Auth.DEFAULT_INSTANCE) {
                this.auth_ = auth;
            } else {
                this.auth_ = Auth.newBuilder(auth2).mergeFrom(auth).buildPartial();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeTime(Timestamp timestamp) {
            timestamp.getClass();
            Timestamp timestamp2 = this.time_;
            if (timestamp2 == null || timestamp2 == Timestamp.DEFAULT_INSTANCE) {
                this.time_ = timestamp;
            } else {
                this.time_ = Timestamp.newBuilder(timestamp2).mergeFrom(timestamp).buildPartial();
            }
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Request parseDelimitedFrom(InputStream inputStream) {
            return (Request) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Request parseFrom(ByteBuffer byteBuffer) {
            return (Request) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<Request> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAuth(Auth auth) {
            auth.getClass();
            this.auth_ = auth;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setHost(String str) {
            str.getClass();
            this.host_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setHostBytes(ByteString byteString) {
            AbstractMessageLite.checkByteStringIsUtf8(byteString);
            this.host_ = byteString.toStringUtf8();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setId(String str) {
            str.getClass();
            this.id_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIdBytes(ByteString byteString) {
            AbstractMessageLite.checkByteStringIsUtf8(byteString);
            this.id_ = byteString.toStringUtf8();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMethod(String str) {
            str.getClass();
            this.method_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMethodBytes(ByteString byteString) {
            AbstractMessageLite.checkByteStringIsUtf8(byteString);
            this.method_ = byteString.toStringUtf8();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPath(String str) {
            str.getClass();
            this.path_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPathBytes(ByteString byteString) {
            AbstractMessageLite.checkByteStringIsUtf8(byteString);
            this.path_ = byteString.toStringUtf8();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setProtocol(String str) {
            str.getClass();
            this.protocol_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setProtocolBytes(ByteString byteString) {
            AbstractMessageLite.checkByteStringIsUtf8(byteString);
            this.protocol_ = byteString.toStringUtf8();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setQuery(String str) {
            str.getClass();
            this.query_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setQueryBytes(ByteString byteString) {
            AbstractMessageLite.checkByteStringIsUtf8(byteString);
            this.query_ = byteString.toStringUtf8();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setReason(String str) {
            str.getClass();
            this.reason_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setReasonBytes(ByteString byteString) {
            AbstractMessageLite.checkByteStringIsUtf8(byteString);
            this.reason_ = byteString.toStringUtf8();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setScheme(String str) {
            str.getClass();
            this.scheme_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSchemeBytes(ByteString byteString) {
            AbstractMessageLite.checkByteStringIsUtf8(byteString);
            this.scheme_ = byteString.toStringUtf8();
        }

        private void setSize(long j) {
            this.size_ = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTime(Timestamp timestamp) {
            timestamp.getClass();
            this.time_ = timestamp;
        }

        @Override // com.google.rpc.context.AttributeContext.RequestOrBuilder
        public boolean containsHeaders(String str) {
            str.getClass();
            return this.headers_.containsKey(str);
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
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\f\u0000\u0000\u0001\r\f\u0001\u0000\u0000\u0001Ȉ\u0002Ȉ\u00032\u0004Ȉ\u0005Ȉ\u0006Ȉ\u0007Ȉ\t\t\n\u0002\u000bȈ\fȈ\r\t", new Object[]{"id_", "method_", "headers_", HeadersDefaultEntryHolder.defaultEntry, "path_", "host_", "scheme_", "query_", "time_", "size_", "protocol_", "reason_", "auth_"});
                case NEW_MUTABLE_INSTANCE:
                    return new Request();
                case NEW_BUILDER:
                    return new Builder(anonymousClass1);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser<Request> defaultInstanceBasedParser = PARSER;
                    if (defaultInstanceBasedParser == null) {
                        synchronized (Request.class) {
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

        @Override // com.google.rpc.context.AttributeContext.RequestOrBuilder
        public Auth getAuth() {
            Auth auth = this.auth_;
            return auth == null ? Auth.DEFAULT_INSTANCE : auth;
        }

        @Override // com.google.rpc.context.AttributeContext.RequestOrBuilder
        @Deprecated
        public Map<String, String> getHeaders() {
            return getHeadersMap();
        }

        @Override // com.google.rpc.context.AttributeContext.RequestOrBuilder
        public int getHeadersCount() {
            return this.headers_.size();
        }

        @Override // com.google.rpc.context.AttributeContext.RequestOrBuilder
        public Map<String, String> getHeadersMap() {
            return Collections.unmodifiableMap(this.headers_);
        }

        @Override // com.google.rpc.context.AttributeContext.RequestOrBuilder
        public String getHeadersOrDefault(String str, String str2) {
            str.getClass();
            MapFieldLite<String, String> mapFieldLite = this.headers_;
            return mapFieldLite.containsKey(str) ? mapFieldLite.get(str) : str2;
        }

        @Override // com.google.rpc.context.AttributeContext.RequestOrBuilder
        public String getHeadersOrThrow(String str) {
            str.getClass();
            MapFieldLite<String, String> mapFieldLite = this.headers_;
            if (mapFieldLite.containsKey(str)) {
                return mapFieldLite.get(str);
            }
            throw new IllegalArgumentException();
        }

        @Override // com.google.rpc.context.AttributeContext.RequestOrBuilder
        public String getHost() {
            return this.host_;
        }

        @Override // com.google.rpc.context.AttributeContext.RequestOrBuilder
        public ByteString getHostBytes() {
            return ByteString.copyFromUtf8(this.host_);
        }

        @Override // com.google.rpc.context.AttributeContext.RequestOrBuilder
        public String getId() {
            return this.id_;
        }

        @Override // com.google.rpc.context.AttributeContext.RequestOrBuilder
        public ByteString getIdBytes() {
            return ByteString.copyFromUtf8(this.id_);
        }

        @Override // com.google.rpc.context.AttributeContext.RequestOrBuilder
        public String getMethod() {
            return this.method_;
        }

        @Override // com.google.rpc.context.AttributeContext.RequestOrBuilder
        public ByteString getMethodBytes() {
            return ByteString.copyFromUtf8(this.method_);
        }

        @Override // com.google.rpc.context.AttributeContext.RequestOrBuilder
        public String getPath() {
            return this.path_;
        }

        @Override // com.google.rpc.context.AttributeContext.RequestOrBuilder
        public ByteString getPathBytes() {
            return ByteString.copyFromUtf8(this.path_);
        }

        @Override // com.google.rpc.context.AttributeContext.RequestOrBuilder
        public String getProtocol() {
            return this.protocol_;
        }

        @Override // com.google.rpc.context.AttributeContext.RequestOrBuilder
        public ByteString getProtocolBytes() {
            return ByteString.copyFromUtf8(this.protocol_);
        }

        @Override // com.google.rpc.context.AttributeContext.RequestOrBuilder
        public String getQuery() {
            return this.query_;
        }

        @Override // com.google.rpc.context.AttributeContext.RequestOrBuilder
        public ByteString getQueryBytes() {
            return ByteString.copyFromUtf8(this.query_);
        }

        @Override // com.google.rpc.context.AttributeContext.RequestOrBuilder
        public String getReason() {
            return this.reason_;
        }

        @Override // com.google.rpc.context.AttributeContext.RequestOrBuilder
        public ByteString getReasonBytes() {
            return ByteString.copyFromUtf8(this.reason_);
        }

        @Override // com.google.rpc.context.AttributeContext.RequestOrBuilder
        public String getScheme() {
            return this.scheme_;
        }

        @Override // com.google.rpc.context.AttributeContext.RequestOrBuilder
        public ByteString getSchemeBytes() {
            return ByteString.copyFromUtf8(this.scheme_);
        }

        @Override // com.google.rpc.context.AttributeContext.RequestOrBuilder
        public long getSize() {
            return this.size_;
        }

        @Override // com.google.rpc.context.AttributeContext.RequestOrBuilder
        public Timestamp getTime() {
            Timestamp timestamp = this.time_;
            return timestamp == null ? Timestamp.DEFAULT_INSTANCE : timestamp;
        }

        @Override // com.google.rpc.context.AttributeContext.RequestOrBuilder
        public boolean hasAuth() {
            return this.auth_ != null;
        }

        @Override // com.google.rpc.context.AttributeContext.RequestOrBuilder
        public boolean hasTime() {
            return this.time_ != null;
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Request, Builder> implements RequestOrBuilder {
            public Builder() {
                super(Request.DEFAULT_INSTANCE);
            }

            public Builder clearAuth() {
                copyOnWrite();
                ((Request) this.instance).auth_ = null;
                return this;
            }

            public Builder clearHeaders() {
                copyOnWrite();
                ((Request) this.instance).internalGetMutableHeaders().clear();
                return this;
            }

            public Builder clearHost() {
                copyOnWrite();
                ((Request) this.instance).clearHost();
                return this;
            }

            public Builder clearId() {
                copyOnWrite();
                ((Request) this.instance).clearId();
                return this;
            }

            public Builder clearMethod() {
                copyOnWrite();
                ((Request) this.instance).clearMethod();
                return this;
            }

            public Builder clearPath() {
                copyOnWrite();
                ((Request) this.instance).clearPath();
                return this;
            }

            public Builder clearProtocol() {
                copyOnWrite();
                ((Request) this.instance).clearProtocol();
                return this;
            }

            public Builder clearQuery() {
                copyOnWrite();
                ((Request) this.instance).clearQuery();
                return this;
            }

            public Builder clearReason() {
                copyOnWrite();
                ((Request) this.instance).clearReason();
                return this;
            }

            public Builder clearScheme() {
                copyOnWrite();
                ((Request) this.instance).clearScheme();
                return this;
            }

            public Builder clearSize() {
                copyOnWrite();
                ((Request) this.instance).size_ = 0L;
                return this;
            }

            public Builder clearTime() {
                copyOnWrite();
                ((Request) this.instance).time_ = null;
                return this;
            }

            @Override // com.google.rpc.context.AttributeContext.RequestOrBuilder
            public boolean containsHeaders(String str) {
                str.getClass();
                return ((Request) this.instance).getHeadersMap().containsKey(str);
            }

            @Override // com.google.rpc.context.AttributeContext.RequestOrBuilder
            public Auth getAuth() {
                return ((Request) this.instance).getAuth();
            }

            @Override // com.google.rpc.context.AttributeContext.RequestOrBuilder
            @Deprecated
            public Map<String, String> getHeaders() {
                return getHeadersMap();
            }

            @Override // com.google.rpc.context.AttributeContext.RequestOrBuilder
            public int getHeadersCount() {
                return ((Request) this.instance).getHeadersMap().size();
            }

            @Override // com.google.rpc.context.AttributeContext.RequestOrBuilder
            public Map<String, String> getHeadersMap() {
                return Collections.unmodifiableMap(((Request) this.instance).getHeadersMap());
            }

            @Override // com.google.rpc.context.AttributeContext.RequestOrBuilder
            public String getHeadersOrDefault(String str, String str2) {
                str.getClass();
                Map<String, String> headersMap = ((Request) this.instance).getHeadersMap();
                return headersMap.containsKey(str) ? headersMap.get(str) : str2;
            }

            @Override // com.google.rpc.context.AttributeContext.RequestOrBuilder
            public String getHeadersOrThrow(String str) {
                str.getClass();
                Map<String, String> headersMap = ((Request) this.instance).getHeadersMap();
                if (headersMap.containsKey(str)) {
                    return headersMap.get(str);
                }
                throw new IllegalArgumentException();
            }

            @Override // com.google.rpc.context.AttributeContext.RequestOrBuilder
            public String getHost() {
                return ((Request) this.instance).getHost();
            }

            @Override // com.google.rpc.context.AttributeContext.RequestOrBuilder
            public ByteString getHostBytes() {
                return ((Request) this.instance).getHostBytes();
            }

            @Override // com.google.rpc.context.AttributeContext.RequestOrBuilder
            public String getId() {
                return ((Request) this.instance).getId();
            }

            @Override // com.google.rpc.context.AttributeContext.RequestOrBuilder
            public ByteString getIdBytes() {
                return ((Request) this.instance).getIdBytes();
            }

            @Override // com.google.rpc.context.AttributeContext.RequestOrBuilder
            public String getMethod() {
                return ((Request) this.instance).getMethod();
            }

            @Override // com.google.rpc.context.AttributeContext.RequestOrBuilder
            public ByteString getMethodBytes() {
                return ((Request) this.instance).getMethodBytes();
            }

            @Override // com.google.rpc.context.AttributeContext.RequestOrBuilder
            public String getPath() {
                return ((Request) this.instance).getPath();
            }

            @Override // com.google.rpc.context.AttributeContext.RequestOrBuilder
            public ByteString getPathBytes() {
                return ((Request) this.instance).getPathBytes();
            }

            @Override // com.google.rpc.context.AttributeContext.RequestOrBuilder
            public String getProtocol() {
                return ((Request) this.instance).getProtocol();
            }

            @Override // com.google.rpc.context.AttributeContext.RequestOrBuilder
            public ByteString getProtocolBytes() {
                return ((Request) this.instance).getProtocolBytes();
            }

            @Override // com.google.rpc.context.AttributeContext.RequestOrBuilder
            public String getQuery() {
                return ((Request) this.instance).getQuery();
            }

            @Override // com.google.rpc.context.AttributeContext.RequestOrBuilder
            public ByteString getQueryBytes() {
                return ((Request) this.instance).getQueryBytes();
            }

            @Override // com.google.rpc.context.AttributeContext.RequestOrBuilder
            public String getReason() {
                return ((Request) this.instance).getReason();
            }

            @Override // com.google.rpc.context.AttributeContext.RequestOrBuilder
            public ByteString getReasonBytes() {
                return ((Request) this.instance).getReasonBytes();
            }

            @Override // com.google.rpc.context.AttributeContext.RequestOrBuilder
            public String getScheme() {
                return ((Request) this.instance).getScheme();
            }

            @Override // com.google.rpc.context.AttributeContext.RequestOrBuilder
            public ByteString getSchemeBytes() {
                return ((Request) this.instance).getSchemeBytes();
            }

            @Override // com.google.rpc.context.AttributeContext.RequestOrBuilder
            public long getSize() {
                return ((Request) this.instance).getSize();
            }

            @Override // com.google.rpc.context.AttributeContext.RequestOrBuilder
            public Timestamp getTime() {
                return ((Request) this.instance).getTime();
            }

            @Override // com.google.rpc.context.AttributeContext.RequestOrBuilder
            public boolean hasAuth() {
                return ((Request) this.instance).hasAuth();
            }

            @Override // com.google.rpc.context.AttributeContext.RequestOrBuilder
            public boolean hasTime() {
                return ((Request) this.instance).hasTime();
            }

            public Builder mergeAuth(Auth auth) {
                copyOnWrite();
                ((Request) this.instance).mergeAuth(auth);
                return this;
            }

            public Builder mergeTime(Timestamp timestamp) {
                copyOnWrite();
                ((Request) this.instance).mergeTime(timestamp);
                return this;
            }

            public Builder putAllHeaders(Map<String, String> map) {
                copyOnWrite();
                ((Request) this.instance).internalGetMutableHeaders().putAll(map);
                return this;
            }

            public Builder putHeaders(String str, String str2) {
                str.getClass();
                str2.getClass();
                copyOnWrite();
                ((Request) this.instance).internalGetMutableHeaders().put(str, str2);
                return this;
            }

            public Builder removeHeaders(String str) {
                str.getClass();
                copyOnWrite();
                ((Request) this.instance).internalGetMutableHeaders().remove(str);
                return this;
            }

            public Builder setAuth(Auth auth) {
                copyOnWrite();
                ((Request) this.instance).setAuth(auth);
                return this;
            }

            public Builder setHost(String str) {
                copyOnWrite();
                ((Request) this.instance).setHost(str);
                return this;
            }

            public Builder setHostBytes(ByteString byteString) {
                copyOnWrite();
                ((Request) this.instance).setHostBytes(byteString);
                return this;
            }

            public Builder setId(String str) {
                copyOnWrite();
                ((Request) this.instance).setId(str);
                return this;
            }

            public Builder setIdBytes(ByteString byteString) {
                copyOnWrite();
                ((Request) this.instance).setIdBytes(byteString);
                return this;
            }

            public Builder setMethod(String str) {
                copyOnWrite();
                ((Request) this.instance).setMethod(str);
                return this;
            }

            public Builder setMethodBytes(ByteString byteString) {
                copyOnWrite();
                ((Request) this.instance).setMethodBytes(byteString);
                return this;
            }

            public Builder setPath(String str) {
                copyOnWrite();
                ((Request) this.instance).setPath(str);
                return this;
            }

            public Builder setPathBytes(ByteString byteString) {
                copyOnWrite();
                ((Request) this.instance).setPathBytes(byteString);
                return this;
            }

            public Builder setProtocol(String str) {
                copyOnWrite();
                ((Request) this.instance).setProtocol(str);
                return this;
            }

            public Builder setProtocolBytes(ByteString byteString) {
                copyOnWrite();
                ((Request) this.instance).setProtocolBytes(byteString);
                return this;
            }

            public Builder setQuery(String str) {
                copyOnWrite();
                ((Request) this.instance).setQuery(str);
                return this;
            }

            public Builder setQueryBytes(ByteString byteString) {
                copyOnWrite();
                ((Request) this.instance).setQueryBytes(byteString);
                return this;
            }

            public Builder setReason(String str) {
                copyOnWrite();
                ((Request) this.instance).setReason(str);
                return this;
            }

            public Builder setReasonBytes(ByteString byteString) {
                copyOnWrite();
                ((Request) this.instance).setReasonBytes(byteString);
                return this;
            }

            public Builder setScheme(String str) {
                copyOnWrite();
                ((Request) this.instance).setScheme(str);
                return this;
            }

            public Builder setSchemeBytes(ByteString byteString) {
                copyOnWrite();
                ((Request) this.instance).setSchemeBytes(byteString);
                return this;
            }

            public Builder setSize(long j) {
                copyOnWrite();
                ((Request) this.instance).size_ = j;
                return this;
            }

            public Builder setTime(Timestamp timestamp) {
                copyOnWrite();
                ((Request) this.instance).setTime(timestamp);
                return this;
            }

            public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                super(Request.DEFAULT_INSTANCE);
            }

            public Builder setAuth(Auth.Builder builder) {
                copyOnWrite();
                ((Request) this.instance).setAuth(builder.build());
                return this;
            }

            public Builder setTime(Timestamp.Builder builder) {
                copyOnWrite();
                ((Request) this.instance).setTime(builder.build());
                return this;
            }
        }

        public static Builder newBuilder(Request request) {
            return DEFAULT_INSTANCE.createBuilder(request);
        }

        public static Request parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (Request) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Request parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
            return (Request) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static Request parseFrom(ByteString byteString) {
            return (Request) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static Request parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (Request) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static Request parseFrom(byte[] bArr) {
            return (Request) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Request parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (Request) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static Request parseFrom(InputStream inputStream) {
            return (Request) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Request parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (Request) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Request parseFrom(CodedInputStream codedInputStream) {
            return (Request) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static Request parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (Request) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    public interface RequestOrBuilder extends MessageLiteOrBuilder {
        boolean containsHeaders(String str);

        Auth getAuth();

        @Deprecated
        Map<String, String> getHeaders();

        int getHeadersCount();

        Map<String, String> getHeadersMap();

        String getHeadersOrDefault(String str, String str2);

        String getHeadersOrThrow(String str);

        String getHost();

        ByteString getHostBytes();

        String getId();

        ByteString getIdBytes();

        String getMethod();

        ByteString getMethodBytes();

        String getPath();

        ByteString getPathBytes();

        String getProtocol();

        ByteString getProtocolBytes();

        String getQuery();

        ByteString getQueryBytes();

        String getReason();

        ByteString getReasonBytes();

        String getScheme();

        ByteString getSchemeBytes();

        long getSize();

        Timestamp getTime();

        boolean hasAuth();

        boolean hasTime();
    }

    public static final class Resource extends GeneratedMessageLite<Resource, Builder> implements ResourceOrBuilder {
        public static final Resource DEFAULT_INSTANCE;
        public static final int LABELS_FIELD_NUMBER = 4;
        public static final int NAME_FIELD_NUMBER = 2;
        public static volatile Parser<Resource> PARSER = null;
        public static final int SERVICE_FIELD_NUMBER = 1;
        public static final int TYPE_FIELD_NUMBER = 3;
        public MapFieldLite<String, String> labels_ = MapFieldLite.EMPTY_MAP_FIELD;
        public String service_ = "";
        public String name_ = "";
        public String type_ = "";

        private static final class LabelsDefaultEntryHolder {
            public static final MapEntryLite<String, String> defaultEntry;

            static {
                WireFormat.FieldType fieldType = WireFormat.FieldType.STRING;
                defaultEntry = new MapEntryLite<>(fieldType, "", fieldType, "");
            }
        }

        static {
            Resource resource = new Resource();
            DEFAULT_INSTANCE = resource;
            GeneratedMessageLite.defaultInstanceMap.put(Resource.class, resource);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearName() {
            this.name_ = DEFAULT_INSTANCE.getName();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearService() {
            this.service_ = DEFAULT_INSTANCE.getService();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearType() {
            this.type_ = DEFAULT_INSTANCE.getType();
        }

        public static Resource getDefaultInstance() {
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

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Resource parseDelimitedFrom(InputStream inputStream) {
            return (Resource) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Resource parseFrom(ByteBuffer byteBuffer) {
            return (Resource) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<Resource> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setName(String str) {
            str.getClass();
            this.name_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNameBytes(ByteString byteString) {
            AbstractMessageLite.checkByteStringIsUtf8(byteString);
            this.name_ = byteString.toStringUtf8();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setService(String str) {
            str.getClass();
            this.service_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setServiceBytes(ByteString byteString) {
            AbstractMessageLite.checkByteStringIsUtf8(byteString);
            this.service_ = byteString.toStringUtf8();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setType(String str) {
            str.getClass();
            this.type_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTypeBytes(ByteString byteString) {
            AbstractMessageLite.checkByteStringIsUtf8(byteString);
            this.type_ = byteString.toStringUtf8();
        }

        @Override // com.google.rpc.context.AttributeContext.ResourceOrBuilder
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
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0001\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003Ȉ\u00042", new Object[]{"service_", "name_", "type_", "labels_", LabelsDefaultEntryHolder.defaultEntry});
                case NEW_MUTABLE_INSTANCE:
                    return new Resource();
                case NEW_BUILDER:
                    return new Builder(anonymousClass1);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser<Resource> defaultInstanceBasedParser = PARSER;
                    if (defaultInstanceBasedParser == null) {
                        synchronized (Resource.class) {
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

        @Override // com.google.rpc.context.AttributeContext.ResourceOrBuilder
        @Deprecated
        public Map<String, String> getLabels() {
            return getLabelsMap();
        }

        @Override // com.google.rpc.context.AttributeContext.ResourceOrBuilder
        public int getLabelsCount() {
            return this.labels_.size();
        }

        @Override // com.google.rpc.context.AttributeContext.ResourceOrBuilder
        public Map<String, String> getLabelsMap() {
            return Collections.unmodifiableMap(this.labels_);
        }

        @Override // com.google.rpc.context.AttributeContext.ResourceOrBuilder
        public String getLabelsOrDefault(String str, String str2) {
            str.getClass();
            MapFieldLite<String, String> mapFieldLite = this.labels_;
            return mapFieldLite.containsKey(str) ? mapFieldLite.get(str) : str2;
        }

        @Override // com.google.rpc.context.AttributeContext.ResourceOrBuilder
        public String getLabelsOrThrow(String str) {
            str.getClass();
            MapFieldLite<String, String> mapFieldLite = this.labels_;
            if (mapFieldLite.containsKey(str)) {
                return mapFieldLite.get(str);
            }
            throw new IllegalArgumentException();
        }

        @Override // com.google.rpc.context.AttributeContext.ResourceOrBuilder
        public String getName() {
            return this.name_;
        }

        @Override // com.google.rpc.context.AttributeContext.ResourceOrBuilder
        public ByteString getNameBytes() {
            return ByteString.copyFromUtf8(this.name_);
        }

        @Override // com.google.rpc.context.AttributeContext.ResourceOrBuilder
        public String getService() {
            return this.service_;
        }

        @Override // com.google.rpc.context.AttributeContext.ResourceOrBuilder
        public ByteString getServiceBytes() {
            return ByteString.copyFromUtf8(this.service_);
        }

        @Override // com.google.rpc.context.AttributeContext.ResourceOrBuilder
        public String getType() {
            return this.type_;
        }

        @Override // com.google.rpc.context.AttributeContext.ResourceOrBuilder
        public ByteString getTypeBytes() {
            return ByteString.copyFromUtf8(this.type_);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Resource, Builder> implements ResourceOrBuilder {
            public Builder() {
                super(Resource.DEFAULT_INSTANCE);
            }

            public Builder clearLabels() {
                copyOnWrite();
                ((Resource) this.instance).internalGetMutableLabels().clear();
                return this;
            }

            public Builder clearName() {
                copyOnWrite();
                ((Resource) this.instance).clearName();
                return this;
            }

            public Builder clearService() {
                copyOnWrite();
                ((Resource) this.instance).clearService();
                return this;
            }

            public Builder clearType() {
                copyOnWrite();
                ((Resource) this.instance).clearType();
                return this;
            }

            @Override // com.google.rpc.context.AttributeContext.ResourceOrBuilder
            public boolean containsLabels(String str) {
                str.getClass();
                return ((Resource) this.instance).getLabelsMap().containsKey(str);
            }

            @Override // com.google.rpc.context.AttributeContext.ResourceOrBuilder
            @Deprecated
            public Map<String, String> getLabels() {
                return getLabelsMap();
            }

            @Override // com.google.rpc.context.AttributeContext.ResourceOrBuilder
            public int getLabelsCount() {
                return ((Resource) this.instance).getLabelsMap().size();
            }

            @Override // com.google.rpc.context.AttributeContext.ResourceOrBuilder
            public Map<String, String> getLabelsMap() {
                return Collections.unmodifiableMap(((Resource) this.instance).getLabelsMap());
            }

            @Override // com.google.rpc.context.AttributeContext.ResourceOrBuilder
            public String getLabelsOrDefault(String str, String str2) {
                str.getClass();
                Map<String, String> labelsMap = ((Resource) this.instance).getLabelsMap();
                return labelsMap.containsKey(str) ? labelsMap.get(str) : str2;
            }

            @Override // com.google.rpc.context.AttributeContext.ResourceOrBuilder
            public String getLabelsOrThrow(String str) {
                str.getClass();
                Map<String, String> labelsMap = ((Resource) this.instance).getLabelsMap();
                if (labelsMap.containsKey(str)) {
                    return labelsMap.get(str);
                }
                throw new IllegalArgumentException();
            }

            @Override // com.google.rpc.context.AttributeContext.ResourceOrBuilder
            public String getName() {
                return ((Resource) this.instance).getName();
            }

            @Override // com.google.rpc.context.AttributeContext.ResourceOrBuilder
            public ByteString getNameBytes() {
                return ((Resource) this.instance).getNameBytes();
            }

            @Override // com.google.rpc.context.AttributeContext.ResourceOrBuilder
            public String getService() {
                return ((Resource) this.instance).getService();
            }

            @Override // com.google.rpc.context.AttributeContext.ResourceOrBuilder
            public ByteString getServiceBytes() {
                return ((Resource) this.instance).getServiceBytes();
            }

            @Override // com.google.rpc.context.AttributeContext.ResourceOrBuilder
            public String getType() {
                return ((Resource) this.instance).getType();
            }

            @Override // com.google.rpc.context.AttributeContext.ResourceOrBuilder
            public ByteString getTypeBytes() {
                return ((Resource) this.instance).getTypeBytes();
            }

            public Builder putAllLabels(Map<String, String> map) {
                copyOnWrite();
                ((Resource) this.instance).internalGetMutableLabels().putAll(map);
                return this;
            }

            public Builder putLabels(String str, String str2) {
                str.getClass();
                str2.getClass();
                copyOnWrite();
                ((Resource) this.instance).internalGetMutableLabels().put(str, str2);
                return this;
            }

            public Builder removeLabels(String str) {
                str.getClass();
                copyOnWrite();
                ((Resource) this.instance).internalGetMutableLabels().remove(str);
                return this;
            }

            public Builder setName(String str) {
                copyOnWrite();
                ((Resource) this.instance).setName(str);
                return this;
            }

            public Builder setNameBytes(ByteString byteString) {
                copyOnWrite();
                ((Resource) this.instance).setNameBytes(byteString);
                return this;
            }

            public Builder setService(String str) {
                copyOnWrite();
                ((Resource) this.instance).setService(str);
                return this;
            }

            public Builder setServiceBytes(ByteString byteString) {
                copyOnWrite();
                ((Resource) this.instance).setServiceBytes(byteString);
                return this;
            }

            public Builder setType(String str) {
                copyOnWrite();
                ((Resource) this.instance).setType(str);
                return this;
            }

            public Builder setTypeBytes(ByteString byteString) {
                copyOnWrite();
                ((Resource) this.instance).setTypeBytes(byteString);
                return this;
            }

            public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                super(Resource.DEFAULT_INSTANCE);
            }
        }

        public static Builder newBuilder(Resource resource) {
            return DEFAULT_INSTANCE.createBuilder(resource);
        }

        public static Resource parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (Resource) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Resource parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
            return (Resource) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static Resource parseFrom(ByteString byteString) {
            return (Resource) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static Resource parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (Resource) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static Resource parseFrom(byte[] bArr) {
            return (Resource) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Resource parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (Resource) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static Resource parseFrom(InputStream inputStream) {
            return (Resource) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Resource parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (Resource) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Resource parseFrom(CodedInputStream codedInputStream) {
            return (Resource) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static Resource parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (Resource) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    public interface ResourceOrBuilder extends MessageLiteOrBuilder {
        boolean containsLabels(String str);

        @Deprecated
        Map<String, String> getLabels();

        int getLabelsCount();

        Map<String, String> getLabelsMap();

        String getLabelsOrDefault(String str, String str2);

        String getLabelsOrThrow(String str);

        String getName();

        ByteString getNameBytes();

        String getService();

        ByteString getServiceBytes();

        String getType();

        ByteString getTypeBytes();
    }

    public static final class Response extends GeneratedMessageLite<Response, Builder> implements ResponseOrBuilder {
        public static final int CODE_FIELD_NUMBER = 1;
        public static final Response DEFAULT_INSTANCE;
        public static final int HEADERS_FIELD_NUMBER = 3;
        public static volatile Parser<Response> PARSER = null;
        public static final int SIZE_FIELD_NUMBER = 2;
        public static final int TIME_FIELD_NUMBER = 4;
        public long code_;
        public MapFieldLite<String, String> headers_ = MapFieldLite.EMPTY_MAP_FIELD;
        public long size_;
        public Timestamp time_;

        private static final class HeadersDefaultEntryHolder {
            public static final MapEntryLite<String, String> defaultEntry;

            static {
                WireFormat.FieldType fieldType = WireFormat.FieldType.STRING;
                defaultEntry = new MapEntryLite<>(fieldType, "", fieldType, "");
            }
        }

        static {
            Response response = new Response();
            DEFAULT_INSTANCE = response;
            GeneratedMessageLite.defaultInstanceMap.put(Response.class, response);
        }

        private void clearCode() {
            this.code_ = 0L;
        }

        private void clearSize() {
            this.size_ = 0L;
        }

        private void clearTime() {
            this.time_ = null;
        }

        public static Response getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        private Map<String, String> getMutableHeadersMap() {
            return internalGetMutableHeaders();
        }

        private MapFieldLite<String, String> internalGetHeaders() {
            return this.headers_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public MapFieldLite<String, String> internalGetMutableHeaders() {
            if (!this.headers_.isMutable()) {
                this.headers_ = this.headers_.mutableCopy();
            }
            return this.headers_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeTime(Timestamp timestamp) {
            timestamp.getClass();
            Timestamp timestamp2 = this.time_;
            if (timestamp2 == null || timestamp2 == Timestamp.DEFAULT_INSTANCE) {
                this.time_ = timestamp;
            } else {
                this.time_ = Timestamp.newBuilder(timestamp2).mergeFrom(timestamp).buildPartial();
            }
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Response parseDelimitedFrom(InputStream inputStream) {
            return (Response) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Response parseFrom(ByteBuffer byteBuffer) {
            return (Response) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<Response> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        private void setCode(long j) {
            this.code_ = j;
        }

        private void setSize(long j) {
            this.size_ = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTime(Timestamp timestamp) {
            timestamp.getClass();
            this.time_ = timestamp;
        }

        @Override // com.google.rpc.context.AttributeContext.ResponseOrBuilder
        public boolean containsHeaders(String str) {
            str.getClass();
            return this.headers_.containsKey(str);
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
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0001\u0000\u0000\u0001\u0002\u0002\u0002\u00032\u0004\t", new Object[]{"code_", "size_", "headers_", HeadersDefaultEntryHolder.defaultEntry, "time_"});
                case NEW_MUTABLE_INSTANCE:
                    return new Response();
                case NEW_BUILDER:
                    return new Builder(anonymousClass1);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser<Response> defaultInstanceBasedParser = PARSER;
                    if (defaultInstanceBasedParser == null) {
                        synchronized (Response.class) {
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

        @Override // com.google.rpc.context.AttributeContext.ResponseOrBuilder
        public long getCode() {
            return this.code_;
        }

        @Override // com.google.rpc.context.AttributeContext.ResponseOrBuilder
        @Deprecated
        public Map<String, String> getHeaders() {
            return getHeadersMap();
        }

        @Override // com.google.rpc.context.AttributeContext.ResponseOrBuilder
        public int getHeadersCount() {
            return this.headers_.size();
        }

        @Override // com.google.rpc.context.AttributeContext.ResponseOrBuilder
        public Map<String, String> getHeadersMap() {
            return Collections.unmodifiableMap(this.headers_);
        }

        @Override // com.google.rpc.context.AttributeContext.ResponseOrBuilder
        public String getHeadersOrDefault(String str, String str2) {
            str.getClass();
            MapFieldLite<String, String> mapFieldLite = this.headers_;
            return mapFieldLite.containsKey(str) ? mapFieldLite.get(str) : str2;
        }

        @Override // com.google.rpc.context.AttributeContext.ResponseOrBuilder
        public String getHeadersOrThrow(String str) {
            str.getClass();
            MapFieldLite<String, String> mapFieldLite = this.headers_;
            if (mapFieldLite.containsKey(str)) {
                return mapFieldLite.get(str);
            }
            throw new IllegalArgumentException();
        }

        @Override // com.google.rpc.context.AttributeContext.ResponseOrBuilder
        public long getSize() {
            return this.size_;
        }

        @Override // com.google.rpc.context.AttributeContext.ResponseOrBuilder
        public Timestamp getTime() {
            Timestamp timestamp = this.time_;
            return timestamp == null ? Timestamp.DEFAULT_INSTANCE : timestamp;
        }

        @Override // com.google.rpc.context.AttributeContext.ResponseOrBuilder
        public boolean hasTime() {
            return this.time_ != null;
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Response, Builder> implements ResponseOrBuilder {
            public Builder() {
                super(Response.DEFAULT_INSTANCE);
            }

            public Builder clearCode() {
                copyOnWrite();
                ((Response) this.instance).code_ = 0L;
                return this;
            }

            public Builder clearHeaders() {
                copyOnWrite();
                ((Response) this.instance).internalGetMutableHeaders().clear();
                return this;
            }

            public Builder clearSize() {
                copyOnWrite();
                ((Response) this.instance).size_ = 0L;
                return this;
            }

            public Builder clearTime() {
                copyOnWrite();
                ((Response) this.instance).time_ = null;
                return this;
            }

            @Override // com.google.rpc.context.AttributeContext.ResponseOrBuilder
            public boolean containsHeaders(String str) {
                str.getClass();
                return ((Response) this.instance).getHeadersMap().containsKey(str);
            }

            @Override // com.google.rpc.context.AttributeContext.ResponseOrBuilder
            public long getCode() {
                return ((Response) this.instance).getCode();
            }

            @Override // com.google.rpc.context.AttributeContext.ResponseOrBuilder
            @Deprecated
            public Map<String, String> getHeaders() {
                return getHeadersMap();
            }

            @Override // com.google.rpc.context.AttributeContext.ResponseOrBuilder
            public int getHeadersCount() {
                return ((Response) this.instance).getHeadersMap().size();
            }

            @Override // com.google.rpc.context.AttributeContext.ResponseOrBuilder
            public Map<String, String> getHeadersMap() {
                return Collections.unmodifiableMap(((Response) this.instance).getHeadersMap());
            }

            @Override // com.google.rpc.context.AttributeContext.ResponseOrBuilder
            public String getHeadersOrDefault(String str, String str2) {
                str.getClass();
                Map<String, String> headersMap = ((Response) this.instance).getHeadersMap();
                return headersMap.containsKey(str) ? headersMap.get(str) : str2;
            }

            @Override // com.google.rpc.context.AttributeContext.ResponseOrBuilder
            public String getHeadersOrThrow(String str) {
                str.getClass();
                Map<String, String> headersMap = ((Response) this.instance).getHeadersMap();
                if (headersMap.containsKey(str)) {
                    return headersMap.get(str);
                }
                throw new IllegalArgumentException();
            }

            @Override // com.google.rpc.context.AttributeContext.ResponseOrBuilder
            public long getSize() {
                return ((Response) this.instance).getSize();
            }

            @Override // com.google.rpc.context.AttributeContext.ResponseOrBuilder
            public Timestamp getTime() {
                return ((Response) this.instance).getTime();
            }

            @Override // com.google.rpc.context.AttributeContext.ResponseOrBuilder
            public boolean hasTime() {
                return ((Response) this.instance).hasTime();
            }

            public Builder mergeTime(Timestamp timestamp) {
                copyOnWrite();
                ((Response) this.instance).mergeTime(timestamp);
                return this;
            }

            public Builder putAllHeaders(Map<String, String> map) {
                copyOnWrite();
                ((Response) this.instance).internalGetMutableHeaders().putAll(map);
                return this;
            }

            public Builder putHeaders(String str, String str2) {
                str.getClass();
                str2.getClass();
                copyOnWrite();
                ((Response) this.instance).internalGetMutableHeaders().put(str, str2);
                return this;
            }

            public Builder removeHeaders(String str) {
                str.getClass();
                copyOnWrite();
                ((Response) this.instance).internalGetMutableHeaders().remove(str);
                return this;
            }

            public Builder setCode(long j) {
                copyOnWrite();
                ((Response) this.instance).code_ = j;
                return this;
            }

            public Builder setSize(long j) {
                copyOnWrite();
                ((Response) this.instance).size_ = j;
                return this;
            }

            public Builder setTime(Timestamp timestamp) {
                copyOnWrite();
                ((Response) this.instance).setTime(timestamp);
                return this;
            }

            public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                super(Response.DEFAULT_INSTANCE);
            }

            public Builder setTime(Timestamp.Builder builder) {
                copyOnWrite();
                ((Response) this.instance).setTime(builder.build());
                return this;
            }
        }

        public static Builder newBuilder(Response response) {
            return DEFAULT_INSTANCE.createBuilder(response);
        }

        public static Response parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (Response) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Response parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
            return (Response) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static Response parseFrom(ByteString byteString) {
            return (Response) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static Response parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (Response) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static Response parseFrom(byte[] bArr) {
            return (Response) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Response parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (Response) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static Response parseFrom(InputStream inputStream) {
            return (Response) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Response parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (Response) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Response parseFrom(CodedInputStream codedInputStream) {
            return (Response) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static Response parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (Response) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    public interface ResponseOrBuilder extends MessageLiteOrBuilder {
        boolean containsHeaders(String str);

        long getCode();

        @Deprecated
        Map<String, String> getHeaders();

        int getHeadersCount();

        Map<String, String> getHeadersMap();

        String getHeadersOrDefault(String str, String str2);

        String getHeadersOrThrow(String str);

        long getSize();

        Timestamp getTime();

        boolean hasTime();
    }

    static {
        AttributeContext attributeContext = new AttributeContext();
        DEFAULT_INSTANCE = attributeContext;
        GeneratedMessageLite.defaultInstanceMap.put(AttributeContext.class, attributeContext);
    }

    private void clearApi() {
        this.api_ = null;
    }

    private void clearDestination() {
        this.destination_ = null;
    }

    private void clearOrigin() {
        this.origin_ = null;
    }

    private void clearRequest() {
        this.request_ = null;
    }

    private void clearResource() {
        this.resource_ = null;
    }

    private void clearResponse() {
        this.response_ = null;
    }

    private void clearSource() {
        this.source_ = null;
    }

    public static AttributeContext getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeApi(Api api) {
        api.getClass();
        Api api2 = this.api_;
        if (api2 == null || api2 == Api.DEFAULT_INSTANCE) {
            this.api_ = api;
        } else {
            this.api_ = Api.newBuilder(api2).mergeFrom(api).buildPartial();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeDestination(Peer peer) {
        peer.getClass();
        Peer peer2 = this.destination_;
        if (peer2 == null || peer2 == Peer.DEFAULT_INSTANCE) {
            this.destination_ = peer;
        } else {
            this.destination_ = Peer.newBuilder(peer2).mergeFrom(peer).buildPartial();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeOrigin(Peer peer) {
        peer.getClass();
        Peer peer2 = this.origin_;
        if (peer2 == null || peer2 == Peer.DEFAULT_INSTANCE) {
            this.origin_ = peer;
        } else {
            this.origin_ = Peer.newBuilder(peer2).mergeFrom(peer).buildPartial();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeRequest(Request request) {
        request.getClass();
        Request request2 = this.request_;
        if (request2 == null || request2 == Request.DEFAULT_INSTANCE) {
            this.request_ = request;
        } else {
            this.request_ = Request.newBuilder(request2).mergeFrom(request).buildPartial();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeResource(Resource resource) {
        resource.getClass();
        Resource resource2 = this.resource_;
        if (resource2 == null || resource2 == Resource.DEFAULT_INSTANCE) {
            this.resource_ = resource;
        } else {
            this.resource_ = Resource.newBuilder(resource2).mergeFrom(resource).buildPartial();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeResponse(Response response) {
        response.getClass();
        Response response2 = this.response_;
        if (response2 == null || response2 == Response.DEFAULT_INSTANCE) {
            this.response_ = response;
        } else {
            this.response_ = Response.newBuilder(response2).mergeFrom(response).buildPartial();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeSource(Peer peer) {
        peer.getClass();
        Peer peer2 = this.source_;
        if (peer2 == null || peer2 == Peer.DEFAULT_INSTANCE) {
            this.source_ = peer;
        } else {
            this.source_ = Peer.newBuilder(peer2).mergeFrom(peer).buildPartial();
        }
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static AttributeContext parseDelimitedFrom(InputStream inputStream) {
        return (AttributeContext) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static AttributeContext parseFrom(ByteBuffer byteBuffer) {
        return (AttributeContext) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<AttributeContext> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setApi(Api api) {
        api.getClass();
        this.api_ = api;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDestination(Peer peer) {
        peer.getClass();
        this.destination_ = peer;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOrigin(Peer peer) {
        peer.getClass();
        this.origin_ = peer;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRequest(Request request) {
        request.getClass();
        this.request_ = request;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setResource(Resource resource) {
        resource.getClass();
        this.resource_ = resource;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setResponse(Response response) {
        response.getClass();
        this.response_ = response;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSource(Peer peer) {
        peer.getClass();
        this.source_ = peer;
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0007\u0000\u0000\u0001\u0007\u0007\u0000\u0000\u0000\u0001\t\u0002\t\u0003\t\u0004\t\u0005\t\u0006\t\u0007\t", new Object[]{"source_", "destination_", "request_", "response_", "resource_", "api_", "origin_"});
            case NEW_MUTABLE_INSTANCE:
                return new AttributeContext();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<AttributeContext> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (AttributeContext.class) {
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

    @Override // com.google.rpc.context.AttributeContextOrBuilder
    public Api getApi() {
        Api api = this.api_;
        return api == null ? Api.DEFAULT_INSTANCE : api;
    }

    @Override // com.google.rpc.context.AttributeContextOrBuilder
    public Peer getDestination() {
        Peer peer = this.destination_;
        return peer == null ? Peer.DEFAULT_INSTANCE : peer;
    }

    @Override // com.google.rpc.context.AttributeContextOrBuilder
    public Peer getOrigin() {
        Peer peer = this.origin_;
        return peer == null ? Peer.DEFAULT_INSTANCE : peer;
    }

    @Override // com.google.rpc.context.AttributeContextOrBuilder
    public Request getRequest() {
        Request request = this.request_;
        return request == null ? Request.DEFAULT_INSTANCE : request;
    }

    @Override // com.google.rpc.context.AttributeContextOrBuilder
    public Resource getResource() {
        Resource resource = this.resource_;
        return resource == null ? Resource.DEFAULT_INSTANCE : resource;
    }

    @Override // com.google.rpc.context.AttributeContextOrBuilder
    public Response getResponse() {
        Response response = this.response_;
        return response == null ? Response.DEFAULT_INSTANCE : response;
    }

    @Override // com.google.rpc.context.AttributeContextOrBuilder
    public Peer getSource() {
        Peer peer = this.source_;
        return peer == null ? Peer.DEFAULT_INSTANCE : peer;
    }

    @Override // com.google.rpc.context.AttributeContextOrBuilder
    public boolean hasApi() {
        return this.api_ != null;
    }

    @Override // com.google.rpc.context.AttributeContextOrBuilder
    public boolean hasDestination() {
        return this.destination_ != null;
    }

    @Override // com.google.rpc.context.AttributeContextOrBuilder
    public boolean hasOrigin() {
        return this.origin_ != null;
    }

    @Override // com.google.rpc.context.AttributeContextOrBuilder
    public boolean hasRequest() {
        return this.request_ != null;
    }

    @Override // com.google.rpc.context.AttributeContextOrBuilder
    public boolean hasResource() {
        return this.resource_ != null;
    }

    @Override // com.google.rpc.context.AttributeContextOrBuilder
    public boolean hasResponse() {
        return this.response_ != null;
    }

    @Override // com.google.rpc.context.AttributeContextOrBuilder
    public boolean hasSource() {
        return this.source_ != null;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<AttributeContext, Builder> implements AttributeContextOrBuilder {
        public Builder() {
            super(AttributeContext.DEFAULT_INSTANCE);
        }

        public Builder clearApi() {
            copyOnWrite();
            ((AttributeContext) this.instance).api_ = null;
            return this;
        }

        public Builder clearDestination() {
            copyOnWrite();
            ((AttributeContext) this.instance).destination_ = null;
            return this;
        }

        public Builder clearOrigin() {
            copyOnWrite();
            ((AttributeContext) this.instance).origin_ = null;
            return this;
        }

        public Builder clearRequest() {
            copyOnWrite();
            ((AttributeContext) this.instance).request_ = null;
            return this;
        }

        public Builder clearResource() {
            copyOnWrite();
            ((AttributeContext) this.instance).resource_ = null;
            return this;
        }

        public Builder clearResponse() {
            copyOnWrite();
            ((AttributeContext) this.instance).response_ = null;
            return this;
        }

        public Builder clearSource() {
            copyOnWrite();
            ((AttributeContext) this.instance).source_ = null;
            return this;
        }

        @Override // com.google.rpc.context.AttributeContextOrBuilder
        public Api getApi() {
            return ((AttributeContext) this.instance).getApi();
        }

        @Override // com.google.rpc.context.AttributeContextOrBuilder
        public Peer getDestination() {
            return ((AttributeContext) this.instance).getDestination();
        }

        @Override // com.google.rpc.context.AttributeContextOrBuilder
        public Peer getOrigin() {
            return ((AttributeContext) this.instance).getOrigin();
        }

        @Override // com.google.rpc.context.AttributeContextOrBuilder
        public Request getRequest() {
            return ((AttributeContext) this.instance).getRequest();
        }

        @Override // com.google.rpc.context.AttributeContextOrBuilder
        public Resource getResource() {
            return ((AttributeContext) this.instance).getResource();
        }

        @Override // com.google.rpc.context.AttributeContextOrBuilder
        public Response getResponse() {
            return ((AttributeContext) this.instance).getResponse();
        }

        @Override // com.google.rpc.context.AttributeContextOrBuilder
        public Peer getSource() {
            return ((AttributeContext) this.instance).getSource();
        }

        @Override // com.google.rpc.context.AttributeContextOrBuilder
        public boolean hasApi() {
            return ((AttributeContext) this.instance).hasApi();
        }

        @Override // com.google.rpc.context.AttributeContextOrBuilder
        public boolean hasDestination() {
            return ((AttributeContext) this.instance).hasDestination();
        }

        @Override // com.google.rpc.context.AttributeContextOrBuilder
        public boolean hasOrigin() {
            return ((AttributeContext) this.instance).hasOrigin();
        }

        @Override // com.google.rpc.context.AttributeContextOrBuilder
        public boolean hasRequest() {
            return ((AttributeContext) this.instance).hasRequest();
        }

        @Override // com.google.rpc.context.AttributeContextOrBuilder
        public boolean hasResource() {
            return ((AttributeContext) this.instance).hasResource();
        }

        @Override // com.google.rpc.context.AttributeContextOrBuilder
        public boolean hasResponse() {
            return ((AttributeContext) this.instance).hasResponse();
        }

        @Override // com.google.rpc.context.AttributeContextOrBuilder
        public boolean hasSource() {
            return ((AttributeContext) this.instance).hasSource();
        }

        public Builder mergeApi(Api api) {
            copyOnWrite();
            ((AttributeContext) this.instance).mergeApi(api);
            return this;
        }

        public Builder mergeDestination(Peer peer) {
            copyOnWrite();
            ((AttributeContext) this.instance).mergeDestination(peer);
            return this;
        }

        public Builder mergeOrigin(Peer peer) {
            copyOnWrite();
            ((AttributeContext) this.instance).mergeOrigin(peer);
            return this;
        }

        public Builder mergeRequest(Request request) {
            copyOnWrite();
            ((AttributeContext) this.instance).mergeRequest(request);
            return this;
        }

        public Builder mergeResource(Resource resource) {
            copyOnWrite();
            ((AttributeContext) this.instance).mergeResource(resource);
            return this;
        }

        public Builder mergeResponse(Response response) {
            copyOnWrite();
            ((AttributeContext) this.instance).mergeResponse(response);
            return this;
        }

        public Builder mergeSource(Peer peer) {
            copyOnWrite();
            ((AttributeContext) this.instance).mergeSource(peer);
            return this;
        }

        public Builder setApi(Api api) {
            copyOnWrite();
            ((AttributeContext) this.instance).setApi(api);
            return this;
        }

        public Builder setDestination(Peer peer) {
            copyOnWrite();
            ((AttributeContext) this.instance).setDestination(peer);
            return this;
        }

        public Builder setOrigin(Peer peer) {
            copyOnWrite();
            ((AttributeContext) this.instance).setOrigin(peer);
            return this;
        }

        public Builder setRequest(Request request) {
            copyOnWrite();
            ((AttributeContext) this.instance).setRequest(request);
            return this;
        }

        public Builder setResource(Resource resource) {
            copyOnWrite();
            ((AttributeContext) this.instance).setResource(resource);
            return this;
        }

        public Builder setResponse(Response response) {
            copyOnWrite();
            ((AttributeContext) this.instance).setResponse(response);
            return this;
        }

        public Builder setSource(Peer peer) {
            copyOnWrite();
            ((AttributeContext) this.instance).setSource(peer);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(AttributeContext.DEFAULT_INSTANCE);
        }

        public Builder setApi(Api.Builder builder) {
            copyOnWrite();
            ((AttributeContext) this.instance).setApi(builder.build());
            return this;
        }

        public Builder setDestination(Peer.Builder builder) {
            copyOnWrite();
            ((AttributeContext) this.instance).setDestination(builder.build());
            return this;
        }

        public Builder setOrigin(Peer.Builder builder) {
            copyOnWrite();
            ((AttributeContext) this.instance).setOrigin(builder.build());
            return this;
        }

        public Builder setRequest(Request.Builder builder) {
            copyOnWrite();
            ((AttributeContext) this.instance).setRequest(builder.build());
            return this;
        }

        public Builder setResource(Resource.Builder builder) {
            copyOnWrite();
            ((AttributeContext) this.instance).setResource(builder.build());
            return this;
        }

        public Builder setResponse(Response.Builder builder) {
            copyOnWrite();
            ((AttributeContext) this.instance).setResponse(builder.build());
            return this;
        }

        public Builder setSource(Peer.Builder builder) {
            copyOnWrite();
            ((AttributeContext) this.instance).setSource(builder.build());
            return this;
        }
    }

    public static Builder newBuilder(AttributeContext attributeContext) {
        return DEFAULT_INSTANCE.createBuilder(attributeContext);
    }

    public static AttributeContext parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (AttributeContext) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static AttributeContext parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (AttributeContext) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static AttributeContext parseFrom(ByteString byteString) {
        return (AttributeContext) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static AttributeContext parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (AttributeContext) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static AttributeContext parseFrom(byte[] bArr) {
        return (AttributeContext) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static AttributeContext parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (AttributeContext) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static AttributeContext parseFrom(InputStream inputStream) {
        return (AttributeContext) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static AttributeContext parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (AttributeContext) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static AttributeContext parseFrom(CodedInputStream codedInputStream) {
        return (AttributeContext) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static AttributeContext parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (AttributeContext) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
