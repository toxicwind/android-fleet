package kotlin.reflect.jvm.internal.impl.load.java;

import j$.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import kotlin.Pair;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.MutabilityQualifier;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.WithMigrationStatus;
import kotlin.reflect.jvm.internal.impl.name.FqName;

/* JADX INFO: compiled from: AbstractAnnotationTypeQualifierResolver.kt */
/* JADX INFO: loaded from: classes7.dex */
public abstract class AbstractAnnotationTypeQualifierResolver<TAnnotation> {
    private static final Companion Companion = new Companion(null);
    private static final Map<String, AnnotationQualifierApplicabilityType> JAVA_APPLICABILITY_TYPES;
    private final JavaTypeEnhancementState javaTypeEnhancementState;
    private final ConcurrentHashMap<Object, TAnnotation> resolvedNicknames;

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean extractDefaultQualifiers$lambda$1(Object extractNullability) {
        Intrinsics.checkNotNullParameter(extractNullability, "$this$extractNullability");
        return false;
    }

    protected abstract Iterable<String> enumArguments(TAnnotation tannotation, boolean z);

    protected abstract FqName getFqName(TAnnotation tannotation);

    protected abstract Object getKey(TAnnotation tannotation);

    protected abstract Iterable<TAnnotation> getMetaAnnotations(TAnnotation tannotation);

    public abstract boolean isK2();

    public AbstractAnnotationTypeQualifierResolver(JavaTypeEnhancementState javaTypeEnhancementState) {
        Intrinsics.checkNotNullParameter(javaTypeEnhancementState, "javaTypeEnhancementState");
        this.javaTypeEnhancementState = javaTypeEnhancementState;
        this.resolvedNicknames = new ConcurrentHashMap<>();
    }

    private final TAnnotation findAnnotation(TAnnotation tannotation, FqName fqName) {
        for (TAnnotation tannotation2 : getMetaAnnotations(tannotation)) {
            if (Intrinsics.areEqual(getFqName(tannotation2), fqName)) {
                return tannotation2;
            }
        }
        return null;
    }

    private final boolean hasAnnotation(TAnnotation tannotation, FqName fqName) {
        Iterable<TAnnotation> metaAnnotations = getMetaAnnotations(tannotation);
        if ((metaAnnotations instanceof Collection) && ((Collection) metaAnnotations).isEmpty()) {
            return false;
        }
        Iterator<TAnnotation> it = metaAnnotations.iterator();
        while (it.hasNext()) {
            if (Intrinsics.areEqual(getFqName(it.next()), fqName)) {
                return true;
            }
        }
        return false;
    }

    public final TAnnotation resolveTypeQualifierAnnotation(TAnnotation annotation) {
        TAnnotation tannotationResolveTypeQualifierAnnotation;
        Intrinsics.checkNotNullParameter(annotation, "annotation");
        if (this.javaTypeEnhancementState.getJsr305().isDisabled()) {
            return null;
        }
        if (CollectionsKt.contains(JvmAnnotationNamesKt.getBUILT_IN_TYPE_QUALIFIER_ANNOTATIONS(), getFqName(annotation)) || hasAnnotation(annotation, JvmAnnotationNamesKt.getJAVAX_TYPE_QUALIFIER_ANNOTATION_FQ_NAME())) {
            return annotation;
        }
        if (!hasAnnotation(annotation, JvmAnnotationNamesKt.getJAVAX_TYPE_QUALIFIER_NICKNAME_ANNOTATION_FQ_NAME())) {
            return null;
        }
        ConcurrentHashMap<Object, TAnnotation> concurrentHashMap = this.resolvedNicknames;
        Object key = getKey(annotation);
        TAnnotation tannotation = concurrentHashMap.get(key);
        if (tannotation != null) {
            return tannotation;
        }
        Iterator<TAnnotation> it = getMetaAnnotations(annotation).iterator();
        do {
            if (!it.hasNext()) {
                tannotationResolveTypeQualifierAnnotation = null;
                break;
            }
            tannotationResolveTypeQualifierAnnotation = resolveTypeQualifierAnnotation(it.next());
        } while (tannotationResolveTypeQualifierAnnotation == null);
        if (tannotationResolveTypeQualifierAnnotation == null) {
            return null;
        }
        TAnnotation tannotationPutIfAbsent = concurrentHashMap.putIfAbsent(key, tannotationResolveTypeQualifierAnnotation);
        return tannotationPutIfAbsent == null ? tannotationResolveTypeQualifierAnnotation : tannotationPutIfAbsent;
    }

    private final JavaDefaultQualifiers resolveQualifierBuiltInDefaultAnnotation(TAnnotation tannotation, boolean z) {
        JavaDefaultQualifiers javaDefaultQualifiers;
        if (this.javaTypeEnhancementState.getDisabledDefaultAnnotations() || (javaDefaultQualifiers = JavaDefaultQualifiersKt.getBUILT_IN_TYPE_QUALIFIER_DEFAULT_ANNOTATIONS().get(getFqName(tannotation))) == null) {
            return null;
        }
        ReportLevel reportLevelResolveDefaultAnnotationState = resolveDefaultAnnotationState(tannotation);
        if (reportLevelResolveDefaultAnnotationState == ReportLevel.IGNORE) {
            reportLevelResolveDefaultAnnotationState = null;
        }
        if (reportLevelResolveDefaultAnnotationState == null) {
            return null;
        }
        return JavaDefaultQualifiers.copy$default(javaDefaultQualifiers, WithMigrationStatus.copy$default(javaDefaultQualifiers.getNullabilityQualifier(), null, z || reportLevelResolveDefaultAnnotationState.isWarning(), 1, null), null, false, false, false, 30, null);
    }

    private final ReportLevel resolveDefaultAnnotationState(TAnnotation tannotation) {
        FqName fqName = getFqName(tannotation);
        if (fqName != null && JavaDefaultQualifiersKt.getJSPECIFY_DEFAULT_ANNOTATIONS().containsKey(fqName)) {
            return this.javaTypeEnhancementState.getGetReportLevelForAnnotation().invoke(fqName);
        }
        return resolveJsr305AnnotationState(tannotation);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final Set<AnnotationQualifierApplicabilityType> allIfTypeUse(Set<? extends AnnotationQualifierApplicabilityType> set) {
        return set.contains(AnnotationQualifierApplicabilityType.TYPE_USE) ? SetsKt.plus(SetsKt.minus((Set<? extends AnnotationQualifierApplicabilityType>) ArraysKt.toSet(AnnotationQualifierApplicabilityType.values()), AnnotationQualifierApplicabilityType.TYPE_PARAMETER_BOUNDS), (Iterable) set) : set;
    }

    private final Pair<TAnnotation, Set<AnnotationQualifierApplicabilityType>> resolveTypeQualifierDefaultAnnotation(TAnnotation tannotation) {
        TAnnotation tannotationFindAnnotation;
        TAnnotation next;
        if (this.javaTypeEnhancementState.getJsr305().isDisabled() || (tannotationFindAnnotation = findAnnotation(tannotation, JvmAnnotationNamesKt.getJAVAX_TYPE_QUALIFIER_DEFAULT_ANNOTATION_FQ_NAME())) == null) {
            return null;
        }
        Iterator<TAnnotation> it = getMetaAnnotations(tannotation).iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (resolveTypeQualifierAnnotation(next) == null);
        if (next == null) {
            return null;
        }
        Iterable<String> iterableEnumArguments = enumArguments(tannotationFindAnnotation, true);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<String> it2 = iterableEnumArguments.iterator();
        while (it2.hasNext()) {
            AnnotationQualifierApplicabilityType annotationQualifierApplicabilityType = JAVA_APPLICABILITY_TYPES.get(it2.next());
            if (annotationQualifierApplicabilityType != null) {
                linkedHashSet.add(annotationQualifierApplicabilityType);
            }
        }
        return new Pair<>(next, allIfTypeUse(linkedHashSet));
    }

    public final boolean isTypeUseAnnotation(TAnnotation annotation) {
        Intrinsics.checkNotNullParameter(annotation, "annotation");
        TAnnotation tannotationFindAnnotation = findAnnotation(annotation, StandardNames.FqNames.target);
        if (tannotationFindAnnotation == null) {
            return false;
        }
        Iterable<String> iterableEnumArguments = enumArguments(tannotationFindAnnotation, false);
        if ((iterableEnumArguments instanceof Collection) && ((Collection) iterableEnumArguments).isEmpty()) {
            return false;
        }
        Iterator<String> it = iterableEnumArguments.iterator();
        while (it.hasNext()) {
            if (Intrinsics.areEqual(it.next(), "TYPE")) {
                return true;
            }
        }
        return false;
    }

    private final ReportLevel resolveJsr305AnnotationState(TAnnotation tannotation) {
        ReportLevel reportLevelResolveJsr305CustomState = resolveJsr305CustomState(tannotation);
        return reportLevelResolveJsr305CustomState != null ? reportLevelResolveJsr305CustomState : this.javaTypeEnhancementState.getJsr305().getGlobalLevel();
    }

    private final ReportLevel resolveJsr305CustomState(TAnnotation tannotation) {
        Iterable<String> iterableEnumArguments;
        String str;
        ReportLevel reportLevel = this.javaTypeEnhancementState.getJsr305().getUserDefinedLevelForSpecificAnnotation().get(getFqName(tannotation));
        if (reportLevel != null) {
            return reportLevel;
        }
        TAnnotation tannotationFindAnnotation = findAnnotation(tannotation, JvmAnnotationNamesKt.getUNDER_MIGRATION_ANNOTATION_FQ_NAME());
        if (tannotationFindAnnotation == null || (iterableEnumArguments = enumArguments(tannotationFindAnnotation, false)) == null || (str = (String) CollectionsKt.firstOrNull(iterableEnumArguments)) == null) {
            return null;
        }
        ReportLevel migrationLevel = this.javaTypeEnhancementState.getJsr305().getMigrationLevel();
        if (migrationLevel != null) {
            return migrationLevel;
        }
        int iHashCode = str.hashCode();
        if (iHashCode != -2137067054) {
            if (iHashCode != -1838656823) {
                if (iHashCode == 2656902 && str.equals("WARN")) {
                    return ReportLevel.WARN;
                }
            } else if (str.equals("STRICT")) {
                return ReportLevel.STRICT;
            }
        } else if (str.equals("IGNORE")) {
            return ReportLevel.IGNORE;
        }
        return null;
    }

    private final WithMigrationStatus<NullabilityQualifier> extractNullability(TAnnotation tannotation, Function1<? super TAnnotation, Boolean> function1) {
        WithMigrationStatus<NullabilityQualifier> withMigrationStatusKnownNullability;
        WithMigrationStatus<NullabilityQualifier> withMigrationStatusKnownNullability2 = knownNullability(tannotation, function1.invoke(tannotation).booleanValue());
        if (withMigrationStatusKnownNullability2 != null) {
            return withMigrationStatusKnownNullability2;
        }
        TAnnotation tannotationResolveTypeQualifierAnnotation = resolveTypeQualifierAnnotation(tannotation);
        if (tannotationResolveTypeQualifierAnnotation == null) {
            return null;
        }
        ReportLevel reportLevelResolveJsr305AnnotationState = resolveJsr305AnnotationState(tannotation);
        if (reportLevelResolveJsr305AnnotationState.isIgnore() || (withMigrationStatusKnownNullability = knownNullability(tannotationResolveTypeQualifierAnnotation, function1.invoke(tannotationResolveTypeQualifierAnnotation).booleanValue())) == null) {
            return null;
        }
        return WithMigrationStatus.copy$default(withMigrationStatusKnownNullability, null, reportLevelResolveJsr305AnnotationState.isWarning(), 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final WithMigrationStatus extractNullability$lambda$2(AbstractAnnotationTypeQualifierResolver abstractAnnotationTypeQualifierResolver, Function1 function1, Object it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return abstractAnnotationTypeQualifierResolver.extractNullability(it, (Function1<? super Object, Boolean>) function1);
    }

    public final WithMigrationStatus<NullabilityQualifier> extractNullability(Iterable<? extends TAnnotation> annotations, final Function1<? super TAnnotation, Boolean> forceWarning) {
        Intrinsics.checkNotNullParameter(annotations, "annotations");
        Intrinsics.checkNotNullParameter(forceWarning, "forceWarning");
        return extractQualifier(annotations, new Function1(this, forceWarning) { // from class: kotlin.reflect.jvm.internal.impl.load.java.AbstractAnnotationTypeQualifierResolver$$Lambda$0
            private final AbstractAnnotationTypeQualifierResolver arg$0;
            private final Function1 arg$1;

            {
                this.arg$0 = this;
                this.arg$1 = forceWarning;
            }

            @Override // kotlin.jvm.functions.Function1
            public Object invoke(Object obj) {
                return AbstractAnnotationTypeQualifierResolver.extractNullability$lambda$2(this.arg$0, this.arg$1, obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final WithMigrationStatus<MutabilityQualifier> extractMutability(TAnnotation tannotation) {
        MutabilityQualifier mutabilityQualifier;
        FqName fqName = getFqName(tannotation);
        if (fqName == null) {
            return null;
        }
        if (JvmAnnotationNamesKt.getREAD_ONLY_ANNOTATIONS().contains(fqName)) {
            mutabilityQualifier = MutabilityQualifier.READ_ONLY;
        } else {
            if (!JvmAnnotationNamesKt.getMUTABLE_ANNOTATIONS().contains(fqName)) {
                return null;
            }
            mutabilityQualifier = MutabilityQualifier.MUTABLE;
        }
        ReportLevel reportLevelInvoke = this.javaTypeEnhancementState.getGetReportLevelForAnnotation().invoke(fqName);
        if (reportLevelInvoke.isIgnore()) {
            return null;
        }
        if (!reportLevelInvoke.isWarning() || isK2()) {
            return new WithMigrationStatus<>(mutabilityQualifier, reportLevelInvoke.isWarning());
        }
        return null;
    }

    /* JADX INFO: renamed from: kotlin.reflect.jvm.internal.impl.load.java.AbstractAnnotationTypeQualifierResolver$extractMutability$1, reason: invalid class name */
    /* JADX INFO: compiled from: AbstractAnnotationTypeQualifierResolver.kt */
    static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<TAnnotation, WithMigrationStatus<MutabilityQualifier>> {
        AnonymousClass1(Object obj) {
            super(1, obj, AbstractAnnotationTypeQualifierResolver.class, "extractMutability", "extractMutability(Ljava/lang/Object;)Lorg/jetbrains/kotlin/load/java/typeEnhancement/WithMigrationStatus;", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public final WithMigrationStatus<MutabilityQualifier> invoke(TAnnotation p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return ((AbstractAnnotationTypeQualifierResolver) this.receiver).extractMutability(p0);
        }
    }

    public final WithMigrationStatus<MutabilityQualifier> extractMutability(Iterable<? extends TAnnotation> annotations) {
        Intrinsics.checkNotNullParameter(annotations, "annotations");
        return extractQualifier(annotations, new AnonymousClass1(this));
    }

    private final JavaDefaultQualifiers extractDefaultQualifiers(TAnnotation tannotation, boolean z) {
        WithMigrationStatus<NullabilityQualifier> withMigrationStatusExtractNullability;
        JavaDefaultQualifiers javaDefaultQualifiersResolveQualifierBuiltInDefaultAnnotation = resolveQualifierBuiltInDefaultAnnotation(tannotation, z);
        if (javaDefaultQualifiersResolveQualifierBuiltInDefaultAnnotation != null) {
            return javaDefaultQualifiersResolveQualifierBuiltInDefaultAnnotation;
        }
        Pair<TAnnotation, Set<AnnotationQualifierApplicabilityType>> pairResolveTypeQualifierDefaultAnnotation = resolveTypeQualifierDefaultAnnotation(tannotation);
        if (pairResolveTypeQualifierDefaultAnnotation == null) {
            return null;
        }
        TAnnotation tannotationComponent1 = pairResolveTypeQualifierDefaultAnnotation.component1();
        Set<AnnotationQualifierApplicabilityType> setComponent2 = pairResolveTypeQualifierDefaultAnnotation.component2();
        ReportLevel reportLevelResolveJsr305CustomState = resolveJsr305CustomState(tannotation);
        if (reportLevelResolveJsr305CustomState == null) {
            reportLevelResolveJsr305CustomState = resolveJsr305AnnotationState(tannotationComponent1);
        }
        if (reportLevelResolveJsr305CustomState.isIgnore() || (withMigrationStatusExtractNullability = extractNullability(tannotationComponent1, new Function1() { // from class: kotlin.reflect.jvm.internal.impl.load.java.AbstractAnnotationTypeQualifierResolver$$Lambda$2
            @Override // kotlin.jvm.functions.Function1
            public Object invoke(Object obj) {
                return Boolean.valueOf(AbstractAnnotationTypeQualifierResolver.extractDefaultQualifiers$lambda$1(obj));
            }
        })) == null) {
            return null;
        }
        return new JavaDefaultQualifiers(WithMigrationStatus.copy$default(withMigrationStatusExtractNullability, null, z || reportLevelResolveJsr305CustomState.isWarning(), 1, null), setComponent2, false, false, false, 28, null);
    }

    public static /* synthetic */ JavaTypeQualifiersByElementType extractAndMergeDefaultQualifiers$default(AbstractAnnotationTypeQualifierResolver abstractAnnotationTypeQualifierResolver, JavaTypeQualifiersByElementType javaTypeQualifiersByElementType, Iterable iterable, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: extractAndMergeDefaultQualifiers");
        }
        if ((i & 4) != 0) {
            z = false;
        }
        return abstractAnnotationTypeQualifierResolver.extractAndMergeDefaultQualifiers(javaTypeQualifiersByElementType, iterable, z);
    }

    public final JavaTypeQualifiersByElementType extractAndMergeDefaultQualifiers(JavaTypeQualifiersByElementType javaTypeQualifiersByElementType, Iterable<? extends TAnnotation> annotations, boolean z) {
        EnumMap<AnnotationQualifierApplicabilityType, JavaDefaultQualifiers> defaultQualifiers;
        Intrinsics.checkNotNullParameter(annotations, "annotations");
        if (!this.javaTypeEnhancementState.getDisabledDefaultAnnotations()) {
            ArrayList arrayList = new ArrayList();
            Iterator<? extends TAnnotation> it = annotations.iterator();
            while (it.hasNext()) {
                JavaDefaultQualifiers javaDefaultQualifiersExtractDefaultQualifiers = extractDefaultQualifiers(it.next(), z);
                if (javaDefaultQualifiersExtractDefaultQualifiers != null) {
                    arrayList.add(javaDefaultQualifiersExtractDefaultQualifiers);
                }
            }
            ArrayList<JavaDefaultQualifiers> arrayList2 = arrayList;
            if (!arrayList2.isEmpty()) {
                EnumMap enumMap = new EnumMap(AnnotationQualifierApplicabilityType.class);
                for (JavaDefaultQualifiers javaDefaultQualifiers : arrayList2) {
                    for (AnnotationQualifierApplicabilityType annotationQualifierApplicabilityType : javaDefaultQualifiers.getQualifierApplicabilityTypes()) {
                        EnumMap enumMap2 = enumMap;
                        if (!enumMap2.containsKey(annotationQualifierApplicabilityType) || !isK2()) {
                            enumMap2.put(annotationQualifierApplicabilityType, javaDefaultQualifiers);
                        } else {
                            JavaDefaultQualifiers javaDefaultQualifiers2 = (JavaDefaultQualifiers) enumMap.get(annotationQualifierApplicabilityType);
                            if (javaDefaultQualifiers2 != null) {
                                WithMigrationStatus<NullabilityQualifier> nullabilityQualifier = javaDefaultQualifiers2.getNullabilityQualifier();
                                WithMigrationStatus<NullabilityQualifier> nullabilityQualifier2 = javaDefaultQualifiers.getNullabilityQualifier();
                                if (!Intrinsics.areEqual(nullabilityQualifier2, nullabilityQualifier) && (!nullabilityQualifier2.isForWarningOnly() || nullabilityQualifier.isForWarningOnly())) {
                                    javaDefaultQualifiers2 = (nullabilityQualifier2.isForWarningOnly() || !nullabilityQualifier.isForWarningOnly()) ? null : javaDefaultQualifiers;
                                }
                                enumMap2.put(annotationQualifierApplicabilityType, javaDefaultQualifiers2);
                            }
                        }
                    }
                }
                EnumMap enumMap3 = (javaTypeQualifiersByElementType == null || (defaultQualifiers = javaTypeQualifiersByElementType.getDefaultQualifiers()) == null) ? new EnumMap(AnnotationQualifierApplicabilityType.class) : new EnumMap((EnumMap) defaultQualifiers);
                boolean z2 = false;
                for (Map.Entry entry : enumMap.entrySet()) {
                    AnnotationQualifierApplicabilityType annotationQualifierApplicabilityType2 = (AnnotationQualifierApplicabilityType) entry.getKey();
                    JavaDefaultQualifiers javaDefaultQualifiers3 = (JavaDefaultQualifiers) entry.getValue();
                    if (javaDefaultQualifiers3 != null) {
                        enumMap3.put(annotationQualifierApplicabilityType2, javaDefaultQualifiers3);
                        z2 = true;
                    }
                }
                if (z2) {
                    return new JavaTypeQualifiersByElementType(enumMap3);
                }
            }
        }
        return javaTypeQualifiersByElementType;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0068, code lost:
    
        if (r5.equals("ALWAYS") != false) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x007d, code lost:
    
        if (r5.equals("NEVER") == false) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0086, code lost:
    
        if (r5.equals("MAYBE") == false) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0089, code lost:
    
        r5 = kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier.NULLABLE;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.WithMigrationStatus<kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier> knownNullability(TAnnotation r6, boolean r7) {
        /*
            r5 = this;
            kotlin.reflect.jvm.internal.impl.name.FqName r0 = r5.getFqName(r6)
            r1 = 0
            if (r0 != 0) goto L8
            return r1
        L8:
            kotlin.reflect.jvm.internal.impl.load.java.JavaTypeEnhancementState r2 = r5.javaTypeEnhancementState
            kotlin.jvm.functions.Function1 r2 = r2.getGetReportLevelForAnnotation()
            java.lang.Object r2 = r2.invoke(r0)
            kotlin.reflect.jvm.internal.impl.load.java.ReportLevel r2 = (kotlin.reflect.jvm.internal.impl.load.java.ReportLevel) r2
            boolean r3 = r2.isIgnore()
            if (r3 == 0) goto L1b
            return r1
        L1b:
            java.util.Set r3 = kotlin.reflect.jvm.internal.impl.load.java.JvmAnnotationNamesKt.getNOT_NULL_ANNOTATIONS()
            boolean r3 = r3.contains(r0)
            r4 = 0
            if (r3 == 0) goto L2a
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier r5 = kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier.NOT_NULL
            goto L8f
        L2a:
            java.util.Set r3 = kotlin.reflect.jvm.internal.impl.load.java.JvmAnnotationNamesKt.getNULLABLE_ANNOTATIONS()
            boolean r3 = r3.contains(r0)
            if (r3 == 0) goto L37
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier r5 = kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier.NULLABLE
            goto L8f
        L37:
            java.util.Set r3 = kotlin.reflect.jvm.internal.impl.load.java.JvmAnnotationNamesKt.getFORCE_FLEXIBILITY_ANNOTATIONS()
            boolean r3 = r3.contains(r0)
            if (r3 == 0) goto L44
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier r5 = kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier.FORCE_FLEXIBILITY
            goto L8f
        L44:
            kotlin.reflect.jvm.internal.impl.name.FqName r3 = kotlin.reflect.jvm.internal.impl.load.java.JvmAnnotationNamesKt.getJAVAX_NONNULL_ANNOTATION_FQ_NAME()
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r3)
            if (r0 == 0) goto L9e
            java.lang.Iterable r5 = r5.enumArguments(r6, r4)
            java.lang.Object r5 = kotlin.collections.CollectionsKt.firstOrNull(r5)
            java.lang.String r5 = (java.lang.String) r5
            if (r5 == 0) goto L8d
            int r6 = r5.hashCode()
            switch(r6) {
                case 73135176: goto L80;
                case 74175084: goto L77;
                case 433141802: goto L6b;
                case 1933739535: goto L62;
                default: goto L61;
            }
        L61:
            goto L8c
        L62:
            java.lang.String r6 = "ALWAYS"
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L8c
            goto L8d
        L6b:
            java.lang.String r6 = "UNKNOWN"
            boolean r5 = r5.equals(r6)
            if (r5 != 0) goto L74
            goto L8c
        L74:
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier r5 = kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier.FORCE_FLEXIBILITY
            goto L8f
        L77:
            java.lang.String r6 = "NEVER"
            boolean r5 = r5.equals(r6)
            if (r5 != 0) goto L89
            goto L8c
        L80:
            java.lang.String r6 = "MAYBE"
            boolean r5 = r5.equals(r6)
            if (r5 != 0) goto L89
            goto L8c
        L89:
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier r5 = kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier.NULLABLE
            goto L8f
        L8c:
            return r1
        L8d:
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier r5 = kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier.NOT_NULL
        L8f:
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.WithMigrationStatus r6 = new kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.WithMigrationStatus
            boolean r0 = r2.isWarning()
            if (r0 != 0) goto L99
            if (r7 == 0) goto L9a
        L99:
            r4 = 1
        L9a:
            r6.<init>(r5, r4)
            return r6
        L9e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.java.AbstractAnnotationTypeQualifierResolver.knownNullability(java.lang.Object, boolean):kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.WithMigrationStatus");
    }

    /* JADX INFO: compiled from: AbstractAnnotationTypeQualifierResolver.kt */
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    static {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (AnnotationQualifierApplicabilityType annotationQualifierApplicabilityType : AnnotationQualifierApplicabilityType.values()) {
            String javaTarget = annotationQualifierApplicabilityType.getJavaTarget();
            if (linkedHashMap.get(javaTarget) == null) {
                linkedHashMap.put(javaTarget, annotationQualifierApplicabilityType);
            }
        }
        JAVA_APPLICABILITY_TYPES = linkedHashMap;
    }

    private final <TQualifier> WithMigrationStatus<TQualifier> extractQualifier(Iterable<? extends TAnnotation> iterable, Function1<? super TAnnotation, WithMigrationStatus<TQualifier>> function1) {
        Iterator<? extends TAnnotation> it = iterable.iterator();
        WithMigrationStatus<TQualifier> withMigrationStatus = null;
        while (it.hasNext()) {
            WithMigrationStatus<TQualifier> withMigrationStatusInvoke = function1.invoke(it.next());
            if (withMigrationStatus != null) {
                if (withMigrationStatusInvoke != null && !Intrinsics.areEqual(withMigrationStatusInvoke, withMigrationStatus) && (!withMigrationStatusInvoke.isForWarningOnly() || withMigrationStatus.isForWarningOnly())) {
                    if (withMigrationStatusInvoke.isForWarningOnly() || !withMigrationStatus.isForWarningOnly()) {
                        return null;
                    }
                }
            }
            withMigrationStatus = withMigrationStatusInvoke;
        }
        return withMigrationStatus;
    }
}
