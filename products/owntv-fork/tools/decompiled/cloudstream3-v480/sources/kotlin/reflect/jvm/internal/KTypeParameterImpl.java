package kotlin.reflect.jvm.internal;

import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.ArrayList;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.KTypeParameterBase;
import kotlin.reflect.KType;
import kotlin.reflect.KVariance;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.model.TypeConstructorMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeParameterMarker;
import kotlin.reflect.jvm.internal.types.DescriptorKType;
import kotlin.reflect.jvm.internal.types.KTypeSubstitutor;

/* JADX INFO: compiled from: KTypeParameterImpl.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B3\b\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fB)\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u0010B#\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012¢\u0006\u0004\b\u000e\u0010\u0013R\u0015\u0010\b\u001a\u00020\tX\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0015\u0010\n\u001a\u00020\u000bX\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0015\u0010\f\u001a\u00020\rX\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0018R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR!\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dX\u0096®\b¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"¨\u0006#"}, d2 = {"Lkotlin/reflect/jvm/internal/KTypeParameterImpl;", "Lkotlin/jvm/internal/KTypeParameterBase;", "Lkotlin/reflect/jvm/internal/impl/types/model/TypeParameterMarker;", "Lkotlin/reflect/jvm/internal/impl/types/model/TypeConstructorMarker;", "descriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/TypeParameterDescriptor;", TtmlNode.RUBY_CONTAINER, "Lkotlin/reflect/jvm/internal/KTypeParameterOwnerImpl;", "name", "", "variance", "Lkotlin/reflect/KVariance;", "isReified", "", "<init>", "(Lorg/jetbrains/kotlin/descriptors/TypeParameterDescriptor;Lkotlin/reflect/jvm/internal/KTypeParameterOwnerImpl;Ljava/lang/String;Lkotlin/reflect/KVariance;Z)V", "(Lkotlin/reflect/jvm/internal/KTypeParameterOwnerImpl;Ljava/lang/String;Lkotlin/reflect/KVariance;Z)V", "typeSubstitutor", "Lkotlin/reflect/jvm/internal/types/KTypeSubstitutor;", "(Lkotlin/reflect/jvm/internal/KTypeParameterOwnerImpl;Lorg/jetbrains/kotlin/descriptors/TypeParameterDescriptor;Lkotlin/reflect/jvm/internal/types/KTypeSubstitutor;)V", "getName", "()Ljava/lang/String;", "getVariance", "()Lkotlin/reflect/KVariance;", "()Z", "_descriptor", "getDescriptor", "()Lorg/jetbrains/kotlin/descriptors/TypeParameterDescriptor;", "upperBounds", "", "Lkotlin/reflect/KType;", "getUpperBounds", "()Ljava/util/List;", "setUpperBounds", "(Ljava/util/List;)V", "kotlin-reflection"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class KTypeParameterImpl extends KTypeParameterBase implements TypeConstructorMarker, TypeParameterMarker {
    private final TypeParameterDescriptor _descriptor;
    private final boolean isReified;
    private final String name;
    public volatile List<? extends KType> upperBounds;
    private final KVariance variance;

    public final TypeParameterDescriptor getDescriptor() {
        TypeParameterDescriptor typeParameterDescriptor = this._descriptor;
        if (typeParameterDescriptor != null) {
            return typeParameterDescriptor;
        }
        throw new IllegalStateException(("Descriptor-less type parameter: " + this).toString());
    }

    private KTypeParameterImpl(TypeParameterDescriptor typeParameterDescriptor, KTypeParameterOwnerImpl kTypeParameterOwnerImpl, String str, KVariance kVariance, boolean z) {
        super(kTypeParameterOwnerImpl);
        this.name = str;
        this.variance = kVariance;
        this.isReified = z;
        this._descriptor = typeParameterDescriptor;
    }

    @Override // kotlin.reflect.KTypeParameter
    public String getName() {
        return this.name;
    }

    @Override // kotlin.reflect.KTypeParameter
    public KVariance getVariance() {
        return this.variance;
    }

    @Override // kotlin.reflect.KTypeParameter
    /* JADX INFO: renamed from: isReified, reason: from getter */
    public boolean getIsReified() {
        return this.isReified;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public KTypeParameterImpl(KTypeParameterOwnerImpl container, String name, KVariance variance, boolean z) {
        this((TypeParameterDescriptor) null, container, name, variance, z);
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(variance, "variance");
    }

    public /* synthetic */ KTypeParameterImpl(KTypeParameterOwnerImpl kTypeParameterOwnerImpl, TypeParameterDescriptor typeParameterDescriptor, KTypeSubstitutor kTypeSubstitutor, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(kTypeParameterOwnerImpl, typeParameterDescriptor, (i & 4) != 0 ? KTypeSubstitutor.INSTANCE.getEMPTY() : kTypeSubstitutor);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /* JADX WARN: Multi-variable type inference failed */
    public KTypeParameterImpl(KTypeParameterOwnerImpl container, TypeParameterDescriptor descriptor, KTypeSubstitutor typeSubstitutor) {
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(typeSubstitutor, "typeSubstitutor");
        String strAsString = descriptor.getName().asString();
        Intrinsics.checkNotNullExpressionValue(strAsString, "asString(...)");
        Variance variance = descriptor.getVariance();
        Intrinsics.checkNotNullExpressionValue(variance, "getVariance(...)");
        this(descriptor, container, strAsString, KTypeParameterImplKt.toKVariance(variance), descriptor.isReified());
        List<KotlinType> upperBounds = descriptor.getUpperBounds();
        Intrinsics.checkNotNullExpressionValue(upperBounds, "getUpperBounds(...)");
        List<KotlinType> list = upperBounds;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (KotlinType kotlinType : list) {
            Intrinsics.checkNotNull(kotlinType);
            KType type = KTypeSubstitutor.substitute$default(typeSubstitutor, new DescriptorKType(kotlinType, null, 2, 0 == true ? 1 : 0), null, 2, null).getType();
            if (type == null) {
                FakeOverridesKt.starProjectionInTopLevelTypeIsNotPossible(container);
                throw new KotlinNothingValueException();
            }
            arrayList.add(type);
        }
        setUpperBounds(arrayList);
    }

    @Override // kotlin.reflect.KTypeParameter
    public List<KType> getUpperBounds() {
        List list = this.upperBounds;
        if (list != null) {
            return list;
        }
        Intrinsics.throwUninitializedPropertyAccessException("upperBounds");
        return null;
    }

    public void setUpperBounds(List<? extends KType> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.upperBounds = list;
    }
}
