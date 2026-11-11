package io.ktor.utils.io;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: compiled from: ByteReadChannelOperations.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.utils.io.ByteReadChannelOperationsKt", f = "ByteReadChannelOperations.kt", i = {0, 0, 0}, l = {716}, m = "internalReadLineTo$discardCrlfOrCr", n = {"$this$internalReadLineTo_u24discardCrlfOrCr", "$this_internalReadLineTo", "$lenientLineEnding"}, s = {"L$0", "L$1", "Z$0"}, v = 1)
final class ByteReadChannelOperationsKt$internalReadLineTo$discardCrlfOrCr$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    boolean Z$0;
    int label;
    /* synthetic */ Object result;

    ByteReadChannelOperationsKt$internalReadLineTo$discardCrlfOrCr$1(Continuation<? super ByteReadChannelOperationsKt$internalReadLineTo$discardCrlfOrCr$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ByteReadChannelOperationsKt.internalReadLineTo$discardCrlfOrCr(null, null, false, this);
    }
}
