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
import kotlin.reflect.jvm.internal.impl.km.KmProperty;

/* JADX INFO: compiled from: KotlinKProperty0.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0010\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0001\u001aB)\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u0014\u001a\u00028\u0000H\u0096\u0080\u0004¢\u0006\u0002\u0010\u0015J\f\u0010\u0018\u001a\u0004\u0018\u00010\tH\u0096\u0080\u0004J\u000f\u0010\u0019\u001a\u00028\u0000H\u0096\u0082\u0004¢\u0006\u0002\u0010\u0015R!\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u000f8VX\u0096\u0084\n¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0017X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lkotlin/reflect/jvm/internal/KotlinKProperty0;", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "Lkotlin/reflect/jvm/internal/KotlinKProperty;", "Lkotlin/reflect/KProperty0;", TtmlNode.RUBY_CONTAINER, "Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;", "signature", "", "rawBoundReceiver", "", "kmProperty", "Lkotlin/reflect/jvm/internal/impl/km/KmProperty;", "<init>", "(Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;Ljava/lang/String;Ljava/lang/Object;Lkotlin/metadata/KmProperty;)V", "getter", "Lkotlin/reflect/jvm/internal/KotlinKProperty0$Getter;", "getGetter", "()Lkotlin/reflect/jvm/internal/KotlinKProperty0$Getter;", "getter$delegate", "Lkotlin/Lazy;", "get", "()Ljava/lang/Object;", "delegateValue", "Lkotlin/Lazy;", "getDelegate", "invoke", "Getter", "kotlin-reflection"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class KotlinKProperty0<V> extends KotlinKProperty<V> implements KProperty0<V> {
    private final Lazy<Object> delegateValue;

    /* JADX INFO: renamed from: getter$delegate, reason: from kotlin metadata */
    private final Lazy getter;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KotlinKProperty0(KDeclarationContainerImpl container, String signature, Object obj, KmProperty kmProperty) {
        super(container, signature, obj, kmProperty);
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(signature, "signature");
        Intrinsics.checkNotNullParameter(kmProperty, "kmProperty");
        this.getter = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0(this) { // from class: kotlin.reflect.jvm.internal.KotlinKProperty0$$Lambda$0
            private final KotlinKProperty0 arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return KotlinKProperty0.getter_delegate$lambda$0(this.arg$0);
            }
        });
        this.delegateValue = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0(this) { // from class: kotlin.reflect.jvm.internal.KotlinKProperty0$$Lambda$1
            private final KotlinKProperty0 arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return KotlinKProperty0.delegateValue$lambda$0(this.arg$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Getter getter_delegate$lambda$0(KotlinKProperty0 kotlinKProperty0) {
        return new Getter(kotlinKProperty0);
    }

    @Override // kotlin.reflect.jvm.internal.KotlinKProperty, kotlin.reflect.KProperty
    public Getter<V> getGetter() {
        return (Getter) this.getter.getValue();
    }

    @Override // kotlin.reflect.KProperty0
    public V get() {
        return getGetter().call(new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object delegateValue$lambda$0(KotlinKProperty0 kotlinKProperty0) {
        return ReflectKPropertyKt.getDelegateImpl(kotlinKProperty0, kotlinKProperty0.computeDelegateSource(), null, null);
    }

    @Override // kotlin.reflect.KProperty0
    public Object getDelegate() {
        return this.delegateValue.getValue();
    }

    @Override // kotlin.jvm.functions.Function0
    public V invoke() {
        return get();
    }

    /* JADX INFO: compiled from: KotlinKProperty0.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\n\u001a\u00028\u0001H\u0096\u0082\u0004¢\u0006\u0002\u0010\u000bR\u001b\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\f"}, d2 = {"Lkotlin/reflect/jvm/internal/KotlinKProperty0$Getter;", "R", "Lkotlin/reflect/jvm/internal/KotlinKProperty$Getter;", "Lkotlin/reflect/KProperty0$Getter;", "property", "Lkotlin/reflect/jvm/internal/KotlinKProperty0;", "<init>", "(Lkotlin/reflect/jvm/internal/KotlinKProperty0;)V", "getProperty", "()Lkotlin/reflect/jvm/internal/KotlinKProperty0;", "invoke", "()Ljava/lang/Object;", "kotlin-reflection"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Getter<R> extends KotlinKProperty.Getter<R> implements KProperty0.Getter<R> {
        private final KotlinKProperty0<R> property;

        /* JADX WARN: Multi-variable type inference failed */
        public Getter(KotlinKProperty0<? extends R> property) {
            Intrinsics.checkNotNullParameter(property, "property");
            this.property = property;
        }

        @Override // kotlin.reflect.jvm.internal.KotlinKProperty.Accessor, kotlin.reflect.KProperty.Accessor
        public KotlinKProperty0<R> getProperty() {
            return this.property;
        }

        @Override // kotlin.jvm.functions.Function0
        public R invoke() {
            return getProperty().get();
        }
    }
}
