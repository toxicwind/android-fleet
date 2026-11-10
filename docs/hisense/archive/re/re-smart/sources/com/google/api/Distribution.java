package com.google.api;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.Any;
import com.google.protobuf.AnyOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.Timestamp;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class Distribution extends GeneratedMessageLite<Distribution, Builder> implements DistributionOrBuilder {
    public static final int BUCKET_COUNTS_FIELD_NUMBER = 7;
    public static final int BUCKET_OPTIONS_FIELD_NUMBER = 6;
    public static final int COUNT_FIELD_NUMBER = 1;
    public static final Distribution DEFAULT_INSTANCE;
    public static final int EXEMPLARS_FIELD_NUMBER = 10;
    public static final int MEAN_FIELD_NUMBER = 2;
    public static volatile Parser<Distribution> PARSER = null;
    public static final int RANGE_FIELD_NUMBER = 4;
    public static final int SUM_OF_SQUARED_DEVIATION_FIELD_NUMBER = 3;
    public BucketOptions bucketOptions_;
    public long count_;
    public double mean_;
    public Range range_;
    public double sumOfSquaredDeviation_;
    public int bucketCountsMemoizedSerializedSize = -1;
    public Internal.LongList bucketCounts_ = GeneratedMessageLite.emptyLongList();
    public Internal.ProtobufList<Exemplar> exemplars_ = GeneratedMessageLite.emptyProtobufList();

    /* JADX INFO: renamed from: com.google.api.Distribution$1, reason: invalid class name */
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

    public static final class BucketOptions extends GeneratedMessageLite<BucketOptions, Builder> implements BucketOptionsOrBuilder {
        public static final BucketOptions DEFAULT_INSTANCE;
        public static final int EXPLICIT_BUCKETS_FIELD_NUMBER = 3;
        public static final int EXPONENTIAL_BUCKETS_FIELD_NUMBER = 2;
        public static final int LINEAR_BUCKETS_FIELD_NUMBER = 1;
        public static volatile Parser<BucketOptions> PARSER;
        public int optionsCase_ = 0;
        public Object options_;

        public static final class Explicit extends GeneratedMessageLite<Explicit, Builder> implements ExplicitOrBuilder {
            public static final int BOUNDS_FIELD_NUMBER = 1;
            public static final Explicit DEFAULT_INSTANCE;
            public static volatile Parser<Explicit> PARSER;
            public int boundsMemoizedSerializedSize = -1;
            public Internal.DoubleList bounds_ = GeneratedMessageLite.emptyDoubleList();

            static {
                Explicit explicit = new Explicit();
                DEFAULT_INSTANCE = explicit;
                GeneratedMessageLite.defaultInstanceMap.put(Explicit.class, explicit);
            }

            public static /* synthetic */ void access$2300(Explicit explicit, int i, double d2) {
                explicit.ensureBoundsIsMutable();
                explicit.bounds_.setDouble(i, d2);
            }

            public static /* synthetic */ void access$2400(Explicit explicit, double d2) {
                explicit.ensureBoundsIsMutable();
                explicit.bounds_.addDouble(d2);
            }

            public static /* synthetic */ void access$2500(Explicit explicit, Iterable iterable) {
                explicit.ensureBoundsIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, (List) explicit.bounds_);
            }

            private void addAllBounds(Iterable<? extends Double> iterable) {
                ensureBoundsIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.bounds_);
            }

            private void addBounds(double d2) {
                ensureBoundsIsMutable();
                this.bounds_.addDouble(d2);
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void clearBounds() {
                this.bounds_ = GeneratedMessageLite.emptyDoubleList();
            }

            private void ensureBoundsIsMutable() {
                Internal.DoubleList doubleList = this.bounds_;
                if (doubleList.isModifiable()) {
                    return;
                }
                this.bounds_ = GeneratedMessageLite.mutableCopy(doubleList);
            }

            public static Explicit getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.createBuilder();
            }

            public static Explicit parseDelimitedFrom(InputStream inputStream) {
                return (Explicit) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static Explicit parseFrom(ByteBuffer byteBuffer) {
                return (Explicit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
            }

            public static Parser<Explicit> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }

            private void setBounds(int i, double d2) {
                ensureBoundsIsMutable();
                this.bounds_.setDouble(i, d2);
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
                        return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001#", new Object[]{"bounds_"});
                    case NEW_MUTABLE_INSTANCE:
                        return new Explicit();
                    case NEW_BUILDER:
                        return new Builder(anonymousClass1);
                    case GET_DEFAULT_INSTANCE:
                        return DEFAULT_INSTANCE;
                    case GET_PARSER:
                        Parser<Explicit> defaultInstanceBasedParser = PARSER;
                        if (defaultInstanceBasedParser == null) {
                            synchronized (Explicit.class) {
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

            @Override // com.google.api.Distribution.BucketOptions.ExplicitOrBuilder
            public double getBounds(int i) {
                return this.bounds_.getDouble(i);
            }

            @Override // com.google.api.Distribution.BucketOptions.ExplicitOrBuilder
            public int getBoundsCount() {
                return this.bounds_.size();
            }

            @Override // com.google.api.Distribution.BucketOptions.ExplicitOrBuilder
            public List<Double> getBoundsList() {
                return this.bounds_;
            }

            public static final class Builder extends GeneratedMessageLite.Builder<Explicit, Builder> implements ExplicitOrBuilder {
                public Builder() {
                    super(Explicit.DEFAULT_INSTANCE);
                }

                public Builder addAllBounds(Iterable<? extends Double> iterable) {
                    copyOnWrite();
                    Explicit.access$2500((Explicit) this.instance, iterable);
                    return this;
                }

                public Builder addBounds(double d2) {
                    copyOnWrite();
                    Explicit.access$2400((Explicit) this.instance, d2);
                    return this;
                }

                public Builder clearBounds() {
                    copyOnWrite();
                    ((Explicit) this.instance).clearBounds();
                    return this;
                }

                @Override // com.google.api.Distribution.BucketOptions.ExplicitOrBuilder
                public double getBounds(int i) {
                    return ((Explicit) this.instance).getBounds(i);
                }

                @Override // com.google.api.Distribution.BucketOptions.ExplicitOrBuilder
                public int getBoundsCount() {
                    return ((Explicit) this.instance).getBoundsCount();
                }

                @Override // com.google.api.Distribution.BucketOptions.ExplicitOrBuilder
                public List<Double> getBoundsList() {
                    return Collections.unmodifiableList(((Explicit) this.instance).getBoundsList());
                }

                public Builder setBounds(int i, double d2) {
                    copyOnWrite();
                    Explicit.access$2300((Explicit) this.instance, i, d2);
                    return this;
                }

                public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                    super(Explicit.DEFAULT_INSTANCE);
                }
            }

            public static Builder newBuilder(Explicit explicit) {
                return DEFAULT_INSTANCE.createBuilder(explicit);
            }

            public static Explicit parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (Explicit) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static Explicit parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
                return (Explicit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
            }

            public static Explicit parseFrom(ByteString byteString) {
                return (Explicit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
            }

            public static Explicit parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
                return (Explicit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
            }

            public static Explicit parseFrom(byte[] bArr) {
                return (Explicit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
            }

            public static Explicit parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
                return (Explicit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
            }

            public static Explicit parseFrom(InputStream inputStream) {
                return (Explicit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static Explicit parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (Explicit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static Explicit parseFrom(CodedInputStream codedInputStream) {
                return (Explicit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
            }

            public static Explicit parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (Explicit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
            }
        }

        public interface ExplicitOrBuilder extends MessageLiteOrBuilder {
            double getBounds(int i);

            int getBoundsCount();

            List<Double> getBoundsList();
        }

        public static final class Exponential extends GeneratedMessageLite<Exponential, Builder> implements ExponentialOrBuilder {
            public static final Exponential DEFAULT_INSTANCE;
            public static final int GROWTH_FACTOR_FIELD_NUMBER = 2;
            public static final int NUM_FINITE_BUCKETS_FIELD_NUMBER = 1;
            public static volatile Parser<Exponential> PARSER = null;
            public static final int SCALE_FIELD_NUMBER = 3;
            public double growthFactor_;
            public int numFiniteBuckets_;
            public double scale_;

            static {
                Exponential exponential = new Exponential();
                DEFAULT_INSTANCE = exponential;
                GeneratedMessageLite.defaultInstanceMap.put(Exponential.class, exponential);
            }

            private void clearGrowthFactor() {
                this.growthFactor_ = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            }

            private void clearNumFiniteBuckets() {
                this.numFiniteBuckets_ = 0;
            }

            private void clearScale() {
                this.scale_ = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            }

            public static Exponential getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.createBuilder();
            }

            public static Exponential parseDelimitedFrom(InputStream inputStream) {
                return (Exponential) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static Exponential parseFrom(ByteBuffer byteBuffer) {
                return (Exponential) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
            }

            public static Parser<Exponential> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }

            private void setGrowthFactor(double d2) {
                this.growthFactor_ = d2;
            }

            private void setNumFiniteBuckets(int i) {
                this.numFiniteBuckets_ = i;
            }

            private void setScale(double d2) {
                this.scale_ = d2;
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
                        return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u0004\u0002\u0000\u0003\u0000", new Object[]{"numFiniteBuckets_", "growthFactor_", "scale_"});
                    case NEW_MUTABLE_INSTANCE:
                        return new Exponential();
                    case NEW_BUILDER:
                        return new Builder(anonymousClass1);
                    case GET_DEFAULT_INSTANCE:
                        return DEFAULT_INSTANCE;
                    case GET_PARSER:
                        Parser<Exponential> defaultInstanceBasedParser = PARSER;
                        if (defaultInstanceBasedParser == null) {
                            synchronized (Exponential.class) {
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

            @Override // com.google.api.Distribution.BucketOptions.ExponentialOrBuilder
            public double getGrowthFactor() {
                return this.growthFactor_;
            }

            @Override // com.google.api.Distribution.BucketOptions.ExponentialOrBuilder
            public int getNumFiniteBuckets() {
                return this.numFiniteBuckets_;
            }

            @Override // com.google.api.Distribution.BucketOptions.ExponentialOrBuilder
            public double getScale() {
                return this.scale_;
            }

            public static final class Builder extends GeneratedMessageLite.Builder<Exponential, Builder> implements ExponentialOrBuilder {
                public Builder() {
                    super(Exponential.DEFAULT_INSTANCE);
                }

                public Builder clearGrowthFactor() {
                    copyOnWrite();
                    ((Exponential) this.instance).growthFactor_ = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                    return this;
                }

                public Builder clearNumFiniteBuckets() {
                    copyOnWrite();
                    ((Exponential) this.instance).numFiniteBuckets_ = 0;
                    return this;
                }

                public Builder clearScale() {
                    copyOnWrite();
                    ((Exponential) this.instance).scale_ = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                    return this;
                }

                @Override // com.google.api.Distribution.BucketOptions.ExponentialOrBuilder
                public double getGrowthFactor() {
                    return ((Exponential) this.instance).getGrowthFactor();
                }

                @Override // com.google.api.Distribution.BucketOptions.ExponentialOrBuilder
                public int getNumFiniteBuckets() {
                    return ((Exponential) this.instance).getNumFiniteBuckets();
                }

                @Override // com.google.api.Distribution.BucketOptions.ExponentialOrBuilder
                public double getScale() {
                    return ((Exponential) this.instance).getScale();
                }

                public Builder setGrowthFactor(double d2) {
                    copyOnWrite();
                    ((Exponential) this.instance).growthFactor_ = d2;
                    return this;
                }

                public Builder setNumFiniteBuckets(int i) {
                    copyOnWrite();
                    ((Exponential) this.instance).numFiniteBuckets_ = i;
                    return this;
                }

                public Builder setScale(double d2) {
                    copyOnWrite();
                    ((Exponential) this.instance).scale_ = d2;
                    return this;
                }

                public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                    super(Exponential.DEFAULT_INSTANCE);
                }
            }

            public static Builder newBuilder(Exponential exponential) {
                return DEFAULT_INSTANCE.createBuilder(exponential);
            }

            public static Exponential parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (Exponential) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static Exponential parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
                return (Exponential) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
            }

            public static Exponential parseFrom(ByteString byteString) {
                return (Exponential) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
            }

            public static Exponential parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
                return (Exponential) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
            }

            public static Exponential parseFrom(byte[] bArr) {
                return (Exponential) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
            }

            public static Exponential parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
                return (Exponential) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
            }

            public static Exponential parseFrom(InputStream inputStream) {
                return (Exponential) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static Exponential parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (Exponential) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static Exponential parseFrom(CodedInputStream codedInputStream) {
                return (Exponential) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
            }

            public static Exponential parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (Exponential) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
            }
        }

        public interface ExponentialOrBuilder extends MessageLiteOrBuilder {
            double getGrowthFactor();

            int getNumFiniteBuckets();

            double getScale();
        }

        public static final class Linear extends GeneratedMessageLite<Linear, Builder> implements LinearOrBuilder {
            public static final Linear DEFAULT_INSTANCE;
            public static final int NUM_FINITE_BUCKETS_FIELD_NUMBER = 1;
            public static final int OFFSET_FIELD_NUMBER = 3;
            public static volatile Parser<Linear> PARSER = null;
            public static final int WIDTH_FIELD_NUMBER = 2;
            public int numFiniteBuckets_;
            public double offset_;
            public double width_;

            static {
                Linear linear = new Linear();
                DEFAULT_INSTANCE = linear;
                GeneratedMessageLite.defaultInstanceMap.put(Linear.class, linear);
            }

            private void clearNumFiniteBuckets() {
                this.numFiniteBuckets_ = 0;
            }

            private void clearOffset() {
                this.offset_ = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            }

            private void clearWidth() {
                this.width_ = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            }

            public static Linear getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.createBuilder();
            }

            public static Linear parseDelimitedFrom(InputStream inputStream) {
                return (Linear) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static Linear parseFrom(ByteBuffer byteBuffer) {
                return (Linear) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
            }

            public static Parser<Linear> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }

            private void setNumFiniteBuckets(int i) {
                this.numFiniteBuckets_ = i;
            }

            private void setOffset(double d2) {
                this.offset_ = d2;
            }

            private void setWidth(double d2) {
                this.width_ = d2;
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
                        return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u0004\u0002\u0000\u0003\u0000", new Object[]{"numFiniteBuckets_", "width_", "offset_"});
                    case NEW_MUTABLE_INSTANCE:
                        return new Linear();
                    case NEW_BUILDER:
                        return new Builder(anonymousClass1);
                    case GET_DEFAULT_INSTANCE:
                        return DEFAULT_INSTANCE;
                    case GET_PARSER:
                        Parser<Linear> defaultInstanceBasedParser = PARSER;
                        if (defaultInstanceBasedParser == null) {
                            synchronized (Linear.class) {
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

            @Override // com.google.api.Distribution.BucketOptions.LinearOrBuilder
            public int getNumFiniteBuckets() {
                return this.numFiniteBuckets_;
            }

            @Override // com.google.api.Distribution.BucketOptions.LinearOrBuilder
            public double getOffset() {
                return this.offset_;
            }

            @Override // com.google.api.Distribution.BucketOptions.LinearOrBuilder
            public double getWidth() {
                return this.width_;
            }

            public static final class Builder extends GeneratedMessageLite.Builder<Linear, Builder> implements LinearOrBuilder {
                public Builder() {
                    super(Linear.DEFAULT_INSTANCE);
                }

                public Builder clearNumFiniteBuckets() {
                    copyOnWrite();
                    ((Linear) this.instance).numFiniteBuckets_ = 0;
                    return this;
                }

                public Builder clearOffset() {
                    copyOnWrite();
                    ((Linear) this.instance).offset_ = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                    return this;
                }

                public Builder clearWidth() {
                    copyOnWrite();
                    ((Linear) this.instance).width_ = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                    return this;
                }

                @Override // com.google.api.Distribution.BucketOptions.LinearOrBuilder
                public int getNumFiniteBuckets() {
                    return ((Linear) this.instance).getNumFiniteBuckets();
                }

                @Override // com.google.api.Distribution.BucketOptions.LinearOrBuilder
                public double getOffset() {
                    return ((Linear) this.instance).getOffset();
                }

                @Override // com.google.api.Distribution.BucketOptions.LinearOrBuilder
                public double getWidth() {
                    return ((Linear) this.instance).getWidth();
                }

                public Builder setNumFiniteBuckets(int i) {
                    copyOnWrite();
                    ((Linear) this.instance).numFiniteBuckets_ = i;
                    return this;
                }

                public Builder setOffset(double d2) {
                    copyOnWrite();
                    ((Linear) this.instance).offset_ = d2;
                    return this;
                }

                public Builder setWidth(double d2) {
                    copyOnWrite();
                    ((Linear) this.instance).width_ = d2;
                    return this;
                }

                public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                    super(Linear.DEFAULT_INSTANCE);
                }
            }

            public static Builder newBuilder(Linear linear) {
                return DEFAULT_INSTANCE.createBuilder(linear);
            }

            public static Linear parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (Linear) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static Linear parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
                return (Linear) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
            }

            public static Linear parseFrom(ByteString byteString) {
                return (Linear) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
            }

            public static Linear parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
                return (Linear) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
            }

            public static Linear parseFrom(byte[] bArr) {
                return (Linear) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
            }

            public static Linear parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
                return (Linear) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
            }

            public static Linear parseFrom(InputStream inputStream) {
                return (Linear) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static Linear parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (Linear) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static Linear parseFrom(CodedInputStream codedInputStream) {
                return (Linear) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
            }

            public static Linear parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (Linear) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
            }
        }

        public interface LinearOrBuilder extends MessageLiteOrBuilder {
            int getNumFiniteBuckets();

            double getOffset();

            double getWidth();
        }

        public enum OptionsCase {
            LINEAR_BUCKETS(1),
            EXPONENTIAL_BUCKETS(2),
            EXPLICIT_BUCKETS(3),
            OPTIONS_NOT_SET(0);

            public final int value;

            OptionsCase(int i) {
                this.value = i;
            }

            public static OptionsCase forNumber(int i) {
                if (i == 0) {
                    return OPTIONS_NOT_SET;
                }
                if (i == 1) {
                    return LINEAR_BUCKETS;
                }
                if (i == 2) {
                    return EXPONENTIAL_BUCKETS;
                }
                if (i != 3) {
                    return null;
                }
                return EXPLICIT_BUCKETS;
            }

            public int getNumber() {
                return this.value;
            }

            @Deprecated
            public static OptionsCase valueOf(int i) {
                return forNumber(i);
            }
        }

        static {
            BucketOptions bucketOptions = new BucketOptions();
            DEFAULT_INSTANCE = bucketOptions;
            GeneratedMessageLite.defaultInstanceMap.put(BucketOptions.class, bucketOptions);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearExplicitBuckets() {
            if (this.optionsCase_ == 3) {
                this.optionsCase_ = 0;
                this.options_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearExponentialBuckets() {
            if (this.optionsCase_ == 2) {
                this.optionsCase_ = 0;
                this.options_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearLinearBuckets() {
            if (this.optionsCase_ == 1) {
                this.optionsCase_ = 0;
                this.options_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearOptions() {
            this.optionsCase_ = 0;
            this.options_ = null;
        }

        public static BucketOptions getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeExplicitBuckets(Explicit explicit) {
            Object obj;
            explicit.getClass();
            if (this.optionsCase_ != 3 || (obj = this.options_) == Explicit.DEFAULT_INSTANCE) {
                this.options_ = explicit;
            } else {
                this.options_ = Explicit.newBuilder((Explicit) obj).mergeFrom(explicit).buildPartial();
            }
            this.optionsCase_ = 3;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeExponentialBuckets(Exponential exponential) {
            Object obj;
            exponential.getClass();
            if (this.optionsCase_ != 2 || (obj = this.options_) == Exponential.DEFAULT_INSTANCE) {
                this.options_ = exponential;
            } else {
                this.options_ = Exponential.newBuilder((Exponential) obj).mergeFrom(exponential).buildPartial();
            }
            this.optionsCase_ = 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeLinearBuckets(Linear linear) {
            Object obj;
            linear.getClass();
            if (this.optionsCase_ != 1 || (obj = this.options_) == Linear.DEFAULT_INSTANCE) {
                this.options_ = linear;
            } else {
                this.options_ = Linear.newBuilder((Linear) obj).mergeFrom(linear).buildPartial();
            }
            this.optionsCase_ = 1;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static BucketOptions parseDelimitedFrom(InputStream inputStream) {
            return (BucketOptions) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static BucketOptions parseFrom(ByteBuffer byteBuffer) {
            return (BucketOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<BucketOptions> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setExplicitBuckets(Explicit explicit) {
            explicit.getClass();
            this.options_ = explicit;
            this.optionsCase_ = 3;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setExponentialBuckets(Exponential exponential) {
            exponential.getClass();
            this.options_ = exponential;
            this.optionsCase_ = 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLinearBuckets(Linear linear) {
            linear.getClass();
            this.options_ = linear;
            this.optionsCase_ = 1;
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
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0001\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001<\u0000\u0002<\u0000\u0003<\u0000", new Object[]{"options_", "optionsCase_", Linear.class, Exponential.class, Explicit.class});
                case NEW_MUTABLE_INSTANCE:
                    return new BucketOptions();
                case NEW_BUILDER:
                    return new Builder(anonymousClass1);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser<BucketOptions> defaultInstanceBasedParser = PARSER;
                    if (defaultInstanceBasedParser == null) {
                        synchronized (BucketOptions.class) {
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

        @Override // com.google.api.Distribution.BucketOptionsOrBuilder
        public Explicit getExplicitBuckets() {
            return this.optionsCase_ == 3 ? (Explicit) this.options_ : Explicit.DEFAULT_INSTANCE;
        }

        @Override // com.google.api.Distribution.BucketOptionsOrBuilder
        public Exponential getExponentialBuckets() {
            return this.optionsCase_ == 2 ? (Exponential) this.options_ : Exponential.DEFAULT_INSTANCE;
        }

        @Override // com.google.api.Distribution.BucketOptionsOrBuilder
        public Linear getLinearBuckets() {
            return this.optionsCase_ == 1 ? (Linear) this.options_ : Linear.DEFAULT_INSTANCE;
        }

        @Override // com.google.api.Distribution.BucketOptionsOrBuilder
        public OptionsCase getOptionsCase() {
            return OptionsCase.forNumber(this.optionsCase_);
        }

        @Override // com.google.api.Distribution.BucketOptionsOrBuilder
        public boolean hasExplicitBuckets() {
            return this.optionsCase_ == 3;
        }

        @Override // com.google.api.Distribution.BucketOptionsOrBuilder
        public boolean hasExponentialBuckets() {
            return this.optionsCase_ == 2;
        }

        @Override // com.google.api.Distribution.BucketOptionsOrBuilder
        public boolean hasLinearBuckets() {
            return this.optionsCase_ == 1;
        }

        public static final class Builder extends GeneratedMessageLite.Builder<BucketOptions, Builder> implements BucketOptionsOrBuilder {
            public Builder() {
                super(BucketOptions.DEFAULT_INSTANCE);
            }

            public Builder clearExplicitBuckets() {
                copyOnWrite();
                ((BucketOptions) this.instance).clearExplicitBuckets();
                return this;
            }

            public Builder clearExponentialBuckets() {
                copyOnWrite();
                ((BucketOptions) this.instance).clearExponentialBuckets();
                return this;
            }

            public Builder clearLinearBuckets() {
                copyOnWrite();
                ((BucketOptions) this.instance).clearLinearBuckets();
                return this;
            }

            public Builder clearOptions() {
                copyOnWrite();
                ((BucketOptions) this.instance).clearOptions();
                return this;
            }

            @Override // com.google.api.Distribution.BucketOptionsOrBuilder
            public Explicit getExplicitBuckets() {
                return ((BucketOptions) this.instance).getExplicitBuckets();
            }

            @Override // com.google.api.Distribution.BucketOptionsOrBuilder
            public Exponential getExponentialBuckets() {
                return ((BucketOptions) this.instance).getExponentialBuckets();
            }

            @Override // com.google.api.Distribution.BucketOptionsOrBuilder
            public Linear getLinearBuckets() {
                return ((BucketOptions) this.instance).getLinearBuckets();
            }

            @Override // com.google.api.Distribution.BucketOptionsOrBuilder
            public OptionsCase getOptionsCase() {
                return ((BucketOptions) this.instance).getOptionsCase();
            }

            @Override // com.google.api.Distribution.BucketOptionsOrBuilder
            public boolean hasExplicitBuckets() {
                return ((BucketOptions) this.instance).hasExplicitBuckets();
            }

            @Override // com.google.api.Distribution.BucketOptionsOrBuilder
            public boolean hasExponentialBuckets() {
                return ((BucketOptions) this.instance).hasExponentialBuckets();
            }

            @Override // com.google.api.Distribution.BucketOptionsOrBuilder
            public boolean hasLinearBuckets() {
                return ((BucketOptions) this.instance).hasLinearBuckets();
            }

            public Builder mergeExplicitBuckets(Explicit explicit) {
                copyOnWrite();
                ((BucketOptions) this.instance).mergeExplicitBuckets(explicit);
                return this;
            }

            public Builder mergeExponentialBuckets(Exponential exponential) {
                copyOnWrite();
                ((BucketOptions) this.instance).mergeExponentialBuckets(exponential);
                return this;
            }

            public Builder mergeLinearBuckets(Linear linear) {
                copyOnWrite();
                ((BucketOptions) this.instance).mergeLinearBuckets(linear);
                return this;
            }

            public Builder setExplicitBuckets(Explicit explicit) {
                copyOnWrite();
                ((BucketOptions) this.instance).setExplicitBuckets(explicit);
                return this;
            }

            public Builder setExponentialBuckets(Exponential exponential) {
                copyOnWrite();
                ((BucketOptions) this.instance).setExponentialBuckets(exponential);
                return this;
            }

            public Builder setLinearBuckets(Linear linear) {
                copyOnWrite();
                ((BucketOptions) this.instance).setLinearBuckets(linear);
                return this;
            }

            public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                super(BucketOptions.DEFAULT_INSTANCE);
            }

            public Builder setExplicitBuckets(Explicit.Builder builder) {
                copyOnWrite();
                ((BucketOptions) this.instance).setExplicitBuckets(builder.build());
                return this;
            }

            public Builder setExponentialBuckets(Exponential.Builder builder) {
                copyOnWrite();
                ((BucketOptions) this.instance).setExponentialBuckets(builder.build());
                return this;
            }

            public Builder setLinearBuckets(Linear.Builder builder) {
                copyOnWrite();
                ((BucketOptions) this.instance).setLinearBuckets(builder.build());
                return this;
            }
        }

        public static Builder newBuilder(BucketOptions bucketOptions) {
            return DEFAULT_INSTANCE.createBuilder(bucketOptions);
        }

        public static BucketOptions parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (BucketOptions) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static BucketOptions parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
            return (BucketOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static BucketOptions parseFrom(ByteString byteString) {
            return (BucketOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static BucketOptions parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (BucketOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static BucketOptions parseFrom(byte[] bArr) {
            return (BucketOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static BucketOptions parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (BucketOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static BucketOptions parseFrom(InputStream inputStream) {
            return (BucketOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static BucketOptions parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (BucketOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static BucketOptions parseFrom(CodedInputStream codedInputStream) {
            return (BucketOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static BucketOptions parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (BucketOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    public interface BucketOptionsOrBuilder extends MessageLiteOrBuilder {
        BucketOptions.Explicit getExplicitBuckets();

        BucketOptions.Exponential getExponentialBuckets();

        BucketOptions.Linear getLinearBuckets();

        BucketOptions.OptionsCase getOptionsCase();

        boolean hasExplicitBuckets();

        boolean hasExponentialBuckets();

        boolean hasLinearBuckets();
    }

    public static final class Exemplar extends GeneratedMessageLite<Exemplar, Builder> implements ExemplarOrBuilder {
        public static final int ATTACHMENTS_FIELD_NUMBER = 3;
        public static final Exemplar DEFAULT_INSTANCE;
        public static volatile Parser<Exemplar> PARSER = null;
        public static final int TIMESTAMP_FIELD_NUMBER = 2;
        public static final int VALUE_FIELD_NUMBER = 1;
        public Internal.ProtobufList<Any> attachments_ = GeneratedMessageLite.emptyProtobufList();
        public Timestamp timestamp_;
        public double value_;

        static {
            Exemplar exemplar = new Exemplar();
            DEFAULT_INSTANCE = exemplar;
            GeneratedMessageLite.defaultInstanceMap.put(Exemplar.class, exemplar);
        }

        public static /* synthetic */ void access$4900(Exemplar exemplar, Iterable iterable) {
            exemplar.ensureAttachmentsIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, (List) exemplar.attachments_);
        }

        public static /* synthetic */ void access$5100(Exemplar exemplar, int i) {
            exemplar.ensureAttachmentsIsMutable();
            exemplar.attachments_.remove(i);
        }

        private void addAllAttachments(Iterable<? extends Any> iterable) {
            ensureAttachmentsIsMutable();
            AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.attachments_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAttachments(Any any) {
            any.getClass();
            ensureAttachmentsIsMutable();
            this.attachments_.add(any);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAttachments() {
            this.attachments_ = GeneratedMessageLite.emptyProtobufList();
        }

        private void clearTimestamp() {
            this.timestamp_ = null;
        }

        private void clearValue() {
            this.value_ = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        }

        private void ensureAttachmentsIsMutable() {
            Internal.ProtobufList<Any> protobufList = this.attachments_;
            if (protobufList.isModifiable()) {
                return;
            }
            this.attachments_ = GeneratedMessageLite.mutableCopy(protobufList);
        }

        public static Exemplar getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeTimestamp(Timestamp timestamp) {
            timestamp.getClass();
            Timestamp timestamp2 = this.timestamp_;
            if (timestamp2 == null || timestamp2 == Timestamp.DEFAULT_INSTANCE) {
                this.timestamp_ = timestamp;
            } else {
                this.timestamp_ = Timestamp.newBuilder(timestamp2).mergeFrom(timestamp).buildPartial();
            }
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Exemplar parseDelimitedFrom(InputStream inputStream) {
            return (Exemplar) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Exemplar parseFrom(ByteBuffer byteBuffer) {
            return (Exemplar) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<Exemplar> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        private void removeAttachments(int i) {
            ensureAttachmentsIsMutable();
            this.attachments_.remove(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAttachments(int i, Any any) {
            any.getClass();
            ensureAttachmentsIsMutable();
            this.attachments_.set(i, any);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTimestamp(Timestamp timestamp) {
            timestamp.getClass();
            this.timestamp_ = timestamp;
        }

        private void setValue(double d2) {
            this.value_ = d2;
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
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0001\u0000\u0001\u0000\u0002\t\u0003\u001b", new Object[]{"value_", "timestamp_", "attachments_", Any.class});
                case NEW_MUTABLE_INSTANCE:
                    return new Exemplar();
                case NEW_BUILDER:
                    return new Builder(anonymousClass1);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser<Exemplar> defaultInstanceBasedParser = PARSER;
                    if (defaultInstanceBasedParser == null) {
                        synchronized (Exemplar.class) {
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

        @Override // com.google.api.Distribution.ExemplarOrBuilder
        public Any getAttachments(int i) {
            return this.attachments_.get(i);
        }

        @Override // com.google.api.Distribution.ExemplarOrBuilder
        public int getAttachmentsCount() {
            return this.attachments_.size();
        }

        @Override // com.google.api.Distribution.ExemplarOrBuilder
        public List<Any> getAttachmentsList() {
            return this.attachments_;
        }

        public AnyOrBuilder getAttachmentsOrBuilder(int i) {
            return this.attachments_.get(i);
        }

        public List<? extends AnyOrBuilder> getAttachmentsOrBuilderList() {
            return this.attachments_;
        }

        @Override // com.google.api.Distribution.ExemplarOrBuilder
        public Timestamp getTimestamp() {
            Timestamp timestamp = this.timestamp_;
            return timestamp == null ? Timestamp.DEFAULT_INSTANCE : timestamp;
        }

        @Override // com.google.api.Distribution.ExemplarOrBuilder
        public double getValue() {
            return this.value_;
        }

        @Override // com.google.api.Distribution.ExemplarOrBuilder
        public boolean hasTimestamp() {
            return this.timestamp_ != null;
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Exemplar, Builder> implements ExemplarOrBuilder {
            public Builder() {
                super(Exemplar.DEFAULT_INSTANCE);
            }

            public Builder addAllAttachments(Iterable<? extends Any> iterable) {
                copyOnWrite();
                Exemplar.access$4900((Exemplar) this.instance, iterable);
                return this;
            }

            public Builder addAttachments(Any any) {
                copyOnWrite();
                ((Exemplar) this.instance).addAttachments(any);
                return this;
            }

            public Builder clearAttachments() {
                copyOnWrite();
                ((Exemplar) this.instance).clearAttachments();
                return this;
            }

            public Builder clearTimestamp() {
                copyOnWrite();
                ((Exemplar) this.instance).timestamp_ = null;
                return this;
            }

            public Builder clearValue() {
                copyOnWrite();
                ((Exemplar) this.instance).value_ = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                return this;
            }

            @Override // com.google.api.Distribution.ExemplarOrBuilder
            public Any getAttachments(int i) {
                return ((Exemplar) this.instance).getAttachments(i);
            }

            @Override // com.google.api.Distribution.ExemplarOrBuilder
            public int getAttachmentsCount() {
                return ((Exemplar) this.instance).getAttachmentsCount();
            }

            @Override // com.google.api.Distribution.ExemplarOrBuilder
            public List<Any> getAttachmentsList() {
                return Collections.unmodifiableList(((Exemplar) this.instance).getAttachmentsList());
            }

            @Override // com.google.api.Distribution.ExemplarOrBuilder
            public Timestamp getTimestamp() {
                return ((Exemplar) this.instance).getTimestamp();
            }

            @Override // com.google.api.Distribution.ExemplarOrBuilder
            public double getValue() {
                return ((Exemplar) this.instance).getValue();
            }

            @Override // com.google.api.Distribution.ExemplarOrBuilder
            public boolean hasTimestamp() {
                return ((Exemplar) this.instance).hasTimestamp();
            }

            public Builder mergeTimestamp(Timestamp timestamp) {
                copyOnWrite();
                ((Exemplar) this.instance).mergeTimestamp(timestamp);
                return this;
            }

            public Builder removeAttachments(int i) {
                copyOnWrite();
                Exemplar.access$5100((Exemplar) this.instance, i);
                return this;
            }

            public Builder setAttachments(int i, Any any) {
                copyOnWrite();
                ((Exemplar) this.instance).setAttachments(i, any);
                return this;
            }

            public Builder setTimestamp(Timestamp timestamp) {
                copyOnWrite();
                ((Exemplar) this.instance).setTimestamp(timestamp);
                return this;
            }

            public Builder setValue(double d2) {
                copyOnWrite();
                ((Exemplar) this.instance).value_ = d2;
                return this;
            }

            public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                super(Exemplar.DEFAULT_INSTANCE);
            }

            public Builder addAttachments(int i, Any any) {
                copyOnWrite();
                ((Exemplar) this.instance).addAttachments(i, any);
                return this;
            }

            public Builder setAttachments(int i, Any.Builder builder) {
                copyOnWrite();
                ((Exemplar) this.instance).setAttachments(i, builder.build());
                return this;
            }

            public Builder setTimestamp(Timestamp.Builder builder) {
                copyOnWrite();
                ((Exemplar) this.instance).setTimestamp(builder.build());
                return this;
            }

            public Builder addAttachments(Any.Builder builder) {
                copyOnWrite();
                ((Exemplar) this.instance).addAttachments(builder.build());
                return this;
            }

            public Builder addAttachments(int i, Any.Builder builder) {
                copyOnWrite();
                ((Exemplar) this.instance).addAttachments(i, builder.build());
                return this;
            }
        }

        public static Builder newBuilder(Exemplar exemplar) {
            return DEFAULT_INSTANCE.createBuilder(exemplar);
        }

        public static Exemplar parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (Exemplar) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Exemplar parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
            return (Exemplar) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static Exemplar parseFrom(ByteString byteString) {
            return (Exemplar) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAttachments(int i, Any any) {
            any.getClass();
            ensureAttachmentsIsMutable();
            this.attachments_.add(i, any);
        }

        public static Exemplar parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (Exemplar) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static Exemplar parseFrom(byte[] bArr) {
            return (Exemplar) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Exemplar parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (Exemplar) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static Exemplar parseFrom(InputStream inputStream) {
            return (Exemplar) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Exemplar parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (Exemplar) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Exemplar parseFrom(CodedInputStream codedInputStream) {
            return (Exemplar) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static Exemplar parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (Exemplar) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    public interface ExemplarOrBuilder extends MessageLiteOrBuilder {
        Any getAttachments(int i);

        int getAttachmentsCount();

        List<Any> getAttachmentsList();

        Timestamp getTimestamp();

        double getValue();

        boolean hasTimestamp();
    }

    public static final class Range extends GeneratedMessageLite<Range, Builder> implements RangeOrBuilder {
        public static final Range DEFAULT_INSTANCE;
        public static final int MAX_FIELD_NUMBER = 2;
        public static final int MIN_FIELD_NUMBER = 1;
        public static volatile Parser<Range> PARSER;
        public double max_;
        public double min_;

        static {
            Range range = new Range();
            DEFAULT_INSTANCE = range;
            GeneratedMessageLite.defaultInstanceMap.put(Range.class, range);
        }

        private void clearMax() {
            this.max_ = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        }

        private void clearMin() {
            this.min_ = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        }

        public static Range getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Range parseDelimitedFrom(InputStream inputStream) {
            return (Range) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Range parseFrom(ByteBuffer byteBuffer) {
            return (Range) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<Range> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        private void setMax(double d2) {
            this.max_ = d2;
        }

        private void setMin(double d2) {
            this.min_ = d2;
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
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0000\u0002\u0000", new Object[]{"min_", "max_"});
                case NEW_MUTABLE_INSTANCE:
                    return new Range();
                case NEW_BUILDER:
                    return new Builder(anonymousClass1);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser<Range> defaultInstanceBasedParser = PARSER;
                    if (defaultInstanceBasedParser == null) {
                        synchronized (Range.class) {
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

        @Override // com.google.api.Distribution.RangeOrBuilder
        public double getMax() {
            return this.max_;
        }

        @Override // com.google.api.Distribution.RangeOrBuilder
        public double getMin() {
            return this.min_;
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Range, Builder> implements RangeOrBuilder {
            public Builder() {
                super(Range.DEFAULT_INSTANCE);
            }

            public Builder clearMax() {
                copyOnWrite();
                ((Range) this.instance).max_ = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                return this;
            }

            public Builder clearMin() {
                copyOnWrite();
                ((Range) this.instance).min_ = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                return this;
            }

            @Override // com.google.api.Distribution.RangeOrBuilder
            public double getMax() {
                return ((Range) this.instance).getMax();
            }

            @Override // com.google.api.Distribution.RangeOrBuilder
            public double getMin() {
                return ((Range) this.instance).getMin();
            }

            public Builder setMax(double d2) {
                copyOnWrite();
                ((Range) this.instance).max_ = d2;
                return this;
            }

            public Builder setMin(double d2) {
                copyOnWrite();
                ((Range) this.instance).min_ = d2;
                return this;
            }

            public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                super(Range.DEFAULT_INSTANCE);
            }
        }

        public static Builder newBuilder(Range range) {
            return DEFAULT_INSTANCE.createBuilder(range);
        }

        public static Range parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (Range) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Range parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
            return (Range) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static Range parseFrom(ByteString byteString) {
            return (Range) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static Range parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (Range) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static Range parseFrom(byte[] bArr) {
            return (Range) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Range parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (Range) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static Range parseFrom(InputStream inputStream) {
            return (Range) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Range parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (Range) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Range parseFrom(CodedInputStream codedInputStream) {
            return (Range) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static Range parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (Range) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    public interface RangeOrBuilder extends MessageLiteOrBuilder {
        double getMax();

        double getMin();
    }

    static {
        Distribution distribution = new Distribution();
        DEFAULT_INSTANCE = distribution;
        GeneratedMessageLite.defaultInstanceMap.put(Distribution.class, distribution);
    }

    public static /* synthetic */ void access$6600(Distribution distribution, int i, long j) {
        distribution.ensureBucketCountsIsMutable();
        distribution.bucketCounts_.setLong(i, j);
    }

    public static /* synthetic */ void access$6700(Distribution distribution, long j) {
        distribution.ensureBucketCountsIsMutable();
        distribution.bucketCounts_.addLong(j);
    }

    public static /* synthetic */ void access$6800(Distribution distribution, Iterable iterable) {
        distribution.ensureBucketCountsIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) distribution.bucketCounts_);
    }

    public static /* synthetic */ void access$7300(Distribution distribution, Iterable iterable) {
        distribution.ensureExemplarsIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) distribution.exemplars_);
    }

    public static /* synthetic */ void access$7500(Distribution distribution, int i) {
        distribution.ensureExemplarsIsMutable();
        distribution.exemplars_.remove(i);
    }

    private void addAllBucketCounts(Iterable<? extends Long> iterable) {
        ensureBucketCountsIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.bucketCounts_);
    }

    private void addAllExemplars(Iterable<? extends Exemplar> iterable) {
        ensureExemplarsIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.exemplars_);
    }

    private void addBucketCounts(long j) {
        ensureBucketCountsIsMutable();
        this.bucketCounts_.addLong(j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addExemplars(Exemplar exemplar) {
        exemplar.getClass();
        ensureExemplarsIsMutable();
        this.exemplars_.add(exemplar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearBucketCounts() {
        this.bucketCounts_ = GeneratedMessageLite.emptyLongList();
    }

    private void clearBucketOptions() {
        this.bucketOptions_ = null;
    }

    private void clearCount() {
        this.count_ = 0L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearExemplars() {
        this.exemplars_ = GeneratedMessageLite.emptyProtobufList();
    }

    private void clearMean() {
        this.mean_ = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    }

    private void clearRange() {
        this.range_ = null;
    }

    private void clearSumOfSquaredDeviation() {
        this.sumOfSquaredDeviation_ = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    }

    private void ensureBucketCountsIsMutable() {
        Internal.LongList longList = this.bucketCounts_;
        if (longList.isModifiable()) {
            return;
        }
        this.bucketCounts_ = GeneratedMessageLite.mutableCopy(longList);
    }

    private void ensureExemplarsIsMutable() {
        Internal.ProtobufList<Exemplar> protobufList = this.exemplars_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.exemplars_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    public static Distribution getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeBucketOptions(BucketOptions bucketOptions) {
        bucketOptions.getClass();
        BucketOptions bucketOptions2 = this.bucketOptions_;
        if (bucketOptions2 == null || bucketOptions2 == BucketOptions.DEFAULT_INSTANCE) {
            this.bucketOptions_ = bucketOptions;
        } else {
            this.bucketOptions_ = BucketOptions.newBuilder(bucketOptions2).mergeFrom(bucketOptions).buildPartial();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeRange(Range range) {
        range.getClass();
        Range range2 = this.range_;
        if (range2 == null || range2 == Range.DEFAULT_INSTANCE) {
            this.range_ = range;
        } else {
            this.range_ = Range.newBuilder(range2).mergeFrom(range).buildPartial();
        }
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Distribution parseDelimitedFrom(InputStream inputStream) {
        return (Distribution) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Distribution parseFrom(ByteBuffer byteBuffer) {
        return (Distribution) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<Distribution> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    private void removeExemplars(int i) {
        ensureExemplarsIsMutable();
        this.exemplars_.remove(i);
    }

    private void setBucketCounts(int i, long j) {
        ensureBucketCountsIsMutable();
        this.bucketCounts_.setLong(i, j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBucketOptions(BucketOptions bucketOptions) {
        bucketOptions.getClass();
        this.bucketOptions_ = bucketOptions;
    }

    private void setCount(long j) {
        this.count_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setExemplars(int i, Exemplar exemplar) {
        exemplar.getClass();
        ensureExemplarsIsMutable();
        this.exemplars_.set(i, exemplar);
    }

    private void setMean(double d2) {
        this.mean_ = d2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRange(Range range) {
        range.getClass();
        this.range_ = range;
    }

    private void setSumOfSquaredDeviation(double d2) {
        this.sumOfSquaredDeviation_ = d2;
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0007\u0000\u0000\u0001\n\u0007\u0000\u0002\u0000\u0001\u0002\u0002\u0000\u0003\u0000\u0004\t\u0006\t\u0007%\n\u001b", new Object[]{"count_", "mean_", "sumOfSquaredDeviation_", "range_", "bucketOptions_", "bucketCounts_", "exemplars_", Exemplar.class});
            case NEW_MUTABLE_INSTANCE:
                return new Distribution();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<Distribution> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (Distribution.class) {
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

    @Override // com.google.api.DistributionOrBuilder
    public long getBucketCounts(int i) {
        return this.bucketCounts_.getLong(i);
    }

    @Override // com.google.api.DistributionOrBuilder
    public int getBucketCountsCount() {
        return this.bucketCounts_.size();
    }

    @Override // com.google.api.DistributionOrBuilder
    public List<Long> getBucketCountsList() {
        return this.bucketCounts_;
    }

    @Override // com.google.api.DistributionOrBuilder
    public BucketOptions getBucketOptions() {
        BucketOptions bucketOptions = this.bucketOptions_;
        return bucketOptions == null ? BucketOptions.DEFAULT_INSTANCE : bucketOptions;
    }

    @Override // com.google.api.DistributionOrBuilder
    public long getCount() {
        return this.count_;
    }

    @Override // com.google.api.DistributionOrBuilder
    public Exemplar getExemplars(int i) {
        return this.exemplars_.get(i);
    }

    @Override // com.google.api.DistributionOrBuilder
    public int getExemplarsCount() {
        return this.exemplars_.size();
    }

    @Override // com.google.api.DistributionOrBuilder
    public List<Exemplar> getExemplarsList() {
        return this.exemplars_;
    }

    public ExemplarOrBuilder getExemplarsOrBuilder(int i) {
        return this.exemplars_.get(i);
    }

    public List<? extends ExemplarOrBuilder> getExemplarsOrBuilderList() {
        return this.exemplars_;
    }

    @Override // com.google.api.DistributionOrBuilder
    public double getMean() {
        return this.mean_;
    }

    @Override // com.google.api.DistributionOrBuilder
    public Range getRange() {
        Range range = this.range_;
        return range == null ? Range.DEFAULT_INSTANCE : range;
    }

    @Override // com.google.api.DistributionOrBuilder
    public double getSumOfSquaredDeviation() {
        return this.sumOfSquaredDeviation_;
    }

    @Override // com.google.api.DistributionOrBuilder
    public boolean hasBucketOptions() {
        return this.bucketOptions_ != null;
    }

    @Override // com.google.api.DistributionOrBuilder
    public boolean hasRange() {
        return this.range_ != null;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Distribution, Builder> implements DistributionOrBuilder {
        public Builder() {
            super(Distribution.DEFAULT_INSTANCE);
        }

        public Builder addAllBucketCounts(Iterable<? extends Long> iterable) {
            copyOnWrite();
            Distribution.access$6800((Distribution) this.instance, iterable);
            return this;
        }

        public Builder addAllExemplars(Iterable<? extends Exemplar> iterable) {
            copyOnWrite();
            Distribution.access$7300((Distribution) this.instance, iterable);
            return this;
        }

        public Builder addBucketCounts(long j) {
            copyOnWrite();
            Distribution.access$6700((Distribution) this.instance, j);
            return this;
        }

        public Builder addExemplars(Exemplar exemplar) {
            copyOnWrite();
            ((Distribution) this.instance).addExemplars(exemplar);
            return this;
        }

        public Builder clearBucketCounts() {
            copyOnWrite();
            ((Distribution) this.instance).clearBucketCounts();
            return this;
        }

        public Builder clearBucketOptions() {
            copyOnWrite();
            ((Distribution) this.instance).bucketOptions_ = null;
            return this;
        }

        public Builder clearCount() {
            copyOnWrite();
            ((Distribution) this.instance).count_ = 0L;
            return this;
        }

        public Builder clearExemplars() {
            copyOnWrite();
            ((Distribution) this.instance).clearExemplars();
            return this;
        }

        public Builder clearMean() {
            copyOnWrite();
            ((Distribution) this.instance).mean_ = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            return this;
        }

        public Builder clearRange() {
            copyOnWrite();
            ((Distribution) this.instance).range_ = null;
            return this;
        }

        public Builder clearSumOfSquaredDeviation() {
            copyOnWrite();
            ((Distribution) this.instance).sumOfSquaredDeviation_ = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            return this;
        }

        @Override // com.google.api.DistributionOrBuilder
        public long getBucketCounts(int i) {
            return ((Distribution) this.instance).getBucketCounts(i);
        }

        @Override // com.google.api.DistributionOrBuilder
        public int getBucketCountsCount() {
            return ((Distribution) this.instance).getBucketCountsCount();
        }

        @Override // com.google.api.DistributionOrBuilder
        public List<Long> getBucketCountsList() {
            return Collections.unmodifiableList(((Distribution) this.instance).getBucketCountsList());
        }

        @Override // com.google.api.DistributionOrBuilder
        public BucketOptions getBucketOptions() {
            return ((Distribution) this.instance).getBucketOptions();
        }

        @Override // com.google.api.DistributionOrBuilder
        public long getCount() {
            return ((Distribution) this.instance).getCount();
        }

        @Override // com.google.api.DistributionOrBuilder
        public Exemplar getExemplars(int i) {
            return ((Distribution) this.instance).getExemplars(i);
        }

        @Override // com.google.api.DistributionOrBuilder
        public int getExemplarsCount() {
            return ((Distribution) this.instance).getExemplarsCount();
        }

        @Override // com.google.api.DistributionOrBuilder
        public List<Exemplar> getExemplarsList() {
            return Collections.unmodifiableList(((Distribution) this.instance).getExemplarsList());
        }

        @Override // com.google.api.DistributionOrBuilder
        public double getMean() {
            return ((Distribution) this.instance).getMean();
        }

        @Override // com.google.api.DistributionOrBuilder
        public Range getRange() {
            return ((Distribution) this.instance).getRange();
        }

        @Override // com.google.api.DistributionOrBuilder
        public double getSumOfSquaredDeviation() {
            return ((Distribution) this.instance).getSumOfSquaredDeviation();
        }

        @Override // com.google.api.DistributionOrBuilder
        public boolean hasBucketOptions() {
            return ((Distribution) this.instance).hasBucketOptions();
        }

        @Override // com.google.api.DistributionOrBuilder
        public boolean hasRange() {
            return ((Distribution) this.instance).hasRange();
        }

        public Builder mergeBucketOptions(BucketOptions bucketOptions) {
            copyOnWrite();
            ((Distribution) this.instance).mergeBucketOptions(bucketOptions);
            return this;
        }

        public Builder mergeRange(Range range) {
            copyOnWrite();
            ((Distribution) this.instance).mergeRange(range);
            return this;
        }

        public Builder removeExemplars(int i) {
            copyOnWrite();
            Distribution.access$7500((Distribution) this.instance, i);
            return this;
        }

        public Builder setBucketCounts(int i, long j) {
            copyOnWrite();
            Distribution.access$6600((Distribution) this.instance, i, j);
            return this;
        }

        public Builder setBucketOptions(BucketOptions bucketOptions) {
            copyOnWrite();
            ((Distribution) this.instance).setBucketOptions(bucketOptions);
            return this;
        }

        public Builder setCount(long j) {
            copyOnWrite();
            ((Distribution) this.instance).count_ = j;
            return this;
        }

        public Builder setExemplars(int i, Exemplar exemplar) {
            copyOnWrite();
            ((Distribution) this.instance).setExemplars(i, exemplar);
            return this;
        }

        public Builder setMean(double d2) {
            copyOnWrite();
            ((Distribution) this.instance).mean_ = d2;
            return this;
        }

        public Builder setRange(Range range) {
            copyOnWrite();
            ((Distribution) this.instance).setRange(range);
            return this;
        }

        public Builder setSumOfSquaredDeviation(double d2) {
            copyOnWrite();
            ((Distribution) this.instance).sumOfSquaredDeviation_ = d2;
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(Distribution.DEFAULT_INSTANCE);
        }

        public Builder addExemplars(int i, Exemplar exemplar) {
            copyOnWrite();
            ((Distribution) this.instance).addExemplars(i, exemplar);
            return this;
        }

        public Builder setBucketOptions(BucketOptions.Builder builder) {
            copyOnWrite();
            ((Distribution) this.instance).setBucketOptions(builder.build());
            return this;
        }

        public Builder setExemplars(int i, Exemplar.Builder builder) {
            copyOnWrite();
            ((Distribution) this.instance).setExemplars(i, builder.build());
            return this;
        }

        public Builder setRange(Range.Builder builder) {
            copyOnWrite();
            ((Distribution) this.instance).setRange(builder.build());
            return this;
        }

        public Builder addExemplars(Exemplar.Builder builder) {
            copyOnWrite();
            ((Distribution) this.instance).addExemplars(builder.build());
            return this;
        }

        public Builder addExemplars(int i, Exemplar.Builder builder) {
            copyOnWrite();
            ((Distribution) this.instance).addExemplars(i, builder.build());
            return this;
        }
    }

    public static Builder newBuilder(Distribution distribution) {
        return DEFAULT_INSTANCE.createBuilder(distribution);
    }

    public static Distribution parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Distribution) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Distribution parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (Distribution) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static Distribution parseFrom(ByteString byteString) {
        return (Distribution) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addExemplars(int i, Exemplar exemplar) {
        exemplar.getClass();
        ensureExemplarsIsMutable();
        this.exemplars_.add(i, exemplar);
    }

    public static Distribution parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (Distribution) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Distribution parseFrom(byte[] bArr) {
        return (Distribution) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Distribution parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (Distribution) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Distribution parseFrom(InputStream inputStream) {
        return (Distribution) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Distribution parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Distribution) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Distribution parseFrom(CodedInputStream codedInputStream) {
        return (Distribution) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Distribution parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Distribution) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
