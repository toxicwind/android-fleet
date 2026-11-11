package kotlin.reflect.jvm.internal;

import androidx.media3.extractor.text.ttml.TtmlNode;
import java.lang.reflect.Constructor;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.ClassBasedDeclarationContainer;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionBase;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.KotlinGenericDeclarationKt;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.reflect.KClass;
import kotlin.reflect.KParameter;
import kotlin.reflect.KProperty;
import kotlin.reflect.full.KCallables;
import kotlin.reflect.jvm.internal.calls.AnnotationConstructorCaller;
import kotlin.reflect.jvm.internal.calls.Caller;
import kotlin.reflect.jvm.internal.calls.CallerImpl;
import kotlin.reflect.jvm.internal.calls.CallerKt;
import kotlin.reflect.jvm.internal.calls.ValueClassAwareCallerKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.InlineClassesUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.InlineClassManglingRulesKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.types.DescriptorKType;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: DescriptorKFunction.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u001e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\b\u0000\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\u00020\u00032\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00042\u00020\u0005B=\b\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0004\b\u0010\u0010\u0011B+\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0010\u0010\u0013B#\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\u0014\u001a\u00020\f\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f¢\u0006\u0004\b\u0010\u0010\u0015J\u0015\u0010.\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u000fH\u0010¢\u0006\u0002\b/J\u0012\u00100\u001a\u0004\u0018\u00010\u00032\u0006\u00101\u001a\u00020\u0003H\u0002J\u0010\u00102\u001a\u0002032\u0006\u00104\u001a\u000205H\u0002J\u001c\u00106\u001a\u0006\u0012\u0002\b\u00030\"2\u0006\u00104\u001a\u0002052\u0006\u00107\u001a\u000203H\u0002J\u0010\u00108\u001a\u0002092\u0006\u00104\u001a\u000205H\u0002J\u0010\u0010:\u001a\u0002092\u0006\u00104\u001a\u000205H\u0002J.\u0010;\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030=0<2\n\u00104\u001a\u0006\u0012\u0002\b\u00030=2\u0006\u0010\u0014\u001a\u00020\f2\u0006\u0010>\u001a\u000203H\u0002J\b\u0010?\u001a\u00020@H\u0014J\u0014\u0010L\u001a\u0002032\b\u0010M\u001a\u0004\u0018\u00010\u0002H\u0096\u0082\u0004J\n\u0010N\u001a\u00020BH\u0096\u0080\u0004J\n\u0010O\u001a\u00020\tH\u0096\u0080\u0004R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\n\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0016\u0010\r\u001a\u0004\u0018\u00010\u0002X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u001b\u0010\u0014\u001a\u00020\f8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b\u001c\u0010\u001dR\u0015\u0010\b\u001a\u00020\t8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b \u0010\u0019R\u001f\u0010!\u001a\u0006\u0012\u0002\b\u00030\"8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b%\u0010&\u001a\u0004\b#\u0010$R!\u0010'\u001a\b\u0012\u0002\b\u0003\u0018\u00010\"8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b)\u0010&\u001a\u0004\b(\u0010$R\u001a\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00030+8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b,\u0010-R\u0015\u0010A\u001a\u00020B8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\bC\u0010DR\u0015\u0010E\u001a\u0002038VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\bE\u0010FR\u0015\u0010G\u001a\u0002038VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\bG\u0010FR\u0015\u0010H\u001a\u0002038VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\bH\u0010FR\u0015\u0010I\u001a\u0002038VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\bI\u0010FR\u0015\u0010J\u001a\u0002038VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\bJ\u0010FR\u0014\u0010K\u001a\u0002038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bK\u0010F¨\u0006P"}, d2 = {"Lkotlin/reflect/jvm/internal/DescriptorKFunction;", "Lkotlin/reflect/jvm/internal/DescriptorKCallable;", "", "Lkotlin/reflect/jvm/internal/ReflectKFunction;", "Lkotlin/jvm/internal/FunctionBase;", "Lkotlin/reflect/jvm/internal/FunctionWithAllInvokes;", TtmlNode.RUBY_CONTAINER, "Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;", "name", "", "signature", "descriptorInitialValue", "Lkotlin/reflect/jvm/internal/impl/descriptors/FunctionDescriptor;", "rawBoundReceiver", "overriddenStorage", "Lkotlin/reflect/jvm/internal/KCallableOverriddenStorage;", "<init>", "(Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;Ljava/lang/String;Ljava/lang/String;Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;Ljava/lang/Object;Lkotlin/reflect/jvm/internal/KCallableOverriddenStorage;)V", "boundReceiver", "(Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V", "descriptor", "(Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;Lkotlin/reflect/jvm/internal/KCallableOverriddenStorage;)V", "getContainer", "()Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;", "getSignature", "()Ljava/lang/String;", "getRawBoundReceiver", "()Ljava/lang/Object;", "getDescriptor", "()Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;", "descriptor$delegate", "Lkotlin/reflect/jvm/internal/ReflectProperties$LazySoftVal;", "getName", "caller", "Lkotlin/reflect/jvm/internal/calls/Caller;", "getCaller", "()Lkotlin/reflect/jvm/internal/calls/Caller;", "caller$delegate", "Lkotlin/Lazy;", "defaultCaller", "getDefaultCaller", "defaultCaller$delegate", "overridden", "", "getOverridden", "()Ljava/util/Collection;", "shallowCopy", "shallowCopy$kotlin_reflection", "getFunctionWithDefaultParametersForValueClassOverride", "function", "useBoxedBoundReceiver", "", "member", "Ljava/lang/reflect/Method;", "createStaticMethodCaller", "isCallByToValueClassMangledMethod", "createJvmStaticInObjectCaller", "Lkotlin/reflect/jvm/internal/calls/CallerImpl$Method;", "createInstanceMethodCaller", "createConstructorCaller", "Lkotlin/reflect/jvm/internal/calls/CallerImpl;", "Ljava/lang/reflect/Constructor;", "isDefault", "computeReturnType", "Lkotlin/reflect/jvm/internal/types/DescriptorKType;", "arity", "", "getArity", "()I", "isInline", "()Z", "isExternal", "isOperator", "isInfix", "isSuspend", "isPrimaryConstructor", "equals", "other", "hashCode", "toString", "kotlin-reflection"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class DescriptorKFunction extends DescriptorKCallable<Object> implements FunctionBase<Object>, FunctionWithAllInvokes, ReflectKFunction {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl(DescriptorKFunction.class, "descriptor", "getDescriptor()Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;", 0)};

    /* JADX INFO: renamed from: caller$delegate, reason: from kotlin metadata */
    private final Lazy caller;
    private final KDeclarationContainerImpl container;

    /* JADX INFO: renamed from: defaultCaller$delegate, reason: from kotlin metadata */
    private final Lazy defaultCaller;

    /* JADX INFO: renamed from: descriptor$delegate, reason: from kotlin metadata */
    private final ReflectProperties.LazySoftVal descriptor;
    private final Object rawBoundReceiver;
    private final String signature;

    public GenericDeclaration default$findJavaDeclaration() {
        return KotlinGenericDeclarationKt.findMethodBySignature(getContainer(), getSignature());
    }

    private DescriptorKFunction(KDeclarationContainerImpl kDeclarationContainerImpl, final String str, String str2, FunctionDescriptor functionDescriptor, Object obj, KCallableOverriddenStorage kCallableOverriddenStorage) {
        super(kCallableOverriddenStorage);
        this.container = kDeclarationContainerImpl;
        this.signature = str2;
        this.rawBoundReceiver = obj;
        this.descriptor = ReflectProperties.lazySoft(functionDescriptor, new Function0(this, str) { // from class: kotlin.reflect.jvm.internal.DescriptorKFunction$$Lambda$0
            private final DescriptorKFunction arg$0;
            private final String arg$1;

            {
                this.arg$0 = this;
                this.arg$1 = str;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return DescriptorKFunction.descriptor_delegate$lambda$0(this.arg$0, this.arg$1);
            }
        });
        this.caller = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0(this) { // from class: kotlin.reflect.jvm.internal.DescriptorKFunction$$Lambda$1
            private final DescriptorKFunction arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return DescriptorKFunction.caller_delegate$lambda$0(this.arg$0);
            }
        });
        this.defaultCaller = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0(this) { // from class: kotlin.reflect.jvm.internal.DescriptorKFunction$$Lambda$2
            private final DescriptorKFunction arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return DescriptorKFunction.defaultCaller_delegate$lambda$0(this.arg$0);
            }
        });
    }

    @Override // kotlin.jvm.internal.KotlinGenericDeclaration
    public /* bridge */ GenericDeclaration findJavaDeclaration() {
        return default$findJavaDeclaration();
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ Object invoke() {
        return default$invoke();
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ Object invoke(Object obj) {
        return default$invoke(obj);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ Object invoke(Object obj, Object obj2) {
        return default$invoke(obj, obj2);
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ Object invoke(Object obj, Object obj2, Object obj3) {
        return default$invoke(obj, obj2, obj3);
    }

    @Override // kotlin.jvm.functions.Function4
    public /* bridge */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        return default$invoke(obj, obj2, obj3, obj4);
    }

    @Override // kotlin.jvm.functions.Function5
    public /* bridge */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        return default$invoke(obj, obj2, obj3, obj4, obj5);
    }

    @Override // kotlin.jvm.functions.Function6
    public /* bridge */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        return default$invoke(obj, obj2, obj3, obj4, obj5, obj6);
    }

    @Override // kotlin.jvm.functions.Function7
    public /* bridge */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        return default$invoke(obj, obj2, obj3, obj4, obj5, obj6, obj7);
    }

    @Override // kotlin.jvm.functions.Function8
    public /* bridge */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        return default$invoke(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8);
    }

    @Override // kotlin.jvm.functions.Function9
    public /* bridge */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        return default$invoke(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9);
    }

    @Override // kotlin.jvm.functions.Function10
    public /* bridge */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        return default$invoke(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10);
    }

    @Override // kotlin.jvm.functions.Function11
    public /* bridge */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11) {
        return default$invoke(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11);
    }

    @Override // kotlin.jvm.functions.Function12
    public /* bridge */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12) {
        return default$invoke(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12);
    }

    @Override // kotlin.jvm.functions.Function13
    public /* bridge */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12, Object obj13) {
        return default$invoke(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13);
    }

    @Override // kotlin.jvm.functions.Function14
    public /* bridge */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12, Object obj13, Object obj14) {
        return default$invoke(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14);
    }

    @Override // kotlin.jvm.functions.Function15
    public /* bridge */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12, Object obj13, Object obj14, Object obj15) {
        return default$invoke(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14, obj15);
    }

    @Override // kotlin.jvm.functions.Function16
    public /* bridge */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12, Object obj13, Object obj14, Object obj15, Object obj16) {
        return default$invoke(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14, obj15, obj16);
    }

    @Override // kotlin.jvm.functions.Function17
    public /* bridge */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12, Object obj13, Object obj14, Object obj15, Object obj16, Object obj17) {
        return default$invoke(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14, obj15, obj16, obj17);
    }

    @Override // kotlin.jvm.functions.Function18
    public /* bridge */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12, Object obj13, Object obj14, Object obj15, Object obj16, Object obj17, Object obj18) {
        return default$invoke(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14, obj15, obj16, obj17, obj18);
    }

    @Override // kotlin.jvm.functions.Function19
    public /* bridge */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12, Object obj13, Object obj14, Object obj15, Object obj16, Object obj17, Object obj18, Object obj19) {
        return default$invoke(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14, obj15, obj16, obj17, obj18, obj19);
    }

    @Override // kotlin.jvm.functions.Function20
    public /* bridge */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12, Object obj13, Object obj14, Object obj15, Object obj16, Object obj17, Object obj18, Object obj19, Object obj20) {
        return default$invoke(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14, obj15, obj16, obj17, obj18, obj19, obj20);
    }

    @Override // kotlin.jvm.functions.Function21
    public /* bridge */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12, Object obj13, Object obj14, Object obj15, Object obj16, Object obj17, Object obj18, Object obj19, Object obj20, Object obj21) {
        return default$invoke(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14, obj15, obj16, obj17, obj18, obj19, obj20, obj21);
    }

    @Override // kotlin.jvm.functions.Function22
    public /* bridge */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12, Object obj13, Object obj14, Object obj15, Object obj16, Object obj17, Object obj18, Object obj19, Object obj20, Object obj21, Object obj22) {
        return default$invoke(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14, obj15, obj16, obj17, obj18, obj19, obj20, obj21, obj22);
    }

    @Override // kotlin.reflect.jvm.internal.ReflectKCallable
    public KDeclarationContainerImpl getContainer() {
        return this.container;
    }

    @Override // kotlin.reflect.jvm.internal.ReflectKFunction
    public String getSignature() {
        return this.signature;
    }

    @Override // kotlin.reflect.jvm.internal.ReflectKCallable
    public Object getRawBoundReceiver() {
        return this.rawBoundReceiver;
    }

    public Object default$invoke() {
        return call(new Object[0]);
    }

    public Object default$invoke(Object obj) {
        return call(obj);
    }

    public Object default$invoke(Object obj, Object obj2) {
        return call(obj, obj2);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DescriptorKFunction(KDeclarationContainerImpl container, String name, String signature, Object obj) {
        this(container, name, signature, null, obj, KCallableOverriddenStorage.INSTANCE.getEMPTY());
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(signature, "signature");
    }

    public Object default$invoke(Object obj, Object obj2, Object obj3) {
        return call(obj, obj2, obj3);
    }

    public Object default$invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        return call(obj, obj2, obj3, obj4);
    }

    public Object default$invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        return call(obj, obj2, obj3, obj4, obj5);
    }

    public Object default$invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        return call(obj, obj2, obj3, obj4, obj5, obj6);
    }

    public Object default$invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        return call(obj, obj2, obj3, obj4, obj5, obj6, obj7);
    }

    public /* synthetic */ DescriptorKFunction(KDeclarationContainerImpl kDeclarationContainerImpl, FunctionDescriptor functionDescriptor, KCallableOverriddenStorage kCallableOverriddenStorage, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(kDeclarationContainerImpl, functionDescriptor, (i & 4) != 0 ? KCallableOverriddenStorage.INSTANCE.getEMPTY() : kCallableOverriddenStorage);
    }

    public Object default$invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        return call(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8);
    }

    public Object default$invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        return call(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9);
    }

    public Object default$invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        return call(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public DescriptorKFunction(KDeclarationContainerImpl container, FunctionDescriptor descriptor, KCallableOverriddenStorage overriddenStorage) {
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(overriddenStorage, "overriddenStorage");
        String strAsString = descriptor.getName().asString();
        Intrinsics.checkNotNullExpressionValue(strAsString, "asString(...)");
        this(container, strAsString, RuntimeTypeMapper.INSTANCE.mapSignature(descriptor).get_signature(), descriptor, CallableReference.NO_RECEIVER, overriddenStorage);
    }

    public Object default$invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11) {
        return call(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11);
    }

    public Object default$invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12) {
        return call(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12);
    }

    public Object default$invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12, Object obj13) {
        return call(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13);
    }

    public Object default$invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12, Object obj13, Object obj14) {
        return call(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14);
    }

    public Object default$invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12, Object obj13, Object obj14, Object obj15) {
        return call(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14, obj15);
    }

    public Object default$invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12, Object obj13, Object obj14, Object obj15, Object obj16) {
        return call(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14, obj15, obj16);
    }

    public Object default$invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12, Object obj13, Object obj14, Object obj15, Object obj16, Object obj17) {
        return call(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14, obj15, obj16, obj17);
    }

    public Object default$invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12, Object obj13, Object obj14, Object obj15, Object obj16, Object obj17, Object obj18) {
        return call(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14, obj15, obj16, obj17, obj18);
    }

    @Override // kotlin.reflect.jvm.internal.DescriptorKCallable
    public FunctionDescriptor getDescriptor() {
        T value = this.descriptor.getValue(this, $$delegatedProperties[0]);
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (FunctionDescriptor) value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FunctionDescriptor descriptor_delegate$lambda$0(DescriptorKFunction descriptorKFunction, String str) {
        return descriptorKFunction.getContainer().findFunctionDescriptor(str, descriptorKFunction.getSignature());
    }

    public Object default$invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12, Object obj13, Object obj14, Object obj15, Object obj16, Object obj17, Object obj18, Object obj19) {
        return call(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14, obj15, obj16, obj17, obj18, obj19);
    }

    public Object default$invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12, Object obj13, Object obj14, Object obj15, Object obj16, Object obj17, Object obj18, Object obj19, Object obj20) {
        return call(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14, obj15, obj16, obj17, obj18, obj19, obj20);
    }

    public Object default$invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12, Object obj13, Object obj14, Object obj15, Object obj16, Object obj17, Object obj18, Object obj19, Object obj20, Object obj21) {
        return call(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14, obj15, obj16, obj17, obj18, obj19, obj20, obj21);
    }

    public Object default$invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12, Object obj13, Object obj14, Object obj15, Object obj16, Object obj17, Object obj18, Object obj19, Object obj20, Object obj21, Object obj22) {
        return call(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14, obj15, obj16, obj17, obj18, obj19, obj20, obj21, obj22);
    }

    @Override // kotlin.reflect.KCallable
    public String getName() {
        String strAsString = getDescriptor().getName().asString();
        Intrinsics.checkNotNullExpressionValue(strAsString, "asString(...)");
        return strAsString;
    }

    @Override // kotlin.reflect.jvm.internal.ReflectKCallable
    public Caller<?> getCaller() {
        return (Caller) this.caller.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Caller caller_delegate$lambda$0(DescriptorKFunction descriptorKFunction) {
        Constructor<?> constructorFindMethodBySignature;
        CallerImpl.Method methodCreateStaticMethodCaller;
        JvmFunctionSignature jvmFunctionSignatureMapSignature = RuntimeTypeMapper.INSTANCE.mapSignature(descriptorKFunction.getDescriptor());
        if (jvmFunctionSignatureMapSignature instanceof JvmFunctionSignature.KotlinConstructor) {
            if (ReflectKCallableKt.isAnnotationConstructor(descriptorKFunction)) {
                Class<?> jClass = descriptorKFunction.getContainer().getJClass();
                List<KParameter> parameters = descriptorKFunction.getParameters();
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(parameters, 10));
                Iterator<T> it = parameters.iterator();
                while (it.hasNext()) {
                    String name = ((KParameter) it.next()).getName();
                    Intrinsics.checkNotNull(name);
                    arrayList.add(name);
                }
                return new AnnotationConstructorCaller(jClass, arrayList, AnnotationConstructorCaller.CallMode.POSITIONAL_CALL, AnnotationConstructorCaller.Origin.KOTLIN, null, 16, null);
            }
            constructorFindMethodBySignature = descriptorKFunction.getContainer().findConstructorBySignature(((JvmFunctionSignature.KotlinConstructor) jvmFunctionSignatureMapSignature).getConstructorDesc());
        } else if (jvmFunctionSignatureMapSignature instanceof JvmFunctionSignature.KotlinFunction) {
            JvmFunctionSignature.KotlinFunction kotlinFunction = (JvmFunctionSignature.KotlinFunction) jvmFunctionSignatureMapSignature;
            constructorFindMethodBySignature = descriptorKFunction.getContainer().findMethodBySignature(kotlinFunction.getMethodName(), kotlinFunction.getMethodDesc());
        } else if (jvmFunctionSignatureMapSignature instanceof JvmFunctionSignature.JavaMethod) {
            Method method = ((JvmFunctionSignature.JavaMethod) jvmFunctionSignatureMapSignature).getMethod();
            Intrinsics.checkNotNull(method, "null cannot be cast to non-null type java.lang.reflect.Member");
            constructorFindMethodBySignature = method;
        } else {
            if (!(jvmFunctionSignatureMapSignature instanceof JvmFunctionSignature.JavaConstructor)) {
                if (!(jvmFunctionSignatureMapSignature instanceof JvmFunctionSignature.FakeJavaAnnotationConstructor)) {
                    throw new NoWhenBranchMatchedException();
                }
                List<Method> methods = ((JvmFunctionSignature.FakeJavaAnnotationConstructor) jvmFunctionSignatureMapSignature).getMethods();
                Class<?> jClass2 = descriptorKFunction.getContainer().getJClass();
                List<Method> list = methods;
                ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                Iterator<T> it2 = list.iterator();
                while (it2.hasNext()) {
                    arrayList2.add(((Method) it2.next()).getName());
                }
                return new AnnotationConstructorCaller(jClass2, arrayList2, AnnotationConstructorCaller.CallMode.POSITIONAL_CALL, AnnotationConstructorCaller.Origin.JAVA, methods);
            }
            Constructor<?> constructor = ((JvmFunctionSignature.JavaConstructor) jvmFunctionSignatureMapSignature).getConstructor();
            Intrinsics.checkNotNull(constructor, "null cannot be cast to non-null type java.lang.reflect.Member");
            constructorFindMethodBySignature = constructor;
        }
        if (constructorFindMethodBySignature instanceof Constructor) {
            methodCreateStaticMethodCaller = descriptorKFunction.createConstructorCaller((Constructor) constructorFindMethodBySignature, descriptorKFunction.getDescriptor(), false);
        } else if (constructorFindMethodBySignature instanceof Method) {
            Method method2 = (Method) constructorFindMethodBySignature;
            if (!Modifier.isStatic(method2.getModifiers())) {
                methodCreateStaticMethodCaller = descriptorKFunction.createInstanceMethodCaller(method2);
            } else if (descriptorKFunction.getDescriptor().getAnnotations().mo10237findAnnotation(UtilKt.getJVM_STATIC()) != null) {
                methodCreateStaticMethodCaller = descriptorKFunction.createJvmStaticInObjectCaller(method2);
            } else {
                methodCreateStaticMethodCaller = descriptorKFunction.createStaticMethodCaller(method2, false);
            }
        } else {
            throw new KotlinReflectionInternalError("Could not compute caller for function: " + descriptorKFunction.getDescriptor() + " (member = " + constructorFindMethodBySignature + ')');
        }
        return ValueClassAwareCallerKt.createValueClassAwareCallerIfNeeded(methodCreateStaticMethodCaller, descriptorKFunction, false, CollectionsKt.emptyList());
    }

    @Override // kotlin.reflect.jvm.internal.ReflectKCallable
    public Caller<?> getDefaultCaller() {
        return (Caller) this.defaultCaller.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:44:0x01b3  */
    public static final Caller defaultCaller_delegate$lambda$0(DescriptorKFunction descriptorKFunction) {
        Constructor<?> constructorFindDefaultConstructor;
        CallerImpl.Method methodCreateStaticMethodCaller;
        ArrayList arrayList = new ArrayList();
        JvmFunctionSignature jvmFunctionSignatureMapSignature = RuntimeTypeMapper.INSTANCE.mapSignature(descriptorKFunction.getDescriptor());
        if (jvmFunctionSignatureMapSignature instanceof JvmFunctionSignature.KotlinFunction) {
            DescriptorKFunction descriptorKFunction2 = descriptorKFunction;
            ReflectKFunction functionWithDefaultParametersForValueClassOverride = descriptorKFunction.getFunctionWithDefaultParametersForValueClassOverride(descriptorKFunction2);
            if (functionWithDefaultParametersForValueClassOverride != null) {
                String strSubstringBefore$default = StringsKt.substringBefore$default(functionWithDefaultParametersForValueClassOverride.getSignature(), '(', (String) null, 2, (Object) null);
                String strSubstring = functionWithDefaultParametersForValueClassOverride.getSignature().substring(strSubstringBefore$default.length());
                Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                DescriptorPatchingResult descriptorPatchingResultPatchJvmDescriptorByExtraBoxing = ReflectKFunctionKt.patchJvmDescriptorByExtraBoxing(functionWithDefaultParametersForValueClassOverride, strSubstring);
                arrayList.addAll(descriptorPatchingResultPatchJvmDescriptorByExtraBoxing.getBoxedIndices());
                constructorFindDefaultConstructor = descriptorKFunction.getContainer().findDefaultMethod(strSubstringBefore$default, descriptorPatchingResultPatchJvmDescriptorByExtraBoxing.getNewDescriptor(), true, descriptorKFunction.getDescriptor().getExtensionReceiverParameter() != null);
            } else {
                JvmFunctionSignature.KotlinFunction kotlinFunction = (JvmFunctionSignature.KotlinFunction) jvmFunctionSignatureMapSignature;
                DescriptorPatchingResult descriptorPatchingResultPatchJvmDescriptorByExtraBoxing2 = ReflectKFunctionKt.patchJvmDescriptorByExtraBoxing(descriptorKFunction2, kotlinFunction.getMethodDesc());
                arrayList.addAll(descriptorPatchingResultPatchJvmDescriptorByExtraBoxing2.getBoxedIndices());
                KDeclarationContainerImpl container = descriptorKFunction.getContainer();
                String methodName = kotlinFunction.getMethodName();
                String newDescriptor = descriptorPatchingResultPatchJvmDescriptorByExtraBoxing2.getNewDescriptor();
                Member memberMo10234getMember = descriptorKFunction.getCaller().mo10234getMember();
                Intrinsics.checkNotNull(memberMo10234getMember);
                constructorFindDefaultConstructor = container.findDefaultMethod(methodName, newDescriptor, !Modifier.isStatic(memberMo10234getMember.getModifiers()), descriptorKFunction.getDescriptor().getExtensionReceiverParameter() != null);
            }
        } else if (jvmFunctionSignatureMapSignature instanceof JvmFunctionSignature.KotlinConstructor) {
            if (ReflectKCallableKt.isAnnotationConstructor(descriptorKFunction)) {
                Class<?> jClass = descriptorKFunction.getContainer().getJClass();
                List<KParameter> parameters = descriptorKFunction.getParameters();
                ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(parameters, 10));
                Iterator<T> it = parameters.iterator();
                while (it.hasNext()) {
                    String name = ((KParameter) it.next()).getName();
                    Intrinsics.checkNotNull(name);
                    arrayList2.add(name);
                }
                return new AnnotationConstructorCaller(jClass, arrayList2, AnnotationConstructorCaller.CallMode.CALL_BY_NAME, AnnotationConstructorCaller.Origin.KOTLIN, null, 16, null);
            }
            DescriptorPatchingResult descriptorPatchingResultPatchJvmDescriptorByExtraBoxing3 = ReflectKFunctionKt.patchJvmDescriptorByExtraBoxing(descriptorKFunction, ((JvmFunctionSignature.KotlinConstructor) jvmFunctionSignatureMapSignature).getConstructorDesc());
            arrayList.addAll(descriptorPatchingResultPatchJvmDescriptorByExtraBoxing3.getBoxedIndices());
            constructorFindDefaultConstructor = descriptorKFunction.getContainer().findDefaultConstructor(descriptorPatchingResultPatchJvmDescriptorByExtraBoxing3.getNewDescriptor());
        } else {
            if (jvmFunctionSignatureMapSignature instanceof JvmFunctionSignature.FakeJavaAnnotationConstructor) {
                List<Method> methods = ((JvmFunctionSignature.FakeJavaAnnotationConstructor) jvmFunctionSignatureMapSignature).getMethods();
                Class<?> jClass2 = descriptorKFunction.getContainer().getJClass();
                List<Method> list = methods;
                ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                Iterator<T> it2 = list.iterator();
                while (it2.hasNext()) {
                    arrayList3.add(((Method) it2.next()).getName());
                }
                return new AnnotationConstructorCaller(jClass2, arrayList3, AnnotationConstructorCaller.CallMode.CALL_BY_NAME, AnnotationConstructorCaller.Origin.JAVA, methods);
            }
            constructorFindDefaultConstructor = null;
        }
        if (constructorFindDefaultConstructor instanceof Constructor) {
            methodCreateStaticMethodCaller = descriptorKFunction.createConstructorCaller((Constructor) constructorFindDefaultConstructor, descriptorKFunction.getDescriptor(), true);
        } else if (!(constructorFindDefaultConstructor instanceof Method)) {
            methodCreateStaticMethodCaller = null;
        } else if (descriptorKFunction.getDescriptor().getAnnotations().mo10237findAnnotation(UtilKt.getJVM_STATIC()) != null) {
            DeclarationDescriptor containingDeclaration = descriptorKFunction.getDescriptor().getContainingDeclaration();
            Intrinsics.checkNotNull(containingDeclaration, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
            if (!((ClassDescriptor) containingDeclaration).isCompanionObject()) {
                methodCreateStaticMethodCaller = descriptorKFunction.createJvmStaticInObjectCaller((Method) constructorFindDefaultConstructor);
            } else {
                methodCreateStaticMethodCaller = descriptorKFunction.createStaticMethodCaller((Method) constructorFindDefaultConstructor, descriptorKFunction.getCaller().isBoundInstanceCallWithValueClasses());
            }
        } else {
            methodCreateStaticMethodCaller = descriptorKFunction.createStaticMethodCaller((Method) constructorFindDefaultConstructor, descriptorKFunction.getCaller().isBoundInstanceCallWithValueClasses());
        }
        if (methodCreateStaticMethodCaller != null) {
            return ValueClassAwareCallerKt.createValueClassAwareCallerIfNeeded(methodCreateStaticMethodCaller, descriptorKFunction, true, arrayList);
        }
        return null;
    }

    @Override // kotlin.reflect.jvm.internal.ReflectKFunction
    public Collection<ReflectKFunction> getOverridden() {
        Collection<? extends FunctionDescriptor> overriddenDescriptors = getDescriptor().getOverriddenDescriptors();
        Intrinsics.checkNotNullExpressionValue(overriddenDescriptors, "getOverriddenDescriptors(...)");
        Collection<? extends FunctionDescriptor> collection = overriddenDescriptors;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection, 10));
        for (FunctionDescriptor functionDescriptor : collection) {
            DeclarationDescriptor containingDeclaration = functionDescriptor.getContainingDeclaration();
            Intrinsics.checkNotNull(containingDeclaration, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
            Class<?> javaClass = UtilKt.toJavaClass((ClassDescriptor) containingDeclaration);
            if (javaClass == null) {
                throw new KotlinReflectionInternalError("Unknown container class for overridden function: " + this);
            }
            KClass kotlinClass = JvmClassMappingKt.getKotlinClass(javaClass);
            Intrinsics.checkNotNull(kotlinClass, "null cannot be cast to non-null type kotlin.reflect.jvm.internal.KClassImpl<*>");
            Intrinsics.checkNotNull(functionDescriptor);
            arrayList.add(new DescriptorKFunction((KClassImpl) kotlinClass, functionDescriptor, null, 4, null));
        }
        return arrayList;
    }

    @Override // kotlin.reflect.jvm.internal.DescriptorKCallable
    public DescriptorKCallable<Object> shallowCopy$kotlin_reflection(KCallableOverriddenStorage overriddenStorage) {
        Intrinsics.checkNotNullParameter(overriddenStorage, "overriddenStorage");
        return new DescriptorKFunction(getContainer(), getDescriptor(), overriddenStorage);
    }

    private final ReflectKFunction getFunctionWithDefaultParametersForValueClassOverride(ReflectKFunction function) {
        List<KParameter> valueParameters = KCallables.getValueParameters(function);
        Object obj = null;
        if (!(valueParameters instanceof Collection) || !valueParameters.isEmpty()) {
            for (KParameter kParameter : valueParameters) {
                ReflectKParameter reflectKParameter = kParameter instanceof ReflectKParameter ? (ReflectKParameter) kParameter : null;
                if (reflectKParameter != null && reflectKParameter.getDeclaresDefaultValue()) {
                    return null;
                }
            }
        }
        ClassBasedDeclarationContainer container = function.getContainer();
        KClass kClass = container instanceof KClass ? (KClass) container : null;
        if (kClass == null || !kClass.isValue()) {
            return null;
        }
        Member memberMo10234getMember = getCaller().mo10234getMember();
        Intrinsics.checkNotNull(memberMo10234getMember);
        if (!Modifier.isStatic(memberMo10234getMember.getModifiers())) {
            return null;
        }
        loop0: for (Object obj2 : function.getOverridden()) {
            List<KParameter> valueParameters2 = KCallables.getValueParameters((ReflectKFunction) obj2);
            if (!(valueParameters2 instanceof Collection) || !valueParameters2.isEmpty()) {
                for (KParameter kParameter2 : valueParameters2) {
                    ReflectKParameter reflectKParameter2 = kParameter2 instanceof ReflectKParameter ? (ReflectKParameter) kParameter2 : null;
                    if (reflectKParameter2 != null && reflectKParameter2.getDeclaresDefaultValue()) {
                        obj = obj2;
                        break loop0;
                    }
                }
            }
        }
        return (ReflectKFunction) obj;
    }

    private final boolean useBoxedBoundReceiver(Method member) {
        KotlinType type;
        ReceiverParameterDescriptor dispatchReceiverParameter = getDescriptor().getDispatchReceiverParameter();
        if (dispatchReceiverParameter == null || (type = dispatchReceiverParameter.getType()) == null || !InlineClassesUtilsKt.isInlineClassType(type)) {
            return false;
        }
        Class<?>[] parameterTypes = member.getParameterTypes();
        Intrinsics.checkNotNullExpressionValue(parameterTypes, "getParameterTypes(...)");
        Class cls = (Class) ArraysKt.firstOrNull(parameterTypes);
        return cls != null && cls.isInterface();
    }

    private final Caller<?> createStaticMethodCaller(Method member, boolean isCallByToValueClassMangledMethod) {
        DescriptorKFunction descriptorKFunction = this;
        if (ReflectKCallableKt.isBound(descriptorKFunction)) {
            return new CallerImpl.Method.BoundStatic(member, isCallByToValueClassMangledMethod, useBoxedBoundReceiver(member) ? getRawBoundReceiver() : ReflectKCallableKt.getBoundReceiver(descriptorKFunction));
        }
        return new CallerImpl.Method.Static(member);
    }

    private final CallerImpl.Method createJvmStaticInObjectCaller(Method member) {
        return ReflectKCallableKt.isBound(this) ? new CallerImpl.Method.BoundJvmStaticInObject(member) : new CallerImpl.Method.JvmStaticInObject(member);
    }

    private final CallerImpl.Method createInstanceMethodCaller(Method member) {
        DescriptorKFunction descriptorKFunction = this;
        return ReflectKCallableKt.isBound(descriptorKFunction) ? new CallerImpl.Method.BoundInstance(member, ReflectKCallableKt.getBoundReceiver(descriptorKFunction)) : new CallerImpl.Method.Instance(member);
    }

    private final CallerImpl<Constructor<?>> createConstructorCaller(Constructor<?> member, FunctionDescriptor descriptor, boolean isDefault) {
        if (!isDefault && InlineClassManglingRulesKt.shouldHideConstructorDueToValueClassTypeValueParameters(descriptor)) {
            DescriptorKFunction descriptorKFunction = this;
            if (ReflectKCallableKt.isBound(descriptorKFunction)) {
                return new CallerImpl.AccessorForHiddenBoundConstructor(member, ReflectKCallableKt.getBoundReceiver(descriptorKFunction));
            }
            return new CallerImpl.AccessorForHiddenConstructor(member);
        }
        DescriptorKFunction descriptorKFunction2 = this;
        if (ReflectKCallableKt.isBound(descriptorKFunction2)) {
            return new CallerImpl.BoundConstructor(member, ReflectKCallableKt.getBoundReceiver(descriptorKFunction2));
        }
        return new CallerImpl.Constructor(member);
    }

    @Override // kotlin.reflect.jvm.internal.DescriptorKCallable
    protected DescriptorKType computeReturnType() {
        KotlinType returnType = getDescriptor().getReturnType();
        Intrinsics.checkNotNull(returnType);
        return new DescriptorKType(returnType, new Function0(this) { // from class: kotlin.reflect.jvm.internal.DescriptorKFunction$$Lambda$3
            private final DescriptorKFunction arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return DescriptorKFunction.computeReturnType$lambda$0(this.arg$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Type computeReturnType$lambda$0(DescriptorKFunction descriptorKFunction) {
        Type typeExtractContinuationArgument = ReflectKFunctionKt.extractContinuationArgument(descriptorKFunction);
        return typeExtractContinuationArgument == null ? descriptorKFunction.getCaller().getReturnType() : typeExtractContinuationArgument;
    }

    @Override // kotlin.jvm.internal.FunctionBase
    public int getArity() {
        return CallerKt.getArity(getCaller());
    }

    @Override // kotlin.reflect.KFunction
    public boolean isInline() {
        return getOverriddenStorage().getForceIsInline() || getDescriptor().isInline();
    }

    @Override // kotlin.reflect.KFunction
    public boolean isExternal() {
        return getOverriddenStorage().getForceIsExternal() || getDescriptor().isExternal();
    }

    @Override // kotlin.reflect.KFunction
    public boolean isOperator() {
        return getOverriddenStorage().getForceIsOperator() || getDescriptor().isOperator();
    }

    @Override // kotlin.reflect.KFunction
    public boolean isInfix() {
        return getOverriddenStorage().getForceIsInfix() || getDescriptor().isInfix();
    }

    @Override // kotlin.reflect.KCallable
    public boolean isSuspend() {
        return getDescriptor().isSuspend();
    }

    @Override // kotlin.reflect.jvm.internal.ReflectKFunction
    public boolean isPrimaryConstructor() {
        FunctionDescriptor descriptor = getDescriptor();
        ConstructorDescriptor constructorDescriptor = descriptor instanceof ConstructorDescriptor ? (ConstructorDescriptor) descriptor : null;
        return constructorDescriptor != null && constructorDescriptor.isPrimary();
    }

    public boolean equals(Object other) {
        ReflectKFunction reflectKFunctionAsReflectFunction = UtilKt.asReflectFunction(other);
        return reflectKFunctionAsReflectFunction != null && Intrinsics.areEqual(getContainer(), reflectKFunctionAsReflectFunction.getContainer()) && Intrinsics.areEqual(getName(), reflectKFunctionAsReflectFunction.getName()) && Intrinsics.areEqual(getSignature(), reflectKFunctionAsReflectFunction.getSignature()) && Intrinsics.areEqual(getRawBoundReceiver(), reflectKFunctionAsReflectFunction.getRawBoundReceiver());
    }

    public int hashCode() {
        return (((getContainer().hashCode() * 31) + getName().hashCode()) * 31) + getSignature().hashCode();
    }

    public String toString() {
        return ReflectionObjectRenderer.INSTANCE.renderFunction(this);
    }
}
