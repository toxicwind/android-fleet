package kotlin.reflect.full;

import androidx.exifinterface.media.ExifInterface;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.reflect.KCallable;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KFunction;
import kotlin.reflect.KParameter;
import kotlin.reflect.KProperty0;
import kotlin.reflect.KProperty1;
import kotlin.reflect.KProperty2;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeParameter;
import kotlin.reflect.KTypeProjection;
import kotlin.reflect.KVariance;
import kotlin.reflect.jvm.internal.DescriptorKCallable;
import kotlin.reflect.jvm.internal.KClassImpl;
import kotlin.reflect.jvm.internal.KotlinReflectionInternalError;
import kotlin.reflect.jvm.internal.ReflectKFunction;
import kotlin.reflect.jvm.internal.impl.utils.DFS;
import kotlin.reflect.jvm.internal.types.AbstractKType;
import kotlin.reflect.jvm.internal.types.CapturedKTypeKt;
import kotlin.reflect.jvm.internal.types.KTypeSubstitutor;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: KClasses.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000T\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0013\u001a\u0010\u0010\u0016\u001a\u00020\u0012*\u0006\u0012\u0002\b\u00030\u0004H\u0000\u001a\u001c\u0010T\u001a\u00020E*\u0006\u0012\u0002\b\u00030\u00042\n\u0010U\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0007\u001a\u001c\u0010V\u001a\u00020E*\u0006\u0012\u0002\b\u00030\u00042\n\u0010W\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0007\u001a+\u0010X\u001a\u0002H\u0002\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00042\b\u0010Y\u001a\u0004\u0018\u00010\u0003H\u0007¢\u0006\u0002\u0010Z\u001a-\u0010[\u001a\u0004\u0018\u0001H\u0002\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00042\b\u0010Y\u001a\u0004\u0018\u00010\u0003H\u0007¢\u0006\u0002\u0010Z\u001a!\u0010\\\u001a\u0002H\u0002\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u0007¢\u0006\u0002\u0010\u0010\"6\u0010\u0000\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00048FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b\"(\u0010\t\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0004*\u0006\u0012\u0002\b\u00030\u00048FX\u0087\u0004¢\u0006\f\u0012\u0004\b\n\u0010\u0006\u001a\u0004\b\u000b\u0010\f\"$\u0010\r\u001a\u0004\u0018\u00010\u0003*\u0006\u0012\u0002\b\u00030\u00048FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u000e\u0010\u0006\u001a\u0004\b\u000f\u0010\u0010\"\"\u0010\u0011\u001a\u00020\u0012*\u0006\u0012\u0002\b\u00030\u00048FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0013\u0010\u0006\u001a\u0004\b\u0014\u0010\u0015\",\u0010\u0017\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00190\u0018*\u0006\u0012\u0002\b\u00030\u00048FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u001a\u0010\u0006\u001a\u0004\b\u001b\u0010\u001c\",\u0010\u001d\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00010\u0018*\u0006\u0012\u0002\b\u00030\u00048FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u001e\u0010\u0006\u001a\u0004\b\u001f\u0010\u001c\",\u0010 \u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00010\u0018*\u0006\u0012\u0002\b\u00030\u00048FX\u0087\u0004¢\u0006\f\u0012\u0004\b!\u0010\u0006\u001a\u0004\b\"\u0010\u001c\",\u0010#\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00010\u0018*\u0006\u0012\u0002\b\u00030\u00048FX\u0087\u0004¢\u0006\f\u0012\u0004\b$\u0010\u0006\u001a\u0004\b%\u0010\u001c\",\u0010&\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00010\u0018*\u0006\u0012\u0002\b\u00030\u00048FX\u0087\u0004¢\u0006\f\u0012\u0004\b'\u0010\u0006\u001a\u0004\b(\u0010\u001c\",\u0010)\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00010\u0018*\u0006\u0012\u0002\b\u00030\u00048FX\u0087\u0004¢\u0006\f\u0012\u0004\b*\u0010\u0006\u001a\u0004\b+\u0010\u001c\",\u0010,\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00010\u0018*\u0006\u0012\u0002\b\u00030\u00048FX\u0087\u0004¢\u0006\f\u0012\u0004\b-\u0010\u0006\u001a\u0004\b.\u0010\u001c\",\u0010/\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00010\u0018*\u0006\u0012\u0002\b\u00030\u00048FX\u0087\u0004¢\u0006\f\u0012\u0004\b0\u0010\u0006\u001a\u0004\b1\u0010\u001c\",\u00102\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u0003030\u0018*\u0006\u0012\u0002\b\u00030\u00048FX\u0087\u0004¢\u0006\f\u0012\u0004\b4\u0010\u0006\u001a\u0004\b5\u0010\u001c\">\u00106\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u0002H\u0002\u0012\u0002\b\u0003070\u0018\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00048FX\u0087\u0004¢\u0006\f\u0012\u0004\b8\u0010\u0006\u001a\u0004\b9\u0010\u001c\"B\u0010:\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0002\b\u0003\u0012\u0002\b\u00030;0\u0018\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00048FX\u0087\u0004¢\u0006\f\u0012\u0004\b<\u0010\u0006\u001a\u0004\b=\u0010\u001c\">\u0010>\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u0002H\u0002\u0012\u0002\b\u0003070\u0018\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00048FX\u0087\u0004¢\u0006\f\u0012\u0004\b?\u0010\u0006\u001a\u0004\b@\u0010\u001c\"B\u0010A\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0002\b\u0003\u0012\u0002\b\u00030;0\u0018\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00048FX\u0087\u0004¢\u0006\f\u0012\u0004\bB\u0010\u0006\u001a\u0004\bC\u0010\u001c\"\u001c\u0010D\u001a\u00020E*\u0006\u0012\u0002\b\u00030F8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bD\u0010G\"\u001c\u0010H\u001a\u00020E*\u0006\u0012\u0002\b\u00030F8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bH\u0010G\",\u0010I\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040J*\u0006\u0012\u0002\b\u00030\u00048FX\u0087\u0004¢\u0006\f\u0012\u0004\bK\u0010\u0006\u001a\u0004\bL\u0010M\"(\u0010N\u001a\b\u0012\u0004\u0012\u00020\u00120\u0018*\u0006\u0012\u0002\b\u00030\u00048FX\u0087\u0004¢\u0006\f\u0012\u0004\bO\u0010\u0006\u001a\u0004\bP\u0010\u001c\",\u0010Q\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u0018*\u0006\u0012\u0002\b\u00030\u00048FX\u0087\u0004¢\u0006\f\u0012\u0004\bR\u0010\u0006\u001a\u0004\bS\u0010\u001c¨\u0006]"}, d2 = {"primaryConstructor", "Lkotlin/reflect/KFunction;", ExifInterface.GPS_DIRECTION_TRUE, "", "Lkotlin/reflect/KClass;", "getPrimaryConstructor$annotations", "(Lkotlin/reflect/KClass;)V", "getPrimaryConstructor", "(Lkotlin/reflect/KClass;)Lkotlin/reflect/KFunction;", "companionObject", "getCompanionObject$annotations", "getCompanionObject", "(Lkotlin/reflect/KClass;)Lkotlin/reflect/KClass;", "companionObjectInstance", "getCompanionObjectInstance$annotations", "getCompanionObjectInstance", "(Lkotlin/reflect/KClass;)Ljava/lang/Object;", "defaultType", "Lkotlin/reflect/KType;", "getDefaultType$annotations", "getDefaultType", "(Lkotlin/reflect/KClass;)Lkotlin/reflect/KType;", "createDefaultType", "declaredMembers", "", "Lkotlin/reflect/KCallable;", "getDeclaredMembers$annotations", "getDeclaredMembers", "(Lkotlin/reflect/KClass;)Ljava/util/Collection;", "functions", "getFunctions$annotations", "getFunctions", "staticFunctions", "getStaticFunctions$annotations", "getStaticFunctions", "memberFunctions", "getMemberFunctions$annotations", "getMemberFunctions", "memberExtensionFunctions", "getMemberExtensionFunctions$annotations", "getMemberExtensionFunctions", "declaredFunctions", "getDeclaredFunctions$annotations", "getDeclaredFunctions", "declaredMemberFunctions", "getDeclaredMemberFunctions$annotations", "getDeclaredMemberFunctions", "declaredMemberExtensionFunctions", "getDeclaredMemberExtensionFunctions$annotations", "getDeclaredMemberExtensionFunctions", "staticProperties", "Lkotlin/reflect/KProperty0;", "getStaticProperties$annotations", "getStaticProperties", "memberProperties", "Lkotlin/reflect/KProperty1;", "getMemberProperties$annotations", "getMemberProperties", "memberExtensionProperties", "Lkotlin/reflect/KProperty2;", "getMemberExtensionProperties$annotations", "getMemberExtensionProperties", "declaredMemberProperties", "getDeclaredMemberProperties$annotations", "getDeclaredMemberProperties", "declaredMemberExtensionProperties", "getDeclaredMemberExtensionProperties$annotations", "getDeclaredMemberExtensionProperties", "isExtension", "", "Lkotlin/reflect/jvm/internal/DescriptorKCallable;", "(Lkotlin/reflect/jvm/internal/DescriptorKCallable;)Z", "isNotExtension", "superclasses", "", "getSuperclasses$annotations", "getSuperclasses", "(Lkotlin/reflect/KClass;)Ljava/util/List;", "allSupertypes", "getAllSupertypes$annotations", "getAllSupertypes", "allSuperclasses", "getAllSuperclasses$annotations", "getAllSuperclasses", "isSubclassOf", TtmlNode.RUBY_BASE, "isSuperclassOf", "derived", "cast", ES6Iterator.VALUE_PROPERTY, "(Lkotlin/reflect/KClass;Ljava/lang/Object;)Ljava/lang/Object;", "safeCast", "createInstance", "kotlin-reflection"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class KClasses {
    public static /* synthetic */ void getAllSuperclasses$annotations(KClass kClass) {
    }

    public static /* synthetic */ void getAllSupertypes$annotations(KClass kClass) {
    }

    public static /* synthetic */ void getCompanionObject$annotations(KClass kClass) {
    }

    public static /* synthetic */ void getCompanionObjectInstance$annotations(KClass kClass) {
    }

    public static /* synthetic */ void getDeclaredFunctions$annotations(KClass kClass) {
    }

    public static /* synthetic */ void getDeclaredMemberExtensionFunctions$annotations(KClass kClass) {
    }

    public static /* synthetic */ void getDeclaredMemberExtensionProperties$annotations(KClass kClass) {
    }

    public static /* synthetic */ void getDeclaredMemberFunctions$annotations(KClass kClass) {
    }

    public static /* synthetic */ void getDeclaredMemberProperties$annotations(KClass kClass) {
    }

    public static /* synthetic */ void getDeclaredMembers$annotations(KClass kClass) {
    }

    @Deprecated(message = "This function creates a type which rarely makes sense for generic classes. For example, such type can only be used in signatures of members of that class. Use starProjectedType or createType() for clearer semantics.")
    public static /* synthetic */ void getDefaultType$annotations(KClass kClass) {
    }

    public static /* synthetic */ void getFunctions$annotations(KClass kClass) {
    }

    public static /* synthetic */ void getMemberExtensionFunctions$annotations(KClass kClass) {
    }

    public static /* synthetic */ void getMemberExtensionProperties$annotations(KClass kClass) {
    }

    public static /* synthetic */ void getMemberFunctions$annotations(KClass kClass) {
    }

    public static /* synthetic */ void getMemberProperties$annotations(KClass kClass) {
    }

    public static /* synthetic */ void getPrimaryConstructor$annotations(KClass kClass) {
    }

    public static /* synthetic */ void getStaticFunctions$annotations(KClass kClass) {
    }

    public static /* synthetic */ void getStaticProperties$annotations(KClass kClass) {
    }

    public static /* synthetic */ void getSuperclasses$annotations(KClass kClass) {
    }

    public static final <T> KFunction<T> getPrimaryConstructor(KClass<T> kClass) {
        T next;
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        Iterator<T> it = kClass.getConstructors().iterator();
        while (it.hasNext()) {
            next = it.next();
            KFunction kFunction = (KFunction) next;
            Intrinsics.checkNotNull(kFunction, "null cannot be cast to non-null type kotlin.reflect.jvm.internal.ReflectKFunction");
            if (((ReflectKFunction) kFunction).isPrimaryConstructor()) {
                return (KFunction) next;
            }
        }
        next = null;
        return (KFunction) next;
    }

    public static final KClass<?> getCompanionObject(KClass<?> kClass) {
        Object next;
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        Iterator<T> it = kClass.getNestedClasses().iterator();
        while (it.hasNext()) {
            next = it.next();
            if (((KClass) next).isCompanion()) {
                return (KClass) next;
            }
        }
        next = null;
        return (KClass) next;
    }

    public static final Object getCompanionObjectInstance(KClass<?> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        KClass<?> companionObject = getCompanionObject(kClass);
        if (companionObject != null) {
            return companionObject.getObjectInstance();
        }
        return null;
    }

    public static final KType getDefaultType(KClass<?> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        return createDefaultType(kClass);
    }

    public static final KType createDefaultType(KClass<?> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        KClass<?> kClass2 = kClass;
        List<KTypeParameter> listAllTypeParameters = CapturedKTypeKt.allTypeParameters(kClass);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listAllTypeParameters, 10));
        Iterator<T> it = listAllTypeParameters.iterator();
        while (it.hasNext()) {
            arrayList.add(new KTypeProjection(KVariance.INVARIANT, KClassifiers.createType$default((KTypeParameter) it.next(), null, false, null, 7, null)));
        }
        return KClassifiers.createType$default(kClass2, arrayList, false, null, 6, null);
    }

    public static final Collection<KCallable<?>> getDeclaredMembers(KClass<?> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        return ((KClassImpl.Data) ((KClassImpl) kClass).getData().getValue()).getDeclaredMembers();
    }

    public static final Collection<KFunction<?>> getFunctions(KClass<?> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        Collection<KCallable<?>> members = kClass.getMembers();
        ArrayList arrayList = new ArrayList();
        for (Object obj : members) {
            if (obj instanceof KFunction) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static final Collection<KFunction<?>> getStaticFunctions(KClass<?> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        Collection<DescriptorKCallable<?>> allStaticMembers = ((KClassImpl.Data) ((KClassImpl) kClass).getData().getValue()).getAllStaticMembers();
        ArrayList arrayList = new ArrayList();
        for (Object obj : allStaticMembers) {
            if (obj instanceof KFunction) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static final Collection<KFunction<?>> getMemberFunctions(KClass<?> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        Collection<DescriptorKCallable<?>> allNonStaticMembers = ((KClassImpl.Data) ((KClassImpl) kClass).getData().getValue()).getAllNonStaticMembers();
        ArrayList arrayList = new ArrayList();
        for (Object obj : allNonStaticMembers) {
            DescriptorKCallable descriptorKCallable = (DescriptorKCallable) obj;
            if (isNotExtension(descriptorKCallable) && (descriptorKCallable instanceof KFunction)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static final Collection<KFunction<?>> getMemberExtensionFunctions(KClass<?> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        Collection<DescriptorKCallable<?>> allNonStaticMembers = ((KClassImpl.Data) ((KClassImpl) kClass).getData().getValue()).getAllNonStaticMembers();
        ArrayList arrayList = new ArrayList();
        for (Object obj : allNonStaticMembers) {
            DescriptorKCallable descriptorKCallable = (DescriptorKCallable) obj;
            if (isExtension(descriptorKCallable) && (descriptorKCallable instanceof KFunction)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static final Collection<KFunction<?>> getDeclaredFunctions(KClass<?> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        Collection<DescriptorKCallable<?>> declaredMembers = ((KClassImpl.Data) ((KClassImpl) kClass).getData().getValue()).getDeclaredMembers();
        ArrayList arrayList = new ArrayList();
        for (Object obj : declaredMembers) {
            if (obj instanceof KFunction) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static final Collection<KFunction<?>> getDeclaredMemberFunctions(KClass<?> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        Collection<DescriptorKCallable<?>> declaredNonStaticMembers = ((KClassImpl.Data) ((KClassImpl) kClass).getData().getValue()).getDeclaredNonStaticMembers();
        ArrayList arrayList = new ArrayList();
        for (Object obj : declaredNonStaticMembers) {
            DescriptorKCallable descriptorKCallable = (DescriptorKCallable) obj;
            if (isNotExtension(descriptorKCallable) && (descriptorKCallable instanceof KFunction)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static final Collection<KFunction<?>> getDeclaredMemberExtensionFunctions(KClass<?> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        Collection<DescriptorKCallable<?>> declaredNonStaticMembers = ((KClassImpl.Data) ((KClassImpl) kClass).getData().getValue()).getDeclaredNonStaticMembers();
        ArrayList arrayList = new ArrayList();
        for (Object obj : declaredNonStaticMembers) {
            DescriptorKCallable descriptorKCallable = (DescriptorKCallable) obj;
            if (isExtension(descriptorKCallable) && (descriptorKCallable instanceof KFunction)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static final Collection<KProperty0<?>> getStaticProperties(KClass<?> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        Collection<DescriptorKCallable<?>> allStaticMembers = ((KClassImpl.Data) ((KClassImpl) kClass).getData().getValue()).getAllStaticMembers();
        ArrayList arrayList = new ArrayList();
        for (Object obj : allStaticMembers) {
            DescriptorKCallable descriptorKCallable = (DescriptorKCallable) obj;
            if (isNotExtension(descriptorKCallable) && (descriptorKCallable instanceof KProperty0)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static final <T> Collection<KProperty1<T, ?>> getMemberProperties(KClass<T> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        Collection<DescriptorKCallable<?>> allNonStaticMembers = ((KClassImpl) kClass).getData().getValue().getAllNonStaticMembers();
        ArrayList arrayList = new ArrayList();
        for (T t : allNonStaticMembers) {
            DescriptorKCallable descriptorKCallable = (DescriptorKCallable) t;
            if (isNotExtension(descriptorKCallable) && (descriptorKCallable instanceof KProperty1)) {
                arrayList.add(t);
            }
        }
        return arrayList;
    }

    public static final <T> Collection<KProperty2<T, ?, ?>> getMemberExtensionProperties(KClass<T> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        Collection<DescriptorKCallable<?>> allNonStaticMembers = ((KClassImpl) kClass).getData().getValue().getAllNonStaticMembers();
        ArrayList arrayList = new ArrayList();
        for (T t : allNonStaticMembers) {
            DescriptorKCallable descriptorKCallable = (DescriptorKCallable) t;
            if (isExtension(descriptorKCallable) && (descriptorKCallable instanceof KProperty2)) {
                arrayList.add(t);
            }
        }
        return arrayList;
    }

    public static final <T> Collection<KProperty1<T, ?>> getDeclaredMemberProperties(KClass<T> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        Collection<DescriptorKCallable<?>> declaredNonStaticMembers = ((KClassImpl) kClass).getData().getValue().getDeclaredNonStaticMembers();
        ArrayList arrayList = new ArrayList();
        for (T t : declaredNonStaticMembers) {
            DescriptorKCallable descriptorKCallable = (DescriptorKCallable) t;
            if (isNotExtension(descriptorKCallable) && (descriptorKCallable instanceof KProperty1)) {
                arrayList.add(t);
            }
        }
        return arrayList;
    }

    public static final <T> Collection<KProperty2<T, ?, ?>> getDeclaredMemberExtensionProperties(KClass<T> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        Collection<DescriptorKCallable<?>> declaredNonStaticMembers = ((KClassImpl) kClass).getData().getValue().getDeclaredNonStaticMembers();
        ArrayList arrayList = new ArrayList();
        for (T t : declaredNonStaticMembers) {
            DescriptorKCallable descriptorKCallable = (DescriptorKCallable) t;
            if (isExtension(descriptorKCallable) && (descriptorKCallable instanceof KProperty2)) {
                arrayList.add(t);
            }
        }
        return arrayList;
    }

    private static final boolean isExtension(DescriptorKCallable<?> descriptorKCallable) {
        return descriptorKCallable.getDescriptor().getExtensionReceiverParameter() != null;
    }

    private static final boolean isNotExtension(DescriptorKCallable<?> descriptorKCallable) {
        return !isExtension(descriptorKCallable);
    }

    public static final List<KClass<?>> getSuperclasses(KClass<?> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        List<KType> supertypes = kClass.getSupertypes();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = supertypes.iterator();
        while (it.hasNext()) {
            KClassifier classifier = ((KType) it.next()).getClassifier();
            KClass kClass2 = classifier instanceof KClass ? (KClass) classifier : null;
            if (kClass2 != null) {
                arrayList.add(kClass2);
            }
        }
        return arrayList;
    }

    public static final Collection<KType> getAllSupertypes(KClass<?> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        Object objDfs = DFS.dfs(kClass.getSupertypes(), new DFS.Neighbors() { // from class: kotlin.reflect.full.KClasses$$Lambda$0
            @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.Neighbors
            public Iterable getNeighbors(Object obj) {
                return KClasses._get_allSupertypes_$lambda$0((KType) obj);
            }
        }, new DFS.VisitedWithSet(), new DFS.NodeHandlerWithListResult<KType, KType>() { // from class: kotlin.reflect.full.KClasses$allSupertypes$2
            @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.AbstractNodeHandler, kotlin.reflect.jvm.internal.impl.utils.DFS.NodeHandler
            public boolean beforeChildren(KType current) {
                Intrinsics.checkNotNullParameter(current, "current");
                ((LinkedList) this.result).add(current);
                return true;
            }
        });
        Intrinsics.checkNotNullExpressionValue(objDfs, "dfs(...)");
        return (Collection) objDfs;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Iterable _get_allSupertypes_$lambda$0(KType kType) {
        KClassifier classifier = kType.getClassifier();
        KClass<?> kClass = classifier instanceof KClass ? (KClass) classifier : null;
        if (kClass == null) {
            throw new KotlinReflectionInternalError("Supertype not a class: " + kType);
        }
        List<KType> supertypes = kClass.getSupertypes();
        if (kType.getArguments().isEmpty()) {
            return supertypes;
        }
        KTypeSubstitutor.Companion companion = KTypeSubstitutor.INSTANCE;
        List<KTypeProjection> arguments = kType.getArguments();
        Intrinsics.checkNotNull(kType, "null cannot be cast to non-null type kotlin.reflect.jvm.internal.types.AbstractKType");
        KTypeSubstitutor kTypeSubstitutorCreate = companion.create(kClass, arguments, ((AbstractKType) kType).getIsSuspendFunctionType());
        List<KType> list = supertypes;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (KType kType2 : list) {
            KType type = KTypeSubstitutor.substitute$default(kTypeSubstitutorCreate, kType2, null, 2, null).getType();
            if (type == null) {
                throw new KotlinReflectionInternalError("Incorrect type substitution: " + kType2);
            }
            arrayList.add(type);
        }
        return arrayList;
    }

    public static final Collection<KClass<?>> getAllSuperclasses(KClass<?> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        Collection<KType> allSupertypes = getAllSupertypes(kClass);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(allSupertypes, 10));
        for (KType kType : allSupertypes) {
            KClassifier classifier = kType.getClassifier();
            KClass kClass2 = classifier instanceof KClass ? (KClass) classifier : null;
            if (kClass2 == null) {
                throw new KotlinReflectionInternalError("Supertype not a class: " + kType);
            }
            arrayList.add(kClass2);
        }
        return arrayList;
    }

    public static final boolean isSubclassOf(KClass<?> kClass, final KClass<?> base) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        Intrinsics.checkNotNullParameter(base, "base");
        if (Intrinsics.areEqual(kClass, base)) {
            return true;
        }
        List listListOf = CollectionsKt.listOf(kClass);
        final AnonymousClass1 anonymousClass1 = new PropertyReference1Impl() { // from class: kotlin.reflect.full.KClasses.isSubclassOf.1
            @Override // kotlin.jvm.internal.PropertyReference1Impl, kotlin.reflect.KProperty1
            public Object get(Object obj) {
                return KClasses.getSuperclasses((KClass) obj);
            }
        };
        return DFS.ifAny(listListOf, new DFS.Neighbors(anonymousClass1) { // from class: kotlin.reflect.full.KClasses$$Lambda$1
            private final KProperty1 arg$0;

            {
                this.arg$0 = anonymousClass1;
            }

            @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.Neighbors
            public Iterable getNeighbors(Object obj) {
                return KClasses.isSubclassOf$lambda$0(this.arg$0, (KClass) obj);
            }
        }, new Function1(base) { // from class: kotlin.reflect.full.KClasses$$Lambda$2
            private final KClass arg$0;

            {
                this.arg$0 = base;
            }

            @Override // kotlin.jvm.functions.Function1
            public Object invoke(Object obj) {
                return KClasses.isSubclassOf$lambda$1(this.arg$0, (KClass) obj);
            }
        }).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Iterable isSubclassOf$lambda$0(KProperty1 kProperty1, KClass kClass) {
        return (Iterable) kProperty1.invoke(kClass);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Boolean isSubclassOf$lambda$1(KClass kClass, KClass kClass2) {
        return Boolean.valueOf(Intrinsics.areEqual(kClass2, kClass));
    }

    public static final boolean isSuperclassOf(KClass<?> kClass, KClass<?> derived) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        Intrinsics.checkNotNullParameter(derived, "derived");
        return isSubclassOf(derived, kClass);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> T cast(KClass<T> kClass, Object obj) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        if (!kClass.isInstance(obj)) {
            throw new TypeCastException("Value cannot be cast to " + kClass.getQualifiedName());
        }
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type T of kotlin.reflect.full.KClasses.cast");
        return obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> T safeCast(KClass<T> kClass, Object obj) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        if (!kClass.isInstance(obj)) {
            return null;
        }
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type T of kotlin.reflect.full.KClasses.safeCast");
        return obj;
    }

    public static final <T> T createInstance(KClass<T> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        Iterator<T> it = kClass.getConstructors().iterator();
        T t = null;
        boolean z = false;
        T t2 = null;
        while (true) {
            if (!it.hasNext()) {
                if (!z) {
                    break;
                }
                t = t2;
                break;
            }
            T next = it.next();
            List<KParameter> parameters = ((KFunction) next).getParameters();
            if (!(parameters instanceof Collection) || !parameters.isEmpty()) {
                Iterator<T> it2 = parameters.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        if (!((KParameter) it2.next()).isOptional()) {
                        }
                    }
                }
            }
            if (z) {
                break;
            }
            z = true;
            t2 = next;
        }
        KFunction kFunction = (KFunction) t;
        if (kFunction == null) {
            throw new IllegalArgumentException("Class should have a single no-arg constructor: " + kClass);
        }
        return (T) kFunction.callBy(MapsKt.emptyMap());
    }
}
