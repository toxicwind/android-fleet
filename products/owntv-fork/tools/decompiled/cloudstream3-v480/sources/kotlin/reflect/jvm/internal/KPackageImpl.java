package kotlin.reflect.jvm.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.reflect.KCallable;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.ReflectKotlinClass;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.km.KmConstructor;
import kotlin.reflect.jvm.internal.impl.km.KmFunction;
import kotlin.reflect.jvm.internal.impl.km.KmPackage;
import kotlin.reflect.jvm.internal.impl.km.KmProperty;
import kotlin.reflect.jvm.internal.impl.km.internal.ReadersKt;
import kotlin.reflect.jvm.internal.impl.km.jvm.JvmExtensionsKt;
import kotlin.reflect.jvm.internal.impl.km.jvm.KotlinClassMetadata;
import kotlin.reflect.jvm.internal.impl.load.kotlin.header.KotlinClassHeader;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoBufUtilKt;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.ChainedMemberScope;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedPackageMemberScope;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: KPackageImpl.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001:\u00016B\u0013\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010&\u001a\b\u0012\u0004\u0012\u00020'0\u00122\u0006\u0010(\u001a\u00020)H\u0016J\u0016\u0010*\u001a\b\u0012\u0004\u0012\u00020+0\u00122\u0006\u0010(\u001a\u00020)H\u0016J\u0012\u0010,\u001a\u0004\u0018\u00010'2\u0006\u0010-\u001a\u00020.H\u0016J\u0012\u0010/\u001a\u0004\u0018\u00010\u001e2\u0006\u0010-\u001a\u00020.H\u0016J\u0014\u00100\u001a\u00020\u00172\b\u00101\u001a\u0004\u0018\u000102H\u0096\u0082\u0004J\n\u00103\u001a\u00020.H\u0096\u0080\u0004J\n\u00104\u001a\u000205H\u0096\u0080\u0004R\u0019\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0018\u0010\b\u001a\f\u0012\b\u0012\u00060\nR\u00020\u00000\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\u00038TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\u0007R\u0014\u0010\r\u001a\u00020\u000e8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u001f\u0010\u0011\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00130\u00128VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u00178@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0015R\u001a\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0015R\u001a\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010\u0015R\u001a\u0010#\u001a\b\u0012\u0004\u0012\u00020$0\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b%\u0010\u0015¨\u00067"}, d2 = {"Lkotlin/reflect/jvm/internal/KPackageImpl;", "Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;", "jClass", "Ljava/lang/Class;", "<init>", "(Ljava/lang/Class;)V", "getJClass", "()Ljava/lang/Class;", "data", "Lkotlin/Lazy;", "Lkotlin/reflect/jvm/internal/KPackageImpl$Data;", "methodOwner", "getMethodOwner", "scope", "Lkotlin/reflect/jvm/internal/impl/resolve/scopes/MemberScope;", "getScope", "()Lorg/jetbrains/kotlin/resolve/scopes/MemberScope;", "members", "", "Lkotlin/reflect/KCallable;", "getMembers", "()Ljava/util/Collection;", "isMultifilePart", "", "isMultifilePart$kotlin_reflection", "()Z", "functionsMetadata", "Lkotlin/reflect/jvm/internal/impl/km/KmFunction;", "getFunctionsMetadata", "propertiesMetadata", "Lkotlin/reflect/jvm/internal/impl/km/KmProperty;", "getPropertiesMetadata", "constructorsMetadata", "Lkotlin/reflect/jvm/internal/impl/km/KmConstructor;", "getConstructorsMetadata", "constructorDescriptors", "Lkotlin/reflect/jvm/internal/impl/descriptors/ConstructorDescriptor;", "getConstructorDescriptors", "getProperties", "Lkotlin/reflect/jvm/internal/impl/descriptors/PropertyDescriptor;", "name", "Lkotlin/reflect/jvm/internal/impl/name/Name;", "getFunctions", "Lkotlin/reflect/jvm/internal/impl/descriptors/FunctionDescriptor;", "getLocalPropertyDescriptor", "index", "", "getLocalPropertyMetadata", "equals", "other", "", "hashCode", "toString", "", "Data", "kotlin-reflection"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class KPackageImpl extends KDeclarationContainerImpl {
    private final Lazy<Data> data;
    private final Class<?> jClass;

    public KPackageImpl(Class<?> jClass) {
        Intrinsics.checkNotNullParameter(jClass, "jClass");
        this.jClass = jClass;
        this.data = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0(this) { // from class: kotlin.reflect.jvm.internal.KPackageImpl$$Lambda$0
            private final KPackageImpl arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return KPackageImpl.data$lambda$0(this.arg$0);
            }
        });
    }

    @Override // kotlin.jvm.internal.ClassBasedDeclarationContainer
    public Class<?> getJClass() {
        return this.jClass;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: KPackageImpl.kt */
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0082\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004R!\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u001d\u0010\f\u001a\u0004\u0018\u00010\r8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000fR\u001b\u0010\u0012\u001a\u00020\u00138FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\u0011\u001a\u0004\b\u0014\u0010\u0015R!\u0010\u0017\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00188FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u000b\u001a\u0004\b\u0019\u0010\u001aR%\u0010\u001c\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001e0\u001d8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b!\u0010\u0011\u001a\u0004\b\u001f\u0010 ¨\u0006\""}, d2 = {"Lkotlin/reflect/jvm/internal/KPackageImpl$Data;", "Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl$Data;", "Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;", "<init>", "(Lkotlin/reflect/jvm/internal/KPackageImpl;)V", "kmPackages", "", "Lkotlin/reflect/jvm/internal/impl/km/KmPackage;", "getKmPackages", "()Ljava/util/List;", "kmPackages$delegate", "Lkotlin/Lazy;", "kotlinClass", "Lkotlin/reflect/jvm/internal/impl/descriptors/runtime/components/ReflectKotlinClass;", "getKotlinClass", "()Lorg/jetbrains/kotlin/descriptors/runtime/components/ReflectKotlinClass;", "kotlinClass$delegate", "Lkotlin/reflect/jvm/internal/ReflectProperties$LazySoftVal;", "scope", "Lkotlin/reflect/jvm/internal/impl/resolve/scopes/MemberScope;", "getScope", "()Lorg/jetbrains/kotlin/resolve/scopes/MemberScope;", "scope$delegate", "multifileFacade", "Ljava/lang/Class;", "getMultifileFacade", "()Ljava/lang/Class;", "multifileFacade$delegate", "members", "", "Lkotlin/reflect/KCallable;", "getMembers", "()Ljava/util/Collection;", "members$delegate", "kotlin-reflection"}, k = 1, mv = {2, 3, 0}, xi = 48)
    final class Data extends KDeclarationContainerImpl.Data {
        static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl(Data.class, "kotlinClass", "getKotlinClass()Lorg/jetbrains/kotlin/descriptors/runtime/components/ReflectKotlinClass;", 0), new PropertyReference1Impl(Data.class, "scope", "getScope()Lorg/jetbrains/kotlin/resolve/scopes/MemberScope;", 0), new PropertyReference1Impl(Data.class, "members", "getMembers()Ljava/util/Collection;", 0)};

        /* JADX INFO: renamed from: kmPackages$delegate, reason: from kotlin metadata */
        private final Lazy kmPackages;

        /* JADX INFO: renamed from: kotlinClass$delegate, reason: from kotlin metadata */
        private final ReflectProperties.LazySoftVal kotlinClass;

        /* JADX INFO: renamed from: members$delegate, reason: from kotlin metadata */
        private final ReflectProperties.LazySoftVal members;

        /* JADX INFO: renamed from: multifileFacade$delegate, reason: from kotlin metadata */
        private final Lazy multifileFacade;

        /* JADX INFO: renamed from: scope$delegate, reason: from kotlin metadata */
        private final ReflectProperties.LazySoftVal scope;

        public Data() {
            super();
            this.kmPackages = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0(KPackageImpl.this, this) { // from class: kotlin.reflect.jvm.internal.KPackageImpl$Data$$Lambda$0
                private final KPackageImpl arg$0;
                private final KPackageImpl.Data arg$1;

                {
                    this.arg$0 = kPackageImpl;
                    this.arg$1 = this;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return KPackageImpl.Data.kmPackages_delegate$lambda$0(this.arg$0, this.arg$1);
                }
            });
            this.kotlinClass = ReflectProperties.lazySoft(new Function0(KPackageImpl.this) { // from class: kotlin.reflect.jvm.internal.KPackageImpl$Data$$Lambda$1
                private final KPackageImpl arg$0;

                {
                    this.arg$0 = kPackageImpl;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return KPackageImpl.Data.kotlinClass_delegate$lambda$0(this.arg$0);
                }
            });
            this.scope = ReflectProperties.lazySoft(new Function0(this) { // from class: kotlin.reflect.jvm.internal.KPackageImpl$Data$$Lambda$2
                private final KPackageImpl.Data arg$0;

                {
                    this.arg$0 = this;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return KPackageImpl.Data.scope_delegate$lambda$0(this.arg$0);
                }
            });
            this.multifileFacade = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0(this, KPackageImpl.this) { // from class: kotlin.reflect.jvm.internal.KPackageImpl$Data$$Lambda$3
                private final KPackageImpl.Data arg$0;
                private final KPackageImpl arg$1;

                {
                    this.arg$0 = this;
                    this.arg$1 = kPackageImpl;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return KPackageImpl.Data.multifileFacade_delegate$lambda$0(this.arg$0, this.arg$1);
                }
            });
            this.members = ReflectProperties.lazySoft(new Function0(this, KPackageImpl.this) { // from class: kotlin.reflect.jvm.internal.KPackageImpl$Data$$Lambda$4
                private final KPackageImpl.Data arg$0;
                private final KPackageImpl arg$1;

                {
                    this.arg$0 = this;
                    this.arg$1 = kPackageImpl;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return KPackageImpl.Data.members_delegate$lambda$0(this.arg$0, this.arg$1);
                }
            });
        }

        public final List<KmPackage> getKmPackages() {
            return (List) this.kmPackages.getValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final List kmPackages_delegate$lambda$0(KPackageImpl kPackageImpl, Data data) throws ClassNotFoundException {
            List<MemberScope> componentScopes;
            if (SystemPropertiesKt.getLoadMetadataDirectly()) {
                Metadata metadata = (Metadata) kPackageImpl.getJClass().getAnnotation(Metadata.class);
                KotlinClassMetadata lenient = metadata != null ? KotlinClassMetadata.Companion.readLenient(metadata) : null;
                if (lenient instanceof KotlinClassMetadata.FileFacade) {
                    return CollectionsKt.listOf(((KotlinClassMetadata.FileFacade) lenient).getKmPackage());
                }
                if (lenient instanceof KotlinClassMetadata.MultiFileClassPart) {
                    return CollectionsKt.listOf(((KotlinClassMetadata.MultiFileClassPart) lenient).getKmPackage());
                }
                if (!(lenient instanceof KotlinClassMetadata.MultiFileClassFacade)) {
                    return CollectionsKt.emptyList();
                }
                List<String> partClassNames = ((KotlinClassMetadata.MultiFileClassFacade) lenient).getPartClassNames();
                ArrayList arrayList = new ArrayList();
                Iterator<T> it = partClassNames.iterator();
                while (it.hasNext()) {
                    Class<?> clsLoadClass = kPackageImpl.getJClass().getClassLoader().loadClass(StringsKt.replace$default((String) it.next(), '/', '.', false, 4, (Object) null));
                    Intrinsics.checkNotNullExpressionValue(clsLoadClass, "loadClass(...)");
                    KDeclarationContainer orCreateKotlinPackage = CachesKt.getOrCreateKotlinPackage(clsLoadClass);
                    Intrinsics.checkNotNull(orCreateKotlinPackage, "null cannot be cast to non-null type kotlin.reflect.jvm.internal.KPackageImpl");
                    CollectionsKt.addAll(arrayList, ((Data) ((KPackageImpl) orCreateKotlinPackage).data.getValue()).getKmPackages());
                }
                return arrayList;
            }
            MemberScope scope = data.getScope();
            if (scope instanceof DeserializedPackageMemberScope) {
                componentScopes = CollectionsKt.listOf(scope);
            } else {
                componentScopes = scope instanceof ChainedMemberScope ? ((ChainedMemberScope) scope).getComponentScopes() : CollectionsKt.emptyList();
            }
            List<MemberScope> list = componentScopes;
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            for (MemberScope memberScope : list) {
                Intrinsics.checkNotNull(memberScope, "null cannot be cast to non-null type org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedPackageMemberScope");
                DeserializedPackageMemberScope deserializedPackageMemberScope = (DeserializedPackageMemberScope) memberScope;
                arrayList2.add(ReadersKt.toKmPackage$default(deserializedPackageMemberScope.getProto(), deserializedPackageMemberScope.getC().getNameResolver(), false, null, 6, null));
            }
            return arrayList2;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final ReflectKotlinClass getKotlinClass() {
            return (ReflectKotlinClass) this.kotlinClass.getValue(this, $$delegatedProperties[0]);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final ReflectKotlinClass kotlinClass_delegate$lambda$0(KPackageImpl kPackageImpl) {
            return ReflectKotlinClass.Factory.create(kPackageImpl.getJClass());
        }

        public final MemberScope getScope() {
            T value = this.scope.getValue(this, $$delegatedProperties[1]);
            Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
            return (MemberScope) value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final MemberScope scope_delegate$lambda$0(Data data) {
            ReflectKotlinClass kotlinClass = data.getKotlinClass();
            if (kotlinClass != null) {
                return data.getModuleData().getPackagePartScopeCache().getPackagePartScope(kotlinClass);
            }
            return MemberScope.Empty.INSTANCE;
        }

        public final Class<?> getMultifileFacade() {
            return (Class) this.multifileFacade.getValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Class multifileFacade_delegate$lambda$0(Data data, KPackageImpl kPackageImpl) {
            KotlinClassHeader classHeader;
            ReflectKotlinClass kotlinClass = data.getKotlinClass();
            String multifileClassName = (kotlinClass == null || (classHeader = kotlinClass.getClassHeader()) == null) ? null : classHeader.getMultifileClassName();
            if (multifileClassName == null || multifileClassName.length() <= 0) {
                return null;
            }
            return kPackageImpl.getJClass().getClassLoader().loadClass(StringsKt.replace$default(multifileClassName, '/', '.', false, 4, (Object) null));
        }

        public final Collection<KCallable<?>> getMembers() {
            T value = this.members.getValue(this, $$delegatedProperties[2]);
            Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
            return (Collection) value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final List members_delegate$lambda$0(Data data, final KPackageImpl kPackageImpl) {
            if (SystemPropertiesKt.getUseK1Implementation()) {
                CreateKCallableVisitor createKCallableVisitor = new CreateKCallableVisitor(kPackageImpl) { // from class: kotlin.reflect.jvm.internal.KPackageImpl$Data$members$2$visitor$1
                    {
                        super(kPackageImpl);
                    }

                    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorVisitorEmptyBodies, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
                    public DescriptorKCallable<?> visitConstructorDescriptor(ConstructorDescriptor descriptor, Unit data2) {
                        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
                        Intrinsics.checkNotNullParameter(data2, "data");
                        throw new IllegalStateException("No constructors should appear here: " + descriptor);
                    }
                };
                Collection<DeclarationDescriptor> contributedDescriptors$default = ResolutionScope.DefaultImpls.getContributedDescriptors$default(data.getScope(), null, null, 3, null);
                ArrayList arrayList = new ArrayList();
                for (DeclarationDescriptor declarationDescriptor : contributedDescriptors$default) {
                    DescriptorKCallable descriptorKCallable = declarationDescriptor instanceof CallableMemberDescriptor ? (DescriptorKCallable) declarationDescriptor.accept(createKCallableVisitor, Unit.INSTANCE) : null;
                    if (descriptorKCallable != null) {
                        arrayList.add(descriptorKCallable);
                    }
                }
                return CollectionsKt.toList(arrayList);
            }
            ArrayList arrayList2 = new ArrayList();
            for (KmPackage kmPackage : data.getKmPackages()) {
                Iterator<KmProperty> it = kmPackage.getProperties().iterator();
                while (it.hasNext()) {
                    arrayList2.add(ConvertFromMetadataKt.createUnboundProperty(it.next(), kPackageImpl));
                }
                Iterator<KmFunction> it2 = kmPackage.getFunctions().iterator();
                while (it2.hasNext()) {
                    arrayList2.add(ConvertFromMetadataKt.createUnboundFunction(it2.next(), kPackageImpl));
                }
            }
            return CollectionsKt.toList(arrayList2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Data data$lambda$0(KPackageImpl kPackageImpl) {
        return kPackageImpl.new Data();
    }

    @Override // kotlin.reflect.jvm.internal.KDeclarationContainerImpl
    protected Class<?> getMethodOwner() {
        Class<?> multifileFacade = this.data.getValue().getMultifileFacade();
        return multifileFacade == null ? getJClass() : multifileFacade;
    }

    private final MemberScope getScope() {
        return this.data.getValue().getScope();
    }

    @Override // kotlin.reflect.KDeclarationContainer
    public Collection<KCallable<?>> getMembers() {
        return this.data.getValue().getMembers();
    }

    public final boolean isMultifilePart$kotlin_reflection() {
        KotlinClassHeader classHeader;
        ReflectKotlinClass kotlinClass = this.data.getValue().getKotlinClass();
        return ((kotlinClass == null || (classHeader = kotlinClass.getClassHeader()) == null) ? null : classHeader.getKind()) == KotlinClassHeader.Kind.MULTIFILE_CLASS_PART;
    }

    public Collection<KmFunction> getFunctionsMetadata() {
        List<KmPackage> kmPackages = this.data.getValue().getKmPackages();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = kmPackages.iterator();
        while (it.hasNext()) {
            CollectionsKt.addAll(arrayList, ((KmPackage) it.next()).getFunctions());
        }
        return arrayList;
    }

    public Collection<KmProperty> getPropertiesMetadata() {
        List<KmPackage> kmPackages = this.data.getValue().getKmPackages();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = kmPackages.iterator();
        while (it.hasNext()) {
            CollectionsKt.addAll(arrayList, ((KmPackage) it.next()).getProperties());
        }
        return arrayList;
    }

    @Override // kotlin.reflect.jvm.internal.KDeclarationContainerImpl
    public Collection<KmConstructor> getConstructorsMetadata() {
        return CollectionsKt.emptyList();
    }

    @Override // kotlin.reflect.jvm.internal.KDeclarationContainerImpl
    public Collection<ConstructorDescriptor> getConstructorDescriptors() {
        return CollectionsKt.emptyList();
    }

    @Override // kotlin.reflect.jvm.internal.KDeclarationContainerImpl
    public Collection<PropertyDescriptor> getProperties(Name name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return getScope().getContributedVariables(name, NoLookupLocation.FROM_REFLECTION);
    }

    @Override // kotlin.reflect.jvm.internal.KDeclarationContainerImpl
    public Collection<FunctionDescriptor> getFunctions(Name name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return getScope().getContributedFunctions(name, NoLookupLocation.FROM_REFLECTION);
    }

    @Override // kotlin.reflect.jvm.internal.KDeclarationContainerImpl
    public PropertyDescriptor getLocalPropertyDescriptor(int index) {
        MemberScope scope = getScope();
        DeserializedPackageMemberScope deserializedPackageMemberScope = scope instanceof DeserializedPackageMemberScope ? (DeserializedPackageMemberScope) scope : null;
        if (deserializedPackageMemberScope == null) {
            return null;
        }
        ProtoBuf.Package proto = deserializedPackageMemberScope.getProto();
        GeneratedMessageLite.GeneratedExtension<ProtoBuf.Package, List<ProtoBuf.Property>> packageLocalVariable = JvmProtoBuf.packageLocalVariable;
        Intrinsics.checkNotNullExpressionValue(packageLocalVariable, "packageLocalVariable");
        ProtoBuf.Property property = (ProtoBuf.Property) ProtoBufUtilKt.getExtensionOrNull(proto, packageLocalVariable, index);
        if (property == null) {
            return null;
        }
        NameResolver nameResolver = deserializedPackageMemberScope.getC().getNameResolver();
        ProtoBuf.TypeTable typeTable = proto.getTypeTable();
        Intrinsics.checkNotNullExpressionValue(typeTable, "getTypeTable(...)");
        return (PropertyDescriptor) UtilKt.deserializeToDescriptor(getJClass(), new LocalDelegatedPropertyFakeContainerSource(this), property, nameResolver, new TypeTable(typeTable), deserializedPackageMemberScope.getC().getMetadataVersion(), new Function2() { // from class: kotlin.reflect.jvm.internal.KPackageImpl$$Lambda$1
            @Override // kotlin.jvm.functions.Function2
            public Object invoke(Object obj, Object obj2) {
                return KPackageImpl.getLocalPropertyDescriptor$lambda$0$0((MemberDeserializer) obj, (ProtoBuf.Property) obj2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PropertyDescriptor getLocalPropertyDescriptor$lambda$0$0(MemberDeserializer deserializeToDescriptor, ProtoBuf.Property proto) {
        Intrinsics.checkNotNullParameter(deserializeToDescriptor, "$this$deserializeToDescriptor");
        Intrinsics.checkNotNullParameter(proto, "proto");
        return deserializeToDescriptor.loadProperty(proto, true);
    }

    @Override // kotlin.reflect.jvm.internal.KDeclarationContainerImpl
    public KmProperty getLocalPropertyMetadata(int index) {
        List<KmProperty> localDelegatedProperties;
        KmPackage kmPackage = (KmPackage) CollectionsKt.singleOrNull((List) this.data.getValue().getKmPackages());
        if (kmPackage == null || (localDelegatedProperties = JvmExtensionsKt.getLocalDelegatedProperties(kmPackage)) == null) {
            return null;
        }
        return (KmProperty) CollectionsKt.getOrNull(localDelegatedProperties, index);
    }

    public boolean equals(Object other) {
        return (other instanceof KPackageImpl) && Intrinsics.areEqual(getJClass(), ((KPackageImpl) other).getJClass());
    }

    public int hashCode() {
        return getJClass().hashCode();
    }

    public String toString() {
        return "file class " + ReflectClassUtilKt.getClassId(getJClass()).asSingleFqName();
    }
}
