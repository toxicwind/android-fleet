package io.ktor.utils.io;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.RawSource;

/* JADX INFO: compiled from: ByteReadChannelSource.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0011\u0010\u0002\u001a\u00020\u0001*\u00020\u0000¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lio/ktor/utils/io/ByteReadChannel;", "Lkotlinx/io/RawSource;", "asSource", "(Lio/ktor/utils/io/ByteReadChannel;)Lkotlinx/io/RawSource;", "ktor-io"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ByteReadChannelSourceKt {
    public static final RawSource asSource(ByteReadChannel byteReadChannel) {
        Intrinsics.checkNotNullParameter(byteReadChannel, "<this>");
        return new ByteReadChannelSource(byteReadChannel);
    }
}
