package io.ktor.utils.io.jvm.nio;

import io.ktor.utils.io.ByteWriteChannel;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.io.Buffer;
import kotlinx.io.Segment;
import kotlinx.io.SegmentKt;
import kotlinx.io.unsafe.UnsafeBufferOperations;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: WriteSuspendSession.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a=\u0010\b\u001a\u00020\u0004*\u00020\u00002'\u0010\u0007\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0001¢\u0006\u0002\b\u0006H\u0087@¢\u0006\u0004\b\b\u0010\t\u001a*\u0010\r\u001a\u00020\u0004*\u00020\u00002\u0014\b\u0004\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\nH\u0086H¢\u0006\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lio/ktor/utils/io/ByteWriteChannel;", "Lkotlin/Function2;", "Lio/ktor/utils/io/jvm/nio/WriteSuspendSession;", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "block", "writeSuspendSession", "(Lio/ktor/utils/io/ByteWriteChannel;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlin/Function1;", "Ljava/nio/ByteBuffer;", "", "writeWhile", "(Lio/ktor/utils/io/ByteWriteChannel;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-io"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class WriteSuspendSessionKt {

    /* JADX INFO: renamed from: io.ktor.utils.io.jvm.nio.WriteSuspendSessionKt$writeSuspendSession$1, reason: invalid class name */
    /* JADX INFO: compiled from: WriteSuspendSession.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.utils.io.jvm.nio.WriteSuspendSessionKt", f = "WriteSuspendSession.kt", i = {0, 0, 1, 1, 2, 2}, l = {43, 45, 45}, m = "writeSuspendSession", n = {"$this$writeSuspendSession", "block", "$this$writeSuspendSession", "block", "$this$writeSuspendSession", "block"}, s = {"L$0", "L$1", "L$0", "L$1", "L$0", "L$1"}, v = 1)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return WriteSuspendSessionKt.writeSuspendSession(null, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.jvm.nio.WriteSuspendSessionKt$writeWhile$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: WriteSuspendSession.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 176)
    @DebugMetadata(c = "io.ktor.utils.io.jvm.nio.WriteSuspendSessionKt", f = "WriteSuspendSession.kt", i = {0, 0, 0, 0}, l = {59}, m = "writeWhile", n = {"$this$writeWhile", "block", ES6Iterator.DONE_PROPERTY, "$i$f$writeWhile"}, s = {"L$0", "L$1", "L$2", "I$0"}, v = 1)
    static final class C10351 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C10351(Continuation<? super C10351> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return WriteSuspendSessionKt.writeWhile(null, null, this);
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0088, code lost:
    
        if (r7 == r1) goto L32;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v0, types: [io.ktor.utils.io.ByteWriteChannel, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v16 */
    /* JADX WARN: Type inference failed for: r7v17 */
    /* JADX WARN: Type inference failed for: r7v3, types: [io.ktor.utils.io.ByteWriteChannel, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v4, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v0, types: [java.lang.Object, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2<? super io.ktor.utils.io.jvm.nio.WriteSuspendSession, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object>] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v12 */
    /* JADX WARN: Type inference failed for: r8v13 */
    /* JADX WARN: Type inference failed for: r8v2, types: [io.ktor.utils.io.ByteWriteChannel, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v4, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v5, types: [java.lang.Object] */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "writeSuspendSession deprecated, use writeWhile instead", replaceWith = @kotlin.ReplaceWith(expression = "writeWhile { buffer -> }", imports = {}))
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object writeSuspendSession(io.ktor.utils.io.ByteWriteChannel r7, kotlin.jvm.functions.Function2<? super io.ktor.utils.io.jvm.nio.WriteSuspendSession, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> r8, kotlin.coroutines.Continuation<? super kotlin.Unit> r9) throws java.lang.Throwable {
        /*
            boolean r0 = r9 instanceof io.ktor.utils.io.jvm.nio.WriteSuspendSessionKt.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r9
            io.ktor.utils.io.jvm.nio.WriteSuspendSessionKt$writeSuspendSession$1 r0 = (io.ktor.utils.io.jvm.nio.WriteSuspendSessionKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L19
        L14:
            io.ktor.utils.io.jvm.nio.WriteSuspendSessionKt$writeSuspendSession$1 r0 = new io.ktor.utils.io.jvm.nio.WriteSuspendSessionKt$writeSuspendSession$1
            r0.<init>(r9)
        L19:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L5d
            if (r2 == r5) goto L50
            if (r2 == r4) goto L44
            if (r2 == r3) goto L34
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L34:
            java.lang.Object r7 = r0.L$2
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r0.L$1
            kotlin.jvm.functions.Function2 r8 = (kotlin.jvm.functions.Function2) r8
            java.lang.Object r8 = r0.L$0
            io.ktor.utils.io.ByteWriteChannel r8 = (io.ktor.utils.io.ByteWriteChannel) r8
            kotlin.ResultKt.throwOnFailure(r9)
            goto Laa
        L44:
            java.lang.Object r7 = r0.L$1
            kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7
            java.lang.Object r7 = r0.L$0
            io.ktor.utils.io.ByteWriteChannel r7 = (io.ktor.utils.io.ByteWriteChannel) r7
            kotlin.ResultKt.throwOnFailure(r9)
            goto L8b
        L50:
            java.lang.Object r7 = r0.L$1
            r8 = r7
            kotlin.jvm.functions.Function2 r8 = (kotlin.jvm.functions.Function2) r8
            java.lang.Object r7 = r0.L$0
            io.ktor.utils.io.ByteWriteChannel r7 = (io.ktor.utils.io.ByteWriteChannel) r7
            kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Throwable -> L8e
            goto L76
        L5d:
            kotlin.ResultKt.throwOnFailure(r9)
            io.ktor.utils.io.jvm.nio.WriteSuspendSession r9 = new io.ktor.utils.io.jvm.nio.WriteSuspendSession     // Catch: java.lang.Throwable -> L8e
            r9.<init>(r7)     // Catch: java.lang.Throwable -> L8e
            r0.L$0 = r7     // Catch: java.lang.Throwable -> L8e
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)     // Catch: java.lang.Throwable -> L8e
            r0.L$1 = r2     // Catch: java.lang.Throwable -> L8e
            r0.label = r5     // Catch: java.lang.Throwable -> L8e
            java.lang.Object r9 = r8.invoke(r9, r0)     // Catch: java.lang.Throwable -> L8e
            if (r9 != r1) goto L76
            goto La9
        L76:
            java.lang.Object r9 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)
            r0.L$0 = r9
            java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)
            r0.L$1 = r8
            r0.label = r4
            java.lang.Object r7 = r7.flush(r0)
            if (r7 != r1) goto L8b
            goto La9
        L8b:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        L8e:
            r9 = move-exception
            r6 = r8
            r8 = r7
            r7 = r9
            r9 = r6
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)
            r0.L$0 = r2
            java.lang.Object r9 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r9)
            r0.L$1 = r9
            r0.L$2 = r7
            r0.label = r3
            java.lang.Object r8 = r8.flush(r0)
            if (r8 != r1) goto Laa
        La9:
            return r1
        Laa:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.jvm.nio.WriteSuspendSessionKt.writeSuspendSession(io.ktor.utils.io.ByteWriteChannel, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final Object writeWhile(ByteWriteChannel byteWriteChannel, Function1<? super ByteBuffer, Boolean> function1, Continuation<? super Unit> continuation) {
        C10351 c10351;
        Ref.BooleanRef booleanRef;
        Function1<? super ByteBuffer, Boolean> function2;
        int i;
        if (continuation instanceof C10351) {
            c10351 = (C10351) continuation;
            if ((c10351.label & Integer.MIN_VALUE) != 0) {
                c10351.label -= Integer.MIN_VALUE;
            } else {
                c10351 = new C10351(continuation);
            }
        } else {
            c10351 = new C10351(continuation);
        }
        Object obj = c10351.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c10351.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            booleanRef = new Ref.BooleanRef();
            function2 = function1;
            i = 0;
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i3 = c10351.I$0;
            Ref.BooleanRef booleanRef2 = (Ref.BooleanRef) c10351.L$2;
            Function1<? super ByteBuffer, Boolean> function3 = (Function1) c10351.L$1;
            ByteWriteChannel byteWriteChannel2 = (ByteWriteChannel) c10351.L$0;
            ResultKt.throwOnFailure(obj);
            function2 = function3;
            booleanRef = booleanRef2;
            i = i3;
            byteWriteChannel = byteWriteChannel2;
        }
        while (!booleanRef.element) {
            UnsafeBufferOperations unsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
            Buffer bufferField = byteWriteChannel.getWriteBuffer().getBufferField();
            Segment segmentWritableSegment = bufferField.writableSegment(1);
            byte[] bArrDataAsByteArray = segmentWritableSegment.dataAsByteArray(false);
            int limit = segmentWritableSegment.getLimit();
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArrDataAsByteArray, limit, bArrDataAsByteArray.length - limit);
            Intrinsics.checkNotNull(byteBufferWrap);
            booleanRef.element = !function2.invoke(byteBufferWrap).booleanValue();
            int iPosition = byteBufferWrap.position() - limit;
            if (iPosition == 1) {
                segmentWritableSegment.writeBackData(bArrDataAsByteArray, iPosition);
                segmentWritableSegment.setLimit(segmentWritableSegment.getLimit() + iPosition);
                bufferField.setSizeMut(bufferField.getSizeMut() + ((long) iPosition));
            } else {
                if (iPosition < 0 || iPosition > segmentWritableSegment.getRemainingCapacity()) {
                    throw new IllegalStateException(("Invalid number of bytes written: " + iPosition + ". Should be in 0.." + segmentWritableSegment.getRemainingCapacity()).toString());
                }
                if (iPosition != 0) {
                    segmentWritableSegment.writeBackData(bArrDataAsByteArray, iPosition);
                    segmentWritableSegment.setLimit(segmentWritableSegment.getLimit() + iPosition);
                    bufferField.setSizeMut(bufferField.getSizeMut() + ((long) iPosition));
                } else if (SegmentKt.isEmpty(segmentWritableSegment)) {
                    bufferField.recycleTail();
                }
            }
            c10351.L$0 = byteWriteChannel;
            c10351.L$1 = function2;
            c10351.L$2 = booleanRef;
            c10351.I$0 = i;
            c10351.label = 1;
            if (byteWriteChannel.flush(c10351) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return Unit.INSTANCE;
    }

    private static final Object writeWhile$$forInline(ByteWriteChannel byteWriteChannel, Function1<? super ByteBuffer, Boolean> function1, Continuation<? super Unit> continuation) {
        Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        while (!booleanRef.element) {
            UnsafeBufferOperations unsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
            Buffer bufferField = byteWriteChannel.getWriteBuffer().getBufferField();
            Segment segmentWritableSegment = bufferField.writableSegment(1);
            byte[] bArrDataAsByteArray = segmentWritableSegment.dataAsByteArray(false);
            Integer numValueOf = Integer.valueOf(segmentWritableSegment.getLimit());
            int iIntValue = Integer.valueOf(bArrDataAsByteArray.length).intValue();
            int iIntValue2 = numValueOf.intValue();
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArrDataAsByteArray, iIntValue2, iIntValue - iIntValue2);
            Intrinsics.checkNotNull(byteBufferWrap);
            booleanRef.element = !function1.invoke(byteBufferWrap).booleanValue();
            int iIntValue3 = Integer.valueOf(byteBufferWrap.position() - iIntValue2).intValue();
            if (iIntValue3 == 1) {
                segmentWritableSegment.writeBackData(bArrDataAsByteArray, iIntValue3);
                segmentWritableSegment.setLimit(segmentWritableSegment.getLimit() + iIntValue3);
                bufferField.setSizeMut(bufferField.getSizeMut() + ((long) iIntValue3));
            } else {
                if (iIntValue3 < 0 || iIntValue3 > segmentWritableSegment.getRemainingCapacity()) {
                    throw new IllegalStateException(("Invalid number of bytes written: " + iIntValue3 + ". Should be in 0.." + segmentWritableSegment.getRemainingCapacity()).toString());
                }
                if (iIntValue3 != 0) {
                    segmentWritableSegment.writeBackData(bArrDataAsByteArray, iIntValue3);
                    segmentWritableSegment.setLimit(segmentWritableSegment.getLimit() + iIntValue3);
                    bufferField.setSizeMut(bufferField.getSizeMut() + ((long) iIntValue3));
                } else if (SegmentKt.isEmpty(segmentWritableSegment)) {
                    bufferField.recycleTail();
                }
            }
            byteWriteChannel.flush(continuation);
        }
        return Unit.INSTANCE;
    }
}
