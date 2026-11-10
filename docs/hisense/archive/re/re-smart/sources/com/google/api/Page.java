package com.google.api;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.Parser;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class Page extends GeneratedMessageLite<Page, Builder> implements PageOrBuilder {
    public static final int CONTENT_FIELD_NUMBER = 2;
    public static final Page DEFAULT_INSTANCE;
    public static final int NAME_FIELD_NUMBER = 1;
    public static volatile Parser<Page> PARSER = null;
    public static final int SUBPAGES_FIELD_NUMBER = 3;
    public String name_ = "";
    public String content_ = "";
    public Internal.ProtobufList<Page> subpages_ = GeneratedMessageLite.emptyProtobufList();

    /* JADX INFO: renamed from: com.google.api.Page$1, reason: invalid class name */
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
        Page page = new Page();
        DEFAULT_INSTANCE = page;
        GeneratedMessageLite.defaultInstanceMap.put(Page.class, page);
    }

    public static /* synthetic */ void access$1000(Page page, Iterable iterable) {
        page.ensureSubpagesIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) page.subpages_);
    }

    public static /* synthetic */ void access$1200(Page page, int i) {
        page.ensureSubpagesIsMutable();
        page.subpages_.remove(i);
    }

    private void addAllSubpages(Iterable<? extends Page> iterable) {
        ensureSubpagesIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.subpages_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addSubpages(Page page) {
        page.getClass();
        ensureSubpagesIsMutable();
        this.subpages_.add(page);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearContent() {
        this.content_ = DEFAULT_INSTANCE.getContent();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearName() {
        this.name_ = DEFAULT_INSTANCE.getName();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearSubpages() {
        this.subpages_ = GeneratedMessageLite.emptyProtobufList();
    }

    private void ensureSubpagesIsMutable() {
        Internal.ProtobufList<Page> protobufList = this.subpages_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.subpages_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    public static Page getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Page parseDelimitedFrom(InputStream inputStream) {
        return (Page) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Page parseFrom(ByteBuffer byteBuffer) {
        return (Page) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<Page> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    private void removeSubpages(int i) {
        ensureSubpagesIsMutable();
        this.subpages_.remove(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setContent(String str) {
        str.getClass();
        this.content_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setContentBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.content_ = byteString.toStringUtf8();
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
    public void setSubpages(int i, Page page) {
        page.getClass();
        ensureSubpagesIsMutable();
        this.subpages_.set(i, page);
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0001\u0000\u0001Ȉ\u0002Ȉ\u0003\u001b", new Object[]{"name_", "content_", "subpages_", Page.class});
            case NEW_MUTABLE_INSTANCE:
                return new Page();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<Page> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (Page.class) {
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

    @Override // com.google.api.PageOrBuilder
    public String getContent() {
        return this.content_;
    }

    @Override // com.google.api.PageOrBuilder
    public ByteString getContentBytes() {
        return ByteString.copyFromUtf8(this.content_);
    }

    @Override // com.google.api.PageOrBuilder
    public String getName() {
        return this.name_;
    }

    @Override // com.google.api.PageOrBuilder
    public ByteString getNameBytes() {
        return ByteString.copyFromUtf8(this.name_);
    }

    @Override // com.google.api.PageOrBuilder
    public Page getSubpages(int i) {
        return this.subpages_.get(i);
    }

    @Override // com.google.api.PageOrBuilder
    public int getSubpagesCount() {
        return this.subpages_.size();
    }

    @Override // com.google.api.PageOrBuilder
    public List<Page> getSubpagesList() {
        return this.subpages_;
    }

    public PageOrBuilder getSubpagesOrBuilder(int i) {
        return this.subpages_.get(i);
    }

    public List<? extends PageOrBuilder> getSubpagesOrBuilderList() {
        return this.subpages_;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Page, Builder> implements PageOrBuilder {
        public Builder() {
            super(Page.DEFAULT_INSTANCE);
        }

        public Builder addAllSubpages(Iterable<? extends Page> iterable) {
            copyOnWrite();
            Page.access$1000((Page) this.instance, iterable);
            return this;
        }

        public Builder addSubpages(Page page) {
            copyOnWrite();
            ((Page) this.instance).addSubpages(page);
            return this;
        }

        public Builder clearContent() {
            copyOnWrite();
            ((Page) this.instance).clearContent();
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((Page) this.instance).clearName();
            return this;
        }

        public Builder clearSubpages() {
            copyOnWrite();
            ((Page) this.instance).clearSubpages();
            return this;
        }

        @Override // com.google.api.PageOrBuilder
        public String getContent() {
            return ((Page) this.instance).getContent();
        }

        @Override // com.google.api.PageOrBuilder
        public ByteString getContentBytes() {
            return ((Page) this.instance).getContentBytes();
        }

        @Override // com.google.api.PageOrBuilder
        public String getName() {
            return ((Page) this.instance).getName();
        }

        @Override // com.google.api.PageOrBuilder
        public ByteString getNameBytes() {
            return ((Page) this.instance).getNameBytes();
        }

        @Override // com.google.api.PageOrBuilder
        public Page getSubpages(int i) {
            return ((Page) this.instance).getSubpages(i);
        }

        @Override // com.google.api.PageOrBuilder
        public int getSubpagesCount() {
            return ((Page) this.instance).getSubpagesCount();
        }

        @Override // com.google.api.PageOrBuilder
        public List<Page> getSubpagesList() {
            return Collections.unmodifiableList(((Page) this.instance).getSubpagesList());
        }

        public Builder removeSubpages(int i) {
            copyOnWrite();
            Page.access$1200((Page) this.instance, i);
            return this;
        }

        public Builder setContent(String str) {
            copyOnWrite();
            ((Page) this.instance).setContent(str);
            return this;
        }

        public Builder setContentBytes(ByteString byteString) {
            copyOnWrite();
            ((Page) this.instance).setContentBytes(byteString);
            return this;
        }

        public Builder setName(String str) {
            copyOnWrite();
            ((Page) this.instance).setName(str);
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            copyOnWrite();
            ((Page) this.instance).setNameBytes(byteString);
            return this;
        }

        public Builder setSubpages(int i, Page page) {
            copyOnWrite();
            ((Page) this.instance).setSubpages(i, page);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(Page.DEFAULT_INSTANCE);
        }

        public Builder addSubpages(int i, Page page) {
            copyOnWrite();
            ((Page) this.instance).addSubpages(i, page);
            return this;
        }

        public Builder setSubpages(int i, Builder builder) {
            copyOnWrite();
            ((Page) this.instance).setSubpages(i, builder.build());
            return this;
        }

        public Builder addSubpages(Builder builder) {
            copyOnWrite();
            ((Page) this.instance).addSubpages(builder.build());
            return this;
        }

        public Builder addSubpages(int i, Builder builder) {
            copyOnWrite();
            ((Page) this.instance).addSubpages(i, builder.build());
            return this;
        }
    }

    public static Builder newBuilder(Page page) {
        return DEFAULT_INSTANCE.createBuilder(page);
    }

    public static Page parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Page) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Page parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (Page) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static Page parseFrom(ByteString byteString) {
        return (Page) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addSubpages(int i, Page page) {
        page.getClass();
        ensureSubpagesIsMutable();
        this.subpages_.add(i, page);
    }

    public static Page parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (Page) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Page parseFrom(byte[] bArr) {
        return (Page) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Page parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (Page) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Page parseFrom(InputStream inputStream) {
        return (Page) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Page parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Page) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Page parseFrom(CodedInputStream codedInputStream) {
        return (Page) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Page parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Page) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
