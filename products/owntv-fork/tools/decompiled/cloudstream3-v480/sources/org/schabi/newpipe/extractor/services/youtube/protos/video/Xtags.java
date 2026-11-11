package org.schabi.newpipe.extractor.services.youtube.protos.video;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import j$.util.DesugarCollections;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

/* JADX INFO: loaded from: classes8.dex */
public final class Xtags {

    public interface KeyValuePairOrBuilder extends MessageLiteOrBuilder {
        String getKey();

        ByteString getKeyBytes();

        String getValue();

        ByteString getValueBytes();

        boolean hasKey();

        boolean hasValue();
    }

    public interface XTagsOrBuilder extends MessageLiteOrBuilder {
        KeyValuePair getXtags(int i);

        int getXtagsCount();

        List<KeyValuePair> getXtagsList();
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private Xtags() {
    }

    public static final class KeyValuePair extends GeneratedMessageLite<KeyValuePair, Builder> implements KeyValuePairOrBuilder {
        private static final KeyValuePair DEFAULT_INSTANCE;
        public static final int KEY_FIELD_NUMBER = 1;
        private static volatile Parser<KeyValuePair> PARSER = null;
        public static final int VALUE_FIELD_NUMBER = 2;
        private int bitField0_;
        private String key_ = "";
        private String value_ = "";

        private KeyValuePair() {
        }

        @Override // org.schabi.newpipe.extractor.services.youtube.protos.video.Xtags.KeyValuePairOrBuilder
        public boolean hasKey() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // org.schabi.newpipe.extractor.services.youtube.protos.video.Xtags.KeyValuePairOrBuilder
        public String getKey() {
            return this.key_;
        }

        @Override // org.schabi.newpipe.extractor.services.youtube.protos.video.Xtags.KeyValuePairOrBuilder
        public ByteString getKeyBytes() {
            return ByteString.copyFromUtf8(this.key_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setKey(String str) {
            str.getClass();
            this.bitField0_ |= 1;
            this.key_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearKey() {
            this.bitField0_ &= -2;
            this.key_ = getDefaultInstance().getKey();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setKeyBytes(ByteString byteString) {
            checkByteStringIsUtf8(byteString);
            this.key_ = byteString.toStringUtf8();
            this.bitField0_ |= 1;
        }

        @Override // org.schabi.newpipe.extractor.services.youtube.protos.video.Xtags.KeyValuePairOrBuilder
        public boolean hasValue() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // org.schabi.newpipe.extractor.services.youtube.protos.video.Xtags.KeyValuePairOrBuilder
        public String getValue() {
            return this.value_;
        }

        @Override // org.schabi.newpipe.extractor.services.youtube.protos.video.Xtags.KeyValuePairOrBuilder
        public ByteString getValueBytes() {
            return ByteString.copyFromUtf8(this.value_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setValue(String str) {
            str.getClass();
            this.bitField0_ |= 2;
            this.value_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearValue() {
            this.bitField0_ &= -3;
            this.value_ = getDefaultInstance().getValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setValueBytes(ByteString byteString) {
            checkByteStringIsUtf8(byteString);
            this.value_ = byteString.toStringUtf8();
            this.bitField0_ |= 2;
        }

        public static KeyValuePair parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (KeyValuePair) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static KeyValuePair parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (KeyValuePair) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static KeyValuePair parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (KeyValuePair) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static KeyValuePair parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (KeyValuePair) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static KeyValuePair parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (KeyValuePair) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static KeyValuePair parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (KeyValuePair) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static KeyValuePair parseFrom(InputStream inputStream) throws IOException {
            return (KeyValuePair) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static KeyValuePair parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (KeyValuePair) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static KeyValuePair parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (KeyValuePair) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static KeyValuePair parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (KeyValuePair) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static KeyValuePair parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (KeyValuePair) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static KeyValuePair parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (KeyValuePair) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(KeyValuePair keyValuePair) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(keyValuePair);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<KeyValuePair, Builder> implements KeyValuePairOrBuilder {
            private Builder() {
                super(KeyValuePair.DEFAULT_INSTANCE);
            }

            @Override // org.schabi.newpipe.extractor.services.youtube.protos.video.Xtags.KeyValuePairOrBuilder
            public boolean hasKey() {
                return ((KeyValuePair) this.instance).hasKey();
            }

            @Override // org.schabi.newpipe.extractor.services.youtube.protos.video.Xtags.KeyValuePairOrBuilder
            public String getKey() {
                return ((KeyValuePair) this.instance).getKey();
            }

            @Override // org.schabi.newpipe.extractor.services.youtube.protos.video.Xtags.KeyValuePairOrBuilder
            public ByteString getKeyBytes() {
                return ((KeyValuePair) this.instance).getKeyBytes();
            }

            public Builder setKey(String str) {
                copyOnWrite();
                ((KeyValuePair) this.instance).setKey(str);
                return this;
            }

            public Builder clearKey() {
                copyOnWrite();
                ((KeyValuePair) this.instance).clearKey();
                return this;
            }

            public Builder setKeyBytes(ByteString byteString) {
                copyOnWrite();
                ((KeyValuePair) this.instance).setKeyBytes(byteString);
                return this;
            }

            @Override // org.schabi.newpipe.extractor.services.youtube.protos.video.Xtags.KeyValuePairOrBuilder
            public boolean hasValue() {
                return ((KeyValuePair) this.instance).hasValue();
            }

            @Override // org.schabi.newpipe.extractor.services.youtube.protos.video.Xtags.KeyValuePairOrBuilder
            public String getValue() {
                return ((KeyValuePair) this.instance).getValue();
            }

            @Override // org.schabi.newpipe.extractor.services.youtube.protos.video.Xtags.KeyValuePairOrBuilder
            public ByteString getValueBytes() {
                return ((KeyValuePair) this.instance).getValueBytes();
            }

            public Builder setValue(String str) {
                copyOnWrite();
                ((KeyValuePair) this.instance).setValue(str);
                return this;
            }

            public Builder clearValue() {
                copyOnWrite();
                ((KeyValuePair) this.instance).clearValue();
                return this;
            }

            public Builder setValueBytes(ByteString byteString) {
                copyOnWrite();
                ((KeyValuePair) this.instance).setValueBytes(byteString);
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            Parser defaultInstanceBasedParser;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new KeyValuePair();
                case 2:
                    return new Builder();
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ለ\u0000\u0002ለ\u0001", new Object[]{"bitField0_", "key_", "value_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<KeyValuePair> parser = PARSER;
                    if (parser != null) {
                        return parser;
                    }
                    synchronized (KeyValuePair.class) {
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
            KeyValuePair keyValuePair = new KeyValuePair();
            DEFAULT_INSTANCE = keyValuePair;
            GeneratedMessageLite.registerDefaultInstance(KeyValuePair.class, keyValuePair);
        }

        public static KeyValuePair getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<KeyValuePair> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* JADX INFO: renamed from: org.schabi.newpipe.extractor.services.youtube.protos.video.Xtags$1, reason: invalid class name */
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

    public static final class XTags extends GeneratedMessageLite<XTags, Builder> implements XTagsOrBuilder {
        private static final XTags DEFAULT_INSTANCE;
        private static volatile Parser<XTags> PARSER = null;
        public static final int XTAGS_FIELD_NUMBER = 1;
        private Internal.ProtobufList<KeyValuePair> xtags_ = emptyProtobufList();

        private XTags() {
        }

        @Override // org.schabi.newpipe.extractor.services.youtube.protos.video.Xtags.XTagsOrBuilder
        public List<KeyValuePair> getXtagsList() {
            return this.xtags_;
        }

        public List<? extends KeyValuePairOrBuilder> getXtagsOrBuilderList() {
            return this.xtags_;
        }

        @Override // org.schabi.newpipe.extractor.services.youtube.protos.video.Xtags.XTagsOrBuilder
        public int getXtagsCount() {
            return this.xtags_.size();
        }

        @Override // org.schabi.newpipe.extractor.services.youtube.protos.video.Xtags.XTagsOrBuilder
        public KeyValuePair getXtags(int i) {
            return this.xtags_.get(i);
        }

        public KeyValuePairOrBuilder getXtagsOrBuilder(int i) {
            return this.xtags_.get(i);
        }

        private void ensureXtagsIsMutable() {
            Internal.ProtobufList<KeyValuePair> protobufList = this.xtags_;
            if (protobufList.isModifiable()) {
                return;
            }
            this.xtags_ = GeneratedMessageLite.mutableCopy(protobufList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setXtags(int i, KeyValuePair keyValuePair) {
            keyValuePair.getClass();
            ensureXtagsIsMutable();
            this.xtags_.set(i, keyValuePair);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addXtags(KeyValuePair keyValuePair) {
            keyValuePair.getClass();
            ensureXtagsIsMutable();
            this.xtags_.add(keyValuePair);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addXtags(int i, KeyValuePair keyValuePair) {
            keyValuePair.getClass();
            ensureXtagsIsMutable();
            this.xtags_.add(i, keyValuePair);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllXtags(Iterable<? extends KeyValuePair> iterable) {
            ensureXtagsIsMutable();
            AbstractMessageLite.addAll(iterable, this.xtags_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearXtags() {
            this.xtags_ = emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeXtags(int i) {
            ensureXtagsIsMutable();
            this.xtags_.remove(i);
        }

        public static XTags parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (XTags) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static XTags parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (XTags) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static XTags parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (XTags) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static XTags parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (XTags) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static XTags parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (XTags) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static XTags parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (XTags) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static XTags parseFrom(InputStream inputStream) throws IOException {
            return (XTags) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static XTags parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (XTags) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static XTags parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (XTags) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static XTags parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (XTags) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static XTags parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (XTags) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static XTags parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (XTags) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(XTags xTags) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(xTags);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<XTags, Builder> implements XTagsOrBuilder {
            private Builder() {
                super(XTags.DEFAULT_INSTANCE);
            }

            @Override // org.schabi.newpipe.extractor.services.youtube.protos.video.Xtags.XTagsOrBuilder
            public List<KeyValuePair> getXtagsList() {
                return DesugarCollections.unmodifiableList(((XTags) this.instance).getXtagsList());
            }

            @Override // org.schabi.newpipe.extractor.services.youtube.protos.video.Xtags.XTagsOrBuilder
            public int getXtagsCount() {
                return ((XTags) this.instance).getXtagsCount();
            }

            @Override // org.schabi.newpipe.extractor.services.youtube.protos.video.Xtags.XTagsOrBuilder
            public KeyValuePair getXtags(int i) {
                return ((XTags) this.instance).getXtags(i);
            }

            public Builder setXtags(int i, KeyValuePair keyValuePair) {
                copyOnWrite();
                ((XTags) this.instance).setXtags(i, keyValuePair);
                return this;
            }

            public Builder setXtags(int i, KeyValuePair.Builder builder) {
                copyOnWrite();
                ((XTags) this.instance).setXtags(i, builder.build());
                return this;
            }

            public Builder addXtags(KeyValuePair keyValuePair) {
                copyOnWrite();
                ((XTags) this.instance).addXtags(keyValuePair);
                return this;
            }

            public Builder addXtags(int i, KeyValuePair keyValuePair) {
                copyOnWrite();
                ((XTags) this.instance).addXtags(i, keyValuePair);
                return this;
            }

            public Builder addXtags(KeyValuePair.Builder builder) {
                copyOnWrite();
                ((XTags) this.instance).addXtags(builder.build());
                return this;
            }

            public Builder addXtags(int i, KeyValuePair.Builder builder) {
                copyOnWrite();
                ((XTags) this.instance).addXtags(i, builder.build());
                return this;
            }

            public Builder addAllXtags(Iterable<? extends KeyValuePair> iterable) {
                copyOnWrite();
                ((XTags) this.instance).addAllXtags(iterable);
                return this;
            }

            public Builder clearXtags() {
                copyOnWrite();
                ((XTags) this.instance).clearXtags();
                return this;
            }

            public Builder removeXtags(int i) {
                copyOnWrite();
                ((XTags) this.instance).removeXtags(i);
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            Parser defaultInstanceBasedParser;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new XTags();
                case 2:
                    return new Builder();
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"xtags_", KeyValuePair.class});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<XTags> parser = PARSER;
                    if (parser != null) {
                        return parser;
                    }
                    synchronized (XTags.class) {
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
            XTags xTags = new XTags();
            DEFAULT_INSTANCE = xTags;
            GeneratedMessageLite.registerDefaultInstance(XTags.class, xTags);
        }

        public static XTags getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<XTags> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }
}
