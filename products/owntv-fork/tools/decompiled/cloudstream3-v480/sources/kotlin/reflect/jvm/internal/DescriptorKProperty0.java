package kotlin.reflect.jvm.internal;

import androidx.exifinterface.media.ExifInterface;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty0;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;

/* JADX INFO: compiled from: DescriptorKProperty0.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0010\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0001 B!\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bB+\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\u0004\b\n\u0010\u0011J\u000f\u0010\u0018\u001a\u00028\u0000H\u0096\u0080\u0004¢\u0006\u0002\u0010\u0019J\f\u0010\u001c\u001a\u0004\u0018\u00010\u0010H\u0096\u0080\u0004J\u000f\u0010\u001d\u001a\u00028\u0000H\u0096\u0082\u0004¢\u0006\u0002\u0010\u0019J\u001b\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\b\u001a\u00020\tH\u0010¢\u0006\u0002\b\u001fR!\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u00138VX\u0096\u0084\n¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0014\u0010\u0015R\u0016\u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u001bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lkotlin/reflect/jvm/internal/DescriptorKProperty0;", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "Lkotlin/reflect/KProperty0;", "Lkotlin/reflect/jvm/internal/DescriptorKProperty;", TtmlNode.RUBY_CONTAINER, "Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;", "descriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/PropertyDescriptor;", "overriddenStorage", "Lkotlin/reflect/jvm/internal/KCallableOverriddenStorage;", "<init>", "(Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;Lkotlin/reflect/jvm/internal/KCallableOverriddenStorage;)V", "name", "", "signature", "boundReceiver", "", "(Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V", "getter", "Lkotlin/reflect/jvm/internal/DescriptorKProperty0$Getter;", "getGetter", "()Lkotlin/reflect/jvm/internal/DescriptorKProperty0$Getter;", "getter$delegate", "Lkotlin/Lazy;", "get", "()Ljava/lang/Object;", "delegateValue", "Lkotlin/Lazy;", "getDelegate", "invoke", "shallowCopy", "shallowCopy$kotlin_reflection", "Getter", "kotlin-reflection"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class DescriptorKProperty0<V> extends DescriptorKProperty<V> implements KProperty0<V> {
    private final Lazy<Object> delegateValue;

    /* JADX INFO: renamed from: getter$delegate, reason: from kotlin metadata */
    private final Lazy getter;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DescriptorKProperty0(KDeclarationContainerImpl container, PropertyDescriptor descriptor, KCallableOverriddenStorage overriddenStorage) {
        super(container, descriptor, overriddenStorage);
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(overriddenStorage, "overriddenStorage");
        this.getter = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0(this) { // from class: kotlin.reflect.jvm.internal.DescriptorKProperty0$$Lambda$0
            private final DescriptorKProperty0 arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return DescriptorKProperty0.getter_delegate$lambda$0(this.arg$0);
            }
        });
        this.delegateValue = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0(this) { // from class: kotlin.reflect.jvm.internal.DescriptorKProperty0$$Lambda$1
            private final DescriptorKProperty0 arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return DescriptorKProperty0.delegateValue$lambda$0(this.arg$0);
            }
        });
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DescriptorKProperty0(KDeclarationContainerImpl container, String name, String signature, Object obj) {
        super(container, name, signature, obj);
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(signature, "signature");
        this.getter = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0(this) { // from class: kotlin.reflect.jvm.internal.DescriptorKProperty0$$Lambda$0
            private final DescriptorKProperty0 arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return DescriptorKProperty0.getter_delegate$lambda$0(this.arg$0);
            }
        });
        this.delegateValue = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0(this) { // from class: kotlin.reflect.jvm.internal.DescriptorKProperty0$$Lambda$1
            private final DescriptorKProperty0 arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return DescriptorKProperty0.delegateValue$lambda$0(this.arg$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Getter getter_delegate$lambda$0(DescriptorKProperty0 descriptorKProperty0) {
        return new Getter(descriptorKProperty0);
    }

    @Override // kotlin.reflect.jvm.internal.DescriptorKProperty, kotlin.reflect.KProperty
    public Getter<V> getGetter() {
        return (Getter) this.getter.getValue();
    }

    @Override // kotlin.reflect.KProperty0
    public V get() {
        return getGetter().call(new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object delegateValue$lambda$0(DescriptorKProperty0 descriptorKProperty0) {
        return ReflectKPropertyKt.getDelegateImpl(descriptorKProperty0, descriptorKProperty0.computeDelegateSource(), null, null);
    }

    @Override // kotlin.reflect.KProperty0
    public Object getDelegate() {
        return this.delegateValue.getValue();
    }

    @Override // kotlin.jvm.functions.Function0
    public V invoke() {
        return get();
    }

    @Override // kotlin.reflect.jvm.internal.DescriptorKCallable
    public DescriptorKProperty0<V> shallowCopy$kotlin_reflection(KCallableOverriddenStorage overriddenStorage) {
        Intrinsics.checkNotNullParameter(overriddenStorage, "overriddenStorage");
        return new DescriptorKProperty0<>(getContainer(), getDescriptor(), overriddenStorage);
    }

    /* JADX INFO: compiled from: DescriptorKProperty0.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\n\u001a\u00028\u0001H\u0096\u0082\u0004¢\u0006\u0002\u0010\u000bR\u001b\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\f"}, d2 = {"Lkotlin/reflect/jvm/internal/DescriptorKProperty0$Getter;", "R", "Lkotlin/reflect/jvm/internal/DescriptorKProperty$Getter;", "Lkotlin/reflect/KProperty0$Getter;", "property", "Lkotlin/reflect/jvm/internal/DescriptorKProperty0;", "<init>", "(Lkotlin/reflect/jvm/internal/DescriptorKProperty0;)V", "getProperty", "()Lkotlin/reflect/jvm/internal/DescriptorKProperty0;", "invoke", "()Ljava/lang/Object;", "kotlin-reflection"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Getter<R> extends DescriptorKProperty.Getter<R> implements KProperty0.Getter<R> {
        private final DescriptorKProperty0<R> property;

        /* JADX WARN: Multi-variable type inference failed */
        public Getter(DescriptorKProperty0<? extends R> property) {
            Intrinsics.checkNotNullParameter(property, "property");
            this.property = property;
        }

        @Override // kotlin.reflect.jvm.internal.DescriptorKProperty.Accessor, kotlin.reflect.KProperty.Accessor
        public DescriptorKProperty0<R> getProperty() {
            return this.property;
        }

        @Override // kotlin.jvm.functions.Function0
        public R invoke() {
            return getProperty().get();
        }
    }
}
