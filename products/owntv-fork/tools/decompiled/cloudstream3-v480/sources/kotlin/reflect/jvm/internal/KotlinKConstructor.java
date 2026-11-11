package kotlin.reflect.jvm.internal;

import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KType;
import kotlin.reflect.KVisibility;
import kotlin.reflect.full.KClasses;
import kotlin.reflect.jvm.internal.impl.km.Attributes;
import kotlin.reflect.jvm.internal.impl.km.KmConstructor;
import kotlin.reflect.jvm.internal.impl.km.KmType;
import kotlin.reflect.jvm.internal.impl.km.KmValueParameter;
import kotlin.reflect.jvm.internal.impl.km.Modality;
import kotlin.reflect.jvm.internal.impl.km.jvm.JvmExtensionsKt;
import kotlin.reflect.jvm.internal.impl.km.jvm.JvmMethodSignature;

/* JADX INFO: compiled from: KotlinKConstructor.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bR\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0011\u001a\u0004\u0018\u00010\u00128TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0010R\u0014\u0010\u0017\u001a\u00020\u00188TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\u00020\u001c8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u0015\u0010\u001f\u001a\u00020\u00058VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b \u0010!R\u001b\u0010\"\u001a\u00020#8VX\u0096\u0084\n¢\u0006\f\n\u0004\b&\u0010'\u001a\u0004\b$\u0010%R\u0017\u0010(\u001a\u0004\u0018\u00010)8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b*\u0010+R\u0014\u0010,\u001a\u00020-8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b.\u0010/R\u0015\u00100\u001a\u0002018VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b0\u00102R\u0015\u00103\u001a\u0002018VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b3\u00102R\u0015\u00104\u001a\u0002018VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b4\u00102R\u0015\u00105\u001a\u0002018VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b5\u00102R\u0015\u00106\u001a\u0002018VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b6\u00102R\u0014\u00107\u001a\u0002018VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b7\u00102¨\u00068"}, d2 = {"Lkotlin/reflect/jvm/internal/KotlinKConstructor;", "Lkotlin/reflect/jvm/internal/KotlinKFunction;", TtmlNode.RUBY_CONTAINER, "Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;", "signature", "", "rawBoundReceiver", "", "kmConstructor", "Lkotlin/reflect/jvm/internal/impl/km/KmConstructor;", "<init>", "(Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;Ljava/lang/String;Ljava/lang/Object;Lkotlin/metadata/KmConstructor;)V", "contextParameters", "", "Lkotlin/reflect/jvm/internal/impl/km/KmValueParameter;", "getContextParameters", "()Ljava/util/List;", "extensionReceiverType", "Lkotlin/reflect/jvm/internal/impl/km/KmType;", "getExtensionReceiverType", "()Lkotlin/metadata/KmType;", "valueParameters", "getValueParameters", "typeParameterTable", "Lkotlin/reflect/jvm/internal/TypeParameterTable;", "getTypeParameterTable", "()Lkotlin/reflect/jvm/internal/TypeParameterTable;", "jvmSignature", "Lkotlin/reflect/jvm/internal/impl/km/jvm/JvmMethodSignature;", "getJvmSignature", "()Lkotlin/metadata/jvm/JvmMethodSignature;", "name", "getName", "()Ljava/lang/String;", "returnType", "Lkotlin/reflect/KType;", "getReturnType", "()Lkotlin/reflect/KType;", "returnType$delegate", "Lkotlin/Lazy;", "visibility", "Lkotlin/reflect/KVisibility;", "getVisibility", "()Lkotlin/reflect/KVisibility;", "modality", "Lkotlin/reflect/jvm/internal/impl/km/Modality;", "getModality", "()Lkotlin/metadata/Modality;", "isSuspend", "", "()Z", "isInline", "isExternal", "isOperator", "isInfix", "isPrimaryConstructor", "kotlin-reflection"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class KotlinKConstructor extends KotlinKFunction {
    private final KmConstructor kmConstructor;

    /* JADX INFO: renamed from: returnType$delegate, reason: from kotlin metadata */
    private final Lazy returnType;

    @Override // kotlin.reflect.jvm.internal.KotlinKFunction
    protected KmType getExtensionReceiverType() {
        return null;
    }

    @Override // kotlin.reflect.KFunction
    public boolean isExternal() {
        return false;
    }

    @Override // kotlin.reflect.KFunction
    public boolean isInfix() {
        return false;
    }

    @Override // kotlin.reflect.KFunction
    public boolean isInline() {
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

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KotlinKConstructor(final KDeclarationContainerImpl container, String signature, Object obj, KmConstructor kmConstructor) {
        super(container, signature, obj);
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(signature, "signature");
        Intrinsics.checkNotNullParameter(kmConstructor, "kmConstructor");
        this.kmConstructor = kmConstructor;
        this.returnType = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0(container) { // from class: kotlin.reflect.jvm.internal.KotlinKConstructor$$Lambda$0
            private final KDeclarationContainerImpl arg$0;

            {
                this.arg$0 = container;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return KotlinKConstructor.returnType_delegate$lambda$0(this.arg$0);
            }
        });
    }

    @Override // kotlin.reflect.jvm.internal.KotlinKFunction
    protected List<KmValueParameter> getContextParameters() {
        return CollectionsKt.emptyList();
    }

    @Override // kotlin.reflect.jvm.internal.KotlinKFunction
    protected List<KmValueParameter> getValueParameters() {
        return this.kmConstructor.getValueParameters();
    }

    @Override // kotlin.reflect.jvm.internal.KotlinKFunction
    protected TypeParameterTable getTypeParameterTable() {
        KDeclarationContainerImpl container = getContainer();
        Intrinsics.checkNotNull(container, "null cannot be cast to non-null type kotlin.reflect.jvm.internal.KClassImpl<*>");
        return ((KClassImpl) container).getTypeParameterTable$kotlin_reflection();
    }

    @Override // kotlin.reflect.jvm.internal.KotlinKFunction
    protected JvmMethodSignature getJvmSignature() {
        JvmMethodSignature signature = JvmExtensionsKt.getSignature(this.kmConstructor);
        if (signature != null) {
            return signature;
        }
        throw new KotlinReflectionInternalError("No signature for constructor: " + this);
    }

    @Override // kotlin.reflect.KCallable
    public String getName() {
        return "<init>";
    }

    @Override // kotlin.reflect.KCallable
    public KType getReturnType() {
        return (KType) this.returnType.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final KType returnType_delegate$lambda$0(KDeclarationContainerImpl kDeclarationContainerImpl) {
        Intrinsics.checkNotNull(kDeclarationContainerImpl, "null cannot be cast to non-null type kotlin.reflect.jvm.internal.KClassImpl<*>");
        return KClasses.createDefaultType((KClassImpl) kDeclarationContainerImpl);
    }

    @Override // kotlin.reflect.KCallable
    public KVisibility getVisibility() {
        return ConvertFromMetadataKt.toKVisibility(Attributes.getVisibility(this.kmConstructor));
    }

    @Override // kotlin.reflect.jvm.internal.KotlinKCallable
    public Modality getModality() {
        return Modality.FINAL;
    }

    @Override // kotlin.reflect.jvm.internal.ReflectKFunction
    public boolean isPrimaryConstructor() {
        return !Attributes.isSecondary(this.kmConstructor);
    }
}
