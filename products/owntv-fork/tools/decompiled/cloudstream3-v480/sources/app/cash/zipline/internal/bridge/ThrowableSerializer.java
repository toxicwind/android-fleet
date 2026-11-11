package app.cash.zipline.internal.bridge;

import app.cash.zipline.ZiplineApiMismatchException;
import app.cash.zipline.ZiplineException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: throwables.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u001a\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0002H\u0096\u0080\u0004J\u0012\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0012H\u0096\u0080\u0004J\u0016\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0016\u001a\u00020\u0002H\u0002J\u001e\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u0015H\u0002R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0015\u0010\u0007\u001a\u00020\bX\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u001a"}, d2 = {"Lapp/cash/zipline/internal/bridge/ThrowableSerializer;", "Lkotlinx/serialization/KSerializer;", "", "<init>", "()V", "surrogateSerializer", "Lapp/cash/zipline/internal/bridge/ThrowableSurrogate;", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", ES6Iterator.VALUE_PROPERTY, "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "knownTypeNames", "", "", "throwable", "knownTypeConstructor", "Lkotlin/Function1;", "typeName", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ThrowableSerializer implements KSerializer<Throwable> {
    public static final ThrowableSerializer INSTANCE = new ThrowableSerializer();
    private static final SerialDescriptor descriptor;
    private static final KSerializer<ThrowableSurrogate> surrogateSerializer;

    private ThrowableSerializer() {
    }

    static {
        KSerializer<ThrowableSurrogate> kSerializerSerializer = ThrowableSurrogate.INSTANCE.serializer();
        surrogateSerializer = kSerializerSerializer;
        descriptor = kSerializerSerializer.getDescriptor();
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, Throwable value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        if (value instanceof CancellationException) {
            encoder.encodeSerializableValue(surrogateSerializer, new ThrowableSurrogate(CollectionsKt.listOf("CancellationException"), ""));
            return;
        }
        String strStacktraceString = ThrowablesJniKt.stacktraceString(value);
        encoder.encodeSerializableValue(surrogateSerializer, new ThrowableSurrogate(knownTypeNames(value), strStacktraceString));
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public Throwable deserialize(Decoder decoder) {
        Pair pair;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        ThrowableSurrogate throwableSurrogate = (ThrowableSurrogate) decoder.decodeSerializableValue(surrogateSerializer);
        if (throwableSurrogate.getTypes().contains("CancellationException")) {
            return ThrowablesKt.getTheOnlyCancellationException();
        }
        Iterator<T> it = throwableSurrogate.getTypes().iterator();
        do {
            if (!it.hasNext()) {
                pair = null;
                break;
            }
            String str = (String) it.next();
            Function1<String, Throwable> function1KnownTypeConstructor = INSTANCE.knownTypeConstructor(str);
            pair = function1KnownTypeConstructor == null ? null : TuplesKt.to(str, function1KnownTypeConstructor);
        } while (pair == null);
        if (pair == null) {
            pair = TuplesKt.to("ZiplineException", new Function1() { // from class: app.cash.zipline.internal.bridge.ThrowableSerializer$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return ThrowableSerializer.deserialize$lambda$1((String) obj);
                }
            });
        }
        String str2 = (String) pair.component1();
        Function1 function1 = (Function1) pair.component2();
        String stacktraceString = throwableSurrogate.getStacktraceString();
        if (StringsKt.startsWith$default(stacktraceString, str2, false, 2, (Object) null) && StringsKt.regionMatches$default(stacktraceString, str2.length(), ": ", 0, 2, false, 16, (Object) null)) {
            stacktraceString = stacktraceString.substring(str2.length() + 2);
            Intrinsics.checkNotNullExpressionValue(stacktraceString, "substring(...)");
        }
        return ThrowablesJniKt.toInboundThrowable(stacktraceString, function1);
    }

    static final ZiplineException deserialize$lambda$1(String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        return new ZiplineException(message, null, 2, null);
    }

    private final List<String> knownTypeNames(Throwable throwable) {
        return throwable instanceof ZiplineApiMismatchException ? CollectionsKt.listOf("ZiplineApiMismatchException") : CollectionsKt.emptyList();
    }

    /* JADX INFO: renamed from: app.cash.zipline.internal.bridge.ThrowableSerializer$knownTypeConstructor$1, reason: invalid class name */
    /* JADX INFO: compiled from: throwables.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<String, ZiplineApiMismatchException> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
            super(1, ZiplineApiMismatchException.class, "<init>", "<init>(Ljava/lang/String;)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public final ZiplineApiMismatchException invoke(String p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return new ZiplineApiMismatchException(p0);
        }
    }

    private final Function1<String, Throwable> knownTypeConstructor(String typeName) {
        if (Intrinsics.areEqual(typeName, "ZiplineApiMismatchException")) {
            return AnonymousClass1.INSTANCE;
        }
        return null;
    }
}
