package com.google.geo.type;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Parser;
import com.google.type.LatLng;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class Viewport extends GeneratedMessageLite<Viewport, Builder> implements ViewportOrBuilder {
    public static final Viewport DEFAULT_INSTANCE;
    public static final int HIGH_FIELD_NUMBER = 2;
    public static final int LOW_FIELD_NUMBER = 1;
    public static volatile Parser<Viewport> PARSER;
    public LatLng high_;
    public LatLng low_;

    /* JADX INFO: renamed from: com.google.geo.type.Viewport$1, reason: invalid class name */
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
        Viewport viewport = new Viewport();
        DEFAULT_INSTANCE = viewport;
        GeneratedMessageLite.defaultInstanceMap.put(Viewport.class, viewport);
    }

    private void clearHigh() {
        this.high_ = null;
    }

    private void clearLow() {
        this.low_ = null;
    }

    public static Viewport getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeHigh(LatLng latLng) {
        latLng.getClass();
        LatLng latLng2 = this.high_;
        if (latLng2 == null || latLng2 == LatLng.DEFAULT_INSTANCE) {
            this.high_ = latLng;
        } else {
            this.high_ = LatLng.newBuilder(latLng2).mergeFrom(latLng).buildPartial();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeLow(LatLng latLng) {
        latLng.getClass();
        LatLng latLng2 = this.low_;
        if (latLng2 == null || latLng2 == LatLng.DEFAULT_INSTANCE) {
            this.low_ = latLng;
        } else {
            this.low_ = LatLng.newBuilder(latLng2).mergeFrom(latLng).buildPartial();
        }
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Viewport parseDelimitedFrom(InputStream inputStream) {
        return (Viewport) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Viewport parseFrom(ByteBuffer byteBuffer) {
        return (Viewport) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<Viewport> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHigh(LatLng latLng) {
        latLng.getClass();
        this.high_ = latLng;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLow(LatLng latLng) {
        latLng.getClass();
        this.low_ = latLng;
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\t", new Object[]{"low_", "high_"});
            case NEW_MUTABLE_INSTANCE:
                return new Viewport();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<Viewport> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (Viewport.class) {
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

    @Override // com.google.geo.type.ViewportOrBuilder
    public LatLng getHigh() {
        LatLng latLng = this.high_;
        return latLng == null ? LatLng.DEFAULT_INSTANCE : latLng;
    }

    @Override // com.google.geo.type.ViewportOrBuilder
    public LatLng getLow() {
        LatLng latLng = this.low_;
        return latLng == null ? LatLng.DEFAULT_INSTANCE : latLng;
    }

    @Override // com.google.geo.type.ViewportOrBuilder
    public boolean hasHigh() {
        return this.high_ != null;
    }

    @Override // com.google.geo.type.ViewportOrBuilder
    public boolean hasLow() {
        return this.low_ != null;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Viewport, Builder> implements ViewportOrBuilder {
        public Builder() {
            super(Viewport.DEFAULT_INSTANCE);
        }

        public Builder clearHigh() {
            copyOnWrite();
            ((Viewport) this.instance).high_ = null;
            return this;
        }

        public Builder clearLow() {
            copyOnWrite();
            ((Viewport) this.instance).low_ = null;
            return this;
        }

        @Override // com.google.geo.type.ViewportOrBuilder
        public LatLng getHigh() {
            return ((Viewport) this.instance).getHigh();
        }

        @Override // com.google.geo.type.ViewportOrBuilder
        public LatLng getLow() {
            return ((Viewport) this.instance).getLow();
        }

        @Override // com.google.geo.type.ViewportOrBuilder
        public boolean hasHigh() {
            return ((Viewport) this.instance).hasHigh();
        }

        @Override // com.google.geo.type.ViewportOrBuilder
        public boolean hasLow() {
            return ((Viewport) this.instance).hasLow();
        }

        public Builder mergeHigh(LatLng latLng) {
            copyOnWrite();
            ((Viewport) this.instance).mergeHigh(latLng);
            return this;
        }

        public Builder mergeLow(LatLng latLng) {
            copyOnWrite();
            ((Viewport) this.instance).mergeLow(latLng);
            return this;
        }

        public Builder setHigh(LatLng latLng) {
            copyOnWrite();
            ((Viewport) this.instance).setHigh(latLng);
            return this;
        }

        public Builder setLow(LatLng latLng) {
            copyOnWrite();
            ((Viewport) this.instance).setLow(latLng);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(Viewport.DEFAULT_INSTANCE);
        }

        public Builder setHigh(LatLng.Builder builder) {
            copyOnWrite();
            ((Viewport) this.instance).setHigh(builder.build());
            return this;
        }

        public Builder setLow(LatLng.Builder builder) {
            copyOnWrite();
            ((Viewport) this.instance).setLow(builder.build());
            return this;
        }
    }

    public static Builder newBuilder(Viewport viewport) {
        return DEFAULT_INSTANCE.createBuilder(viewport);
    }

    public static Viewport parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Viewport) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Viewport parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (Viewport) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static Viewport parseFrom(ByteString byteString) {
        return (Viewport) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static Viewport parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (Viewport) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Viewport parseFrom(byte[] bArr) {
        return (Viewport) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Viewport parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (Viewport) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Viewport parseFrom(InputStream inputStream) {
        return (Viewport) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Viewport parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Viewport) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Viewport parseFrom(CodedInputStream codedInputStream) {
        return (Viewport) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Viewport parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Viewport) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
