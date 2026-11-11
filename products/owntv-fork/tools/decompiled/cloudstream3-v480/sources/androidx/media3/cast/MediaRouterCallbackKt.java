package androidx.media3.cast;

import androidx.mediarouter.media.MediaRouteSelector;
import androidx.mediarouter.media.MediaRouter;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: MediaRouterCallback.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u001a(\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0080@¢\u0006\u0002\u0010\b\u001a(\u0010\t\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0082@¢\u0006\u0002\u0010\b¨\u0006\n"}, d2 = {"observeCallback", "", "Landroidx/mediarouter/media/MediaRouter;", "selector", "Landroidx/mediarouter/media/MediaRouteSelector;", "onEvent", "Lkotlin/Function0;", "", "(Landroidx/mediarouter/media/MediaRouter;Landroidx/mediarouter/media/MediaRouteSelector;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeCallbackImpl", "lib-cast_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class MediaRouterCallbackKt {

    /* JADX INFO: renamed from: androidx.media3.cast.MediaRouterCallbackKt$observeCallback$1, reason: invalid class name */
    /* JADX INFO: compiled from: MediaRouterCallback.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.media3.cast.MediaRouterCallbackKt", f = "MediaRouterCallback.kt", i = {}, l = {45, 47}, m = "observeCallback", n = {}, s = {})
    static final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MediaRouterCallbackKt.observeCallback(null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.media3.cast.MediaRouterCallbackKt$observeCallbackImpl$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MediaRouterCallback.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.media3.cast.MediaRouterCallbackKt", f = "MediaRouterCallback.kt", i = {0, 0, 0, 0}, l = {142}, m = "observeCallbackImpl", n = {"$this$observeCallbackImpl", "selector", "onEvent", "callback"}, s = {"L$0", "L$1", "L$2", "L$3"})
    static final class C06381 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        C06381(Continuation<? super C06381> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MediaRouterCallbackKt.observeCallbackImpl(null, null, null, this);
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0050, code lost:
    
        if (observeCallbackImpl(r5, r6, r7, r0) == r1) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x007a, code lost:
    
        if (kotlinx.coroutines.BuildersKt.withContext(r8, r4, r0) == r1) goto L25;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object observeCallback(androidx.mediarouter.media.MediaRouter r5, androidx.mediarouter.media.MediaRouteSelector r6, kotlin.jvm.functions.Function0<kotlin.Unit> r7, kotlin.coroutines.Continuation<?> r8) {
        /*
            boolean r0 = r8 instanceof androidx.media3.cast.MediaRouterCallbackKt.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r8
            androidx.media3.cast.MediaRouterCallbackKt$observeCallback$1 r0 = (androidx.media3.cast.MediaRouterCallbackKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            androidx.media3.cast.MediaRouterCallbackKt$observeCallback$1 r0 = new androidx.media3.cast.MediaRouterCallbackKt$observeCallback$1
            r0.<init>(r8)
        L19:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L39
            if (r2 == r4) goto L35
            if (r2 == r3) goto L31
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L31:
            kotlin.ResultKt.throwOnFailure(r8)
            goto L7d
        L35:
            kotlin.ResultKt.throwOnFailure(r8)
            goto L53
        L39:
            kotlin.ResultKt.throwOnFailure(r8)
            android.os.Looper r8 = android.os.Looper.myLooper()
            android.os.Looper r2 = android.os.Looper.getMainLooper()
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual(r8, r2)
            if (r8 == 0) goto L59
            r0.label = r4
            java.lang.Object r5 = observeCallbackImpl(r5, r6, r7, r0)
            if (r5 != r1) goto L53
            goto L7c
        L53:
            kotlin.KotlinNothingValueException r5 = new kotlin.KotlinNothingValueException
            r5.<init>()
            throw r5
        L59:
            android.os.Looper r8 = android.os.Looper.getMainLooper()
            android.os.Handler r8 = androidx.core.os.HandlerCompat.createAsync(r8)
            java.lang.String r2 = "createAsync(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r2)
            r2 = 0
            kotlinx.coroutines.android.HandlerDispatcher r8 = kotlinx.coroutines.android.HandlerDispatcherKt.from$default(r8, r2, r4, r2)
            kotlin.coroutines.CoroutineContext r8 = (kotlin.coroutines.CoroutineContext) r8
            androidx.media3.cast.MediaRouterCallbackKt$observeCallback$2 r4 = new androidx.media3.cast.MediaRouterCallbackKt$observeCallback$2
            r4.<init>(r5, r6, r7, r2)
            kotlin.jvm.functions.Function2 r4 = (kotlin.jvm.functions.Function2) r4
            r0.label = r3
            java.lang.Object r5 = kotlinx.coroutines.BuildersKt.withContext(r8, r4, r0)
            if (r5 != r1) goto L7d
        L7c:
            return r1
        L7d:
            kotlin.KotlinNothingValueException r5 = new kotlin.KotlinNothingValueException
            r5.<init>()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.cast.MediaRouterCallbackKt.observeCallback(androidx.mediarouter.media.MediaRouter, androidx.mediarouter.media.MediaRouteSelector, kotlin.jvm.functions.Function0, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: androidx.media3.cast.MediaRouterCallbackKt$observeCallback$2, reason: invalid class name */
    /* JADX INFO: compiled from: MediaRouterCallback.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0001\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.media3.cast.MediaRouterCallbackKt$observeCallback$2", f = "MediaRouterCallback.kt", i = {}, l = {48}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<?>, Object> {
        final /* synthetic */ Function0<Unit> $onEvent;
        final /* synthetic */ MediaRouteSelector $selector;
        final /* synthetic */ MediaRouter $this_observeCallback;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(MediaRouter mediaRouter, MediaRouteSelector mediaRouteSelector, Function0<Unit> function0, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$this_observeCallback = mediaRouter;
            this.$selector = mediaRouteSelector;
            this.$onEvent = function0;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$this_observeCallback, this.$selector, this.$onEvent, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<?> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (MediaRouterCallbackKt.observeCallbackImpl(this.$this_observeCallback, this.$selector, this.$onEvent, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            throw new KotlinNothingValueException();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:33:0x00af  */
    /* JADX WARN: Code duplicated, block: B:34:0x00b3  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v1, types: [T, androidx.media3.cast.MediaRouterCallback] */
    public static final Object observeCallbackImpl(MediaRouter mediaRouter, MediaRouteSelector mediaRouteSelector, Function0<Unit> function0, Continuation<?> continuation) throws Throwable {
        C06381 c06381;
        final Ref.ObjectRef objectRef;
        MediaRouterCallback mediaRouterCallback;
        Throwable th;
        if (continuation instanceof C06381) {
            c06381 = (C06381) continuation;
            if ((c06381.label & Integer.MIN_VALUE) != 0) {
                c06381.label -= Integer.MIN_VALUE;
            } else {
                c06381 = new C06381(continuation);
            }
        } else {
            c06381 = new C06381(continuation);
        }
        Object obj = c06381.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c06381.label;
        MediaRouterCallback mediaRouterCallback2 = null;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            objectRef = new Ref.ObjectRef();
            try {
                c06381.L$0 = mediaRouter;
                c06381.L$1 = mediaRouteSelector;
                c06381.L$2 = function0;
                c06381.L$3 = objectRef;
                c06381.label = 1;
                CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(c06381), 1);
                cancellableContinuationImpl.initCancellability();
                CancellableContinuationImpl cancellableContinuationImpl2 = cancellableContinuationImpl;
                objectRef.element = new MediaRouterCallback(function0, cancellableContinuationImpl2);
                cancellableContinuationImpl2.invokeOnCancellation(new Function1<Throwable, Unit>() { // from class: androidx.media3.cast.MediaRouterCallbackKt$observeCallbackImpl$2$1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Throwable th2) {
                        invoke2(th2);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Throwable th2) {
                        MediaRouterCallback mediaRouterCallback3;
                        if (objectRef.element == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("callback");
                            mediaRouterCallback3 = null;
                        } else {
                            mediaRouterCallback3 = objectRef.element;
                        }
                        mediaRouterCallback3.getIsCancelled().set(true);
                    }
                });
                if (objectRef.element == 0) {
                    Intrinsics.throwUninitializedPropertyAccessException("callback");
                    mediaRouterCallback = null;
                } else {
                    mediaRouterCallback = (MediaRouterCallback) objectRef.element;
                }
                mediaRouter.addCallback(mediaRouteSelector, mediaRouterCallback);
                Object result = cancellableContinuationImpl.getResult();
                if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    DebugProbesKt.probeCoroutineSuspended(c06381);
                }
                if (result == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } catch (Throwable th2) {
                th = th2;
                if (objectRef.element == 0) {
                    Intrinsics.throwUninitializedPropertyAccessException("callback");
                } else {
                    mediaRouterCallback2 = (MediaRouterCallback) objectRef.element;
                }
                mediaRouter.removeCallback(mediaRouterCallback2);
                throw th;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            Ref.ObjectRef objectRef2 = (Ref.ObjectRef) c06381.L$3;
            MediaRouter mediaRouter2 = (MediaRouter) c06381.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                objectRef = objectRef2;
                mediaRouter = mediaRouter2;
            } catch (Throwable th3) {
                th = th3;
                objectRef = objectRef2;
                mediaRouter = mediaRouter2;
                if (objectRef.element == 0) {
                    Intrinsics.throwUninitializedPropertyAccessException("callback");
                } else {
                    mediaRouterCallback2 = (MediaRouterCallback) objectRef.element;
                }
                mediaRouter.removeCallback(mediaRouterCallback2);
                throw th;
            }
        }
        throw new KotlinNothingValueException();
    }
}
