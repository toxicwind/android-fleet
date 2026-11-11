package androidx.core.content;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;
import androidx.exifinterface.media.ExifInterface;
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
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import org.mozilla.universalchardet.prober.HebrewProber;

/* JADX INFO: compiled from: Context.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000t\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a \u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002*\u00020\u0003H\u0086\b¢\u0006\u0002\u0010\u0004\u001aN\u0010\u0005\u001a\u00020\u0006*\u00020\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0003\u0010\u000b\u001a\u00020\f2\b\b\u0003\u0010\r\u001a\u00020\f2\u0017\u0010\u000e\u001a\u0013\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00060\u000f¢\u0006\u0002\b\u0011H\u0086\b\u001a8\u0010\u0005\u001a\u00020\u0006*\u00020\u00032\b\b\u0001\u0010\u0012\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\n2\u0017\u0010\u000e\u001a\u0013\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00060\u000f¢\u0006\u0002\b\u0011H\u0086\b\u001a[\u0010\u0013\u001a\u00020\u0014*\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\f2\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u001f\u0010\u001c\u001a\u001b\u0012\u0004\u0012\u00020\u001e\u0012\u0006\u0012\u0004\u0018\u00010\u001f\u0012\u0004\u0012\u00020\u00060\u001d¢\u0006\u0002\b\u0011H\u0086@¢\u0006\u0002\u0010 \u001ak\u0010!\u001a\u00020\u0014*\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\f2\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001b2/\u0010\u001c\u001a+\b\u0001\u0012\u0004\u0012\u00020#\u0012\u0006\u0012\u0004\u0018\u00010\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060$\u0012\u0006\u0012\u0004\u0018\u00010\u00020\"¢\u0006\u0002\b\u0011H\u0086@¢\u0006\u0002\u0010%¨\u0006&"}, d2 = {"getSystemService", ExifInterface.GPS_DIRECTION_TRUE, "", "Landroid/content/Context;", "(Landroid/content/Context;)Ljava/lang/Object;", "withStyledAttributes", "", "set", "Landroid/util/AttributeSet;", "attrs", "", "defStyleAttr", "", "defStyleRes", "block", "Lkotlin/Function1;", "Landroid/content/res/TypedArray;", "Lkotlin/ExtensionFunctionType;", "resourceId", "receiveBroadcasts", "", "filter", "Landroid/content/IntentFilter;", "flags", "broadcastPermission", "", "scheduler", "Landroid/os/Handler;", "onReceive", "Lkotlin/Function2;", "Landroid/content/BroadcastReceiver;", "Landroid/content/Intent;", "(Landroid/content/Context;Landroid/content/IntentFilter;ILjava/lang/String;Landroid/os/Handler;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "receiveBroadcastsAsync", "Lkotlin/Function3;", "Landroid/content/BroadcastReceiver$PendingResult;", "Lkotlin/coroutines/Continuation;", "(Landroid/content/Context;Landroid/content/IntentFilter;ILjava/lang/String;Landroid/os/Handler;Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "core-ktx"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class ContextKt {

    /* JADX INFO: renamed from: androidx.core.content.ContextKt$receiveBroadcasts$1, reason: invalid class name */
    /* JADX INFO: compiled from: Context.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.core.content.ContextKt", f = "Context.kt", i = {0, 0, 0, 0, 0, 0, 0}, l = {279}, m = "receiveBroadcasts", n = {"$this$receiveBroadcasts", "filter", "broadcastPermission", "scheduler", "onReceive", "receiver", "flags"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "I$0"}, v = 1)
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ContextKt.receiveBroadcasts(null, null, 0, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.core.content.ContextKt$receiveBroadcastsAsync$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Context.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.core.content.ContextKt", f = "Context.kt", i = {}, l = {HebrewProber.FINAL_MEM}, m = "receiveBroadcastsAsync", n = {}, s = {}, v = 1)
    static final class C06151 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C06151(Continuation<? super C06151> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ContextKt.receiveBroadcastsAsync(null, null, 0, null, null, null, this);
        }
    }

    public static final /* synthetic */ <T> T getSystemService(Context context) {
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return (T) ContextCompat.getSystemService(context, Object.class);
    }

    public static final void withStyledAttributes(Context context, AttributeSet attributeSet, int[] iArr, int i, int i2, Function1<? super TypedArray, Unit> function1) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i, i2);
        function1.invoke(typedArrayObtainStyledAttributes);
        typedArrayObtainStyledAttributes.recycle();
    }

    public static /* synthetic */ void withStyledAttributes$default(Context context, AttributeSet attributeSet, int[] iArr, int i, int i2, Function1 function1, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            attributeSet = null;
        }
        if ((i3 & 4) != 0) {
            i = 0;
        }
        if ((i3 & 8) != 0) {
            i2 = 0;
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i, i2);
        function1.invoke(typedArrayObtainStyledAttributes);
        typedArrayObtainStyledAttributes.recycle();
    }

    public static final void withStyledAttributes(Context context, int i, int[] iArr, Function1<? super TypedArray, Unit> function1) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i, iArr);
        function1.invoke(typedArrayObtainStyledAttributes);
        typedArrayObtainStyledAttributes.recycle();
    }

    /* JADX WARN: Code duplicated, block: B:30:0x00b7  */
    /* JADX WARN: Code duplicated, block: B:36:? A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v1, types: [T, androidx.core.content.ContinuationBroadcastReceiver] */
    public static final Object receiveBroadcasts(Context context, IntentFilter intentFilter, int i, String str, Handler handler, Function2<? super BroadcastReceiver, ? super Intent, Unit> function2, Continuation<?> continuation) throws Throwable {
        AnonymousClass1 anonymousClass1;
        Ref.ObjectRef objectRef;
        Throwable th;
        Context context2;
        Ref.ObjectRef objectRef2;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        Object obj = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = anonymousClass1.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            objectRef = new Ref.ObjectRef();
            try {
                anonymousClass1.L$0 = context;
                anonymousClass1.L$1 = intentFilter;
                anonymousClass1.L$2 = str;
                anonymousClass1.L$3 = handler;
                anonymousClass1.L$4 = function2;
                anonymousClass1.L$5 = objectRef;
                anonymousClass1.I$0 = i;
                anonymousClass1.label = 1;
                CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(anonymousClass1), 1);
                cancellableContinuationImpl.initCancellability();
                objectRef.element = new ContinuationBroadcastReceiver(cancellableContinuationImpl, function2);
                ContextCompat.registerReceiver(context, (BroadcastReceiver) objectRef.element, intentFilter, str, handler, i);
                Object result = cancellableContinuationImpl.getResult();
                if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    DebugProbesKt.probeCoroutineSuspended(anonymousClass1);
                }
                if (result == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } catch (Throwable th2) {
                th = th2;
                context2 = context;
                objectRef2 = objectRef;
                if (objectRef2.element != 0) {
                    throw th;
                }
                context2.unregisterReceiver((BroadcastReceiver) objectRef2.element);
                throw th;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i3 = anonymousClass1.I$0;
            objectRef2 = (Ref.ObjectRef) anonymousClass1.L$5;
            context2 = (Context) anonymousClass1.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                objectRef = objectRef2;
                context = context2;
            } catch (Throwable th3) {
                th = th3;
                if (objectRef2.element != 0) {
                    throw th;
                }
                context2.unregisterReceiver((BroadcastReceiver) objectRef2.element);
                throw th;
            }
        }
        throw new KotlinNothingValueException();
    }

    public static /* synthetic */ Object receiveBroadcasts$default(Context context, IntentFilter intentFilter, int i, String str, Handler handler, Function2 function2, Continuation continuation, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            str = null;
        }
        if ((i2 & 8) != 0) {
            handler = null;
        }
        return receiveBroadcasts(context, intentFilter, i, str, handler, function2, continuation);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    public static final Object receiveBroadcastsAsync(Context context, IntentFilter intentFilter, int i, String str, Handler handler, Function3<? super BroadcastReceiver.PendingResult, ? super Intent, ? super Continuation<? super Unit>, ? extends Object> function3, Continuation<?> continuation) {
        C06151 c06151;
        if (continuation instanceof C06151) {
            c06151 = (C06151) continuation;
            if ((c06151.label & Integer.MIN_VALUE) != 0) {
                c06151.label -= Integer.MIN_VALUE;
            } else {
                c06151 = new C06151(continuation);
            }
        } else {
            c06151 = new C06151(continuation);
        }
        Object obj = c06151.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c06151.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(context, intentFilter, i, str, handler, function3, null);
            c06151.label = 1;
            if (CoroutineScopeKt.coroutineScope(anonymousClass2, c06151) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        throw new KotlinNothingValueException();
    }

    public static /* synthetic */ Object receiveBroadcastsAsync$default(Context context, IntentFilter intentFilter, int i, String str, Handler handler, Function3 function3, Continuation continuation, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            str = null;
        }
        if ((i2 & 8) != 0) {
            handler = null;
        }
        return receiveBroadcastsAsync(context, intentFilter, i, str, handler, function3, continuation);
    }

    /* JADX INFO: renamed from: androidx.core.content.ContextKt$receiveBroadcastsAsync$2, reason: invalid class name */
    /* JADX INFO: compiled from: Context.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0001\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.core.content.ContextKt$receiveBroadcastsAsync$2", f = "Context.kt", i = {}, l = {HebrewProber.NORMAL_MEM}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<?>, Object> {
        final /* synthetic */ String $broadcastPermission;
        final /* synthetic */ IntentFilter $filter;
        final /* synthetic */ int $flags;
        final /* synthetic */ Function3<BroadcastReceiver.PendingResult, Intent, Continuation<? super Unit>, Object> $onReceive;
        final /* synthetic */ Handler $scheduler;
        final /* synthetic */ Context $this_receiveBroadcastsAsync;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass2(Context context, IntentFilter intentFilter, int i, String str, Handler handler, Function3<? super BroadcastReceiver.PendingResult, ? super Intent, ? super Continuation<? super Unit>, ? extends Object> function3, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$this_receiveBroadcastsAsync = context;
            this.$filter = intentFilter;
            this.$flags = i;
            this.$broadcastPermission = str;
            this.$scheduler = handler;
            this.$onReceive = function3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$this_receiveBroadcastsAsync, this.$filter, this.$flags, this.$broadcastPermission, this.$scheduler, this.$onReceive, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
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
                final CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                Context context = this.$this_receiveBroadcastsAsync;
                IntentFilter intentFilter = this.$filter;
                int i2 = this.$flags;
                String str = this.$broadcastPermission;
                Handler handler = this.$scheduler;
                final Function3<BroadcastReceiver.PendingResult, Intent, Continuation<? super Unit>, Object> function3 = this.$onReceive;
                this.label = 1;
                if (ContextKt.receiveBroadcasts(context, intentFilter, i2, str, handler, new Function2() { // from class: androidx.core.content.ContextKt$receiveBroadcastsAsync$2$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj2, Object obj3) {
                        return ContextKt.AnonymousClass2.invokeSuspend$lambda$0(coroutineScope, function3, (BroadcastReceiver) obj2, (Intent) obj3);
                    }
                }, this) == coroutine_suspended) {
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

        static final Unit invokeSuspend$lambda$0(CoroutineScope coroutineScope, Function3 function3, BroadcastReceiver broadcastReceiver, Intent intent) {
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, CoroutineStart.ATOMIC, new ContextKt$receiveBroadcastsAsync$2$1$1(function3, broadcastReceiver.goAsync(), intent, null), 1, null);
            return Unit.INSTANCE;
        }
    }
}
