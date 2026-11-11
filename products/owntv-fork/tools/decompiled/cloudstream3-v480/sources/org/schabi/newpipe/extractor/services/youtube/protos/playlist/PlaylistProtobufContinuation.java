package org.schabi.newpipe.extractor.services.youtube.protos.playlist;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes8.dex */
public final class PlaylistProtobufContinuation {

    public interface ContinuationParamsOrBuilder extends MessageLiteOrBuilder {
        String getBrowseId();

        ByteString getBrowseIdBytes();

        String getContinuationProperties();

        ByteString getContinuationPropertiesBytes();

        String getPlaylistId();

        ByteString getPlaylistIdBytes();
    }

    public interface PlaylistContinuationOrBuilder extends MessageLiteOrBuilder {
        ContinuationParams getParameters();

        boolean hasParameters();
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private PlaylistProtobufContinuation() {
    }

    public static final class PlaylistContinuation extends GeneratedMessageLite<PlaylistContinuation, Builder> implements PlaylistContinuationOrBuilder {
        private static final PlaylistContinuation DEFAULT_INSTANCE;
        public static final int PARAMETERS_FIELD_NUMBER = 80226972;
        private static volatile Parser<PlaylistContinuation> PARSER;
        private int bitField0_;
        private ContinuationParams parameters_;

        private PlaylistContinuation() {
        }

        @Override // org.schabi.newpipe.extractor.services.youtube.protos.playlist.PlaylistProtobufContinuation.PlaylistContinuationOrBuilder
        public boolean hasParameters() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // org.schabi.newpipe.extractor.services.youtube.protos.playlist.PlaylistProtobufContinuation.PlaylistContinuationOrBuilder
        public ContinuationParams getParameters() {
            ContinuationParams continuationParams = this.parameters_;
            return continuationParams == null ? ContinuationParams.getDefaultInstance() : continuationParams;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setParameters(ContinuationParams continuationParams) {
            continuationParams.getClass();
            this.parameters_ = continuationParams;
            this.bitField0_ |= 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeParameters(ContinuationParams continuationParams) {
            continuationParams.getClass();
            ContinuationParams continuationParams2 = this.parameters_;
            if (continuationParams2 != null && continuationParams2 != ContinuationParams.getDefaultInstance()) {
                this.parameters_ = ContinuationParams.newBuilder(this.parameters_).mergeFrom(continuationParams).buildPartial();
            } else {
                this.parameters_ = continuationParams;
            }
            this.bitField0_ |= 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearParameters() {
            this.parameters_ = null;
            this.bitField0_ &= -2;
        }

        public static PlaylistContinuation parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (PlaylistContinuation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static PlaylistContinuation parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (PlaylistContinuation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static PlaylistContinuation parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (PlaylistContinuation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static PlaylistContinuation parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (PlaylistContinuation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static PlaylistContinuation parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (PlaylistContinuation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static PlaylistContinuation parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (PlaylistContinuation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static PlaylistContinuation parseFrom(InputStream inputStream) throws IOException {
            return (PlaylistContinuation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static PlaylistContinuation parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PlaylistContinuation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static PlaylistContinuation parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (PlaylistContinuation) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static PlaylistContinuation parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PlaylistContinuation) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static PlaylistContinuation parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (PlaylistContinuation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static PlaylistContinuation parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PlaylistContinuation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(PlaylistContinuation playlistContinuation) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(playlistContinuation);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<PlaylistContinuation, Builder> implements PlaylistContinuationOrBuilder {
            private Builder() {
                super(PlaylistContinuation.DEFAULT_INSTANCE);
            }

            @Override // org.schabi.newpipe.extractor.services.youtube.protos.playlist.PlaylistProtobufContinuation.PlaylistContinuationOrBuilder
            public boolean hasParameters() {
                return ((PlaylistContinuation) this.instance).hasParameters();
            }

            @Override // org.schabi.newpipe.extractor.services.youtube.protos.playlist.PlaylistProtobufContinuation.PlaylistContinuationOrBuilder
            public ContinuationParams getParameters() {
                return ((PlaylistContinuation) this.instance).getParameters();
            }

            public Builder setParameters(ContinuationParams continuationParams) {
                copyOnWrite();
                ((PlaylistContinuation) this.instance).setParameters(continuationParams);
                return this;
            }

            public Builder setParameters(ContinuationParams.Builder builder) {
                copyOnWrite();
                ((PlaylistContinuation) this.instance).setParameters(builder.build());
                return this;
            }

            public Builder mergeParameters(ContinuationParams continuationParams) {
                copyOnWrite();
                ((PlaylistContinuation) this.instance).mergeParameters(continuationParams);
                return this;
            }

            public Builder clearParameters() {
                copyOnWrite();
                ((PlaylistContinuation) this.instance).clearParameters();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            Parser defaultInstanceBasedParser;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new PlaylistContinuation();
                case 2:
                    return new Builder();
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0001\uea9c♁\uea9c♁\u0001\u0000\u0000\u0000\uea9c♁ဉ\u0000", new Object[]{"bitField0_", "parameters_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<PlaylistContinuation> parser = PARSER;
                    if (parser != null) {
                        return parser;
                    }
                    synchronized (PlaylistContinuation.class) {
                        defaultInstanceBasedParser = PARSER;
                        if (defaultInstanceBasedParser == null) {
                            defaultInstanceBasedParser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            PARSER = defaultInstanceBasedParser;
                        }
                        break;
                    }
                    return defaultInstanceBasedParser;
                case 6:
                    return (byte) 1;
                default:
                    throw null;
            }
        }

        static {
            PlaylistContinuation playlistContinuation = new PlaylistContinuation();
            DEFAULT_INSTANCE = playlistContinuation;
            GeneratedMessageLite.registerDefaultInstance(PlaylistContinuation.class, playlistContinuation);
        }

        public static PlaylistContinuation getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<PlaylistContinuation> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* JADX INFO: renamed from: org.schabi.newpipe.extractor.services.youtube.protos.playlist.PlaylistProtobufContinuation$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
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

    public static final class ContinuationParams extends GeneratedMessageLite<ContinuationParams, Builder> implements ContinuationParamsOrBuilder {
        public static final int BROWSEID_FIELD_NUMBER = 2;
        public static final int CONTINUATIONPROPERTIES_FIELD_NUMBER = 3;
        private static final ContinuationParams DEFAULT_INSTANCE;
        private static volatile Parser<ContinuationParams> PARSER = null;
        public static final int PLAYLISTID_FIELD_NUMBER = 35;
        private String browseId_ = "";
        private String continuationProperties_ = "";
        private String playlistId_ = "";

        private ContinuationParams() {
        }

        @Override // org.schabi.newpipe.extractor.services.youtube.protos.playlist.PlaylistProtobufContinuation.ContinuationParamsOrBuilder
        public String getBrowseId() {
            return this.browseId_;
        }

        @Override // org.schabi.newpipe.extractor.services.youtube.protos.playlist.PlaylistProtobufContinuation.ContinuationParamsOrBuilder
        public ByteString getBrowseIdBytes() {
            return ByteString.copyFromUtf8(this.browseId_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBrowseId(String str) {
            str.getClass();
            this.browseId_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearBrowseId() {
            this.browseId_ = getDefaultInstance().getBrowseId();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBrowseIdBytes(ByteString byteString) {
            checkByteStringIsUtf8(byteString);
            this.browseId_ = byteString.toStringUtf8();
        }

        @Override // org.schabi.newpipe.extractor.services.youtube.protos.playlist.PlaylistProtobufContinuation.ContinuationParamsOrBuilder
        public String getContinuationProperties() {
            return this.continuationProperties_;
        }

        @Override // org.schabi.newpipe.extractor.services.youtube.protos.playlist.PlaylistProtobufContinuation.ContinuationParamsOrBuilder
        public ByteString getContinuationPropertiesBytes() {
            return ByteString.copyFromUtf8(this.continuationProperties_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setContinuationProperties(String str) {
            str.getClass();
            this.continuationProperties_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearContinuationProperties() {
            this.continuationProperties_ = getDefaultInstance().getContinuationProperties();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setContinuationPropertiesBytes(ByteString byteString) {
            checkByteStringIsUtf8(byteString);
            this.continuationProperties_ = byteString.toStringUtf8();
        }

        @Override // org.schabi.newpipe.extractor.services.youtube.protos.playlist.PlaylistProtobufContinuation.ContinuationParamsOrBuilder
        public String getPlaylistId() {
            return this.playlistId_;
        }

        @Override // org.schabi.newpipe.extractor.services.youtube.protos.playlist.PlaylistProtobufContinuation.ContinuationParamsOrBuilder
        public ByteString getPlaylistIdBytes() {
            return ByteString.copyFromUtf8(this.playlistId_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPlaylistId(String str) {
            str.getClass();
            this.playlistId_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPlaylistId() {
            this.playlistId_ = getDefaultInstance().getPlaylistId();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPlaylistIdBytes(ByteString byteString) {
            checkByteStringIsUtf8(byteString);
            this.playlistId_ = byteString.toStringUtf8();
        }

        public static ContinuationParams parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (ContinuationParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static ContinuationParams parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ContinuationParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static ContinuationParams parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (ContinuationParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static ContinuationParams parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ContinuationParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static ContinuationParams parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (ContinuationParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static ContinuationParams parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ContinuationParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static ContinuationParams parseFrom(InputStream inputStream) throws IOException {
            return (ContinuationParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ContinuationParams parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ContinuationParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ContinuationParams parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ContinuationParams) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ContinuationParams parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ContinuationParams) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ContinuationParams parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ContinuationParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static ContinuationParams parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ContinuationParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(ContinuationParams continuationParams) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(continuationParams);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<ContinuationParams, Builder> implements ContinuationParamsOrBuilder {
            private Builder() {
                super(ContinuationParams.DEFAULT_INSTANCE);
            }

            @Override // org.schabi.newpipe.extractor.services.youtube.protos.playlist.PlaylistProtobufContinuation.ContinuationParamsOrBuilder
            public String getBrowseId() {
                return ((ContinuationParams) this.instance).getBrowseId();
            }

            @Override // org.schabi.newpipe.extractor.services.youtube.protos.playlist.PlaylistProtobufContinuation.ContinuationParamsOrBuilder
            public ByteString getBrowseIdBytes() {
                return ((ContinuationParams) this.instance).getBrowseIdBytes();
            }

            public Builder setBrowseId(String str) {
                copyOnWrite();
                ((ContinuationParams) this.instance).setBrowseId(str);
                return this;
            }

            public Builder clearBrowseId() {
                copyOnWrite();
                ((ContinuationParams) this.instance).clearBrowseId();
                return this;
            }

            public Builder setBrowseIdBytes(ByteString byteString) {
                copyOnWrite();
                ((ContinuationParams) this.instance).setBrowseIdBytes(byteString);
                return this;
            }

            @Override // org.schabi.newpipe.extractor.services.youtube.protos.playlist.PlaylistProtobufContinuation.ContinuationParamsOrBuilder
            public String getContinuationProperties() {
                return ((ContinuationParams) this.instance).getContinuationProperties();
            }

            @Override // org.schabi.newpipe.extractor.services.youtube.protos.playlist.PlaylistProtobufContinuation.ContinuationParamsOrBuilder
            public ByteString getContinuationPropertiesBytes() {
                return ((ContinuationParams) this.instance).getContinuationPropertiesBytes();
            }

            public Builder setContinuationProperties(String str) {
                copyOnWrite();
                ((ContinuationParams) this.instance).setContinuationProperties(str);
                return this;
            }

            public Builder clearContinuationProperties() {
                copyOnWrite();
                ((ContinuationParams) this.instance).clearContinuationProperties();
                return this;
            }

            public Builder setContinuationPropertiesBytes(ByteString byteString) {
                copyOnWrite();
                ((ContinuationParams) this.instance).setContinuationPropertiesBytes(byteString);
                return this;
            }

            @Override // org.schabi.newpipe.extractor.services.youtube.protos.playlist.PlaylistProtobufContinuation.ContinuationParamsOrBuilder
            public String getPlaylistId() {
                return ((ContinuationParams) this.instance).getPlaylistId();
            }

            @Override // org.schabi.newpipe.extractor.services.youtube.protos.playlist.PlaylistProtobufContinuation.ContinuationParamsOrBuilder
            public ByteString getPlaylistIdBytes() {
                return ((ContinuationParams) this.instance).getPlaylistIdBytes();
            }

            public Builder setPlaylistId(String str) {
                copyOnWrite();
                ((ContinuationParams) this.instance).setPlaylistId(str);
                return this;
            }

            public Builder clearPlaylistId() {
                copyOnWrite();
                ((ContinuationParams) this.instance).clearPlaylistId();
                return this;
            }

            public Builder setPlaylistIdBytes(ByteString byteString) {
                copyOnWrite();
                ((ContinuationParams) this.instance).setPlaylistIdBytes(byteString);
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            Parser defaultInstanceBasedParser;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new ContinuationParams();
                case 2:
                    return new Builder();
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0002#\u0003\u0000\u0000\u0000\u0002Ȉ\u0003Ȉ#Ȉ", new Object[]{"browseId_", "continuationProperties_", "playlistId_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<ContinuationParams> parser = PARSER;
                    if (parser != null) {
                        return parser;
                    }
                    synchronized (ContinuationParams.class) {
                        defaultInstanceBasedParser = PARSER;
                        if (defaultInstanceBasedParser == null) {
                            defaultInstanceBasedParser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            PARSER = defaultInstanceBasedParser;
                        }
                        break;
                    }
                    return defaultInstanceBasedParser;
                case 6:
                    return (byte) 1;
                default:
                    throw null;
            }
        }

        static {
            ContinuationParams continuationParams = new ContinuationParams();
            DEFAULT_INSTANCE = continuationParams;
            GeneratedMessageLite.registerDefaultInstance(ContinuationParams.class, continuationParams);
        }

        public static ContinuationParams getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ContinuationParams> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }
}
