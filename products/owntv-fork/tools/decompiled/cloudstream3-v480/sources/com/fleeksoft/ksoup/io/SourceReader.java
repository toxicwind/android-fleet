package com.fleeksoft.ksoup.io;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.schabi.newpipe.extractor.services.peertube.PeertubeParsingHelper;

/* JADX INFO: compiled from: SourceReader.kt */
/* JADX INFO: loaded from: classes5.dex */
@Deprecated(level = DeprecationLevel.WARNING, message = "SourceReader is deprecated, use com.fleeksoft.io.InputStream instead.", replaceWith = @ReplaceWith(expression = "com.fleeksoft.io.InputStream", imports = {}))
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\bg\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&J\b\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\bH&J$\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\n2\b\b\u0002\u0010\u000e\u001a\u00020\b2\b\b\u0002\u0010\u000f\u001a\u00020\bH&J\b\u0010\u0010\u001a\u00020\nH&J\b\u0010\u0011\u001a\u00020\u0012H&J\b\u0010\u0013\u001a\u00020\u0003H&¨\u0006\u0015À\u0006\u0003"}, d2 = {"Lcom/fleeksoft/ksoup/io/SourceReader;", "", "mark", "", "readLimit", "", "reset", "readInt", "", "readBytes", "", PeertubeParsingHelper.COUNT_KEY, "read", "bytes", TypedValues.CycleType.S_WAVE_OFFSET, "length", "readAllBytes", "exhausted", "", "close", "Companion", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface SourceReader {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    void close();

    boolean exhausted();

    void mark(long readLimit);

    int read(byte[] bytes, int offset, int length);

    byte[] readAllBytes();

    byte[] readBytes(int count);

    int readInt();

    void reset();

    /* JADX INFO: renamed from: com.fleeksoft.ksoup.io.SourceReader$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: SourceReader.kt */
    public final /* synthetic */ class CC {
        static {
            Companion companion = SourceReader.INSTANCE;
        }

        public static /* synthetic */ int read$default(SourceReader sourceReader, byte[] bArr, int i, int i2, int i3, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: read");
            }
            if ((i3 & 2) != 0) {
                i = 0;
            }
            if ((i3 & 4) != 0) {
                i2 = bArr.length;
            }
            return sourceReader.read(bArr, i, i2);
        }
    }

    /* JADX INFO: compiled from: SourceReader.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final class DefaultImpls {
    }

    /* JADX INFO: compiled from: SourceReader.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/fleeksoft/ksoup/io/SourceReader$Companion;", "", "<init>", "()V", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }
    }
}
