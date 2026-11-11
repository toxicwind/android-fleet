package kotlin.reflect.jvm.internal;

import androidx.media3.extractor.text.ttml.TtmlNode;
import java.lang.reflect.Type;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KType;
import kotlin.reflect.KVisibility;
import kotlin.reflect.jvm.internal.impl.km.Attributes;
import kotlin.reflect.jvm.internal.impl.km.KmFunction;
import kotlin.reflect.jvm.internal.impl.km.KmType;
import kotlin.reflect.jvm.internal.impl.km.KmValueParameter;
import kotlin.reflect.jvm.internal.impl.km.Modality;
import kotlin.reflect.jvm.internal.impl.km.jvm.JvmExtensionsKt;
import kotlin.reflect.jvm.internal.impl.km.jvm.JvmMethodSignature;

/* JADX INFO: compiled from: KotlinKNamedFunction.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bR\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0011\u001a\u0004\u0018\u00010\u00128TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0010R\u0014\u0010\u0017\u001a\u00020\u00188TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\u00020\u001c8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u0014\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00180 X\u0082\u0004¢\u0006\u0002\n\u0000R\u0015\u0010!\u001a\u00020\u00058VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u001b\u0010$\u001a\u00020%8VX\u0096\u0084\n¢\u0006\f\n\u0004\b(\u0010)\u001a\u0004\b&\u0010'R\u0017\u0010*\u001a\u0004\u0018\u00010+8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b,\u0010-R\u0014\u0010.\u001a\u00020/8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b0\u00101R\u0015\u00102\u001a\u0002038VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b2\u00104R\u0015\u00105\u001a\u0002038VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b5\u00104R\u0015\u00106\u001a\u0002038VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b6\u00104R\u0015\u00107\u001a\u0002038VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b7\u00104R\u0015\u00108\u001a\u0002038VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b8\u00104R\u0014\u00109\u001a\u0002038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b9\u00104¨\u0006:"}, d2 = {"Lkotlin/reflect/jvm/internal/KotlinKNamedFunction;", "Lkotlin/reflect/jvm/internal/KotlinKFunction;", TtmlNode.RUBY_CONTAINER, "Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;", "signature", "", "rawBoundReceiver", "", "kmFunction", "Lkotlin/reflect/jvm/internal/impl/km/KmFunction;", "<init>", "(Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;Ljava/lang/String;Ljava/lang/Object;Lkotlin/metadata/KmFunction;)V", "contextParameters", "", "Lkotlin/reflect/jvm/internal/impl/km/KmValueParameter;", "getContextParameters", "()Ljava/util/List;", "extensionReceiverType", "Lkotlin/reflect/jvm/internal/impl/km/KmType;", "getExtensionReceiverType", "()Lkotlin/metadata/KmType;", "valueParameters", "getValueParameters", "typeParameterTable", "Lkotlin/reflect/jvm/internal/TypeParameterTable;", "getTypeParameterTable", "()Lkotlin/reflect/jvm/internal/TypeParameterTable;", "jvmSignature", "Lkotlin/reflect/jvm/internal/impl/km/jvm/JvmMethodSignature;", "getJvmSignature", "()Lkotlin/metadata/jvm/JvmMethodSignature;", "_typeParameterTable", "Lkotlin/Lazy;", "name", "getName", "()Ljava/lang/String;", "returnType", "Lkotlin/reflect/KType;", "getReturnType", "()Lkotlin/reflect/KType;", "returnType$delegate", "Lkotlin/Lazy;", "visibility", "Lkotlin/reflect/KVisibility;", "getVisibility", "()Lkotlin/reflect/KVisibility;", "modality", "Lkotlin/reflect/jvm/internal/impl/km/Modality;", "getModality", "()Lkotlin/metadata/Modality;", "isSuspend", "", "()Z", "isInline", "isExternal", "isOperator", "isInfix", "isPrimaryConstructor", "kotlin-reflection"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class KotlinKNamedFunction extends KotlinKFunction {
    private final Lazy<TypeParameterTable> _typeParameterTable;
    private final KmFunction kmFunction;

    /* JADX INFO: renamed from: returnType$delegate, reason: from kotlin metadata */
    private final Lazy returnType;

    @Override // kotlin.reflect.jvm.internal.ReflectKFunction
    public boolean isPrimaryConstructor() {
        return false;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KotlinKNamedFunction(final KDeclarationContainerImpl container, String signature, Object obj, KmFunction kmFunction) {
        super(container, signature, obj);
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(signature, "signature");
        Intrinsics.checkNotNullParameter(kmFunction, "kmFunction");
        this.kmFunction = kmFunction;
        this._typeParameterTable = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0(container, this) { // from class: kotlin.reflect.jvm.internal.KotlinKNamedFunction$$Lambda$0
            private final KDeclarationContainerImpl arg$0;
            private final KotlinKNamedFunction arg$1;

            {
                this.arg$0 = container;
                this.arg$1 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return KotlinKNamedFunction._typeParameterTable$lambda$0(this.arg$0, this.arg$1);
            }
        });
        this.returnType = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0(this, container) { // from class: kotlin.reflect.jvm.internal.KotlinKNamedFunction$$Lambda$1
            private final KotlinKNamedFunction arg$0;
            private final KDeclarationContainerImpl arg$1;

            {
                this.arg$0 = this;
                this.arg$1 = container;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return KotlinKNamedFunction.returnType_delegate$lambda$0(this.arg$0, this.arg$1);
            }
        });
    }

    @Override // kotlin.reflect.jvm.internal.KotlinKFunction
    protected List<KmValueParameter> getContextParameters() {
        return this.kmFunction.getContextParameters();
    }

    @Override // kotlin.reflect.jvm.internal.KotlinKFunction
    protected KmType getExtensionReceiverType() {
        return this.kmFunction.getReceiverParameterType();
    }

    @Override // kotlin.reflect.jvm.internal.KotlinKFunction
    protected List<KmValueParameter> getValueParameters() {
        return this.kmFunction.getValueParameters();
    }

    @Override // kotlin.reflect.jvm.internal.KotlinKFunction
    protected TypeParameterTable getTypeParameterTable() {
        return this._typeParameterTable.getValue();
    }

    @Override // kotlin.reflect.jvm.internal.KotlinKFunction
    protected JvmMethodSignature getJvmSignature() {
        JvmMethodSignature signature = JvmExtensionsKt.getSignature(this.kmFunction);
        if (signature != null) {
            return signature;
        }
        throw new KotlinReflectionInternalError("No signature for function: " + this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final TypeParameterTable _typeParameterTable$lambda$0(KDeclarationContainerImpl kDeclarationContainerImpl, KotlinKNamedFunction kotlinKNamedFunction) {
        KClassImpl kClassImpl = kDeclarationContainerImpl instanceof KClassImpl ? (KClassImpl) kDeclarationContainerImpl : null;
        TypeParameterTable typeParameterTable$kotlin_reflection = kClassImpl != null ? kClassImpl.getTypeParameterTable$kotlin_reflection() : null;
        ClassLoader classLoader = kDeclarationContainerImpl.getJClass().getClassLoader();
        Intrinsics.checkNotNullExpressionValue(classLoader, "getClassLoader(...)");
        return TypeParameterTable.INSTANCE.create(kotlinKNamedFunction.kmFunction.getTypeParameters(), typeParameterTable$kotlin_reflection, kotlinKNamedFunction, classLoader);
    }

    @Override // kotlin.reflect.KCallable
    public String getName() {
        return this.kmFunction.getName();
    }

    @Override // kotlin.reflect.KCallable
    public KType getReturnType() {
        return (KType) this.returnType.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final KType returnType_delegate$lambda$0(final KotlinKNamedFunction kotlinKNamedFunction, KDeclarationContainerImpl kDeclarationContainerImpl) {
        KmType returnType = kotlinKNamedFunction.kmFunction.getReturnType();
        ClassLoader classLoader = kDeclarationContainerImpl.getJClass().getClassLoader();
        Intrinsics.checkNotNullExpressionValue(classLoader, "getClassLoader(...)");
        return ConvertFromMetadataKt.toKType(returnType, classLoader, kotlinKNamedFunction.getTypeParameterTable(), new Function0(kotlinKNamedFunction) { // from class: kotlin.reflect.jvm.internal.KotlinKNamedFunction$$Lambda$2
            private final KotlinKNamedFunction arg$0;

            {
                this.arg$0 = kotlinKNamedFunction;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return KotlinKNamedFunction.returnType_delegate$lambda$0$0(this.arg$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Type returnType_delegate$lambda$0$0(KotlinKNamedFunction kotlinKNamedFunction) {
        Type typeExtractContinuationArgument = ReflectKFunctionKt.extractContinuationArgument(kotlinKNamedFunction);
        return typeExtractContinuationArgument == null ? kotlinKNamedFunction.getCaller().getReturnType() : typeExtractContinuationArgument;
    }

    @Override // kotlin.reflect.KCallable
    public KVisibility getVisibility() {
        return ConvertFromMetadataKt.toKVisibility(Attributes.getVisibility(this.kmFunction));
    }

    @Override // kotlin.reflect.jvm.internal.KotlinKCallable
    public Modality getModality() {
        return Attributes.getModality(this.kmFunction);
    }

    @Override // kotlin.reflect.KCallable
    public boolean isSuspend() {
        return Attributes.isSuspend(this.kmFunction);
    }

    @Override // kotlin.reflect.KFunction
    public boolean isInline() {
        return Attributes.isInline(this.kmFunction);
    }

    @Override // kotlin.reflect.KFunction
    public boolean isExternal() {
        return Attributes.isExternal(this.kmFunction);
    }

    @Override // kotlin.reflect.KFunction
    public boolean isOperator() {
        return Attributes.isOperator(this.kmFunction);
    }

    @Override // kotlin.reflect.KFunction
    public boolean isInfix() {
        return Attributes.isInfix(this.kmFunction);
    }
}
