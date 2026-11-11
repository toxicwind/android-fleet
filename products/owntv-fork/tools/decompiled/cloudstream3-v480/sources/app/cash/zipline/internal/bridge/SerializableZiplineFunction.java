package app.cash.zipline.internal.bridge;

import app.cash.zipline.ZiplineFunction;
import app.cash.zipline.ZiplineService;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;

/* JADX INFO: compiled from: ziplineFunctions.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u0000 \u001f2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\u001e\u001fB\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tB\u0015\b\u0016\u0012\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u0001¢\u0006\u0004\b\b\u0010\u000bB7\b\u0010\u0012\u0006\u0010\f\u001a\u00020\r\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\b\b\u0010\u0010J%\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0001¢\u0006\u0002\b\u001dR\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0005\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0014¨\u0006 "}, d2 = {"Lapp/cash/zipline/internal/bridge/SerializableZiplineFunction;", "Lapp/cash/zipline/ZiplineFunction;", "Lapp/cash/zipline/ZiplineService;", "id", "", "signature", "isSuspending", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Z)V", "function", "(Lapp/cash/zipline/ZiplineFunction;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;ZLkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getId", "()Ljava/lang/String;", "getSignature", "()Z", "isClose", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$zipline_release", "$serializer", "Companion", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
@Serializable
public final class SerializableZiplineFunction implements ZiplineFunction<ZiplineService> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String id;
    private final boolean isSuspending;
    private final String signature;

    /* JADX INFO: compiled from: ziplineFunctions.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lapp/cash/zipline/internal/bridge/SerializableZiplineFunction$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lapp/cash/zipline/internal/bridge/SerializableZiplineFunction;", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<SerializableZiplineFunction> serializer() {
            return SerializableZiplineFunction$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ SerializableZiplineFunction(int i, String str, String str2, boolean z, SerializationConstructorMarker serializationConstructorMarker) {
        if (7 != (i & 7)) {
            PluginExceptionsKt.throwMissingFieldException(i, 7, SerializableZiplineFunction$$serializer.INSTANCE.getDescriptor());
        }
        this.id = str;
        this.signature = str2;
        this.isSuspending = z;
    }

    public SerializableZiplineFunction(String id, String signature, boolean z) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(signature, "signature");
        this.id = id;
        this.signature = signature;
        this.isSuspending = z;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$zipline_release(SerializableZiplineFunction self, CompositeEncoder output, SerialDescriptor serialDesc) {
        output.encodeStringElement(serialDesc, 0, self.getId());
        output.encodeStringElement(serialDesc, 1, self.getSignature());
        output.encodeBooleanElement(serialDesc, 2, self.getIsSuspending());
    }

    @Override // app.cash.zipline.ZiplineFunction
    public String getId() {
        return this.id;
    }

    @Override // app.cash.zipline.ZiplineFunction
    public String getSignature() {
        return this.signature;
    }

    @Override // app.cash.zipline.ZiplineFunction
    /* JADX INFO: renamed from: isSuspending, reason: from getter */
    public boolean getIsSuspending() {
        return this.isSuspending;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public SerializableZiplineFunction(ZiplineFunction<?> function) {
        this(function.getId(), function.getSignature(), function.getIsSuspending());
        Intrinsics.checkNotNullParameter(function, "function");
    }

    @Override // app.cash.zipline.ZiplineFunction
    public boolean isClose() {
        return Intrinsics.areEqual(getSignature(), "fun close(): kotlin.Unit");
    }
}
