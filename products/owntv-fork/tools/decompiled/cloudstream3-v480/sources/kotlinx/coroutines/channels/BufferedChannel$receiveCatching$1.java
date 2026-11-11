package kotlinx.coroutines.channels;

import androidx.media3.exoplayer.upstream.CmcdData;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: compiled from: BufferedChannel.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "kotlinx.coroutines.channels.BufferedChannel", f = "BufferedChannel.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, l = {736}, m = "receiveCatching-JP2dKIU$suspendImpl", n = {"$this", "this_$iv", "segment$iv", "updCellResult$iv", "segm", "$i$f$receiveImpl", "r$iv", "id$iv", "i$iv", "r", CmcdData.OBJECT_TYPE_INIT_SEGMENT, "$i$a$-receiveImpl-BufferedChannel$receiveCatching$5"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "I$0", "J$0", "J$1", "I$1", "J$2", "I$2", "I$3"}, v = 1)
final class BufferedChannel$receiveCatching$1<E> extends ContinuationImpl {
    int I$0;
    int I$1;
    int I$2;
    int I$3;
    long J$0;
    long J$1;
    long J$2;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ BufferedChannel<E> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    BufferedChannel$receiveCatching$1(BufferedChannel<E> bufferedChannel, Continuation<? super BufferedChannel$receiveCatching$1> continuation) {
        super(continuation);
        this.this$0 = bufferedChannel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object objM10486receiveCatchingJP2dKIU$suspendImpl = BufferedChannel.m10486receiveCatchingJP2dKIU$suspendImpl(this.this$0, this);
        return objM10486receiveCatchingJP2dKIU$suspendImpl == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objM10486receiveCatchingJP2dKIU$suspendImpl : ChannelResult.m10495boximpl(objM10486receiveCatchingJP2dKIU$suspendImpl);
    }
}
