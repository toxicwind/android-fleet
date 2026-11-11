package coil3.network;

import coil3.disk.DiskCache;
import coil3.fetch.SourceFetchResult;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: NetworkFetcher.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "Lcoil3/fetch/SourceFetchResult;", "response", "Lcoil3/network/NetworkResponse;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "coil3.network.NetworkFetcher$fetch$fetchResult$1", f = "NetworkFetcher.kt", i = {0, 1}, l = {76, 87}, m = "invokeSuspend", n = {"response", "response"}, s = {"L$0", "L$0"})
final class NetworkFetcher$fetch$fetchResult$1 extends SuspendLambda implements Function2<NetworkResponse, Continuation<? super SourceFetchResult>, Object> {
    final /* synthetic */ Ref.ObjectRef<NetworkResponse> $cacheResponse;
    final /* synthetic */ NetworkRequest $networkRequest;
    final /* synthetic */ Ref.ObjectRef<DiskCache.Snapshot> $snapshot;
    /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ NetworkFetcher this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    NetworkFetcher$fetch$fetchResult$1(Ref.ObjectRef<DiskCache.Snapshot> objectRef, NetworkFetcher networkFetcher, Ref.ObjectRef<NetworkResponse> objectRef2, NetworkRequest networkRequest, Continuation<? super NetworkFetcher$fetch$fetchResult$1> continuation) {
        super(2, continuation);
        this.$snapshot = objectRef;
        this.this$0 = networkFetcher;
        this.$cacheResponse = objectRef2;
        this.$networkRequest = networkRequest;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        NetworkFetcher$fetch$fetchResult$1 networkFetcher$fetch$fetchResult$1 = new NetworkFetcher$fetch$fetchResult$1(this.$snapshot, this.this$0, this.$cacheResponse, this.$networkRequest, continuation);
        networkFetcher$fetch$fetchResult$1.L$0 = obj;
        return networkFetcher$fetch$fetchResult$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(NetworkResponse networkResponse, Continuation<? super SourceFetchResult> continuation) {
        return ((NetworkFetcher$fetch$fetchResult$1) create(networkResponse, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x00b4, code lost:
    
        if (r13 == r0) goto L25;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v3, types: [T, coil3.network.NetworkResponse] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r13) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 228
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: coil3.network.NetworkFetcher$fetch$fetchResult$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
