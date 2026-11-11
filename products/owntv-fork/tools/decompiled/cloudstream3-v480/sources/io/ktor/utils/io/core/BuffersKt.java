package io.ktor.utils.io.core;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.Buffer;
import kotlinx.io.SourcesKt;
import org.schabi.newpipe.extractor.services.peertube.PeertubeParsingHelper;

/* JADX INFO: compiled from: Buffers.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a\u001b\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u0013\u0010\u0007\u001a\u00020\u0006*\u00020\u0000H\u0000¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lkotlinx/io/Buffer;", "", PeertubeParsingHelper.COUNT_KEY, "", "readBytes", "(Lkotlinx/io/Buffer;I)[B", "", "isEmpty", "(Lkotlinx/io/Buffer;)Z", "ktor-io"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class BuffersKt {
    public static /* synthetic */ byte[] readBytes$default(Buffer buffer, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = (int) buffer.getSizeMut();
        }
        return readBytes(buffer, i);
    }

    public static final byte[] readBytes(Buffer buffer, int i) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        return SourcesKt.readByteArray(buffer, i);
    }

    public static final boolean isEmpty(Buffer buffer) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        return buffer.getSizeMut() == 0;
    }
}
