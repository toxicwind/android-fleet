package kotlin.reflect.jvm.internal;

import androidx.exifinterface.media.ExifInterface;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.KotlinGenericDeclaration;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.reflect.KCallable;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KFunction;
import kotlin.reflect.KProperty;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeParameter;
import kotlin.reflect.KVisibility;
import kotlin.reflect.full.KClasses;
import kotlin.reflect.jvm.internal.impl.SpecialJvmAnnotations;
import kotlin.reflect.jvm.internal.impl.builtins.CompanionObjectMapping;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.functions.FunctionClassDescriptor;
import kotlin.reflect.jvm.internal.impl.builtins.functions.FunctionTypeKind;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ClassDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.EmptyPackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.ReflectKotlinClass;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.RuntimeModuleData;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.Java16SealedRecordLoader;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.km.Attributes;
import kotlin.reflect.jvm.internal.impl.km.ClassKind;
import kotlin.reflect.jvm.internal.impl.km.KmClass;
import kotlin.reflect.jvm.internal.impl.km.KmClassifier;
import kotlin.reflect.jvm.internal.impl.km.KmConstructor;
import kotlin.reflect.jvm.internal.impl.km.KmProperty;
import kotlin.reflect.jvm.internal.impl.km.KmType;
import kotlin.reflect.jvm.internal.impl.km.KmTypeParameter;
import kotlin.reflect.jvm.internal.impl.km.Modality;
import kotlin.reflect.jvm.internal.impl.km.internal.ReadersKt;
import kotlin.reflect.jvm.internal.impl.km.jvm.JvmExtensionsKt;
import kotlin.reflect.jvm.internal.impl.km.jvm.KotlinClassMetadata;
import kotlin.reflect.jvm.internal.impl.load.kotlin.header.KotlinClassHeader;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoBufUtilKt;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.GivenFunctionsMemberScope;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.model.TypeConstructorMarker;
import kotlin.reflect.jvm.internal.types.DescriptorKType;
import kotlin.reflect.jvm.internal.types.KTypeSubstitutor;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: KClassImpl.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u008e\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u001b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 \u008f\u0001*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u00032\b\u0012\u0004\u0012\u0002H\u00010\u00042\u00020\u00052\u00020\u00062\u00020\u0007:\u0006\u008d\u0001\u008e\u0001\u008f\u0001B\u0015\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\t¢\u0006\u0004\b\n\u0010\u000bJ\"\u00101\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u0003030/2\u0006\u00104\u001a\u00020)2\u0006\u00105\u001a\u000206H\u0002J\u0016\u0010C\u001a\b\u0012\u0004\u0012\u00020D0/2\u0006\u0010E\u001a\u00020FH\u0016J\u0016\u0010G\u001a\b\u0012\u0004\u0012\u00020H0/2\u0006\u0010E\u001a\u00020FH\u0016J\u0012\u0010I\u001a\u0004\u0018\u00010D2\u0006\u0010J\u001a\u00020KH\u0016J\u0012\u0010L\u001a\u0004\u0018\u00010;2\u0006\u0010J\u001a\u00020KH\u0016J\u0014\u0010[\u001a\u00020\\2\b\u0010]\u001a\u0004\u0018\u00010\u0002H\u0096\u0080\u0004J\f\u0010\u0081\u0001\u001a\u00030\u0082\u0001H\u0096\u0080\u0004J\u0016\u0010\u0085\u0001\u001a\u00020\\2\t\u0010\u0086\u0001\u001a\u0004\u0018\u00010\u0002H\u0096\u0082\u0004J\u000b\u0010\u0087\u0001\u001a\u00020KH\u0096\u0080\u0004J\u000b\u0010\u0088\u0001\u001a\u00020NH\u0096\u0080\u0004J\u001b\u0010\u0089\u0001\u001a\u00020\u00142\u0006\u0010 \u001a\u00020!2\b\u0010\u008a\u0001\u001a\u00030\u008b\u0001H\u0002J\u001b\u0010\u008c\u0001\u001a\u00020\u00142\u0006\u0010 \u001a\u00020!2\b\u0010\u008a\u0001\u001a\u00030\u008b\u0001H\u0002R\u001b\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\tX\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR!\u0010\u000e\u001a\u0012\u0012\u000e\u0012\f0\u0010R\b\u0012\u0004\u0012\u00028\u00000\u00000\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u00148F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0017\u001a\u0004\u0018\u00010\u00188BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u001b\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u0014\u0010 \u001a\u00020!8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u0014\u0010$\u001a\u00020%8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b&\u0010'R\u0014\u0010(\u001a\u00020)8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b*\u0010+R\u0014\u0010,\u001a\u00020)8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b-\u0010+R\u001f\u0010.\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u0003000/8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b1\u00102R\u001a\u00107\u001a\b\u0012\u0004\u0012\u0002080/8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b9\u00102R\u001a\u0010:\u001a\b\u0012\u0004\u0012\u00020;0/8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b<\u00102R\u001a\u0010=\u001a\b\u0012\u0004\u0012\u00020>0/8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b?\u00102R\u001a\u0010@\u001a\b\u0012\u0004\u0012\u00020A0/8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bB\u00102R\u0017\u0010M\u001a\u0004\u0018\u00010N8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\bO\u0010PR\u0017\u0010Q\u001a\u0004\u0018\u00010N8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\bR\u0010PR!\u0010S\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000T0/8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\bU\u00102R\u001f\u0010V\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040/8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\bW\u00102R\u0017\u0010X\u001a\u0004\u0018\u00018\u00008VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\bY\u0010ZR\u001b\u0010^\u001a\b\u0012\u0004\u0012\u00020_0\u001c8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b`\u0010\u001fR\u0014\u0010a\u001a\u00020b8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bc\u0010dR\u001b\u0010e\u001a\b\u0012\u0004\u0012\u00020f0\u001c8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\bg\u0010\u001fR#\u0010h\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u00040\u001c8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\bi\u0010\u001fR\u0017\u0010j\u001a\u0004\u0018\u00010k8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\bl\u0010mR\u0014\u0010n\u001a\u00020o8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bp\u0010qR\u0015\u0010r\u001a\u00020\\8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\br\u0010sR\u0015\u0010t\u001a\u00020\\8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\bt\u0010sR\u0015\u0010u\u001a\u00020\\8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\bu\u0010sR\u0015\u0010v\u001a\u00020\\8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\bv\u0010sR\u0015\u0010w\u001a\u00020\\8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\bw\u0010sR\u0015\u0010x\u001a\u00020\\8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\bx\u0010sR\u0015\u0010y\u001a\u00020\\8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\by\u0010sR\u0015\u0010z\u001a\u00020\\8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\bz\u0010sR\u0015\u0010{\u001a\u00020\\8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b{\u0010sR\u0016\u0010|\u001a\u0004\u0018\u00010N8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b}\u0010PR\u0017\u0010~\u001a\u0004\u0018\u00010f8@X\u0080\u0004¢\u0006\u0007\u001a\u0005\b\u007f\u0010\u0080\u0001R\u0018\u0010\u0083\u0001\u001a\u0004\u0018\u00010N8@X\u0080\u0004¢\u0006\u0007\u001a\u0005\b\u0084\u0001\u0010P¨\u0006\u0090\u0001"}, d2 = {"Lkotlin/reflect/jvm/internal/KClassImpl;", ExifInterface.GPS_DIRECTION_TRUE, "", "Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;", "Lkotlin/reflect/KClass;", "Lkotlin/reflect/jvm/internal/KTypeParameterOwnerImpl;", "Lkotlin/reflect/jvm/internal/impl/types/model/TypeConstructorMarker;", "Lkotlin/jvm/internal/KotlinGenericDeclaration;", "jClass", "Ljava/lang/Class;", "<init>", "(Ljava/lang/Class;)V", "getJClass", "()Ljava/lang/Class;", "data", "Lkotlin/Lazy;", "Lkotlin/reflect/jvm/internal/KClassImpl$Data;", "getData", "()Lkotlin/Lazy;", "descriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/ClassDescriptor;", "getDescriptor", "()Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;", "kmClass", "Lkotlin/reflect/jvm/internal/impl/km/KmClass;", "getKmClass", "()Lkotlin/metadata/KmClass;", "annotations", "", "", "getAnnotations", "()Ljava/util/List;", "classId", "Lkotlin/reflect/jvm/internal/impl/name/ClassId;", "getClassId", "()Lorg/jetbrains/kotlin/name/ClassId;", "classKind", "Lkotlin/reflect/jvm/internal/impl/km/ClassKind;", "getClassKind$kotlin_reflection", "()Lkotlin/metadata/ClassKind;", "memberScope", "Lkotlin/reflect/jvm/internal/impl/resolve/scopes/MemberScope;", "getMemberScope$kotlin_reflection", "()Lorg/jetbrains/kotlin/resolve/scopes/MemberScope;", "staticScope", "getStaticScope$kotlin_reflection", "members", "", "Lkotlin/reflect/KCallable;", "getMembers", "()Ljava/util/Collection;", "Lkotlin/reflect/jvm/internal/DescriptorKCallable;", "scope", "belonginess", "Lkotlin/reflect/jvm/internal/KClassImpl$MemberBelonginess;", "functionsMetadata", "Lkotlin/reflect/jvm/internal/impl/km/KmFunction;", "getFunctionsMetadata", "propertiesMetadata", "Lkotlin/reflect/jvm/internal/impl/km/KmProperty;", "getPropertiesMetadata", "constructorsMetadata", "Lkotlin/reflect/jvm/internal/impl/km/KmConstructor;", "getConstructorsMetadata", "constructorDescriptors", "Lkotlin/reflect/jvm/internal/impl/descriptors/ConstructorDescriptor;", "getConstructorDescriptors", "getProperties", "Lkotlin/reflect/jvm/internal/impl/descriptors/PropertyDescriptor;", "name", "Lkotlin/reflect/jvm/internal/impl/name/Name;", "getFunctions", "Lkotlin/reflect/jvm/internal/impl/descriptors/FunctionDescriptor;", "getLocalPropertyDescriptor", "index", "", "getLocalPropertyMetadata", "simpleName", "", "getSimpleName", "()Ljava/lang/String;", "qualifiedName", "getQualifiedName", "constructors", "Lkotlin/reflect/KFunction;", "getConstructors", "nestedClasses", "getNestedClasses", "objectInstance", "getObjectInstance", "()Ljava/lang/Object;", "isInstance", "", ES6Iterator.VALUE_PROPERTY, "typeParameters", "Lkotlin/reflect/KTypeParameter;", "getTypeParameters", "typeParameterTable", "Lkotlin/reflect/jvm/internal/TypeParameterTable;", "getTypeParameterTable$kotlin_reflection", "()Lkotlin/reflect/jvm/internal/TypeParameterTable;", "supertypes", "Lkotlin/reflect/KType;", "getSupertypes", "sealedSubclasses", "getSealedSubclasses", "visibility", "Lkotlin/reflect/KVisibility;", "getVisibility", "()Lkotlin/reflect/KVisibility;", "modality", "Lkotlin/reflect/jvm/internal/impl/km/Modality;", "getModality", "()Lkotlin/metadata/Modality;", "isFinal", "()Z", "isOpen", "isAbstract", "isSealed", "isData", "isInner", "isCompanion", "isFun", "isValue", "inlineClassUnderlyingPropertyName", "getInlineClassUnderlyingPropertyName$kotlin_reflection", "inlineClassUnderlyingType", "getInlineClassUnderlyingType$kotlin_reflection", "()Lkotlin/reflect/KType;", "findJavaDeclaration", "Ljava/lang/reflect/GenericDeclaration;", "moduleName", "getModuleName$kotlin_reflection", "equals", "other", "hashCode", "toString", "createSyntheticClassOrFail", "moduleData", "Lkotlin/reflect/jvm/internal/impl/descriptors/runtime/components/RuntimeModuleData;", "createSyntheticClass", "Data", "MemberBelonginess", "Companion", "kotlin-reflection"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class KClassImpl<T> extends KDeclarationContainerImpl implements KotlinGenericDeclaration, KClass<T>, KTypeParameterOwnerImpl, TypeConstructorMarker {
    private static final Set<String> SPECIAL_JVM_ANNOTATION_NAMES;
    private final Lazy<KClassImpl<T>.Data> data;
    private final Class<T> jClass;

    /* JADX INFO: compiled from: KClassImpl.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[KotlinClassHeader.Kind.values().length];
            try {
                iArr[KotlinClassHeader.Kind.FILE_FACADE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[KotlinClassHeader.Kind.MULTIFILE_CLASS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[KotlinClassHeader.Kind.MULTIFILE_CLASS_PART.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[KotlinClassHeader.Kind.SYNTHETIC_CLASS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[KotlinClassHeader.Kind.UNKNOWN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[KotlinClassHeader.Kind.CLASS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public KClassImpl(Class<T> jClass) {
        Intrinsics.checkNotNullParameter(jClass, "jClass");
        this.jClass = jClass;
        this.data = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0(this) { // from class: kotlin.reflect.jvm.internal.KClassImpl$$Lambda$0
            private final KClassImpl arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return KClassImpl.data$lambda$0(this.arg$0);
            }
        });
    }

    @Override // kotlin.jvm.internal.ClassBasedDeclarationContainer
    public Class<T> getJClass() {
        return this.jClass;
    }

    /* JADX INFO: compiled from: KClassImpl.kt */
    @Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0010\u001b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0014\u0010\"\u001a\u00020\u00182\n\u0010#\u001a\u0006\u0012\u0002\b\u00030$H\u0002J\u000e\u0010C\u001a\b\u0012\u0004\u0012\u00020@0\u0012H\u0002J\b\u0010K\u001a\u00020 H\u0002R\u001d\u0010\u0005\u001a\u0004\u0018\u00010\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\f8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000eR!\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00128FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\u0010\u001a\u0004\b\u0014\u0010\u0015R\u001d\u0010\u0017\u001a\u0004\u0018\u00010\u00188FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u0010\u001a\u0004\b\u0019\u0010\u001aR\u001d\u0010\u001c\u001a\u0004\u0018\u00010\u00188FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\u0010\u001a\u0004\b\u001d\u0010\u001aR\u0018\u0010\u001f\u001a\u00020 *\u00020\u00138BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010!R-\u0010%\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000'0&8FX\u0086\u0084\u0002¢\u0006\u0012\n\u0004\b,\u0010\u0010\u0012\u0004\b(\u0010)\u001a\u0004\b*\u0010+R%\u0010-\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030.0&8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b0\u0010\u0010\u001a\u0004\b/\u0010+R#\u00101\u001a\u0004\u0018\u00018\u00008FX\u0086\u0084\u0002¢\u0006\u0012\n\u0004\b5\u0010\n\u0012\u0004\b2\u0010)\u001a\u0004\b3\u00104R!\u00106\u001a\b\u0012\u0004\u0012\u0002070\u00128FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b9\u0010\u0010\u001a\u0004\b8\u0010\u0015R\u001b\u0010:\u001a\u00020;8@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b>\u0010\u0010\u001a\u0004\b<\u0010=R!\u0010?\u001a\b\u0012\u0004\u0012\u00020@0\u00128FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bB\u0010\u0010\u001a\u0004\bA\u0010\u0015R)\u0010D\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00028\u00000.0\u00128FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bF\u0010\u0010\u001a\u0004\bE\u0010\u0015R\u001d\u0010G\u001a\u0004\u0018\u00010@8@X\u0080\u0084\u0002¢\u0006\f\n\u0004\bJ\u0010\n\u001a\u0004\bH\u0010IR%\u0010L\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030M0&8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bO\u0010\u0010\u001a\u0004\bN\u0010+R%\u0010P\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030M0&8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bR\u0010\u0010\u001a\u0004\bQ\u0010+R%\u0010S\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030M0&8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bU\u0010\u0010\u001a\u0004\bT\u0010+R%\u0010V\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030M0&8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bX\u0010\u0010\u001a\u0004\bW\u0010+R%\u0010Y\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030M0&8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b[\u0010\u0010\u001a\u0004\bZ\u0010+R%\u0010\\\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030M0&8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b^\u0010\u0010\u001a\u0004\b]\u0010+R%\u0010_\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030M0&8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\ba\u0010\u0010\u001a\u0004\b`\u0010+R%\u0010b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030M0&8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bd\u0010\u0010\u001a\u0004\bc\u0010+R\u001b\u0010e\u001a\u00020f8@X\u0080\u0084\u0002¢\u0006\f\n\u0004\bi\u0010\u0010\u001a\u0004\bg\u0010h¨\u0006j"}, d2 = {"Lkotlin/reflect/jvm/internal/KClassImpl$Data;", "Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl$Data;", "Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;", "<init>", "(Lkotlin/reflect/jvm/internal/KClassImpl;)V", "kmClass", "Lkotlin/reflect/jvm/internal/impl/km/KmClass;", "getKmClass", "()Lkotlin/metadata/KmClass;", "kmClass$delegate", "Lkotlin/Lazy;", "descriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/ClassDescriptor;", "getDescriptor", "()Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;", "descriptor$delegate", "Lkotlin/reflect/jvm/internal/ReflectProperties$LazySoftVal;", "annotations", "", "", "getAnnotations", "()Ljava/util/List;", "annotations$delegate", "simpleName", "", "getSimpleName", "()Ljava/lang/String;", "simpleName$delegate", "qualifiedName", "getQualifiedName", "qualifiedName$delegate", "isInheritable", "", "(Ljava/lang/annotation/Annotation;)Z", "calculateLocalClassName", "jClass", "Ljava/lang/Class;", "constructors", "", "Lkotlin/reflect/KFunction;", "getConstructors$annotations", "()V", "getConstructors", "()Ljava/util/Collection;", "constructors$delegate", "nestedClasses", "Lkotlin/reflect/KClass;", "getNestedClasses", "nestedClasses$delegate", "objectInstance", "getObjectInstance$annotations", "getObjectInstance", "()Ljava/lang/Object;", "objectInstance$delegate", "typeParameters", "Lkotlin/reflect/KTypeParameter;", "getTypeParameters", "typeParameters$delegate", "typeParameterTable", "Lkotlin/reflect/jvm/internal/TypeParameterTable;", "getTypeParameterTable$kotlin_reflection", "()Lkotlin/reflect/jvm/internal/TypeParameterTable;", "typeParameterTable$delegate", "supertypes", "Lkotlin/reflect/KType;", "getSupertypes", "supertypes$delegate", "computeLegacySupertypes", "sealedSubclasses", "getSealedSubclasses", "sealedSubclasses$delegate", "inlineClassUnderlyingType", "getInlineClassUnderlyingType$kotlin_reflection", "()Lkotlin/reflect/KType;", "inlineClassUnderlyingType$delegate", "useK1ImplementationForFakeOverrides", "declaredNonStaticMembers", "Lkotlin/reflect/jvm/internal/DescriptorKCallable;", "getDeclaredNonStaticMembers", "declaredNonStaticMembers$delegate", "declaredStaticMembers", "getDeclaredStaticMembers", "declaredStaticMembers$delegate", "inheritedNonStaticMembers_k1Impl", "getInheritedNonStaticMembers_k1Impl", "inheritedNonStaticMembers_k1Impl$delegate", "inheritedStaticMembers_k1Impl", "getInheritedStaticMembers_k1Impl", "inheritedStaticMembers_k1Impl$delegate", "allNonStaticMembers", "getAllNonStaticMembers", "allNonStaticMembers$delegate", "allStaticMembers", "getAllStaticMembers", "allStaticMembers$delegate", "declaredMembers", "getDeclaredMembers", "declaredMembers$delegate", "allMembers", "getAllMembers", "allMembers$delegate", "fakeOverrideMembers", "Lkotlin/reflect/jvm/internal/FakeOverrideMembers;", "getFakeOverrideMembers$kotlin_reflection", "()Lkotlin/reflect/jvm/internal/FakeOverrideMembers;", "fakeOverrideMembers$delegate", "kotlin-reflection"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public final class Data extends KDeclarationContainerImpl.Data {
        static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl(Data.class, "descriptor", "getDescriptor()Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;", 0), new PropertyReference1Impl(Data.class, "annotations", "getAnnotations()Ljava/util/List;", 0), new PropertyReference1Impl(Data.class, "simpleName", "getSimpleName()Ljava/lang/String;", 0), new PropertyReference1Impl(Data.class, "qualifiedName", "getQualifiedName()Ljava/lang/String;", 0), new PropertyReference1Impl(Data.class, "constructors", "getConstructors()Ljava/util/Collection;", 0), new PropertyReference1Impl(Data.class, "nestedClasses", "getNestedClasses()Ljava/util/Collection;", 0), new PropertyReference1Impl(Data.class, "typeParameters", "getTypeParameters()Ljava/util/List;", 0), new PropertyReference1Impl(Data.class, "typeParameterTable", "getTypeParameterTable$kotlin_reflection()Lkotlin/reflect/jvm/internal/TypeParameterTable;", 0), new PropertyReference1Impl(Data.class, "supertypes", "getSupertypes()Ljava/util/List;", 0), new PropertyReference1Impl(Data.class, "sealedSubclasses", "getSealedSubclasses()Ljava/util/List;", 0), new PropertyReference1Impl(Data.class, "declaredNonStaticMembers", "getDeclaredNonStaticMembers()Ljava/util/Collection;", 0), new PropertyReference1Impl(Data.class, "declaredStaticMembers", "getDeclaredStaticMembers()Ljava/util/Collection;", 0), new PropertyReference1Impl(Data.class, "inheritedNonStaticMembers_k1Impl", "getInheritedNonStaticMembers_k1Impl()Ljava/util/Collection;", 0), new PropertyReference1Impl(Data.class, "inheritedStaticMembers_k1Impl", "getInheritedStaticMembers_k1Impl()Ljava/util/Collection;", 0), new PropertyReference1Impl(Data.class, "allNonStaticMembers", "getAllNonStaticMembers()Ljava/util/Collection;", 0), new PropertyReference1Impl(Data.class, "allStaticMembers", "getAllStaticMembers()Ljava/util/Collection;", 0), new PropertyReference1Impl(Data.class, "declaredMembers", "getDeclaredMembers()Ljava/util/Collection;", 0), new PropertyReference1Impl(Data.class, "allMembers", "getAllMembers()Ljava/util/Collection;", 0), new PropertyReference1Impl(Data.class, "fakeOverrideMembers", "getFakeOverrideMembers$kotlin_reflection()Lkotlin/reflect/jvm/internal/FakeOverrideMembers;", 0)};

        /* JADX INFO: renamed from: allMembers$delegate, reason: from kotlin metadata */
        private final ReflectProperties.LazySoftVal allMembers;

        /* JADX INFO: renamed from: allNonStaticMembers$delegate, reason: from kotlin metadata */
        private final ReflectProperties.LazySoftVal allNonStaticMembers;

        /* JADX INFO: renamed from: allStaticMembers$delegate, reason: from kotlin metadata */
        private final ReflectProperties.LazySoftVal allStaticMembers;

        /* JADX INFO: renamed from: annotations$delegate, reason: from kotlin metadata */
        private final ReflectProperties.LazySoftVal annotations;

        /* JADX INFO: renamed from: constructors$delegate, reason: from kotlin metadata */
        private final ReflectProperties.LazySoftVal constructors;

        /* JADX INFO: renamed from: declaredMembers$delegate, reason: from kotlin metadata */
        private final ReflectProperties.LazySoftVal declaredMembers;

        /* JADX INFO: renamed from: declaredNonStaticMembers$delegate, reason: from kotlin metadata */
        private final ReflectProperties.LazySoftVal declaredNonStaticMembers;

        /* JADX INFO: renamed from: declaredStaticMembers$delegate, reason: from kotlin metadata */
        private final ReflectProperties.LazySoftVal declaredStaticMembers;

        /* JADX INFO: renamed from: descriptor$delegate, reason: from kotlin metadata */
        private final ReflectProperties.LazySoftVal descriptor;

        /* JADX INFO: renamed from: fakeOverrideMembers$delegate, reason: from kotlin metadata */
        private final ReflectProperties.LazySoftVal fakeOverrideMembers;

        /* JADX INFO: renamed from: inheritedNonStaticMembers_k1Impl$delegate, reason: from kotlin metadata */
        private final ReflectProperties.LazySoftVal inheritedNonStaticMembers_k1Impl;

        /* JADX INFO: renamed from: inheritedStaticMembers_k1Impl$delegate, reason: from kotlin metadata */
        private final ReflectProperties.LazySoftVal inheritedStaticMembers_k1Impl;

        /* JADX INFO: renamed from: inlineClassUnderlyingType$delegate, reason: from kotlin metadata */
        private final Lazy inlineClassUnderlyingType;

        /* JADX INFO: renamed from: kmClass$delegate, reason: from kotlin metadata */
        private final Lazy kmClass;

        /* JADX INFO: renamed from: nestedClasses$delegate, reason: from kotlin metadata */
        private final ReflectProperties.LazySoftVal nestedClasses;

        /* JADX INFO: renamed from: objectInstance$delegate, reason: from kotlin metadata */
        private final Lazy objectInstance;

        /* JADX INFO: renamed from: qualifiedName$delegate, reason: from kotlin metadata */
        private final ReflectProperties.LazySoftVal qualifiedName;

        /* JADX INFO: renamed from: sealedSubclasses$delegate, reason: from kotlin metadata */
        private final ReflectProperties.LazySoftVal sealedSubclasses;

        /* JADX INFO: renamed from: simpleName$delegate, reason: from kotlin metadata */
        private final ReflectProperties.LazySoftVal simpleName;

        /* JADX INFO: renamed from: supertypes$delegate, reason: from kotlin metadata */
        private final ReflectProperties.LazySoftVal supertypes;

        /* JADX INFO: renamed from: typeParameterTable$delegate, reason: from kotlin metadata */
        private final ReflectProperties.LazySoftVal typeParameterTable;

        /* JADX INFO: renamed from: typeParameters$delegate, reason: from kotlin metadata */
        private final ReflectProperties.LazySoftVal typeParameters;

        public Data() {
            super();
            this.kmClass = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0(KClassImpl.this, this) { // from class: kotlin.reflect.jvm.internal.KClassImpl$Data$$Lambda$0
                private final KClassImpl arg$0;
                private final KClassImpl.Data arg$1;

                {
                    this.arg$0 = kClassImpl;
                    this.arg$1 = this;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return KClassImpl.Data.kmClass_delegate$lambda$0(this.arg$0, this.arg$1);
                }
            });
            this.descriptor = ReflectProperties.lazySoft(new Function0(KClassImpl.this) { // from class: kotlin.reflect.jvm.internal.KClassImpl$Data$$Lambda$1
                private final KClassImpl arg$0;

                {
                    this.arg$0 = kClassImpl;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return KClassImpl.Data.descriptor_delegate$lambda$0(this.arg$0);
                }
            });
            this.annotations = ReflectProperties.lazySoft(new Function0(KClassImpl.this, this) { // from class: kotlin.reflect.jvm.internal.KClassImpl$Data$$Lambda$2
                private final KClassImpl arg$0;
                private final KClassImpl.Data arg$1;

                {
                    this.arg$0 = kClassImpl;
                    this.arg$1 = this;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return KClassImpl.Data.annotations_delegate$lambda$0(this.arg$0, this.arg$1);
                }
            });
            this.simpleName = ReflectProperties.lazySoft(new Function0(KClassImpl.this, this) { // from class: kotlin.reflect.jvm.internal.KClassImpl$Data$$Lambda$3
                private final KClassImpl arg$0;
                private final KClassImpl.Data arg$1;

                {
                    this.arg$0 = kClassImpl;
                    this.arg$1 = this;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return KClassImpl.Data.simpleName_delegate$lambda$0(this.arg$0, this.arg$1);
                }
            });
            this.qualifiedName = ReflectProperties.lazySoft(new Function0(KClassImpl.this) { // from class: kotlin.reflect.jvm.internal.KClassImpl$Data$$Lambda$4
                private final KClassImpl arg$0;

                {
                    this.arg$0 = kClassImpl;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return KClassImpl.Data.qualifiedName_delegate$lambda$0(this.arg$0);
                }
            });
            this.constructors = ReflectProperties.lazySoft(new Function0(KClassImpl.this, this) { // from class: kotlin.reflect.jvm.internal.KClassImpl$Data$$Lambda$5
                private final KClassImpl arg$0;
                private final KClassImpl.Data arg$1;

                {
                    this.arg$0 = kClassImpl;
                    this.arg$1 = this;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return KClassImpl.Data.constructors_delegate$lambda$0(this.arg$0, this.arg$1);
                }
            });
            this.nestedClasses = ReflectProperties.lazySoft(new Function0(this, KClassImpl.this) { // from class: kotlin.reflect.jvm.internal.KClassImpl$Data$$Lambda$6
                private final KClassImpl.Data arg$0;
                private final KClassImpl arg$1;

                {
                    this.arg$0 = this;
                    this.arg$1 = kClassImpl;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return KClassImpl.Data.nestedClasses_delegate$lambda$0(this.arg$0, this.arg$1);
                }
            });
            this.objectInstance = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0(this, KClassImpl.this) { // from class: kotlin.reflect.jvm.internal.KClassImpl$Data$$Lambda$7
                private final KClassImpl.Data arg$0;
                private final KClassImpl arg$1;

                {
                    this.arg$0 = this;
                    this.arg$1 = kClassImpl;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return KClassImpl.Data.objectInstance_delegate$lambda$0(this.arg$0, this.arg$1);
                }
            });
            this.typeParameters = ReflectProperties.lazySoft(new Function0(this, KClassImpl.this) { // from class: kotlin.reflect.jvm.internal.KClassImpl$Data$$Lambda$8
                private final KClassImpl.Data arg$0;
                private final KClassImpl arg$1;

                {
                    this.arg$0 = this;
                    this.arg$1 = kClassImpl;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return KClassImpl.Data.typeParameters_delegate$lambda$0(this.arg$0, this.arg$1);
                }
            });
            this.typeParameterTable = ReflectProperties.lazySoft(new Function0(this, KClassImpl.this) { // from class: kotlin.reflect.jvm.internal.KClassImpl$Data$$Lambda$9
                private final KClassImpl.Data arg$0;
                private final KClassImpl arg$1;

                {
                    this.arg$0 = this;
                    this.arg$1 = kClassImpl;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return KClassImpl.Data.typeParameterTable_delegate$lambda$0(this.arg$0, this.arg$1);
                }
            });
            this.supertypes = ReflectProperties.lazySoft(new Function0(KClassImpl.this, this) { // from class: kotlin.reflect.jvm.internal.KClassImpl$Data$$Lambda$10
                private final KClassImpl arg$0;
                private final KClassImpl.Data arg$1;

                {
                    this.arg$0 = kClassImpl;
                    this.arg$1 = this;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return KClassImpl.Data.supertypes_delegate$lambda$0(this.arg$0, this.arg$1);
                }
            });
            this.sealedSubclasses = ReflectProperties.lazySoft(new Function0(KClassImpl.this, this) { // from class: kotlin.reflect.jvm.internal.KClassImpl$Data$$Lambda$11
                private final KClassImpl arg$0;
                private final KClassImpl.Data arg$1;

                {
                    this.arg$0 = kClassImpl;
                    this.arg$1 = this;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return KClassImpl.Data.sealedSubclasses_delegate$lambda$0(this.arg$0, this.arg$1);
                }
            });
            this.inlineClassUnderlyingType = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0(this, KClassImpl.this) { // from class: kotlin.reflect.jvm.internal.KClassImpl$Data$$Lambda$12
                private final KClassImpl.Data arg$0;
                private final KClassImpl arg$1;

                {
                    this.arg$0 = this;
                    this.arg$1 = kClassImpl;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return KClassImpl.Data.inlineClassUnderlyingType_delegate$lambda$0(this.arg$0, this.arg$1);
                }
            });
            this.declaredNonStaticMembers = ReflectProperties.lazySoft(new Function0(KClassImpl.this) { // from class: kotlin.reflect.jvm.internal.KClassImpl$Data$$Lambda$13
                private final KClassImpl arg$0;

                {
                    this.arg$0 = kClassImpl;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return KClassImpl.Data.declaredNonStaticMembers_delegate$lambda$0(this.arg$0);
                }
            });
            this.declaredStaticMembers = ReflectProperties.lazySoft(new Function0(KClassImpl.this) { // from class: kotlin.reflect.jvm.internal.KClassImpl$Data$$Lambda$14
                private final KClassImpl arg$0;

                {
                    this.arg$0 = kClassImpl;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return KClassImpl.Data.declaredStaticMembers_delegate$lambda$0(this.arg$0);
                }
            });
            this.inheritedNonStaticMembers_k1Impl = ReflectProperties.lazySoft(new Function0(KClassImpl.this) { // from class: kotlin.reflect.jvm.internal.KClassImpl$Data$$Lambda$15
                private final KClassImpl arg$0;

                {
                    this.arg$0 = kClassImpl;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return KClassImpl.Data.inheritedNonStaticMembers_k1Impl_delegate$lambda$0(this.arg$0);
                }
            });
            this.inheritedStaticMembers_k1Impl = ReflectProperties.lazySoft(new Function0(KClassImpl.this) { // from class: kotlin.reflect.jvm.internal.KClassImpl$Data$$Lambda$16
                private final KClassImpl arg$0;

                {
                    this.arg$0 = kClassImpl;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return KClassImpl.Data.inheritedStaticMembers_k1Impl_delegate$lambda$0(this.arg$0);
                }
            });
            this.allNonStaticMembers = ReflectProperties.lazySoft(new Function0(this) { // from class: kotlin.reflect.jvm.internal.KClassImpl$Data$$Lambda$17
                private final KClassImpl.Data arg$0;

                {
                    this.arg$0 = this;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return KClassImpl.Data.allNonStaticMembers_delegate$lambda$0(this.arg$0);
                }
            });
            this.allStaticMembers = ReflectProperties.lazySoft(new Function0(this) { // from class: kotlin.reflect.jvm.internal.KClassImpl$Data$$Lambda$18
                private final KClassImpl.Data arg$0;

                {
                    this.arg$0 = this;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return KClassImpl.Data.allStaticMembers_delegate$lambda$0(this.arg$0);
                }
            });
            this.declaredMembers = ReflectProperties.lazySoft(new Function0(this) { // from class: kotlin.reflect.jvm.internal.KClassImpl$Data$$Lambda$19
                private final KClassImpl.Data arg$0;

                {
                    this.arg$0 = this;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return KClassImpl.Data.declaredMembers_delegate$lambda$0(this.arg$0);
                }
            });
            this.allMembers = ReflectProperties.lazySoft(new Function0(this, KClassImpl.this) { // from class: kotlin.reflect.jvm.internal.KClassImpl$Data$$Lambda$20
                private final KClassImpl.Data arg$0;
                private final KClassImpl arg$1;

                {
                    this.arg$0 = this;
                    this.arg$1 = kClassImpl;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return KClassImpl.Data.allMembers_delegate$lambda$0(this.arg$0, this.arg$1);
                }
            });
            this.fakeOverrideMembers = ReflectProperties.lazySoft(new Function0(KClassImpl.this) { // from class: kotlin.reflect.jvm.internal.KClassImpl$Data$$Lambda$21
                private final KClassImpl arg$0;

                {
                    this.arg$0 = kClassImpl;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return FakeOverridesKt.computeFakeOverrideMembers(this.arg$0);
                }
            });
        }

        public final KmClass getKmClass() {
            return (KmClass) this.kmClass.getValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final KmClass kmClass_delegate$lambda$0(KClassImpl kClassImpl, Data data) {
            if (SystemPropertiesKt.getLoadMetadataDirectly()) {
                Metadata metadata = (Metadata) kClassImpl.getJClass().getAnnotation(Metadata.class);
                if (metadata != null) {
                    KotlinClassMetadata lenient = KotlinClassMetadata.Companion.readLenient(metadata);
                    KotlinClassMetadata.Class r8 = lenient instanceof KotlinClassMetadata.Class ? (KotlinClassMetadata.Class) lenient : null;
                    if (r8 != null) {
                        return r8.getKmClass();
                    }
                }
                return null;
            }
            ClassDescriptor descriptor = data.getDescriptor();
            if (descriptor instanceof FunctionClassDescriptor) {
                FunctionClassDescriptor functionClassDescriptor = (FunctionClassDescriptor) descriptor;
                if (!(functionClassDescriptor.getFunctionTypeKind() instanceof FunctionTypeKind.Function)) {
                    throw new KotlinReflectionInternalError("Unsupported function type kind: " + functionClassDescriptor.getFunctionTypeKind() + " (" + descriptor + ')');
                }
                return BuiltinsKt.createFunctionKmClass(functionClassDescriptor.getArity());
            }
            DeserializedClassDescriptor deserializedClassDescriptor = descriptor instanceof DeserializedClassDescriptor ? (DeserializedClassDescriptor) descriptor : null;
            if (deserializedClassDescriptor != null) {
                return ReadersKt.toKmClass$default(deserializedClassDescriptor.getClassProto(), deserializedClassDescriptor.getC().getNameResolver(), false, null, 6, null);
            }
            return null;
        }

        public final ClassDescriptor getDescriptor() {
            T value = this.descriptor.getValue(this, $$delegatedProperties[0]);
            Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
            return (ClassDescriptor) value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final ClassDescriptor descriptor_delegate$lambda$0(KClassImpl kClassImpl) {
            ClassDescriptor classDescriptorFindClassAcrossModuleDependencies;
            ClassId classId = kClassImpl.getClassId();
            RuntimeModuleData moduleData = kClassImpl.getData().getValue().getModuleData();
            ModuleDescriptor module = moduleData.getModule();
            if (classId.isLocal() && kClassImpl.getJClass().isAnnotationPresent(Metadata.class)) {
                classDescriptorFindClassAcrossModuleDependencies = moduleData.getDeserialization().deserializeClass(classId);
            } else {
                classDescriptorFindClassAcrossModuleDependencies = FindClassInModuleKt.findClassAcrossModuleDependencies(module, classId);
            }
            return classDescriptorFindClassAcrossModuleDependencies == null ? kClassImpl.createSyntheticClassOrFail(classId, moduleData) : classDescriptorFindClassAcrossModuleDependencies;
        }

        public final List<Annotation> getAnnotations() {
            T value = this.annotations.getValue(this, $$delegatedProperties[1]);
            Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
            return (List) value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Code duplicated, block: B:14:0x0059  */
        /* JADX WARN: Code duplicated, block: B:16:0x0068  */
        /* JADX WARN: Code duplicated, block: B:20:0x0073  */
        public static final List annotations_delegate$lambda$0(KClassImpl kClassImpl, Data data) {
            ArrayList arrayListReversed;
            KClass<? extends Annotation> unwrappedAnnotationClass;
            Class cls;
            Annotation[] annotations = kClassImpl.getJClass().getAnnotations();
            if (annotations.length == kClassImpl.getJClass().getDeclaredAnnotations().length) {
                Intrinsics.checkNotNull(annotations);
                ArrayList arrayList = new ArrayList();
                for (Annotation annotation : annotations) {
                    if (!KClassImpl.SPECIAL_JVM_ANNOTATION_NAMES.contains(JvmClassMappingKt.getJavaClass(JvmClassMappingKt.getAnnotationClass(annotation)).getName())) {
                        arrayList.add(annotation);
                    }
                }
                arrayListReversed = arrayList;
            } else {
                ArrayList arrayList2 = new ArrayList();
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                Class<T> jClass = kClassImpl.getJClass();
                do {
                    Annotation[] declaredAnnotations = jClass.getDeclaredAnnotations();
                    int length = declaredAnnotations.length;
                    while (true) {
                        length--;
                        if (-1 >= length) {
                            break;
                        }
                        Annotation annotation2 = declaredAnnotations[length];
                        if (!KClassImpl.SPECIAL_JVM_ANNOTATION_NAMES.contains(JvmClassMappingKt.getJavaClass(JvmClassMappingKt.getAnnotationClass(annotation2)).getName())) {
                            if (jClass != kClassImpl.getJClass()) {
                                Intrinsics.checkNotNull(annotation2);
                                if (data.isInheritable(annotation2)) {
                                    Intrinsics.checkNotNull(annotation2);
                                    unwrappedAnnotationClass = UtilKt.getUnwrappedAnnotationClass(annotation2);
                                    cls = (Class) linkedHashMap.get(unwrappedAnnotationClass);
                                    if (cls == null) {
                                        linkedHashMap.put(unwrappedAnnotationClass, jClass);
                                    }
                                    if (cls != null || Intrinsics.areEqual(cls, jClass)) {
                                        arrayList2.add(annotation2);
                                    }
                                }
                            } else {
                                Intrinsics.checkNotNull(annotation2);
                                unwrappedAnnotationClass = UtilKt.getUnwrappedAnnotationClass(annotation2);
                                cls = (Class) linkedHashMap.get(unwrappedAnnotationClass);
                                if (cls == null) {
                                    linkedHashMap.put(unwrappedAnnotationClass, jClass);
                                }
                                if (cls != null) {
                                    arrayList2.add(annotation2);
                                } else {
                                    arrayList2.add(annotation2);
                                }
                            }
                        }
                    }
                    jClass = jClass.getSuperclass();
                } while (jClass != null);
                arrayListReversed = CollectionsKt.reversed(arrayList2);
            }
            return UtilKt.unwrapKotlinRepeatableAnnotations(arrayListReversed);
        }

        public final String getSimpleName() {
            return (String) this.simpleName.getValue(this, $$delegatedProperties[2]);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String simpleName_delegate$lambda$0(KClassImpl kClassImpl, Data data) {
            if (kClassImpl.getJClass().isAnonymousClass()) {
                return null;
            }
            ClassId classId = kClassImpl.getClassId();
            if (classId.isLocal()) {
                return data.calculateLocalClassName(kClassImpl.getJClass());
            }
            String strAsString = classId.getShortClassName().asString();
            Intrinsics.checkNotNullExpressionValue(strAsString, "asString(...)");
            return strAsString;
        }

        public final String getQualifiedName() {
            return (String) this.qualifiedName.getValue(this, $$delegatedProperties[3]);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String qualifiedName_delegate$lambda$0(KClassImpl kClassImpl) {
            if (kClassImpl.getJClass().isAnonymousClass()) {
                return null;
            }
            ClassId classId = kClassImpl.getClassId();
            if (classId.isLocal()) {
                return null;
            }
            return classId.asSingleFqName().asString();
        }

        private final boolean isInheritable(Annotation annotation) {
            return UtilKt.hasInherited(annotation) && !UtilKt.isRepeatableContainerForNonInheritedAnnotation(annotation);
        }

        private final String calculateLocalClassName(Class<?> jClass) {
            String simpleName = jClass.getSimpleName();
            Method enclosingMethod = jClass.getEnclosingMethod();
            if (enclosingMethod != null) {
                Intrinsics.checkNotNull(simpleName);
                return StringsKt.substringAfter$default(simpleName, enclosingMethod.getName() + Typography.dollar, (String) null, 2, (Object) null);
            }
            Constructor<?> enclosingConstructor = jClass.getEnclosingConstructor();
            if (enclosingConstructor != null) {
                Intrinsics.checkNotNull(simpleName);
                return StringsKt.substringAfter$default(simpleName, enclosingConstructor.getName() + Typography.dollar, (String) null, 2, (Object) null);
            }
            Intrinsics.checkNotNull(simpleName);
            return StringsKt.substringAfter$default(simpleName, Typography.dollar, (String) null, 2, (Object) null);
        }

        public final Collection<KFunction<T>> getConstructors() {
            T value = this.constructors.getValue(this, $$delegatedProperties[4]);
            Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
            return (Collection) value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final List constructors_delegate$lambda$0(KClassImpl kClassImpl, Data data) {
            if (kClassImpl.getClassKind$kotlin_reflection() == ClassKind.INTERFACE || kClassImpl.getClassKind$kotlin_reflection() == ClassKind.OBJECT || kClassImpl.getClassKind$kotlin_reflection() == ClassKind.COMPANION_OBJECT || kClassImpl.getClassKind$kotlin_reflection() == ClassKind.ENUM_ENTRY) {
                return CollectionsKt.emptyList();
            }
            if (SystemPropertiesKt.getUseK1Implementation() || data.getKmClass() == null) {
                Collection<ConstructorDescriptor> constructorDescriptors = kClassImpl.getConstructorDescriptors();
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(constructorDescriptors, 10));
                Iterator<T> it = constructorDescriptors.iterator();
                while (it.hasNext()) {
                    arrayList.add(new DescriptorKFunction(kClassImpl, (ConstructorDescriptor) it.next(), null, 4, null));
                }
                return arrayList;
            }
            Collection<KmConstructor> constructorsMetadata = kClassImpl.getConstructorsMetadata();
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(constructorsMetadata, 10));
            Iterator<T> it2 = constructorsMetadata.iterator();
            while (it2.hasNext()) {
                KotlinKFunction kotlinKFunctionCreateUnboundConstructor = ConvertFromMetadataKt.createUnboundConstructor((KmConstructor) it2.next(), kClassImpl);
                Intrinsics.checkNotNull(kotlinKFunctionCreateUnboundConstructor, "null cannot be cast to non-null type kotlin.reflect.KFunction<T of kotlin.reflect.jvm.internal.KClassImpl>");
                arrayList2.add(kotlinKFunctionCreateUnboundConstructor);
            }
            return arrayList2;
        }

        public final Collection<KClass<?>> getNestedClasses() {
            T value = this.nestedClasses.getValue(this, $$delegatedProperties[5]);
            Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
            return (Collection) value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final List nestedClasses_delegate$lambda$0(Data data, KClassImpl kClassImpl) {
            KmClass kmClass = data.getKmClass();
            if (kmClass != null) {
                ClassId classId = ConvertFromMetadataKt.toClassId(kmClass.getName());
                ClassLoader safeClassLoader = ReflectClassUtilKt.getSafeClassLoader(kClassImpl.getJClass());
                List<String> nestedClasses = kmClass.getNestedClasses();
                ArrayList arrayList = new ArrayList();
                Iterator<T> it = nestedClasses.iterator();
                while (it.hasNext()) {
                    Name nameIdentifier = Name.identifier((String) it.next());
                    Intrinsics.checkNotNullExpressionValue(nameIdentifier, "identifier(...)");
                    Class clsLoadClass$default = UtilKt.loadClass$default(safeClassLoader, classId.createNestedClassId(nameIdentifier), 0, 2, null);
                    KClass kotlinClass = clsLoadClass$default != null ? JvmClassMappingKt.getKotlinClass(clsLoadClass$default) : null;
                    if (kotlinClass != null) {
                        arrayList.add(kotlinClass);
                    }
                }
                return arrayList;
            }
            Class<?>[] declaredClasses = kClassImpl.getJClass().getDeclaredClasses();
            Intrinsics.checkNotNullExpressionValue(declaredClasses, "getDeclaredClasses(...)");
            ArrayList arrayList2 = new ArrayList();
            for (Class<?> cls : declaredClasses) {
                Intrinsics.checkNotNull(cls);
                KClass kotlinClass2 = JvmClassMappingKt.getKotlinClass(cls);
                if (kotlinClass2 != null) {
                    arrayList2.add(kotlinClass2);
                }
            }
            return arrayList2;
        }

        public final T getObjectInstance() {
            return (T) this.objectInstance.getValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Object objectInstance_delegate$lambda$0(Data data, KClassImpl kClassImpl) throws IllegalAccessException, NoSuchFieldException {
            Field declaredField;
            KmClass kmClass = data.getKmClass();
            if (kmClass == null || !(Attributes.getKind(kmClass) == ClassKind.OBJECT || Attributes.getKind(kmClass) == ClassKind.COMPANION_OBJECT)) {
                return null;
            }
            if (Attributes.getKind(kmClass) == ClassKind.COMPANION_OBJECT && !CollectionsKt.contains(CompanionObjectMapping.INSTANCE.getClassIds(), ConvertFromMetadataKt.toClassId(kmClass.getName()).getOuterClassId())) {
                declaredField = kClassImpl.getJClass().getEnclosingClass().getDeclaredField(ConvertFromMetadataKt.toNonLocalSimpleName(kmClass.getName()));
            } else {
                declaredField = kClassImpl.getJClass().getDeclaredField("INSTANCE");
            }
            Object obj = declaredField.get(null);
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type T of kotlin.reflect.jvm.internal.KClassImpl");
            return obj;
        }

        public final List<KTypeParameter> getTypeParameters() {
            T value = this.typeParameters.getValue(this, $$delegatedProperties[6]);
            Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
            return (List) value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final List typeParameters_delegate$lambda$0(Data data, KClassImpl kClassImpl) {
            if (SystemPropertiesKt.getUseK1Implementation()) {
                List<TypeParameterDescriptor> declaredTypeParameters = data.getDescriptor().getDeclaredTypeParameters();
                Intrinsics.checkNotNullExpressionValue(declaredTypeParameters, "getDeclaredTypeParameters(...)");
                List<TypeParameterDescriptor> list = declaredTypeParameters;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                for (TypeParameterDescriptor typeParameterDescriptor : list) {
                    Intrinsics.checkNotNull(typeParameterDescriptor);
                    arrayList.add(new KTypeParameterImpl(kClassImpl, typeParameterDescriptor, (KTypeSubstitutor) null, 4, (DefaultConstructorMarker) null));
                }
                return arrayList;
            }
            if (data.getKmClass() == null) {
                TypeVariable<Class<T>>[] typeParameters = kClassImpl.getJClass().getTypeParameters();
                Intrinsics.checkNotNullExpressionValue(typeParameters, "getTypeParameters(...)");
                return ConvertFromJavaKt.toKTypeParameters(typeParameters);
            }
            return data.getTypeParameterTable$kotlin_reflection().getOwnTypeParameters();
        }

        public final TypeParameterTable getTypeParameterTable$kotlin_reflection() {
            T value = this.typeParameterTable.getValue(this, $$delegatedProperties[7]);
            Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
            return (TypeParameterTable) value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Code duplicated, block: B:14:0x0037  */
        public static final TypeParameterTable typeParameterTable_delegate$lambda$0(Data data, KClassImpl kClassImpl) {
            KClass kotlinClass;
            Lazy<KClassImpl<T>.Data> data2;
            KClassImpl<T>.Data value;
            if (data.getKmClass() == null) {
                return TypeParameterTable.EMPTY;
            }
            TypeParameterTable.Companion companion = TypeParameterTable.INSTANCE;
            KmClass kmClass = data.getKmClass();
            Intrinsics.checkNotNull(kmClass);
            List<KmTypeParameter> typeParameters = kmClass.getTypeParameters();
            Class<?> enclosingClass = kClassImpl.getJClass().getEnclosingClass();
            TypeParameterTable typeParameterTable$kotlin_reflection = null;
            if (enclosingClass != null) {
                KmClass kmClass2 = data.getKmClass();
                Intrinsics.checkNotNull(kmClass2);
                if (!Attributes.isInner(kmClass2)) {
                    enclosingClass = null;
                }
                if (enclosingClass != null) {
                    kotlinClass = JvmClassMappingKt.getKotlinClass(enclosingClass);
                } else {
                    kotlinClass = null;
                }
            } else {
                kotlinClass = null;
            }
            KClassImpl kClassImpl2 = kotlinClass instanceof KClassImpl ? (KClassImpl) kotlinClass : null;
            if (kClassImpl2 != null && (data2 = kClassImpl2.getData()) != null && (value = data2.getValue()) != null) {
                typeParameterTable$kotlin_reflection = value.getTypeParameterTable$kotlin_reflection();
            }
            return companion.create(typeParameters, typeParameterTable$kotlin_reflection, kClassImpl, ReflectClassUtilKt.getSafeClassLoader(kClassImpl.getJClass()));
        }

        public final List<KType> getSupertypes() {
            T value = this.supertypes.getValue(this, $$delegatedProperties[8]);
            Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
            return (List) value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final List supertypes_delegate$lambda$0(final KClassImpl kClassImpl, Data data) {
            String qualifiedName;
            String name;
            final ClassId classId;
            if (Intrinsics.areEqual(kClassImpl.getJClass(), Object.class)) {
                return CollectionsKt.emptyList();
            }
            if (SystemPropertiesKt.getUseK1Implementation()) {
                return data.computeLegacySupertypes();
            }
            ArrayList arrayList = new ArrayList();
            KmClass kmClass = data.getKmClass();
            List<KmType> supertypes = kmClass != null ? kmClass.getSupertypes() : null;
            if (supertypes == null) {
                Type genericSuperclass = kClassImpl.getJClass().getGenericSuperclass();
                if (genericSuperclass != null) {
                    Type type = !Intrinsics.areEqual(genericSuperclass, Object.class) ? genericSuperclass : null;
                    if (type != null) {
                        arrayList.add(ConvertFromJavaKt.toKType$default(type, MapsKt.emptyMap(), TypeNullability.NOT_NULL, false, 4, null));
                    }
                }
                Type[] genericInterfaces = kClassImpl.getJClass().getGenericInterfaces();
                Intrinsics.checkNotNullExpressionValue(genericInterfaces, "getGenericInterfaces(...)");
                ArrayList arrayList2 = arrayList;
                for (Type type2 : genericInterfaces) {
                    Intrinsics.checkNotNull(type2);
                    arrayList2.add(ConvertFromJavaKt.toKType$default(type2, MapsKt.emptyMap(), TypeNullability.NOT_NULL, false, 4, null));
                }
            } else {
                ArrayList arrayList3 = arrayList;
                for (KmType kmType : supertypes) {
                    KmClassifier classifier = kmType.getClassifier();
                    KmClassifier.Class r7 = classifier instanceof KmClassifier.Class ? (KmClassifier.Class) classifier : null;
                    if (r7 == null || (name = r7.getName()) == null || (classId = ConvertFromMetadataKt.toClassId(name)) == null) {
                        throw new KotlinReflectionInternalError("Supertype of " + kClassImpl + " not a class: " + kmType.getClassifier());
                    }
                    final Class clsLoadClass$default = UtilKt.loadClass$default(ReflectClassUtilKt.getSafeClassLoader(kClassImpl.getJClass()), classId, 0, 2, null);
                    if (clsLoadClass$default == null) {
                        throw new KotlinReflectionInternalError("Unsupported superclass of " + kClassImpl + ": " + classId);
                    }
                    arrayList3.add(ConvertFromMetadataKt.toKType(kmType, ReflectClassUtilKt.getSafeClassLoader(kClassImpl.getJClass()), data.getTypeParameterTable$kotlin_reflection(), new Function0(kClassImpl, clsLoadClass$default, classId) { // from class: kotlin.reflect.jvm.internal.KClassImpl$Data$$Lambda$23
                        private final KClassImpl arg$0;
                        private final Class arg$1;
                        private final ClassId arg$2;

                        {
                            this.arg$0 = kClassImpl;
                            this.arg$1 = clsLoadClass$default;
                            this.arg$2 = classId;
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public Object invoke() {
                            return KClassImpl.Data.supertypes_delegate$lambda$0$0$0(this.arg$0, this.arg$1, this.arg$2);
                        }
                    }));
                }
                if (kClassImpl.getJClass().isArray()) {
                    arrayList3.add(StandardKTypes.INSTANCE.getCLONEABLE());
                }
                if (Serializable.class.isAssignableFrom(kClassImpl.getJClass()) && !arrayList.contains(StandardKTypes.INSTANCE.getSERIALIZABLE()) && (qualifiedName = data.getQualifiedName()) != null && StringsKt.startsWith$default(qualifiedName, "kotlin.", false, 2, (Object) null)) {
                    arrayList3.add(StandardKTypes.INSTANCE.getSERIALIZABLE());
                }
            }
            ArrayList arrayList4 = arrayList;
            if ((arrayList4 instanceof Collection) && arrayList4.isEmpty()) {
                arrayList.add(StandardKTypes.INSTANCE.getANY());
            } else {
                Iterator<T> it = arrayList4.iterator();
                while (it.hasNext()) {
                    KClassifier classifier2 = ((KType) it.next()).getClassifier();
                    KClassImpl kClassImpl2 = classifier2 instanceof KClassImpl ? (KClassImpl) classifier2 : null;
                    if (kClassImpl2 == null || (kClassImpl2.getClassKind$kotlin_reflection() != ClassKind.INTERFACE && kClassImpl2.getClassKind$kotlin_reflection() != ClassKind.ANNOTATION_CLASS)) {
                    }
                }
                arrayList.add(StandardKTypes.INSTANCE.getANY());
            }
            return kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.compact(arrayList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Type supertypes_delegate$lambda$0$0$0(KClassImpl kClassImpl, Class cls, ClassId classId) {
            if (Intrinsics.areEqual(kClassImpl.getJClass().getSuperclass(), cls)) {
                Type genericSuperclass = kClassImpl.getJClass().getGenericSuperclass();
                Intrinsics.checkNotNull(genericSuperclass);
                return genericSuperclass;
            }
            Class<?>[] interfaces = kClassImpl.getJClass().getInterfaces();
            Intrinsics.checkNotNullExpressionValue(interfaces, "getInterfaces(...)");
            int iIndexOf = ArraysKt.indexOf(interfaces, cls);
            if (iIndexOf < 0) {
                throw new KotlinReflectionInternalError("No superclass of " + kClassImpl + " in Java reflection for " + classId);
            }
            Type type = kClassImpl.getJClass().getGenericInterfaces()[iIndexOf];
            Intrinsics.checkNotNull(type);
            return type;
        }

        private final List<KType> computeLegacySupertypes() {
            Collection<KotlinType> collectionMo10247getSupertypes = getDescriptor().getTypeConstructor().mo10247getSupertypes();
            Intrinsics.checkNotNullExpressionValue(collectionMo10247getSupertypes, "getSupertypes(...)");
            ArrayList arrayList = new ArrayList(collectionMo10247getSupertypes.size());
            ArrayList arrayList2 = arrayList;
            final KClassImpl<T> kClassImpl = KClassImpl.this;
            for (final KotlinType kotlinType : collectionMo10247getSupertypes) {
                Intrinsics.checkNotNull(kotlinType);
                arrayList2.add(new DescriptorKType(kotlinType, new Function0(kotlinType, kClassImpl) { // from class: kotlin.reflect.jvm.internal.KClassImpl$Data$$Lambda$22
                    private final KotlinType arg$0;
                    private final KClassImpl arg$1;

                    {
                        this.arg$0 = kotlinType;
                        this.arg$1 = kClassImpl;
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public Object invoke() {
                        return KClassImpl.Data.computeLegacySupertypes$lambda$0$0(this.arg$0, this.arg$1);
                    }
                }));
            }
            if (!KotlinBuiltIns.isSpecialClassWithNoSupertypes(getDescriptor())) {
                ArrayList arrayList3 = arrayList;
                if ((arrayList3 instanceof Collection) && arrayList3.isEmpty()) {
                    arrayList2.add(StandardKTypes.INSTANCE.getANY());
                } else {
                    Iterator<T> it = arrayList3.iterator();
                    while (it.hasNext()) {
                        KClassifier classifier = ((KType) it.next()).getClassifier();
                        KClassImpl kClassImpl2 = classifier instanceof KClassImpl ? (KClassImpl) classifier : null;
                        if (kClassImpl2 == null || (kClassImpl2.getClassKind$kotlin_reflection() != ClassKind.INTERFACE && kClassImpl2.getClassKind$kotlin_reflection() != ClassKind.ANNOTATION_CLASS)) {
                        }
                    }
                    arrayList2.add(StandardKTypes.INSTANCE.getANY());
                }
            }
            return kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.compact(arrayList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Type computeLegacySupertypes$lambda$0$0(KotlinType kotlinType, KClassImpl kClassImpl) {
            ClassifierDescriptor classifierDescriptorMo10246getDeclarationDescriptor = kotlinType.getConstructor().mo10246getDeclarationDescriptor();
            if (!(classifierDescriptorMo10246getDeclarationDescriptor instanceof ClassDescriptor)) {
                throw new KotlinReflectionInternalError("Supertype not a class: " + classifierDescriptorMo10246getDeclarationDescriptor);
            }
            Class<?> javaClass = UtilKt.toJavaClass((ClassDescriptor) classifierDescriptorMo10246getDeclarationDescriptor);
            if (javaClass == null) {
                throw new KotlinReflectionInternalError("Unsupported superclass of " + kClassImpl + ": " + classifierDescriptorMo10246getDeclarationDescriptor);
            }
            if (Intrinsics.areEqual(kClassImpl.getJClass().getSuperclass(), javaClass)) {
                Type genericSuperclass = kClassImpl.getJClass().getGenericSuperclass();
                Intrinsics.checkNotNull(genericSuperclass);
                return genericSuperclass;
            }
            Class<?>[] interfaces = kClassImpl.getJClass().getInterfaces();
            Intrinsics.checkNotNullExpressionValue(interfaces, "getInterfaces(...)");
            int iIndexOf = ArraysKt.indexOf(interfaces, javaClass);
            if (iIndexOf < 0) {
                throw new KotlinReflectionInternalError("No superclass of " + kClassImpl + " in Java reflection for " + classifierDescriptorMo10246getDeclarationDescriptor);
            }
            Type type = kClassImpl.getJClass().getGenericInterfaces()[iIndexOf];
            Intrinsics.checkNotNull(type);
            return type;
        }

        public final List<KClass<? extends T>> getSealedSubclasses() {
            T value = this.sealedSubclasses.getValue(this, $$delegatedProperties[9]);
            Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
            return (List) value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final List sealedSubclasses_delegate$lambda$0(KClassImpl kClassImpl, Data data) throws IllegalAccessException, InvocationTargetException {
            ArrayList arrayListEmptyList;
            ClassLoader safeClassLoader = ReflectClassUtilKt.getSafeClassLoader(kClassImpl.getJClass());
            KmClass kmClass = data.getKmClass();
            if (kmClass != null) {
                List<String> sealedSubclasses = kmClass.getSealedSubclasses();
                ArrayList arrayList = new ArrayList();
                Iterator<T> it = sealedSubclasses.iterator();
                while (it.hasNext()) {
                    KClass<?> kClassLoadKClass = ConvertFromMetadataKt.loadKClass(safeClassLoader, (String) it.next());
                    if (kClassLoadKClass != null) {
                        arrayList.add(kClassLoadKClass);
                    }
                }
                arrayListEmptyList = arrayList;
            } else if (Intrinsics.areEqual((Object) Java16SealedRecordLoader.INSTANCE.loadIsSealed(kClassImpl.getJClass()), (Object) true)) {
                Class<?>[] clsArrLoadGetPermittedSubclasses = Java16SealedRecordLoader.INSTANCE.loadGetPermittedSubclasses(kClassImpl.getJClass());
                if (clsArrLoadGetPermittedSubclasses != null) {
                    ArrayList arrayList2 = new ArrayList(clsArrLoadGetPermittedSubclasses.length);
                    for (Class<?> cls : clsArrLoadGetPermittedSubclasses) {
                        arrayList2.add(JvmClassMappingKt.getKotlinClass(cls));
                    }
                    arrayListEmptyList = arrayList2;
                } else {
                    arrayListEmptyList = null;
                }
                if (arrayListEmptyList == null) {
                    arrayListEmptyList = CollectionsKt.emptyList();
                }
            } else {
                arrayListEmptyList = CollectionsKt.emptyList();
            }
            Intrinsics.checkNotNull(arrayListEmptyList, "null cannot be cast to non-null type kotlin.collections.List<kotlin.reflect.KClass<out T of kotlin.reflect.jvm.internal.KClassImpl>>");
            return arrayListEmptyList;
        }

        public final KType getInlineClassUnderlyingType$kotlin_reflection() {
            return (KType) this.inlineClassUnderlyingType.getValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final KType inlineClassUnderlyingType_delegate$lambda$0(Data data, KClassImpl kClassImpl) {
            KmClass kmClass = data.getKmClass();
            KmProperty kmProperty = null;
            if (kmClass == null || !Attributes.isValue(kmClass)) {
                return null;
            }
            if (kmClass.getInlineClassUnderlyingType() != null) {
                KmType inlineClassUnderlyingType = kmClass.getInlineClassUnderlyingType();
                if (inlineClassUnderlyingType == null) {
                    return null;
                }
                ClassLoader classLoader = kClassImpl.getJClass().getClassLoader();
                Intrinsics.checkNotNullExpressionValue(classLoader, "getClassLoader(...)");
                return ConvertFromMetadataKt.toKType$default(inlineClassUnderlyingType, classLoader, data.getTypeParameterTable$kotlin_reflection(), null, 4, null);
            }
            boolean z = false;
            for (T t : kmClass.getProperties()) {
                KmProperty kmProperty2 = (KmProperty) t;
                if (Intrinsics.areEqual(kmProperty2.getName(), kmClass.getInlineClassUnderlyingPropertyName()) && kmProperty2.getContextParameters().isEmpty() && kmProperty2.getReceiverParameterType() == null) {
                    if (z) {
                        throw new IllegalArgumentException("Collection contains more than one matching element.");
                    }
                    z = true;
                    kmProperty = t;
                }
            }
            if (z) {
                KmType returnType = kmProperty.getReturnType();
                ClassLoader classLoader2 = kClassImpl.getJClass().getClassLoader();
                Intrinsics.checkNotNullExpressionValue(classLoader2, "getClassLoader(...)");
                return ConvertFromMetadataKt.toKType$default(returnType, classLoader2, data.getTypeParameterTable$kotlin_reflection(), null, 4, null);
            }
            throw new NoSuchElementException("Collection contains no element matching the predicate.");
        }

        private final boolean useK1ImplementationForFakeOverrides() {
            return !SystemPropertiesKt.getNewFakeOverridesImplementation() || SystemPropertiesKt.getUseK1Implementation() || KClasses.isSubclassOf(KClassImpl.this, Reflection.getOrCreateKotlinClass(Iterable.class)) || KClasses.isSubclassOf(KClassImpl.this, Reflection.getOrCreateKotlinClass(Map.class)) || KClasses.isSubclassOf(KClassImpl.this, Reflection.getOrCreateKotlinClass(CharSequence.class)) || KClasses.isSubclassOf(KClassImpl.this, Reflection.getOrCreateKotlinClass(Number.class));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Collection declaredNonStaticMembers_delegate$lambda$0(KClassImpl kClassImpl) {
            return kClassImpl.getMembers(kClassImpl.getMemberScope$kotlin_reflection(), MemberBelonginess.DECLARED);
        }

        public final Collection<DescriptorKCallable<?>> getDeclaredNonStaticMembers() {
            T value = this.declaredNonStaticMembers.getValue(this, $$delegatedProperties[10]);
            Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
            return (Collection) value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Collection declaredStaticMembers_delegate$lambda$0(KClassImpl kClassImpl) {
            return kClassImpl.getMembers(kClassImpl.getStaticScope$kotlin_reflection(), MemberBelonginess.DECLARED);
        }

        private final Collection<DescriptorKCallable<?>> getDeclaredStaticMembers() {
            T value = this.declaredStaticMembers.getValue(this, $$delegatedProperties[11]);
            Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
            return (Collection) value;
        }

        private final Collection<DescriptorKCallable<?>> getInheritedNonStaticMembers_k1Impl() {
            T value = this.inheritedNonStaticMembers_k1Impl.getValue(this, $$delegatedProperties[12]);
            Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
            return (Collection) value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Collection inheritedNonStaticMembers_k1Impl_delegate$lambda$0(KClassImpl kClassImpl) {
            return kClassImpl.getMembers(kClassImpl.getMemberScope$kotlin_reflection(), MemberBelonginess.INHERITED);
        }

        private final Collection<DescriptorKCallable<?>> getInheritedStaticMembers_k1Impl() {
            T value = this.inheritedStaticMembers_k1Impl.getValue(this, $$delegatedProperties[13]);
            Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
            return (Collection) value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Collection inheritedStaticMembers_k1Impl_delegate$lambda$0(KClassImpl kClassImpl) {
            return kClassImpl.getMembers(kClassImpl.getStaticScope$kotlin_reflection(), MemberBelonginess.INHERITED);
        }

        public final Collection<DescriptorKCallable<?>> getAllNonStaticMembers() {
            T value = this.allNonStaticMembers.getValue(this, $$delegatedProperties[14]);
            Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
            return (Collection) value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final List allNonStaticMembers_delegate$lambda$0(Data data) {
            boolean zUseK1ImplementationForFakeOverrides = data.useK1ImplementationForFakeOverrides();
            if (zUseK1ImplementationForFakeOverrides) {
                return CollectionsKt.plus((Collection) data.getDeclaredNonStaticMembers(), (Iterable) data.getInheritedNonStaticMembers_k1Impl());
            }
            if (zUseK1ImplementationForFakeOverrides) {
                throw new NoWhenBranchMatchedException();
            }
            Collection<DescriptorKCallable<?>> allMembers = data.getAllMembers();
            ArrayList arrayList = new ArrayList();
            for (T t : allMembers) {
                if (!FakeOverridesKt.isStatic((DescriptorKCallable) t)) {
                    arrayList.add(t);
                }
            }
            return arrayList;
        }

        public final Collection<DescriptorKCallable<?>> getAllStaticMembers() {
            T value = this.allStaticMembers.getValue(this, $$delegatedProperties[15]);
            Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
            return (Collection) value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final List allStaticMembers_delegate$lambda$0(Data data) {
            boolean zUseK1ImplementationForFakeOverrides = data.useK1ImplementationForFakeOverrides();
            if (zUseK1ImplementationForFakeOverrides) {
                return CollectionsKt.plus((Collection) data.getDeclaredStaticMembers(), (Iterable) data.getInheritedStaticMembers_k1Impl());
            }
            if (zUseK1ImplementationForFakeOverrides) {
                throw new NoWhenBranchMatchedException();
            }
            Collection<DescriptorKCallable<?>> allMembers = data.getAllMembers();
            ArrayList arrayList = new ArrayList();
            for (T t : allMembers) {
                if (FakeOverridesKt.isStatic((DescriptorKCallable) t)) {
                    arrayList.add(t);
                }
            }
            return arrayList;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final List declaredMembers_delegate$lambda$0(Data data) {
            return CollectionsKt.plus((Collection) data.getDeclaredNonStaticMembers(), (Iterable) data.getDeclaredStaticMembers());
        }

        public final Collection<DescriptorKCallable<?>> getDeclaredMembers() {
            T value = this.declaredMembers.getValue(this, $$delegatedProperties[16]);
            Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
            return (Collection) value;
        }

        public final Collection<DescriptorKCallable<?>> getAllMembers() {
            T value = this.allMembers.getValue(this, $$delegatedProperties[17]);
            Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
            return (Collection) value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Collection allMembers_delegate$lambda$0(Data data, KClassImpl kClassImpl) {
            boolean zUseK1ImplementationForFakeOverrides = data.useK1ImplementationForFakeOverrides();
            if (zUseK1ImplementationForFakeOverrides) {
                return CollectionsKt.plus((Collection) data.getAllNonStaticMembers(), (Iterable) data.getAllStaticMembers());
            }
            if (zUseK1ImplementationForFakeOverrides) {
                throw new NoWhenBranchMatchedException();
            }
            return FakeOverridesKt.getAllMembers(kClassImpl);
        }

        public final FakeOverrideMembers getFakeOverrideMembers$kotlin_reflection() {
            T value = this.fakeOverrideMembers.getValue(this, $$delegatedProperties[18]);
            Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
            return (FakeOverrideMembers) value;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Data data$lambda$0(KClassImpl kClassImpl) {
        return new Data();
    }

    public final Lazy<KClassImpl<T>.Data> getData() {
        return this.data;
    }

    public final ClassDescriptor getDescriptor() {
        return this.data.getValue().getDescriptor();
    }

    private final KmClass getKmClass() {
        return this.data.getValue().getKmClass();
    }

    @Override // kotlin.reflect.KAnnotatedElement
    public List<Annotation> getAnnotations() {
        return this.data.getValue().getAnnotations();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ClassId getClassId() {
        return RuntimeTypeMapper.INSTANCE.mapJvmClassToKotlinClassId(getJClass());
    }

    public final ClassKind getClassKind$kotlin_reflection() {
        ClassKind kind;
        KmClass kmClass = getKmClass();
        if (kmClass != null && (kind = Attributes.getKind(kmClass)) != null) {
            return kind;
        }
        if (getJClass().isAnnotation()) {
            return ClassKind.ANNOTATION_CLASS;
        }
        if (getJClass().isInterface()) {
            return ClassKind.INTERFACE;
        }
        if (getJClass().isEnum()) {
            return ClassKind.ENUM_CLASS;
        }
        return getJClass().getSuperclass().isEnum() ? ClassKind.ENUM_ENTRY : ClassKind.CLASS;
    }

    public final MemberScope getMemberScope$kotlin_reflection() {
        return getDescriptor().getDefaultType().getMemberScope();
    }

    public final MemberScope getStaticScope$kotlin_reflection() {
        MemberScope staticScope = getDescriptor().getStaticScope();
        Intrinsics.checkNotNullExpressionValue(staticScope, "getStaticScope(...)");
        return staticScope;
    }

    @Override // kotlin.reflect.KDeclarationContainer
    public Collection<KCallable<?>> getMembers() {
        return this.data.getValue().getAllMembers();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:12:0x004b  */
    public final Collection<DescriptorKCallable<?>> getMembers(MemberScope scope, MemberBelonginess belonginess) {
        DescriptorKCallable descriptorKCallable;
        CreateKCallableVisitor createKCallableVisitor = new CreateKCallableVisitor(this) { // from class: kotlin.reflect.jvm.internal.KClassImpl$getMembers$visitor$1
            {
                super(this);
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorVisitorEmptyBodies, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
            public DescriptorKCallable<?> visitConstructorDescriptor(ConstructorDescriptor descriptor, Unit data) {
                Intrinsics.checkNotNullParameter(descriptor, "descriptor");
                Intrinsics.checkNotNullParameter(data, "data");
                throw new IllegalStateException("No constructors should appear here: " + descriptor);
            }
        };
        Collection<DeclarationDescriptor> contributedDescriptors$default = ResolutionScope.DefaultImpls.getContributedDescriptors$default(scope, null, null, 3, null);
        ArrayList arrayList = new ArrayList();
        for (DeclarationDescriptor declarationDescriptor : contributedDescriptors$default) {
            if (declarationDescriptor instanceof CallableMemberDescriptor) {
                CallableMemberDescriptor callableMemberDescriptor = (CallableMemberDescriptor) declarationDescriptor;
                if (Intrinsics.areEqual(callableMemberDescriptor.getVisibility(), DescriptorVisibilities.INVISIBLE_FAKE) || !belonginess.accept(callableMemberDescriptor)) {
                    descriptorKCallable = null;
                } else {
                    descriptorKCallable = (DescriptorKCallable) declarationDescriptor.accept(createKCallableVisitor, Unit.INSTANCE);
                }
            } else {
                descriptorKCallable = null;
            }
            if (descriptorKCallable != null) {
                arrayList.add(descriptorKCallable);
            }
        }
        return CollectionsKt.toList(arrayList);
    }

    /* JADX INFO: compiled from: KClassImpl.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tj\u0002\b\u0004j\u0002\b\u0005¨\u0006\n"}, d2 = {"Lkotlin/reflect/jvm/internal/KClassImpl$MemberBelonginess;", "", "<init>", "(Ljava/lang/String;I)V", "DECLARED", "INHERITED", "accept", "", "member", "Lkotlin/reflect/jvm/internal/impl/descriptors/CallableMemberDescriptor;", "kotlin-reflection"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private enum MemberBelonginess {
        DECLARED,
        INHERITED;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public final boolean accept(CallableMemberDescriptor member) {
            Intrinsics.checkNotNullParameter(member, "member");
            return member.getKind().isReal() == (this == DECLARED);
        }
    }

    @Override // kotlin.reflect.jvm.internal.KDeclarationContainerImpl
    public Collection<KmConstructor> getConstructorsMetadata() {
        KmClass kmClass = getKmClass();
        List<KmConstructor> constructors = kmClass != null ? kmClass.getConstructors() : null;
        if (constructors == null) {
            constructors = CollectionsKt.emptyList();
        }
        return constructors;
    }

    @Override // kotlin.reflect.jvm.internal.KDeclarationContainerImpl
    public Collection<ConstructorDescriptor> getConstructorDescriptors() {
        Collection<ClassConstructorDescriptor> constructors = getDescriptor().getConstructors();
        Intrinsics.checkNotNullExpressionValue(constructors, "getConstructors(...)");
        return constructors;
    }

    @Override // kotlin.reflect.jvm.internal.KDeclarationContainerImpl
    public Collection<PropertyDescriptor> getProperties(Name name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return CollectionsKt.plus((Collection) getMemberScope$kotlin_reflection().getContributedVariables(name, NoLookupLocation.FROM_REFLECTION), (Iterable) getStaticScope$kotlin_reflection().getContributedVariables(name, NoLookupLocation.FROM_REFLECTION));
    }

    @Override // kotlin.reflect.jvm.internal.KDeclarationContainerImpl
    public Collection<FunctionDescriptor> getFunctions(Name name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return CollectionsKt.plus((Collection) getMemberScope$kotlin_reflection().getContributedFunctions(name, NoLookupLocation.FROM_REFLECTION), (Iterable) getStaticScope$kotlin_reflection().getContributedFunctions(name, NoLookupLocation.FROM_REFLECTION));
    }

    @Override // kotlin.reflect.jvm.internal.KDeclarationContainerImpl
    public PropertyDescriptor getLocalPropertyDescriptor(int index) {
        ClassDescriptor descriptor = getDescriptor();
        DeserializedClassDescriptor deserializedClassDescriptor = descriptor instanceof DeserializedClassDescriptor ? (DeserializedClassDescriptor) descriptor : null;
        if (deserializedClassDescriptor != null) {
            ProtoBuf.Class classProto = deserializedClassDescriptor.getClassProto();
            GeneratedMessageLite.GeneratedExtension<ProtoBuf.Class, List<ProtoBuf.Property>> classLocalVariable = JvmProtoBuf.classLocalVariable;
            Intrinsics.checkNotNullExpressionValue(classLocalVariable, "classLocalVariable");
            ProtoBuf.Property property = (ProtoBuf.Property) ProtoBufUtilKt.getExtensionOrNull(classProto, classLocalVariable, index);
            if (property != null) {
                return (PropertyDescriptor) UtilKt.deserializeToDescriptor(getJClass(), new LocalDelegatedPropertyFakeContainerSource(this), property, deserializedClassDescriptor.getC().getNameResolver(), deserializedClassDescriptor.getC().getTypeTable(), deserializedClassDescriptor.getMetadataVersion(), new Function2() { // from class: kotlin.reflect.jvm.internal.KClassImpl$$Lambda$1
                    @Override // kotlin.jvm.functions.Function2
                    public Object invoke(Object obj, Object obj2) {
                        return KClassImpl.getLocalPropertyDescriptor$lambda$0$0$0((MemberDeserializer) obj, (ProtoBuf.Property) obj2);
                    }
                });
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PropertyDescriptor getLocalPropertyDescriptor$lambda$0$0$0(MemberDeserializer deserializeToDescriptor, ProtoBuf.Property proto) {
        Intrinsics.checkNotNullParameter(deserializeToDescriptor, "$this$deserializeToDescriptor");
        Intrinsics.checkNotNullParameter(proto, "proto");
        return deserializeToDescriptor.loadProperty(proto, true);
    }

    @Override // kotlin.reflect.jvm.internal.KDeclarationContainerImpl
    public KmProperty getLocalPropertyMetadata(int index) {
        List<KmProperty> localDelegatedProperties;
        KmClass kmClass = getKmClass();
        if (kmClass == null || (localDelegatedProperties = JvmExtensionsKt.getLocalDelegatedProperties(kmClass)) == null) {
            return null;
        }
        return (KmProperty) CollectionsKt.getOrNull(localDelegatedProperties, index);
    }

    @Override // kotlin.reflect.KClass
    public String getSimpleName() {
        return this.data.getValue().getSimpleName();
    }

    @Override // kotlin.reflect.KClass
    public String getQualifiedName() {
        return this.data.getValue().getQualifiedName();
    }

    @Override // kotlin.reflect.KClass
    public Collection<KFunction<T>> getConstructors() {
        return this.data.getValue().getConstructors();
    }

    @Override // kotlin.reflect.KClass
    public Collection<KClass<?>> getNestedClasses() {
        return this.data.getValue().getNestedClasses();
    }

    @Override // kotlin.reflect.KClass
    public T getObjectInstance() {
        return this.data.getValue().getObjectInstance();
    }

    @Override // kotlin.reflect.KClass
    public boolean isInstance(Object value) {
        Integer functionClassArity = ReflectClassUtilKt.getFunctionClassArity(getJClass());
        if (functionClassArity != null) {
            return TypeIntrinsics.isFunctionOfArity(value, functionClassArity.intValue());
        }
        Class wrapperByPrimitive = ReflectClassUtilKt.getWrapperByPrimitive(getJClass());
        if (wrapperByPrimitive == null) {
            wrapperByPrimitive = getJClass();
        }
        return wrapperByPrimitive.isInstance(value);
    }

    @Override // kotlin.reflect.KClass
    public List<KTypeParameter> getTypeParameters() {
        return this.data.getValue().getTypeParameters();
    }

    public final TypeParameterTable getTypeParameterTable$kotlin_reflection() {
        return this.data.getValue().getTypeParameterTable$kotlin_reflection();
    }

    @Override // kotlin.reflect.KClass
    public List<KType> getSupertypes() {
        return this.data.getValue().getSupertypes();
    }

    @Override // kotlin.reflect.KClass
    public List<KClass<? extends T>> getSealedSubclasses() {
        return this.data.getValue().getSealedSubclasses();
    }

    @Override // kotlin.reflect.KClass
    public KVisibility getVisibility() {
        DescriptorVisibility visibility = getDescriptor().getVisibility();
        Intrinsics.checkNotNullExpressionValue(visibility, "getVisibility(...)");
        return UtilKt.toKVisibility(visibility);
    }

    private final Modality getModality() {
        Modality modality;
        KmClass kmClass = getKmClass();
        if (kmClass != null && (modality = Attributes.getModality(kmClass)) != null) {
            return modality;
        }
        if (getJClass().isAnnotation() || getJClass().isEnum()) {
            return Modality.FINAL;
        }
        if (Intrinsics.areEqual((Object) Java16SealedRecordLoader.INSTANCE.loadIsSealed(getJClass()), (Object) true)) {
            return Modality.SEALED;
        }
        if (Modifier.isAbstract(getJClass().getModifiers())) {
            return Modality.ABSTRACT;
        }
        return !Modifier.isFinal(getJClass().getModifiers()) ? Modality.OPEN : Modality.FINAL;
    }

    @Override // kotlin.reflect.KClass
    public boolean isFinal() {
        return getModality() == Modality.FINAL;
    }

    @Override // kotlin.reflect.KClass
    public boolean isOpen() {
        return getModality() == Modality.OPEN;
    }

    @Override // kotlin.reflect.KClass
    public boolean isAbstract() {
        return getModality() == Modality.ABSTRACT;
    }

    @Override // kotlin.reflect.KClass
    public boolean isSealed() {
        return getModality() == Modality.SEALED;
    }

    @Override // kotlin.reflect.KClass
    public boolean isData() {
        KmClass kmClass = getKmClass();
        return kmClass != null && Attributes.isData(kmClass);
    }

    @Override // kotlin.reflect.KClass
    public boolean isInner() {
        KmClass kmClass = getKmClass();
        if (kmClass == null) {
            return (getJClass().getDeclaringClass() == null || Modifier.isStatic(getJClass().getModifiers())) ? false : true;
        }
        return Attributes.isInner(kmClass);
    }

    @Override // kotlin.reflect.KClass
    public boolean isCompanion() {
        KmClass kmClass = getKmClass();
        return (kmClass != null ? Attributes.getKind(kmClass) : null) == ClassKind.COMPANION_OBJECT;
    }

    @Override // kotlin.reflect.KClass
    public boolean isFun() {
        KmClass kmClass = getKmClass();
        return kmClass != null && Attributes.isFunInterface(kmClass);
    }

    @Override // kotlin.reflect.KClass
    public boolean isValue() {
        KmClass kmClass = getKmClass();
        return kmClass != null && Attributes.isValue(kmClass);
    }

    public final String getInlineClassUnderlyingPropertyName$kotlin_reflection() {
        KmClass kmClass = getKmClass();
        if (kmClass != null) {
            return kmClass.getInlineClassUnderlyingPropertyName();
        }
        return null;
    }

    public final KType getInlineClassUnderlyingType$kotlin_reflection() {
        return this.data.getValue().getInlineClassUnderlyingType$kotlin_reflection();
    }

    @Override // kotlin.jvm.internal.KotlinGenericDeclaration
    public GenericDeclaration findJavaDeclaration() {
        return getJClass();
    }

    public final String getModuleName$kotlin_reflection() {
        KmClass kmClass = getKmClass();
        if (kmClass != null) {
            return JvmExtensionsKt.getModuleName(kmClass);
        }
        return null;
    }

    @Override // kotlin.reflect.KClass
    public boolean equals(Object other) {
        return (other instanceof KClassImpl) && Intrinsics.areEqual(JvmClassMappingKt.getJavaObjectType(this), JvmClassMappingKt.getJavaObjectType((KClass) other));
    }

    @Override // kotlin.reflect.KClass
    public int hashCode() {
        return JvmClassMappingKt.getJavaObjectType(this).hashCode();
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder("class ");
        ClassId classId = getClassId();
        FqName packageFqName = classId.getPackageFqName();
        if (packageFqName.isRoot()) {
            str = "";
        } else {
            str = packageFqName.asString() + '.';
        }
        sb.append(str + StringsKt.replace$default(classId.getRelativeClassName().asString(), '.', Typography.dollar, false, 4, (Object) null));
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ClassDescriptor createSyntheticClassOrFail(ClassId classId, RuntimeModuleData moduleData) {
        KotlinClassHeader classHeader;
        if (getJClass().isSynthetic()) {
            return createSyntheticClass(classId, moduleData);
        }
        ReflectKotlinClass reflectKotlinClassCreate = ReflectKotlinClass.Factory.create(getJClass());
        KotlinClassHeader.Kind kind = (reflectKotlinClassCreate == null || (classHeader = reflectKotlinClassCreate.getClassHeader()) == null) ? null : classHeader.getKind();
        switch (kind == null ? -1 : WhenMappings.$EnumSwitchMapping$0[kind.ordinal()]) {
            case -1:
            case 6:
                throw new KotlinReflectionInternalError("Unresolved class: " + getJClass() + " (kind = " + kind + ')');
            case 0:
            default:
                throw new NoWhenBranchMatchedException();
            case 1:
            case 2:
            case 3:
            case 4:
                return createSyntheticClass(classId, moduleData);
            case 5:
                throw new KotlinReflectionInternalError("Unknown class: " + getJClass() + " (kind = " + kind + ')');
        }
    }

    private final ClassDescriptor createSyntheticClass(ClassId classId, RuntimeModuleData moduleData) {
        final ClassDescriptorImpl classDescriptorImpl = new ClassDescriptorImpl(new EmptyPackageFragmentDescriptor(moduleData.getModule(), classId.getPackageFqName()), classId.getShortClassName(), kotlin.reflect.jvm.internal.impl.descriptors.Modality.FINAL, kotlin.reflect.jvm.internal.impl.descriptors.ClassKind.CLASS, CollectionsKt.listOf(moduleData.getModule().getBuiltIns().getAny().getDefaultType()), SourceElement.NO_SOURCE, false, moduleData.getDeserialization().getStorageManager());
        final StorageManager storageManager = moduleData.getDeserialization().getStorageManager();
        classDescriptorImpl.initialize(new GivenFunctionsMemberScope(classDescriptorImpl, storageManager) { // from class: kotlin.reflect.jvm.internal.KClassImpl$createSyntheticClass$1$1
            {
                super(storageManager, classDescriptorImpl);
            }

            @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.GivenFunctionsMemberScope
            protected List<FunctionDescriptor> computeDeclaredFunctions() {
                return CollectionsKt.emptyList();
            }
        }, SetsKt.emptySet(), null);
        return classDescriptorImpl;
    }

    static {
        Set<ClassId> special_annotations = SpecialJvmAnnotations.INSTANCE.getSPECIAL_ANNOTATIONS();
        HashSet hashSet = new HashSet();
        Iterator<T> it = special_annotations.iterator();
        while (it.hasNext()) {
            hashSet.add(((ClassId) it.next()).asSingleFqName().toString());
        }
        SPECIAL_JVM_ANNOTATION_NAMES = hashSet;
    }
}
