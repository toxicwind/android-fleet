package kotlin.reflect.jvm.internal.impl.load.kotlin;

import androidx.compose.material3.internal.TextFieldImplKt;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.SpecialJvmAnnotations;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationLoader.AnnotationsContainer;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.MetadataVersion;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoBufUtilKt;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoTypeTableUtilKt;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.ClassMapperLite;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmProtoBufUtil;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationLoader;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoContainer;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: AbstractBinaryClassAnnotationLoader.kt */
/* JADX INFO: loaded from: classes7.dex */
public abstract class AbstractBinaryClassAnnotationLoader<A, S extends AnnotationsContainer<? extends A>> implements AnnotationLoader<A> {
    public static final Companion Companion = new Companion(null);
    private final KotlinClassFinder kotlinClassFinder;

    /* JADX INFO: compiled from: AbstractBinaryClassAnnotationLoader.kt */
    public static abstract class AnnotationsContainer<A> {
        public abstract Map<MemberSignature, List<A>> getMemberAnnotations();
    }

    /* JADX INFO: compiled from: AbstractBinaryClassAnnotationLoader.kt */
    private enum PropertyRelatedElement {
        PROPERTY,
        BACKING_FIELD,
        DELEGATE_FIELD;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    }

    /* JADX INFO: compiled from: AbstractBinaryClassAnnotationLoader.kt */
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[AnnotatedCallableKind.values().length];
            try {
                iArr[AnnotatedCallableKind.PROPERTY_GETTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[AnnotatedCallableKind.PROPERTY_SETTER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[AnnotatedCallableKind.PROPERTY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    protected abstract S getAnnotationsContainer(KotlinJvmBinaryClass kotlinJvmBinaryClass);

    protected byte[] getCachedFileContent(KotlinJvmBinaryClass kotlinClass) {
        Intrinsics.checkNotNullParameter(kotlinClass, "kotlinClass");
        return null;
    }

    public abstract MetadataVersion getMetadataVersion();

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationLoader
    public abstract A loadAnnotation(ProtoBuf.Annotation annotation, NameResolver nameResolver);

    protected abstract KotlinJvmBinaryClass.AnnotationArgumentVisitor loadAnnotation(ClassId classId, SourceElement sourceElement, List<A> list);

    public AbstractBinaryClassAnnotationLoader(KotlinClassFinder kotlinClassFinder) {
        Intrinsics.checkNotNullParameter(kotlinClassFinder, "kotlinClassFinder");
        this.kotlinClassFinder = kotlinClassFinder;
    }

    protected final KotlinClassFinder getKotlinClassFinder() {
        return this.kotlinClassFinder;
    }

    protected final KotlinJvmBinaryClass.AnnotationArgumentVisitor loadAnnotationIfNotSpecial(ClassId annotationClassId, SourceElement source, List<A> result) {
        Intrinsics.checkNotNullParameter(annotationClassId, "annotationClassId");
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(result, "result");
        if (SpecialJvmAnnotations.INSTANCE.getSPECIAL_ANNOTATIONS().contains(annotationClassId)) {
            return null;
        }
        return loadAnnotation(annotationClassId, source, result);
    }

    private final KotlinJvmBinaryClass toBinaryClass(ProtoContainer.Class r2) {
        SourceElement source = r2.getSource();
        KotlinJvmBinarySourceElement kotlinJvmBinarySourceElement = source instanceof KotlinJvmBinarySourceElement ? (KotlinJvmBinarySourceElement) source : null;
        if (kotlinJvmBinarySourceElement != null) {
            return kotlinJvmBinarySourceElement.getBinaryClass();
        }
        return null;
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationLoader
    public List<A> loadClassAnnotations(ProtoContainer.Class container) {
        Intrinsics.checkNotNullParameter(container, "container");
        if (noAnnotationsInBytecode(container.getClassProto().getFlags())) {
            return CollectionsKt.emptyList();
        }
        KotlinJvmBinaryClass binaryClass = toBinaryClass(container);
        if (binaryClass == null) {
            throw new IllegalStateException(("Class for loading annotations is not found: " + container.debugFqName()).toString());
        }
        final ArrayList arrayList = new ArrayList(1);
        binaryClass.loadClassAnnotations(new KotlinJvmBinaryClass.AnnotationVisitor(this) { // from class: kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationLoader.loadClassAnnotations.1
            final /* synthetic */ AbstractBinaryClassAnnotationLoader<A, S> this$0;

            @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationVisitor
            public void visitEnd() {
            }

            {
                this.this$0 = this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationVisitor
            public KotlinJvmBinaryClass.AnnotationArgumentVisitor visitAnnotation(ClassId classId, SourceElement source) {
                Intrinsics.checkNotNullParameter(classId, "classId");
                Intrinsics.checkNotNullParameter(source, "source");
                return this.this$0.loadAnnotationIfNotSpecial(classId, source, arrayList);
            }
        }, getCachedFileContent(binaryClass));
        return arrayList;
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationLoader
    public List<A> loadCallableAnnotations(ProtoContainer container, MessageLite proto, AnnotatedCallableKind kind) {
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(proto, "proto");
        Intrinsics.checkNotNullParameter(kind, "kind");
        if (noAnnotationsInBytecode(getCallableAnnotationFlags(proto, kind))) {
            return CollectionsKt.emptyList();
        }
        if (kind == AnnotatedCallableKind.PROPERTY) {
            return loadPropertyAnnotations(container, (ProtoBuf.Property) proto, PropertyRelatedElement.PROPERTY);
        }
        MemberSignature callableSignature$default = getCallableSignature$default(this, proto, container.getNameResolver(), container.getTypeTable(), kind, false, 16, null);
        return callableSignature$default == null ? CollectionsKt.emptyList() : findClassAndLoadMemberAnnotations$default(this, container, callableSignature$default, false, false, null, false, 60, null);
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationLoader
    public List<A> loadPropertyBackingFieldAnnotations(final ProtoContainer container, final ProtoBuf.Property proto) {
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(proto, "proto");
        return loadAnnotationsIfPresentInBytecode(proto.getFlags(), new Function0(this, container, proto) { // from class: kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationLoader$$Lambda$0
            private final AbstractBinaryClassAnnotationLoader arg$0;
            private final ProtoContainer arg$1;
            private final ProtoBuf.Property arg$2;

            {
                this.arg$0 = this;
                this.arg$1 = container;
                this.arg$2 = proto;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return AbstractBinaryClassAnnotationLoader.loadPropertyBackingFieldAnnotations$lambda$0(this.arg$0, this.arg$1, this.arg$2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List loadPropertyBackingFieldAnnotations$lambda$0(AbstractBinaryClassAnnotationLoader abstractBinaryClassAnnotationLoader, ProtoContainer protoContainer, ProtoBuf.Property property) {
        return abstractBinaryClassAnnotationLoader.loadPropertyAnnotations(protoContainer, property, PropertyRelatedElement.BACKING_FIELD);
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationLoader
    public List<A> loadPropertyDelegateFieldAnnotations(final ProtoContainer container, final ProtoBuf.Property proto) {
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(proto, "proto");
        return loadAnnotationsIfPresentInBytecode(proto.getFlags(), new Function0(this, container, proto) { // from class: kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationLoader$$Lambda$1
            private final AbstractBinaryClassAnnotationLoader arg$0;
            private final ProtoContainer arg$1;
            private final ProtoBuf.Property arg$2;

            {
                this.arg$0 = this;
                this.arg$1 = container;
                this.arg$2 = proto;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return AbstractBinaryClassAnnotationLoader.loadPropertyDelegateFieldAnnotations$lambda$0(this.arg$0, this.arg$1, this.arg$2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List loadPropertyDelegateFieldAnnotations$lambda$0(AbstractBinaryClassAnnotationLoader abstractBinaryClassAnnotationLoader, ProtoContainer protoContainer, ProtoBuf.Property property) {
        return abstractBinaryClassAnnotationLoader.loadPropertyAnnotations(protoContainer, property, PropertyRelatedElement.DELEGATE_FIELD);
    }

    private final List<A> loadPropertyAnnotations(ProtoContainer protoContainer, ProtoBuf.Property property, PropertyRelatedElement propertyRelatedElement) {
        Boolean bool = Flags.IS_CONST.get(property.getFlags());
        Intrinsics.checkNotNullExpressionValue(bool, "get(...)");
        bool.booleanValue();
        boolean zIsMovedFromInterfaceCompanion = JvmProtoBufUtil.isMovedFromInterfaceCompanion(property);
        if (propertyRelatedElement == PropertyRelatedElement.PROPERTY) {
            MemberSignature propertySignature$default = AbstractBinaryClassAnnotationLoaderKt.getPropertySignature$default(property, protoContainer.getNameResolver(), protoContainer.getTypeTable(), false, true, false, 40, null);
            return propertySignature$default == null ? CollectionsKt.emptyList() : findClassAndLoadMemberAnnotations$default(this, protoContainer, propertySignature$default, true, false, bool, zIsMovedFromInterfaceCompanion, 8, null);
        }
        MemberSignature propertySignature$default2 = AbstractBinaryClassAnnotationLoaderKt.getPropertySignature$default(property, protoContainer.getNameResolver(), protoContainer.getTypeTable(), true, false, false, 48, null);
        if (propertySignature$default2 == null) {
            return CollectionsKt.emptyList();
        }
        return StringsKt.contains$default((CharSequence) propertySignature$default2.getSignature(), (CharSequence) "$delegate", false, 2, (Object) null) != (propertyRelatedElement == PropertyRelatedElement.DELEGATE_FIELD) ? CollectionsKt.emptyList() : findClassAndLoadMemberAnnotations(protoContainer, propertySignature$default2, true, true, bool, zIsMovedFromInterfaceCompanion);
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationLoader
    public List<A> loadEnumEntryAnnotations(ProtoContainer container, ProtoBuf.EnumEntry proto) {
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(proto, "proto");
        return findClassAndLoadMemberAnnotations$default(this, container, MemberSignature.Companion.fromFieldNameAndDesc(container.getNameResolver().getString(proto.getName()), ClassMapperLite.mapClass(((ProtoContainer.Class) container).getClassId().asString())), false, false, null, false, 60, null);
    }

    static /* synthetic */ List findClassAndLoadMemberAnnotations$default(AbstractBinaryClassAnnotationLoader abstractBinaryClassAnnotationLoader, ProtoContainer protoContainer, MemberSignature memberSignature, boolean z, boolean z2, Boolean bool, boolean z3, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: findClassAndLoadMemberAnnotations");
        }
        if ((i & 4) != 0) {
            z = false;
        }
        if ((i & 8) != 0) {
            z2 = false;
        }
        if ((i & 16) != 0) {
            bool = null;
        }
        if ((i & 32) != 0) {
            z3 = false;
        }
        return abstractBinaryClassAnnotationLoader.findClassAndLoadMemberAnnotations(protoContainer, memberSignature, z, z2, bool, z3);
    }

    private final List<A> findClassAndLoadMemberAnnotations(ProtoContainer protoContainer, MemberSignature memberSignature, boolean z, boolean z2, Boolean bool, boolean z3) {
        KotlinJvmBinaryClass kotlinJvmBinaryClassFindClassWithAnnotationsAndInitializers = findClassWithAnnotationsAndInitializers(protoContainer, Companion.getSpecialCaseContainerClass(protoContainer, z, z2, bool, z3, this.kotlinClassFinder, getMetadataVersion()));
        if (kotlinJvmBinaryClassFindClassWithAnnotationsAndInitializers == null) {
            return CollectionsKt.emptyList();
        }
        List<A> list = getAnnotationsContainer(kotlinJvmBinaryClassFindClassWithAnnotationsAndInitializers).getMemberAnnotations().get(memberSignature);
        return list == null ? CollectionsKt.emptyList() : list;
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationLoader
    public List<A> loadValueParameterAnnotations(final ProtoContainer container, final MessageLite callableProto, final AnnotatedCallableKind kind, final int i, ProtoBuf.ValueParameter proto) {
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(callableProto, "callableProto");
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(proto, "proto");
        return loadAnnotationsIfPresentInBytecode(proto.getFlags(), new Function0(this, container, callableProto, kind, i) { // from class: kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationLoader$$Lambda$2
            private final AbstractBinaryClassAnnotationLoader arg$0;
            private final ProtoContainer arg$1;
            private final MessageLite arg$2;
            private final AnnotatedCallableKind arg$3;
            private final int arg$4;

            {
                this.arg$0 = this;
                this.arg$1 = container;
                this.arg$2 = callableProto;
                this.arg$3 = kind;
                this.arg$4 = i;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return AbstractBinaryClassAnnotationLoader.loadValueParameterAnnotations$lambda$0(this.arg$0, this.arg$1, this.arg$2, this.arg$3, this.arg$4);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List loadValueParameterAnnotations$lambda$0(AbstractBinaryClassAnnotationLoader abstractBinaryClassAnnotationLoader, ProtoContainer protoContainer, MessageLite messageLite, AnnotatedCallableKind annotatedCallableKind, int i) {
        return abstractBinaryClassAnnotationLoader.loadParameterAnnotations(protoContainer, messageLite, annotatedCallableKind, i + abstractBinaryClassAnnotationLoader.computeJvmParameterIndexShift(protoContainer, messageLite));
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    private final int computeJvmParameterIndexShift(ProtoContainer protoContainer, MessageLite messageLite) {
        int contextParameterCount = getContextParameterCount(messageLite);
        int i = 1;
        if (messageLite instanceof ProtoBuf.Function) {
            if (!ProtoTypeTableUtilKt.hasReceiver((ProtoBuf.Function) messageLite)) {
                i = 0;
            }
        } else if (messageLite instanceof ProtoBuf.Property) {
            if (!ProtoTypeTableUtilKt.hasReceiver((ProtoBuf.Property) messageLite)) {
                i = 0;
            }
        } else if (messageLite instanceof ProtoBuf.Constructor) {
            Intrinsics.checkNotNull(protoContainer, "null cannot be cast to non-null type org.jetbrains.kotlin.serialization.deserialization.ProtoContainer.Class");
            ProtoContainer.Class r4 = (ProtoContainer.Class) protoContainer;
            if (r4.getKind() == ProtoBuf.Class.Kind.ENUM_CLASS) {
                i = 2;
            } else if (!r4.isInner()) {
                i = 0;
            }
        } else {
            throw new UnsupportedOperationException("Unsupported message: " + messageLite.getClass());
        }
        return contextParameterCount + i;
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationLoader
    public List<A> loadExtensionReceiverParameterAnnotations(ProtoContainer container, MessageLite proto, AnnotatedCallableKind kind) {
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(proto, "proto");
        Intrinsics.checkNotNullParameter(kind, "kind");
        return loadParameterAnnotations(container, proto, kind, getContextParameterCount(proto));
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationLoader
    public List<A> loadContextParameterAnnotations(final ProtoContainer container, final MessageLite callableProto, final AnnotatedCallableKind kind, final int i, ProtoBuf.ValueParameter valueParameter) {
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(callableProto, "callableProto");
        Intrinsics.checkNotNullParameter(kind, "kind");
        return loadAnnotationsIfPresentInBytecode(valueParameter != null ? valueParameter.getFlags() : 0, new Function0(this, container, callableProto, kind, i) { // from class: kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationLoader$$Lambda$3
            private final AbstractBinaryClassAnnotationLoader arg$0;
            private final ProtoContainer arg$1;
            private final MessageLite arg$2;
            private final AnnotatedCallableKind arg$3;
            private final int arg$4;

            {
                this.arg$0 = this;
                this.arg$1 = container;
                this.arg$2 = callableProto;
                this.arg$3 = kind;
                this.arg$4 = i;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.arg$0.loadParameterAnnotations(this.arg$1, this.arg$2, this.arg$3, this.arg$4);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<A> loadParameterAnnotations(ProtoContainer protoContainer, MessageLite messageLite, AnnotatedCallableKind annotatedCallableKind, int i) {
        MemberSignature callableSignature$default = getCallableSignature$default(this, messageLite, protoContainer.getNameResolver(), protoContainer.getTypeTable(), annotatedCallableKind, false, 16, null);
        return callableSignature$default == null ? CollectionsKt.emptyList() : findClassAndLoadMemberAnnotations$default(this, protoContainer, MemberSignature.Companion.fromMethodSignatureAndParameterIndex(callableSignature$default, i), false, false, null, false, 60, null);
    }

    private final int getContextParameterCount(MessageLite messageLite) {
        if (messageLite instanceof ProtoBuf.Function) {
            return ((ProtoBuf.Function) messageLite).getContextParameterCount();
        }
        if (messageLite instanceof ProtoBuf.Property) {
            return ((ProtoBuf.Property) messageLite).getContextParameterCount();
        }
        return 0;
    }

    private final int getCallableAnnotationFlags(MessageLite messageLite, AnnotatedCallableKind annotatedCallableKind) {
        if (messageLite instanceof ProtoBuf.Constructor) {
            return ((ProtoBuf.Constructor) messageLite).getFlags();
        }
        if (messageLite instanceof ProtoBuf.Function) {
            return ((ProtoBuf.Function) messageLite).getFlags();
        }
        if (messageLite instanceof ProtoBuf.Property) {
            return getPropertyFlags((ProtoBuf.Property) messageLite, annotatedCallableKind);
        }
        return 0;
    }

    private final int getPropertyFlags(ProtoBuf.Property property, AnnotatedCallableKind annotatedCallableKind) {
        int i = WhenMappings.$EnumSwitchMapping$0[annotatedCallableKind.ordinal()];
        if (i == 1) {
            return property.hasGetterFlags() ? property.getGetterFlags() : property.getFlags();
        }
        if (i != 2) {
            return property.getFlags();
        }
        return property.hasSetterFlags() ? property.getSetterFlags() : property.getFlags();
    }

    private final List<A> loadAnnotationsIfPresentInBytecode(int i, Function0<? extends List<? extends A>> function0) {
        return noAnnotationsInBytecode(i) ? CollectionsKt.emptyList() : function0.invoke();
    }

    private final boolean noAnnotationsInBytecode(int i) {
        return !Flags.HAS_ANNOTATIONS.get(i).booleanValue();
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationLoader
    public List<A> loadTypeAnnotations(ProtoBuf.Type proto, NameResolver nameResolver) {
        Intrinsics.checkNotNullParameter(proto, "proto");
        Intrinsics.checkNotNullParameter(nameResolver, "nameResolver");
        List<ProtoBuf.Annotation> annotationList = proto.getAnnotationList();
        Intrinsics.checkNotNullExpressionValue(annotationList, "getAnnotationList(...)");
        List<ProtoBuf.Annotation> list = annotationList;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (ProtoBuf.Annotation annotation : list) {
            Intrinsics.checkNotNull(annotation);
            arrayList.add(loadAnnotation(annotation, nameResolver));
        }
        return arrayList;
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationLoader
    public List<A> loadTypeParameterAnnotations(ProtoBuf.TypeParameter proto, NameResolver nameResolver) {
        Intrinsics.checkNotNullParameter(proto, "proto");
        Intrinsics.checkNotNullParameter(nameResolver, "nameResolver");
        List<ProtoBuf.Annotation> annotationList = proto.getAnnotationList();
        Intrinsics.checkNotNullExpressionValue(annotationList, "getAnnotationList(...)");
        List<ProtoBuf.Annotation> list = annotationList;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (ProtoBuf.Annotation annotation : list) {
            Intrinsics.checkNotNull(annotation);
            arrayList.add(loadAnnotation(annotation, nameResolver));
        }
        return arrayList;
    }

    protected final KotlinJvmBinaryClass findClassWithAnnotationsAndInitializers(ProtoContainer container, KotlinJvmBinaryClass kotlinJvmBinaryClass) {
        Intrinsics.checkNotNullParameter(container, "container");
        if (kotlinJvmBinaryClass != null) {
            return kotlinJvmBinaryClass;
        }
        if (container instanceof ProtoContainer.Class) {
            return toBinaryClass((ProtoContainer.Class) container);
        }
        return null;
    }

    public static /* synthetic */ MemberSignature getCallableSignature$default(AbstractBinaryClassAnnotationLoader abstractBinaryClassAnnotationLoader, MessageLite messageLite, NameResolver nameResolver, TypeTable typeTable, AnnotatedCallableKind annotatedCallableKind, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getCallableSignature");
        }
        if ((i & 16) != 0) {
            z = false;
        }
        return abstractBinaryClassAnnotationLoader.getCallableSignature(messageLite, nameResolver, typeTable, annotatedCallableKind, z);
    }

    protected final MemberSignature getCallableSignature(MessageLite proto, NameResolver nameResolver, TypeTable typeTable, AnnotatedCallableKind kind, boolean z) {
        Intrinsics.checkNotNullParameter(proto, "proto");
        Intrinsics.checkNotNullParameter(nameResolver, "nameResolver");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        Intrinsics.checkNotNullParameter(kind, "kind");
        if (proto instanceof ProtoBuf.Constructor) {
            MemberSignature.Companion companion = MemberSignature.Companion;
            JvmMemberSignature.Method jvmConstructorSignature = JvmProtoBufUtil.INSTANCE.getJvmConstructorSignature((ProtoBuf.Constructor) proto, nameResolver, typeTable);
            if (jvmConstructorSignature == null) {
                return null;
            }
            return companion.fromJvmMemberSignature(jvmConstructorSignature);
        }
        if (proto instanceof ProtoBuf.Function) {
            MemberSignature.Companion companion2 = MemberSignature.Companion;
            JvmMemberSignature.Method jvmMethodSignature = JvmProtoBufUtil.INSTANCE.getJvmMethodSignature((ProtoBuf.Function) proto, nameResolver, typeTable);
            if (jvmMethodSignature == null) {
                return null;
            }
            return companion2.fromJvmMemberSignature(jvmMethodSignature);
        }
        if (proto instanceof ProtoBuf.Property) {
            GeneratedMessageLite.GeneratedExtension<ProtoBuf.Property, JvmProtoBuf.JvmPropertySignature> propertySignature = JvmProtoBuf.propertySignature;
            Intrinsics.checkNotNullExpressionValue(propertySignature, "propertySignature");
            JvmProtoBuf.JvmPropertySignature jvmPropertySignature = (JvmProtoBuf.JvmPropertySignature) ProtoBufUtilKt.getExtensionOrNull((GeneratedMessageLite.ExtendableMessage) proto, propertySignature);
            if (jvmPropertySignature == null) {
                return null;
            }
            int i = WhenMappings.$EnumSwitchMapping$0[kind.ordinal()];
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        return null;
                    }
                    return AbstractBinaryClassAnnotationLoaderKt.getPropertySignature((ProtoBuf.Property) proto, nameResolver, typeTable, true, true, z);
                }
                if (!jvmPropertySignature.hasSetter()) {
                    return null;
                }
                MemberSignature.Companion companion3 = MemberSignature.Companion;
                JvmProtoBuf.JvmMethodSignature setter = jvmPropertySignature.getSetter();
                Intrinsics.checkNotNullExpressionValue(setter, "getSetter(...)");
                return companion3.fromMethod(nameResolver, setter);
            }
            if (jvmPropertySignature.hasGetter()) {
                MemberSignature.Companion companion4 = MemberSignature.Companion;
                JvmProtoBuf.JvmMethodSignature getter = jvmPropertySignature.getGetter();
                Intrinsics.checkNotNullExpressionValue(getter, "getGetter(...)");
                return companion4.fromMethod(nameResolver, getter);
            }
        }
        return null;
    }

    protected final boolean isImplicitRepeatableContainer(ClassId classId) {
        KotlinJvmBinaryClass kotlinJvmBinaryClassFindKotlinClass;
        Intrinsics.checkNotNullParameter(classId, "classId");
        return classId.getOuterClassId() != null && Intrinsics.areEqual(classId.getShortClassName().asString(), TextFieldImplKt.ContainerId) && (kotlinJvmBinaryClassFindKotlinClass = KotlinClassFinderKt.findKotlinClass(this.kotlinClassFinder, classId, getMetadataVersion())) != null && SpecialJvmAnnotations.INSTANCE.isAnnotatedWithContainerMetaAnnotation(kotlinJvmBinaryClassFindKotlinClass);
    }

    /* JADX INFO: compiled from: AbstractBinaryClassAnnotationLoader.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KotlinJvmBinaryClass getSpecialCaseContainerClass(ProtoContainer container, boolean z, boolean z2, Boolean bool, boolean z3, KotlinClassFinder kotlinClassFinder, MetadataVersion metadataVersion) {
            ProtoContainer.Class outerClass;
            Intrinsics.checkNotNullParameter(container, "container");
            Intrinsics.checkNotNullParameter(kotlinClassFinder, "kotlinClassFinder");
            Intrinsics.checkNotNullParameter(metadataVersion, "metadataVersion");
            if (z) {
                if (bool == null) {
                    throw new IllegalStateException(("isConst should not be null for property (container=" + container + ')').toString());
                }
                if (container instanceof ProtoContainer.Class) {
                    ProtoContainer.Class r8 = (ProtoContainer.Class) container;
                    if (r8.getKind() == ProtoBuf.Class.Kind.INTERFACE) {
                        ClassId classId = r8.getClassId();
                        Name nameIdentifier = Name.identifier("DefaultImpls");
                        Intrinsics.checkNotNullExpressionValue(nameIdentifier, "identifier(...)");
                        return KotlinClassFinderKt.findKotlinClass(kotlinClassFinder, classId.createNestedClassId(nameIdentifier), metadataVersion);
                    }
                }
                if (bool.booleanValue() && (container instanceof ProtoContainer.Package)) {
                    SourceElement source = container.getSource();
                    JvmPackagePartSource jvmPackagePartSource = source instanceof JvmPackagePartSource ? (JvmPackagePartSource) source : null;
                    JvmClassName facadeClassName = jvmPackagePartSource != null ? jvmPackagePartSource.getFacadeClassName() : null;
                    if (facadeClassName != null) {
                        ClassId.Companion companion = ClassId.Companion;
                        String internalName = facadeClassName.getInternalName();
                        Intrinsics.checkNotNullExpressionValue(internalName, "getInternalName(...)");
                        return KotlinClassFinderKt.findKotlinClass(kotlinClassFinder, companion.topLevel(new FqName(StringsKt.replace$default(internalName, '/', '.', false, 4, (Object) null))), metadataVersion);
                    }
                }
            }
            if (z2 && (container instanceof ProtoContainer.Class)) {
                ProtoContainer.Class r9 = (ProtoContainer.Class) container;
                if (r9.getKind() == ProtoBuf.Class.Kind.COMPANION_OBJECT && (outerClass = r9.getOuterClass()) != null && (outerClass.getKind() == ProtoBuf.Class.Kind.CLASS || outerClass.getKind() == ProtoBuf.Class.Kind.ENUM_CLASS || (z3 && (outerClass.getKind() == ProtoBuf.Class.Kind.INTERFACE || outerClass.getKind() == ProtoBuf.Class.Kind.ANNOTATION_CLASS)))) {
                    SourceElement source2 = outerClass.getSource();
                    KotlinJvmBinarySourceElement kotlinJvmBinarySourceElement = source2 instanceof KotlinJvmBinarySourceElement ? (KotlinJvmBinarySourceElement) source2 : null;
                    if (kotlinJvmBinarySourceElement != null) {
                        return kotlinJvmBinarySourceElement.getBinaryClass();
                    }
                    return null;
                }
            }
            if (!(container instanceof ProtoContainer.Package) || !(container.getSource() instanceof JvmPackagePartSource)) {
                return null;
            }
            SourceElement source3 = container.getSource();
            Intrinsics.checkNotNull(source3, "null cannot be cast to non-null type org.jetbrains.kotlin.load.kotlin.JvmPackagePartSource");
            JvmPackagePartSource jvmPackagePartSource2 = (JvmPackagePartSource) source3;
            KotlinJvmBinaryClass knownJvmBinaryClass = jvmPackagePartSource2.getKnownJvmBinaryClass();
            return knownJvmBinaryClass == null ? KotlinClassFinderKt.findKotlinClass(kotlinClassFinder, jvmPackagePartSource2.getClassId(), metadataVersion) : knownJvmBinaryClass;
        }
    }
}
