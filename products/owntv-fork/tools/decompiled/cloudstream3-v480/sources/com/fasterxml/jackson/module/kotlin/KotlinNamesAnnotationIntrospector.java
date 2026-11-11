package com.fasterxml.jackson.module.kotlin;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedConstructor;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.introspect.NopAnnotationIntrospector;
import com.fasterxml.jackson.databind.util.BeanUtil;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.reflect.KFunction;
import kotlin.reflect.KParameter;
import kotlin.reflect.KProperty1;
import kotlin.reflect.full.KClasses;
import kotlin.reflect.jvm.ReflectJvmMapping;
import kotlin.reflect.jvm.internal.KotlinReflectionInternalError;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: KotlinNamesAnnotationIntrospector.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0010\u0010\u0006\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b0\u0007¢\u0006\u0002\u0010\tJ\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J&\u0010\u0017\u001a\u0004\u0018\u00010\u00182\n\u0010\u0019\u001a\u0006\u0012\u0002\b\u00030\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0018H\u0016J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0012\u001a\u00020 H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001b\u0010\u0006\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006!"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/KotlinNamesAnnotationIntrospector;", "Lcom/fasterxml/jackson/databind/introspect/NopAnnotationIntrospector;", "module", "Lcom/fasterxml/jackson/module/kotlin/KotlinModule;", "cache", "Lcom/fasterxml/jackson/module/kotlin/ReflectionCache;", "ignoredClassesForImplyingJsonCreator", "", "Lkotlin/reflect/KClass;", "(Lcom/fasterxml/jackson/module/kotlin/KotlinModule;Lcom/fasterxml/jackson/module/kotlin/ReflectionCache;Ljava/util/Set;)V", "getCache", "()Lcom/fasterxml/jackson/module/kotlin/ReflectionCache;", "getIgnoredClassesForImplyingJsonCreator", "()Ljava/util/Set;", "getModule", "()Lcom/fasterxml/jackson/module/kotlin/KotlinModule;", "findImplicitPropertyName", "", "member", "Lcom/fasterxml/jackson/databind/introspect/AnnotatedMember;", "findKotlinParameterName", "param", "Lcom/fasterxml/jackson/databind/introspect/AnnotatedParameter;", "findRenameByField", "Lcom/fasterxml/jackson/databind/PropertyName;", "config", "Lcom/fasterxml/jackson/databind/cfg/MapperConfig;", "field", "Lcom/fasterxml/jackson/databind/introspect/AnnotatedField;", "implName", "hasCreatorAnnotation", "", "Lcom/fasterxml/jackson/databind/introspect/Annotated;", "jackson-module-kotlin"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class KotlinNamesAnnotationIntrospector extends NopAnnotationIntrospector {
    private final ReflectionCache cache;
    private final Set<KClass<?>> ignoredClassesForImplyingJsonCreator;
    private final KotlinModule module;

    /* JADX WARN: Multi-variable type inference failed */
    public KotlinNamesAnnotationIntrospector(KotlinModule module, ReflectionCache cache, Set<? extends KClass<?>> ignoredClassesForImplyingJsonCreator) {
        Intrinsics.checkNotNullParameter(module, "module");
        Intrinsics.checkNotNullParameter(cache, "cache");
        Intrinsics.checkNotNullParameter(ignoredClassesForImplyingJsonCreator, "ignoredClassesForImplyingJsonCreator");
        this.module = module;
        this.cache = cache;
        this.ignoredClassesForImplyingJsonCreator = ignoredClassesForImplyingJsonCreator;
    }

    public final ReflectionCache getCache() {
        return this.cache;
    }

    public final Set<KClass<?>> getIgnoredClassesForImplyingJsonCreator() {
        return this.ignoredClassesForImplyingJsonCreator;
    }

    public final KotlinModule getModule() {
        return this.module;
    }

    @Override // com.fasterxml.jackson.databind.AnnotationIntrospector
    public String findImplicitPropertyName(AnnotatedMember member) {
        String strSubstringAfter$default;
        Intrinsics.checkNotNullParameter(member, "member");
        if (!(member instanceof AnnotatedMethod)) {
            if (member instanceof AnnotatedParameter) {
                return findKotlinParameterName((AnnotatedParameter) member);
            }
            return null;
        }
        AnnotatedMethod annotatedMethod = (AnnotatedMethod) member;
        String name = annotatedMethod.getName();
        Intrinsics.checkNotNullExpressionValue(name, "member.name");
        if (!StringsKt.contains$default((CharSequence) name, '-', false, 2, (Object) null) || annotatedMethod.getParameterCount() != 0) {
            return null;
        }
        String name2 = annotatedMethod.getName();
        Intrinsics.checkNotNullExpressionValue(name2, "member.name");
        if (StringsKt.startsWith$default(name2, "get", false, 2, (Object) null)) {
            String name3 = annotatedMethod.getName();
            Intrinsics.checkNotNullExpressionValue(name3, "member.name");
            strSubstringAfter$default = StringsKt.substringAfter$default(name3, "get", (String) null, 2, (Object) null);
        } else {
            String name4 = annotatedMethod.getName();
            Intrinsics.checkNotNullExpressionValue(name4, "member.name");
            if (StringsKt.startsWith$default(name4, "is", false, 2, (Object) null)) {
                String name5 = annotatedMethod.getName();
                Intrinsics.checkNotNullExpressionValue(name5, "member.name");
                strSubstringAfter$default = StringsKt.substringAfter$default(name5, "is", (String) null, 2, (Object) null);
            } else {
                strSubstringAfter$default = null;
            }
        }
        if (strSubstringAfter$default == null) {
            return null;
        }
        if (strSubstringAfter$default.length() > 0) {
            StringBuilder sb = new StringBuilder();
            char cCharAt = strSubstringAfter$default.charAt(0);
            Locale locale = Locale.getDefault();
            Intrinsics.checkNotNullExpressionValue(locale, "getDefault()");
            sb.append(CharsKt.lowercase(cCharAt, locale).toString());
            String strSubstring = strSubstringAfter$default.substring(1);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.String).substring(startIndex)");
            sb.append(strSubstring);
            strSubstringAfter$default = sb.toString();
        }
        if (strSubstringAfter$default == null) {
            return null;
        }
        return StringsKt.substringBefore$default(strSubstringAfter$default, '-', (String) null, 2, (Object) null);
    }

    @Override // com.fasterxml.jackson.databind.AnnotationIntrospector
    public PropertyName findRenameByField(MapperConfig<?> config, AnnotatedField field, PropertyName implName) {
        String strStdManglePropertyName;
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(field, "field");
        Intrinsics.checkNotNullParameter(implName, "implName");
        String origSimple = implName.getSimpleName();
        Class<?> declaringClass = field.getDeclaringClass();
        Intrinsics.checkNotNullExpressionValue(declaringClass, "field.declaringClass");
        if (KotlinModuleKt.isKotlinClass(declaringClass)) {
            Intrinsics.checkNotNullExpressionValue(origSimple, "origSimple");
            if (StringsKt.startsWith$default(origSimple, "is", false, 2, (Object) null) && (strStdManglePropertyName = BeanUtil.stdManglePropertyName(origSimple, 2)) != null && !strStdManglePropertyName.equals(origSimple)) {
                return PropertyName.construct(strStdManglePropertyName);
            }
        }
        return null;
    }

    @Override // com.fasterxml.jackson.databind.AnnotationIntrospector
    public boolean hasCreatorAnnotation(final Annotated member) {
        Intrinsics.checkNotNullParameter(member, "member");
        if (!(member instanceof AnnotatedConstructor)) {
            return false;
        }
        AnnotatedConstructor annotatedConstructor = (AnnotatedConstructor) member;
        if (annotatedConstructor.getDeclaringClass().isEnum() || annotatedConstructor.getParameterCount() <= 0) {
            return false;
        }
        Class<?> declaringClass = annotatedConstructor.getDeclaringClass();
        Intrinsics.checkNotNullExpressionValue(declaringClass, "member.declaringClass");
        if (KotlinModuleKt.isKotlinClass(declaringClass)) {
            return this.cache.checkConstructorIsCreatorAnnotated(annotatedConstructor, new Function1<AnnotatedConstructor, Boolean>() { // from class: com.fasterxml.jackson.module.kotlin.KotlinNamesAnnotationIntrospector.hasCreatorAnnotation.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                /* JADX WARN: Code duplicated, block: B:42:0x00fb A[EDGE_INSN: B:42:0x00fb->B:75:0x0192 BREAK  A[LOOP:2: B:52:0x011d->B:121:0x011d]] */
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(AnnotatedConstructor it) {
                    boolean z;
                    Collection<KFunction<?>> declaredFunctions;
                    boolean z2;
                    int i;
                    Intrinsics.checkNotNullParameter(it, "it");
                    ReflectionCache cache = KotlinNamesAnnotationIntrospector.this.getCache();
                    Class<?> declaringClass2 = ((AnnotatedConstructor) member).getDeclaringClass();
                    if (declaringClass2 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type java.lang.Class<kotlin.Any>");
                    }
                    KClass<Object> kClassKotlinFromJava = cache.kotlinFromJava(declaringClass2);
                    ReflectionCache cache2 = KotlinNamesAnnotationIntrospector.this.getCache();
                    Constructor<?> annotated = ((AnnotatedConstructor) member).getAnnotated();
                    if (annotated == null) {
                        throw new NullPointerException("null cannot be cast to non-null type java.lang.reflect.Constructor<kotlin.Any>");
                    }
                    KFunction<Object> kFunctionKotlinFromJava = cache2.kotlinFromJava(annotated);
                    boolean z3 = false;
                    if (kFunctionKotlinFromJava != null) {
                        boolean z4 = Intrinsics.areEqual(KClasses.getPrimaryConstructor(kClassKotlinFromJava), kFunctionKotlinFromJava) || (KClasses.getPrimaryConstructor(kClassKotlinFromJava) == null && kClassKotlinFromJava.getConstructors().size() == 1);
                        Collection memberProperties = KClasses.getMemberProperties(kClassKotlinFromJava);
                        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(memberProperties, 10));
                        Iterator it2 = memberProperties.iterator();
                        while (it2.hasNext()) {
                            arrayList.add(((KProperty1) it2.next()).getName());
                        }
                        Set set = CollectionsKt.toSet(arrayList);
                        Collection<KFunction<?>> collectionInvoke$filterOutSingleStringCallables = invoke$filterOutSingleStringCallables(kClassKotlinFromJava.getConstructors(), set);
                        if (!(collectionInvoke$filterOutSingleStringCallables instanceof Collection) || !collectionInvoke$filterOutSingleStringCallables.isEmpty()) {
                            Iterator<T> it3 = collectionInvoke$filterOutSingleStringCallables.iterator();
                            loop5: while (true) {
                                if (!it3.hasNext()) {
                                    z = false;
                                    break;
                                }
                                List<Annotation> annotations = ((KFunction) it3.next()).getAnnotations();
                                if (!(annotations instanceof Collection) || !annotations.isEmpty()) {
                                    Iterator<T> it4 = annotations.iterator();
                                    while (it4.hasNext()) {
                                        if (Intrinsics.areEqual(JvmClassMappingKt.getJavaClass(JvmClassMappingKt.getAnnotationClass((Annotation) it4.next())), JsonCreator.class)) {
                                            z = true;
                                            break loop5;
                                        }
                                    }
                                }
                            }
                        } else {
                            z = false;
                            break;
                        }
                        Class<?> rawClass = ((AnnotatedConstructor) member).getType().getRawClass();
                        Intrinsics.checkNotNullExpressionValue(rawClass, "member.type.rawClass");
                        KClass<?> companionObject = KClasses.getCompanionObject(JvmClassMappingKt.getKotlinClass(rawClass));
                        if (companionObject != null && (declaredFunctions = KClasses.getDeclaredFunctions(companionObject)) != null) {
                            Collection<KFunction<?>> collectionInvoke$filterOutSingleStringCallables2 = invoke$filterOutSingleStringCallables(declaredFunctions, set);
                            if (!(collectionInvoke$filterOutSingleStringCallables2 instanceof Collection) || !collectionInvoke$filterOutSingleStringCallables2.isEmpty()) {
                                Iterator<T> it5 = collectionInvoke$filterOutSingleStringCallables2.iterator();
                                loop2: while (true) {
                                    if (!it5.hasNext()) {
                                        z2 = false;
                                        break;
                                    }
                                    KFunction kFunction = (KFunction) it5.next();
                                    List<Annotation> annotations2 = kFunction.getAnnotations();
                                    if (!(annotations2 instanceof Collection) || !annotations2.isEmpty()) {
                                        Iterator<T> it6 = annotations2.iterator();
                                        while (it6.hasNext()) {
                                            if (Intrinsics.areEqual(JvmClassMappingKt.getJavaClass(JvmClassMappingKt.getAnnotationClass((Annotation) it6.next())), JvmStatic.class)) {
                                                List<Annotation> annotations3 = kFunction.getAnnotations();
                                                if (!(annotations3 instanceof Collection) || !annotations3.isEmpty()) {
                                                    Iterator<T> it7 = annotations3.iterator();
                                                    while (it7.hasNext()) {
                                                        if (Intrinsics.areEqual(JvmClassMappingKt.getJavaClass(JvmClassMappingKt.getAnnotationClass((Annotation) it7.next())), JsonCreator.class)) {
                                                            z2 = true;
                                                            break loop2;
                                                        }
                                                    }
                                                    break;
                                                }
                                                break;
                                            }
                                        }
                                    }
                                }
                            } else {
                                z2 = false;
                                break;
                            }
                        } else {
                            z2 = false;
                            break;
                        }
                        int size = kFunctionKotlinFromJava.getParameters().size();
                        List<KParameter> parameters = kFunctionKotlinFromJava.getParameters();
                        if ((parameters instanceof Collection) && parameters.isEmpty()) {
                            i = 0;
                        } else {
                            Iterator<T> it8 = parameters.iterator();
                            i = 0;
                            while (it8.hasNext()) {
                                if (((KParameter) it8.next()).getName() != null && (i = i + 1) < 0) {
                                    CollectionsKt.throwCountOverflow();
                                }
                            }
                        }
                        boolean z5 = size == i;
                        boolean zInvoke$isPossibleSingleString = invoke$isPossibleSingleString(kFunctionKotlinFromJava, set);
                        if (z4 && !z && !z2 && z5 && !zInvoke$isPossibleSingleString && !KotlinNamesAnnotationIntrospector.this.getIgnoredClassesForImplyingJsonCreator().contains(kClassKotlinFromJava)) {
                            z3 = true;
                        }
                    }
                    return Boolean.valueOf(z3);
                }

                private static final boolean invoke$isPossibleSingleString(KFunction<?> kFunction, Set<String> set) {
                    if (kFunction.getParameters().size() == 1 && !CollectionsKt.contains(set, kFunction.getParameters().get(0).getName()) && Intrinsics.areEqual(ReflectJvmMapping.getJavaType(kFunction.getParameters().get(0).getType()), String.class)) {
                        List<Annotation> annotations = kFunction.getParameters().get(0).getAnnotations();
                        if (!(annotations instanceof Collection) || !annotations.isEmpty()) {
                            Iterator<T> it = annotations.iterator();
                            while (it.hasNext()) {
                                if (Intrinsics.areEqual(JvmClassMappingKt.getJavaClass(JvmClassMappingKt.getAnnotationClass((Annotation) it.next())), JsonProperty.class)) {
                                }
                            }
                        }
                        return true;
                    }
                    return false;
                }

                private static final Collection<KFunction<?>> invoke$filterOutSingleStringCallables(Collection<? extends KFunction<?>> collection, Set<String> set) {
                    ArrayList arrayList = new ArrayList();
                    for (Object obj : collection) {
                        if (!invoke$isPossibleSingleString((KFunction) obj, set)) {
                            arrayList.add(obj);
                        }
                    }
                    return arrayList;
                }
            });
        }
        return false;
    }

    private final String findKotlinParameterName(AnnotatedParameter param) {
        List<KParameter> parameters;
        KParameter kParameter;
        List<KParameter> parameters2;
        List<KParameter> parameters3;
        KParameter kParameter2;
        List<KParameter> parameters4;
        KParameter kParameter3;
        List<KParameter> parameters5;
        Class<?> declaringClass = param.getDeclaringClass();
        Intrinsics.checkNotNullExpressionValue(declaringClass, "param.declaringClass");
        if (KotlinModuleKt.isKotlinClass(declaringClass)) {
            Member member = param.getOwner().getMember();
            int size = 0;
            if (member instanceof Constructor) {
                Constructor constructor = (Constructor) member;
                int length = constructor.getParameterTypes().length;
                try {
                    KFunction kotlinFunction = ReflectJvmMapping.getKotlinFunction(constructor);
                    if (kotlinFunction != null && (parameters5 = kotlinFunction.getParameters()) != null) {
                        size = parameters5.size();
                    }
                } catch (UnsupportedOperationException | KotlinReflectionInternalError unused) {
                }
                if (size > 0 && size == length) {
                    KFunction kotlinFunction2 = ReflectJvmMapping.getKotlinFunction(constructor);
                    if (kotlinFunction2 == null || (parameters4 = kotlinFunction2.getParameters()) == null || (kParameter3 = parameters4.get(param.getIndex())) == null) {
                        return null;
                    }
                    return kParameter3.getName();
                }
                return null;
            }
            if (member instanceof Method) {
                try {
                    KFunction<?> kotlinFunction3 = ReflectJvmMapping.getKotlinFunction((Method) member);
                    int index = ((kotlinFunction3 != null && (parameters = kotlinFunction3.getParameters()) != null && (kParameter = (KParameter) CollectionsKt.firstOrNull((List) parameters)) != null) ? kParameter.getKind() : null) != KParameter.Kind.VALUE ? param.getIndex() + 1 : param.getIndex();
                    if (kotlinFunction3 != null && (parameters2 = kotlinFunction3.getParameters()) != null) {
                        size = parameters2.size();
                    }
                    if (size <= index) {
                        return null;
                    }
                    if (kotlinFunction3 != null && (parameters3 = kotlinFunction3.getParameters()) != null && (kParameter2 = parameters3.get(index)) != null) {
                        return kParameter2.getName();
                    }
                    return null;
                } catch (KotlinReflectionInternalError unused2) {
                    return null;
                }
            }
            return null;
        }
        return null;
    }
}
