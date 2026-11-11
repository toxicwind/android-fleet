package kotlin.reflect.jvm.internal;

import androidx.exifinterface.media.ExifInterface;
import androidx.media3.exoplayer.upstream.CmcdData;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.collections.ArraysKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.ClassBasedDeclarationContainer;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KAnnotatedElement;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KFunction;
import kotlin.reflect.KParameter;
import kotlin.reflect.KProperty;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeParameter;
import kotlin.reflect.KTypeProjection;
import kotlin.reflect.KVisibility;
import kotlin.reflect.full.KClasses;
import kotlin.reflect.full.KClassifiers;
import kotlin.reflect.full.KTypes;
import kotlin.reflect.jvm.ReflectJvmMapping;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.km.ClassKind;
import kotlin.reflect.jvm.internal.impl.utils.CollectionsKt;
import kotlin.reflect.jvm.internal.types.AbstractKType;
import kotlin.reflect.jvm.internal.types.KTypeSubstitutor;
import kotlin.reflect.jvm.internal.types.MutableCollectionKClass;
import kotlin.reflect.jvm.internal.types.ReflectTypeSystemContext;
import org.mozilla.javascript.ES6Iterator;
import org.mozilla.universalchardet.prober.HebrewProber;

/* JADX INFO: compiled from: fakeOverrides.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u001a\u001e\u0010\u0000\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u00012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0000\u001a\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0000\u001a\u001c\u0010\u0011\u001a\u00020\u0012*\u0006\u0012\u0002\b\u00030\u00022\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0002\u001a \u0010\u0013\u001a\u00020\u00122\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u00042\n\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\u0002H\u0002\u001a\u0014\u0010\u0015\u001a\u00020\u00162\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0000\u001aX\u0010\u0017\u001a\u0002H\u0018\"\u0004\b\u0000\u0010\u0019\"\b\b\u0001\u0010\u0018*\u00020\b*\u000e\u0012\u0004\u0012\u0002H\u0019\u0012\u0004\u0012\u0002H\u00180\u000e2\u0006\u0010\u001a\u001a\u0002H\u00192\u0006\u0010\u001b\u001a\u0002H\u00182\u0018\u0010\u001c\u001a\u0014\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u00180\u001dH\u0082\b¢\u0006\u0002\u0010\u001e\u001a-\u0010)\u001a\b\u0012\u0004\u0012\u0002H*0\u000b\"\b\b\u0000\u0010**\u00020+*\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010,\u001a\u0002H*H\u0002¢\u0006\u0002\u0010-\u001a\"\u00106\u001a\u0004\u0018\u000107*\b\u0012\u0004\u0012\u000209082\f\u0010:\u001a\b\u0012\u0004\u0012\u00020908H\u0002\u001a\u0014\u0010;\u001a\u00020<*\u00020<2\u0006\u0010=\u001a\u00020>H\u0002\u001a \u0010?\u001a\b\u0012\u0004\u0012\u00020<08*\b\u0012\u0004\u0012\u00020<082\u0006\u0010=\u001a\u00020>H\u0002\u001a\u0018\u0010@\u001a\u00020\u00122\u0006\u0010A\u001a\u00020<2\u0006\u0010B\u001a\u00020<H\u0002\"&\u0010\u001f\u001a\u001a\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020 j\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0002`!X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001c\u0010\"\u001a\u00020\u0012*\u0006\u0012\u0002\b\u00030\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#\"\u001c\u0010$\u001a\u00020\u0012*\u0006\u0012\u0002\b\u00030\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b$\u0010#\"\u001c\u0010%\u001a\u00020\u0016*\u0006\u0012\u0002\b\u00030&8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b'\u0010(\"\u001c\u0010.\u001a\u00020\u0012*\u0006\u0012\u0002\b\u00030/8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b.\u00100\",\u00101\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001*\u0006\u0012\u0002\b\u00030&8BX\u0082\u0004¢\u0006\f\u0012\u0004\b2\u00103\u001a\u0004\b4\u00105*8\b\u0002\u0010\t\"\u0018\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\n2\u0018\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\n*8\b\u0002\u0010\r\"\u0018\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u000e2\u0018\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u000e*8\b\u0002\u0010\u000f\"\u0018\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000b\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u000e2\u0018\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000b\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u000e¨\u0006C"}, d2 = {"getAllMembers", "", "Lkotlin/reflect/jvm/internal/DescriptorKCallable;", "kClass", "Lkotlin/reflect/jvm/internal/KClassImpl;", "starProjectionInTopLevelTypeIsNotPossible", "", "containerForDebug", "", "MembersJavaSignatureMap", "", "Lkotlin/reflect/jvm/internal/EquatableCallableSignature;", "Lkotlin/reflect/jvm/internal/EqualityMode$JavaSignature;", "MutableMembersJavaSignatureMap", "", "MutableMembersKotlinSignatureMap", "Lkotlin/reflect/jvm/internal/EqualityMode$KotlinSignature;", "isStaticMethodInInterface", "", "skipDeclaredMember", "member", "computeFakeOverrideMembers", "Lkotlin/reflect/jvm/internal/FakeOverrideMembers;", "mergeWith", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "K", "key", ES6Iterator.VALUE_PROPERTY, "remappingFunction", "Lkotlin/Function2;", "(Ljava/util/Map;Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "modalityIntersectionOverrideComparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "isStatic", "(Lkotlin/reflect/jvm/internal/DescriptorKCallable;)Z", "isJavaField", "fakeOverrideMembers", "Lkotlin/reflect/KClass;", "getFakeOverrideMembers", "(Lkotlin/reflect/KClass;)Lkotlin/reflect/jvm/internal/FakeOverrideMembers;", "toEquatableCallableSignature", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlin/reflect/jvm/internal/EqualityMode;", "equalityMode", "(Lkotlin/reflect/jvm/internal/DescriptorKCallable;Lkotlin/reflect/jvm/internal/EqualityMode;)Lkotlin/reflect/jvm/internal/EquatableCallableSignature;", "isKotlin", "Ljava/lang/Class;", "(Ljava/lang/Class;)Z", "declaredDescriptorKCallableMembers", "getDeclaredDescriptorKCallableMembers$annotations", "(Lkotlin/reflect/KClass;)V", "getDeclaredDescriptorKCallableMembers", "(Lkotlin/reflect/KClass;)Ljava/util/Collection;", "substitutedWith", "Lkotlin/reflect/jvm/internal/types/KTypeSubstitutor;", "", "Lkotlin/reflect/KTypeParameter;", "arguments", "coerceFlexibleTypesAndMutabilityRecursive", "Lkotlin/reflect/KType;", "memberNameForDebug", "", "sortedUpperBounds", "areEqualKTypes", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "b", "kotlin-reflection"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class FakeOverridesKt {
    private static final Comparator<DescriptorKCallable<?>> modalityIntersectionOverrideComparator = ComparisonsKt.compareBy(new Function1() { // from class: kotlin.reflect.jvm.internal.FakeOverridesKt$$Lambda$0
        @Override // kotlin.jvm.functions.Function1
        public Object invoke(Object obj) {
            return FakeOverridesKt.modalityIntersectionOverrideComparator$lambda$0((DescriptorKCallable) obj);
        }
    }, new Function1() { // from class: kotlin.reflect.jvm.internal.FakeOverridesKt$$Lambda$1
        @Override // kotlin.jvm.functions.Function1
        public Object invoke(Object obj) {
            return FakeOverridesKt.modalityIntersectionOverrideComparator$lambda$1((DescriptorKCallable) obj);
        }
    });

    public static final Collection<DescriptorKCallable<?>> getAllMembers(KClassImpl<?> kClass) {
        HashMap map;
        Intrinsics.checkNotNullParameter(kClass, "kClass");
        FakeOverrideMembers fakeOverrideMembers$kotlin_reflection = kClass.getData().getValue().getFakeOverrideMembers$kotlin_reflection();
        KClassImpl<?> kClassImpl = kClass;
        boolean zIsKotlin = isKotlin(JvmClassMappingKt.getJavaClass((KClass) kClassImpl));
        boolean z = fakeOverrideMembers$kotlin_reflection.getContainsInheritedStatics() && kClass.getClassKind$kotlin_reflection() != ClassKind.ENUM_CLASS && zIsKotlin;
        boolean z2 = fakeOverrideMembers$kotlin_reflection.getContainsPackagePrivate() || z;
        if (z2) {
            Map<EquatableCallableSignature<EqualityMode.JavaSignature>, DescriptorKCallable<?>> members = fakeOverrideMembers$kotlin_reflection.getMembers();
            HashMap mapNewHashMapWithExpectedSize = CollectionsKt.newHashMapWithExpectedSize(fakeOverrideMembers$kotlin_reflection.getMembers().size());
            for (Map.Entry<EquatableCallableSignature<EqualityMode.JavaSignature>, DescriptorKCallable<?>> entry : members.entrySet()) {
                DescriptorKCallable<?> value = entry.getValue();
                if (!z || !isStatic(value)) {
                    if (!value.isPackagePrivate$kotlin_reflection() || Intrinsics.areEqual(value.getContainer().getJClass().getPackage(), JvmClassMappingKt.getJavaClass((KClass) kClassImpl).getPackage())) {
                        mapNewHashMapWithExpectedSize.put(entry.getKey(), entry.getValue());
                    }
                }
            }
            map = mapNewHashMapWithExpectedSize;
        } else {
            if (z2) {
                throw new NoWhenBranchMatchedException();
            }
            map = new HashMap(fakeOverrideMembers$kotlin_reflection.getMembers());
        }
        HashMap map2 = new HashMap();
        for (DescriptorKCallable<?> descriptorKCallable : getDeclaredDescriptorKCallableMembers(kClassImpl)) {
            if (isStaticMethodInInterface(descriptorKCallable, kClass)) {
                if (zIsKotlin) {
                    throw new IllegalStateException(("Kotlin doesn't have statics. '" + descriptorKCallable.getName() + "' appears to be declared static member in '" + kClass.getSimpleName() + '\'').toString());
                }
                map.put(toEquatableCallableSignature(descriptorKCallable, EqualityMode.JavaSignature.INSTANCE), descriptorKCallable);
            } else if (descriptorKCallable.getVisibility() == KVisibility.PRIVATE) {
                if (zIsKotlin) {
                    map2.put(toEquatableCallableSignature(descriptorKCallable, EqualityMode.KotlinSignature.INSTANCE), descriptorKCallable);
                } else {
                    map.put(toEquatableCallableSignature(descriptorKCallable, EqualityMode.JavaSignature.INSTANCE), descriptorKCallable);
                }
            }
        }
        Collection collectionValues = map.values();
        Intrinsics.checkNotNullExpressionValue(collectionValues, "<get-values>(...)");
        return kotlin.collections.CollectionsKt.plus(collectionValues, (Iterable) map2.values());
    }

    public static final Void starProjectionInTopLevelTypeIsNotPossible(Object containerForDebug) {
        Intrinsics.checkNotNullParameter(containerForDebug, "containerForDebug");
        throw new IllegalStateException(("Star projection in top level type is not possible. Star projection appeared in the following container: '" + containerForDebug + '\'').toString());
    }

    private static final boolean isStaticMethodInInterface(DescriptorKCallable<?> descriptorKCallable, KClassImpl<?> kClassImpl) {
        return isStatic(descriptorKCallable) && kClassImpl.getClassKind$kotlin_reflection() == ClassKind.INTERFACE && !isJavaField(descriptorKCallable);
    }

    private static final boolean skipDeclaredMember(KClassImpl<?> kClassImpl, DescriptorKCallable<?> descriptorKCallable) {
        return descriptorKCallable.getVisibility() == KVisibility.PRIVATE || isStaticMethodInInterface(descriptorKCallable, kClassImpl);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v6, types: [java.lang.Object, kotlin.reflect.jvm.internal.DescriptorKCallable] */
    /* JADX WARN: Type inference failed for: r7v7, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v8 */
    public static final FakeOverrideMembers computeFakeOverrideMembers(KClassImpl<?> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "kClass");
        HashMap map = new HashMap();
        ReceiverParameterDescriptor thisAsReceiverParameter = kClass.getDescriptor().getThisAsReceiverParameter();
        Intrinsics.checkNotNullExpressionValue(thisAsReceiverParameter, "getThisAsReceiverParameter(...)");
        KClassImpl<?> kClassImpl = kClass;
        boolean zIsKotlin = isKotlin(JvmClassMappingKt.getJavaClass((KClass) kClassImpl));
        HashMap map2 = new HashMap();
        if (zIsKotlin) {
            for (DescriptorKCallable<?> descriptorKCallable : getDeclaredDescriptorKCallableMembers(kClassImpl)) {
                if (!skipDeclaredMember(kClass, descriptorKCallable)) {
                    map2.put(toEquatableCallableSignature(descriptorKCallable, EqualityMode.KotlinSignature.INSTANCE), descriptorKCallable);
                }
            }
        }
        boolean z = false;
        boolean z2 = false;
        for (KType kType : kClass.getSupertypes()) {
            KClassifier classifier = kType.getClassifier();
            KClass kClass2 = classifier instanceof KClass ? (KClass) classifier : null;
            if (kClass2 == null) {
                throw new IllegalStateException(("Non-denotable supertypes are not possible. Supertype '" + kType + "' appears non-denotable in class '" + kClass + '\'').toString());
            }
            KTypeSubstitutor kTypeSubstitutorCreate = KTypeSubstitutor.INSTANCE.create(kType);
            FakeOverrideMembers fakeOverrideMembers = getFakeOverrideMembers(kClass2);
            z = z || fakeOverrideMembers.getContainsInheritedStatics();
            z2 = z2 || fakeOverrideMembers.getContainsPackagePrivate();
            Iterator<Map.Entry<EquatableCallableSignature<EqualityMode.JavaSignature>, DescriptorKCallable<?>>> it = fakeOverrideMembers.getMembers().entrySet().iterator();
            while (it.hasNext()) {
                DescriptorKCallable<?> value = it.next().getValue();
                ?? ShallowCopy$kotlin_reflection = value.shallowCopy$kotlin_reflection(KCallableOverriddenStorage.copy$default(value.getOverriddenStorage(), isStatic(value) ? null : thisAsReceiverParameter, value.getOverriddenStorage().getTypeSubstitutor().combinedWith(kTypeSubstitutorCreate), null, true, false, false, false, false, HebrewProber.NORMAL_PE, null));
                EquatableCallableSignature equatableCallableSignature = toEquatableCallableSignature(ShallowCopy$kotlin_reflection, EqualityMode.KotlinSignature.INSTANCE);
                if (!map2.containsKey(equatableCallableSignature)) {
                    EquatableCallableSignature equatableCallableSignatureWithEqualityMode = equatableCallableSignature.withEqualityMode(EqualityMode.JavaSignature.INSTANCE);
                    Object obj = map.get(equatableCallableSignatureWithEqualityMode);
                    if (obj != null) {
                        KAnnotatedElement kAnnotatedElement = (DescriptorKCallable) obj;
                        DescriptorKCallable descriptorKCallableShallowCopy$kotlin_reflection = (DescriptorKCallable) ComparisonsKt.minOf(kAnnotatedElement, ShallowCopy$kotlin_reflection, CovariantOverrideComparator.INSTANCE);
                        if ((kAnnotatedElement instanceof KFunction) && (ShallowCopy$kotlin_reflection instanceof KFunction)) {
                            KFunction kFunction = (KFunction) kAnnotatedElement;
                            descriptorKCallableShallowCopy$kotlin_reflection = descriptorKCallableShallowCopy$kotlin_reflection.shallowCopy$kotlin_reflection(KCallableOverriddenStorage.copy$default(descriptorKCallableShallowCopy$kotlin_reflection.getOverriddenStorage(), null, null, ((DescriptorKCallable) ComparisonsKt.minOf(kAnnotatedElement, ShallowCopy$kotlin_reflection, modalityIntersectionOverrideComparator)).getModality$kotlin_reflection(), false, kFunction.isExternal() || ((KFunction) ShallowCopy$kotlin_reflection).isExternal(), kFunction.isOperator() || ((KFunction) ShallowCopy$kotlin_reflection).isOperator(), kFunction.isInfix() || ((KFunction) ShallowCopy$kotlin_reflection).isInfix(), kFunction.isInline() || ((KFunction) ShallowCopy$kotlin_reflection).isInline(), 11, null));
                        }
                        if (descriptorKCallableShallowCopy$kotlin_reflection != null) {
                            ShallowCopy$kotlin_reflection = descriptorKCallableShallowCopy$kotlin_reflection;
                        }
                    }
                    map.put(equatableCallableSignatureWithEqualityMode, ShallowCopy$kotlin_reflection);
                }
            }
        }
        for (Map.Entry entry : map2.entrySet()) {
            EquatableCallableSignature equatableCallableSignature2 = (EquatableCallableSignature) entry.getKey();
            DescriptorKCallable descriptorKCallable2 = (DescriptorKCallable) entry.getValue();
            z = z || isStatic(descriptorKCallable2);
            z2 = z2 || descriptorKCallable2.isPackagePrivate$kotlin_reflection();
            map.put(equatableCallableSignature2.withEqualityMode(EqualityMode.JavaSignature.INSTANCE), descriptorKCallable2);
        }
        if (!zIsKotlin) {
            for (DescriptorKCallable<?> descriptorKCallable3 : getDeclaredDescriptorKCallableMembers(kClassImpl)) {
                if (!skipDeclaredMember(kClass, descriptorKCallable3)) {
                    z = z || isStatic(descriptorKCallable3);
                    z2 = z2 || descriptorKCallable3.isPackagePrivate$kotlin_reflection();
                    map.put(toEquatableCallableSignature(descriptorKCallable3, EqualityMode.JavaSignature.INSTANCE), descriptorKCallable3);
                }
            }
        }
        return new FakeOverrideMembers(map, z, z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Comparable modalityIntersectionOverrideComparator$lambda$0(DescriptorKCallable it) {
        Class javaClass;
        Intrinsics.checkNotNullParameter(it, "it");
        ClassBasedDeclarationContainer container = it.getContainer();
        KClass kClass = container instanceof KClass ? (KClass) container : null;
        boolean z = false;
        if (kClass != null && (javaClass = JvmClassMappingKt.getJavaClass(kClass)) != null && javaClass.isInterface()) {
            z = true;
        }
        return Boolean.valueOf(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Comparable modalityIntersectionOverrideComparator$lambda$1(DescriptorKCallable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Boolean.valueOf(Intrinsics.areEqual(it.getContainer(), Reflection.getOrCreateKotlinClass(Object.class)));
    }

    public static final boolean isStatic(DescriptorKCallable<?> descriptorKCallable) {
        Intrinsics.checkNotNullParameter(descriptorKCallable, "<this>");
        return UtilKt.getInstanceReceiverParameter(descriptorKCallable) == null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final boolean isJavaField(DescriptorKCallable<?> descriptorKCallable) {
        Field javaField;
        Class<?> declaringClass;
        return (!(descriptorKCallable instanceof KProperty) || (javaField = ReflectJvmMapping.getJavaField((KProperty) descriptorKCallable)) == null || (declaringClass = javaField.getDeclaringClass()) == null || isKotlin(declaringClass)) ? false : true;
    }

    private static final FakeOverrideMembers getFakeOverrideMembers(KClass<?> kClass) {
        if (kClass instanceof KClassImpl) {
            return ((KClassImpl.Data) ((KClassImpl) kClass).getData().getValue()).getFakeOverrideMembers$kotlin_reflection();
        }
        if (kClass instanceof MutableCollectionKClass) {
            return getFakeOverrideMembers(((MutableCollectionKClass) kClass).getKlass());
        }
        throw new IllegalStateException(("Unknown type " + Reflection.getOrCreateKotlinClass(kClass.getClass())).toString());
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final <T extends EqualityMode> EquatableCallableSignature<T> toEquatableCallableSignature(DescriptorKCallable<?> descriptorKCallable, T t) {
        SignatureKind signatureKind;
        List<KParameter> parameters = descriptorKCallable.getParameters();
        ArrayList arrayList = new ArrayList();
        for (Object obj : parameters) {
            if (((KParameter) obj).getKind() != KParameter.Kind.INSTANCE) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = arrayList;
        ArrayList arrayList3 = new ArrayList(kotlin.collections.CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList3.add(((KParameter) it.next()).getType());
        }
        ArrayList arrayList4 = arrayList3;
        if (isJavaField(descriptorKCallable)) {
            signatureKind = SignatureKind.FIELD_IN_JAVA_CLASS;
        } else if (descriptorKCallable instanceof KProperty) {
            signatureKind = SignatureKind.PROPERTY;
        } else {
            if (!(descriptorKCallable instanceof KFunction)) {
                throw new IllegalStateException(("Unknown kind for " + Reflection.getOrCreateKotlinClass(descriptorKCallable.getClass())).toString());
            }
            signatureKind = SignatureKind.FUNCTION;
        }
        SignatureKind signatureKind2 = signatureKind;
        KFunction kFunction = descriptorKCallable instanceof KFunction ? (KFunction) descriptorKCallable : null;
        Method javaMethod = kFunction != null ? ReflectJvmMapping.getJavaMethod(kFunction) : null;
        Type[] genericParameterTypes = javaMethod != null ? javaMethod.getGenericParameterTypes() : null;
        if (genericParameterTypes == null) {
            genericParameterTypes = new Type[0];
        }
        List list = ArraysKt.toList(genericParameterTypes);
        Class<?>[] parameterTypes = javaMethod != null ? javaMethod.getParameterTypes() : null;
        if (parameterTypes == null) {
            parameterTypes = new Class[0];
        }
        return new EquatableCallableSignature<>(signatureKind2, descriptorKCallable.getName(), javaMethod != null ? javaMethod.getName() : null, descriptorKCallable.getTypeParameters(), arrayList4, ArraysKt.toList(parameterTypes), list, isStatic(descriptorKCallable), t);
    }

    public static final boolean isKotlin(Class<?> cls) {
        Intrinsics.checkNotNullParameter(cls, "<this>");
        return cls.getAnnotation(Metadata.class) != null;
    }

    private static final Collection<DescriptorKCallable<?>> getDeclaredDescriptorKCallableMembers(KClass<?> kClass) {
        Collection declaredMembers = KClasses.getDeclaredMembers(kClass);
        Intrinsics.checkNotNull(declaredMembers, "null cannot be cast to non-null type kotlin.collections.Collection<kotlin.reflect.jvm.internal.DescriptorKCallable<*>>");
        return declaredMembers;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final KTypeSubstitutor substitutedWith(List<? extends KTypeParameter> list, List<? extends KTypeParameter> list2) {
        if (list.size() != list2.size()) {
            return null;
        }
        if (list2.isEmpty() || list.isEmpty()) {
            return KTypeSubstitutor.INSTANCE.getEMPTY();
        }
        List<Pair> listZip = kotlin.collections.CollectionsKt.zip(list, list2);
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(kotlin.collections.CollectionsKt.collectionSizeOrDefault(listZip, 10)), 16));
        for (Pair pair : listZip) {
            Pair pair2 = new Pair((KTypeParameter) pair.component1(), KTypeProjection.INSTANCE.invariant(KClassifiers.createType$default((KTypeParameter) pair.component2(), null, false, null, 7, null)));
            linkedHashMap.put(pair2.getFirst(), pair2.getSecond());
        }
        return new KTypeSubstitutor(linkedHashMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final KType coerceFlexibleTypesAndMutabilityRecursive(KType kType, String str) {
        ReflectTypeSystemContext reflectTypeSystemContext = ReflectTypeSystemContext.INSTANCE;
        AbstractKType abstractKType = kType instanceof AbstractKType ? (AbstractKType) kType : null;
        if (abstractKType != null && reflectTypeSystemContext.isError(abstractKType)) {
            return kType;
        }
        KClassifier classifier = kType.getClassifier();
        if (classifier != null) {
            List<KTypeProjection> arguments = kType.getArguments();
            ArrayList arrayList = new ArrayList(kotlin.collections.CollectionsKt.collectionSizeOrDefault(arguments, 10));
            for (KTypeProjection kTypeProjection : arguments) {
                KType type = kTypeProjection.getType();
                arrayList.add(KTypeProjection.copy$default(kTypeProjection, null, type != null ? coerceFlexibleTypesAndMutabilityRecursive(type, str) : null, 1, null));
            }
            return KClassifiers.createType(classifier, arrayList, false, kType.getAnnotations());
        }
        throw new IllegalStateException(("Non-denotable parameter types are not possible. Some parameter types appear non-denotable for type '" + kType + "' (" + Reflection.getOrCreateKotlinClass(kType.getClass()) + ") which belongs to member '" + str + '\'').toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<KType> sortedUpperBounds(List<? extends KType> list, final String str) {
        return kotlin.collections.CollectionsKt.sortedWith(list, new Comparator() { // from class: kotlin.reflect.jvm.internal.FakeOverridesKt$sortedUpperBounds$$inlined$sortedBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                String name;
                String name2;
                KClassifier classifier = ((KType) t).getClassifier();
                if (classifier == null) {
                    throw new IllegalStateException(("Upper bounds are always denotable. Upper bounds appear non-denotable for member: '" + str + '\'').toString());
                }
                if (classifier instanceof KClass) {
                    name = JvmClassMappingKt.getJavaClass((KClass) classifier).getName();
                } else {
                    if (!(classifier instanceof KTypeParameter)) {
                        throw new IllegalStateException(("Unknown upper bound classifier: " + Reflection.getOrCreateKotlinClass(classifier.getClass())).toString());
                    }
                    name = ((KTypeParameter) classifier).getName();
                }
                String str2 = name;
                KClassifier classifier2 = ((KType) t2).getClassifier();
                if (classifier2 == null) {
                    throw new IllegalStateException(("Upper bounds are always denotable. Upper bounds appear non-denotable for member: '" + str + '\'').toString());
                }
                if (classifier2 instanceof KClass) {
                    name2 = JvmClassMappingKt.getJavaClass((KClass) classifier2).getName();
                } else {
                    if (!(classifier2 instanceof KTypeParameter)) {
                        throw new IllegalStateException(("Unknown upper bound classifier: " + Reflection.getOrCreateKotlinClass(classifier2.getClass())).toString());
                    }
                    name2 = ((KTypeParameter) classifier2).getName();
                }
                return ComparisonsKt.compareValues(str2, name2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean areEqualKTypes(KType kType, KType kType2) {
        return KTypes.isSubtypeOf(kType, kType2) && KTypes.isSubtypeOf(kType2, kType);
    }
}
