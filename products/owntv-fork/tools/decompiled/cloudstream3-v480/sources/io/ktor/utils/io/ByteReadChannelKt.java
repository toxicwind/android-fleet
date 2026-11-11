package io.ktor.utils.io;

import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ByteReadChannel.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u001a\u0011\u0010\u0002\u001a\u00020\u0001*\u00020\u0000¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lio/ktor/utils/io/ByteReadChannel;", "", "cancel", "(Lio/ktor/utils/io/ByteReadChannel;)V", "ktor-io"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ByteReadChannelKt {
    public static final void cancel(ByteReadChannel byteReadChannel) {
        Intrinsics.checkNotNullParameter(byteReadChannel, "<this>");
        byteReadChannel.cancel(new IOException("Channel was cancelled"));
    }
}
