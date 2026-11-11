package kotlinx.coroutines.channels;

import androidx.exifinterface.media.ExifInterface;
import java.util.Collection;
import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.ExceptionsKt;
import kotlinx.coroutines.selects.SelectClause1;

/* JADX INFO: compiled from: Channels.common.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000H\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u001f\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0000\u001a$\u0010\u0002\u001a\u0004\u0018\u0001H\u0003\"\b\b\u0000\u0010\u0003*\u00020\u0004*\b\u0012\u0004\u0012\u0002H\u00030\u0005H\u0087@¢\u0006\u0002\u0010\u0006\u001a$\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00030\b\"\b\b\u0000\u0010\u0003*\u00020\u0004*\b\u0012\u0004\u0012\u0002H\u00030\u0005H\u0007\u001aP\u0010\t\u001a\u0002H\n\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\n*\b\u0012\u0004\u0012\u0002H\u00030\u00052\u001d\u0010\u000b\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0005\u0012\u0004\u0012\u0002H\n0\f¢\u0006\u0002\b\rH\u0086\b\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0002\u0010\u000e\u001a2\u0010\u000f\u001a\u00020\u0010\"\u0004\b\u0000\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u00052\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u00020\u00100\fH\u0086H¢\u0006\u0002\u0010\u0012\u001a$\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00030\u0014\"\u0004\b\u0000\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u0005H\u0086@¢\u0006\u0002\u0010\u0006\u001a6\u0010\u0015\u001a\u0002H\u0016\"\u0004\b\u0000\u0010\u0003\"\u000e\b\u0001\u0010\u0016*\b\u0012\u0004\u0012\u0002H\u00030\u0017*\b\u0012\u0004\u0012\u0002H\u00030\u00052\u0006\u0010\u0018\u001a\u0002H\u0016H\u0086@¢\u0006\u0002\u0010\u0019\u001a\u001a\u0010\u001a\u001a\u00020\u0010*\u0006\u0012\u0002\b\u00030\u00052\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0001\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"DEFAULT_CLOSE_MESSAGE", "", "receiveOrNull", ExifInterface.LONGITUDE_EAST, "", "Lkotlinx/coroutines/channels/ReceiveChannel;", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onReceiveOrNull", "Lkotlinx/coroutines/selects/SelectClause1;", "consume", "R", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "consumeEach", "", "action", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toList", "", "consumeTo", "C", "", "collection", "(Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/util/Collection;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "cancelConsumed", "cause", "", "kotlinx-coroutines-core"}, k = 5, mv = {2, 2, 0}, xi = 48, xs = "kotlinx/coroutines/channels/ChannelsKt")
final /* synthetic */ class ChannelsKt__Channels_commonKt {

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$consumeEach$1, reason: invalid class name */
    /* JADX INFO: compiled from: Channels.common.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 176)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt", f = "Channels.common.kt", i = {0, 0, 0, 0, 0, 0, 0}, l = {157}, m = "consumeEach", n = {"$this$consumeEach", "action", "$this$consume$iv", "$this$consumeEach_u24lambda_u240", "$i$f$consumeEach", "$i$f$consume", "$i$a$-consume-ChannelsKt__Channels_commonKt$consumeEach$2"}, s = {"L$0", "L$1", "L$2", "L$3", "I$0", "I$1", "I$2"}, v = 1)
    static final class AnonymousClass1<E> extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__Channels_commonKt.consumeEach(null, null, this);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$consumeTo$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Channels.common.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt", f = "Channels.common.kt", i = {0, 0, 0, 0, 0, 0, 0, 0}, l = {277}, m = "consumeTo", n = {"$this$consumeTo", "collection", "$this$consumeEach$iv", "$this$consume$iv$iv", "$this$consumeEach_u24lambda_u240$iv", "$i$f$consumeEach", "$i$f$consume", "$i$a$-consume-ChannelsKt__Channels_commonKt$consumeEach$2$iv"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "I$0", "I$1", "I$2"}, v = 1)
    static final class C10691<E, C extends Collection<E>> extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        C10691(Continuation<? super C10691> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt.consumeTo(null, null, this);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$toList$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Channels.common.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt", f = "Channels.common.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, l = {277}, m = "toList", n = {"$this$toList", "$this$toList_u24lambda_u240", "$this$consumeEach$iv", "$completion$iv", "$this$consume$iv$iv", "$this$consumeEach_u24lambda_u240$iv", "$i$a$-buildList-ChannelsKt__Channels_commonKt$toList$2", "$i$f$consumeEach", "$i$f$consume", "$i$a$-consume-ChannelsKt__Channels_commonKt$consumeEach$2$iv"}, s = {"L$0", "L$2", "L$3", "L$4", "L$5", "L$6", "I$0", "I$1", "I$2", "I$3"}, v = 1)
    static final class C10701<E> extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        int label;
        /* synthetic */ Object result;

        C10701(Continuation<? super C10701> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt.toList(null, this);
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Deprecated in the favour of 'receiveCatching'", replaceWith = @ReplaceWith(expression = "receiveCatching().getOrNull()", imports = {}))
    public static final /* synthetic */ Object receiveOrNull(ReceiveChannel receiveChannel, Continuation continuation) {
        Intrinsics.checkNotNull(receiveChannel, "null cannot be cast to non-null type kotlinx.coroutines.channels.ReceiveChannel<E of kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.receiveOrNull?>");
        return receiveChannel.receiveOrNull(continuation);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Deprecated in the favour of 'onReceiveCatching'")
    public static final /* synthetic */ SelectClause1 onReceiveOrNull(ReceiveChannel receiveChannel) {
        Intrinsics.checkNotNull(receiveChannel, "null cannot be cast to non-null type kotlinx.coroutines.channels.ReceiveChannel<E of kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.onReceiveOrNull?>");
        return receiveChannel.getOnReceiveOrNull();
    }

    public static final <E, R> R consume(ReceiveChannel<? extends E> receiveChannel, Function1<? super ReceiveChannel<? extends E>, ? extends R> function1) {
        try {
            R rInvoke = function1.invoke(receiveChannel);
            ChannelsKt.cancelConsumed(receiveChannel, null);
            return rInvoke;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                ChannelsKt.cancelConsumed(receiveChannel, th);
                throw th2;
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:24:0x008b A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:25:0x008c  */
    /* JADX WARN: Code duplicated, block: B:28:0x009a A[Catch: all -> 0x00ae, TryCatch #2 {all -> 0x00ae, blocks: (B:26:0x0092, B:28:0x009a, B:22:0x006b, B:29:0x00a6), top: B:44:0x0092 }] */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x008c -> B:14:0x004d). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public static final <E> java.lang.Object consumeEach(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r11, kotlin.jvm.functions.Function1<? super E, kotlin.Unit> r12, kotlin.coroutines.Continuation<? super kotlin.Unit> r13) {
        /*
            boolean r0 = r13 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r13
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$consumeEach$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r13 = r0.label
            int r13 = r13 - r2
            r0.label = r13
            goto L19
        L14:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$consumeEach$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$consumeEach$1
            r0.<init>(r13)
        L19:
            java.lang.Object r13 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L5b
            if (r2 != r4) goto L53
            int r11 = r0.I$2
            int r12 = r0.I$1
            int r2 = r0.I$0
            java.lang.Object r5 = r0.L$4
            kotlinx.coroutines.channels.ChannelIterator r5 = (kotlinx.coroutines.channels.ChannelIterator) r5
            java.lang.Object r6 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r0.L$1
            kotlin.jvm.functions.Function1 r8 = (kotlin.jvm.functions.Function1) r8
            java.lang.Object r9 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            kotlin.ResultKt.throwOnFailure(r13)     // Catch: java.lang.Throwable -> L4f
            r10 = r2
            r2 = r11
            r11 = r9
            r9 = r5
            r5 = r0
            r0 = r8
            r8 = r3
            r3 = r12
            r12 = r7
        L4d:
            r7 = r10
            goto L92
        L4f:
            r11 = move-exception
            r12 = r7
            goto Lb4
        L53:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L5b:
            kotlin.ResultKt.throwOnFailure(r13)
            kotlinx.coroutines.channels.ChannelIterator r13 = r11.iterator()     // Catch: java.lang.Throwable -> Lb0
            r2 = 0
            r8 = r13
            r5 = r0
            r6 = r2
            r7 = r3
            r13 = r11
            r0 = r12
            r3 = r6
            r12 = r13
        L6b:
            java.lang.Object r9 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r11)     // Catch: java.lang.Throwable -> Lae
            r5.L$0 = r9     // Catch: java.lang.Throwable -> Lae
            r5.L$1 = r0     // Catch: java.lang.Throwable -> Lae
            r5.L$2 = r12     // Catch: java.lang.Throwable -> Lae
            java.lang.Object r9 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r13)     // Catch: java.lang.Throwable -> Lae
            r5.L$3 = r9     // Catch: java.lang.Throwable -> Lae
            r5.L$4 = r8     // Catch: java.lang.Throwable -> Lae
            r5.I$0 = r6     // Catch: java.lang.Throwable -> Lae
            r5.I$1 = r3     // Catch: java.lang.Throwable -> Lae
            r5.I$2 = r2     // Catch: java.lang.Throwable -> Lae
            r5.label = r4     // Catch: java.lang.Throwable -> Lae
            java.lang.Object r9 = r8.hasNext(r5)     // Catch: java.lang.Throwable -> Lae
            if (r9 != r1) goto L8c
            return r1
        L8c:
            r10 = r6
            r6 = r13
            r13 = r9
            r9 = r8
            r8 = r7
            goto L4d
        L92:
            java.lang.Boolean r13 = (java.lang.Boolean) r13     // Catch: java.lang.Throwable -> Lae
            boolean r13 = r13.booleanValue()     // Catch: java.lang.Throwable -> Lae
            if (r13 == 0) goto La6
            java.lang.Object r13 = r9.next()     // Catch: java.lang.Throwable -> Lae
            r0.invoke(r13)     // Catch: java.lang.Throwable -> Lae
            r13 = r6
            r6 = r7
            r7 = r8
            r8 = r9
            goto L6b
        La6:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> Lae
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r12, r8)
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        Lae:
            r11 = move-exception
            goto Lb4
        Lb0:
            r12 = move-exception
            r10 = r12
            r12 = r11
            r11 = r10
        Lb4:
            throw r11     // Catch: java.lang.Throwable -> Lb5
        Lb5:
            r13 = move-exception
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r12, r11)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.consumeEach(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:23:0x00b9 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:24:0x00ba  */
    /* JADX WARN: Code duplicated, block: B:27:0x00c9 A[Catch: all -> 0x00de, TryCatch #3 {all -> 0x00de, blocks: (B:25:0x00c1, B:27:0x00c9, B:21:0x0088, B:28:0x00d4), top: B:46:0x00c1 }] */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r14v1, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r14v2, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r14v5 */
    /* JADX WARN: Type inference failed for: r14v6 */
    /* JADX WARN: Type inference failed for: r14v7 */
    /* JADX WARN: Type inference failed for: r14v8 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x00ba -> B:46:0x00c1). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public static final <E> java.lang.Object toList(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r17, kotlin.coroutines.Continuation<? super java.util.List<? extends E>> r18) {
        /*
            Method dump skipped, instruction units count: 234
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.toList(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:23:0x0099 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:24:0x009a  */
    /* JADX WARN: Code duplicated, block: B:27:0x00a8 A[Catch: all -> 0x0056, TryCatch #1 {all -> 0x0056, blocks: (B:12:0x0045, B:25:0x00a0, B:27:0x00a8, B:28:0x00b4), top: B:41:0x0045 }] */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v3, types: [java.lang.Object, java.util.Collection] */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x009a -> B:25:0x00a0). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public static final <E, C extends java.util.Collection<E>> java.lang.Object consumeTo(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r12, C r13, kotlin.coroutines.Continuation<? super C> r14) {
        /*
            boolean r0 = r14 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.C10691
            if (r0 == 0) goto L14
            r0 = r14
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$consumeTo$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.C10691) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r14 = r0.label
            int r14 = r14 - r2
            r0.label = r14
            goto L19
        L14:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$consumeTo$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$consumeTo$1
            r0.<init>(r14)
        L19:
            java.lang.Object r14 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L62
            if (r2 != r4) goto L5a
            int r12 = r0.I$2
            int r13 = r0.I$1
            int r2 = r0.I$0
            java.lang.Object r5 = r0.L$5
            kotlinx.coroutines.channels.ChannelIterator r5 = (kotlinx.coroutines.channels.ChannelIterator) r5
            java.lang.Object r6 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r0.L$1
            java.util.Collection r9 = (java.util.Collection) r9
            java.lang.Object r10 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            kotlin.ResultKt.throwOnFailure(r14)     // Catch: java.lang.Throwable -> L56
            r11 = r2
            r2 = r12
            r12 = r10
            r10 = r5
            r5 = r13
            r13 = r8
            r8 = r11
            r11 = r6
            r6 = r0
            r0 = r11
            r11 = r9
            r9 = r3
            r3 = r11
            goto La0
        L56:
            r12 = move-exception
            r14 = r7
            goto Lc1
        L5a:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L62:
            kotlin.ResultKt.throwOnFailure(r14)
            kotlinx.coroutines.channels.ChannelIterator r14 = r12.iterator()     // Catch: java.lang.Throwable -> Lbe
            r2 = 0
            r9 = r14
            r6 = r0
            r5 = r2
            r7 = r5
            r8 = r3
            r14 = r12
            r0 = r14
            r3 = r13
            r13 = r0
        L73:
            java.lang.Object r10 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r12)     // Catch: java.lang.Throwable -> Lbc
            r6.L$0 = r10     // Catch: java.lang.Throwable -> Lbc
            r6.L$1 = r3     // Catch: java.lang.Throwable -> Lbc
            java.lang.Object r10 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r13)     // Catch: java.lang.Throwable -> Lbc
            r6.L$2 = r10     // Catch: java.lang.Throwable -> Lbc
            r6.L$3 = r14     // Catch: java.lang.Throwable -> Lbc
            java.lang.Object r10 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0)     // Catch: java.lang.Throwable -> Lbc
            r6.L$4 = r10     // Catch: java.lang.Throwable -> Lbc
            r6.L$5 = r9     // Catch: java.lang.Throwable -> Lbc
            r6.I$0 = r7     // Catch: java.lang.Throwable -> Lbc
            r6.I$1 = r5     // Catch: java.lang.Throwable -> Lbc
            r6.I$2 = r2     // Catch: java.lang.Throwable -> Lbc
            r6.label = r4     // Catch: java.lang.Throwable -> Lbc
            java.lang.Object r10 = r9.hasNext(r6)     // Catch: java.lang.Throwable -> Lbc
            if (r10 != r1) goto L9a
            return r1
        L9a:
            r11 = r7
            r7 = r14
            r14 = r10
            r10 = r9
            r9 = r8
            r8 = r11
        La0:
            java.lang.Boolean r14 = (java.lang.Boolean) r14     // Catch: java.lang.Throwable -> L56
            boolean r14 = r14.booleanValue()     // Catch: java.lang.Throwable -> L56
            if (r14 == 0) goto Lb4
            java.lang.Object r14 = r10.next()     // Catch: java.lang.Throwable -> L56
            r3.add(r14)     // Catch: java.lang.Throwable -> L56
            r14 = r7
            r7 = r8
            r8 = r9
            r9 = r10
            goto L73
        Lb4:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L56
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r7, r9)
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r3
        Lbc:
            r12 = move-exception
            goto Lc1
        Lbe:
            r13 = move-exception
            r14 = r12
            r12 = r13
        Lc1:
            throw r12     // Catch: java.lang.Throwable -> Lc2
        Lc2:
            r13 = move-exception
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r14, r12)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.consumeTo(kotlinx.coroutines.channels.ReceiveChannel, java.util.Collection, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final void cancelConsumed(ReceiveChannel<?> receiveChannel, Throwable th) {
        CancellationException CancellationException = null;
        if (th != null) {
            CancellationException = th instanceof CancellationException ? (CancellationException) th : null;
            if (CancellationException == null) {
                CancellationException = ExceptionsKt.CancellationException("Channel was consumed, consumer had failed", th);
            }
        }
        receiveChannel.cancel(CancellationException);
    }

    private static final <E> Object consumeEach$$forInline(ReceiveChannel<? extends E> receiveChannel, Function1<? super E, Unit> function1, Continuation<? super Unit> continuation) {
        try {
            ChannelIterator<? extends E> it = receiveChannel.iterator();
            while (((Boolean) it.hasNext(null)).booleanValue()) {
                function1.invoke(it.next());
            }
            Unit unit = Unit.INSTANCE;
            ChannelsKt.cancelConsumed(receiveChannel, null);
            return Unit.INSTANCE;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                ChannelsKt.cancelConsumed(receiveChannel, th);
                throw th2;
            }
        }
    }
}
