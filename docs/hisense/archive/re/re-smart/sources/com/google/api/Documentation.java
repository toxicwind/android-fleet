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
public final class Documentation extends GeneratedMessageLite<Documentation, Builder> implements DocumentationOrBuilder {
    public static final Documentation DEFAULT_INSTANCE;
    public static final int DOCUMENTATION_ROOT_URL_FIELD_NUMBER = 4;
    public static final int OVERVIEW_FIELD_NUMBER = 2;
    public static final int PAGES_FIELD_NUMBER = 5;
    public static volatile Parser<Documentation> PARSER = null;
    public static final int RULES_FIELD_NUMBER = 3;
    public static final int SUMMARY_FIELD_NUMBER = 1;
    public String summary_ = "";
    public Internal.ProtobufList<Page> pages_ = GeneratedMessageLite.emptyProtobufList();
    public Internal.ProtobufList<DocumentationRule> rules_ = GeneratedMessageLite.emptyProtobufList();
    public String documentationRootUrl_ = "";
    public String overview_ = "";

    /* JADX INFO: renamed from: com.google.api.Documentation$1, reason: invalid class name */
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
        Documentation documentation = new Documentation();
        DEFAULT_INSTANCE = documentation;
        GeneratedMessageLite.defaultInstanceMap.put(Documentation.class, documentation);
    }

    public static /* synthetic */ void access$1300(Documentation documentation, Iterable iterable) {
        documentation.ensureRulesIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) documentation.rules_);
    }

    public static /* synthetic */ void access$1500(Documentation documentation, int i) {
        documentation.ensureRulesIsMutable();
        documentation.rules_.remove(i);
    }

    public static /* synthetic */ void access$700(Documentation documentation, Iterable iterable) {
        documentation.ensurePagesIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) documentation.pages_);
    }

    public static /* synthetic */ void access$900(Documentation documentation, int i) {
        documentation.ensurePagesIsMutable();
        documentation.pages_.remove(i);
    }

    private void addAllPages(Iterable<? extends Page> iterable) {
        ensurePagesIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.pages_);
    }

    private void addAllRules(Iterable<? extends DocumentationRule> iterable) {
        ensureRulesIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.rules_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addPages(Page page) {
        page.getClass();
        ensurePagesIsMutable();
        this.pages_.add(page);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addRules(DocumentationRule documentationRule) {
        documentationRule.getClass();
        ensureRulesIsMutable();
        this.rules_.add(documentationRule);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDocumentationRootUrl() {
        this.documentationRootUrl_ = DEFAULT_INSTANCE.getDocumentationRootUrl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearOverview() {
        this.overview_ = DEFAULT_INSTANCE.getOverview();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearPages() {
        this.pages_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearRules() {
        this.rules_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearSummary() {
        this.summary_ = DEFAULT_INSTANCE.getSummary();
    }

    private void ensurePagesIsMutable() {
        Internal.ProtobufList<Page> protobufList = this.pages_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.pages_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    private void ensureRulesIsMutable() {
        Internal.ProtobufList<DocumentationRule> protobufList = this.rules_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.rules_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    public static Documentation getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Documentation parseDelimitedFrom(InputStream inputStream) {
        return (Documentation) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Documentation parseFrom(ByteBuffer byteBuffer) {
        return (Documentation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<Documentation> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    private void removePages(int i) {
        ensurePagesIsMutable();
        this.pages_.remove(i);
    }

    private void removeRules(int i) {
        ensureRulesIsMutable();
        this.rules_.remove(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDocumentationRootUrl(String str) {
        str.getClass();
        this.documentationRootUrl_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDocumentationRootUrlBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.documentationRootUrl_ = byteString.toStringUtf8();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOverview(String str) {
        str.getClass();
        this.overview_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOverviewBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.overview_ = byteString.toStringUtf8();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPages(int i, Page page) {
        page.getClass();
        ensurePagesIsMutable();
        this.pages_.set(i, page);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRules(int i, DocumentationRule documentationRule) {
        documentationRule.getClass();
        ensureRulesIsMutable();
        this.rules_.set(i, documentationRule);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSummary(String str) {
        str.getClass();
        this.summary_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSummaryBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.summary_ = byteString.toStringUtf8();
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0005\u0000\u0000\u0001\u0005\u0005\u0000\u0002\u0000\u0001Ȉ\u0002Ȉ\u0003\u001b\u0004Ȉ\u0005\u001b", new Object[]{"summary_", "overview_", "rules_", DocumentationRule.class, "documentationRootUrl_", "pages_", Page.class});
            case NEW_MUTABLE_INSTANCE:
                return new Documentation();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<Documentation> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (Documentation.class) {
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

    @Override // com.google.api.DocumentationOrBuilder
    public String getDocumentationRootUrl() {
        return this.documentationRootUrl_;
    }

    @Override // com.google.api.DocumentationOrBuilder
    public ByteString getDocumentationRootUrlBytes() {
        return ByteString.copyFromUtf8(this.documentationRootUrl_);
    }

    @Override // com.google.api.DocumentationOrBuilder
    public String getOverview() {
        return this.overview_;
    }

    @Override // com.google.api.DocumentationOrBuilder
    public ByteString getOverviewBytes() {
        return ByteString.copyFromUtf8(this.overview_);
    }

    @Override // com.google.api.DocumentationOrBuilder
    public Page getPages(int i) {
        return this.pages_.get(i);
    }

    @Override // com.google.api.DocumentationOrBuilder
    public int getPagesCount() {
        return this.pages_.size();
    }

    @Override // com.google.api.DocumentationOrBuilder
    public List<Page> getPagesList() {
        return this.pages_;
    }

    public PageOrBuilder getPagesOrBuilder(int i) {
        return this.pages_.get(i);
    }

    public List<? extends PageOrBuilder> getPagesOrBuilderList() {
        return this.pages_;
    }

    @Override // com.google.api.DocumentationOrBuilder
    public DocumentationRule getRules(int i) {
        return this.rules_.get(i);
    }

    @Override // com.google.api.DocumentationOrBuilder
    public int getRulesCount() {
        return this.rules_.size();
    }

    @Override // com.google.api.DocumentationOrBuilder
    public List<DocumentationRule> getRulesList() {
        return this.rules_;
    }

    public DocumentationRuleOrBuilder getRulesOrBuilder(int i) {
        return this.rules_.get(i);
    }

    public List<? extends DocumentationRuleOrBuilder> getRulesOrBuilderList() {
        return this.rules_;
    }

    @Override // com.google.api.DocumentationOrBuilder
    public String getSummary() {
        return this.summary_;
    }

    @Override // com.google.api.DocumentationOrBuilder
    public ByteString getSummaryBytes() {
        return ByteString.copyFromUtf8(this.summary_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Documentation, Builder> implements DocumentationOrBuilder {
        public Builder() {
            super(Documentation.DEFAULT_INSTANCE);
        }

        public Builder addAllPages(Iterable<? extends Page> iterable) {
            copyOnWrite();
            Documentation.access$700((Documentation) this.instance, iterable);
            return this;
        }

        public Builder addAllRules(Iterable<? extends DocumentationRule> iterable) {
            copyOnWrite();
            Documentation.access$1300((Documentation) this.instance, iterable);
            return this;
        }

        public Builder addPages(Page page) {
            copyOnWrite();
            ((Documentation) this.instance).addPages(page);
            return this;
        }

        public Builder addRules(DocumentationRule documentationRule) {
            copyOnWrite();
            ((Documentation) this.instance).addRules(documentationRule);
            return this;
        }

        public Builder clearDocumentationRootUrl() {
            copyOnWrite();
            ((Documentation) this.instance).clearDocumentationRootUrl();
            return this;
        }

        public Builder clearOverview() {
            copyOnWrite();
            ((Documentation) this.instance).clearOverview();
            return this;
        }

        public Builder clearPages() {
            copyOnWrite();
            ((Documentation) this.instance).clearPages();
            return this;
        }

        public Builder clearRules() {
            copyOnWrite();
            ((Documentation) this.instance).clearRules();
            return this;
        }

        public Builder clearSummary() {
            copyOnWrite();
            ((Documentation) this.instance).clearSummary();
            return this;
        }

        @Override // com.google.api.DocumentationOrBuilder
        public String getDocumentationRootUrl() {
            return ((Documentation) this.instance).getDocumentationRootUrl();
        }

        @Override // com.google.api.DocumentationOrBuilder
        public ByteString getDocumentationRootUrlBytes() {
            return ((Documentation) this.instance).getDocumentationRootUrlBytes();
        }

        @Override // com.google.api.DocumentationOrBuilder
        public String getOverview() {
            return ((Documentation) this.instance).getOverview();
        }

        @Override // com.google.api.DocumentationOrBuilder
        public ByteString getOverviewBytes() {
            return ((Documentation) this.instance).getOverviewBytes();
        }

        @Override // com.google.api.DocumentationOrBuilder
        public Page getPages(int i) {
            return ((Documentation) this.instance).getPages(i);
        }

        @Override // com.google.api.DocumentationOrBuilder
        public int getPagesCount() {
            return ((Documentation) this.instance).getPagesCount();
        }

        @Override // com.google.api.DocumentationOrBuilder
        public List<Page> getPagesList() {
            return Collections.unmodifiableList(((Documentation) this.instance).getPagesList());
        }

        @Override // com.google.api.DocumentationOrBuilder
        public DocumentationRule getRules(int i) {
            return ((Documentation) this.instance).getRules(i);
        }

        @Override // com.google.api.DocumentationOrBuilder
        public int getRulesCount() {
            return ((Documentation) this.instance).getRulesCount();
        }

        @Override // com.google.api.DocumentationOrBuilder
        public List<DocumentationRule> getRulesList() {
            return Collections.unmodifiableList(((Documentation) this.instance).getRulesList());
        }

        @Override // com.google.api.DocumentationOrBuilder
        public String getSummary() {
            return ((Documentation) this.instance).getSummary();
        }

        @Override // com.google.api.DocumentationOrBuilder
        public ByteString getSummaryBytes() {
            return ((Documentation) this.instance).getSummaryBytes();
        }

        public Builder removePages(int i) {
            copyOnWrite();
            Documentation.access$900((Documentation) this.instance, i);
            return this;
        }

        public Builder removeRules(int i) {
            copyOnWrite();
            Documentation.access$1500((Documentation) this.instance, i);
            return this;
        }

        public Builder setDocumentationRootUrl(String str) {
            copyOnWrite();
            ((Documentation) this.instance).setDocumentationRootUrl(str);
            return this;
        }

        public Builder setDocumentationRootUrlBytes(ByteString byteString) {
            copyOnWrite();
            ((Documentation) this.instance).setDocumentationRootUrlBytes(byteString);
            return this;
        }

        public Builder setOverview(String str) {
            copyOnWrite();
            ((Documentation) this.instance).setOverview(str);
            return this;
        }

        public Builder setOverviewBytes(ByteString byteString) {
            copyOnWrite();
            ((Documentation) this.instance).setOverviewBytes(byteString);
            return this;
        }

        public Builder setPages(int i, Page page) {
            copyOnWrite();
            ((Documentation) this.instance).setPages(i, page);
            return this;
        }

        public Builder setRules(int i, DocumentationRule documentationRule) {
            copyOnWrite();
            ((Documentation) this.instance).setRules(i, documentationRule);
            return this;
        }

        public Builder setSummary(String str) {
            copyOnWrite();
            ((Documentation) this.instance).setSummary(str);
            return this;
        }

        public Builder setSummaryBytes(ByteString byteString) {
            copyOnWrite();
            ((Documentation) this.instance).setSummaryBytes(byteString);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(Documentation.DEFAULT_INSTANCE);
        }

        public Builder addPages(int i, Page page) {
            copyOnWrite();
            ((Documentation) this.instance).addPages(i, page);
            return this;
        }

        public Builder addRules(int i, DocumentationRule documentationRule) {
            copyOnWrite();
            ((Documentation) this.instance).addRules(i, documentationRule);
            return this;
        }

        public Builder setPages(int i, Page.Builder builder) {
            copyOnWrite();
            ((Documentation) this.instance).setPages(i, builder.build());
            return this;
        }

        public Builder setRules(int i, DocumentationRule.Builder builder) {
            copyOnWrite();
            ((Documentation) this.instance).setRules(i, builder.build());
            return this;
        }

        public Builder addPages(Page.Builder builder) {
            copyOnWrite();
            ((Documentation) this.instance).addPages(builder.build());
            return this;
        }

        public Builder addRules(DocumentationRule.Builder builder) {
            copyOnWrite();
            ((Documentation) this.instance).addRules(builder.build());
            return this;
        }

        public Builder addPages(int i, Page.Builder builder) {
            copyOnWrite();
            ((Documentation) this.instance).addPages(i, builder.build());
            return this;
        }

        public Builder addRules(int i, DocumentationRule.Builder builder) {
            copyOnWrite();
            ((Documentation) this.instance).addRules(i, builder.build());
            return this;
        }
    }

    public static Builder newBuilder(Documentation documentation) {
        return DEFAULT_INSTANCE.createBuilder(documentation);
    }

    public static Documentation parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Documentation) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Documentation parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (Documentation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static Documentation parseFrom(ByteString byteString) {
        return (Documentation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addPages(int i, Page page) {
        page.getClass();
        ensurePagesIsMutable();
        this.pages_.add(i, page);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addRules(int i, DocumentationRule documentationRule) {
        documentationRule.getClass();
        ensureRulesIsMutable();
        this.rules_.add(i, documentationRule);
    }

    public static Documentation parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (Documentation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Documentation parseFrom(byte[] bArr) {
        return (Documentation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Documentation parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (Documentation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Documentation parseFrom(InputStream inputStream) {
        return (Documentation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Documentation parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Documentation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Documentation parseFrom(CodedInputStream codedInputStream) {
        return (Documentation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Documentation parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Documentation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
