package kotlin.reflect.jvm.internal;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KParameter;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeParameter;
import kotlin.reflect.KVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyAccessorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ValueParameterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.load.java.JavaDescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaCallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedPropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedSimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.types.DescriptorKType;
import kotlin.reflect.jvm.internal.types.KTypeSubstitutor;

/* JADX INFO: compiled from: DescriptorKCallable.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u001b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\b \u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\r\u001a\u00020\u000eH$J\u001b\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u0003\u001a\u00020\u0004H ¢\u0006\u0002\b\u0010J\u0016\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001a0\u00132\u0006\u0010!\u001a\u00020\"H\u0002J\u0012\u0010#\u001a\b\u0012\u0004\u0012\u00020$0\u0013*\u00020\nH\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0012\u0010\t\u001a\u00020\nX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR(\u0010\u0011\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u0014 \u0015*\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u00130\u00130\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00140\u00138VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R(\u0010\u0019\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u001a \u0015*\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u00130\u00130\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0018R(\u0010\u001d\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u001a \u0015*\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u00130\u00130\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00138VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0018R\u001c\u0010%\u001a\u0010\u0012\f\u0012\n \u0015*\u0004\u0018\u00010&0&0\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0015\u0010'\u001a\u00020&8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b(\u0010)R(\u0010*\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020+ \u0015*\n\u0012\u0004\u0012\u00020+\u0018\u00010\u00130\u00130\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010,\u001a\b\u0012\u0004\u0012\u00020-0\u00138VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b.\u0010\u0018R\u0017\u0010/\u001a\u0004\u0018\u0001008VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b1\u00102R\u0014\u00103\u001a\u0002048@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b5\u00106R\u0014\u00107\u001a\u00020\"8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b8\u00109R\u0015\u0010:\u001a\u00020\"8FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b:\u00109R\u0015\u0010;\u001a\u00020\"8FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b;\u00109R\u0015\u0010<\u001a\u00020\"8FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b<\u00109¨\u0006="}, d2 = {"Lkotlin/reflect/jvm/internal/DescriptorKCallable;", "R", "Lkotlin/reflect/jvm/internal/ReflectKCallableImpl;", "overriddenStorage", "Lkotlin/reflect/jvm/internal/KCallableOverriddenStorage;", "<init>", "(Lkotlin/reflect/jvm/internal/KCallableOverriddenStorage;)V", "getOverriddenStorage$kotlin_reflection", "()Lkotlin/reflect/jvm/internal/KCallableOverriddenStorage;", "descriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/CallableMemberDescriptor;", "getDescriptor", "()Lorg/jetbrains/kotlin/descriptors/CallableMemberDescriptor;", "computeReturnType", "Lkotlin/reflect/jvm/internal/types/DescriptorKType;", "shallowCopy", "shallowCopy$kotlin_reflection", "_annotations", "Lkotlin/reflect/jvm/internal/ReflectProperties$LazySoftVal;", "", "", "kotlin.jvm.PlatformType", "annotations", "getAnnotations", "()Ljava/util/List;", "_allParameters", "Lkotlin/reflect/KParameter;", "allParameters", "getAllParameters", "_parameters", "parameters", "getParameters", "computeParameters", "includeReceivers", "", "computeContextParameters", "Lkotlin/reflect/jvm/internal/impl/descriptors/ValueParameterDescriptor;", "_returnType", "Lkotlin/reflect/KType;", "returnType", "getReturnType", "()Lkotlin/reflect/KType;", "_typeParameters", "Lkotlin/reflect/jvm/internal/KTypeParameterImpl;", "typeParameters", "Lkotlin/reflect/KTypeParameter;", "getTypeParameters", "visibility", "Lkotlin/reflect/KVisibility;", "getVisibility", "()Lkotlin/reflect/KVisibility;", "modality", "Lkotlin/reflect/jvm/internal/impl/descriptors/Modality;", "getModality$kotlin_reflection", "()Lorg/jetbrains/kotlin/descriptors/Modality;", "isPackagePrivate", "isPackagePrivate$kotlin_reflection", "()Z", "isFinal", "isOpen", "isAbstract", "kotlin-reflection"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class DescriptorKCallable<R> extends ReflectKCallableImpl<R> {
    private final ReflectProperties.LazySoftVal<List<KParameter>> _allParameters;
    private final ReflectProperties.LazySoftVal<List<Annotation>> _annotations;
    private final ReflectProperties.LazySoftVal<List<KParameter>> _parameters;
    private final ReflectProperties.LazySoftVal<KType> _returnType;
    private final ReflectProperties.LazySoftVal<List<KTypeParameterImpl>> _typeParameters;
    private final KCallableOverriddenStorage overriddenStorage;

    protected abstract DescriptorKType computeReturnType();

    public abstract CallableMemberDescriptor getDescriptor();

    public abstract DescriptorKCallable<R> shallowCopy$kotlin_reflection(KCallableOverriddenStorage overriddenStorage);

    public DescriptorKCallable(KCallableOverriddenStorage overriddenStorage) {
        Intrinsics.checkNotNullParameter(overriddenStorage, "overriddenStorage");
        this.overriddenStorage = overriddenStorage;
        ReflectProperties.LazySoftVal<List<Annotation>> lazySoftValLazySoft = ReflectProperties.lazySoft(new Function0(this) { // from class: kotlin.reflect.jvm.internal.DescriptorKCallable$$Lambda$0
            private final DescriptorKCallable arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return DescriptorKCallable._annotations$lambda$0(this.arg$0);
            }
        });
        Intrinsics.checkNotNullExpressionValue(lazySoftValLazySoft, "lazySoft(...)");
        this._annotations = lazySoftValLazySoft;
        ReflectProperties.LazySoftVal<List<KParameter>> lazySoftValLazySoft2 = ReflectProperties.lazySoft(new Function0(this) { // from class: kotlin.reflect.jvm.internal.DescriptorKCallable$$Lambda$1
            private final DescriptorKCallable arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return DescriptorKCallable._allParameters$lambda$0(this.arg$0);
            }
        });
        Intrinsics.checkNotNullExpressionValue(lazySoftValLazySoft2, "lazySoft(...)");
        this._allParameters = lazySoftValLazySoft2;
        ReflectProperties.LazySoftVal<List<KParameter>> lazySoftValLazySoft3 = ReflectProperties.lazySoft(new Function0(this) { // from class: kotlin.reflect.jvm.internal.DescriptorKCallable$$Lambda$2
            private final DescriptorKCallable arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return DescriptorKCallable._parameters$lambda$0(this.arg$0);
            }
        });
        Intrinsics.checkNotNullExpressionValue(lazySoftValLazySoft3, "lazySoft(...)");
        this._parameters = lazySoftValLazySoft3;
        ReflectProperties.LazySoftVal<KType> lazySoftValLazySoft4 = ReflectProperties.lazySoft(new Function0(this) { // from class: kotlin.reflect.jvm.internal.DescriptorKCallable$$Lambda$3
            private final DescriptorKCallable arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return DescriptorKCallable._returnType$lambda$0(this.arg$0);
            }
        });
        Intrinsics.checkNotNullExpressionValue(lazySoftValLazySoft4, "lazySoft(...)");
        this._returnType = lazySoftValLazySoft4;
        ReflectProperties.LazySoftVal<List<KTypeParameterImpl>> lazySoftValLazySoft5 = ReflectProperties.lazySoft(new Function0(this) { // from class: kotlin.reflect.jvm.internal.DescriptorKCallable$$Lambda$4
            private final DescriptorKCallable arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return DescriptorKCallable._typeParameters$lambda$0(this.arg$0);
            }
        });
        Intrinsics.checkNotNullExpressionValue(lazySoftValLazySoft5, "lazySoft(...)");
        this._typeParameters = lazySoftValLazySoft5;
    }

    /* JADX INFO: renamed from: getOverriddenStorage$kotlin_reflection, reason: from getter */
    public final KCallableOverriddenStorage getOverriddenStorage() {
        return this.overriddenStorage;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List _annotations$lambda$0(DescriptorKCallable descriptorKCallable) {
        return UtilKt.computeAnnotations(descriptorKCallable.getDescriptor());
    }

    @Override // kotlin.reflect.KAnnotatedElement
    public List<Annotation> getAnnotations() {
        List<Annotation> listInvoke = this._annotations.invoke();
        Intrinsics.checkNotNullExpressionValue(listInvoke, "invoke(...)");
        return listInvoke;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List _allParameters$lambda$0(DescriptorKCallable descriptorKCallable) {
        return descriptorKCallable.computeParameters(true);
    }

    @Override // kotlin.reflect.jvm.internal.ReflectKCallable
    public List<KParameter> getAllParameters() {
        List<KParameter> listInvoke = this._allParameters.invoke();
        Intrinsics.checkNotNullExpressionValue(listInvoke, "invoke(...)");
        return listInvoke;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List _parameters$lambda$0(DescriptorKCallable descriptorKCallable) {
        return ReflectKCallableKt.isBound(descriptorKCallable) ? descriptorKCallable.computeParameters(false) : descriptorKCallable.getAllParameters();
    }

    @Override // kotlin.reflect.KCallable
    public List<KParameter> getParameters() {
        List<KParameter> listInvoke = this._parameters.invoke();
        Intrinsics.checkNotNullExpressionValue(listInvoke, "invoke(...)");
        return listInvoke;
    }

    private final List<KParameter> computeParameters(boolean includeReceivers) {
        final CallableMemberDescriptor descriptor = getDescriptor();
        ArrayList arrayList = new ArrayList();
        if (includeReceivers) {
            final ReceiverParameterDescriptor instanceReceiverParameter = UtilKt.getInstanceReceiverParameter(this);
            if (instanceReceiverParameter != null) {
                arrayList.add(new DescriptorKParameter(this, arrayList.size(), KParameter.Kind.INSTANCE, new Function0(instanceReceiverParameter) { // from class: kotlin.reflect.jvm.internal.DescriptorKCallable$$Lambda$5
                    private final ReceiverParameterDescriptor arg$0;

                    {
                        this.arg$0 = instanceReceiverParameter;
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public Object invoke() {
                        return DescriptorKCallable.computeParameters$lambda$0(this.arg$0);
                    }
                }));
            }
            final List<ValueParameterDescriptor> listComputeContextParameters = computeContextParameters(descriptor);
            int size = listComputeContextParameters.size();
            for (final int i = 0; i < size; i++) {
                arrayList.add(new DescriptorKParameter(this, arrayList.size(), KParameter.Kind.CONTEXT, new Function0(listComputeContextParameters, i) { // from class: kotlin.reflect.jvm.internal.DescriptorKCallable$$Lambda$6
                    private final List arg$0;
                    private final int arg$1;

                    {
                        this.arg$0 = listComputeContextParameters;
                        this.arg$1 = i;
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public Object invoke() {
                        return DescriptorKCallable.computeParameters$lambda$1(this.arg$0, this.arg$1);
                    }
                }));
            }
            final ReceiverParameterDescriptor extensionReceiverParameter = descriptor.getExtensionReceiverParameter();
            if (extensionReceiverParameter != null) {
                arrayList.add(new DescriptorKParameter(this, arrayList.size(), KParameter.Kind.EXTENSION_RECEIVER, new Function0(extensionReceiverParameter) { // from class: kotlin.reflect.jvm.internal.DescriptorKCallable$$Lambda$7
                    private final ReceiverParameterDescriptor arg$0;

                    {
                        this.arg$0 = extensionReceiverParameter;
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public Object invoke() {
                        return DescriptorKCallable.computeParameters$lambda$2(this.arg$0);
                    }
                }));
            }
        }
        int size2 = descriptor.getValueParameters().size();
        for (final int i2 = 0; i2 < size2; i2++) {
            arrayList.add(new DescriptorKParameter(this, arrayList.size(), KParameter.Kind.VALUE, new Function0(descriptor, i2) { // from class: kotlin.reflect.jvm.internal.DescriptorKCallable$$Lambda$8
                private final CallableMemberDescriptor arg$0;
                private final int arg$1;

                {
                    this.arg$0 = descriptor;
                    this.arg$1 = i2;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return DescriptorKCallable.computeParameters$lambda$3(this.arg$0, this.arg$1);
                }
            }));
        }
        if (ReflectKCallableKt.isAnnotationConstructor(this) && (descriptor instanceof JavaCallableMemberDescriptor)) {
            ArrayList arrayList2 = arrayList;
            if (arrayList2.size() > 1) {
                CollectionsKt.sortWith(arrayList2, new Comparator() { // from class: kotlin.reflect.jvm.internal.DescriptorKCallable$computeParameters$$inlined$sortBy$1
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        return ComparisonsKt.compareValues(((KParameter) t).getName(), ((KParameter) t2).getName());
                    }
                });
            }
        }
        arrayList.trimToSize();
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ParameterDescriptor computeParameters$lambda$0(ReceiverParameterDescriptor receiverParameterDescriptor) {
        return receiverParameterDescriptor;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ParameterDescriptor computeParameters$lambda$1(List list, int i) {
        return (ParameterDescriptor) list.get(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ParameterDescriptor computeParameters$lambda$2(ReceiverParameterDescriptor receiverParameterDescriptor) {
        return receiverParameterDescriptor;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ParameterDescriptor computeParameters$lambda$3(CallableMemberDescriptor callableMemberDescriptor, int i) {
        ValueParameterDescriptor valueParameterDescriptor = callableMemberDescriptor.getValueParameters().get(i);
        Intrinsics.checkNotNullExpressionValue(valueParameterDescriptor, "get(...)");
        return valueParameterDescriptor;
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0059  */
    private final List<ValueParameterDescriptor> computeContextParameters(CallableMemberDescriptor callableMemberDescriptor) {
        Pair pair;
        if (callableMemberDescriptor instanceof DeserializedSimpleFunctionDescriptor) {
            DeserializedSimpleFunctionDescriptor deserializedSimpleFunctionDescriptor = (DeserializedSimpleFunctionDescriptor) callableMemberDescriptor;
            pair = TuplesKt.to(deserializedSimpleFunctionDescriptor.getNameResolver(), deserializedSimpleFunctionDescriptor.getProto().getContextParameterList());
        } else if (callableMemberDescriptor instanceof DeserializedPropertyDescriptor) {
            DeserializedPropertyDescriptor deserializedPropertyDescriptor = (DeserializedPropertyDescriptor) callableMemberDescriptor;
            pair = TuplesKt.to(deserializedPropertyDescriptor.getNameResolver(), deserializedPropertyDescriptor.getProto().getContextParameterList());
        } else if (callableMemberDescriptor instanceof PropertyAccessorDescriptor) {
            PropertyDescriptor correspondingProperty = ((PropertyAccessorDescriptor) callableMemberDescriptor).getCorrespondingProperty();
            DeserializedPropertyDescriptor deserializedPropertyDescriptor2 = correspondingProperty instanceof DeserializedPropertyDescriptor ? (DeserializedPropertyDescriptor) correspondingProperty : null;
            if (deserializedPropertyDescriptor2 != null) {
                pair = TuplesKt.to(deserializedPropertyDescriptor2.getNameResolver(), deserializedPropertyDescriptor2.getProto().getContextParameterList());
            } else {
                pair = null;
            }
        } else {
            pair = null;
        }
        if (pair != null) {
            NameResolver nameResolver = (NameResolver) pair.component1();
            List list = (List) pair.component2();
            List<ReceiverParameterDescriptor> contextReceiverParameters = callableMemberDescriptor.getContextReceiverParameters();
            Intrinsics.checkNotNullExpressionValue(contextReceiverParameters, "getContextReceiverParameters(...)");
            List<ReceiverParameterDescriptor> list2 = contextReceiverParameters;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
            int i = 0;
            for (Object obj : list2) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                ReceiverParameterDescriptor receiverParameterDescriptor = (ReceiverParameterDescriptor) obj;
                Annotations annotations = receiverParameterDescriptor.getAnnotations();
                Name nameGuessByFirstCharacter = Name.guessByFirstCharacter(nameResolver.getString(((ProtoBuf.ValueParameter) list.get(i)).getName()));
                Intrinsics.checkNotNullExpressionValue(nameGuessByFirstCharacter, "guessByFirstCharacter(...)");
                KotlinType type = receiverParameterDescriptor.getType();
                Intrinsics.checkNotNullExpressionValue(type, "getType(...)");
                SourceElement source = receiverParameterDescriptor.getSource();
                Intrinsics.checkNotNullExpressionValue(source, "getSource(...)");
                arrayList.add(new ValueParameterDescriptorImpl(callableMemberDescriptor, null, i, annotations, nameGuessByFirstCharacter, type, false, false, false, null, source));
                i = i2;
            }
            return arrayList;
        }
        return CollectionsKt.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final KType _returnType$lambda$0(DescriptorKCallable descriptorKCallable) {
        KType type = KTypeSubstitutor.substitute$default(descriptorKCallable.overriddenStorage.getTypeSubstitutor(), descriptorKCallable.computeReturnType(), null, 2, null).getType();
        if (type != null) {
            return type;
        }
        FakeOverridesKt.starProjectionInTopLevelTypeIsNotPossible(descriptorKCallable.getName());
        throw new KotlinNothingValueException();
    }

    @Override // kotlin.reflect.KCallable
    public KType getReturnType() {
        KType kTypeInvoke = this._returnType.invoke();
        Intrinsics.checkNotNullExpressionValue(kTypeInvoke, "invoke(...)");
        return kTypeInvoke;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List _typeParameters$lambda$0(DescriptorKCallable descriptorKCallable) {
        List<TypeParameterDescriptor> typeParameters = descriptorKCallable.getDescriptor().getTypeParameters();
        Intrinsics.checkNotNullExpressionValue(typeParameters, "getTypeParameters(...)");
        List<TypeParameterDescriptor> list = typeParameters;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (TypeParameterDescriptor typeParameterDescriptor : list) {
            Intrinsics.checkNotNull(typeParameterDescriptor);
            arrayList.add(new KTypeParameterImpl(descriptorKCallable, typeParameterDescriptor, descriptorKCallable.overriddenStorage.getTypeSubstitutor()));
        }
        return arrayList;
    }

    @Override // kotlin.reflect.KCallable
    public List<KTypeParameter> getTypeParameters() {
        List<KTypeParameterImpl> listInvoke = this._typeParameters.invoke();
        Intrinsics.checkNotNullExpressionValue(listInvoke, "invoke(...)");
        return listInvoke;
    }

    @Override // kotlin.reflect.KCallable
    public KVisibility getVisibility() {
        DescriptorVisibility visibility = getDescriptor().getVisibility();
        Intrinsics.checkNotNullExpressionValue(visibility, "getVisibility(...)");
        return UtilKt.toKVisibility(visibility);
    }

    public final Modality getModality$kotlin_reflection() {
        Modality modality = this.overriddenStorage.getModality();
        if (modality != null) {
            return modality;
        }
        Modality modality2 = getDescriptor().getModality();
        Intrinsics.checkNotNullExpressionValue(modality2, "getModality(...)");
        return modality2;
    }

    public final boolean isPackagePrivate$kotlin_reflection() {
        return Intrinsics.areEqual(getDescriptor().getVisibility(), JavaDescriptorVisibilities.PACKAGE_VISIBILITY);
    }

    @Override // kotlin.reflect.KCallable
    public final boolean isFinal() {
        return getModality$kotlin_reflection() == Modality.FINAL;
    }

    @Override // kotlin.reflect.KCallable
    public final boolean isOpen() {
        return getModality$kotlin_reflection() == Modality.OPEN;
    }

    @Override // kotlin.reflect.KCallable
    public final boolean isAbstract() {
        return getModality$kotlin_reflection() == Modality.ABSTRACT;
    }
}
