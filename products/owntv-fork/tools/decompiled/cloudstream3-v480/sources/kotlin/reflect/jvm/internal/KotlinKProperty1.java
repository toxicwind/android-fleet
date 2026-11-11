package kotlin.reflect.jvm.internal;

import androidx.exifinterface.media.ExifInterface;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.lang.reflect.Member;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty1;
import kotlin.reflect.jvm.internal.impl.km.KmProperty;

/* JADX INFO: compiled from: KotlinKProperty1.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0010\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0006\b\u0001\u0010\u0002 \u00012\b\u0012\u0004\u0012\u0002H\u00020\u00032\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0004:\u0001\u001dB)\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0015\u001a\u00028\u00012\u0006\u0010\u0016\u001a\u00028\u0000H\u0096\u0080\u0004¢\u0006\u0002\u0010\u0017J\u0019\u0010\u001b\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0016\u001a\u00028\u0000H\u0096\u0080\u0004¢\u0006\u0002\u0010\u0017J\u0017\u0010\u001c\u001a\u00028\u00012\u0006\u0010\u0016\u001a\u00028\u0000H\u0096\u0082\u0004¢\u0006\u0002\u0010\u0017R'\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00108VX\u0096\u0084\n¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0018\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001a0\u0019X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lkotlin/reflect/jvm/internal/KotlinKProperty1;", ExifInterface.GPS_DIRECTION_TRUE, ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "Lkotlin/reflect/jvm/internal/KotlinKProperty;", "Lkotlin/reflect/KProperty1;", TtmlNode.RUBY_CONTAINER, "Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;", "signature", "", "rawBoundReceiver", "", "kmProperty", "Lkotlin/reflect/jvm/internal/impl/km/KmProperty;", "<init>", "(Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;Ljava/lang/String;Ljava/lang/Object;Lkotlin/metadata/KmProperty;)V", "getter", "Lkotlin/reflect/jvm/internal/KotlinKProperty1$Getter;", "getGetter", "()Lkotlin/reflect/jvm/internal/KotlinKProperty1$Getter;", "getter$delegate", "Lkotlin/Lazy;", "get", "receiver", "(Ljava/lang/Object;)Ljava/lang/Object;", "delegateSource", "Lkotlin/Lazy;", "Ljava/lang/reflect/Member;", "getDelegate", "invoke", "Getter", "kotlin-reflection"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class KotlinKProperty1<T, V> extends KotlinKProperty<V> implements KProperty1<T, V> {
    private final Lazy<Member> delegateSource;

    /* JADX INFO: renamed from: getter$delegate, reason: from kotlin metadata */
    private final Lazy getter;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KotlinKProperty1(KDeclarationContainerImpl container, String signature, Object obj, KmProperty kmProperty) {
        super(container, signature, obj, kmProperty);
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(signature, "signature");
        Intrinsics.checkNotNullParameter(kmProperty, "kmProperty");
        this.getter = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0(this) { // from class: kotlin.reflect.jvm.internal.KotlinKProperty1$$Lambda$0
            private final KotlinKProperty1 arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return KotlinKProperty1.getter_delegate$lambda$0(this.arg$0);
            }
        });
        this.delegateSource = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0(this) { // from class: kotlin.reflect.jvm.internal.KotlinKProperty1$$Lambda$1
            private final KotlinKProperty1 arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.arg$0.computeDelegateSource();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Getter getter_delegate$lambda$0(KotlinKProperty1 kotlinKProperty1) {
        return new Getter(kotlinKProperty1);
    }

    @Override // kotlin.reflect.jvm.internal.KotlinKProperty, kotlin.reflect.KProperty
    public Getter<T, V> getGetter() {
        return (Getter) this.getter.getValue();
    }

    @Override // kotlin.reflect.KProperty1
    public V get(T receiver) {
        return getGetter().call(receiver);
    }

    @Override // kotlin.reflect.KProperty1
    public Object getDelegate(T receiver) {
        return ReflectKPropertyKt.getDelegateImpl(this, this.delegateSource.getValue(), receiver, null);
    }

    @Override // kotlin.jvm.functions.Function1
    public V invoke(T receiver) {
        return get(receiver);
    }

    /* JADX INFO: compiled from: KotlinKProperty1.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0006\b\u0003\u0010\u0002 \u00012\b\u0012\u0004\u0012\u0002H\u00020\u00032\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0004B\u001b\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u000b\u001a\u00028\u00032\u0006\u0010\f\u001a\u00028\u0002H\u0096\u0082\u0004¢\u0006\u0002\u0010\rR!\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0006X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000e"}, d2 = {"Lkotlin/reflect/jvm/internal/KotlinKProperty1$Getter;", ExifInterface.GPS_DIRECTION_TRUE, ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "Lkotlin/reflect/jvm/internal/KotlinKProperty$Getter;", "Lkotlin/reflect/KProperty1$Getter;", "property", "Lkotlin/reflect/jvm/internal/KotlinKProperty1;", "<init>", "(Lkotlin/reflect/jvm/internal/KotlinKProperty1;)V", "getProperty", "()Lkotlin/reflect/jvm/internal/KotlinKProperty1;", "invoke", "receiver", "(Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-reflection"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Getter<T, V> extends KotlinKProperty.Getter<V> implements KProperty1.Getter<T, V> {
        private final KotlinKProperty1<T, V> property;

        /* JADX WARN: Multi-variable type inference failed */
        public Getter(KotlinKProperty1<T, ? extends V> property) {
            Intrinsics.checkNotNullParameter(property, "property");
            this.property = property;
        }

        @Override // kotlin.reflect.jvm.internal.KotlinKProperty.Accessor, kotlin.reflect.KProperty.Accessor
        public KotlinKProperty1<T, V> getProperty() {
            return this.property;
        }

        @Override // kotlin.jvm.functions.Function1
        public V invoke(T receiver) {
            return getProperty().get(receiver);
        }
    }
}
