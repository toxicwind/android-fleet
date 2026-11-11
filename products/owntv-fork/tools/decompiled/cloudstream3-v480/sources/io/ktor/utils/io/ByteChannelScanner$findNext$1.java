package io.ktor.utils.io;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: compiled from: ByteChannelScanner.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.utils.io.ByteChannelScanner", f = "ByteChannelScanner.kt", i = {0, 1, 2, 3}, l = {53, 55, 58, 70}, m = "findNext$ktor_io", n = {"ignoreMissing", "ignoreMissing", "ignoreMissing", "ignoreMissing"}, s = {"Z$0", "Z$0", "Z$0", "Z$0"}, v = 1)
final class ByteChannelScanner$findNext$1 extends ContinuationImpl {
    boolean Z$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ByteChannelScanner this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ByteChannelScanner$findNext$1(ByteChannelScanner byteChannelScanner, Continuation<? super ByteChannelScanner$findNext$1> continuation) {
        super(continuation);
        this.this$0 = byteChannelScanner;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.findNext$ktor_io(false, this);
    }
}
