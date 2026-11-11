package kotlin.reflect.jvm.internal;

import androidx.exifinterface.media.ExifInterface;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KMutableProperty0;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: DescriptorKProperty0.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0001\u001eB!\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bB+\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\u0004\b\n\u0010\u0011J\u0017\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00028\u0000H\u0096\u0080\u0004¢\u0006\u0002\u0010\u001bJ\u001b\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\b\u001a\u00020\tH\u0010¢\u0006\u0002\b\u001dR!\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u00138VX\u0096\u0084\n¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u001f"}, d2 = {"Lkotlin/reflect/jvm/internal/DescriptorKMutableProperty0;", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "Lkotlin/reflect/jvm/internal/DescriptorKProperty0;", "Lkotlin/reflect/KMutableProperty0;", TtmlNode.RUBY_CONTAINER, "Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;", "descriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/PropertyDescriptor;", "overriddenStorage", "Lkotlin/reflect/jvm/internal/KCallableOverriddenStorage;", "<init>", "(Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;Lkotlin/reflect/jvm/internal/KCallableOverriddenStorage;)V", "name", "", "signature", "boundReceiver", "", "(Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V", "setter", "Lkotlin/reflect/jvm/internal/DescriptorKMutableProperty0$Setter;", "getSetter", "()Lkotlin/reflect/jvm/internal/DescriptorKMutableProperty0$Setter;", "setter$delegate", "Lkotlin/Lazy;", "set", "", ES6Iterator.VALUE_PROPERTY, "(Ljava/lang/Object;)V", "shallowCopy", "shallowCopy$kotlin_reflection", "Setter", "kotlin-reflection"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class DescriptorKMutableProperty0<V> extends DescriptorKProperty0<V> implements KMutableProperty0<V> {

    /* JADX INFO: renamed from: setter$delegate, reason: from kotlin metadata */
    private final Lazy setter;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DescriptorKMutableProperty0(KDeclarationContainerImpl container, PropertyDescriptor descriptor, KCallableOverriddenStorage overriddenStorage) {
        super(container, descriptor, overriddenStorage);
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(overriddenStorage, "overriddenStorage");
        this.setter = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0(this) { // from class: kotlin.reflect.jvm.internal.DescriptorKMutableProperty0$$Lambda$0
            private final DescriptorKMutableProperty0 arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return DescriptorKMutableProperty0.setter_delegate$lambda$0(this.arg$0);
            }
        });
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DescriptorKMutableProperty0(KDeclarationContainerImpl container, String name, String signature, Object obj) {
        super(container, name, signature, obj);
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(signature, "signature");
        this.setter = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0(this) { // from class: kotlin.reflect.jvm.internal.DescriptorKMutableProperty0$$Lambda$0
            private final DescriptorKMutableProperty0 arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return DescriptorKMutableProperty0.setter_delegate$lambda$0(this.arg$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Setter setter_delegate$lambda$0(DescriptorKMutableProperty0 descriptorKMutableProperty0) {
        return new Setter(descriptorKMutableProperty0);
    }

    @Override // kotlin.reflect.KMutableProperty0, kotlin.reflect.KMutableProperty
    public Setter<V> getSetter() {
        return (Setter) this.setter.getValue();
    }

    @Override // kotlin.reflect.KMutableProperty0
    public void set(V value) {
        getSetter().call(value);
    }

    @Override // kotlin.reflect.jvm.internal.DescriptorKProperty0, kotlin.reflect.jvm.internal.DescriptorKCallable
    public DescriptorKMutableProperty0<V> shallowCopy$kotlin_reflection(KCallableOverriddenStorage overriddenStorage) {
        Intrinsics.checkNotNullParameter(overriddenStorage, "overriddenStorage");
        return new DescriptorKMutableProperty0<>(getContainer(), getDescriptor(), overriddenStorage);
    }

    /* JADX INFO: compiled from: DescriptorKProperty0.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00028\u0001H\u0096\u0082\u0004¢\u0006\u0002\u0010\rR\u001b\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u000e"}, d2 = {"Lkotlin/reflect/jvm/internal/DescriptorKMutableProperty0$Setter;", "R", "Lkotlin/reflect/jvm/internal/DescriptorKProperty$Setter;", "Lkotlin/reflect/KMutableProperty0$Setter;", "property", "Lkotlin/reflect/jvm/internal/DescriptorKMutableProperty0;", "<init>", "(Lkotlin/reflect/jvm/internal/DescriptorKMutableProperty0;)V", "getProperty", "()Lkotlin/reflect/jvm/internal/DescriptorKMutableProperty0;", "invoke", "", ES6Iterator.VALUE_PROPERTY, "(Ljava/lang/Object;)V", "kotlin-reflection"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Setter<R> extends DescriptorKProperty.Setter<R> implements KMutableProperty0.Setter<R> {
        private final DescriptorKMutableProperty0<R> property;

        public Setter(DescriptorKMutableProperty0<R> property) {
            Intrinsics.checkNotNullParameter(property, "property");
            this.property = property;
        }

        @Override // kotlin.reflect.jvm.internal.DescriptorKProperty.Accessor, kotlin.reflect.KProperty.Accessor
        public DescriptorKMutableProperty0<R> getProperty() {
            return this.property;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
            invoke2(obj);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public void invoke2(R value) {
            getProperty().set(value);
        }
    }
}
