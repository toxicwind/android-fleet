package kotlin.reflect.jvm.internal;

import androidx.media3.extractor.text.ttml.TtmlNode;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionBase;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.KotlinGenericDeclarationKt;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KParameter;
import kotlin.reflect.KTypeParameter;
import kotlin.reflect.KVisibility;
import kotlin.reflect.jvm.KTypesJvm;
import kotlin.reflect.jvm.internal.calls.AnnotationConstructorCaller;
import kotlin.reflect.jvm.internal.calls.Caller;
import kotlin.reflect.jvm.internal.calls.CallerImpl;
import kotlin.reflect.jvm.internal.calls.CallerKt;
import kotlin.reflect.jvm.internal.calls.ValueClassAwareCallerKt;
import kotlin.reflect.jvm.internal.impl.km.KmType;
import kotlin.reflect.jvm.internal.impl.km.KmValueParameter;
import kotlin.reflect.jvm.internal.impl.km.jvm.JvmMethodSignature;

/* JADX INFO: compiled from: KotlinKFunction.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000¦\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b \u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\u00020\u00032\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00042\u00020\u0005B!\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u000b\u0010\fJ\f\u0010D\u001a\u00020E*\u00020\u0007H\u0002J\u0010\u0010F\u001a\u00020E2\u0006\u0010G\u001a\u00020HH\u0002J\u001c\u0010I\u001a\u0006\u0012\u0002\b\u00030=2\u0006\u0010G\u001a\u00020H2\u0006\u0010J\u001a\u00020EH\u0002J&\u0010K\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030M0L2\n\u0010G\u001a\u0006\u0012\u0002\b\u00030M2\u0006\u0010N\u001a\u00020EH\u0002J\u0010\u0010O\u001a\u00020E2\u0006\u0010P\u001a\u00020QH\u0002J\u0010\u0010R\u001a\u00020E*\u0006\u0012\u0002\b\u00030SH\u0002J\u0014\u0010T\u001a\u00020E2\b\u0010U\u001a\u0004\u0018\u00010\u0002H\u0096\u0082\u0004J\n\u0010V\u001a\u000205H\u0096\u0080\u0004J\n\u0010W\u001a\u00020\tH\u0096\u0080\u0004R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0016\u0010\n\u001a\u0004\u0018\u00010\u0002X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0018\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014X¤\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u0004\u0018\u00010\u0019X¤\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0018\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014X¤\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0017R\u0012\u0010\u001e\u001a\u00020\u001fX¤\u0004¢\u0006\u0006\u001a\u0004\b \u0010!R\u0012\u0010\"\u001a\u00020#X¤\u0004¢\u0006\u0006\u001a\u0004\b$\u0010%R!\u0010&\u001a\b\u0012\u0004\u0012\u00020'0\u00148VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b)\u0010*\u001a\u0004\b(\u0010\u0017R!\u0010+\u001a\b\u0012\u0004\u0012\u00020'0\u00148VX\u0096\u0084\n¢\u0006\f\n\u0004\b-\u0010*\u001a\u0004\b,\u0010\u0017R\u001b\u0010.\u001a\b\u0012\u0004\u0012\u00020/0\u00148VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b0\u0010\u0017R\u001b\u00101\u001a\b\u0012\u0004\u0012\u0002020\u00148VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b3\u0010\u0017R\u0015\u00104\u001a\u0002058VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b6\u00107R\u001a\u00108\u001a\b\u0012\u0004\u0012\u00020\u0003098VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b:\u0010;R\u001f\u0010<\u001a\u0006\u0012\u0002\b\u00030=8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b@\u0010*\u001a\u0004\b>\u0010?R!\u0010A\u001a\b\u0012\u0002\b\u0003\u0018\u00010=8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\bC\u0010*\u001a\u0004\bB\u0010?¨\u0006X"}, d2 = {"Lkotlin/reflect/jvm/internal/KotlinKFunction;", "Lkotlin/reflect/jvm/internal/KotlinKCallable;", "", "Lkotlin/reflect/jvm/internal/ReflectKFunction;", "Lkotlin/jvm/internal/FunctionBase;", "Lkotlin/reflect/jvm/internal/FunctionWithAllInvokes;", TtmlNode.RUBY_CONTAINER, "Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;", "signature", "", "rawBoundReceiver", "<init>", "(Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;Ljava/lang/String;Ljava/lang/Object;)V", "getContainer", "()Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;", "getSignature", "()Ljava/lang/String;", "getRawBoundReceiver", "()Ljava/lang/Object;", "contextParameters", "", "Lkotlin/reflect/jvm/internal/impl/km/KmValueParameter;", "getContextParameters", "()Ljava/util/List;", "extensionReceiverType", "Lkotlin/reflect/jvm/internal/impl/km/KmType;", "getExtensionReceiverType", "()Lkotlin/metadata/KmType;", "valueParameters", "getValueParameters", "typeParameterTable", "Lkotlin/reflect/jvm/internal/TypeParameterTable;", "getTypeParameterTable", "()Lkotlin/reflect/jvm/internal/TypeParameterTable;", "jvmSignature", "Lkotlin/reflect/jvm/internal/impl/km/jvm/JvmMethodSignature;", "getJvmSignature", "()Lkotlin/metadata/jvm/JvmMethodSignature;", "allParameters", "Lkotlin/reflect/KParameter;", "getAllParameters", "allParameters$delegate", "Lkotlin/Lazy;", "parameters", "getParameters", "parameters$delegate", "typeParameters", "Lkotlin/reflect/KTypeParameter;", "getTypeParameters", "annotations", "", "getAnnotations", "arity", "", "getArity", "()I", "overridden", "", "getOverridden", "()Ljava/util/Collection;", "caller", "Lkotlin/reflect/jvm/internal/calls/Caller;", "getCaller", "()Lkotlin/reflect/jvm/internal/calls/Caller;", "caller$delegate", "defaultCaller", "getDefaultCaller", "defaultCaller$delegate", "isInlineClass", "", "useBoxedBoundReceiver", "member", "Ljava/lang/reflect/Method;", "createStaticMethodCaller", "isCallByToValueClassMangledMethod", "createConstructorCaller", "Lkotlin/reflect/jvm/internal/calls/CallerImpl;", "Ljava/lang/reflect/Constructor;", "isDefault", "shouldHideConstructorDueToValueClassTypeValueParameters", "constructor", "Lkotlin/reflect/jvm/internal/KotlinKConstructor;", "isValueClassThatRequiresMangling", "Lkotlin/reflect/KClass;", "equals", "other", "hashCode", "toString", "kotlin-reflection"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class KotlinKFunction extends KotlinKCallable<Object> implements FunctionBase<Object>, FunctionWithAllInvokes, ReflectKFunction {

    /* JADX INFO: renamed from: allParameters$delegate, reason: from kotlin metadata */
    private final Lazy allParameters;

    /* JADX INFO: renamed from: caller$delegate, reason: from kotlin metadata */
    private final Lazy caller;
    private final KDeclarationContainerImpl container;

    /* JADX INFO: renamed from: defaultCaller$delegate, reason: from kotlin metadata */
    private final Lazy defaultCaller;

    /* JADX INFO: renamed from: parameters$delegate, reason: from kotlin metadata */
    private final Lazy parameters;
    private final Object rawBoundReceiver;
    private final String signature;

    protected abstract List<KmValueParameter> getContextParameters();

    protected abstract KmType getExtensionReceiverType();

    protected abstract JvmMethodSignature getJvmSignature();

    protected abstract TypeParameterTable getTypeParameterTable();

    protected abstract List<KmValueParameter> getValueParameters();

    public KotlinKFunction(KDeclarationContainerImpl container, String signature, Object obj) {
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(signature, "signature");
        this.container = container;
        this.signature = signature;
        this.rawBoundReceiver = obj;
        this.allParameters = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0(this) { // from class: kotlin.reflect.jvm.internal.KotlinKFunction$$Lambda$0
            private final KotlinKFunction arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return KotlinKFunction.allParameters_delegate$lambda$0(this.arg$0);
            }
        });
        this.parameters = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0(this) { // from class: kotlin.reflect.jvm.internal.KotlinKFunction$$Lambda$1
            private final KotlinKFunction arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return KotlinKFunction.parameters_delegate$lambda$0(this.arg$0);
            }
        });
        this.caller = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0(this) { // from class: kotlin.reflect.jvm.internal.KotlinKFunction$$Lambda$2
            private final KotlinKFunction arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return KotlinKFunction.caller_delegate$lambda$0(this.arg$0);
            }
        });
        this.defaultCaller = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0(this) { // from class: kotlin.reflect.jvm.internal.KotlinKFunction$$Lambda$3
            private final KotlinKFunction arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return KotlinKFunction.defaultCaller_delegate$lambda$0(this.arg$0);
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

    public GenericDeclaration default$findJavaDeclaration() {
        return KotlinGenericDeclarationKt.findMethodBySignature(getContainer(), getSignature());
    }

    @Override // kotlin.reflect.jvm.internal.ReflectKFunction
    public String getSignature() {
        return this.signature;
    }

    @Override // kotlin.reflect.jvm.internal.ReflectKCallable
    public Object getRawBoundReceiver() {
        return this.rawBoundReceiver;
    }

    @Override // kotlin.reflect.jvm.internal.ReflectKCallable
    public List<KParameter> getAllParameters() {
        return (List) this.allParameters.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List allParameters_delegate$lambda$0(KotlinKFunction kotlinKFunction) {
        return KotlinKCallableKt.computeParameters(kotlinKFunction, kotlinKFunction.getContextParameters(), kotlinKFunction.getExtensionReceiverType(), kotlinKFunction.getValueParameters(), kotlinKFunction.getTypeParameterTable(), true);
    }

    @Override // kotlin.reflect.KCallable
    public List<KParameter> getParameters() {
        return (List) this.parameters.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List parameters_delegate$lambda$0(KotlinKFunction kotlinKFunction) {
        if (ReflectKCallableKt.isBound(kotlinKFunction)) {
            return KotlinKCallableKt.computeParameters(kotlinKFunction, kotlinKFunction.getContextParameters(), kotlinKFunction.getExtensionReceiverType(), kotlinKFunction.getValueParameters(), kotlinKFunction.getTypeParameterTable(), false);
        }
        return kotlinKFunction.getAllParameters();
    }

    @Override // kotlin.reflect.KCallable
    public List<KTypeParameter> getTypeParameters() {
        return getTypeParameterTable().getOwnTypeParameters();
    }

    public Object default$invoke() {
        return call(new Object[0]);
    }

    @Override // kotlin.reflect.KAnnotatedElement
    public List<Annotation> getAnnotations() {
        Member memberMo10234getMember = getCaller().mo10234getMember();
        AnnotatedElement annotatedElement = memberMo10234getMember instanceof AnnotatedElement ? (AnnotatedElement) memberMo10234getMember : null;
        if (annotatedElement == null) {
            return CollectionsKt.emptyList();
        }
        Annotation[] annotations = annotatedElement.getAnnotations();
        Intrinsics.checkNotNullExpressionValue(annotations, "getAnnotations(...)");
        return UtilKt.unwrapKotlinRepeatableAnnotations(ArraysKt.toList(annotations));
    }

    public Object default$invoke(Object obj) {
        return call(obj);
    }

    public Object default$invoke(Object obj, Object obj2) {
        return call(obj, obj2);
    }

    public Object default$invoke(Object obj, Object obj2, Object obj3) {
        return call(obj, obj2, obj3);
    }

    public Object default$invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        return call(obj, obj2, obj3, obj4);
    }

    @Override // kotlin.jvm.internal.FunctionBase
    public int getArity() {
        return CallerKt.getArity(getCaller());
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

    public Object default$invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        return call(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8);
    }

    @Override // kotlin.reflect.jvm.internal.ReflectKFunction
    public Collection<ReflectKFunction> getOverridden() {
        if (!(getContainer() instanceof KPackageImpl)) {
            throw new IllegalArgumentException(("Only top-level functions are supported for now: " + this).toString());
        }
        return CollectionsKt.emptyList();
    }

    public Object default$invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        return call(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9);
    }

    public Object default$invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        return call(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10);
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

    @Override // kotlin.reflect.jvm.internal.ReflectKCallable
    public Caller<?> getCaller() {
        return (Caller) this.caller.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Caller caller_delegate$lambda$0(KotlinKFunction kotlinKFunction) {
        Method methodFindMethodBySignature;
        CallerImpl<Constructor<?>> callerImplCreateStaticMethodCaller;
        KotlinKFunction kotlinKFunction2 = kotlinKFunction;
        if (!ReflectKCallableKt.isConstructor(kotlinKFunction2) && !(kotlinKFunction.getContainer() instanceof KPackageImpl)) {
            throw new IllegalArgumentException(("Only constructors and top-level functions are supported for now: " + kotlinKFunction).toString());
        }
        JvmMethodSignature jvmSignature = kotlinKFunction.getJvmSignature();
        if (ReflectKCallableKt.isConstructor(kotlinKFunction2) && !kotlinKFunction.isInlineClass(kotlinKFunction.getContainer())) {
            if (ReflectKCallableKt.isAnnotationConstructor(kotlinKFunction2)) {
                Class<?> jClass = kotlinKFunction.getContainer().getJClass();
                List<KParameter> parameters = kotlinKFunction.getParameters();
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(parameters, 10));
                Iterator<T> it = parameters.iterator();
                while (it.hasNext()) {
                    String name = ((KParameter) it.next()).getName();
                    Intrinsics.checkNotNull(name);
                    arrayList.add(name);
                }
                return new AnnotationConstructorCaller(jClass, arrayList, AnnotationConstructorCaller.CallMode.POSITIONAL_CALL, AnnotationConstructorCaller.Origin.KOTLIN, null, 16, null);
            }
            methodFindMethodBySignature = kotlinKFunction.getContainer().findConstructorBySignature(jvmSignature.getDescriptor());
        } else {
            methodFindMethodBySignature = kotlinKFunction.getContainer().findMethodBySignature(jvmSignature.getName(), jvmSignature.getDescriptor());
        }
        if (methodFindMethodBySignature instanceof Constructor) {
            callerImplCreateStaticMethodCaller = kotlinKFunction.createConstructorCaller((Constructor) methodFindMethodBySignature, false);
        } else {
            if (!(methodFindMethodBySignature instanceof Method)) {
                throw new KotlinReflectionInternalError("Could not compute caller for function: " + kotlinKFunction);
            }
            callerImplCreateStaticMethodCaller = kotlinKFunction.createStaticMethodCaller((Method) methodFindMethodBySignature, false);
        }
        return ValueClassAwareCallerKt.createValueClassAwareCallerIfNeeded(callerImplCreateStaticMethodCaller, kotlinKFunction2, false, CollectionsKt.emptyList());
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

    @Override // kotlin.reflect.jvm.internal.ReflectKCallable
    public Caller<?> getDefaultCaller() {
        return (Caller) this.defaultCaller.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Caller defaultCaller_delegate$lambda$0(KotlinKFunction kotlinKFunction) {
        Method methodFindDefaultMethod;
        CallerImpl<Constructor<?>> callerImplCreateStaticMethodCaller;
        KotlinKFunction kotlinKFunction2 = kotlinKFunction;
        if (!ReflectKCallableKt.isConstructor(kotlinKFunction2) && !(kotlinKFunction.getContainer() instanceof KPackageImpl)) {
            throw new IllegalArgumentException(("Only constructors and top-level functions are supported for now: " + kotlinKFunction).toString());
        }
        JvmMethodSignature jvmSignature = kotlinKFunction.getJvmSignature();
        ArrayList arrayList = new ArrayList();
        if (ReflectKCallableKt.isConstructor(kotlinKFunction2) && !kotlinKFunction.isInlineClass(kotlinKFunction.getContainer())) {
            if (ReflectKCallableKt.isAnnotationConstructor(kotlinKFunction2)) {
                Class<?> jClass = kotlinKFunction.getContainer().getJClass();
                List<KParameter> parameters = kotlinKFunction.getParameters();
                ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(parameters, 10));
                Iterator<T> it = parameters.iterator();
                while (it.hasNext()) {
                    String name = ((KParameter) it.next()).getName();
                    Intrinsics.checkNotNull(name);
                    arrayList2.add(name);
                }
                return new AnnotationConstructorCaller(jClass, arrayList2, AnnotationConstructorCaller.CallMode.CALL_BY_NAME, AnnotationConstructorCaller.Origin.KOTLIN, null, 16, null);
            }
            DescriptorPatchingResult descriptorPatchingResultPatchJvmDescriptorByExtraBoxing = ReflectKFunctionKt.patchJvmDescriptorByExtraBoxing(kotlinKFunction, kotlinKFunction.getJvmSignature().getDescriptor());
            arrayList.addAll(descriptorPatchingResultPatchJvmDescriptorByExtraBoxing.getBoxedIndices());
            methodFindDefaultMethod = kotlinKFunction.getContainer().findDefaultConstructor(descriptorPatchingResultPatchJvmDescriptorByExtraBoxing.getNewDescriptor());
        } else {
            DescriptorPatchingResult descriptorPatchingResultPatchJvmDescriptorByExtraBoxing2 = ReflectKFunctionKt.patchJvmDescriptorByExtraBoxing(kotlinKFunction, jvmSignature.getDescriptor());
            arrayList.addAll(descriptorPatchingResultPatchJvmDescriptorByExtraBoxing2.getBoxedIndices());
            KDeclarationContainerImpl container = kotlinKFunction.getContainer();
            String name2 = jvmSignature.getName();
            String newDescriptor = descriptorPatchingResultPatchJvmDescriptorByExtraBoxing2.getNewDescriptor();
            Member memberMo10234getMember = kotlinKFunction.getCaller().mo10234getMember();
            Intrinsics.checkNotNull(memberMo10234getMember);
            boolean z = !Modifier.isStatic(memberMo10234getMember.getModifiers());
            List<KParameter> allParameters = kotlinKFunction.getAllParameters();
            boolean z2 = false;
            if (!(allParameters instanceof Collection) || !allParameters.isEmpty()) {
                Iterator<T> it2 = allParameters.iterator();
                while (it2.hasNext()) {
                    if (((KParameter) it2.next()).getKind() == KParameter.Kind.EXTENSION_RECEIVER) {
                        z2 = true;
                        break;
                    }
                }
            }
            methodFindDefaultMethod = container.findDefaultMethod(name2, newDescriptor, z, z2);
        }
        if (methodFindDefaultMethod instanceof Constructor) {
            callerImplCreateStaticMethodCaller = kotlinKFunction.createConstructorCaller((Constructor) methodFindDefaultMethod, true);
        } else {
            callerImplCreateStaticMethodCaller = methodFindDefaultMethod instanceof Method ? kotlinKFunction.createStaticMethodCaller((Method) methodFindDefaultMethod, kotlinKFunction.getCaller().isBoundInstanceCallWithValueClasses()) : null;
        }
        if (callerImplCreateStaticMethodCaller != null) {
            return ValueClassAwareCallerKt.createValueClassAwareCallerIfNeeded(callerImplCreateStaticMethodCaller, kotlinKFunction2, true, arrayList);
        }
        return null;
    }

    private final boolean isInlineClass(KDeclarationContainerImpl kDeclarationContainerImpl) {
        return (kDeclarationContainerImpl instanceof KClassImpl) && ((KClassImpl) kDeclarationContainerImpl).isValue();
    }

    private final boolean useBoxedBoundReceiver(Method member) {
        if (getContainer() instanceof KPackageImpl) {
            return false;
        }
        throw new IllegalArgumentException(("Only top-level functions are supported for now: " + this).toString());
    }

    private final Caller<?> createStaticMethodCaller(Method member, boolean isCallByToValueClassMangledMethod) {
        KotlinKFunction kotlinKFunction = this;
        if (ReflectKCallableKt.isBound(kotlinKFunction)) {
            return new CallerImpl.Method.BoundStatic(member, isCallByToValueClassMangledMethod, useBoxedBoundReceiver(member) ? getRawBoundReceiver() : ReflectKCallableKt.getBoundReceiver(kotlinKFunction));
        }
        return new CallerImpl.Method.Static(member);
    }

    private final CallerImpl<Constructor<?>> createConstructorCaller(Constructor<?> member, boolean isDefault) {
        if (!isDefault && (this instanceof KotlinKConstructor) && shouldHideConstructorDueToValueClassTypeValueParameters((KotlinKConstructor) this)) {
            KotlinKFunction kotlinKFunction = this;
            if (ReflectKCallableKt.isBound(kotlinKFunction)) {
                return new CallerImpl.AccessorForHiddenBoundConstructor(member, ReflectKCallableKt.getBoundReceiver(kotlinKFunction));
            }
            return new CallerImpl.AccessorForHiddenConstructor(member);
        }
        KotlinKFunction kotlinKFunction2 = this;
        if (ReflectKCallableKt.isBound(kotlinKFunction2)) {
            return new CallerImpl.BoundConstructor(member, ReflectKCallableKt.getBoundReceiver(kotlinKFunction2));
        }
        return new CallerImpl.Constructor(member);
    }

    private final boolean shouldHideConstructorDueToValueClassTypeValueParameters(KotlinKConstructor constructor) {
        if (constructor.getVisibility() == KVisibility.PRIVATE) {
            return false;
        }
        List<KParameter> parameters = constructor.getParameters();
        if ((parameters instanceof Collection) && parameters.isEmpty()) {
            return false;
        }
        Iterator<T> it = parameters.iterator();
        while (it.hasNext()) {
            if (isValueClassThatRequiresMangling(KTypesJvm.getJvmErasure(((KParameter) it.next()).getType()))) {
                return true;
            }
        }
        return false;
    }

    private final boolean isValueClassThatRequiresMangling(KClass<?> kClass) {
        return kClass.isValue() && !Intrinsics.areEqual(kClass, Reflection.getOrCreateKotlinClass(Result.class));
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
