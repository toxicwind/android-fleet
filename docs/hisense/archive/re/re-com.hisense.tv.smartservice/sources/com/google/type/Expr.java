package com.google.type;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Parser;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes2.dex */
public final class Expr extends GeneratedMessageLite<Expr, Builder> implements ExprOrBuilder {
    public static final Expr DEFAULT_INSTANCE;
    public static final int DESCRIPTION_FIELD_NUMBER = 3;
    public static final int EXPRESSION_FIELD_NUMBER = 1;
    public static final int LOCATION_FIELD_NUMBER = 4;
    public static volatile Parser<Expr> PARSER = null;
    public static final int TITLE_FIELD_NUMBER = 2;
    public String expression_ = "";
    public String title_ = "";
    public String description_ = "";
    public String location_ = "";

    /* JADX INFO: renamed from: com.google.type.Expr$1, reason: invalid class name */
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
        Expr expr = new Expr();
        DEFAULT_INSTANCE = expr;
        GeneratedMessageLite.defaultInstanceMap.put(Expr.class, expr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDescription() {
        this.description_ = DEFAULT_INSTANCE.getDescription();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearExpression() {
        this.expression_ = DEFAULT_INSTANCE.getExpression();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearLocation() {
        this.location_ = DEFAULT_INSTANCE.getLocation();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTitle() {
        this.title_ = DEFAULT_INSTANCE.getTitle();
    }

    public static Expr getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Expr parseDelimitedFrom(InputStream inputStream) {
        return (Expr) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Expr parseFrom(ByteBuffer byteBuffer) {
        return (Expr) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<Expr> parser() {
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
    public void setExpression(String str) {
        str.getClass();
        this.expression_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setExpressionBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.expression_ = byteString.toStringUtf8();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLocation(String str) {
        str.getClass();
        this.location_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLocationBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.location_ = byteString.toStringUtf8();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTitle(String str) {
        str.getClass();
        this.title_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTitleBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.title_ = byteString.toStringUtf8();
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003Ȉ\u0004Ȉ", new Object[]{"expression_", "title_", "description_", "location_"});
            case NEW_MUTABLE_INSTANCE:
                return new Expr();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<Expr> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (Expr.class) {
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

    @Override // com.google.type.ExprOrBuilder
    public String getDescription() {
        return this.description_;
    }

    @Override // com.google.type.ExprOrBuilder
    public ByteString getDescriptionBytes() {
        return ByteString.copyFromUtf8(this.description_);
    }

    @Override // com.google.type.ExprOrBuilder
    public String getExpression() {
        return this.expression_;
    }

    @Override // com.google.type.ExprOrBuilder
    public ByteString getExpressionBytes() {
        return ByteString.copyFromUtf8(this.expression_);
    }

    @Override // com.google.type.ExprOrBuilder
    public String getLocation() {
        return this.location_;
    }

    @Override // com.google.type.ExprOrBuilder
    public ByteString getLocationBytes() {
        return ByteString.copyFromUtf8(this.location_);
    }

    @Override // com.google.type.ExprOrBuilder
    public String getTitle() {
        return this.title_;
    }

    @Override // com.google.type.ExprOrBuilder
    public ByteString getTitleBytes() {
        return ByteString.copyFromUtf8(this.title_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Expr, Builder> implements ExprOrBuilder {
        public Builder() {
            super(Expr.DEFAULT_INSTANCE);
        }

        public Builder clearDescription() {
            copyOnWrite();
            ((Expr) this.instance).clearDescription();
            return this;
        }

        public Builder clearExpression() {
            copyOnWrite();
            ((Expr) this.instance).clearExpression();
            return this;
        }

        public Builder clearLocation() {
            copyOnWrite();
            ((Expr) this.instance).clearLocation();
            return this;
        }

        public Builder clearTitle() {
            copyOnWrite();
            ((Expr) this.instance).clearTitle();
            return this;
        }

        @Override // com.google.type.ExprOrBuilder
        public String getDescription() {
            return ((Expr) this.instance).getDescription();
        }

        @Override // com.google.type.ExprOrBuilder
        public ByteString getDescriptionBytes() {
            return ((Expr) this.instance).getDescriptionBytes();
        }

        @Override // com.google.type.ExprOrBuilder
        public String getExpression() {
            return ((Expr) this.instance).getExpression();
        }

        @Override // com.google.type.ExprOrBuilder
        public ByteString getExpressionBytes() {
            return ((Expr) this.instance).getExpressionBytes();
        }

        @Override // com.google.type.ExprOrBuilder
        public String getLocation() {
            return ((Expr) this.instance).getLocation();
        }

        @Override // com.google.type.ExprOrBuilder
        public ByteString getLocationBytes() {
            return ((Expr) this.instance).getLocationBytes();
        }

        @Override // com.google.type.ExprOrBuilder
        public String getTitle() {
            return ((Expr) this.instance).getTitle();
        }

        @Override // com.google.type.ExprOrBuilder
        public ByteString getTitleBytes() {
            return ((Expr) this.instance).getTitleBytes();
        }

        public Builder setDescription(String str) {
            copyOnWrite();
            ((Expr) this.instance).setDescription(str);
            return this;
        }

        public Builder setDescriptionBytes(ByteString byteString) {
            copyOnWrite();
            ((Expr) this.instance).setDescriptionBytes(byteString);
            return this;
        }

        public Builder setExpression(String str) {
            copyOnWrite();
            ((Expr) this.instance).setExpression(str);
            return this;
        }

        public Builder setExpressionBytes(ByteString byteString) {
            copyOnWrite();
            ((Expr) this.instance).setExpressionBytes(byteString);
            return this;
        }

        public Builder setLocation(String str) {
            copyOnWrite();
            ((Expr) this.instance).setLocation(str);
            return this;
        }

        public Builder setLocationBytes(ByteString byteString) {
            copyOnWrite();
            ((Expr) this.instance).setLocationBytes(byteString);
            return this;
        }

        public Builder setTitle(String str) {
            copyOnWrite();
            ((Expr) this.instance).setTitle(str);
            return this;
        }

        public Builder setTitleBytes(ByteString byteString) {
            copyOnWrite();
            ((Expr) this.instance).setTitleBytes(byteString);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(Expr.DEFAULT_INSTANCE);
        }
    }

    public static Builder newBuilder(Expr expr) {
        return DEFAULT_INSTANCE.createBuilder(expr);
    }

    public static Expr parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Expr) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Expr parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (Expr) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static Expr parseFrom(ByteString byteString) {
        return (Expr) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static Expr parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (Expr) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Expr parseFrom(byte[] bArr) {
        return (Expr) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Expr parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (Expr) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Expr parseFrom(InputStream inputStream) {
        return (Expr) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Expr parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Expr) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Expr parseFrom(CodedInputStream codedInputStream) {
        return (Expr) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Expr parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Expr) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
