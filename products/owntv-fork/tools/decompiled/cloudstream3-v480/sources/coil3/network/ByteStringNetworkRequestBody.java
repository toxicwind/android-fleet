package coil3.network;

import java.io.IOException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.Intrinsics;
import okio.BufferedSink;
import okio.ByteString;

/* JADX INFO: compiled from: NetworkClient.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0083@\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0096@¢\u0006\u0004\b\n\u0010\u000bJ\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006\u0014"}, d2 = {"Lcoil3/network/ByteStringNetworkRequestBody;", "Lcoil3/network/NetworkRequestBody;", "bytes", "Lokio/ByteString;", "constructor-impl", "(Lokio/ByteString;)Lokio/ByteString;", "writeTo", "", "sink", "Lokio/BufferedSink;", "writeTo-impl", "(Lokio/ByteString;Lokio/BufferedSink;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "equals", "", "other", "", "hashCode", "", "toString", "", "coil-network-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@JvmInline
final class ByteStringNetworkRequestBody implements NetworkRequestBody {
    private final ByteString bytes;

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ ByteStringNetworkRequestBody m8089boximpl(ByteString byteString) {
        return new ByteStringNetworkRequestBody(byteString);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static ByteString m8090constructorimpl(ByteString byteString) {
        return byteString;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m8091equalsimpl(ByteString byteString, Object obj) {
        return (obj instanceof ByteStringNetworkRequestBody) && Intrinsics.areEqual(byteString, ((ByteStringNetworkRequestBody) obj).getBytes());
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m8092equalsimpl0(ByteString byteString, ByteString byteString2) {
        return Intrinsics.areEqual(byteString, byteString2);
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m8093hashCodeimpl(ByteString byteString) {
        return byteString.hashCode();
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m8094toStringimpl(ByteString byteString) {
        return "ByteStringNetworkRequestBody(bytes=" + byteString + ')';
    }

    public boolean equals(Object other) {
        return m8091equalsimpl(this.bytes, other);
    }

    public int hashCode() {
        return m8093hashCodeimpl(this.bytes);
    }

    public String toString() {
        return m8094toStringimpl(this.bytes);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ ByteString getBytes() {
        return this.bytes;
    }

    private /* synthetic */ ByteStringNetworkRequestBody(ByteString byteString) {
        this.bytes = byteString;
    }

    @Override // coil3.network.NetworkRequestBody
    public Object writeTo(BufferedSink bufferedSink, Continuation<? super Unit> continuation) {
        return m8095writeToimpl(this.bytes, bufferedSink, continuation);
    }

    /* JADX INFO: renamed from: writeTo-impl, reason: not valid java name */
    public static Object m8095writeToimpl(ByteString byteString, BufferedSink bufferedSink, Continuation<? super Unit> continuation) throws IOException {
        bufferedSink.write(byteString);
        return Unit.INSTANCE;
    }
}
