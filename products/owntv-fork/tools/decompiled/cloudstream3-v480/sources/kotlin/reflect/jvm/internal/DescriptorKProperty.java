package kotlin.reflect.jvm.internal;

import androidx.exifinterface.media.ExifInterface;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.lang.reflect.Field;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Member;
import java.lang.reflect.Type;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.KotlinGenericDeclarationKt;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.reflect.KFunction;
import kotlin.reflect.KMutableProperty;
import kotlin.reflect.KProperty;
import kotlin.reflect.KType;
import kotlin.reflect.jvm.internal.calls.Caller;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyAccessorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyGetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertySetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyGetterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertySetterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.load.java.DescriptorsJvmAbiUtil;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmProtoBufUtil;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.types.DescriptorKType;
import kotlin.text.Typography;

/* JADX INFO: compiled from: DescriptorKProperty.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0006\b \u0018\u0000 A*\u0006\b\u0000\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0004>?@AB=\b\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b\u000f\u0010\u0010B+\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\u000f\u0010\u0012B!\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0013\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b\u000f\u0010\u0014J\n\u0010\"\u001a\u0004\u0018\u00010#H\u0004J\b\u00102\u001a\u000203H\u0014J\u0014\u00109\u001a\u0002052\b\u0010:\u001a\u0004\u0018\u00010\fH\u0096\u0082\u0004J\n\u0010;\u001a\u00020<H\u0096\u0080\u0004J\n\u0010=\u001a\u00020\u0007H\u0096\u0080\u0004R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0015\u0010\u0006\u001a\u00020\u0007X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\b\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u0016\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u001d\u0010\u001c\u001a\u0004\u0018\u00010\u001d8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\u001e\u0010\u001fR\u0019\u0010$\u001a\b\u0012\u0004\u0012\u00028\u00000%X¦\u0084\b¢\u0006\u0006\u001a\u0004\b&\u0010'R\u001b\u0010\u0013\u001a\u00020\n8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b*\u0010+\u001a\u0004\b(\u0010)R\u0018\u0010,\u001a\u0006\u0012\u0002\b\u00030-8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b.\u0010/R\u001a\u00100\u001a\b\u0012\u0002\b\u0003\u0018\u00010-8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b1\u0010/R\u0015\u00104\u001a\u0002058VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b4\u00106R\u0015\u00107\u001a\u0002058VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b7\u00106R\u0015\u00108\u001a\u0002058VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b8\u00106¨\u0006B"}, d2 = {"Lkotlin/reflect/jvm/internal/DescriptorKProperty;", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "Lkotlin/reflect/jvm/internal/DescriptorKCallable;", "Lkotlin/reflect/jvm/internal/ReflectKProperty;", TtmlNode.RUBY_CONTAINER, "Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;", "name", "", "signature", "descriptorInitialValue", "Lkotlin/reflect/jvm/internal/impl/descriptors/PropertyDescriptor;", "rawBoundReceiver", "", "overriddenStorage", "Lkotlin/reflect/jvm/internal/KCallableOverriddenStorage;", "<init>", "(Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;Ljava/lang/String;Ljava/lang/String;Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;Ljava/lang/Object;Lkotlin/reflect/jvm/internal/KCallableOverriddenStorage;)V", "boundReceiver", "(Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V", "descriptor", "(Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;Lkotlin/reflect/jvm/internal/KCallableOverriddenStorage;)V", "getContainer", "()Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;", "getName", "()Ljava/lang/String;", "getSignature", "getRawBoundReceiver", "()Ljava/lang/Object;", "javaField", "Ljava/lang/reflect/Field;", "getJavaField", "()Ljava/lang/reflect/Field;", "javaField$delegate", "Lkotlin/Lazy;", "computeDelegateSource", "Ljava/lang/reflect/Member;", "getter", "Lkotlin/reflect/jvm/internal/DescriptorKProperty$Getter;", "getGetter", "()Lkotlin/reflect/jvm/internal/DescriptorKProperty$Getter;", "getDescriptor", "()Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;", "descriptor$delegate", "Lkotlin/reflect/jvm/internal/ReflectProperties$LazySoftVal;", "caller", "Lkotlin/reflect/jvm/internal/calls/Caller;", "getCaller", "()Lkotlin/reflect/jvm/internal/calls/Caller;", "defaultCaller", "getDefaultCaller", "computeReturnType", "Lkotlin/reflect/jvm/internal/types/DescriptorKType;", "isLateinit", "", "()Z", "isConst", "isSuspend", "equals", "other", "hashCode", "", "toString", "Accessor", "Getter", "Setter", "Companion", "kotlin-reflection"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class DescriptorKProperty<V> extends DescriptorKCallable<V> implements ReflectKProperty<V> {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl(DescriptorKProperty.class, "descriptor", "getDescriptor()Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;", 0)};

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Object EXTENSION_PROPERTY_DELEGATE = new Object();
    private final KDeclarationContainerImpl container;

    /* JADX INFO: renamed from: descriptor$delegate, reason: from kotlin metadata */
    private final ReflectProperties.LazySoftVal descriptor;

    /* JADX INFO: renamed from: javaField$delegate, reason: from kotlin metadata */
    private final Lazy javaField;
    private final String name;
    private final Object rawBoundReceiver;
    private final String signature;

    @Override // kotlin.reflect.KProperty
    public abstract Getter<V> getGetter();

    @Override // kotlin.reflect.KCallable
    public boolean isSuspend() {
        return false;
    }

    public GenericDeclaration default$findJavaDeclaration() {
        return KotlinGenericDeclarationKt.findMethodBySignature(getContainer(), getSignature());
    }

    private DescriptorKProperty(KDeclarationContainerImpl kDeclarationContainerImpl, String str, String str2, PropertyDescriptor propertyDescriptor, Object obj, KCallableOverriddenStorage kCallableOverriddenStorage) {
        super(kCallableOverriddenStorage);
        this.container = kDeclarationContainerImpl;
        this.name = str;
        this.signature = str2;
        this.rawBoundReceiver = obj;
        this.javaField = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0(this) { // from class: kotlin.reflect.jvm.internal.DescriptorKProperty$$Lambda$0
            private final DescriptorKProperty arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return DescriptorKProperty.javaField_delegate$lambda$0(this.arg$0);
            }
        });
        this.descriptor = ReflectProperties.lazySoft(propertyDescriptor, new Function0(this) { // from class: kotlin.reflect.jvm.internal.DescriptorKProperty$$Lambda$1
            private final DescriptorKProperty arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return DescriptorKProperty.descriptor_delegate$lambda$0(this.arg$0);
            }
        });
    }

    @Override // kotlin.jvm.internal.KotlinGenericDeclaration
    public /* bridge */ GenericDeclaration findJavaDeclaration() {
        return default$findJavaDeclaration();
    }

    @Override // kotlin.reflect.jvm.internal.ReflectKCallable
    public KDeclarationContainerImpl getContainer() {
        return this.container;
    }

    @Override // kotlin.reflect.KCallable
    public String getName() {
        return this.name;
    }

    @Override // kotlin.reflect.jvm.internal.ReflectKProperty
    public String getSignature() {
        return this.signature;
    }

    @Override // kotlin.reflect.jvm.internal.ReflectKCallable
    public Object getRawBoundReceiver() {
        return this.rawBoundReceiver;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DescriptorKProperty(KDeclarationContainerImpl container, String name, String signature, Object obj) {
        this(container, name, signature, null, obj, KCallableOverriddenStorage.INSTANCE.getEMPTY());
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(signature, "signature");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public DescriptorKProperty(KDeclarationContainerImpl container, PropertyDescriptor descriptor, KCallableOverriddenStorage overriddenStorage) {
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(overriddenStorage, "overriddenStorage");
        String strAsString = descriptor.getName().asString();
        Intrinsics.checkNotNullExpressionValue(strAsString, "asString(...)");
        this(container, strAsString, RuntimeTypeMapper.INSTANCE.mapPropertySignature(descriptor).getString(), descriptor, CallableReference.NO_RECEIVER, overriddenStorage);
    }

    @Override // kotlin.reflect.jvm.internal.ReflectKProperty
    public Field getJavaField() {
        return (Field) this.javaField.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Field javaField_delegate$lambda$0(DescriptorKProperty descriptorKProperty) {
        Class<?> enclosingClass;
        JvmPropertySignature jvmPropertySignatureMapPropertySignature = RuntimeTypeMapper.INSTANCE.mapPropertySignature(descriptorKProperty.getDescriptor());
        if (jvmPropertySignatureMapPropertySignature instanceof JvmPropertySignature.KotlinProperty) {
            JvmPropertySignature.KotlinProperty kotlinProperty = (JvmPropertySignature.KotlinProperty) jvmPropertySignatureMapPropertySignature;
            PropertyDescriptor descriptor = kotlinProperty.getDescriptor();
            JvmMemberSignature.Field jvmFieldSignature$default = JvmProtoBufUtil.getJvmFieldSignature$default(JvmProtoBufUtil.INSTANCE, kotlinProperty.getProto(), kotlinProperty.getNameResolver(), kotlinProperty.getTypeTable(), false, 8, null);
            if (jvmFieldSignature$default == null) {
                return null;
            }
            if (DescriptorsJvmAbiUtil.isPropertyWithBackingFieldInOuterClass(descriptor) || JvmProtoBufUtil.isMovedFromInterfaceCompanion(kotlinProperty.getProto())) {
                enclosingClass = descriptorKProperty.getContainer().getJClass().getEnclosingClass();
            } else {
                DeclarationDescriptor containingDeclaration = descriptor.getContainingDeclaration();
                enclosingClass = containingDeclaration instanceof ClassDescriptor ? UtilKt.toJavaClass((ClassDescriptor) containingDeclaration) : descriptorKProperty.getContainer().getJClass();
            }
            if (enclosingClass == null) {
                return null;
            }
            try {
                return enclosingClass.getDeclaredField(jvmFieldSignature$default.getName());
            } catch (NoSuchFieldException unused) {
                return null;
            }
        }
        if (jvmPropertySignatureMapPropertySignature instanceof JvmPropertySignature.JavaField) {
            return ((JvmPropertySignature.JavaField) jvmPropertySignatureMapPropertySignature).getField();
        }
        if ((jvmPropertySignatureMapPropertySignature instanceof JvmPropertySignature.JavaMethodProperty) || (jvmPropertySignatureMapPropertySignature instanceof JvmPropertySignature.MappedKotlinProperty)) {
            return null;
        }
        throw new NoWhenBranchMatchedException();
    }

    protected final Member computeDelegateSource() {
        if (!getDescriptor().isDelegated()) {
            return null;
        }
        JvmPropertySignature jvmPropertySignatureMapPropertySignature = RuntimeTypeMapper.INSTANCE.mapPropertySignature(getDescriptor());
        if (jvmPropertySignatureMapPropertySignature instanceof JvmPropertySignature.KotlinProperty) {
            JvmPropertySignature.KotlinProperty kotlinProperty = (JvmPropertySignature.KotlinProperty) jvmPropertySignatureMapPropertySignature;
            if (kotlinProperty.getSignature().hasDelegateMethod()) {
                JvmProtoBuf.JvmMethodSignature delegateMethod = kotlinProperty.getSignature().getDelegateMethod();
                if (!delegateMethod.hasName() || !delegateMethod.hasDesc()) {
                    return null;
                }
                return getContainer().findMethodBySignature(kotlinProperty.getNameResolver().getString(delegateMethod.getName()), kotlinProperty.getNameResolver().getString(delegateMethod.getDesc()));
            }
        }
        return getJavaField();
    }

    @Override // kotlin.reflect.jvm.internal.DescriptorKCallable
    public PropertyDescriptor getDescriptor() {
        T value = this.descriptor.getValue(this, $$delegatedProperties[0]);
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (PropertyDescriptor) value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PropertyDescriptor descriptor_delegate$lambda$0(DescriptorKProperty descriptorKProperty) {
        return descriptorKProperty.getContainer().findPropertyDescriptor(descriptorKProperty.getName(), descriptorKProperty.getSignature());
    }

    @Override // kotlin.reflect.jvm.internal.ReflectKCallable
    public Caller<?> getCaller() {
        return getGetter().getCaller();
    }

    @Override // kotlin.reflect.jvm.internal.ReflectKCallable
    public Caller<?> getDefaultCaller() {
        return getGetter().getDefaultCaller();
    }

    @Override // kotlin.reflect.jvm.internal.DescriptorKCallable
    protected DescriptorKType computeReturnType() {
        KotlinType returnType = getDescriptor().getReturnType();
        Intrinsics.checkNotNull(returnType);
        return new DescriptorKType(returnType, ReflectKPropertyKt.isLocalDelegated(this) ? null : new Function0(this) { // from class: kotlin.reflect.jvm.internal.DescriptorKProperty$$Lambda$2
            private final DescriptorKProperty arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return DescriptorKProperty.computeReturnType$lambda$0(this.arg$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <V> Type computeReturnType$lambda$0(DescriptorKProperty<? extends V> descriptorKProperty) {
        return descriptorKProperty.getCaller().getReturnType();
    }

    @Override // kotlin.reflect.KProperty
    public boolean isLateinit() {
        return getDescriptor().isLateInit();
    }

    @Override // kotlin.reflect.KProperty
    public boolean isConst() {
        return getDescriptor().isConst();
    }

    public boolean equals(Object other) {
        ReflectKProperty<?> reflectKPropertyAsReflectProperty = UtilKt.asReflectProperty(other);
        return reflectKPropertyAsReflectProperty != null && Intrinsics.areEqual(getContainer(), reflectKPropertyAsReflectProperty.getContainer()) && Intrinsics.areEqual(getName(), reflectKPropertyAsReflectProperty.getName()) && Intrinsics.areEqual(getSignature(), reflectKPropertyAsReflectProperty.getSignature()) && Intrinsics.areEqual(getRawBoundReceiver(), reflectKPropertyAsReflectProperty.getRawBoundReceiver());
    }

    public int hashCode() {
        return (((getContainer().hashCode() * 31) + getName().hashCode()) * 31) + getSignature().hashCode();
    }

    public String toString() {
        return ReflectionObjectRenderer.INSTANCE.renderProperty(this);
    }

    /* JADX INFO: compiled from: DescriptorKProperty.kt */
    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\b&\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u0001*\u0006\b\u0002\u0010\u0002 \u00012\b\u0012\u0004\u0012\u0002H\u00020\u00032\b\u0012\u0004\u0012\u0002H\u00010\u00042\b\u0012\u0004\u0012\u0002H\u00020\u0005B\u0007¢\u0006\u0004\b\u0006\u0010\u0007R\u0019\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00010\tX¦\u0084\b¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0012\u0010\f\u001a\u00020\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00158VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0016\u0010\u0018\u001a\u0004\u0018\u00010\u00198VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0015\u0010\u001c\u001a\u00020\u001d8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001eR\u0015\u0010\u001f\u001a\u00020\u001d8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u001eR\u0015\u0010 \u001a\u00020\u001d8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b \u0010\u001eR\u0015\u0010!\u001a\u00020\u001d8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b!\u0010\u001eR\u0015\u0010\"\u001a\u00020\u001d8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\"\u0010\u001e¨\u0006#"}, d2 = {"Lkotlin/reflect/jvm/internal/DescriptorKProperty$Accessor;", "PropertyType", "ReturnType", "Lkotlin/reflect/jvm/internal/DescriptorKCallable;", "Lkotlin/reflect/KProperty$Accessor;", "Lkotlin/reflect/KFunction;", "<init>", "()V", "property", "Lkotlin/reflect/jvm/internal/DescriptorKProperty;", "getProperty", "()Lkotlin/reflect/jvm/internal/DescriptorKProperty;", "descriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/PropertyAccessorDescriptor;", "getDescriptor", "()Lorg/jetbrains/kotlin/descriptors/PropertyAccessorDescriptor;", TtmlNode.RUBY_CONTAINER, "Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;", "getContainer", "()Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;", "defaultCaller", "Lkotlin/reflect/jvm/internal/calls/Caller;", "getDefaultCaller", "()Lkotlin/reflect/jvm/internal/calls/Caller;", "rawBoundReceiver", "", "getRawBoundReceiver", "()Ljava/lang/Object;", "isInline", "", "()Z", "isExternal", "isOperator", "isInfix", "isSuspend", "kotlin-reflection"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static abstract class Accessor<PropertyType, ReturnType> extends DescriptorKCallable<ReturnType> implements KFunction<ReturnType>, KProperty.Accessor<PropertyType> {
        @Override // kotlin.reflect.jvm.internal.ReflectKCallable
        public Caller<?> getDefaultCaller() {
            return null;
        }

        @Override // kotlin.reflect.jvm.internal.DescriptorKCallable
        public abstract PropertyAccessorDescriptor getDescriptor();

        public abstract DescriptorKProperty<PropertyType> getProperty();

        public Accessor() {
            super(KCallableOverriddenStorage.INSTANCE.getEMPTY());
        }

        @Override // kotlin.reflect.jvm.internal.ReflectKCallable
        public KDeclarationContainerImpl getContainer() {
            return getProperty().getContainer();
        }

        @Override // kotlin.reflect.jvm.internal.ReflectKCallable
        public Object getRawBoundReceiver() {
            return getProperty().getRawBoundReceiver();
        }

        @Override // kotlin.reflect.KFunction
        public boolean isInline() {
            return getDescriptor().isInline();
        }

        @Override // kotlin.reflect.KFunction
        public boolean isExternal() {
            return getDescriptor().isExternal();
        }

        @Override // kotlin.reflect.KFunction
        public boolean isOperator() {
            return getDescriptor().isOperator();
        }

        @Override // kotlin.reflect.KFunction
        public boolean isInfix() {
            return getDescriptor().isInfix();
        }

        @Override // kotlin.reflect.KCallable
        public boolean isSuspend() {
            return getDescriptor().isSuspend();
        }
    }

    /* JADX INFO: compiled from: DescriptorKProperty.kt */
    @Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b&\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00012\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00010\u000b2\u0006\u0010\f\u001a\u00020\rH\u0000¢\u0006\u0002\b\u000eJ\b\u0010\u001b\u001a\u00020\u001cH\u0014J\n\u0010\u001d\u001a\u00020\u0007H\u0096\u0080\u0004J\u0014\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!H\u0096\u0082\u0004J\n\u0010\"\u001a\u00020#H\u0096\u0080\u0004R\u0015\u0010\u0006\u001a\u00020\u00078VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u001b\u0010\u000f\u001a\u00020\u00108VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0011\u0010\u0012R\u001f\u0010\u0015\u001a\u0006\u0012\u0002\b\u00030\u00168VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0017\u0010\u0018¨\u0006$"}, d2 = {"Lkotlin/reflect/jvm/internal/DescriptorKProperty$Getter;", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "Lkotlin/reflect/jvm/internal/DescriptorKProperty$Accessor;", "Lkotlin/reflect/KProperty$Getter;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "shallowCopy", "Lkotlin/reflect/jvm/internal/DescriptorKCallable;", "overriddenStorage", "Lkotlin/reflect/jvm/internal/KCallableOverriddenStorage;", "shallowCopy$kotlin_reflection", "descriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/PropertyGetterDescriptor;", "getDescriptor", "()Lorg/jetbrains/kotlin/descriptors/PropertyGetterDescriptor;", "descriptor$delegate", "Lkotlin/reflect/jvm/internal/ReflectProperties$LazySoftVal;", "caller", "Lkotlin/reflect/jvm/internal/calls/Caller;", "getCaller", "()Lkotlin/reflect/jvm/internal/calls/Caller;", "caller$delegate", "Lkotlin/Lazy;", "computeReturnType", "Lkotlin/reflect/jvm/internal/types/DescriptorKType;", "toString", "equals", "", "other", "", "hashCode", "", "kotlin-reflection"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static abstract class Getter<V> extends Accessor<V, V> implements KProperty.Getter<V> {
        static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl(Getter.class, "descriptor", "getDescriptor()Lorg/jetbrains/kotlin/descriptors/PropertyGetterDescriptor;", 0)};

        /* JADX INFO: renamed from: descriptor$delegate, reason: from kotlin metadata */
        private final ReflectProperties.LazySoftVal descriptor = ReflectProperties.lazySoft(new Function0(this) { // from class: kotlin.reflect.jvm.internal.DescriptorKProperty$Getter$$Lambda$0
            private final DescriptorKProperty.Getter arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return DescriptorKProperty.Getter.descriptor_delegate$lambda$0(this.arg$0);
            }
        });

        /* JADX INFO: renamed from: caller$delegate, reason: from kotlin metadata */
        private final Lazy caller = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0(this) { // from class: kotlin.reflect.jvm.internal.DescriptorKProperty$Getter$$Lambda$1
            private final DescriptorKProperty.Getter arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return DescriptorKProperty.Getter.caller_delegate$lambda$0(this.arg$0);
            }
        });

        @Override // kotlin.reflect.KCallable
        public String getName() {
            return "<get-" + getProperty().getName() + Typography.greater;
        }

        @Override // kotlin.reflect.jvm.internal.DescriptorKCallable
        public final DescriptorKCallable<V> shallowCopy$kotlin_reflection(KCallableOverriddenStorage overriddenStorage) {
            Intrinsics.checkNotNullParameter(overriddenStorage, "overriddenStorage");
            throw new IllegalStateException("Property accessors can only be copied by copying the corresponding property".toString());
        }

        @Override // kotlin.reflect.jvm.internal.DescriptorKProperty.Accessor, kotlin.reflect.jvm.internal.DescriptorKCallable
        public PropertyGetterDescriptor getDescriptor() {
            T value = this.descriptor.getValue(this, $$delegatedProperties[0]);
            Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
            return (PropertyGetterDescriptor) value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final PropertyGetterDescriptor descriptor_delegate$lambda$0(Getter getter) {
            PropertyGetterDescriptor getter2 = getter.getProperty().getDescriptor().getGetter();
            if (getter2 != null) {
                return getter2;
            }
            PropertyGetterDescriptorImpl propertyGetterDescriptorImplCreateDefaultGetter = DescriptorFactory.createDefaultGetter(getter.getProperty().getDescriptor(), Annotations.Companion.getEMPTY());
            propertyGetterDescriptorImplCreateDefaultGetter.initialize(getter.getProperty().getDescriptor().getType());
            Intrinsics.checkNotNullExpressionValue(propertyGetterDescriptorImplCreateDefaultGetter, "apply(...)");
            return propertyGetterDescriptorImplCreateDefaultGetter;
        }

        @Override // kotlin.reflect.jvm.internal.ReflectKCallable
        public Caller<?> getCaller() {
            return (Caller) this.caller.getValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Caller caller_delegate$lambda$0(Getter getter) {
            return DescriptorKPropertyKt.computeCallerForAccessor(getter, true);
        }

        @Override // kotlin.reflect.jvm.internal.DescriptorKCallable
        protected DescriptorKType computeReturnType() {
            KType returnType = getProperty().getReturnType();
            Intrinsics.checkNotNull(returnType, "null cannot be cast to non-null type kotlin.reflect.jvm.internal.types.DescriptorKType");
            return (DescriptorKType) returnType;
        }

        public String toString() {
            return "getter of " + getProperty();
        }

        public boolean equals(Object other) {
            return (other instanceof Getter) && Intrinsics.areEqual(getProperty(), ((Getter) other).getProperty());
        }

        public int hashCode() {
            return getProperty().hashCode();
        }
    }

    /* JADX INFO: compiled from: DescriptorKProperty.kt */
    @Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b&\u0018\u0000*\u0004\b\u0001\u0010\u00012\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00030\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0004B\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\u001b\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\f2\u0006\u0010\r\u001a\u00020\u000eH\u0000¢\u0006\u0002\b\u000fJ\b\u0010\u001c\u001a\u00020\u001dH\u0014J\n\u0010\u001e\u001a\u00020\bH\u0096\u0080\u0004J\u0014\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0096\u0082\u0004J\n\u0010#\u001a\u00020$H\u0096\u0080\u0004R\u0015\u0010\u0007\u001a\u00020\b8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u001b\u0010\u0010\u001a\u00020\u00118VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0012\u0010\u0013R\u001f\u0010\u0016\u001a\u0006\u0012\u0002\b\u00030\u00178VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u0018\u0010\u0019¨\u0006%"}, d2 = {"Lkotlin/reflect/jvm/internal/DescriptorKProperty$Setter;", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "Lkotlin/reflect/jvm/internal/DescriptorKProperty$Accessor;", "", "Lkotlin/reflect/KMutableProperty$Setter;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "shallowCopy", "Lkotlin/reflect/jvm/internal/DescriptorKCallable;", "overriddenStorage", "Lkotlin/reflect/jvm/internal/KCallableOverriddenStorage;", "shallowCopy$kotlin_reflection", "descriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/PropertySetterDescriptor;", "getDescriptor", "()Lorg/jetbrains/kotlin/descriptors/PropertySetterDescriptor;", "descriptor$delegate", "Lkotlin/reflect/jvm/internal/ReflectProperties$LazySoftVal;", "caller", "Lkotlin/reflect/jvm/internal/calls/Caller;", "getCaller", "()Lkotlin/reflect/jvm/internal/calls/Caller;", "caller$delegate", "Lkotlin/Lazy;", "computeReturnType", "Lkotlin/reflect/jvm/internal/types/DescriptorKType;", "toString", "equals", "", "other", "", "hashCode", "", "kotlin-reflection"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static abstract class Setter<V> extends Accessor<V, Unit> implements KMutableProperty.Setter<V> {
        static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl(Setter.class, "descriptor", "getDescriptor()Lorg/jetbrains/kotlin/descriptors/PropertySetterDescriptor;", 0)};

        /* JADX INFO: renamed from: descriptor$delegate, reason: from kotlin metadata */
        private final ReflectProperties.LazySoftVal descriptor = ReflectProperties.lazySoft(new Function0(this) { // from class: kotlin.reflect.jvm.internal.DescriptorKProperty$Setter$$Lambda$0
            private final DescriptorKProperty.Setter arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return DescriptorKProperty.Setter.descriptor_delegate$lambda$0(this.arg$0);
            }
        });

        /* JADX INFO: renamed from: caller$delegate, reason: from kotlin metadata */
        private final Lazy caller = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0(this) { // from class: kotlin.reflect.jvm.internal.DescriptorKProperty$Setter$$Lambda$1
            private final DescriptorKProperty.Setter arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return DescriptorKProperty.Setter.caller_delegate$lambda$0(this.arg$0);
            }
        });

        @Override // kotlin.reflect.KCallable
        public String getName() {
            return "<set-" + getProperty().getName() + Typography.greater;
        }

        @Override // kotlin.reflect.jvm.internal.DescriptorKCallable
        public final DescriptorKCallable<Unit> shallowCopy$kotlin_reflection(KCallableOverriddenStorage overriddenStorage) {
            Intrinsics.checkNotNullParameter(overriddenStorage, "overriddenStorage");
            throw new IllegalStateException("Property accessors can only be copied by copying the corresponding property".toString());
        }

        @Override // kotlin.reflect.jvm.internal.DescriptorKProperty.Accessor, kotlin.reflect.jvm.internal.DescriptorKCallable
        public PropertySetterDescriptor getDescriptor() {
            T value = this.descriptor.getValue(this, $$delegatedProperties[0]);
            Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
            return (PropertySetterDescriptor) value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final PropertySetterDescriptor descriptor_delegate$lambda$0(Setter setter) {
            PropertySetterDescriptor setter2 = setter.getProperty().getDescriptor().getSetter();
            if (setter2 != null) {
                return setter2;
            }
            PropertySetterDescriptorImpl propertySetterDescriptorImplCreateDefaultSetter = DescriptorFactory.createDefaultSetter(setter.getProperty().getDescriptor(), Annotations.Companion.getEMPTY(), Annotations.Companion.getEMPTY());
            Intrinsics.checkNotNullExpressionValue(propertySetterDescriptorImplCreateDefaultSetter, "createDefaultSetter(...)");
            return propertySetterDescriptorImplCreateDefaultSetter;
        }

        @Override // kotlin.reflect.jvm.internal.ReflectKCallable
        public Caller<?> getCaller() {
            return (Caller) this.caller.getValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Caller caller_delegate$lambda$0(Setter setter) {
            return DescriptorKPropertyKt.computeCallerForAccessor(setter, false);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Type computeReturnType$lambda$0() {
            Class TYPE = Void.TYPE;
            Intrinsics.checkNotNullExpressionValue(TYPE, "TYPE");
            return TYPE;
        }

        @Override // kotlin.reflect.jvm.internal.DescriptorKCallable
        protected DescriptorKType computeReturnType() {
            SimpleType unitType = DescriptorUtilsKt.getBuiltIns(getDescriptor()).getUnitType();
            Intrinsics.checkNotNullExpressionValue(unitType, "getUnitType(...)");
            return new DescriptorKType(unitType, new Function0() { // from class: kotlin.reflect.jvm.internal.DescriptorKProperty$Setter$$Lambda$2
                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return DescriptorKProperty.Setter.computeReturnType$lambda$0();
                }
            });
        }

        public String toString() {
            return "setter of " + getProperty();
        }

        public boolean equals(Object other) {
            return (other instanceof Setter) && Intrinsics.areEqual(getProperty(), ((Setter) other).getProperty());
        }

        public int hashCode() {
            return getProperty().hashCode();
        }
    }

    /* JADX INFO: compiled from: DescriptorKProperty.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lkotlin/reflect/jvm/internal/DescriptorKProperty$Companion;", "", "<init>", "()V", "EXTENSION_PROPERTY_DELEGATE", "getEXTENSION_PROPERTY_DELEGATE", "()Ljava/lang/Object;", "kotlin-reflection"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Object getEXTENSION_PROPERTY_DELEGATE() {
            return DescriptorKProperty.EXTENSION_PROPERTY_DELEGATE;
        }
    }
}
