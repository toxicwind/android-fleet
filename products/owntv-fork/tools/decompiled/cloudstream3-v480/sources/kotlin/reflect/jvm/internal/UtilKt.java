package kotlin.reflect.jvm.internal;

import androidx.compose.material3.internal.TextFieldImplKt;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.lang.annotation.Annotation;
import java.lang.annotation.Inherited;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference;
import kotlin.jvm.internal.RepeatableContainer;
import kotlin.reflect.KCallable;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KParameter;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeParameter;
import kotlin.reflect.KVisibility;
import kotlin.reflect.jvm.KClassesJvm;
import kotlin.reflect.jvm.internal.calls.AnnotationConstructorCallerKt;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType;
import kotlin.reflect.jvm.internal.impl.builtins.functions.FunctionTypeKind;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.ReflectAnnotationSource;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.ReflectJavaClassFinderKt;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.ReflectKotlinClass;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.RuntimeModuleData;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.RuntimeSourceElementFactory;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaAnnotation;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaClass;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaElement;
import kotlin.reflect.jvm.internal.impl.load.java.JvmAnnotationNames;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinarySourceElement;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import kotlin.reflect.jvm.internal.impl.resolve.constants.AnnotationValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ArrayValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ErrorValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.KClassValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.NullValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.TypedArrayValue;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationComponents;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedContainerSource;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.types.AbstractKType;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: util.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0084\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u001b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0012\u0010\u0004\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0005*\u00020\u0006H\u0000\u001a$\u0010\t\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0005*\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0000\u001a.\u0010\t\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00052\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000eH\u0002\u001a\u0014\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u0005*\u0006\u0012\u0002\b\u00030\u0005H\u0000\u001a\u000e\u0010\u0013\u001a\u0004\u0018\u00010\u0014*\u00020\u0015H\u0000\u001a\u0012\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017*\u00020\u0019H\u0000\u001a\f\u0010\u001a\u001a\u00020\u001b*\u00020\u0018H\u0000\u001a\u0014\u0010\u001a\u001a\u00020\u001b*\n\u0012\u0006\b\u0001\u0012\u00020\u00180\u001cH\u0002\u001a \u0010\u001d\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00180\u001c2\u000e\u0010\u001e\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00180\u001cH\u0002\u001a\f\u0010\u001f\u001a\u00020\u001b*\u00020\u0018H\u0000\u001a\u0018\u0010#\u001a\u00020\u001b2\u000e\u0010$\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00180\u001cH\u0002\u001a\u0018\u0010%\u001a\u00020\u001b2\u000e\u0010$\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00180\u001cH\u0002\u001a\u0016\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017*\b\u0012\u0004\u0012\u00020\u00180\u0017\u001a\u000e\u0010'\u001a\u0004\u0018\u00010\u0018*\u00020(H\u0002\u001a\u001a\u0010)\u001a\u0004\u0018\u00010**\u0006\u0012\u0002\b\u00030+2\u0006\u0010\u000f\u001a\u00020\nH\u0002\u001a\u0016\u0010,\u001a\u0004\u0018\u00010**\u00020-2\u0006\u0010\u000f\u001a\u00020\nH\u0002\u001a%\u0010.\u001a\u0002H/\"\u0004\b\u0000\u0010/2\f\u00100\u001a\b\u0012\u0004\u0012\u0002H/01H\u0080\bø\u0001\u0000¢\u0006\u0002\u00102\u001a\u0010\u00103\u001a\u0004\u0018\u000104*\u0004\u0018\u00010*H\u0000\u001a\u0014\u00105\u001a\b\u0012\u0002\b\u0003\u0018\u000106*\u0004\u0018\u00010*H\u0000\u001a\u0014\u00107\u001a\b\u0012\u0002\b\u0003\u0018\u000108*\u0004\u0018\u00010*H\u0000\u001av\u0010>\u001a\u0002H?\"\b\b\u0000\u0010@*\u00020A\"\b\b\u0001\u0010?*\u00020B2\n\u0010C\u001a\u0006\u0012\u0002\b\u00030\u00052\b\u0010D\u001a\u0004\u0018\u00010E2\u0006\u0010F\u001a\u0002H@2\u0006\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020L2\u001d\u0010M\u001a\u0019\u0012\u0004\u0012\u00020O\u0012\u0004\u0012\u0002H@\u0012\u0004\u0012\u0002H?0N¢\u0006\u0002\bPH\u0000¢\u0006\u0002\u0010Q\u001a\u0012\u0010U\u001a\u0004\u0018\u00010*2\u0006\u0010V\u001a\u00020WH\u0000\u001a;\u0010X\u001a\u0004\u0018\u00010Y*\u0006\u0012\u0002\b\u00030\u00052\u0006\u0010Z\u001a\u00020\b2\u001a\u0010[\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00050\\\"\u0006\u0012\u0002\b\u00030\u0005H\u0000¢\u0006\u0002\u0010]\u001a\u001a\u0010^\u001a\u0004\u0018\u00010_*\u0006\u0012\u0002\b\u00030\u00052\u0006\u0010Z\u001a\u00020\bH\u0000\u001a\f\u0010`\u001a\u00020\u001b*\u00020SH\u0000\u001a\u0010\u0010a\u001a\u00020b2\u0006\u0010c\u001a\u00020\bH\u0000\u001a\u0010\u0010d\u001a\u00020\b*\u0006\u0012\u0002\b\u00030\u001cH\u0000\u001a\u000e\u0010h\u001a\u0004\u0018\u00010S*\u00020SH\u0000\u001a\u001c\u0010i\u001a\u00020j*\u00020\n2\u0006\u0010c\u001a\u00020\b2\u0006\u0010k\u001a\u00020\u001bH\u0000\u001a,\u0010l\u001a\u0006\u0012\u0002\b\u00030\u0005*\u00020\n2\u0006\u0010c\u001a\u00020\b2\b\b\u0002\u0010m\u001a\u00020\u000e2\b\b\u0002\u0010n\u001a\u00020\u000eH\u0002\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000\" \u0010 \u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00180\u001c*\u00020\u00188@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"\"\u001e\u00109\u001a\u0004\u0018\u00010:*\u0006\u0012\u0002\b\u00030;8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b<\u0010=\"\u0018\u0010R\u001a\u00020\u001b*\u00020S8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bR\u0010T\"\u0018\u0010e\u001a\u00020\u001b*\u00020f8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\be\u0010g\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006o"}, d2 = {"JVM_STATIC", "Lkotlin/reflect/jvm/internal/impl/name/FqName;", "getJVM_STATIC", "()Lorg/jetbrains/kotlin/name/FqName;", "toJavaClass", "Ljava/lang/Class;", "Lkotlin/reflect/jvm/internal/impl/descriptors/ClassDescriptor;", "SUSPEND_FUNCTION_PREFIX", "", "loadClass", "Ljava/lang/ClassLoader;", "kotlinClassId", "Lkotlin/reflect/jvm/internal/impl/name/ClassId;", "arrayDimensions", "", "classLoader", "packageName", "className", "createArrayType", "toKVisibility", "Lkotlin/reflect/KVisibility;", "Lkotlin/reflect/jvm/internal/impl/descriptors/DescriptorVisibility;", "computeAnnotations", "", "", "Lkotlin/reflect/jvm/internal/impl/descriptors/annotations/Annotated;", "hasInherited", "", "Lkotlin/reflect/KClass;", "getRepeatableContainerComponentType", "containerClass", "isRepeatableContainerForNonInheritedAnnotation", "unwrappedAnnotationClass", "getUnwrappedAnnotationClass", "(Ljava/lang/annotation/Annotation;)Lkotlin/reflect/KClass;", "isKotlinRepeatableContainer", "klass", "isJavaRepeatableContainer", "unwrapKotlinRepeatableAnnotations", "toAnnotationInstance", "Lkotlin/reflect/jvm/internal/impl/descriptors/annotations/AnnotationDescriptor;", "toRuntimeValue", "", "Lkotlin/reflect/jvm/internal/impl/resolve/constants/ConstantValue;", "arrayToRuntimeValue", "Lkotlin/reflect/jvm/internal/impl/resolve/constants/ArrayValue;", "reflectionCall", "R", "block", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "asReflectFunction", "Lkotlin/reflect/jvm/internal/ReflectKFunction;", "asReflectProperty", "Lkotlin/reflect/jvm/internal/ReflectKProperty;", "asReflectCallable", "Lkotlin/reflect/jvm/internal/ReflectKCallable;", "instanceReceiverParameter", "Lkotlin/reflect/jvm/internal/impl/descriptors/ReceiverParameterDescriptor;", "Lkotlin/reflect/jvm/internal/DescriptorKCallable;", "getInstanceReceiverParameter", "(Lkotlin/reflect/jvm/internal/DescriptorKCallable;)Lorg/jetbrains/kotlin/descriptors/ReceiverParameterDescriptor;", "deserializeToDescriptor", "D", "M", "Lkotlin/reflect/jvm/internal/impl/protobuf/MessageLite;", "Lkotlin/reflect/jvm/internal/impl/descriptors/CallableDescriptor;", "moduleAnchor", "containerSource", "Lkotlin/reflect/jvm/internal/impl/serialization/deserialization/descriptors/DeserializedContainerSource;", "proto", "nameResolver", "Lkotlin/reflect/jvm/internal/impl/metadata/deserialization/NameResolver;", "typeTable", "Lkotlin/reflect/jvm/internal/impl/metadata/deserialization/TypeTable;", "metadataVersion", "Lkotlin/reflect/jvm/internal/impl/metadata/deserialization/BinaryVersion;", "createDescriptor", "Lkotlin/Function2;", "Lkotlin/reflect/jvm/internal/impl/serialization/deserialization/MemberDeserializer;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Class;Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerSource;Lorg/jetbrains/kotlin/protobuf/MessageLite;Lorg/jetbrains/kotlin/metadata/deserialization/NameResolver;Lorg/jetbrains/kotlin/metadata/deserialization/TypeTable;Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;Lkotlin/jvm/functions/Function2;)Lorg/jetbrains/kotlin/descriptors/CallableDescriptor;", "isInlineClassType", "Lkotlin/reflect/KType;", "(Lkotlin/reflect/KType;)Z", "defaultPrimitiveValue", "type", "Ljava/lang/reflect/Type;", "getDeclaredMethodOrNull", "Ljava/lang/reflect/Method;", "name", "parameterTypes", "", "(Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;", "getDeclaredFieldOrNull", "Ljava/lang/reflect/Field;", "isNullableType", "parseJvmDescriptor", "Lkotlin/reflect/jvm/internal/FunctionJvmDescriptor;", "desc", "toJvmDescriptor", "isAlwaysBoxedByCompiler", "Lkotlin/reflect/KParameter;", "(Lkotlin/reflect/KParameter;)Z", "unsubstitutedUnderlyingType", "parseAndLoadDescriptor", "Lkotlin/reflect/jvm/internal/FunctionJvmDescriptorLoaded;", "loadReturnType", "parseAndLoadType", "begin", TtmlNode.END, "kotlin-reflection"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class UtilKt {
    private static final FqName JVM_STATIC = new FqName("kotlin.jvm.JvmStatic");
    private static final String SUSPEND_FUNCTION_PREFIX = FunctionTypeKind.SuspendFunction.INSTANCE.getPackageFqName().asString() + '.' + FunctionTypeKind.SuspendFunction.INSTANCE.getClassNamePrefix();

    /* JADX INFO: compiled from: util.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PrimitiveType.values().length];
            try {
                iArr[PrimitiveType.BOOLEAN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[PrimitiveType.CHAR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[PrimitiveType.BYTE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[PrimitiveType.SHORT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[PrimitiveType.INT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[PrimitiveType.FLOAT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[PrimitiveType.LONG.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[PrimitiveType.DOUBLE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final FqName getJVM_STATIC() {
        return JVM_STATIC;
    }

    public static final Class<?> toJavaClass(ClassDescriptor classDescriptor) {
        Intrinsics.checkNotNullParameter(classDescriptor, "<this>");
        SourceElement source = classDescriptor.getSource();
        Intrinsics.checkNotNullExpressionValue(source, "getSource(...)");
        if (source instanceof KotlinJvmBinarySourceElement) {
            KotlinJvmBinaryClass binaryClass = ((KotlinJvmBinarySourceElement) source).getBinaryClass();
            Intrinsics.checkNotNull(binaryClass, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.runtime.components.ReflectKotlinClass");
            return ((ReflectKotlinClass) binaryClass).getKlass();
        }
        if (source instanceof RuntimeSourceElementFactory.RuntimeSourceElement) {
            ReflectJavaElement javaElement = ((RuntimeSourceElementFactory.RuntimeSourceElement) source).getJavaElement();
            Intrinsics.checkNotNull(javaElement, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.runtime.structure.ReflectJavaClass");
            return ((ReflectJavaClass) javaElement).getElement();
        }
        ClassId classId = DescriptorUtilsKt.getClassId(classDescriptor);
        if (classId == null) {
            return null;
        }
        return loadClass$default(ReflectClassUtilKt.getSafeClassLoader(classDescriptor.getClass()), classId, 0, 2, null);
    }

    public static /* synthetic */ Class loadClass$default(ClassLoader classLoader, ClassId classId, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return loadClass(classLoader, classId, i);
    }

    public static final Class<?> loadClass(ClassLoader classLoader, ClassId kotlinClassId, int i) {
        Intrinsics.checkNotNullParameter(classLoader, "<this>");
        Intrinsics.checkNotNullParameter(kotlinClassId, "kotlinClassId");
        FqNameUnsafe unsafe = kotlinClassId.asSingleFqName().toUnsafe();
        Integer intOrNull = StringsKt.toIntOrNull(StringsKt.substringAfter$default(unsafe.asString(), SUSPEND_FUNCTION_PREFIX, (String) null, 2, (Object) null));
        if (intOrNull != null) {
            return loadClass(classLoader, FunctionTypeKind.Function.INSTANCE.numberedClassId(intOrNull.intValue() + 1), i);
        }
        ClassId classIdMapKotlinToJava = JavaToKotlinClassMap.INSTANCE.mapKotlinToJava(unsafe);
        if (classIdMapKotlinToJava == null) {
            classIdMapKotlinToJava = kotlinClassId;
        }
        if (!Intrinsics.areEqual(classIdMapKotlinToJava, kotlinClassId)) {
            classLoader = ReflectClassUtilKt.getSafeClassLoader(Unit.class);
        }
        return loadClass(classLoader, classIdMapKotlinToJava.getPackageFqName().asString(), classIdMapKotlinToJava.getRelativeClassName().asString(), i);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private static final Class<?> loadClass(ClassLoader classLoader, String str, String str2, int i) {
        if (Intrinsics.areEqual(str, "kotlin")) {
            switch (str2.hashCode()) {
                case -901856463:
                    if (str2.equals("BooleanArray")) {
                        return boolean[].class;
                    }
                    break;
                case -763279523:
                    if (str2.equals("ShortArray")) {
                        return short[].class;
                    }
                    break;
                case -755911549:
                    if (str2.equals("CharArray")) {
                        return char[].class;
                    }
                    break;
                case -74930671:
                    if (str2.equals("ByteArray")) {
                        return byte[].class;
                    }
                    break;
                case 22374632:
                    if (str2.equals("DoubleArray")) {
                        return double[].class;
                    }
                    break;
                case 63537721:
                    if (str2.equals("Array")) {
                        return Object[].class;
                    }
                    break;
                case 601811914:
                    if (str2.equals("IntArray")) {
                        return int[].class;
                    }
                    break;
                case 948852093:
                    if (str2.equals("FloatArray")) {
                        return float[].class;
                    }
                    break;
                case 2104330525:
                    if (str2.equals("LongArray")) {
                        return long[].class;
                    }
                    break;
            }
        }
        StringBuilder sb = new StringBuilder();
        if (i > 0) {
            for (int i2 = 0; i2 < i; i2++) {
                sb.append("[");
            }
            sb.append("L");
        }
        if (str.length() > 0) {
            sb.append(str + '.');
        }
        sb.append(StringsKt.replace$default(str2, '.', Typography.dollar, false, 4, (Object) null));
        if (i > 0) {
            sb.append(";");
        }
        return ReflectJavaClassFinderKt.tryLoadClass(classLoader, sb.toString());
    }

    public static final Class<?> createArrayType(Class<?> cls) {
        Intrinsics.checkNotNullParameter(cls, "<this>");
        return Array.newInstance(cls, 0).getClass();
    }

    public static final KVisibility toKVisibility(DescriptorVisibility descriptorVisibility) {
        Intrinsics.checkNotNullParameter(descriptorVisibility, "<this>");
        if (Intrinsics.areEqual(descriptorVisibility, DescriptorVisibilities.PUBLIC)) {
            return KVisibility.PUBLIC;
        }
        if (Intrinsics.areEqual(descriptorVisibility, DescriptorVisibilities.PROTECTED)) {
            return KVisibility.PROTECTED;
        }
        if (Intrinsics.areEqual(descriptorVisibility, DescriptorVisibilities.INTERNAL)) {
            return KVisibility.INTERNAL;
        }
        if (Intrinsics.areEqual(descriptorVisibility, DescriptorVisibilities.PRIVATE) || Intrinsics.areEqual(descriptorVisibility, DescriptorVisibilities.PRIVATE_TO_THIS)) {
            return KVisibility.PRIVATE;
        }
        return null;
    }

    public static final List<Annotation> computeAnnotations(Annotated annotated) {
        Annotation annotationInstance;
        Intrinsics.checkNotNullParameter(annotated, "<this>");
        Annotations annotations = annotated.getAnnotations();
        ArrayList arrayList = new ArrayList();
        for (AnnotationDescriptor annotationDescriptor : annotations) {
            SourceElement source = annotationDescriptor.getSource();
            if (source instanceof ReflectAnnotationSource) {
                annotationInstance = ((ReflectAnnotationSource) source).getAnnotation();
            } else if (source instanceof RuntimeSourceElementFactory.RuntimeSourceElement) {
                ReflectJavaElement javaElement = ((RuntimeSourceElementFactory.RuntimeSourceElement) source).getJavaElement();
                ReflectJavaAnnotation reflectJavaAnnotation = javaElement instanceof ReflectJavaAnnotation ? (ReflectJavaAnnotation) javaElement : null;
                annotationInstance = reflectJavaAnnotation != null ? reflectJavaAnnotation.getAnnotation() : null;
            } else {
                annotationInstance = toAnnotationInstance(annotationDescriptor);
            }
            if (annotationInstance != null) {
                arrayList.add(annotationInstance);
            }
        }
        return unwrapKotlinRepeatableAnnotations(arrayList);
    }

    public static final boolean hasInherited(Annotation annotation) {
        Intrinsics.checkNotNullParameter(annotation, "<this>");
        return hasInherited((KClass<? extends Annotation>) JvmClassMappingKt.getAnnotationClass(annotation));
    }

    private static final boolean hasInherited(KClass<? extends Annotation> kClass) {
        return JvmClassMappingKt.getJavaClass((KClass) kClass).getAnnotation(Inherited.class) != null;
    }

    private static final KClass<? extends Annotation> getRepeatableContainerComponentType(KClass<? extends Annotation> kClass) {
        Class<?> componentType = JvmClassMappingKt.getJavaClass((KClass) kClass).getDeclaredMethod(ES6Iterator.VALUE_PROPERTY, null).getReturnType().getComponentType();
        Intrinsics.checkNotNull(componentType);
        KClass<? extends Annotation> kotlinClass = JvmClassMappingKt.getKotlinClass(componentType);
        Intrinsics.checkNotNull(kotlinClass, "null cannot be cast to non-null type kotlin.reflect.KClass<out kotlin.Annotation>");
        return kotlinClass;
    }

    public static final boolean isRepeatableContainerForNonInheritedAnnotation(Annotation annotation) {
        Intrinsics.checkNotNullParameter(annotation, "<this>");
        return isJavaRepeatableContainer(JvmClassMappingKt.getAnnotationClass(annotation)) && !hasInherited(getRepeatableContainerComponentType(JvmClassMappingKt.getAnnotationClass(annotation)));
    }

    public static final KClass<? extends Annotation> getUnwrappedAnnotationClass(Annotation annotation) {
        Intrinsics.checkNotNullParameter(annotation, "<this>");
        KClass<? extends Annotation> annotationClass = JvmClassMappingKt.getAnnotationClass(annotation);
        return isJavaRepeatableContainer(annotationClass) ? getRepeatableContainerComponentType(annotationClass) : annotationClass;
    }

    private static final boolean isKotlinRepeatableContainer(KClass<? extends Annotation> kClass) {
        Class javaClass = JvmClassMappingKt.getJavaClass((KClass) kClass);
        return Intrinsics.areEqual(javaClass.getSimpleName(), TextFieldImplKt.ContainerId) && javaClass.getAnnotation(RepeatableContainer.class) != null;
    }

    private static final boolean isJavaRepeatableContainer(KClass<? extends Annotation> kClass) {
        Class<?> componentType;
        Annotation annotation;
        Object objInvoke;
        Class javaClass = JvmClassMappingKt.getJavaClass((KClass) kClass);
        Method declaredMethodOrNull = getDeclaredMethodOrNull(javaClass, ES6Iterator.VALUE_PROPERTY, new Class[0]);
        if (declaredMethodOrNull == null || (componentType = declaredMethodOrNull.getReturnType().getComponentType()) == null || !componentType.isAnnotation()) {
            return false;
        }
        Annotation[] annotations = componentType.getAnnotations();
        Intrinsics.checkNotNullExpressionValue(annotations, "getAnnotations(...)");
        Annotation[] annotationArr = annotations;
        int length = annotationArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                annotation = null;
                break;
            }
            annotation = annotationArr[i];
            if (Intrinsics.areEqual(JvmClassMappingKt.getJavaClass(JvmClassMappingKt.getAnnotationClass(annotation)).getName(), JvmAnnotationNames.REPEATABLE_ANNOTATION.asString())) {
                break;
            }
            i++;
        }
        Annotation annotation2 = annotation;
        if (annotation2 == null || (objInvoke = JvmClassMappingKt.getJavaClass(JvmClassMappingKt.getAnnotationClass(annotation2)).getMethod(ES6Iterator.VALUE_PROPERTY, null).invoke(annotation2, null)) == null) {
            return false;
        }
        return Intrinsics.areEqual(javaClass, objInvoke);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final List<Annotation> unwrapKotlinRepeatableAnnotations(List<? extends Annotation> list) throws IllegalAccessException, InvocationTargetException {
        List listListOf;
        Intrinsics.checkNotNullParameter(list, "<this>");
        List<? extends Annotation> list2 = list;
        if ((list2 instanceof Collection) && list2.isEmpty()) {
            return list;
        }
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            if (Intrinsics.areEqual(JvmClassMappingKt.getJavaClass(JvmClassMappingKt.getAnnotationClass((Annotation) it.next())).getSimpleName(), TextFieldImplKt.ContainerId)) {
                ArrayList arrayList = new ArrayList();
                for (Annotation annotation : list2) {
                    KClass annotationClass = JvmClassMappingKt.getAnnotationClass(annotation);
                    if (isKotlinRepeatableContainer(annotationClass)) {
                        Object objInvoke = JvmClassMappingKt.getJavaClass(annotationClass).getDeclaredMethod(ES6Iterator.VALUE_PROPERTY, null).invoke(annotation, null);
                        Intrinsics.checkNotNull(objInvoke, "null cannot be cast to non-null type kotlin.Array<out kotlin.Annotation>");
                        listListOf = ArraysKt.asList((Annotation[]) objInvoke);
                    } else {
                        listListOf = CollectionsKt.listOf(annotation);
                    }
                    CollectionsKt.addAll(arrayList, listListOf);
                }
                return arrayList;
            }
        }
        return list;
    }

    private static final Annotation toAnnotationInstance(AnnotationDescriptor annotationDescriptor) {
        ClassDescriptor annotationClass = DescriptorUtilsKt.getAnnotationClass(annotationDescriptor);
        Class<?> javaClass = annotationClass != null ? toJavaClass(annotationClass) : null;
        if (!(javaClass instanceof Class)) {
            javaClass = null;
        }
        if (javaClass == null) {
            return null;
        }
        Set<Map.Entry<Name, ConstantValue<?>>> setEntrySet = annotationDescriptor.getAllValueArguments().entrySet();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = setEntrySet.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Name name = (Name) entry.getKey();
            ConstantValue constantValue = (ConstantValue) entry.getValue();
            ClassLoader classLoader = javaClass.getClassLoader();
            Intrinsics.checkNotNullExpressionValue(classLoader, "getClassLoader(...)");
            Object runtimeValue = toRuntimeValue(constantValue, classLoader);
            Pair pair = runtimeValue != null ? TuplesKt.to(name.asString(), runtimeValue) : null;
            if (pair != null) {
                arrayList.add(pair);
            }
        }
        return (Annotation) AnnotationConstructorCallerKt.createAnnotationInstance$default(javaClass, MapsKt.toMap(arrayList), null, 4, null);
    }

    private static final Object toRuntimeValue(ConstantValue<?> constantValue, ClassLoader classLoader) {
        if (constantValue instanceof AnnotationValue) {
            return toAnnotationInstance(((AnnotationValue) constantValue).getValue());
        }
        if (constantValue instanceof ArrayValue) {
            return arrayToRuntimeValue((ArrayValue) constantValue, classLoader);
        }
        if (constantValue instanceof EnumValue) {
            Pair<? extends ClassId, ? extends Name> value = ((EnumValue) constantValue).getValue();
            ClassId classIdComponent1 = value.component1();
            Name nameComponent2 = value.component2();
            Class clsLoadClass$default = loadClass$default(classLoader, classIdComponent1, 0, 2, null);
            if (clsLoadClass$default != null) {
                return Util.getEnumConstantByName(clsLoadClass$default, nameComponent2.asString());
            }
            return null;
        }
        if (constantValue instanceof KClassValue) {
            KClassValue.Value value2 = ((KClassValue) constantValue).getValue();
            if (value2 instanceof KClassValue.Value.NormalClass) {
                KClassValue.Value.NormalClass normalClass = (KClassValue.Value.NormalClass) value2;
                return loadClass(classLoader, normalClass.getClassId(), normalClass.getArrayDimensions());
            }
            if (!(value2 instanceof KClassValue.Value.LocalClass)) {
                throw new NoWhenBranchMatchedException();
            }
            ClassifierDescriptor classifierDescriptorMo10246getDeclarationDescriptor = ((KClassValue.Value.LocalClass) value2).getType().getConstructor().mo10246getDeclarationDescriptor();
            ClassDescriptor classDescriptor = classifierDescriptorMo10246getDeclarationDescriptor instanceof ClassDescriptor ? (ClassDescriptor) classifierDescriptorMo10246getDeclarationDescriptor : null;
            if (classDescriptor != null) {
                return toJavaClass(classDescriptor);
            }
            return null;
        }
        if ((constantValue instanceof ErrorValue) || (constantValue instanceof NullValue)) {
            return null;
        }
        return constantValue.getValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final Object arrayToRuntimeValue(ArrayValue arrayValue, ClassLoader classLoader) {
        KotlinType type;
        Class clsLoadClass$default;
        TypedArrayValue typedArrayValue = arrayValue instanceof TypedArrayValue ? (TypedArrayValue) arrayValue : null;
        if (typedArrayValue == null || (type = typedArrayValue.getType()) == null) {
            return null;
        }
        List<? extends ConstantValue<?>> value = arrayValue.getValue();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(value, 10));
        Iterator<T> it = value.iterator();
        while (it.hasNext()) {
            arrayList.add(toRuntimeValue((ConstantValue) it.next(), classLoader));
        }
        ArrayList arrayList2 = arrayList;
        PrimitiveType primitiveArrayElementType = KotlinBuiltIns.getPrimitiveArrayElementType(type);
        int i = 0;
        switch (primitiveArrayElementType == null ? -1 : WhenMappings.$EnumSwitchMapping$0[primitiveArrayElementType.ordinal()]) {
            case -1:
                if (!KotlinBuiltIns.isArray(type)) {
                    throw new IllegalStateException(("Not an array type: " + type).toString());
                }
                KotlinType type2 = ((TypeProjection) CollectionsKt.single((List) type.getArguments())).getType();
                Intrinsics.checkNotNullExpressionValue(type2, "getType(...)");
                ClassifierDescriptor classifierDescriptorMo10246getDeclarationDescriptor = type2.getConstructor().mo10246getDeclarationDescriptor();
                ClassDescriptor classDescriptor = classifierDescriptorMo10246getDeclarationDescriptor instanceof ClassDescriptor ? (ClassDescriptor) classifierDescriptorMo10246getDeclarationDescriptor : null;
                if (classDescriptor == null) {
                    throw new IllegalStateException(("Not a class type: " + type2).toString());
                }
                if (KotlinBuiltIns.isString(type2)) {
                    int size = arrayValue.getValue().size();
                    String[] strArr = new String[size];
                    while (i < size) {
                        Object obj = arrayList2.get(i);
                        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.String");
                        strArr[i] = obj;
                        i++;
                    }
                    return strArr;
                }
                if (KotlinBuiltIns.isKClass(classDescriptor)) {
                    int size2 = arrayValue.getValue().size();
                    Class[] clsArr = new Class[size2];
                    while (i < size2) {
                        Object obj2 = arrayList2.get(i);
                        Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type java.lang.Class<*>");
                        clsArr[i] = obj2;
                        i++;
                    }
                    return clsArr;
                }
                ClassId classId = DescriptorUtilsKt.getClassId(classDescriptor);
                if (classId == null || (clsLoadClass$default = loadClass$default(classLoader, classId, 0, 2, null)) == null) {
                    return null;
                }
                Object objNewInstance = Array.newInstance((Class<?>) clsLoadClass$default, arrayValue.getValue().size());
                Intrinsics.checkNotNull(objNewInstance, "null cannot be cast to non-null type kotlin.Array<in kotlin.Any?>");
                Object[] objArr = (Object[]) objNewInstance;
                int size3 = arrayList2.size();
                while (i < size3) {
                    objArr[i] = arrayList2.get(i);
                    i++;
                }
                return objArr;
            case 0:
            default:
                throw new NoWhenBranchMatchedException();
            case 1:
                int size4 = arrayValue.getValue().size();
                boolean[] zArr = new boolean[size4];
                while (i < size4) {
                    Object obj3 = arrayList2.get(i);
                    Intrinsics.checkNotNull(obj3, "null cannot be cast to non-null type kotlin.Boolean");
                    zArr[i] = ((Boolean) obj3).booleanValue();
                    i++;
                }
                return zArr;
            case 2:
                int size5 = arrayValue.getValue().size();
                char[] cArr = new char[size5];
                while (i < size5) {
                    Object obj4 = arrayList2.get(i);
                    Intrinsics.checkNotNull(obj4, "null cannot be cast to non-null type kotlin.Char");
                    cArr[i] = ((Character) obj4).charValue();
                    i++;
                }
                return cArr;
            case 3:
                int size6 = arrayValue.getValue().size();
                byte[] bArr = new byte[size6];
                while (i < size6) {
                    Object obj5 = arrayList2.get(i);
                    Intrinsics.checkNotNull(obj5, "null cannot be cast to non-null type kotlin.Byte");
                    bArr[i] = ((Byte) obj5).byteValue();
                    i++;
                }
                return bArr;
            case 4:
                int size7 = arrayValue.getValue().size();
                short[] sArr = new short[size7];
                while (i < size7) {
                    Object obj6 = arrayList2.get(i);
                    Intrinsics.checkNotNull(obj6, "null cannot be cast to non-null type kotlin.Short");
                    sArr[i] = ((Short) obj6).shortValue();
                    i++;
                }
                return sArr;
            case 5:
                int size8 = arrayValue.getValue().size();
                int[] iArr = new int[size8];
                while (i < size8) {
                    Object obj7 = arrayList2.get(i);
                    Intrinsics.checkNotNull(obj7, "null cannot be cast to non-null type kotlin.Int");
                    iArr[i] = ((Integer) obj7).intValue();
                    i++;
                }
                return iArr;
            case 6:
                int size9 = arrayValue.getValue().size();
                float[] fArr = new float[size9];
                while (i < size9) {
                    Object obj8 = arrayList2.get(i);
                    Intrinsics.checkNotNull(obj8, "null cannot be cast to non-null type kotlin.Float");
                    fArr[i] = ((Float) obj8).floatValue();
                    i++;
                }
                return fArr;
            case 7:
                int size10 = arrayValue.getValue().size();
                long[] jArr = new long[size10];
                while (i < size10) {
                    Object obj9 = arrayList2.get(i);
                    Intrinsics.checkNotNull(obj9, "null cannot be cast to non-null type kotlin.Long");
                    jArr[i] = ((Long) obj9).longValue();
                    i++;
                }
                return jArr;
            case 8:
                int size11 = arrayValue.getValue().size();
                double[] dArr = new double[size11];
                while (i < size11) {
                    Object obj10 = arrayList2.get(i);
                    Intrinsics.checkNotNull(obj10, "null cannot be cast to non-null type kotlin.Double");
                    dArr[i] = ((Double) obj10).doubleValue();
                    i++;
                }
                return dArr;
        }
    }

    public static final ReflectKFunction asReflectFunction(Object obj) {
        if (obj instanceof ReflectKFunction) {
            return (ReflectKFunction) obj;
        }
        if (obj instanceof FunctionReference) {
            KCallable kCallableCompute = ((FunctionReference) obj).compute();
            if (kCallableCompute instanceof ReflectKFunction) {
                return (ReflectKFunction) kCallableCompute;
            }
        }
        return null;
    }

    public static final ReflectKProperty<?> asReflectProperty(Object obj) {
        if (obj instanceof LazyKProperty) {
            return asReflectProperty(((LazyKProperty) obj).getDelegate());
        }
        if (obj instanceof ReflectKProperty) {
            return (ReflectKProperty) obj;
        }
        if (obj instanceof PropertyReference) {
            KCallable kCallableCompute = ((PropertyReference) obj).compute();
            if (kCallableCompute == obj) {
                kCallableCompute = null;
            }
            if (kCallableCompute != null) {
                return asReflectProperty(kCallableCompute);
            }
        }
        return null;
    }

    public static final ReflectKCallable<?> asReflectCallable(Object obj) {
        if (obj instanceof LazyKProperty) {
            return asReflectCallable(((LazyKProperty) obj).getDelegate());
        }
        if (obj instanceof ReflectKCallable) {
            return (ReflectKCallable) obj;
        }
        if (obj instanceof CallableReference) {
            KCallable kCallableCompute = ((CallableReference) obj).compute();
            if (kCallableCompute == obj) {
                kCallableCompute = null;
            }
            if (kCallableCompute != null) {
                return asReflectCallable(kCallableCompute);
            }
        }
        return null;
    }

    public static final ReceiverParameterDescriptor getInstanceReceiverParameter(DescriptorKCallable<?> descriptorKCallable) {
        Intrinsics.checkNotNullParameter(descriptorKCallable, "<this>");
        ReceiverParameterDescriptor instanceReceiverParameter = descriptorKCallable.getOverriddenStorage().getInstanceReceiverParameter();
        if (instanceReceiverParameter != null) {
            return instanceReceiverParameter;
        }
        CallableMemberDescriptor descriptor = descriptorKCallable.getDescriptor();
        if (descriptor instanceof ConstructorDescriptor) {
            return ((ConstructorDescriptor) descriptor).getDispatchReceiverParameter();
        }
        if (descriptor.getDispatchReceiverParameter() == null) {
            return null;
        }
        DeclarationDescriptor containingDeclaration = descriptor.getContainingDeclaration();
        Intrinsics.checkNotNull(containingDeclaration, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
        return ((ClassDescriptor) containingDeclaration).getThisAsReceiverParameter();
    }

    public static final <M extends MessageLite, D extends CallableDescriptor> D deserializeToDescriptor(Class<?> moduleAnchor, DeserializedContainerSource deserializedContainerSource, M proto, NameResolver nameResolver, TypeTable typeTable, BinaryVersion metadataVersion, Function2<? super MemberDeserializer, ? super M, ? extends D> createDescriptor) {
        List<ProtoBuf.TypeParameter> typeParameterList;
        Intrinsics.checkNotNullParameter(moduleAnchor, "moduleAnchor");
        Intrinsics.checkNotNullParameter(proto, "proto");
        Intrinsics.checkNotNullParameter(nameResolver, "nameResolver");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        Intrinsics.checkNotNullParameter(metadataVersion, "metadataVersion");
        Intrinsics.checkNotNullParameter(createDescriptor, "createDescriptor");
        RuntimeModuleData orCreateModule = ModuleByClassLoaderKt.getOrCreateModule(moduleAnchor);
        if (proto instanceof ProtoBuf.Function) {
            typeParameterList = ((ProtoBuf.Function) proto).getTypeParameterList();
        } else {
            if (!(proto instanceof ProtoBuf.Property)) {
                throw new IllegalStateException(("Unsupported message: " + proto).toString());
            }
            typeParameterList = ((ProtoBuf.Property) proto).getTypeParameterList();
        }
        List<ProtoBuf.TypeParameter> list = typeParameterList;
        DeserializationComponents deserialization = orCreateModule.getDeserialization();
        ModuleDescriptor module = orCreateModule.getModule();
        VersionRequirementTable empty = VersionRequirementTable.Companion.getEMPTY();
        Intrinsics.checkNotNull(list);
        return createDescriptor.invoke(new MemberDeserializer(new DeserializationContext(deserialization, nameResolver, module, typeTable, empty, metadataVersion, deserializedContainerSource, null, list)), proto);
    }

    public static final boolean isInlineClassType(KType kType) {
        Intrinsics.checkNotNullParameter(kType, "<this>");
        KClassifier classifier = kType.getClassifier();
        KClassImpl kClassImpl = classifier instanceof KClassImpl ? (KClassImpl) classifier : null;
        return kClassImpl != null && kClassImpl.isValue();
    }

    public static final Object defaultPrimitiveValue(Type type) {
        Intrinsics.checkNotNullParameter(type, "type");
        if (!(type instanceof Class)) {
            return null;
        }
        Class cls = (Class) type;
        if (!cls.isPrimitive()) {
            return null;
        }
        if (Intrinsics.areEqual(cls, Boolean.TYPE)) {
            return false;
        }
        if (Intrinsics.areEqual(cls, Character.TYPE)) {
            return (char) 0;
        }
        if (Intrinsics.areEqual(cls, Byte.TYPE)) {
            return (byte) 0;
        }
        if (Intrinsics.areEqual(cls, Short.TYPE)) {
            return (short) 0;
        }
        if (Intrinsics.areEqual(cls, Integer.TYPE)) {
            return 0;
        }
        if (Intrinsics.areEqual(cls, Float.TYPE)) {
            return Float.valueOf(0.0f);
        }
        if (Intrinsics.areEqual(cls, Long.TYPE)) {
            return 0L;
        }
        if (Intrinsics.areEqual(cls, Double.TYPE)) {
            return Double.valueOf(0.0d);
        }
        if (Intrinsics.areEqual(cls, Void.TYPE)) {
            throw new IllegalStateException("Parameter with void type is illegal");
        }
        throw new UnsupportedOperationException("Unknown primitive: " + type);
    }

    public static final Method getDeclaredMethodOrNull(Class<?> cls, String name, Class<?>... parameterTypes) {
        Intrinsics.checkNotNullParameter(cls, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(parameterTypes, "parameterTypes");
        try {
            return cls.getDeclaredMethod(name, (Class[]) Arrays.copyOf(parameterTypes, parameterTypes.length));
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    public static final Field getDeclaredFieldOrNull(Class<?> cls, String name) {
        Intrinsics.checkNotNullParameter(cls, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        try {
            return cls.getDeclaredField(name);
        } catch (NoSuchFieldException unused) {
            return null;
        }
    }

    public static final boolean isNullableType(KType kType) {
        Intrinsics.checkNotNullParameter(kType, "<this>");
        if (kType.getIsMarkedNullable()) {
            return true;
        }
        AbstractKType abstractKType = (AbstractKType) kType;
        AbstractKType abstractKTypeUpperBoundIfFlexible = abstractKType.getUpperBound();
        if (abstractKTypeUpperBoundIfFlexible != null && isNullableType(abstractKTypeUpperBoundIfFlexible)) {
            return true;
        }
        if (abstractKType.getIsDefinitelyNotNullType()) {
            return false;
        }
        KClassifier classifier = kType.getClassifier();
        if (classifier instanceof KTypeParameter) {
            List<KType> upperBounds = ((KTypeParameter) classifier).getUpperBounds();
            if (!(upperBounds instanceof Collection) || !upperBounds.isEmpty()) {
                Iterator<T> it = upperBounds.iterator();
                while (it.hasNext()) {
                    if (isNullableType((KType) it.next())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static final FunctionJvmDescriptor parseJvmDescriptor(String desc) {
        int iIndexOf$default;
        Intrinsics.checkNotNullParameter(desc, "desc");
        ArrayList arrayList = new ArrayList();
        int i = 1;
        while (desc.charAt(i) != ')') {
            int i2 = i;
            while (desc.charAt(i2) == '[') {
                i2++;
            }
            char cCharAt = desc.charAt(i2);
            if (StringsKt.contains$default((CharSequence) "VZCBSIFJD", cCharAt, false, 2, (Object) null)) {
                iIndexOf$default = i2 + 1;
            } else if (cCharAt == 'L') {
                iIndexOf$default = StringsKt.indexOf$default((CharSequence) desc, ';', i, false, 4, (Object) null) + 1;
            } else {
                throw new KotlinReflectionInternalError("Unknown type prefix in the method signature: " + desc);
            }
            String strSubstring = desc.substring(i, iIndexOf$default);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            arrayList.add(strSubstring);
            i = iIndexOf$default;
        }
        String strSubstring2 = desc.substring(i + 1);
        Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
        return new FunctionJvmDescriptor(arrayList, strSubstring2);
    }

    public static final String toJvmDescriptor(KClass<?> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        return "L" + StringsKt.replace$default(KClassesJvm.getJvmName(kClass), '.', '/', false, 4, (Object) null) + ';';
    }

    public static final boolean isAlwaysBoxedByCompiler(KParameter kParameter) {
        Intrinsics.checkNotNullParameter(kParameter, "<this>");
        if (!(kParameter instanceof ReflectKParameter) || !((ReflectKParameter) kParameter).getDeclaresDefaultValue() || !isInlineClassType(kParameter.getType())) {
            return false;
        }
        Iterator it = SequencesKt.drop(SequencesKt.generateSequence(kParameter.getType(), new Function1() { // from class: kotlin.reflect.jvm.internal.UtilKt$$Lambda$0
            @Override // kotlin.jvm.functions.Function1
            public Object invoke(Object obj) {
                return UtilKt._get_isAlwaysBoxedByCompiler_$lambda$0((KType) obj);
            }
        }), 1).iterator();
        while (it.hasNext()) {
            if (isNullableType((KType) it.next())) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final KType _get_isAlwaysBoxedByCompiler_$lambda$0(KType it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return unsubstitutedUnderlyingType(it);
    }

    public static final KType unsubstitutedUnderlyingType(KType kType) {
        Intrinsics.checkNotNullParameter(kType, "<this>");
        KClassifier classifier = kType.getClassifier();
        KClassImpl kClassImpl = classifier instanceof KClassImpl ? (KClassImpl) classifier : null;
        if (kClassImpl != null) {
            return kClassImpl.getInlineClassUnderlyingType$kotlin_reflection();
        }
        return null;
    }

    public static final FunctionJvmDescriptorLoaded parseAndLoadDescriptor(ClassLoader classLoader, String desc, boolean z) {
        Intrinsics.checkNotNullParameter(classLoader, "<this>");
        Intrinsics.checkNotNullParameter(desc, "desc");
        FunctionJvmDescriptor jvmDescriptor = parseJvmDescriptor(desc);
        List<String> parameters = jvmDescriptor.getParameters();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(parameters, 10));
        Iterator<T> it = parameters.iterator();
        while (it.hasNext()) {
            ClassLoader classLoader2 = classLoader;
            arrayList.add(parseAndLoadType$default(classLoader2, (String) it.next(), 0, 0, 6, null));
            classLoader = classLoader2;
        }
        return new FunctionJvmDescriptorLoaded(arrayList, z ? parseAndLoadType$default(classLoader, jvmDescriptor.getReturnType(), 0, 0, 6, null) : null);
    }

    static /* synthetic */ Class parseAndLoadType$default(ClassLoader classLoader, String str, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = str.length();
        }
        return parseAndLoadType(classLoader, str, i, i2);
    }

    private static final Class<?> parseAndLoadType(ClassLoader classLoader, String str, int i, int i2) throws ClassNotFoundException {
        char cCharAt = str.charAt(i);
        if (cCharAt == 'F') {
            return Float.TYPE;
        }
        if (cCharAt == 'L') {
            String strSubstring = str.substring(i + 1, i2 - 1);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            Class<?> clsLoadClass = classLoader.loadClass(StringsKt.replace$default(strSubstring, '/', '.', false, 4, (Object) null));
            Intrinsics.checkNotNullExpressionValue(clsLoadClass, "loadClass(...)");
            return clsLoadClass;
        }
        if (cCharAt == 'S') {
            return Short.TYPE;
        }
        if (cCharAt == 'V') {
            Class<?> TYPE = Void.TYPE;
            Intrinsics.checkNotNullExpressionValue(TYPE, "TYPE");
            return TYPE;
        }
        if (cCharAt == 'I') {
            return Integer.TYPE;
        }
        if (cCharAt == 'J') {
            return Long.TYPE;
        }
        if (cCharAt == 'Z') {
            return Boolean.TYPE;
        }
        if (cCharAt == '[') {
            return createArrayType(parseAndLoadType(classLoader, str, i + 1, i2));
        }
        switch (cCharAt) {
            case 'B':
                return Byte.TYPE;
            case 'C':
                return Character.TYPE;
            case 'D':
                return Double.TYPE;
            default:
                throw new KotlinReflectionInternalError("Unknown type prefix in the method signature: " + str);
        }
    }
}
