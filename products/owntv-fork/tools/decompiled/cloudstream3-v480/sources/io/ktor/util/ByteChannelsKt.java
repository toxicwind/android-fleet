package io.ktor.util;

import io.ktor.utils.io.ByteChannel;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ByteWriteChannel;
import io.ktor.utils.io.ByteWriteChannelOperationsKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: compiled from: ByteChannels.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\u001a%\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00000\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0005\u001a!\u0010\n\u001a\u00020\t*\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006¢\u0006\u0004\b\n\u0010\u000b\"\u0014\u0010\r\u001a\u00020\f8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lio/ktor/utils/io/ByteReadChannel;", "Lkotlinx/coroutines/CoroutineScope;", "coroutineScope", "Lkotlin/Pair;", "split", "(Lio/ktor/utils/io/ByteReadChannel;Lkotlinx/coroutines/CoroutineScope;)Lkotlin/Pair;", "Lio/ktor/utils/io/ByteWriteChannel;", "first", "second", "", "copyToBoth", "(Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/utils/io/ByteWriteChannel;Lio/ktor/utils/io/ByteWriteChannel;)V", "", "CHUNK_BUFFER_SIZE", "J", "ktor-utils"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ByteChannelsKt {
    private static final long CHUNK_BUFFER_SIZE = 4096;

    public static final Pair<ByteReadChannel, ByteReadChannel> split(ByteReadChannel byteReadChannel, CoroutineScope coroutineScope) {
        Intrinsics.checkNotNullParameter(byteReadChannel, "<this>");
        Intrinsics.checkNotNullParameter(coroutineScope, "coroutineScope");
        final ByteChannel byteChannel = new ByteChannel(true);
        final ByteChannel byteChannel2 = new ByteChannel(true);
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new C09861(byteReadChannel, byteChannel, byteChannel2, null), 3, null).invokeOnCompletion(new Function1() { // from class: io.ktor.util.ByteChannelsKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ByteChannelsKt.split$lambda$0(byteChannel, byteChannel2, (Throwable) obj);
            }
        });
        return TuplesKt.to(byteChannel, byteChannel2);
    }

    /* JADX INFO: renamed from: io.ktor.util.ByteChannelsKt$split$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ByteChannels.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.util.ByteChannelsKt$split$1", f = "ByteChannels.kt", i = {0, 0, 1, 1, 1}, l = {27, 32}, m = "invokeSuspend", n = {"$this$launch", "buffer", "$this$launch", "buffer", "read"}, s = {"L$0", "L$1", "L$0", "L$1", "I$0"}, v = 1)
    static final class C09861 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ ByteChannel $first;
        final /* synthetic */ ByteChannel $second;
        final /* synthetic */ ByteReadChannel $this_split;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C09861(ByteReadChannel byteReadChannel, ByteChannel byteChannel, ByteChannel byteChannel2, Continuation<? super C09861> continuation) {
            super(2, continuation);
            this.$this_split = byteReadChannel;
            this.$first = byteChannel;
            this.$second = byteChannel2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C09861 c09861 = new C09861(this.$this_split, this.$first, this.$second, continuation);
            c09861.L$0 = obj;
            return c09861;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C09861) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:21:0x004b A[Catch: all -> 0x00c5, TRY_ENTER, TryCatch #2 {all -> 0x00c5, blocks: (B:18:0x0041, B:21:0x004b, B:24:0x0060, B:26:0x0068, B:29:0x00ac, B:33:0x00c4), top: B:46:0x0041 }] */
        /* JADX WARN: Code duplicated, block: B:23:0x005f  */
        /* JADX WARN: Code duplicated, block: B:24:0x0060 A[Catch: all -> 0x00c5, PHI: r3 r11
          0x0060: PHI (r3v7 java.lang.Object) = (r3v3 java.lang.Object), (r3v21 java.lang.Object) binds: [B:13:0x002c, B:22:0x005d] A[DONT_GENERATE, DONT_INLINE]
          0x0060: PHI (r11v1 byte[]) = (r11v0 byte[]), (r11v2 byte[]) binds: [B:13:0x002c, B:22:0x005d] A[DONT_GENERATE, DONT_INLINE], TryCatch #2 {all -> 0x00c5, blocks: (B:18:0x0041, B:21:0x004b, B:24:0x0060, B:26:0x0068, B:29:0x00ac, B:33:0x00c4), top: B:46:0x0041 }] */
        /* JADX WARN: Code duplicated, block: B:26:0x0068 A[Catch: all -> 0x00c5, TryCatch #2 {all -> 0x00c5, blocks: (B:18:0x0041, B:21:0x004b, B:24:0x0060, B:26:0x0068, B:29:0x00ac, B:33:0x00c4), top: B:46:0x0041 }] */
        /* JADX WARN: Code duplicated, block: B:46:0x0041 A[EXC_TOP_SPLITTER, PHI: r11
          0x0041: PHI (r11v2 byte[]) = (r11v1 byte[]), (r11v1 byte[]), (r11v3 byte[]) binds: [B:25:0x0066, B:27:0x00a9, B:17:0x0040] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r3v0, types: [int] */
        /* JADX WARN: Type inference failed for: r3v18 */
        /* JADX WARN: Type inference failed for: r3v6, types: [java.lang.Object] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x0066 -> B:46:0x0041). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x00a9 -> B:46:0x0041). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r18) {
            /*
                Method dump skipped, instruction units count: 244
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.util.ByteChannelsKt.C09861.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* JADX INFO: renamed from: io.ktor.util.ByteChannelsKt$split$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: ByteChannels.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
        @DebugMetadata(c = "io.ktor.util.ByteChannelsKt$split$1$1", f = "ByteChannels.kt", i = {}, l = {30}, m = "invokeSuspend", n = {}, s = {}, v = 1)
        static final class C01811 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ byte[] $buffer;
            final /* synthetic */ ByteChannel $first;
            final /* synthetic */ int $read;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C01811(ByteChannel byteChannel, byte[] bArr, int i, Continuation<? super C01811> continuation) {
                super(2, continuation);
                this.$first = byteChannel;
                this.$buffer = bArr;
                this.$read = i;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C01811(this.$first, this.$buffer, this.$read, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C01811) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    this.label = 1;
                    if (ByteWriteChannelOperationsKt.writeFully(this.$first, this.$buffer, 0, this.$read, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }

        /* JADX INFO: renamed from: io.ktor.util.ByteChannelsKt$split$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: ByteChannels.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
        @DebugMetadata(c = "io.ktor.util.ByteChannelsKt$split$1$2", f = "ByteChannels.kt", i = {}, l = {31}, m = "invokeSuspend", n = {}, s = {}, v = 1)
        static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ byte[] $buffer;
            final /* synthetic */ int $read;
            final /* synthetic */ ByteChannel $second;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass2(ByteChannel byteChannel, byte[] bArr, int i, Continuation<? super AnonymousClass2> continuation) {
                super(2, continuation);
                this.$second = byteChannel;
                this.$buffer = bArr;
                this.$read = i;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass2(this.$second, this.$buffer, this.$read, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    this.label = 1;
                    if (ByteWriteChannelOperationsKt.writeFully(this.$second, this.$buffer, 0, this.$read, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }
    }

    static final Unit split$lambda$0(ByteChannel byteChannel, ByteChannel byteChannel2, Throwable th) {
        if (th == null) {
            return Unit.INSTANCE;
        }
        byteChannel.cancel(th);
        byteChannel2.cancel(th);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: io.ktor.util.ByteChannelsKt$copyToBoth$1, reason: invalid class name */
    /* JADX INFO: compiled from: ByteChannels.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.util.ByteChannelsKt$copyToBoth$1", f = "ByteChannels.kt", i = {1, 1, 2, 2}, l = {64, 66, 67, 81, 82, 81, 82, 81, 82}, m = "invokeSuspend", n = {"it", "$i$a$-use-ByteChannelsKt$copyToBoth$1$1", "it", "$i$a$-use-ByteChannelsKt$copyToBoth$1$1"}, s = {"L$4", "I$0", "L$4", "I$0"}, v = 1)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ ByteWriteChannel $first;
        final /* synthetic */ ByteWriteChannel $second;
        final /* synthetic */ ByteReadChannel $this_copyToBoth;
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(ByteReadChannel byteReadChannel, ByteWriteChannel byteWriteChannel, ByteWriteChannel byteWriteChannel2, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$this_copyToBoth = byteReadChannel;
            this.$first = byteWriteChannel;
            this.$second = byteWriteChannel2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$this_copyToBoth, this.$first, this.$second, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:28:0x008a A[Catch: all -> 0x007c, TryCatch #2 {all -> 0x007c, blocks: (B:45:0x0108, B:26:0x0082, B:28:0x008a, B:30:0x0092, B:32:0x009a, B:35:0x00b6, B:50:0x0110, B:51:0x0113, B:52:0x0114, B:60:0x0145, B:21:0x0078, B:44:0x0106, B:36:0x00bf, B:43:0x00fd, B:48:0x010e), top: B:83:0x0007, inners: #3, #5 }] */
        /* JADX WARN: Code duplicated, block: B:39:0x00dc  */
        /* JADX WARN: Code duplicated, block: B:40:0x00de A[Catch: all -> 0x004d, PHI: r1 r3 r4 r5 r7 r10
          0x00de: PHI (r1v16 io.ktor.utils.io.ByteReadChannel) = (r1v12 io.ktor.utils.io.ByteReadChannel), (r1v17 io.ktor.utils.io.ByteReadChannel) binds: [B:18:0x0069, B:38:0x00da] A[DONT_GENERATE, DONT_INLINE]
          0x00de: PHI (r3v9 io.ktor.utils.io.ByteWriteChannel) = (r3v6 io.ktor.utils.io.ByteWriteChannel), (r3v10 io.ktor.utils.io.ByteWriteChannel) binds: [B:18:0x0069, B:38:0x00da] A[DONT_GENERATE, DONT_INLINE]
          0x00de: PHI (r4v6 io.ktor.utils.io.ByteWriteChannel) = (r4v3 io.ktor.utils.io.ByteWriteChannel), (r4v7 io.ktor.utils.io.ByteWriteChannel) binds: [B:18:0x0069, B:38:0x00da] A[DONT_GENERATE, DONT_INLINE]
          0x00de: PHI (r5v7 java.lang.AutoCloseable) = (r5v3 java.lang.AutoCloseable), (r5v9 java.lang.AutoCloseable) binds: [B:18:0x0069, B:38:0x00da] A[DONT_GENERATE, DONT_INLINE]
          0x00de: PHI (r7v3 int) = (r7v2 int), (r7v4 int) binds: [B:18:0x0069, B:38:0x00da] A[DONT_GENERATE, DONT_INLINE]
          0x00de: PHI (r10v12 kotlinx.io.Source) = (r10v8 kotlinx.io.Source), (r10v18 kotlinx.io.Source) binds: [B:18:0x0069, B:38:0x00da] A[DONT_GENERATE, DONT_INLINE], TRY_LEAVE, TryCatch #4 {all -> 0x004d, blocks: (B:12:0x0048, B:37:0x00c2, B:40:0x00de), top: B:86:0x0048 }] */
        /* JADX WARN: Code duplicated, block: B:52:0x0114 A[Catch: all -> 0x007c, TRY_LEAVE, TryCatch #2 {all -> 0x007c, blocks: (B:45:0x0108, B:26:0x0082, B:28:0x008a, B:30:0x0092, B:32:0x009a, B:35:0x00b6, B:50:0x0110, B:51:0x0113, B:52:0x0114, B:60:0x0145, B:21:0x0078, B:44:0x0106, B:36:0x00bf, B:43:0x00fd, B:48:0x010e), top: B:83:0x0007, inners: #3, #5 }] */
        /* JADX WARN: Code duplicated, block: B:54:0x011c  */
        /* JADX WARN: Code duplicated, block: B:57:0x0136  */
        /* JADX WARN: Code duplicated, block: B:60:0x0145 A[Catch: all -> 0x007c, TRY_ENTER, TRY_LEAVE, TryCatch #2 {all -> 0x007c, blocks: (B:45:0x0108, B:26:0x0082, B:28:0x008a, B:30:0x0092, B:32:0x009a, B:35:0x00b6, B:50:0x0110, B:51:0x0113, B:52:0x0114, B:60:0x0145, B:21:0x0078, B:44:0x0106, B:36:0x00bf, B:43:0x00fd, B:48:0x010e), top: B:83:0x0007, inners: #3, #5 }] */
        /* JADX WARN: Code duplicated, block: B:77:0x01a8  */
        /* JADX WARN: Code restructure failed: missing block: B:41:0x00f9, code lost:
        
            if (io.ktor.utils.io.ByteWriteChannelOperationsKt.writePacket(r3, r6, r9) == r0) goto L76;
         */
        /* JADX WARN: Code restructure failed: missing block: B:58:0x0142, code lost:
        
            if (r9.$second.flushAndClose(r9) == r0) goto L76;
         */
        /* JADX WARN: Code restructure failed: missing block: B:66:0x0175, code lost:
        
            if (r9.$second.flushAndClose(r9) == r0) goto L76;
         */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:41:0x00f9 -> B:44:0x0106). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:43:0x00fd -> B:44:0x0106). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r10) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 450
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.util.ByteChannelsKt.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public static final void copyToBoth(ByteReadChannel byteReadChannel, final ByteWriteChannel first, final ByteWriteChannel second) {
        Intrinsics.checkNotNullParameter(byteReadChannel, "<this>");
        Intrinsics.checkNotNullParameter(first, "first");
        Intrinsics.checkNotNullParameter(second, "second");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getDefault(), null, new AnonymousClass1(byteReadChannel, first, second, null), 2, null).invokeOnCompletion(new Function1() { // from class: io.ktor.util.ByteChannelsKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ByteChannelsKt.copyToBoth$lambda$0(first, second, (Throwable) obj);
            }
        });
    }

    static final Unit copyToBoth$lambda$0(ByteWriteChannel byteWriteChannel, ByteWriteChannel byteWriteChannel2, Throwable th) {
        if (th == null) {
            return Unit.INSTANCE;
        }
        ByteWriteChannelOperationsKt.close(byteWriteChannel, th);
        ByteWriteChannelOperationsKt.close(byteWriteChannel2, th);
        return Unit.INSTANCE;
    }
}
