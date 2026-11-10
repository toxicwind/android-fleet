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
public final class HttpRule extends GeneratedMessageLite<HttpRule, Builder> implements HttpRuleOrBuilder {
    public static final int ADDITIONAL_BINDINGS_FIELD_NUMBER = 11;
    public static final int BODY_FIELD_NUMBER = 7;
    public static final int CUSTOM_FIELD_NUMBER = 8;
    public static final HttpRule DEFAULT_INSTANCE;
    public static final int DELETE_FIELD_NUMBER = 5;
    public static final int GET_FIELD_NUMBER = 2;
    public static volatile Parser<HttpRule> PARSER = null;
    public static final int PATCH_FIELD_NUMBER = 6;
    public static final int POST_FIELD_NUMBER = 4;
    public static final int PUT_FIELD_NUMBER = 3;
    public static final int RESPONSE_BODY_FIELD_NUMBER = 12;
    public static final int SELECTOR_FIELD_NUMBER = 1;
    public Object pattern_;
    public int patternCase_ = 0;
    public String selector_ = "";
    public String body_ = "";
    public String responseBody_ = "";
    public Internal.ProtobufList<HttpRule> additionalBindings_ = GeneratedMessageLite.emptyProtobufList();

    /* JADX INFO: renamed from: com.google.api.HttpRule$1, reason: invalid class name */
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

    public enum PatternCase {
        GET(2),
        PUT(3),
        POST(4),
        DELETE(5),
        PATCH(6),
        CUSTOM(8),
        PATTERN_NOT_SET(0);

        public final int value;

        PatternCase(int i) {
            this.value = i;
        }

        public static PatternCase forNumber(int i) {
            if (i == 0) {
                return PATTERN_NOT_SET;
            }
            if (i == 8) {
                return CUSTOM;
            }
            if (i == 2) {
                return GET;
            }
            if (i == 3) {
                return PUT;
            }
            if (i == 4) {
                return POST;
            }
            if (i == 5) {
                return DELETE;
            }
            if (i != 6) {
                return null;
            }
            return PATCH;
        }

        public int getNumber() {
            return this.value;
        }

        @Deprecated
        public static PatternCase valueOf(int i) {
            return forNumber(i);
        }
    }

    static {
        HttpRule httpRule = new HttpRule();
        DEFAULT_INSTANCE = httpRule;
        GeneratedMessageLite.defaultInstanceMap.put(HttpRule.class, httpRule);
    }

    public static /* synthetic */ void access$3200(HttpRule httpRule, Iterable iterable) {
        httpRule.ensureAdditionalBindingsIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) httpRule.additionalBindings_);
    }

    public static /* synthetic */ void access$3400(HttpRule httpRule, int i) {
        httpRule.ensureAdditionalBindingsIsMutable();
        httpRule.additionalBindings_.remove(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAdditionalBindings(HttpRule httpRule) {
        httpRule.getClass();
        ensureAdditionalBindingsIsMutable();
        this.additionalBindings_.add(httpRule);
    }

    private void addAllAdditionalBindings(Iterable<? extends HttpRule> iterable) {
        ensureAdditionalBindingsIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.additionalBindings_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearAdditionalBindings() {
        this.additionalBindings_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearBody() {
        this.body_ = DEFAULT_INSTANCE.getBody();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearCustom() {
        if (this.patternCase_ == 8) {
            this.patternCase_ = 0;
            this.pattern_ = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDelete() {
        if (this.patternCase_ == 5) {
            this.patternCase_ = 0;
            this.pattern_ = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearGet() {
        if (this.patternCase_ == 2) {
            this.patternCase_ = 0;
            this.pattern_ = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearPatch() {
        if (this.patternCase_ == 6) {
            this.patternCase_ = 0;
            this.pattern_ = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearPattern() {
        this.patternCase_ = 0;
        this.pattern_ = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearPost() {
        if (this.patternCase_ == 4) {
            this.patternCase_ = 0;
            this.pattern_ = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearPut() {
        if (this.patternCase_ == 3) {
            this.patternCase_ = 0;
            this.pattern_ = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearResponseBody() {
        this.responseBody_ = DEFAULT_INSTANCE.getResponseBody();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearSelector() {
        this.selector_ = DEFAULT_INSTANCE.getSelector();
    }

    private void ensureAdditionalBindingsIsMutable() {
        Internal.ProtobufList<HttpRule> protobufList = this.additionalBindings_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.additionalBindings_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    public static HttpRule getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeCustom(CustomHttpPattern customHttpPattern) {
        Object obj;
        customHttpPattern.getClass();
        if (this.patternCase_ != 8 || (obj = this.pattern_) == CustomHttpPattern.DEFAULT_INSTANCE) {
            this.pattern_ = customHttpPattern;
        } else {
            this.pattern_ = CustomHttpPattern.newBuilder((CustomHttpPattern) obj).mergeFrom(customHttpPattern).buildPartial();
        }
        this.patternCase_ = 8;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static HttpRule parseDelimitedFrom(InputStream inputStream) {
        return (HttpRule) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static HttpRule parseFrom(ByteBuffer byteBuffer) {
        return (HttpRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<HttpRule> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    private void removeAdditionalBindings(int i) {
        ensureAdditionalBindingsIsMutable();
        this.additionalBindings_.remove(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAdditionalBindings(int i, HttpRule httpRule) {
        httpRule.getClass();
        ensureAdditionalBindingsIsMutable();
        this.additionalBindings_.set(i, httpRule);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBody(String str) {
        str.getClass();
        this.body_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBodyBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.body_ = byteString.toStringUtf8();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCustom(CustomHttpPattern customHttpPattern) {
        customHttpPattern.getClass();
        this.pattern_ = customHttpPattern;
        this.patternCase_ = 8;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDelete(String str) {
        str.getClass();
        this.patternCase_ = 5;
        this.pattern_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDeleteBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.pattern_ = byteString.toStringUtf8();
        this.patternCase_ = 5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setGet(String str) {
        str.getClass();
        this.patternCase_ = 2;
        this.pattern_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setGetBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.pattern_ = byteString.toStringUtf8();
        this.patternCase_ = 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPatch(String str) {
        str.getClass();
        this.patternCase_ = 6;
        this.pattern_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPatchBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.pattern_ = byteString.toStringUtf8();
        this.patternCase_ = 6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPost(String str) {
        str.getClass();
        this.patternCase_ = 4;
        this.pattern_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPostBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.pattern_ = byteString.toStringUtf8();
        this.patternCase_ = 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPut(String str) {
        str.getClass();
        this.patternCase_ = 3;
        this.pattern_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPutBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.pattern_ = byteString.toStringUtf8();
        this.patternCase_ = 3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setResponseBody(String str) {
        str.getClass();
        this.responseBody_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setResponseBodyBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.responseBody_ = byteString.toStringUtf8();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSelector(String str) {
        str.getClass();
        this.selector_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSelectorBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.selector_ = byteString.toStringUtf8();
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\n\u0001\u0000\u0001\f\n\u0000\u0001\u0000\u0001Ȉ\u0002Ȼ\u0000\u0003Ȼ\u0000\u0004Ȼ\u0000\u0005Ȼ\u0000\u0006Ȼ\u0000\u0007Ȉ\b<\u0000\u000b\u001b\fȈ", new Object[]{"pattern_", "patternCase_", "selector_", "body_", CustomHttpPattern.class, "additionalBindings_", HttpRule.class, "responseBody_"});
            case NEW_MUTABLE_INSTANCE:
                return new HttpRule();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<HttpRule> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (HttpRule.class) {
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

    @Override // com.google.api.HttpRuleOrBuilder
    public HttpRule getAdditionalBindings(int i) {
        return this.additionalBindings_.get(i);
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public int getAdditionalBindingsCount() {
        return this.additionalBindings_.size();
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public List<HttpRule> getAdditionalBindingsList() {
        return this.additionalBindings_;
    }

    public HttpRuleOrBuilder getAdditionalBindingsOrBuilder(int i) {
        return this.additionalBindings_.get(i);
    }

    public List<? extends HttpRuleOrBuilder> getAdditionalBindingsOrBuilderList() {
        return this.additionalBindings_;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public String getBody() {
        return this.body_;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public ByteString getBodyBytes() {
        return ByteString.copyFromUtf8(this.body_);
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public CustomHttpPattern getCustom() {
        return this.patternCase_ == 8 ? (CustomHttpPattern) this.pattern_ : CustomHttpPattern.DEFAULT_INSTANCE;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public String getDelete() {
        return this.patternCase_ == 5 ? (String) this.pattern_ : "";
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public ByteString getDeleteBytes() {
        return ByteString.copyFromUtf8(this.patternCase_ == 5 ? (String) this.pattern_ : "");
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public String getGet() {
        return this.patternCase_ == 2 ? (String) this.pattern_ : "";
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public ByteString getGetBytes() {
        return ByteString.copyFromUtf8(this.patternCase_ == 2 ? (String) this.pattern_ : "");
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public String getPatch() {
        return this.patternCase_ == 6 ? (String) this.pattern_ : "";
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public ByteString getPatchBytes() {
        return ByteString.copyFromUtf8(this.patternCase_ == 6 ? (String) this.pattern_ : "");
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public PatternCase getPatternCase() {
        return PatternCase.forNumber(this.patternCase_);
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public String getPost() {
        return this.patternCase_ == 4 ? (String) this.pattern_ : "";
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public ByteString getPostBytes() {
        return ByteString.copyFromUtf8(this.patternCase_ == 4 ? (String) this.pattern_ : "");
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public String getPut() {
        return this.patternCase_ == 3 ? (String) this.pattern_ : "";
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public ByteString getPutBytes() {
        return ByteString.copyFromUtf8(this.patternCase_ == 3 ? (String) this.pattern_ : "");
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public String getResponseBody() {
        return this.responseBody_;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public ByteString getResponseBodyBytes() {
        return ByteString.copyFromUtf8(this.responseBody_);
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public String getSelector() {
        return this.selector_;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public ByteString getSelectorBytes() {
        return ByteString.copyFromUtf8(this.selector_);
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public boolean hasCustom() {
        return this.patternCase_ == 8;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<HttpRule, Builder> implements HttpRuleOrBuilder {
        public Builder() {
            super(HttpRule.DEFAULT_INSTANCE);
        }

        public Builder addAdditionalBindings(HttpRule httpRule) {
            copyOnWrite();
            ((HttpRule) this.instance).addAdditionalBindings(httpRule);
            return this;
        }

        public Builder addAllAdditionalBindings(Iterable<? extends HttpRule> iterable) {
            copyOnWrite();
            HttpRule.access$3200((HttpRule) this.instance, iterable);
            return this;
        }

        public Builder clearAdditionalBindings() {
            copyOnWrite();
            ((HttpRule) this.instance).clearAdditionalBindings();
            return this;
        }

        public Builder clearBody() {
            copyOnWrite();
            ((HttpRule) this.instance).clearBody();
            return this;
        }

        public Builder clearCustom() {
            copyOnWrite();
            ((HttpRule) this.instance).clearCustom();
            return this;
        }

        public Builder clearDelete() {
            copyOnWrite();
            ((HttpRule) this.instance).clearDelete();
            return this;
        }

        public Builder clearGet() {
            copyOnWrite();
            ((HttpRule) this.instance).clearGet();
            return this;
        }

        public Builder clearPatch() {
            copyOnWrite();
            ((HttpRule) this.instance).clearPatch();
            return this;
        }

        public Builder clearPattern() {
            copyOnWrite();
            ((HttpRule) this.instance).clearPattern();
            return this;
        }

        public Builder clearPost() {
            copyOnWrite();
            ((HttpRule) this.instance).clearPost();
            return this;
        }

        public Builder clearPut() {
            copyOnWrite();
            ((HttpRule) this.instance).clearPut();
            return this;
        }

        public Builder clearResponseBody() {
            copyOnWrite();
            ((HttpRule) this.instance).clearResponseBody();
            return this;
        }

        public Builder clearSelector() {
            copyOnWrite();
            ((HttpRule) this.instance).clearSelector();
            return this;
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public HttpRule getAdditionalBindings(int i) {
            return ((HttpRule) this.instance).getAdditionalBindings(i);
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public int getAdditionalBindingsCount() {
            return ((HttpRule) this.instance).getAdditionalBindingsCount();
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public List<HttpRule> getAdditionalBindingsList() {
            return Collections.unmodifiableList(((HttpRule) this.instance).getAdditionalBindingsList());
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public String getBody() {
            return ((HttpRule) this.instance).getBody();
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public ByteString getBodyBytes() {
            return ((HttpRule) this.instance).getBodyBytes();
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public CustomHttpPattern getCustom() {
            return ((HttpRule) this.instance).getCustom();
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public String getDelete() {
            return ((HttpRule) this.instance).getDelete();
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public ByteString getDeleteBytes() {
            return ((HttpRule) this.instance).getDeleteBytes();
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public String getGet() {
            return ((HttpRule) this.instance).getGet();
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public ByteString getGetBytes() {
            return ((HttpRule) this.instance).getGetBytes();
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public String getPatch() {
            return ((HttpRule) this.instance).getPatch();
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public ByteString getPatchBytes() {
            return ((HttpRule) this.instance).getPatchBytes();
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public PatternCase getPatternCase() {
            return ((HttpRule) this.instance).getPatternCase();
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public String getPost() {
            return ((HttpRule) this.instance).getPost();
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public ByteString getPostBytes() {
            return ((HttpRule) this.instance).getPostBytes();
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public String getPut() {
            return ((HttpRule) this.instance).getPut();
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public ByteString getPutBytes() {
            return ((HttpRule) this.instance).getPutBytes();
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public String getResponseBody() {
            return ((HttpRule) this.instance).getResponseBody();
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public ByteString getResponseBodyBytes() {
            return ((HttpRule) this.instance).getResponseBodyBytes();
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public String getSelector() {
            return ((HttpRule) this.instance).getSelector();
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public ByteString getSelectorBytes() {
            return ((HttpRule) this.instance).getSelectorBytes();
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public boolean hasCustom() {
            return ((HttpRule) this.instance).hasCustom();
        }

        public Builder mergeCustom(CustomHttpPattern customHttpPattern) {
            copyOnWrite();
            ((HttpRule) this.instance).mergeCustom(customHttpPattern);
            return this;
        }

        public Builder removeAdditionalBindings(int i) {
            copyOnWrite();
            HttpRule.access$3400((HttpRule) this.instance, i);
            return this;
        }

        public Builder setAdditionalBindings(int i, HttpRule httpRule) {
            copyOnWrite();
            ((HttpRule) this.instance).setAdditionalBindings(i, httpRule);
            return this;
        }

        public Builder setBody(String str) {
            copyOnWrite();
            ((HttpRule) this.instance).setBody(str);
            return this;
        }

        public Builder setBodyBytes(ByteString byteString) {
            copyOnWrite();
            ((HttpRule) this.instance).setBodyBytes(byteString);
            return this;
        }

        public Builder setCustom(CustomHttpPattern customHttpPattern) {
            copyOnWrite();
            ((HttpRule) this.instance).setCustom(customHttpPattern);
            return this;
        }

        public Builder setDelete(String str) {
            copyOnWrite();
            ((HttpRule) this.instance).setDelete(str);
            return this;
        }

        public Builder setDeleteBytes(ByteString byteString) {
            copyOnWrite();
            ((HttpRule) this.instance).setDeleteBytes(byteString);
            return this;
        }

        public Builder setGet(String str) {
            copyOnWrite();
            ((HttpRule) this.instance).setGet(str);
            return this;
        }

        public Builder setGetBytes(ByteString byteString) {
            copyOnWrite();
            ((HttpRule) this.instance).setGetBytes(byteString);
            return this;
        }

        public Builder setPatch(String str) {
            copyOnWrite();
            ((HttpRule) this.instance).setPatch(str);
            return this;
        }

        public Builder setPatchBytes(ByteString byteString) {
            copyOnWrite();
            ((HttpRule) this.instance).setPatchBytes(byteString);
            return this;
        }

        public Builder setPost(String str) {
            copyOnWrite();
            ((HttpRule) this.instance).setPost(str);
            return this;
        }

        public Builder setPostBytes(ByteString byteString) {
            copyOnWrite();
            ((HttpRule) this.instance).setPostBytes(byteString);
            return this;
        }

        public Builder setPut(String str) {
            copyOnWrite();
            ((HttpRule) this.instance).setPut(str);
            return this;
        }

        public Builder setPutBytes(ByteString byteString) {
            copyOnWrite();
            ((HttpRule) this.instance).setPutBytes(byteString);
            return this;
        }

        public Builder setResponseBody(String str) {
            copyOnWrite();
            ((HttpRule) this.instance).setResponseBody(str);
            return this;
        }

        public Builder setResponseBodyBytes(ByteString byteString) {
            copyOnWrite();
            ((HttpRule) this.instance).setResponseBodyBytes(byteString);
            return this;
        }

        public Builder setSelector(String str) {
            copyOnWrite();
            ((HttpRule) this.instance).setSelector(str);
            return this;
        }

        public Builder setSelectorBytes(ByteString byteString) {
            copyOnWrite();
            ((HttpRule) this.instance).setSelectorBytes(byteString);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(HttpRule.DEFAULT_INSTANCE);
        }

        public Builder addAdditionalBindings(int i, HttpRule httpRule) {
            copyOnWrite();
            ((HttpRule) this.instance).addAdditionalBindings(i, httpRule);
            return this;
        }

        public Builder setAdditionalBindings(int i, Builder builder) {
            copyOnWrite();
            ((HttpRule) this.instance).setAdditionalBindings(i, builder.build());
            return this;
        }

        public Builder setCustom(CustomHttpPattern.Builder builder) {
            copyOnWrite();
            ((HttpRule) this.instance).setCustom(builder.build());
            return this;
        }

        public Builder addAdditionalBindings(Builder builder) {
            copyOnWrite();
            ((HttpRule) this.instance).addAdditionalBindings(builder.build());
            return this;
        }

        public Builder addAdditionalBindings(int i, Builder builder) {
            copyOnWrite();
            ((HttpRule) this.instance).addAdditionalBindings(i, builder.build());
            return this;
        }
    }

    public static Builder newBuilder(HttpRule httpRule) {
        return DEFAULT_INSTANCE.createBuilder(httpRule);
    }

    public static HttpRule parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (HttpRule) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static HttpRule parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (HttpRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static HttpRule parseFrom(ByteString byteString) {
        return (HttpRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAdditionalBindings(int i, HttpRule httpRule) {
        httpRule.getClass();
        ensureAdditionalBindingsIsMutable();
        this.additionalBindings_.add(i, httpRule);
    }

    public static HttpRule parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (HttpRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static HttpRule parseFrom(byte[] bArr) {
        return (HttpRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static HttpRule parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (HttpRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static HttpRule parseFrom(InputStream inputStream) {
        return (HttpRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static HttpRule parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (HttpRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static HttpRule parseFrom(CodedInputStream codedInputStream) {
        return (HttpRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static HttpRule parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (HttpRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
