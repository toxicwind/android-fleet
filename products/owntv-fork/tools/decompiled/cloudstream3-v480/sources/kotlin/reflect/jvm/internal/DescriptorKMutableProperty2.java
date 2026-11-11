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
import kotlin.reflect.KMutableProperty2;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: DescriptorKProperty2.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\b\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u0002*\u0004\b\u0002\u0010\u00032\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0005:\u0001 B!\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\fB!\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010¢\u0006\u0004\b\u000b\u0010\u0011J'\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00028\u00002\u0006\u0010\u001b\u001a\u00028\u00012\u0006\u0010\u001c\u001a\u00028\u0002H\u0096\u0080\u0004¢\u0006\u0002\u0010\u001dJ'\u0010\u001e\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u0010H\u0010¢\u0006\u0002\b\u001fR-\u0010\u0012\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u00138VX\u0096\u0084\n¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0014\u0010\u0015¨\u0006!"}, d2 = {"Lkotlin/reflect/jvm/internal/DescriptorKMutableProperty2;", "D", ExifInterface.LONGITUDE_EAST, ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "Lkotlin/reflect/jvm/internal/DescriptorKProperty2;", "Lkotlin/reflect/KMutableProperty2;", TtmlNode.RUBY_CONTAINER, "Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;", "name", "", "signature", "<init>", "(Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;Ljava/lang/String;Ljava/lang/String;)V", "descriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/PropertyDescriptor;", "overriddenStorage", "Lkotlin/reflect/jvm/internal/KCallableOverriddenStorage;", "(Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;Lkotlin/reflect/jvm/internal/KCallableOverriddenStorage;)V", "setter", "Lkotlin/reflect/jvm/internal/DescriptorKMutableProperty2$Setter;", "getSetter", "()Lkotlin/reflect/jvm/internal/DescriptorKMutableProperty2$Setter;", "setter$delegate", "Lkotlin/Lazy;", "set", "", "receiver1", "receiver2", ES6Iterator.VALUE_PROPERTY, "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V", "shallowCopy", "shallowCopy$kotlin_reflection", "Setter", "kotlin-reflection"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class DescriptorKMutableProperty2<D, E, V> extends DescriptorKProperty2<D, E, V> implements KMutableProperty2<D, E, V> {

    /* JADX INFO: renamed from: setter$delegate, reason: from kotlin metadata */
    private final Lazy setter;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DescriptorKMutableProperty2(KDeclarationContainerImpl container, String name, String signature) {
        super(container, name, signature);
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(signature, "signature");
        this.setter = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0(this) { // from class: kotlin.reflect.jvm.internal.DescriptorKMutableProperty2$$Lambda$0
            private final DescriptorKMutableProperty2 arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return DescriptorKMutableProperty2.setter_delegate$lambda$0(this.arg$0);
            }
        });
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DescriptorKMutableProperty2(KDeclarationContainerImpl container, PropertyDescriptor descriptor, KCallableOverriddenStorage overriddenStorage) {
        super(container, descriptor, overriddenStorage);
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(overriddenStorage, "overriddenStorage");
        this.setter = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0(this) { // from class: kotlin.reflect.jvm.internal.DescriptorKMutableProperty2$$Lambda$0
            private final DescriptorKMutableProperty2 arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return DescriptorKMutableProperty2.setter_delegate$lambda$0(this.arg$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Setter setter_delegate$lambda$0(DescriptorKMutableProperty2 descriptorKMutableProperty2) {
        return new Setter(descriptorKMutableProperty2);
    }

    @Override // kotlin.reflect.KMutableProperty2, kotlin.reflect.KMutableProperty
    public Setter<D, E, V> getSetter() {
        return (Setter) this.setter.getValue();
    }

    @Override // kotlin.reflect.KMutableProperty2
    public void set(D receiver1, E receiver2, V value) {
        getSetter().call(receiver1, receiver2, value);
    }

    @Override // kotlin.reflect.jvm.internal.DescriptorKProperty2, kotlin.reflect.jvm.internal.DescriptorKCallable
    public DescriptorKMutableProperty2<D, E, V> shallowCopy$kotlin_reflection(KCallableOverriddenStorage overriddenStorage) {
        Intrinsics.checkNotNullParameter(overriddenStorage, "overriddenStorage");
        return new DescriptorKMutableProperty2<>(getContainer(), getDescriptor(), overriddenStorage);
    }

    /* JADX INFO: compiled from: DescriptorKProperty2.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000*\u0004\b\u0003\u0010\u0001*\u0004\b\u0004\u0010\u0002*\u0004\b\u0005\u0010\u00032\b\u0012\u0004\u0012\u0002H\u00030\u00042\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0005B!\u0012\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u0004\u0012\u0004\u0012\u00028\u00050\u0007¢\u0006\u0004\b\b\u0010\tJ'\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00028\u00032\u0006\u0010\u000f\u001a\u00028\u00042\u0006\u0010\u0010\u001a\u00028\u0005H\u0096\u0082\u0004¢\u0006\u0002\u0010\u0011R'\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u0004\u0012\u0004\u0012\u00028\u00050\u0007X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0012"}, d2 = {"Lkotlin/reflect/jvm/internal/DescriptorKMutableProperty2$Setter;", "D", ExifInterface.LONGITUDE_EAST, ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "Lkotlin/reflect/jvm/internal/DescriptorKProperty$Setter;", "Lkotlin/reflect/KMutableProperty2$Setter;", "property", "Lkotlin/reflect/jvm/internal/DescriptorKMutableProperty2;", "<init>", "(Lkotlin/reflect/jvm/internal/DescriptorKMutableProperty2;)V", "getProperty", "()Lkotlin/reflect/jvm/internal/DescriptorKMutableProperty2;", "invoke", "", "receiver1", "receiver2", ES6Iterator.VALUE_PROPERTY, "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V", "kotlin-reflection"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Setter<D, E, V> extends DescriptorKProperty.Setter<V> implements KMutableProperty2.Setter<D, E, V> {
        private final DescriptorKMutableProperty2<D, E, V> property;

        public Setter(DescriptorKMutableProperty2<D, E, V> property) {
            Intrinsics.checkNotNullParameter(property, "property");
            this.property = property;
        }

        @Override // kotlin.reflect.jvm.internal.DescriptorKProperty.Accessor, kotlin.reflect.KProperty.Accessor
        public DescriptorKMutableProperty2<D, E, V> getProperty() {
            return this.property;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(Object obj, Object obj2, Object obj3) {
            invoke2(obj, obj2, obj3);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public void invoke2(D receiver1, E receiver2, V value) {
            getProperty().set(receiver1, receiver2, value);
        }
    }
}
