package kotlinx.serialization.json.internal;

import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.schabi.newpipe.extractor.services.peertube.PeertubeParsingHelper;

/* JADX INFO: compiled from: JvmJsonStreams.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0011\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\"\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\tH\u0096\u0080\u0004J\n\u0010\u000e\u001a\u00020\u000fH\u0086\u0080\u0004R\u000f\u0010\u0006\u001a\u00020\u0007X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lkotlinx/serialization/json/internal/JavaStreamSerialReader;", "Lkotlinx/serialization/json/internal/InternalJsonReader;", "stream", "Ljava/io/InputStream;", "<init>", "(Ljava/io/InputStream;)V", "reader", "Lkotlinx/serialization/json/internal/CharsetReader;", "read", "", "buffer", "", "bufferOffset", PeertubeParsingHelper.COUNT_KEY, "release", "", "kotlinx-serialization-json"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class JavaStreamSerialReader implements InternalJsonReader {
    private final CharsetReader reader;

    public JavaStreamSerialReader(InputStream stream) {
        Intrinsics.checkNotNullParameter(stream, "stream");
        this.reader = new CharsetReader(stream, Charsets.UTF_8);
    }

    @Override // kotlinx.serialization.json.internal.InternalJsonReader
    public int read(char[] buffer, int bufferOffset, int count) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        return this.reader.read(buffer, bufferOffset, count);
    }

    public final void release() {
        this.reader.release();
    }
}
