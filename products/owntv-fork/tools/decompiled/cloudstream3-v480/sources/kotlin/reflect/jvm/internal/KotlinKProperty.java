package kotlin.reflect.jvm.internal;

import androidx.exifinterface.media.ExifInterface;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.KotlinGenericDeclarationKt;
import kotlin.reflect.KFunction;
import kotlin.reflect.KMutableProperty;
import kotlin.reflect.KParameter;
import kotlin.reflect.KProperty;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeParameter;
import kotlin.reflect.KVisibility;
import kotlin.reflect.jvm.internal.calls.Caller;
import kotlin.reflect.jvm.internal.impl.km.Attributes;
import kotlin.reflect.jvm.internal.impl.km.KmAnnotation;
import kotlin.reflect.jvm.internal.impl.km.KmProperty;
import kotlin.reflect.jvm.internal.impl.km.KmPropertyAccessorAttributes;
import kotlin.reflect.jvm.internal.impl.km.KmType;
import kotlin.reflect.jvm.internal.impl.km.KmTypeParameter;
import kotlin.reflect.jvm.internal.impl.km.KmValueParameter;
import kotlin.reflect.jvm.internal.impl.km.Modality;
import kotlin.reflect.jvm.internal.impl.km.Visibility;
import kotlin.reflect.jvm.internal.impl.km.jvm.JvmExtensionsKt;
import kotlin.reflect.jvm.internal.impl.km.jvm.JvmFieldSignature;
import kotlin.reflect.jvm.internal.impl.km.jvm.JvmMethodSignature;
import kotlin.text.Typography;

/* JADX INFO: compiled from: KotlinKProperty.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u001b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\b \u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0003UVWB)\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\n\u0010E\u001a\u0004\u0018\u00010FH\u0004J\u0014\u0010P\u001a\u0002082\b\u0010Q\u001a\u0004\u0018\u00010\tH\u0096\u0082\u0004J\n\u0010R\u001a\u00020SH\u0096\u0080\u0004J\n\u0010T\u001a\u00020\u0007H\u0096\u0080\u0004R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\b\u001a\u0004\u0018\u00010\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0015\u0010\u0016\u001a\u00020\u00078VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0011R!\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00198VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001b\u0010\u001cR!\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00198VX\u0096\u0084\n¢\u0006\f\n\u0004\b!\u0010\u001e\u001a\u0004\b \u0010\u001cR\u001b\u0010\"\u001a\u00020#8VX\u0096\u0084\n¢\u0006\f\n\u0004\b&\u0010\u001e\u001a\u0004\b$\u0010%R\u0017\u0010'\u001a\b\u0012\u0004\u0012\u00020)0(¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u001b\u0010,\u001a\b\u0012\u0004\u0012\u00020-0\u00198VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b.\u0010\u001cR\u0017\u0010/\u001a\u0004\u0018\u0001008VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b1\u00102R\u0014\u00103\u001a\u0002048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b5\u00106R\u0015\u00107\u001a\u0002088VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b7\u00109R\u0015\u0010:\u001a\u0002088VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b:\u00109R\u0015\u0010;\u001a\u0002088VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b;\u00109R\u0019\u0010<\u001a\b\u0012\u0004\u0012\u00028\u00000=X¦\u0084\b¢\u0006\u0006\u001a\u0004\b>\u0010?R\u001d\u0010@\u001a\u0004\u0018\u00010A8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\bD\u0010\u001e\u001a\u0004\bB\u0010CR\u0018\u0010G\u001a\u0006\u0012\u0002\b\u00030H8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bI\u0010JR\u001a\u0010K\u001a\b\u0012\u0002\b\u0003\u0018\u00010H8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bL\u0010JR\u001b\u0010M\u001a\b\u0012\u0004\u0012\u00020N0\u00198VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\bO\u0010\u001c¨\u0006X"}, d2 = {"Lkotlin/reflect/jvm/internal/KotlinKProperty;", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "Lkotlin/reflect/jvm/internal/KotlinKCallable;", "Lkotlin/reflect/jvm/internal/ReflectKProperty;", TtmlNode.RUBY_CONTAINER, "Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;", "signature", "", "rawBoundReceiver", "", "kmProperty", "Lkotlin/reflect/jvm/internal/impl/km/KmProperty;", "<init>", "(Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;Ljava/lang/String;Ljava/lang/Object;Lkotlin/metadata/KmProperty;)V", "getContainer", "()Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;", "getSignature", "()Ljava/lang/String;", "getRawBoundReceiver", "()Ljava/lang/Object;", "getKmProperty", "()Lkotlin/metadata/KmProperty;", "name", "getName", "allParameters", "", "Lkotlin/reflect/KParameter;", "getAllParameters", "()Ljava/util/List;", "allParameters$delegate", "Lkotlin/Lazy;", "parameters", "getParameters", "parameters$delegate", "returnType", "Lkotlin/reflect/KType;", "getReturnType", "()Lkotlin/reflect/KType;", "returnType$delegate", "typeParameterTable", "Lkotlin/Lazy;", "Lkotlin/reflect/jvm/internal/TypeParameterTable;", "getTypeParameterTable", "()Lkotlin/Lazy;", "typeParameters", "Lkotlin/reflect/KTypeParameter;", "getTypeParameters", "visibility", "Lkotlin/reflect/KVisibility;", "getVisibility", "()Lkotlin/reflect/KVisibility;", "modality", "Lkotlin/reflect/jvm/internal/impl/km/Modality;", "getModality", "()Lkotlin/metadata/Modality;", "isSuspend", "", "()Z", "isLateinit", "isConst", "getter", "Lkotlin/reflect/jvm/internal/KotlinKProperty$Getter;", "getGetter", "()Lkotlin/reflect/jvm/internal/KotlinKProperty$Getter;", "javaField", "Ljava/lang/reflect/Field;", "getJavaField", "()Ljava/lang/reflect/Field;", "javaField$delegate", "computeDelegateSource", "Ljava/lang/reflect/Member;", "caller", "Lkotlin/reflect/jvm/internal/calls/Caller;", "getCaller", "()Lkotlin/reflect/jvm/internal/calls/Caller;", "defaultCaller", "getDefaultCaller", "annotations", "", "getAnnotations", "equals", "other", "hashCode", "", "toString", "Accessor", "Getter", "Setter", "kotlin-reflection"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class KotlinKProperty<V> extends KotlinKCallable<V> implements ReflectKProperty<V> {

    /* JADX INFO: renamed from: allParameters$delegate, reason: from kotlin metadata */
    private final Lazy allParameters;
    private final KDeclarationContainerImpl container;

    /* JADX INFO: renamed from: javaField$delegate, reason: from kotlin metadata */
    private final Lazy javaField;
    private final KmProperty kmProperty;

    /* JADX INFO: renamed from: parameters$delegate, reason: from kotlin metadata */
    private final Lazy parameters;
    private final Object rawBoundReceiver;

    /* JADX INFO: renamed from: returnType$delegate, reason: from kotlin metadata */
    private final Lazy returnType;
    private final String signature;
    private final Lazy<TypeParameterTable> typeParameterTable;

    @Override // kotlin.reflect.KProperty
    public abstract Getter<V> getGetter();

    @Override // kotlin.reflect.KCallable
    public boolean isSuspend() {
        return false;
    }

    public KotlinKProperty(KDeclarationContainerImpl container, String signature, Object obj, KmProperty kmProperty) {
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(signature, "signature");
        Intrinsics.checkNotNullParameter(kmProperty, "kmProperty");
        this.container = container;
        this.signature = signature;
        this.rawBoundReceiver = obj;
        this.kmProperty = kmProperty;
        this.allParameters = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0(this) { // from class: kotlin.reflect.jvm.internal.KotlinKProperty$$Lambda$0
            private final KotlinKProperty arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return KotlinKProperty.allParameters_delegate$lambda$0(this.arg$0);
            }
        });
        this.parameters = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0(this) { // from class: kotlin.reflect.jvm.internal.KotlinKProperty$$Lambda$1
            private final KotlinKProperty arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return KotlinKProperty.parameters_delegate$lambda$0(this.arg$0);
            }
        });
        this.returnType = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0(this) { // from class: kotlin.reflect.jvm.internal.KotlinKProperty$$Lambda$2
            private final KotlinKProperty arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return KotlinKProperty.returnType_delegate$lambda$0(this.arg$0);
            }
        });
        this.typeParameterTable = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0(this) { // from class: kotlin.reflect.jvm.internal.KotlinKProperty$$Lambda$3
            private final KotlinKProperty arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return KotlinKProperty.typeParameterTable$lambda$0(this.arg$0);
            }
        });
        this.javaField = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0(this) { // from class: kotlin.reflect.jvm.internal.KotlinKProperty$$Lambda$4
            private final KotlinKProperty arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return KotlinKProperty.javaField_delegate$lambda$0(this.arg$0);
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

    @Override // kotlin.reflect.jvm.internal.ReflectKProperty
    public String getSignature() {
        return this.signature;
    }

    public GenericDeclaration default$findJavaDeclaration() {
        return KotlinGenericDeclarationKt.findMethodBySignature(getContainer(), getSignature());
    }

    @Override // kotlin.reflect.jvm.internal.ReflectKCallable
    public Object getRawBoundReceiver() {
        return this.rawBoundReceiver;
    }

    public final KmProperty getKmProperty() {
        return this.kmProperty;
    }

    @Override // kotlin.reflect.KCallable
    public String getName() {
        return this.kmProperty.getName();
    }

    @Override // kotlin.reflect.jvm.internal.ReflectKCallable
    public List<KParameter> getAllParameters() {
        return (List) this.allParameters.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List allParameters_delegate$lambda$0(KotlinKProperty kotlinKProperty) {
        return KotlinKCallableKt.computeParameters(kotlinKProperty, kotlinKProperty.kmProperty.getContextParameters(), kotlinKProperty.kmProperty.getReceiverParameterType(), CollectionsKt.emptyList(), kotlinKProperty.typeParameterTable.getValue(), true);
    }

    @Override // kotlin.reflect.KCallable
    public List<KParameter> getParameters() {
        return (List) this.parameters.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List parameters_delegate$lambda$0(KotlinKProperty kotlinKProperty) {
        if (ReflectKCallableKt.isBound(kotlinKProperty)) {
            return KotlinKCallableKt.computeParameters(kotlinKProperty, kotlinKProperty.kmProperty.getContextParameters(), kotlinKProperty.kmProperty.getReceiverParameterType(), CollectionsKt.emptyList(), kotlinKProperty.typeParameterTable.getValue(), false);
        }
        return kotlinKProperty.getAllParameters();
    }

    @Override // kotlin.reflect.KCallable
    public KType getReturnType() {
        return (KType) this.returnType.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final KType returnType_delegate$lambda$0(final KotlinKProperty kotlinKProperty) {
        KmType returnType = kotlinKProperty.kmProperty.getReturnType();
        ClassLoader classLoader = kotlinKProperty.getContainer().getJClass().getClassLoader();
        Intrinsics.checkNotNullExpressionValue(classLoader, "getClassLoader(...)");
        return ConvertFromMetadataKt.toKType(returnType, classLoader, kotlinKProperty.typeParameterTable.getValue(), ReflectKPropertyKt.isLocalDelegated(kotlinKProperty) ? null : new Function0(kotlinKProperty) { // from class: kotlin.reflect.jvm.internal.KotlinKProperty$$Lambda$5
            private final KotlinKProperty arg$0;

            {
                this.arg$0 = kotlinKProperty;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return KotlinKProperty.returnType_delegate$lambda$0$0(this.arg$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <V> Type returnType_delegate$lambda$0$0(KotlinKProperty<? extends V> kotlinKProperty) {
        return kotlinKProperty.getCaller().getReturnType();
    }

    public final Lazy<TypeParameterTable> getTypeParameterTable() {
        return this.typeParameterTable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final TypeParameterTable typeParameterTable$lambda$0(KotlinKProperty kotlinKProperty) {
        KDeclarationContainerImpl container = kotlinKProperty.getContainer();
        KClassImpl kClassImpl = container instanceof KClassImpl ? (KClassImpl) container : null;
        TypeParameterTable typeParameterTable$kotlin_reflection = kClassImpl != null ? kClassImpl.getTypeParameterTable$kotlin_reflection() : null;
        TypeParameterTable.Companion companion = TypeParameterTable.INSTANCE;
        List<KmTypeParameter> typeParameters = kotlinKProperty.kmProperty.getTypeParameters();
        KotlinKProperty kotlinKProperty2 = kotlinKProperty;
        ClassLoader classLoader = kotlinKProperty.getContainer().getJClass().getClassLoader();
        Intrinsics.checkNotNullExpressionValue(classLoader, "getClassLoader(...)");
        return companion.create(typeParameters, typeParameterTable$kotlin_reflection, kotlinKProperty2, classLoader);
    }

    @Override // kotlin.reflect.KCallable
    public List<KTypeParameter> getTypeParameters() {
        return this.typeParameterTable.getValue().getOwnTypeParameters();
    }

    @Override // kotlin.reflect.KCallable
    public KVisibility getVisibility() {
        return ConvertFromMetadataKt.toKVisibility(Attributes.getVisibility(this.kmProperty));
    }

    @Override // kotlin.reflect.jvm.internal.KotlinKCallable
    public Modality getModality() {
        return Attributes.getModality(this.kmProperty);
    }

    @Override // kotlin.reflect.KProperty
    public boolean isLateinit() {
        return Attributes.isLateinit(this.kmProperty);
    }

    @Override // kotlin.reflect.KProperty
    public boolean isConst() {
        return Attributes.isConst(this.kmProperty);
    }

    @Override // kotlin.reflect.jvm.internal.ReflectKProperty
    public Field getJavaField() {
        return (Field) this.javaField.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Field javaField_delegate$lambda$0(KotlinKProperty kotlinKProperty) {
        JvmFieldSignature fieldSignature;
        if (ReflectKPropertyKt.isLocalDelegated(kotlinKProperty) || (fieldSignature = JvmExtensionsKt.getFieldSignature(kotlinKProperty.kmProperty)) == null) {
            return null;
        }
        if (!(kotlinKProperty.getContainer() instanceof KPackageImpl)) {
            throw new IllegalArgumentException(("javaField is only supported for top-level properties for now: " + kotlinKProperty).toString());
        }
        try {
            return kotlinKProperty.getContainer().getJClass().getDeclaredField(fieldSignature.getName());
        } catch (NoSuchFieldException unused) {
            return null;
        }
    }

    protected final Member computeDelegateSource() {
        if (!Attributes.isDelegated(this.kmProperty)) {
            return null;
        }
        JvmMethodSignature syntheticMethodForDelegate = JvmExtensionsKt.getSyntheticMethodForDelegate(this.kmProperty);
        if (syntheticMethodForDelegate != null) {
            return getContainer().findMethodBySignature(syntheticMethodForDelegate.getName(), syntheticMethodForDelegate.getDescriptor());
        }
        return getJavaField();
    }

    @Override // kotlin.reflect.jvm.internal.ReflectKCallable
    public Caller<?> getCaller() {
        return getGetter().getCaller();
    }

    @Override // kotlin.reflect.jvm.internal.ReflectKCallable
    public Caller<?> getDefaultCaller() {
        return getGetter().getDefaultCaller();
    }

    @Override // kotlin.reflect.KAnnotatedElement
    public List<Annotation> getAnnotations() {
        Annotation[] annotations;
        List list;
        if (ReflectKPropertyKt.isLocalDelegated(this)) {
            List<KmAnnotation> annotations2 = this.kmProperty.getAnnotations();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(annotations2, 10));
            for (KmAnnotation kmAnnotation : annotations2) {
                ClassLoader classLoader = getContainer().getJClass().getClassLoader();
                Intrinsics.checkNotNullExpressionValue(classLoader, "getClassLoader(...)");
                arrayList.add(ConvertFromMetadataKt.toAnnotation(kmAnnotation, classLoader));
            }
            return arrayList;
        }
        if (!(getContainer() instanceof KPackageImpl)) {
            throw new IllegalArgumentException(("Annotations are only supported for top-level properties for now: " + this).toString());
        }
        JvmMethodSignature syntheticMethodForAnnotations = JvmExtensionsKt.getSyntheticMethodForAnnotations(this.kmProperty);
        if (syntheticMethodForAnnotations == null) {
            return CollectionsKt.emptyList();
        }
        Method methodFindMethodBySignature = getContainer().findMethodBySignature(syntheticMethodForAnnotations.getName(), syntheticMethodForAnnotations.getDescriptor());
        if (methodFindMethodBySignature == null || (annotations = methodFindMethodBySignature.getAnnotations()) == null || (list = ArraysKt.toList(annotations)) == null) {
            throw new KotlinReflectionInternalError("No synthetic method found: " + this);
        }
        return UtilKt.unwrapKotlinRepeatableAnnotations(list);
    }

    /* JADX INFO: compiled from: KotlinKProperty.kt */
    @Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u001b\n\u0002\b\u0002\b&\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u0001*\u0006\b\u0002\u0010\u0002 \u00012\b\u0012\u0004\u0012\u0002H\u00020\u00032\b\u0012\u0004\u0012\u0002H\u00010\u00042\b\u0012\u0004\u0012\u0002H\u00020\u0005B\u0007¢\u0006\u0004\b\u0006\u0010\u0007R\u0019\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00010\tX¦\u0084\b¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u0004\u0018\u00010\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00158VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0016\u0010\u0018\u001a\u0004\u0018\u00010\u00198VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u001b\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u0014\u0010!\u001a\u00020\"8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b#\u0010$R\u0017\u0010%\u001a\u0004\u0018\u00010&8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b'\u0010(R\u0015\u0010)\u001a\u00020*8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b)\u0010+R\u0015\u0010,\u001a\u00020*8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b,\u0010+R\u0015\u0010-\u001a\u00020*8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b-\u0010+R\u0015\u0010.\u001a\u00020*8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b.\u0010+R\u0015\u0010/\u001a\u00020*8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b/\u0010+R\u001b\u00100\u001a\b\u0012\u0004\u0012\u0002010\u001d8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b2\u0010 ¨\u00063"}, d2 = {"Lkotlin/reflect/jvm/internal/KotlinKProperty$Accessor;", "PropertyType", "ReturnType", "Lkotlin/reflect/jvm/internal/KotlinKCallable;", "Lkotlin/reflect/KProperty$Accessor;", "Lkotlin/reflect/KFunction;", "<init>", "()V", "property", "Lkotlin/reflect/jvm/internal/KotlinKProperty;", "getProperty", "()Lkotlin/reflect/jvm/internal/KotlinKProperty;", "accessor", "Lkotlin/reflect/jvm/internal/impl/km/KmPropertyAccessorAttributes;", "getAccessor", "()Lkotlin/metadata/KmPropertyAccessorAttributes;", TtmlNode.RUBY_CONTAINER, "Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;", "getContainer", "()Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;", "defaultCaller", "Lkotlin/reflect/jvm/internal/calls/Caller;", "getDefaultCaller", "()Lkotlin/reflect/jvm/internal/calls/Caller;", "rawBoundReceiver", "", "getRawBoundReceiver", "()Ljava/lang/Object;", "typeParameters", "", "Lkotlin/reflect/KTypeParameter;", "getTypeParameters", "()Ljava/util/List;", "modality", "Lkotlin/reflect/jvm/internal/impl/km/Modality;", "getModality", "()Lkotlin/metadata/Modality;", "visibility", "Lkotlin/reflect/KVisibility;", "getVisibility", "()Lkotlin/reflect/KVisibility;", "isInline", "", "()Z", "isExternal", "isOperator", "isInfix", "isSuspend", "annotations", "", "getAnnotations", "kotlin-reflection"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static abstract class Accessor<PropertyType, ReturnType> extends KotlinKCallable<ReturnType> implements KFunction<ReturnType>, KProperty.Accessor<PropertyType> {
        public abstract KmPropertyAccessorAttributes getAccessor();

        @Override // kotlin.reflect.jvm.internal.ReflectKCallable
        public Caller<?> getDefaultCaller() {
            return null;
        }

        public abstract KotlinKProperty<PropertyType> getProperty();

        @Override // kotlin.reflect.KFunction
        public boolean isInfix() {
            return false;
        }

        @Override // kotlin.reflect.KFunction
        public boolean isOperator() {
            return false;
        }

        @Override // kotlin.reflect.KCallable
        public boolean isSuspend() {
            return false;
        }

        @Override // kotlin.reflect.jvm.internal.ReflectKCallable
        public KDeclarationContainerImpl getContainer() {
            return getProperty().getContainer();
        }

        @Override // kotlin.reflect.jvm.internal.ReflectKCallable
        public Object getRawBoundReceiver() {
            return getProperty().getRawBoundReceiver();
        }

        @Override // kotlin.reflect.KCallable
        public List<KTypeParameter> getTypeParameters() {
            return getProperty().getTypeParameters();
        }

        @Override // kotlin.reflect.jvm.internal.KotlinKCallable
        public Modality getModality() {
            Modality modality;
            KmPropertyAccessorAttributes accessor = getAccessor();
            return (accessor == null || (modality = Attributes.getModality(accessor)) == null) ? getProperty().getModality() : modality;
        }

        @Override // kotlin.reflect.KCallable
        public KVisibility getVisibility() {
            Visibility visibility;
            KVisibility kVisibility;
            KmPropertyAccessorAttributes accessor = getAccessor();
            return (accessor == null || (visibility = Attributes.getVisibility(accessor)) == null || (kVisibility = ConvertFromMetadataKt.toKVisibility(visibility)) == null) ? getProperty().getVisibility() : kVisibility;
        }

        @Override // kotlin.reflect.KFunction
        public boolean isInline() {
            KmPropertyAccessorAttributes accessor = getAccessor();
            return accessor != null && Attributes.isInline(accessor);
        }

        @Override // kotlin.reflect.KFunction
        public boolean isExternal() {
            KmPropertyAccessorAttributes accessor = getAccessor();
            return accessor != null && Attributes.isExternal(accessor);
        }

        @Override // kotlin.reflect.KAnnotatedElement
        public List<Annotation> getAnnotations() {
            Annotation[] annotations;
            if (ReflectKPropertyKt.isLocalDelegated(getProperty())) {
                return CollectionsKt.emptyList();
            }
            Member memberMo10234getMember = getCaller().mo10234getMember();
            List listEmptyList = null;
            Method method = memberMo10234getMember instanceof Method ? (Method) memberMo10234getMember : null;
            if (method != null && (annotations = method.getAnnotations()) != null) {
                listEmptyList = ArraysKt.toList(annotations);
            }
            if (listEmptyList == null) {
                listEmptyList = CollectionsKt.emptyList();
            }
            return UtilKt.unwrapKotlinRepeatableAnnotations(listEmptyList);
        }
    }

    /* JADX INFO: compiled from: KotlinKProperty.kt */
    @Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b&\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00012\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u0014\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0096\u0082\u0004J\n\u0010#\u001a\u00020$H\u0096\u0080\u0004J\n\u0010%\u001a\u00020\u0007H\u0096\u0080\u0004R\u0015\u0010\u0006\u001a\u00020\u00078VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0016\u0010\n\u001a\u0004\u0018\u00010\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u001b\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0012R\u0015\u0010\u0015\u001a\u00020\u00168VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u001f\u0010\u0019\u001a\u0006\u0012\u0002\b\u00030\u001a8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001b\u0010\u001c¨\u0006&"}, d2 = {"Lkotlin/reflect/jvm/internal/KotlinKProperty$Getter;", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "Lkotlin/reflect/jvm/internal/KotlinKProperty$Accessor;", "Lkotlin/reflect/KProperty$Getter;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "accessor", "Lkotlin/reflect/jvm/internal/impl/km/KmPropertyAccessorAttributes;", "getAccessor", "()Lkotlin/metadata/KmPropertyAccessorAttributes;", "allParameters", "", "Lkotlin/reflect/KParameter;", "getAllParameters", "()Ljava/util/List;", "parameters", "getParameters", "returnType", "Lkotlin/reflect/KType;", "getReturnType", "()Lkotlin/reflect/KType;", "caller", "Lkotlin/reflect/jvm/internal/calls/Caller;", "getCaller", "()Lkotlin/reflect/jvm/internal/calls/Caller;", "caller$delegate", "Lkotlin/Lazy;", "equals", "", "other", "", "hashCode", "", "toString", "kotlin-reflection"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static abstract class Getter<V> extends Accessor<V, V> implements KProperty.Getter<V> {

        /* JADX INFO: renamed from: caller$delegate, reason: from kotlin metadata */
        private final Lazy caller = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0(this) { // from class: kotlin.reflect.jvm.internal.KotlinKProperty$Getter$$Lambda$0
            private final KotlinKProperty.Getter arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return KotlinKProperty.Getter.caller_delegate$lambda$0(this.arg$0);
            }
        });

        @Override // kotlin.reflect.KCallable
        public String getName() {
            return "<get-" + getProperty().getName() + Typography.greater;
        }

        @Override // kotlin.reflect.jvm.internal.KotlinKProperty.Accessor
        public KmPropertyAccessorAttributes getAccessor() {
            return getProperty().getKmProperty().getGetter();
        }

        @Override // kotlin.reflect.jvm.internal.ReflectKCallable
        public List<KParameter> getAllParameters() {
            return getProperty().getAllParameters();
        }

        @Override // kotlin.reflect.KCallable
        public List<KParameter> getParameters() {
            return getProperty().getParameters();
        }

        @Override // kotlin.reflect.KCallable
        public KType getReturnType() {
            return getProperty().getReturnType();
        }

        @Override // kotlin.reflect.jvm.internal.ReflectKCallable
        public Caller<?> getCaller() {
            return (Caller) this.caller.getValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Caller caller_delegate$lambda$0(Getter getter) {
            return KotlinKPropertyKt.computeCallerForAccessor(getter, true);
        }

        public boolean equals(Object other) {
            return (other instanceof Getter) && Intrinsics.areEqual(getProperty(), ((Getter) other).getProperty());
        }

        public int hashCode() {
            return getProperty().hashCode();
        }

        public String toString() {
            return "getter of " + getProperty();
        }
    }

    /* JADX INFO: compiled from: KotlinKProperty.kt */
    @Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b&\u0018\u0000*\u0004\b\u0001\u0010\u00012\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00030\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0004:\u0001)B\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\u0014\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%H\u0096\u0082\u0004J\n\u0010&\u001a\u00020'H\u0096\u0080\u0004J\n\u0010(\u001a\u00020\bH\u0096\u0080\u0004R\u0015\u0010\u0007\u001a\u00020\b8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0016\u0010\u000b\u001a\u0004\u0018\u00010\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u001b\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00110\u00108VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0013R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00110\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u0015\u0010\u0018\u001a\u00020\u00198VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u001f\u0010\u001c\u001a\u0006\u0012\u0002\b\u00030\u001d8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\u001e\u0010\u001f¨\u0006*"}, d2 = {"Lkotlin/reflect/jvm/internal/KotlinKProperty$Setter;", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "Lkotlin/reflect/jvm/internal/KotlinKProperty$Accessor;", "", "Lkotlin/reflect/KMutableProperty$Setter;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "accessor", "Lkotlin/reflect/jvm/internal/impl/km/KmPropertyAccessorAttributes;", "getAccessor", "()Lkotlin/metadata/KmPropertyAccessorAttributes;", "allParameters", "", "Lkotlin/reflect/KParameter;", "getAllParameters", "()Ljava/util/List;", "parameters", "getParameters", "setterParameter", "Lkotlin/Lazy;", "returnType", "Lkotlin/reflect/KType;", "getReturnType", "()Lkotlin/reflect/KType;", "caller", "Lkotlin/reflect/jvm/internal/calls/Caller;", "getCaller", "()Lkotlin/reflect/jvm/internal/calls/Caller;", "caller$delegate", "Lkotlin/Lazy;", "equals", "", "other", "", "hashCode", "", "toString", "DefaultSetterValueParameter", "kotlin-reflection"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static abstract class Setter<V> extends Accessor<V, Unit> implements KMutableProperty.Setter<V> {
        private final Lazy<KParameter> setterParameter = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0(this) { // from class: kotlin.reflect.jvm.internal.KotlinKProperty$Setter$$Lambda$0
            private final KotlinKProperty.Setter arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return KotlinKProperty.Setter.setterParameter$lambda$0(this.arg$0);
            }
        });

        /* JADX INFO: renamed from: caller$delegate, reason: from kotlin metadata */
        private final Lazy caller = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0(this) { // from class: kotlin.reflect.jvm.internal.KotlinKProperty$Setter$$Lambda$1
            private final KotlinKProperty.Setter arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return KotlinKProperty.Setter.caller_delegate$lambda$0(this.arg$0);
            }
        });

        @Override // kotlin.reflect.KCallable
        public String getName() {
            return "<set-" + getProperty().getName() + Typography.greater;
        }

        @Override // kotlin.reflect.jvm.internal.KotlinKProperty.Accessor
        public KmPropertyAccessorAttributes getAccessor() {
            return getProperty().getKmProperty().getSetter();
        }

        @Override // kotlin.reflect.jvm.internal.ReflectKCallable
        public List<KParameter> getAllParameters() {
            return CollectionsKt.plus((Collection<? extends KParameter>) getProperty().getAllParameters(), this.setterParameter.getValue());
        }

        @Override // kotlin.reflect.KCallable
        public List<KParameter> getParameters() {
            return CollectionsKt.plus((Collection<? extends KParameter>) getProperty().getParameters(), this.setterParameter.getValue());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final ReflectKParameter setterParameter$lambda$0(Setter setter) {
            KmValueParameter setterParameter = setter.getProperty().getKmProperty().getSetterParameter();
            if (setterParameter != null) {
                return new KotlinKParameter(setter, setterParameter, setter.getProperty().getAllParameters().size(), KParameter.Kind.VALUE, setter.getProperty().getTypeParameterTable().getValue());
            }
            return new DefaultSetterValueParameter(setter.getProperty());
        }

        @Override // kotlin.reflect.KCallable
        public KType getReturnType() {
            return StandardKTypes.INSTANCE.getUNIT_RETURN_TYPE();
        }

        @Override // kotlin.reflect.jvm.internal.ReflectKCallable
        public Caller<?> getCaller() {
            return (Caller) this.caller.getValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Caller caller_delegate$lambda$0(Setter setter) {
            return KotlinKPropertyKt.computeCallerForAccessor(setter, false);
        }

        public boolean equals(Object other) {
            return (other instanceof Setter) && Intrinsics.areEqual(getProperty(), ((Setter) other).getProperty());
        }

        public int hashCode() {
            return getProperty().hashCode();
        }

        public String toString() {
            return "setter of " + getProperty();
        }

        /* JADX INFO: compiled from: KotlinKProperty.kt */
        @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0010\u001b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0013\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0018\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0015\u0010\b\u001a\u00020\t8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\f\u001a\u0004\u0018\u00010\r8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0015\u0010\u0010\u001a\u00020\u00118VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0015\u0010\u0014\u001a\u00020\u00158VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0015\u0010\u0018\u001a\u00020\u00198VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u001aR\u0015\u0010\u001b\u001a\u00020\u00198VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001aR\u0014\u0010\u001c\u001a\u00020\u00198VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001aR\u001b\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001f8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b!\u0010\"¨\u0006#"}, d2 = {"Lkotlin/reflect/jvm/internal/KotlinKProperty$Setter$DefaultSetterValueParameter;", "Lkotlin/reflect/jvm/internal/ReflectKParameter;", "callable", "Lkotlin/reflect/jvm/internal/KotlinKProperty;", "<init>", "(Lkotlin/reflect/jvm/internal/KotlinKProperty;)V", "getCallable", "()Lkotlin/reflect/jvm/internal/KotlinKProperty;", "index", "", "getIndex", "()I", "name", "", "getName", "()Ljava/lang/String;", "type", "Lkotlin/reflect/KType;", "getType", "()Lkotlin/reflect/KType;", "kind", "Lkotlin/reflect/KParameter$Kind;", "getKind", "()Lkotlin/reflect/KParameter$Kind;", "isOptional", "", "()Z", "isVararg", "declaresDefaultValue", "getDeclaresDefaultValue", "annotations", "", "", "getAnnotations", "()Ljava/util/List;", "kotlin-reflection"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class DefaultSetterValueParameter extends ReflectKParameter {
            private final KotlinKProperty<?> callable;

            @Override // kotlin.reflect.jvm.internal.ReflectKParameter
            public boolean getDeclaresDefaultValue() {
                return false;
            }

            @Override // kotlin.reflect.KParameter
            public int getIndex() {
                return 0;
            }

            @Override // kotlin.reflect.KParameter
            public String getName() {
                return null;
            }

            @Override // kotlin.reflect.KParameter
            public boolean isOptional() {
                return false;
            }

            @Override // kotlin.reflect.KParameter
            public boolean isVararg() {
                return false;
            }

            public DefaultSetterValueParameter(KotlinKProperty<?> callable) {
                Intrinsics.checkNotNullParameter(callable, "callable");
                this.callable = callable;
            }

            @Override // kotlin.reflect.jvm.internal.ReflectKParameter
            public KotlinKProperty<?> getCallable() {
                return this.callable;
            }

            @Override // kotlin.reflect.KParameter
            public KType getType() {
                return getCallable().getReturnType();
            }

            @Override // kotlin.reflect.KParameter
            public KParameter.Kind getKind() {
                return KParameter.Kind.VALUE;
            }

            @Override // kotlin.reflect.jvm.internal.ReflectKParameter, kotlin.reflect.KAnnotatedElement
            public List<Annotation> getAnnotations() {
                return CollectionsKt.emptyList();
            }
        }
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
}
