package coil3.intercept;

import androidx.mediarouter.media.GlobalMediaRouter;
import androidx.tvprovider.media.tv.TvContractCompat;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: compiled from: EngineInterceptor.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a8\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0080@¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"transform", "Lcoil3/intercept/EngineInterceptor$ExecuteResult;", "result", "request", "Lcoil3/request/ImageRequest;", "options", "Lcoil3/request/Options;", "eventListener", "Lcoil3/EventListener;", "logger", "Lcoil3/util/Logger;", "(Lcoil3/intercept/EngineInterceptor$ExecuteResult;Lcoil3/request/ImageRequest;Lcoil3/request/Options;Lcoil3/EventListener;Lcoil3/util/Logger;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "coil-core_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class EngineInterceptorKt {

    /* JADX INFO: renamed from: coil3.intercept.EngineInterceptorKt$transform$1, reason: invalid class name */
    /* JADX INFO: compiled from: EngineInterceptor.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "coil3.intercept.EngineInterceptorKt", f = "EngineInterceptor.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, l = {GlobalMediaRouter.CallbackHandler.MSG_ROUTE_CHANGED}, m = "transform", n = {"result", "request", "options", "eventListener", "logger", "transformations", "image", TvContractCompat.PARAM_INPUT, "$this$foldIndices$iv", "initial$iv", "accumulator$iv", "transformation", "bitmap", "$i$f$foldIndices", "i$iv", "$i$a$-foldIndices-EngineInterceptorKt$transform$output$1"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "I$0", "I$1", "I$3"})
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$12;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return EngineInterceptorKt.transform(null, null, null, null, null, this);
        }
    }

    /* JADX WARN: Code duplicated, block: B:28:0x00f7  */
    /* JADX WARN: Code duplicated, block: B:30:0x0155 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:31:0x0156  */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:31:0x0156 -> B:32:0x015b). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public static final java.lang.Object transform(coil3.intercept.EngineInterceptor.ExecuteResult r23, coil3.request.ImageRequest r24, coil3.request.Options r25, coil3.EventListener r26, coil3.util.Logger r27, kotlin.coroutines.Continuation<? super coil3.intercept.EngineInterceptor.ExecuteResult> r28) {
        /*
            Method dump skipped, instruction units count: 395
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: coil3.intercept.EngineInterceptorKt.transform(coil3.intercept.EngineInterceptor$ExecuteResult, coil3.request.ImageRequest, coil3.request.Options, coil3.EventListener, coil3.util.Logger, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
