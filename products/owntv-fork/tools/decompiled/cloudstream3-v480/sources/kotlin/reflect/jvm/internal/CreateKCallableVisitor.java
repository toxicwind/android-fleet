package kotlin.reflect.jvm.internal;

import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;

/* JADX INFO: compiled from: util.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0010\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J!\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016¢\u0006\u0002\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lkotlin/reflect/jvm/internal/CreateKCallableVisitor;", "Lkotlin/reflect/jvm/internal/CreateKFunctionVisitor;", TtmlNode.RUBY_CONTAINER, "Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;", "<init>", "(Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;)V", "visitPropertyDescriptor", "Lkotlin/reflect/jvm/internal/DescriptorKCallable;", "descriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/PropertyDescriptor;", "data", "", "(Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;Lkotlin/Unit;)Lkotlin/reflect/jvm/internal/DescriptorKCallable;", "kotlin-reflection"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class CreateKCallableVisitor extends CreateKFunctionVisitor {
    private final KDeclarationContainerImpl container;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CreateKCallableVisitor(KDeclarationContainerImpl container) {
        super(container);
        Intrinsics.checkNotNullParameter(container, "container");
        this.container = container;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorVisitorEmptyBodies, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
    public DescriptorKCallable<?> visitPropertyDescriptor(PropertyDescriptor descriptor, Unit data) {
        int i;
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(data, "data");
        List<ReceiverParameterDescriptor> contextReceiverParameters = descriptor.getContextReceiverParameters();
        Intrinsics.checkNotNullExpressionValue(contextReceiverParameters, "getContextReceiverParameters(...)");
        if (contextReceiverParameters.isEmpty()) {
            i = (descriptor.getDispatchReceiverParameter() != null ? 1 : 0) + (descriptor.getExtensionReceiverParameter() != null ? 1 : 0);
        } else {
            i = -1;
        }
        if (descriptor.isVar()) {
            if (i == -1) {
                return new DescriptorKMutablePropertyN(this.container, descriptor, KCallableOverriddenStorage.INSTANCE.getEMPTY());
            }
            if (i == 0) {
                return new DescriptorKMutableProperty0(this.container, descriptor, KCallableOverriddenStorage.INSTANCE.getEMPTY());
            }
            if (i == 1) {
                return new DescriptorKMutableProperty1(this.container, descriptor, KCallableOverriddenStorage.INSTANCE.getEMPTY());
            }
            if (i == 2) {
                return new DescriptorKMutableProperty2(this.container, descriptor, KCallableOverriddenStorage.INSTANCE.getEMPTY());
            }
        } else {
            if (i == -1) {
                return new DescriptorKPropertyN(this.container, descriptor, KCallableOverriddenStorage.INSTANCE.getEMPTY());
            }
            if (i == 0) {
                return new DescriptorKProperty0(this.container, descriptor, KCallableOverriddenStorage.INSTANCE.getEMPTY());
            }
            if (i == 1) {
                return new DescriptorKProperty1(this.container, descriptor, KCallableOverriddenStorage.INSTANCE.getEMPTY());
            }
            if (i == 2) {
                return new DescriptorKProperty2(this.container, descriptor, KCallableOverriddenStorage.INSTANCE.getEMPTY());
            }
        }
        throw new KotlinReflectionInternalError("Unsupported property: " + descriptor);
    }
}
