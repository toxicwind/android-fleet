package app.cash.zipline.internal.bridge;

import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import app.cash.zipline.ZiplineFunction;
import app.cash.zipline.ZiplineService;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;

/* JADX INFO: compiled from: ziplineFunctions.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\b!\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B5\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0010\u0010\u0007\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t0\b\u0012\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\t¢\u0006\u0004\b\u000b\u0010\fJ#\u0010\u001c\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u001d\u001a\u00028\u00002\n\u0010\u001e\u001a\u0006\u0012\u0002\b\u00030\bH&¢\u0006\u0002\u0010\u001fJ\n\u0010 \u001a\u00020\u0005H\u0096\u0080\u0004R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0006\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001b\u0010\n\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00150\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\u00198VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u001aR\u0014\u0010\u001b\u001a\u00020\u00198VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001a¨\u0006!"}, d2 = {"Lapp/cash/zipline/internal/bridge/ReturningZiplineFunction;", ExifInterface.GPS_DIRECTION_TRUE, "Lapp/cash/zipline/ZiplineService;", "Lapp/cash/zipline/ZiplineFunction;", "id", "", "signature", "argSerializers", "", "Lkotlinx/serialization/KSerializer;", "resultSerializer", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lkotlinx/serialization/KSerializer;)V", "getId", "()Ljava/lang/String;", "getSignature", "argsListSerializer", "Lapp/cash/zipline/internal/bridge/ArgsListSerializer;", "getArgsListSerializer", "()Lapp/cash/zipline/internal/bridge/ArgsListSerializer;", "Lapp/cash/zipline/internal/bridge/ResultOrCallbackSerializer;", "", "getResultSerializer", "()Lapp/cash/zipline/internal/bridge/ResultOrCallbackSerializer;", "isSuspending", "", "()Z", "isClose", NotificationCompat.CATEGORY_CALL, NotificationCompat.CATEGORY_SERVICE, "args", "(Lapp/cash/zipline/ZiplineService;Ljava/util/List;)Ljava/lang/Object;", "toString", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class ReturningZiplineFunction<T extends ZiplineService> implements ZiplineFunction<T> {
    private final ArgsListSerializer argsListSerializer;
    private final String id;
    private final ResultOrCallbackSerializer<? extends Object> resultSerializer;
    private final String signature;

    public abstract Object call(T service, List<?> args);

    @Override // app.cash.zipline.ZiplineFunction
    /* JADX INFO: renamed from: isSuspending */
    public boolean getIsSuspending() {
        return false;
    }

    public ReturningZiplineFunction(String id, String signature, List<? extends KSerializer<?>> argSerializers, KSerializer<?> resultSerializer) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(signature, "signature");
        Intrinsics.checkNotNullParameter(argSerializers, "argSerializers");
        Intrinsics.checkNotNullParameter(resultSerializer, "resultSerializer");
        this.id = id;
        this.signature = signature;
        this.argsListSerializer = new ArgsListSerializer(argSerializers);
        this.resultSerializer = new ResultOrCallbackSerializer<>(resultSerializer);
    }

    @Override // app.cash.zipline.ZiplineFunction
    public String getId() {
        return this.id;
    }

    @Override // app.cash.zipline.ZiplineFunction
    public String getSignature() {
        return this.signature;
    }

    public final ArgsListSerializer getArgsListSerializer() {
        return this.argsListSerializer;
    }

    public final ResultOrCallbackSerializer<? extends Object> getResultSerializer() {
        return this.resultSerializer;
    }

    @Override // app.cash.zipline.ZiplineFunction
    public boolean isClose() {
        return Intrinsics.areEqual(getSignature(), "fun close(): kotlin.Unit");
    }

    public String toString() {
        return getSignature();
    }
}
