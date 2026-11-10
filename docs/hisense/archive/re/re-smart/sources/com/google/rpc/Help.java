package com.google.rpc;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class Help extends GeneratedMessageLite<Help, Builder> implements HelpOrBuilder {
    public static final Help DEFAULT_INSTANCE;
    public static final int LINKS_FIELD_NUMBER = 1;
    public static volatile Parser<Help> PARSER;
    public Internal.ProtobufList<Link> links_ = GeneratedMessageLite.emptyProtobufList();

    /* JADX INFO: renamed from: com.google.rpc.Help$1, reason: invalid class name */
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

    public static final class Link extends GeneratedMessageLite<Link, Builder> implements LinkOrBuilder {
        public static final Link DEFAULT_INSTANCE;
        public static final int DESCRIPTION_FIELD_NUMBER = 1;
        public static volatile Parser<Link> PARSER = null;
        public static final int URL_FIELD_NUMBER = 2;
        public String description_ = "";
        public String url_ = "";

        static {
            Link link = new Link();
            DEFAULT_INSTANCE = link;
            GeneratedMessageLite.defaultInstanceMap.put(Link.class, link);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDescription() {
            this.description_ = DEFAULT_INSTANCE.getDescription();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearUrl() {
            this.url_ = DEFAULT_INSTANCE.getUrl();
        }

        public static Link getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Link parseDelimitedFrom(InputStream inputStream) {
            return (Link) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Link parseFrom(ByteBuffer byteBuffer) {
            return (Link) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<Link> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDescription(String str) {
            str.getClass();
            this.description_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDescriptionBytes(ByteString byteString) {
            AbstractMessageLite.checkByteStringIsUtf8(byteString);
            this.description_ = byteString.toStringUtf8();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUrl(String str) {
            str.getClass();
            this.url_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUrlBytes(ByteString byteString) {
            AbstractMessageLite.checkByteStringIsUtf8(byteString);
            this.url_ = byteString.toStringUtf8();
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
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ", new Object[]{"description_", "url_"});
                case NEW_MUTABLE_INSTANCE:
                    return new Link();
                case NEW_BUILDER:
                    return new Builder(anonymousClass1);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser<Link> defaultInstanceBasedParser = PARSER;
                    if (defaultInstanceBasedParser == null) {
                        synchronized (Link.class) {
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

        @Override // com.google.rpc.Help.LinkOrBuilder
        public String getDescription() {
            return this.description_;
        }

        @Override // com.google.rpc.Help.LinkOrBuilder
        public ByteString getDescriptionBytes() {
            return ByteString.copyFromUtf8(this.description_);
        }

        @Override // com.google.rpc.Help.LinkOrBuilder
        public String getUrl() {
            return this.url_;
        }

        @Override // com.google.rpc.Help.LinkOrBuilder
        public ByteString getUrlBytes() {
            return ByteString.copyFromUtf8(this.url_);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Link, Builder> implements LinkOrBuilder {
            public Builder() {
                super(Link.DEFAULT_INSTANCE);
            }

            public Builder clearDescription() {
                copyOnWrite();
                ((Link) this.instance).clearDescription();
                return this;
            }

            public Builder clearUrl() {
                copyOnWrite();
                ((Link) this.instance).clearUrl();
                return this;
            }

            @Override // com.google.rpc.Help.LinkOrBuilder
            public String getDescription() {
                return ((Link) this.instance).getDescription();
            }

            @Override // com.google.rpc.Help.LinkOrBuilder
            public ByteString getDescriptionBytes() {
                return ((Link) this.instance).getDescriptionBytes();
            }

            @Override // com.google.rpc.Help.LinkOrBuilder
            public String getUrl() {
                return ((Link) this.instance).getUrl();
            }

            @Override // com.google.rpc.Help.LinkOrBuilder
            public ByteString getUrlBytes() {
                return ((Link) this.instance).getUrlBytes();
            }

            public Builder setDescription(String str) {
                copyOnWrite();
                ((Link) this.instance).setDescription(str);
                return this;
            }

            public Builder setDescriptionBytes(ByteString byteString) {
                copyOnWrite();
                ((Link) this.instance).setDescriptionBytes(byteString);
                return this;
            }

            public Builder setUrl(String str) {
                copyOnWrite();
                ((Link) this.instance).setUrl(str);
                return this;
            }

            public Builder setUrlBytes(ByteString byteString) {
                copyOnWrite();
                ((Link) this.instance).setUrlBytes(byteString);
                return this;
            }

            public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                super(Link.DEFAULT_INSTANCE);
            }
        }

        public static Builder newBuilder(Link link) {
            return DEFAULT_INSTANCE.createBuilder(link);
        }

        public static Link parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (Link) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Link parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
            return (Link) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static Link parseFrom(ByteString byteString) {
            return (Link) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static Link parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (Link) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static Link parseFrom(byte[] bArr) {
            return (Link) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Link parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (Link) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static Link parseFrom(InputStream inputStream) {
            return (Link) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Link parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (Link) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Link parseFrom(CodedInputStream codedInputStream) {
            return (Link) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static Link parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (Link) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    public interface LinkOrBuilder extends MessageLiteOrBuilder {
        String getDescription();

        ByteString getDescriptionBytes();

        String getUrl();

        ByteString getUrlBytes();
    }

    static {
        Help help = new Help();
        DEFAULT_INSTANCE = help;
        GeneratedMessageLite.defaultInstanceMap.put(Help.class, help);
    }

    public static /* synthetic */ void access$1200(Help help, Iterable iterable) {
        help.ensureLinksIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) help.links_);
    }

    public static /* synthetic */ void access$1400(Help help, int i) {
        help.ensureLinksIsMutable();
        help.links_.remove(i);
    }

    private void addAllLinks(Iterable<? extends Link> iterable) {
        ensureLinksIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.links_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addLinks(Link link) {
        link.getClass();
        ensureLinksIsMutable();
        this.links_.add(link);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearLinks() {
        this.links_ = GeneratedMessageLite.emptyProtobufList();
    }

    private void ensureLinksIsMutable() {
        Internal.ProtobufList<Link> protobufList = this.links_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.links_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    public static Help getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Help parseDelimitedFrom(InputStream inputStream) {
        return (Help) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Help parseFrom(ByteBuffer byteBuffer) {
        return (Help) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<Help> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    private void removeLinks(int i) {
        ensureLinksIsMutable();
        this.links_.remove(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLinks(int i, Link link) {
        link.getClass();
        ensureLinksIsMutable();
        this.links_.set(i, link);
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"links_", Link.class});
            case NEW_MUTABLE_INSTANCE:
                return new Help();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<Help> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (Help.class) {
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

    @Override // com.google.rpc.HelpOrBuilder
    public Link getLinks(int i) {
        return this.links_.get(i);
    }

    @Override // com.google.rpc.HelpOrBuilder
    public int getLinksCount() {
        return this.links_.size();
    }

    @Override // com.google.rpc.HelpOrBuilder
    public List<Link> getLinksList() {
        return this.links_;
    }

    public LinkOrBuilder getLinksOrBuilder(int i) {
        return this.links_.get(i);
    }

    public List<? extends LinkOrBuilder> getLinksOrBuilderList() {
        return this.links_;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Help, Builder> implements HelpOrBuilder {
        public Builder() {
            super(Help.DEFAULT_INSTANCE);
        }

        public Builder addAllLinks(Iterable<? extends Link> iterable) {
            copyOnWrite();
            Help.access$1200((Help) this.instance, iterable);
            return this;
        }

        public Builder addLinks(Link link) {
            copyOnWrite();
            ((Help) this.instance).addLinks(link);
            return this;
        }

        public Builder clearLinks() {
            copyOnWrite();
            ((Help) this.instance).clearLinks();
            return this;
        }

        @Override // com.google.rpc.HelpOrBuilder
        public Link getLinks(int i) {
            return ((Help) this.instance).getLinks(i);
        }

        @Override // com.google.rpc.HelpOrBuilder
        public int getLinksCount() {
            return ((Help) this.instance).getLinksCount();
        }

        @Override // com.google.rpc.HelpOrBuilder
        public List<Link> getLinksList() {
            return Collections.unmodifiableList(((Help) this.instance).getLinksList());
        }

        public Builder removeLinks(int i) {
            copyOnWrite();
            Help.access$1400((Help) this.instance, i);
            return this;
        }

        public Builder setLinks(int i, Link link) {
            copyOnWrite();
            ((Help) this.instance).setLinks(i, link);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(Help.DEFAULT_INSTANCE);
        }

        public Builder addLinks(int i, Link link) {
            copyOnWrite();
            ((Help) this.instance).addLinks(i, link);
            return this;
        }

        public Builder setLinks(int i, Link.Builder builder) {
            copyOnWrite();
            ((Help) this.instance).setLinks(i, builder.build());
            return this;
        }

        public Builder addLinks(Link.Builder builder) {
            copyOnWrite();
            ((Help) this.instance).addLinks(builder.build());
            return this;
        }

        public Builder addLinks(int i, Link.Builder builder) {
            copyOnWrite();
            ((Help) this.instance).addLinks(i, builder.build());
            return this;
        }
    }

    public static Builder newBuilder(Help help) {
        return DEFAULT_INSTANCE.createBuilder(help);
    }

    public static Help parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Help) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Help parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (Help) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static Help parseFrom(ByteString byteString) {
        return (Help) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addLinks(int i, Link link) {
        link.getClass();
        ensureLinksIsMutable();
        this.links_.add(i, link);
    }

    public static Help parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (Help) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Help parseFrom(byte[] bArr) {
        return (Help) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Help parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (Help) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Help parseFrom(InputStream inputStream) {
        return (Help) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Help parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Help) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Help parseFrom(CodedInputStream codedInputStream) {
        return (Help) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Help parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Help) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
