package coil3;

import android.content.Context;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import coil3.decode.DataSource;
import coil3.disk.DiskCache;
import coil3.intercept.EngineInterceptor;
import coil3.memory.MemoryCache;
import coil3.request.Disposable;
import coil3.request.ErrorResult;
import coil3.request.ImageRequest;
import coil3.request.ImageRequests_androidKt;
import coil3.request.ImageResult;
import coil3.request.NullRequestData;
import coil3.request.NullRequestDataException;
import coil3.request.RequestDelegate;
import coil3.request.RequestService;
import coil3.request.RequestService_androidKt;
import coil3.request.SuccessResult;
import coil3.size.Size;
import coil3.size.SizeResolver;
import coil3.target.Target;
import coil3.transition.NoneTransition;
import coil3.transition.Transition;
import coil3.transition.TransitionTarget;
import coil3.util.Logger;
import coil3.util.SystemCallbacks;
import coil3.util.SystemCallbacksKt;
import coil3.util.UtilsKt;
import io.ktor.http.ContentDisposition;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.JobKt;

/* JADX INFO: compiled from: RealImageLoader.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001:\u0001;B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0016J\u0016\u0010'\u001a\u00020(2\u0006\u0010%\u001a\u00020&H\u0096@¢\u0006\u0002\u0010)J\u001e\u0010'\u001a\u00020(2\u0006\u0010*\u001a\u00020&2\u0006\u0010+\u001a\u00020,H\u0082@¢\u0006\u0002\u0010-J\b\u0010!\u001a\u00020.H\u0016J\b\u0010/\u001a\u000200H\u0016J\"\u00101\u001a\u00020.2\u0006\u00102\u001a\u0002032\b\u00104\u001a\u0004\u0018\u0001052\u0006\u00106\u001a\u000207H\u0002J\"\u00108\u001a\u00020.2\u0006\u00102\u001a\u0002092\b\u00104\u001a\u0004\u0018\u0001052\u0006\u00106\u001a\u000207H\u0002J\u0018\u0010:\u001a\u00020.2\u0006\u0010%\u001a\u00020&2\u0006\u00106\u001a\u000207H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\u00020\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u001d\u0010\u0012\u001a\u0004\u0018\u00010\u00138VX\u0096\u0084\u0002¢\u0006\f\u001a\u0004\b\u0016\u0010\u0017*\u0004\b\u0014\u0010\u0015R\u001d\u0010\u0018\u001a\u0004\u0018\u00010\u00198VX\u0096\u0084\u0002¢\u0006\f\u001a\u0004\b\u001b\u0010\u001c*\u0004\b\u001a\u0010\u0015R\u0014\u0010\u001d\u001a\u00020\u001eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\t\u0010!\u001a\u00020\"X\u0082\u0004¨\u0006<"}, d2 = {"Lcoil3/RealImageLoader;", "Lcoil3/ImageLoader;", "options", "Lcoil3/RealImageLoader$Options;", "<init>", "(Lcoil3/RealImageLoader$Options;)V", "getOptions", "()Lcoil3/RealImageLoader$Options;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "systemCallbacks", "Lcoil3/util/SystemCallbacks;", "requestService", "Lcoil3/request/RequestService;", "defaults", "Lcoil3/request/ImageRequest$Defaults;", "getDefaults", "()Lcoil3/request/ImageRequest$Defaults;", "memoryCache", "Lcoil3/memory/MemoryCache;", "getMemoryCache$delegate", "(Lcoil3/RealImageLoader;)Ljava/lang/Object;", "getMemoryCache", "()Lcoil3/memory/MemoryCache;", "diskCache", "Lcoil3/disk/DiskCache;", "getDiskCache$delegate", "getDiskCache", "()Lcoil3/disk/DiskCache;", "components", "Lcoil3/ComponentRegistry;", "getComponents", "()Lcoil3/ComponentRegistry;", "shutdown", "Lkotlinx/atomicfu/AtomicBoolean;", "enqueue", "Lcoil3/request/Disposable;", "request", "Lcoil3/request/ImageRequest;", "execute", "Lcoil3/request/ImageResult;", "(Lcoil3/request/ImageRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "initialRequest", "type", "", "(Lcoil3/request/ImageRequest;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "newBuilder", "Lcoil3/ImageLoader$Builder;", "onSuccess", "result", "Lcoil3/request/SuccessResult;", TypedValues.AttributesType.S_TARGET, "Lcoil3/target/Target;", "eventListener", "Lcoil3/EventListener;", "onError", "Lcoil3/request/ErrorResult;", "onCancel", "Options", "coil-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class RealImageLoader implements ImageLoader {
    private static final /* synthetic */ AtomicIntegerFieldUpdater shutdown$volatile$FU = AtomicIntegerFieldUpdater.newUpdater(RealImageLoader.class, "shutdown$volatile");
    private final ComponentRegistry components;
    private final Options options;
    private final RequestService requestService;
    private final CoroutineScope scope;
    private volatile /* synthetic */ int shutdown$volatile;
    private final SystemCallbacks systemCallbacks;

    /* JADX INFO: renamed from: coil3.RealImageLoader$execute$3, reason: invalid class name */
    /* JADX INFO: compiled from: RealImageLoader.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "coil3.RealImageLoader", f = "RealImageLoader.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2}, l = {117, 129, 133}, m = "execute", n = {"initialRequest", "requestDelegate", "request", "eventListener", "type", "initialRequest", "requestDelegate", "request", "eventListener", "cachedPlaceholder", "sizeResolver", "type", "initialRequest", "requestDelegate", "request", "eventListener", "cachedPlaceholder", "sizeResolver", ContentDisposition.Parameters.Size, "type"}, s = {"L$0", "L$1", "L$2", "L$3", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "I$0"})
    static final class AnonymousClass3 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;
        /* synthetic */ Object result;

        AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return RealImageLoader.this.execute(null, 0, this);
        }
    }

    private final /* synthetic */ int getShutdown$volatile() {
        return this.shutdown$volatile;
    }

    private final /* synthetic */ void setShutdown$volatile(int i) {
        this.shutdown$volatile = i;
    }

    public RealImageLoader(Options options) {
        this.options = options;
        this.scope = RealImageLoaderKt.CoroutineScope(options.getLogger());
        SystemCallbacks SystemCallbacks = SystemCallbacksKt.SystemCallbacks(this);
        this.systemCallbacks = SystemCallbacks;
        RealImageLoader realImageLoader = this;
        RequestService RequestService = RequestService_androidKt.RequestService(realImageLoader, SystemCallbacks, options.getLogger());
        this.requestService = RequestService;
        options.getMemoryCacheLazy();
        options.getDiskCacheLazy();
        this.components = RealImageLoaderKt.addCommonComponents(RealImageLoader_nonNativeKt.addAppleComponents(RealImageLoader_jvmCommonKt.addJvmComponents(RealImageLoader_androidKt.addAndroidComponents(RealImageLoaderKt.addServiceLoaderComponents(options.getComponentRegistry().newBuilder(), options), options), options), options)).add(new EngineInterceptor(realImageLoader, SystemCallbacks, RequestService, options.getLogger())).build();
    }

    public final Options getOptions() {
        return this.options;
    }

    @Override // coil3.ImageLoader
    public ImageRequest.Defaults getDefaults() {
        return this.options.getDefaults();
    }

    @Override // coil3.ImageLoader
    public MemoryCache getMemoryCache() {
        return this.options.getMemoryCacheLazy().getValue();
    }

    @Override // coil3.ImageLoader
    public DiskCache getDiskCache() {
        return this.options.getDiskCacheLazy().getValue();
    }

    @Override // coil3.ImageLoader
    public ComponentRegistry getComponents() {
        return this.components;
    }

    @Override // coil3.ImageLoader
    public Disposable enqueue(ImageRequest request) {
        return RealImageLoader_androidKt.getDisposable(request, BuildersKt__Builders_commonKt.async$default(this.scope, this.options.getMainCoroutineContextLazy().getValue(), null, new RealImageLoader$enqueue$job$1(this, request, null), 2, null));
    }

    @Override // coil3.ImageLoader
    public Object execute(ImageRequest imageRequest, Continuation<? super ImageResult> continuation) {
        if (!RealImageLoader_androidKt.needsExecuteOnMainDispatcher(imageRequest)) {
            return execute(imageRequest, 1, continuation);
        }
        return CoroutineScopeKt.coroutineScope(new AnonymousClass2(imageRequest, null), continuation);
    }

    /* JADX INFO: renamed from: coil3.RealImageLoader$execute$2, reason: invalid class name */
    /* JADX INFO: compiled from: RealImageLoader.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcoil3/request/ImageResult;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "coil3.RealImageLoader$execute$2", f = "RealImageLoader.kt", i = {0, 0}, l = {87}, m = "invokeSuspend", n = {"$this$coroutineScope", "job"}, s = {"L$0", "L$1"})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ImageResult>, Object> {
        final /* synthetic */ ImageRequest $request;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(ImageRequest imageRequest, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$request = imageRequest;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = RealImageLoader.this.new AnonymousClass2(this.$request, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super ImageResult> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i != 0) {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return obj;
            }
            ResultKt.throwOnFailure(obj);
            Deferred deferredAsync$default = BuildersKt__Builders_commonKt.async$default(coroutineScope, RealImageLoader.this.getOptions().getMainCoroutineContextLazy().getValue(), null, new RealImageLoader$execute$2$job$1(RealImageLoader.this, this.$request, null), 2, null);
            this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
            this.L$1 = SpillingKt.nullOutSpilledVariable(deferredAsync$default);
            this.label = 1;
            Object objAwait = RealImageLoader_androidKt.getDisposable(this.$request, deferredAsync$default).getJob().await(this);
            return objAwait == coroutine_suspended ? coroutine_suspended : objAwait;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:67:0x01a2  */
    /* JADX WARN: Code duplicated, block: B:70:0x01ac A[Catch: all -> 0x005b, TryCatch #2 {all -> 0x005b, blocks: (B:14:0x0056, B:68:0x01a6, B:70:0x01ac, B:71:0x01b7, B:73:0x01bb, B:76:0x01c9, B:77:0x01ce), top: B:96:0x0056 }] */
    /* JADX WARN: Code duplicated, block: B:71:0x01b7 A[Catch: all -> 0x005b, TryCatch #2 {all -> 0x005b, blocks: (B:14:0x0056, B:68:0x01a6, B:70:0x01ac, B:71:0x01b7, B:73:0x01bb, B:76:0x01c9, B:77:0x01ce), top: B:96:0x0056 }] */
    /* JADX WARN: Code duplicated, block: B:73:0x01bb A[Catch: all -> 0x005b, TRY_LEAVE, TryCatch #2 {all -> 0x005b, blocks: (B:14:0x0056, B:68:0x01a6, B:70:0x01ac, B:71:0x01b7, B:73:0x01bb, B:76:0x01c9, B:77:0x01ce), top: B:96:0x0056 }] */
    /* JADX WARN: Code duplicated, block: B:76:0x01c9 A[Catch: all -> 0x005b, TRY_ENTER, TryCatch #2 {all -> 0x005b, blocks: (B:14:0x0056, B:68:0x01a6, B:70:0x01ac, B:71:0x01b7, B:73:0x01bb, B:76:0x01c9, B:77:0x01ce), top: B:96:0x0056 }] */
    /* JADX WARN: Code duplicated, block: B:7:0x001c  */
    public final Object execute(ImageRequest imageRequest, int i, Continuation<? super ImageResult> continuation) {
        AnonymousClass3 anonymousClass3;
        RequestDelegate requestDelegate;
        ImageRequest imageRequestUpdateRequest;
        EventListener eventListenerCreate;
        RequestDelegate requestDelegate2;
        EventListener eventListener;
        RequestDelegate requestDelegate3;
        int i2;
        ImageRequest imageRequest2;
        EventListener eventListener2;
        ImageRequest imageRequest3;
        RequestDelegate requestDelegate4;
        Image image;
        SizeResolver sizeResolver;
        MemoryCache memoryCache;
        MemoryCache.Value value;
        ImageResult imageResult;
        ImageRequest imageRequest4 = imageRequest;
        int i3 = i;
        if (continuation instanceof AnonymousClass3) {
            anonymousClass3 = (AnonymousClass3) continuation;
            if ((anonymousClass3.label & Integer.MIN_VALUE) != 0) {
                anonymousClass3.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass3 = new AnonymousClass3(continuation);
            }
        } else {
            anonymousClass3 = new AnonymousClass3(continuation);
        }
        AnonymousClass3 anonymousClass4 = anonymousClass3;
        Object objWithContext = anonymousClass4.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i4 = anonymousClass4.label;
        if (i4 == 0) {
            ResultKt.throwOnFailure(objWithContext);
            requestDelegate = this.requestService.requestDelegate(imageRequest4, JobKt.getJob(anonymousClass4.getContext()), i3 == 0);
            requestDelegate.assertActive();
            imageRequestUpdateRequest = this.requestService.updateRequest(imageRequest4);
            eventListenerCreate = this.options.getEventListenerFactory().create(imageRequestUpdateRequest);
            try {
                if (Intrinsics.areEqual(imageRequestUpdateRequest.getData(), NullRequestData.INSTANCE)) {
                    throw new NullRequestDataException();
                }
                requestDelegate.start();
                if (i3 == 0) {
                    anonymousClass4.L$0 = SpillingKt.nullOutSpilledVariable(imageRequest4);
                    anonymousClass4.L$1 = requestDelegate;
                    anonymousClass4.L$2 = imageRequestUpdateRequest;
                    anonymousClass4.L$3 = eventListenerCreate;
                    anonymousClass4.I$0 = i3;
                    anonymousClass4.label = 1;
                    if (requestDelegate.awaitStarted(anonymousClass4) != coroutine_suspended) {
                        requestDelegate3 = requestDelegate;
                        requestDelegate = requestDelegate3;
                    }
                }
                return coroutine_suspended;
            } catch (Throwable th) {
                th = th;
                requestDelegate2 = requestDelegate;
                eventListener = eventListenerCreate;
            }
        } else {
            if (i4 == 1) {
                int i5 = anonymousClass4.I$0;
                eventListener = (EventListener) anonymousClass4.L$3;
                imageRequestUpdateRequest = (ImageRequest) anonymousClass4.L$2;
                requestDelegate3 = (RequestDelegate) anonymousClass4.L$1;
                ImageRequest imageRequest5 = (ImageRequest) anonymousClass4.L$0;
                try {
                    ResultKt.throwOnFailure(objWithContext);
                    i3 = i5;
                    imageRequest4 = imageRequest5;
                    eventListenerCreate = eventListener;
                    requestDelegate = requestDelegate3;
                } catch (Throwable th2) {
                    th = th2;
                    requestDelegate2 = requestDelegate3;
                }
            } else if (i4 == 2) {
                int i6 = anonymousClass4.I$0;
                SizeResolver sizeResolver2 = (SizeResolver) anonymousClass4.L$5;
                Image image2 = (Image) anonymousClass4.L$4;
                EventListener eventListener3 = (EventListener) anonymousClass4.L$3;
                ImageRequest imageRequest6 = (ImageRequest) anonymousClass4.L$2;
                RequestDelegate requestDelegate5 = (RequestDelegate) anonymousClass4.L$1;
                ImageRequest imageRequest7 = (ImageRequest) anonymousClass4.L$0;
                try {
                    ResultKt.throwOnFailure(objWithContext);
                    image = image2;
                    eventListener2 = eventListener3;
                    requestDelegate4 = requestDelegate5;
                    imageRequest3 = imageRequest7;
                    i2 = i6;
                    sizeResolver = sizeResolver2;
                    imageRequest2 = imageRequest6;
                    try {
                        Size size = (Size) objWithContext;
                        eventListener2.resolveSizeEnd(imageRequest2, size);
                        CoroutineContext interceptorCoroutineContext = imageRequest2.getInterceptorCoroutineContext();
                        RealImageLoader$execute$result$1 realImageLoader$execute$result$1 = new RealImageLoader$execute$result$1(imageRequest2, this, size, eventListener2, image, null);
                        anonymousClass4.L$0 = SpillingKt.nullOutSpilledVariable(imageRequest3);
                        anonymousClass4.L$1 = requestDelegate4;
                        anonymousClass4.L$2 = imageRequest2;
                        anonymousClass4.L$3 = eventListener2;
                        anonymousClass4.L$4 = SpillingKt.nullOutSpilledVariable(image);
                        anonymousClass4.L$5 = SpillingKt.nullOutSpilledVariable(sizeResolver);
                        anonymousClass4.L$6 = SpillingKt.nullOutSpilledVariable(size);
                        anonymousClass4.I$0 = i2;
                        anonymousClass4.label = 3;
                        objWithContext = BuildersKt.withContext(interceptorCoroutineContext, realImageLoader$execute$result$1, anonymousClass4);
                        if (objWithContext != coroutine_suspended) {
                            EventListener eventListener4 = eventListener2;
                            imageRequestUpdateRequest = imageRequest2;
                            eventListener = eventListener4;
                            requestDelegate2 = requestDelegate4;
                            imageResult = (ImageResult) objWithContext;
                            if (imageResult instanceof SuccessResult) {
                                onSuccess((SuccessResult) imageResult, imageRequestUpdateRequest.getTarget(), eventListener);
                            } else {
                                if (!(imageResult instanceof ErrorResult)) {
                                    throw new NoWhenBranchMatchedException();
                                }
                                onError((ErrorResult) imageResult, imageRequestUpdateRequest.getTarget(), eventListener);
                            }
                            requestDelegate2.complete();
                            return imageResult;
                        }
                        return coroutine_suspended;
                    } catch (Throwable th3) {
                        th = th3;
                        EventListener eventListener5 = eventListener2;
                        imageRequestUpdateRequest = imageRequest2;
                        eventListener = eventListener5;
                        requestDelegate2 = requestDelegate4;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    eventListener = eventListener3;
                    imageRequestUpdateRequest = imageRequest6;
                    requestDelegate2 = requestDelegate5;
                }
            } else {
                if (i4 != 3) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                int i7 = anonymousClass4.I$0;
                eventListener = (EventListener) anonymousClass4.L$3;
                imageRequestUpdateRequest = (ImageRequest) anonymousClass4.L$2;
                requestDelegate2 = (RequestDelegate) anonymousClass4.L$1;
                try {
                    ResultKt.throwOnFailure(objWithContext);
                    imageResult = (ImageResult) objWithContext;
                    if (imageResult instanceof SuccessResult) {
                        onSuccess((SuccessResult) imageResult, imageRequestUpdateRequest.getTarget(), eventListener);
                    } else {
                        if (!(imageResult instanceof ErrorResult)) {
                            throw new NoWhenBranchMatchedException();
                        }
                        onError((ErrorResult) imageResult, imageRequestUpdateRequest.getTarget(), eventListener);
                    }
                    requestDelegate2.complete();
                    return imageResult;
                } catch (Throwable th5) {
                    th = th5;
                }
            }
            try {
                if (th instanceof CancellationException) {
                    onCancel(imageRequestUpdateRequest, eventListener);
                    throw th;
                }
                ErrorResult ErrorResult = UtilsKt.ErrorResult(imageRequestUpdateRequest, th);
                onError(ErrorResult, imageRequestUpdateRequest.getTarget(), eventListener);
                requestDelegate2.complete();
                return ErrorResult;
            } catch (Throwable th6) {
                requestDelegate2.complete();
                throw th6;
            }
        }
        MemoryCache.Key placeholderMemoryCacheKey = imageRequestUpdateRequest.getPlaceholderMemoryCacheKey();
        Image image3 = null;
        if (placeholderMemoryCacheKey != null && (memoryCache = getMemoryCache()) != null && (value = memoryCache.get(placeholderMemoryCacheKey)) != null) {
            image3 = value.getImage();
        }
        Target target = imageRequestUpdateRequest.getTarget();
        if (target != null) {
            target.onStart(image3 == null ? imageRequestUpdateRequest.placeholder() : image3);
        }
        eventListenerCreate.onStart(imageRequestUpdateRequest);
        ImageRequest.Listener listener = imageRequestUpdateRequest.getListener();
        if (listener != null) {
            listener.onStart(imageRequestUpdateRequest);
        }
        SizeResolver sizeResolver3 = imageRequestUpdateRequest.getSizeResolver();
        eventListenerCreate.resolveSizeStart(imageRequestUpdateRequest, sizeResolver3);
        anonymousClass4.L$0 = SpillingKt.nullOutSpilledVariable(imageRequest4);
        anonymousClass4.L$1 = requestDelegate;
        anonymousClass4.L$2 = imageRequestUpdateRequest;
        anonymousClass4.L$3 = eventListenerCreate;
        anonymousClass4.L$4 = image3;
        anonymousClass4.L$5 = SpillingKt.nullOutSpilledVariable(sizeResolver3);
        anonymousClass4.I$0 = i3;
        anonymousClass4.label = 2;
        Object size2 = sizeResolver3.size(anonymousClass4);
        if (size2 != coroutine_suspended) {
            EventListener eventListener6 = eventListenerCreate;
            i2 = i3;
            imageRequest2 = imageRequestUpdateRequest;
            eventListener2 = eventListener6;
            imageRequest3 = imageRequest4;
            requestDelegate4 = requestDelegate;
            objWithContext = size2;
            image = image3;
            sizeResolver = sizeResolver3;
            Size size3 = (Size) objWithContext;
            eventListener2.resolveSizeEnd(imageRequest2, size3);
            CoroutineContext interceptorCoroutineContext2 = imageRequest2.getInterceptorCoroutineContext();
            RealImageLoader$execute$result$1 realImageLoader$execute$result$2 = new RealImageLoader$execute$result$1(imageRequest2, this, size3, eventListener2, image, null);
            anonymousClass4.L$0 = SpillingKt.nullOutSpilledVariable(imageRequest3);
            anonymousClass4.L$1 = requestDelegate4;
            anonymousClass4.L$2 = imageRequest2;
            anonymousClass4.L$3 = eventListener2;
            anonymousClass4.L$4 = SpillingKt.nullOutSpilledVariable(image);
            anonymousClass4.L$5 = SpillingKt.nullOutSpilledVariable(sizeResolver);
            anonymousClass4.L$6 = SpillingKt.nullOutSpilledVariable(size3);
            anonymousClass4.I$0 = i2;
            anonymousClass4.label = 3;
            objWithContext = BuildersKt.withContext(interceptorCoroutineContext2, realImageLoader$execute$result$2, anonymousClass4);
            if (objWithContext != coroutine_suspended) {
                EventListener eventListener7 = eventListener2;
                imageRequestUpdateRequest = imageRequest2;
                eventListener = eventListener7;
                requestDelegate2 = requestDelegate4;
                imageResult = (ImageResult) objWithContext;
                if (imageResult instanceof SuccessResult) {
                    onSuccess((SuccessResult) imageResult, imageRequestUpdateRequest.getTarget(), eventListener);
                } else {
                    if (!(imageResult instanceof ErrorResult)) {
                        throw new NoWhenBranchMatchedException();
                    }
                    onError((ErrorResult) imageResult, imageRequestUpdateRequest.getTarget(), eventListener);
                }
                requestDelegate2.complete();
                return imageResult;
            }
        }
        return coroutine_suspended;
    }

    @Override // coil3.ImageLoader
    public void shutdown() {
        if (shutdown$volatile$FU.getAndSet(this, 1) == 1) {
            return;
        }
        CoroutineScopeKt.cancel$default(this.scope, null, 1, null);
        this.systemCallbacks.shutdown();
        MemoryCache memoryCache = getMemoryCache();
        if (memoryCache != null) {
            memoryCache.clear();
        }
    }

    @Override // coil3.ImageLoader
    public ImageLoader.Builder newBuilder() {
        return new ImageLoader.Builder(this.options);
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0056  */
    private final void onSuccess(SuccessResult result, Target target, EventListener eventListener) {
        ImageRequest request = result.getRequest();
        DataSource dataSource = result.getDataSource();
        Logger logger = this.options.getLogger();
        if (logger != null) {
            Logger.Level level = Logger.Level.Info;
            if (logger.getMinLevel().compareTo(level) <= 0) {
                logger.log("RealImageLoader", level, UtilsKt.getEmoji(dataSource) + " Successful (" + dataSource.name() + ") - " + request.getData(), null);
            }
        }
        SuccessResult successResult = result;
        if (target instanceof TransitionTarget) {
            Transition transitionCreate = ImageRequests_androidKt.getTransitionFactory(successResult.getRequest()).create((TransitionTarget) target, successResult);
            if (!(transitionCreate instanceof NoneTransition)) {
                eventListener.transitionStart(successResult.getRequest(), transitionCreate);
                transitionCreate.transition();
                eventListener.transitionEnd(successResult.getRequest(), transitionCreate);
            } else if (target != null) {
                target.onSuccess(result.getImage());
            }
        } else if (target != null) {
            target.onSuccess(result.getImage());
        }
        eventListener.onSuccess(request, result);
        ImageRequest.Listener listener = request.getListener();
        if (listener != null) {
            listener.onSuccess(request, result);
        }
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0040  */
    private final void onError(ErrorResult result, Target target, EventListener eventListener) {
        ImageRequest request = result.getRequest();
        Logger logger = this.options.getLogger();
        if (logger != null) {
            Throwable throwable = result.getThrowable();
            if (logger.getMinLevel().compareTo(Logger.Level.Error) <= 0) {
                logger.log("RealImageLoader", Logger.Level.Error, "🚨 Failed - " + request.getData(), throwable);
            }
        }
        ErrorResult errorResult = result;
        if (target instanceof TransitionTarget) {
            Transition transitionCreate = ImageRequests_androidKt.getTransitionFactory(errorResult.getRequest()).create((TransitionTarget) target, errorResult);
            if (!(transitionCreate instanceof NoneTransition)) {
                eventListener.transitionStart(errorResult.getRequest(), transitionCreate);
                transitionCreate.transition();
                eventListener.transitionEnd(errorResult.getRequest(), transitionCreate);
            } else if (target != null) {
                target.onError(result.getImage());
            }
        } else if (target != null) {
            target.onError(result.getImage());
        }
        eventListener.onError(request, result);
        ImageRequest.Listener listener = request.getListener();
        if (listener != null) {
            listener.onError(request, result);
        }
    }

    private final void onCancel(ImageRequest request, EventListener eventListener) {
        Logger logger = this.options.getLogger();
        if (logger != null) {
            Logger.Level level = Logger.Level.Info;
            if (logger.getMinLevel().compareTo(level) <= 0) {
                logger.log("RealImageLoader", level, "🏗 Cancelled - " + request.getData(), null);
            }
        }
        eventListener.onCancel(request);
        ImageRequest.Listener listener = request.getListener();
        if (listener != null) {
            listener.onCancel(request);
        }
    }

    /* JADX INFO: compiled from: RealImageLoader.kt */
    @Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001Bc\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\u000e\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\b\u0012\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\b\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\u0004\b\u0014\u0010\u0015J\u0012\u0010%\u001a\u00060\u0003j\u0002`\u0004HÆ\u0003¢\u0006\u0002\u0010\u0017J\t\u0010&\u001a\u00020\u0006HÆ\u0003J\u000f\u0010'\u001a\b\u0012\u0004\u0012\u00020\t0\bHÆ\u0003J\u0011\u0010(\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\bHÆ\u0003J\u0011\u0010)\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\bHÆ\u0003J\t\u0010*\u001a\u00020\u000fHÆ\u0003J\t\u0010+\u001a\u00020\u0011HÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u0013HÆ\u0003Jz\u0010-\u001a\u00020\u00002\f\b\u0002\u0010\u0002\u001a\u00060\u0003j\u0002`\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0010\b\u0002\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\b2\u0010\b\u0002\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\b2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÆ\u0001¢\u0006\u0002\u0010.J\u0013\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00102\u001a\u000203HÖ\u0001J\t\u00104\u001a\u000205HÖ\u0001R\u0017\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0019\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\b¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001cR\u0019\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\b¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001cR\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$¨\u00066"}, d2 = {"Lcoil3/RealImageLoader$Options;", "", "application", "Landroid/content/Context;", "Lcoil3/PlatformContext;", "defaults", "Lcoil3/request/ImageRequest$Defaults;", "mainCoroutineContextLazy", "Lkotlin/Lazy;", "Lkotlin/coroutines/CoroutineContext;", "memoryCacheLazy", "Lcoil3/memory/MemoryCache;", "diskCacheLazy", "Lcoil3/disk/DiskCache;", "eventListenerFactory", "Lcoil3/EventListener$Factory;", "componentRegistry", "Lcoil3/ComponentRegistry;", "logger", "Lcoil3/util/Logger;", "<init>", "(Landroid/content/Context;Lcoil3/request/ImageRequest$Defaults;Lkotlin/Lazy;Lkotlin/Lazy;Lkotlin/Lazy;Lcoil3/EventListener$Factory;Lcoil3/ComponentRegistry;Lcoil3/util/Logger;)V", "getApplication", "()Landroid/content/Context;", "Landroid/content/Context;", "getDefaults", "()Lcoil3/request/ImageRequest$Defaults;", "getMainCoroutineContextLazy", "()Lkotlin/Lazy;", "getMemoryCacheLazy", "getDiskCacheLazy", "getEventListenerFactory", "()Lcoil3/EventListener$Factory;", "getComponentRegistry", "()Lcoil3/ComponentRegistry;", "getLogger", "()Lcoil3/util/Logger;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(Landroid/content/Context;Lcoil3/request/ImageRequest$Defaults;Lkotlin/Lazy;Lkotlin/Lazy;Lkotlin/Lazy;Lcoil3/EventListener$Factory;Lcoil3/ComponentRegistry;Lcoil3/util/Logger;)Lcoil3/RealImageLoader$Options;", "equals", "", "other", "hashCode", "", "toString", "", "coil-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Options {
        private final Context application;
        private final ComponentRegistry componentRegistry;
        private final ImageRequest.Defaults defaults;
        private final Lazy<DiskCache> diskCacheLazy;
        private final EventListener.Factory eventListenerFactory;
        private final Logger logger;
        private final Lazy<CoroutineContext> mainCoroutineContextLazy;
        private final Lazy<MemoryCache> memoryCacheLazy;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Options copy$default(Options options, Context context, ImageRequest.Defaults defaults, Lazy lazy, Lazy lazy2, Lazy lazy3, EventListener.Factory factory, ComponentRegistry componentRegistry, Logger logger, int i, Object obj) {
            if ((i & 1) != 0) {
                context = options.application;
            }
            if ((i & 2) != 0) {
                defaults = options.defaults;
            }
            if ((i & 4) != 0) {
                lazy = options.mainCoroutineContextLazy;
            }
            if ((i & 8) != 0) {
                lazy2 = options.memoryCacheLazy;
            }
            if ((i & 16) != 0) {
                lazy3 = options.diskCacheLazy;
            }
            if ((i & 32) != 0) {
                factory = options.eventListenerFactory;
            }
            if ((i & 64) != 0) {
                componentRegistry = options.componentRegistry;
            }
            if ((i & 128) != 0) {
                logger = options.logger;
            }
            ComponentRegistry componentRegistry2 = componentRegistry;
            Logger logger2 = logger;
            Lazy lazy4 = lazy3;
            EventListener.Factory factory2 = factory;
            return options.copy(context, defaults, lazy, lazy2, lazy4, factory2, componentRegistry2, logger2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Context getApplication() {
            return this.application;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final ImageRequest.Defaults getDefaults() {
            return this.defaults;
        }

        public final Lazy<CoroutineContext> component3() {
            return this.mainCoroutineContextLazy;
        }

        public final Lazy<MemoryCache> component4() {
            return this.memoryCacheLazy;
        }

        public final Lazy<DiskCache> component5() {
            return this.diskCacheLazy;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final EventListener.Factory getEventListenerFactory() {
            return this.eventListenerFactory;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final ComponentRegistry getComponentRegistry() {
            return this.componentRegistry;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final Logger getLogger() {
            return this.logger;
        }

        public final Options copy(Context application, ImageRequest.Defaults defaults, Lazy<? extends CoroutineContext> mainCoroutineContextLazy, Lazy<? extends MemoryCache> memoryCacheLazy, Lazy<? extends DiskCache> diskCacheLazy, EventListener.Factory eventListenerFactory, ComponentRegistry componentRegistry, Logger logger) {
            return new Options(application, defaults, mainCoroutineContextLazy, memoryCacheLazy, diskCacheLazy, eventListenerFactory, componentRegistry, logger);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Options)) {
                return false;
            }
            Options options = (Options) other;
            return Intrinsics.areEqual(this.application, options.application) && Intrinsics.areEqual(this.defaults, options.defaults) && Intrinsics.areEqual(this.mainCoroutineContextLazy, options.mainCoroutineContextLazy) && Intrinsics.areEqual(this.memoryCacheLazy, options.memoryCacheLazy) && Intrinsics.areEqual(this.diskCacheLazy, options.diskCacheLazy) && Intrinsics.areEqual(this.eventListenerFactory, options.eventListenerFactory) && Intrinsics.areEqual(this.componentRegistry, options.componentRegistry) && Intrinsics.areEqual(this.logger, options.logger);
        }

        public int hashCode() {
            int iHashCode = ((((((((((((this.application.hashCode() * 31) + this.defaults.hashCode()) * 31) + this.mainCoroutineContextLazy.hashCode()) * 31) + this.memoryCacheLazy.hashCode()) * 31) + this.diskCacheLazy.hashCode()) * 31) + this.eventListenerFactory.hashCode()) * 31) + this.componentRegistry.hashCode()) * 31;
            Logger logger = this.logger;
            return iHashCode + (logger == null ? 0 : logger.hashCode());
        }

        public String toString() {
            return "Options(application=" + this.application + ", defaults=" + this.defaults + ", mainCoroutineContextLazy=" + this.mainCoroutineContextLazy + ", memoryCacheLazy=" + this.memoryCacheLazy + ", diskCacheLazy=" + this.diskCacheLazy + ", eventListenerFactory=" + this.eventListenerFactory + ", componentRegistry=" + this.componentRegistry + ", logger=" + this.logger + ')';
        }

        /* JADX WARN: Multi-variable type inference failed */
        public Options(Context context, ImageRequest.Defaults defaults, Lazy<? extends CoroutineContext> lazy, Lazy<? extends MemoryCache> lazy2, Lazy<? extends DiskCache> lazy3, EventListener.Factory factory, ComponentRegistry componentRegistry, Logger logger) {
            this.application = context;
            this.defaults = defaults;
            this.mainCoroutineContextLazy = lazy;
            this.memoryCacheLazy = lazy2;
            this.diskCacheLazy = lazy3;
            this.eventListenerFactory = factory;
            this.componentRegistry = componentRegistry;
            this.logger = logger;
        }

        public final Context getApplication() {
            return this.application;
        }

        public final ImageRequest.Defaults getDefaults() {
            return this.defaults;
        }

        public final Lazy<CoroutineContext> getMainCoroutineContextLazy() {
            return this.mainCoroutineContextLazy;
        }

        public final Lazy<MemoryCache> getMemoryCacheLazy() {
            return this.memoryCacheLazy;
        }

        public final Lazy<DiskCache> getDiskCacheLazy() {
            return this.diskCacheLazy;
        }

        public final EventListener.Factory getEventListenerFactory() {
            return this.eventListenerFactory;
        }

        public final ComponentRegistry getComponentRegistry() {
            return this.componentRegistry;
        }

        public final Logger getLogger() {
            return this.logger;
        }
    }
}
