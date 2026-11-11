package app.cash.zipline.internal.bridge;

import androidx.exifinterface.media.ExifInterface;
import app.cash.zipline.ZiplineFunction;
import app.cash.zipline.ZiplineService;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.internal.UnitSerializer;
import kotlinx.serialization.modules.SerializersModule;

/* JADX INFO: compiled from: SuspendCallback.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000\ba\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\bH&¨\u0006\tÀ\u0006\u0003"}, d2 = {"Lapp/cash/zipline/internal/bridge/SuspendCallback;", ExifInterface.GPS_DIRECTION_TRUE, "Lapp/cash/zipline/ZiplineService;", "success", "", "result", "(Ljava/lang/Object;)V", "failure", "", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface SuspendCallback<T> extends ZiplineService {
    public static final Companion Companion = Companion.$$INSTANCE;

    void failure(Throwable result);

    void success(T result);

    /* JADX INFO: compiled from: SuspendCallback.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        /* JADX INFO: compiled from: SuspendCallback.kt */
        @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
        public static final class Adapter<TX> extends ZiplineServiceAdapter<SuspendCallback<TX>> implements KSerializer<SuspendCallback<TX>> {
            private final String serialName;
            private final List<KSerializer<?>> serializers;
            private final String simpleName;

            /* JADX INFO: compiled from: SuspendCallback.kt */
            @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
            private static final class GeneratedOutboundService<TS> implements SuspendCallback<TS>, OutboundService {
                private final OutboundCallHandler callHandler;

                public GeneratedOutboundService(OutboundCallHandler callHandler) {
                    Intrinsics.checkNotNullParameter(callHandler, "callHandler");
                    this.callHandler = callHandler;
                }

                @Override // app.cash.zipline.ZiplineService, java.lang.AutoCloseable
                public void close() {
                    Object objCall = this.callHandler.call(this, 2, new Object[0]);
                    Intrinsics.checkNotNull(objCall, "null cannot be cast to non-null type kotlin.Unit");
                }

                @Override // app.cash.zipline.internal.bridge.SuspendCallback
                public void failure(Throwable result) {
                    Intrinsics.checkNotNullParameter(result, "result");
                    Object objCall = this.callHandler.call(this, 1, result);
                    Intrinsics.checkNotNull(objCall, "null cannot be cast to non-null type kotlin.Unit");
                }

                @Override // app.cash.zipline.internal.bridge.OutboundService
                public final OutboundCallHandler getCallHandler() {
                    return this.callHandler;
                }

                @Override // app.cash.zipline.internal.bridge.SuspendCallback
                public void success(TS ts) {
                    Object objCall = this.callHandler.call(this, 0, ts);
                    Intrinsics.checkNotNull(objCall, "null cannot be cast to non-null type kotlin.Unit");
                }
            }

            /* JADX INFO: compiled from: SuspendCallback.kt */
            @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
            private static final class ZiplineFunction0<TF> extends ReturningZiplineFunction<SuspendCallback<TF>> {
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public ZiplineFunction0(List<? extends KSerializer<?>> argSerializers, KSerializer<?> resultSerializer) {
                    super("uCBo/XOg", "fun success(T): kotlin.Unit", argSerializers, resultSerializer);
                    Intrinsics.checkNotNullParameter(argSerializers, "argSerializers");
                    Intrinsics.checkNotNullParameter(resultSerializer, "resultSerializer");
                }

                @Override // app.cash.zipline.internal.bridge.ReturningZiplineFunction
                public /* bridge */ /* synthetic */ Object call(ZiplineService ziplineService, List list) {
                    return call((SuspendCallback) ziplineService, (List<?>) list);
                }

                /* JADX WARN: Multi-variable type inference failed */
                public Object call(SuspendCallback<TF> service, List<?> args) {
                    Intrinsics.checkNotNullParameter(service, "service");
                    Intrinsics.checkNotNullParameter(args, "args");
                    service.success(args.get(0));
                    return Unit.INSTANCE;
                }
            }

            /* JADX INFO: compiled from: SuspendCallback.kt */
            @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
            private static final class ZiplineFunction1<TF> extends ReturningZiplineFunction<SuspendCallback<TF>> {
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public ZiplineFunction1(List<? extends KSerializer<?>> argSerializers, KSerializer<?> resultSerializer) {
                    super("tNUxxzEe", "fun failure(kotlin.Throwable): kotlin.Unit", argSerializers, resultSerializer);
                    Intrinsics.checkNotNullParameter(argSerializers, "argSerializers");
                    Intrinsics.checkNotNullParameter(resultSerializer, "resultSerializer");
                }

                @Override // app.cash.zipline.internal.bridge.ReturningZiplineFunction
                public /* bridge */ /* synthetic */ Object call(ZiplineService ziplineService, List list) {
                    return call((SuspendCallback) ziplineService, (List<?>) list);
                }

                public Object call(SuspendCallback<TF> service, List<?> args) {
                    Intrinsics.checkNotNullParameter(service, "service");
                    Intrinsics.checkNotNullParameter(args, "args");
                    Object obj = args.get(0);
                    Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Throwable");
                    service.failure((Throwable) obj);
                    return Unit.INSTANCE;
                }
            }

            /* JADX INFO: compiled from: SuspendCallback.kt */
            @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
            private static final class ZiplineFunction2<TF> extends ReturningZiplineFunction<SuspendCallback<TF>> {
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public ZiplineFunction2(List<? extends KSerializer<?>> argSerializers, KSerializer<?> resultSerializer) {
                    super("moYx+T3e", "fun close(): kotlin.Unit", argSerializers, resultSerializer);
                    Intrinsics.checkNotNullParameter(argSerializers, "argSerializers");
                    Intrinsics.checkNotNullParameter(resultSerializer, "resultSerializer");
                }

                @Override // app.cash.zipline.internal.bridge.ReturningZiplineFunction
                public /* bridge */ /* synthetic */ Object call(ZiplineService ziplineService, List list) {
                    return call((SuspendCallback) ziplineService, (List<?>) list);
                }

                public Object call(SuspendCallback<TF> service, List<?> args) {
                    Intrinsics.checkNotNullParameter(service, "service");
                    Intrinsics.checkNotNullParameter(args, "args");
                    service.close();
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: Multi-variable type inference failed */
            public Adapter(List<? extends KSerializer<?>> serializers, String serialName) {
                Intrinsics.checkNotNullParameter(serializers, "serializers");
                Intrinsics.checkNotNullParameter(serialName, "serialName");
                this.serialName = serialName;
                this.simpleName = "SuspendCallback";
                this.serializers = serializers;
            }

            @Override // app.cash.zipline.internal.bridge.ZiplineServiceAdapter
            public final String getSerialName() {
                return this.serialName;
            }

            @Override // app.cash.zipline.internal.bridge.ZiplineServiceAdapter
            public final List<KSerializer<?>> getSerializers() {
                return this.serializers;
            }

            @Override // app.cash.zipline.internal.bridge.ZiplineServiceAdapter
            public final String getSimpleName() {
                return this.simpleName;
            }

            @Override // app.cash.zipline.internal.bridge.ZiplineServiceAdapter
            public SuspendCallback<TX> outboundService(OutboundCallHandler callHandler) {
                Intrinsics.checkNotNullParameter(callHandler, "callHandler");
                return new GeneratedOutboundService(callHandler);
            }

            @Override // app.cash.zipline.internal.bridge.ZiplineServiceAdapter
            public List<ZiplineFunction<SuspendCallback<TX>>> ziplineFunctions(SerializersModule serializersModule) {
                Intrinsics.checkNotNullParameter(serializersModule, "serializersModule");
                KSerializer<?> kSerializer = this.serializers.get(0);
                UnitSerializer unitSerializer = UnitSerializer.INSTANCE;
                return CollectionsKt.listOf((Object[]) new ZiplineFunction[]{new ZiplineFunction0(CollectionsKt.listOf((Object[]) new KSerializer[]{kSerializer}), unitSerializer), new ZiplineFunction1(CollectionsKt.listOf((Object[]) new KSerializer[]{SerializersKt.noCompiledSerializer(serializersModule, Reflection.getOrCreateKotlinClass(Throwable.class))}), unitSerializer), new ZiplineFunction2(CollectionsKt.listOf((Object[]) new KSerializer[0]), unitSerializer)});
            }
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: app.cash.zipline.internal.bridge.SuspendCallback$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: SuspendCallback.kt */
    public final /* synthetic */ class CC {
        static {
            Companion companion = SuspendCallback.Companion;
        }
    }

    /* JADX INFO: compiled from: SuspendCallback.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static <T> void close(SuspendCallback<T> suspendCallback) {
            ZiplineService.CC.$default$close(suspendCallback);
        }
    }
}
