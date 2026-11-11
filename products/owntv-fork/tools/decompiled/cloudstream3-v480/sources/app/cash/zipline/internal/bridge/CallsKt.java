package app.cash.zipline.internal.bridge;

import app.cash.zipline.SerializersKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.IntCompanionObject;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.internal.IntSerializer;

/* JADX INFO: compiled from: calls.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\" \u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0007¨\u0006\u000b"}, d2 = {"argsListDescriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "failureSuspendCallbackSerializer", "Lkotlinx/serialization/KSerializer;", "Lapp/cash/zipline/internal/bridge/SuspendCallback;", "", "getFailureSuspendCallbackSerializer", "()Lkotlinx/serialization/KSerializer;", "cancelCallbackSerializer", "Lapp/cash/zipline/internal/bridge/CancelCallback;", "getCancelCallbackSerializer", "zipline_release"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class CallsKt {
    private static final SerialDescriptor argsListDescriptor = BuiltinSerializersKt.ListSerializer(BuiltinSerializersKt.serializer(IntCompanionObject.INSTANCE)).getDescriptor();
    private static final KSerializer<SuspendCallback<Integer>> failureSuspendCallbackSerializer = SerializersKt.ziplineServiceSerializer(new SuspendCallback.Companion.Adapter(CollectionsKt.listOf((Object[]) new KSerializer[]{IntSerializer.INSTANCE}), "app.cash.zipline.internal.bridge.SuspendCallback<kotlin.Int>"));
    private static final KSerializer<CancelCallback> cancelCallbackSerializer = SerializersKt.ziplineServiceSerializer(new CancelCallback.Companion.Adapter(CollectionsKt.listOf((Object[]) new KSerializer[0]), "app.cash.zipline.internal.bridge.CancelCallback"));

    public static final KSerializer<SuspendCallback<Integer>> getFailureSuspendCallbackSerializer() {
        return failureSuspendCallbackSerializer;
    }

    public static final KSerializer<CancelCallback> getCancelCallbackSerializer() {
        return cancelCallbackSerializer;
    }
}
